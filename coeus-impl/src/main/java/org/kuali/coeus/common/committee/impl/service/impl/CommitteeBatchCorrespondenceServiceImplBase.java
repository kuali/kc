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
package org.kuali.coeus.common.committee.impl.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceDetailBase;
import org.kuali.coeus.common.committee.impl.print.CommitteeReportType;
import org.kuali.coeus.common.committee.impl.print.service.CommitteePrintingServiceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeBatchCorrespondenceServiceBase;
import org.kuali.coeus.common.framework.compliance.core.ComplianceConstants;
import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDao;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.protocol.correspondence.*;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class generates the batch correspondence of committees.
 */
public abstract class CommitteeBatchCorrespondenceServiceImplBase implements CommitteeBatchCorrespondenceServiceBase {

    protected static final Log LOG = LogFactory.getLog(CommitteeBatchCorrespondenceServiceImplBase.class);
    private static final String COMMITTEE_ID = "committeeId";
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";


    protected BusinessObjectService businessObjectService;
    protected ProtocolDao<? extends ProtocolBase> protocolDao;
    protected ProtocolGenericActionService protocolGenericActionService;
    protected ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService;
    protected DocumentService documentService;
    protected KcNotificationService kcNotificationService;
    private DateTimeService dateTimeService;

    protected int finalActionCounter;

    
    public abstract CommitteeBatchCorrespondenceBase generateBatchCorrespondence(String batchCorrespondenceTypeCode, String committeeId, Date startDate, Date endDate) throws Exception;

    /**
     * This method determines if and for which ProtocolCorrespondenceTypeBase a batch correspondence needs to be generated.
     * The final action is being applied at this time as well.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @return The ProtocolCorrespondeceType for which correspondence needs to be generated.  
     *         Null if no correspondence needs to be generated.
     * @throws Exception
     */
    protected ProtocolCorrespondenceTypeBase getProtocolCorrespondenceTypeToGenerate(ProtocolBase protocol, BatchCorrespondenceBase batchCorrespondence) throws Exception {
        ProtocolCorrespondenceTypeBase protocolCorrespondenceType = null;

        if (StringUtils.equals(batchCorrespondence.getSendCorrespondence(), BatchCorrespondenceBase.SEND_CORRESPONDENCE_BEFORE_EVENT)) {
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
     * This method assists with determining what ProtocolCorrespondenceTypeBase is applicable to be generated at this time for
     * correspondences that are to be send before the event.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @return The ProtocolCorrespondenceTypeBase for which correspondence may be generated at this time.  
     *         Null if no correspondence needs to be generated.
     * @throws Exception
     */
    protected ProtocolCorrespondenceTypeBase getBeforeProtocolCorrespondenceTypeToGenerate(ProtocolBase protocol, 
            BatchCorrespondenceBase batchCorrespondence) throws Exception {
        ProtocolCorrespondenceTypeBase protocolCorrespondenceType = null;
        
        double diff = DateUtils.getDifferenceInDays(new Timestamp(System.currentTimeMillis()), new Timestamp(protocol.getExpirationDate().getTime()));
        
        for (BatchCorrespondenceDetailBase batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
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
     * This method assists with determining what ProtocolCorrespondenceTypeBase is applicable to be generated at this time for
     * correspondences that are to be send after the event.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @return The ProtocolCorrespondenceTypeBase for which correspondence may be generated at this time.  
     *         Null if no correspondence needs to be generated.
     * @throws Exception
     */
    protected ProtocolCorrespondenceTypeBase getAfterProtocolCorrespondenceTypeToGenerate(ProtocolBase protocol, 
            BatchCorrespondenceBase batchCorrespondence) throws Exception {
        ProtocolCorrespondenceTypeBase protocolCorrespondenceType = null;

        //double diff = DateUtils.getDifferenceInDays(protocol.getLastProtocolAction().getUpdateTimestamp(), new Timestamp(System.currentTimeMillis()));
        double diff = DateUtils.getDifferenceInDays(protocol.getLastProtocolAction().getActionDate(), new Timestamp(System.currentTimeMillis()));

        for (BatchCorrespondenceDetailBase batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
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

    
    
    protected abstract void applyFinalAction(ProtocolBase protocol, BatchCorrespondenceBase batchCorrespondence) throws Exception;

    /**
     * 
     * This method determines if the notification for the protocol has already been generated.
     * @param protocol
     * @param protocolCorrespondenceType
     * @return true if the correspondence has already been generated, false otherwise
     */
    protected boolean correspondencePreviouslyGenerated(ProtocolBase protocol, ProtocolCorrespondenceTypeBase protocolCorrespondenceType) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        fieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber().toString());
        fieldValues.put(ComplianceConstants.PROTO_CORRESP_TYPE_CODE, protocolCorrespondenceType.getProtoCorrespTypeCode());

        return !businessObjectService.findMatching(getProtocolCorrespondenceBOClassHook(), fieldValues).isEmpty();
        
    }

    protected abstract Class<? extends ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook();
    
    
    /**
     * 
     * This method creates the CommitteeBatchCorrespondenceDetailBase and all associated business objects.  
     * The associated business objects are persisted to the database on creation.
     * @param protocol
     * @param protocolCorrespondenceType
     * @param committeeBatchCorrespondenceId
     * @param protocolActionTypeCode
     * @return the populated CommitteeBatchCorrespondenceDetailBase
     * @throws PrintingException 
     */
    protected CommitteeBatchCorrespondenceDetailBase createBatchCorrespondenceDetail(String committeeId, ProtocolBase protocol, 
            ProtocolCorrespondenceTypeBase protocolCorrespondenceType, String committeeBatchCorrespondenceId, 
            String protocolActionTypeCode) throws PrintingException {

        CommitteeBatchCorrespondenceDetailBase committeeBatchCorrespondenceDetail = getNewCommitteeBatchCorrespondenceDetailInstanceHook();
        
        committeeBatchCorrespondenceDetail.setCommitteeBatchCorrespondenceId(committeeBatchCorrespondenceId);
        
        committeeBatchCorrespondenceDetail.setProtocolAction(createAndSaveProtocolAction(protocol, protocolCorrespondenceType, protocolActionTypeCode));
        committeeBatchCorrespondenceDetail.setProtocolActionId(committeeBatchCorrespondenceDetail.getProtocolAction().getProtocolActionId());

        committeeBatchCorrespondenceDetail.setProtocolCorrespondence(createAndSaveProtocolCorrespondence(committeeId,
                protocol, protocolCorrespondenceType, committeeBatchCorrespondenceDetail.getProtocolAction()));
        committeeBatchCorrespondenceDetail.setProtocolCorrespondenceId(committeeBatchCorrespondenceDetail.getProtocolCorrespondence().getId());
        committeeBatchCorrespondenceDetail.setCommitteeBatchCorrespondenceDetailId(KcServiceLocator.getService(SequenceAccessorService.class)
                .getNextAvailableSequenceNumber("SEQ_COMMITTEE_ID", committeeBatchCorrespondenceDetail.getClass()));

        return committeeBatchCorrespondenceDetail;
    }

    protected abstract CommitteeBatchCorrespondenceDetailBase getNewCommitteeBatchCorrespondenceDetailInstanceHook();

    
    
    /**
     * 
     * This method creates the ProtocolActionBase business object and persists it to the database.
     * @param protocol
     * @param protocolCorrespondenceType
     * @param protocolActionTypeCode
     * @return the populated ProtocolActionBase
     */
    protected ProtocolActionBase createAndSaveProtocolAction(ProtocolBase protocol, ProtocolCorrespondenceTypeBase protocolCorrespondenceType, 
            String protocolActionTypeCode) {
        ProtocolActionBase protocolAction = getNewProtocolActionInstanceHook(protocol, null, protocolActionTypeCode);
        protocolAction.setComments(protocolCorrespondenceType.getDescription());
        
        businessObjectService.save(protocolAction);
        return protocolAction;
    }

    protected abstract ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, Object object, String protocolActionTypeCode);

    /**
     * 
     * This method creates the ProtocolCorrespondence business object and persists it to the database.  
     * @param protocol
     * @param protocolCorrespondenceType
     * @param protocolAction
     * @return the populated ProtocolCorrespondence
     * @throws PrintingException 
     */
    protected ProtocolCorrespondence createAndSaveProtocolCorrespondence(String committeeId, ProtocolBase protocol, 
            ProtocolCorrespondenceTypeBase protocolCorrespondenceType, ProtocolActionBase protocolAction) throws PrintingException {

        ProtocolCorrespondence protocolCorrespondence = getNewProtocolCorrespondenceInstanceHook();
        
        protocolCorrespondence.setProtocolId(protocol.getProtocolId());
        protocolCorrespondence.setProtocolNumber(protocol.getProtocolNumber());
        protocolCorrespondence.setSequenceNumber(protocol.getSequenceNumber());
        protocolCorrespondence.setActionIdFk(protocolAction.getProtocolActionId());
        protocolCorrespondence.setActionId(protocolAction.getActionId());
        protocolCorrespondence.setProtoCorrespTypeCode(protocolCorrespondenceType.getProtoCorrespTypeCode());
        
        AbstractPrint printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.PROTOCOL_BATCH_CORRESPONDENCE, committeeId);
        printable.setPrintableBusinessObject(protocol);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(COMMITTEE_ID, committeeId);
        reportParameters.put("submissionNumber", protocolAction.getSubmissionNumber());
        reportParameters.put(ComplianceConstants.PROTO_CORRESP_TYPE_CODE, protocolCorrespondenceType.getProtoCorrespTypeCode());
        printable.setReportParameters(reportParameters);
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        printableArtifactList.add(printable);
        protocolCorrespondence.setCorrespondence(getCommitteePrintingService().print(printableArtifactList).getData());

        protocolCorrespondence.setFinalFlag(false);
        protocolCorrespondence.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        protocolCorrespondence.setCreateTimestamp(dateTimeService.getCurrentTimestamp());
       
        protocolCorrespondence.setProtocol(protocol);
        protocolCorrespondence.setProtocolCorrespondenceType(protocolCorrespondenceType);
        
        businessObjectService.save(protocolCorrespondence);
        return protocolCorrespondence;
    }
    
    protected abstract ProtocolCorrespondence getNewProtocolCorrespondenceInstanceHook();
    

    
    protected List<EmailAttachment> getEmailAttachments(ProtocolCorrespondence protocolCorrespondence) {
        List<EmailAttachment> attachments = null;
        
        try {
            byte[] attachmentContents = protocolCorrespondence.getCorrespondence();
            if (attachmentContents != null) {
                    attachments = new ArrayList<EmailAttachment>();
                    String attachmentName = "correspondence_" + protocolCorrespondence.getProtocolNumber() + Constants.PDF_FILE_EXTENSION;
                    
                    EmailAttachment attachment = new EmailAttachment();
                    attachment.setFileName(attachmentName);
                    attachment.setMimeType(Constants.PDF_REPORT_CONTENT_TYPE);
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
     * This method looks up the BatchCorrespondenceBase business object via the batchCorrespondenceTypeCode.
     * @param batchCorrespondenceTypeCode
     * @return the BatchCorrespondenceBase business object
     */
    protected BatchCorrespondenceBase lookupBatchCorrespondence(String batchCorrespondenceTypeCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceTypeCode);

        return businessObjectService.findByPrimaryKey(getBatchCorrespondenceBOClassHook(), fieldValues);
    }
    
    protected abstract Class<? extends BatchCorrespondenceBase> getBatchCorrespondenceBOClassHook();

    
    protected abstract CommitteePrintingServiceBase getCommitteePrintingService();

    /**
     * Populated by Spring Beans.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
     * @param kcNotificationService
     */
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    
    public ProtocolDao<? extends ProtocolBase> getProtocolDao() {
        return protocolDao;
    }

    public void setProtocolDao(ProtocolDao<? extends ProtocolBase> protocolDao) {
        this.protocolDao = protocolDao;
    }


}
