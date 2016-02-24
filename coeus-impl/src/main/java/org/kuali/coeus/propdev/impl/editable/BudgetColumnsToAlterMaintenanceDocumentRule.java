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
package org.kuali.coeus.propdev.impl.editable;

import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaNumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern;

import java.util.HashMap;
import java.util.Map;

public class BudgetColumnsToAlterMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    private static Map<String, String> validationClassesMap = new HashMap<String, String>();
    static {
        validationClassesMap.put(AnyCharacterValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(AlphaNumericValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(AlphaValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(DateValidationPattern.class.getName(), "DATE");
        validationClassesMap.put(NumericValidationPattern.class.getName(), "NUMBER");
    }

}
