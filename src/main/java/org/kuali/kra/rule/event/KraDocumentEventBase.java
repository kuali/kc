/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.rule.event;

import org.kuali.core.document.Document;
import org.kuali.core.rule.event.KualiDocumentEventBase;


/**
 * Base implementation for events for KRA
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.1 $
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
