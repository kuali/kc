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
package org.kuali.kra.iacuc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService;
import org.kuali.kra.protocol.ProtocolSubmissionLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;


/**
 * 
 * This class is to create action links and inquiry url for protocolsubmission lookup. 
 */
@SuppressWarnings({ "serial", "unchecked" })
public class IacucProtocolSubmissionLookupableHelperServiceImpl extends ProtocolSubmissionLookupableHelperServiceImpl {

    protected IacucProtocolSubmitActionService protocolSubmitActionService;
 
    public void setProtocolSubmitActionService(IacucProtocolSubmitActionService protocolSubmitActionService) {
        this.protocolSubmitActionService = protocolSubmitActionService;
    }
    
    /**
     * This method is to add 'edit' and 'view' link for protocol submission lookup result.
     * 
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.krad.bo.BusinessObject,
     *      java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), ((IacucProtocolSubmission) businessObject).getProtocol(),
                PermissionConstants.MODIFY_IACUC_PROTOCOL)) {
            AnchorHtmlData editHtmlData = getViewLink((IacucProtocolSubmission) businessObject);
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
        }
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), ((IacucProtocolSubmission) businessObject).getProtocol(),
                PermissionConstants.VIEW_IACUC_PROTOCOL)) {
            htmlDataList.add(getViewLink((IacucProtocolSubmission) businessObject));
        }
        return htmlDataList;
    }
  
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        List<IacucProtocolSubmission> submissionLookupData=(List<IacucProtocolSubmission>)super.getSearchResults(fieldValues);
        try{
            if((submissionLookupData!=null)&& (submissionLookupData.size()>0)){                            
                 submissionLookupData=protocolSubmitActionService.getProtocolSubmissionsLookupData(submissionLookupData);   
            }             
        }catch (Exception e) {
           LOG.info("submissionLookupData Lookup : " + submissionLookupData.size() + " parsing error");
        }            
        return submissionLookupData;
    }   
    
    @Override
    protected String getHtmlAction() {
        return "iacucProtocolProtocol.do";
    }

    @Override
    protected String getDocumentTypeName() {
        return "IacucProtocolDocument";
    }

}
