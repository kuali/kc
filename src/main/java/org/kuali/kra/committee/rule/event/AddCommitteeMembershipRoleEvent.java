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
package org.kuali.kra.committee.rule.event;

import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRoleRule;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AddCommitteeMembershipRoleEvent extends CommitteeMembershipRoleEventBase {

    /**
     * 
     * Constructs a <code>{@link AddCommitteeMembershipRoleEvent}</code>.
     * 
     * @param errorPathPrefix
     * @param committeeDocument
     * @param committeeMembership
     * @param committeeMembershipRole
     * @param membershipIndex
     */
    public AddCommitteeMembershipRoleEvent(String errorPathPrefix, CommitteeDocument comitteeDocument,
            CommitteeMembershipRole committeeMembershipRole, int membershipIndex) {
        super("adding CommitteeMembershipRole to document " + getDocumentId(comitteeDocument), errorPathPrefix, comitteeDocument,
            committeeMembershipRole, membershipIndex);
    }

    /**
     * 
     * Constructs a <code>{@link AddCommitteeMembershipRoleEvent}</code>.
     * 
     * @param errorPathPrefix
     * @param document
     * @param committeeMembership
     * @param committeeMembershipRole
     * @param membershipIndex
     */
    public AddCommitteeMembershipRoleEvent(String errorPathPrefix, Document document, 
            CommitteeMembershipRole committeeMembershipRole, int membershipIndex) {
        this(errorPathPrefix, (CommitteeDocument) document, committeeMembershipRole, membershipIndex);
    }

    /**
     * 
     * Returns the <code>{@link AddCommitteeMembershipRoleRule}</code> class which is needed to validate a
     * <code>{@link CommitteeMembershipRole}</code>
     * 
     * @return <code>{@link AddCommitteeMembershipRoleRule} class</code>
     */
    public Class getRuleInterfaceClass() {
        return AddCommitteeMembershipRoleRule.class;
    }

    /**
     * 
     * Invokes the processing of the rules when adding a <code>{@link CommitteeMembershipRole}</code>.
     * 
     * @param The <code>{@link AddCommitteeMembershipRoleRule}</code> that is to be used for processing
     * @return <code>true</code> if all rules are satisfied, otherwise <code>false</code>
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCommitteeMembershipRoleRule) rule).processAddCommitteeMembershipRoleBusinessRules(this);
    }

}
