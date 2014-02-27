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
package org.kuali.coeus.sys.framework.rule;

import org.kuali.rice.krad.document.Document;

public abstract class KcDocumentEventBaseExtension extends KcDocumentEventBase {

    protected KcDocumentEventBaseExtension(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
    }

    public abstract KcBusinessRule getRule();
    

    @SuppressWarnings("unchecked")
    public boolean invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule rule) {
        return ((KcBusinessRule)rule).processRules(this);
    }

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return KcBusinessRule.class;
    }

    @Override
    protected void logEvent() {       
    }
}
