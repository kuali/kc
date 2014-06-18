package org.kuali.coeus.common.api.type;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;

public interface ActivityTypeContract extends Coded, Describable {

    String getHigherEducationFunctionCode();
}
