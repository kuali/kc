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

import org.kuali.kra.drools.util.DroolsRuleHandler;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.rice.kns.service.BusinessObjectService;


class ProtocolActionServiceImpl {

    private BusinessObjectService businessObjectService;
    
    public void updateProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol) { 
        runBusinessRules(protocolActionBo, protocol);
        logAction(protocolActionBo, protocol);
    }
    
    public void runBusinessRules(ProtocolAction protocolActionBo, Protocol protocol) {
        
        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();        
        String specialCondition = (protocolNumberUpper.contains("A")? "A" : (protocolNumberUpper.contains("R")? "R" : "NONE"));
        
        ProtocolActionUpdateMapping protocolAction = new ProtocolActionUpdateMapping(protocolActionBo.getProtocolActionTypeCode(), protocol
                .getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode(), protocol.getProtocolStatusCode(), specialCondition);
        protocolAction.setProtocol(protocol);
        protocolAction.setProtocolSubmissionStatus(protocol.getProtocolSubmission().getSubmissionStatus());

        DroolsRuleHandler updateHandle = new DroolsRuleHandler("org/kuali/kra/irb/drools/rules/updateProtocolRules.drl");
        updateHandle.executeRules(protocolAction);
        //TODO transaction
        businessObjectService.save(protocol);   
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void logAction(ProtocolAction protocolActionBo, Protocol protocol) {

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
    public Integer getSequenceNumber(Protocol protocol){
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
    public Integer getActionId(Protocol protocol) {
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
    
    public Timestamp getActionDate(ProtocolAction protocolActionBo) {
        Timestamp date = new Timestamp(new java.util.Date().getTime());
        if (null != protocolActionBo.getActionDate())
            date = protocolActionBo.getActionDate();
        return date;
    }
    
    @SuppressWarnings("unchecked")
    public String getActionComment(ProtocolAction protocolActionBo, Protocol protocol) {
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
