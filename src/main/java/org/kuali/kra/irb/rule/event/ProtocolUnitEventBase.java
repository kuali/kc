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
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

public abstract class ProtocolUnitEventBase extends KraDocumentEventBase implements ProtocolUnitEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(ProtocolUnitEventBase.class);

    private ProtocolUnit protocolUnit;
    private int personIndex;

    protected ProtocolUnitEventBase(String description, String errorPathPrefix, ProtocolDocument document,
            ProtocolUnit protocolUnit, int personIndex) {
        super(description, errorPathPrefix, document);
        setProtocolUnit(protocolUnit);
        this.personIndex = personIndex;
        logEvent();
    }

    /**
     * @return <code>{@link ProtocolUnit}</code> that triggered this event.
     */
    public ProtocolUnit getProtocolUnit() {
        return this.protocolUnit;
    }

    /**
     * @return <code>{@link PersonIndex}</code> that triggered this event.
     */
    public int getPersonIndex() {
        return this.personIndex;
    }
    
    /**
     * This method is to deep copy protocol unit
     * by doing a deep copy, we are ensuring that the business rule class can't update
     * the original object by reference
     */
    private void setProtocolUnit(ProtocolUnit protocolUnit) {
        this.protocolUnit = (ProtocolUnit) ObjectUtils.deepCopy(protocolUnit);
    }

    /**
     * Logs the event type and some information about the associated unit
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProtocolUnit() == null) {
            logMessage.append("null protocolUnit");
        }
        else {
            logMessage.append(getProtocolUnit().toString());
        }

        LOG.debug(logMessage);
    }
}
