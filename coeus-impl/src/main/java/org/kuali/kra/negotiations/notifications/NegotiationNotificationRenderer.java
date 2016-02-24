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
package org.kuali.kra.negotiations.notifications;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.rice.core.api.CoreApiServiceLocator;

import java.text.SimpleDateFormat;
import java.util.Map;

public class NegotiationNotificationRenderer extends NotificationRendererBase {

    private static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    

    private static final long serialVersionUID = 408846376074619623L;
    private Negotiation negotiation; 
    
    private static String noneGiven = null;
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        if (noneGiven == null) {
            noneGiven = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(KeyConstants.NO_REASON_GIVEN);
        }
        Map<String, String> result = super.getDefaultReplacementParameters();
        Negotiable details = negotiation.getAssociatedNegotiable();
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
        result.put("{NEGOTIATION_ID}", negotiation.getNegotiationId().toString());
        result.put("{NEGOTIATOR}", negotiation.getNegotiator().getFullName());
        result.put("{NEGOTIATION_STATUS}", negotiation.getNegotiationStatus().getDescription());
        if (negotiation.getAnticipatedAwardDate() != null) {
            result.put("{ANTICIPATED_START_DATE}", df.format(negotiation.getAnticipatedAwardDate()));
        } else {
            result.put("{ANTICIPATED_START_DATE}", StringUtils.EMPTY);
        }
        if (negotiation.getNegotiationStartDate() != null) {
            result.put("{START_DATE}", df.format(negotiation.getNegotiationStartDate()));
        } else {
            result.put("{START_DATE}", StringUtils.EMPTY);
        }
        if (negotiation.getNegotiationEndDate() != null) {
            result.put("{END_DATE}", df.format(negotiation.getNegotiationEndDate()));
        } else {
            result.put("{END_DATE}", StringUtils.EMPTY);
        }
        if (details != null && StringUtils.isNotBlank(details.getTitle())) {
            result.put("{TITLE}", details.getTitle());
        } else {
            result.put("{TITLE}", noneGiven);
        }
        if (details != null && details.getPiEmployeeName() != null) { 
            result.put("{PI}", details.getPiEmployeeName());
        } else if (details != null && details.getPiNonEmployeeName() != null) {
            result.put("{PI}", details.getPiNonEmployeeName());
        } else {
            result.put("{PI}", noneGiven);
        }
        if (details != null && StringUtils.isNotBlank(details.getLeadUnitNumber())) {
            result.put("{LEAD_UNIT_NUMBER}", details.getLeadUnitName());
            result.put("{LEAD_UNIT_NAME}", details.getLeadUnitNumber());
        } else {
            result.put("{LEAD_UNIT_NUMBER}", noneGiven);
            result.put("{LEAD_UNIT_NAME}", noneGiven);
        }         
        if (details != null && StringUtils.isNotBlank(details.getSponsorCode())) {
            result.put("{SPONSOR_NAME}", details.getSponsorName());
            result.put("{SPONSOR_CODE}", details.getSponsorCode());
        } else {            
            result.put("{SPONSOR_NAME}", noneGiven);
            result.put("{SPONSOR_CODE}", noneGiven);
        }
        if (details != null && StringUtils.isNotBlank(details.getPrimeSponsorCode())) {
            result.put("{PRIME_SPONSOR_NAME}", details.getPrimeSponsorName());
            result.put("{PRIME_SPONSOR_CODE}", details.getPrimeSponsorCode());
        } else {
            result.put("{PRIME_SPONSOR_NAME}", noneGiven);
            result.put("{PRIME_SPONSOR_CODE}", noneGiven);
            
        }
        // set up hotlink in notification
        result.put("{DOCUMENT_NUMBER}", negotiation.getDocumentNumber());

        return result;
    }  

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

}
