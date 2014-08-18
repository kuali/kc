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
package org.kuali.coeus.propdev.impl.budget.editable;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.editable.ProposalDataOverrideEvent;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class BudgetDataOverrideEvent extends KcDocumentEventBase {
private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDataOverrideEvent.class);
    
    private BudgetChangedData budgetChangedData;
    
    /**
     * Constructs an BudgetDataOverrideEvent.
     * 
     * @param document proposal development document
     * @param budgetChangedData the budget Changed Data
     */
    public BudgetDataOverrideEvent(ProposalDevelopmentDocument document, BudgetChangedData budgetChangedData) {
        super("Override Budget Data " + getDocumentId(document), "", document);
        this.setBudgetChangedData(budgetChangedData);
        logEvent();
    }
    
    @Override
    public void validate() {
        super.validate();
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        LOG.debug(logMessage);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return BudgetDataOverrideRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((BudgetDataOverrideRule) rule).processBudgetDataOverrideRules(this);
    }

        public void setBudgetChangedData(BudgetChangedData budgetChangedData) {
        this.budgetChangedData = budgetChangedData;
    }

    public BudgetChangedData getBudgetChangedData() {
        return budgetChangedData;
    }
}
