/*
 * Copyright 2008 The Kuali Foundation.
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

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rule.CustomAttributeRule;
import org.kuali.kra.rules.KraCustomAttributeRule;

public class SaveCustomAttributeEvent extends CustomAttributeEventBase{
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SaveCustomAttributeEvent.class);

    public SaveCustomAttributeEvent(String errorPathPrefix, ProposalDevelopmentDocument document) {
        super("Adding custom attribute to document " + getDocumentId(document), errorPathPrefix, document);
    }

    @Override
    protected void logEvent() {
        // TODO Auto-generated method stub
        
    }

    public Class getRuleInterfaceClass() {
        return CustomAttributeRule.class;    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((CustomAttributeRule) rule).processCustomAttributeRules(this);
    }

}
