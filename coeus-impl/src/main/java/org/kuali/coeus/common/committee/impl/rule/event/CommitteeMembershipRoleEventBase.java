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
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.document.Document;

public abstract class CommitteeMembershipRoleEventBase extends KcDocumentEventBase
                                                       implements CommitteeMembershipRoleEvent {


    private static final Log LOG = LogFactory.getLog(CommitteeMembershipRoleEventBase.class);

    private CommitteeMembershipRole committeeMembershipRole;
    private int membershipIndex;

    protected CommitteeMembershipRoleEventBase(String description, String errorPathPrefix, Document document,
            CommitteeMembershipRole committeeMembershipRole, int membershipIndex) {
        super(description, errorPathPrefix, document);

        this.committeeMembershipRole = committeeMembershipRole;
        this.membershipIndex = membershipIndex;

        logEvent();
    }

    /**
     * Get the <code>{@link CommitteeMembershipRole}</code> of this event.
     * 
     * @return <code>CommitteeMembershipRole</code>
     */
    public CommitteeMembershipRole getCommitteeMembershipRole() {
        return this.committeeMembershipRole;
    }

    /**
     * Get the index of the <code>CommitteeMembershipBase</code> of this event.
     * 
     * @return <code>membershipIndex</code>
     */
    public int getMembershipIndex() {
        return this.membershipIndex;
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
        if (getCommitteeMembershipRole() == null) {
            logMessage.append("null committeeMembershipRole");
        }
        else {
            logMessage.append(getCommitteeMembershipRole().toString());
        }

        LOG.debug(logMessage);
    }
}
