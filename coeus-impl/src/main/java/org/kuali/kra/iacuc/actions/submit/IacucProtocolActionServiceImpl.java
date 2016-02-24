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
package org.kuali.kra.iacuc.actions.submit;

import org.apache.commons.logging.Log;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolSubmissionDoc;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ActionRightMapping;
import org.kuali.kra.protocol.actions.submit.ProtocolActionMappingBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionUpdateMapping;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * This class is to provide the 'protocol' action pre validation and post update.
 * pre-validation include canperform and authorization check.
 * post-update will update protocol status or submission status.
 */
public class IacucProtocolActionServiceImpl extends ProtocolActionServiceImplBase implements IacucProtocolActionService {

    static private final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(IacucProtocolActionServiceImpl.class);

    // for now, only action rule and update rule files are being used. Thus, we override them here.
    protected static final int PERFORMACTION_RULE = 0;

    protected static final int UPDATE_RULE = 1;
    
    protected static final int PERMISSIONS_LEADUNIT_RULE = 2;
    
    protected static final int PERMISSIONS_SUBMIT_RULE = 3;
    
    protected static final int PERMISSIONS_COMMITTEEMEMBERS_RULE = 4;
    
    protected static final int PERMISSIONS_SPECIAL_RULE = 5;
    
    private static final String PERFORMACTION_FILE = "org/kuali/kra/iacuc/drools/rules/canPerformIacucProtocolActionRules.drl";
    
    private static final String KC_IACUC = "KC-IACUC";

    private static final String[] actionCodes = { 
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
            IacucProtocolActionType.REMOVE_FROM_AGENDA,
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
                KC_IACUC, PermissionConstants.MODIFY_ANY_IACUC_PROTOCOL) : false;
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

    public void resetProtocolStatus(ProtocolActionBase protocolActionBo, ProtocolBase protocol) {
        IacucProtocolAction protocolAction = (IacucProtocolAction) protocolActionBo;
        if (protocolAction.getPrevProtocolStatusCode() != null) {
            protocol.setProtocolStatusCode(protocolAction.getPrevProtocolStatusCode());
            protocol.refreshReferenceObject("protocolStatus");
        }
        if (protocolAction.getPrevSubmissionStatusCode() != null) {
            protocol.getProtocolSubmission().setSubmissionStatusCode(protocolAction.getPrevSubmissionStatusCode());
        }
        if (protocolAction.isCreatedSubmission()) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionIdFk", protocolAction.getProtocolSubmission().getSubmissionId().toString());
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            businessObjectService.deleteMatching(IacucProtocolSubmissionDoc.class, fieldValues);
            removeQuestionnaireAnswer(protocolAction, protocol);
            protocol.getProtocolSubmissions().remove(protocolAction.getProtocolSubmission()); 
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
        return new IacucProtocolActionMapping(actionTypeCode, submissionStatusCode, submissionTypeCode, protocolReviewTypeCode, protocolStatusCode, scheduleId, submissionNumber);
    }

    @Override
    protected ProtocolActionUpdateMapping getNewProtocolActionUpdateMappingHook(String actionTypeCode,
            String submissionTypeCode, String protocolStatusCode, String specialCondition) {
        return new IacucProtocolActionUpdateMapping(actionTypeCode, submissionTypeCode, protocolStatusCode, specialCondition);
    }

    @Override
    protected int getUpdateRuleIndexHook() {
        return UPDATE_RULE;
    }

    @Override
    protected String[] getActionCodesArrayHook() {
        return actionCodes;
    }
 
    
    
}
