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
package org.kuali.kra.irb.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceType;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

public class ProtocolReferenceTypeValuesFinder extends KeyValuesBase {
    KeyValueFinderService keyValueFinderService = (KeyValueFinderService) KraServiceLocator.getService("keyValueFinderService");

    /**
     * Constructs the list of Protocol Types. Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed by a user. The list is obtained from the PROTOCOL_TYPE
     * database table via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types. The first entry is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Collection protocolReferenceTypes = keyValuesService.findAllOrderBy(ProtocolReferenceType.class,
                "protocolReferenceTypeCode", true);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        for (Iterator iter = protocolReferenceTypes.iterator(); iter.hasNext();) {
            ProtocolReferenceType protocolReferenceType = (ProtocolReferenceType) iter.next();
            if (protocolReferenceType.isActive()) {
                keyValues.add(new ConcreteKeyValue(protocolReferenceType.getProtocolReferenceTypeCode().toString(),
                    protocolReferenceType.getDescription()));
            }
        }
        return keyValues;
    }


}
