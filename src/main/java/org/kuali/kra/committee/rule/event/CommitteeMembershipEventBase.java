/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This abstract class is used for specific <code>{@link Committee}</code> events.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class CommitteeMembershipEventBase extends KraDocumentEventBase 
                                                   implements CommitteeMembershipEvent {
    private static final Log LOG = LogFactory.getLog(CommitteeMembershipEventBase.class);
    
    private CommitteeMembership committeeMembership;
    
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
            CommitteeDocument comitteeDocument, CommitteeMembership committeeMembership) {
        super(description, errorPathPrefix, comitteeDocument);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.committeeMembership = (CommitteeMembership) ObjectUtils.deepCopy(committeeMembership);

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
            CommitteeDocument comitteeDocument) {
        super(description, errorPathPrefix, comitteeDocument);
        logEvent();
    }

    /**
     * 
     * Get the <code>{@link CommitteeMembership}</code> of this event.
     * 
     * @return <code>CommitteeMembership</code>
     */
    public CommitteeMembership getCommitteeMembership() {
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