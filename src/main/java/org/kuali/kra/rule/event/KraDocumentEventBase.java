/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.rule.event;

import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.event.KualiDocumentEventBase;


/**
 * Base implementation for events for KRA
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.2 $
 */
public abstract class KraDocumentEventBase extends KualiDocumentEventBase {
    
    /**
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    protected KraDocumentEventBase(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
    }

    /**
     * Logs the event type and some information about the associated accountingLine
     */
    protected abstract void logEvent();
}
