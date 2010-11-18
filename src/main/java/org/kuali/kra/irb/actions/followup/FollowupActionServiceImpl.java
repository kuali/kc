/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.followup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ValidProtocolActionAction;
import org.kuali.rice.kns.service.BusinessObjectService;


public class FollowupActionServiceImpl implements FollowupActionService {
    
    BusinessObjectService businessObjectService;
    private static final String PROTOCOL_ACTION_TYPE_CODE = "protocolActionTypeCode"; 
    
    private static final Log LOG = LogFactory.getLog(FollowupActionServiceImpl.class);
    
    private List<ValidProtocolActionAction> getFollowupsForActionTypeCode(String protocolActionTypeCode) {
        List<ValidProtocolActionAction> resultSet = new ArrayList<ValidProtocolActionAction>();
        if (!StringUtils.isEmpty(protocolActionTypeCode)) {
            Map<String,String> fieldSet = new HashMap<String,String>();
            fieldSet.put(PROTOCOL_ACTION_TYPE_CODE, protocolActionTypeCode);
            resultSet = (List<ValidProtocolActionAction>)businessObjectService.findMatching(ValidProtocolActionAction.class, fieldSet);
        }
        return resultSet;
    }
    
    public boolean isActionOpenForFollowup(String protocolActionTypeCode, Protocol protocol) {
        // TODO Auto-generated method stub
        if (LOG.isDebugEnabled()) {
            LOG.debug("**********************************");
            LOG.debug(String.format("isActionOpenForFollowup called for action type code %s, on protocol %s",protocolActionTypeCode,protocol.getProtocolNumber()));
            LOG.debug(String.format("Last protocol action was %s", protocol.getLastProtocolAction().getProtocolActionTypeCode()));
        }
        boolean result = false;
        List<ValidProtocolActionAction> valids = getFollowupsForActionTypeCode(protocol.getLastProtocolAction().getProtocolActionTypeCode());
        for (ValidProtocolActionAction protocolActionAction : valids) {
            if (StringUtils.equals(protocolActionAction.getFollowupActionCode(),protocolActionTypeCode)) {
                if (!StringUtils.isEmpty(protocolActionAction.getMotionTypeCode())) {
                    //there is an associated motion type code with this action
                    //we need to check the committee's motion on the protocol in
                    //addition
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(String.format("ValidProtocolActionAction %s has defined MotionTypeCode %s, checking motion on protocol."
                                ,protocolActionAction.getValidProtocolActionActionId(),protocolActionAction.getMotionTypeCode()));
                    }
                    if (StringUtils.equals(protocol.getProtocolSubmission().getCommitteeDecisionMotionTypeCode(), protocolActionAction.getMotionTypeCode())) {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("MotionType matches last motion on protocol.");
                        }
                        result = true;
                        break;
                    }
                    
                } else {
                    result = true;
                    break;
                }
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("isActionOpenForFollowup returning %s for type code %s",result,protocolActionTypeCode));
            LOG.debug("**********************************");
        }
        
        return result;
    }

    //Getters and setters and other junk
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
