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
package org.kuali.kra.subaward.notification;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Renders fields for the Award notifications.
 */
public class SubAwardNotificationRenderer extends NotificationRendererBase {

    private static final String MM_DD_YYYY = "MM/dd/yyyy";
    private SubAward subAward;
    
    public SubAwardNotificationRenderer() {
        super();
    }

    public SubAwardNotificationRenderer(SubAward subAward) {
        this.subAward = subAward;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        return getSubAwardReplacementParameters(subAward);
    }
    
    public Map<String, String> getSubAwardReplacementParameters(SubAward subAward) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(MM_DD_YYYY);
        Map<String, String> result = super.getDefaultReplacementParameters();
        result.put("{SUBAWARD_ID}", subAward.getSubAwardId().toString());
        result.put("{SEQUENCE_NUMBER}", subAward.getSequenceNumber().toString());
        result.put("{SUBAWARD_TITLE}", subAward.getTitle());
        result.put("{LEAD_UNIT}", subAward.getLeadUnitNumber()==null ? StringUtils.EMPTY : subAward.getLeadUnitNumber());
        result.put("{LEAD_UNIT_NAME}", subAward.getLeadUnitName()==null ? StringUtils.EMPTY : subAward.getLeadUnitName());
        result.put("{ACCOUNT_NUMBER}", subAward.getAccountNumber()==null ? StringUtils.EMPTY : subAward.getAccountNumber());
        result.put("{SPONSOR_AWARD_NUMBER}", subAward.getSponsorAwardNumber()==null ? StringUtils.EMPTY : subAward.getSponsorAwardNumber());
        if (subAward.getStatusCode() != null) {
            result.put("{STATUS_CODE}", subAward.getStatusCode().toString());
            result.put("{STATUS_NAME}", subAward.getStatusDescription());
        } else {
            result.put("{STATUS_CODE}", StringUtils.EMPTY);
            result.put("{STATUS_NAME}", StringUtils.EMPTY);
        }
        if (subAward.getStartDate() != null) {
            result.put("{BEGIN_DATE}", dateFormatter.format(subAward.getStartDate()));
        } else {
            result.put("{BEGIN_DATE}", StringUtils.EMPTY);
        }
        if (subAward.getSponsorCode() != null) {
            result.put("{SPONSOR_CODE}", subAward.getSponsorCode());
            result.put("{SPONSOR_NAME}", subAward.getSponsorName());
        } else {
            result.put("{SPONSOR_CODE}", StringUtils.EMPTY);
            result.put("{SPONSOR_NAME}", StringUtils.EMPTY);
        }
        if (subAward.getPrimeSponsorCode() != null) {
            result.put("{PRIME_SPONSOR_CODE}", subAward.getPrimeSponsorCode());
            result.put("{PRIME_SPONSOR_NAME}", subAward.getPrimeSponsorName());
        } else {
            result.put("{PRIME_SPONSOR_CODE}", StringUtils.EMPTY);
            result.put("{PRIME_SPONSOR_NAME}", StringUtils.EMPTY);
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
