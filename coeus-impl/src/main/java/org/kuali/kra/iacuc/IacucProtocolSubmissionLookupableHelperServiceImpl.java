/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.iacuc;

import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolSubmissionLookupableHelperServiceImplBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is to create action links and inquiry url for protocolsubmission lookup. 
 */
@SuppressWarnings({ "serial", "unchecked" })
public class IacucProtocolSubmissionLookupableHelperServiceImpl extends ProtocolSubmissionLookupableHelperServiceImplBase {

    protected IacucProtocolSubmitActionService protocolSubmitActionService;
 
    public void setProtocolSubmitActionService(IacucProtocolSubmitActionService protocolSubmitActionService) {
        this.protocolSubmitActionService = protocolSubmitActionService;
    }
    public IacucProtocolSubmitActionService getIacucProtocolSubmitActionService() {
        return this.protocolSubmitActionService;
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
                /**
                 * The function call below simply returns NULL at this time, which breaks this search functionality, so commenting out.
                 * I left a note on the function to see this code when the function is implemented.
                 */
                 //submissionLookupData=getIacucProtocolSubmitActionService().getProtocolSubmissionsLookupData(submissionLookupData);   
            }             
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
           LOG.info("submissionLookupData Lookup : " + submissionLookupData.size() + " parsing error");
        }
        for (IacucProtocolSubmission submission : submissionLookupData) {
            if (submission.getCommitteeSchedule() == null) {
                submission.setCommitteeSchedule(new IacucCommitteeSchedule());
            }
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
    
    @Override
    public Class getBusinessObjectClass() {
        return IacucProtocolSubmission.class;
    }

}
