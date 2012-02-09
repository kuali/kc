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
package org.kuali.kra.common.notification.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.common.notification.service.NotificationRoleSubQualifierFinders;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

public class NotificationRoleSubQualifierFindersImpl implements NotificationRoleSubQualifierFinders {

    private Map<String, KeyValuesFinder> finders;
    
    @Override
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
