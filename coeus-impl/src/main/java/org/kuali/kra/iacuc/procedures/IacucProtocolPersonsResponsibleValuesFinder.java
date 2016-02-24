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
package org.kuali.kra.iacuc.procedures;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolPersonsResponsibleValuesFinder extends FormViewAwareUifKeyValuesFinderBase {


    private static final long serialVersionUID = -9014200066652039553L;

    @Override
    public List<KeyValue> getKeyValues() {
        Long protocolId = ((IacucProtocolDocument) getDocument()).getProtocol().getProtocolId();
        Map<String, Object> keyMap = new HashMap<String, Object> ();
        keyMap.put("protocolId", protocolId);
        List<IacucProtocolPerson> protocolPersons = (List<IacucProtocolPerson>)getKeyValuesService().findMatching(IacucProtocolPerson.class, keyMap);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (IacucProtocolPerson protocolPerson : protocolPersons) {
            String protocolPersonId = ObjectUtils.isNull(protocolPerson.getPersonId()) ? protocolPerson.getRolodexId().toString() : protocolPerson.getPersonId();
            String keyPersonIdAndName = protocolPersonId.concat("|").concat(protocolPerson.getPersonName());
            keyValues.add(new ConcreteKeyValue(keyPersonIdAndName, protocolPerson.getPersonName()));
        }
        return keyValues;
    }

    protected KeyValuesService getKeyValuesService() {
        return (KeyValuesService) KcServiceLocator.getService("keyValuesService");
    }
}
