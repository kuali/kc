/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * Find the Lead Units for a Proposal Development Document.  When a user
 * creates a proposal, they can only create proposals in units in which they
 * have the CREATE_PROPOSAL permission.  The Unit Authorization Service is
 * queried to determine which units the user has the CREATE_PROPOSAL permission.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class LeadUnitValuesFinder extends KeyValuesBase {
    
    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        String username = user.getPersonUserIdentifier();
        UnitAuthorizationService authService = KraServiceLocator.getService(UnitAuthorizationService.class);      
        List<Unit> userUnits = authService.getUnits(username, PermissionConstants.CREATE_PROPOSAL);

        // Sort the list of units by Unit Number.  If there are lots of units,
        // the sort will make it easier for the user to find when they view
        // the drop-down list in the web page.
        
        Collections.sort(userUnits, new Comparator() {
            public int compare(Object o1, Object o2) {
                Unit unit1 = (Unit) o1;
                Unit unit2 = (Unit) o2;
                return unit1.getUnitNumber().compareTo(unit2.getUnitNumber());
            }
        });
        
        for (Unit unit : userUnits) {
            keyValues.add(new KeyLabelPair(unit.getUnitNumber(), unit.getUnitNumber() + " - " + unit.getUnitName()));
        }

        return keyValues;
    }
}
