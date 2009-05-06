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
package org.kuali.kra.irb.personnel;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

public abstract class ProtocolPersonnelEventBase extends KraDocumentEventBase implements ProtocolPersonnelEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(ProtocolPersonnelEventBase.class);

    private ProtocolPerson protocolPerson;
    private int personIndex;

    protected ProtocolPersonnelEventBase(String description, String errorPathPrefix, ProtocolDocument document,
            ProtocolPerson protocolPerson) {
        super(description, errorPathPrefix, document);
        setProtocolPerson(protocolPerson);
        logEvent();
    }

    protected ProtocolPersonnelEventBase(String description, String errorPathPrefix, ProtocolDocument document) {
        super(description, errorPathPrefix, document);
        //setProtocolPerson(protocolPerson);
        logEvent();
    }
    
    protected ProtocolPersonnelEventBase(String description, String errorPathPrefix, ProtocolDocument document,
            int personIndex) {
        super(description, errorPathPrefix, document);
        //setProtocolPerson(protocolPerson);
        this.personIndex = personIndex;
        logEvent();
    }

    /**
     * @return <code>{@link ProtocolPerson}</code> that triggered this event.
     */
    public ProtocolPerson getProtocolPerson() {
        return this.protocolPerson;
    }

    /**
     * @return <code>{@link personIndex}</code> that triggered this event.
     */
    public int getPersonIndex() {
        return this.personIndex;
    }
    
    /**
     * This method is to deep copy protocol person
     * by doing a deep copy, we are ensuring that the business rule class can't update
     * the original object by reference
     */
    private void setProtocolPerson(ProtocolPerson protocolPerson) {
        this.protocolPerson = (ProtocolPerson) ObjectUtils.deepCopy(protocolPerson);
    }

    /**
     * Logs the event type and some information about the associated person
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProtocolPerson() == null) {
            logMessage.append("null protocolPerson");
        }
        else {
            logMessage.append(getProtocolPerson().toString());
        }

        LOG.debug(logMessage);
    }
}
