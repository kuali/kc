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
package org.kuali.kra.protocol.protocol.location;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.util.ObjectUtils;

public abstract class ProtocolLocationEventBase extends KcDocumentEventBase implements ProtocolLocationEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(ProtocolLocationEventBase.class);

    private ProtocolLocationBase protocolLocation;

    protected ProtocolLocationEventBase(String description, String errorPathPrefix, ProtocolDocumentBase document,
            ProtocolLocationBase protocolLocation) {
        super(description, errorPathPrefix, document);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.protocolLocation = (ProtocolLocationBase) ObjectUtils.deepCopy(protocolLocation);

        logEvent();
    }

    protected ProtocolLocationEventBase(String description, String errorPathPrefix, ProtocolDocumentBase document) {
        super(description, errorPathPrefix, document);
        logEvent();
    }
    
    /**
     * @return <code>{@link ProtocolLocationBase}</code> that triggered this event.
     */
    public ProtocolLocationBase getProtocolLocation() {
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
