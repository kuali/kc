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
package org.kuali.kra.irb.actions.request;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
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
public class ProtocolRequestServiceImpl implements ProtocolRequestService {
    
    private static final Log LOG = LogFactory.getLog(ProtocolRequestServiceImpl.class);
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
        if (!CollectionUtils.isEmpty(requestBean.getQuestionnaireHelper().getAnswerHeaders())) {
            saveQuestionnaire(requestBean, submission.getSubmissionNumber());
            requestBean.getQuestionnaireHelper().setAnswerHeaders(new ArrayList<AnswerHeader>());
        }
        cleanUnreferencedQuestionnaire(protocol.getProtocolNumber());
        documentService.saveDocument(protocol.getProtocolDocument());
    }
    
    private void saveQuestionnaire(ProtocolRequestBean requestBean, Integer submissionNumber) {
        List<AnswerHeader> saveHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : requestBean.getQuestionnaireHelper().getAnswerHeaders()) {
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
     * Once, one of them is submitted, then the others should be removed.
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
    protected ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolRequestBean requestBean) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, requestBean.getSubmissionTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.PENDING);
        submissionBuilder.setCommittee(requestBean.getCommitteeId());
        submissionBuilder.setActionAttachments(requestBean.getActionAttachments());
        return submissionBuilder.create();
    }


    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }


}
