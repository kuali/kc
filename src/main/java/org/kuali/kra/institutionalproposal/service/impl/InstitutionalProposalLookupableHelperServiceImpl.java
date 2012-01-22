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
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.document.authorization.InstitutionalProposalDocumentAuthorizer;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

/**
 * This class is used to control behavior of Institutional Proposal lookups. Depending
 * on where the lookup is coming from, we may need to add custom action links and/or
 * filter the lookup results.
 */
public class InstitutionalProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 1L;
    
    private static final String MERGE_PROPOSAL_LOG_ACTION = "mergeProposalLog.do";
    private static final String AWARD_HOME_ACTION = "awardHome.do";
    private static final String OPEN = "open";
    private static final String STAT_PENDING = "1";  // Pending
    private static final String STAT_FUNDED  = "2";  // Funded
    private static final String STAT_REV_REQ = "6";  // Revision Requested
    
    private boolean includeMainSearchCustomActionUrls;
    private boolean includeMergeCustomActionUrls;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    /* 
     * Overriding this to only return the currently Active version of a proposal 
     */
    @Override
    @SuppressWarnings("unchecked")
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
        
        List<InstitutionalProposal> searchResults = (List<InstitutionalProposal>) super.getSearchResultsUnbounded(fieldValues);
      
        if (lookupIsFromAward(fieldValues)) {
            filterAlreadyLinkedProposals(searchResults, fieldValues);
            filterApprovedPendingSubmitProposals(searchResults);
            filterInvalidProposalStatus(searchResults);
        }

        List<InstitutionalProposal> filteredResults = filterForPermissions(searchResults);

        return filteredResults;
    }

    /**
     * This method filters results so that the person doing the lookup only gets back the documents he can view.
     * @param results
     * @return
     */
    protected List<InstitutionalProposal> filterForPermissions(List<InstitutionalProposal> results) {
        Person user = GlobalVariables.getUserSession().getPerson();
        InstitutionalProposalDocumentAuthorizer authorizer = new InstitutionalProposalDocumentAuthorizer();
        List<InstitutionalProposal> filteredResults = new ArrayList<InstitutionalProposal>();
        
        for (InstitutionalProposal institutionalProposal : results) {
            
            String documentNumber = institutionalProposal.getInstitutionalProposalDocument().getDocumentNumber();
            try {
                InstitutionalProposalDocument document = (InstitutionalProposalDocument) documentService.getByDocumentHeaderId(documentNumber);
                
                if (authorizer.canOpen(document, user)) {
                    filteredResults.add(institutionalProposal);
                }
            } catch (WorkflowException e) {
                LOG.warn("Cannot find Document with header id " + documentNumber);
            }
        }

        
        return filteredResults;
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (includeMainSearchCustomActionUrls) {
            htmlDataList.add(getOpenLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument()));
        } 
        if (includeMergeCustomActionUrls) {
            htmlDataList.add(getSelectLink((InstitutionalProposal) businessObject));
        }
        htmlDataList.add(getMedusaLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument(), false));
        return htmlDataList;
    }
    
    protected AnchorHtmlData getOpenLink(Document document) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(OPEN);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", "true");
        parameters.put("docOpenedFromIPSearch", "true");
        parameters.put("docId", document.getDocumentNumber());
        String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;

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
    
    protected boolean lookupIsFromAward(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get(KRADConstants.BACK_LOCATION);
        return returnLocation != null && returnLocation.contains(AWARD_HOME_ACTION);
    }
    
    /* 
     * Filters will set flag in IP indicating that they should not be selectable
     * in an Award-based search if they are already linked, if they are Approval Pending Submitted, or if
     * they are not of status 1, 2, or 4. We do this here (instead of in the IP object itself) because
     * this object knows the origin of the lookup, and can easily determine if the IP is already linked.
     */
    /*
     * This method filters out IP's which are already linked to proposals
     */
    @SuppressWarnings("unchecked")
    protected void filterAlreadyLinkedProposals(List<? extends BusinessObject> searchResults, Map<String, String> fieldValues) {
        List<Long> linkedProposals = (List<Long>) GlobalVariables.getUserSession().retrieveObject(Constants.LINKED_FUNDING_PROPOSALS_KEY);
        if (linkedProposals == null) { return; }
        for (Long linkedProposalId : linkedProposals) {
            for (int j = 0; j < searchResults.size(); j++) {
                InstitutionalProposal institutionalProposal = (InstitutionalProposal) searchResults.get(j);
                if (linkedProposalId.equals(institutionalProposal.getProposalId())) {
                    institutionalProposal.setShowReturnLink(false);
                    break;
                }
            }
        }
    }
    
    /*
     * This method filters out IP's which were generated from PD whose ProposeStateType is "Approval Pending Submitted"
     */
    protected void filterApprovedPendingSubmitProposals(List<? extends BusinessObject> searchResults) {
        for (int j = 0; j < searchResults.size(); j++) {
            if (isDevelopmentProposalAppPendingSubmitted((InstitutionalProposal) searchResults.get(j))) {
                ((InstitutionalProposal)searchResults.get(j)).setShowReturnLink(false);
            }
        }
    }

    /*
     * This method is to filter out IP's not having valid status codes of 1,2,6
     */
    protected void filterInvalidProposalStatus(List<? extends BusinessObject> searchResults) {
        String[] validStatuses = new String[] {STAT_PENDING, STAT_FUNDED, STAT_REV_REQ};        
        List<String> validCodesToFilter = Arrays.asList(validStatuses);   
        for (int j = 0; j < searchResults.size(); j++) {
            if (!validCodesToFilter.contains(((InstitutionalProposal) searchResults.get(j)).getStatusCode().toString())) {
                ((InstitutionalProposal)searchResults.get(j)).setShowReturnLink(false);
            }
        }
    }

    /*
     * Find if any proposal associate with this INSP has 'Approval Pending Submitted' proposal state type
     */
    protected boolean isDevelopmentProposalAppPendingSubmitted(InstitutionalProposal ip) {
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
    @SuppressWarnings("unchecked")
    protected Collection<DevelopmentProposal> getDevelopmentProposals(InstitutionalProposal instProposal) {
        //find any dev prop linked to any version of this inst prop
        Collection<DevelopmentProposal> devProposals = new ArrayList<DevelopmentProposal>();
        List<ProposalAdminDetails> proposalAdminDetails = (List<ProposalAdminDetails>) businessObjectService.findMatchingOrderBy(ProposalAdminDetails.class, 
                                                                getFieldValues("instProposalId", instProposal.getProposalId()), "devProposalNumber", true);
        if(proposalAdminDetails.size() > 0) {
            String latestDevelopmentProposalDocNumber = proposalAdminDetails.get(proposalAdminDetails.size() - 1).getDevProposalNumber();
            DevelopmentProposal devProp = (DevelopmentProposal)businessObjectService.findBySinglePrimaryKey(DevelopmentProposal.class, latestDevelopmentProposalDocNumber);
            devProposals.add(devProp);
        }
        return devProposals;
    }
    
    private String getDevelopmentProposalDocumentNumberFromDescription(String proposalDescription) {
        String[] splitProposalDescription = proposalDescription.split(" ");
        String returnVal = splitProposalDescription[splitProposalDescription.length - 1];
        return returnVal;
    }

    protected Map<String, Object> getFieldValues(String key, Object value){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(key, value);
        return fieldValues;
    }

    /* 
     * Determine whether lookup is being called from a location that shouldn't include the custom action links
     */
    protected void configureCustomActions(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get(KRADConstants.BACK_LOCATION);
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
    
    protected AnchorHtmlData getSelectLink(InstitutionalProposal institutionalProposal) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("select");
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "mergeToInstitutionalProposal");
        parameters.put("institutionalProposalNumber", institutionalProposal.getProposalNumber());
        String href  = UrlFactory.parameterizeUrl("../" + MERGE_PROPOSAL_LOG_ACTION, parameters);
        htmlData.setHref(href);
        return htmlData;
    }

    @Override
    public boolean isResultReturnable(BusinessObject object) {
        InstitutionalProposal institutionalProposal = (InstitutionalProposal)object;
        return institutionalProposal.getShowReturnLink();
    }

}
