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
package org.kuali.kra.coi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class is to server as a values finder for coi disclosure actions - 'Approve'/'Disapprove'/'Set Disclosure Status'
 */
public class CoiDisclosureStatusValuesFinder extends KeyValuesBase {
    private String actionType;
    private static final String APPROVE = "Approve";
    private static final String DISAPPROVE = "Disapprove";
    private static final String SETDISCLOSURESTATUS = "SetDisclosureStatus";

    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select "));
        for(CoiDisclosureStatus coiDisclosureStatus : getCoiDisclosureStatus()) {
            if (validStatus(coiDisclosureStatus.getCoiDisclosureStatusCode())) {
                keyValues.add(new ConcreteKeyValue(coiDisclosureStatus.getCoiDisclosureStatusCode(), coiDisclosureStatus.getDescription()));
            }
        }
        return keyValues;
    }

    /*
     * based on the parameter from jsp, and check if the status code should be included in keyvalue pairs list
     */
    private boolean validStatus(String statusCode) {
        boolean isValid;
        if (StringUtils.equals(APPROVE, getActionType())) {
            isValid = StringUtils.startsWith(statusCode, "2");
        } else if (StringUtils.equals(DISAPPROVE, getActionType())) {
            isValid = StringUtils.startsWith(statusCode, "3");
        } else {
            // for 'set disclosure status' action
            isValid = StringUtils.startsWith(statusCode, "1");
        }
        return isValid;
    }
    
    private List<CoiDisclosureStatus> getCoiDisclosureStatus() {
        
        return  (List<CoiDisclosureStatus>)KraServiceLocator.getService(BusinessObjectService.class).findAll(CoiDisclosureStatus.class);

    }
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }


}
