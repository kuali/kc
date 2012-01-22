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
package org.kuali.kra.coi.notification;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.KimConstants;


public class CoiNotificationRoleQualifierServiceImpl implements CoiNotificationRoleQualifierService {

    private CoiDisclosure coiDisclosure;
    
    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService#getRoleQualifierValue(org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier)
     */
    @Override
    public String getRoleQualifierValue(NotificationModuleRoleQualifier qualifier) {
        String qName = qualifier.getQualifier();

        if (StringUtils.equalsIgnoreCase(qName, KimConstants.AttributeConstants.DOCUMENT_NUMBER)) {
            return coiDisclosure.getCoiDisclosureDocument().getDocumentNumber();
        } else if (StringUtils.equalsIgnoreCase(qName, "disclosure")) {
            return coiDisclosure.getCoiDisclosureId().toString();
        } else if (StringUtils.equals(qName, KcKimAttributes.UNIT_NUMBER)) {
            return coiDisclosure.getLeadUnitNumber();
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
