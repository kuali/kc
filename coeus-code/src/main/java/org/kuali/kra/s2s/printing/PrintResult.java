package org.kuali.kra.s2s.printing;

import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.kra.s2s.util.AuditError;

import java.util.List;

public class PrintResult {
    private KcFile file;
    private List<AuditError> errors;

    public KcFile getFile() {
        return file;
    }

    public void setFile(KcFile file) {
        this.file = file;
    }

    public List<AuditError> getErrors() {
        return errors;
    }

    public void setErrors(List<AuditError> errors) {
        this.errors = errors;
    }
}
