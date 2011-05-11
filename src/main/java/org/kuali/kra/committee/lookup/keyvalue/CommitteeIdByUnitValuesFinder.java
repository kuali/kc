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
package org.kuali.kra.committee.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.Contactable;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyLabelPairComparator;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class returns a list of committees that is filtered by the current
 * user's home unit.  Only committees that match the user's home unit or 
 * are part of a sub-unit of the home unit are returned. 
 * 
 */
public class CommitteeIdByUnitValuesFinder extends KeyValuesBase {

    /**
     * Returns the committees that the user is eligible to choose from.
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked" )
    public List getKeyValues() {
        Collection<Committee> committees = KraServiceLocator.getService(BusinessObjectService.class).findAll(Committee.class);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        if (CollectionUtils.isNotEmpty(committees)) {         
            Set<String> unitIds = getUserUnitIds();
            for (Committee committee : committees) {
                if (unitIds.contains(committee.getHomeUnit().getUnitNumber())) {
                    keyValues.add(new KeyLabelPair(committee.getCommitteeId(), committee.getCommitteeName()));
                }
            }

            Collections.sort(keyValues, new KeyLabelPairComparator());            
        }
        keyValues.add(0, new KeyLabelPair(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));

        return keyValues;
    }
    
    
    /**
     * 
     * This method returns the set of unit ids that the user is allowed to select a committee from
     * @return a set of unit ids that the user is allowed to select committees from
     */
    private Set<String> getUserUnitIds() {
        Set<String> unitIds = new HashSet<String>();
        
        String homeUnitId = getKcPerson().getUnit().getUnitNumber();
        unitIds.add(homeUnitId);
        List<Unit> subUnits = getUnitService().getAllSubUnits(homeUnitId);
        
        if(CollectionUtils.isNotEmpty(subUnits)) {
            for (Unit unit : subUnits) {
                unitIds.add(unit.getUnitNumber());
            }
        }
        
        return unitIds;
    }
    
    
    /**
     * Quick method to get the KcPersonService
     * @return KcPersonService reference
     */
    private KcPersonService getPersonService() {
        return KraServiceLocator.getService(KcPersonService.class);
    }
    
    /**
     * 
     * Quick method to get the UnitService
     * @return UnitService reference
     */
    private UnitService getUnitService() {
        return KraServiceLocator.getService(UnitService.class);
    }
    
    /**
     * 
     * This method uses the global user sessioin variable to get the logged in user.  From there
     * we use the principal id of the kim user to lookup the kc person.
     * @return the KcPerson reference
     */
    private Contactable getKcPerson() {  
        return getPersonService().getKcPersonByPersonId(GlobalVariables.getUserSession().getPerson().getPrincipalId()); 
    }

}
