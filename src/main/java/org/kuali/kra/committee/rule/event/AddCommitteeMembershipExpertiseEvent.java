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

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipExpertiseRule;

public class AddCommitteeMembershipExpertiseEvent extends CommitteeMembershipExpertiseEventBase {

    /**
     * 
     * Constructs a <code>{@link AddCommitteeMembershipExpertiseEvent}</code>.
     * 
     * @param errorPathPrefix
     * @param committeeDocument
     * @param committeeMembership
     * @param committeeMembershipExpertise
     * @param membershipIndex
     */
    public AddCommitteeMembershipExpertiseEvent(String errorPathPrefix, CommitteeDocument comitteeDocument,
            CommitteeMembershipExpertise committeeMembershipExpertise, int membershipIndex) {
        super("adding CommitteeMembershipExpertise to document " + getDocumentId(comitteeDocument), errorPathPrefix, comitteeDocument,
            committeeMembershipExpertise, membershipIndex);
    }

    /**
     * 
     * Constructs a <code>{@link AddCommitteeMembershipExpertiseEvent}</code>.
     * 
     * @param errorPathPrefix
     * @param document
     * @param committeeMembership
     * @param committeeMembershipExpertise
     * @param membershipIndex
     */
    public AddCommitteeMembershipExpertiseEvent(String errorPathPrefix, Document document, 
            CommitteeMembershipExpertise committeeMembershipExpertise, int membershipIndex) {
        this(errorPathPrefix, (CommitteeDocument) document, committeeMembershipExpertise, membershipIndex);
    }

    /**
     * 
     * Returns the <code>{@link AddCommitteeMembershipExpertiseRule}</code> class which is needed to validate a
     * <code>{@link CommitteeMembershipExpertise}</code>
     * 
     * @return <code>{@link AddCommitteeMembershipExpertiseRule} class</code>
     */
    public Class getRuleInterfaceClass() {
        return AddCommitteeMembershipExpertiseRule.class;
    }

    /**
     * 
     * Invokes the processing of the rules when adding a <code>{@link CommitteeMembershipExpertise}</code>.
     * 
     * @param The <code>{@link AddCommitteeMembershipExpertiseRule}</code> that is to be used for processing
     * @return <code>true</code> if all rules are satisfied, otherwise <code>false</code>
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCommitteeMembershipExpertiseRule) rule).processAddCommitteeMembershipExpertiseBusinessRules(this);
    }

}
