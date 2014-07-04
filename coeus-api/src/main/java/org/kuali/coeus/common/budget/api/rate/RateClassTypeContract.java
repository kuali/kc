package org.kuali.coeus.common.budget.api.rate;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.Sortable;

public interface RateClassTypeContract extends Describable, Sortable, Coded {
    Boolean getPrefixActivityType();

}
