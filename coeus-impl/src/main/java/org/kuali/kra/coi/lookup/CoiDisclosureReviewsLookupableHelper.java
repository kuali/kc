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
package org.kuali.kra.coi.lookup;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiReviewer;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CoiDisclosureReviewsLookupableHelper extends CoiDisclosureLookupableHelperBase {


    private static final long serialVersionUID = 5482769028074271782L;

    @Override
    public List<? extends BusinessObject> getLookupSpecificSearchResults(Map<String, String> fieldValues) {
        List<CoiDisclosure> allDisclosures = (List<CoiDisclosure>) super.getResults(fieldValues);
        List<CoiDisclosure> coiDisclosureReviews = new ArrayList<CoiDisclosure>();
        String currentUser = GlobalVariables.getUserSession().getPrincipalName();
        
        for (CoiDisclosure disclosure : allDisclosures) {
            List<CoiUserRole> userRoles = disclosure.getCoiUserRoles();
            for (CoiUserRole userRole : userRoles) {
                if (StringUtils.equalsIgnoreCase(userRole.getReviewerCode(), CoiReviewer.ASSIGNED_REVIEWER)) {
                    // userId is really the username . This should probably be "fixed" at some point.
                    if (StringUtils.equalsIgnoreCase(currentUser, userRole.getUserId()) && !userRole.isReviewCompleted()) {
                        coiDisclosureReviews.add(disclosure);
                    }
                }
            }
        }
        return coiDisclosureReviews;
    }
    
    @Override
    protected boolean isAuthorizedForCoiLookups() {
        return true;
    }
}
