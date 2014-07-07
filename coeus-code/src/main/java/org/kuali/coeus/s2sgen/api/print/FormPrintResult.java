package org.kuali.coeus.s2sgen.api.print;

import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.coeus.sys.api.model.KcFile;

import java.util.List;

/**
 * Contains a file that represents a form as a pdf and any errors.
 */
public class FormPrintResult {
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
