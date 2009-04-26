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
package org.kuali.kra.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This is a base class for ValuesFinder tests.
 *
 * To create a new ValuesFinder test class:
 * 1) extend this class
 * 2) override addKeyValues() to build the test set of key/value pairs
 * 3) add a call to setTestClass() in the constructor of the new class
 * 4) create a test method corresponding to every test method in this class
 *    (ex: testGetKeyValues()) that calls into this class to perform the
 *    actual test.
 */
public abstract class ValuesFinderTestBase extends KraTestBase {

    protected List<KeyLabelPair> testKeyValues;
    private Class testClass;

    public ValuesFinderTestBase() {
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
        KeyValuesBase keyValuesBase = (KeyValuesBase) getTestClass().newInstance();
        assertEquals(testKeyValues.size(), keyValuesBase.getKeyValues().size());
        for (int i=0; i<testKeyValues.size(); i++) {
            assertEquals(testKeyValues.get(i).getLabel(), keyValuesBase.getKeyLabel(testKeyValues.get(i).getKey().toString()));
        }
    }

    /**
     * Sets the testClass attribute value.
     * @param testClass The testClass to set.
     */
    public void setTestClass(Class testClass) {
        this.testClass = testClass;
    }

    /**
     * Gets the testClass attribute.
     * @return Returns the testClass.
     */
    public Class getTestClass() {
        return testClass;
    }

}
