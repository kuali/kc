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
package org.kuali.kra.protocol.personnel;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * Represents the event to add a ProtocolPersonnel.
 */
public abstract class AddProtocolPersonnelEventBase extends KcDocumentEventBaseExtension {
    
    protected ProtocolPersonBase protocolPerson;
    
    /**
     * Constructs a AddProtocolPersonnelEventBase.
     * @param errorPathPrefix The error path prefix
     * @param document The document to validate
     * @param protocolPerson the person to add
     */
    protected AddProtocolPersonnelEventBase(String errorPathPrefix, ProtocolDocumentBase document, ProtocolPersonBase protocolPerson) {
        super("Adding ProtocolPersonBase to document " + getDocumentId(document), errorPathPrefix, document);
    
        this.protocolPerson = (ProtocolPersonBase) ObjectUtils.deepCopy(protocolPerson);
    }
    
    public ProtocolPersonBase getProtocolPerson() {
        return protocolPerson;
    }

}
