/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Event triggered when a Key Person is added to a
 * <code>{@link ProposalDevelopmentDocument}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.4 $
 */
public class AddKeyPersonEvent extends KeyPersonEventBase implements KeyPersonEvent {
    /**
     * Constructs an AddKeyPersonEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param document
     * @param proposalPerson
     */
    public AddKeyPersonEvent(ProposalDevelopmentDocument document, ProposalPerson person) {
        super("adding key person to document " + getDocumentId(document), document, person);
    }

    /**
     * Constructs an AddKeyPersonEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param document
     * @param proposalPerson
     */
    public AddKeyPersonEvent(Document document, ProposalPerson person) {
        this((ProposalDevelopmentDocument) document, person);
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddKeyPersonRule.class;
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddKeyPersonRule) rule).processAddKeyPersonBusinessRules((ProposalDevelopmentDocument) getDocument(), getProposalPerson());
    }
}
