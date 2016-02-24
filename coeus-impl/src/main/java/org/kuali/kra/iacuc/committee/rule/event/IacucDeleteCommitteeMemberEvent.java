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
