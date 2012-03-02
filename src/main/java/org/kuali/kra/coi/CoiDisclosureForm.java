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
package org.kuali.kra.coi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.coi.actions.DisclosureActionHelper;
import org.kuali.kra.coi.disclosure.DisclosureHelper;
import org.kuali.kra.coi.notesandattachments.CoiNotesAndAttachmentsHelper;
import org.kuali.kra.coi.notification.CoiNotificationContext;
import org.kuali.kra.coi.questionnaire.DisclosureQuestionnaireHelper;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelper;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.questionnaire.QuestionnaireHelper;
import org.kuali.kra.questionnaire.QuestionableFormInterface;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

public class CoiDisclosureForm extends KraTransactionalDocumentFormBase implements Auditable, QuestionableFormInterface  {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5620344612882618024L;
    private transient DisclosureHelper disclosureHelper;
    private transient DisclosureActionHelper disclosureActionHelper;
    private boolean auditActivated;
    private transient CoiNotesAndAttachmentsHelper coiNotesAndAttachmentsHelper;
    private transient NotificationHelper<CoiNotificationContext> notificationHelper;
    private transient DisclosureQuestionnaireHelper disclosureQuestionnaireHelper; 
    
    //TODO : coiDisclosureStatusCode : this is just a quick set up here for 'approve' action to test 'master disclosure'
    // this should be moved to disclosureactionhelper when 'action' is really implemented
    private String coiDispositionCode; 
    
    
    public CoiDisclosureForm() {
        super();
        initialize();
    }

    /**
     * This method initialize all form variables
     */
    public void initialize() {
       setDisclosureHelper(new DisclosureHelper(this));
       setCoiNotesAndAttachmentsHelper(new CoiNotesAndAttachmentsHelper(this));
       coiNotesAndAttachmentsHelper.prepareView();
       setDisclosureActionHelper(new DisclosureActionHelper(this));
       disclosureActionHelper.prepareView();
       setNotificationHelper(new NotificationHelper<CoiNotificationContext>());
       setDisclosureQuestionnaireHelper(new DisclosureQuestionnaireHelper(this));
    }
    
   public void setCoiNotesAndAttachmentsHelper(CoiNotesAndAttachmentsHelper coiNotesAndAttachmentsHelper) {
        this.coiNotesAndAttachmentsHelper = coiNotesAndAttachmentsHelper;
        
    }
   
   public CoiNotesAndAttachmentsHelper getCoiNotesAndAttachmentsHelper() {
       return coiNotesAndAttachmentsHelper;
    }
   
   public DisclosureQuestionnaireHelper getDisclosureQuestionnaireHelper() {
       return disclosureQuestionnaireHelper;
   }

   public void setDisclosureQuestionnaireHelper(DisclosureQuestionnaireHelper disclosureQuestionnaireHelper) {
       this.disclosureQuestionnaireHelper = disclosureQuestionnaireHelper;
   }
    
   @Override
    protected String getDefaultDocumentTypeName() {
        // TODO Auto-generated method stub
        return "CoiDisclosureDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_COIDISCLOSURE;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        // TODO Auto-generated method stub
        
    }

    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
        
    }

    public DisclosureHelper getDisclosureHelper() {
        return disclosureHelper;
    }

    public void setDisclosureHelper(DisclosureHelper disclosureHelper) {
        this.disclosureHelper = disclosureHelper;
    }

    public CoiDisclosureDocument getCoiDisclosureDocument() {
        return (CoiDisclosureDocument)this.getDocument();
    }

//    @Override
//    public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
//        // TODO Auto-generated method stub
//        return super.validate(mapping, request);
//    }

    /**
     * for approved disclosure, only display "Disclosure" page tab
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     */
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {

        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();

        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
//        boolean isApproved = false;
//        if (this.getCoiDisclosureDocument().getCoiDisclosure().getCoiDisclosureId() != null) {
//            isApproved = isApprovedDisclosure(this.getCoiDisclosureDocument().getCoiDisclosure());
//        }
        for (HeaderNavigation nav : navigation) {
            if ((!this.getCoiDisclosureDocument().getCoiDisclosure().isApprovedDisclosure() && !StringUtils.equals("viewMasterDisclosure", this.getMethodToCall())) || StringUtils.equals(nav.getHeaderTabNavigateTo(), "disclosure")) {
                resultList.add(nav);
            }
        }

        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        return result;
    }


    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        CoiDisclosureDocument document = this.getCoiDisclosureDocument();
        CoiDisclosure disclosure = document.getCoiDisclosure();
        List<HeaderField>newDocInfo = new ArrayList<HeaderField>();
        
        // document status
        CoiDisclosureStatus status = disclosure.getCoiDisclosureStatus();
        String disclosureStatus = status != null ? status.getDescription() : "NEW";
        HeaderField headerStatus = new HeaderField("DataDictionary.CoiDisclosureStatus.attributes.description", disclosureStatus);
        newDocInfo.add(headerStatus);
        
        // document disposition
        CoiDispositionStatus disposition = disclosure.getCoiDispositionStatus();
        String disclosureDisposition = disposition != null ? disposition.getDescription() : "NEW";
        HeaderField headerDisposition = new HeaderField("DataDictionary.CoiDispositionStatus.attributes.description", disclosureDisposition);
        newDocInfo.add(headerDisposition);
        
        // reporter (initiator?)        
        String reporter = document.getDocumentHeader().getWorkflowDocument().getPrincipalId();
        if (reporter != null) {
            reporter = KcPerson.fromPersonId(reporter).getUserName();
        } else {
            reporter = GlobalVariables.getUserSession().getPrincipalId();
        }
        HeaderField disclosureReporter = new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.reporter", reporter);
        newDocInfo.add(disclosureReporter);
        
        // last updated 
        Timestamp timeStamp = document.getUpdateTimestamp();
        if (timeStamp == null) {
            timeStamp = new Timestamp(document.getDocumentHeader().getWorkflowDocument().getDateCreated().getMillis());
        }
        String lastUpdatedDateStr = CoreApiServiceLocator.getDateTimeService().toString(timeStamp, "hh:mm a MM/dd/yyyy");
        newDocInfo.add(new HeaderField("DataDictionary.CoiDisclosure.attributes.updateTimestamp", lastUpdatedDateStr));
        
        // disclosure number
        String disclosureNumber = disclosure.getCoiDisclosureNumber();
        newDocInfo.add(new HeaderField("DataDictionary.CoiDisclosure.attributes.coiDisclosureNumber", disclosureNumber));
        
        // creation date 
        long creationMsecs = document.getDocumentHeader().getWorkflowDocument().getDateCreated().getMillis();
        String disclosureCreated = CoreApiServiceLocator.getDateTimeService().toString(new Date(creationMsecs), "MM/dd/yyyy");
        newDocInfo.add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.createTimestamp", disclosureCreated));
        setDocInfo(newDocInfo);
    }

//    private boolean isApprovedDisclosure(CoiDisclosure coiDisclosure) {
//
//        Map fieldValues = new HashMap();
//        fieldValues.put("coiDisclosureId", coiDisclosure.getCoiDisclosureId());
//        fieldValues.put("disclosureStatus", CoiDisclosureStatus.APPROVE_DISCLOSURE_CODES);
//        return getBusinessObjectService().countMatching(CoiDisclosureHistory.class, fieldValues) > 0;
//    }

    private BusinessObjectService getBusinessObjectService() {
        return KRADServiceLocator.getBusinessObjectService();
    }

    public String getCoiDispositionCode() {
        return coiDispositionCode;
    }

    public void setCoiDispositionCode(String coiDispositionCode) {
        this.coiDispositionCode = coiDispositionCode;
    }

    public DisclosureActionHelper getDisclosureActionHelper() {
        return disclosureActionHelper;
    }

    public void setDisclosureActionHelper(DisclosureActionHelper disclosureActionHelper) {
        this.disclosureActionHelper = disclosureActionHelper;
    }

    public NotificationHelper<CoiNotificationContext> getNotificationHelper() {
        return notificationHelper;
    }

    public void setNotificationHelper(NotificationHelper<CoiNotificationContext> notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    @Override
    public String getQuestionnaireFieldStarter() {
        return "questionnaireHelper.answerHeaders[";
    }
    
    @Override
    public String getQuestionnaireFieldMiddle() {
        return DEFAULT_MIDDLE;
    }
    
    @Override
    public String getQuestionnaireFieldEnd() {
        return DEFAULT_END;
    }
    
}
