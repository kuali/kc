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
package org.kuali.kra.keyvalue;

import java.util.List;

import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

/**
 * This is a base class for ValuesFinder tests.
 *
 * To create a new ValuesFinder test class:
 * 1) extend this class
 * 2) override getKeyValues() to build the test set of key/value pairs
 * 3) override getTestClass() to set the value finder class to test
 * 
 * Note: If the concrete test class needs to be ignored then override testGetKeyValues
 * and add the @Ignore annotation
 */
public abstract class ValuesFinderTestBase extends KcUnitTestBase {
    
    protected static KeyValue createKeyValue(String typeCode, String typeValue) {
        return new ConcreteKeyValue(typeCode, typeValue);
    }

    /**
     * This method should be overridden by subclasses
     * to add the specific key/value pairs to test against.
     */
    protected abstract List<KeyValue> getKeyValues();

    /**
     * Gets the testClass attribute.
     * @return Returns the testClass.
     */
    protected abstract Class<? extends KeyValuesFinder> getTestClass();

    @Test
    public void testGetKeyValues() throws Exception {
        final KeyValuesFinder keyValuesFinder = getTestClass().newInstance();
        final List<KeyValue> keyValues = this.getKeyValues();
        
        assertEquals(keyValues.size(), keyValuesFinder.getKeyValues().size());
        for (int i=0; i<keyValues.size(); i++) {
            assertEquals(keyValues.get(i).getValue(), keyValuesFinder.getKeyLabel(keyValues.get(i).getKey().toString()));
        }
    }
}
