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

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

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
