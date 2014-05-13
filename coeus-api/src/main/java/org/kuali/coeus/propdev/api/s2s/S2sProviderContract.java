package org.kuali.coeus.propdev.api.s2s;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.Inactivatable;

public interface S2sProviderContract extends Coded, Describable, Inactivatable {
    String getConnectorServiceName();
}
