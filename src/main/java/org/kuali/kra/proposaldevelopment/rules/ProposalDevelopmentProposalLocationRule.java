/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSiteRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveProposalSitesEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProposalDevelopmentProposalLocationRule extends ResearchDocumentRuleBase implements AddProposalSiteRule, SaveProposalSitesRule {
    private static final String LOCATION_NAME_PROPERTY = "locationName";
    private static final String ADDRESS_NAME_PROPERTY = "address";

    /**
     * This method validates location names + organizations when a Proposal Site is added.
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalSiteRule#processAddProposalSiteBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent)
     * @param addProposalSiteEvent
     * @return
     */
    public boolean processAddProposalSiteBusinessRules(AddProposalSiteEvent addProposalSiteEvent) {
        ProposalSite proposalSite = addProposalSiteEvent.getProposalSite();
        boolean rulePassed = true;

        rulePassed = checkLocationName(proposalSite);
        if (StringUtils.isBlank(proposalSite.getLocationName())) {
            rulePassed = false;
            reportError(LOCATION_NAME_PROPERTY, KeyConstants.ERROR_PROPOSAL_SITES_LOCATION_NAME_REQUIRED);
        }

        if (proposalSite.getOrganization()==null && proposalSite.getRolodex()==null) {
            rulePassed = false;
            reportError(ADDRESS_NAME_PROPERTY, KeyConstants.ERROR_PROPOSAL_SITES_ADDRESS_REQUIRED);
        }

        return rulePassed;
    }

    /**
     * This method validates location names on the first save.
     * @param saveProposalSiteEvent
     * @return
     */
    public boolean processSaveProposalSiteBusinessRules(SaveProposalSitesEvent saveProposalSiteEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)saveProposalSiteEvent.getDocument();
        DevelopmentProposal developmentProposal = document.getDevelopmentProposal();
        List<ProposalSite> proposalSites = developmentProposal.getProposalSites();
        
        boolean isValid = true;
        
        if (StringUtils.isEmpty(developmentProposal.getProposalNumber())) {
            for (ProposalSite site: proposalSites) {
                isValid &= checkLocationName(site);
            }
        }
        
        return isValid;
    }
    
    // check that location name is not blank
    private boolean checkLocationName(ProposalSite proposalSite) {
        boolean isValid = true;
        
        if (StringUtils.isBlank(proposalSite.getLocationName())) {
            isValid = false;
            reportError(LOCATION_NAME_PROPERTY, KeyConstants.ERROR_PROPOSAL_SITES_LOCATION_NAME_REQUIRED);
        }
        
        return isValid;
    }
}
