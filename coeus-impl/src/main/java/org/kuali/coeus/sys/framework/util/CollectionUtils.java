/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Yet another collection utility.  Contains methods that are not found in the apache util classes or the
 * jdk util classes.
 */
public final class CollectionUtils {

    /** private ctor. */
    private CollectionUtils() {
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
        if (!CollectionUtils.validIndexForList(index, fromList)) {
            return null;
        }
        
        return fromList.get(index);   
    }

    /**
     * Takes an array of keys and an array of values and constructs a map.  Both key and value
     * arrays must be the same length and non-null.
     *
     * @param keys the keys.  Cannot be null
     * @param values the values.  Cannot be null
     * @param <T> the key type
     * @param <U> the value type
     * @return a map.  cannot return null
     * @throws IllegalArgumentException if either argument is null or the arrays aren't the same length
     */
    public static <T, U> Map<T, U> zipMap(T[] keys, U[] values) {
        if (keys == null) {
            throw new IllegalArgumentException("keys is null");
        }

        if (values == null) {
            throw new IllegalArgumentException("values is null");
        }

        if (keys.length != values.length) {
            throw new IllegalArgumentException("Number of keys doesn't match number of values");
        }

        final Map<T, U> map = new HashMap<T, U>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }
}
