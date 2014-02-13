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
package org.kuali.kra.iacuc.procedures;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.krad.migration.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class...
 */
public class IacucProtocolSpeciesAndGroupsValuesFinder extends FormViewAwareUifKeyValuesFinderBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1095033401204774650L;

    /**
     * Constructs the list of Iacuc Protocol Species. Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * protocol species id and the "value" is the textual description of species that is viewed by a user. 
     * The list is obtained from the IACUC_PROTOCOL_SPECIES
     * database table via the "KeyValuesService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types. The first entry is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        
        Long protocolId = ((IacucProtocolDocument) getDocument()).getProtocol().getProtocolId();
        Map<String, Object> keyMap = new HashMap<String, Object> ();
        keyMap.put("protocolId", protocolId);
        List<IacucProtocolSpecies> protocolSpeciesList = (List<IacucProtocolSpecies>)getKeyValuesService().findMatching(IacucProtocolSpecies.class, keyMap);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (IacucProtocolSpecies protocolSpecies : protocolSpeciesList) {
            keyValues.add(new ConcreteKeyValue(protocolSpecies.getIacucProtocolSpeciesId().toString(), protocolSpecies.getGroupAndSpecies()));
        }
        return keyValues;
    }
    
    
    protected KeyValuesService getKeyValuesService() {
        return (KeyValuesService) KcServiceLocator.getService("keyValuesService");
    }
}
