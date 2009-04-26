/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.lookup.keyvalue.KeyLabelPairComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.rice.kns.datadictionary.AttributeDefinition;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * Lookup acceptable values for <code>{@link PersonEditableField}</code> business object
 *  
 * @author $Author: gmcgrego $
 * @version $Revision: 1.5 $
 */
public class PersonEditableFieldValuesFinder extends KeyValuesBase {
    List<AttributeDefinition> attributes; 
    
    /**
     * Constructs a PersonEditableFieldValueFinder
     */
    @SuppressWarnings("unchecked")
    public PersonEditableFieldValuesFinder() {
        super();
        attributes = getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(ProposalPerson.class.getName()).getAttributes();
    }
    
    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> retval = new ArrayList<KeyLabelPair>();
        retval.add(new KeyLabelPair("", "select"));

        for (AttributeDefinition attribute : getAttributes()) {
            if (!getExcludedAttributes().contains(attribute.getName())) {
                retval.add(new KeyLabelPair(attribute.getName(), attribute.getLabel()));
            }
        }
        
        sort(retval, new KeyLabelPairComparator());
        
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
