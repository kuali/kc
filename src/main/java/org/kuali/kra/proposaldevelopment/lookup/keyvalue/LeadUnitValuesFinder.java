/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;

public class LeadUnitValuesFinder extends KeyValuesBase {
    
    public List getKeyValues() {
        
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select:"));
        
        //TODO: Refactor this when we get SpringContext object (like KFS)
        ProposalDevelopmentService proposalDevelopmentService = (ProposalDevelopmentService) KraServiceLocator.getService("proposalDevelopmentService");
        
        // TODO gmcgrego: temp fix for cnv build while investigating why propDevService is null there
        Map<String, String> userUnits = new HashMap<String, String>();
        if (proposalDevelopmentService != null) {
            userUnits = proposalDevelopmentService.getUnitsForUser(GlobalVariables.getUserSession().getLoggedInUserNetworkId());
        } else {
            userUnits.put("IN-PERS", 
                    "IN-PERS - PED - EMERGENCY ROOM SERVICES");
        }
        
        for (String key : userUnits.keySet()) {
            keyValues.add(new KeyLabelPair(key, userUnits.get(key)));
        }
        
        return keyValues;
    }
}
