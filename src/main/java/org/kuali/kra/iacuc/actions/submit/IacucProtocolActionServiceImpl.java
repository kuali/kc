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
package org.kuali.kra.iacuc.actions.submit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.drools.util.DroolsRuleHandler;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDao;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.followup.IacucFollowupActionService;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.submit.ActionRightMapping;
import org.kuali.kra.protocol.actions.submit.ProtocolActionServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolActionUpdateMapping;
import org.kuali.kra.protocol.personnel.ProtocolPerson;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;


/**
 * 
 * This class is to provide the 'protocol' action pre validation and post update.
 * pre-validation include canperform and authorization check.
 * post-update will update protocol status or submission status.
 */
public class IacucProtocolActionServiceImpl extends ProtocolActionServiceImpl {

    static private final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(IacucProtocolActionServiceImpl.class);

    // for now, only action rule and update rule files are being used. Thus, we override them here.
    protected static final int PERFORMACTION_RULE = 0;

    protected static final int UPDATE_RULE = 1;
    
    private static final String PERFORMACTION_FILE = "org/kuali/kra/iacuc/drools/rules/canPerformIacucProtocolActionRules.drl";
    private static final String UPDATE_FILE = "org/kuali/kra/iacuc/drools/rules/updateIacucProtocolRules.drl";
    
    private static final String KC_IACUC = "KC-IACUC";

    private String[] actn = { 
            IacucProtocolActionType.SUBMITTED_TO_IACUC,
            IacucProtocolActionType.AMENDMENT_CREATED,
            IacucProtocolActionType.CONTINUATION,
            IacucProtocolActionType.RENEWAL_CREATED,
            IacucProtocolActionType.CONTINUATION_AMENDMENT,
            IacucProtocolActionType.RENEWAL_AMENDMENT,
            IacucProtocolActionType.REQUEST_DEACTIVATE,
            IacucProtocolActionType.REQUEST_LIFT_HOLD,
            IacucProtocolActionType.NOTIFIED_COMMITTEE,
            IacucProtocolActionType.ADMINISTRATIVE_CORRECTION,
            IacucProtocolActionType.NOTIFY_IACUC,
            IacucProtocolActionType.IACUC_WITHDRAWN,
            IacucProtocolActionType.IACUC_ABANDON,
            IacucProtocolActionType.ASSIGNED_TO_AGENDA,
            IacucProtocolActionType.REMOVED_FROM_AGENDA,
            IacucProtocolActionType.RESCHEDULED,
            IacucProtocolActionType.TABLED,
            IacucProtocolActionType.IACUC_APPROVED,
            IacucProtocolActionType.RESPONSE_APPROVAL,
            IacucProtocolActionType.IACUC_ACKNOWLEDGEMENT,
            IacucProtocolActionType.IACUC_REVIEW_NOT_REQUIRED,
            IacucProtocolActionType.LIFT_HOLD,
            IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED,
            IacucProtocolActionType.RETURNED_TO_PI,
            IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED,
            IacucProtocolActionType.DESIGNATED_REVIEW_APPROVAL,
            IacucProtocolActionType.IACUC_REVISIONS_REQUIRED,
            IacucProtocolActionType.FULL_COMMITEE_REQUIRED,
            IacucProtocolActionType.ADMINISTRATIVE_APPROVAL,
            IacucProtocolActionType.ADMINISTRATIVELY_INCOMPLETE,
            IacucProtocolActionType.ADMINISTRATIVELY_WITHDRAWN,
            IacucProtocolActionType.IACUC_DISAPPROVED,
            IacucProtocolActionType.EXPIRED,
            IacucProtocolActionType.DEACTIVATED,
            IacucProtocolActionType.ADMINISTRATIVELY_DEACTIVATED ,
            IacucProtocolActionType.HOLD ,
            IacucProtocolActionType.TERMINATED,
            IacucProtocolActionType.SUSPENDED  };     
    
    {
        actions = Arrays.asList(actn);
    }

    public String getPerformActionFileNameHook() {     
        return PERFORMACTION_FILE;
    }

    public String getUpdateProtocolRulesFileNameHook() {
        return UPDATE_FILE;
    }

//TODO:IACUC    public void setFollowupActionService(IacucFollowupActionService followupActionService) {
//        this.followupActionService = followupActionService;
//    }

    /**
     * @see org.kuali.kra.irb.actions.submit.IacucProtocolActionService#isActionAllowed(java.lang.String, org.kuali.kra.irb.IacucProtocol)
     */
    public boolean isActionAllowed(String actionTypeCode, IacucProtocol protocol) {
        return canPerformAction(actionTypeCode, protocol) || protocol.isFollowupAction(actionTypeCode);
    }

    /**
     * This method is to check if user is authorized to perform action of 'actionTypeCode'
     */
    protected boolean isAuthorizedtoPerform(String actionTypeCode, IacucProtocol protocol) {
        boolean flag = false;
        ActionRightMapping rightMapper = new ActionRightMapping();

        flag = hasPermissionLeadUnit(actionTypeCode, protocol, rightMapper);

        if (!flag) {
            flag = hasPermissionToSubmit(actionTypeCode, protocol, rightMapper);
        }

        if (!flag) {
            flag = hasPermissionAsCommitteeMember(actionTypeCode, protocol, rightMapper);
        }

        if (!flag) {
            flag = hasPermissionSpecialCase(actionTypeCode, DEFAULT_ORGANIZATION_UNIT, rightMapper);
        }

        return flag;
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.IacucProtocolActionService#getActionsAllowed(org.kuali.kra.irb.IacucProtocol)
     */
    public List<String> getActionsAllowed(IacucProtocol protocol) {

        List<String> actionList = new ArrayList<String>();
        for (String actionTypeCode : actions) {
            if (canPerformAction(actionTypeCode, protocol) ) {
                actionList.add(actionTypeCode);
            }
        }
        return actionList;
    }

    /*
     * This method is to check if user has permission in lead unit
     */
    protected boolean hasPermissionLeadUnit(String actionTypeCode, IacucProtocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_LEADUNIT_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), protocol.getLeadUnitNumber(),
                KC_IACUC, PermissionConstants.MODIFY_ANY_IACUC_PROTOCOL) : false;
    }

    /**
     * This method is to check if user has permission to submit
     */
    protected boolean hasPermissionToSubmit(String actionTypeCode, IacucProtocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_SUBMIT_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? kraAuthorizationService.hasPermission(getUserIdentifier(), protocol, rightMapper
                .getRightId()) : false;
    } 

    private List<String> getPersonnelIds(IacucProtocol protocol) {
        List<String> PersonnelIds = new ArrayList<String>();
       
            for (ProtocolPerson person : protocol.getProtocolPersons()) {
                if (StringUtils.isNotBlank(person.getPersonId())) {
                    PersonnelIds.add(person.getPersonId());
                }
                else {
                    PersonnelIds.add(person.getRolodexId().toString());
                }
            
        }
        return PersonnelIds;
    }
   
    
   public boolean isProtocolPersonnel(IacucProtocol protocol) {
        Person person = GlobalVariables.getUserSession().getPerson();
        return getPersonnelIds(protocol).contains(person.getPrincipalId());        
    }
    /**
     * This method is to check if user has permission in committee home unit
     */
    protected boolean hasPermissionAsCommitteeMember(String actionTypeCode, IacucProtocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rightMapper.setCommitteeId(protocol.getProtocolSubmission().getCommitteeId());
        rightMapper.setScheduleId(protocol.getProtocolSubmission().getScheduleId());
        rulesList.get(PERMISSIONS_COMMITTEEMEMBERS_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), protocol.getLeadUnitNumber(),
                KC_IACUC, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO) : false;
    }

    /**
     * This method is to check if user has permission for special cases.
     */
    protected boolean hasPermissionSpecialCase(String actionTypeCode, String unit, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_SPECIAL_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), unit,
                KC_IACUC, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO) : false;
    }

    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId(); 
    }

    /**
     * This method is to check whether 'actionTypeCode' can be performed based on protocol's status code or submission code or other
     * condition specified in rule.
     */
    public boolean canPerformAction(String actionTypeCode, Protocol protocol) {
//        LOG.info(actionTypeCode);
        String submissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();
        String submissionTypeCode = protocol.getProtocolSubmission().getSubmissionTypeCode();
        String protocolReviewTypeCode = protocol.getProtocolSubmission().getProtocolReviewTypeCode();
        String protocolStatusCode = protocol.getProtocolStatusCode();
        String scheduleId = protocol.getProtocolSubmission().getScheduleId();
        Integer submissionNumber = protocol.getProtocolSubmission().getSubmissionNumber();
        IacucProtocolActionMapping protocolAction = new IacucProtocolActionMapping(actionTypeCode, submissionStatusCode, submissionTypeCode,
            protocolReviewTypeCode, protocolStatusCode, scheduleId, submissionNumber);
        protocolAction.setBusinessObjectService(businessObjectService);
//TODO:IACUC        protocolAction.setDao(protocolDao);
        protocolAction.setProtocol(protocol);
        rulesList.get(PERFORMACTION_RULE).executeRules(protocolAction);
        return protocolAction.isAllowed();
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.IacucProtocolActionService#updateProtocolStatus(org.kuali.kra.irb.actions.IacucProtocolAction,
     *      org.kuali.kra.irb.IacucProtocol)
     */
    public void updateProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol) {
        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();
        String specialCondition = protocolNumberUpper.contains(AMEND) ? AMEND : (protocolNumberUpper.contains(RENEW) ? RENEW : NONE);

        IacucProtocolActionUpdateMapping protocolAction = new IacucProtocolActionUpdateMapping(protocolActionBo.getProtocolActionTypeCode(),
            protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode(), protocol.getProtocolStatusCode(),
            specialCondition);
        protocolAction.setProtocol(protocol);
        protocolAction.setProtocolSubmission(protocol.getProtocolSubmission());
        protocolAction.setProtocolAction(protocolActionBo);
        rulesList.get(UPDATE_RULE).executeRules(protocolAction);
        businessObjectService.save(protocol);
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.submit.IacucProtocolActionService#resetProtocolStatus(org.kuali.kra.irb.actions.IacucProtocolAction, org.kuali.kra.irb.IacucProtocol)
     */
    public void resetProtocolStatus(IacucProtocolAction protocolActionBo, IacucProtocol protocol) {
//TODO: to be implemented for IACUC
//        ProtocolUndoActionMapping protocolAction = new ProtocolUndoActionMapping(protocolActionBo.getProtocolActionTypeCode(), 
//                    protocolActionBo.getSubmissionTypeCode(), protocol.getProtocolStatusCode());
//        
//        protocolAction.setProtocol(protocol);
//        protocolAction.setProtocolSubmission(protocol.getProtocolSubmission());
//        protocolAction.setProtocolAction(protocolActionBo);
//        rulesList.get(UNDO_UPDATE_RULE).executeRules(protocolAction);
//        if (protocolAction.isProtocolSubmissionToBeDeleted()) {
//            Map<String, String> fieldValues = new HashMap<String, String>();
//            fieldValues.put("submissionIdFk", protocolActionBo.getProtocolSubmission().getSubmissionId().toString());
//            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
//            businessObjectService.deleteMatching(ProtocolSubmissionDoc.class, fieldValues);
//            removeQuestionnaireAnswer(protocolActionBo, protocol);
//            protocol.getProtocolSubmissions().remove(protocolActionBo.getProtocolSubmission()); 
//            protocol.setProtocolSubmission(null); 
//        }
    }
    
    /*
     * This is to remove the questionnaire answered for request submission
     */
    private void removeQuestionnaireAnswer(IacucProtocolAction protocolActionBo, IacucProtocol protocol) {
        // 'bos.deletematching' will not work because it is not deleting 'answers'
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        fieldValues.put("moduleItemKey", protocol.getProtocolNumber());
        fieldValues.put("moduleSubItemCode", CoeusSubModule.PROTOCOL_SUBMISSION);
        fieldValues.put("moduleSubItemKey", protocolActionBo.getProtocolSubmission().getSubmissionNumber().toString());
        List<AnswerHeader> answerHeaders = (List<AnswerHeader>)businessObjectService.findMatching(AnswerHeader.class, fieldValues);
        if (!answerHeaders.isEmpty()) {
            businessObjectService.delete(answerHeaders);
        }
    }
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.IacucProtocolActionFollowupService#isActionOpenForFollowup(java.lang.String, org.kuali.kra.irb.IacucProtocol)
     */
    public boolean isActionOpenForFollowup(String protocolActionTypeCode, Protocol protocol) {
//TODO:IACUC        return followupActionService.isActionOpenForFollowup(protocolActionTypeCode, (IacucProtocol)protocol);
return true;        
    }
    
    /**
     * Compile rules if rulehandler is not set.
     */
    public DroolsRuleHandler getCanPerformRuleHandler() {
        // compiling is slow for this rule, so try to just compile once
        if (canPerformRuleHandler == null) {
            canPerformRuleHandler = new DroolsRuleHandler(PERFORMACTION_FILE);
        }
        return canPerformRuleHandler;
    }
    
    /**
     * 
     * This method sets the rule file.
     * @param ruleFiles
     * @throws IOException
     */
    public void setRuleFiles(List<String> ruleFiles) throws IOException {
        this.loadRules(ruleFiles);
    }

    protected void loadRules(List<String> ruleFiles) {
        rulesList = new ArrayList<DroolsRuleHandler>();
        for (String ruleFile : ruleFiles) {
            rulesList.add(new DroolsRuleHandler(ruleFile));
        }
    }
}
