/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule;

public class AddProposalLocationEvent extends ProposalLocationEventBase{
    /**
     * Constructs an AddProposalLocationEvent with the given errorPathPrefix, document, and proposalLocation.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposallocation
     */
    public AddProposalLocationEvent(String errorPathPrefix, ProposalDevelopmentDocument document, ProposalLocation proposalLocation) {
        super("adding location to document " + getDocumentId(document), errorPathPrefix, document, proposalLocation);
    }

    /**
     * Constructs an AddProposalLocationEvent with the given errorPathPrefix, document, and proposalLocation.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalLocation
     */
    public AddProposalLocationEvent(String errorPathPrefix, Document document, ProposalLocation proposalLocation) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document, proposalLocation);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddProposalLocationRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProposalLocationRule) rule).processAddProposalLocationBusinessRules(this);
    }

}
