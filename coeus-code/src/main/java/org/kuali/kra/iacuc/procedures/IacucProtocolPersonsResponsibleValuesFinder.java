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
