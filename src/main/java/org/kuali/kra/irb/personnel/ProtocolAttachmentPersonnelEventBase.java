/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;

public abstract class ProtocolAttachmentPersonnelEventBase  extends KraDocumentEventBase implements ProtocolAttachmentPersonnelEvent {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(ProtocolAttachmentPersonnelEventBase.class);

    private ProtocolAttachmentPersonnel protocolAttachmentPersonnel;
    private int personIndex;

    protected ProtocolAttachmentPersonnelEventBase(String description, String errorPathPrefix, Document document,
            ProtocolAttachmentPersonnel protocolAttachmentPersonnel, int personIndex) {
        super(description, errorPathPrefix, document);
        setProtocolAttachmentPersonnel(protocolAttachmentPersonnel);
        setPersonIndex(personIndex);
    }

    public ProtocolAttachmentPersonnel getProtocolAttachmentPersonnel() {
        return protocolAttachmentPersonnel;
    }

    public void setProtocolAttachmentPersonnel(ProtocolAttachmentPersonnel protocolAttachmentPersonnel) {
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
