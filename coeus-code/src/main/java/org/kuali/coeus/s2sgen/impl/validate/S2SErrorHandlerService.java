package org.kuali.coeus.s2sgen.impl.validate;

import org.kuali.coeus.s2sgen.api.core.AuditError;

public interface S2SErrorHandlerService {

    AuditError getError(String key);
}
