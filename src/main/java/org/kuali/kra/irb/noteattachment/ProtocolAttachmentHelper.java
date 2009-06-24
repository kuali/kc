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

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
public class ProtocolAttachmentHelper implements Serializable {
    
    private transient final ProtocolAttachmentService notesService;
    private transient final TaskAuthorizationService authService;
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private final ProtocolForm form;
    
    private ProtocolAttachmentProtocol newAttachmentProtocol;
    private ProtocolAttachmentPersonnel newAttachmentPersonnel;
    private ProtocolAttachmentNotification newAttachmentNotification;
    
    private boolean modifyAttachments;

    /**
     * Constructs a helper setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    public ProtocolAttachmentHelper(final ProtocolForm form) {
        this(form, KraServiceLocator.getService(ProtocolAttachmentService.class), KraServiceLocator.getService(TaskAuthorizationService.class));
    }
    
    /**
     * Constructs a helper.
     * @param form the form
     * @param notesService the notesService
     * @param authService the authService
     * @throws IllegalArgumentException if the form, notesService, or authService is null
     */
    ProtocolAttachmentHelper(final ProtocolForm form, final ProtocolAttachmentService notesService, final TaskAuthorizationService authService) {
        if (form == null) {
            throw new IllegalArgumentException("the form was null");
        }
        
        if (notesService == null) {
            throw new IllegalArgumentException("the notesService was null");
        }
        
        if (authService == null) {
            throw new IllegalArgumentException("the authService was null");
        }
        
        this.form = form;
        this.notesService = notesService;
        this.authService = authService;
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
    private Protocol getProtocol() {

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
    public void setNewAttachmentProtocol(ProtocolAttachmentProtocol newAttachmentProtocol) {
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
    public void setNewAttachmentPersonnel(ProtocolAttachmentPersonnel newAttachmentPersonnel) {
        this.newAttachmentPersonnel = newAttachmentPersonnel;
    }
    
    /**
     * Gets the new attachment notification. This method will not return null.
     * Also, The ProtocolAttachmentNotification should have a valid protocol Id at this point.
     * @return the new attachment notification
     */
    public ProtocolAttachmentNotification getNewAttachmentNotification() {
        if (this.newAttachmentNotification == null) {
            this.initAttachmentNotification();
        }
        
        return this.newAttachmentNotification;
    }

    /**
     * Sets the new attachment notification.
     * @param newAttachmentNotification the new attachment notification
     */
    public void setNewAttachmentNotification(ProtocolAttachmentNotification newAttachmentNotification) {
        this.newAttachmentNotification = newAttachmentNotification;
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
     * @param modifyProtocol true if modification is allowed false if not.
     */
    public void setModifyAttachments(boolean modifyAttachments) {
        this.modifyAttachments = modifyAttachments;
    }
    
    /**
     * Adds the "new" ProtocolAttachmentProtocol to the Protocol Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    void addNewProtocolAttachmentProtocol() {      
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
        
        this.getProtocol().addAttachmentProtocol(this.newAttachmentProtocol);
        this.notesService.saveAttatchment(this.newAttachmentProtocol);
        this.initAttachmentProtocol();
    }
    
    /**
     * Adds the "new" ProtocolAttachmentPersonnel to the Protocol Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    void addNewProtocolAttachmentPersonnel() {      
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
        
        this.getProtocol().addAttachmentPersonnel(this.newAttachmentPersonnel);
        this.notesService.saveAttatchment(this.newAttachmentPersonnel);
        this.initAttachmentPersonnel();
    }
    
    /**
     * Adds the "new" ProtocolAttachmentNotification to the Protocol Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    void addNewProtocolAttachmentNotification() {      
        /*
         * Since this event isn't created by the framework and this rule isn't executed by the framework,
         * is it necessary to even create a event?  Does the rule have to implement BusinessRule?  There
         * doesn't seem to be many advantages to doing these things...
         */
//        final AddProtocolAttachmentNotificationRule rule = new AddProtocolAttachmentNotificationRuleImpl();
//        final AddProtocolAttachmentNotificationEvent event = new AddProtocolAttachmentNotificationEvent(this.form.getDocument(), this.newAttachmentNotification);
//        
//        if (!rule.processAddProtocolAttachmentNotificationRules(event)) {
//            return;
//        }
        
        this.getProtocol().addAttachmentNotification(this.newAttachmentNotification);
        this.notesService.saveAttatchment(this.newAttachmentNotification);
        this.initAttachmentNotification();
    }
    
    /**
     * Deletes the "existing" ProtocolAttachmentProtocol from the Protocol Document.
     * If attachmentNumber is not valid then this method does returns false.  This is because
     * the item to delete comes from the client and may not be a valid item.
     * 
     * @param attachmentNumber the item to delete.
     * @return whether a delete successfully executed.
     */
    boolean deleteExistingAttachmentProtocol(final int attachmentNumber) {
        return deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentProtocols());
    }
    
    /**
     * Deletes the "existing" ProtocolAttachmentPersonnel from the Protocol Document.
     * If attachmentNumber is not valid then this method does returns false.  This is because
     * the item to delete comes from the client and may not be a valid item.
     * 
     * @param attachmentNumber the item to delete.
     * @return whether a delete successfully executed.
     */
    boolean deleteExistingAttachmentPersonnel(final int attachmentNumber) {
        return deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentPersonnels());
    }
    
    /**
     * Deletes the "existing" ProtocolAttachmentNotification from the Protocol Document.
     * If attachmentNumber is not valid then this method does returns false.  This is because
     * the item to delete comes from the client and may not be a valid item.
     * 
     * @param attachmentNumber the item to delete.
     * @return whether a delete successfully executed.
     */
    boolean deleteExistingAttachmentNotification(final int attachmentNumber) {
        return deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentNotifications());
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
        this.notesService.deleteAttatchment(attachments.remove(index));
        
        return true;
    }
    
    /**
     * Retrieves the "existing" ProtocolAttachmentProtocol from the Protocol Document.
     * If attachmentNumber is not valid then this method returns {@code null}.  This is because
     * the item to retrieve comes from the client and may not be a valid item.
     * 
     * @param attachmentNumber the item to delete.
     * @return the ProtocolAttachmentProtocol
     */
    ProtocolAttachmentProtocol retrieveExistingAttachmentProtocol(final int attachmentNumber) {
        return retrieveExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentProtocols());
    }
    
    /**
     * Retrieves the "existing" ProtocolAttachmentPersonnel from the Protocol Document.
     * If attachmentNumber is not valid then this method returns {@code null}.  This is because
     * the item to retrieve comes from the client and may not be a valid item.
     * 
     * @param attachmentNumber the item to delete.
     * @return the ProtocolAttachmentPersonnel
     */
    ProtocolAttachmentPersonnel retrieveExistingAttachmentPersonnel(final int attachmentNumber) {
        return retrieveExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentPersonnels());
    }
    
    /**
     * Retrieves the "existing" ProtocolAttachmentNotification from the Protocol Document.
     * If attachmentNumber is not valid then this method returns {@code null}.  This is because
     * the item to retrieve comes from the client and may not be a valid item.
     * 
     * @param attachmentNumber the item to delete.
     * @return the ProtocolAttachmentNotification
     */
    ProtocolAttachmentNotification retrieveExistingAttachmentNotification(final int attachmentNumber) {
        return retrieveExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentNotifications());
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
     * initializes a new attachment notification setting the protocol id.
     */
    private void initAttachmentNotification() {
        this.setNewAttachmentNotification(new ProtocolAttachmentNotification(this.getProtocol()));
    }
    
    /** refreshes all attachment's references that can change on the protocol. */
    void refreshAttachmentReferences() {
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentPersonnels());
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentProtocols());
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentPersonnel()));
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentProtocol()));
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentNotification()));
    }
    
    /** 
     * refreshes a given Collection of attachment's references that can change.
     * @param attachments the attachments.
     */
    private void refreshAttachmentReferences(final Collection<? extends ProtocolAttachmentBase> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (ProtocolAttachmentBase attachment : attachments) {   
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
    
    
    /** Syncs all "new" files for attachments on the protocol. If a new file does not exist then this method does nothing. */
    void syncNewFiles() {
        this.syncNewFiles(this.getProtocol().getAttachmentPersonnels());
        this.syncNewFiles(this.getProtocol().getAttachmentProtocols());
        this.syncNewFiles(this.getProtocol().getAttachmentNotifications());
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentPersonnel()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentProtocol()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentNotification()));
    }
    
    /** 
     * Syncs all new files for a given Collection of attachments on the protocol.
     * @param attachments the attachments.
     */
    private void syncNewFiles(final Collection<? extends ProtocolAttachmentBase> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (final ProtocolAttachmentBase attachment : attachments) {
            if (attachment.getNewFile() != null && StringUtils.isNotBlank(attachment.getNewFile().getFileName())) {
                attachment.setFile(ProtocolAttachmentFile.createFromFormFile(attachment.getNewFile()));
            }
        }
    }
}
