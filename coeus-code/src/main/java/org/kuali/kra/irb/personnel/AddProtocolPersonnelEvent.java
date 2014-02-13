/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.protocol.personnel.AddProtocolPersonnelEventBase;

/**
 * Represents the event to add a ProtocolPersonnel.
 */
public class AddProtocolPersonnelEvent extends AddProtocolPersonnelEventBase {
    
    /**
     * Constructs a AddProtocolPersonnelEvent.
     * 
     * @param errorPathPrefix The error path prefix
     * @param document The document to validate
     * @param protocolPerson the person to add
     */
    public AddProtocolPersonnelEvent(String errorPathPrefix, ProtocolDocument document, ProtocolPerson protocolPerson) {
        super(errorPathPrefix, document, protocolPerson);
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new AddProtocolPersonnelRule();
    }

}