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
package org.kuali.kra.committee.rule.event;

import static org.kuali.kra.logging.BufferedLogger.info;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rules.SaveCommitteeMembershipRule;

/**
 * Event triggered when a committee membership is saved to a
 * <code>{@link CommitteeDocument}</code>
 *
 */
public class SaveCommitteeMembershipEvent extends CommitteeMembershipEventBase {

    /**
     * Constructs a SaveCommitteeMembershipEvent.java with the given errorPathPrefix, document.
     * 
     * @param errorPathPrefix
     * @param document
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, Document)
     */
    protected SaveCommitteeMembershipEvent(String errorPathPrefix, CommitteeDocument comitteeDocument) {
        super("Saving committee membership on document " + getDocumentId(comitteeDocument), errorPathPrefix, comitteeDocument);
    }

    /**
     * Constructs an SaveCommitteeMembershipEvent.java with the given errorPathPrefix, document.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalPerson
     */
    public SaveCommitteeMembershipEvent(String errorPathPrefix, Document document) {
        this(errorPathPrefix, (CommitteeDocument) document);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return SaveCommitteeMembershipRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        info("Calling processSaveCommitteeMembershipBusinessRules on ", rule.getClass().getSimpleName());
        return ((SaveCommitteeMembershipRule) rule).processSaveCommitteeMembershipBusinessRules(this);
    }

}
