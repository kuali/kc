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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;

/**
 * 
 * This abstract class is used for specific <code>{@link CommitteeBase}</code> events.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public abstract class CommitteeMembershipEventBase extends KcDocumentEventBase
                                                   implements CommitteeMembershipEvent {
    private static final Log LOG = LogFactory.getLog(CommitteeMembershipEventBase.class);
    
    private CommitteeMembershipBase committeeMembership;
    
    /**
     * 
     * Constructs a <code>{@link CommitteeMembershipEventBase}</code>
     * 
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param committeeMembership
     */
    protected CommitteeMembershipEventBase(String description, String errorPathPrefix, 
            CommitteeDocumentBase comitteeDocument, CommitteeMembershipBase committeeMembership) {
        super(description, errorPathPrefix, comitteeDocument);

        this.committeeMembership = committeeMembership;

        logEvent();
    }

    /**
     * 
     * Constructs a <code>{@link CommitteeMembershipEventBase}</code>
     * 
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    protected CommitteeMembershipEventBase(String description, String errorPathPrefix, 
            CommitteeDocumentBase comitteeDocument) {
        super(description, errorPathPrefix, comitteeDocument);
        logEvent();
    }

    /**
     * 
     * Get the <code>{@link CommitteeMembershipBase}</code> of this event.
     * 
     * @return <code>CommitteeMembershipBase</code>
     */
    public CommitteeMembershipBase getCommitteeMembership() {
        return this.committeeMembership;
    }

    /**
     * 
     * Logs the event type and some information about the associated location.
     */
    protected void logEvent() {
        String className = StringUtils.substringAfterLast(this.getClass().getName(), ".");
        StringBuffer logMessage = new StringBuffer(className);
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getCommitteeMembership() == null) {
            logMessage.append("null committeeMembership");
        } else {
            logMessage.append(getCommitteeMembership().toString());
        }

        LOG.debug(logMessage);
    }
}
