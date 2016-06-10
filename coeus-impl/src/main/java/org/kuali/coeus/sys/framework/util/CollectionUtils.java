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

import org.kuali.rice.krad.lookup.CollectionIncomplete;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    /**
     * Takes a collection and creates a new CollectionIncomplete if needed based upon if CollectionIncomplete is passed in,
     * else create a new Array List
     *
     * @param collection the original collection.
     * @param <T> the list type
     * @return the correct implementation for the collection passed in
     */

    public static <T> List<T> createCorrectImplementationForCollection(Collection<T> collection) {
        if (collection instanceof CollectionIncomplete) {
            return new CollectionIncomplete<>(new ArrayList<>(),((CollectionIncomplete) collection).getActualSizeIfTruncated());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Creates an entry from a key and value.
     * @param key the key. Can be null.
     * @param value the value.  Can be null.
     * @param <K> the key type
     * @param <V> the value type
     * @return a Map.Entry
     */
    public static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleEntry<K, V>(key, value);
    }

    /**
     * Convenience method to a return a Collector that converts an Map.Entry to a Map.
     * @param <K> the key type
     * @param <U> the value type
     * @return A Collector from Map.Entry to Map
     */
    public static <K, U> Collector<Map.Entry<K, U>, ?, Map<K, U>> entriesToMap() {
        return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);
    }
    
    /**
     * Convenience method to a return a Collector that converts an Map.Entry to a Map.
     * @param <K> the key type
     * @param <U> the value type
     * @return A Collector from Map.Entry to Map
     */
    public static <K, U> Collector<Map.Entry<K, U>, ?, Map<K, U>> nullSafeEntriesToMap() {
        return nullSafeToMap(Map.Entry::getKey, Map.Entry::getValue);
    }
    
    /**
     * Null-safe replacement for Collectors.toMap. Allows null keys and values. 
     * If a duplicate key is found, throws IllegalStateException
     * @param keyMapper function to get the key from the items
     * @param valueMapper function to get the value from the items
     * @return A Collector from List to Map
     */
    public static <T, K, U> Collector<T, ?, Map<K, U>> nullSafeToMap(Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends U> valueMapper) {
    	return Collectors.collectingAndThen(
	        Collectors.toList(),
	        list -> {
	            Map<K, U> result = new HashMap<>();
	            for (T item : list) {
	                K key = keyMapper.apply(item);
	                if (result.putIfAbsent(key, valueMapper.apply(item)) != null) {
	                    throw new IllegalStateException(String.format("Duplicate key %s", key));
	                }
	            }
	            return result;
	        }
    	);
    }

    /**
     * A predicate that determines whether a key has been seen previously.  This allows
     * @param keyExtractor which determines which key to use.
     * @param <T> the type of object the predicate evaluates
     * @return a stateful predicate
     * @throws IllegalArgumentException if the key extractor is null
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        if (keyExtractor == null) {
            throw new IllegalArgumentException("the keyExtractor must not be null");
        }

        final Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
