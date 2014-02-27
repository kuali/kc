/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.committee.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeMemberEventBase;
import org.kuali.coeus.common.committee.impl.rules.DeleteCommitteeMemberRuleBase;
import org.kuali.kra.iacuc.committee.rules.IacucDeleteCommitteeMemberRule;
import org.kuali.rice.krad.document.Document;

import java.util.List;

public class IacucDeleteCommitteeMemberEvent extends DeleteCommitteeMemberEventBase {

    /**
     * 
     * Constructs a DeleteCommitteeMemberEventBase.java.
     * 
     * @param errorPathPrefix
     * @param document
     * @param committeeMemberships
     * @param type
     */
    public IacucDeleteCommitteeMemberEvent(String errorPathPrefix, CommitteeDocumentBase document, List<CommitteeMembershipBase> committeeMemberships, ErrorType type) {
        super(errorPathPrefix, document, committeeMemberships, type);
    }

    /**
     * 
     * Constructs a DeleteCommitteeMemberEventBase.java.
     * 
     * @param errorPathPrefix
     * @param document
     * @param committeeMemberships
     * @param type
     */
    public IacucDeleteCommitteeMemberEvent(String errorPathPrefix, Document document, List<CommitteeMembershipBase> committeeMemberships, ErrorType type) {
        this(errorPathPrefix, (CommitteeDocumentBase) document, committeeMemberships, type);
    }

    @Override
    protected DeleteCommitteeMemberRuleBase getNewDeleteCommitteeMemberRuleInstanceHook() {
        return new IacucDeleteCommitteeMemberRule();
    }

}
