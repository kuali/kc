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
