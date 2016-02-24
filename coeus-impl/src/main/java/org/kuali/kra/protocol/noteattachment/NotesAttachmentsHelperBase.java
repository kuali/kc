/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.protocol.noteattachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

/**
 * This is the "Helper" class for ProtocolBase Notes And Attachments.
 */
public abstract class NotesAttachmentsHelperBase {

    private static final Log LOG = LogFactory.getLog(NotesAttachmentsHelperBase.class);

    protected static final String NAMESPACE = "KC-UNT";

    protected static final String UNSUPPORTED_ATTACHMENT_TYPE = "unsupported attachment type ";
    protected static final String CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL = "confirmDeleteAttachmentPersonnel";
    protected static final String CONFIRM_YES_DELETE_ATTACHMENT_PROTOCOL = "confirmDeleteAttachmentProtocol";
    protected static final String CONFIRM_YES_DELETE_NOTE = "confirmDeleteNote";
    
    protected final ProtocolAttachmentService notesService;
    protected final TaskAuthorizationService authService;
    protected final SystemAuthorizationService systemAuthorizationService;
    protected final DateTimeService dateTimeService;
    protected final ProtocolNotepadService protocolNotepadService;
    protected final ParameterService parameterService;
    
    protected final ProtocolAttachmentVersioningUtilityBase versioningUtil;
    
    protected final ProtocolFormBase form;
    
    protected ProtocolAttachmentProtocolBase newAttachmentProtocol;
    protected ProtocolAttachmentPersonnelBase newAttachmentPersonnel;
    protected ProtocolAttachmentFilterBase newAttachmentFilter;
    protected List<AttachmentFile> FilesToDelete;
    
    protected ProtocolNotepadBase protocolNotepad;

    
    protected boolean modifyAttachments;
    protected boolean modifyNotepads;
    protected boolean viewRestricted;
    
    protected boolean manageNotesOpen;

    /**
     * Constructs a helper.
     * @param form the form
     * @param notesService the notesService
     * @param authService the authService
     * @param versioningUtil the versioning util
     * @throws IllegalArgumentException if the form, notesService, authService, or versioningUtil is null
     */
    protected NotesAttachmentsHelperBase(final ProtocolFormBase form,
                           final ProtocolAttachmentService notesService,
                           final TaskAuthorizationService authService,
                           final SystemAuthorizationService systemAuthorizationService,
                           final DateTimeService dateTimeService,
                           final ProtocolNotepadService protocolNotepadService,
                           final ParameterService parameterService,
                           final ProtocolAttachmentVersioningUtilityBase versioningUtil) {
        
        if (form == null) {
            throw new IllegalArgumentException("the form was null");
        }
        
        if (notesService == null) {
            throw new IllegalArgumentException("the notesService was null");
        }
        
        if (authService == null) {
            throw new IllegalArgumentException("the authService was null");
        }
        
        if (systemAuthorizationService == null) {
            throw new IllegalArgumentException("the kraAuthorizationService was null");
        }
        
        if (dateTimeService == null) {
            throw new IllegalArgumentException("the dateTimeService was null.");
        }

        if (protocolNotepadService == null) {
            throw new IllegalArgumentException("the protocolNotepadService was null.");
        }
        
        if (parameterService == null) {
            throw new IllegalArgumentException("the parameterService was null.");
        }
        
        if (versioningUtil == null) {
            throw new IllegalArgumentException("the versioningUtil was null");
        }
        
        this.form = form;
        this.notesService = notesService;
        this.authService = authService;
        this.systemAuthorizationService = systemAuthorizationService;
        this.dateTimeService = dateTimeService;
        this.protocolNotepadService = protocolNotepadService;
        this.parameterService = parameterService;
        this.versioningUtil = versioningUtil;
        this.FilesToDelete = new ArrayList<AttachmentFile>() ;
        this.manageNotesOpen = false;
    }
    
    /**
     * Prepare the tab for viewing.
     */
    public void prepareView() {
        this.initializePermissions();
        notesService.setProtocolAttachmentUpdateUsersName(form.getProtocolDocument().getProtocol().getAttachmentProtocols());
        protocolNotepadService.setProtocolNotepadUpdateUsersName(form.getProtocolDocument().getProtocol().getNotepads());
    }
    
    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    private void initializePermissions() {
        this.modifyAttachments = this.canEditProtocolAttachments();
        this.modifyNotepads = this.canAddProtocolNotepads();
        this.viewRestricted = this.canViewRestrictedProtocolNotepads();
    }
    
    /**
     * Checks if ProtocolBase Attachments can be modified.
     * @return true if can be modified false if cannot
     */
    public abstract boolean canEditProtocolAttachments();
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    /**
     * Get the ProtocolBase.
     * @return the ProtocolBase
     * @throws IllegalArgumentException if the {@link ProtocolDocumentBase ProtocolDocumentBase}
     * or {@link ProtocolBase ProtocolBase} is {@code null}.
     */
    public ProtocolBase getProtocol() {

        if (this.form.getProtocolDocument() == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (this.form.getProtocolDocument().getProtocol() == null) {
            throw new IllegalArgumentException("the protocol is null");
        }

        return this.form.getProtocolDocument().getProtocol();
    }
    
    /**
     * Gets the new attachment protocol.  This method will not return null.
     * Also, The ProtocolAttachmentProtocolBase should have a valid protocol Id at this point.
     * 
     * @return the new attachment protocol
     */
    public ProtocolAttachmentProtocolBase getNewAttachmentProtocol() {
        if (this.newAttachmentProtocol == null) {
            this.initAttachmentProtocol();
        }
        
        return this.newAttachmentProtocol;
    }

    /**
     * Sets the new attachment protocol.
     * @param newAttachmentProtocol the new attachment protocol
     */
    public void setNewAttachmentProtocol(final ProtocolAttachmentProtocolBase newAttachmentProtocol) {
        this.newAttachmentProtocol = newAttachmentProtocol;
    }

    /**
     * Gets the new attachment personnel. This method will not return null.
     * Also, The ProtocolAttachmentPersonnelBase should have a valid protocol Id at this point.
     * @return the new attachment personnel
     */
    public ProtocolAttachmentPersonnelBase getNewAttachmentPersonnel() {
        if (this.newAttachmentPersonnel == null) {
            this.initAttachmentPersonnel();
        }
        
        return this.newAttachmentPersonnel;
    }

    /**
     * Sets the new attachment personnel.
     * @param newAttachmentPersonnel the new attachment personnel
     */
    public void setNewAttachmentPersonnel(final ProtocolAttachmentPersonnelBase newAttachmentPersonnel) {
        this.newAttachmentPersonnel = newAttachmentPersonnel;
    }
    
    /**
     * returns whether a protocol can be modified.
     * @return true if modification is allowed false if not.
     */
    public boolean isModifyAttachments() {
        return this.modifyAttachments;
    }

    /**
     * sets whether a protocol can be modified.
     * @param modifyAttachments true if modification is allowed false if not.
     */
    public void setModifyAttachments(final boolean modifyAttachments) {
        this.modifyAttachments = modifyAttachments;
    }
    
    /** 
     * this method handles logic related to saving attachments.
     * Since multiple attachments can change on a single save, this method must handle all attachment types.
     */
    public void processSave() {
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentProtocols());
        
        this.syncNewFiles(this.getProtocol().getAttachmentProtocols());
        
        if (this.versioningUtil.versioningRequired()) {
            this.versioningUtil.versionExstingAttachments();
        }
        
        //process update user fields for note BOs
        for(ProtocolNotepadBase note: this.getProtocol().getNotepads()) {
            updateUserFieldsIfNecessary(note); 
        }
    }
    

    public void fixReloadedAttachments(Map parameterMap) {
        Iterator keys = parameterMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next().toString();
            String fieldNameStarter = "protocolRefreshButtonClicked";
            try{
                if (key.indexOf(fieldNameStarter) > -1) {
                    //we have a refresh button checker field
                    String fieldValue = ((String[]) parameterMap.get(key))[0];
                    if ("T".equals(fieldValue)) {
                        //a refresh button has been clicked, now we just need to update the appropriate attachment status code
                        int numericVal = Integer.valueOf(key.substring(fieldNameStarter.length()));
                        String documentStatusCode = this.getProtocol().getAttachmentProtocols().get(numericVal).getDocumentStatusCode();
                        if (StringUtils.isBlank(documentStatusCode) || "3".equals(documentStatusCode)) {
                            this.getProtocol().getAttachmentProtocols().get(numericVal).setDocumentStatusCode("1");
                        }
                    }
                }
            } catch (Exception e) {
              //we could get an exception from the getting of the integer, or from the collection not having the integer in it's index/
              //do nothing.
              LOG.error(e.getMessage(), e);
            }
        }
    }
    
    /**
     * Adds the "new" ProtocolAttachmentProtocolBase to the ProtocolBase Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    public void addNewProtocolAttachmentProtocol() {
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentProtocol()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentProtocol()));        
        /*
         * Since this event isn't created by the framework and this rule isn't executed by the framework,
         * is it necessary to even create a event?  Does the rule have to implement BusinessRule?  There
         * doesn't seem to be many advantages to doing these things...
         */
          final AddProtocolAttachmentProtocolRule rule = getAddProtocolAttachmentProtocolRuleInstanceHook();
          final AddProtocolAttachmentProtocolEvent event = new AddProtocolAttachmentProtocolEvent(this.form.getProtocolDocument(), this.newAttachmentProtocol);
       
        if (rule.processAddProtocolAttachmentProtocolRules(event)) {
            this.addNewAttachment(this.newAttachmentProtocol);
            this.initAttachmentProtocol();
        } else {
              this.newAttachmentProtocol.setFile(null);
        }

    }
    
    public abstract AddProtocolAttachmentProtocolRule getAddProtocolAttachmentProtocolRuleInstanceHook();
    
    /**
     * 
     * Deletes an attachment from a protocol based on a type.
     * 
     * @param <T> the type parameter
     * @param attachmentNumber the attachment number
     * @param type the type token
     * @return true if successfully deleted.
     * @throws IllegalArgumentException if the type is null or not recognized
     */
    public <T extends ProtocolAttachmentBase> boolean deleteExistingAttachmentByType(final int attachmentNumber, final Class<T> type) {
        
        final boolean deleted;
        
        if (getProtocolAttachmentProtocolClassHook().equals(type)) {
            deleted = this.deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentProtocols());
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_ATTACHMENT_TYPE + type);
        }
        return deleted;
    }
     
    /**
     * retrieves an attachment from a protocol based on a type.
     * 
     * @param <T> the type parameter
     * @param attachmentNumber the attachment number
     * @param type the type token
     * @return the attachment or null if not found
     * @throws IllegalArgumentException if the type is null or not recognized
     */
    @SuppressWarnings("unchecked")
    public <T extends ProtocolAttachmentBase> T retrieveExistingAttachmentByType(final int attachmentNumber, final Class<T> type) {

        final T attachment;
        
        if (getProtocolAttachmentProtocolClassHook().equals(type)) {
            attachment = (T) retrieveExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentProtocols());
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_ATTACHMENT_TYPE + type);
        }
        return attachment;
    }
    
    /**
     * Retrieves the confirmation method based on a type. This method is highly coupled to the corresponding Action class.
     * This method is here in order to keep the "ByType" methods together.
     * 
     * @param <T> the type parameter
     * @param type the type token
     * @return the confirmation method
     * @throws IllegalArgumentException if the type is null or not recognized
     */
    public <T extends ProtocolAttachmentBase> String retrieveConfirmMethodByType(final Class<T> type) {
        final String confirmMethod;
         
        if (getProtocolAttachmentProtocolClassHook().equals(type)) {
            confirmMethod = CONFIRM_YES_DELETE_ATTACHMENT_PROTOCOL;
        } else if (getProtocolAttachmentPersonnelClassHook().equals(type)) {
            confirmMethod = CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL;
        } else if (getProtocolNotepadClassHook().equals(type)) {
            confirmMethod = CONFIRM_YES_DELETE_NOTE;
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_ATTACHMENT_TYPE + type);
        }
         
        return confirmMethod;
    }
    
    /** 
     * Removes an attachment from the passed in List if a valid index.
     * @param <T> the attachment type
     * @param index the index
     * @param attachments the attachment list
     * @return true deleted false if not a valid index
     */
    private <T extends ProtocolAttachmentBase> boolean deleteExistingAttachment(final int index, final List<T> attachments) {
        
        if (!CollectionUtils.validIndexForList(index, attachments)) {
            return false;
        }
        
        if (this.versioningUtil.versioningRequired() && attachments.get(index).supportsVersioning()) {
            ProtocolAttachmentBase attachment = attachments.get(index);
            if (attachment instanceof ProtocolAttachmentProtocolBase && "1".equals(((ProtocolAttachmentProtocolBase)attachment).getDocumentStatusCode())) {
                ((ProtocolAttachmentProtocolBase)attachment).setDocumentStatusCode("3");
                ((ProtocolAttachmentProtocolBase)attachment).setChanged(true);
            } else {
                this.versioningUtil.versionDeletedAttachment(attachments.get(index));
            }
        } else {
            // personnelattachment will reach here
            attachments.remove(index);
        }
        
        return true;
    }
    
    /** 
     * Removes an attachment from the passed in List if a valid index.
     * @param <T> the attachment type
     * @param attachment the attachment to add
     */
    private <T extends ProtocolAttachmentBase> void addNewAttachment(final T attachment) {
        
        if (this.versioningUtil.versioningRequired() && attachment.supportsVersioning()) {
            this.versioningUtil.versionNewAttachment(attachment);
        } else {
            this.getProtocol().addAttachmentsByType(attachment);
        }      
    }
    
    /** 
     * Retrieves an attachment from the passed in List if a valid index.
     * @param <T> the attachment type
     * @param index the index
     * @param attachments the attachment list
     * @return the attachment or null if not a valid index or if the reference at the index is null
     */
    private static <T extends ProtocolAttachmentBase> T retrieveExistingAttachment(final int index, final List<T> attachments) {
        return CollectionUtils.getFromList(index, attachments);
    }
    
    /**
     * initializes a new attachment protocol setting the protocol id.
     */
    private void initAttachmentProtocol() {
        this.setNewAttachmentProtocol(createNewProtocolAttachmentProtocolInstanceHook(this.getProtocol()));
    }
    
    protected abstract ProtocolAttachmentProtocolBase createNewProtocolAttachmentProtocolInstanceHook(ProtocolBase protocol);
    
    /**
     * initializes a new attachment personnel setting the protocol id.
     */
    private void initAttachmentPersonnel() {
        this.setNewAttachmentPersonnel(createNewProtocolAttachmentPersonnelInstanceHook(this.getProtocol()));
    }
    
    protected abstract ProtocolAttachmentPersonnelBase createNewProtocolAttachmentPersonnelInstanceHook(ProtocolBase protocol);
    
    /**
     * initializes a new attachment filter
     */
    private void initAttachmentFilter() {
        ProtocolAttachmentFilterBase paFilter = createNewProtocolAttachmentFilterInstanceHook();
        
        //Lets see if there is a default set for the attachment sort
        try {
            String defaultSortBy = parameterService.getParameterValueAsString(getProtocolDocumentClassHook(),getAttachmentDefaultSortKeyHook());
            if (StringUtils.isNotBlank(defaultSortBy)) {
                paFilter.setSortBy(defaultSortBy);
            }
        } catch (Exception e) {
            //No default found, do nothing.
        }

        this.setNewAttachmentFilter(paFilter);
    }
    
    protected abstract ProtocolAttachmentFilterBase createNewProtocolAttachmentFilterInstanceHook();
    protected abstract String getAttachmentDefaultSortKeyHook();
    
    /** 
     * refreshes a given Collection of attachment's references that can change.
     * @param attachments the attachments.
     */
    private void refreshAttachmentReferences(final Collection<? extends ProtocolAttachmentBase> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (final ProtocolAttachmentBase attachment : attachments) {   
            if (attachment instanceof ProtocolAttachmentProtocolBase) {
                attachment.refreshReferenceObject("status");   
            }
            
            if (attachment instanceof ProtocolAttachmentPersonnelBase) {
                attachment.refreshReferenceObject("person");
            }

            if (attachment instanceof TypedAttachment) {
                attachment.refreshReferenceObject("type");
            }
        }
    }

    /** 
     * Syncs all new files for a given Collection of attachments on the protocol.
     * @param attachments the attachments.
     */
    private void syncNewFiles(final Collection<? extends ProtocolAttachmentBase> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (final ProtocolAttachmentBase attachment : attachments) {
            if (getKcAttachmentService().doesNewFileExist(attachment.getNewFile())) {
                final AttachmentFile newFile = AttachmentFile.createFromFormFile(attachment.getNewFile());
                //setting the sequence number to the old file sequence number
                if (attachment.getFile() != null) {
                    newFile.setSequenceNumber(attachment.getFile().getSequenceNumber());
                }
                attachment.setFile(newFile);
                // set to null, so the subsequent post will not creating new file again
                attachment.setNewFile(null);
            }
        }
    }

    public List<AttachmentFile> getFilesToDelete() {
        return FilesToDelete;
    }

    public void setFilesToDelete(List<AttachmentFile> filesToDelete) {
        FilesToDelete = filesToDelete;
    }
    
    /**
     * Checks if ProtocolBase Notepads can be modified.
     * @return true if can be modified false if cannot
     */
    public abstract boolean canAddProtocolNotepads();
    
    /**
     * Checks if restricted ProtocolBase Notepads can be viewed.
     * @return true if can be modified false if cannot
     */
    public abstract boolean canViewRestrictedProtocolNotepads();

    /**
     * Gets the new protocol notepad.  This method will not return null.
     * Also, The ProtocolAttachmentProtocolBase should have a valid protocol Id at this point.
     * 
     * @return the new protocol notepad
     */
    public ProtocolNotepadBase getNewProtocolNotepad() {
        if (this.protocolNotepad == null) {
            this.initProtocolNotepad();
        }
        
        return this.protocolNotepad;
    }

    /**
     * Sets the new protocol notepad.
     * @param protocolNotepad the new protocol notepad
     */
    public void setNewProtocolNotepad(final ProtocolNotepadBase protocolNotepad) {
        this.protocolNotepad = protocolNotepad;
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
    
// Note: Changing the name to make this more generic to work with both irb and iacuc.
//       Pushing this method down to return true for its respective admin.  This flag
//       is specifically used in the protocolNotes.tag file to determine if something 
//       is read only.
    public abstract boolean isProtocolAdmin();
    
    /**
     * initializes a new attachment protocol setting the protocol id.
     */
    private void initProtocolNotepad() {
        final ProtocolNotepadBase notepad = createNewProtocolNotepadInstanceHook(this.getProtocol());
        notepad.setEntryNumber(this.getNextEntryNumber());
        notepad.setEditable(true);
        this.setNewProtocolNotepad(notepad);
    }
    
    /**
     * adds a new note to the protocol.  Also performs validation.
     */
    public void addNewNote() {
        final AddProtocolNotepadRule rule = new AddProtocolNotepadRuleImpl();
        final AddProtocolNotepadEvent event = new AddProtocolNotepadEvent(this.form.getProtocolDocument(), this.protocolNotepad);
        
        if (!rule.processAddProtocolNotepadRules(event)) {
            return;
        }
        ProtocolNotepadBase pn = this.protocolNotepad;
        pn.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        pn.setCreateTimestamp(((DateTimeService) KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        this.addNewNotepad(pn);
        
        this.initProtocolNotepad();
    }
    
    /**
     * modifies an existing note in the protocol.
     */
    public void modifyNote(int noteToModify) {
    
        ProtocolNotepadBase notepadObject = this.getProtocol().getNotepads().get(noteToModify);
        final ModifyProtocolNotepadRule rule = new ModifyProtocolNotepadRuleImpl();
        final ModifyProtocolNotepadEvent event = new ModifyProtocolNotepadEvent(this.form.getProtocolDocument(), notepadObject);

        if (!rule.processModifyProtocolNotepadRules(event)) {
            return;
        }
        modifyNotepad(notepadObject);
    }
    
    /**
     * deletes a note from the protocol.
     */
    public boolean deleteNote(int noteToDelete) {
        final DeleteProtocolNotepadRule rule = new DeleteProtocolNotepadRuleImpl();
        final DeleteProtocolNotepadEvent event = new DeleteProtocolNotepadEvent(this.form.getProtocolDocument(), this.protocolNotepad);
        
        if (!rule.processDeleteProtocolNotepadRules(event)) {
            return false;
        }

        this.deleteNotepad(noteToDelete);
        return true;
    }
    
    void updateUserFieldsIfNecessary(ProtocolNotepadBase currentNote) {
        if (currentNote.isEditable()) {
            setUpdateFields(currentNote);
        }
    }

    /**
     * Update the User and Timestamp for the business object.
     * @param bo the business object
     */
    private void setUpdateFields(KcPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
    }

    /**
     * Adds the passed in notepad to the list on the protocol.
     * @param notepad the notepad to add.
     */
    private void addNewNotepad(ProtocolNotepadBase notepad) {
        setUpdateFields(notepad);
        // set notepad to be editable
        notepad.setEditable(true);
        this.getProtocol().getNotepads().add(0, notepad);
    }
    
    /**
     * Adds the passed in notepad to the list on the protocol.
     * @param notepadObject the notepad to add.
     */
    private void modifyNotepad(ProtocolNotepadBase notepadObject) {
        setUpdateFields(notepadObject);
        // set notepad to be editable
        notepadObject.setEditable(true);
    }
    
    /**
     * Adds the passed in notepad to the list on the protocol.
     * @param noteToDelete the notepad to add.
     */
    private void deleteNotepad(int noteToDelete) {
        this.getProtocol().getNotepads().remove(noteToDelete);
    }
    
    /** gets the next entry number based on previously generated numbers. */
    private Integer getNextEntryNumber() {
        final Collection<ProtocolNotepadBase> notepads = this.getProtocol().getNotepads();
        final Integer maxEntry = notepads.isEmpty() ? Integer.valueOf(0) : Collections.max(notepads, ProtocolNotepadBase.NotepadByEntryNumber.INSTANCE).getEntryNumber();
        return Integer.valueOf(maxEntry.intValue() + 1);
    }
    
    public void setManageNotesOpen(boolean manageNotesOpen){
        this.manageNotesOpen = manageNotesOpen;
    }
    
    public boolean isManageNotesOpen() {
        return this.manageNotesOpen;
    }

    public ProtocolAttachmentFilterBase getNewAttachmentFilter() {
        if (newAttachmentFilter == null) {
            this.initAttachmentFilter();
        }
        return newAttachmentFilter;
    }

    public void setNewAttachmentFilter(ProtocolAttachmentFilterBase newAttachmentFilter) {
        this.newAttachmentFilter = newAttachmentFilter;
    }
    
    public void addNewProtocolAttachmentFilter() {
        this.getProtocol().setProtocolAttachmentFilter(getNewAttachmentFilter());
    }
    
    public abstract Class<? extends ProtocolAttachmentProtocolBase> getProtocolAttachmentProtocolClassHook();
    public abstract Class<? extends ProtocolAttachmentPersonnelBase> getProtocolAttachmentPersonnelClassHook();
    public abstract Class<? extends ProtocolNotepadBase> getProtocolNotepadClassHook();
    public abstract Class<? extends ProtocolDocumentBase> getProtocolDocumentClassHook();
    
    protected abstract ProtocolNotepadBase createNewProtocolNotepadInstanceHook(ProtocolBase protocol);

    private KcAttachmentService getKcAttachmentService() {
        return KcServiceLocator.getService(KcAttachmentService.class);
    }
}
