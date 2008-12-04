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
package org.kuali.kra.irb.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public abstract class ProtocolParticipantEventBase extends KraDocumentEventBase implements ProtocolParticipantEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolParticipantEventBase.class);
    
    private ProtocolParticipant protocolParticipant;

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    protected ProtocolParticipantEventBase(String description, String errorPathPrefix, ProtocolDocument document, ProtocolParticipant protocolParticipant) {
        super(description, errorPathPrefix, document);

        //by doing a deep copy, we are ensuring that the business rule class can't update
        //the original object by reference
        //this.budgetPeriod = (BudgetPeriod) ObjectUtils.deepCopy(budgetPeriod);
        this.protocolParticipant = protocolParticipant;
        logEvent();
    }
    
    /**
     * @return <code>{@link ProtocolParticipant}</code> that triggered this event.
     */
    public ProtocolParticipant getProtocolParticipant() {
        return protocolParticipant;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
    public void validate() {
        super.validate();
        if (getProtocolParticipant() == null) {
            throw new IllegalArgumentException("invalid (null) protocol participant");
        }
    }

    /**
     * Logs the event type and some information about the associated location
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProtocolParticipant() == null) {
            logMessage.append("null protocolParticipant");
        }
        else {
            logMessage.append(getProtocolParticipant().toString());
        }

        LOG.debug(logMessage);
    }
}
