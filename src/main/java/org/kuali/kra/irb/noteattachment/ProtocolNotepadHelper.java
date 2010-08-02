/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.noteattachment;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.util.CollectionUtil;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This is the "Helper" class for ProtocolNoteAndAttachment.
 */
public class ProtocolNotepadHelper {
    
    private final TaskAuthorizationService authService;
    private final BusinessObjectService boService;
    private final DateTimeService dateTimeService;
    private final ProtocolForm form;
    
    private ProtocolNotepad newProtocolNotepad;
    
    private boolean modifyNotepads;
    private boolean viewRestricted;
    
    /**
     * Constructs a helper setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    public ProtocolNotepadHelper(final ProtocolForm form) {
        this(form, KraServiceLocator.getService(TaskAuthorizationService.class), KraServiceLocator.getService(BusinessObjectService.class), KraServiceLocator.getService(DateTimeService.class));
        
    }
    
    /**
     * Constructs a helper.
     * @param form the form
     * @param authService the authService
     * @param boService the boService
     * @throws IllegalArgumentException if the form or authService or boService is null
     */
    ProtocolNotepadHelper(final ProtocolForm form,
        final TaskAuthorizationService authService, final BusinessObjectService boService, final DateTimeService dateTimeService) {
        
        if (form == null) {
            throw new IllegalArgumentException("the form was null");
        }
        
        if (authService == null) {
            throw new IllegalArgumentException("the authService was null");
        }
        
        if (boService == null) {
            throw new IllegalArgumentException("the boService was null");
        }
        
        this.form = form;
        this.authService = authService;
        this.boService = boService;
        this.dateTimeService = dateTimeService;
    }
    
    /**
     * Prepare the tab for viewing.
     */
    public void prepareView() {
        this.initializePermissions();
    }
    
    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    private void initializePermissions() {
        this.modifyNotepads = this.canModifyProtocolNotepads();
        this.viewRestricted = this.canViewRestrictedProtocolNotepads();
    }
    
    /**
     * Checks if Protocol Notepads can be modified.
     * @return true if can be modified false if cannot
     */
    private boolean canModifyProtocolNotepads() {
        final ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_NOTEPADS, this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }
    
    /**
     * Checks if restricted Protocol Notepads can be viewed.
     * @return true if can be modified false if cannot
     */
    private boolean canViewRestrictedProtocolNotepads() {
        final ProtocolTask task = new ProtocolTask(TaskName.VIEW_RESTRICTED_NOTES, this.getProtocol());
        return this.authService.isAuthorized(this.getUserIdentifier(), task);
    }
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    /**
     * Get the Protocol.
     * @return the Protocol
     * @throws IllegalArgumentException if the {@link ProtocolDocument ProtocolDocument}
     * or {@link Protocol Protocol} is {@code null}.
     */
    public Protocol getProtocol() {

        if (this.form.getDocument() == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (this.form.getDocument().getProtocol() == null) {
            throw new IllegalArgumentException("the protocol is null");
        }

        return this.form.getDocument().getProtocol();
    }
    
    /**
     * Gets the new attachment protocol.  This method will not return null.
     * Also, The ProtocolAttachmentProtocol should have a valid protocol Id at this point.
     * 
     * @return the new attachment protocol
     */
    public ProtocolNotepad getNewProtocolNotepad() {
        if (this.newProtocolNotepad == null) {
            this.initProtocolNotepad();
        }
        
        return this.newProtocolNotepad;
    }

    /**
     * Sets the new protocol notepad.
     * @param newProtocolNotepad the new protocol notepad
     */
    public void setNewProtocolNotepad(final ProtocolNotepad newProtocolNotepad) {
        this.newProtocolNotepad = newProtocolNotepad;
    }
    
    /**
     * returns whether a protocol can be modified.
     * @return true if modification is allowed false if not.
     */
    public boolean isModifyNotepads() {
        return this.modifyNotepads;
    }

    /**
     * sets whether a protocol can be modified.
     * @param modifyNotepads true if modification is allowed false if not.
     */
    public void setModifyNotepads(final boolean modifyNotepads) {
        this.modifyNotepads = modifyNotepads;
    }
    
    /**
     * returns whether a restricted note can be viewed.
     * @return true if viewing is allowed false if not.
     */
    public boolean isViewRestricted() {
        return this.viewRestricted;
    }

    /**
     * sets whether a restricted note can be viewed.
     * @param viewRestricted true if viewing is allowed false if not.
     */
    public void setViewRestricted(final boolean viewRestricted) {
        this.viewRestricted = viewRestricted;
    }
    
    /**
     * initializes a new attachment protocol setting the protocol id.
     */
    private void initProtocolNotepad() {
        final ProtocolNotepad notepad = new ProtocolNotepad(this.getProtocol());
        notepad.setEntryNumber(this.getNextEntryNumber());
        this.setNewProtocolNotepad(notepad);
    }
    
    /**
     * adds a new note to the protocol.  Also performs validation.
     */
    void addNewNote() {
    
        final AddProtocolNotepadRule rule = new AddProtocolNotepadRuleImpl();
        final AddProtocolNotepadEvent event = new AddProtocolNotepadEvent(this.form.getDocument(), this.newProtocolNotepad);
        
        if (!rule.processAddProtocolNotepadRules(event)) {
            return;
        }

        this.addNewNotepad(this.newProtocolNotepad);
        
        this.initProtocolNotepad();
    }
    
    void processSave() {
        //process update user fields for note BOs
        for(ProtocolNotepad note: this.getProtocol().getNotepads()) {
            updateUserFieldsIfNecessary(note); 
        }

    }
    
    /**
     * Update the User and Timestamp for the business object.
     * @param doc the business object
     */
    private void updateUserFields(KraPersistableBusinessObjectBase bo) {
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();
    
        // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters
        if (updateUser.length() > 60) {
            updateUser = updateUser.substring(0, 60);
        }
        bo.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
        bo.setUpdateUser(updateUser);
    }
    
    /**
     * Gets the selected notepad based on an index.  If the index is not valid this method will return null.
     * @param selection the index
     * @return the notepad or null
     */
    private ProtocolNotepad getSelectedNotepad(int selection) {
        return CollectionUtil.getFromList(selection, getProtocol().getNotepads());
    }
    
    void updateUserFieldsIfNecessary(ProtocolNotepad currentNote) {
        if (currentNote != null) {
            ProtocolNotepad persistedNote = (ProtocolNotepad) this.boService.findBySinglePrimaryKey(ProtocolNotepad.class, currentNote.getId());
            if(!currentNote.equals(persistedNote)) {
                currentNote.setChanged(true);
                updateUserFields(currentNote);
                currentNote.setChanged(false);
            }
        }
    }
    
    /**
     * Updates (saves) the selected notepad based on an index.  If the index is not valid this method will do nothing.
     * @param selection the indexs
     */
    void updateNotepad(int selection) {
        final ProtocolNotepad notepad = this.getSelectedNotepad(selection);
        if (notepad != null) {
            updateUserFieldsIfNecessary(notepad);
            this.boService.save(notepad);
            notepad.setChanged(false);
        }
    }
    
    /**
     * Adds the passed in notepad to the list on the protocol.
     * @param notepad the notepad to add.
     */
    private void addNewNotepad(final ProtocolNotepad notepad) {
        final List<ProtocolNotepad> notepads = this.getProtocol().getNotepads();
        updateUserFields(this.getNewProtocolNotepad());
        notepads.add(this.getNewProtocolNotepad());
        this.getProtocol().setNotepads(notepads);        
    }
    
    /** gets the next entry number based on previously generated numbers. */
    private Integer getNextEntryNumber() {
        final Collection<ProtocolNotepad> notepads = this.getProtocol().getNotepads();
        final Integer maxEntry = notepads.isEmpty() ? Integer.valueOf(0) : Collections.max(notepads, ProtocolNotepad.NotepadByEntryNumber.INSTANCE).getEntryNumber();
        return Integer.valueOf(maxEntry.intValue() + 1);
    }
}
