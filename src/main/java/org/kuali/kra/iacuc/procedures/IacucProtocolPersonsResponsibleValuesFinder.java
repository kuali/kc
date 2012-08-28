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
package org.kuali.kra.iacuc.procedures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolPersonsResponsibleValuesFinder extends KeyValuesBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -9014200066652039553L;

    @Override
    public List<KeyValue> getKeyValues() {
        Long protocolId = ((IacucProtocolForm) KNSGlobalVariables.getKualiForm()).getIacucProtocolDocument().getProtocol().getProtocolId();
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
        return (KeyValuesService) KraServiceLocator.getService("keyValuesService");
    }
}
