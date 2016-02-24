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
package org.kuali.kra.irb.protocol;

import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceType;
import org.kuali.kra.protocol.protocol.ProtocolReferenceTypeValuesFinderBase;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceTypeBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProtocolReferenceTypeValuesFinder extends ProtocolReferenceTypeValuesFinderBase {

    private static final long serialVersionUID = -3469947964740703859L;

    /**
     * Constructs the list of Protocol Types. Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed by a user. The list is obtained from the PROTOCOL_TYPE
     * database table via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types. The first entry is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("rawtypes")
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
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

    @Override
    protected Class<? extends ProtocolReferenceTypeBase> getProtocolReferenceTypeBOClassHook() {
        return ProtocolReferenceType.class;
    }


}
