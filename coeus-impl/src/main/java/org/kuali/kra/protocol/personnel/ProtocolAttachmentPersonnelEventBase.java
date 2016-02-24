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
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.rice.krad.document.Document;

public abstract class ProtocolAttachmentPersonnelEventBase  extends KcDocumentEventBase implements ProtocolAttachmentPersonnelEvent {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(ProtocolAttachmentPersonnelEventBase.class);

    private ProtocolAttachmentPersonnelBase protocolAttachmentPersonnel;
    private int personIndex;

    protected ProtocolAttachmentPersonnelEventBase(String description, String errorPathPrefix, Document document,
            ProtocolAttachmentPersonnelBase protocolAttachmentPersonnel, int personIndex) {
        super(description, errorPathPrefix, document);
        setProtocolAttachmentPersonnel(protocolAttachmentPersonnel);
        setPersonIndex(personIndex);
    }

    public ProtocolAttachmentPersonnelBase getProtocolAttachmentPersonnel() {
        return protocolAttachmentPersonnel;
    }

    public void setProtocolAttachmentPersonnel(ProtocolAttachmentPersonnelBase protocolAttachmentPersonnel) {
        this.protocolAttachmentPersonnel = protocolAttachmentPersonnel;
    }

    public int getPersonIndex() {
        return personIndex;
    }

    public void setPersonIndex(int personIndex) {
        this.personIndex = personIndex;
    }

    /**
     * Logs the event type and some information about the associated unit
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProtocolAttachmentPersonnel() == null) {
            logMessage.append("null protocolAttachmentPersonnel");
        }
        else {
            logMessage.append(getProtocolAttachmentPersonnel().toString());
        }

        LOG.debug(logMessage);
    }

}
