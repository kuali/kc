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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondenceDetail;
import org.kuali.kra.committee.service.CommitteeBatchCorrespondenceService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.rice.kns.service.BusinessObjectService;

public class CommitteeBatchCorrespondenceServiceImpl implements CommitteeBatchCorrespondenceService {

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
        CommitteeBatchCorrespondence committeeBatchCorrespondence = new CommitteeBatchCorrespondence(batchCorrespondenceTypeCode, 
                committeeId, startDate, endDate);
        
        List<Protocol> protocols;
        
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
        
        for (Protocol protocol : protocols) {
            committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().add(createBatchCorrespondenceDetail(batchCorrespondenceTypeCode, 
                    protocol, committeeBatchCorrespondence.getCommitteeBatchCorrespondenceId(), protocolActionTypeCode));
          
            System.out.println(">> " + protocol);
        }

        businessObjectService.save(committeeBatchCorrespondence);
    }

    /**
     * 
     * This method creates the CommitteeBatchCorrespondenceDetail and all associated business objects.  
     * The associated business objects are persisted to the database on creation.
     * @param batchCorrespondenceTypeCode
     * @param protocol
     * @param committeeBatchCorrespondenceId
     * @return the populated CommitteeBatchCorrespondenceDetail
     */
    private CommitteeBatchCorrespondenceDetail createBatchCorrespondenceDetail(String batchCorrespondenceTypeCode, 
            Protocol protocol, String committeeBatchCorrespondenceId, String protocolActionTypeCode) {
        String protoCorrespTypeCode = "23"; // TODO: determine value here or earlier
        String protoCorrespTypeDescription = "Reminder to IRB Notification #1"; // TODO: determine value here or earlier

        CommitteeBatchCorrespondenceDetail committeeBatchCorrespondenceDetail = new CommitteeBatchCorrespondenceDetail();
        
        committeeBatchCorrespondenceDetail.setCommitteeBatchCorrespondenceId(committeeBatchCorrespondenceId);
        
        committeeBatchCorrespondenceDetail.setProtocolAction(createAndSaveProtocolAction(batchCorrespondenceTypeCode, protocol, 
                protocolActionTypeCode, protoCorrespTypeDescription));
        committeeBatchCorrespondenceDetail.setProtocolActionId(committeeBatchCorrespondenceDetail.getProtocolAction().getProtocolActionId());

        committeeBatchCorrespondenceDetail.setProtocolCorrespondence(createAndSaveProtocolCorrespondence(batchCorrespondenceTypeCode,  
                protocol, committeeBatchCorrespondenceDetail.getProtocolAction(), protoCorrespTypeCode));
        committeeBatchCorrespondenceDetail.setProtocolCorrespondenceId(committeeBatchCorrespondenceDetail.getProtocolCorrespondence().getId());
        
        return committeeBatchCorrespondenceDetail;
    }

    /**
     * 
     * This method creates the ProtocolAction business object and persists it to the database.
     * @param batchCorrespondenceTypeCode
     * @param protocol
     * @param protoCorrespTypeDescription
     * @return the populated ProtocolAction
     */
    private ProtocolAction createAndSaveProtocolAction(String batchCorrespondenceTypeCode, Protocol protocol, String protocolActionTypeCode, String comments) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, protocolActionTypeCode);
        protocolAction.setComments(comments);
        
        businessObjectService.save(protocolAction);
        return protocolAction;
    }

    /**
     * 
     * This method creates the ProtocolCorrespondence business object and persists it to the database.  
     * @param batchCorrespondenceTypeCode
     * @param protocol
     * @param protocolAction
     * @param protoCorrespTypeCode
     * @return the populated ProtocolCorrespondence
     */
    private ProtocolCorrespondence createAndSaveProtocolCorrespondence(String batchCorrespondenceTypeCode, Protocol protocol, 
            ProtocolAction protocolAction, String protoCorrespTypeCode) {
        ProtocolCorrespondence protocolCorrespondence = new ProtocolCorrespondence();
        
        protocolCorrespondence.setProtocolId(protocol.getProtocolId());
        protocolCorrespondence.setProtocolNumber(protocol.getProtocolNumber());
        protocolCorrespondence.setSequenceNumber(protocol.getSequenceNumber());
        protocolCorrespondence.setActionIdFk(protocolAction.getProtocolActionId());
        protocolCorrespondence.setActionId(protocolAction.getActionId());
        protocolCorrespondence.setProtoCorrespTypeCode(protoCorrespTypeCode);
        byte[] b = {0};
        protocolCorrespondence.setCorrespondence(b); // TODO: to be populated
        protocolCorrespondence.setFinalFlag(false); //TODO: ???
        
        businessObjectService.save(protocolCorrespondence);
        return protocolCorrespondence;
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
