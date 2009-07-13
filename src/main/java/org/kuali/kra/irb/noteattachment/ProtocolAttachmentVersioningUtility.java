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

import org.apache.commons.lang.math.NumberUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Class used for versioning protocol attachments.
 */
public class ProtocolAttachmentVersioningUtility implements Serializable {

    private final transient ProtocolAttachmentService notesService;
    private final transient VersioningService versionService;
    private final transient DocumentService docService;
    
    private final ProtocolForm form;
    private ProtocolDocument newDocumentVersion;
    
    /**
     * Constructs a versioning util setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    ProtocolAttachmentVersioningUtility(final ProtocolForm form) {
        this(form, KraServiceLocator.getService(ProtocolAttachmentService.class),
            KraServiceLocator.getService(VersioningService.class), KraServiceLocator.getService(DocumentService.class));
    }
    
    /**
     * Constructs a versioning util.
     * @param form the form
     * @param notesService the notesService
     * @param versionService the versionService
     * @param docService the docService
     * @throws IllegalArgumentException if the form, notesService, versionService, or docService is null
     */
    ProtocolAttachmentVersioningUtility(final ProtocolForm form, final ProtocolAttachmentService notesService, final VersioningService versionService, final DocumentService docService) {
        if (form == null) {
            throw new IllegalArgumentException("the form was null");
        }
        
        if (notesService == null) {
            throw new IllegalArgumentException("the notesService was null");
        }
        
        if (versionService == null) {
            throw new IllegalArgumentException("the versionService was null");
        }
        
        if (docService == null) {
            throw new IllegalArgumentException("the docService was null");
        }
        
        this.form = form;
        this.versionService = versionService;
        this.docService = docService;
        this.notesService = notesService;
    }
    
    /**
     * Methods checks whether versioning is required (i.e. does adding/deleting/modfying
     * an attachment trigger a version or not)
     * @return true if versioning is required.
     */
    boolean versioningRequired() {
        return true;
    }
    
    /** 
     * Versions the passed in attachment.  The act of versioning will do the following:
     * Create a new document and create a new protocol version.
     * 
     * The new attachment will be added to the protocol and the new protocol will set on the document.
     * Everything will be saved to the database and the document will be set on the passed in form.
     * As a result, the new document will become the "open" document on the form.
     * 
     * @param toVersion the attachment to version.
     * @throws IllegalArgumentException if the attachment is null or if the attachment is not "new"
     */
    void versionNewAttachment(final ProtocolAttachmentBase toVersion) {
        if (toVersion == null) {
            throw new IllegalArgumentException("the attachment to version was null");
        }
        
        if (!toVersion.isNew()) {
            throw new IllegalArgumentException("the attachment to version is not new " + toVersion);
        }
        
        ProtocolAttachmentVersioningUtility.setInitialVersion(toVersion);
        
        this.createVersion(this.form.getDocument());
        this.saveVersionedDocument();
        
        this.addAttachmentToVersion(toVersion);
        this.saveVersionedDocument();
        
        this.finishVersionProcessing();
    }
    
    
    /** 
     * Versions the passed in attachment.  The act of versioning will do the following:
     * Create a new document, create a new protocol version, and create a new attachment
     * version indicating that the attachment is now deleted.
     * 
     * The new attachment will be added to the protocol and the new protocol will set on the document.
     * Everything will be saved to the database and the document will be set on the passed in form.
     * As a result, the new document will become the "open" document on the form.
     * 
     * @param toVersion the attachment to version.
     * @throws IllegalArgumentException if the attachment is null or if the attachment is "new"
     */
    void versionDeletedAttachment(final ProtocolAttachmentBase toDelete) {
        if (toDelete == null) {
            throw new IllegalArgumentException("the attachment to version was null");
        }
        
        if (toDelete.isNew()) {
            throw new IllegalArgumentException("the attachment to version is new " + toDelete);
        }
        
        this.createVersion(this.form.getDocument());
        this.removeAttachmentFromVersion(toDelete);
        this.saveVersionedDocument();
        
        final ProtocolAttachmentBase newVersion = this.createAttachmentVersionOnNewDocumentVersion(toDelete);
        //FIXME: temp until I figure out how to display deletes
        newVersion.getFile().setName("NEW VERSION - " + newVersion.getFile().getName());
        this.saveVersionedDocument();
        
        this.finishVersionProcessing();
    }
    
    
    /**
     * 
     * This method retrieves all the existing attachments on the passed in form.  For every modified attachment,
     * this method will create a new attachment version.
     * 
     * If any attachments are versioned this method will do the following: Create a new document, create a new protocol
     * version, and create a new attachment version.  This method will only trigger one new document or protocol version
     * regardless of the amount of modified attachments.
     * 
     * The new attachments will be added to the protocol and the new protocol will set on the document.
     * Everything will be saved to the database and the document will be set on the passed in form.
     * As a result, the new document will become the "open" document on the form.
     */
    void versionExstingAttachments() {
        //only need to version attachment protocols at the moment
        this.versionExstingAttachments(this.form.getDocument().getProtocol().getAttachmentProtocols(), ProtocolAttachmentProtocol.class);
        
        this.finishVersionProcessing();     
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
        
        final Collection<T> toVersionAndAdd = new ArrayList<T>();
        
        for (final T attachment : ProtocolAttachmentBase.filterExistingAttachments(attachments)) {
            final T persistedAttachment = this.notesService.getAttachment(type, attachment.getId());
                
            if (hasChanged(attachment, persistedAttachment)) {
                this.createVersion(this.form.getDocument());
                this.removeAttachmentFromVersion(attachment);
                
                toVersionAndAdd.add(attachment);
            }
        }
        
        if (this.doesNewDocumentVersionExist()) {
            this.saveVersionedDocument();
            
            for (final T attachment : toVersionAndAdd) {
                this.createAttachmentVersionOnNewDocumentVersion(attachment);
            }
            this.saveVersionedDocument();
        }
    }

    /**
     * removes an attachment from a new protocol version.
     * @param attachment the attachment to remove.
     */
    private void removeAttachmentFromVersion(ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment is null";
        assert this.newDocumentVersion != null : "the newDocumentVersion is null";
        assert this.newDocumentVersion.getProtocol() != null : "the new document version's protocol is null";
        
        this.newDocumentVersion.getProtocol().removeAttachmentsByType(attachment);
    }
    
    /**
     * Adds an attachment to the new protocol version.
     * @param attachment the attachment to add.
     */
    private void addAttachmentToVersion(ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment is null";
        assert this.newDocumentVersion != null : "the newDocumentVersion is null";
        assert this.newDocumentVersion.getProtocol() != null : "the new document version's protocol is null";
        
        attachment.setProtocol(this.newDocumentVersion.getProtocol());
        this.newDocumentVersion.getProtocol().addAttachmentsByType(attachment);
    }
    
    /**
     * This method sets the new version (if created) on the ProtocolForm and nulls out the {@link #newDocumentVersion tempVersion}.
     * This method must be called after calling all versioning methods 
     * (ex: {@link #versionDeletedAttachments(Collection, Class)}, {@link #versionExstingAttachments()}, {@link #versionNewAttachments(Collection, Class)}
     */
    private void finishVersionProcessing() {
        if (this.doesNewDocumentVersionExist()) {
            this.form.setDocument(this.newDocumentVersion);
        }
        
        this.newDocumentVersion = null;
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
     * Checks if a new Protocol Document version has been created.
     * 
     * @return true if a new version exists.  false if not.
     */
    private boolean doesNewDocumentVersionExist() {
        return this.newDocumentVersion != null;
    }
    
    /**
     * Creates a new a Protocol Document from a Protocol.  This method requires that the passed in
     * ProtocolDocument and Protocol are existing versions.  If they are not existing there is likely
     * a programming error since there would be no point in versioning.
     * 
     * The version is saved off at {@link #newDocumentVersion} for later use.
     * 
     * @param protocolDocument the protocol document to base the version off of.
     * @return the new version
     */
    private ProtocolDocument createVersion(final ProtocolDocument protocolDocument) {      
        assert protocolDocument != null : "the protocol was null";
        assert protocolDocument.getDocumentNumber() != null : "the document number is null";
        assert protocolDocument.getProtocol() != null : "the protocol is null";
        assert protocolDocument.getProtocol().getProtocolId() != null : "the protocol id is null";
        
        try {
            if (!this.doesNewDocumentVersionExist()) {
                final Protocol protocolVersion = this.versionService.createNewVersion(protocolDocument.getProtocol());
                
                try {
                    this.newDocumentVersion = (ProtocolDocument) this.docService.getNewDocument(ProtocolDocument.class);
                } catch (final WorkflowException e) {
                    throw new VersionCreationExeption(e);
                }
                
                this.newDocumentVersion.getDocumentHeader().setDocumentDescription(this.form.getDocument().getDocumentHeader().getDocumentDescription());
                this.newDocumentVersion.setProtocol(protocolVersion);
                protocolVersion.setProtocolDocument(this.newDocumentVersion);
            }
            return this.newDocumentVersion;
        } catch (final VersionException e) {
            throw new VersionCreationExeption(e);
        }
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
    private <T extends ProtocolAttachmentBase> T createAttachmentVersionOnNewDocumentVersion(final T attachment) {
        assert attachment != null : "the attachment was null";
        
        final T newVersion;
        
        try {
            newVersion = this.versionService.versionAssociate(this.newDocumentVersion.getProtocol(), attachment);
        } catch (final VersionException e) {
            throw new VersionCreationExeption(e);
        }
        
        newVersion.setAttachmentVersionNumber(Integer.valueOf(attachment.getAttachmentVersionNumber().intValue() + 1));
        newVersion.setProtocol(this.newDocumentVersion.getProtocol());
        this.addAttachmentToVersion(newVersion);
        
        return newVersion;
    }
    
    /**
     * Saves a versioned document if a new version has been created.  If not, this method does nothing.
     */
    private void saveVersionedDocument() {
        if (this.doesNewDocumentVersionExist()) {
            try {
                this.docService.saveDocument(this.newDocumentVersion);
            }
            catch (final WorkflowException e) {
                throw new VersionCreationExeption(e);
            }            
        }
    }
    
    
    /**
     * Exception thrown for a version creation problem.
     */
    private static class VersionCreationExeption extends RuntimeException {
        
        private static final long serialVersionUID = -8816811550466248534L;

        /**
         * wraps a throwable.
         * @param t the original throwable.
         */
        public VersionCreationExeption(final Throwable t) {
            super(t);
        }
    }
}
