/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.logging.Log;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionDoc;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ActionRightMapping;
import org.kuali.kra.protocol.actions.submit.ProtocolActionMappingBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionServiceImplBase;
import org.kuali.kra.questionnaire.answer.AnswerHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * This class is to provide the 'protocol' action pre validation and post update.
 * pre-validation include canperform and authorization check.
 * post-update will update protocol status or submission status.
 */
public class ProtocolActionServiceImpl extends ProtocolActionServiceImplBase implements ProtocolActionService {

    
    static private final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolActionServiceImpl.class);

    
    private static final int PERMISSIONS_LEADUNIT_RULE = 0;
    private static final int PERMISSIONS_SUBMIT_RULE = 1;
    private static final int PERMISSIONS_COMMITTEEMEMBERS_RULE = 2;
    private static final int PERMISSIONS_SPECIAL_RULE = 3;
    private static final int PERFORMACTION_RULE = 4;
    private static final int UPDATE_RULE = 5;
    private static final int UNDO_UPDATE_RULE = 6;
    
    private static final String PERFORMACTION_FILE = "org/kuali/kra/irb/drools/rules/canPerformProtocolActionRules.drl";

    private static final String KC_PROTOCOL = "KC-PROTOCOL";

    private static final String[] actionCodes = { 
        ProtocolActionType.SUBMIT_TO_IRB,
        ProtocolActionType.RENEWAL_CREATED,
        ProtocolActionType.AMENDMENT_CREATED,
        ProtocolActionType.REQUEST_FOR_TERMINATION,
        ProtocolActionType.REQUEST_TO_CLOSE,
        ProtocolActionType.REQUEST_FOR_SUSPENSION,
        ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT,
        ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY,
        ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT,
        ProtocolActionType.NOTIFY_IRB,
        ProtocolActionType.ASSIGN_TO_AGENDA,
        ProtocolActionType.DEFERRED,
        ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED,
        ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED,
        ProtocolActionType.APPROVED,
        ProtocolActionType.EXPEDITE_APPROVAL,
        ProtocolActionType.GRANT_EXEMPTION,
        ProtocolActionType.CLOSED_FOR_ENROLLMENT,
        ProtocolActionType.RESPONSE_APPROVAL,
        ProtocolActionType.IRB_ACKNOWLEDGEMENT,
        ProtocolActionType.IRB_REVIEW_NOT_REQUIRED,
        ProtocolActionType.DATA_ANALYSIS_ONLY,
        ProtocolActionType.REOPEN_ENROLLMENT,
        ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED,
        ProtocolActionType.TERMINATED,
        ProtocolActionType.SUSPENDED,
        ProtocolActionType.WITHDRAWN,
        ProtocolActionType.DISAPPROVED,
        ProtocolActionType.EXPIRED,
        ProtocolActionType.SUSPENDED_BY_DSMB };
    
    
    public String getPerformActionFileNameHook() {     
        return PERFORMACTION_FILE;
    }

    /*
     * This method is to check if user has permission in lead unit
     */
    protected boolean hasPermissionLeadUnit(String actionTypeCode, ProtocolBase protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_LEADUNIT_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? unitAuthorizationService.hasPermission(getUserIdentifier(), protocol.getLeadUnitNumber(),
                KC_PROTOCOL, PermissionConstants.MODIFY_ANY_PROTOCOL) : false;
    }

    /**
     * This method is to check if user has permission to submit
     */
    protected boolean hasPermissionToSubmit(String actionTypeCode, ProtocolBase protocol, ActionRightMapping rightMapper) {
        rightMapper.setActionTypeCode(actionTypeCode);
        rulesList.get(PERMISSIONS_SUBMIT_RULE).executeRules(rightMapper);
        return rightMapper.isAllowed() ? kraAuthorizationService.hasPermission(getUserIdentifier(), protocol, rightMapper
                .getRightId()) : false;
    }
    
    /**
     * @see org.kuali.kra.protocol.actions.submit.ProtocolActionServiceImplBase#resetProtocolStatus(org.kuali.kra.protocol.actions.ProtocolActionBase, org.kuali.kra.protocol.ProtocolBase)
     */
    public void resetProtocolStatus(ProtocolActionBase protocolActionBo, ProtocolBase protocol) {
        ProtocolUndoActionMapping protocolAction = new ProtocolUndoActionMapping(protocolActionBo.getProtocolActionTypeCode(), 
                protocolActionBo.getSubmissionTypeCode(), protocol.getProtocolStatusCode());
          Protocol irbProtocol = (Protocol)protocol;
          protocolAction.setProtocol(irbProtocol);
          protocolAction.setProtocolSubmission((ProtocolSubmission) irbProtocol.getProtocolSubmission());
          protocolAction.setProtocolAction((ProtocolAction)protocolActionBo);
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
    private void removeQuestionnaireAnswer(ProtocolActionBase protocolActionBo, ProtocolBase protocol) {
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

    @Override
    protected Log getLOGHook() {
        return LOG;
    }

    @Override
    protected int getPerformActionRuleIndexHook() {
        return PERFORMACTION_RULE;
    }

    @Override
    protected ProtocolActionMappingBase getNewProtocolActionMappingInstanceHook(String actionTypeCode, String submissionStatusCode,
            String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId,
            Integer submissionNumber) {
        return new ProtocolActionMapping(actionTypeCode, submissionStatusCode, submissionTypeCode, protocolReviewTypeCode, protocolStatusCode, scheduleId, submissionNumber);
    }

    @Override
    protected ProtocolActionUpdateMapping getNewProtocolActionUpdateMappingHook(String actionTypeCode,
            String submissionTypeCode, String protocolStatusCode, String specialCondition) {
        return new ProtocolActionUpdateMapping(actionTypeCode, submissionTypeCode, protocolStatusCode, specialCondition);
    }

    @Override
    protected int getUpdateRuleIndexHook() {
        return UPDATE_RULE;
    }

    @Override
    protected String[] getActionCodesArrayHook() {
        return this.actionCodes;
    }
}