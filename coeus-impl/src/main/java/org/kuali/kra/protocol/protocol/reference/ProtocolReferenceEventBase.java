/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.protocol.reference;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * This class is abstract base class for Event Notification
 */
public abstract class ProtocolReferenceEventBase extends KcDocumentEventBase implements ProtocolReferenceEvent {
    
    private ProtocolReferenceBeanBase protocolReferenceBean;
    
    protected ProtocolReferenceEventBase(String description, String errorPathPrefix, ProtocolDocumentBase document, ProtocolReferenceBeanBase protocolReferenceBean) {
        super(description, errorPathPrefix, document);
        this.protocolReferenceBean = protocolReferenceBean;
    }

    @Override
    protected void logEvent() {
    }
    
    @Override
    public ProtocolReferenceBeanBase getProtocolReferenceBean() {
        return this.protocolReferenceBean;
    }

}
