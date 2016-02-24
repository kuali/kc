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
package org.kuali.kra.keyvalue;

import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

import java.util.List;

import static org.junit.Assert.*;
/**
 * This is a base class for ValuesFinder tests.
 *
 * To create a new ValuesFinder test class:
 * 1) extend this class
 * 2) override getKeyValues() to build the test set of key/value pairs
 * 3) override getTestClass() to set the value finder class to test
 */
public abstract class ValuesFinderTestBase extends KcIntegrationTestBase {
    
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
    
    protected KeyValuesFinder getKeyValuesFinder() throws InstantiationException, IllegalAccessException {
    	return getTestClass().newInstance();
    }

    @Test
    public void testGetKeyValues() throws InstantiationException, IllegalAccessException {
        final KeyValuesFinder keyValuesFinder = getKeyValuesFinder();
        final List<KeyValue> keyValues = this.getKeyValues();
        
        assertEquals(keyValues.size(), keyValuesFinder.getKeyValues().size());
        for (int i=0; i<keyValues.size(); i++) {
            assertEquals(keyValues.get(i).getValue(), keyValuesFinder.getKeyLabel(keyValues.get(i).getKey().toString()));
        }
    }
}
