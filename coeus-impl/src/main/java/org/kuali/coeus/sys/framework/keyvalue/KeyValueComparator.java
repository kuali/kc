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
package org.kuali.coeus.sys.framework.keyvalue;

import org.kuali.rice.core.api.util.KeyValue;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares two {@link KeyValue KeyValue} instances. Useful when sorting a list of for 
 *  a values finder
 *  
 *  @author $Author: gmcgrego $
 *  @version $Revision: 1.2 $
 */
public final class KeyValueComparator implements Comparator<KeyValue>, Serializable {
    
    private static final long serialVersionUID = -6968793748825904116L;
    private static final Comparator<KeyValue> INSTANCE = new KeyValueComparator();
    
    /**
     * Compares the label of <code>o1</code> to the label of </code>o2</code>.
     * 
     * {@inheritDoc}
     */
    public int compare(KeyValue o1, KeyValue o2) {
        return o1.getValue().compareTo(o2.getValue());
    }

    /**
     * Gets an instance of a {@link KeyValueComparator KeyValueComparator}.
     * @return an instance
     */
    public static Comparator<KeyValue> getInstance() {
        return INSTANCE;
    }
}

