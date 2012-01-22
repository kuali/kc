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
package org.kuali.kra.irb.actions.submit;

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
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolSubmissionDoc;
import org.kuali.kra.irb.actions.followup.FollowupActionService;
import org.kuali.kra.irb.onlinereview.ProtocolReviewable;
import org.kuali.kra.irb.personnel.ProtocolPerson;
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
public class ProtocolActionServiceImpl implements ProtocolActionService {

    static private final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolActionServiceImpl.class);
    
    private static final String PERMISSIONS_LEADUNIT_FILE = "org/kuali/kra/irb/drools/rules/permissionForLeadUnitRules.drl";

    private static final String PERMISSIONS_SUBMIT_FILE = "org/kuali/kra/irb/drools/rules/permissionToSubmitRules.drl";

    private static final String PERMISSIONS_COMMITTEEMEMBERS_FILE = "org/kuali/kra/irb/drools/rules/permissionToCommitteeMemberRules.drl";

    private static final String PERMISSIONS_SPECIAL_FILE = "org/kuali/kra/irb/drools/rules/permissionForSpecialRules.drl";

    private static final String PERFORMACTION_FILE = "org/kuali/kra/irb/drools/rules/canPerformProtocolActionRules.drl";

    private static final String UPDATE_FILE = "org/kuali/kra/irb/drools/rules/updateProtocolRules.drl";
    
    private static final String UNDO_ACTION_FILE = "org/kuali/kra/irb/drools/rules/undoProtocolUpdateRules.drl";
    
    private static final String FOLLOWUP_FILE = "org/kuali/kra/irb/drools/rules/isProtocolActionOpenForFollowupRules.drl";

    private static final int PERMISSIONS_LEADUNIT_RULE = 0;

    private static final int PERMISSIONS_SUBMIT_RULE = 1;

    private static final int PERMISSIONS_COMMITTEEMEMBERS_RULE = 2;

    private static final int PERMISSIONS_SPECIAL_RULE = 3;

    private static final int PERFORMACTION_RULE = 4;

    private static final int UPDATE_RULE = 5;
    
    private static final int UNDO_UPDATE_RULE = 6;
   
    private static final String MODIFY_ANY_PROTOCOL = "Modify Any Protocol";

    private static final String PERFORM_IRB_ACTIONS_ON_PROTO = "Perform IRB Actions on a Protocol";

    private static final String DEFAULT_ORGANIZATION_UNIT = "000001";

    private static final String AMEND = "A";

    private static final String RENEW = "R";

    private static final String NONE = "NONE";
    
    private static final String KC_PROTOCOL = "KC-PROTOCOL";

    private BusinessObjectService businessObjectService;

    private KraAuthorizationService kraAuthorizationService;

    private UnitAuthorizationService unitAuthorizationService;
    
    private FollowupActionService followupActionService;
   
    private ProtocolDao protocolDao;

    private DroolsRuleHandler canPerformRuleHandler;

    private String[] actn = { "101", "102", "103", "104", "105", "106", "108", "114", "115", "116", "200", "201", "202", "203",  
                              "204", "205", "206", "207", "208", "209", "210", "211", "212", "300", "301", "302", "303", "304", 
                              "305", "306" };

    private List<String> actions = new ArrayList<String>();
    private List<DroolsRuleHandler> rulesList;


    {
        actions = Arrays.asList(actn);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    public void setProtocolDao(ProtocolDao protocolDao) {
        this.protocolDao = protocolDao;
    }

    public void setFollowupActionService(FollowupActionService followupActionService) {
        this.followupActionService = followupActionService;
    }

    
    
    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#isActionAllowed(java.lang.String, org.kuali.kra.irb.Protocol)
     */
    public boolean isActionAllowed(String actionTypeCode, Protocol protocol) {
        return canPerformAction(actionTypeCode, protocol) || protocol.isFollowupAction(actionTypeCode);
    }

    /**
     * This method is to check if user is authorized to perform action of 'actionTypeCode'
     */
    protected boolean isAuthorizedtoPerform(String actionTypeCode, Protocol protocol) {
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
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#getActionsAllowed(org.kuali.kra.irb.Protocol)
     */
    public List<String> getActionsAllowed(Protocol protocol) {

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
    protected boolean hasPermissionLeadUnit(String actionTypeCode, Protocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_LEADUNIT_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), protocol.getLeadUnitNumber(),
                KC_PROTOCOL, MODIFY_ANY_PROTOCOL) : false;
    }

    /**
     * This method is to check if user has permission to submit
     */
    protected boolean hasPermissionToSubmit(String actionTypeCode, Protocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_SUBMIT_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? kraAuthorizationService.hasPermission(getUserIdentifier(), protocol, rightMapper
                .getRightId()) : false;
    } 

    private List<String> getPersonnelIds(Protocol protcol) {
        List<String> PersonnelIds = new ArrayList<String>();
       
            for (ProtocolPerson person : protcol.getProtocolPersons()) {
                if (StringUtils.isNotBlank(person.getPersonId())) {
                    PersonnelIds.add(person.getPersonId());
                }
                else {
                    PersonnelIds.add(person.getRolodexId().toString());
                }
            
        }
        return PersonnelIds;
    }
   
    
   public boolean isProtocolPersonnel(Protocol protocol) {
        Person person = GlobalVariables.getUserSession().getPerson();
        return getPersonnelIds(protocol).contains(person.getPrincipalId());        
    }
    /**
     * This method is to check if user has permission in committee home unit
     */
    protected boolean hasPermissionAsCommitteeMember(String actionTypeCode, Protocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rightMapper.setCommitteeId(protocol.getProtocolSubmission().getCommitteeId());
        rightMapper.setScheduleId(protocol.getProtocolSubmission().getScheduleId());
        rulesList.get(PERMISSIONS_COMMITTEEMEMBERS_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), protocol.getLeadUnitNumber(),
                KC_PROTOCOL, PERFORM_IRB_ACTIONS_ON_PROTO) : false;
    }

    /**
     * This method is to check if user has permission for special cases.
     */
    protected boolean hasPermissionSpecialCase(String actionTypeCode, String unit, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_SPECIAL_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), unit,
                KC_PROTOCOL, PERFORM_IRB_ACTIONS_ON_PROTO) : false;
    }

    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId(); 
    }

    /**
     * This method is to check whether 'actionTypeCode' can be performed based on protocol's status code or submission code or other
     * condition specified in rule.
     */
    public boolean canPerformAction(String actionTypeCode, Protocol protocol) {
        LOG.info(actionTypeCode);
        String submissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();
        String submissionTypeCode = protocol.getProtocolSubmission().getSubmissionTypeCode();
        String protocolReviewTypeCode = protocol.getProtocolSubmission().getProtocolReviewTypeCode();
        String protocolStatusCode = protocol.getProtocolStatusCode();
        String scheduleId = protocol.getProtocolSubmission().getScheduleId();
        Integer submissionNumber = protocol.getProtocolSubmission().getSubmissionNumber();
        ProtocolActionMapping protocolAction = new ProtocolActionMapping(actionTypeCode, submissionStatusCode, submissionTypeCode,
            protocolReviewTypeCode, protocolStatusCode, scheduleId, submissionNumber);
        protocolAction.setBusinessObjectService(businessObjectService);
        protocolAction.setDao(protocolDao);
        protocolAction.setProtocol(protocol);
        rulesList.get(PERFORMACTION_RULE).executeRules(protocolAction);
        return protocolAction.isAllowed();
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#updateProtocolStatus(org.kuali.kra.irb.actions.ProtocolAction,
     *      org.kuali.kra.irb.Protocol)
     */
    public void updateProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol) {
        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();
        String specialCondition = protocolNumberUpper.contains(AMEND) ? AMEND : (protocolNumberUpper.contains(RENEW) ? RENEW : NONE);

        ProtocolActionUpdateMapping protocolAction = new ProtocolActionUpdateMapping(protocolActionBo.getProtocolActionTypeCode(),
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
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#resetProtocolStatus(org.kuali.kra.irb.actions.ProtocolAction, org.kuali.kra.irb.Protocol)
     */
    public void resetProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol) {
        ProtocolUndoActionMapping protocolAction = new ProtocolUndoActionMapping(protocolActionBo.getProtocolActionTypeCode(), 
                    protocolActionBo.getSubmissionTypeCode(), protocol.getProtocolStatusCode());
        
        protocolAction.setProtocol(protocol);
        protocolAction.setProtocolSubmission(protocol.getProtocolSubmission());
        protocolAction.setProtocolAction(protocolActionBo);
        rulesList.get(UNDO_UPDATE_RULE).executeRules(protocolAction);
        if (protocolAction.isProtocolSubmissionToBeDeleted()) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionIdFk", protocolActionBo.getProtocolSubmission().getSubmissionId().toString());
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            businessObjectService.deleteMatching(ProtocolSubmissionDoc.class, fieldValues);
            removeQuestionnaireAnswer(protocolActionBo, protocol);
            protocol.getProtocolSubmissions().remove(protocolActionBo.getProtocolSubmission()); 
            protocol.setProtocolSubmission(null); 
        }
    }
    
    /*
     * This is to remove the questionnaire answered for request submission
     */
    private void removeQuestionnaireAnswer(ProtocolAction protocolActionBo, Protocol protocol) {
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
     * @see org.kuali.kra.irb.actions.ProtocolActionFollowupService#isActionOpenForFollowup(java.lang.String, org.kuali.kra.irb.Protocol)
     */
    public boolean isActionOpenForFollowup(String protocolActionTypeCode, Protocol protocol) {
        return followupActionService.isActionOpenForFollowup(protocolActionTypeCode, protocol);
//        String motionTypeCode = protocol.getProtocolSubmission().getCommitteeDecisionMotionTypeCode();
//        ProtocolActionFollowupMapping mapping = new ProtocolActionFollowupMapping(protocolActionTypeCode, motionTypeCode);
//        rulesList.get(FOLLOWUP_RULE).executeRules(mapping);
//        return mapping.getIsOpenForFollowup();
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