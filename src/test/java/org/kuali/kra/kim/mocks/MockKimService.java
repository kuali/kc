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
package org.kuali.kra.kim.mocks;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * This is the base class for all Mock KIM Services. 
 */
public abstract class MockKimService {
    
    protected MockKimDatabase database;
    
    public void setDatabase(MockKimDatabase database) {
        this.database = database;
    }
    
    /**
     * Is the attributes a subset of the qualifiedAttributes?
     * @param attributes
     * @param qualifiedAttributes
     * @return
     */
    protected boolean partialMatch(Map<String, String> attributes, Map<String, String> qualifiedAttributes) {
        if (attributes != null) {
            Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                if (!contains(qualifiedAttributes, entry.getKey(), entry.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Does the map of qualifiedAttributes contain the given attrName/attrValue pair?
     * @param qualifiedAttributes
     * @param attrName
     * @param attrValue
     * @return
     */
    private boolean contains(Map<String, String> qualifiedAttributes, String attrName, String attrValue) {
        Iterator<Entry<String, String>> iterator = qualifiedAttributes.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            if (StringUtils.equals(entry.getKey(), attrName) &&
                StringUtils.equals(entry.getValue(), attrValue)) {
                return true;
            }
        }
        return false;
    }
}
