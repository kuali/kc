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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondenceDetail;
import org.kuali.kra.committee.print.CommitteeReportType;
import org.kuali.kra.committee.service.CommitteeBatchCorrespondenceService;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.correspondence.BatchCorrespondence;
import org.kuali.kra.irb.correspondence.BatchCorrespondenceDetail;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.rice.kns.service.BusinessObjectService;

public class CommitteeBatchCorrespondenceServiceImpl implements CommitteeBatchCorrespondenceService {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String PROTO_CORRESP_TYPE_CODE = "protoCorrespTypeCode";
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";
    private static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;


    private BusinessObjectService businessObjectService;
    private ProtocolDao protocolDao;

    /**
     * This method generates the batch correspondence for a committee.
     * @param batchCorrespondenceTypeCode
     * @param startDate
     * @param endDate
     * @throws Exception 
     */
    public void generateBatchCorrespondence(String batchCorrespondenceTypeCode, String committeeId, Date startDate, 
            Date endDate) throws Exception {
        BatchCorrespondence batchCorrespondence = null;
        List<Protocol> protocols = null;
        ProtocolCorrespondenceType protocolCorrespondenceType = null;

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
            if (StringUtils.equals(batchCorrespondence.getSendCorrespondence(), "BEFORE")) {
                int diff = (int) ((protocol.getExpirationDate().getTime() - getCurrentDate().getTime()) / MILLISECONDS_PER_DAY);
                for (BatchCorrespondenceDetail batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
                    if (batchCorrespondenceDetail.getDaysToEvent() >= diff) { 
                        protocolCorrespondenceType = batchCorrespondenceDetail.getProtocolCorrespondenceType();
                    }
                }
            } else {
                Date updateDate = new Date(protocol.getLastProtocolAction().getUpdateTimestamp().getTime());
                int diff = (int) ((updateDate.getTime() - getCurrentDate().getTime()) / MILLISECONDS_PER_DAY);
                for (BatchCorrespondenceDetail batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
                    if (batchCorrespondenceDetail.getDaysToEvent() <= diff) { 
                        protocolCorrespondenceType = batchCorrespondenceDetail.getProtocolCorrespondenceType();
                    }
                }
            }

            if (!correspondencePreviouslyGenerated(protocol, protocolCorrespondenceType)) {
                committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().add(createBatchCorrespondenceDetail(committeeId, protocol, 
                        protocolCorrespondenceType, committeeBatchCorrespondence.getCommitteeBatchCorrespondenceId(), protocolActionTypeCode));
            }
        }

        businessObjectService.save(committeeBatchCorrespondence);
    }

    /**
     * 
     * This method determines if the notification for the protocol has already been generated.
     * @param protocol
     * @param protocolCorrespondenceType
     * @return true if the correspondence has already been generated, false otherwise
     */
    private boolean correspondencePreviouslyGenerated(Protocol protocol, ProtocolCorrespondenceType protocolCorrespondenceType) {
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
    private CommitteeBatchCorrespondenceDetail createBatchCorrespondenceDetail(String committeeId, Protocol protocol, 
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
    private ProtocolAction createAndSaveProtocolAction(Protocol protocol, ProtocolCorrespondenceType protocolCorrespondenceType, 
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
    private ProtocolCorrespondence createAndSaveProtocolCorrespondence(String committeeId, Protocol protocol, 
            ProtocolCorrespondenceType protocolCorrespondenceType, ProtocolAction protocolAction) throws PrintingException {
        ProtocolCorrespondence protocolCorrespondence = new ProtocolCorrespondence();
        
        protocolCorrespondence.setProtocolId(protocol.getProtocolId());
        protocolCorrespondence.setProtocolNumber(protocol.getProtocolNumber());
        protocolCorrespondence.setSequenceNumber(protocol.getSequenceNumber());
        protocolCorrespondence.setActionIdFk(protocolAction.getProtocolActionId());
        protocolCorrespondence.setActionId(protocolAction.getActionId());
        protocolCorrespondence.setProtoCorrespTypeCode(protocolCorrespondenceType.getProtoCorrespTypeCode());
        
        AbstractPrint printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.TEMPLATE);
        printable.setDocument(protocol.getProtocolDocument());
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("committeeId", committeeId);
        reportParameters.put("protoCorrespTypeCode", protocolCorrespondenceType.getProtoCorrespTypeCode());
        printable.setReportParameters(reportParameters);
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        printableArtifactList.add(printable);
        protocolCorrespondence.setCorrespondence(getCommitteePrintingService().print(printableArtifactList).getContent());

        protocolCorrespondence.setFinalFlag(false);
        
        businessObjectService.save(protocolCorrespondence);
        return protocolCorrespondence;
    }
    
    /**
     * 
     * This method looks up the BatchCorrespondence business object via the batchCorrespondenceTypeCode.
     * @param batchCorrespondenceTypeCode
     * @return the BatchCorrespondence business object
     */
    private BatchCorrespondence lookupBatchCorrespondence(String batchCorrespondenceTypeCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceTypeCode);
        return (BatchCorrespondence) businessObjectService.findByPrimaryKey(BatchCorrespondence.class, fieldValues);
    }
    
    /**
     * This method returns the current date.
     * @return current date
     */
    private Date getCurrentDate() {
        return new Date(new java.util.Date().getTime());
    }

    private CommitteePrintingService getCommitteePrintingService() {
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
    
}
