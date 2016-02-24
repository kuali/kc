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
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.negotiations.bo.Negotiation;

public class NegotiationNotificationRoleQualifierServiceImpl implements NegotiationNotificationRoleQualifierService {

    private Negotiation negotiation;

    @Override
    public String getRoleQualifierValue(NotificationModuleRoleQualifier qualifier) {
        String result = null;
        if (StringUtils.equals(qualifier.getQualifier(), "negotiation")) {
            if (negotiation.getNegotiationId() != null) {
                result = negotiation.getNegotiationId().toString();
            }
        }
        else if (StringUtils.equals(qualifier.getQualifier(), KcKimAttributes.UNIT_NUMBER)) {
            if (negotiation.getAssociatedDocument() != null) {
                result = negotiation.getAssociatedDocument().getLeadUnitNumber();
            }
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
