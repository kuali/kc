/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.actions;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeIdByUnitValuesFinderService;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.committee.lookup.keyvalue.IrbCommitteeIdByUnitValuesFinderService;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersBean;
import org.kuali.kra.irb.actions.correction.AdminCorrectionBean;
import org.kuali.kra.irb.actions.decision.CommitteeDecision;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionService;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteBean;
import org.kuali.kra.irb.actions.expeditedapprove.ProtocolExpeditedApproveBean;
import org.kuali.kra.irb.actions.followup.FollowupActionService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.irb.actions.notifycommittee.ProtocolNotifyCommitteeBean;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean;
import org.kuali.kra.irb.actions.print.ProtocolQuestionnairePrintingService;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.actions.undo.UndoLastActionBean;
import org.kuali.kra.irb.actions.withdraw.ProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.irb.actions.withdraw.ProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.irb.auth.GenericProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.correspondence.IrbProtocolCorrespondenceAuthorizationService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.irb.questionnaire.IrbSubmissionQuestionnaireHelper;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolEditableBean;
import org.kuali.kra.protocol.actions.ProtocolSubmissionDocBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModuleBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewalBase;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.correspondence.CorrespondenceTypeModuleIdConstants;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceAuthorizationService;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.kra.protocol.questionnaire.ProtocolSubmissionQuestionnaireHelper;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

// import org.kuali.kra.irb.actions.notifyirb.ProtocolActionAttachment;

/**
 * The form helper class for the Protocol Actions tab.
 */
@SuppressWarnings("serial")
public class ActionHelper extends ActionHelperBase {

    private static final String NAMESPACE = "KC-UNT";
    private static final List<String> ACTION_TYPE_SUBMISSION_DOC;
    static {
        final List<String> codes = new ArrayList<String>();     
        codes.add(ProtocolActionType.NOTIFY_IRB);
        codes.add(ProtocolActionType.REQUEST_TO_CLOSE);
        codes.add(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
        codes.add(ProtocolActionType.REQUEST_FOR_SUSPENSION);
        codes.add(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT);
        codes.add(ProtocolActionType.REQUEST_FOR_TERMINATION);
        codes.add(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT);
        ACTION_TYPE_SUBMISSION_DOC = codes;
    }

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    
    private boolean canNotifyIrb = false;
    private boolean canNotifyIrbUnavailable = false;
    private boolean canRequestCloseEnrollment = false;
    private boolean canRequestCloseEnrollmentUnavailable = false;
    private boolean canRequestReOpenEnrollment = false;
    private boolean canRequestReOpenEnrollmentUnavailable = false;
    private boolean canRequestDataAnalysis = false;
    private boolean canRequestDataAnalysisUnavailable = false;
    private boolean canWithdrawSubmission = false;
    private boolean canWithdrawSubmissionUnavailable = false;
    private boolean canGrantExemption = false;
    private boolean canGrantExemptionUnavailable = false;
    private boolean canApproveExpedited = false;    
    private boolean canApproveExpeditedUnavailable = false;
    private boolean canReopenEnrollment = false;
    private boolean canReopenEnrollmentUnavailable = false;
    private boolean canCloseEnrollment = false;
    private boolean canCloseEnrollmentUnavailable = false;
    private boolean canSuspendByDsmb = false;
    private boolean canSuspendByDsmbUnavailable = false;
    private boolean canPermitDataAnalysis = false;
    private boolean canPermitDataAnalysisUnavailable = false;
    private boolean canEnterRiskLevel = false;
    private boolean canIrbAcknowledgement = false;
    private boolean canIrbAcknowledgementUnavailable = false;
    private boolean canDefer = false;
    private boolean canDeferUnavailable = false;
    private boolean canReviewNotRequired = false;
    private boolean canReviewNotRequiredUnavailable = false;
    private boolean canAddCloseEnrollmentReviewerComments;
    private boolean canAddDataAnalysisReviewerComments;
    private boolean canAddReopenEnrollmentReviewerComments;

    private ProtocolRequestBean protocolCloseEnrollmentRequestBean;
    private ProtocolRequestBean protocolReOpenEnrollmentRequestBean;
    private ProtocolRequestBean protocolDataAnalysisRequestBean;
    private ProtocolRequestBean protocolWithdrawSubmissionBean;
    private ProtocolNotifyIrbBean protocolNotifyIrbBean;
    private ProtocolAssignCmtSchedBean assignCmtSchedBean;
    
    private List<KeyValue> assignCmtSchedActionCommitteeIdByUnitKeyValues;
    private List<KeyValue> notifyIrbActionCommitteeIdByUnitKeyValues;

    private ProtocolAssignReviewersBean protocolAssignReviewersBean;
    private ProtocolGrantExemptionBean protocolGrantExemptionBean;
    private ProtocolExpeditedApproveBean protocolExpeditedApprovalBean;
    private ProtocolApproveBean protocolResponseApprovalBean;
    private ProtocolGenericActionBean protocolReopenEnrollmentBean;
    private ProtocolGenericActionBean protocolCloseEnrollmentBean;
    private ProtocolGenericActionBean protocolSuspendByDsmbBean;
    private ProtocolGenericActionBean protocolPermitDataAnalysisBean;
    private ProtocolGenericActionBean protocolIrbAcknowledgementBean;
    private UndoLastActionBean undoLastActionBean;
    private ProtocolModifySubmissionBean protocolModifySubmissionBean;    
    private ProtocolGenericActionBean protocolDeferBean;
    private ProtocolReviewNotRequiredBean protocolReviewNotRequiredBean;
    private transient ProtocolSubmitActionService protocolSubmitActionService;

    private boolean currentUserAuthorizedToAssignCommittee = true;
    
    /*
     * Identifies the protocol "document" to print.
     */
    private String printTag;
    
    /**
     * Constructs an ActionHelper.
     * @param form the protocol form
     * @throws Exception 
     */
    public ActionHelper(ProtocolForm form) throws Exception {
        super(form);
    }

    @Override
    public void initializeProtocolActions() throws Exception {
    	super.initializeProtocolActions();
        protocolNotifyIrbBean = new ProtocolNotifyIrbBean(this, "protocolNotifyIrbBean");
        // setting the attachment here so new files can be attached to newActionAttachment
        protocolNotifyIrbBean.setNewActionAttachment(new ProtocolActionAttachment());
        assignCmtSchedBean = new ProtocolAssignCmtSchedBean(this);
        assignCmtSchedBean.init();
        protocolAssignReviewersBean = new ProtocolAssignReviewersBean(this);
        protocolGrantExemptionBean = new ProtocolGrantExemptionBean(this);
        protocolGrantExemptionBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        protocolExpeditedApprovalBean = buildProtocolExpeditedApproveBean(ProtocolActionType.EXPEDITE_APPROVAL);
        protocolResponseApprovalBean = (ProtocolApproveBean) buildProtocolApproveBean(ProtocolActionType.RESPONSE_APPROVAL, 
                Constants.PROTOCOL_RESPONSE_APPROVAL_ACTION_PROPERTY_KEY);
        protocolReopenEnrollmentBean = buildProtocolGenericActionBean(ProtocolActionType.REOPEN_ENROLLMENT, 
                Constants.PROTOCOL_REOPEN_ENROLLMENT_ACTION_PROPERTY_KEY);
        protocolCloseEnrollmentBean = buildProtocolGenericActionBean(ProtocolActionType.CLOSED_FOR_ENROLLMENT, 
                Constants.PROTOCOL_CLOSE_ENROLLMENT_ACTION_PROPERTY_KEY);
        protocolSuspendByDsmbBean = buildProtocolGenericActionBean(ProtocolActionType.SUSPENDED_BY_DSMB, 
                Constants.PROTOCOL_SUSPEND_BY_DSMB_ACTION_PROPERTY_KEY);
        protocolCloseBean = buildProtocolGenericActionBean(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, 
                Constants.PROTOCOL_CLOSE_ACTION_PROPERTY_KEY);
        protocolPermitDataAnalysisBean = buildProtocolGenericActionBean(ProtocolActionType.DATA_ANALYSIS_ONLY, 
                Constants.PROTOCOL_PERMIT_DATA_ANALYSIS_ACTION_PROPERTY_KEY);
        protocolIrbAcknowledgementBean = buildProtocolGenericActionBean(ProtocolActionType.IRB_ACKNOWLEDGEMENT, 
                Constants.PROTOCOL_IRB_ACKNOWLEDGEMENT_ACTION_PROPERTY_KEY);
        undoLastActionBean = createUndoLastActionBean((Protocol) getProtocol());
        protocolModifySubmissionBean = new ProtocolModifySubmissionBean(this);
        protocolDeferBean = buildProtocolGenericActionBean(ProtocolActionType.DEFERRED, 
                Constants.PROTOCOL_DEFER_ACTION_PROPERTY_KEY);
        protocolReviewNotRequiredBean = new ProtocolReviewNotRequiredBean(this);
        protocolCloseRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_TO_CLOSE, 
                ProtocolSubmissionType.REQUEST_TO_CLOSE, "protocolCloseRequestBean");
        protocolSuspendRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_FOR_SUSPENSION, 
                ProtocolSubmissionType.REQUEST_FOR_SUSPENSION, "protocolSuspendRequestBean");
        protocolCloseEnrollmentRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, 
                ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, "protocolCloseEnrollmentRequestBean");
        protocolReOpenEnrollmentRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT,
                ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT, "protocolReOpenEnrollmentRequestBean");
        protocolDataAnalysisRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY,
                ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, "protocolDataAnalysisRequestBean");
        protocolTerminateRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_FOR_TERMINATION,
                ProtocolSubmissionType.REQUEST_FOR_TERMINATION, "protocolTerminateRequestBean");
        protocolWithdrawSubmissionBean = new ProtocolRequestBean(this, ProtocolActionType.WITHDRAW_SUBMISSION, 
                ProtocolSubmissionType.WITHDRAW_SUBMISSION, "protocolWithdrawSubmissionRequestBean");
        toAnswerSubmissionQuestionnaire = hasSubmissionQuestionnaire();

        initIRBSpecificActionBeanTaskMap();
    }
    
    
    /**
     * Initializes the mapping between the task names and the beans.  This is used to get the bean associated to the task name passed in from the tag file.
     * The reason TaskName (a text code) is used and ProtocolActionType (a number code) is not is because not every task is mapped to a ProtocolActionType.
     */    
    private void initIRBSpecificActionBeanTaskMap() {    
        actionBeanTaskMap.put(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, assignCmtSchedBean);
        actionBeanTaskMap.put(TaskName.ASSIGN_REVIEWERS, protocolAssignReviewersBean);  
        actionBeanTaskMap.put(TaskName.CLOSE_PROTOCOL, protocolCloseBean);
        actionBeanTaskMap.put(TaskName.CLOSE_ENROLLMENT_PROTOCOL, protocolCloseEnrollmentBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, protocolCloseEnrollmentRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE, protocolCloseRequestBean);
        actionBeanTaskMap.put(TaskName.PERMIT_DATA_ANALYSIS, protocolPermitDataAnalysisBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, protocolDataAnalysisRequestBean);
        actionBeanTaskMap.put(TaskName.DEFER_PROTOCOL, protocolDeferBean);
        actionBeanTaskMap.put(TaskName.EXPEDITE_APPROVAL, protocolExpeditedApprovalBean);
        actionBeanTaskMap.put(TaskName.GRANT_EXEMPTION, protocolGrantExemptionBean);
        actionBeanTaskMap.put(TaskName.IRB_ACKNOWLEDGEMENT, protocolIrbAcknowledgementBean);
        actionBeanTaskMap.put(TaskName.MODIFY_PROTOCOL_SUBMISSION, protocolModifySubmissionBean);
        actionBeanTaskMap.put(TaskName.NOTIFY_IRB, protocolNotifyIrbBean);
        actionBeanTaskMap.put(TaskName.REOPEN_PROTOCOL, protocolReopenEnrollmentBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, protocolReOpenEnrollmentRequestBean);
        actionBeanTaskMap.put(TaskName.RESPONSE_APPROVAL, protocolResponseApprovalBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE, protocolCloseRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, protocolCloseEnrollmentRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, protocolReOpenEnrollmentRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, protocolDataAnalysisRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_SUSPENSION, protocolSuspendRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_TERMINATE, protocolTerminateRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_WITHDRAW_SUBMISSION, protocolWithdrawSubmissionBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED, protocolReviewNotRequiredBean);
        actionBeanTaskMap.put(TaskName.SUSPEND_PROTOCOL_BY_DSMB, protocolSuspendByDsmbBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_SUSPENSION, protocolSuspendRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_TERMINATE, protocolTerminateRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_UNDO_LAST_ACTION, undoLastActionBean);
    }
    
    /**
     *     
     * This method builds a ProtocolGenericActionBean.  A number of different beans
     * in this object are of type ProtocolGenericActionBean, and all need to add
     * reviewer comments.  This encapsulates that.
     * @return a ProtocolGenericActionBean, and pre-populated with reviewer comments if any exist
     */
    protected ProtocolGenericActionBean buildProtocolGenericActionBean(String actionTypeCode, String errorPropertyKey) {
        ProtocolGenericActionBean bean = new ProtocolGenericActionBean(this, errorPropertyKey);
        
        bean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());            
        ProtocolAction protocolAction = (ProtocolAction) findProtocolAction(actionTypeCode, getProtocol().getProtocolActions(), getProtocol().getProtocolSubmission());
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            bean.setActionDate(new Date(protocolAction.getActionDate().getTime()));
        }
        
        return bean;
    }

    private ProtocolExpeditedApproveBean buildProtocolExpeditedApproveBean(String actionTypeCode) throws Exception {
        
        ProtocolExpeditedApproveBean bean = new ProtocolExpeditedApproveBean(this);
        
        bean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        ProtocolAction protocolAction = (ProtocolAction) findProtocolAction(actionTypeCode, getProtocol().getProtocolActions(), getProtocol().getProtocolSubmission());
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            bean.setActionDate(new Date(protocolAction.getActionDate().getTime()));
        }
        bean.setApprovalDate(buildApprovalDate(getProtocol()));
        bean.setExpirationDate(buildExpirationDate(getProtocol(), bean.getApprovalDate()));
        bean.setDefaultExpirationDateDifference(this.getDefaultExpirationDateDifference());
        return bean;
    }    

    /**
     * This method copies the settings from the ProtocolAmendRenewal bo to the amendmentBean and enables the
     * corresponding modules. 
     * @param amendmentBean
     */
    protected void populateExistingAmendmentBean(org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean amendmentBean, List<String> moduleTypeCodes) {
        ProtocolAmendRenewal protocolAmendRenewal = (ProtocolAmendRenewal) getProtocol().getProtocolAmendRenewal();
        amendmentBean.setSummary(protocolAmendRenewal.getSummary());
        for (ProtocolAmendRenewModuleBase module : protocolAmendRenewal.getModules()) {
            moduleTypeCodes.add(module.getProtocolModuleTypeCode());
            if (StringUtils.equals(ProtocolModule.GENERAL_INFO, module.getProtocolModuleTypeCode())) {
                amendmentBean.setGeneralInfo(true);
            } 
            else if (StringUtils.equals(ProtocolModule.ADD_MODIFY_ATTACHMENTS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setAddModifyAttachments(true);
            }
            else if (StringUtils.equals(ProtocolModule.AREAS_OF_RESEARCH, module.getProtocolModuleTypeCode())) {
                amendmentBean.setAreasOfResearch(true);
            }
            else if (StringUtils.equals(ProtocolModule.FUNDING_SOURCE, module.getProtocolModuleTypeCode())) {
                amendmentBean.setFundingSource(true);
            }
            else if (StringUtils.equals(ProtocolModule.OTHERS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setOthers(true);
            }
            else if (StringUtils.equals(ProtocolModule.PROTOCOL_ORGANIZATIONS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolOrganizations(true);
            }
            else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERSONNEL, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolPersonnel(true);
            }
            else if (StringUtils.equals(ProtocolModule.PROTOCOL_REFERENCES, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolReferencesAndOtherIdentifiers(true);
            }
            else if (StringUtils.equals(ProtocolModule.SPECIAL_REVIEW, module.getProtocolModuleTypeCode())) {
                amendmentBean.setSpecialReview(true);
            }
            else if (StringUtils.equals(ProtocolModule.SUBJECTS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setSubjects(true);
            }
            else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERMISSIONS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolPermissions(true);
            }
            else if (StringUtils.equals(ProtocolModule.QUESTIONNAIRE, module.getProtocolModuleTypeCode())) {
                amendmentBean.setQuestionnaire(true);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private UndoLastActionBean createUndoLastActionBean(Protocol protocol) throws Exception {
        undoLastActionBean = new UndoLastActionBean(this);
        undoLastActionBean.setProtocol(protocol);
        Collections.sort((List)protocol.getProtocolActions(), new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
        undoLastActionBean.setActionsPerformed((List)protocol.getProtocolActions());
        return undoLastActionBean;
    }
    
    /**
     * Enable a module for selection by a user by setting its corresponding enabled
     * flag to true in the amendment bean.
     * @param moduleTypeCode
     * @param amendmentBean
     */
    protected void enableModuleOption(String moduleTypeCode, ProtocolEditableBean amendmentBean) {
        if (StringUtils.equals(ProtocolModule.GENERAL_INFO, moduleTypeCode)) {
            amendmentBean.setGeneralInfoEnabled(true);
        } 
        else if (StringUtils.equals(ProtocolModule.ADD_MODIFY_ATTACHMENTS, moduleTypeCode)) {
            amendmentBean.setAddModifyAttachmentsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.AREAS_OF_RESEARCH, moduleTypeCode)) {
            amendmentBean.setAreasOfResearchEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.FUNDING_SOURCE, moduleTypeCode)) {
            amendmentBean.setFundingSourceEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.OTHERS, moduleTypeCode)) {
            amendmentBean.setOthersEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_ORGANIZATIONS, moduleTypeCode)) {
            amendmentBean.setProtocolOrganizationsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERSONNEL, moduleTypeCode)) {
            amendmentBean.setProtocolPersonnelEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_REFERENCES, moduleTypeCode)) {
            amendmentBean.setProtocolReferencesEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.SPECIAL_REVIEW, moduleTypeCode)) {
            amendmentBean.setSpecialReviewEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.SUBJECTS,moduleTypeCode)) {
            amendmentBean.setSubjectsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERMISSIONS,moduleTypeCode)) {
            amendmentBean.setProtocolPermissionsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.QUESTIONNAIRE,moduleTypeCode)) {
            amendmentBean.setQuestionnaireEnabled(true);
        }
    }

    protected ProtocolAmendRenewService getProtocolAmendRenewServiceHook() {
        if (this.protocolAmendRenewService == null) {
            this.protocolAmendRenewService = KcServiceLocator.getService(ProtocolAmendRenewService.class);
        }
        return (ProtocolAmendRenewService) this.protocolAmendRenewService;
    }

    public void prepareView() throws Exception {
        
        super.prepareView();
        assignCmtSchedBean.init();
        prepareAssignCommitteeScheduleActionView();
        prepareModifySubmissionRequestActionView();
        prepareNotifyIrbActionView();
       
        protocolAssignReviewersBean.prepareView();
        protocolExpeditedApprovalBean.prepareView();
        
        
        
        canRequestClose = hasRequestClosePermission();
        canRequestCloseUnavailable = hasRequestCloseUnavailablePermission();
        canRequestSuspension = hasRequestSuspensionPermission();
        canRequestSuspensionUnavailable = hasRequestSuspensionUnavailablePermission();
        canRequestCloseEnrollment = hasRequestCloseEnrollmentPermission();
        canRequestCloseEnrollmentUnavailable = hasRequestCloseEnrollmentUnavailablePermission();
        canRequestReOpenEnrollment = hasRequestReOpenEnrollmentPermission();
        canRequestReOpenEnrollmentUnavailable = hasRequestReOpenEnrollmentUnavailablePermission();
        canRequestDataAnalysis = hasRequestDataAnalysisPermission();
        canRequestDataAnalysisUnavailable = hasRequestDataAnalysisUnavailablePermission();
        canWithdrawSubmission = hasWithdrawSubmissionPermission();
        canWithdrawSubmissionUnavailable = hasWithdrawSubmissionUnavailablePermission();
        canRequestTerminate = hasRequestTerminatePermission();
        canRequestTerminateUnavailable = hasRequestTerminateUnavailablePermission();

        canAssignReviewers = hasAssignReviewersPermission();
        // we will do the workflow-heavy check for reviewer assignment only if the user can submit the protocol
        if(canSubmitProtocol) {
            canAssignReviewersCmtSel = hasAssignReviewersCmtSel();
        } 
        else {
            canAssignReviewersCmtSel = false;
        }
        canAssignReviewersUnavailable = hasAssignReviewersUnavailablePermission();
        canGrantExemption = hasGrantExemptionPermission();
        canGrantExemptionUnavailable = hasGrantExemptionUnavailablePermission();
        canApproveExpedited = hasExpeditedApprovalPermission();
        canApproveExpeditedUnavailable = hasExpeditedApprovalUnavailablePermission();
        canApproveResponse = hasResponseApprovalPermission();
        canApproveResponseUnavailable = hasResponseApprovalUnavailablePermission();
        canReopenEnrollment = hasReopenEnrollmentPermission();
        canReopenEnrollmentUnavailable = hasReopenEnrollmentUnavailablePermission();
        canCloseEnrollment = hasCloseEnrollmentPermission();
        canCloseEnrollmentUnavailable = hasCloseEnrollmentUnavailablePermission();
        canSuspendByDsmb = hasSuspendByDsmbPermission();
        canSuspendByDsmbUnavailable = hasSuspendByDsmbUnavailablePermission();
        canClose = hasClosePermission();
        canCloseUnavailable = hasCloseUnavailablePermission();
        canPermitDataAnalysis = hasPermitDataAnalysisPermission();
        canPermitDataAnalysisUnavailable = hasPermitDataAnalysisUnavailablePermission();
        canEnterRiskLevel = hasEnterRiskLevelPermission();
        canUndoLastAction = hasUndoLastActionPermission();
        canUndoLastActionUnavailable = hasUndoLastActionUnavailablePermission();
        canIrbAcknowledgement = hasIrbAcknowledgementPermission();
        canIrbAcknowledgementUnavailable = hasIrbAcknowledgementUnavailablePermission();
        canDefer = hasDeferPermission();
        canDeferUnavailable = hasDeferUnavailablePermission();
        canReviewNotRequired = hasReviewNotRequiredPermission();
        canReviewNotRequiredUnavailable = hasReviewNotRequiredUnavailablePermission();
        canManageNotes = hasManageNotesPermission();
        canManageNotesUnavailable = hasManageNotesUnavailablePermission();
        canReturnToPI = hasPermission(TaskName.RETURN_TO_PI_PROTOCOL);
        canReturnToPIUnavailable = hasPermission(TaskName.RETURN_TO_PI_PROTOCOL_UNAVAILABLE);
        canAddCloseReviewerComments = hasCloseRequestLastAction();
        canAddCloseEnrollmentReviewerComments = hasCloseEnrollmentRequestLastAction();
        canAddDataAnalysisReviewerComments = hasDataAnalysisRequestLastAction();
        canAddReopenEnrollmentReviewerComments = hasReopenEnrollmentRequestLastAction();
        hideReviewerName = checkToHideReviewName();
        hidePrivateFinalFlagsForPublicCommentsAttachments = checkToHidePrivateFinalFlagsForPublicCommentsAttachments();        
        undoLastActionBean = createUndoLastActionBean((Protocol) getProtocol());
       
        initSummaryDetails();

        setAmendmentDetails();
        initFilterDatesView();

        this.populateSubmissionQuestionnaires();
    }
    
    
    
    private void prepareNotifyIrbActionView() {
        canNotifyIrb = hasNotifyIrbPermission();
        canNotifyIrbUnavailable = hasNotifyIrbUnavailablePermission();
        // Initialize the notify irb key values (expensive call) only after checking the conditions for the display of the committee selection
        if(canNotifyIrb && isShowCommittee()) {            
            // pass in the current committee id and the doc route status to the committee finder service
            Collection<? extends CommitteeBase<?, ?, ?>> committees = 
                getCommitteeIdByUnitValuesFinderService().getAssignmentCommittees(null, getDocRouteStatus(), protocolNotifyIrbBean.getCommitteeId());
            if(!committees.isEmpty()) {
                notifyIrbActionCommitteeIdByUnitKeyValues = getKeyValuesForCommitteeSelection(committees);
                setCurrentUserAuthorizedToAssignCommittee(true);
            }else {
                setCurrentUserAuthorizedToAssignCommittee(false);
            }
        }        
    }


    private void prepareAssignCommitteeScheduleActionView() {
        assignCmtSchedBean.prepareView();
        canAssignCmtSched = hasAssignCmtSchedPermission();
        canAssignCmtSchedUnavailable = hasAssignCmtSchedUnavailablePermission();
        // Initialize the assign committee key values (expensive call) only after checking the conditions for the display of the committee selection
        if(canAssignCmtSched) {
            // pass in the current committee id and the doc route status to the committee finder service
            Collection<? extends CommitteeBase<?, ?, ?>> committees = 
                getCommitteeIdByUnitValuesFinderService().getAssignmentCommittees(null, getDocRouteStatus(), assignCmtSchedBean.getCommitteeId());
            assignCmtSchedActionCommitteeIdByUnitKeyValues = getKeyValuesForCommitteeSelection(committees);
        }
    }    
    
    //This method was created to deal with assign to committee and schedule action panel going away and 
    //being replaced by the modify submission request action panel but all of the underlying assign to 
    //committee and schedule processing and data beans being left in place.  
    private void prepareModifySubmissionRequestActionView() {
        protocolModifySubmissionBean.prepareView();
        canModifyProtocolSubmission = hasCanModifySubmissionPermission();
        canModifyProtocolSubmissionUnavailable = hasCanModifySubmissionUnavailablePermission();
        // Initialize the assign committee key values (expensive call) only after checking the conditions for the display of the committee selection
        if(canModifyProtocolSubmission) {
            // pass in the current committee id and the doc route status to the committee finder service
            Collection<? extends CommitteeBase<?, ?, ?>> committees = 
                getCommitteeIdByUnitValuesFinderService().getAssignmentCommittees(null, getDocRouteStatus(), assignCmtSchedBean.getCommitteeId());
            assignCmtSchedActionCommitteeIdByUnitKeyValues = getKeyValuesForCommitteeSelection(committees);
        }
    }
    
    /**
     * Refreshes the comments for all the beans from the database.  Use sparingly since this will erase non-persisted comments.
     */
    public void prepareCommentsView() {
        
        super.prepareCommentsView();
        protocolGrantExemptionBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolIrbAcknowledgementBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolExpeditedApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolResponseApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolReopenEnrollmentBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolCloseEnrollmentBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolSuspendByDsmbBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolCloseBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolPermitDataAnalysisBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        protocolDeferBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
    }
    
    protected ProtocolVersionService getProtocolVersionService() {
        if (this.protocolVersionService == null) {
            this.protocolVersionService = KcServiceLocator.getService(ProtocolVersionService.class);
        }
        return (ProtocolVersionService) this.protocolVersionService;
    }
    
    private ProtocolSubmitActionService getProtocolSubmitActionService() {
        if (protocolSubmitActionService == null) {
            protocolSubmitActionService = KcServiceLocator.getService(ProtocolSubmitActionService.class);
        }
        return protocolSubmitActionService;
    }

    
    private boolean hasNotifyIrbPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_IRB, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasNotifyIrbUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_IRB_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    private boolean hasRequestClosePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestCloseUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestSuspensionPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_SUSPENSION, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestSuspensionUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_SUSPENSION_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestCloseEnrollmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestCloseEnrollmentUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestReOpenEnrollmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestReOpenEnrollmentUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestDataAnalysisPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestDataAnalysisUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestTerminatePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_TERMINATE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestTerminateUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_TERMINATE_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasWithdrawSubmissionPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_WITHDRAW_SUBMISSION, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasWithdrawSubmissionUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_WITHDRAW_SUBMISSION_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignCmtSchedPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignCmtSchedUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignReviewersPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignReviewersUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignReviewersCmtSel() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS_CMT_SEL, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasGrantExemptionPermission() {
        return hasPermission(TaskName.GRANT_EXEMPTION);
    }
    
    private boolean hasGrantExemptionUnavailablePermission() {
        return hasPermission(TaskName.GRANT_EXEMPTION_UNAVAILABLE);
    }

    private boolean hasExpeditedApprovalPermission() {
        return hasPermission(TaskName.EXPEDITE_APPROVAL);
    }
    
    private boolean hasExpeditedApprovalUnavailablePermission() {
        return hasPermission(TaskName.EXPEDITE_APPROVAL_UNAVAILABLE);
    }
    
    private boolean hasResponseApprovalPermission() {
        return hasPermission(TaskName.RESPONSE_APPROVAL);
    }
    
    private boolean hasResponseApprovalUnavailablePermission() {
        return hasPermission(TaskName.RESPONSE_APPROVAL_UNAVAILABLE);
    }
        
    private boolean hasReopenEnrollmentPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.REOPEN_PROTOCOL);
    }
    
    private boolean hasReopenEnrollmentUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.REOPEN_PROTOCOL);
    }
    
    private boolean hasCloseEnrollmentPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL);
    }
    
    private boolean hasCloseEnrollmentUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL);
    }
        
    protected boolean hasSuspendPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL);
    }
    
    protected boolean hasSuspendUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL);
    }
    
    @Override
    protected ProtocolTaskBase getSuspendTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }

    @Override
    protected ProtocolTaskBase getSuspendUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }
    
    
    
    private boolean hasSuspendByDsmbPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB);
    }
    
    private boolean hasSuspendByDsmbUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB);
    }
    
    private boolean hasClosePermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.CLOSE_PROTOCOL);
    }
    
    private boolean hasCloseUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.CLOSE_PROTOCOL);
    }
    
    protected boolean hasExpirePermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.EXPIRE_PROTOCOL);
    }
    
    protected boolean hasExpireUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.EXPIRE_PROTOCOL);
    }
    
    protected boolean hasTerminatePermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.TERMINATE_PROTOCOL);
    }
    
    protected boolean hasTerminateUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.TERMINATE_PROTOCOL);
    }
    
    
    
    
// hooks from overridden methods given null impls
    
    @Override
    protected ProtocolTaskBase getExpireTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }

    @Override
    protected ProtocolTaskBase getExpireUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }
    
    @Override
    protected ProtocolTaskBase getTerminateTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }

    @Override
    protected ProtocolTaskBase getTerminateUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }
    

    
    // IRB specific
    private boolean hasPermitDataAnalysisPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS);
    }
    
    // IRB specific
    private boolean hasPermitDataAnalysisUnavailablePermission() {
        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS);
    }
   
            
    protected boolean hasAdminCorrectionPermission() {
        return hasPermission(TaskName.PROTOCOL_ADMIN_CORRECTION);
    }
    
    protected boolean hasAdminCorrectionUnavailablePermission() {
        return hasPermission(TaskName.PROTOCOL_ADMIN_CORRECTION_UNAVAILABLE);
    }
    
    protected boolean hasUndoLastActionPermission() {
        return hasPermission(TaskName.PROTOCOL_UNDO_LAST_ACTION) && undoLastActionBean.canUndoLastAction();
    }
    
    protected boolean hasUndoLastActionUnavailablePermission() {
        return hasPermission(TaskName.PROTOCOL_UNDO_LAST_ACTION) && !undoLastActionBean.canUndoLastAction();
    }
    
 // hooks from overridden methods given null impls
    
    @Override
    protected ProtocolTaskBase getAdminCorrectionProtocolTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }

    @Override
    protected ProtocolTaskBase getAdminCorrectionUnavailableProtocolTaskInstanceHook(ProtocolBase protocol) {
        return null;
    }

    private boolean hasEnterRiskLevelPermission() {
        return hasPermission(TaskName.ENTER_RISK_LEVEL);
    }
    
    private boolean hasDeferPermission() {
        return hasPermission(TaskName.DEFER_PROTOCOL);
    }
    
    private boolean hasDeferUnavailablePermission() {
        return hasPermission(TaskName.DEFER_PROTOCOL_UNAVAILABLE);
    }
    
    protected boolean hasApproveOtherPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_APPROVE_OTHER, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasManageNotesPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_MANAGE_NOTES, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasManageNotesUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_MANAGE_NOTES_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasGenericPermission(String genericActionName) {
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, (Protocol) getProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasGenericUnavailablePermission(String genericActionName) {
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION_UNAVAILABLE, (Protocol) getProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasIrbAcknowledgementPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.IRB_ACKNOWLEDGEMENT, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasIrbAcknowledgementUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.IRB_ACKNOWLEDGEMENT_UNAVAILABLE, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    private boolean hasReviewNotRequiredPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED, (Protocol) getProtocol());
        boolean retVal = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        return retVal;
    }
    
    private boolean hasReviewNotRequiredUnavailablePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED_UNAVAILABLE, (Protocol) getProtocol());
        boolean retVal = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        return retVal;
    }
    
    private boolean hasCloseRequestLastAction() {
        return ProtocolActionType.REQUEST_TO_CLOSE.equals(getLastPerformedAction().getProtocolActionTypeCode());
    }
    
    private boolean hasCloseEnrollmentRequestLastAction() {
        return ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT.equals(getLastPerformedAction().getProtocolActionTypeCode());
    }
    
    private boolean hasDataAnalysisRequestLastAction() {
        return ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY.equals(getLastPerformedAction().getProtocolActionTypeCode());
    }
    
    private boolean hasReopenEnrollmentRequestLastAction() {
        return ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT.equals(getLastPerformedAction().getProtocolActionTypeCode());
    }
    
    private boolean hasSuspendRequestLastAction() {
        return ProtocolActionType.REQUEST_FOR_SUSPENSION.equals(getLastPerformedAction().getProtocolActionTypeCode());
    }
    
    private boolean hasTerminateRequestLastAction() {
        return ProtocolActionType.REQUEST_FOR_TERMINATION.equals(getLastPerformedAction().getProtocolActionTypeCode());
    }
    
    public Protocol getProtocol() {
        return (Protocol) super.getProtocol();
    }
    
    public ProtocolRequestBean getProtocolCloseEnrollmentRequestBean() {
        return protocolCloseEnrollmentRequestBean;
    }

    public ProtocolRequestBean getProtocolReOpenEnrollmentRequestBean() {
        return protocolReOpenEnrollmentRequestBean;
    }
    
    public ProtocolRequestBean getProtocolDataAnalysisRequestBean() {
        return protocolDataAnalysisRequestBean;
    }

    public ProtocolRequestBean getProtocolWithdrawSubmissionBean() {
        return protocolWithdrawSubmissionBean;
    }

    public ProtocolNotifyIrbBean getProtocolNotifyIrbBean() {
        return protocolNotifyIrbBean;
    }
    
    public ProtocolAssignCmtSchedBean getAssignCmtSchedBean() {
        return assignCmtSchedBean;
    }
    
    public ProtocolAssignReviewersBean getProtocolAssignReviewersBean() {
        return protocolAssignReviewersBean;
    }
                           
    public ProtocolGrantExemptionBean getProtocolGrantExemptionBean() {
        return protocolGrantExemptionBean;
    }

    public ProtocolApproveBean getProtocolExpeditedApprovalBean() {
        return protocolExpeditedApprovalBean;
    }
    
    public ProtocolApproveBean getProtocolResponseApprovalBean() {
        return protocolResponseApprovalBean;
    }
    
    public ProtocolGenericActionBean getProtocolReopenEnrollmentBean() {
        return protocolReopenEnrollmentBean;
    }
    
    public ProtocolGenericActionBean getProtocolCloseEnrollmentBean() {
        return protocolCloseEnrollmentBean;
    }
    
    public ProtocolGenericActionBean getProtocolSuspendByDsmbBean() {
        return protocolSuspendByDsmbBean;
    }
    
    public ProtocolGenericActionBean getProtocolPermitDataAnalysisBean() {
        return protocolPermitDataAnalysisBean;
    }
    
    public ProtocolGenericActionBean getProtocolIrbAcknowledgementBean() {
        return protocolIrbAcknowledgementBean;
    }

    public UndoLastActionBean getUndoLastActionBean() {
        if(null != undoLastActionBean) {
            undoLastActionBean.refreshActionsPerformed();
        }
        return undoLastActionBean;
    }
    
    public ProtocolModifySubmissionBean getProtocolModifySubmissionBean() {
        return this.protocolModifySubmissionBean;
    }
    
    public boolean getCanNotifyIrb() {
        return canNotifyIrb;
    }
    
    public boolean getCanNotifyIrbUnavailable() {
        return canNotifyIrbUnavailable;
    }
    
    public boolean getCanRequestClose() {
        return canRequestClose;
    }
    
    public boolean getCanRequestCloseUnavailable() {
        return canRequestCloseUnavailable;
    }
    
    public boolean getCanRequestCloseEnrollment() {
        return canRequestCloseEnrollment;
    }
    
    public boolean getCanRequestCloseEnrollmentUnavailable() {
        return canRequestCloseEnrollmentUnavailable;
    }
    
    public boolean getCanRequestReOpenEnrollment() {
        return canRequestReOpenEnrollment;
    }
    
    public boolean getCanRequestReOpenEnrollmentUnavailable() {
        return canRequestReOpenEnrollmentUnavailable;
    }
    
    public boolean getCanRequestDataAnalysis() {
        return canRequestDataAnalysis;
    }
    
    public boolean getCanRequestDataAnalysisUnavailable() {
        return canRequestDataAnalysisUnavailable;
    }    
    
    public boolean getCanGrantExemption() {
        return canGrantExemption;
    }
    
    public boolean isCanWithdrawSubmission() {
        return canWithdrawSubmission;
    }

    public boolean isCanWithdrawSubmissionUnavailable() {
        return canWithdrawSubmissionUnavailable;
    }

    public boolean getCanGrantExemptionUnavailable() {
        return canGrantExemptionUnavailable;
    }
    
    public boolean getCanApproveExpedited() {
        return canApproveExpedited;
    }
    
    public boolean getCanApproveExpeditedUnavailable() {
        return canApproveExpeditedUnavailable;
    }
    
    public boolean getCanReopenEnrollment() {
        return canReopenEnrollment;
    }
    
    public boolean getCanReopenEnrollmentUnavailable() {
        return canReopenEnrollmentUnavailable;
    }
    
    public boolean getCanCloseEnrollment() {
        return canCloseEnrollment;
    }
    
    public boolean getCanCloseEnrollmentUnavailable() {
        return canCloseEnrollmentUnavailable;
    }
    
    public boolean getCanSuspendByDsmb() {
        return canSuspendByDsmb;
    }
    
    public boolean getCanSuspendByDsmbUnavailable() {
        return canSuspendByDsmbUnavailable;
    }
    
    public boolean getCanPermitDataAnalysis() {
        return canPermitDataAnalysis;
    }
    
    public boolean getCanPermitDataAnalysisUnavailable() {
        return canPermitDataAnalysisUnavailable;
    }
    
    public boolean getCanEnterRiskLevel() {
        return canEnterRiskLevel;
    }
    
    public boolean getCanIrbAcknowledgement() {
        return canIrbAcknowledgement;
    }
    
    public boolean getCanIrbAcknowledgementUnavailable() {
        return canIrbAcknowledgementUnavailable;
    }
    
    public boolean getCanDefer() {
        return canDefer;
    }
    
    public boolean getCanDeferUnavailable() {
        return canDeferUnavailable;
    }
    
    public boolean getCanReviewNotRequired() {
        return this.canReviewNotRequired;
    }

    public boolean getCanReviewNotRequiredUnavailable() {
        return this.canReviewNotRequiredUnavailable;
    }

    public boolean getCanAddCloseReviewerComments() {
        return canAddCloseReviewerComments;
    }

    public boolean getCanAddCloseEnrollmentReviewerComments() {
        return canAddCloseEnrollmentReviewerComments;
    }

    public boolean getCanAddDataAnalysisReviewerComments() {
        return canAddDataAnalysisReviewerComments;
    }

    public boolean getCanAddReopenEnrollmentReviewerComments() {
        return canAddReopenEnrollmentReviewerComments;
    }

    public void setPrintTag(String printTag) {
        this.printTag = printTag;
    }
    
    public String getPrintTag() {
        return printTag;
    }
 
    /**
     * Prepares all protocol actions for being filtered by setting their isInFilterView attribute.
     */
    public void initFilterDatesView() {
        java.util.Date dayBeforeStartDate = null;
        java.util.Date dayAfterEndDate = null;

        if (filteredHistoryStartDate != null && filteredHistoryEndDate != null) {
            dayBeforeStartDate = DateUtils.addDays(filteredHistoryStartDate, -1);
            dayAfterEndDate = DateUtils.addDays(filteredHistoryEndDate, 1);
        }

        for (ProtocolActionBase protocolAction : getSortedProtocolActions()) {
            Timestamp actionDate = protocolAction.getActionDate();
            if (dayBeforeStartDate != null && dayAfterEndDate != null) {
                protocolAction.setIsInFilterView(actionDate.after(dayBeforeStartDate) && actionDate.before(dayAfterEndDate));
            }
            else {
                protocolAction.setIsInFilterView(true);
            }
            if (protocolAction.getIsInFilterView()) {
                ((ProtocolAction) protocolAction).setQuestionnairePrintOptionFromHelper(this);
            }
        }
    }
    
    // All the following methods were refactored into the ProtocolAction, where they belong
    
    /**
     * Prepares, sorts, and returns a list of protocol actions.
     * @return
     */
    public List<ProtocolActionBase> getSortedProtocolActions() {
        List<ProtocolAction> protocolActions = new ArrayList<ProtocolAction>();
        for (ProtocolActionBase protocolAction : form.getProtocolDocument().getProtocol().getProtocolActions()) {
            if (protocolAction.getSubmissionNumber() != null && ACTION_TYPE_SUBMISSION_DOC.contains(protocolAction.getProtocolActionTypeCode())) {
                protocolAction.setProtocolSubmissionDocs(new ArrayList<ProtocolSubmissionDocBase>(getSubmissionDocs(protocolAction)));
            }
            protocolActions.add((ProtocolAction) protocolAction);
        }
        
        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
     
        return (List)protocolActions;
    }
    
    protected CommitteeDecisionService getCommitteeDecisionService() {
        return KcServiceLocator.getService("protocolCommitteeDecisionService");
    }
    
    public int getTotalSubmissions() {
        return getProtocolSubmitActionService().getTotalSubmissions((Protocol) getProtocol());
    }
    
    /**
     * Sets up dates for the submission details subpanel.
     */
    public void initSubmissionDetails() {
        if (currentSubmissionNumber <= 0) {
            currentSubmissionNumber = getTotalSubmissions();
        }

        if (CollectionUtils.isNotEmpty(getProtocol().getProtocolSubmissions()) && getProtocol().getProtocolSubmissions().size() > 1) {
            setPrevNextFlag();
        } else {
            setPrevDisabled(true);
            setNextDisabled(true);
        }

        setReviewComments(getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        if (CollectionUtils.isNotEmpty(getReviewComments())) {
            // check if our comments bean has empty list of review comments, this can happen if the submission has no schedule assigned
            // also check that the list of deleted comments is empty, because deletion of comments can also lead to an empty list of review comments.
            if( (protocolManageReviewCommentsBean.getReviewCommentsBean().getReviewComments().size() == 0)
                && 
                (protocolManageReviewCommentsBean.getReviewCommentsBean().getDeletedReviewComments().size() == 0) ) {
                List<CommitteeScheduleMinute> reviewComments = getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), currentSubmissionNumber);
                Collections.sort(reviewComments, new Comparator<CommitteeScheduleMinute>() {

                    @Override
                    public int compare(CommitteeScheduleMinute csm1, CommitteeScheduleMinute csm2) {
                        int retVal = 0;
                        if( (csm1 != null) && (csm2 != null) && (csm1.getEntryNumber() != null) && (csm2.getEntryNumber() != null) ) {
                            retVal = csm1.getEntryNumber().compareTo(csm2.getEntryNumber());
                        }
                        return retVal;
                    }
                    
                });
                protocolManageReviewCommentsBean.getReviewCommentsBean().setReviewComments((List)reviewComments);
                getReviewerCommentsService().setHideReviewerName(reviewComments);
                setHidePrivateFinalFlagsForPublicCommentsAttachments(getReviewerCommentsService().isHidePrivateFinalFlagsForPI(reviewComments));                
            }
            getReviewerCommentsService().setHideReviewerName(getReviewComments());
            setHidePrivateFinalFlagsForPublicCommentsAttachments(getReviewerCommentsService().isHidePrivateFinalFlagsForPI(getReviewComments()));            
        }
        setReviewAttachments(getReviewerCommentsService().getReviewerAttachments(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        if (CollectionUtils.isNotEmpty(getReviewAttachments())) {
            hideReviewerNameForAttachment = getReviewerCommentsService().setHideReviewerName(getReviewAttachments());
            getReviewerCommentsService().setHideViewButton(getReviewAttachments());
        }
        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
        hideSubmissionReviewerName = checkToHideSubmissionReviewerName();

        populateReviewersAndOnlineReviewsMap();
        
        setAbstainees((List)getCommitteeDecisionService().getAbstainers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        setRecusers((List)getCommitteeDecisionService().getRecusers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        
        protocolSubmissionQuestionnaireHelper = new IrbSubmissionQuestionnaireHelper(this.getProtocol(), null, Integer.toString(currentSubmissionNumber), true);
        protocolSubmissionQuestionnaireHelper.populateAnswers();
        setSubmissionQuestionnaireExist(!protocolSubmissionQuestionnaireHelper.getAnswerHeaders().isEmpty());
    }

    /**
     * This method populates the protocolAmendmentBean with the amendment details from the 
     * current submission.
     * @throws Exception
     */
    protected void setAmendmentDetails() throws Exception {
        /*
         * Check if the user is trying to modify amendment sections, if so, do not setAmendmentDetails.
         * If you set it, the user's data gets refreshed and the amendment details from the currentSubmission
         * will be populated in the protocolAmendmentBean.
         */
        if (!currentTaskName.equalsIgnoreCase(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS)) {
            ProtocolAmendmentBean amendmentSummaryBean = (ProtocolAmendmentBean) getProtocolAmendmentSummaryBean();
            String originalProtocolNumber;
            // Use the submission number to get the correct amendment details
            if (getProtocol().isAmendment()) {
                originalProtocolNumber = getProtocol().getProtocolAmendRenewal().getProtocolNumber();           
            } else {
                // We want to display amendment details even if the document is not an amendment.
                // Amendment details needs to be displayed even after the amendment has been merged with the protocol.
                originalProtocolNumber = getProtocol().getProtocolNumber();
            }
            List<ProtocolBase> protocols = getProtocolAmendRenewServiceHook().getAmendmentAndRenewals(originalProtocolNumber);

            ProtocolAmendRenewal correctAmendment = (ProtocolAmendRenewal) getCorrectAmendment(protocols);
            
            if (ObjectUtils.isNotNull(correctAmendment)) {
                setSubmissionHasNoAmendmentDetails(false);
                amendmentSummaryBean.setSummary(correctAmendment.getSummary());
                amendmentSummaryBean.setGeneralInfo((correctAmendment.hasModule(ProtocolModule.GENERAL_INFO)) ? true : false);
                amendmentSummaryBean.setProtocolPersonnel((correctAmendment.hasModule(ProtocolModule.PROTOCOL_PERSONNEL)) ? true : false);
                amendmentSummaryBean.setAreasOfResearch((correctAmendment.hasModule(ProtocolModule.AREAS_OF_RESEARCH)) ? true : false);
                amendmentSummaryBean.setAddModifyAttachments((correctAmendment.hasModule(ProtocolModule.ADD_MODIFY_ATTACHMENTS)) ? true : false);
                amendmentSummaryBean.setFundingSource((correctAmendment.hasModule(ProtocolModule.FUNDING_SOURCE)) ? true : false);
                amendmentSummaryBean.setOthers((correctAmendment.hasModule(ProtocolModule.OTHERS)) ? true : false);
                amendmentSummaryBean.setProtocolOrganizations((correctAmendment.hasModule(ProtocolModule.PROTOCOL_ORGANIZATIONS)) ? true : false);
                amendmentSummaryBean.setProtocolPermissions((correctAmendment.hasModule(ProtocolModule.PROTOCOL_PERMISSIONS)) ? true : false);
                amendmentSummaryBean.setProtocolReferencesAndOtherIdentifiers((correctAmendment.hasModule(ProtocolModule.PROTOCOL_REFERENCES)) ? true : false);
                amendmentSummaryBean.setQuestionnaire((correctAmendment.hasModule(ProtocolModule.QUESTIONNAIRE)) ? true : false);
                amendmentSummaryBean.setSpecialReview((correctAmendment.hasModule(ProtocolModule.SPECIAL_REVIEW)) ? true : false);
                amendmentSummaryBean.setSubjects((correctAmendment.hasModule(ProtocolModule.SUBJECTS)) ? true : false);
            } else {
                setSubmissionHasNoAmendmentDetails(true);
            }
        }
    } 
    
    protected String getCoeusModule() {
        return CoeusModule.IRB_MODULE_CODE;
    }
    
    protected ModuleQuestionnaireBean getQuestionnaireBean(String moduleCode, String moduleKey, String subModuleCode, String subModuleKey, boolean finalDoc) {
        return new ProtocolModuleQuestionnaireBean(moduleCode, moduleKey, subModuleCode, subModuleKey, finalDoc);
    }

    public void addNotifyIrbAttachment() {
        getProtocolNotifyIrbBean().getActionAttachments().add(
                getProtocolNotifyIrbBean().getNewActionAttachment());
        getProtocolNotifyIrbBean().setNewActionAttachment(new ProtocolActionAttachment());
    }

    public boolean validFile(final ProtocolActionAttachment attachment, String propertyName) {
        
        boolean valid = true;
        
        //this got much more complex using anon keys
        if (attachment.getFile() == null || StringUtils.isBlank(attachment.getFile().getFileName())) {
            valid = false;
            KcServiceLocator.getService(ErrorReporter.class).reportError("actionHelper." + propertyName + ".newActionAttachment.file",
                KeyConstants.ERROR_ATTACHMENT_REQUIRED);
        }
        
        return valid;
    }

    public ProtocolGenericActionBean getProtocolDeferBean() {
        return protocolDeferBean;
    }

    
    public ProtocolReviewNotRequiredBean getProtocolReviewNotRequiredBean() {
        return this.protocolReviewNotRequiredBean;
    }

    public ProtocolRequestBean getRequestBean(String actionTypeCode) {
        ProtocolRequestBean protocolRequestBean = null;
        
        ProtocolRequestAction action = ProtocolRequestAction.valueOfActionTypeCode(actionTypeCode);
        if (action != null) {
            ProtocolActionBean bean = (ProtocolActionBean) actionBeanTaskMap.get(action.getTaskName());
            if (bean instanceof ProtocolRequestBean) {
                protocolRequestBean = (ProtocolRequestBean) bean;
            }
        }
        
        return protocolRequestBean;
    }
    
    public boolean isSubmissionQuestionnaireExist() {
        return submissionQuestionnaireExist;
    }

    public void setSubmissionQuestionnaireExist(boolean submissionQuestionnaireExist) {
        this.submissionQuestionnaireExist = submissionQuestionnaireExist;
    }

    public boolean isToAnswerSubmissionQuestionnaire() {
        return toAnswerSubmissionQuestionnaire;
    }

    public void setToAnswerSubmissionQuestionnaire(boolean toAnswerSubmissionQuestionnaire) {
        this.toAnswerSubmissionQuestionnaire = toAnswerSubmissionQuestionnaire;
    }

    public boolean isIrbAdmin() {
        return getSystemAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IRB_ADMINISTRATOR);
    }

    @Override
    protected String getSRRProtocolActionTypeHook() {
        return ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED;
    }

    @Override
    protected String getSMRProtocolActionTypeHook() {
        return ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED;
    }

    @Override
    protected String getReturnToPIActionTypeHook() {
        return ProtocolActionType.RETURNED_TO_PI;
    }

    @Override
    protected String getDisapprovedProtocolActionTypeHook() {
        return ProtocolActionType.DISAPPROVED;
    }

    @Override
    protected String getProtocolActionTypeCodeForManageReviewCommentsHook() {
        return ProtocolActionType.MANAGE_REVIEW_COMMENTS;
    }

    @Override
    protected org.kuali.kra.protocol.actions.decision.CommitteeDecision<?> getNewCommitteeDecisionInstanceHook(ActionHelperBase actionHelper) {
        return new CommitteeDecision((ActionHelper) actionHelper);
    }

    @Override
    protected org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaBean getNewProtocolAssignToAgendaBeanInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolAssignToAgendaBean((ActionHelper) actionHelper);
    }

    @Override
    protected ProtocolAdministrativelyWithdrawBean getNewProtocolAdminWithdrawBeanInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolAdministrativelyWithdrawBean((ActionHelper) actionHelper);
    }

    @Override
    protected ProtocolAdministrativelyIncompleteBean getNewProtocolAdminIncompleteBeanInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolAdministrativelyIncompleteBean((ActionHelper) actionHelper);
    }

    @Override
    protected String getAdminApprovalProtocolActionTypeHook() {
        return ProtocolActionType.APPROVED;
    }

    @Override
    protected String getFullApprovalProtocolActionTypeHook() {
        return ProtocolActionType.APPROVED;
    }

    @Override
    protected ProtocolWithdrawBean getNewProtocolWithdrawBeanInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolWithdrawBean((ActionHelper) actionHelper);
    }

    @Override
    protected ProtocolAmendmentBean getNewProtocolAmendmentBeanInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolAmendmentBean((ActionHelper) actionHelper);
    }

    @Override
    protected ProtocolNotifyCommitteeBean getNewProtocolNotifyCommitteeBeanInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolNotifyCommitteeBean((ActionHelper) actionHelper);
    }

    @Override
    protected ProtocolSubmitAction getNewProtocolSubmitActionInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolSubmitAction((ActionHelper) actionHelper);
    }

    @Override
    protected ProtocolDeleteBean getNewProtocolDeleteBeanInstanceHook(ActionHelperBase actionHelper) {
        return new ProtocolDeleteBean((ActionHelper) actionHelper);
    }

    @Override
    protected AdminCorrectionBean getNewAdminCorrectionBeanInstanceHook(ActionHelperBase actionHelper) {
        return new AdminCorrectionBean((ActionHelper) actionHelper);
    }

    @Override
    protected UndoLastActionBean getNewUndoLastActionBeanInstanceHook() {
        return new UndoLastActionBean(this);
    }

    @Override
    protected String getAbandonActionTypeHook() {
        return ProtocolActionType.ABANDON_PROTOCOL;
    }

    @Override
    protected String getAbandonPropertyKeyHook() {
        return Constants.PROTOCOL_ABANDON_ACTION_PROPERTY_KEY;
    }

    @Override
    protected String getExpireKeyHook() {
        return ProtocolActionType.EXPIRED;
    }

    @Override
    protected String getTerminateKeyHook() {
        return ProtocolActionType.TERMINATED;
    }

    @Override
    protected String getSuspendKeyHook() {
        return ProtocolActionType.SUSPENDED;
    }

    @Override
    protected Class<ReviewCommentsService> getReviewCommentsServiceClassHook() {
        return ReviewCommentsService.class;
    }

    @Override
    protected ProtocolApproveBean getNewProtocolApproveBeanInstanceHook(ActionHelperBase actionHelper, String errorPropertyKey) {
        return new ProtocolApproveBean((ActionHelper) actionHelper, errorPropertyKey);
    }

    @Override
    protected ProtocolTaskBase getNewAdminApproveProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ADMIN_APPROVE_PROTOCOL, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewAdminApproveUnavailableProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ADMIN_APPROVE_PROTOCOL_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewAdminWithdrawProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ADMIN_WITHDRAW_PROTOCOL, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewAdminWithdrawUnavailableProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ADMIN_WITHDRAW_PROTOCOL_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewAdminMarkIncompleteProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ADMIN_INCOMPLETE_PROTOCOL, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewAdminMarkIncompleteUnavailableProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ADMIN_INCOMPLETE_PROTOCOL_UNAVAILABLE, getProtocol());
    }

    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return CommitteeScheduleService.class;
    }

    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook() {
        return ProtocolDocument.class;
    }

    @Override
    protected ProtocolTaskBase getNewSubmitProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.SUBMIT_PROTOCOL, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewSubmitProtocolUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.SUBMIT_PROTOCOL_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewAmendmentProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.CREATE_PROTOCOL_AMMENDMENT, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewAmendmentProtocolUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.CREATE_PROTOCOL_AMMENDMENT_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getModifyAmendmentSectionsProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getModifyAmendmentSectionsUnavailableProtocolUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewRenewalProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.CREATE_PROTOCOL_RENEWAL, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewRenewalProtocolUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.CREATE_PROTOCOL_RENEWAL_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewWithdrawProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.PROTOCOL_WITHDRAW, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewWithdrawProtocolUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.PROTOCOL_WITHDRAW_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase createNewAmendRenewDeleteTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase createNewAmendRenewDeleteUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE_UNAVAILABLE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase createNewAssignToAgendaTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ASSIGN_TO_AGENDA, getProtocol());
    }

    @Override
    protected ProtocolTaskBase createNewAssignToAgendaUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ASSIGN_TO_AGENDA_UNAVAILABLE, getProtocol());
    }
    
    @Override
    protected ProtocolTaskBase createNewAbandonTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ABANDON_PROTOCOL, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getModifySubmissionAvailableTaskHook() {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_SUBMISSION, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getModifySubmissionUnavailableTaskHook() {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_SUBMISSION_UNAVAILABLE, getProtocol());
    }
    
    @Override
    protected ProtocolTaskBase getNewNotifyCommitteeTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.NOTIFY_COMMITTEE, getProtocol());
    }

    @Override
    protected ProtocolTaskBase getNewNotifyCommitteeUnavailableTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.NOTIFY_COMMITTEE_UNAVAILABLE, getProtocol());
    }

    @Override
    protected Class<? extends ProtocolSubmissionDocBase> getProtocolSubmissionDocClassHook() {
        return ProtocolSubmissionDoc.class;
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.actions.followup.FollowupActionService<?>> getFollowupActionServiceClassHook() {
        return FollowupActionService.class;
    }

    @Override
    protected ProtocolQuestionnairePrintingService getProtocolQuestionnairePrintingServiceHook() {
        return KcServiceLocator.getService(ProtocolQuestionnairePrintingService.class);
    }

    @Override
    protected ProtocolModuleQuestionnaireBeanBase getNewProtocolModuleQuestionnaireBeanInstanceHook(ProtocolBase protocol) {
        return new ProtocolModuleQuestionnaireBean((Protocol) protocol);
    }

    @Override
    protected void enableModuleOption(org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean amendmentBean, ProtocolAmendRenewalBase correctAmendment) {
        // deliberately empty implementation since the calling method for this hook has been overridden making it a redundant
        // hook. See backfit notes.
        // do nothing
    }

    @Override
    protected ProtocolSubmissionQuestionnaireHelper getProtocolSubmissionQuestionnaireHelperHook(ProtocolBase protocol,
            String actionTypeCode, String submissionNumber, boolean finalDoc) {
        return new IrbSubmissionQuestionnaireHelper(protocol, actionTypeCode, submissionNumber, finalDoc);
    }
    
    // we suppress the reviewer display if the current user does not have permission to assign reviewers during submission or 
    // if a committee is not selected, or if a schedule is not selected in case of a non-expedited review type.
    // Putting this logic in the helper to avoid creating a verbose JSTL-EL expression in the tag file
    public boolean isReviewersDisplayToBeSuppressed() {
        boolean retVal = false;
        ProtocolSubmitAction submitBean = (ProtocolSubmitAction) this.getProtocolSubmitAction(); 
        if( (!this.canAssignReviewersCmtSel) || 
            (StringUtils.isBlank(submitBean.getScheduleId()) && !(submitBean.isExpeditedProtocolReviewType())) || 
            (StringUtils.isBlank(submitBean.getCommitteeId())) ) {
            
            retVal = true;
        }
        return retVal;
    }

    @Override
    protected void initializeSubmissionConstraintHook() {
        submissionConstraint = getParameterValue(Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);        
    }
   

    @Override
    protected ProtocolTaskBase getNewProtocolTaskInstanceHook(String taskName) {
        return new ProtocolTask(taskName, getProtocol());
    }

    @Override
    protected Class<? extends ProtocolCorrespondenceAuthorizationService> getProtocolCorrespondenceAuthorizationServiceClassHook() {
        return IrbProtocolCorrespondenceAuthorizationService.class;
    }

    @Override
    protected Class<? extends CommitteeIdByUnitValuesFinderService<?>> getCommitteeIdByUnitValuesFinderServiceClassHook() {
        return IrbCommitteeIdByUnitValuesFinderService.class;
    }
    
    public List<KeyValue> getNotifyIrbActionCommitteeIdByUnitKeyValues() {
        return notifyIrbActionCommitteeIdByUnitKeyValues;
    }
    
    public List<KeyValue> getAssignCmtSchedActionCommitteeIdByUnitKeyValues() {
        return assignCmtSchedActionCommitteeIdByUnitKeyValues;
    }

    @Override
    protected void initPrintCorrespondence() {
        List<CorrespondencePrintOption> printOptions = new ArrayList<CorrespondencePrintOption>();
        Map<String, Object> values = new HashMap<String, Object>();
        List<ProtocolCorrespondenceType> correspondenceTypes = (List<ProtocolCorrespondenceType>)
                KcServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolCorrespondenceType.class,values);
        for(ProtocolCorrespondenceType correspondenceType : correspondenceTypes) {
            if(StringUtils.equals(correspondenceType.getModuleId(),CorrespondenceTypeModuleIdConstants.PROTOCOL.getCode())) {
                CorrespondencePrintOption printOption = new CorrespondencePrintOption();
                printOption.setDescription(correspondenceType.getDescription());
                printOption.setLabel(correspondenceType.getDescription());
                printOption.setCorrespondenceId(1L);
                printOption.setProtocolCorrespondenceTemplate(correspondenceType.getDefaultProtocolCorrespondenceTemplate());
                printOptions.add(printOption);
            }
        }
        setCorrespondencesToPrint(printOptions);
    }

    public boolean isCurrentUserAuthorizedToAssignCommittee() {
        return currentUserAuthorizedToAssignCommittee;
    }

    public void setCurrentUserAuthorizedToAssignCommittee(boolean currentUserAuthorizedToAssignCommittee) {
        this.currentUserAuthorizedToAssignCommittee = currentUserAuthorizedToAssignCommittee;
    }

}
