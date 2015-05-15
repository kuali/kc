/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.AddCommitteeMembershipRoleRule;
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
    public AddCommitteeMembershipRoleEvent(String errorPathPrefix, CommitteeDocumentBase comitteeDocument,
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
        this(errorPathPrefix, (CommitteeDocumentBase) document, committeeMembershipRole, membershipIndex);
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
     * @param rule <code>{@link AddCommitteeMembershipRoleRule}</code> that is to be used for processing
     * @return <code>true</code> if all rules are satisfied, otherwise <code>false</code>
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCommitteeMembershipRoleRule) rule).processAddCommitteeMembershipRoleBusinessRules(this);
    }

}
