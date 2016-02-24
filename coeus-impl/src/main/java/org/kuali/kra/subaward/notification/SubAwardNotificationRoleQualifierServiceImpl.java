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
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kim.api.KimConstants;

/**
 * Implements the AwardNotificationRoleQualifierService.
 */
public class SubAwardNotificationRoleQualifierServiceImpl implements SubAwardNotificationRoleQualifierService {

    private SubAward subAward;
    
    @Override
    public String getRoleQualifierValue(NotificationModuleRoleQualifier qualifier) {
        String roleQualifierValue = null;
        if (StringUtils.equals(qualifier.getQualifier(), KcKimAttributes.SUBAWARD)) {
            roleQualifierValue = subAward.getSubAwardId().toString();
        }
        else if (StringUtils.equals(qualifier.getQualifier(), KcKimAttributes.UNIT_NUMBER)) {
            if (subAward.getLeadUnitNumber() != null) {
                roleQualifierValue = subAward.getLeadUnitNumber();
            }
        } else if (StringUtils.equals(qualifier.getQualifier(), KimConstants.AttributeConstants.DOCUMENT_NUMBER)) {
            roleQualifierValue = subAward.getSubAwardDocument().getDocumentNumber();
        }
        return roleQualifierValue;
    }

    public SubAward getSubAward() {
        return subAward;
    }
    
    public void setSubAward(SubAward subAward) {
        this.subAward = subAward;
    }

}
