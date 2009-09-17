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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSiteRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProposalDevelopmentProposalLocationRule extends ResearchDocumentRuleBase implements AddProposalSiteRule {
    private static final String LOCATION_NAME_PROPERTY = "locationName";
    private static final String ADDRESS_NAME_PROPERTY = "address";

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalSiteRule#processAddProposalSiteBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent)
     */
    public boolean processAddProposalSiteBusinessRules(AddProposalSiteEvent addProposalSiteEvent) {
        ProposalSite proposalSite = addProposalSiteEvent.getProposalSite();
        boolean rulePassed = true;

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

}
