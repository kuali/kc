/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.home;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.NoteService;

public class InstitutionalProposalNotepad extends InstitutionalProposalAssociate implements SequenceAssociate {

    private static final long serialVersionUID = 1L;

    private Long proposalNotepadId;

    private String proposalNumber;

    private Integer entryNumber;

    private String comments;

    private String noteTopic;

    private boolean restrictedView;

    private Date createTimestamp;
    
    private List<Note> attachments;

    public InstitutionalProposalNotepad() {
        Calendar cl = Calendar.getInstance();
        setCreateTimestamp(new Date(cl.getTime().getTime()));
        attachments = new ArrayList<Note>();
    }

    public Long getProposalNotepadId() {
        return proposalNotepadId;
    }

    public void setProposalNotepadId(Long proposalNotepadId) {
        this.proposalNotepadId = proposalNotepadId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean getRestrictedView() {
        return restrictedView;
    }

    public void setRestrictedView(boolean restrictedView) {
        this.restrictedView = restrictedView;
    }

    /**
     * Gets the noteTopic attribute. 
     * @return Returns the noteTopic.
     */
    public String getNoteTopic() {
        return noteTopic;
    }

    /**
     * Sets the noteTopic attribute value.
     * @param noteTopic The noteTopic to set.
     */
    public void setNoteTopic(String noteTopic) {
        this.noteTopic = noteTopic;
    }

    /**
     * Gets the createTimeStamp attribute. 
     * @return Returns the createTimeStamp.
     */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * Sets the createTimeStamp attribute value.
     * @param createTimeStamp The createTimeStamp to set.
     */
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
        return getInstitutionalProposal();
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setInstitutionalProposal((InstitutionalProposal) newlyVersionedOwner);
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.proposalNotepadId = null;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected void postPersist() {
        NoteService noteService = KraServiceLocator.getService(NoteService.class);
        for (Note note : getAttachments()) {
            note.setRemoteObjectIdentifier(this.getObjectId());
            noteService.save(note);
        }
    }

    public List<Note> getAttachments() {
        if (attachments.isEmpty() && StringUtils.isNotEmpty(getObjectId())) {
            attachments = KraServiceLocator.getService(NoteService.class).getByRemoteObjectId(getObjectId());
        }
        return attachments;
    }

    public void setAttachments(List<Note> attachments) {
        this.attachments = attachments;
    }
}
