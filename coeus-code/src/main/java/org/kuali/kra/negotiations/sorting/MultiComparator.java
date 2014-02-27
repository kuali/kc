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
package org.kuali.kra.negotiations.sorting;

import java.util.Comparator;

/**
 * Comparator that takes a list of comparators and uses each in turn and returns the first non-zero comparison.
 * @param <T>
 */
public class MultiComparator<T> implements Comparator<T> {
    
    private final Comparator<T>[] comparators;
    
    public MultiComparator(Comparator<T>[] comparators) {
        this.comparators = comparators;
    }
    
    @Override
    public int compare(T o1, T o2) {
        for (Comparator<T> comparator : comparators) {
            int comparison = comparator.compare(o1, o2);
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;        
    }
}
