/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.KeyValueComparator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.location.impl.campus.CampusBo;
import org.kuali.rice.location.impl.country.CountryBo;
import org.kuali.rice.location.impl.county.CountyBo;
import org.kuali.rice.location.impl.postalcode.PostalCodeBo;
import org.kuali.rice.location.impl.state.StateBo;

import java.util.*;

public class LookupableBoValuesFinder extends UifKeyValuesFinderBase {

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        DataDictionaryService dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        // this only has entries that have been loaded - force load?

    	Map<String, BusinessObjectEntry> businessObjectEntries = dataDictionaryService.getDataDictionary().getBusinessObjectEntries();
    	
    	Map<String, Integer> labelCounts = getLabelCounts(businessObjectEntries);
    	
    	for (String businessObject: businessObjectEntries.keySet()) {
    	    org.kuali.rice.kns.datadictionary.BusinessObjectEntry businessObjectEntry = (org.kuali.rice.kns.datadictionary.BusinessObjectEntry) businessObjectEntries.get(businessObject);
    	    if ((businessObjectEntry.hasLookupDefinition()) 
    	            && (kcBo(businessObject) || supportedRiceBo(businessObject))) {
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
                    && (kcBo(businessObject) || supportedRiceBo(businessObject))) {
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

    protected boolean kcBo(String businessObject) {
        return businessObject.startsWith("org.kuali.kra") || businessObject.startsWith("org.kuali.coeus");
    }

    protected boolean supportedRiceBo(String businessObject) {
        return businessObject.equals(CampusBo.class.getName())
                || businessObject.equals(CountryBo.class.getName())
                || businessObject.equals(CountyBo.class.getName())
                || businessObject.equals(PostalCodeBo.class.getName())
                || businessObject.equals(StateBo.class.getName());
    }
}
