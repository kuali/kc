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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;

/**
 * Notification Context for Negotiation Closed action.
 */
public class NegotiationCloseNotificationContext implements NotificationContext {
    
    private static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    private static final String NOTIFICATION_LINE_BREAK = "\n<br/>\n";
    
    private NegotiationDocument negotiationDocument;
    private Negotiable negotiableBo;
    
    
    public NegotiationCloseNotificationContext(NegotiationDocument negotiationDocument, Negotiable negotiableBo) {
        this.negotiationDocument = negotiationDocument;
        this.negotiableBo = negotiableBo;
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
    }

    @Override
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        
        String qualifierKey = null;
        String qualifierValue = null;
        if (StringUtils.equals(notificationRecipient.getRoleName(), "KC-NEGOTIATION:Negotiator") ||
            StringUtils.equals(notificationRecipient.getRoleName(), "KC-NEGOTIATION:Investigators")) {
            qualifierKey = KcKimAttributes.NEGOTIATION;
            qualifierValue = getNegotiationDocument().getNegotiation().getDocumentNumberForPermission();
        } else if (StringUtils.equals(notificationRecipient.getRoleName(), "KC-ADM:OSP Administrator") ||
                   StringUtils.equals(notificationRecipient.getRoleName(), "KC-WKFLW:Unit Administrator")) {
            qualifierKey = KcKimAttributes.UNIT_NUMBER;
            qualifierValue = getNegotiationDocument().getNegotiation().getLeadUnitNumber();
        }
        
        AttributeSet qualifications = notificationRecipient.getRoleQualifiers();
        if (qualifications == null) {
            qualifications = new AttributeSet();
        }
        if (qualifierKey != null && qualifierValue != null) {
            qualifications.put(qualifierKey, qualifierValue);
        }
        notificationRecipient.setRoleQualifiers(qualifications);

    }

    @Override
    public String replaceContextVariables(String text) {
        if (text.contains("{NEGOTIATION_INFO}")) {
            text = StringUtils.replace(text, "{NEGOTIATION_INFO}", buildNegotiationInfo());
        }
        return text;
    }
    
    protected String buildNegotiationInfo() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
        StringBuffer buffer = new StringBuffer();
        Negotiation negotiation = negotiationDocument.getNegotiation();
        buffer.append("Negotiation ID:\t");
        buffer.append(negotiation.getNegotiationId());
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Negotiator:\t");
        buffer.append(negotiation.getNegotiator().getFullName());
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Negotiation Status:\t");
        buffer.append(negotiation.getNegotiationStatus().getDescription());
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Anticipated Project Start Date:\t");
        if (negotiation.getAnticipatedAwardDate() != null) {
            buffer.append(df.format(negotiation.getAnticipatedAwardDate()));
        }
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Negotiation Start Date:\t");
        if (negotiation.getNegotiationStartDate() != null) {
            buffer.append(df.format(negotiation.getNegotiationStartDate()));
        }
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Negotiation End Date:\t");
        if (negotiation.getNegotiationEndDate() != null) {
            buffer.append(df.format(negotiation.getNegotiationEndDate()));
        }
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Title:\t");
        if (negotiableBo != null) {
            buffer.append(negotiableBo.getTitle());
        }
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Primary Investigator:\t");
        if (negotiableBo != null) {
            if (negotiableBo.getPiEmployeeName() != null) { 
                buffer.append(negotiableBo.getPiEmployeeName());
            } else if (negotiableBo.getPiNonEmployeeName() != null) {
                buffer.append(negotiableBo.getPiNonEmployeeName());
            }
        }
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Lead Unit:\t");
        if (negotiableBo != null && StringUtils.isNotBlank(negotiableBo.getLeadUnitNumber())) {
            buffer.append(negotiableBo.getLeadUnitName());
            buffer.append(" (");
            buffer.append(negotiableBo.getLeadUnitNumber());
            buffer.append(")");
        }
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        buffer.append("Sponsor:\t");
        if (negotiableBo != null && StringUtils.isNotBlank(negotiableBo.getSponsorCode())) {
            buffer.append(negotiableBo.getSponsorName());
            buffer.append(" (");
            buffer.append(negotiableBo.getSponsorCode());
            buffer.append(")");
        }
        buffer.append(NOTIFICATION_LINE_BREAK);

        buffer.append("Prime Sponsor:\t");
        if (negotiableBo != null && StringUtils.isNotBlank(negotiableBo.getPrimeSponsorCode())) {
            buffer.append(negotiableBo.getPrimeSponsorName());
            buffer.append(" (");
            buffer.append(negotiableBo.getPrimeSponsorCode());
            buffer.append(")");
        }
        buffer.append(NOTIFICATION_LINE_BREAK);
        
        return buffer.toString();
    }

    public NegotiationDocument getNegotiationDocument() {
        return negotiationDocument;
    }

    public void setNegotiationDocument(NegotiationDocument negotiationDocument) {
        this.negotiationDocument = negotiationDocument;
    }

    public Negotiable getNegotiableBo() {
        return negotiableBo;
    }

    public void setNegotiableBo(Negotiable negotiableBo) {
        this.negotiableBo = negotiableBo;
    }

}
