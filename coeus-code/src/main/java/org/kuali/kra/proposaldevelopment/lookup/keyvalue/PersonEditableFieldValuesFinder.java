/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.lookup.keyvalue.KeyValueComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

/**
 * Lookup acceptable values for <code>{@link PersonEditableField}</code> business object
 *  
 * @author $Author: gmcgrego $
 * @version $Revision: 1.5 $
 */
public class PersonEditableFieldValuesFinder extends UifKeyValuesFinderBase {
    List<AttributeDefinition> attributes; 
    
    /**
     * Constructs a PersonEditableFieldValueFinder
     */
    @SuppressWarnings("unchecked")
    public PersonEditableFieldValuesFinder() {
        super();
        attributes = getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(ProposalPerson.class.getName()).getAttributes();
    }

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> retval = new ArrayList<KeyValue>();
        retval.add(new ConcreteKeyValue("", "select"));

        for (AttributeDefinition attribute : getAttributes()) {
            if (!getExcludedAttributes().contains(attribute.getName())) {
                retval.add(new ConcreteKeyValue(attribute.getName(), attribute.getLabel()));
            }
        }
        
        sort(retval, new KeyValueComparator());
        
        return retval;
    }

    /**
     * Returns a searchable <code>{@link Collection}</code> of attributes to explicitly not allow for <code>{@link PersonEditableField}</code>
     *  assignment.
     *  
     * @return Collection<String>
     */
    private Collection<String> getExcludedAttributes() {
        return asList(new String[] {"proposalNumber", "proposalPersonNumber", "personId", "rolodexId", "delete", "versionNumber"});
    }
    
    /**
     * Read-only access to a map of attributes for <code>{@link ProposalPerson}</code>
     * @return Map<String, AttributeDefinition>
     */
    private List<AttributeDefinition> getAttributes() {
        return attributes;
    }
    
    /**
     * Lookup the <code>{@link DataDictionaryService}</code>
     * 
     * @return DataDictionaryService
     */
    private DataDictionaryService getDataDictionaryService() {
        return getService(DataDictionaryService.class);
    }
}
