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
package org.kuali.kra.lookup.keyvalue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

/**
 * A {@link KeyValuesFinder KeyValuesFinder} that sort the {@link KeyValue KeyValue} returned from
 * {@link #getKeyValues()}.
 * 
 * <p>
 * This class is designed to wrap other KeyValuesFinders in order to add behavior.
 * </p>
 * for example:
 * <pre>
        PersistableBusinessObjectValuesFinder boFinder = new PersistableBusinessObjectValuesFinder();
        boFinder.setBusinessObjectClass(Foo.class);
        boFinder.setKeyAttributeName("foo");
        boFinder.setLabelAttributeName("bar");
        this.finder = new PrefixValuesFinder(new SortedValuesFinder(boFinder));
        .
        .
        .
        boFinder.getKeyValues();
   </pre>
 * 
 * Then just use the wrapped KeyValuesFinder within a custom finder.
 */
//TODO: we may consider creating a builder for these wrappers similar to Google's Collection builder
//in the Google Collections API
public final class SortedValuesFinder implements KeyValuesFinder {

    private static final Comparator<KeyValue> DEFAULT_COMPARATOR = KeyValueComparator.getInstance();
    
    private final KeyValuesFinder finder;
    private final Comparator<KeyValue> comparator; 
    
    /**
     * Wraps a {@link KeyValuesFinder KeyValuesFinder} and using the default {@link Comparator Comparator}.
     * @param finder the finder.
     * @see #getDefaultComparator()
     * @throws NullPointerException if the finder is null.
     */
    public SortedValuesFinder(final KeyValuesFinder finder) {
        this(finder, DEFAULT_COMPARATOR);
    }
    
    /**
     * Wraps a {@link KeyValuesFinder KeyValuesFinder} and using the passed in prefix value.
     * @param finder the finder.
     * @param comparator the comparator to use for sorting.
     * @throws NullPointerException if the finder or the comparator value is null.
     */
    public SortedValuesFinder(final KeyValuesFinder finder, final Comparator<KeyValue> comparator) {
        
        if (finder == null) {
            throw new NullPointerException("the finder is null");
        }
        
        if (comparator == null) {
            throw new NullPointerException("the comparator is null");
        }
        
        this.finder = finder;
        this.comparator = comparator;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getKeyLabel(final String key) {
        return this.finder.getKeyLabel(key);
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, String> getKeyLabelMap() {
        @SuppressWarnings("unchecked")
        final Map<String, String> map = this.finder.getKeyLabelMap();
        return map;
    }

    /**
     * {@inheritDoc}
     */
    public List<KeyValue> getKeyValues() {
        @SuppressWarnings("unchecked")
        final List<KeyValue> list = this.finder.getKeyValues();
        Collections.sort(list, this.comparator);
        return list;
    }
    
    /**
     * Gets the default {@link Comparator Comparator}.
     * @return the default {@link Comparator Comparator}
     */
    public static Comparator<KeyValue> getDefaultComparator() {
        return DEFAULT_COMPARATOR;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<KeyValue> getKeyValues(boolean includeActiveOnly) {
        @SuppressWarnings("unchecked")
        final List<KeyValue> list = this.finder.getKeyValues(includeActiveOnly);
        Collections.sort(list, this.comparator);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    public void clearInternalCache() {
        this.finder.clearInternalCache();
    }
}
