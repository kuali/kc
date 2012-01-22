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
package org.kuali.kra.proposaldevelopment.budget.modular;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class SyncModularBudgetEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(SyncModularBudgetEvent.class);
    
    public SyncModularBudgetEvent(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
    }
    
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SyncModularBudgetRule) rule).processSyncModularBusinessRules(this.getDocument());
    }
    
    public Class<SyncModularBudgetRule> getRuleInterfaceClass() {
        return SyncModularBudgetRule.class;
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        LOG.debug(logMessage);
    }

}
