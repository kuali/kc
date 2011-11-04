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
package org.kuali.kra.irb.actions.request;

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
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Protocol Request Service Implementation.
 */
public class ProtocolRequestServiceImpl implements ProtocolRequestService {
    
    private static final Log LOG = LogFactory.getLog(ProtocolRequestServiceImpl.class);
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
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @throws WorkflowException 
     * @see org.kuali.kra.irb.actions.request.ProtocolRequestService#submitRequest(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.request.ProtocolRequestBean)
     */
    public void submitRequest(Protocol protocol, ProtocolRequestBean requestBean) throws WorkflowException {
        LOG.info("submitRequest "+ requestBean.getProtocolActionTypeCode() + " " +protocol.getProtocolDocument().getDocumentNumber());
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        // if doing request following 'Approve' immediately, it may get OLE issue, which is caused by saving documentheader
        // refresh ProtodolDocument to keep it uptodate
//        protocol.refreshReferenceObject("protocolDocument");
        String prevSubmissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();

        ProtocolSubmission submission = createProtocolSubmission(protocol, requestBean);
        String submissionTypeCode = submission.getSubmissionTypeCode();
        protocol.setProtocolSubmission(submission);
        
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, requestBean.getProtocolActionTypeCode());        
        protocolAction.setComments(requestBean.getReason());
        protocolAction.setProtocol(protocol);
        
        //Creating an audit trail
        protocolAction.setPrevProtocolStatusCode(protocol.getProtocolStatusCode());
        protocolAction.setPrevSubmissionStatusCode(prevSubmissionStatusCode);
        protocolAction.setSubmissionTypeCode(submissionTypeCode);
        
        protocol.getProtocolActions().add(protocolAction);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        if (!CollectionUtils.isEmpty(requestBean.getAnswerHeaders())) {
            saveQuestionnaire(requestBean, submission.getSubmissionNumber());
            requestBean.setAnswerHeaders(new ArrayList<AnswerHeader>());
        }
        cleanUnreferencedQuestionnaire(protocol.getProtocolNumber());
//        businessObjectService.save(protocol.getProtocolDocument());
        documentService.saveDocument(protocol.getProtocolDocument());
        try {
            sendRequestNotification(protocol, requestBean);
        } catch (Exception e) {
            LOG.info("Request notification exception " + e.getStackTrace());
        }
    }
    
    private void saveQuestionnaire(ProtocolRequestBean requestBean, Integer submissionNumber) {
        List<AnswerHeader> saveHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : requestBean.getAnswerHeaders()) {
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
     * Once, one of them is submitted, then the others should be removed.
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
    protected ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolRequestBean requestBean) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, requestBean.getSubmissionTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.PENDING);
        submissionBuilder.setCommittee(requestBean.getCommitteeId());
       // submissionBuilder.addAttachment(requestBean.getFile());
        submissionBuilder.setActionAttachments(requestBean.getActionAttachments());
        return submissionBuilder.create();
    }
    
    /*
     * send Request notification for different event
     */
    protected void sendRequestNotification(Protocol protocol, ProtocolRequestBean requestBean) throws Exception {
        ProtocolActionType protocolActionType = businessObjectService.findBySinglePrimaryKey(ProtocolActionType.class, requestBean.getProtocolActionTypeCode());
        String protocolActionTypeCode = protocolActionType.getProtocolActionTypeCode();
        String description = protocolActionType.getDescription();
        
        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
        IRBNotificationContext context = new IRBNotificationContext(protocol, protocolActionTypeCode, description, renderer);
        kcNotificationService.sendNotification(context);
    }


    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

}
