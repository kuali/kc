package org.kuali.coeus.common.budget.api.rate;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;

public interface AbstractInstituteRateContract {
    
    String getFiscalYear();

    Boolean getOnOffCampusFlag();
    
    Date getStartDate();

    ScaleTwoDecimal getInstituteRate();

    RateClassContract getRateClass();

    RateTypeContract getRateType();
}
