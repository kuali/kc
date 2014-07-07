package org.kuali.coeus.s2sgen.api.generate;

import java.util.List;

/**
 * This class holds the result of executing form generation on a proposal document.
 */
public class FormGenerationResult extends FormValidationResult {

    private String applicationXml;
    private List<AttachmentData> attachments;

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
