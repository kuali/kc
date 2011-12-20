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
package org.kuali.kra.irb.actions.notifyirb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.notification.NotifyIrbNotificationRenderer;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Protocol Request Service Implementation.
 */
public class ProtocolNotifyIrbServiceImpl implements ProtocolNotifyIrbService {
    
    private static final Log LOG = LogFactory.getLog(ProtocolNotifyIrbServiceImpl.class);
    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private KcNotificationService kcNotificationService;

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
//        businessObjectService.save(protocol.getProtocolDocument());
        if (!CollectionUtils.isEmpty(notifyIrbBean.getAnswerHeaders())) {
            saveQuestionnaire(notifyIrbBean, submission.getSubmissionNumber());
            notifyIrbBean.setAnswerHeaders(new ArrayList<AnswerHeader>());
        }
        cleanUnreferencedQuestionnaire(protocol.getProtocolNumber());
        documentService.saveDocument(protocol.getProtocolDocument());
        protocol.refreshReferenceObject("protocolSubmissions");
//        try {
//            NotifyIrbNotificationRenderer renderer = new NotifyIrbNotificationRenderer(protocol, protocolAction.getComments());
//            IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.NOTIFY_IRB, "Notify IRB", renderer);
//            kcNotificationService.sendNotification(context);
//        } catch (Exception e) {
//            LOG.info("Notify Irb Notification exception " + e.getStackTrace());
//        }
    }
    
    private void saveQuestionnaire(ProtocolNotifyIrbBean notifyIrbBean, Integer submissionNumber) {
        List<AnswerHeader> saveHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : notifyIrbBean.getAnswerHeaders()) {
            if (answerHeader.getAnswerHeaderId() != null) {
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
    protected ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolNotifyIrbBean notifyIrbBean) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, ProtocolSubmissionType.NOTIFY_IRB);
        //submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        //submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FYI_TYPE_CODE);
        submissionBuilder.setProtocolReviewTypeCode(notifyIrbBean.getReviewTypeCode());
        submissionBuilder.setSubmissionTypeQualifierCode(notifyIrbBean.getSubmissionQualifierTypeCode());
        submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionBuilder.setCommittee(notifyIrbBean.getCommitteeId());
        submissionBuilder.setComments(notifyIrbBean.getComment());
//        for (ProtocolActionAttachment attachment : notifyIrbBean.getActionAttachments()) {
//            submissionBuilder.addAttachment(attachment.getFile());
//        }
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
    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }
    
}
