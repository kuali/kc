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
package org.kuali.kra.irb.actions.notifyirb;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Protocol Request Service Implementation.
 */
public class ProtocolNotifyIrbServiceImpl implements ProtocolNotifyIrbService {
    
    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;

    /**
     * Set the business object service.
     * @param businessObjectService the business office service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @throws WorkflowException 
     * @see org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService#submitIrbNotification(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean)
     */
    public void submitIrbNotification(Protocol protocol, ProtocolNotifyIrbBean notifyIrbBean) throws WorkflowException {
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        ProtocolSubmission submission = createProtocolSubmission(protocol, notifyIrbBean);
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.NOTIFY_IRB);
        protocolAction.setComments(notifyIrbBean.getComment());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        if (!CollectionUtils.isEmpty(notifyIrbBean.getQuestionnaireHelper().getAnswerHeaders())) {
            saveQuestionnaire(notifyIrbBean, submission.getSubmissionNumber());
            notifyIrbBean.getQuestionnaireHelper().setAnswerHeaders(new ArrayList<AnswerHeader>());
        }
        cleanUnreferencedQuestionnaire(protocol.getProtocolNumber());
        documentService.saveDocument(protocol.getProtocolDocument());
        protocol.refreshReferenceObject("protocolSubmissions");
    }
    
    private void saveQuestionnaire(ProtocolNotifyIrbBean notifyIrbBean, Integer submissionNumber) {
        List<AnswerHeader> saveHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : notifyIrbBean.getQuestionnaireHelper().getAnswerHeaders()) {
            if (answerHeader.getId() != null) {
                answerHeader.setModuleSubItemKey(submissionNumber.toString());
                //remove any trailing characters from the protocol number to avoid linking to an amendment or renewal or whatever T means.
                if (answerHeader.getModuleItemKey().matches(".*[A-Z]$")) {
                    answerHeader.setModuleItemKey(answerHeader.getModuleItemKey().substring(0,
                            answerHeader.getModuleItemKey().length() - 1));
                }
                saveHeaders.add(answerHeader);
            }
        }
        if (!saveHeaders.isEmpty()) {
            businessObjectService.save(saveHeaders);
        }
    }

    /*
     * This is a clean up work.  If user enters submission questionnaire for several different request submit action.
     * Once, one of them is submitted, then the others whould be removed.
     */
    private void cleanUnreferencedQuestionnaire(String protocolNumber) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_ITEM_CODE, CoeusModule.IRB_MODULE_CODE);
        fieldValues.put(MODULE_ITEM_KEY, protocolNumber + "T");

        List<AnswerHeader> answerHeaders = (List<AnswerHeader>) businessObjectService.findMatching(AnswerHeader.class, fieldValues);
        if (CollectionUtils.isNotEmpty(answerHeaders)) {
            businessObjectService.delete(answerHeaders);
        }
    }
    
    /**
     * Create a Protocol Submission.
     * @param protocol the protocol
     * @param requestBean the request data
     * @return a protocol submission
     */
    protected ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolNotifyIrbBean notifyIrbBean) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, ProtocolSubmissionType.NOTIFY_IRB);
        submissionBuilder.setProtocolReviewTypeCode(notifyIrbBean.getReviewTypeCode());
        submissionBuilder.setSubmissionTypeQualifierCode(notifyIrbBean.getSubmissionQualifierTypeCode());
        submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionBuilder.setCommittee(notifyIrbBean.getCommitteeId());
        submissionBuilder.setComments(notifyIrbBean.getComment());
        submissionBuilder.setActionAttachments(notifyIrbBean.getActionAttachments());
        ProtocolSubmission submission = submissionBuilder.create();
        // schedule id is set to null
        submission.setScheduleId(null);
        submission.setScheduleIdFk(null);
        return submission;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
}
