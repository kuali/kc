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
package org.kuali.kra.coi.notification;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
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
                return reporter.getUnit().getUnitNumber();
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
