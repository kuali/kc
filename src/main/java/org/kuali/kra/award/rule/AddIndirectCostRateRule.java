package org.kuali.kra.award.rule;

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent;

/**
 * 
 * This interface declares the rule method associated with <code>AwardIndirectCostRate</code> Business Object.
 */
public interface AddIndirectCostRateRule extends BusinessRule {
    
    /**
     * Rule invoked upon adding a indirect cost rate
     * <code>{@link org.kuali.kra.award.document.AwardDocument}</code>
     *
     * @return boolean
     */
    public boolean processAddIndirectCostRatesBusinessRules(AddAwardIndirectCostRateEvent addAwardIndirectCostRateEvent);

}
