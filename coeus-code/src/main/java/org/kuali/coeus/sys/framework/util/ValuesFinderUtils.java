/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.framework.util;

import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;

public class ValuesFinderUtils {

    /** private ctor. */
    private ValuesFinderUtils() {
        throw new UnsupportedOperationException("do not call");
    }

    private static final String SEMICOLON_AS_DELIMITOR = ";";
    private static final String COMMA_AS_DELIMITOR = ",";
    
    /**
     * 
     * This method processes a list of KeyValue and converts them to a string separated
     * by semi-colons and comas.
     * This is used in both getFrequencyCodes and getFrequencyBaseCodes services.
     *  
     * @param KeyValueList
     * @return
     */
    public static String processKeyValueList(List<KeyValue> KeyValueList){
        
        StringBuilder strBuilder = new StringBuilder();
        
        int lastElementIndex = KeyValueList.size()-1;
        
        for(int i = 0; i < lastElementIndex; i++){
            strBuilder.append(KeyValueList.get(i).getKey());
            strBuilder.append(SEMICOLON_AS_DELIMITOR);
            strBuilder.append(KeyValueList.get(i).getValue());
            strBuilder.append(COMMA_AS_DELIMITOR);
        }
        
        strBuilder.append(KeyValueList.get(lastElementIndex).getKey());
        strBuilder.append(SEMICOLON_AS_DELIMITOR);
        strBuilder.append(KeyValueList.get(lastElementIndex).getValue());
        
        return strBuilder.toString();
    }
}