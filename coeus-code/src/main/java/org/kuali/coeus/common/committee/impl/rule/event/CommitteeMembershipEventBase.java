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
