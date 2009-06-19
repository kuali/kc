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
package org.kuali.kra.irb.protocol.location;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

public abstract class ProtocolLocationEventBase extends KraDocumentEventBase implements ProtocolLocationEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(ProtocolLocationEventBase.class);

    private ProtocolLocation protocolLocation;

    protected ProtocolLocationEventBase(String description, String errorPathPrefix, ProtocolDocument document,
            ProtocolLocation protocolLocation) {
        super(description, errorPathPrefix, document);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.protocolLocation = (ProtocolLocation) ObjectUtils.deepCopy(protocolLocation);

        logEvent();
    }

    protected ProtocolLocationEventBase(String description, String errorPathPrefix, ProtocolDocument document) {
        super(description, errorPathPrefix, document);
        logEvent();
    }
    
    /**
     * @return <code>{@link ProtocolLocation}</code> that triggered this event.
     */
    public ProtocolLocation getProtocolLocation() {
        return this.protocolLocation;
    }

    /**
     * Logs the event type and some information about the associated location
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProtocolLocation() == null) {
            logMessage.append("null protocolLocation");
        }
        else {
            logMessage.append(getProtocolLocation().toString());
        }

        LOG.debug(logMessage);
    }
}
