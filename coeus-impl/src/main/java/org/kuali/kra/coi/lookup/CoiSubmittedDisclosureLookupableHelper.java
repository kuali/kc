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
package org.kuali.kra.coi.lookup;

import org.drools.core.util.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CoiSubmittedDisclosureLookupableHelper extends CoiDisclosureLookupableHelperBase {
    

    private static final long serialVersionUID = 4999402404037030752L;
    //field name
    private static final String LEAD_UNIT = "leadUnitNumber";
    

    @Override
    public List<? extends BusinessObject> getLookupSpecificSearchResults(Map<String, String> fieldValues) {
        List<CoiDisclosure> allDisclosures = (List<CoiDisclosure>) super.getResults(fieldValues);
        List<CoiDisclosure> submittedDisclosures = new ArrayList<CoiDisclosure>();

        for (CoiDisclosure disclosure : allDisclosures) {
            if (disclosure.isSubmitted() && this.disclosureCanBeDisplayed(disclosure, fieldValues)) {
                submittedDisclosures.add(disclosure);
            }
        }
        return submittedDisclosures;
    }
    
    /**
     * This method determines whether the disclosure can be viewed by the current user.
     * Researchers should only see their own disclosures.  COI Admin should have unrestricted access.
     * @param rawDisclosure
     * @param fieldValues
     * @return true when current user is allowed to view the disclosure; false otherwise
     */
    private boolean disclosureCanBeDisplayed(CoiDisclosure rawDisclosure, Map<String, String> fieldValues) {
        boolean displayDisclosure = false;
        String researcherLeadUnit = fieldValues.get(LEAD_UNIT);
        if (rawDisclosure.getCoiDisclosureDocument() != null) {
            CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE, rawDisclosure);
            if (getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task) && 
                (StringUtils.isEmpty(researcherLeadUnit) || researcherLeadUnit.equals(rawDisclosure.getLeadUnitNumber()))) {
                
                displayDisclosure = true;
            }
        }        
        return displayDisclosure;
    }

    
    @Override
    protected boolean isAuthorizedForCoiLookups() {
        return true;
    }
    
}
