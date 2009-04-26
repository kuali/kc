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

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.rule.CustomAttributeRule;
import org.kuali.rice.kns.rule.BusinessRule;

public class SaveCustomAttributeEvent extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(SaveCustomAttributeEvent.class);

    public SaveCustomAttributeEvent(String errorPathPrefix, ResearchDocumentBase document) {
        super("Adding custom attribute to document " + getDocumentId(document), errorPathPrefix, document);
    }

    @Override
    protected void logEvent() {
        LOG.info("Save custom attribute event");
    }

    public Class getRuleInterfaceClass() {
        return CustomAttributeRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((CustomAttributeRule) rule).processCustomAttributeRules(this);
    }

}
