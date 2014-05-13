package org.kuali.kra.s2s.depend;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.Inactivatable;

public interface S2sProviderContract extends Coded, Describable, Inactivatable {
    String getConnectorServiceName();
}
