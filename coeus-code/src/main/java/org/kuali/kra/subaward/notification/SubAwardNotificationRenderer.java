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
package org.kuali.kra.subaward.notification;

import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Renders fields for the Award notifications.
 */
public class SubAwardNotificationRenderer extends NotificationRendererBase {

    private SubAward subAward;
    
    public SubAwardNotificationRenderer() {
    }
    
    /**
     * Constructs an Award notification renderer.
     * @param institutionalProposal
     */
    public SubAwardNotificationRenderer(SubAward subAward) {
        this.subAward = subAward;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        return getSubAwardReplacementParameters(subAward);
    }
    
    public Map<String, String> getSubAwardReplacementParameters(SubAward subAward) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");        
        Map<String, String> result = super.getDefaultReplacementParameters();
        result.put("{SUBAWARD_ID}", subAward.getSubAwardId().toString());
        result.put("{SEQUENCE_NUMBER}", subAward.getSequenceNumber().toString());
        result.put("{SUBAWARD_TITLE}", subAward.getTitle());
        result.put("{LEAD_UNIT}", subAward.getLeadUnitNumber()==null?"":subAward.getLeadUnitNumber());
        result.put("{LEAD_UNIT_NAME}", subAward.getLeadUnitName()==null?"":subAward.getLeadUnitName());
        result.put("{ACCOUNT_NUMBER}", subAward.getAccountNumber()==null?"":subAward.getAccountNumber());
        result.put("{SPONSOR_AWARD_NUMBER}", subAward.getSponsorAwardNumber()==null?"":subAward.getSponsorAwardNumber());
        if (subAward.getStatusCode() != null) {
            result.put("{STATUS_CODE}", subAward.getStatusCode().toString());
            result.put("{STATUS_NAME}", subAward.getStatusDescription());
        } else {
            result.put("{STATUS_CODE}", "");
            result.put("{STATUS_NAME}", "");                        
        }
        if (subAward.getStartDate() != null) {
            result.put("{BEGIN_DATE}", dateFormatter.format(subAward.getStartDate()));
        } else {
            result.put("{BEGIN_DATE}", "");            
        }
        if (subAward.getSponsorCode() != null) {
            result.put("{SPONSOR_CODE}", subAward.getSponsorCode());
            result.put("{SPONSOR_NAME}", subAward.getSponsorName());
        } else {
            result.put("{SPONSOR_CODE}", "");
            result.put("{SPONSOR_NAME}", "");            
        }
        if (subAward.getPrimeSponsorCode() != null) {
            result.put("{PRIME_SPONSOR_CODE}", subAward.getPrimeSponsorCode());
            result.put("{PRIME_SPONSOR_NAME}", subAward.getPrimeSponsorName());
        } else {
            result.put("{PRIME_SPONSOR_CODE}", "");
            result.put("{PRIME_SPONSOR_NAME}", "");            
        }
        result.put("{USER_FULLNAME}", GlobalVariables.getUserSession().getPerson().getName());
        return result;
    }

    public SubAward getSubAward() {
        return subAward;
    }

    public void setSubAward(SubAward subAward) {
        this.subAward = subAward;
    }
    
}