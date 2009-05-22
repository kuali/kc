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

import java.util.List;

import org.kuali.kra.drools.util.DroolsRuleHandler;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.rice.kns.service.BusinessObjectService;


public class ProtocolActionServiceImpl {

    private BusinessObjectService businessObjectService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public List<String> availableActionTypeCodes(Protocol protocol) {
        
        ProtocolAvailableActionMapping protocolAction = new ProtocolAvailableActionMapping(protocol.getProtocolSubmission().getSubmissionTypeCode());
        
        DroolsRuleHandler updateHandle = new DroolsRuleHandler("org/kuali/kra/irb/drools/rules/availableActionTypeCodeRules.drl");
        updateHandle.executeRules(protocolAction);
        return protocolAction.actionTypeCodes;
    }
    
    public void updateProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol) {
        runUpdateProtocolRules(protocolActionBo, protocol);
        new ActionLogger().log(protocolActionBo, protocol, businessObjectService);
    }
    
    public void runUpdateProtocolRules(ProtocolAction protocolActionBo, Protocol protocol) {
        
        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();        
        String specialCondition = (protocolNumberUpper.contains("A")? "A" : (protocolNumberUpper.contains("R")? "R" : "NONE"));
        
        ProtocolActionUpdateMapping protocolAction = new ProtocolActionUpdateMapping(protocolActionBo.getProtocolActionTypeCode(), protocol
                .getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode(), protocol.getProtocolStatusCode(), specialCondition);
        protocolAction.setProtocol(protocol);
        protocolAction.setProtocolSubmissionStatus(protocol.getProtocolSubmission().getSubmissionStatus());

        DroolsRuleHandler updateHandle = new DroolsRuleHandler("org/kuali/kra/irb/drools/rules/updateProtocolRules.drl");
        updateHandle.executeRules(protocolAction);
        businessObjectService.save(protocol);   
    }

}
