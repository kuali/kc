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
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public abstract class PersistableBusinessObjectValuesFinderTestBase extends KcIntegrationTestBase {
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

    
    /**
     * This method should be overridden by subclasses
     * to add the specific key/value pairs to test against.
     */
    protected abstract void addKeyValues();

    @Test public void testGetKeyValues() throws Exception {
        PersistableBusinessObjectValuesFinder persistableBusinessObjectValuesFinder = (PersistableBusinessObjectValuesFinder) getValuesFinderClass().newInstance();        
        persistableBusinessObjectValuesFinder.setBusinessObjectClass(getBusinessObjectClass());
        persistableBusinessObjectValuesFinder.setIncludeKeyInDescription(isIncludeKeyInDescription());
        persistableBusinessObjectValuesFinder.setKeyAttributeName(getKeyAttributeName());
        persistableBusinessObjectValuesFinder.setLabelAttributeName(getLabelAttributeName());
        persistableBusinessObjectValuesFinder.getKeyValues();
        assertEquals("expected:\n" + testKeyValues + "\nactual:\n" + persistableBusinessObjectValuesFinder.getKeyValues(), testKeyValues.size(), persistableBusinessObjectValuesFinder.getKeyValues().size());
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
