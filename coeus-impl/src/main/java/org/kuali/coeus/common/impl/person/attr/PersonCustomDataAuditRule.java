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
package org.kuali.coeus.common.impl.person.attr;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.common.framework.person.attr.PersonCustomData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.Map;

public class PersonCustomDataAuditRule extends PersonCustomDataRuleBase implements DocumentAuditRule {
    
    private static final String CUSTOM_DATA_ERROR_PREFIX = KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + "businessObject.personCustomDataList";
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean rulePassed = true;
        
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) document;
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) maintenanceDocument.getNewMaintainableObject().getDataObject();
        Map<String, CustomAttributeDocument> customAttributeDocuments = getCustomAttributeDocuments();
        
        int i = 0;
        for (PersonCustomData personCustomData : kcPersonExtendedAttributes.getPersonCustomDataList()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(String.valueOf(personCustomData.getCustomAttributeId()));
            String errorKey = CUSTOM_DATA_ERROR_PREFIX + Constants.LEFT_SQUARE_BRACKET + i++ + Constants.RIGHT_SQUARE_BRACKET + ".value";
            rulePassed &= validateRequired(personCustomData, customAttributeDocument, errorKey);
        }

        return rulePassed;
    }
    
    private boolean validateRequired(PersonCustomData personCustomData, CustomAttributeDocument customAttributeDocument, String errorKey) {
        boolean rulePassed = true;
        
        if (customAttributeDocument.isRequired()) {
            if (StringUtils.isBlank(personCustomData.getValue())) {
                reportError(errorKey, RiceKeyConstants.ERROR_REQUIRED, personCustomData.getCustomAttribute().getLabel());
                rulePassed = false;
            }
        }
        
        return rulePassed;
    }

}
