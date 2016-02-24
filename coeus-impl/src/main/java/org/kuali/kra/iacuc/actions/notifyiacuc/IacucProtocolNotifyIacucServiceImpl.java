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
package org.kuali.kra.iacuc.actions.notifyiacuc;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.*;
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
public class IacucProtocolNotifyIacucServiceImpl implements IacucProtocolNotifyIacucService {
    
    protected static final String MODULE_ITEM_CODE = "moduleItemCode";
    protected static final String MODULE_ITEM_KEY = "moduleItemKey";
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private IacucProtocolActionService protocolActionService;
    /**
     * Set the business object service.
     * @param businessObjectService the business office service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public void setProtocolActionService(IacucProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @throws WorkflowException 
     * @see org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService#submitIrbNotification(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean)
     */
    public void submitIacucNotification(IacucProtocol protocol, IacucProtocolNotifyIacucBean notifyIacucBean) throws WorkflowException {
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        IacucProtocolSubmission submission = createProtocolSubmission(protocol, notifyIacucBean);
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, submission, IacucProtocolActionType.NOTIFY_IACUC);
        protocolAction.setComments(notifyIacucBean.getComment());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        if (!CollectionUtils.isEmpty(notifyIacucBean.getAnswerHeaders())) {
            saveQuestionnaire(notifyIacucBean, submission.getSubmissionNumber());
            notifyIacucBean.setAnswerHeaders(new ArrayList<AnswerHeader>());
        }
        cleanUnreferencedQuestionnaire(protocol.getProtocolNumber());
        documentService.saveDocument(protocol.getProtocolDocument());
        protocol.refreshReferenceObject("protocolSubmissions");
    }
    
    private void saveQuestionnaire(IacucProtocolNotifyIacucBean notifyIacucBean, Integer submissionNumber) {
        List<AnswerHeader> saveHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : notifyIacucBean.getAnswerHeaders()) {
            if (answerHeader.getId() != null) {
                answerHeader.setModuleSubItemKey(submissionNumber.toString());
                answerHeader.setModuleItemKey(answerHeader.getModuleItemKey().substring(0,
                        answerHeader.getModuleItemKey().length() - 1));
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
        // TODO : make this a shared 
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
    protected IacucProtocolSubmission createProtocolSubmission(IacucProtocol protocol, IacucProtocolNotifyIacucBean notifyIacucBean) {
        IacucProtocolSubmissionBuilder submissionBuilder = new IacucProtocolSubmissionBuilder(protocol, IacucProtocolSubmissionType.NOTIFY_IACUC);
        submissionBuilder.setProtocolReviewTypeCode(notifyIacucBean.getReviewTypeCode());
        submissionBuilder.setSubmissionTypeQualifierCode(notifyIacucBean.getSubmissionQualifierTypeCode());
        submissionBuilder.setSubmissionStatus(IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionBuilder.setCommittee(notifyIacucBean.getCommitteeId());
        submissionBuilder.setComments(notifyIacucBean.getComment());
        submissionBuilder.setActionAttachments(notifyIacucBean.getActionAttachments());
        IacucProtocolSubmission submission = submissionBuilder.create();
        // schedule id is set to null
        submission.setScheduleId(null);
        submission.setScheduleIdFk(null);
        return submission;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    protected DocumentService getDocumentService() {
        return documentService;
    }
    protected IacucProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }
    
}
