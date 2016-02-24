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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.AddCommitteeMembershipRule;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * This class represents the event when a <code>{@link CommitteeMembershipBase}</code> is added to a 
 * <code>{@link CommitteeBase}</code>.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class AddCommitteeMembershipEvent extends CommitteeMembershipEventBase {

    /**
     * 
     * Constructs a <code>{@link AddCommitteeMembershipEvent}</code>.
     * 
     * @param errorPathPrefix
     * @param committeeDocument
     * @param committeeMembership
     */
   public AddCommitteeMembershipEvent(String errorPathPrefix, CommitteeDocumentBase comitteeDocument, 
           CommitteeMembershipBase committeeMembership) {
        super("adding CommitteeMembershipBase to document " + getDocumentId(comitteeDocument),
                errorPathPrefix, comitteeDocument, committeeMembership);
    }

   /**
    * 
    * Constructs a <code>{@link AddCommitteeMembershipEvent}</code>.
    * 
    * @param errorPathPrefix
    * @param document
    * @param committeeMembership
    */
  public AddCommitteeMembershipEvent(String errorPathPrefix, Document document, 
          CommitteeMembershipBase committeeMembership) {
       this(errorPathPrefix, (CommitteeDocumentBase) document, committeeMembership);
   }

    /**
     * 
     * Returns the <code>{@link AddCommitteeMembershipRule}</code> class which is needed to validate a
     * <code>{@link CommitteeMembershipBase}</code>
     * 
     * @return <code>{@link AddCommitteeMembershipRule} class</code>
     */
    public Class getRuleInterfaceClass() {
        return AddCommitteeMembershipRule.class;
    }

    /**
     * 
     * Invokes the processing of the rules when adding a <code>{@link CommitteeMembershipBase}</code>.
     * 
     * @param The <code>{@link AddCommitteeMembershipRule}</code> that is to be used for processing
     * @return <code>true</code> if all rules are satisfied, otherwise <code>false</code>
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCommitteeMembershipRule) rule).processAddCommitteeMembershipBusinessRules(this);
    }

}
