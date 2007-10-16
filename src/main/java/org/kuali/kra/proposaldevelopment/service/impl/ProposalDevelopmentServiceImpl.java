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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService; // This is until boService injection works


public class ProposalDevelopmentServiceImpl implements ProposalDevelopmentService {
    private BusinessObjectService businessObjectService;
    
    public Map<String, String> getUnitsForUser(String userId) {
        Map<String, String> userUnits = new HashMap<String, String>();
        Collection<Unit> units = getUnitsWithNumbers("IN-PERS", "BL-IIDC");
        
        for (Unit unit : units) {
            userUnits.put(unit.getUnitNumber(), 
                          unit.getUnitNumber() + " - " + unit.getUnitName());
        }
        
        return userUnits;
    }
    
    /**
     * Gets units for the given names. Useful when you know what you want.
     *
     * @param numbers varargs representation of unitNumber array
     * @return Collection<Unit>
     */
    private Collection<Unit> getUnitsWithNumbers(String ... numbers) {
        Collection<Unit> retval = new ArrayList<Unit>();

        for (String unitNumber : numbers) {
            Map query_map = new HashMap();
            query_map.put("unitNumber", unitNumber);
            retval.add((Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, query_map));
        }
        
        return retval;
    }
        
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class); // Just until boService injection is working
        // return businessObjectService;
    }

}
