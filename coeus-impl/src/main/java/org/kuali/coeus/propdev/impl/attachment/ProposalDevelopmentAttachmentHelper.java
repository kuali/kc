package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalDevelopmentAttachmentHelper {
    private Narrative narrative;
    private Narrative instituteAttachment;
    private ProposalPersonBiography biography;

    private String selectedLineIndex;
    private String markAllStatus;

    private Map<String,List<String>> editableFileAttachments;

    public ProposalDevelopmentAttachmentHelper() {
        instituteAttachment = new Narrative();
        narrative = new Narrative();
        biography = new ProposalPersonBiography();
        editableFileAttachments = new HashMap<String,List<String>>();
    }

    public void reset() {
        instituteAttachment = new Narrative();
        narrative = new Narrative();
        biography = new ProposalPersonBiography();
        editableFileAttachments = new HashMap<String,List<String>>();
        selectedLineIndex = null;
    }

    public ProposalPersonBiography getBiography() {
        return biography;
    }

    public void setBiography(ProposalPersonBiography biography) {
        this.biography = biography;
    }

    public Narrative getNarrative() {
        return narrative;
    }

    public void setNarrative(Narrative narrative) {
        this.narrative = narrative;
    }

    public String getSelectedLineIndex() {
        return selectedLineIndex;
    }

    public void setSelectedLineIndex(String selectedLineIndex) {
        this.selectedLineIndex = selectedLineIndex;
    }

    public String getMarkAllStatus() {
        return markAllStatus;
    }

    public void setMarkAllStatus(String markAllStatus) {
        this.markAllStatus = markAllStatus;
    }

    public Narrative getInstituteAttachment() {
        return instituteAttachment;
    }

    public void setInstituteAttachment(Narrative instituteAttachment) {
        this.instituteAttachment = instituteAttachment;
    }

    public Map<String, List<String>> getEditableFileLineAttachments() {
        return editableFileAttachments;
    }

    public void setEditableFileAttachments(Map<String, List<String>> editableFileAttachments) {
        this.editableFileAttachments = editableFileAttachments;
    }
}
