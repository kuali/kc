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
package org.kuali.kra.iacuc.actions;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.iacuc.questionnaire.IacucSubmissionQuestionnaireHelper;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.kra.protocol.questionnaire.ProtocolSubmissionQuestionnaireHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class manages all the attributes needed to maintain a protocol action.
 */
public class IacucProtocolAction extends ProtocolActionBase {
    
    private static final String COMMENT_SUBMITTED_TO_IACUC = "SubmittedToIACUC";

    private static final long serialVersionUID = -4895673225969021493L;

    private boolean createdSubmission;
    
    public IacucProtocolAction() {
    }

    public IacucProtocolAction(IacucProtocol protocol, IacucProtocolSubmission protocolSubmission, String protocolActionTypeCode) {
        super(protocol, protocolSubmission, protocolActionTypeCode);
    }
        
    public IacucProtocolAction(IacucProtocol protocol, String protocolActionTypeCode) {
        super(protocol, protocolActionTypeCode);
    }

    public void setQuestionnairePrintOptionFromHelper(IacucActionHelper actionHelper) {
        if (getSubmissionNumber() != null
                && !IacucProtocolActionType.SUBMITTED_TO_IACUC.equals(getProtocolActionTypeCode())) {
            if (getQuestionnaireHelper().getAnswerHeaders().isEmpty()) {
                setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber(),
                                getSubmissionNumber().toString(), CoeusSubModule.PROTOCOL_SUBMISSION));
            }
        } else if (IacucProtocolActionType.SUBMITTED_TO_IACUC.equals(getProtocolActionTypeCode())
                && COMMENT_SUBMITTED_TO_IACUC.equals(getComments())) {
            if (getProtocol().isAmendment() || getProtocol().isRenewal()) {
                setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber(),
                                getSequenceNumber().toString(), CoeusSubModule.AMENDMENT_RENEWAL));

            } else {
                setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber(),
                                getInitialSequence(this, ""), CoeusSubModule.ZERO_SUBMODULE));
            }
        } else if (IacucProtocolActionType.SUBMITTED_TO_IACUC.equals(getProtocolActionTypeCode()) && StringUtils.isNotBlank(getComments())
                                                            && (getComments().startsWith(COMMENT_PREFIX_AMMENDMENT) || getComments().startsWith(COMMENT_PREFIX_RENEWAL) || getComments().startsWith(COMMENT_PREFIX_FYI))) {
            String amendmentRenewalNumber = getAmendmentRenewalNumber(getComments());
            setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber() + amendmentRenewalNumber, 
                                        getInitialSequence(this, amendmentRenewalNumber), CoeusSubModule.AMENDMENT_RENEWAL));
        }
    }
    
    /*
     * get the sequence number of the protocol that the action initially created
     */
    private String getInitialSequence(IacucProtocolAction protocolAction, String amendmentRenewalNumber) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PROTOCOL_NUMBER_FIELD_KEY, protocolAction.getProtocolNumber() + amendmentRenewalNumber);
        if (StringUtils.isBlank(amendmentRenewalNumber)) {
            fieldValues.put(ACTION_ID_FIELD_KEY, protocolAction.getActionId().toString());
        }
        else {
            fieldValues.put(SUBMISSION_NUMBER_FIELD_KEY, protocolAction.getSubmissionNumber().toString());
        }
        fieldValues.put(PROTOCOL_ACTION_TYPE_CODE_FIELD_KEY, IacucProtocolActionType.SUBMITTED_TO_IACUC);
        return ((List<IacucProtocolAction>) getBusinessObjectService().findMatchingOrderBy(IacucProtocolAction.class, fieldValues,
                PROTOCOL_ACTION_ID_FIELD_KEY, true)).get(0).getProtocol().getSequenceNumber().toString();
    }

    protected String getCoeusModule() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    public boolean isCreatedSubmission() {
        return createdSubmission;
    }

    public void setCreatedSubmission(boolean createdSubmission) {
        this.createdSubmission = createdSubmission;
    }
    
    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }

    @Override
    protected ProtocolSubmissionQuestionnaireHelper getProtocolSubmissionQuestionnaireHelperHook(ProtocolBase protocol, String actionTypeCode,
            String submissionNumber) {
        return new IacucSubmissionQuestionnaireHelper(protocol, actionTypeCode, submissionNumber, true);
    }
    
    @Override
    public ProtocolSubmissionQuestionnaireHelper getQuestionnaireHelper() {
        IacucSubmissionQuestionnaireHelper questionnaireHelper = (IacucSubmissionQuestionnaireHelper) super.getQuestionnaireHelper();
        
        if(StringUtils.equals(getProtocolActionTypeCode(),IacucProtocolActionType.SUBMITTED_TO_IACUC)) {
            //add ZERO_SUBMODULE answer headers. These headers are not readily available from super
            ProtocolModuleQuestionnaireBeanBase protocolBaseQnBean = questionnaireHelper.getBaseProtocolModuleQuestionnaireBean(getSequenceNumber() == null ? null : getSequenceNumber().toString());
            List<AnswerHeader> protocolAnswerHeaders = questionnaireHelper.getQuestionnaireAnswerService().getQuestionnaireAnswer(protocolBaseQnBean);
            List<AnswerHeader> submissionAnswerHeaders = questionnaireHelper.getAnswerHeaders();
            submissionAnswerHeaders.addAll(protocolAnswerHeaders);
            questionnaireHelper.setAnswerHeaders(submissionAnswerHeaders);
            questionnaireHelper.resetHeaderLabels();
            setQuestionnaireHelper(questionnaireHelper);
        }
        
        return questionnaireHelper;
    }
    
}
