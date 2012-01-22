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
package org.kuali.kra.irb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * This class is to create action links and inquiry url for protocolsubmission lookup. 
 */
@SuppressWarnings("serial")
public class ProtocolSubmissionLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {
    private static final String COMMITTEE_ID = "committeeId";
    private static final String COMMITTEE_SCHEDULE_SCHEDULE_DATE = "committeeSchedule.scheduledDate";
    private static final String PROTOCOL_TITLE = "protocol.title";
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String DOC_TYPE_NAME_PARAM = "&docTypeName=";
    private KraAuthorizationService kraAuthorizationService;
    private KcPersonService kcPersonService;
    private ProtocolSubmitActionService protocolSubmitActionService;
   
        
   

    public void setProtocolSubmitActionService(ProtocolSubmitActionService protocolSubmitActionService) {
        this.protocolSubmitActionService = protocolSubmitActionService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), ((ProtocolSubmission) businessObject).getProtocol(),
                PermissionConstants.MODIFY_PROTOCOL)) {
            AnchorHtmlData editHtmlData = getViewLink((ProtocolSubmission) businessObject);
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
        }
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), ((ProtocolSubmission) businessObject).getProtocol(),
                PermissionConstants.VIEW_PROTOCOL)) {
            htmlDataList.add(getViewLink((ProtocolSubmission) businessObject));
        }
        return htmlDataList;
    }
  
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        List<ProtocolSubmission> submissionLookupData=(List<ProtocolSubmission>)super.getSearchResults(fieldValues);
        try{
            if((submissionLookupData!=null)&& (submissionLookupData.size()>0)){                            
                 submissionLookupData=protocolSubmitActionService.getProtocolSubmissionsLookupData(submissionLookupData);   
            }             
        }catch (Exception e) {
           LOG.info("submissionLookupData Lookup : " + submissionLookupData.size() + " parsing error");
        }            
        return submissionLookupData;
    }   
    
    
    

    /*
     * create the view link url for protocolsubmission
     */
    protected AnchorHtmlData getViewLink(ProtocolSubmission protocolSubmission) {
        AnchorHtmlData viewHtmlData = super.getViewLink(protocolSubmission.getProtocol().getProtocolDocument());
        String submissionIdParam = "&submissionId=" + protocolSubmission.getSubmissionId();
        String href = viewHtmlData.getHref();
        href = href.replace(DOC_TYPE_NAME_PARAM, submissionIdParam + DOC_TYPE_NAME_PARAM);
        viewHtmlData.setHref(href);
        return viewHtmlData;
    }
    
    
    
    

    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    protected String getUserIdentifier() {
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
        return PROTOCOL_NUMBER;
    }


           
  
    /**
     * This method is for several fields that does not have inquiry created by lookup frame work.
     * Also, disable inquiry link for protocol title & schedule date.
     * 
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject,
     *      java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        HtmlData inqUrl = new AnchorHtmlData();
        if (!COMMITTEE_SCHEDULE_SCHEDULE_DATE.equals(propertyName) && !PROTOCOL_TITLE.equals(propertyName)) {
            if (PROTOCOL_NUMBER.equals(propertyName)) {
                inqBo = ((ProtocolSubmission) bo).getProtocol();
            }
            else if (propertyName.equals(COMMITTEE_ID)) {
                inqBo = ((ProtocolSubmission) bo).getCommittee();
            }
            else if ("piName".equals(propertyName)) {
                Protocol protocol = ((ProtocolSubmission) bo).getProtocol();
                ProtocolPerson principalInvestigator = protocol.getPrincipalInvestigator();
                if (principalInvestigator != null) {
                    if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                        inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                        inqPropertyName = ProtocolLookupConstants.Property.PERSON_ID;
                    }
                    else {
                        if (principalInvestigator.getRolodexId() != null) {
                            inqBo = new Rolodex();
                            ((Rolodex) inqBo).setRolodexId(principalInvestigator.getRolodexId());
                            inqPropertyName = ProtocolLookupConstants.Property.ROLODEX_ID;
                        }
                    }
                }
            }
            if (inqBo != null) {
                // withdraw committeeidfk = null will cause inqbo=null
                inqUrl = super.getInquiryUrl(inqBo, inqPropertyName);
            }
        }
        return inqUrl;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    /**
     * To disable the search icon for 'title' & 'scheduleddate' fields.  These fields are referencing to reference objects' fields.
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (PROTOCOL_TITLE.equals(field.getPropertyName()) || COMMITTEE_SCHEDULE_SCHEDULE_DATE.equals(field.getPropertyName())) {
                   
                    field.setQuickFinderClassNameImpl(KRADConstants.EMPTY_STRING);                 
                }
            }
        }
        return rows;
    }

}
