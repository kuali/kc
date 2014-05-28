package org.kuali.kra.s2s.service;

import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.util.AuditError;

import java.util.List;

public class FormActionResult {

    private boolean valid;
    private List<AuditError> errors;
    private String applicationXml;
    private List<AttachmentData> attachments;

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

    public String getApplicationXml() {
        return applicationXml;
    }

    public void setApplicationXml(String applicationXml) {
        this.applicationXml = applicationXml;
    }

    public List<AttachmentData> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentData> attachments) {
        this.attachments = attachments;
    }
}
