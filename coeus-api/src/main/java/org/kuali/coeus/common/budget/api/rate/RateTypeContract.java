package org.kuali.coeus.common.budget.api.rate;

import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.api.model.Describable;

public interface RateTypeContract extends Describable {

    String getRateClassCode();

    String getRateTypeCode();

    RateClassContract getRateClass();
}
