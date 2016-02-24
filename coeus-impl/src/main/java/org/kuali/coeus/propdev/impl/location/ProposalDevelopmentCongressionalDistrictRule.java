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
package org.kuali.coeus.propdev.impl.location;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;

import java.util.List;
import java.util.regex.Pattern;

/**
 * This class implements rule checks for adding and deleting congressional districts to a Proposal Site.
 */
public class ProposalDevelopmentCongressionalDistrictRule extends ProposalSiteRule {
    Pattern districtValidationPattern = Pattern.compile("^[a-zA-Z][a-zA-Z]-[0-9]{1,3}|[a-zA-Z][a-zA-Z]-all|00-000|US-all$");   // see Organization.xml

    private GlobalVariableService globalVariableService;

    /**
     * Checks that the site index is valid, and that a valid state code and district number has been entered.
     * @see org.kuali.coeus.propdev.impl.location.AddCongressionalDistrictRule#processAddCongressionalDistrictRules(org.kuali.coeus.propdev.impl.location.AddProposalCongressionalDistrictEvent)
     */
    public boolean processAddCongressionalDistrictRules(AddProposalCongressionalDistrictEvent addCongressionalDistrictEvent) {
    	boolean isValid = true;
        List<CongressionalDistrict> congressionalDistricts = addCongressionalDistrictEvent.getCongressionalDistricts();
        CongressionalDistrict  congressionalDistrict= addCongressionalDistrictEvent.getCongressionalDistrict();

        String stateCode = congressionalDistrict.getNewState();
        String districtNumber = congressionalDistrict.getNewDistrictNumber().toLowerCase();
        CongressionalDistrict newDistrict = new CongressionalDistrict();
        newDistrict.setCongressionalDistrict(stateCode, districtNumber);
            
        String districtString = newDistrict.getCongressionalDistrict();
        if (!districtValidationPattern.matcher(districtString).matches()) {
            getGlobalVariableService().getMessageMap().putErrorForSectionId(addCongressionalDistrictEvent.getCollectionId(), KeyConstants.ERROR_PROPOSAL_SITES_DISTRICT_INVALID_FORMAT,
                    addCongressionalDistrictEvent.getCollectionLabel(),congressionalDistrict.getCongressionalDistrict());
              isValid = false;
        }
        
        if (isValid) {
            isValid = checkUniqueness(addCongressionalDistrictEvent, congressionalDistrict);
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
    private boolean checkUniqueness(AddProposalCongressionalDistrictEvent addCongressionalDistrictEvent, CongressionalDistrict congressionalDistrict) {
        boolean isValid = true;
        
        CongressionalDistrict newDistrict = new CongressionalDistrict();
        newDistrict.setCongressionalDistrict(congressionalDistrict.getNewState(), congressionalDistrict.getNewDistrictNumber());
        
        for (CongressionalDistrict existingDistrict: addCongressionalDistrictEvent.getCongressionalDistricts()) {
            if (StringUtils.equals(newDistrict.getCongressionalDistrict(), existingDistrict.getCongressionalDistrict())) {
                getGlobalVariableService().getMessageMap().putErrorForSectionId(addCongressionalDistrictEvent.getCollectionId(), RiceKeyConstants.ERROR_DUPLICATE_ELEMENT,
            			addCongressionalDistrictEvent.getCollectionLabel(),congressionalDistrict.getCongressionalDistrict());
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

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null)
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
