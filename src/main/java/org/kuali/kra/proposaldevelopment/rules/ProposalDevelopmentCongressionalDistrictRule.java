/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.State;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.web.struts.form.CongressionalDistrictHelper;

/**
 * This class implements rule checks for adding and deleting congressional districts to a Proposal Site.
 */
public class ProposalDevelopmentCongressionalDistrictRule extends ProposalSiteRule {

    /**
     * Checks that the site index is valid, and that a valid state code and district number has been entered.
     * @see org.kuali.kra.proposaldevelopment.rule.AddCongressionalDistrictRule#processAddCongressionalDistrictRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent)
     */
    public boolean processAddCongressionalDistrictRules(AddProposalCongressionalDistrictEvent addCongressionalDistrictEvent) {
        String siteIndexStr = addCongressionalDistrictEvent.getSiteIndex();
        boolean isValid = isIndexValid(siteIndexStr, "Site Index");
        
        CongressionalDistrictHelper proposalSiteHelper = null;
        if (isValid) {
            int siteIndex = new Integer(siteIndexStr);
            proposalSiteHelper = addCongressionalDistrictEvent.getProposalSiteHelpers().get(siteIndex);
        }

        if (isValid) {
            String stateCode = proposalSiteHelper.getNewState();
            isValid = isStateCodeValid(stateCode);
        }

        if (isValid) {
            String districtNumber = proposalSiteHelper.getNewDistrictNumber();
            isValid &= isDistrictNumberValid(districtNumber);
        }

        return isValid;
    }

    /**
     * Checks that site index and district index are valid.
     * @param deleteCongressionalDistrictEvent
     * @return
     */
    public boolean processDeleteCongressionalDistrictRules(DeleteProposalCongressionalDistrictEvent deleteCongressionalDistrictEvent) {
        String siteIndexStr = deleteCongressionalDistrictEvent.getSiteIndex();
        String districtIndexStr = deleteCongressionalDistrictEvent.getDistrictIndex();
        
        return isIndexValid(siteIndexStr, "Site Index") && isIndexValid(districtIndexStr, "District Index");
    }
        
    private boolean isStateCodeValid(String stateCode) {
        Map<String,String> fieldValues = new HashMap<String,String>();
        fieldValues.put("stateCode", stateCode);
        if (getBusinessObjectService().countMatching(State.class, fieldValues) < 1) {
            reportError("newPropLocation.location", KeyConstants.ERROR_PROPOSAL_SITES_STATE_CODE_INVALID);
            return false;
        }
        else {
            return true;
        }
    }
    
    private boolean isDistrictNumberValid(String districtNumber) {
        boolean isValid = true;
        
        try {
            if (Integer.parseInt(districtNumber) < 1) {
                reportError("newPropLocation.location", KeyConstants.ERROR_PROPOSAL_SITES_DISTRICT_NUMBER_LESS_THAN_ONE, "District Number");
                isValid = false;
            }
        }
        catch (NumberFormatException e) {
            reportError("newPropLocation.location", KeyConstants.ERROR_PROPOSAL_SITES_DISTRICT_NUMBER_INVALID_FORMAT, "The value " + districtNumber + " for District Number");
            isValid = false;
        }
        
        return isValid;
    }
}