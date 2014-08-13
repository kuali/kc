/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.actions.followup;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ValidProtocolActionActionBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class FollowupActionServiceImplBase<T extends ValidProtocolActionActionBase> implements FollowupActionService<T> {
    
    BusinessObjectService businessObjectService;
    private static final String PROTOCOL_ACTION_TYPE_CODE = "protocolActionTypeCode"; 
    private static final String COMMITTEE_MOTION_TYPE_CODE = "motionTypeCode";
  
    @Override
    public List<T> getFollowupsForActionTypeAndMotionType(String protocolActionTypeCode, String committeeMotionTypeCode) {
         
        Map<String,String> fieldSet = new HashMap<String,String>();
        if (!StringUtils.isEmpty(protocolActionTypeCode)) {
            fieldSet.put(PROTOCOL_ACTION_TYPE_CODE, protocolActionTypeCode);
        }
        fieldSet.put(COMMITTEE_MOTION_TYPE_CODE, committeeMotionTypeCode);
        List<T> resultSet = (List<T>) businessObjectService.findMatching(getValidProtocolActionActionClassHook(), fieldSet);
        return resultSet;
    }
    
    
    protected abstract Class<T> getValidProtocolActionActionClassHook();


    /**
     * Return a list of ValidProtocolActionActionBase bos for the given action type code.
     * @param protocolActionTypeCode
     * @return 
     */
    public List<T> getFollowupsForActionTypeCode(String protocolActionTypeCode) {
        List<T> resultSet = new ArrayList<T>();
        if (!StringUtils.isEmpty(protocolActionTypeCode)) {
            Map<String,String> fieldSet = new HashMap<String,String>();
            fieldSet.put(PROTOCOL_ACTION_TYPE_CODE, protocolActionTypeCode);
            resultSet = (List<T>)businessObjectService.findMatching(getValidProtocolActionActionClassHook(), fieldSet);
        }
        return resultSet;
    }
    
    @Override
    public List<T> getFollowupsForProtocol(ProtocolBase protocol) {
        List<T> resultList; 
        if (protocol.getLastProtocolAction()==null) {
            return new ArrayList<T>();
        }
        
        //if the last action was record committee decision we get the list slightly differently.
        if (StringUtils.equals(protocol.getLastProtocolAction().getProtocolActionTypeCode(), getProtocolActionTypeCodeForRecordCommitteeDecisionHook())) {
            resultList = getFollowupsForActionTypeAndMotionType(getProtocolActionTypeCodeForRecordCommitteeDecisionHook(), protocol.getProtocolSubmission().getCommitteeDecisionMotionTypeCode());
        } else {
            resultList = getFollowupsForActionTypeCode(protocol.getLastProtocolAction().getProtocolActionTypeCode());
        }
        return resultList;
    }
    
    protected abstract String getProtocolActionTypeCodeForRecordCommitteeDecisionHook();


    @Override
    public boolean isActionOpenForFollowup(String protocolActionTypeCode, ProtocolBase protocol) {
        if (getLogHook().isDebugEnabled()) {
            getLogHook().debug("**********************************");
            getLogHook().debug(String.format("isActionOpenForFollowup called for action type code %s, on protocol %s",protocolActionTypeCode,protocol.getProtocolNumber()));
            getLogHook().debug(String.format("Last protocol action was %s", protocol.getLastProtocolAction().getProtocolActionTypeCode()));
        }
        boolean result = false;
        List<T> valids = getFollowupsForActionTypeCode(protocol.getLastProtocolAction().getProtocolActionTypeCode());
        for (ValidProtocolActionActionBase protocolActionAction : valids) {
            if (StringUtils.equals(protocolActionAction.getFollowupActionCode(),protocolActionTypeCode)) {
                if (!StringUtils.isEmpty(protocolActionAction.getMotionTypeCode())) {
                    //there is an associated motion type code with this action
                    //we need to check the committee's motion on the protocol in
                    //addition
                    if (getLogHook().isDebugEnabled()) {
                        getLogHook().debug(String.format("%s %s has defined MotionTypeCode %s, checking motion on protocol.",
                                getValidProtocolActionActionClassHook().getName(),protocolActionAction.getValidProtocolActionActionId(),protocolActionAction.getMotionTypeCode()));
                    }
                    if (StringUtils.equals(protocol.getProtocolSubmission().getCommitteeDecisionMotionTypeCode(), protocolActionAction.getMotionTypeCode())) {
                        if (getLogHook().isDebugEnabled()) {
                            getLogHook().debug("MotionType matches last motion on protocol.");
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
        
        if (getLogHook().isDebugEnabled()) {
            getLogHook().debug(String.format("isActionOpenForFollowup returning %s for type code %s",result,protocolActionTypeCode));
            getLogHook().debug("**********************************");
        }
        
        return result;
    }
    
    protected abstract Log getLogHook();


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
