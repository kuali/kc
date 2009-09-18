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
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;

public class InstitutionalProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 1L;
    
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
        return super.getSearchResults(fieldValues);
    }

    /**
     * add 'copy' link to actions list
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (includeMainSearchCustomActionUrls) {
            htmlDataList.add(getViewLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument()));
        } if (includeMergeCustomActionUrls) {
            htmlDataList.add(getSelectLink((InstitutionalProposal) businessObject));
        }
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
    
    // Determine whether lookup is being called from a location that shouldn't include the custom action links
    protected void configureCustomActions(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get("backLocation");
        if (returnLocation.contains("awardHome.do")) {
            includeMainSearchCustomActionUrls = false;
            includeMergeCustomActionUrls = false;
        } else if (returnLocation.contains("mergeProposalLog.do")) {
            includeMainSearchCustomActionUrls = false;
            includeMergeCustomActionUrls = true;
        } else {
            includeMainSearchCustomActionUrls = true;
            includeMergeCustomActionUrls = false;
        }
    }
    
    protected AnchorHtmlData getSelectLink(InstitutionalProposal institutionalProposal) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("select");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "mergeToInstitutionalProposal");
        parameters.put("institutionalProposalNumber", institutionalProposal.getProposalNumber());
        String href  = UrlFactory.parameterizeUrl("../" + "mergeProposalLog.do", parameters);
        htmlData.setHref(href);
        return htmlData;
    }

}
