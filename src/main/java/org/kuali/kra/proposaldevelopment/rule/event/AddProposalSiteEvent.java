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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSiteRule;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * Rule event class for adding a Proposal Site to a proposal
 */
public class AddProposalSiteEvent extends ProposalSiteEventBase {
    
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

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AddProposalSiteRule.class;
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProposalSiteRule) rule).processAddProposalSiteBusinessRules(this);
    }

}
