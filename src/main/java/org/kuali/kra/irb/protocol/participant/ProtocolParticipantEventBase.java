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
package org.kuali.kra.irb.protocol.participant;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This abstract class is used for specific <code>{@link ProtocolParticipant}</code> events.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public abstract class ProtocolParticipantEventBase extends KraDocumentEventBase 
                                                   implements ProtocolParticipantEvent {

    private static final Log LOG = LogFactory.getLog(ProtocolParticipantEventBase.class);

    private ProtocolParticipant protocolParticipant;

    /**
     * 
     * Constructs a <code>{@link ProtocolParticipantEventBase}</code>
     * 
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param protocolParticipant
     */
    protected ProtocolParticipantEventBase(String description, String errorPathPrefix, 
            ProtocolDocument document, ProtocolParticipant protocolParticipant) {
        super(description, errorPathPrefix, document);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.protocolParticipant = (ProtocolParticipant) ObjectUtils.deepCopy(protocolParticipant);

        logEvent();
    }

    /**
     * 
     * Get the <code>{@link ProtocolParticipant}</code> of this event.
     * 
     * @return <code>ProtocolParticipant</code>
     */
    public ProtocolParticipant getProtocolParticipant() {
        return this.protocolParticipant;
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
        if (getProtocolParticipant() == null) {
            logMessage.append("null protocolParticipant");
        } else {
            logMessage.append(getProtocolParticipant().toString());
        }

        LOG.debug(logMessage);
    }
}
