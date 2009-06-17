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

import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

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
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddProposalLocationRule.class;
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProposalLocationRule) rule).processAddProposalLocationBusinessRules(this);
    }

}
