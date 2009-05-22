/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class ActionLogger {

    private BusinessObjectService businessObjectService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    void log(ProtocolAction protocolActionBo, Protocol protocol, BusinessObjectService businessObjectService) { 
        setBusinessObjectService(businessObjectService);
        logAction(protocolActionBo, protocol);
    }

    void logAction(ProtocolAction protocolActionBo, Protocol protocol) {

        String comments = getActionComment(protocolActionBo,protocol);
        
        Timestamp date = getActionDate(protocolActionBo);
        
        Integer maxActionId = getActionId(protocol);
        
        Integer maxSequenceNo = getSequenceNumber(protocol);
        
        ProtocolAction newActionBo = new ProtocolAction();
        newActionBo.setProtocolNumber(protocol.getProtocolNumber());
        newActionBo.setSequenceNumber(maxSequenceNo);
        newActionBo.setActionId(maxActionId);
        newActionBo.setProtocolActionTypeCode(protocolActionBo.getProtocolActionTypeCode());
        newActionBo.setComments(comments.toString());
        newActionBo.setSubmissionNumber(protocolActionBo.getSubmissionNumber());
        newActionBo.setActionDate(date);
        
        businessObjectService.save(newActionBo);
    }
    
    @SuppressWarnings("unchecked")
    Integer getSequenceNumber(Protocol protocol){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocol.getProtocolNumber());
        List<Protocol> protocols = (List<Protocol>)businessObjectService.findMatching(Protocol.class, fieldValues);
        
        Integer maxSequenceNo = 0;
        
        for(Protocol prtcl: protocols) {
            if(prtcl.getSequenceNumber() != null && prtcl.getSequenceNumber() > maxSequenceNo) {
                maxSequenceNo = prtcl.getSequenceNumber();
            }
        }
        if(maxSequenceNo == 0) {
            maxSequenceNo = protocol.getSequenceNumber();
        }
        return maxSequenceNo;
    }
    
    @SuppressWarnings("unchecked")
    Integer getActionId(Protocol protocol) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocol.getProtocolNumber());
        List<ProtocolAction> actions = (List<ProtocolAction>)businessObjectService.findMatching(ProtocolAction.class, fieldValues);
        
        Integer maxActionId = 0;
        
        for(ProtocolAction action: actions) {
            if(action.getActionId() != null && action.getActionId() > maxActionId) {
                maxActionId = action.getActionId();
            }
        }
        return ++maxActionId;
    }
    
    Timestamp getActionDate(ProtocolAction protocolActionBo) {
        Timestamp date = new Timestamp(new java.util.Date().getTime());
        if (null != protocolActionBo.getActionDate())
            date = protocolActionBo.getActionDate();
        return date;
    }
    
    @SuppressWarnings("unchecked")
    String getActionComment(ProtocolAction protocolActionBo, Protocol protocol) {
        List<ProtocolAmendRenewal> protocolAmendRenewals = (List<ProtocolAmendRenewal>)businessObjectService.findAll(ProtocolAmendRenewal.class);
        String actionComments = protocolActionBo.getComments() != null? protocolActionBo.getComments() : "" ;
        StringBuffer comments = new StringBuffer();
        
        Integer max = 0;
        String maxString = null;
        if(protocolActionBo.getProtocolActionTypeCode().equalsIgnoreCase("103")) {
            for(ProtocolAmendRenewal renewal : protocolAmendRenewals) {
                if(renewal.getProtoAmendRenNumber().substring(0,11).equalsIgnoreCase(protocol.getProtocolNumber()+"A")) {
                    Integer tmp = new Integer(renewal.getProtoAmendRenNumber().substring(11,14));
                    if(tmp > max) 
                        max = tmp;                    
                }
            }
            if (max == 0) maxString = "001";
            comments.append("Amendment- ").append(maxString != null ? maxString : max).append(" created");
        }
        else { 
            if(protocolActionBo.getProtocolActionTypeCode().equalsIgnoreCase("102")){        
                for(ProtocolAmendRenewal renewal : protocolAmendRenewals) {
                    if(renewal.getProtoAmendRenNumber().substring(0,11).equalsIgnoreCase(protocol.getProtocolNumber()+"R")) {
                        Integer tmp = new Integer(renewal.getProtoAmendRenNumber().substring(11, 14));
                        if(tmp > max) 
                            max = tmp; 
                    }
                }
                if (max == 0) maxString = "001";
                comments.append("Renewal- ").append(maxString != null ? maxString : max).append(" created");
            } 
            else {
                String nbr = protocol.getProtocolNumber().substring(11, 14);
                if(protocol.getProtocolNumber().substring(10, 11).equalsIgnoreCase("A"))
                        comments.append("Amendment- ").append(nbr).append(actionComments);
                else if(protocol.getProtocolNumber().substring(10, 11).equalsIgnoreCase("R"))
                        comments.append("Renewal- ").append(nbr).append(actionComments);                        
            }
        }
        return comments.toString();
    }
}
