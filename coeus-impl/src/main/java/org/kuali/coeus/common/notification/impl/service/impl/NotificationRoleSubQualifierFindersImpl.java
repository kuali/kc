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
package org.kuali.coeus.common.notification.impl.service.impl;

import org.kuali.coeus.common.notification.impl.service.NotificationRoleSubQualifierFinders;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRoleSubQualifierFindersImpl implements NotificationRoleSubQualifierFinders {

    private Map<String, KeyValuesFinder> finders;

    public Map<String, KeyValuesFinder> getFinders() {
        return finders;
    }
    
    public void setFinders(Map<String, KeyValuesFinder> finders) {
        this.finders = finders;
    }
    
    public List<KeyValue> getKeyValuesForRole(String roleName) {
        KeyValuesFinder finder = getFinders().get(roleName);
        if (finder != null) {
            return asKeyValueList(finder.getKeyLabelMap());
        } else {
            return new ArrayList<KeyValue>();
        }
    }
    
    public List<KeyValue> getKeyValuesForAllRoles() {
        Map<String, String> result = new HashMap<String, String>();
        for (Map.Entry<String, KeyValuesFinder> entry : getFinders().entrySet()) {
            result.putAll(entry.getValue().getKeyLabelMap());
        }

        return asKeyValueList(result);
    }
    
    protected List<KeyValue> asKeyValueList(Map<String, String> values) {
        values.remove("");
        values.put("", "All");
        List<KeyValue> asList = new ArrayList<KeyValue>();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            asList.add(new ConcreteKeyValue(entry.getKey(), entry.getValue()));
        }
        return asList;
    }
}
