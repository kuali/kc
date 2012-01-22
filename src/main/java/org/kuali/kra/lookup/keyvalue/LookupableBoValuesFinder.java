/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class LookupableBoValuesFinder extends KeyValuesBase {

    /**
     * Get a list of the lookupable classes
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        // this only has entries that have been loaded - force load?

    	Map<String, BusinessObjectEntry> businessObjectEntries = dataDictionaryService.getDataDictionary().getBusinessObjectEntries();
    	
    	Map<String, Integer> labelCounts = getLabelCounts(businessObjectEntries);
    	
    	for (String businessObject: businessObjectEntries.keySet()) {
    	    org.kuali.rice.kns.datadictionary.BusinessObjectEntry businessObjectEntry = (org.kuali.rice.kns.datadictionary.BusinessObjectEntry) businessObjectEntries.get(businessObject);
    	    if ((businessObjectEntry.hasLookupDefinition()) 
    	            && (businessObject.startsWith("org.kuali.kra") 
    	                    || businessObject.equals("org.kuali.rice.location.impl.campus.CampusBo")
                            || businessObject.equals("org.kuali.rice.location.api.campus.CampusContractTypeImpl")
                            || businessObject.equals("org.kuali.rice.location.api.country.CountryImpl")
                            || businessObject.equals("org.kuali.rice.kns.bo.CountyImpl")
                            || businessObject.equals("org.kuali.rice.kns.bo.PostalCodeImpl")
                            || businessObject.equals("org.kuali.rice.location.api.state.StateImpl"))) {
    	        String key = businessObject;
    	        
    	        String label = StringUtils.removeEnd(businessObjectEntry.getLookupDefinition().getTitle().trim()," Lookup");
    	        
    	        if (labelCounts.get(label) > 1){
    	            label = label + " (" + key.toString().substring(key.toString().lastIndexOf(".") + 1) + ")";
    	        }

        		KeyValue KeyValue = new ConcreteKeyValue(key, label);
        		if (!keyValues.contains(KeyValue)) {
        		    keyValues.add(KeyValue);
        		}
    	    }
    	}

    	// added comparator below to alphabetize lists on label
        Collections.sort(keyValues, new KeyValueComparator());
        keyValues.add(0, new ConcreteKeyValue("", "select"));

    	return keyValues;
    }
    
    public Map<String, Integer> getLabelCounts(Map<String, BusinessObjectEntry> businessObjectEntries){
        Map<String, Integer> labels = new HashMap<String, Integer>();
        
        for (String businessObject: businessObjectEntries.keySet()) {
            org.kuali.rice.kns.datadictionary.BusinessObjectEntry businessObjectEntry = (org.kuali.rice.kns.datadictionary.BusinessObjectEntry) businessObjectEntries.get(businessObject);
            if ((businessObjectEntry.hasLookupDefinition()) 
                    && (businessObject.startsWith("org.kuali.kra") 
                            || businessObject.equals("org.kuali.rice.location.impl.campus.CampusBo")
                            || businessObject.equals("org.kuali.rice.location.api.campus.CampusContractTypeImpl")
                            || businessObject.equals("org.kuali.rice.location.api.country.CountryImpl")
                            || businessObject.equals("org.kuali.rice.kns.bo.CountyImpl")
                            || businessObject.equals("org.kuali.rice.kns.bo.PostalCodeImpl")
                            || businessObject.equals("org.kuali.rice.location.api.state.StateImpl"))) {
                String label = StringUtils.removeEnd(businessObjectEntry.getLookupDefinition().getTitle().trim()," Lookup");
                if(labels.containsKey(label)){
                    Integer count = labels.get(label) + 1;
                    labels.put(label, count);
                } else {
                    labels.put(label, 1);
                }
            }
        }
        
        return labels;
    }

}
