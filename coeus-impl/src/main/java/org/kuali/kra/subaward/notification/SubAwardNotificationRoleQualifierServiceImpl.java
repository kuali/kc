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