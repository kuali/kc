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
    private String markAllStatus;

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
    
    public void handleNarrativeUpdates(ProposalDevelopmentDocumentForm form, ProposalDevelopmentDocument document) {
    	
    	// Delete Narratives which are not on the form - from document.
    	removeDeletedNarrativesFromDocument(form.getDevelopmentProposal().getNarratives(),document.getDevelopmentProposal().getNarratives());
    	// Handle newly added and updated Narratives
    	addNewUpdateNarrativesOnDocument(form.getDevelopmentProposal().getNarratives(),document.getDevelopmentProposal().getNarratives());
    	
    }
    
    public void handleInstAttachmentUpdates(ProposalDevelopmentDocumentForm form, ProposalDevelopmentDocument document) {
    	
    	// Delete Institute attachment which are not on the form - from document.
    	removeDeletedNarrativesFromDocument(form.getDevelopmentProposal().getInstituteAttachments(),document.getDevelopmentProposal().getInstituteAttachments());
    	// Handle newly added and updated Institute attachment
    	addNewUpdateNarrativesOnDocument(form.getDevelopmentProposal().getInstituteAttachments(),document.getDevelopmentProposal().getInstituteAttachments());
    	
    }
    
    public void handlePersonBioUpdates(ProposalDevelopmentDocumentForm form, ProposalDevelopmentDocument document) {
    	
    	// Delete Person bios which are not on the form - from document.
    	removeDeletedPersonBiosFromDocument(form.getDevelopmentProposal().getPropPersonBios(),document.getDevelopmentProposal().getPropPersonBios());
    	// Handle newly added and updated person bios
    	addNewUpdatePersonBiosOnDocument(form.getDevelopmentProposal().getPropPersonBios(),document.getDevelopmentProposal().getPropPersonBios());

        //Extract the new document next values so they can be saved and not overwritten.
        DocumentNextvalue formNextValue = null;
        for (DocumentNextvalue documentNextValue : form.getProposalDevelopmentDocument().getDocumentNextvalues()) {
            if (documentNextValue.getPropertyName().equalsIgnoreCase(Constants.PROP_PERSON_BIO_NUMBER)) {
                formNextValue = documentNextValue;
                break;
            }
        }
        DocumentNextvalue docNextValue = null;
        for (DocumentNextvalue documentNextValue : document.getDocumentNextvalues()) {
            if (documentNextValue.getPropertyName().equalsIgnoreCase(Constants.PROP_PERSON_BIO_NUMBER)) {
                docNextValue = documentNextValue;
                break;
            }
        }
        if (formNextValue != null
                && docNextValue == null){
            document.getDocumentNextvalues().add(formNextValue);
        }
        else if (formNextValue != null
                && docNextValue != null){
            if (formNextValue.getNextValue() > docNextValue.getNextValue()){
                docNextValue.setNextValue(formNextValue.getNextValue());
            }

        }
    }
    
    private void removeDeletedPersonBiosFromDocument( List<ProposalPersonBiography> formBios, List<ProposalPersonBiography> documentBios) {
    	// Delete Narratives which are not on the form - from document.
    	if(documentBios != null && documentBios.size() > 0) {
    		List<ProposalPersonBiography> deletedBios = new ArrayList<ProposalPersonBiography>();
    		for(ProposalPersonBiography documentBio : documentBios ) {
    			if(findBioByPersonNumAndBioNum(formBios, documentBio) == null ) {
    				deletedBios.add(documentBio);
            	}
    		}
	   		if(deletedBios.size() > 0) {
	   			documentBios.removeAll(deletedBios);
	   		}
    	}
    }
    
    private void addNewUpdatePersonBiosOnDocument(List<ProposalPersonBiography> formBios, List<ProposalPersonBiography> documentBios) {
	   	
    	for(ProposalPersonBiography formBio :  formBios) {
	   		if(formBio.getObjectId() == null) {
	   			documentBios.add(formBio);
	   			continue;
	   		}
	       	if(formBio.isUpdated()) {
	       		ProposalPersonBiography updatedBio = findBioByPersonNumAndBioNum(documentBios,formBio);
	       		documentBios.remove(documentBios.indexOf(updatedBio));
	       		documentBios.add(formBio);
	       	}
	   	}
    }
    
    private void addNewUpdateNarrativesOnDocument(List<Narrative> formNarratives, List<Narrative> documentNarratives) {
	   	
    	for(Narrative formNarrative :  formNarratives) {
	   		if(formNarrative.getObjectId() == null) {
	   			documentNarratives.add(formNarrative);
	   			continue;
	   		}
	       	if(formNarrative.isUpdated()) {
	       		Narrative updatedNarrative = findNarrativeByModuleNumber(documentNarratives,formNarrative.getModuleNumber());
	       		documentNarratives.remove(documentNarratives.indexOf(updatedNarrative));
	       		documentNarratives.add(formNarrative);
	       	}
	   	}
    }
    	
    private void removeDeletedNarrativesFromDocument( List<Narrative> formNarratives, List<Narrative> documentNarratives) {
    	// Delete Narratives which are not on the form - from document.
    	if(documentNarratives != null && documentNarratives.size() > 0) {
    		List<Narrative> deletedNarratives = new ArrayList<Narrative>();
    		for(Narrative documentNarrative : documentNarratives ) {
    			if(findNarrativeByModuleNumber(formNarratives, documentNarrative.getModuleNumber()) == null ) {
            		deletedNarratives.add(documentNarrative);
            	}
    		}
	   		if(deletedNarratives.size() > 0) {
	   			documentNarratives.removeAll(deletedNarratives);
	   		}
    	}
    }
    
    protected ProposalPersonBiography findBioByPersonNumAndBioNum(List<ProposalPersonBiography> origBios, final ProposalPersonBiography newBio) {
    	
    	ProposalPersonBiography dulicatedBio = CollectionUtils.find(origBios, new Predicate<ProposalPersonBiography>() {

			@Override
			public boolean evaluate(ProposalPersonBiography origBio) {
				if(new EqualsBuilder().append(origBio.getProposalPersonNumber(), newBio.getProposalPersonNumber())
						.append(origBio.getBiographyNumber(), newBio.getBiographyNumber()).isEquals()) {
					return true;
				}	
				else {
					return false;
				}
			}
		});

    	return dulicatedBio;
    }
    
    
    protected Narrative findNarrativeByModuleNumber(List<Narrative> origNarratives, final Integer moduleNumber) {
    	
    	Narrative updatedNarrative = CollectionUtils.find(origNarratives, new Predicate<Narrative>() {

			@Override
			public boolean evaluate(Narrative narrative) {
				if(narrative.getModuleNumber().equals(moduleNumber)) {
					return true;
				}	
				else {
					return false;
				}
			}
		});

    	return updatedNarrative;
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
}
