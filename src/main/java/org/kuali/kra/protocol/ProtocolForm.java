/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.protocol;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelper;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.medusa.MedusaBean;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelper;
import org.kuali.kra.protocol.noteattachment.NotesAttachmentsHelper;
import org.kuali.kra.protocol.notification.ProtocolNotificationContext;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelper;
import org.kuali.kra.protocol.permission.PermissionsHelper;
import org.kuali.kra.protocol.personnel.PersonnelHelper;
import org.kuali.kra.protocol.protocol.ProtocolHelper;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBean;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelper;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelper;
import org.kuali.kra.questionnaire.QuestionableFormInterface;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public abstract class ProtocolForm extends KraTransactionalDocumentFormBase implements PermissionsForm, Auditable, QuestionableFormInterface {
    
    private static final long serialVersionUID = 4646326030098259702L;
    
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final String DATE_TIME_FORMAT = "hh:mm a MM/dd/yyyy";
    
    
//    /**
//     * When true, the online review header will not be displayed when it is disabled.
//     */
    protected static final boolean HIDE_ONLINE_REVIEW_WHEN_DISABLED = true;
    protected static final String ONLINE_REVIEW_NAV_TO = "onlineReview";
    protected static final String CUSTOM_DATA_NAV_TO = "customData";
    
    
    private ProtocolHelper protocolHelper;
    private PermissionsHelper permissionsHelper;
    private PersonnelHelper personnelHelper;
    private ProtocolCustomDataHelper customDataHelper;
    private ProtocolSpecialReviewHelper specialReviewHelper;

    private ActionHelper actionHelper;
    
    private OnlineReviewsActionHelper onlineReviewsActionHelper;
    private QuestionnaireHelper questionnaireHelper;    
    private NotificationHelper<ProtocolNotificationContext> protocolNotificationHelper;  
    //transient so that the helper and its members don't have to be serializable or transient
    //reinitialized in the getter
    private transient NotesAttachmentsHelper notesAttachmentsHelper;
    private boolean auditActivated;
    
    private ProtocolReferenceBean newProtocolReferenceBean;
    private MedusaBean medusaBean;
    
    //KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
 
    private boolean javaScriptEnabled = true;
    
    private String detailId;
    // temp field : set in presave and then referenced in postsave
    private transient List<ProtocolFundingSource> deletedProtocolFundingSources;
 
    
    public ProtocolForm() throws Exception {
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
        setPermissionsHelper(createNewPermissionsHelperInstanceHook(this));
        
        setCustomDataHelper(createNewCustomDataHelperInstanceHook(this)); 
        setSpecialReviewHelper(createNewSpecialReviewHelperInstanceHook(this));
        setActionHelper(createNewActionHelperInstanceHook(this));
        setQuestionnaireHelper(createNewQuestionnaireHelperInstanceHook(this));
        
        setNotesAttachmentsHelper(createNewNotesAttachmentsHelperInstanceHook(this));
        this.notesAttachmentsHelper.prepareView();
        setNewProtocolReferenceBean(createNewProtocolReferenceBeanInstance());
        setOnlineReviewsActionHelper(createNewOnlineReviewsActionHelperInstanceHook(this));
        setMedusaBean(new MedusaBean());
        
// TODO *********commented the code below during IACUC refactoring*********         
//        setNotificationHelper(new NotificationHelper<IRBNotificationContext>());
            setNotificationHelper(getNotificationHelperHook());
    }
       
  

    protected abstract NotificationHelper getNotificationHelperHook();
    
    protected abstract ProtocolReferenceBean createNewProtocolReferenceBeanInstance();

    protected abstract QuestionnaireHelper createNewQuestionnaireHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract ActionHelper createNewActionHelperInstanceHook(ProtocolForm protocolForm) throws Exception;
    protected abstract ProtocolSpecialReviewHelper createNewSpecialReviewHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract ProtocolCustomDataHelper createNewCustomDataHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract OnlineReviewsActionHelper createNewOnlineReviewsActionHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract ProtocolHelper createNewProtocolHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract PermissionsHelper createNewPermissionsHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract PersonnelHelper createNewPersonnelHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract QuestionnaireHelper createNewQuestionnaireHelper(ProtocolForm protocolForm);
    protected abstract NotesAttachmentsHelper createNewNotesAttachmentsHelperInstanceHook(ProtocolForm protocolForm);
    
    
    
    
// TODO *********uncomment the code below in increments as needed during refactoring*********     
//    /**
//     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
//     * 
//     * We only enable the Online Review tab if the protocol is in a state to be reviewed and
//     * the user has the IRB Admin role or the user has an Online Review. 
//     * 
//     * If HIDE_ONLINE_REVIEW_WHEN_DISABLED is true, then the tab will be removed from the tabs 
//     * List if it is disabled.
//     * 
//     */
//    @Override
//    public HeaderNavigation[] getHeaderNavigationTabs() {
//        
//        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
//        
//        ProtocolOnlineReviewService onlineReviewService = getProtocolOnlineReviewService();
//        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
//        boolean onlineReviewTabEnabled = false;
//
//        if (getProtocolDocument() != null && getProtocolDocument().getProtocol() != null) {
//            String principalId = GlobalVariables.getUserSession().getPrincipalId();
//            ProtocolSubmission submission = getProtocolDocument().getProtocol().getProtocolSubmission();
//            boolean isUserOnlineReviewer = onlineReviewService.isProtocolReviewer(principalId, false, submission);
//            boolean isUserIrbAdmin = getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), "KC-UNT", "IRB Administrator"); 
//            onlineReviewTabEnabled = (isUserOnlineReviewer || isUserIrbAdmin) 
//                    && onlineReviewService.isProtocolInStateToBeReviewed(getProtocolDocument().getProtocol());
//        }
//        
//            //We have to copy the HeaderNavigation elements into a new collection as the 
//            //List returned by DD is it's cached copy of the header navigation list.
//        for (HeaderNavigation nav : navigation) {
//            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),ONLINE_REVIEW_NAV_TO)) {
//                nav.setDisabled(!onlineReviewTabEnabled);
//                if (onlineReviewTabEnabled || ((!onlineReviewTabEnabled) && (!HIDE_ONLINE_REVIEW_WHEN_DISABLED))) {
//                    resultList.add(nav);
//                }
////            } else if (StringUtils.equals(nav.getHeaderTabNavigateTo(),CUSTOM_DATA_NAV_TO)) {
////                boolean displayTab = this.getCustomDataHelper().canDisplayCustomDataTab();
////                nav.setDisabled(!displayTab);
////                if (displayTab) {
////                    resultList.add(nav);
////                }
//            } else {
//                resultList.add(nav);
//            }
//        }
//        
//        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
//        resultList.toArray(result);
//        return result;
//    }
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }

// TODO *********commented the code below during IACUC refactoring********* 
//    /**
//     * 
//     * This method is a wrapper method for getting ProtocolOnlineReviewerService service.
//     * @return
//     */
//    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
//        return KraServiceLocator.getService(ProtocolOnlineReviewService.class);
//    }
//
//    
//    
//    @Override
//    public void populate(HttpServletRequest request) { 
//        initAnswerList(request);
//        super.populate(request);
//        
//        // Temporary hack for KRACOEUS-489
//        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
//            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
//        }
//    }
//    
//    /*
//     * For submission questionnaire, it is a popup and not a session document.
//     * so, it has to be retrieved, then populate with the new data.
//     */
//    private void initAnswerList(HttpServletRequest request) {
//        
//        String protocolNumber = request.getParameter("questionnaireHelper.protocolNumber");
//        String submissionNumber = request.getParameter("questionnaireHelper.submissionNumber");
//        if (StringUtils.isNotBlank(protocolNumber) && protocolNumber.endsWith("T")) {
//            ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocolNumber, CoeusSubModule.PROTOCOL_SUBMISSION, submissionNumber, false);
//            this.getQuestionnaireHelper().setAnswerHeaders(
//                    getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
//        }
//    }
//
//    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
//        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
//}

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
        //onlineReviewsActionHelper.init(true);
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
    

    public void setProtocolHelper(ProtocolHelper protocolHelper) {
        this.protocolHelper = protocolHelper;
    }

    public ProtocolHelper getProtocolHelper() {
        return protocolHelper;
    }
    
    private void setPersonnelHelper(PersonnelHelper personnelHelper) {
        this.personnelHelper = personnelHelper;
    }
    
    public PersonnelHelper getPersonnelHelper() {
        return personnelHelper;
    }
    
    private void setPermissionsHelper(PermissionsHelper permissionsHelper) {
        this.permissionsHelper = permissionsHelper;
    }
    
    public PermissionsHelper getPermissionsHelper() {
          return permissionsHelper;
    }
    
    
    public void setNewProtocolReferenceBean(ProtocolReferenceBean newProtocolReferenceBean) {
        this.newProtocolReferenceBean = newProtocolReferenceBean;
    }

    public ProtocolReferenceBean getNewProtocolReferenceBean() {
        return newProtocolReferenceBean;
    }
    
    @Override
    protected void setSaveDocumentControl(Map editMode) {
      
    }
   
    public ProtocolCustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    public void setCustomDataHelper(ProtocolCustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }
    
    
    /** {@inheritDoc} */
    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    /** {@inheritDoc} */
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    public ProtocolSpecialReviewHelper getSpecialReviewHelper() {
        return specialReviewHelper;
    }
    

    public void setSpecialReviewHelper(ProtocolSpecialReviewHelper specialReviewHelper) {
        this.specialReviewHelper = specialReviewHelper;
    }

    /**
     * Gets the Notes & Attachments Helper.
     * @return Notes & Attachments Helper
     */
    public NotesAttachmentsHelper getNotesAttachmentsHelper() {
        if (notesAttachmentsHelper == null) {
            notesAttachmentsHelper = createNewNotesAttachmentsHelperInstanceHook(this);
        }
        
        return notesAttachmentsHelper;
    }

    /**
     * Sets the Notes & Attachments Helper.
     * @param notesAttachmentsHelper the Notes & Attachments Helper
     */
    public void setNotesAttachmentsHelper(NotesAttachmentsHelper notesAttachmentsHelper) {
        this.notesAttachmentsHelper = notesAttachmentsHelper;
    }
    
    public ActionHelper getActionHelper() {
        return actionHelper;
    }
    
    protected void setActionHelper(ActionHelper actionHelper) {
        this.actionHelper = actionHelper;
    }


    public boolean isJavaScriptEnabled() {
        return javaScriptEnabled;
    }

    public void setJavaScriptEnabled(boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }

    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }

    public QuestionnaireHelper getQuestionnaireHelper() {
        return questionnaireHelper;
    }

    public void setQuestionnaireHelper(QuestionnaireHelper questionnaireHelper) {
        this.questionnaireHelper = questionnaireHelper;
    }

    public void setOnlineReviewsActionHelper(OnlineReviewsActionHelper onlineReviewActionHelper) {
        this.onlineReviewsActionHelper = onlineReviewActionHelper;
    }

    public OnlineReviewsActionHelper getOnlineReviewsActionHelper() {
        return onlineReviewsActionHelper;
    }

    public NotificationHelper<ProtocolNotificationContext> getNotificationHelper() {
        return protocolNotificationHelper;
    }

    public void setNotificationHelper(NotificationHelper notificationHelper) {
        this.protocolNotificationHelper = notificationHelper;
    }

   

// TODO *********commented the code below during IACUC refactoring*********     
//    @Override
//    public boolean isPropertyEditable(String propertyName) {
//        if (propertyName.startsWith("actionHelper.protocolSubmitAction.reviewer") ||
//                propertyName.startsWith("methodToCall.printSubmissionQuestionnaireAnswer.line")
//                || propertyName.startsWith("methodToCall.saveCorrespondence")
//                || propertyName.startsWith("methodToCall.closeCorrespondence")) {
//            return true;
//        } else {
//            return super.isPropertyEditable(propertyName);
//        }
//    }
    
    public KraAuthorizationService getKraAuthorizationService() {
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }
    
    /**
     * 
     * This method returns true if the risk level panel should be displayed.
     * @return
     */
    public boolean getDisplayRiskLevelPanel() {
        return true;
// TODO *********commented the code below during IACUC refactoring*********        
//        return this.getProtocolDocument().getProtocol().getProtocolRiskLevels() != null 
//            && this.getProtocolDocument().getProtocol().getProtocolRiskLevels().size() > 0;
        
    }
    
    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();

        String externalImageURL = Constants.KR_EXTERNALIZABLE_IMAGES_URI_KEY;
        ConfigurationService configurationService = KRADServiceLocator.getKualiConfigurationService();
        
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
// TODO *********commented the code below during IACUC refactoring*********       
//    public String getModuleCode() {
//        return CoeusModule.IRB_MODULE_CODE;
//    }

      
    
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public List<ProtocolFundingSource> getDeletedProtocolFundingSources() {
        return deletedProtocolFundingSources;
    }

    public void setDeletedProtocolFundingSources(List<ProtocolFundingSource> deletedProtocolFundingSources) {
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
//    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
//        return KraServiceLocator.getService(IacucProtocolOnlineReviewService.class);
//    }


    public MedusaBean getMedusaBean() {
        return medusaBean;
    }


    public void setMedusaBean(MedusaBean medusaBean) {
        this.medusaBean = medusaBean;
    }
    
}