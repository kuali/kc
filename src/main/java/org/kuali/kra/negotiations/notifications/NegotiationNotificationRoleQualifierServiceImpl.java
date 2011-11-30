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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier;
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
