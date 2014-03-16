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
package org.kuali.coeus.common.notification.impl.lookup.keyvalue;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.kuali.rice.core.api.util.AbstractKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

/**
 * Provides a value finder for the Notification Type Recipient Role Namespace and Role name combination.  Has different
 * Comparator to sort by label instead of by key.
 */
public class KeyLabelSortByValue extends AbstractKeyValue implements Comparable<KeyValue> {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    public KeyLabelSortByValue(String key, String label) {
         super(key, label);
    }

    @Override
    public int compareTo(KeyValue o) {
        if (o == null) {
            throw new NullPointerException("the object to compare to is null");
        }
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(this.getValue(), o.getValue(), String.CASE_INSENSITIVE_ORDER);

        if ((this.getKey() instanceof String) && (o.getKey() instanceof String))
            builder.append(this.getKey(), o.getKey(), String.CASE_INSENSITIVE_ORDER);
        else {
            builder.append(this.getKey(), o.getKey());
        }

        return builder.toComparison();

    }
}
