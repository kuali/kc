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
package org.kuali.kra.proposaldevelopment.rule.event;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddCongressionalDistrictRule;
import org.kuali.kra.proposaldevelopment.web.struts.form.CongressionalDistrictHelper;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class represents the "add congressional district to a proposal site" event.
 */
public class AddProposalCongressionalDistrictEvent extends ProposalSiteEventBase {
    private List<CongressionalDistrictHelper> proposalSiteHelpers;
    
    public AddProposalCongressionalDistrictEvent(String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, ProposalSite proposalSite, CongressionalDistrictHelper proposalSiteHelper) {
        super(getEventDescription(proposalDevelopmentDocument), errorPathPrefix, proposalDevelopmentDocument, proposalSite);
        
        proposalSiteHelpers = new ArrayList<CongressionalDistrictHelper>();
        proposalSiteHelpers.add(proposalSiteHelper);
    }

    public AddProposalCongressionalDistrictEvent(String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, List<ProposalSite> proposalSites, List<CongressionalDistrictHelper> proposalSiteHelpers, String siteIndex) {
        super(getEventDescription(proposalDevelopmentDocument), errorPathPrefix, proposalDevelopmentDocument, proposalSites, siteIndex);
        this.proposalSiteHelpers = proposalSiteHelpers;
    }

    private static String getEventDescription(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return "adding congressional district to document " + getDocumentId(proposalDevelopmentDocument);
    }
    
    public List<CongressionalDistrictHelper> getProposalSiteHelpers() {
        return proposalSiteHelpers;
    }

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AddCongressionalDistrictRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCongressionalDistrictRule)rule).processAddCongressionalDistrictRules(this);
    }
}
