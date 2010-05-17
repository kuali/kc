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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.web.struts.form.CongressionalDistrictHelper;

/**
 * This class implements rule checks for adding and deleting congressional districts to a Proposal Site.
 */
public class ProposalDevelopmentCongressionalDistrictRule extends ProposalSiteRule {
    Pattern districtValidationPattern = Pattern.compile("^[a-zA-Z][a-zA-Z]-[0-9]{1,3}|[a-zA-Z][a-zA-Z]-all|00-000|US-all$");   // see Organization.xml

    /**
     * Checks that the site index is valid, and that a valid state code and district number has been entered.
     * @see org.kuali.kra.proposaldevelopment.rule.AddCongressionalDistrictRule#processAddCongressionalDistrictRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent)
     */
    public boolean processAddCongressionalDistrictRules(AddProposalCongressionalDistrictEvent addCongressionalDistrictEvent) {
        String siteIndexStr = addCongressionalDistrictEvent.getSiteIndex();
        boolean isValid = isIndexValid(siteIndexStr, "Site Index");
        
        CongressionalDistrictHelper proposalSiteHelper = null;
        int siteIndex = -1;
        if (isValid) {
            siteIndex = new Integer(siteIndexStr);
            proposalSiteHelper = addCongressionalDistrictEvent.getCongressionalDistrictHelpers().get(siteIndex);

            String stateCode = proposalSiteHelper.getNewState();
            String districtNumber = proposalSiteHelper.getNewDistrictNumber();
            CongressionalDistrict newDistrict = new CongressionalDistrict();
            newDistrict.setCongressionalDistrict(stateCode, districtNumber);
            
            String districtString = newDistrict.getCongressionalDistrict();
            if (!districtValidationPattern.matcher(districtString).matches()) {
                reportError("newDistrictNumber", KeyConstants.ERROR_PROPOSAL_SITES_DISTRICT_INVALID_FORMAT);
                isValid = false;
            }
        }
        
        if (isValid) {
            isValid = checkUniqueness(addCongressionalDistrictEvent, proposalSiteHelper);
        }
        
        return isValid;
    }

    /**
     * This method tests whether the new district already exists for the Proposal Site.
     * It assumes the site index in addCongressionalDistrictEvent is valid.
     * @param addCongressionalDistrictEvent
     * @param proposalSiteHelper
     * @return
     */
    private boolean checkUniqueness(AddProposalCongressionalDistrictEvent addCongressionalDistrictEvent, CongressionalDistrictHelper proposalSiteHelper) {
        boolean isValid = true;
        
        CongressionalDistrict newDistrict = new CongressionalDistrict();
        newDistrict.setCongressionalDistrict(proposalSiteHelper.getNewState(), proposalSiteHelper.getNewDistrictNumber());
        
        for (CongressionalDistrict existingDistrict: addCongressionalDistrictEvent.getProposalSite().getCongressionalDistricts()) {
            if (StringUtils.equals(newDistrict.getCongressionalDistrict(), existingDistrict.getCongressionalDistrict())) {
                reportError("newDistrictNumber", KeyConstants.ERROR_PROPOSAL_SITES_DISTRICT_DUPLICATE);
                isValid = false;
            }
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
        
}
