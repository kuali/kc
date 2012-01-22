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
package org.kuali.kra.proposaldevelopment.rule.event;

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.DeleteCongressionalDistrictRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Event class for deleting a congressional district from a Proposal Site.
 */
public class DeleteProposalCongressionalDistrictEvent extends BasicProposalSiteEvent {
    private String districtIndex;

    /**
     * Constructor for use with a ProposalSite reference.
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSite
     * @param districtIndex
     */
    public DeleteProposalCongressionalDistrictEvent(String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, ProposalSite proposalSite, String districtIndex) {
        super(getEventDescription(proposalDevelopmentDocument), errorPathPrefix, proposalDevelopmentDocument, proposalSite);
        this.districtIndex = districtIndex;
    }

    /**
     * Constructor for use with a List of ProposalSites, and an index into the List.
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSites
     * @param siteIndex
     * @param districtIndex
     */
    public DeleteProposalCongressionalDistrictEvent(String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, List<ProposalSite> proposalSites, String siteIndex, String districtIndex) {
        super(getEventDescription(proposalDevelopmentDocument), errorPathPrefix, proposalDevelopmentDocument, proposalSites, siteIndex);
        this.districtIndex = districtIndex;
    }

    public String getDistrictIndex() {
        return districtIndex;
    }

    private static String getEventDescription(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return "deleting congressional district from document " + getDocumentId(proposalDevelopmentDocument);
    }
    
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return DeleteCongressionalDistrictRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((DeleteCongressionalDistrictRule)rule).processDeleteCongressionalDistrictRules(this);
    }
}
