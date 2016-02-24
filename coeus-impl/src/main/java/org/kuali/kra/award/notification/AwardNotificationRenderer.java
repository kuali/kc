/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.notification;

import org.apache.commons.lang3.StringUtils;
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
    private static final String MM_DD_YYYY = "MM/dd/yyyy";
    private static final String AWARD_TYPE = "awardType";
    private static final String ZERO = "0.00";
    private static final String ACTIVITY_TYPE = "activityType";
    private static Log LOG = LogFactory.getLog(AwardNotificationRenderer.class);
    
    private Award award;
    
    public AwardNotificationRenderer() {
        super();
    }

    public AwardNotificationRenderer(Award award) {
        this.award = award;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        return getAwardReplacementParameters(award);
    }
    
    public Map<String, String> getAwardReplacementParameters(Award award) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(MM_DD_YYYY);
        Map<String, String> result = super.getDefaultReplacementParameters();
        result.put("{AWARD_NUMBER}", award.getAwardNumber());
        result.put("{SEQUENCE_NUMBER}", award.getSequenceNumber().toString());
        result.put("{AWARD_TITLE}", award.getTitle());
        if (award.getAwardTypeCode() != null) {
            if (award.getAwardType() == null) {
                award.refreshReferenceObject(AWARD_TYPE);
            }
            result.put("{AWARD_TYPE_CODE}", award.getAwardTypeCode().toString());
            result.put("{AWARD_TYPE_NAME}", award.getAwardType().getDescription());
        } else {
            result.put("{AWARD_TYPE_CODE}", StringUtils.EMPTY);
            result.put("{AWARD_TYPE_NAME}", StringUtils.EMPTY);
        }
        result.put("{PI_NAME}", award.getPiName()==null ? StringUtils.EMPTY : award.getPiName());
        result.put("{LEAD_UNIT}", award.getLeadUnitNumber()==null ? StringUtils.EMPTY : award.getLeadUnitNumber());
        result.put("{LEAD_UNIT_NAME}", award.getLeadUnitName()==null ? StringUtils.EMPTY : award.getLeadUnitName());
        result.put("{ACCOUNT_NUMBER}", award.getAccountNumber()==null ? StringUtils.EMPTY : award.getAccountNumber());
        result.put("{SPONSOR_AWARD_NUMBER}", award.getSponsorAwardNumber()==null ? StringUtils.EMPTY : award.getSponsorAwardNumber());
        if (award.getStatusCode() != null) {
            result.put("{STATUS_CODE}", award.getStatusCode().toString());
            result.put("{STATUS_NAME}", award.getStatusDescription());
        } else {
            result.put("{STATUS_CODE}", StringUtils.EMPTY);
            result.put("{STATUS_NAME}", StringUtils.EMPTY);
        }
        if (award.getBeginDate() != null) {
            result.put("{BEGIN_DATE}", dateFormatter.format(award.getBeginDate()));
        } else {
            result.put("{BEGIN_DATE}", StringUtils.EMPTY);
        }
        if (award.getAwardExecutionDate() != null) {
            result.put("{EXECUTION_DATE}", dateFormatter.format(award.getAwardExecutionDate()));
        } else {
            result.put("{EXECUTION_DATE}", StringUtils.EMPTY);
        }
        if (award.getAwardEffectiveDate() != null) {
            result.put("{EFFECTIVE_DATE}", dateFormatter.format(award.getAwardEffectiveDate()));
        } else {
            result.put("{EFFECTIVE_DATE}", StringUtils.EMPTY);
        }
        if (award.getSponsorCode() != null) {
            result.put("{SPONSOR_CODE}", award.getSponsorCode());
            result.put("{SPONSOR_NAME}", award.getSponsorName());
        } else {
            result.put("{SPONSOR_CODE}", StringUtils.EMPTY);
            result.put("{SPONSOR_NAME}", StringUtils.EMPTY);
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
            result.put("{FINAL_EXPIRATION_DATE}", StringUtils.EMPTY);
        }
        if (award.getAwardEffectiveDate() != null) {
            result.put("{OBLIGATION_EFFECTIVE_DATE}", dateFormatter.format(award.getAwardEffectiveDate()));
        } else {
            result.put("{OBLIGATION_EFFECTIVE_DATE}", StringUtils.EMPTY);
        }
        if (awardAmountInfo != null && awardAmountInfo.getObligationExpirationDate() != null) {
            result.put("{OBLIGATION_EXPIRATION_DATE}", dateFormatter.format(awardAmountInfo.getObligationExpirationDate()));
        } else {
            result.put("{OBLIGATION_EXPIRATION_DATE}", StringUtils.EMPTY);
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
            result.put("{OBLIGATED_TOTAL_AMOUNT}", ZERO);
        }
        if (awardAmountInfo != null) {
            ScaleTwoDecimal totalAmount = ScaleTwoDecimal.ZERO;
            if (awardAmountInfo.getAnticipatedTotalAmount() != null) {
                totalAmount = totalAmount.add(awardAmountInfo.getAnticipatedTotalAmount());
            }
            result.put("{ANTICIPATED_TOTAL_AMOUNT}", totalAmount.toString());
        } else {
            result.put("{ANTICIPATED_TOTAL_AMOUNT}", ZERO);
        }
        if (award.getPrimeSponsorCode() != null) {
            result.put("{PRIME_SPONSOR_CODE}", award.getPrimeSponsorCode());
            result.put("{PRIME_SPONSOR_NAME}", award.getPrimeSponsorName());
        } else {
            result.put("{PRIME_SPONSOR_CODE}", StringUtils.EMPTY);
            result.put("{PRIME_SPONSOR_NAME}", StringUtils.EMPTY);
        }
        if (award.getActivityTypeCode() != null) {
            result.put("{ACTIVITY_TYPE_CODE}", award.getActivityTypeCode());
            if (award.getActivityType() == null) {
                award.refreshReferenceObject(ACTIVITY_TYPE);
            }
            result.put("{ACTIVITY_TYPE_NAME}", award.getActivityType().getDescription());
        } else {
            result.put("{ACTIVITY_TYPE_CODE}", StringUtils.EMPTY);
            result.put("{ACTIVITY_TYPE_NAME}", StringUtils.EMPTY);
        }
        if (award.getAccountTypeCode() != null) {
            result.put("{ACCOUNT_TYPE_CODE}", award.getAccountTypeCode().toString());
            result.put("{ACCOUNT_TYPE_NAME}", award.getAccountTypeDescription());
        } else {
            result.put("{ACCOUNT_TYPE_CODE}", StringUtils.EMPTY);
            result.put("{ACCOUNT_TYPE_NAME}", StringUtils.EMPTY);
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
