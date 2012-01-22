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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;

public abstract class PersistableBusinessObjectValuesFinderTestBase extends KcUnitTestBase {
    private Class valuesFinderClass;
    private Class businessObjectClass;
    private String keyAttributeName;
    private String labelAttributeName;
    private boolean includeKeyInDescription = false;
    protected List<KeyValue> testKeyValues;
    
    public PersistableBusinessObjectValuesFinderTestBase() {
        testKeyValues = new ArrayList<KeyValue>();
        addKeyValues();
    }
    
    protected void addKeyValue(String typeCode, String typeValue) {
        testKeyValues.add(new ConcreteKeyValue(typeCode, typeValue));
    }
    
    /**
     * This method should be overridden by subclasses
     * to add the specific key/value pairs to test against.
     */
    protected abstract void addKeyValues();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test public void testGetKeyValues() throws Exception {
        PersistableBusinessObjectValuesFinder persistableBusinessObjectValuesFinder = (PersistableBusinessObjectValuesFinder) getValuesFinderClass().newInstance();        
        persistableBusinessObjectValuesFinder.setBusinessObjectClass(getBusinessObjectClass());
        persistableBusinessObjectValuesFinder.setIncludeKeyInDescription(isIncludeKeyInDescription());
        persistableBusinessObjectValuesFinder.setKeyAttributeName(getKeyAttributeName());
        persistableBusinessObjectValuesFinder.setLabelAttributeName(getLabelAttributeName());
        persistableBusinessObjectValuesFinder.getKeyValues();
        assertEquals(testKeyValues.size(), persistableBusinessObjectValuesFinder.getKeyValues().size());
        for (int i=0; i<testKeyValues.size(); i++) {
            assertEquals(testKeyValues.get(i).getValue(), persistableBusinessObjectValuesFinder.getKeyLabel(testKeyValues.get(i).getKey().toString()));
        }
    }
    
    public Class getValuesFinderClass() {
        return valuesFinderClass;
    }
    public void setValuesFinderClass(Class valuesFinderClass) {
        this.valuesFinderClass = valuesFinderClass;
    }
    public Class getBusinessObjectClass() {
        return businessObjectClass;
    }
    public void setBusinessObjectClass(Class businessObjectClass) {
        this.businessObjectClass = businessObjectClass;
    }
    public String getKeyAttributeName() {
        return keyAttributeName;
    }
    public void setKeyAttributeName(String keyAttributeName) {
        this.keyAttributeName = keyAttributeName;
    }
    public String getLabelAttributeName() {
        return labelAttributeName;
    }
    public void setLabelAttributeName(String labelAttributeName) {
        this.labelAttributeName = labelAttributeName;
    }
    public boolean isIncludeKeyInDescription() {
        return includeKeyInDescription;
    }
    public void setIncludeKeyInDescription(boolean includeKeyInDescription) {
        this.includeKeyInDescription = includeKeyInDescription;
    }
}
