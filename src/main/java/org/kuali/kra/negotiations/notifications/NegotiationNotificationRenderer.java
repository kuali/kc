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
package org.kuali.kra.negotiations.notifications;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.NotificationRendererBase;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.Negotiation;

public class NegotiationNotificationRenderer extends NotificationRendererBase {

    private static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    private static final String NOTIFICATION_LINE_BREAK = "\n<br/>\n";
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 408846376074619623L;
    private Negotiation negotiation; 
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> result = new HashMap<String, String>();
        Negotiable negotiableBo = negotiation.getAssociatedDocument();
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
        result.put("{NEGOTIATION_ID}", negotiation.getNegotiationId().toString());
        result.put("{NEGOTIATOR}", negotiation.getNegotiator().getFullName());
        result.put("{NEGOTIATION_STATUS}", negotiation.getNegotiationStatus().getDescription());
        if (negotiation.getAnticipatedAwardDate() != null) {
            result.put("{ANTICIPATED_START_DATE}", df.format(negotiation.getAnticipatedAwardDate()));
        } else {
            result.put("{ANTICIPATED_START_DATE}", "");
        }
        if (negotiation.getNegotiationStartDate() != null) {
            result.put("{START_DATE}", df.format(negotiation.getNegotiationStartDate()));
        } else {
            result.put("{START_DATE}", "");
        }
        if (negotiation.getNegotiationEndDate() != null) {
            result.put("{END_DATE}", df.format(negotiation.getNegotiationEndDate()));
        } else {
            result.put("{END_DATE}", "");
        }
        if (negotiableBo != null) {
            result.put("{TITLE}", negotiableBo.getTitle());
        } else {
            result.put("{TITLE}", "");
        }
        if (negotiableBo != null && negotiableBo.getPiEmployeeName() != null) { 
            result.put("{PI}", negotiableBo.getPiEmployeeName());
        } else if (negotiableBo != null && negotiableBo.getPiNonEmployeeName() != null) {
            result.put("{PI}", negotiableBo.getPiNonEmployeeName());
        } else {
            result.put("{PI}", "");
        }
        if (negotiableBo != null && StringUtils.isNotBlank(negotiableBo.getLeadUnitNumber())) {
            result.put("{LEAD_UNIT_NUMBER}", negotiableBo.getLeadUnitName());
            result.put("{LEAD_UNIT_NAME}", negotiableBo.getLeadUnitNumber());
        } else {
            result.put("{LEAD_UNIT_NUMBER}", "");
            result.put("{LEAD_UNIT_NAME}", "");
        }         
        if (negotiableBo != null && StringUtils.isNotBlank(negotiableBo.getSponsorCode())) {
            result.put("{SPONSOR_NAME}", negotiableBo.getSponsorName());
            result.put("{SPONSOR_CODE}", negotiableBo.getSponsorCode());
        } else {            
            result.put("{SPONSOR_NAME}", "");
            result.put("{SPONSOR_CODE}", "");
        }
        if (negotiableBo != null && StringUtils.isNotBlank(negotiableBo.getPrimeSponsorCode())) {
            result.put("{PRIME_SPONSOR_NAME}", negotiableBo.getPrimeSponsorName());
            result.put("{PRIME_SPONSOR_CODE}", negotiableBo.getPrimeSponsorCode());
        } else {
            result.put("{PRIME_SPONSOR_NAME}", "");
            result.put("{PRIME_SPONSOR_CODE}", "");
            
        }
        return result;
    }  

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

}
