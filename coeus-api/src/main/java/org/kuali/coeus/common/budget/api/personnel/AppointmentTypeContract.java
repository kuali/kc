package org.kuali.coeus.common.budget.api.personnel;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;

public interface AppointmentTypeContract extends Coded, Describable {

    Integer getDuration();
}
