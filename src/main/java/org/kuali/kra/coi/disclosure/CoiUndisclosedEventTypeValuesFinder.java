/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.coi.disclosure;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.DisclosureEventTypeConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * 
 * This class creates a drop down list for Event type for undisclosed events criteria.
 */
public class CoiUndisclosedEventTypeValuesFinder extends KeyValuesBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2596141537553819058L;

    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (DisclosureEventTypeConstants eventType : DisclosureEventTypeConstants.values()) {
            keyValues.add(new ConcreteKeyValue(eventType.code(), eventType.description()));
        }
        return keyValues;
    }
}