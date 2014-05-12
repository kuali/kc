package org.kuali.kra.s2s.service;

import org.kuali.kra.s2s.util.AuditError;

import java.util.List;

public class FormActionResult {

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
