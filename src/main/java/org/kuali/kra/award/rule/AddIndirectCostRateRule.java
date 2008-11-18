package org.kuali.kra.award.rule;

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent;

public interface AddIndirectCostRateRule extends BusinessRule {
    
    /**
     * Rule invoked upon adding a indirect cost rate
     * <code>{@link org.kuali.kra.award.document.AwardDocument}</code>
     *
     * @return boolean
     */
    public boolean processAddIndirectCostRatesBusinessRules(AddAwardIndirectCostRateEvent addAwardIndirectCostRateEvent);

}
