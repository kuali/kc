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
package org.kuali.kra.iacuc.committee.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceDetailBase;
import org.kuali.coeus.common.committee.impl.print.service.CommitteePrintingServiceBase;
import org.kuali.coeus.common.committee.impl.service.impl.CommitteeBatchCorrespondenceServiceImplBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeBatchCorrespondence;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeBatchCorrespondenceDetail;
import org.kuali.kra.iacuc.committee.print.service.IacucCommitteePrintingService;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeBatchCorrespondenceService;
import org.kuali.kra.iacuc.correspondence.IacucBatchCorrespondence;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence;
import org.kuali.kra.iacuc.notification.IacucBatchCorrespondenceNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.correspondence.BatchCorrespondenceBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTypeBase;

import java.sql.Date;
import java.util.List;


public class IacucCommitteeBatchCorrespondenceServiceImpl extends CommitteeBatchCorrespondenceServiceImplBase implements IacucCommitteeBatchCorrespondenceService {

    @Override
      /**
      * This method generates the batch correspondence for a committee.
      * @param batchCorrespondenceTypeCode
      * @param startDate
      * @param endDate
      * @return CommitteeBatchCorrespondenceBase
      * @throws Exception 
      */
     public CommitteeBatchCorrespondenceBase generateBatchCorrespondence(String batchCorrespondenceTypeCode, String committeeId, Date startDate, 
             Date endDate) throws Exception {
         BatchCorrespondenceBase batchCorrespondence = null;
         List<? extends ProtocolBase> protocols = null;
         finalActionCounter = 0;
    
         CommitteeBatchCorrespondenceBase committeeBatchCorrespondence = new IacucCommitteeBatchCorrespondence(batchCorrespondenceTypeCode, 
                 committeeId, startDate, endDate);
         
         String protocolActionTypeCode;
         
         if (StringUtils.equals(batchCorrespondenceTypeCode, Constants.PROTOCOL_RENEWAL_REMINDERS)) {
             protocols = protocolDao.getExpiringProtocols(committeeId, startDate, endDate);
             protocolActionTypeCode = Constants.IACUC_PROTOCOL_ACTION_TYPE_CODE_RENEWAL_REMINDER_GENERATED;
         } else if (StringUtils.equals(batchCorrespondenceTypeCode, Constants.REMINDER_TO_IACUC_NOTIFICATIONS)) {
             protocols = protocolDao.getNotifiedProtocols(committeeId, startDate, endDate);
             protocolActionTypeCode = Constants.IACUC_PROTOCOL_ACTION_TYPE_CODE_IACUC_REMINDER_GENERATED;
         } else {
             throw new IllegalArgumentException(batchCorrespondenceTypeCode);
         }
    
         batchCorrespondence = lookupBatchCorrespondence(batchCorrespondenceTypeCode);
         
         for (ProtocolBase protocol : protocols) {
             ProtocolCorrespondenceTypeBase protocolCorrespondenceType = getProtocolCorrespondenceTypeToGenerate(protocol, batchCorrespondence);
    
             if (protocolCorrespondenceType != null)  {
                 if (protocolCorrespondenceTemplateService.getProtocolCorrespondenceTemplate(committeeId, 
                         protocolCorrespondenceType.getProtoCorrespTypeCode()) == null) {
                     LOG.warn("Correspondence template \"" + protocolCorrespondenceType.getDescription() + "\" is missing.  Correspondence for protocol " 
                             + protocol.getProtocolNumber() + " has not been generated.  Add the missing template and regenerate correspondence.");
                 } else {
                     CommitteeBatchCorrespondenceDetailBase batchCorrespondenceDetail = createBatchCorrespondenceDetail(committeeId, protocol, 
                             protocolCorrespondenceType, committeeBatchCorrespondence.getCommitteeBatchCorrespondenceId(), protocolActionTypeCode);
                     committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().add(batchCorrespondenceDetail);
                     
                     Long detailId = batchCorrespondenceDetail.getCommitteeBatchCorrespondenceDetailId();
                     String description = protocolCorrespondenceType.getDescription();
                     String userFullName = Constants.EMPTY_STRING;
                 
                     IacucBatchCorrespondenceNotificationRenderer renderer 
                         = new IacucBatchCorrespondenceNotificationRenderer((IacucProtocol) protocol, detailId, description, userFullName);
                     IacucProtocolNotificationContext context 
                         = new IacucProtocolNotificationContext((IacucProtocol) protocol, IacucProtocolActionType.RENEWAL_REMINDER_GENERATED, "Renewal Reminder Generated", renderer);
                     context.setEmailAttachments(getEmailAttachments(batchCorrespondenceDetail.getProtocolCorrespondence()));
                     kcNotificationService.sendNotification(context);
                     
                     
                 }
             }
         }
    
         businessObjectService.save(committeeBatchCorrespondence);
         
         committeeBatchCorrespondence.setFinalActionCounter(finalActionCounter);
         
         return committeeBatchCorrespondence;
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
     
         IacucProtocolGenericActionBean actionBean = new IacucProtocolGenericActionBean(null, Constants.EMPTY_STRING);
         actionBean.setComments("Final action of batch Correspondence: " + batchCorrespondence.getDescription());
         
         if (StringUtils.equals(IacucProtocolActionType.SUSPENDED, batchCorrespondence.getFinalActionTypeCode())) {
             try {
                 protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
             }
             catch (RuntimeException ex) {
                 protocol.setProtocolDocument((IacucProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
             }
             protocolGenericActionService.suspend(protocol, actionBean);
             finalActionCounter++;
         }
         
         if (StringUtils.equals(IacucProtocolActionType.EXPIRED, batchCorrespondence.getFinalActionTypeCode())) {
             try {
                 protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
             }
             catch (RuntimeException ex) {
                 protocol.setProtocolDocument((IacucProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber()));
             }
             protocolGenericActionService.expire(protocol, actionBean);
             finalActionCounter++;
         }
         
     }

    @Override
    protected Class<? extends ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook() {
       return IacucProtocolCorrespondence.class;
    }

    @Override
    protected CommitteeBatchCorrespondenceDetailBase getNewCommitteeBatchCorrespondenceDetailInstanceHook() {
        return new IacucCommitteeBatchCorrespondenceDetail();
    }

    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, Object object, String protocolActionTypeCode) {
        return new IacucProtocolAction((IacucProtocol) protocol, null, protocolActionTypeCode);
    }

    @Override
    protected ProtocolCorrespondence getNewProtocolCorrespondenceInstanceHook() {
        return new IacucProtocolCorrespondence();
    }

    @Override
    protected Class<? extends BatchCorrespondenceBase> getBatchCorrespondenceBOClassHook() {
        return IacucBatchCorrespondence.class;
    }

    @Override
    protected CommitteePrintingServiceBase getCommitteePrintingService() {
        return KcServiceLocator.getService(IacucCommitteePrintingService.class);
    }

}
