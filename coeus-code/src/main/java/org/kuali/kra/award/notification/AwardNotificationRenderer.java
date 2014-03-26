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
package org.kuali.kra.award.notification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Renders fields for the Award notifications.
 */
public class AwardNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = -5066268431930093815L;
    private static Log LOG = LogFactory.getLog(AwardNotificationRenderer.class);
    
    private Award award;
    
    public AwardNotificationRenderer() {
        
    }
    
    /**
     * Constructs an Award notification renderer.
     * @param institutionalProposal
     */
    public AwardNotificationRenderer(Award award) {
        this.award = award;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        return getAwardReplacementParameters(award);
    }
    
    public Map<String, String> getAwardReplacementParameters(Award award) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");        
        Map<String, String> result = super.getDefaultReplacementParameters();
        result.put("{AWARD_NUMBER}", award.getAwardNumber());
        result.put("{SEQUENCE_NUMBER}", award.getSequenceNumber().toString());
        result.put("{AWARD_TITLE}", award.getTitle());
        if (award.getAwardTypeCode() != null) {
            if (award.getAwardType() == null) {
                award.refreshReferenceObject("awardType");
            }
            result.put("{AWARD_TYPE_CODE}", award.getAwardTypeCode().toString());
            result.put("{AWARD_TYPE_NAME}", award.getAwardType().getDescription());
        } else {
            result.put("{AWARD_TYPE_CODE}", "");
            result.put("{AWARD_TYPE_NAME}", "");
        }
        result.put("{PI_NAME}", award.getPiName()==null?"":award.getPiName());
        result.put("{LEAD_UNIT}", award.getLeadUnitNumber()==null?"":award.getLeadUnitNumber());
        result.put("{LEAD_UNIT_NAME}", award.getLeadUnitName()==null?"":award.getLeadUnitName());
        result.put("{ACCOUNT_NUMBER}", award.getAccountNumber()==null?"":award.getAccountNumber());
        result.put("{SPONSOR_AWARD_NUMBER}", award.getSponsorAwardNumber()==null?"":award.getSponsorAwardNumber());
        if (award.getStatusCode() != null) {
            result.put("{STATUS_CODE}", award.getStatusCode().toString());
            result.put("{STATUS_NAME}", award.getStatusDescription());
        } else {
            result.put("{STATUS_CODE}", "");
            result.put("{STATUS_NAME}", "");                        
        }
        if (award.getBeginDate() != null) {
            result.put("{BEGIN_DATE}", dateFormatter.format(award.getBeginDate()));
        } else {
            result.put("{BEGIN_DATE}", "");            
        }
        if (award.getAwardExecutionDate() != null) {
            result.put("{EXECUTION_DATE}", dateFormatter.format(award.getAwardExecutionDate()));
        } else {
            result.put("{EXECUTION_DATE}", "");            
        }
        if (award.getAwardEffectiveDate() != null) {
            result.put("{EFFECTIVE_DATE}", dateFormatter.format(award.getAwardEffectiveDate()));
        } else {
            result.put("{EFFECTIVE_DATE}", "");            
        }
        if (award.getSponsorCode() != null) {
            result.put("{SPONSOR_CODE}", award.getSponsorCode());
            result.put("{SPONSOR_NAME}", award.getSponsorName());
        } else {
            result.put("{SPONSOR_CODE}", "");
            result.put("{SPONSOR_NAME}", "");            
        }
        AwardAmountInfo awardAmountInfo = null;
        try {
            awardAmountInfo = award.getAwardAmountInfos().get(award.getIndexOfAwardAmountInfoForDisplay());
        }
        catch (WorkflowException e) {
            LOG.warn("Unable to load award amount info information.", e);
        } 
        if (awardAmountInfo != null && awardAmountInfo.getFinalExpirationDate() != null) {
            result.put("{FINAL_EXPIRATION_DATE}", dateFormatter.format(awardAmountInfo.getFinalExpirationDate()));
        } else {
            result.put("{FINAL_EXPIRATION_DATE}", "");
        }
        if (award.getAwardEffectiveDate() != null) {
            result.put("{OBLIGATION_EFFECTIVE_DATE}", dateFormatter.format(award.getAwardEffectiveDate()));
        } else {
            result.put("{OBLIGATION_EFFECTIVE_DATE}", "");            
        }
        if (awardAmountInfo != null && awardAmountInfo.getObligationExpirationDate() != null) {
            result.put("{OBLIGATION_EXPIRATION_DATE}", dateFormatter.format(awardAmountInfo.getObligationExpirationDate()));
        } else {
            result.put("{OBLIGATION_EXPIRATION_DATE}", "");            
        }
        if (awardAmountInfo != null) {
            ScaleTwoDecimal totalAmount = ScaleTwoDecimal.ZERO;
            if (awardAmountInfo.getObligatedTotalDirect() != null) {
                totalAmount = totalAmount.add(awardAmountInfo.getObligatedTotalDirect());
            }
            if (awardAmountInfo.getObligatedTotalIndirect() != null) {
                totalAmount = totalAmount.add(awardAmountInfo.getObligatedTotalIndirect());
            }
            result.put("{OBLIGATED_TOTAL_AMOUNT}", totalAmount.toString());
        } else {
            result.put("{OBLIGATED_TOTAL_AMOUNT}", "0.00");            
        }
        if (awardAmountInfo != null) {
            ScaleTwoDecimal totalAmount = ScaleTwoDecimal.ZERO;
            if (awardAmountInfo.getAnticipatedTotalAmount() != null) {
                totalAmount = totalAmount.add(awardAmountInfo.getAnticipatedTotalAmount());
            }
            result.put("{ANTICIPATED_TOTAL_AMOUNT}", totalAmount.toString());
        } else {
            result.put("{ANTICIPATED_TOTAL_AMOUNT}", "0.00");            
        }
        if (award.getPrimeSponsorCode() != null) {
            result.put("{PRIME_SPONSOR_CODE}", award.getPrimeSponsorCode());
            result.put("{PRIME_SPONSOR_NAME}", award.getPrimeSponsorName());
        } else {
            result.put("{PRIME_SPONSOR_CODE}", "");
            result.put("{PRIME_SPONSOR_NAME}", "");            
        }
        if (award.getActivityTypeCode() != null) {
            result.put("{ACTIVITY_TYPE_CODE}", award.getActivityTypeCode());
            if (award.getActivityType() == null) {
                award.refreshReferenceObject("activityType");
            }
            result.put("{ACTIVITY_TYPE_NAME}", award.getActivityType().getDescription());
        } else {
            result.put("{ACTIVITY_TYPE_CODE}", "");
            result.put("{ACTIVITY_TYPE_NAME}", "");            
        }
        if (award.getAccountTypeCode() != null) {
            result.put("{ACCOUNT_TYPE_CODE}", award.getAccountTypeCode().toString());
            result.put("{ACCOUNT_TYPE_NAME}", award.getAccountTypeDescription());
        } else {
            result.put("{ACCOUNT_TYPE_CODE}", "");
            result.put("{ACCOUNT_TYPE_NAME}", "");            
        }
        return result;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }
    
}