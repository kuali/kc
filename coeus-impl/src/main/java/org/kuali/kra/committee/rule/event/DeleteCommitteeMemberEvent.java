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
package org.kuali.kra.committee.rule.event;

import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeMemberEventBase;
import org.kuali.coeus.common.committee.impl.rules.DeleteCommitteeMemberRuleBase;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rules.DeleteCommitteeMemberRule;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * 
 * This class is the rule event for deleting committee member.
 */
public class DeleteCommitteeMemberEvent extends DeleteCommitteeMemberEventBase {
    
    /**
     * 
     * Constructs a DeleteCommitteeMemberEvent.java.
     * 
     * @param errorPathPrefix
     * @param document
     * @param committeeMemberships
     * @param type
     */
    public DeleteCommitteeMemberEvent(String errorPathPrefix, CommitteeDocument document, List<CommitteeMembership> committeeMemberships, ErrorType type) {
        super(errorPathPrefix, document, (List) committeeMemberships, type);
    }

    /**
     * 
     * Constructs a DeleteCommitteeMemberEvent.java.
     * 
     * @param errorPathPrefix
     * @param document
     * @param committeeMemberships
     * @param type
     */
    public DeleteCommitteeMemberEvent(String errorPathPrefix, Document document, List<CommitteeMembership> committeeMemberships, ErrorType type) {
        this(errorPathPrefix, (CommitteeDocument) document, committeeMemberships, type);
    }
    

    @Override
    protected DeleteCommitteeMemberRuleBase getNewDeleteCommitteeMemberRuleInstanceHook() {
        return new DeleteCommitteeMemberRule();
    }
}
