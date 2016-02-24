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
package org.kuali.kra.coi.notification;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.krad.util.GlobalVariables;


public class CoiNotificationRoleQualifierServiceImpl implements CoiNotificationRoleQualifierService {

    private CoiDisclosure coiDisclosure;
    
    @Override
    public String getRoleQualifierValue(NotificationModuleRoleQualifier qualifier) {
        String qName = qualifier.getQualifier();

        if (StringUtils.equalsIgnoreCase(qName, KimConstants.AttributeConstants.DOCUMENT_NUMBER)) {
            return coiDisclosure.getCoiDisclosureDocument().getDocumentNumber();
        } else if (StringUtils.equalsIgnoreCase(qName, "coiDisclosureId")) {
            return coiDisclosure.getCoiDisclosureId().toString();
        } else if (StringUtils.equals(qName, KcKimAttributes.UNIT_NUMBER)) {
            if (coiDisclosure == null) {
                // no disclosure, so we must be sending a FE notification
                KcPerson reporter = KcPerson.fromPersonId(GlobalVariables.getUserSession().getPrincipalId());
                final Unit unit = reporter.getUnit();
                if (unit != null) {
                  return unit.getUnitNumber();
                }
            } else {
                return coiDisclosure.getLeadUnitNumber();
            }
        } else if (StringUtils.equals(qName, KcKimAttributes.SUBUNITS)) {
            return "*";
        }
        
        return null;
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

}
