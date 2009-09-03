/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceHelper {
    private static final String FIEL_NAME_FIELD_VALUE_SIZE_MISMATCH = "Number of field names doesn't match number of field values";
    private static final ServiceHelper instance;
    
    static {
        instance = new ServiceHelper();
    }
    private ServiceHelper() {
        
    }
    
    public static ServiceHelper getInstance() {
        return instance;
    }
    
    /**
     * This method builds a search map from a single field
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public Map<String, Object> buildCriteriaMap(String fieldName, Object fieldValue) {
        Map<String, Object> fieldsToMatch = new HashMap<String, Object>();
        fieldsToMatch.put(fieldName, fieldValue);
        return fieldsToMatch;
    }
    
    /**
     * This method builds a search map from a single field
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public Map<String, Object> buildCriteriaMap(String[] fieldNames, Object[] fieldValues) {
        if(fieldNames.length != fieldValues.length) {
            throw new IllegalArgumentException(FIEL_NAME_FIELD_VALUE_SIZE_MISMATCH);
        }
        Map<String, Object> fieldsToMatch = new HashMap<String, Object>();
        for(int i = 0; i < fieldNames.length; i++) {
            fieldsToMatch.put(fieldNames[i], fieldValues[i]);
        }
        return fieldsToMatch;
    }
}
