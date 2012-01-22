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
package org.kuali.kra.proposaldevelopment.document;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalColumnsToAlter;
import org.kuali.kra.service.KraPersistenceStructureService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.util.GlobalVariables;

public class ProposalColumnsToAlterMaintainableImpl extends KraMaintainableImpl {

    private static Map<String, String> validationClassesMap = new HashMap<String, String>();
    static {
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AnyCharacterValidationPattern", "STRING");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaNumericValidationPattern", "STRING");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.AlphaValidationPattern", "STRING"); 
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.fieldlevel.DateValidationPattern", "DATE");
        validationClassesMap.put("org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern", "NUMBER");
    }    
    
    public void prepareForSave() {
        super.prepareForSave();
        ProposalColumnsToAlter proposalCol = (ProposalColumnsToAlter)businessObject;
        
        KraPersistenceStructureService persistenceStructureService = 
            KraServiceLocator.getService(KraPersistenceStructureService.class);
        Map<String, String> columnToAttrMap = persistenceStructureService.getDBColumnToObjectAttributeMap(DevelopmentProposal.class);
        
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        AttributeDefinition attrDefinition = dataDictionaryService.getDataDictionary().
            getBusinessObjectEntry(DevelopmentProposal.class.getName()).
            getAttributeDefinition(columnToAttrMap.get(proposalCol.getColumnName()));
        
        if (attrDefinition == null) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.columnName", "error.proposalcolumnstoalter.attributeNotFound");
            return;
        } else {
            if (attrDefinition.getLabel().length() > 30) {
                proposalCol.setColumnLabel(attrDefinition.getLabel().substring(0, 29));
            } else {
                proposalCol.setColumnLabel(attrDefinition.getLabel());
            }
            proposalCol.setDataLength(attrDefinition.getMaxLength());
            String dataType = null;
            if (attrDefinition.getValidationPattern() != null) {
                String validationPattern = attrDefinition.getValidationPattern().getClass().getName();
                if ((dataType = validationClassesMap.get(validationPattern)) == null) {
                    dataType = "STRING";
                }
            } else {
                dataType = "STRING";
            }
            proposalCol.setDataType(dataType);
        }
    }
}
