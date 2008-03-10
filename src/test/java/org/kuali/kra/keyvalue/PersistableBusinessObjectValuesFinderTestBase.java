/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.KraTestBase;

public abstract class PersistableBusinessObjectValuesFinderTestBase extends KraTestBase {
    private Class valuesFinderClass;
    private Class businessObjectClass;
    private String keyAttributeName;
    private String labelAttributeName;
    private boolean includeKeyInDescription = false;
    protected List<KeyLabelPair> testKeyValues;
    
    public PersistableBusinessObjectValuesFinderTestBase() {
        testKeyValues = new ArrayList<KeyLabelPair>();
        addKeyValues();
    }
    
    protected void addKeyValue(String typeCode, String typeValue) {
        testKeyValues.add(new KeyLabelPair(typeCode, typeValue));
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
            assertEquals(testKeyValues.get(i).getLabel(), persistableBusinessObjectValuesFinder.getKeyLabel(testKeyValues.get(i).getKey().toString()));
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
