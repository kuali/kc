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

import java.util.List;

import org.kuali.kra.common.notification.service.NotificationRoleSubQualifierFinders;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class NotificationRoleSubQualifierValuesFinder extends KeyValuesBase {
    
    private static final long serialVersionUID = 8109336340804375108L;
    private NotificationRoleSubQualifierFinders finders;

    @Override
    public List<KeyValue> getKeyValues() {
        return getFinders().getKeyValuesForAllRoles();
    }

    protected NotificationRoleSubQualifierFinders getFinders() {
        if (finders == null) {
            finders = KraServiceLocator.getService(NotificationRoleSubQualifierFinders.class);
        }
        return finders;
    }

    public void setFinders(NotificationRoleSubQualifierFinders finders) {
        this.finders = finders;
    }
    
    

}
