/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import java.util.Comparator;

import org.kuali.core.web.ui.KeyLabelPair;

/**
 * Compares two <code>{@link KeyLabelPair}</code> instances. Useful when sorting a list of for 
 *  a values finder
 *  
 *  @author $Author: lprzybyl $
 *  @version $Revision: 1.1 $
 */
public class KeyLabelPairComparator implements Comparator<KeyLabelPair> {

    /**
     * Compares the label of <code>o1</code> to the label of </code>o2</code>
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(KeyLabelPair o1, KeyLabelPair o2) {
        return o1.getLabel().compareTo(o2.getLabel());
    }

}

