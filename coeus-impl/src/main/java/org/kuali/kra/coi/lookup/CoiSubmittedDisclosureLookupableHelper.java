/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.coi.lookup;

import org.apache.commons.lang3.StringUtils;
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
                (StringUtils.isBlank(researcherLeadUnit) || researcherLeadUnit.equals(rawDisclosure.getLeadUnitNumber()))) {
                
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
