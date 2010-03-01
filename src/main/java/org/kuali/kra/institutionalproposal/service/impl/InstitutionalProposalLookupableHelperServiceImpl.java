/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;
import org.springframework.util.StringUtils;

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

    /* 
     * Overriding this to only return the currently Active version of a proposal 
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        
        configureCustomActions(fieldValues);
        
        fieldValues.remove(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING);
        fieldValues.put(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING, VersionStatus.ACTIVE.toString());
        
        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);
        
        if (lookupIsFromAward(fieldValues)) {
            filterAlreadyLinkedProposals(searchResults, fieldValues);
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
