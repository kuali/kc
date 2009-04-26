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
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.ObjectUtils;

public abstract class CommitteeMembershipExpertiseEventBase extends KraDocumentEventBase 
                                                            implements CommitteeMembershipExpertiseEvent {


    private static final Log LOG = LogFactory.getLog(CommitteeMembershipExpertiseEventBase.class);

    private CommitteeMembershipExpertise committeeMembershipExpertise;
    private int membershipIndex;

    protected CommitteeMembershipExpertiseEventBase(String description, String errorPathPrefix, Document document,
            CommitteeMembershipExpertise committeeMembershipExpertise, int membershipIndex) {
        super(description, errorPathPrefix, document);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.committeeMembershipExpertise = (CommitteeMembershipExpertise) ObjectUtils.deepCopy(committeeMembershipExpertise);
        this.membershipIndex = membershipIndex;

        logEvent();
    }

    /**
     * Get the <code>{@link CommitteeMembershipExpertise}</code> of this event.
     * 
     * @return <code>CommitteeMembershipExpertise</code>
     */
    public CommitteeMembershipExpertise getCommitteeMembershipExpertise() {
        return this.committeeMembershipExpertise;
    }

    /**
     * Get the index of the <code>CommitteeMembership</code> of this event.
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
        if (getCommitteeMembershipExpertise() == null) {
            logMessage.append("null committeeMembershipExpertise");
        }
        else {
            logMessage.append(getCommitteeMembershipExpertise().toString());
        }

        LOG.debug(logMessage);
    }
}
