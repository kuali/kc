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
