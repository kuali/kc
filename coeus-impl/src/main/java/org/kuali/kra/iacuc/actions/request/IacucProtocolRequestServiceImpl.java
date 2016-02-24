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
package org.kuali.kra.iacuc.actions.request;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
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
public class IacucProtocolRequestServiceImpl implements IacucProtocolRequestService {
    
    private static final Log LOG = LogFactory.getLog(IacucProtocolRequestServiceImpl.class);
    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private IacucProtocolActionService protocolActionService;
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
    public void setProtocolActionService(IacucProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @throws WorkflowException 
     * @see org.kuali.kra.irb.actions.request.ProtocolRequestService#submitRequest(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.request.ProtocolRequestBean)
     */
    public void submitRequest(IacucProtocol protocol, IacucProtocolRequestBean requestBean) throws WorkflowException {
        LOG.info("submitRequest "+ requestBean.getProtocolActionTypeCode() + " " +protocol.getProtocolDocument().getDocumentNumber());
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        String prevSubmissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();

        IacucProtocolSubmission submission = createProtocolSubmission(protocol, requestBean);
        String submissionTypeCode = submission.getSubmissionTypeCode();
        protocol.setProtocolSubmission(submission);
        
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, submission, requestBean.getProtocolActionTypeCode());        
        protocolAction.setComments(requestBean.getReason());
        protocolAction.setProtocol(protocol);
        
        //Creating an audit trail
        protocolAction.setPrevProtocolStatusCode(protocol.getProtocolStatusCode());
        protocolAction.setPrevSubmissionStatusCode(prevSubmissionStatusCode);
        protocolAction.setSubmissionTypeCode(submissionTypeCode);
        protocolAction.setCreatedSubmission(true);
        
        protocol.getProtocolActions().add(protocolAction);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        if (!CollectionUtils.isEmpty(requestBean.getAnswerHeaders())) {
            saveQuestionnaire(requestBean, submission.getSubmissionNumber());
            requestBean.setAnswerHeaders(new ArrayList<AnswerHeader>());
        }
        cleanUnreferencedQuestionnaire(protocol.getProtocolNumber());
        documentService.saveDocument(protocol.getProtocolDocument());
    }
    
    private void saveQuestionnaire(IacucProtocolRequestBean requestBean, Integer submissionNumber) {
        List<AnswerHeader> saveHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : requestBean.getAnswerHeaders()) {
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
    protected IacucProtocolSubmission createProtocolSubmission(IacucProtocol protocol, IacucProtocolRequestBean requestBean) {
        IacucProtocolSubmissionBuilder submissionBuilder = new IacucProtocolSubmissionBuilder(protocol, requestBean.getSubmissionTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(IacucProtocolReviewType.FULL_COMMITTEE_REVIEW);
        submissionBuilder.setSubmissionStatus(IacucProtocolSubmissionStatus.PENDING);
        submissionBuilder.setCommittee(requestBean.getCommitteeId());
        submissionBuilder.setActionAttachments(requestBean.getActionAttachments());
        return submissionBuilder.create();
    }


    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

    public KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }

}
