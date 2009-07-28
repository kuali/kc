/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This is the "Helper" class for ProtocolNoteAndAttachment.
 */
public class ProtocolAttachmentHelper {
    
    private static final String UNSUPPORTED_ATTACHMENT_TYPE = "unsupported attachment type ";
    private static final String CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL = "confirmDeleteAttachmentPersonnel";
    private static final String CONFIRM_YES_DELETE_ATTACHMENT_PROTOCOL = "confirmDeleteAttachmentProtocol";
    
    private final ProtocolAttachmentService notesService;
    private final TaskAuthorizationService authService;
    
    private final ProtocolAttachmentVersioningUtility versioningUtil;
    
    private final ProtocolForm form;
    
    private ProtocolAttachmentProtocol newAttachmentProtocol;
    private ProtocolAttachmentPersonnel newAttachmentPersonnel;
    
    private boolean modifyAttachments;
    
    /**
     * Constructs a helper setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    public ProtocolAttachmentHelper(final ProtocolForm form) {
        this(form, KraServiceLocator.getService(ProtocolAttachmentService.class), KraServiceLocator.getService(TaskAuthorizationService.class), new ProtocolAttachmentVersioningUtility(form));
    }
    
    /**
     * Constructs a helper.
     * @param form the form
     * @param notesService the notesService
     * @param authService the authService
     * @param versioningUtil the versioning util
     * @throws IllegalArgumentException if the form, notesService, authService, or versioningUtil is null
     */
    ProtocolAttachmentHelper(final ProtocolForm form,
        final ProtocolAttachmentService notesService,
        final TaskAuthorizationService authService,
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
        
        if (versioningUtil == null) {
            throw new IllegalArgumentException("the versioningUtil was null");
        }
        
        this.form = form;
        this.notesService = notesService;
        this.authService = authService;
        this.versioningUtil = versioningUtil;
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
        this.modifyAttachments = this.canModifyProtocolAttachments();
    }
    
    /**
     * Checks if Protocol Attachments can be modified.
     * @return true if can be modified false if cannot
     */
    private boolean canModifyProtocolAttachments() {
        final ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ATTACHMENTS, this.getProtocol());
        return this.authService.isAuthorized(this.getUserName(), task);
    }
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    private String getUserName() {
        final UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        return user.getPersonUserIdentifier();
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
    void processSave() {
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentPersonnels());
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentProtocols());
        
        this.syncNewFiles(this.getProtocol().getAttachmentPersonnels());
        this.syncNewFiles(this.getProtocol().getAttachmentProtocols());
        
        if (this.versioningUtil.versioningRequired()) {
            this.versioningUtil.versionExstingAttachments();
        }
    }
    
    /**
     * Adds the "new" ProtocolAttachmentProtocol to the Protocol Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    void addNewProtocolAttachmentProtocol() {
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentProtocol()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentProtocol()));
        
        this.assignDocumentId(Collections.singletonList(this.getNewAttachmentProtocol()),
                this.createTypeToMaxDocNumber(this.getProtocol().getAttachmentProtocols()));
        
        /*
         * Since this event isn't created by the framework and this rule isn't executed by the framework,
         * is it necessary to even create a event?  Does the rule have to implement BusinessRule?  There
         * doesn't seem to be many advantages to doing these things...
         */
        final AddProtocolAttachmentProtocolRule rule = new AddProtocolAttachmentProtocolRuleImpl();
        final AddProtocolAttachmentProtocolEvent event = new AddProtocolAttachmentProtocolEvent(this.form.getDocument(), this.newAttachmentProtocol);
        
        if (!rule.processAddProtocolAttachmentProtocolRules(event)) {
            return;
        }

        this.addNewAttachment(this.newAttachmentProtocol);
        
        this.initAttachmentProtocol();
    }
    
    /**
     * Adds the "new" ProtocolAttachmentPersonnel to the Protocol Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    void addNewProtocolAttachmentPersonnel() {
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentPersonnel()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentPersonnel()));
        
        this.assignDocumentId(Collections.singletonList(this.getNewAttachmentPersonnel()), 
                this.createTypeToMaxDocNumber(this.getProtocol().getAttachmentPersonnels()));
        
        
        /*
         * Since this event isn't created by the framework and this rule isn't executed by the framework,
         * is it necessary to even create a event?  Does the rule have to implement BusinessRule?  There
         * doesn't seem to be many advantages to doing these things...
         */
        final AddProtocolAttachmentPersonnelRule rule = new AddProtocolAttachmentPersonnelRuleImpl();
        final AddProtocolAttachmentPersonnelEvent event = new AddProtocolAttachmentPersonnelEvent(this.form.getDocument(), this.newAttachmentPersonnel);
        
        if (!rule.processAddProtocolAttachmentPersonnelRules(event)) {
            return;
        }
        this.addNewAttachment(this.newAttachmentPersonnel);

        this.initAttachmentPersonnel();
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
        } else if (ProtocolAttachmentPersonnel.class.equals(type)) {
            deleted = this.deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentPersonnels());
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
        } else if (ProtocolAttachmentPersonnel.class.equals(type)) {
            attachment = (T) retrieveExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentPersonnels());
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_ATTACHMENT_TYPE + type);
        }
        return attachment;
    }
    
    /**
     * Retrieves the confirmation method based on a type. This method is highly coupled to the corresponding Action class.
     * This method is here is order to keep the "ByType" methods together.
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
        
        if (!validIndexForList(index, attachments)) {
            return false;
        }
        
        if (this.versioningUtil.versioningRequired() && attachments.get(index).supportsVersioning()) {
            this.versioningUtil.versionDeletedAttachment(attachments.get(index));
        } else {
            this.notesService.deleteAttatchment(attachments.remove(index));
        }
        
        return true;
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
            this.notesService.saveAttatchment(attachment);
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
        if (!validIndexForList(index, attachments)) {
            return null;
        }
        
        return attachments.get(index);
    }
    
    /**
     * Checks if a given index is valid for a given list. This method returns null if the list is null.
     * 
     * @param index the index
     * @param forList the list
     * @return true if a valid index
     */
    private static boolean validIndexForList(final int index, final List<?> forList) {      
        return forList != null && index >= 0 && index <= forList.size() - 1;
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
            if (ProtocolAttachmentHelper.doesNewFileExist(attachment)) {
                final ProtocolAttachmentFile newFile = ProtocolAttachmentFile.createFromFormFile(attachment.getNewFile());
                //setting the sequence number to the old file sequence number
                if (attachment.getFile() != null) {
                    newFile.setSequenceNumber(attachment.getFile().getSequenceNumber());
                }
                attachment.setFile(newFile);
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
                final Integer nextDocNumber = ProtocolAttachmentHelper.createNextDocNumber(typeToDocNumber.get(attachment.getTypeCode()));
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
}
