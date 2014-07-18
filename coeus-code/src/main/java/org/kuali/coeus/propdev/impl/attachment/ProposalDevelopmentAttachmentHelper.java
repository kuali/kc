package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;

public class ProposalDevelopmentAttachmentHelper {
    private Narrative narrative;
    private ProposalPersonBiography biography;

    private String selectedLineIndex;
    private String markAllStatus;

    public ProposalDevelopmentAttachmentHelper() {
        narrative = new Narrative();
        biography = new ProposalPersonBiography();
    }

    public void reset() {
        narrative = new Narrative();
        biography = new ProposalPersonBiography();
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
}
