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
package org.kuali.kra.irb.personnel;

import java.util.Collections;
import java.util.List;

import org.kuali.kra.lookup.keyvalue.ConditionValuesFinder;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.lookup.keyvalue.SortedValuesFinder;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

/**
 * See {@link #getKeyValues()}.
 */
public class ProtocolPersonValuesFinder extends KeyValuesBase {

    private static final String PROTOCOL_ID_NAME = "protocolId";
    private static final String PROTOCOL_PERSON_ID_NAME = "protocolPersonId";
    private static final String PERSON_NAME_NAME = "personName";
    
    private Long protocolId;
    
    /**
     * Gets the keyvalue pair for {@link ProtocolPerson ProtocolPerson}.
     * The key is the protocolPersonId and the value is the type personName.
     * <p>
     * {@link #setProtocolId(Integer) setProtocolId(Integer)}
     * must be called with valid values before calling this method.
     * </p>
     * @return a list of {@link KeyValue KeyValue}
     */
    public List<KeyValue> getKeyValues() {   
        this.validateRequiredProperties();
        
        @SuppressWarnings("unchecked")
        final List<KeyValue> persons = this.createKeyValuesFinder().getKeyValues();
        return persons;
    }
    
    /**
     * Creates the {@link KeyValuesFinder KeyValuesFinder} that is used by this finder.
     * <p>
     * Default visibility for easier testing.
     * </p>
     * @return the {@link KeyValuesFinder KeyValuesFinder}
     */
    KeyValuesFinder createKeyValuesFinder() {
        ConditionValuesFinder<ProtocolPerson> condFinder
            = new ConditionValuesFinder<ProtocolPerson>();
        condFinder.setClazz(ProtocolPerson.class);
        condFinder.setKey(PROTOCOL_PERSON_ID_NAME);
        condFinder.setValue(PERSON_NAME_NAME);
        condFinder.setConditions(Collections.<String, Object>singletonMap(PROTOCOL_ID_NAME, this.getProtocolId()));
        return new PrefixValuesFinder(new SortedValuesFinder(condFinder));
    }
    
    /**
     * Gets the Protocol Id.
     * @return the Protocol Id
     */
    public Long getProtocolId() {
        return this.protocolId;
    }

    /**
     * Sets the Protocol Id.
     * @param protocolId the Protocol Id
     */
    public void setProtocolId(final Long protocolId) {
        this.protocolId = protocolId;
    }
    
    /**
     * Validates the the proper fields have been set on this object.
     * @throws IllegalStateException if this properties are invalid
     */
    private void validateRequiredProperties() {
        if (this.protocolId == null) {
            throw new IllegalStateException("the protocolId has not been set to a non-null value");
        }
    }
}
