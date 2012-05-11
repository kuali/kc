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
package org.kuali.kra.iacuc.actions;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.iacuc.actions.notifyiacuc.ProtocolNotifyIacucBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.table.IacucProtocolTableBean;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolWithdrawBean;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.util.DateUtils;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * The form helper class for the Protocol Actions tab.
 */
public class IacucActionHelper extends ActionHelper {

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private boolean canDeleteIacucProtocol;
    private boolean canDeleteIacucProtocolUnavailable;
    private boolean canAdministrativelyApprove;
    private boolean canAdministrativelyApproveUnavailable;
    private boolean canAdministrativelyMarkIncomplete;
    private boolean canAdministrativelyMarkIncompleteUnavailable;
    private boolean canAdministrativelyWithdraw;
    private boolean canAdministrativelyWithdrawUnavailable;
    private boolean canReturnToPI;
    private boolean canReturnToPIUnavailable;
    private boolean canReviewNotRequired;
    private boolean canReviewNotRequiredUnavailable;
    private boolean canNotifyIacuc = false;
    private boolean canNotifyIacucUnavailable = false;
    private boolean canDesignatedMemberApproval = false;
    private boolean canDesignatedMemberApprovalUnavailable = false;
    private boolean canHold = false;
    private boolean canHoldUnavailable = false;
    private boolean canLiftHold = false;
    private boolean canLiftHoldUnavailable = false;
    private boolean canRequestToLiftHold = false;
    private boolean canRequestToLiftHoldUnavailable = false;
    private boolean canTable = false;
    private boolean canTableUnavailable = false;
    private boolean canIacucAcknowledge = false;
    private boolean canIacucAcknowledgeUnavailable = false;
    private boolean canIacucRequestDeactivate = false;
    private boolean canIacucRequestDeactivateUnavailable = false;
    
    // action beans that are specific to IACUC
    protected IacucProtocolTableBean iacucProtocolTableBean;
    protected ProtocolNotifyIacucBean protocolNotifyIacucBean;    

    /**
     * Constructs an ActionHelper.
     * @param form the protocol form
     * @throws Exception 
     */
    public IacucActionHelper(ProtocolForm form) throws Exception {
        super(form);
        
        protocolSubmitAction = new IacucProtocolSubmitAction(this);
        protocolWithdrawBean = new IacucProtocolWithdrawBean(this);

        iacucProtocolTableBean = new IacucProtocolTableBean(this);

        
// TODO *********commented the code below during IACUC refactoring*********     
        // setting the attachment here so new files can be attached to newActionAttachment
//        protocolNotifyIrbBean.setNewActionAttachment(new ProtocolActionAttachment());
//        protocolNotifyCommitteeBean = new ProtocolNotifyCommitteeBean(this);

//TODO: Need to put IACUC-specific beans here        
//        protocolGrantExemptionBean = new ProtocolGrantExemptionBean(this);
//        protocolGrantExemptionBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        initActionBeanTaskMap();
        
//        protocolSummaryPrintOptions = new ProtocolSummaryPrintOptions();
//        toAnswerSubmissionQuestionnaire = hasSubmissionQuestionnaire();
//        protocolPrintOption = new ProtocolSummaryPrintOptions();
//        initPrintQuestionnaire();
    }
    
    /**
     * Initializes the mapping between the task names and the beans.  This is used to get the bean associated to the task name passed in from the tag file.
     * The reason TaskName (a text code) is used and ProtocolActionType (a number code) is not is because not every task is mapped to a ProtocolActionType.
     */
    private void initActionBeanTaskMap() {
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION, protocolAdminCorrectionBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT, protocolAmendmentBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.CREATE_IACUC_PROTOCOL, createIacucProtocolBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL, modifyIacucProtocolBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.VIEW_IACUC_PROTOCOL, viewIacucProtocolBean);
        actionBeanTaskMap.put(TaskName.SUBMIT_PROTOCOL, protocolSubmitAction);

//TODO:IACUC        actionBeanTaskMap.put(TaskName.SUBMIT_IACUC_PROTOCOL, submitIacucProtocolBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_WITHDRAW, getProtocolWithdrawBean());
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_SPECIAL_REVIEW, modifyIacucProtocolSpecialReviewBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_OTHERS, modifyIacucProtocolOthersBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_GENERAL_INFO, modifyIacucProtocolGeneralInfoBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_ROLES, modifyIacucProtocolRolesBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.ADD_IACUC_PROTOCOL_NOTES, addIacucProtocolNotesBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT, createIacucAmendmentBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT_UNAVAILABLE, createIacucAmendmentUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS, modifyIacucAmendmentSectionsBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS_UNAVAILABLE, modifyIacucAmendmentSectionsUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL, createIacucRenewalBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL_UNAVAILABLE, createIacucRenewalUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_BILLABLE, modifyIacucBillableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.SUBMIT_IACUC_PROTOCOL_UNAVAILABLE, submitIacucProtocolUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_FUNDING_SOURCE, modifyIacucProtocolFundingSourceBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_REFERENCES, modifyIacucProtocolReferencesBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_ORGANIZATIONS, modifyIacucProtocolOrganizationsBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_SUBJECTS, modifyIacucProtocolSubjectsBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_AREAS_OF_RESEARCH, modifyIacucProtocolAreasOfResearchBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_ATTACHMENTS, modifyIacucProtocolAttachmentsBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.MODIFY_IACUC_PROTOCOL_PERSONNEL, modifyIacucProtocolPersonnelBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_NOTIFY_COMMITTEE, iacucProtocolNotifyCommitteeBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_NOTIFY_COMMITTEE_UNAVAILABLE, iacucProtocolNotifyCommitteeUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_CLOSE, iacucProtocolRequestCloseBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_CLOSE_UNAVAILABLE, iacucProtocolRequestCloseUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION, iacucProtocolRequestSuspensionBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION_UNAVAILABLE, iacucProtocolRequestSuspensionUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_CLOSE_ENROLLMENT, iacucProtocolRequestCloseEnrollmentBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_CLOSE_ENROLLMENT_UNAVAILABLE, iacucProtocolRequestCloseEnrollmentUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_REOPEN_ENROLLMENT, iacucProtocolRequestReOpenEnrollmentBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_REOPEN_ENROLLMENT_UNAVAILABLE, iacucProtocolRequestReOpenEnrollmentUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_DATA_ANALYSIS, iacucProtocolRequestDataAnalysisBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_DATA_ANALYSIS_UNAVAILABLE, iacucProtocolRequestDataAnalysisUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_TERMINATE, iacucProtocolRequestTerminateBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_TERMINATE_UNAVAILABLE, iacucProtocolRequestTerminateUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_AMEND_RENEW_DELETE, iacucProtocolAmendRenewDeleteBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_AMEND_RENEW_DELETE_UNAVAILABLE, iacucProtocolAmendRenewDeleteUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_TO_AGENDA, iacucProtocolAssignToAgendaBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_TO_AGENDA_UNAVAILABLE, iacucProtocolAssignToAgendaUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_TO_COMMITTEE_SCHEDULE, iacucProtocolAssignToCmtSchedBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_TO_COMMITTEE_SCHEDULE_UNAVAILABLE, iacucProtocolAssignToCmtSchedUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_REVIEWERS, iacucProtocolAssignReviewersBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_REVIEWERS_UNAVAILABLE, iacucProtocolAssignReviewersUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_REVIEWERS_CMT_SEL, iacucProtocolAssignReviewersCmtSelBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_VIEW_RESTRICTED_NOTES, viewRestrictedNotesBean);
    //?    actionBeanTaskMap.put(TaskName.RESPONSE_APPROVAL, iacucProtocolResponseApprovalBean);
    //?    actionBeanTaskMap.put(TaskName.RESPONSE_APPROVAL_UNAVAILABLE, iacucProtocolResponseApprovalUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_APPROVE_PROTOCOL, iacucProtocolApproveBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_APPROVE_PROTOCOL_UNAVAILABLE, iacucProtocolApproveUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_DISAPPROVE_PROTOCOL, iacucProtocolDisapproveBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_DISAPPROVE_PROTOCOL_UNAVAILABLE, iacucProtocolDisapproveUnavailableBean);
    //?    actionBeanTaskMap.put(TaskName.RETURN_FOR_SMR, iacucProtocolReturnForSMRBean);
    //?    actionBeanTaskMap.put(TaskName.RETURN_FOR_SMR_UNAVAILABLE, iacucProtocolReturnForSMRUnavailableBean);
    //?    actionBeanTaskMap.put(TaskName.RETURN_FOR_SRR, iacucProtocolReturnForSRRBean);
    //?    actionBeanTaskMap.put(TaskName.RETURN_FOR_SRR_UNAVAILABLE, iacucProtocolReturnForSRRUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_REOPEN_PROTOCOL, iacucProtocolReopenBean);
    //?    actionBeanTaskMap.put(TaskName.CLOSE_ENROLLMENT_PROTOCOL, iacucProtocolCloseEnrollmentBean);
    //?    actionBeanTaskMap.put(TaskName.SUSPEND_PROTOCOL, iacucProtocolSuspendBean);
    //?    actionBeanTaskMap.put(TaskName.SUSPEND_PROTOCOL_BY_DSMB, iacucProtocolSuspendByDsmbBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_CLOSE_PROTOCOL, iacucProtocolCloseBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_EXPIRE_PROTOCOL, iacucProtocolExpireBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_TERMINATE_PROTOCOL, iacucProtocolTerminateBean);
    //?    actionBeanTaskMap.put(TaskName.PERMIT_DATA_ANALYSIS, iacucProtocolPermitDataAnalysisBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION, iacucProtocolAdminCorrectionBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION_UNAVAILABLE, iacucProtocolAdminCorrectionUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_UNDO_LAST_ACTION, iacucProtocolUndoLastActionBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_RECORD_COMMITTEE_DECISION, iacucProtocolRecordCommitteeDecisionBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_RECORD_COMMITTEE_DECISION_UNAVAILABLE, iacucProtocolRecordCommitteeDecisionUnavailableBean);
    //?    actionBeanTaskMap.put(TaskName.ENTER_RISK_LEVEL, iacucProtocolEnterRiskLevelBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION, modifyIacucProtocolSubmisisonBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION_UNAVAILABLE, modifyIacucProtocolSubmisisonUnavailableBean);
    actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_TABLE, iacucProtocolTableBean);
    //?    actionBeanTaskMap.put(TaskName.DEFER_PROTOCOL_UNAVAILABLE, iacucProtocolDeferUnavailableBean);
    //?    actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REVIEW_NOT_REQUIRED, iacucProtocolReviewNotRequiredBean);
    //?    actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REVIEW_NOT_REQUIRED_UNAVAILABLE, iacucProtocolReviewNotRequiredUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_MANAGE_REVIEW_COMMENTS, iacucProtocolManageReviewCommentsBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_MANAGE_REVIEW_COMMENTS_UNAVAILABLE, iacucProtocolManageReviewCommentsUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_APPROVE_OTHER, iacucProtocolApproveOtherBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_MANAGE_NOTES, iacucProtocolManageNotesBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_MANAGE_NOTES_UNAVAILABLE, iacucProtocolManageNotesUnavailableBean);
//TODO:IACUC        actionBeanTaskMap.put(TaskName.IACUC_ABANDON_PROTOCOL, iacucProtocolAbandonBean);
    }

    /**
     * Builds an approval date, defaulting to the approval date from the protocol.
     * 
     * If the approval date from the protocol is null, or if the protocol is new or a renewal, then if the committee has scheduled a meeting to approve the 
     * protocol, sets to the scheduled approval date; otherwise, sets to the current date.
     * 
     * @param protocol
     * @return a non-null approval date
     */
    private Date buildApprovalDate(Protocol protocol) {
        Date approvalDate = protocol.getApprovalDate();
        
        if (approvalDate == null || protocol.isNew() || protocol.isRenewal()) {
            CommitteeSchedule committeeSchedule = protocol.getProtocolSubmission().getCommitteeSchedule();
            if (committeeSchedule != null) {
                approvalDate = committeeSchedule.getScheduledDate();
            } else {
                approvalDate = new Date(System.currentTimeMillis());
            }
        }
        
        return approvalDate;
    }
    
    /**
     * Builds an expiration date, defaulting to the expiration date from the protocol.  
     * 
     * If the expiration date from the protocol is null, or if the protocol is new or a renewal, creates an expiration date exactly one year ahead and one day 
     * less than the approval date.
     * 
     * @param protocol
     * @param approvalDate
     * @return a non-null expiration date
     */
    private Date buildExpirationDate(Protocol protocol, Date approvalDate) {
        Date expirationDate = protocol.getExpirationDate();
        
        if (expirationDate == null || protocol.isNew() || protocol.isRenewal()) {
            java.util.Date newExpirationDate = DateUtils.addYears(approvalDate, 1);
            newExpirationDate = DateUtils.addDays(newExpirationDate, -1);
            expirationDate = DateUtils.convertToSqlDate(newExpirationDate);
        }
        
        return expirationDate;
    }

    private ProtocolAction findProtocolAction(String actionTypeCode, List<ProtocolAction> protocolActions, IacucProtocolSubmission currentSubmission) {

        for (ProtocolAction pa : protocolActions) {
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(actionTypeCode)
                    && (pa.getProtocolSubmission() == null || pa.getProtocolSubmission().equals(currentSubmission))) {
                return pa;
            }
        }
        return null;
    }

//TODO: Need to put IACUC-specific code here        
//    // always reinitialize amendment beans, otherwise a second pass thru prepareView() will show same
//    // amendment creation options as previous passes
//    public void initAmendmentBeans() throws Exception {
////        if (protocolAmendmentBean == null) {
//            protocolAmendmentBean = createAmendmentBean();
////        }
////        if (protocolRenewAmendmentBean == null) {
//            protocolRenewAmendmentBean = createAmendmentBean();
////        }
//    }
//
//    /**
//     * Create an Amendment Bean.  The modules that can be selected depends upon the
//     * current outstanding amendments.  If a module is currently being modified by a
//     * previous amendment, it cannot be modified by another amendment.  Once the 
//     * previous amendment has completed (approved, disapproved, etc), then a new
//     * amendment can modify the same module.
//     * @return
//     * @throws Exception 
//     */
//    private ProtocolAmendmentBean createAmendmentBean() throws Exception {
//        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean(this);
//        List<String> moduleTypeCodes;
//
//        if (StringUtils.isNotEmpty(getProtocol().getProtocolNumber()) && (getProtocol().isAmendment() || getProtocol().isRenewal())) {
//            moduleTypeCodes = getProtocolAmendRenewService().getAvailableModules(getProtocol().getAmendedProtocolNumber());
//            populateExistingAmendmentBean(amendmentBean, moduleTypeCodes);
//        } else {
//            moduleTypeCodes = getProtocolAmendRenewService().getAvailableModules(getProtocol().getProtocolNumber());
//        }
//        
//        for (String moduleTypeCode : moduleTypeCodes) {
//            enableModuleOption(moduleTypeCode, amendmentBean);
//        }
//        
//        return amendmentBean;
//    }
//
//    /**
//     * This method copies the settings from the ProtocolAmendRenewal bo to the amendmentBean and enables the
//     * corresponding modules. 
//     * @param amendmentBean
//     */
//    private void populateExistingAmendmentBean(ProtocolAmendmentBean amendmentBean, List<String> moduleTypeCodes) {
//        ProtocolAmendRenewal protocolAmendRenewal = getProtocol().getProtocolAmendRenewal();
//        amendmentBean.setSummary(protocolAmendRenewal.getSummary());
//        for (ProtocolAmendRenewModule module : protocolAmendRenewal.getModules()) {
//            moduleTypeCodes.add(module.getProtocolModuleTypeCode());
//            if (StringUtils.equals(ProtocolModule.GENERAL_INFO, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setGeneralInfo(true);
//            } 
//            else if (StringUtils.equals(ProtocolModule.ADD_MODIFY_ATTACHMENTS, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setAddModifyAttachments(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.AREAS_OF_RESEARCH, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setAreasOfResearch(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.FUNDING_SOURCE, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setFundingSource(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.OTHERS, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setOthers(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.PROTOCOL_ORGANIZATIONS, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setProtocolOrganizations(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERSONNEL, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setProtocolPersonnel(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.PROTOCOL_REFERENCES, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setProtocolReferencesAndOtherIdentifiers(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.SPECIAL_REVIEW, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setSpecialReview(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.SUBJECTS, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setSubjects(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERMISSIONS, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setProtocolPermissions(true);
//            }
//            else if (StringUtils.equals(ProtocolModule.QUESTIONNAIRE, module.getProtocolModuleTypeCode())) {
//                amendmentBean.setQuestionnaire(true);
//            }
//        }
//    }
//
//
//    /**
//     * Create an AdminCorrection Bean.  The modules that can be edited (or corrected) depends upon the
//     * current outstanding amendments.  If a module is currently being modified by a
//     * an amendment, it cannot be corrected through Administrative Correction.  
//     * @return
//     * @throws Exception 
//     */
//    private AdminCorrectionBean createAdminCorrectionBean() throws Exception {
//        AdminCorrectionBean adminCorrectionBean = new AdminCorrectionBean(this);
//        List<String> moduleTypeCodes = getProtocolAmendRenewService().getAvailableModules(getProtocol().getProtocolNumber());
//        
//        for (String moduleTypeCode : moduleTypeCodes) {
//            enableModuleOption(moduleTypeCode, adminCorrectionBean);
//        }
//        
//        return adminCorrectionBean;
//    }
//    
//    private UndoLastActionBean createUndoLastActionBean(Protocol protocol) throws Exception {
//        undoLastActionBean = new UndoLastActionBean(this);
//        undoLastActionBean.setProtocol(protocol);
//        Collections.sort(protocol.getProtocolActions(), new Comparator<ProtocolAction>() {
//            public int compare(ProtocolAction action1, ProtocolAction action2) {
//                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
//            }
//        });
//        undoLastActionBean.setActionsPerformed(protocol.getProtocolActions());
//        return undoLastActionBean;
//    }
//    
//    /**
//     * Enable a module for selection by a user by setting its corresponding enabled
//     * flag to true in the amendment bean.
//     * @param moduleTypeCode
//     * @param amendmentBean
//     */
//    private void enableModuleOption(String moduleTypeCode, ProtocolEditableBean amendmentBean) {
//        if (StringUtils.equals(ProtocolModule.GENERAL_INFO, moduleTypeCode)) {
//            amendmentBean.setGeneralInfoEnabled(true);
//        } 
//        else if (StringUtils.equals(ProtocolModule.ADD_MODIFY_ATTACHMENTS, moduleTypeCode)) {
//            amendmentBean.setAddModifyAttachmentsEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.AREAS_OF_RESEARCH, moduleTypeCode)) {
//            amendmentBean.setAreasOfResearchEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.FUNDING_SOURCE, moduleTypeCode)) {
//            amendmentBean.setFundingSourceEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.OTHERS, moduleTypeCode)) {
//            amendmentBean.setOthersEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.PROTOCOL_ORGANIZATIONS, moduleTypeCode)) {
//            amendmentBean.setProtocolOrganizationsEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERSONNEL, moduleTypeCode)) {
//            amendmentBean.setProtocolPersonnelEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.PROTOCOL_REFERENCES, moduleTypeCode)) {
//            amendmentBean.setProtocolReferencesEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.SPECIAL_REVIEW, moduleTypeCode)) {
//            amendmentBean.setSpecialReviewEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.SUBJECTS,moduleTypeCode)) {
//            amendmentBean.setSubjectsEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERMISSIONS,moduleTypeCode)) {
//            amendmentBean.setProtocolPermissionsEnabled(true);
//        }
//        else if (StringUtils.equals(ProtocolModule.QUESTIONNAIRE,moduleTypeCode)) {
//            amendmentBean.setQuestionnaireEnabled(true);
//        }
//    }
//
//    private ProtocolAmendRenewService getProtocolAmendRenewService() {
//        if (this.protocolAmendRenewService == null) {
//            this.protocolAmendRenewService = KraServiceLocator.getService(ProtocolAmendRenewService.class);        
//        }
//        return this.protocolAmendRenewService;
//    }
//
    public void prepareView() throws Exception {
        protocolSubmitAction.prepareView();
        super.prepareView();
//TODO: Need to put IACUC-specific code here        
//        assignToAgendaBean.prepareView();
//        assignCmtSchedBean.prepareView();
//        protocolAssignReviewersBean.prepareView();
        submissionConstraint = getParameterValue(Constants.PARAMETER_IACUC_COMM_SELECTION_DURING_SUBMISSION);

        canDeleteIacucProtocol = hasPermission(TaskName.DELETE_IACUC_PROTOCOL);
        canDeleteIacucProtocolUnavailable = hasPermission(TaskName.DELETE_IACUC_PROTOCOL_UNAVAILABLE);
        canAdministrativelyApprove = hasPermission(TaskName.ADMIN_APPROVE_IACUC_PROTOCOL);
        canAdministrativelyApproveUnavailable = hasPermission(TaskName.ADMIN_APPROVE_IACUC_PROTOCOL_UNAVAILABLE);
        canAdministrativelyWithdraw = hasPermission(TaskName.ADMIN_WITHDRAW_IACUC_PROTOCOL);
        canAdministrativelyWithdrawUnavailable = hasPermission(TaskName.ADMIN_WITHDRAW_IACUC_PROTOCOL_UNAVAILABLE);
        canReturnToPI = hasPermission(TaskName.RETURN_TO_PI_IACUC_PROTOCOL);
        canReturnToPIUnavailable = hasPermission(TaskName.RETURN_TO_PI_IACUC_PROTOCOL_UNAVAILABLE);
        canNotifyIacuc = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE);
        canNotifyIacucUnavailable = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE_UNAVAILABLE);
        canHold = hasPermission(TaskName.IACUC_PROTOCOL_HOLD);
        canHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_HOLD_UNAVAILABLE);
        canLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD);
        canLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD_UNAVAILABLE);
        canRequestToLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD);
        canRequestToLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD_UNAVAILABLE);
        canTable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE);
        canTableUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE_UNAVAILABLE);
        canIacucAcknowledge = hasPermission(TaskName.IACUC_ACKNOWLEDGEMENT);
        canIacucAcknowledgeUnavailable = hasPermission(TaskName.IACUC_ACKNOWLEDGEMENT_UNAVAILABLE);
        canIacucRequestDeactivate = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE);
        canIacucRequestDeactivateUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE_UNAVAILABLE);
        canSubmitProtocol = hasPermission(TaskName.SUBMIT_IACUC_PROTOCOL);
//TODO:IACUC        canSubmitProtocolUnavailable = hasPermission(TaskName.SUBMIT_IACUC_PROTOCOL_UNAVAILABLE);
canSubmitProtocolUnavailable = !canSubmitProtocol;        
        canCreateAmendment = hasPermission(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT);
        canCreateAmendmentUnavailable = hasPermission(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT_UNAVAILABLE);
        canModifyAmendmentSections = hasPermission(TaskName.MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS);
        canModifyAmendmentSectionsUnavailable = hasPermission(TaskName.MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS_UNAVAILABLE);
        canCreateRenewal = hasPermission(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL);
        canCreateRenewalUnavailable = hasPermission(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL_UNAVAILABLE);
        canNotifyCommittee = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE);
        canNotifyCommitteeUnavailable = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE_UNAVAILABLE);
        canWithdraw = hasPermission(TaskName.IACUC_PROTOCOL_WITHDRAW);
        canWithdrawUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_WITHDRAW_UNAVAILABLE);
        canRequestClose = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_CLOSE);
        canRequestCloseUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_CLOSE_UNAVAILABLE);
        canRequestSuspension = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION);
        canRequestSuspensionUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION_UNAVAILABLE);
        canRequestTerminate = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_TERMINATE);
        canRequestTerminateUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_TERMINATE_UNAVAILABLE);
        canDeleteProtocolAmendRenew = hasPermission(TaskName.IACUC_PROTOCOL_AMEND_RENEW_DELETE);
        canDeleteProtocolAmendRenewUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_AMEND_RENEW_DELETE_UNAVAILABLE);
        canAssignToAgenda = hasPermission(TaskName.IACUC_ASSIGN_TO_AGENDA);
        canAssignToAgendaUnavailable = hasPermission(TaskName.IACUC_ASSIGN_TO_AGENDA_UNAVAILABLE);
        canAssignCmtSched = hasPermission(TaskName.IACUC_ASSIGN_TO_COMMITTEE_SCHEDULE);
        canAssignCmtSchedUnavailable = hasPermission(TaskName.IACUC_ASSIGN_TO_COMMITTEE_SCHEDULE_UNAVAILABLE);
        canAssignReviewers = hasPermission(TaskName.IACUC_ASSIGN_REVIEWERS);
        canAssignReviewersUnavailable = hasPermission(TaskName.IACUC_ASSIGN_REVIEWERS_UNAVAILABLE);
        canAssignReviewersCmtSel = hasPermission(TaskName.IACUC_ASSIGN_REVIEWERS_CMT_SEL);
        canReturnForSMR = hasPermission(TaskName.IACUC_RETURN_FOR_SMR);
        canReturnForSMRUnavailable = hasPermission(TaskName.IACUC_RETURN_FOR_SMR_UNAVAILABLE); 
        canReturnForSRR = hasPermission(TaskName.IACUC_RETURN_FOR_SRR);
        canReturnForSRRUnavailable = hasPermission(TaskName.IACUC_RETURN_FOR_SRR_UNAVAILABLE);
        canApproveFull = hasPermission(TaskName.IACUC_APPROVE_PROTOCOL);
        canApproveFullUnavailable = hasPermission(TaskName.IACUC_APPROVE_PROTOCOL_UNAVAILABLE);
        canDisapprove = hasPermission(TaskName.IACUC_DISAPPROVE_PROTOCOL);
        canDisapproveUnavailable = hasPermission(TaskName.IACUC_DISAPPROVE_PROTOCOL_UNAVAILABLE);
//        canClose = hasGenericPermission(GenericProtocolAuthorizer.IACUC_CLOSE_PROTOCOL);
//        canCloseUnavailable = hasGenericUnavailablePermission(GenericProtocolAuthorizer.IACUC_CLOSE_PROTOCOL);
//        canExpire = hasGenericPermission(GenericProtocolAuthorizer.IACUC_EXPIRE_PROTOCOL);
//        canExpireUnavailable = hasGenericUnavailablePermission(GenericProtocolAuthorizer.IACUC_EXPIRE_PROTOCOL);
//        canTerminate = hasGenericPermission(GenericProtocolAuthorizer.IACUC_TERMINATE_PROTOCOL);
//        canTerminateUnavailable = hasGenericUnavailablePermission(GenericProtocolAuthorizer.IACUC_TERMINATE_PROTOCOL);
        canMakeAdminCorrection = hasPermission(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION);
        canMakeAdminCorrectionUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION_UNAVAILABLE);
//        canUndoLastAction = hasPermission(TaskName.IACUC_PROTOCOL_UNDO_LAST_ACTION) && undoLastActionBean.canUndoLastAction();
//        canUndoLastActionUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_UNDO_LAST_ACTION) && !undoLastActionBean.canUndoLastAction();
        canRecordCommitteeDecision = hasPermission(TaskName.IACUC_RECORD_COMMITTEE_DECISION);
        canRecordCommitteeDecisionUnavailable = hasPermission(TaskName.IACUC_RECORD_COMMITTEE_DECISION_UNAVAILABLE);
//        canEnterRiskLevel = hasPermission(TaskName.ENTER_RISK_LEVEL);
        canManageReviewComments = hasPermission(TaskName.IACUC_PROTOCOL_MANAGE_REVIEW_COMMENTS); 
        canManageReviewCommentsUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_MANAGE_REVIEW_COMMENTS_UNAVAILABLE); 
//??        canApproveOther = hasPermission(TaskName.IACUC_PROTOCOL_APPROVE_OTHER);
        canManageNotes = hasPermission(TaskName.IACUC_PROTOCOL_MANAGE_NOTES);
        canManageNotesUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_MANAGE_NOTES_UNAVAILABLE);
        canAbandon = hasPermission(TaskName.IACUC_ABANDON_PROTOCOL);
        canModifyProtocolSubmission = hasPermission(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION);
        canModifyProtocolSubmissionUnavailable = hasPermission(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION_UNAVAILABLE);
        
//        followupActionActions = getFollowupActionService().getFollowupsForProtocol(form.getProtocolDocument().getProtocol());
//        canViewOnlineReviewers = hasCanViewOnlineReviewersPermission();
//        canViewOnlineReviewerComments = hasCanViewOnlineReviewerCommentsPermission();
//        canAddCloseReviewerComments = hasCloseRequestLastAction();
//        canAddSuspendReviewerComments = hasSuspendRequestLastAction();
//        canAddTerminateReviewerComments = hasTerminateRequestLastAction();
        canApproveResponse = hasPermission(TaskName.IACUC_RESPONSE_APPROVAL);
        canApproveResponseUnavailable = hasPermission(TaskName.IACUC_RESPONSE_APPROVAL_UNAVAILABLE);

        // IACUC-specific actions
        canAdministrativelyMarkIncomplete = hasPermission(TaskName.ADMIN_INCOMPLETE_IACUC_PROTOCOL);
        canAdministrativelyMarkIncompleteUnavailable = hasPermission(TaskName.ADMIN_INCOMPLETE_IACUC_PROTOCOL_UNAVAILABLE);
        canDesignatedMemberApproval = hasPermission(TaskName.IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL);
        canDesignatedMemberApprovalUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL_UNAVAILABLE);
        canHold = hasPermission(TaskName.IACUC_PROTOCOL_HOLD);
        canHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_HOLD_UNAVAILABLE);
        canLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD);
        canLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD_UNAVAILABLE);
        canNotifyIacuc = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE);
        canNotifyIacucUnavailable = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE_UNAVAILABLE);
        canRequestToLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD);
        canRequestToLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD_UNAVAILABLE);
        canReturnToPI = hasPermission(TaskName.RETURN_TO_PI_IACUC_PROTOCOL);
        canReturnToPIUnavailable = hasPermission(TaskName.RETURN_TO_PI_IACUC_PROTOCOL_UNAVAILABLE);
        canReviewNotRequired = hasPermission(TaskName.REVIEW_NOT_REQUIRED_IACUC_PROTOCOL);
        canReviewNotRequiredUnavailable = hasPermission(TaskName.REVIEW_NOT_REQUIRED_IACUC_PROTOCOL_UNAVAILABLE);
        canTable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE);
        canTableUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE_UNAVAILABLE);

//TODO: Need to put IACUC-specific code here        
//        hideReviewerName = checkToHideReviewName();
////        undoLastActionBean = createUndoLastActionBean(getProtocol());
//       
//        initSummaryDetails();
//        initSubmissionDetails();
//        setAmendmentDetails();
//        initFilterDatesView();
//        initAmendmentBeans();
//        initPrintQuestionnaire();
    }
    
    /**
     * Refreshes the comments for all the beans from the database.  Use sparingly since this will erase non-persisted comments.
     */
    public void prepareCommentsView() {
//TODO: Need to put IACUC-specific code here        
//        assignToAgendaBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolGrantExemptionBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolIrbAcknowledgementBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolFullApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolExpeditedApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolResponseApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolDisapproveBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolSMRBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolSRRBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolReopenEnrollmentBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolCloseEnrollmentBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolSuspendBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolSuspendByDsmbBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolCloseBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolExpireBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolTerminateBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolPermitDataAnalysisBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        committeeDecision.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
////        committeeDecision.getReviewCommentsBean().setHideReviewerName(getReviewCommentsService().setHideReviewerName(committeeDecision.getReviewCommentsBean().getReviewComments()));            
//        protocolDeferBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolManageReviewCommentsBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
//        protocolManageReviewCommentsBean.getReviewAttachmentsBean().setReviewAttachments(getProtocol().getProtocolSubmission().getReviewAttachments());
//        if (CollectionUtils.isNotEmpty(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments())) {
//            protocolManageReviewCommentsBean.getReviewAttachmentsBean().setHideReviewerName(getReviewerCommentsService().setHideReviewerName(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments()));
//        }
    }
    
//TODO: Need to put IACUC-specific code here        
//    private List<CommitteeScheduleMinute> getCopiedReviewComments() {
//        List<CommitteeScheduleMinute> clonedMinutes = new ArrayList<CommitteeScheduleMinute>();
//        Long scheduleIdFk = getProtocol().getProtocolSubmission().getScheduleIdFk();
//        List<CommitteeScheduleMinute> minutes = getCommitteeScheduleService().getMinutesBySchedule(scheduleIdFk);
//        if (CollectionUtils.isNotEmpty(minutes)) {
//            for (CommitteeScheduleMinute minute : minutes) {
//                clonedMinutes.add(minute.getCopy());
//            }
//        }
//        
//        return clonedMinutes;
//    }
//        
//    private CommitteeScheduleService getCommitteeScheduleService() {
//        if (committeeScheduleService == null) {
//            committeeScheduleService = KraServiceLocator.getService(CommitteeScheduleService.class);        
//        }
//        return committeeScheduleService;
//    }
//    
//    private ProtocolVersionService getProtocolVersionService() {
//        if (this.protocolVersionService == null) {
//            this.protocolVersionService = KraServiceLocator.getService(ProtocolVersionService.class);        
//        }
//        return this.protocolVersionService;
//    }
//    
//    private ProtocolSubmitActionService getProtocolSubmitActionService() {
//        if (protocolSubmitActionService == null) {
//            protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
//        }
//        return protocolSubmitActionService;
//    }
//    
//    private ProtocolActionService getProtocolActionService() {
//        if (protocolActionService == null) {
//            protocolActionService = KraServiceLocator.getService(ProtocolActionService.class);
//        }
//        return protocolActionService;
//    }

    public static boolean hasAssignCmtSchedPermission(String userId, String protocolNumber) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolNumber", protocolNumber);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        IacucProtocol protocol = ((List<IacucProtocol>) bos.findMatching(IacucProtocol.class, fieldValues)).get(0);
        IacucProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL, protocol);
        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);        
        return tas.isAuthorized(userId, task);
    }
    
    protected boolean hasPermission(String taskName) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, getIacucProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasGenericPermission(String genericActionName) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.GENERIC_IACUC_PROTOCOL_ACTION, getIacucProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasGenericUnavailablePermission(String genericActionName) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.GENERIC_IACUC_PROTOCOL_ACTION_UNAVAILABLE, getIacucProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected boolean hasFollowupAction(String actionCode) {
//        for (ValidProtocolActionAction action : followupActionActions) {
//            if (StringUtils.equals(action.getFollowupActionCode(),actionCode)) {
//                return true;
//            }
//        }
        return false;
    }
    
    
//TODO: Need to put IACUC-specific code here        
//  private boolean hasCanViewOnlineReviewersPermission() {
//        return getReviewerCommentsService().canViewOnlineReviewers(getUserIdentifier(), getSelectedSubmission());
//    }
//    
//    private boolean hasCanViewOnlineReviewerCommentsPermission() {
//        return getReviewerCommentsService().canViewOnlineReviewerComments(getUserIdentifier(), getSelectedSubmission());
//    }
//    
//    private boolean hasCloseRequestLastAction() {
//        return ProtocolActionType.REQUEST_TO_CLOSE.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    private boolean hasCloseEnrollmentRequestLastAction() {
//        return ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    private boolean hasDataAnalysisRequestLastAction() {
//        return ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    private boolean hasReopenEnrollmentRequestLastAction() {
//        return ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    private boolean hasSuspendRequestLastAction() {
//        return ProtocolActionType.REQUEST_FOR_SUSPENSION.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    private boolean hasTerminateRequestLastAction() {
//        return ProtocolActionType.REQUEST_FOR_TERMINATION.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//
//    private TaskAuthorizationService getTaskAuthorizationService() {
//        if (this.taskAuthorizationService == null) {
//            this.taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);        
//        }
//        return this.taskAuthorizationService;
//    }
    
    public IacucProtocolSubmitAction getIacucProtocolSubmitAction() {
        return (IacucProtocolSubmitAction)protocolSubmitAction;
    }

    public ProtocolNotifyIacucBean getProtocolNotifyIacucBean() {
        return protocolNotifyIacucBean;
    }

    public ProtocolForm getProtocolForm() {
        return form;
    }
    
    public Protocol getProtocol() {
        return form.getProtocolDocument().getProtocol();
    }

    
//TODO: Need to put IACUC-specific code here        
//    public ProtocolAction getLastPerformedAction() {
//        List<ProtocolAction> protocolActions = form.getProtocolDocument().getProtocol().getProtocolActions();
//        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
//            public int compare(ProtocolAction action1, ProtocolAction action2) {
//                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
//            }
//        });
//     
//        return protocolActions.size() > 0 ? protocolActions.get(0) : null;
//    }
//    
//    /**
//     * Prepares all protocol actions for being filtered by setting their isInFilterView attribute.
//     */
//    public void initFilterDatesView() {
//        java.util.Date dayBeforeStartDate = null;
//        java.util.Date dayAfterEndDate = null;
//
//        if (filteredHistoryStartDate != null && filteredHistoryEndDate != null) {
//            dayBeforeStartDate = DateUtils.addDays(filteredHistoryStartDate, -1);
//            dayAfterEndDate = DateUtils.addDays(filteredHistoryEndDate, 1);
//        }
//
//        for (ProtocolAction protocolAction : getSortedProtocolActions()) {
//            Timestamp actionDate = protocolAction.getActionDate();
//            if (dayBeforeStartDate != null && dayAfterEndDate != null) {
//                protocolAction.setIsInFilterView(actionDate.after(dayBeforeStartDate) && actionDate.before(dayAfterEndDate));
//            }
//            else {
//                protocolAction.setIsInFilterView(true);
//            }
//            if (protocolAction.getIsInFilterView()) {
//                protocolAction.setQuestionnairePrintOptionFromHelper(this);
//            }
//        }
//    }
//    
//    // All the following methods were refactored into the ProtocolAction, where they belong
//    
//    /*
//     * check if this particular ProtocolAction has any questionnaire associated with it.
//     */
///*    private void checkQuestionnaire(ProtocolAction protocolAction) {
//        if (protocolAction.getSubmissionNumber() != null
//                && !ProtocolActionType.SUBMIT_TO_IRB.equals(protocolAction.getProtocolActionTypeCode())) {
//            protocolAction.setAnswerHeadersCount(getAnswerHeaderCount(CoeusSubModule.PROTOCOL_SUBMISSION, Integer
//                    .toString(protocolAction.getSubmissionNumber())));
//             if (protocolAction.getAnswerHeadersCount() > 0) {
//                protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber(),
//                        protocolAction.getSubmissionNumber().toString(), CoeusSubModule.PROTOCOL_SUBMISSION, protocolAction));
//            }
//        } else if (ProtocolActionType.SUBMIT_TO_IRB.equals(protocolAction.getProtocolActionTypeCode())
//                && ("Submitted to IRB").equals(protocolAction.getComments())) {
//            if (protocolAction.getProtocol().isAmendment() || protocolAction.getProtocol().isRenewal()) {
//                protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber(),
//                        protocolAction.getSequenceNumber().toString(), CoeusSubModule.AMENDMENT_RENEWAL, protocolAction));
//
//            } else {
//                protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber(),
//                        getInitialSequence(protocolAction, ""), CoeusSubModule.ZERO_SUBMODULE, protocolAction));
//            }
//        } else if (ProtocolActionType.SUBMIT_TO_IRB.equals(protocolAction.getProtocolActionTypeCode())
//                && StringUtils.isNotBlank(protocolAction.getComments())
//                && (protocolAction.getComments().startsWith("Amendment-") || protocolAction.getComments().startsWith("Renewal-"))) {
//            String amendmentRenewalNumber = getAmendmentRenewalNumber(protocolAction.getComments());
//            protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber()
//                    + amendmentRenewalNumber, getInitialSequence(protocolAction, amendmentRenewalNumber),
//                    CoeusSubModule.AMENDMENT_RENEWAL, protocolAction));
//         }
//    }
//*/
//
//    /*
//     * set up questionnaire option for UI view button
//     */
///*    QuestionnairePrintOption getQnPrintOptionForAction(String itemKey, String subItemKey, String subItemCode, ProtocolAction protocolAction) {
//
//        protocolAction.setAnswerHeadersCount(getAnswerHeaderCount(subItemCode, itemKey, subItemKey));
//        if (protocolAction.getAnswerHeadersCount() > 0) {
//            QuestionnairePrintOption qnPrintOption = new QuestionnairePrintOption();
//            qnPrintOption.setItemKey(itemKey);
//            qnPrintOption.setSubItemCode(subItemCode);
//            qnPrintOption.setSubItemKey(subItemKey);
//            return qnPrintOption;
//        } else {
//            return null;
//        }
//    }
//    
//    public int getAnswerHeaderCount(String moduleSubItemCode, String moduleItemKey, String moduleSubItemKey) {
//        Map<String, String> fieldValues = new HashMap<String, String>();
//        fieldValues.put("moduleItemCode", getCoeusModule());
//        fieldValues.put("moduleItemKey", moduleItemKey);
//        if (!moduleItemKey.contains("A") && !moduleItemKey.contains("R") && !getProtocol().isAmendment() && !getProtocol().isRenewal()) {
//            fieldValues.put("moduleSubItemCode", moduleSubItemCode);
//        }
//        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
//        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
//        
//    }
//*/
//    
//    /*
//     * utility method to get amend or renewal number
//     */
///*  private String getAmendmentRenewalNumber(String comment) {
//        String retVal="";
//        if (comment.startsWith("Amendment-")) {
//            retVal = "A" + comment.substring(10, 13);
//        } else {
//            retVal = "R" + comment.substring(8, 11);
//                     
//        }
//        return retVal;
//    }
//*/    
//
//    /*
//     * get the sequence number of the protocol that the action initially created
//     */
///*  private String getInitialSequence(ProtocolAction protocolAction, String amendmentRenewalNumber) {
//        Map<String, String> fieldValues = new HashMap<String, String>();
//        fieldValues.put("protocolNumber", protocolAction.getProtocolNumber() + amendmentRenewalNumber);
//        if (StringUtils.isBlank(amendmentRenewalNumber)) {
//            fieldValues.put("actionId", protocolAction.getActionId().toString());
//        }
//        else {
//            fieldValues.put("submissionNumber", protocolAction.getSubmissionNumber().toString());
//        }
//        fieldValues.put("protocolActionTypeCode", ProtocolActionType.SUBMIT_TO_IRB);
//        return ((List<ProtocolAction>) getBusinessObjectService().findMatchingOrderBy(ProtocolAction.class, fieldValues,
//                "protocolActionId", true)).get(0).getProtocol().getSequenceNumber().toString();
//    }
//*/
//
//    /**
//     * Prepares, sorts, and returns a list of protocol actions.
//     * @return
//     */
//    public List<ProtocolAction> getSortedProtocolActions() {
//        List<ProtocolAction> protocolActions = new ArrayList<ProtocolAction>();
//        for (ProtocolAction protocolAction : form.getProtocolDocument().getProtocol().getProtocolActions()) {
//            if (protocolAction.getSubmissionNumber() != null && ACTION_TYPE_SUBMISSION_DOC.contains(protocolAction.getProtocolActionTypeCode())) {
//                protocolAction.setProtocolSubmissionDocs(new ArrayList<ProtocolSubmissionDoc>(getSubmissionDocs(protocolAction)));
//            }
//            protocolActions.add(protocolAction);
//        }
//        
//        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
//            public int compare(ProtocolAction action1, ProtocolAction action2) {
//                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
//            }
//        });
//     
//        return protocolActions;
//    }
//    
//    private Collection<ProtocolSubmissionDoc> getSubmissionDocs(ProtocolAction protocolAction) {
//        Map<String, Object> fieldValues = new HashMap<String, Object>();
//        fieldValues.put("protocolNumber", protocolAction.getProtocolNumber());
//        fieldValues.put("submissionNumber", protocolAction.getSubmissionNumber());
//        return getBusinessObjectService().findMatchingOrderBy(ProtocolSubmissionDoc.class, fieldValues, "documentId", true);
//    }
//    
//    public ProtocolAction getSelectedProtocolAction() {
//        for (ProtocolAction action : getProtocol().getProtocolActions()) {
//            if (StringUtils.equals(action.getProtocolActionId().toString(), selectedHistoryItem)) {
//                return action;
//            }
//        }
//        return null;
//    }
    
//    /**
//     * Finds and returns the current selection based on the currentSubmissionNumber.
//     * 
//     * If the currentSubmissionNumber is invalid, it will return the current protocol's latest submission (which is always non-null); otherwise, it will get
//     * the submission from the protocol based on the currentSubmissionNumber.
//     * @return the currently selected submission
//     */
//    public ProtocolSubmission getSelectedSubmission() {
//        ProtocolSubmission protocolSubmission = null;
//        
//        if (currentSubmissionNumber <= 0) {
//            protocolSubmission = getProtocol().getProtocolSubmission();
//        } else if (currentSubmissionNumber > 0) {
//            // For amendment/renewal, the submission number are not starting at 1
//            //protocolSubmission = getProtocol().getProtocolSubmissions().get(currentSubmissionNumber - 1);
//            for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
//                if (submission.getSubmissionNumber().intValue() == currentSubmissionNumber) {
//                    protocolSubmission = submission;
//                    break;
//                }
//            }
//            if (protocolSubmission == null) {
//                // undo last action may remove the last submission; so it can't be found
//                protocolSubmission = getProtocol().getProtocolSubmission();
//                currentSubmissionNumber = protocolSubmission.getSubmissionNumber();
//            }
//        }
//        
//        return protocolSubmission;
//    }
//  
//    public FollowupActionService getFollowupActionService() {
//        if (followupActionService == null) {
//            followupActionService = KraServiceLocator.getService(FollowupActionService.class);
//        }
//        return followupActionService;
//    }
//    
//    private ReviewCommentsService getReviewerCommentsService() {
//        return KraServiceLocator.getService(ReviewCommentsService.class);
//    }
//    
//    private CommitteeDecisionService getCommitteeDecisionService() {
//        return KraServiceLocator.getService("protocolCommitteeDecisionService");
//    }
//    
//    protected KcPersonService getKcPersonService() {
//        if (this.kcPersonService == null) {
//            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
//        }
//        
//        return this.kcPersonService;
//    }
    
//    /**
//     * Sets up the summary details subpanel.
//     * @throws Exception 
//     */
//    public void initSummaryDetails() throws Exception {
//        if (currentSequenceNumber == -1) {
//            currentSequenceNumber = getProtocol().getSequenceNumber();
//        } else if (currentSequenceNumber > getProtocol().getSequenceNumber()) {
//            currentSequenceNumber = getProtocol().getSequenceNumber();
//        }
//        
//        protocolSummary =  null;
//        String protocolNumber = getProtocol().getProtocolNumber();
//        Protocol protocol = getProtocolVersionService().getProtocolVersion(protocolNumber, currentSequenceNumber);
//        if (protocol != null) {
//            protocolSummary = protocol.getProtocolSummary();
//        }
//        
//        prevProtocolSummary = null;
//        if (currentSequenceNumber > 0) {
//            protocol = getProtocolVersionService().getProtocolVersion(protocolNumber, currentSequenceNumber - 1);
//            if (protocol != null) {
//                prevProtocolSummary = protocol.getProtocolSummary();
//            }
//        }
//        
//        if (protocolSummary != null && prevProtocolSummary != null) {
//            protocolSummary.compare(prevProtocolSummary);
//            prevProtocolSummary.compare(protocolSummary);
//        }
//
//        setSummaryQuestionnaireExist(hasAnsweredQuestionnaire((protocol.isAmendment() || protocol.isRenewal()) ? CoeusSubModule.AMENDMENT_RENEWAL : CoeusSubModule.ZERO_SUBMODULE, protocol.getSequenceNumber().toString()));
//    }
//
//    /**
//     * Sets up dates for the submission details subpanel.
//     */
//    public void initSubmissionDetails() {
//        if (currentSubmissionNumber <= 0) {
//            currentSubmissionNumber = getTotalSubmissions();
//        }
//
//        if (CollectionUtils.isNotEmpty(getProtocol().getProtocolSubmissions()) && getProtocol().getProtocolSubmissions().size() > 1) {
//            setPrevNextFlag();
//        } else {
//            setPrevDisabled(true);
//            setNextDisabled(true);
//        }
//
//        setReviewComments(getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), currentSubmissionNumber));
//        if (CollectionUtils.isNotEmpty(getReviewComments())) {
//            // check if our comments bean has empty list of review comments, this can happen if the submission has no schedule assigned
//            if(protocolManageReviewCommentsBean.getReviewCommentsBean().getReviewComments().size() == 0) {
//                List<CommitteeScheduleMinute> reviewComments = getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), 
//                        currentSubmissionNumber);
//                Collections.sort(reviewComments, new Comparator<CommitteeScheduleMinute>() {
//
//                    @Override
//                    public int compare(CommitteeScheduleMinute csm1, CommitteeScheduleMinute csm2) {
//                        int retVal = 0;
//                        if( (csm1 != null) && (csm2 != null) && (csm1.getEntryNumber() != null) && (csm2.getEntryNumber() != null) ) {
//                            retVal = csm1.getEntryNumber().compareTo(csm2.getEntryNumber());
//                        }
//                        return retVal;
//                    }
//                    
//                });
//                protocolManageReviewCommentsBean.getReviewCommentsBean().setReviewComments(reviewComments);
//                getReviewerCommentsService().setHideReviewerName(reviewComments);
//            }
//            getReviewerCommentsService().setHideReviewerName(getReviewComments());
//        }
//        setReviewAttachments(getReviewerCommentsService().getReviewerAttachments(getProtocol().getProtocolNumber(),
//                currentSubmissionNumber));
//        if (CollectionUtils.isNotEmpty(getReviewAttachments())) {
//            hideReviewerNameForAttachment = getReviewerCommentsService().setHideReviewerName(getReviewAttachments());
//            getReviewerCommentsService().setHideViewButton(getReviewAttachments());
//        }
//        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
////        setReviewAttachments(getProtocol().getProtocolSubmission().getReviewAttachments());
//        hideSubmissionReviewerName = checkToHideSubmissionReviewerName();
//
//        setProtocolReviewers(getReviewerCommentsService().getProtocolReviewers(getProtocol().getProtocolNumber(),
//                currentSubmissionNumber));
//        setAbstainees(getCommitteeDecisionService().getAbstainers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
//        setRecusers(getCommitteeDecisionService().getRecusers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
//        setSubmissionQuestionnaireExist(hasAnsweredQuestionnaire(CoeusSubModule.PROTOCOL_SUBMISSION, Integer.toString(currentSubmissionNumber)));
//    }
//    
//    public void setCurrentTask(String currentTaskName) {
//        this.currentTaskName = currentTaskName;
//    }
//    
//    public String getCurrentTask() {
//        return currentTaskName;
//    }
//    /**
//     * This method populates the protocolAmendmentBean with the amendment details from the 
//     * current submission.
//     * @throws Exception
//     */
//    protected void setAmendmentDetails() throws Exception {
//        /*
//         * Check if the user is trying to modify amendment sections, if so, do not setAmendmentDetials.
//         * If you set it, the user's data gets refreshed and the amendment details from the currentSubmission
//         * will be populated in the protocolAmendmentBean.
//         */
//        if (!currentTaskName.equalsIgnoreCase(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS)) {
//            ProtocolAmendmentBean amendmentBean = getProtocolAmendmentBean();
//            String originalProtocolNumber;
//            // Use the submission number to get the correct amendment details
//            if (getProtocol().isAmendment()) {
//                originalProtocolNumber = getProtocol().getProtocolAmendRenewal().getProtocolNumber();           
//            } else {
//                // We want to display amendment details even if the document is not an amendment.
//                // Amendment details needs to be displayed even after the amendment has been merged with the protocol.
//                originalProtocolNumber = getProtocol().getProtocolNumber();
//            }
//            List<Protocol> protocols = getProtocolAmendRenewService().getAmendmentAndRenewals(originalProtocolNumber);
//
//            ProtocolAmendRenewal correctAmendment = getCorrectAmendment(protocols);
//            if (ObjectUtils.isNotNull(correctAmendment)) {
//                setSubmissionHasNoAmendmentDetails(false);
//                amendmentBean.setSummary(correctAmendment.getSummary());
//                amendmentBean.setGeneralInfo((correctAmendment.hasModule(ProtocolModule.GENERAL_INFO)) ? true : false);
//                amendmentBean.setProtocolPersonnel((correctAmendment.hasModule(ProtocolModule.PROTOCOL_PERSONNEL)) ? true : false);
//                amendmentBean.setAreasOfResearch((correctAmendment.hasModule(ProtocolModule.AREAS_OF_RESEARCH)) ? true : false);
//                amendmentBean.setAddModifyAttachments((correctAmendment.hasModule(ProtocolModule.ADD_MODIFY_ATTACHMENTS)) ? true : false);
//                amendmentBean.setFundingSource((correctAmendment.hasModule(ProtocolModule.FUNDING_SOURCE)) ? true : false);
//                amendmentBean.setOthers((correctAmendment.hasModule(ProtocolModule.OTHERS)) ? true : false);
//                amendmentBean.setProtocolOrganizations((correctAmendment.hasModule(ProtocolModule.PROTOCOL_ORGANIZATIONS)) ? true : false);
//                amendmentBean.setProtocolPermissions((correctAmendment.hasModule(ProtocolModule.PROTOCOL_PERMISSIONS)) ? true : false);
//                amendmentBean.setProtocolReferencesAndOtherIdentifiers((correctAmendment.hasModule(ProtocolModule.PROTOCOL_REFERENCES)) ? true : false);
//                amendmentBean.setQuestionnaire((correctAmendment.hasModule(ProtocolModule.QUESTIONNAIRE)) ? true : false);
//                amendmentBean.setSpecialReview((correctAmendment.hasModule(ProtocolModule.SPECIAL_REVIEW)) ? true : false);
//                amendmentBean.setSubjects((correctAmendment.hasModule(ProtocolModule.SUBJECTS)) ? true : false);
//            } else {
//                setSubmissionHasNoAmendmentDetails(true);
//            }
//        }
//    }
//    
//    public void setSubmissionHasNoAmendmentDetails(boolean submissionHasNoAmendmentDetails) {
//        this.submissionHasNoAmendmentDetails = submissionHasNoAmendmentDetails;
//    }
//    
//    public boolean getSubmissionHasNoAmendmentDetails() {
//        return submissionHasNoAmendmentDetails;
//    }
//    
//    /**
//     * This method returns the amendRenewal bean with the current submission number. 
//     * @param protocols
//     * @return
//     */
//    protected ProtocolAmendRenewal getCorrectAmendment(List<Protocol> protocols) {
//        for (Protocol protocol : protocols) {
//            // There should always be an amendment with the current submission number.
//            if (protocol.isAmendment() && ObjectUtils.isNotNull(protocol.getProtocolSubmission().getSubmissionNumber()) 
//                && protocol.getProtocolSubmission().getSubmissionNumber() == currentSubmissionNumber) {
//                return protocol.getProtocolAmendRenewal();
//            }
//        }
//        return null;
//    }
    
    private boolean hasAnsweredQuestionnaire(String moduleSubItemCode, String moduleSubItemKey) {
        return getAnswerHeaderCount(moduleSubItemCode, moduleSubItemKey) > 0;
    }

    int getAnswerHeaderCount(String moduleSubItemCode, String moduleSubItemKey) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", CoeusModule.IACUC_PROTOCOL_MODULE_CODE);
        fieldValues.put("moduleItemKey", getProtocol().getProtocolNumber());
        fieldValues.put("moduleSubItemCode", moduleSubItemCode);
        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
    }
    
    /*
     * This will check whether there is submission questionnaire.
     * When business rule is implemented, this will become more complicated because
     * each request action may have different set of questionnaire, so this has to be changed.
     */
    private boolean hasSubmissionQuestionnaire() {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IACUC_PROTOCOL_MODULE_CODE, this.getProtocolForm().getProtocolDocument().getProtocol().getProtocolNumber() + "T", CoeusSubModule.PROTOCOL_SUBMISSION, "999", false);
        return CollectionUtils.isNotEmpty(getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }

//TODO: Need to put IACUC-specific code here        
//    /**
//     * 
//     * This method is to get previous submission number.  Current implementation is based on submission number in sequence.
//     * If multiple amendment/renewal are submitted, but approved not according to submission order.  Then we may have gaping submission number.
//     * @return
//     */
//    public int getPrevSubmissionNumber() {
//        List<Integer> submissionNumbers = getAvailableSubmissionNumbers();
//        Integer submissionNumber = currentSubmissionNumber - 1;
//        if (!submissionNumbers.contains(submissionNumber)) {
//            for (int i = currentSubmissionNumber - 1; i > 0; i--) {
//                if (submissionNumbers.contains(i)) {
//                    submissionNumber = i;
//                    break;
//                }
//            }
//        }
//        return submissionNumber;
//
//    }
//    
//    /**
//     * 
//     * This method is to get next submissionnumber
//     * @return
//     */
//    public int getNextSubmissionNumber() {
//        List<Integer> submissionNumbers = getAvailableSubmissionNumbers();
//        int maxSubmissionNumber = getMaxSubmissionNumber();
//
//        Integer submissionNumber = currentSubmissionNumber + 1;
//        if (!submissionNumbers.contains(submissionNumber)) {
//            for (int i = currentSubmissionNumber + 1; i <= maxSubmissionNumber; i++) {
//                if (submissionNumbers.contains(i)) {
//                    submissionNumber = i;
//                    break;
//                }
//            }
//        }
//        return submissionNumber;
//
//    }
//
//    /*
//  * this returns a list of submission numbers for a protocol.
//  */
// private List<Integer> getAvailableSubmissionNumbers() {
//     List<Integer> submissionNumbers = new ArrayList<Integer>();
//     for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
//         submissionNumbers.add(submission.getSubmissionNumber());
//     }
//     return submissionNumbers;
// }
//
//
//
// /*
//     * utility method to set whether to display next or previous button on submission panel.
//     */
//    private void setPrevNextFlag() {
//        int maxSubmissionNumber = 0;
//        int minSubmissionNumber = 0;
//        setPrevDisabled(false);
//        setNextDisabled(false);
//
//        for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
//            if (submission.getSubmissionNumber() > maxSubmissionNumber) {
//                maxSubmissionNumber = submission.getSubmissionNumber();
//            }
//            if (submission.getSubmissionNumber() < minSubmissionNumber || minSubmissionNumber == 0) {
//                minSubmissionNumber = submission.getSubmissionNumber();
//            }
//        }
//        if (currentSubmissionNumber == minSubmissionNumber) {
//            setPrevDisabled(true);
//        }
//        if (currentSubmissionNumber == maxSubmissionNumber) {
//            setNextDisabled(true);
//        }
//    }
//
//    public void addNotifyIacucAttachment() {
//        getProtocolNotifyIacucBean().getActionAttachments().add(
//    }
//
//    public boolean validFile(final ProtocolActionAttachment attachment, String propertyName) {
//        
//        boolean valid = true;
//        
//        //this got much more complex using anon keys
//        if (attachment.getFile() == null || StringUtils.isBlank(attachment.getFile().getFileName())) {
//            valid = false;
//            new ErrorReporter().reportError("actionHelper." + propertyName + ".newActionAttachment.file",
//                KeyConstants.ERROR_ATTACHMENT_REQUIRED);
//        }
//        
//        return valid;
//    }
//
//    public ProtocolReviewNotRequiredBean getProtocolReviewNotRequiredBean() {
//        return this.protocolReviewNotRequiredBean;
//    }
//
//    public boolean isPrevDisabled() {
//        return prevDisabled;
//    }
//
//
//    public void setPrevDisabled(boolean prevDisabled) {
//        this.prevDisabled = prevDisabled;
//    }
//
//
//    public boolean isNextDisabled() {
//        return nextDisabled;
//    }
//
//
//    public void setNextDisabled(boolean nextDisabled) {
//        this.nextDisabled = nextDisabled;
//    }
//
//
//    public List<ProtocolReviewer> getProtocolReviewers() {
//        return protocolReviewers;
//    }
//
//
//    public void setProtocolReviewers(List<ProtocolReviewer> protocolReviewers) {
//        this.protocolReviewers = protocolReviewers;
//    }
//
//
//    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
//        this.businessObjectService = businessObjectService;
//    }
//
//
//    public String getRenewalSummary() {
//        return renewalSummary;
//    }
//
//
//    public void setRenewalSummary(String renewalSummary) {
//        this.renewalSummary = renewalSummary;
//    }
//
//
//    /**
//     * Sets the protocolSummaryPrintOptions attribute value.
//     * @param protocolSummaryPrintOptions The protocolSummaryPrintOptions to set.
//     */
//    public void setProtocolSummaryPrintOptions(ProtocolSummaryPrintOptions protocolSumamryPrintOptions) {
//        this.protocolSummaryPrintOptions = protocolSumamryPrintOptions;
//    }
//
//
//    /**
//     * Gets the protocolSummaryPrintOptions attribute. 
//     * @return Returns the protocolSummaryPrintOptions.
//     */
//    public ProtocolSummaryPrintOptions getProtocolSummaryPrintOptions() {
//        return protocolSummaryPrintOptions;
//    }
    
    public ProtocolActionBean getActionBean(String taskName) {
        return actionBeanTaskMap.get(taskName);
    }

//TODO: Need to put IACUC-specific code here        
//    public ProtocolRequestBean getRequestBean(String actionTypeCode) {
//        ProtocolRequestBean protocolRequestBean = null;
//        
//        ProtocolRequestAction action = ProtocolRequestAction.valueOfActionTypeCode(actionTypeCode);
//        if (action != null) {
//            ProtocolActionBean bean = actionBeanTaskMap.get(action.getTaskName());
//            if (bean instanceof ProtocolRequestBean) {
//                protocolRequestBean = (ProtocolRequestBean) bean;
//            }
//        }
//        
//        return protocolRequestBean;
//    }
//
//    public Boolean getSummaryReport() {
//        return summaryReport;
//    }
//
//    public void setSummaryReport(Boolean summaryReport) {
//        this.summaryReport = summaryReport;
//    }
//
//    public Boolean getFullReport() {
//        return fullReport;
//    }
//
//    public void setFullReport(Boolean fullReport) {
//        this.fullReport = fullReport;
//    }
//
//    public Boolean getHistoryReport() {
//        return historyReport;
//    }
//
//    public void setHistoryReport(Boolean historyReport) {
//        this.historyReport = historyReport;
//    }
//
//    public Boolean getReviewCommentsReport() {
//        return reviewCommentsReport;
//    }
//
//    public void setReviewCommentsReport(Boolean reviewCommentsReport) {
//        this.reviewCommentsReport = reviewCommentsReport;
//    }
//
//    public boolean isSubmissionQuestionnaireExist() {
//        return submissionQuestionnaireExist;
//    }
//
//    public void setSubmissionQuestionnaireExist(boolean submissionQuestionnaireExist) {
//        this.submissionQuestionnaireExist = submissionQuestionnaireExist;
//    }
//
//    public boolean isToAnswerSubmissionQuestionnaire() {
//        return toAnswerSubmissionQuestionnaire;
//    }
//
//    public void setToAnswerSubmissionQuestionnaire(boolean toAnswerSubmissionQuestionnaire) {
//        this.toAnswerSubmissionQuestionnaire = toAnswerSubmissionQuestionnaire;
//    }
//
//    public ProtocolSummaryPrintOptions getProtocolPrintOption() {
//        return protocolPrintOption;
//    }
//
//    public void setProtocolPrintOption(ProtocolSummaryPrintOptions protocolPrintOption) {
//        this.protocolPrintOption = protocolPrintOption;
//    }
//
//    public List<QuestionnairePrintOption> getQuestionnairesToPrints() {
//        return questionnairesToPrints;
//    }
//
//    public void setQuestionnairesToPrints(List<QuestionnairePrintOption> questionnairesToPrints) {
//        this.questionnairesToPrints = questionnairesToPrints;
//    }
//
//    private void initPrintQuestionnaire() {
//        setQuestionnairesToPrints(new ArrayList<QuestionnairePrintOption>());
//        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean(getProtocol());
//        //answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
//        List<AnswerHeader> answerHeaders  = getQuestionnaireAnswerService().getAnswerHeadersForProtocol(getProtocol().getProtocolNumber());
//        setupQnPrintOption(answerHeaders);
//    }
//
//    /*
//     * This method is to set up the questionnaire print list.  Then sorted it.
//     */
//    private void setupQnPrintOption(List<AnswerHeader> answerHeaders) {
//        int maxSubmissionNumber = getMaxSubmissionNumber();
//        for (AnswerHeader answerHeader : answerHeaders) {
//            // only submission questionnaire and current protocol questionnaire will be printed
//            if ( (CoeusSubModule.PROTOCOL_SUBMISSION.equals(answerHeader.getModuleSubItemCode()) && Integer.parseInt(answerHeader.getModuleSubItemKey()) <= maxSubmissionNumber)
//                    || (isCurrentAmendRenewalQn(answerHeader)) ) {
//                QuestionnairePrintOption printOption = new QuestionnairePrintOption();
//                printOption.setQuestionnaireRefId(answerHeader.getQuestionnaire().getQuestionnaireRefIdAsLong());
//                printOption.setQuestionnaireId(answerHeader.getQuestionnaire().getQuestionnaireIdAsInteger());
//                printOption.setSelected(true);
//                printOption.setQuestionnaireName(answerHeader.getQuestionnaire().getName());
//                printOption.setLabel(getQuestionnaireLabel(answerHeader));
//                printOption.setItemKey(answerHeader.getModuleItemKey());
//                printOption.setSubItemKey(answerHeader.getModuleSubItemKey());
//                printOption.setSubItemCode(answerHeader.getModuleSubItemCode());
//                // finally check if the answerheader's questionnaire is active, and set it accordingly in the Qnnr print option bean
//                printOption.setQuestionnaireActive(isQuestionnaireActive(answerHeader));
//                getQuestionnairesToPrints().add(printOption);
//            }
//        }
//        Collections.sort(getQuestionnairesToPrints(), new QuestionnairePrintOptionComparator());
//    }
//    
//    private boolean isQuestionnaireActive(AnswerHeader answerHeader) {        
//        Integer questionnaireId = answerHeader.getQuestionnaire().getQuestionnaireIdAsInteger();
//        String coeusModuleCode = answerHeader.getModuleItemCode();
//        String coeusSubModuleCode = answerHeader.getModuleSubItemCode(); 
//        return getQuestionnaireAnswerService().checkIfQuestionnaireIsActiveForModule(questionnaireId, coeusModuleCode, coeusSubModuleCode);
//    }
//
//    private int getMaxSubmissionNumber() {
//        int maxSubmissionNumber = 0;
//
//        for (Integer submissionNumber : getAvailableSubmissionNumbers()) {
//            if (submissionNumber > maxSubmissionNumber) {
//                maxSubmissionNumber = submissionNumber;
//            }
//        }
//        return maxSubmissionNumber;
//    }
//    /*
//     * check if this is the current version of the amend/renewal Qn
//     */
//    private boolean isCurrentAmendRenewalQn(AnswerHeader answerHeader) {
//        boolean isCurrentQn = CoeusSubModule.AMENDMENT_RENEWAL.equals(answerHeader.getModuleSubItemCode())
//                || CoeusSubModule.ZERO_SUBMODULE.equals(answerHeader.getModuleSubItemCode());
//        if (isCurrentQn) {
//            if (CoeusSubModule.ZERO_SUBMODULE.equals(answerHeader.getModuleSubItemCode())) {
//                isCurrentQn = isCurrentRegularQn(answerHeader);
//            } else if (CoeusSubModule.AMENDMENT_RENEWAL.equals(answerHeader.getModuleSubItemCode())) {
//                isCurrentQn = isCurrentAorRQn(answerHeader);
//            }
//        }
//        return isCurrentQn;
//    }
//
//    /*
//     * This is Questionnaire answer is with submodulecode of "0".
//     * Then if this protocol is A/R, then it has to check whether this is the first version
//     * A/R merged into. 
//     */
//    private boolean isCurrentRegularQn(AnswerHeader answerHeader) {
//        boolean isCurrentQn = false;
//        if ((getProtocol().isAmendment() || getProtocol().isRenewal()) && !answerHeader.getModuleItemKey().equals(getProtocol().getProtocolNumber())) {
//            Map keyValues = new HashMap();
//            keyValues.put("protocolNumber", answerHeader.getModuleItemKey());
//          //  keyValues.put("protocolNumber", getProtocol().getProtocolNumber());
//            Protocol prevProtocol = null;
//            // if this is an A/R protocol, then need to find the original protocol that the A/R first merged into.
//            for (Protocol protocol : ((List<Protocol>) businessObjectService.findMatchingOrderBy(Protocol.class, keyValues,
//                    "sequenceNumber", true))) {
//                isCurrentQn = answerHeader.getModuleSubItemKey().equals(protocol.getSequenceNumber().toString())
//                        && !CollectionUtils.isEmpty(getProtocol().getProtocolSubmissions())
//                        && isMergedToProtocol(protocol, getProtocol());
//                if (isCurrentQn) {
//                    if (prevProtocol == null
//                            || !isMergedToProtocol(prevProtocol, getProtocol())) {
//                        // this is the protocol this A/R merged into. so, use this questionnaire.
//                        break;
//                    } else {
//                        // prevProtocol is the initial Protocol that this A/R merged into.
//                        isCurrentQn = false;
//                    }
//
//                }
//                prevProtocol = protocol;
//            }
//        } else {
//            // if this is a regular protocol, then check if sequencenumber & modulesubitemkey match
//            isCurrentQn = answerHeader.getModuleSubItemKey().equals(getProtocol().getSequenceNumber().toString());
//        }
//        return isCurrentQn;
//    }
//    
//    /*
//     * if questionnaire is associated with Amendment/renewal submodulecode.
//     * if this protocol is normal protocol, then it has to check whether the A/R of this
//     * questionnaire has merged to this protocol yet.
//     */
//    private boolean isCurrentAorRQn(AnswerHeader answerHeader) {
//        boolean isCurrentQn = false;
//        if (getProtocol().isAmendment() || getProtocol().isRenewal()) {
//            // if this is A/R, then just match sequencenumber and modulesubitemkey
//            isCurrentQn = answerHeader.getModuleSubItemKey().equals(getProtocol().getSequenceNumber().toString());
//        } else {
//            // if this is a regular protocol, then get this A/R associated this this Qn and see if
//            // A/R has been merged to this version of protocol
//            Map keyValues = new HashMap();
//            keyValues.put("protocolNumber", answerHeader.getModuleItemKey());
//            Protocol protocol = ((List<Protocol>) businessObjectService.findMatchingOrderBy(Protocol.class, keyValues,
//                    "sequenceNumber", false)).get(0);
//            isCurrentQn = answerHeader.getModuleSubItemKey().equals(protocol.getSequenceNumber().toString())
//                    && !CollectionUtils.isEmpty(protocol.getProtocolSubmissions())
//                    && isMergedToProtocol(getProtocol(), protocol);
//        }
//        return isCurrentQn;
//    }       
//    
//    private boolean isMergedToProtocol(Protocol protocol, Protocol amendment) {
//        boolean merged = false;
//        int submissionNumber = amendment.getProtocolSubmissions().get(amendment.getProtocolSubmissions().size() - 1).getSubmissionNumber();
//        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
//            if (submissionNumber == submission.getSubmissionNumber().intValue()) {
//                merged = true;
//                break;
//            }
//        }
//        return merged;
//    }
//    
//    private String getQuestionnaireLabel(AnswerHeader answerHeader) {
//        String label = null;
//        List<QuestionnaireUsage> usages = answerHeader.getQuestionnaire().getQuestionnaireUsages();
//        if (CollectionUtils.isNotEmpty(usages) && usages.size() > 1) {
//            Collections.sort((List<QuestionnaireUsage>) usages);
//           // Collections.reverse((List<QuestionnaireUsage>) usages);
//        }
//        for (QuestionnaireUsage usage : usages) {
//            if (getCoeusModule().equals(usage.getModuleItemCode()) && answerHeader.getModuleSubItemCode().equals(usage.getModuleSubItemCode())) {
//                if ("0".equals(answerHeader.getModuleSubItemCode())) {
//                    label = usage.getQuestionnaireLabel();
//                } else if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(answerHeader.getModuleSubItemCode())) {
//                    Map keyValues = new HashMap();
//                    keyValues.put("protocolNumber", answerHeader.getModuleItemKey());
//                    keyValues.put("submissionNumber", answerHeader.getModuleSubItemKey());
//                    List<ProtocolSubmission> submissions = ((List<ProtocolSubmission>) businessObjectService.findMatchingOrderBy(ProtocolSubmission.class, keyValues,
//                            "submissionId", false));
//                    if (submissions.isEmpty()) {
//                        // this may happen if it is undo last action
//                        label = usage.getQuestionnaireLabel();
//                    } else {
//                        keyValues.clear();
//                        keyValues.put("protocolId", submissions.get(0).getProtocolId());
//                        keyValues.put("submissionNumber", answerHeader.getModuleSubItemKey());
//                        // keyValues.put("submissionIdFk", submission.getSubmissionId());
//                        ProtocolAction protocolAction = ((List<ProtocolAction>) businessObjectService.findMatching(
//                                ProtocolAction.class, keyValues)).get(0);
//                        label = usage.getQuestionnaireLabel() + " - " + protocolAction.getProtocolActionType().getDescription()
//                                + " - " + protocolAction.getActionDateString();
//                    }
//                } else if (CoeusSubModule.AMENDMENT_RENEWAL.equals(answerHeader.getModuleSubItemCode())) {
//                    if (answerHeader.getModuleItemKey().contains("A")) {
//                        label = usage.getQuestionnaireLabel() + " - Amendment " + answerHeader.getModuleItemKey().substring(10);
//                    } else {
//                        label = usage.getQuestionnaireLabel() + " - Renewal " + answerHeader.getModuleItemKey().substring(10);
//                    }
//                }
//            }
//        }
//        return label;
//    }
//
//    public boolean isSummaryQuestionnaireExist() {
//        return summaryQuestionnaireExist;
//    }
//
//    public void setSummaryQuestionnaireExist(boolean summaryQuestionnaireExist) {
//        this.summaryQuestionnaireExist = summaryQuestionnaireExist;
//    }

    public boolean isCanAbandon() {
        return canAbandon;
    }

//    public ProtocolGenericActionBean getProtocolAbandonBean() {
//        return protocolAbandonBean;
//    }
//
//    public void setProtocolAbandonBean(ProtocolGenericActionBean protocolAbandonBean) {
//        this.protocolAbandonBean = protocolAbandonBean;
//    }
//
//    public boolean isHideReviewerName() {
//        return hideReviewerName;
//    }
//
//    public void setHideReviewerName(boolean hideReviewerName) {
//        this.hideReviewerName = hideReviewerName;
//    }
//    
//    /*
//     * check if to display reviewer name for any of the review comments of the submission selected in submission details..
//     */
//    private boolean checkToHideSubmissionReviewerName() {
//        boolean isHide = true;
//        for (CommitteeScheduleMinute reviewComment : getReviewComments()) {
//            if (reviewComment.isDisplayReviewerName()) {
//                isHide = false;
//                break;
//            }
//        }
//        return isHide;
//    }
//    
//    /*
//     * check if to display reviewer name for any of the review comments of current submission.
//     */
//    private boolean checkToHideReviewName() {
//        boolean isHide = true;
//        if (getProtocol().getProtocolSubmission().getSubmissionId() != null) {
//            isHide = getReviewerCommentsService().setHideReviewerName(getProtocol(), getProtocol().getProtocolSubmission().getSubmissionNumber());
//        }
//        return isHide;
//    }
//
//    public boolean isHideSubmissionReviewerName() {
//        return hideSubmissionReviewerName;
//    }
//
//    public void setHideSubmissionReviewerName(boolean hideSubmissionReviewerName) {
//        this.hideSubmissionReviewerName = hideSubmissionReviewerName;
//    }
//
//    public List<ProtocolReviewAttachment> getReviewAttachments() {
//        return reviewAttachments;
//    }
//
//    public void setReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments) {
//        this.reviewAttachments = reviewAttachments;
//    }
//
//    public boolean isHideReviewerNameForAttachment() {
//        return hideReviewerNameForAttachment;
//    }
//
//    public void setHideReviewerNameForAttachment(boolean hideReviewerNameForAttachment) {
//        this.hideReviewerNameForAttachment = hideReviewerNameForAttachment;
//    }

    public ProtocolCorrespondence getProtocolCorrespondence() {
        return protocolCorrespondence;
    }

    public void setProtocolCorrespondence(ProtocolCorrespondence protocolCorrespondence) {
        this.protocolCorrespondence = protocolCorrespondence;
    }

    public boolean getIsApproveOpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_APPROVED);
    }

    public boolean getIsDisapproveOpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_DISAPPROVED);
    }

    public boolean getIsReturnForSMROpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED);
    }

    public boolean getIsReturnForSRROpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED);
    }

    public boolean isOpenForFollowup() {
        return getIsApproveOpenForFollowup() || getIsDisapproveOpenForFollowup() ||
               getIsReturnForSMROpenForFollowup() || getIsReturnForSRROpenForFollowup();
    }

    protected List<String>getActionTypeSubmissionDocList() {
        return IacucProtocolActionType.getActionTypeSubmissionDocs();
    }

    public boolean isCanDesignatedMemberApproval() {
        return canDesignatedMemberApproval;
    }

    public boolean isCanDesignatedMemberApprovalUnavailable() {
        return canDesignatedMemberApprovalUnavailable;
    }

    public boolean isCanHold() {
        return canHold;
    }

    public boolean isCanHoldUnavailable() {
        return canHoldUnavailable;
    }

    public boolean isCanLiftHold() {
        return canLiftHold;
    }

    public boolean isCanLiftHoldUnavailable() {
        return canLiftHoldUnavailable;
    }

    public boolean isCanRequestToLiftHold() {
        return canRequestToLiftHold;
    }

    public boolean isCanRequestToLiftHoldUnavailable() {
        return canRequestToLiftHoldUnavailable;
    }

    public boolean isCanTable() {
        return canTable;
    }

    public boolean isCanTableUnavailable() {
        return canTableUnavailable;
    }
    
    public boolean isCanNotifyIacuc() {
        return canNotifyIacuc;
    }
    
    public boolean isCanNotifyIacucUnavailable() {
        return canNotifyIacucUnavailable;
    }

    public boolean getCanDeleteIacucProtocol() {
        return canDeleteIacucProtocol;
    }
    
    public boolean getCanDeleteIacucProtocolUnavailable() {
        return canDeleteIacucProtocolUnavailable;
    }
    
    public boolean getCanAdministrativelyApprove() {
        return canAdministrativelyApprove;
    }
    
    public boolean getCanAdministrativelyApproveUnavailable() {
        return canAdministrativelyApproveUnavailable;
    }
    
    public boolean getCanAdministrativelyMarkIncomplete() {
        return canAdministrativelyMarkIncomplete;
    }
    
    public boolean getCanAdministrativelyMarkIncompleteUnavailable() {
        return canAdministrativelyMarkIncompleteUnavailable;
    }
    
    public boolean getCanAdministrativelyWithdraw() {
        return canAdministrativelyWithdraw;
    }
    
    public boolean getCanAdministrativelyWithdrawUnavailable() {
        return canAdministrativelyWithdrawUnavailable;
    }
    
    public boolean getCanReturnToPI() {
        return canReturnToPI;
    }
    
    public boolean getCanReturnToPIUnavailable() {
        return canReturnToPIUnavailable;
    }
    
    public boolean getCanReviewNotRequired() {
        return canReviewNotRequired;
    }
    
    public boolean getCanReviewNotRequiredUnavailable() {
        return canReviewNotRequiredUnavailable;
    }

    public boolean canIacucAcknowledge() {
        return canIacucAcknowledge;
    }
    
    public boolean canIacucAcknowledgeUnavailable() {
        return canIacucAcknowledgeUnavailable;
    }
    
    public boolean canIacucRequestDeactivate() {
        return canIacucRequestDeactivate;
    }
    
    public boolean canIacucRequestDeactivateUnavailable() {
        return canIacucRequestDeactivateUnavailable;
    }

    protected String getParameterValue(String parameterName) {
        String result = getParameterService().getParameterValueAsString(IacucProtocolDocument.class, parameterName);
        if (result == null) {
            result = super.getParameterValue(parameterName);
        }
        return result;
    }

    protected IacucProtocol getIacucProtocol() {
        return (IacucProtocol)getProtocol();
    }
}

