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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.kra.drools.util.DroolsRuleHandler;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.rice.kns.service.BusinessObjectService;


public class ProtocolActionServiceImpl implements ProtocolActionService {

    private BusinessObjectService businessObjectService;
    
    String[] actn = { "104", "105", "106", "108", "114", "115", "116", "200", "201", "202", "203", "204", "205", "206", "207",
            "208", "209", "210", "211", "212", "300", "301", "302", "303", "304", "305", "306" };
    
    private List<String> actions = new ArrayList<String>();

    {
        actions = Arrays.asList(actn);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#getActionsAllowed(org.kuali.kra.irb.Protocol)
     */
    public List<String> getActionsAllowed(Protocol protocol) {

        List<String> actionList = new ArrayList<String>();
        for (String actionTypeCode : actions) {
            if (canPerformAction(actionTypeCode, protocol) && isAuthorizedToPerform(actionTypeCode, protocol)) {
                actionList.add(actionTypeCode);
            }
        }
        return Arrays.asList(actn);
    }

    // TODO
    public boolean isAuthorizedToPerform(String actionTypeCode, Protocol protocol) {
        return true;
    }

    public boolean canPerformAction(String actionTypeCode, Protocol protocol) {
        String submissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();
        String submissionTypeCode = protocol.getProtocolSubmission().getSubmissionTypeCode();
        String protocolReviewTypeCode = protocol.getProtocolSubmission().getProtocolReviewTypeCode();
        String protocolStatusCode = protocol.getProtocolStatusCode();
        ProtocolActionMapping protocolAction = new ProtocolActionMapping(actionTypeCode, submissionStatusCode, submissionTypeCode,
            protocolReviewTypeCode, protocolStatusCode);

        DroolsRuleHandler updateHandle = new DroolsRuleHandler("org/kuali/kra/irb/drools/rules/canPerformProtocolActionRules.drl");
        updateHandle.executeRules(protocolAction);
        return protocolAction.isAllowed();
    }

    /**
     * @see org.kuali.kra.irb.actions.submit.ProtocolActionService#updateProtocolStatus(org.kuali.kra.irb.actions.ProtocolAction, org.kuali.kra.irb.Protocol)
     */
    public void updateProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol) {
        runUpdateProtocolRules(protocolActionBo, protocol);
        new ActionLogger().log(protocolActionBo, protocol, businessObjectService);
    }

    public void runUpdateProtocolRules(ProtocolAction protocolActionBo, Protocol protocol) {

        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();
        String specialCondition = (protocolNumberUpper.contains("A") ? "A" : (protocolNumberUpper.contains("R") ? "R" : "NONE"));

        ProtocolActionUpdateMapping protocolAction = new ProtocolActionUpdateMapping(protocolActionBo.getProtocolActionTypeCode(),
            protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode(), protocol.getProtocolStatusCode(),
            specialCondition);
        protocolAction.setProtocol(protocol);
        protocolAction.setProtocolSubmissionStatus(protocol.getProtocolSubmission().getSubmissionStatus());

        DroolsRuleHandler updateHandle = new DroolsRuleHandler("org/kuali/kra/irb/drools/rules/updateProtocolRules.drl");
        updateHandle.executeRules(protocolAction);
        businessObjectService.save(protocol);
    }

}
