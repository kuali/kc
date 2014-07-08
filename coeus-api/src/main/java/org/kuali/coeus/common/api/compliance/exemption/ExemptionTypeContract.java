package org.kuali.coeus.common.api.compliance.exemption;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;

public interface ExemptionTypeContract extends Coded, Describable {

    String getDetailedDescription();
}
