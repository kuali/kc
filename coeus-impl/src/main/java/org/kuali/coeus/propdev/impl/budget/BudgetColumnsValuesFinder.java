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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BudgetColumnsValuesFinder extends UifKeyValuesFinderBase {
    private DataDictionaryService dataDictionaryService;
    private KcPersistenceStructureService kcPersistenceStructureService;

    @Override
    public List<KeyValue> getKeyValues() {
        BusinessObjectEntry proposalEntry = 
            (BusinessObjectEntry) getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(ProposalDevelopmentBudgetExt.class.getName());
        KcPersistenceStructureService persistenceStructureService = getKcPersistenceStructureService();
        Map<String, String> attrToColumnMap = persistenceStructureService.getPersistableAttributesColumnMap(ProposalDevelopmentBudgetExt.class);        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (AttributeDefinition entry : proposalEntry.getAttributes()) {
            if (attrToColumnMap.get(entry.getName()) == null) {
                //if the data dictionary name cannot be found in the 
                //database mapping then this is not a valid entry
                continue;
            }
            ConcreteKeyValue keyPair = new ConcreteKeyValue();
            keyPair.setKey(attrToColumnMap.get(entry.getName()));
            keyPair.setValue(entry.getShortLabel());
            keyValues.add(keyPair);
        }
        
        return keyValues;
    }

    protected  KcPersistenceStructureService getKcPersistenceStructureService (){
        if (kcPersistenceStructureService == null)
            kcPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        return kcPersistenceStructureService;
    }
    private DataDictionaryService getDataDictionaryService() {
        if (dataDictionaryService == null) {
            dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        }
        return dataDictionaryService;
    }
}
