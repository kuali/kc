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
package org.kuali.kra.irb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class is to create action links for protocolsubmission lookup.  Also, converts the 
 * search criteria to a couple of reference object field.
 */
public class ProtocolSubmissionLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {
    private KraAuthorizationService kraAuthorizationService;
    private KcPersonService kcPersonService;
    
    /**
     * This method is to add 'edit' and 'view' link for protocol submission lookup result.
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if(kraAuthorizationService.hasPermission(getUserIdentifier(), ((ProtocolSubmission) businessObject).getProtocol(), PermissionConstants.MODIFY_PROTOCOL)) {
            String submissionIdParam = "&submissionId=" + ((ProtocolSubmission) businessObject).getSubmissionId();
            AnchorHtmlData editHtmlData = getViewLink(((ProtocolSubmission) businessObject).getProtocol().getProtocolDocument());
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            href = href.replace("&docTypeName=", submissionIdParam +"&docTypeName=");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
       }
        if(kraAuthorizationService.hasPermission(getUserIdentifier(), ((ProtocolSubmission) businessObject).getProtocol(), PermissionConstants.VIEW_PROTOCOL)) {
            String submissionIdParam = "&submissionId=" + ((ProtocolSubmission) businessObject).getSubmissionId();
            AnchorHtmlData viewHtmlData = getViewLink(((ProtocolSubmission) businessObject).getProtocol().getProtocolDocument());
            String href = viewHtmlData.getHref();
            href = href.replace("&docTypeName=", submissionIdParam +"&docTypeName=");
            viewHtmlData.setHref(href);
            htmlDataList.add(viewHtmlData);
        }
        return htmlDataList;
    }

    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
   }

    @Override
    protected String getHtmlAction() {
        return "protocolProtocol.do";
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "ProtocolDocument";
    }
    
    @Override
    protected String getKeyFieldName() {
        return "protocolNumber"; 
    }

    /**
     * This method is to convert search criteria to proper reference object field.
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        Map<String, String> convertedFieldValues = new HashMap<String, String>();
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if ("title".equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                convertedFieldValues.put("protocol.title", entry.getValue());
            } else if ("scheduleDate".equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                convertedFieldValues.put("committeeSchedule.scheduleDate", entry.getValue());
            } else if ("committeeId".equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                convertedFieldValues.put("committee.committeeId", entry.getValue());
            } else{
                convertedFieldValues.put(entry.getKey(), entry.getValue());
            }
        }
        return super.getSearchResults(convertedFieldValues);
    }

    /**
     * This method is for several fields that does not have inquiry created by lookup frame work.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject, java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (propertyName.equals("protocolNumber")) {
           inqBo = ((ProtocolSubmission) bo).getProtocol();
            inqPropertyName = "protocolNumber";
        } else if (propertyName.equals("committeeId")) {
           inqBo = ((ProtocolSubmission) bo).getCommittee();
            inqPropertyName = "committeeId";
        } else if (propertyName.equals("piName")) {
            Protocol protocol = ((ProtocolSubmission) bo).getProtocol();
            ProtocolPerson principalInvestigator = protocol.getPrincipalInvestigator();
            if (principalInvestigator != null) {
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                    inqPropertyName = ProtocolLookupConstants.Property.PERSON_ID;
                } else {
                    if (principalInvestigator.getRolodexId() != null) {
                        inqBo = new Rolodex();
                        ((Rolodex) inqBo).setRolodexId(principalInvestigator.getRolodexId());
                        inqPropertyName = ProtocolLookupConstants.Property.ROLODEX_ID;
                    }
                }
            }
        }
        return super.getInquiryUrl(inqBo, inqPropertyName);
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
