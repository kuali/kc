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

/**
 * Rule event class for adding a Proposal Site to a proposal
 */
public class AddProposalSiteEvent extends BasicProposalSiteEvent {
    
    /**
     * Constructs an AddProposalSiteEvent with the given errorPathPrefix, document, and proposalSite.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalSite
     */
    public AddProposalSiteEvent(String errorPathPrefix, ProposalDevelopmentDocument document, ProposalSite proposalSite) {
        super("adding site to document " + getDocumentId(document), errorPathPrefix, document, proposalSite);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AddProposalSiteRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProposalSiteRule) rule).processAddProposalSiteBusinessRules(this);
    }

}
