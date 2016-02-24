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
package org.kuali.coeus.propdev.impl.budget.editable;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
        Map<String, String> columnToAttrMap = persistenceStructureService.getDBColumnToObjectAttributeMap(ProposalDevelopmentBudgetExt.class);
        
        DataDictionaryService dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        AttributeDefinition attrDefinition = dataDictionaryService.getDataDictionary().
            getBusinessObjectEntry(ProposalDevelopmentBudgetExt.class.getName()).
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
