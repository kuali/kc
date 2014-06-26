package org.kuali.coeus.common.budget.api.rate;

import org.kuali.coeus.sys.api.model.Inactivatable;

public interface ValidCeRateTypeContract extends Inactivatable {
    
    String getCostElement();
    
    RateClassContract getRateClass();
    
    RateTypeContract getRateType();
    
}
