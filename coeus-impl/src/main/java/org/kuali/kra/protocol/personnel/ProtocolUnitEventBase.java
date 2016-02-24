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
package org.kuali.kra.protocol.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.util.ObjectUtils;

public abstract class ProtocolUnitEventBase extends KcDocumentEventBase implements ProtocolUnitEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(ProtocolUnitEventBase.class);

    private ProtocolUnitBase protocolUnit;
    private int personIndex;

    protected ProtocolUnitEventBase(String description, String errorPathPrefix, ProtocolDocumentBase document,
            ProtocolUnitBase protocolUnit, int personIndex) {
        super(description, errorPathPrefix, document);
        setProtocolUnit(protocolUnit);
        this.personIndex = personIndex;
        logEvent();
    }

    /**
     * @return <code>{@link ProtocolUnitBase}</code> that triggered this event.
     */
    public ProtocolUnitBase getProtocolUnit() {
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
    private void setProtocolUnit(ProtocolUnitBase protocolUnit) {
        this.protocolUnit = (ProtocolUnitBase) ObjectUtils.deepCopy(protocolUnit);
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
