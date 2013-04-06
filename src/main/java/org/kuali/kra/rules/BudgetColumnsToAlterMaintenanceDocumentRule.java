/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.util.HashMap;
import java.util.Map;

import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class BudgetColumnsToAlterMaintenanceDocumentRule extends MaintenanceDocumentRuleBase {
    private static Map<String, String> validationClassesMap = new HashMap<String, String>();
    static {
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern", "STRING");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaNumericValidationPattern", "STRING");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaValidationPattern", "STRING"); 
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern", "DATE");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern", "NUMBER");
    }

}
