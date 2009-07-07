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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.kra.drools.util.DroolsRuleHandler;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;


public class ProtocolActionServiceImpl implements ProtocolActionService {
    
    static private final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolActionServiceImpl.class); 
    private static final String  PERMISSIONS_LEADUNIT_FILE = "org/kuali/kra/irb/drools/rules/permissionForLeadUnitRules.drl";
    
    private static final String  PERMISSIONS_SUBMIT_FILE = "org/kuali/kra/irb/drools/rules/permissionToSubmitRules.drl";
    
    private static final String  PERMISSIONS_COMMITTEEMEMBERS_FILE = "org/kuali/kra/irb/drools/rules/permissionToCommitteeMemberRules.drl";
    
    private static final String  PERMISSIONS_SPECIAL_FILE = "org/kuali/kra/irb/drools/rules/permissionForSpecialRules.drl";
    
    private static final String  PERFORMACTION_FILE = "org/kuali/kra/irb/drools/rules/canPerformProtocolActionRules.drl";
    
    private static final String  UPDATE_FILE = "org/kuali/kra/irb/drools/rules/updateProtocolRules.drl";
    
    private static final String MODIFY_ANY_PROTOCOL = "MODIFY_ANY_PROTOCOL";
    
    private static final String SUBMIT_PROTOCOL = "SUBMIT_PROTOCOL";
    
    private static final String PERFORM_IRB_ACTIONS_ON_PROTO = "PERFORM_IRB_ACTIONS_ON_PROTO";
    
    private static final String DEFAULT_ORGANIZATION_UNIT = "000001";
    
    private static final String AMEND = "A";
    
    private static final String RENEW = "R";
    
    private static final String NONE = "NONE";

    private BusinessObjectService businessObjectService;

    private ProtocolAuthorizationService protocolAuthorizationService;

    private UnitAuthorizationService unitAuthorizationService;

    private ProtocolDao protocolDao;
    
    private DroolsRuleHandler canPerformRuleHandler;

    String[] actn = { "101","102","103","104", "105", "106", "108", "114", "115", "116", "200", "201", "202", "203", "204", "205", "206", "207",
            "208", "209", "210", "211", "212", "300", "301", "302", "303", "304", "305", "306" };

    private List<String> actions = new ArrayList<String>();

    {
        actions = Arrays.asList(actn);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setProtocolAuthorizationService(ProtocolAuthorizationService protocolAuthorizationService) {
        this.protocolAuthorizationService = protocolAuthorizationService;
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    public void setProtocolDao(ProtocolDao protocolDao) {
        this.protocolDao = protocolDao;
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#isActionAllowed(java.lang.String, org.kuali.kra.irb.Protocol)
     */
    public boolean isActionAllowed(String actionTypeCode, Protocol protocol) {        
        return canPerformAction(actionTypeCode, protocol) && isAuthorizedtoPerform(actionTypeCode, protocol);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#isActionAllowed(java.lang.String, org.kuali.kra.irb.Protocol)
     */
    private boolean isAuthorizedtoPerform(String actionTypeCode, Protocol protocol) {
        boolean flag = false;
        ActionRightMapping rightMapper = new ActionRightMapping();
        
        flag = hasPermissionLeadUnit(actionTypeCode, protocol, rightMapper);
        
        if(!flag) 
            flag = hasPermissionToSubmit(actionTypeCode, protocol, rightMapper);
         
        if(!flag) 
            flag = hasPermissionAsCommitteeMember(actionTypeCode, protocol, rightMapper);
        
        if(!flag)
            flag = hasPermissionSpecialCase(actionTypeCode, DEFAULT_ORGANIZATION_UNIT , rightMapper);
        
        return flag;
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#getActionsAllowed(org.kuali.kra.irb.Protocol)
     */
    public List<String> getActionsAllowed(Protocol protocol) {

        List<String> actionList = new ArrayList<String>();
        for (String actionTypeCode : actions) {
            if (canPerformAction(actionTypeCode, protocol) && isAuthorizedtoPerform(actionTypeCode, protocol)) {
                actionList.add(actionTypeCode);
            }
        }
        return actionList;
    }
    
    private boolean hasPermissionLeadUnit(String actionTypeCode, Protocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        DroolsRuleHandler updateHandle = new DroolsRuleHandler(PERMISSIONS_LEADUNIT_FILE);
        updateHandle.executeRules(rightMapper);            
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), protocol.getLeadUnitNumber(), MODIFY_ANY_PROTOCOL) : false;
    }
    
    private boolean hasPermissionToSubmit(String actionTypeCode, Protocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        DroolsRuleHandler updateHandle = new DroolsRuleHandler(PERMISSIONS_SUBMIT_FILE);
        updateHandle.executeRules(rightMapper);
        return rightMapper.isAllowed() ? protocolAuthorizationService.hasPermission(getUserIdentifier(), protocol, rightMapper.getRightId()) : false; 
    }
    
    private boolean hasPermissionAsCommitteeMember(String actionTypeCode, Protocol protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rightMapper.setCommitteeId(protocol.getProtocolSubmission().getCommitteeId());
        rightMapper.setScheduleId(protocol.getProtocolSubmission().getScheduleId());
        DroolsRuleHandler updateHandle = new DroolsRuleHandler(PERMISSIONS_COMMITTEEMEMBERS_FILE);
        updateHandle.executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), protocol.getLeadUnitNumber(), PERFORM_IRB_ACTIONS_ON_PROTO) : false;
    }
    
    private boolean hasPermissionSpecialCase(String actionTypeCode, String unit, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        DroolsRuleHandler updateHandle = new DroolsRuleHandler(PERMISSIONS_SPECIAL_FILE);
        updateHandle.executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), unit, PERFORM_IRB_ACTIONS_ON_PROTO) : false;
    }
    
    private String getUserIdentifier() {
        return new UniversalUser(GlobalVariables.getUserSession().getPerson()).getPersonUserIdentifier();
    }
    
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
        //LOG.info(actionTypeCode+" before check rule ");
        //DroolsRuleHandler updateHandle = new DroolsRuleHandler(PERFORMACTION_FILE);
        //LOG.info(actionTypeCode+" rule compiled ");
        getCanPerformRuleHandler().executeRules(protocolAction);
        //LOG.info(actionTypeCode+" after check rule ");
        return protocolAction.isAllowed();
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#updateProtocolStatus(org.kuali.kra.irb.actions.ProtocolAction,
     *      org.kuali.kra.irb.Protocol)
     */
    public void updateProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol) {
        runUpdateProtocolRules(protocolActionBo, protocol);
        //new ActionLogger().log(protocolActionBo, protocol, businessObjectService);
    }

    public void runUpdateProtocolRules(ProtocolAction protocolActionBo, Protocol protocol) {

        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();
        String specialCondition = (protocolNumberUpper.contains(AMEND) ? AMEND : (protocolNumberUpper.contains(RENEW) ? RENEW : NONE));

        ProtocolActionUpdateMapping protocolAction = new ProtocolActionUpdateMapping(protocolActionBo.getProtocolActionTypeCode(),
            protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode(), protocol.getProtocolStatusCode(),
            specialCondition);
        protocolAction.setProtocol(protocol);
        protocolAction.setProtocolSubmissionStatus(protocol.getProtocolSubmission().getSubmissionStatus());

        DroolsRuleHandler updateHandle = new DroolsRuleHandler(UPDATE_FILE);
        updateHandle.executeRules(protocolAction);
        businessObjectService.save(protocol);
    }

    public DroolsRuleHandler getCanPerformRuleHandler() {
        // compiling is slow for this rule, so try to just compile once 
        if (canPerformRuleHandler == null) {
            //LOG.info(" before check rule ");
            canPerformRuleHandler = new DroolsRuleHandler(PERFORMACTION_FILE);
            //LOG.info(" rule compiled ");
        }
        return canPerformRuleHandler;
    }

}
