/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public abstract class ProposalLocationEventBase extends KraDocumentEventBase implements ProposalLocationEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(ProposalLocationEventBase.class);

    private ProposalLocation proposalLocation;

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    protected ProposalLocationEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document,
            ProposalLocation proposalLocation) {
        super(description, errorPathPrefix, document);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.proposalLocation = (ProposalLocation) ObjectUtils.deepCopy(proposalLocation);
        logEvent();
    }

    /**
     * @return <code>{@link ProposalLocation}</code> that triggered this event.
     */
    public ProposalLocation getProposalLocation() {
        return proposalLocation;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
    public void validate() {
        super.validate();
        if (getProposalLocation() == null) {
            throw new IllegalArgumentException("invalid (null) proposal location");
        }
    }

    /**
     * Logs the event type and some information about the associated location
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProposalLocation() == null) {
            logMessage.append("null proposalLocation");
        }
        else {
            logMessage.append(getProposalLocation().toString());
        }

        LOG.debug(logMessage);
    }


}
