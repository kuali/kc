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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.util.CollectionUtil;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This is the "Helper" class for Protocol Notes And Attachments.
 */
public class NotesAttachmentsHelper {
    
    private static final String NAMESPACE = "KC-UNT";

    private static final String UNSUPPORTED_ATTACHMENT_TYPE = "unsupported attachment type ";
    private static final String CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL = "confirmDeleteAttachmentPersonnel";
    private static final String CONFIRM_YES_DELETE_ATTACHMENT_PROTOCOL = "confirmDeleteAttachmentProtocol";
    private static final String CONFIRM_YES_DELETE_NOTE = "confirmDeleteNote";
    
    private final ProtocolAttachmentService notesService;
    private final TaskAuthorizationService authService;
    private final KraAuthorizationService kraAuthorizationService;
    private final DateTimeService dateTimeService;
    private final ProtocolNotepadService protocolNotepadService;
    private final ParameterService parameterService;
    
    private final ProtocolAttachmentVersioningUtility versioningUtil;
    
    private final ProtocolForm form;
    
    private ProtocolAttachmentProtocol newAttachmentProtocol;
    private ProtocolAttachmentPersonnel newAttachmentPersonnel;
    private ProtocolAttachmentFilter newAttachmentFilter;
    private List<AttachmentFile> FilesToDelete;
    
    private ProtocolNotepad protocolNotepad;

    
    private boolean modifyAttachments;
    private boolean modifyNotepads;
    private boolean viewRestricted;
    
    private boolean manageNotesOpen;
    


    /**
     * Constructs a helper setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    public NotesAttachmentsHelper(final ProtocolForm form) {
        this(form, KraServiceLocator.getService(ProtocolAttachmentService.class), 
                   KraServiceLocator.getService(TaskAuthorizationService.class),
                   KraServiceLocator.getService(KraAuthorizationService.class),
                   KraServiceLocator.getService(DateTimeService.class),
                   KraServiceLocator.getService(ProtocolNotepadService.class),
                   KraServiceLocator.getService(ParameterService.class),
                   new ProtocolAttachmentVersioningUtility(form));
    }
    
    /**
     * Constructs a helper.
     * @param form the form
     * @param notesService the notesService
     * @param authService the authService
     * @param versioningUtil the versioning util
     * @throws IllegalArgumentException if the form, notesService, authService, or versioningUtil is null
     */
    NotesAttachmentsHelper(final ProtocolForm form,
                           final ProtocolAttachmentService notesService,
                           final TaskAuthorizationService authService,
                           final KraAuthorizationService kraAuthorizationService,
                           final DateTimeService dateTimeService,
                           final ProtocolNotepadService protocolNotepadService,
                           final ParameterService parameterService,
                           final ProtocolAttachmentVersioningUtility versioningUtil) {
        
        if (form == null) {
            throw new IllegalArgumentException("the form was null");
        }
        
        if (notesService == null) {
            throw new IllegalArgumentException("the notesService was null");
        }
        
        if (authService == null) {
            throw new IllegalArgumentException("the authService was null");
        }
        
        if (kraAuthorizationService == null) {
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
        this.kraAuthorizationService = kraAuthorizationService;
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
     * Checks if Protocol Attachments can be modified.
     * @return true if can be modified false if cannot
     */
    private boolean canEditProtocolAttachments() {
        final ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ATTACHMENTS, this.getProtocol());
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
    public ProtocolAttachmentProtocol getNewAttachmentProtocol() {
        if (this.newAttachmentProtocol == null) {
            this.initAttachmentProtocol();
        }
        
        return this.newAttachmentProtocol;
    }

    /**
     * Sets the new attachment protocol.
     * @param newAttachmentProtocol the new attachment protocol
     */
    public void setNewAttachmentProtocol(final ProtocolAttachmentProtocol newAttachmentProtocol) {
        this.newAttachmentProtocol = newAttachmentProtocol;
    }

    /**
     * Gets the new attachment personnel. This method will not return null.
     * Also, The ProtocolAttachmentPersonnel should have a valid protocol Id at this point.
     * @return the new attachment personnel
     */
    public ProtocolAttachmentPersonnel getNewAttachmentPersonnel() {
        if (this.newAttachmentPersonnel == null) {
            this.initAttachmentPersonnel();
        }
        
        return this.newAttachmentPersonnel;
    }

    /**
     * Sets the new attachment personnel.
     * @param newAttachmentPersonnel the new attachment personnel
     */
    public void setNewAttachmentPersonnel(final ProtocolAttachmentPersonnel newAttachmentPersonnel) {
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
        for(ProtocolNotepad note: this.getProtocol().getNotepads()) {
            updateUserFieldsIfNecessary(note); 
        }
    }
    
    /**
     * 
     * This method...
     * @param parameterMap
     */
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
              e.printStackTrace();
            }
        }
    }
    
    /**
     * Adds the "new" ProtocolAttachmentProtocol to the Protocol Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    void addNewProtocolAttachmentProtocol() {
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentProtocol()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentProtocol()));
        
//        this.assignDocumentId(Collections.singletonList(this.getNewAttachmentProtocol()),
//                this.createTypeToMaxDocNumber(this.getProtocol().getAttachmentProtocols()));
        
        /*
         * Since this event isn't created by the framework and this rule isn't executed by the framework,
         * is it necessary to even create a event?  Does the rule have to implement BusinessRule?  There
         * doesn't seem to be many advantages to doing these things...
         */
        final AddProtocolAttachmentProtocolRule rule = new AddProtocolAttachmentProtocolRuleImpl();
        final AddProtocolAttachmentProtocolEvent event = new AddProtocolAttachmentProtocolEvent(this.form.getDocument(), this.newAttachmentProtocol);
        
        if (rule.processAddProtocolAttachmentProtocolRules(event)) {
            this.addNewAttachment(this.newAttachmentProtocol);
            this.initAttachmentProtocol();
        } else {
            this.newAttachmentProtocol.setFile(null);
        }
    }
    
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
    <T extends ProtocolAttachmentBase> boolean deleteExistingAttachmentByType(final int attachmentNumber, final Class<T> type) {
        
        final boolean deleted;
        
        if (ProtocolAttachmentProtocol.class.equals(type)) {
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
    <T extends ProtocolAttachmentBase> T retrieveExistingAttachmentByType(final int attachmentNumber, final Class<T> type) {

        final T attachment;
        
        if (ProtocolAttachmentProtocol.class.equals(type)) {
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
    <T extends ProtocolAttachmentBase> String retrieveConfirmMethodByType(final Class<T> type) {
        final String confirmMethod;
         
        if (ProtocolAttachmentProtocol.class.equals(type)) {
            confirmMethod = CONFIRM_YES_DELETE_ATTACHMENT_PROTOCOL;
        } else if (ProtocolAttachmentPersonnel.class.equals(type)) {
            confirmMethod = CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL;
        } else if (ProtocolNotepad.class.equals(type)) {
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
        
        if (!CollectionUtil.validIndexForList(index, attachments)) {
            return false;
        }
        
        if (this.versioningUtil.versioningRequired() && attachments.get(index).supportsVersioning()) {
            ProtocolAttachmentBase attachment = attachments.get(index);
            if (attachment instanceof ProtocolAttachmentProtocol && "1".equals(((ProtocolAttachmentProtocol)attachment).getDocumentStatusCode())) {
               // attachments.remove(index);
                ((ProtocolAttachmentProtocol)attachment).setDocumentStatusCode("3");
                ((ProtocolAttachmentProtocol)attachment).setChanged(true);
            } else {
                this.versioningUtil.versionDeletedAttachment(attachments.get(index));
            }
        } else {
            // personnelattachment will reach here
            attachments.remove(index);
            //this.notesService.deleteAttatchment(attachments.remove(index));
        }
        
        return true;
    }
    
    private void checkTodeleteFile(ProtocolAttachmentPersonnel attachment) {
        if (attachment.getFileId() != null && !notesService.isSharedFile(attachment)) {
            FilesToDelete.add(attachment.getFile());
        }
    }
    
    /** 
     * Removes an attachment from the passed in List if a valid index.
     * @param <T> the attachment type
     * @param the attachment to add
     * @param attachments the attachment list to add the attachment to
     */
    private <T extends ProtocolAttachmentBase> void addNewAttachment(final T attachment) {
        
        if (this.versioningUtil.versioningRequired() && attachment.supportsVersioning()) {
            this.versioningUtil.versionNewAttachment(attachment);
        } else {
            this.getProtocol().addAttachmentsByType(attachment);
//            this.notesService.saveAttatchment(attachment);
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
        return CollectionUtil.getFromList(index, attachments);
    }
    
    /**
     * initializes a new attachment protocol setting the protocol id.
     */
    private void initAttachmentProtocol() {
        this.setNewAttachmentProtocol(new ProtocolAttachmentProtocol(this.getProtocol()));
    }
    
    /**
     * initializes a new attachment personnel setting the protocol id.
     */
    private void initAttachmentPersonnel() {
        this.setNewAttachmentPersonnel(new ProtocolAttachmentPersonnel(this.getProtocol()));
    }
    
    /**
     * initializes a new attachment filter
     */
    private void initAttachmentFilter() {
        ProtocolAttachmentFilter paFilter = new ProtocolAttachmentFilter();
        
        //Lets see if there is a default set for the attachment sort
        try {
            String defaultSortBy = parameterService.getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_PROTOCOL_ATTACHMENT_DEFAULT_SORT);
            if (StringUtils.isNotBlank(defaultSortBy)) {
                paFilter.setSortBy(defaultSortBy);
            }
        } catch (Exception e) {
            //No default found, do nothing.
        }

        this.setNewAttachmentFilter(paFilter);
    }
    
    /** 
     * refreshes a given Collection of attachment's references that can change.
     * @param attachments the attachments.
     */
    private void refreshAttachmentReferences(final Collection<? extends ProtocolAttachmentBase> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (final ProtocolAttachmentBase attachment : attachments) {   
            if (attachment instanceof ProtocolAttachmentProtocol) {
                attachment.refreshReferenceObject("status");   
            }
            
            if (attachment instanceof ProtocolAttachmentPersonnel) {
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
            if (NotesAttachmentsHelper.doesNewFileExist(attachment)) {
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
    
    /** 
     * assigns a document id to all attachments in the passed in collection based on the passed in type to doc number map. 
     * 
     * @param <T> the type of attachments
     * @param attachments the collection of attachments that require doc number assignment
     * @param typeToDocNumber the map that contains all the highest doc numbers of existing attachments based on type code
     */
    private <T extends ProtocolAttachmentBase & TypedAttachment> void assignDocumentId(final Collection<T> attachments,
        final Map<String, Integer> typeToDocNumber) {
        assert attachments != null : "the attachments was null";
        assert typeToDocNumber != null : "the typeToDocNumber was null";
       
        for (final T attachment : attachments) {
            if (attachment.isNew()) {
                final Integer nextDocNumber = NotesAttachmentsHelper.createNextDocNumber(typeToDocNumber.get(attachment.getTypeCode()));
                attachment.setDocumentId(nextDocNumber);
            }
        }
    }
    
    /**
     * Creates a map containing the highest doc number from a collection of attachments for each type code.
     * @param <T> the type
     * @param attachments the collection of attachments
     * @return the map
     */
    private <T extends ProtocolAttachmentBase & TypedAttachment> Map<String, Integer> createTypeToMaxDocNumber(final Collection<T> attachments) {
        assert attachments != null : "the attachments was null";
        
        final Map<String, Integer> typeToDocNumber = new HashMap<String, Integer>();
        
        for (final T attachment : attachments) {
            final Integer curMax = typeToDocNumber.get(attachment.getTypeCode());
            if (curMax == null || curMax.intValue() < attachment.getDocumentId().intValue()) {
                typeToDocNumber.put(attachment.getTypeCode(), attachment.getDocumentId());
            }
        }
        
        return typeToDocNumber;
    }
    
    /**
     * Creates the next doc number from a passed in doc number.  If null 1 is returned.
     * @param docNumber the doc number to base the new number off of.
     * @return the new doc number.
     */
    private static Integer createNextDocNumber(final Integer docNumber) {
        return docNumber == null ? NumberUtils.INTEGER_ONE : Integer.valueOf(docNumber.intValue() + 1);
    }
    
    /**
     * Checks if a new file exists on an attachment
     * 
     * @param attachment the attachment
     * @return true if new false if not
     */
    private static boolean doesNewFileExist(final ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment was null";
        return attachment.getNewFile() != null && StringUtils.isNotBlank(attachment.getNewFile().getFileName());
    }

    public List<AttachmentFile> getFilesToDelete() {
        return FilesToDelete;
    }

    public void setFilesToDelete(List<AttachmentFile> filesToDelete) {
        FilesToDelete = filesToDelete;
    }
    
    /**
     * Checks if Protocol Notepads can be modified.
     * @return true if can be modified false if cannot
     */
    private boolean canAddProtocolNotepads() {
        final ProtocolTask task = new ProtocolTask(TaskName.ADD_PROTOCOL_NOTES, this.getProtocol());
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
     * Gets the new protocol notepad.  This method will not return null.
     * Also, The ProtocolAttachmentProtocol should have a valid protocol Id at this point.
     * 
     * @return the new protocol notepad
     */
    public ProtocolNotepad getNewProtocolNotepad() {
        if (this.protocolNotepad == null) {
            this.initProtocolNotepad();
        }
        
        return this.protocolNotepad;
    }

    /**
     * Sets the new protocol notepad.
     * @param protocolNotepad the new protocol notepad
     */
    public void setNewProtocolNotepad(final ProtocolNotepad protocolNotepad) {
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
    
    public boolean isIrbAdmin() {
        return this.kraAuthorizationService.hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IRB_ADMINISTRATOR);
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
    public void addNewNote() {
    
        final AddProtocolNotepadRule rule = new AddProtocolNotepadRuleImpl();
        final AddProtocolNotepadEvent event = new AddProtocolNotepadEvent(this.form.getDocument(), this.protocolNotepad);
        
        if (!rule.processAddProtocolNotepadRules(event)) {
            return;
        }

        this.addNewNotepad(this.protocolNotepad);
        
        this.initProtocolNotepad();
    }
    
    /**
     * modifies an existing note in the protocol.
     */
    public void modifyNote(int noteToModify) {
    
        ProtocolNotepad notepadObject = this.getProtocol().getNotepads().get(noteToModify);
        final ModifyProtocolNotepadRule rule = new ModifyProtocolNotepadRuleImpl();
        final ModifyProtocolNotepadEvent event = new ModifyProtocolNotepadEvent(this.form.getDocument(), notepadObject);

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
        final DeleteProtocolNotepadEvent event = new DeleteProtocolNotepadEvent(this.form.getDocument(), this.protocolNotepad);
        
        if (!rule.processDeleteProtocolNotepadRules(event)) {
            return false;
        }

        this.deleteNotepad(noteToDelete);
        return true;
    }
    
    void updateUserFieldsIfNecessary(ProtocolNotepad currentNote) {
        if (currentNote.isEditable()) {
                setUpdateFields(currentNote);
        }
    }

    /**
     * Update the User and Timestamp for the business object.
     * @param bo the business object
     */
    private void setUpdateFields(KraPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
    }

    /**
     * Adds the passed in notepad to the list on the protocol.
     * @param notepad the notepad to add.
     */
    private void addNewNotepad(ProtocolNotepad notepad) {
        setUpdateFields(notepad);
        // set notepad to be editable
        notepad.setEditable(true);
        this.getProtocol().getNotepads().add(notepad);
    }
    
    /**
     * Adds the passed in notepad to the list on the protocol.
     * @param notepad the notepad to add.
     */
    private void modifyNotepad(ProtocolNotepad notepadObject) {
        setUpdateFields(notepadObject);
        // set notepad to be editable
        notepadObject.setEditable(true);
    }
    
    /**
     * Adds the passed in notepad to the list on the protocol.
     * @param notepad the notepad to add.
     */
    private void deleteNotepad(int noteToDelete) {
        this.getProtocol().getNotepads().remove(noteToDelete);
    }
    
    /** gets the next entry number based on previously generated numbers. */
    private Integer getNextEntryNumber() {
        final Collection<ProtocolNotepad> notepads = this.getProtocol().getNotepads();
        final Integer maxEntry = notepads.isEmpty() ? Integer.valueOf(0) : Collections.max(notepads, ProtocolNotepad.NotepadByEntryNumber.INSTANCE).getEntryNumber();
        return Integer.valueOf(maxEntry.intValue() + 1);
    }
    
    public void setManageNotesOpen(boolean manageNotesOpen){
        this.manageNotesOpen = manageNotesOpen;
    }
    
    public boolean isManageNotesOpen() {
        return this.manageNotesOpen;
    }

    public ProtocolAttachmentFilter getNewAttachmentFilter() {
        if (newAttachmentFilter == null) {
            this.initAttachmentFilter();
        }
        return newAttachmentFilter;
    }

    public void setNewAttachmentFilter(ProtocolAttachmentFilter newAttachmentFilter) {
        this.newAttachmentFilter = newAttachmentFilter;
    }
    
    public void addNewProtocolAttachmentFilter() {
        this.getProtocol().setProtocolAttachmentFilter(getNewAttachmentFilter());
    }

}
