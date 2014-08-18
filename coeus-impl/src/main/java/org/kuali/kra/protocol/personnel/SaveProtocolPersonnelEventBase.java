/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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

/**
 * Represents the event to save a ProtocolPersonnel.
 */
public abstract class SaveProtocolPersonnelEventBase extends KcDocumentEventBaseExtension {

    /**
     * Constructs an SaveProtocolPersonnelEventBase.
     * @param errorPathPrefix The error path prefix
     * @param document The document to validate
     */
    protected SaveProtocolPersonnelEventBase(String errorPathPrefix, ProtocolDocumentBase document) {
        super("Saving protocol personnel on document " + getDocumentId(document), errorPathPrefix, document);
    }
  
}
