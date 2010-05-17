/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;

/**
 * This class is used to control behavior of Institutional Proposal lookups. Depending
 * on where the lookup is coming from, we may need to add custom action links and/or
 * filter the lookup results.
 */
public class InstitutionalProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 1L;
    
    private static final String MERGE_PROPOSAL_LOG_ACTION = "mergeProposalLog.do";
    private static final String AWARD_HOME_ACTION = "awardHome.do";
    
    private boolean includeMainSearchCustomActionUrls;
    private boolean includeMergeCustomActionUrls;
    private BusinessObjectService businessObjectService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /* 
     * Overriding this to only return the currently Active version of a proposal 
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        
        configureCustomActions(fieldValues);
        
        fieldValues.remove(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING);
        fieldValues.put(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING, VersionStatus.ACTIVE.toString());
        
        Map<String, String> formProps = new HashMap<String, String>();
        if (!StringUtils.isEmpty(fieldValues.get("lookupUnit.unitName"))) {
            formProps.put("units.unit.unitName", fieldValues.get("lookupUnit.unitName"));
        }
        if (!StringUtils.isEmpty(fieldValues.get("lookupUnit.unitNumber"))) {
            formProps.put("units.unitNumber", fieldValues.get("lookupUnit.unitNumber"));
        }
        fieldValues.remove("lookupUnit.unitNumber");
        fieldValues.remove("lookupUnit.unitName");
        if (!formProps.isEmpty()) {
            List<Long> ids = new ArrayList<Long>();
            Collection<InstitutionalProposalPerson> persons = getLookupService().findCollectionBySearch(InstitutionalProposalPerson.class, formProps);
            if (persons.isEmpty()) {
                return new ArrayList<InstitutionalProposal>();
            }
            for (InstitutionalProposalPerson person : persons) {
                ids.add(person.getInstitutionalProposalContactId());
            }
            fieldValues.put("projectPersons.institutionalProposalContactId", StringUtils.join(ids, '|'));
        }
        
        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);
        
        if (lookupIsFromAward(fieldValues)) {
            filterAlreadyLinkedProposals(searchResults, fieldValues);
            filterApprovedPendingSubmitProposals(searchResults);
            filterInvalidProposalStatus(searchResults);
        }
        
        return searchResults;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (includeMainSearchCustomActionUrls) {
            htmlDataList.add(getViewLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument()));
        } 
        if (includeMergeCustomActionUrls) {
            htmlDataList.add(getSelectLink((InstitutionalProposal) businessObject));
        }
        htmlDataList.add(getMedusaLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument(), false));
        return htmlDataList;
    }

    @Override
    protected String getHtmlAction() {
        return "institutionalProposalHome.do";
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "InstitutionalProposalDocument";
    }
    
    @Override
    protected String getKeyFieldName() {
        return InstitutionalProposal.PROPOSAL_NUMBER_PROPERTY_STRING;
    }
    
    private boolean lookupIsFromAward(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get(KNSConstants.BACK_LOCATION);
        return returnLocation != null && returnLocation.contains(AWARD_HOME_ACTION);
    }
    
    @SuppressWarnings("unchecked")
    private void filterAlreadyLinkedProposals(List<? extends BusinessObject> searchResults, Map<String, String> fieldValues) {
        List<Long> linkedProposals = (List<Long>) GlobalVariables.getUserSession().retrieveObject(Constants.LINKED_FUNDING_PROPOSALS_KEY);
        if (linkedProposals == null) { return; }
        int indexToRemove = -1;
        for (Long linkedProposalId : linkedProposals) {
            for (int j = 0; j < searchResults.size(); j++) {
                InstitutionalProposal institutionalProposal = (InstitutionalProposal) searchResults.get(j);
                if (linkedProposalId.equals(institutionalProposal.getProposalId())) {
                    indexToRemove = j;
                    break;
                }
            }
            if (indexToRemove != -1) {
                searchResults.remove(indexToRemove);
                indexToRemove = -1;
            }
        }
    }
    
    /*
     * This method is filter out INSP which is generated from PD whose ProposeStateType is "Approval Pending Submitted"
     */
    private void filterApprovedPendingSubmitProposals(List<? extends BusinessObject> searchResults) {
        List<BusinessObject> removeResults = new ArrayList<BusinessObject>();
        for (int j = 0; j < searchResults.size(); j++) {
            if (isDevelopmentProposalAppPendingSubmitted((InstitutionalProposal) searchResults.get(j))) {
                removeResults.add(searchResults.get(j));
            }
        }
        if (!removeResults.isEmpty()) {
            searchResults.removeAll(removeResults);
        }
    }

    /*
     * This method is to filter for valid status codes of 1,2,6
     */
    private void filterInvalidProposalStatus(List<? extends BusinessObject> searchResults) {
        List<BusinessObject> removeResults = new ArrayList<BusinessObject>();
        String[] validStatuses = new String[] {"1","2","6"};        
        List<String> validCodesToFilter = Arrays.asList(validStatuses);   
        for (int j = 0; j < searchResults.size(); j++) {
            if (!validCodesToFilter.contains(((InstitutionalProposal) searchResults.get(j)).getStatusCode().toString())) {
                removeResults.add(searchResults.get(j));
            }
        }
        if (!removeResults.isEmpty()) {
            searchResults.removeAll(removeResults);
        }
    }

    /*
     * Find if any proposal associate with this INSP has 'Approval Pending Submitted' proposal state type
     */
    private boolean isDevelopmentProposalAppPendingSubmitted(InstitutionalProposal ip) {
        boolean isApprovePending = false;
        Collection<DevelopmentProposal> devProposals = getDevelopmentProposals(ip);
        for (DevelopmentProposal developmentProposal : devProposals) {
            if ("5".equals(developmentProposal.getProposalStateTypeCode())) {
                isApprovePending = true;
                break;
            }
        }
        return isApprovePending;
    }

    /*
     * find any version of IP that has PD with approve pending
     */
    private Collection<DevelopmentProposal> getDevelopmentProposals(InstitutionalProposal instProposal) {
        //find any dev prop linked to any version of this inst prop
        Collection<DevelopmentProposal> devProposals = new ArrayList<DevelopmentProposal>();
        Collection<InstitutionalProposal> proposalVersions = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", instProposal.getProposalNumber()));
        for (InstitutionalProposal ip : proposalVersions) {
            Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("instProposalId", ip.getProposalId()));
            for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
                proposalAdminDetail.refreshReferenceObject("developmentProposal");
                devProposals.add(proposalAdminDetail.getDevelopmentProposal());
            }
        }
        return devProposals;
    }

    private Map<String, Object> getFieldValues(String key, Object value){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(key, value);
        return fieldValues;
    }

    /* 
     * Determine whether lookup is being called from a location that shouldn't include the custom action links
     */
    private void configureCustomActions(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get(KNSConstants.BACK_LOCATION);
        if (returnLocation != null) {
            if (returnLocation.contains(AWARD_HOME_ACTION)) {
                includeMainSearchCustomActionUrls = false;
                includeMergeCustomActionUrls = false;
            } else if (returnLocation.contains(MERGE_PROPOSAL_LOG_ACTION)) {
                includeMainSearchCustomActionUrls = false;
                includeMergeCustomActionUrls = true;
            } else {
                includeMainSearchCustomActionUrls = true;
                includeMergeCustomActionUrls = false;
            }
        } else {
            includeMainSearchCustomActionUrls = false;
            includeMergeCustomActionUrls = false;
        }
    }
    
    private AnchorHtmlData getSelectLink(InstitutionalProposal institutionalProposal) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("select");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "mergeToInstitutionalProposal");
        parameters.put("institutionalProposalNumber", institutionalProposal.getProposalNumber());
        String href  = UrlFactory.parameterizeUrl("../" + MERGE_PROPOSAL_LOG_ACTION, parameters);
        htmlData.setHref(href);
        return htmlData;
    }

}
