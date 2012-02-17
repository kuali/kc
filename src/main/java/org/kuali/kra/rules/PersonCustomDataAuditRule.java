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
package org.kuali.kra.rules;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.KcPersonExtendedAttributes;
import org.kuali.kra.bo.PersonCustomData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.KRADConstants;

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