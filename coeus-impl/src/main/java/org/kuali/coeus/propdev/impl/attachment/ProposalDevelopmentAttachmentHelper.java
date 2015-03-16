/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalDevelopmentAttachmentHelper {
    private Narrative narrative;
    private Narrative instituteAttachment;
    private Note note;
    private ProposalPersonBiography biography;
    private ProposalAbstract proposalAbstract;

    private String selectedLineIndex;
    private String proposalAttachmentModuleStatusCode;
    private String internalAttachmentModuleStatusCode;

    private Map<String,List<String>> editableFileAttachments;

    public ProposalDevelopmentAttachmentHelper() {
        instituteAttachment = new Narrative();
        narrative = new Narrative();
        biography = new ProposalPersonBiography();
        note = new Note();
        proposalAbstract = new ProposalAbstract();
        editableFileAttachments = new HashMap<String,List<String>>();
    }

    public void reset() {
        instituteAttachment = new Narrative();
        narrative = new Narrative();
        biography = new ProposalPersonBiography();
        note = new Note();
        proposalAbstract = new ProposalAbstract();
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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public ProposalAbstract getProposalAbstract() {
        return proposalAbstract;
    }

    public void setProposalAbstract(ProposalAbstract proposalAbstract) {
        this.proposalAbstract = proposalAbstract;
    }

	public String getProposalAttachmentModuleStatusCode() {
		return proposalAttachmentModuleStatusCode;
	}

	public void setProposalAttachmentModuleStatusCode(String proposalAttachmentModuleStatusCode) {
		this.proposalAttachmentModuleStatusCode = proposalAttachmentModuleStatusCode;
	}

	public String getInternalAttachmentModuleStatusCode() {
		return internalAttachmentModuleStatusCode;
	}

	public void setInternalAttachmentModuleStatusCode(String internalAttachmentModuleStatusCode) {
		this.internalAttachmentModuleStatusCode = internalAttachmentModuleStatusCode;
	}
}
