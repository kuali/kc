/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalBudgetVersionRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class AddProposalBudgetVersionEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AddProposalBudgetVersionEvent.class);   
    private String newBudgetVersionName;
       
    public AddProposalBudgetVersionEvent(Document document, String newBudgetVersionName) {
        super("adding budget version to document " + getDocumentId(document), "", document);
        this.newBudgetVersionName = newBudgetVersionName;
        logEvent();
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with newBudgetVersionName=[" + getNewBudgetVersionName()+"]");
        LOG.debug(logMessage);
    }

    public Class<AddProposalBudgetVersionRule> getRuleInterfaceClass() {
        return AddProposalBudgetVersionRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProposalBudgetVersionRule) rule).processAddProposalBudgetVersion(this);
    }

    public String getNewBudgetVersionName() {
        return newBudgetVersionName;
    }

    public void setNewBudgetVersionName(String newBudgetVersionName) {
        this.newBudgetVersionName = newBudgetVersionName;
    }
}
