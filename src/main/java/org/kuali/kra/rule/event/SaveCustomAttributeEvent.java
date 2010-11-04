/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.SaveCustomAttributeRule;

/**
 * Encapsulates a validation event for a Custom Attribute add action.
 */
public class SaveCustomAttributeEvent extends KraDocumentEventBaseExtension {

    /**
     * Constructs a SaveCustomAttributeEvent.
     * @param errorPathPrefix The error path prefix
     * @param document The document to validate
     */
    public SaveCustomAttributeEvent(String errorPathPrefix, ResearchDocumentBase document) {
        super("Saving custom attribute to document " + getDocumentId(document), errorPathPrefix, document);
    }

    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new SaveCustomAttributeRule();
    }

}