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
package org.kuali.kra.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.coi.actions.DisclosureActionHelper;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.coi.disclosure.DisclosureHelper;
import org.kuali.kra.coi.notesandattachments.CoiNotesAndAttachmentsHelper;
import org.kuali.kra.coi.notification.CoiNotificationContext;
import org.kuali.kra.coi.permission.PermissionHelper;
import org.kuali.kra.coi.questionnaire.DisclosureQuestionnaireHelper;
import org.kuali.kra.coi.questionnaire.ScreeningQuestionnaireHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionableFormInterface;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoiDisclosureForm extends KcTransactionalDocumentFormBase implements Auditable, QuestionableFormInterface, PermissionsForm  {

    private static final long serialVersionUID = -5620344612882618024L;
    private transient DisclosureHelper disclosureHelper;
    private transient DisclosureActionHelper disclosureActionHelper;
    private PermissionHelper permissionHelper;
    private boolean auditActivated;
    private transient CoiNotesAndAttachmentsHelper coiNotesAndAttachmentsHelper;
    private transient NotificationHelper<CoiNotificationContext> notificationHelper;
    private transient DisclosureQuestionnaireHelper disclosureQuestionnaireHelper; 
    private transient ScreeningQuestionnaireHelper screeningQuestionnaireHelper;

    //TODO : coiDisclosureStatusCode : this is just a quick set up here for 'approve' action to test 'master disclosure'
    // this should be moved to disclosureactionhelper when 'action' is really implemented
    private String coiDispositionCode; 
    private String coiDisclosureStatusCode;
    
    //for admin person search
    private String personId;
    
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
       //coiNotesAndAttachmentsHelper.prepareView();

       setDisclosureActionHelper(new DisclosureActionHelper(this));
       disclosureActionHelper.prepareView();
       setNotificationHelper(new NotificationHelper<CoiNotificationContext>());
       setPermissionsHelper(new PermissionHelper(this));
    }

   public void setCoiNotesAndAttachmentsHelper(CoiNotesAndAttachmentsHelper coiNotesAndAttachmentsHelper) {
        this.coiNotesAndAttachmentsHelper = coiNotesAndAttachmentsHelper;
        
    }
   
   public CoiNotesAndAttachmentsHelper getCoiNotesAndAttachmentsHelper() {
       return coiNotesAndAttachmentsHelper;
    }
   
   public DisclosureQuestionnaireHelper getDisclosureQuestionnaireHelper() {
       //lazy init questionnaire helper to make sure the document and dislosure are current
       if (disclosureQuestionnaireHelper == null) {
           disclosureQuestionnaireHelper = new DisclosureQuestionnaireHelper(this.getCoiDisclosureDocument().getCoiDisclosure());
       }
       return disclosureQuestionnaireHelper;
   }

   public void setDisclosureQuestionnaireHelper(DisclosureQuestionnaireHelper disclosureQuestionnaireHelper) {
       this.disclosureQuestionnaireHelper = disclosureQuestionnaireHelper;
   }
   
   public ScreeningQuestionnaireHelper getScreeningQuestionnaireHelper() {
       if (screeningQuestionnaireHelper == null) {
           screeningQuestionnaireHelper = new ScreeningQuestionnaireHelper(this.getCoiDisclosureDocument().getCoiDisclosure());
       }
       return screeningQuestionnaireHelper;
   }

   public void setScreeningQuestionnaireHelper(ScreeningQuestionnaireHelper screeningQuestionnaireHelper) {
       this.screeningQuestionnaireHelper = screeningQuestionnaireHelper;
   }   
    
   @Override
    protected String getDefaultDocumentTypeName() {

        return "CoiDisclosureDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_COIDISCLOSURE;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {

        
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

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    /**
     * for approved disclosure, only display "Disclosure" page tab
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     */
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {

        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();

        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
        // Adding disapproved disclosures to this because they are also displayed in the disclosures list in the
        // master disclosure
        CoiDisclosure disclosure = this.getCoiDisclosureDocument().getCoiDisclosure(); 
        for (HeaderNavigation nav : navigation) {
            if (((!disclosure.isApprovedDisclosure() 
                    && (!disclosure.isDisapprovedDisclosure())) 
                    && !StringUtils.equals("viewMasterDisclosure", this.getMethodToCall())) 
                    || StringUtils.equals(nav.getHeaderTabNavigateTo(), "disclosure")) {
                // disable "disclosure" tab if we are trying to view the master but there is none yet
                nav.setDisabled(StringUtils.equals("viewMasterDisclosure",this.getMethodToCall()) && disclosure.getCoiDisclosureId() == null);
                resultList.add(nav);
            }
            if (StringUtils.equalsIgnoreCase("disclosureActions", nav.getHeaderTabNavigateTo())) {
                CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE_ACTIONS, getCoiDisclosureDocument().getCoiDisclosure());
                    // if not coi admin, remove the actions tab completely
                    if (!getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task)) {
                        resultList.remove(nav);
                    }
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
        

        // document id/number
        newDocInfo.add(getDocumentIdHeaderField(workflowDocument));
        
        // document status
//        CoiDisclosureStatus status = disclosure.getCoiDisclosureStatus();
//        String disclosureStatus = status != null ? status.getDescription() : "NEW";
//        HeaderField headerStatus = new HeaderField("DataDictionary.CoiDisclosureStatus.attributes.description", disclosureStatus);
//        newDocInfo.add(headerStatus);
        
        // document status - review status
        CoiDisclosureStatus status = disclosure.getCoiDisclosureStatus();
        String disclosureStatus = status != null ? status.getDescription() : "NEW";
        
        String reviewStatus = disclosure.getCoiReviewStatus() != null ? disclosure.getCoiReviewStatus().getDescription() : "";
        String disclosureAndReviewStatus = disclosureStatus + " : " + reviewStatus;
        HeaderField headerStatus = new HeaderField("DataDictionary.CoiDisclosure.attributes.disclosureStatusReviewStatus", disclosureAndReviewStatus);
        newDocInfo.add(headerStatus);
        
        
        // document disposition
        CoiDispositionStatus disposition = disclosure.getCoiDispositionStatus();
        String disclosureDisposition = disposition != null ? disposition.getDescription() : "NEW";
        HeaderField headerDisposition = new HeaderField("DataDictionary.CoiDisclosureStatus.attributes.description", disclosureDisposition);
        newDocInfo.add(headerDisposition);

        newDocInfo.add(getReporterAndCreatedHeaderField(workflowDocument));
        
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
        
        setDocInfo(newDocInfo);
    }
    
    /**
     * This method is to get document id/number field
     * @param workflowDocument
     * @return
     */
    @SuppressWarnings("deprecation")
    protected HeaderField getDocumentIdHeaderField(WorkflowDocument workflowDocument) {
        String documentId = "";
        if (workflowDocument != null) {
            documentId = getCoiDisclosureDocument().getDocumentNumber();
        }
        return new HeaderField("DataDictionary.CoiDisclosureDocument.attributes.documentNumber", documentId);
    }
    
    /**
     * This method is to get reporter and the created field put together
     * to display in document header
     * @param workflowDocument
     * @return
     */
    @SuppressWarnings("deprecation")
    protected HeaderField getReporterAndCreatedHeaderField(WorkflowDocument workflowDocument) {

        String reporterCreated = Constants.COLON;
        String reporter = getCoiDisclosureDocument().getCoiDisclosure().getDisclosureReporter().getPersonId();

        long creationMsecs = 0L;

        if (workflowDocument != null) {
            // creation date 
            creationMsecs = workflowDocument.getDateCreated().getMillis();
        }
        
        if (reporter != null) {
            reporter = KcPerson.fromPersonId(reporter).getUserName();
        } else {
            reporter = GlobalVariables.getUserSession().getPrincipalId();
        }
        String disclosureCreated = CoreApiServiceLocator.getDateTimeService().toString(new Date(creationMsecs), "MM/dd/yyyy");
        reporterCreated = reporter + Constants.COLON + disclosureCreated;
        
        return new HeaderField("DataDictionary.CoiDisclosure.attributes.reporterCreated", reporterCreated);
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

    public void setCoiDisclosureStatusCode(String coiDisclosureStatusCode) {
        this.coiDisclosureStatusCode = coiDisclosureStatusCode;
    }

    public String getCoiDisclosureStatusCode() {
        return coiDisclosureStatusCode;
    }

    @Override
    public String getQuestionnaireFieldStarter() {
        return "disclosureQuestionnaireHelper.answerHeaders[";
    }
    
    @Override
    public String getQuestionnaireFieldMiddle() {
        return DEFAULT_MIDDLE;
    }
    
    @Override
    public String getQuestionnaireFieldEnd() {
        return DEFAULT_END;
    }
    
    public String getQuestionnaireExpression() {
        return ".*[Qq]uestionnaireHelper\\.answerHeaders\\[\\d+\\]\\.answers\\[\\d+\\]\\.answer";
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

	@Override
	public PermissionsHelperBase getPermissionsHelper() {
		return this.permissionHelper;
	}
	
	public void setPermissionsHelper(PermissionHelper persmissionHelper) {
		this.permissionHelper = persmissionHelper;
	}
    
}
