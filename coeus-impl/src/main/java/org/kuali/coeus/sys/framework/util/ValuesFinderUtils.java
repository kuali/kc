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
