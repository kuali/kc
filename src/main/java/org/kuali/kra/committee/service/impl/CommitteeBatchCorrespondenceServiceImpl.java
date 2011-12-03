/*
 * Copyright 2006-2010 The Kuali Foundation
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
package org.kuali.kra.committee.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondenceDetail;
import org.kuali.kra.committee.print.CommitteeReportType;
import org.kuali.kra.committee.service.CommitteeBatchCorrespondenceService;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.notification.BatchCorrespondenceNotificationRenderer;
import org.kuali.kra.irb.correspondence.BatchCorrespondence;
import org.kuali.kra.irb.correspondence.BatchCorrespondenceDetail;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.service.KcEmailService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.util.EmailAttachment;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.DateUtils;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This class generates the batch correspondence of committees.
 */
public class CommitteeBatchCorrespondenceServiceImpl implements CommitteeBatchCorrespondenceService {

    private static final Log LOG = LogFactory.getLog(CommitteeBatchCorrespondenceServiceImpl.class);
    private static final String COMMITTEE_ID = "committeeId";
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String PROTO_CORRESP_TYPE_CODE = "protoCorrespTypeCode";
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";


    private BusinessObjectService businessObjectService;
    private ProtocolDao protocolDao;
    private ProtocolGenericActionService protocolGenericActionService;
    private ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService;
    private DocumentService documentService;
    private KcPersonService kcPersonService;
    private KcNotificationService kcNotificationService;
    private KcEmailService kcEmailService;
    
    private int finalActionCounter;

    /**
     * This method generates the batch correspondence for a committee.
     * @param batchCorrespondenceTypeCode
     * @param startDate
     * @param endDate
     * @return CommitteeBatchCorrespondence
     * @throws Exception 
     */
    public CommitteeBatchCorrespondence generateBatchCorrespondence(String batchCorrespondenceTypeCode, String committeeId, Date startDate, 
            Date endDate) throws Exception {
        BatchCorrespondence batchCorrespondence = null;
        List<Protocol> protocols = null;
        finalActionCounter = 0;

        CommitteeBatchCorrespondence committeeBatchCorrespondence = new CommitteeBatchCorrespondence(batchCorrespondenceTypeCode, 
                committeeId, startDate, endDate);
        
        String protocolActionTypeCode;
        
        if (StringUtils.equals(batchCorrespondenceTypeCode, Constants.PROTOCOL_RENEWAL_REMINDERS)) {
            protocols = protocolDao.getExpiringProtocols(committeeId, startDate, endDate);
            protocolActionTypeCode = Constants.PROTOCOL_ACTION_TYPE_CODE_RENEWAL_REMINDER_GENERATED;
        } else if (StringUtils.equals(batchCorrespondenceTypeCode, Constants.REMINDER_TO_IRB_NOTIFICATIONS)) {
            protocols = protocolDao.getIrbNotifiedProtocols(committeeId, startDate, endDate);
            protocolActionTypeCode = Constants.PROTOCOL_ACTION_TYPE_CODE_IRB_NOTIFICATION_GENERATED;
        } else {
            throw new IllegalArgumentException(batchCorrespondenceTypeCode);
        }

        batchCorrespondence = lookupBatchCorrespondence(batchCorrespondenceTypeCode);
        
        for (Protocol protocol : protocols) {
            ProtocolCorrespondenceType protocolCorrespondenceType = getProtocolCorrespondenceTypeToGenerate(protocol, batchCorrespondence);

            if (protocolCorrespondenceType != null)  {
                if (protocolCorrespondenceTemplateService.getProtocolCorrespondenceTemplate(committeeId, 
                        protocolCorrespondenceType.getProtoCorrespTypeCode()) == null) {
                    LOG.warn("Correspondence template \"" + protocolCorrespondenceType.getDescription() + "\" is missing.  Correspondence for protocol " 
                            + protocol.getProtocolNumber() + " has not been generated.  Add the missing template and regenerate correspondence.");
                } else {
                    CommitteeBatchCorrespondenceDetail batchCorrespondenceDetail = createBatchCorrespondenceDetail(committeeId, protocol, 
                            protocolCorrespondenceType, committeeBatchCorrespondence.getCommitteeBatchCorrespondenceId(), protocolActionTypeCode);
                    committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().add(batchCorrespondenceDetail);
                    
                    Long detailId = batchCorrespondenceDetail.getCommitteeBatchCorrespondenceDetailId();
                    String description = protocolCorrespondenceType.getDescription();
                    //String userFullName = kcPersonService.getKcPersonByPersonId(GlobalVariables.getUserSession().getPrincipalId()).getFullName();
                    String userFullName = Constants.EMPTY_STRING;
                    BatchCorrespondenceNotificationRenderer renderer 
                        = new BatchCorrespondenceNotificationRenderer(protocol, detailId, description, userFullName);
                    IRBNotificationContext context 
                        = new IRBNotificationContext(protocol, ProtocolActionType.RENEWAL_REMINDER_GENERATED, "Renewal Reminder Generated", renderer);
                    context.setEmailAttachments(getEmailAttachments(batchCorrespondenceDetail.getProtocolCorrespondence()));
                    kcNotificationService.sendNotification(context);                 
                }
            }
        }

        businessObjectService.save(committeeBatchCorrespondence);
        
        committeeBatchCorrespondence.setFinalActionCounter(finalActionCounter);
        
        return committeeBatchCorrespondence;
    }
    
    /**
     * This method determines if and for which ProtocolCorrespondenceType a batch correspondence needs to be generated.
     * The final action is being applied at this time as well.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @return The ProtocolCorrespondeceType for which correspondence needs to be generated.  
     *         Null if no correspondence needs to be generated.
     * @throws Exception
     */
    protected ProtocolCorrespondenceType getProtocolCorrespondenceTypeToGenerate(Protocol protocol, BatchCorrespondence batchCorrespondence) throws Exception {
        ProtocolCorrespondenceType protocolCorrespondenceType = null;

        if (StringUtils.equals(batchCorrespondence.getSendCorrespondence(), BatchCorrespondence.SEND_CORRESPONDENCE_BEFORE_EVENT)) {
            protocolCorrespondenceType = getBeforeProtocolCorrespondenceTypeToGenerate(protocol, batchCorrespondence);
        } else {
            protocolCorrespondenceType = getAfterProtocolCorrespondenceTypeToGenerate(protocol, batchCorrespondence);
        }
        
        if ((protocolCorrespondenceType != null) && (correspondencePreviouslyGenerated(protocol, protocolCorrespondenceType))) {
            return null;
        } else {
            return protocolCorrespondenceType;
        }
    }
    
    /**
     * This method assists with determining what ProtocolCorrespondenceType is applicable to be generated at this time for
     * correspondences that are to be send before the event.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @return The ProtocolCorrespondenceType for which correspondence may be generated at this time.  
     *         Null if no correspondence needs to be generated.
     * @throws Exception
     */
    protected ProtocolCorrespondenceType getBeforeProtocolCorrespondenceTypeToGenerate(Protocol protocol, 
            BatchCorrespondence batchCorrespondence) throws Exception {
        ProtocolCorrespondenceType protocolCorrespondenceType = null;
        
        double diff = DateUtils.getDifferenceInDays(new Timestamp(System.currentTimeMillis()), new Timestamp(protocol.getExpirationDate().getTime()));
        
        for (BatchCorrespondenceDetail batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
            if (batchCorrespondenceDetail.getDaysToEvent() >= diff) { 
                protocolCorrespondenceType = batchCorrespondenceDetail.getProtocolCorrespondenceType();
            }
        }
        
        if (batchCorrespondence.getFinalActionDay() >= diff) {
            protocolCorrespondenceType = batchCorrespondence.getProtocolCorrespondenceType();
            applyFinalAction(protocol, batchCorrespondence);
        }

        return protocolCorrespondenceType;
    }

    /**
     * This method assists with determining what ProtocolCorrespondenceType is applicable to be generated at this time for
     * correspondences that are to be send after the event.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @return The ProtocolCorrespondenceType for which correspondence may be generated at this time.  
     *         Null if no correspondence needs to be generated.
     * @throws Exception
     */
    protected ProtocolCorrespondenceType getAfterProtocolCorrespondenceTypeToGenerate(Protocol protocol, 
            BatchCorrespondence batchCorrespondence) throws Exception {
        ProtocolCorrespondenceType protocolCorrespondenceType = null;

        //double diff = DateUtils.getDifferenceInDays(protocol.getLastProtocolAction().getUpdateTimestamp(), new Timestamp(System.currentTimeMillis()));
        double diff = DateUtils.getDifferenceInDays(protocol.getLastProtocolAction().getActionDate(), new Timestamp(System.currentTimeMillis()));

        for (BatchCorrespondenceDetail batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
            if (batchCorrespondenceDetail.getDaysToEvent() <= diff) { 
                protocolCorrespondenceType = batchCorrespondenceDetail.getProtocolCorrespondenceType();
            }
        }

        if (batchCorrespondence.getFinalActionDay() <= diff) {
            protocolCorrespondenceType = batchCorrespondence.getProtocolCorrespondenceType();
            applyFinalAction(protocol, batchCorrespondence);
        }

        return protocolCorrespondenceType;
    }

    /**
     * This method applies the final action to the protocol.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @throws Exception
     */
    protected void applyFinalAction(Protocol protocol, BatchCorrespondence batchCorrespondence) throws Exception {
        ProtocolGenericActionBean actionBean = new ProtocolGenericActionBean(null, Constants.EMPTY_STRING);
        actionBean.setComments("Final action of batch Correspondence: " + batchCorrespondence.getDescription());
        
        if (StringUtils.equals(ProtocolActionType.SUSPENDED, batchCorrespondence.getFinalActionTypeCode())) {
            try {
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            }
            catch (RuntimeException ex) {
                protocol.setProtocolDocument((ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
            }
            protocolGenericActionService.suspend(protocol, actionBean);
            finalActionCounter++;
        }
        
        if (StringUtils.equals(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, batchCorrespondence.getFinalActionTypeCode())) {
            try {
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            }
            catch (RuntimeException ex) {
                protocol.setProtocolDocument((ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
            }
            protocolGenericActionService.close(protocol, actionBean);
            finalActionCounter++;
        }
    }
    
    /**
     * 
     * This method determines if the notification for the protocol has already been generated.
     * @param protocol
     * @param protocolCorrespondenceType
     * @return true if the correspondence has already been generated, false otherwise
     */
    protected boolean correspondencePreviouslyGenerated(Protocol protocol, ProtocolCorrespondenceType protocolCorrespondenceType) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        fieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber().toString());
        fieldValues.put(PROTO_CORRESP_TYPE_CODE, protocolCorrespondenceType.getProtoCorrespTypeCode());
        return !businessObjectService.findMatching(ProtocolCorrespondence.class, fieldValues).isEmpty();
    }

    /**
     * 
     * This method creates the CommitteeBatchCorrespondenceDetail and all associated business objects.  
     * The associated business objects are persisted to the database on creation.
     * @param protocol
     * @param protocolCorrespondenceType
     * @param committeeBatchCorrespondenceId
     * @param protocolActionTypeCode
     * @return the populated CommitteeBatchCorrespondenceDetail
     * @throws PrintingException 
     */
    protected CommitteeBatchCorrespondenceDetail createBatchCorrespondenceDetail(String committeeId, Protocol protocol, 
            ProtocolCorrespondenceType protocolCorrespondenceType, String committeeBatchCorrespondenceId, 
            String protocolActionTypeCode) throws PrintingException {
        CommitteeBatchCorrespondenceDetail committeeBatchCorrespondenceDetail = new CommitteeBatchCorrespondenceDetail();
        
        committeeBatchCorrespondenceDetail.setCommitteeBatchCorrespondenceId(committeeBatchCorrespondenceId);
        
        committeeBatchCorrespondenceDetail.setProtocolAction(createAndSaveProtocolAction(protocol, 
                protocolCorrespondenceType, protocolActionTypeCode));
        committeeBatchCorrespondenceDetail.setProtocolActionId(committeeBatchCorrespondenceDetail.getProtocolAction().getProtocolActionId());

        committeeBatchCorrespondenceDetail.setProtocolCorrespondence(createAndSaveProtocolCorrespondence(committeeId,
                protocol, protocolCorrespondenceType, committeeBatchCorrespondenceDetail.getProtocolAction()));
        committeeBatchCorrespondenceDetail.setProtocolCorrespondenceId(committeeBatchCorrespondenceDetail.getProtocolCorrespondence().getId());
        committeeBatchCorrespondenceDetail.setCommitteeBatchCorrespondenceDetailId(KraServiceLocator.getService(SequenceAccessorService.class)
                .getNextAvailableSequenceNumber("SEQ_COMMITTEE_ID"));

        return committeeBatchCorrespondenceDetail;
    }

    /**
     * 
     * This method creates the ProtocolAction business object and persists it to the database.
     * @param protocol
     * @param protocolCorrespondenceType
     * @param protocolActionTypeCode
     * @return the populated ProtocolAction
     */
    protected ProtocolAction createAndSaveProtocolAction(Protocol protocol, ProtocolCorrespondenceType protocolCorrespondenceType, 
            String protocolActionTypeCode) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, protocolActionTypeCode);
        protocolAction.setComments(protocolCorrespondenceType.getDescription());
        
        businessObjectService.save(protocolAction);
        return protocolAction;
    }

    /**
     * 
     * This method creates the ProtocolCorrespondence business object and persists it to the database.  
     * @param protocol
     * @param protocolCorrespondenceType
     * @param protocolAction
     * @return the populated ProtocolCorrespondence
     * @throws PrintingException 
     */
    protected ProtocolCorrespondence createAndSaveProtocolCorrespondence(String committeeId, Protocol protocol, 
            ProtocolCorrespondenceType protocolCorrespondenceType, ProtocolAction protocolAction) throws PrintingException {
        ProtocolCorrespondence protocolCorrespondence = new ProtocolCorrespondence();
        
        protocolCorrespondence.setProtocolId(protocol.getProtocolId());
        protocolCorrespondence.setProtocolNumber(protocol.getProtocolNumber());
        protocolCorrespondence.setSequenceNumber(protocol.getSequenceNumber());
        protocolCorrespondence.setActionIdFk(protocolAction.getProtocolActionId());
        protocolCorrespondence.setActionId(protocolAction.getActionId());
        protocolCorrespondence.setProtoCorrespTypeCode(protocolCorrespondenceType.getProtoCorrespTypeCode());
        
        AbstractPrint printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.PROTOCOL_BATCH_CORRESPONDENCE);
        printable.setPrintableBusinessObject(protocol);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(COMMITTEE_ID, committeeId);
        reportParameters.put("submissionNumber", protocolAction.getSubmissionNumber());
        reportParameters.put(PROTO_CORRESP_TYPE_CODE, protocolCorrespondenceType.getProtoCorrespTypeCode());
        printable.setReportParameters(reportParameters);
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        printableArtifactList.add(printable);
        protocolCorrespondence.setCorrespondence(getCommitteePrintingService().print(printableArtifactList).getContent());

        protocolCorrespondence.setFinalFlag(false);
        
        protocolCorrespondence.setProtocol(protocol);
        protocolCorrespondence.setProtocolCorrespondenceType(protocolCorrespondenceType);
        
        businessObjectService.save(protocolCorrespondence);
        return protocolCorrespondence;
    }
    
    private List<EmailAttachment> getEmailAttachments(ProtocolCorrespondence protocolCorrespondence) {
        List<EmailAttachment> attachments = null;
        
        try {
            byte[] attachmentContents = protocolCorrespondence.getCorrespondence();
            if (attachmentContents != null) {
                    attachments = new ArrayList<EmailAttachment>();
                    String attachmentName = "correspondence_" + protocolCorrespondence.getProtocolNumber() + ".pdf";
                    
                    EmailAttachment attachment = new EmailAttachment();
                    attachment.setFileName(attachmentName);
                    attachment.setMimeType("application/pdf");
                    attachment.setContents(attachmentContents);
                    attachments.add(attachment);         
            }
        } catch (Exception e) {
            LOG.error("Failed to get email attachments for batch correspondence.", e);
        }
        
        return attachments;
    }
    
    /**
     * 
     * This method looks up the BatchCorrespondence business object via the batchCorrespondenceTypeCode.
     * @param batchCorrespondenceTypeCode
     * @return the BatchCorrespondence business object
     */
    protected BatchCorrespondence lookupBatchCorrespondence(String batchCorrespondenceTypeCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceTypeCode);
        return (BatchCorrespondence) businessObjectService.findByPrimaryKey(BatchCorrespondence.class, fieldValues);
    }
    
    protected CommitteePrintingService getCommitteePrintingService() {
        return KraServiceLocator.getService(CommitteePrintingService.class);
    }

    /**
     * Populated by Spring Beans.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Populated by Spring Beans.
     * @param protocolDao
     */
    public void setProtocolDao(ProtocolDao protocolDao) {
        this.protocolDao = protocolDao;
    }
    
    /**
     * Populated by Spring Beans.
     * @param protocolGenericActionService
     */
    public void setProtocolGenericActionService(ProtocolGenericActionService protocolGenericActionService) {
        this.protocolGenericActionService = protocolGenericActionService;
    }

    /**
     * Populated by Spring Beans.
     * @param protocolCorrespondenceTemplateService
     */
    public void setProtocolCorrespondenceTemplateService(ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService) {
        this.protocolCorrespondenceTemplateService = protocolCorrespondenceTemplateService;
    }

    /**
     * Populated by Spring Beans.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Populated by Spring Beans.
     * @param kcPersonService
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    /**
     * Populated by Spring Beans.
     * @param kcNotificationService
     */
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }
}
