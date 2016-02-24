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
package org.kuali.kra.coi.certification;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.coi.actions.DisclosureActionHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is really just a "form" for submitting a protocol for review in the Submit for Review Action.
 */
public class SubmitDisclosureAction implements Serializable {

    private static final long serialVersionUID = -4712974868607781787L;

    private DisclosureActionHelper disclosureActionHelper;
    private boolean javascriptEnabled;
    
    /**
     * Constructs a SubmitDisclosureAction.
     * 
     * @param disclosureActionHelper Reference back to the action helper for this bean
     */
    public SubmitDisclosureAction(DisclosureActionHelper disclosureActionHelper) {
        this.disclosureActionHelper = disclosureActionHelper;
    }

    public DisclosureActionHelper getActionHelper() {
        return disclosureActionHelper;
    }

    public void setDisclosureActionHelper(DisclosureActionHelper disclosureActionHelper) {
        this.disclosureActionHelper = disclosureActionHelper;
    }

    public CoiDisclosure getDisclosure() {
        return getActionHelper().getCoiDisclosure();
    }

    public boolean getJavascriptEnabled() {
        return javascriptEnabled;
    }

    public void setJavascriptEnabled(boolean javascriptEnabled) {
        this.javascriptEnabled = javascriptEnabled;
    }

    public List<CoiUserRole> getReviewers() {
        List<CoiUserRole>list = new ArrayList<CoiUserRole>();
        for (CoiUserRole userRole: getDisclosure().getCoiUserRoles()) {
            if ("COI Reviewer".equals(userRole.getRoleName())) {
                list.add(userRole);
            }
        }        
        return list;
    }

}
