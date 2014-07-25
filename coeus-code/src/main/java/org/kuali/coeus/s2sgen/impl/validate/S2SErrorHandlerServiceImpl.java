package org.kuali.coeus.s2sgen.impl.validate;

import org.kuali.coeus.propdev.api.s2s.S2sErrorContract;
import org.kuali.coeus.propdev.api.s2s.S2sErrorService;
import org.kuali.coeus.s2sgen.api.core.AuditError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("s2SErrorHandlerService")
public class S2SErrorHandlerServiceImpl implements S2SErrorHandlerService {

    @Autowired
    @Qualifier("s2sErrorService")
    private S2sErrorService s2sErrorService;

    @Override
    public AuditError getError(String key) {
        final S2sErrorContract s2sError = s2sErrorService.findS2sErrorByKey(key);
        return s2sError == null ? new AuditError(AuditError.NO_FIELD_ERROR_KEY, key + " is not valid", AuditError.GG_LINK) :
                new AuditError(s2sError.getKey(), s2sError.getMessage(), s2sError.getLink());
    }
}
