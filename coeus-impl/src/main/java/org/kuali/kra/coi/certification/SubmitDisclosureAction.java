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
