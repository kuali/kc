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
package org.kuali.kra.budget.rules;

import org.kuali.core.util.GlobalVariables;
import org.springframework.util.StringUtils;

/**
 * This class provides validation support
 */
public class ValidationHelper {
    private static final String REQUIRED_ERROR_KEY = "error.required";
    
    /**
     * This method checks a required Object field value and registers an error if the 
     * field value is null, or if a String, if field value is empty 
     * @param fieldValue
     * @param errorProperty
     * @param errorParms
     * @return True if field is valid according to required rules
     */
    public boolean checkRequiredField(Object fieldValue, String errorProperty, String... errorParms) {
        boolean isEmpty = isEmpty(fieldValue);
        if(isEmpty) {
            GlobalVariables.getErrorMap().putError(errorProperty, REQUIRED_ERROR_KEY, errorParms);
        }
        
        return !isEmpty; 
    }

    /**
     * This method checks if field is null, or if a String, empty
     * @param fieldValue
     * @return
     */
    private boolean isEmpty(Object fieldValue) {
        boolean empty = (fieldValue == null);
        if (!empty && fieldValue instanceof String) {
            String value = (String) fieldValue;
            empty = StringUtils.trimWhitespace(value).length() == 0;
        }
        return empty;
    }
}
