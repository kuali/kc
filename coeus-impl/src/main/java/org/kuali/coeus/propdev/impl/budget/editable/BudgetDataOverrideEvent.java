/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
