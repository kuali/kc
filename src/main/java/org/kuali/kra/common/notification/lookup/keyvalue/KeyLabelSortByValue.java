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
package org.kuali.kra.common.notification.lookup.keyvalue;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.kuali.rice.core.util.KeyLabelPair;

/**
 * Provides a value finder for the Notification Type Recipient Role Namespace and Role name combination.  Has different
 * Comparator to sort by label instead of by key.
 */
public class KeyLabelSortByValue extends KeyLabelPair {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    public KeyLabelSortByValue(Object key, String label) {
         super(key, label);
    }

    @Override
    public int compareTo(KeyLabelPair o) {
        if (o == null) {
            throw new NullPointerException("the object to compare to is null");
        }
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(this.label, o.label, String.CASE_INSENSITIVE_ORDER);

        if ((this.key instanceof String) && (o.key instanceof String))
            builder.append(this.key, o.key, String.CASE_INSENSITIVE_ORDER);
        else {
            builder.append(this.key, o.key);
        }

        builder.append(this.numPaddedSpaces, o.numPaddedSpaces);
        return builder.toComparison();

    }
}
