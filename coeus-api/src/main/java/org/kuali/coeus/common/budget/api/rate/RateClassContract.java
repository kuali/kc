package org.kuali.coeus.common.budget.api.rate;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;

public interface RateClassContract extends Coded, Describable{

    RateClassTypeContract getRateClassType();
}
