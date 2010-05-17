/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.util;

import java.util.List;

/**
 * Yet another collection utility.  Contains methods that are not found in the apache util classes or the
 * jdk util classes.
 */
public final class CollectionUtil {

    /** private ctor. */
    private CollectionUtil() {
        throw new UnsupportedOperationException("do not call");
    }
    
    /**
     * Checks if a given index is valid for a given list. This method returns null if the list is null.
     * 
     * @param index the index
     * @param forList the list
     * @return true if a valid index
     */
    public static boolean validIndexForList(final int index, final List<?> forList) {      
        return forList != null && index >= 0 && index <= forList.size() - 1;
    }
    
    /**
     * Gets an element from a list based on the index.  If the index is invalid null will be returned.
     * @param <T> the type of List
     * @param index the index
     * @param fromList the list to retrieve from
     * @return the element
     */
    public static <T> T getFromList(final int index, final List<T> fromList) {
        if (!CollectionUtil.validIndexForList(index, fromList)) {
            return null;
        }
        
        return fromList.get(index);   
    }
}
