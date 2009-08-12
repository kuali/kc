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

import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;

public class InstitutionalProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 1L;
    private boolean suppressCustomActionUrls;

    @Override
    // Overriding this to only return the currently Active version of a proposal
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        
        suppressCustomActionUrls = suppressCustomActionUrls(fieldValues);
        
        //fieldValues.remove("statusCode");
        //fieldValues.put("statusCode", "2");  //TODO system parm
        return super.getSearchResults(fieldValues);
    }

    /**
     * add 'copy' link to actions list
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (!suppressCustomActionUrls) {
        //if(getProtocolAuthorizationService().hasPermission(getUserName(), (Protocol) businessObject, PermissionConstants.MODIFY_PROTOCOL)) {
            //htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            // Chnage "edit" to edit same document, NOT initializing a new Doc
            AnchorHtmlData editHtmlData = getViewLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument());
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
            //AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            //InstitutionalProposalDocument document = ((InstitutionalProposal) businessObject).getInstitutionalProposalDocument();
            //htmlData.setHref("../DocCopyHandler.do?docId=" + document.getDocumentNumber()
            //        + "&command=displayDocSearchView&documentTypeName=" + getDocumentTypeName());
            //htmlDataList.add(htmlData);
        //}
        //if(getProtocolAuthorizationService().hasPermission(getUserName(), (Protocol) businessObject, PermissionConstants.VIEW_PROTOCOL)) {
            htmlDataList.add(getViewLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument()));
        //}
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
        return "proposalNumber";
    }
    
    // Determine whether lookup is being called from a location that shouldn't include the custom action links
    protected boolean suppressCustomActionUrls(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get("backLocation");
        return returnLocation != null && returnLocation.contains("awardHome.do");
    }

}
