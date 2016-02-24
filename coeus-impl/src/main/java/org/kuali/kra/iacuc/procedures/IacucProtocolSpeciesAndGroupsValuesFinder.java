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
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IacucProtocolSpeciesAndGroupsValuesFinder extends FormViewAwareUifKeyValuesFinderBase {


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
