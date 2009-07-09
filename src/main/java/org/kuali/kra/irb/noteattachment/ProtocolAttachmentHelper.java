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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This is the "Helper" class for ProtocolNoteAndAttachment.
 */
public class ProtocolAttachmentHelper implements Serializable {
    
    private static final Log LOG = LogFactory.getLog(ProtocolAttachmentHelper.class);
    
    //TODO: these services cannot be final and transient or else these services will be null
    //upon deserialization.  Rather than this helper being Serializable, we may want to
    //reinitialize it on the form and make instance transient
    private final transient ProtocolAttachmentService notesService;
    private final transient TaskAuthorizationService authService;
    private final transient VersioningService versionService;
    private final transient DocumentService docService;
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private final ProtocolForm form;
    
    private ProtocolAttachmentProtocol newAttachmentProtocol;
    private ProtocolAttachmentPersonnel newAttachmentPersonnel;
    private ProtocolAttachmentNotification newAttachmentNotification;
    
    private boolean modifyAttachments;
    
    //when versioning this will be set to an new version of a Protocol
    //after versioning is complete, this instance will set set back on the form
    //if this is null then versioning has not occurred
    private Protocol tempVersion;

    /**
     * Constructs a helper setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    public ProtocolAttachmentHelper(final ProtocolForm form) {
        this(form, KraServiceLocator.getService(ProtocolAttachmentService.class),
            KraServiceLocator.getService(TaskAuthorizationService.class),
            KraServiceLocator.getService(VersioningService.class),
            KraServiceLocator.getService(DocumentService.class));
    }
    
    /**
     * Constructs a helper.
     * @param form the form
     * @param notesService the notesService
     * @param authService the authService
     * @param versionService the versionService
     * @param docService the docService
     * @throws IllegalArgumentException if the form, notesService, authService, versionService, or docService is null
     */
    ProtocolAttachmentHelper(final ProtocolForm form,
        final ProtocolAttachmentService notesService,
        final TaskAuthorizationService authService,
        final VersioningService versionService,
        final DocumentService docService) {
        
        if (form == null) {
            throw new IllegalArgumentException("the form was null");
        }
        
        if (notesService == null) {
            throw new IllegalArgumentException("the notesService was null");
        }
        
        if (authService == null) {
            throw new IllegalArgumentException("the authService was null");
        }
        
        if (versionService == null) {
            throw new IllegalArgumentException("the versionService was null");
        }
        
        if (docService == null) {
            throw new IllegalArgumentException("the docService was null");
        }
        
        this.form = form;
        this.notesService = notesService;
        this.authService = authService;
        this.versionService = versionService;
        this.docService = docService;
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
    public void setNewAttachmentNotification(final ProtocolAttachmentNotification newAttachmentNotification) {
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
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentNotifications());
        
        this.syncNewFiles(this.getProtocol().getAttachmentPersonnels());
        this.syncNewFiles(this.getProtocol().getAttachmentProtocols());
        this.syncNewFiles(this.getProtocol().getAttachmentNotifications());
        
        //this.versionExstingAttachments();
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
        
        this.getProtocol().addAttachmentProtocol(this.newAttachmentProtocol);
        
        //if create version
            //versionNewAttachments(this.getProtocol().getAttachmentProtocols(), ProtocolAttachmentProtocol.class);
            //finishVersionProcessing();
        //else
            this.notesService.saveAttatchment(this.newAttachmentProtocol);
        
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
        
        this.getProtocol().addAttachmentPersonnel(this.newAttachmentPersonnel);
        
        //if create version
            //versionNewAttachments(this.getProtocol().getAttachmentPersonnels(), ProtocolAttachmentPersonnel.class);
            //finishVersionProcessing();
        //else
            this.notesService.saveAttatchment(this.newAttachmentPersonnel);

        this.initAttachmentPersonnel();
    }
    
    /**
     * Adds the "new" ProtocolAttachmentNotification to the Protocol Document.  Before
     * adding this method executes validation.  If the validation fails the attachment is not added.
     */
    void addNewProtocolAttachmentNotification() {      
        this.refreshAttachmentReferences(Collections.singletonList(this.getNewAttachmentNotification()));
        this.syncNewFiles(Collections.singletonList(this.getNewAttachmentNotification()));
        
        /*
         * Since this event isn't created by the framework and this rule isn't executed by the framework,
         * is it necessary to even create a event?  Does the rule have to implement BusinessRule?  There
         * doesn't seem to be many advantages to doing these things...
         */
//        final AddProtocolAttachmentNotificationRule rule = new AddProtocolAttachmentNotificationRuleImpl();
//        final AddProtocolAttachmentNotificationEvent event
//            = new AddProtocolAttachmentNotificationEvent(this.form.getDocument(), this.newAttachmentNotification);
//        
//        if (!rule.processAddProtocolAttachmentNotificationRules(event)) {
//            return;
//        }
        
        this.getProtocol().addAttachmentNotification(this.newAttachmentNotification);
        
        //if create version
            //versionNewAttachments(this.getProtocol().getAttachmentNotifications(), ProtocolAttachmentNotification.class);
            //finishVersionProcessing();
        //else
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
        return this.deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentProtocols(), ProtocolAttachmentProtocol.class);
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
        return this.deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentPersonnels(), ProtocolAttachmentPersonnel.class);
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
        return this.deleteExistingAttachment(attachmentNumber, this.getProtocol().getAttachmentNotifications(), ProtocolAttachmentNotification.class);
    }
    
    /** 
     * Removes an attachment from the passed in List if a valid index.
     * @param <T> the attachment type
     * @param index the index
     * @param attachments the attachment list
     * @param type the type of attachment
     * @return true deleted false if not a valid index
     */
    private <T extends ProtocolAttachmentBase> boolean deleteExistingAttachment(final int index, final List<T> attachments, Class<T> type) {
        
        if (!validIndexForList(index, attachments)) {
            return false;
        }
        
        //if create version
            versionDeletedAttachments(attachments, type);
            finishVersionProcessing();
        //else
            //this.notesService.deleteAttatchment(attachments.remove(index));
        
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
    
    /** versions all attachments that are applicable for versioning. */
    private void versionExstingAttachments() {
        //only need to version attachment protocols at the moment
        LOG.error("pre version");
        this.versionExstingAttachments(this.getProtocol().getAttachmentProtocols(), ProtocolAttachmentProtocol.class);
        LOG.error("pre version finish");
        this.finishVersionProcessing();
        LOG.error("post version finish");
    }
    
    /**
     * Returns a new collection containing only new attachments.
     * @param <T> the type of attachments in the collection.
     * @param attachments the current collection
     * @return an collection containing only new attachments
     */
    private static <T extends ProtocolAttachmentBase> Collection<T> filterNewAttachments(final Collection<T> attachments) {
        final Collection<T> newAttachments = new ArrayList<T>();
        
        for (final T attachment : attachments) {
            if (ProtocolAttachmentHelper.isNewAttachment(attachment)) {
                newAttachments.add(attachment);
            }
        }
        
        return newAttachments;
    }
    
    /**
     * Returns a new collection containing only exiting attachments.
     * @param <T> the type of attachments in the collection.
     * @param attachments the current collection
     * @return an collection containing only exiting attachments
     */
    private static <T extends ProtocolAttachmentBase> Collection<T> filterExistingAttachments(final Collection<T> attachments) {
        final Collection<T> existingAttachments = new ArrayList<T>(attachments);
        existingAttachments.removeAll(filterNewAttachments(attachments));
        return existingAttachments;
    }
    
    /** 
     * Versions all attachments for a given Collection of attachments on the protocol.  Any deleted attachment will cause a new version
     * of an attachment & protocol to be created. The passed in Collection may be modified.
     * <T> the type of attachments
     * @param attachments the attachments.
     * @param type the class token for type of attachments (required for adding to generic Collection)
     */
    private <T extends ProtocolAttachmentBase> void versionDeletedAttachments(final Collection<T> attachments, final Class<T> type) {
      
        final Collection<Long> ids = new ArrayList<Long>();
        for (T attachment : filterNewAttachments(attachments)) {
            ids.add(attachment.getId());
        }
        
        final Collection<T> deletedAttachments
            = this.notesService.getAttachmentsForProtocolNotMatchingIds(type, getProtocol().getProtocolId(), ids.toArray(new Long[0]));
        if (!deletedAttachments.isEmpty()) {
            this.tempVersion = this.createVersion(this.getProtocol());
            for (final T attachment : deletedAttachments) {
                this.tempVersion.addAttachmentsByType(this.createVersion(attachment, this.tempVersion));
            }  
        }
    }    
    
    /** 
     * Versions all attachments for a given Collection of attachments on the protocol.  Any new attachment will cause a new version
     * of a protocol to be created. The passed in Collection may be modified.
     * <T> the type of attachments
     * @param attachments the attachments.
     * @param type the class token for type of attachments (required for adding to generic Collection)
     */
    private <T extends ProtocolAttachmentBase> void versionNewAttachments(final Collection<T> attachments, final Class<T> type) {
        for (final T attachment : filterNewAttachments(attachments)) {
            ProtocolAttachmentHelper.setInitialVersion(attachment);
            this.tempVersion = this.createVersion(this.getProtocol());
            ///attachments.remove(attachment);
        }
    }
    
    /** 
     * Versions all attachments for a given Collection of attachments on the protocol.  Any changes done on a passed in
     * attachment will effectively be reverted to what is in the database and those changes will be reflected
     * in a new attachment & protocol version.  The passed in Collection may be modified.
     * <T> the type of attachments
     * @param attachments the attachments.
     * @param type the class token for type of attachments (required for adding to generic Collection)
     */
    private <T extends ProtocolAttachmentBase> void versionExstingAttachments(final Collection<T> attachments, final Class<T> type) {
        assert attachments != null : "the attachments was null";
        assert type != null : "the type was null";
        
        Collection<T> versionedAttachments = new ArrayList<T>();
        
        for (final T attachment : filterExistingAttachments(attachments)) {
            final T persistedAttachment = this.notesService.getAttachment(type, attachment.getId());
                
            if (ProtocolAttachmentHelper.hasChanged(attachment, persistedAttachment)) {
                versionedAttachments.add(attachment);
                this.createVersion(attachment, this.createVersion(this.getProtocol()));
            }
        }
        
        if (!versionedAttachments.isEmpty() && this.tempVersion != null) {
            for (final T attachment : versionedAttachments) {
                this.tempVersion.removeAttachmentsByType(attachment);
            }
        }
    }
    
    /**
     * This method sets the new version (if created) on the ProtocolForm and nulls out the {@link #tempVersion tempVersion}.
     * This method must be called after calling all versioning methods 
     * (ex: {@link #versionDeletedAttachments(Collection, Class)}, {@link #versionExstingAttachments()}, {@link #versionNewAttachments(Collection, Class)}
     */
    private void finishVersionProcessing() {
        if (this.tempVersion != null) {
            
            final ProtocolDocument newDoc;
            try {
                newDoc = (ProtocolDocument) this.docService.getNewDocument(ProtocolDocument.class);
            } catch (WorkflowException e) {
                throw new VersionCreationExeption(e);
            }
            
            newDoc.getDocumentHeader().setDocumentDescription(this.form.getDocument().getDocumentHeader().getDocumentDescription());
            newDoc.setProtocol(this.tempVersion);
            this.tempVersion.setProtocolDocument(newDoc);
            this.form.setDocument(newDoc);
        }
        this.tempVersion = null;
    }
    
    /**
     * Sets the initial version number.
     * @param attachment the attachment
     */
    private static void setInitialVersion(final ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment was null";
        attachment.setAttachmentVersionNumber(NumberUtils.INTEGER_ONE);
    }
    
    /**
     * Creates a version of an attachment. The act of versioning associates the attachment with its owner.
     * Also, the attachment is associated as a 1:1 association with its owner.
     * 
     * @param <T> the attachment type
     * @param attachment the attachment
     * @return the new version
     * @throws VersionCreationExeption if unable to create a version
     */
    private <T extends ProtocolAttachmentBase> T createVersion(final T attachment, final Protocol protocol) {
        assert attachment != null : "the attachment was null";
        assert protocol != null : "the protocol was null";
        
        final T newVersion;
        
        try {
            newVersion = this.versionService.versionAssociate(protocol, attachment);
        } catch (final VersionException e) {
            throw new VersionCreationExeption(e);
        }
        
        newVersion.setAttachmentVersionNumber(Integer.valueOf(attachment.getAttachmentVersionNumber().intValue() + 1));
        protocol.addAttachmentsByType(attachment);
        
//        ProtocolAttachmentProtocol p = new ProtocolAttachmentProtocol();
//        p.setAttachmentVersionNumber(newVersion.getAttachmentVersionNumber());
//        p.setComments("a new version");
//        p.setDocumentId(2);
//        p.setFile(newVersion.getFile());
//        p.setProtocol(protocol);
//        p.setStatusCode("1");
//        p.setTypeCode("1");
//        protocol.addAttachmentsByType(p);
//        newVersion.setProtocol(protocol);
//        newVersion.setProtocolId(null);
//        newVersion.setId(null);
//        newVersion.setObjectId(null);
//        newVersion.setVersionNumber(1L);
        //newVersion.getSequenceOwners().clear();
        //newVersion.addSequenceOwner(protocol);
        
        
        //protocol.getAttachmentProtocolsVersions().clear();
        //protocol.getAttachmentProtocolsVersions().add((ProtocolAttachmentProtocol)newVersion);
        //protocol.getAttachmentProtocols().clear();
        //protocol.getAttachmentProtocols().add((ProtocolAttachmentProtocol)newVersion);
        LOG.error(newVersion);
        return newVersion;
    }
    
    /**
     * Creates a new version of a Protocol. The version is saved off at {@link #tempVersion} for later use.
     * @param protocol the protocol to base the version off of.
     * @return the new version
     */
    private Protocol createVersion(final Protocol protocol) {      
        assert protocol != null : "the protocol was null";
        
        try {
            if (this.tempVersion == null) {
                this.tempVersion = this.versionService.createNewVersion(protocol);
            }
            return this.tempVersion;
        } catch (final VersionException e) {
            throw new VersionCreationExeption(e);
        }
    }
    
    /**
     * Checks if a version of an attachment should be created.
     * 
     * @param localAttachment the local attachment
     * @param persistedAttachment the persisted attachment with same id
     * @return true if should be versioned false if not.
     */
    private static boolean hasChanged(final ProtocolAttachmentBase localAttachment, final ProtocolAttachmentBase persistedAttachment) {
        
        if (persistedAttachment == null || localAttachment == null) {
            return false;
        }
                
        return !(persistedAttachment.equals(localAttachment));
    }
    
    /**
     * Checks if an attachment is new (not persisted yet).
     * @param attachment the attachment
     * @return true if new false if not
     */
    private static boolean isNewAttachment(final ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment was null";
        return attachment.getId() == null;
    }

    /** 
     * Syncs all new files for a given Collection of attachments on the protocol.
     * @param attachments the attachments.
     */
    private void syncNewFiles(final Collection<? extends ProtocolAttachmentBase> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (final ProtocolAttachmentBase attachment : attachments) {
            if (ProtocolAttachmentHelper.doesNewFileExist(attachment)) {
                attachment.setFile(ProtocolAttachmentFile.createFromFormFile(attachment.getNewFile()));
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
            if (ProtocolAttachmentHelper.isNewAttachment(attachment)) {
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
    
    /**
     * Exception thrown for a version creation problem.
     */
    private static class VersionCreationExeption extends RuntimeException {
        
        private static final long serialVersionUID = -8816811550466248534L;

        /**
         * wraps a throwable
         * @param t the original throwable.
         */
        public VersionCreationExeption(final Throwable t) {
            super(t);
        }
    }
}
