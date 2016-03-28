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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.medusa.MedusaBean;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelperBase;
import org.kuali.kra.protocol.noteattachment.NotesAttachmentsHelperBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelperBase;
import org.kuali.kra.protocol.permission.PermissionsHelperBase;
import org.kuali.kra.protocol.personnel.PersonnelHelperBase;
import org.kuali.kra.protocol.protocol.ProtocolHelperBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBeanBase;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelperBase;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionableFormInterface;
import org.kuali.coeus.common.framework.custom.CustomDataDocumentForm;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ProtocolFormBase extends KcTransactionalDocumentFormBase implements PermissionsForm, Auditable, QuestionableFormInterface,
                                                                                        CustomDataDocumentForm {
    
    private static final long serialVersionUID = 4646326030098259702L;
    
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final String DATE_TIME_FORMAT = "hh:mm a MM/dd/yyyy";
    
    
//    /**
//     * When true, the online review header will not be displayed when it is disabled.
//     */
    protected static final boolean HIDE_ONLINE_REVIEW_WHEN_DISABLED = true;
    protected static final String ONLINE_REVIEW_NAV_TO = "onlineReview";

    
    private ProtocolHelperBase protocolHelper;
    private PermissionsHelperBase permissionsHelper;
    private PersonnelHelperBase personnelHelper;
    private ProtocolCustomDataHelperBase customDataHelper;
    private ProtocolSpecialReviewHelperBase specialReviewHelper;
    private ActionHelperBase actionHelper;    
    private OnlineReviewsActionHelperBase onlineReviewsActionHelper;
    private QuestionnaireHelperBase questionnaireHelper;    
    private NotificationHelper<ProtocolNotificationContextBase> protocolNotificationHelper;  
    //transient so that the helper and its members don't have to be serializable or transient
    //reinitialized in the getter
    private transient NotesAttachmentsHelperBase notesAttachmentsHelper;
    private boolean auditActivated;
    
    private transient KcPersonService kcPersonService;

    private ProtocolReferenceBeanBase newProtocolReferenceBean;
    private MedusaBean medusaBean;
    
    //KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
 
    private boolean javaScriptEnabled = true;
    
    private String detailId;
    // temp field : set in presave and then referenced in postsave
    private transient List<ProtocolFundingSourceBase> deletedProtocolFundingSources;
 
    private boolean showNotificationEditor = false;  // yep, it's a hack

    public ProtocolFormBase() throws Exception {
        super();
        initialize();
        this.registerEditableProperty("methodToCall");
    }
    

    /**
     *
     * This method initialize all form variables
     * @throws Exception 
     */
    public void initialize() throws Exception {
            setProtocolHelper(createNewProtocolHelperInstanceHook(this));
            setPersonnelHelper(createNewPersonnelHelperInstanceHook(this));
            setCustomDataHelper(createNewCustomDataHelperInstanceHook(this));
            setSpecialReviewHelper(createNewSpecialReviewHelperInstanceHook(this));
            setQuestionnaireHelper(createNewQuestionnaireHelperInstanceHook(this));
            setNewProtocolReferenceBean(createNewProtocolReferenceBeanInstance());
            setOnlineReviewsActionHelper(createNewOnlineReviewsActionHelperInstanceHook(this));
            setNotificationHelper(getNotificationHelperHook());
            setMedusaBean(new MedusaBean());
    }
       
  
    public void initializePermission() throws Exception {
        setPermissionsHelper(createNewPermissionsHelperInstanceHook(this));
    }
    
    public void initializeNotesAttachments() throws Exception {
        setNotesAttachmentsHelper(createNewNotesAttachmentsHelperInstanceHook(this));
        this.notesAttachmentsHelper.prepareView();
    }
    
    public void initializeProtocolAction() throws Exception {
        setActionHelper(createNewActionHelperInstanceHook(this, true));
    }

    public void initializeProtocolHistory() throws Exception {
        setActionHelper(createNewActionHelperInstanceHook(this, true));
    }
    
    protected abstract NotificationHelper<? extends ProtocolNotificationContextBase> getNotificationHelperHook();
    
    protected abstract ProtocolReferenceBeanBase createNewProtocolReferenceBeanInstance();

    protected abstract QuestionnaireHelperBase createNewQuestionnaireHelperInstanceHook(ProtocolFormBase protocolForm);
    protected abstract ActionHelperBase createNewActionHelperInstanceHook(ProtocolFormBase protocolForm, boolean initializeActions) throws Exception;
    protected abstract ProtocolSpecialReviewHelperBase createNewSpecialReviewHelperInstanceHook(ProtocolFormBase protocolForm);
    protected abstract ProtocolCustomDataHelperBase createNewCustomDataHelperInstanceHook(ProtocolFormBase protocolForm);
    protected abstract OnlineReviewsActionHelperBase createNewOnlineReviewsActionHelperInstanceHook(ProtocolFormBase protocolForm);
    protected abstract ProtocolHelperBase createNewProtocolHelperInstanceHook(ProtocolFormBase protocolForm);
    protected abstract PermissionsHelperBase createNewPermissionsHelperInstanceHook(ProtocolFormBase protocolForm);
    protected abstract PersonnelHelperBase createNewPersonnelHelperInstanceHook(ProtocolFormBase protocolForm);
    protected abstract QuestionnaireHelperBase createNewQuestionnaireHelper(ProtocolFormBase protocolForm);
    protected abstract NotesAttachmentsHelperBase createNewNotesAttachmentsHelperInstanceHook(ProtocolFormBase protocolForm);

    @SuppressWarnings("deprecation")
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
    }
    
    protected String getFormattedDate(Date dateInput) {
        return CoreApiServiceLocator.getDateTimeService().toString(dateInput, DATE_FORMAT);        
    }

    protected String getFormattedDateTime(Timestamp dateTimeInput) {
        return CoreApiServiceLocator.getDateTimeService().toString(dateTimeInput, DATE_TIME_FORMAT);        
    }
    
    /* Reset method
     * @param mapping
     * @param request
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setLookupResultsSequenceNumber(null);
        this.setLookupResultsBOClassName(null);
        
        onlineReviewsActionHelper.init(true);
    }
    
    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }
    
    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }
    

    public void setProtocolHelper(ProtocolHelperBase protocolHelper) {
        this.protocolHelper = protocolHelper;
    }

    public ProtocolHelperBase getProtocolHelper() {
        return protocolHelper;
    }
    
    private void setPersonnelHelper(PersonnelHelperBase personnelHelper) {
        this.personnelHelper = personnelHelper;
    }
    
    public PersonnelHelperBase getPersonnelHelper() {
        return personnelHelper;
    }
    
    private void setPermissionsHelper(PermissionsHelperBase permissionsHelper) {
        this.permissionsHelper = permissionsHelper;
    }
    
    public PermissionsHelperBase getPermissionsHelper() {
    	if (permissionsHelper == null) {
    		try {
    			this.initializePermission();
    		} catch (Exception e) {
    			throw new RuntimeException(e);
    		}
    	}
        return permissionsHelper;
    }
    
    public void resetUserPermissionStates() {
    	if (permissionsHelper != null) {
    		permissionsHelper.resetUserStates();
    	}
    }
    
    
    public void setNewProtocolReferenceBean(ProtocolReferenceBeanBase newProtocolReferenceBean) {
        this.newProtocolReferenceBean = newProtocolReferenceBean;
    }

    public ProtocolReferenceBeanBase getNewProtocolReferenceBean() {
        return newProtocolReferenceBean;
    }
    
    @Override
    protected void setSaveDocumentControl(Map editMode) {
      
    }
   
    public ProtocolCustomDataHelperBase getCustomDataHelper() {
        return customDataHelper;
    }

    public void setCustomDataHelper(ProtocolCustomDataHelperBase customDataHelper) {
        this.customDataHelper = customDataHelper;
    }
    
    
    @Override
    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    @Override
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    public ProtocolSpecialReviewHelperBase getSpecialReviewHelper() {
        return specialReviewHelper;
    }
    

    public void setSpecialReviewHelper(ProtocolSpecialReviewHelperBase specialReviewHelper) {
        this.specialReviewHelper = specialReviewHelper;
    }

    public NotesAttachmentsHelperBase getNotesAttachmentsHelper() {
        if (notesAttachmentsHelper == null) {
            notesAttachmentsHelper = createNewNotesAttachmentsHelperInstanceHook(this);
        }
        
        return notesAttachmentsHelper;
    }

    public void setNotesAttachmentsHelper(NotesAttachmentsHelperBase notesAttachmentsHelper) {
        this.notesAttachmentsHelper = notesAttachmentsHelper;
    }
    
    public ActionHelperBase getActionHelper() {
        return actionHelper;
    }
    
    protected void setActionHelper(ActionHelperBase actionHelper) {
        this.actionHelper = actionHelper;
    }


    public boolean isJavaScriptEnabled() {
        return javaScriptEnabled;
    }

    public void setJavaScriptEnabled(boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }

    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }

    public QuestionnaireHelperBase getQuestionnaireHelper() {
        return questionnaireHelper;
    }

    public void setQuestionnaireHelper(QuestionnaireHelperBase questionnaireHelper) {
        this.questionnaireHelper = questionnaireHelper;
    }

    public void setOnlineReviewsActionHelper(OnlineReviewsActionHelperBase onlineReviewActionHelper) {
        this.onlineReviewsActionHelper = onlineReviewActionHelper;
    }

    public OnlineReviewsActionHelperBase getOnlineReviewsActionHelper() {
        return onlineReviewsActionHelper;
    }

    public NotificationHelper<ProtocolNotificationContextBase> getNotificationHelper() {
        return protocolNotificationHelper;
    }

    public void setNotificationHelper(NotificationHelper notificationHelper) {
        this.protocolNotificationHelper = notificationHelper;
    }

    public SystemAuthorizationService getSystemAuthorizationService() {
        return KcServiceLocator.getService(SystemAuthorizationService.class);
    }

    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();

        String externalImageURL = Constants.KR_EXTERNALIZABLE_IMAGES_URI_KEY;
        ConfigurationService configurationService = CoreApiServiceLocator.getKualiConfigurationService();
        
        boolean suppressRoutingControls = getActionHelper().getCanApproveFull() || !getActionHelper().getCanApproveOther();
        if (suppressRoutingControls && getDocumentActions().get(KRADConstants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS) != null) {
            String sendAdHocRequestsImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_sendadhocreq.gif";
            addExtraButton("methodToCall.sendAdHocRequests", sendAdHocRequestsImage, "Send AdHoc Requests");
        }
        externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        
        String sendNotificationImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_send_notification.gif";
        addExtraButton("methodToCall.sendNotification", sendNotificationImage, "Send Notification");
        
        return extraButtons;
    }
     
    public abstract String getModuleCode();

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public List<ProtocolFundingSourceBase> getDeletedProtocolFundingSources() {
        return deletedProtocolFundingSources;
    }

    public void setDeletedProtocolFundingSources(List<ProtocolFundingSourceBase> deletedProtocolFundingSources) {
        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
    }
    
    public String getQuestionnaireFieldStarter() {
        return "questionnaireHelper.answerHeaders[";
    }
    
    public String getQuestionnaireFieldMiddle() {
        return DEFAULT_MIDDLE;
    }
    
    public String getQuestionnaireFieldEnd() {
        return DEFAULT_END;
    }
    
    public String getQuestionnaireExpression() {
        return "^undefined$";
    }

    public MedusaBean getMedusaBean() {
        return medusaBean;
    }


    public void setMedusaBean(MedusaBean medusaBean) {
        this.medusaBean = medusaBean;
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    
    // superuser should never be allowed to perform approve doc on protocol docs since 
    // that bypasses the usual committee voting process for protocol approval, and makes the doc FINAL
    public boolean isSuperUserApproveDocumentAuthorized() {
        return false;
    }
    
    
    // for terminal nodes we will restrict the super user to carry out only ad-hoc requests or exception requests,
    // since carrying out machine-generated approve requests at a terminal (review) node 
    // will bypass the committee voting process and make the document status final.
    // At non-terminal nodes however we will not restrict the list of available requested actions in any way.
    @SuppressWarnings("deprecation")
    public List<ActionRequest> getActionRequests() {
        List<ActionRequest> retVal;
        List<ActionRequest> allAvailableRequests = super.getActionRequests(); 
        if(!isDocumentAtTerminalNode()) {
            retVal = allAvailableRequests;
        }
        // otherwise keep only the ad-hoc and exception requests
        else {
            retVal = new ArrayList<ActionRequest>();
            for(ActionRequest request: allAvailableRequests) {
                if(request.isAdHocRequest() || request.isExceptionRequest()) {
                    retVal.add(request);
                }
            }
        }
        return retVal;
    }
    
    @SuppressWarnings("deprecation")
    private boolean isDocumentAtTerminalNode() {
        boolean retVal = false;
        List<String> terminalNodeNames = getTerminalNodeNamesHook();
        List<String> currentNodes = 
            KewApiServiceLocator.getWorkflowDocumentService().getCurrentRouteNodeNames(this.getDocument().getDocumentHeader().getWorkflowDocument().getDocumentId());
        for(String terminalNodeName: terminalNodeNames) {
            if(currentNodes.contains(terminalNodeName)) {
                retVal = true;
                break;
            }
        }
        return retVal;
    } 

    // hook methods for subclasses to provide their specific list of terminal nodes (usually only a list of one element)
    protected abstract List<String> getTerminalNodeNamesHook();


    public boolean isShowNotificationEditor() {
        return showNotificationEditor;
    }

    public void setShowNotificationEditor(boolean showNotificationEditor) {
        this.showNotificationEditor = showNotificationEditor;
    }

    @SuppressWarnings("deprecation")
    public boolean isSuperUserActionAvaliable() {
        boolean retVal = false; 
        if(!isDocumentStatusSaved()) {
            retVal = super.isSuperUserActionAvaliable();
        }
        return retVal;
    }


    @SuppressWarnings("deprecation")
    private boolean isDocumentStatusSaved() {
        DocumentStatus status = null;
        WorkflowDocument document = WorkflowDocumentFactory.loadDocument(GlobalVariables.getUserSession().getPrincipalId(),
            this.getDocument().getDocumentHeader().getWorkflowDocument().getDocumentId());
        if (ObjectUtils.isNotNull(document)) {
            status = document.getStatus();
        } else {
            status = this.getDocument().getDocumentHeader().getWorkflowDocument().getStatus();
        }
        return StringUtils.equals(status.getCode(), DocumentStatus.SAVED.getCode());
    }

    public boolean getDisplayEditButton() {
        return !getProtocolDocument().getProtocol().isRenewalWithoutAmendment();
    }

    public boolean getDisplayCoiDisclosureStatus() {
        return getParameterService().getParameterValueAsBoolean(getModuleName(),
               Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.ENABLE_DISCLOSURE_STATUS_FROM_COI_MODULE);
        }

    protected abstract String getModuleName();

    public ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }


}
