/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.io.Serializable;
import java.util.Comparator;

import org.displaytag.model.Cell;
import org.kuali.rice.kns.web.comparator.CellComparatorHelper;

/**
 * This class...
 */
public class NaturalStringCellComparator implements Serializable, Comparator<Object> {

    private static final long serialVersionUID = 7694287976357082369L;

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     * <p>
     * This implementation merely returns
     *  <code> compare((Cell)o1, (Cell)o2) </code>.

     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second. 
     * @exception ClassCastException the arguments cannot be cast to Cells.
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
        return this.compare((Cell)o1, (Cell)o2);
    }

    /**
     * Compares the contents of two arguments for order using 
     * {@link NaturalStringComparator}.  Returns a negative integer, zero, 
     * or a positive integer as the first argument is less than, equal to, 
     * or greater than the second.
     * 
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second. 
     *
     * @see NaturalStringComparator
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Cell c1, Cell c2) {
        if (c1 == c2) return 0;
        if (c1 == null) return -1;
        if (c2 == null) return 1;

        String s1 = CellComparatorHelper.getSanitizedStaticValue(c1);
        String s2 = CellComparatorHelper.getSanitizedStaticValue(c2);
        
        return NaturalStringComparator.getInstance().compare(s1, s2);
    }
}
