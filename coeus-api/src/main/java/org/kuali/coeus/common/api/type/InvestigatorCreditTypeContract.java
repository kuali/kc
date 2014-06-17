package org.kuali.coeus.common.api.type;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.Inactivatable;

public interface InvestigatorCreditTypeContract extends Coded, Describable, Inactivatable {
    Boolean getAddsToHundred();
}
