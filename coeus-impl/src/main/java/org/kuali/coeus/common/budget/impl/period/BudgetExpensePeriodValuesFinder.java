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
package org.kuali.coeus.common.budget.impl.period;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.common.budget.framework.core.BudgetContainer;
import org.kuali.coeus.common.budget.impl.core.BudgetPeriodValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class BudgetExpensePeriodValuesFinder extends BudgetPeriodValuesFinder {
    protected Log LOG = LogFactory.getLog(BudgetExpensePeriodValuesFinder.class);

    protected AwardBudgetService awardBudgetService = KcServiceLocator.getService(AwardBudgetService.class);
    protected DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
    
    /**
     * Constructs the list of Budget Periods.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from the BudgetDocument if any are defined there. 
     * Otherwise, it is obtained from a lookup of the BUDGET_PERIOD database table
     * via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> KeyValues = null;
        
        Object formOrView = getFormOrView();
        if (formOrView instanceof BudgetContainer) {
            List<BudgetPeriod> budgetPeriods = ((BudgetContainer) formOrView).getBudget().getBudgetPeriods();
            if (budgetPeriods.size() > 0) {
                KeyValues = buildKeyValues(budgetPeriods);
            }
        } else if (formOrView instanceof MultipleValueLookupForm) {
            try {
                AwardBudgetDocument doc = (AwardBudgetDocument) getDocumentService().getByDocumentHeaderId(((MultipleValueLookupForm) formOrView).getDocNum());
                List<BudgetPeriod> budgetPeriods = getAwardBudgetService().findBudgetPeriodsFromLinkedProposal((Award) doc.getBudget().getBudgetParent());
                if (budgetPeriods.size() > 0) {
                    KeyValues = buildKeyValuesForPeriodSearch(budgetPeriods);
                }
            }
            catch (WorkflowException e) {
                LOG.error("Unable to load document for budget period values finder.", e);
            }
        }
        
        if (KeyValues != null) {
            return KeyValues;
        } else {
            return new ArrayList<KeyValue>();
        }
    }
    
    public List<KeyValue> getKeyValues(ModelAndView model) {
    	return buildKeyValues(((BudgetContainer) model).getBudget().getBudgetPeriods());
    }
    
    private List<KeyValue> buildKeyValues(List<BudgetPeriod> budgetPeriods) {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if (budgetPeriod.getBudgetPeriod() != null) {
                KeyValues.add(new ConcreteKeyValue(budgetPeriod.getBudgetPeriod().toString(), budgetPeriod.getLabel()));
            }
        }
        return KeyValues;
    }
    
    private List<KeyValue> buildKeyValuesForPeriodSearch(List<BudgetPeriod> budgetPeriods) {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();
        List<Integer> uniqueKeys = new ArrayList<Integer>();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if (budgetPeriod.getBudgetPeriod() != null && !uniqueKeys.contains(budgetPeriod.getBudgetPeriod())) {
                uniqueKeys.add(budgetPeriod.getBudgetPeriod());
                KeyValues.add(new ConcreteKeyValue(budgetPeriod.getBudgetPeriod().toString(), budgetPeriod.getBudgetPeriod().toString()));
            }
        }
        return KeyValues;
    }

    protected AwardBudgetService getAwardBudgetService() {
        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
