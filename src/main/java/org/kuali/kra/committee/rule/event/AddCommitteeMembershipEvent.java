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

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRule;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * This class represents the event when a <code>{@link CommitteeMembership}</code> is added to a 
 * <code>{@link Committee}</code>.
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
   public AddCommitteeMembershipEvent(String errorPathPrefix, CommitteeDocument comitteeDocument, 
           CommitteeMembership committeeMembership) {
        super("adding CommitteeMembership to document " + getDocumentId(comitteeDocument),
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
          CommitteeMembership committeeMembership) {
       this(errorPathPrefix, (CommitteeDocument) document, committeeMembership);
   }

    /**
     * 
     * Returns the <code>{@link AddCommitteeMembershipRule}</code> class which is needed to validate a
     * <code>{@link CommitteeMembership}</code>
     * 
     * @return <code>{@link AddCommitteeMembershipRule} class</code>
     */
    public Class getRuleInterfaceClass() {
        return AddCommitteeMembershipRule.class;
    }

    /**
     * 
     * Invokes the processing of the rules when adding a <code>{@link CommitteeMembership}</code>.
     * 
     * @param The <code>{@link AddCommitteeMembershipRule}</code> that is to be used for processing
     * @return <code>true</code> if all rules are satisfied, otherwise <code>false</code>
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCommitteeMembershipRule) rule).processAddCommitteeMembershipBusinessRules(this);
    }

}
