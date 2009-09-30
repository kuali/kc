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
package org.kuali.kra.institutionalproposal.proposallog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;

/**
 * Lookupable helper service used for proposal log lookup
 */     
public class ProposalLogLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = -7638045643796948730L;
    
    private boolean isLookupForProposalCreation;
    
    /* 
     * Overriding this to only return the currently Active version of a proposal 
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        
        checkIsLookupForProposalCreation(fieldValues);
        
        if (isLookupForProposalCreation) {
            fieldValues.remove("logStatus");
            fieldValues.put("logStatus", ProposalLogUtils.getProposalLogPendingStatusCode());
        }
        
        return super.getSearchResults(fieldValues);
    }
    
    /**
     * create 'merge' link
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (isLookupForProposalCreation) {
            htmlDataList.add(getSelectLinkForProposalCreation((ProposalLog) businessObject));
        } else {
            htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            if (isUnmergedTemporaryLog((ProposalLog) businessObject)) {
                htmlDataList.add(getMergeLink(((ProposalLog) businessObject).getProposalNumber()));
            }
        }
        return htmlDataList;
    }
    
    protected AnchorHtmlData getSelectLinkForProposalCreation(ProposalLog proposalLog) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("select");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "docHandler");
        parameters.put(KNSConstants.PARAMETER_COMMAND, "initiate");
        parameters.put(KNSConstants.DOCUMENT_TYPE_NAME, "InstitutionalProposalDocument");
        parameters.put("proposalNumber", proposalLog.getProposalNumber());
        String href  = UrlFactory.parameterizeUrl("../institutionalProposalHome.do", parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }
    
    protected AnchorHtmlData getMergeLink(String proposalNumber) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("merge");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "pageEntry");
        parameters.put("proposalLogNumber", proposalNumber);
        String href  = UrlFactory.parameterizeUrl("../mergeProposalLog.do", parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }
    
    private boolean isUnmergedTemporaryLog(ProposalLog proposalLog) {
        return ProposalLogUtils.getProposalLogTemporaryTypeCode().equals(proposalLog.getProposalLogTypeCode())
            && !ProposalLogUtils.getProposalLogMergedStatusCode().equals(proposalLog.getLogStatus());
    }
    
    protected void checkIsLookupForProposalCreation(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get("backLocation");
        if (returnLocation.contains("institutionalProposalHome.do")) {
            isLookupForProposalCreation = true;
        }
    }
    
}
