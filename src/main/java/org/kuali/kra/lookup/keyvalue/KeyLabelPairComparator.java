/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.lookup.keyvalue;

import java.io.Serializable;
import java.util.Comparator;

import org.kuali.core.web.ui.KeyLabelPair;

/**
 * Compares two {@link KeyLabelPair KeyLabelPair} instances. Useful when sorting a list of for 
 *  a values finder
 *  
 *  @author $Author: gmcgrego $
 *  @version $Revision: 1.2 $
 */
public final class KeyLabelPairComparator implements Comparator<KeyLabelPair>, Serializable {
    
    private static final long serialVersionUID = -6968793748825904116L;
    private static final Comparator<KeyLabelPair> INSTANCE = new KeyLabelPairComparator();
    
    /**
     * Compares the label of <code>o1</code> to the label of </code>o2</code>.
     * 
     * {@inheritDoc}
     */
    public int compare(KeyLabelPair o1, KeyLabelPair o2) {
        return o1.getLabel().compareTo(o2.getLabel());
    }

    /**
     * Gets an instance of a {@link KeyLabelPairComparator KeyLabelPairComparator}.
     * @return an instance
     */
    public static final Comparator<KeyLabelPair> getInstance() {
        return INSTANCE;
    }
}

