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
package org.kuali.coeus.common.notification.impl.lookup.keyvalue;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.kuali.rice.core.api.util.AbstractKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

/**
 * Provides a value finder for the Notification Type Recipient Role Namespace and Role name combination.  Has different
 * Comparator to sort by label instead of by key.
 */
public class KeyLabelSortByValue extends AbstractKeyValue implements Comparable<KeyValue> {
    

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
