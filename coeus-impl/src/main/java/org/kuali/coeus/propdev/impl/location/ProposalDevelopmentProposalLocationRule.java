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
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

import java.util.List;

public class ProposalDevelopmentProposalLocationRule extends KcTransactionalDocumentRuleBase implements AddProposalSiteRule, SaveProposalSitesRule {
    private static final String LOCATION_NAME_PROPERTY = "locationName";
    private static final String ADDRESS_NAME_PROPERTY = "address";

    /**
     * This method validates location names + organizations when a Proposal Site is added.
     * @see org.kuali.coeus.propdev.impl.location.AddProposalSiteRule#processAddProposalSiteBusinessRules(org.kuali.coeus.propdev.impl.location.AddProposalSiteEvent)
     * @param addProposalSiteEvent
     * @return
     */
    public boolean processAddProposalSiteBusinessRules(AddProposalSiteEvent addProposalSiteEvent) {
        ProposalSite proposalSite = addProposalSiteEvent.getProposalSite();

        boolean rulePassed = checkLocationName(proposalSite, LOCATION_NAME_PROPERTY);

        // an address is required for all location types except performance sites
        boolean isPerformanceSite = proposalSite.getLocationTypeCode()==ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE;
        if (proposalSite.getOrganization()==null && proposalSite.getRolodex()==null && !isPerformanceSite) {
            rulePassed = false;
            reportError(ADDRESS_NAME_PROPERTY, KeyConstants.ERROR_PROPOSAL_SITES_ADDRESS_REQUIRED);
        }

        return rulePassed;
    }

    /**
     * This method validates editable location names.
     * @param saveProposalSiteEvent
     * @return
     */
    public boolean processSaveProposalSiteBusinessRules(SaveProposalSitesEvent saveProposalSiteEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)saveProposalSiteEvent.getDocument();
        DevelopmentProposal developmentProposal = document.getDevelopmentProposal();
        boolean isValid = true;
        
        List<ProposalSite> performanceSites = developmentProposal.getPerformanceSites();
        for (int i=0; i<performanceSites.size(); i++) {
            isValid &= checkLocationName(performanceSites.get(i), "performanceSites[" + i + "].locationName");
        }
        
        List<ProposalSite> otherOrganizations = developmentProposal.getOtherOrganizations();
        for (int i=0; i<otherOrganizations.size(); i++) {
            isValid &= checkLocationName(otherOrganizations.get(i), "otherOrganizations[" + i + "].locationName");
        }
        
        return isValid;
    }
    
    // check that the location name is not blank
    private boolean checkLocationName(ProposalSite proposalSite, String propertyName) {
        boolean isValid = true;
        
        if (StringUtils.isBlank(proposalSite.getLocationName())) {
            isValid = false;
            reportError(propertyName, KeyConstants.ERROR_PROPOSAL_SITES_LOCATION_NAME_REQUIRED);
        }
        
        return isValid;
    }
}
