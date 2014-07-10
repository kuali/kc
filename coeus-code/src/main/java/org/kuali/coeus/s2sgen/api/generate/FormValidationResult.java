package org.kuali.coeus.s2sgen.api.generate;

import org.kuali.coeus.s2sgen.api.core.AuditError;

import java.util.List;

/**
 * This class holds the result of executing form validation on a proposal document.
 */
public class FormValidationResult {

    private boolean valid;
    private List<AuditError> errors;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<AuditError> getErrors() {
        return errors;
    }

    public void setErrors(List<AuditError> errors) {
        this.errors = errors;
    }
}
