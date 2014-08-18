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
package org.kuali.coeus.propdev.impl.budget.editable;

import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaNumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;
import org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

public class BudgetColumnsToAlterMaintainableImpl  extends KraMaintainableImpl {
    private static Map<String, String> validationClassesMap = new HashMap<String, String>();
    static {
        validationClassesMap.put(AnyCharacterValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(AlphaNumericValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(AlphaValidationPattern.class.getName(), "STRING");
        validationClassesMap.put(DateValidationPattern.class.getName(), "DATE");
        validationClassesMap.put(NumericValidationPattern.class.getName(), "NUMBER");
    }    
    
    public void prepareForSave() {
        super.prepareForSave();
        BudgetColumnsToAlter budgetCol = (BudgetColumnsToAlter)businessObject;
        
        KcPersistenceStructureService persistenceStructureService =
            KcServiceLocator.getService(KcPersistenceStructureService.class);
        Map<String, String> columnToAttrMap = persistenceStructureService.getDBColumnToObjectAttributeMap(Budget.class);
        
        DataDictionaryService dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        AttributeDefinition attrDefinition = dataDictionaryService.getDataDictionary().
            getBusinessObjectEntry(Budget.class.getName()).
            getAttributeDefinition(columnToAttrMap.get(budgetCol.getColumnName()));
        
        if (attrDefinition == null) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.columnName", "error.proposalcolumnstoalter.attributeNotFound");
            return;
        } else {
            if (attrDefinition.getLabel().length() > 30) {
                budgetCol.setColumnLabel(attrDefinition.getLabel().substring(0, 29));
            } else {
                budgetCol.setColumnLabel(attrDefinition.getLabel());
            }
            budgetCol.setDataLength(attrDefinition.getMaxLength());
            String dataType = null;
            if (attrDefinition.getValidationPattern() != null) {
                String validationPattern = attrDefinition.getValidationPattern().getClass().getName();
                if ((dataType = validationClassesMap.get(validationPattern)) == null) {
                    dataType = "STRING";
                }
            } else {
                dataType = "STRING";
            }
            budgetCol.setDataType(dataType);
        }
    }
}
