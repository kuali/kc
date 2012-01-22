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
package org.kuali.kra.irb.protocol.reference;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * This class is abstract base class for Event Notification
 */
public abstract class ProtocolReferenceEventBase extends KraDocumentEventBase implements ProtocolReferenceEvent {
    
    private ProtocolReferenceBean protocolReferenceBean;
    
    protected ProtocolReferenceEventBase(String description, String errorPathPrefix, ProtocolDocument document, ProtocolReferenceBean protocolReferenceBean) {
        super(description, errorPathPrefix, document);
        this.protocolReferenceBean = protocolReferenceBean;
    }

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
    }
    
    /**
     * @see org.kuali.kra.irb.protocol.reference.ProtocolReferenceEvent#getProtocolReferenceBean()
     */
    public ProtocolReferenceBean getProtocolReferenceBean() {
        return this.protocolReferenceBean;
    }

}
