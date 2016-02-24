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
package org.kuali.kra.committee.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceDetailBase;
import org.kuali.coeus.common.committee.impl.service.impl.CommitteeBatchCorrespondenceServiceImplBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondenceDetail;
import org.kuali.kra.committee.service.CommitteeBatchCorrespondenceService;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.abandon.ProtocolAbandonService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.notification.BatchCorrespondenceNotificationRenderer;
import org.kuali.kra.irb.correspondence.BatchCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.correspondence.BatchCorrespondenceBase;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class generates the batch correspondence of committees.
 */
public class CommitteeBatchCorrespondenceServiceImpl extends CommitteeBatchCorrespondenceServiceImplBase implements CommitteeBatchCorrespondenceService {

    protected ProtocolAbandonService protocolAbandonService;

    @Override
    /**
     * This method generates the batch correspondence for a committee.
     * @param batchCorrespondenceTypeCode
     * @param startDate
     * @param endDate
     * @return CommitteeBatchCorrespondence
     * @throws Exception 
     */
    public CommitteeBatchCorrespondenceBase generateBatchCorrespondence(String batchCorrespondenceTypeCode, String committeeId, Date startDate, 
            Date endDate) throws Exception {
        BatchCorrespondence batchCorrespondence = null;
        List<? extends ProtocolBase> protocols = null;
        finalActionCounter = 0;

        CommitteeBatchCorrespondence committeeBatchCorrespondence = new CommitteeBatchCorrespondence(batchCorrespondenceTypeCode, 
                committeeId, startDate, endDate);
        
        String protocolActionTypeCode;
        
        if (StringUtils.equals(batchCorrespondenceTypeCode, Constants.PROTOCOL_RENEWAL_REMINDERS)) {
            protocols = protocolDao.getExpiringProtocols(committeeId, startDate, endDate);
            protocolActionTypeCode = Constants.PROTOCOL_ACTION_TYPE_CODE_RENEWAL_REMINDER_GENERATED;
        } else if (StringUtils.equals(batchCorrespondenceTypeCode, Constants.REMINDER_TO_IRB_NOTIFICATIONS)) {
            protocols = protocolDao.getNotifiedProtocols(committeeId, startDate, endDate);
            protocolActionTypeCode = Constants.PROTOCOL_ACTION_TYPE_CODE_IRB_NOTIFICATION_GENERATED;
        } else {
            throw new IllegalArgumentException(batchCorrespondenceTypeCode);
        }

        batchCorrespondence = (BatchCorrespondence) lookupBatchCorrespondence(batchCorrespondenceTypeCode);
        
        for (ProtocolBase protocol : protocols) {
            ProtocolCorrespondenceType protocolCorrespondenceType = (ProtocolCorrespondenceType) getProtocolCorrespondenceTypeToGenerate(protocol, batchCorrespondence);

            if (protocolCorrespondenceType != null)  {
                if (protocolCorrespondenceTemplateService.getProtocolCorrespondenceTemplate(committeeId, 
                        protocolCorrespondenceType.getProtoCorrespTypeCode()) == null) {
                    LOG.warn("Correspondence template \"" + protocolCorrespondenceType.getDescription() + "\" is missing.  Correspondence for protocol " 
                            + protocol.getProtocolNumber() + " has not been generated.  Add the missing template and regenerate correspondence.");
                } 
                else if (!isOutstandingRenewal(protocol.getProtocolNumber(), batchCorrespondence, protocolCorrespondenceType)) {
                    CommitteeBatchCorrespondenceDetail batchCorrespondenceDetail = (CommitteeBatchCorrespondenceDetail) createBatchCorrespondenceDetail(committeeId, protocol, 
                            protocolCorrespondenceType, committeeBatchCorrespondence.getCommitteeBatchCorrespondenceId(), protocolActionTypeCode);
                    committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().add(batchCorrespondenceDetail);
                    
                    Long detailId = batchCorrespondenceDetail.getCommitteeBatchCorrespondenceDetailId();
                    String description = protocolCorrespondenceType.getDescription();
                    String userFullName = Constants.EMPTY_STRING;
                    BatchCorrespondenceNotificationRenderer renderer 
                        = new BatchCorrespondenceNotificationRenderer((Protocol) protocol, detailId, description, userFullName);
                    IRBNotificationContext context 
                        = new IRBNotificationContext((Protocol) protocol, ProtocolActionType.RENEWAL_REMINDER_GENERATED, "Renewal Reminder Generated", renderer);
                    context.setEmailAttachments(getEmailAttachments(batchCorrespondenceDetail.getProtocolCorrespondence()));
                    kcNotificationService.sendNotification(context);
                }
            }
        }

        businessObjectService.save(committeeBatchCorrespondence);
        
        committeeBatchCorrespondence.setFinalActionCounter(finalActionCounter);
        
        return committeeBatchCorrespondence;
    }


    protected boolean isOutstandingRenewal(String protocolNumber, BatchCorrespondence batchCorresp, ProtocolCorrespondenceType correspType) {
        boolean outstandingRenewalFound = false;
        
        if(batchCorresp.getFinalActionCorrespType().equals(correspType.getProtoCorrespTypeCode())) {
            return outstandingRenewalFound;
        }
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolNumber", protocolNumber + "R*");
        fieldValues.put("active", "Y");
        
        Collection <Protocol> matchingProtocols = businessObjectService.findMatching(Protocol.class, fieldValues);
        if  ( (matchingProtocols != null) && (!matchingProtocols.isEmpty()) ) {
            outstandingRenewalFound = true;
        }
         
        return outstandingRenewalFound;
    }


    @Override
    /**
     * This method applies the final action to the protocol.
     * 
     * @param protocol
     * @param batchCorrespondence
     * @throws Exception
     */
    protected void applyFinalAction(ProtocolBase protocol, BatchCorrespondenceBase batchCorrespondence) throws Exception {
        ProtocolGenericActionBean actionBean = new ProtocolGenericActionBean(null, Constants.EMPTY_STRING);
        actionBean.setComments("Final action of batch Correspondence: " + batchCorrespondence.getDescription());
        
        if (StringUtils.equals(ProtocolActionType.SUSPENDED, batchCorrespondence.getFinalActionTypeCode())) {
            try {
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            }
            catch (RuntimeException ex) {
                protocol.setProtocolDocument((ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
            }
            protocolGenericActionService.suspend((Protocol) protocol, actionBean);
            finalActionCounter++;
        }
        
        if (StringUtils.equals(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, batchCorrespondence.getFinalActionTypeCode())) {
            try {
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            }
            catch (RuntimeException ex) {
                protocol.setProtocolDocument((ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
            }
            ((ProtocolGenericActionService) protocolGenericActionService).close((Protocol) protocol, actionBean);
            finalActionCounter++;
        }
        
        if (StringUtils.equals(ProtocolActionType.EXPIRED, batchCorrespondence.getFinalActionTypeCode())) {
            try {
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            }
            catch (RuntimeException ex) {
                protocol.setProtocolDocument((ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
            }
            protocolGenericActionService.expire((Protocol) protocol, actionBean);
            finalActionCounter++;
        }
        
        if (StringUtils.equals(ProtocolActionType.ABANDON_PROTOCOL, batchCorrespondence.getFinalActionTypeCode())) {
            try {
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            }
            catch (RuntimeException ex) {
                protocol.setProtocolDocument((ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
            }
            getProtocolAbandonService().abandonProtocol(protocol, actionBean);
            finalActionCounter++;
        }
    }
    
    protected CommitteePrintingService getCommitteePrintingService() {
        return KcServiceLocator.getService(CommitteePrintingService.class);
    }
    
    /**
     * Populated by Spring Beans.
     * @param protocoAbandonService
     */
    public void setProtocolAbandonService(ProtocolAbandonService protocolAbandonService) {
        this.protocolAbandonService = protocolAbandonService;
    }
 
    private ProtocolAbandonService getProtocolAbandonService() {
        return this.protocolAbandonService;
    }
    
    /**
     * Populated by Spring Beans.
     * @param protocolGenericActionService
     */
    public void setProtocolGenericActionService(ProtocolGenericActionService protocolGenericActionService) {
        this.protocolGenericActionService = protocolGenericActionService;
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.correspondence.ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook() {
        return ProtocolCorrespondence.class;
    }

    @Override
    protected CommitteeBatchCorrespondenceDetailBase getNewCommitteeBatchCorrespondenceDetailInstanceHook() {
        return new CommitteeBatchCorrespondenceDetail();
    }

    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, Object object, String protocolActionTypeCode) {
        return new ProtocolAction((Protocol) protocol, null, protocolActionTypeCode);
    }

    @Override
    protected org.kuali.kra.protocol.correspondence.ProtocolCorrespondence getNewProtocolCorrespondenceInstanceHook() {
        return new ProtocolCorrespondence();
    }

    @Override
    protected Class<? extends BatchCorrespondenceBase> getBatchCorrespondenceBOClassHook() {
        return BatchCorrespondence.class;
    }

}
