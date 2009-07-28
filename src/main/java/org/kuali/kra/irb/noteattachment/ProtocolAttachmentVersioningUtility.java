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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Class used for versioning protocol attachments.
 * 
 * <p>
 * The basic algorithm for versioning is the following:
 * 
 * <ol>
 * <li> check if versioning is required </li>
 * <li> version the File BO </li>
 * <li> associate the new File version with an "existing" Attachment BO </li>
 * <li> create a new Protocol Document </li>
 * <li> version the Protocol and all its BOs which includes all attachment BOs (that are referencing the new file BO) </li>
 * <li> set the new Protocol Document w/ the new Protocol version </li>
 * <li> set the struts form to reference the new Protocol Document </li>
 * </ol>
 * </p>
 * 
 * It was easier to create the version of the file before the version of the Protocol to allow for easy updating of file references.
 * Also, versioning of the Protocol naturally only needs to happen once, at the end of this process.
 */
public class ProtocolAttachmentVersioningUtility {

    private final ProtocolAttachmentService notesService;
    private final VersioningService versionService;
    private final DocumentService docService;
    
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
        
        this.addAttachment(toVersion);
        
        this.finishVersionProcessing(true);
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
    void versionDeletedAttachment(final ProtocolAttachmentBase toVersion) {
        if (toVersion == null) {
            throw new IllegalArgumentException("the attachment to version was null");
        }
        
        if (toVersion.isNew()) {
            throw new IllegalArgumentException("the attachment to version is new " + toVersion);
        }
        
        this.deleteAttachment(toVersion);

        this.finishVersionProcessing(true);
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
        boolean createVersion = this.versionExstingAttachments(this.form.getDocument().getProtocol().getAttachmentProtocols(), ProtocolAttachmentProtocol.class);
        
        this.finishVersionProcessing(createVersion);     
    }

    /** 
     * Versions all attachments for a given Collection of attachments on the protocol.  Any changes done on a passed in
     * attachment will effectively be reverted to what is in the database and those changes will be reflected
     * in a new attachment & protocol version.  The passed in Collection or objects in Collection may be modified.
     * <T> the type of attachments
     * @param attachments the attachments.
     * @param type the class token for type of attachments (required for adding to generic Collection)
     */
    private <T extends ProtocolAttachmentBase> boolean versionExstingAttachments(final Collection<T> attachments, final Class<T> type) {
        assert attachments != null : "the attachments was null";
        assert type != null : "the type was null";
        
        boolean createVersion = false;
        for (final T attachment : ProtocolAttachmentBase.filterExistingAttachments(attachments)) {
            final T persistedAttachment = this.notesService.getAttachment(type, attachment.getId());
                
            if (hasChanged(attachment.getFile(), persistedAttachment.getFile())) {
                createVersion = true;
                //change the attachments file...when the doc is versioned the change will come w/ it
                createFileVersionOnAttachment(attachment);
            }
        }
        return createVersion;
    }
    
    /**
     * Adds an attachment to the protocol.
     * @param attachment the attachment to add.
     */
    private void addAttachment(ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment is null";
        
        this.form.getDocument().getProtocol().addAttachmentsByType(attachment);
    }
    
    /**
     * Delete an attachment from the protocol.
     * @param attachment the attachment to delete.
     */
    private void deleteAttachment(ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment is null";
        
        this.createFileVersionOnAttachment(attachment);
        //FIXME: temp until I figure out how to display deletes
        attachment.getFile().setName("DELETED VERSION - " + attachment.getFile().getName());
    }
    
    /**
     * This method will create a new Document, Protocol version, save the Document, and set the Document on the form.
     * This method must be called after calling all versioning methods 
     * (ex: {@link #versionDeletedAttachments(Collection)}, {@link #versionExstingAttachments()}, {@link #versionNewAttachments(Collection)}
     * @param makeNewVersion whether to make the new version, save, etc.
     */
    private void finishVersionProcessing(boolean makeNewVersion) {
        if (makeNewVersion) {
            this.createVersion(this.form.getDocument());

            //hack start
            this.doAttachmentPersonnelSaveHack();
            //hack end
            
            this.saveVersionedDocument();
            this.form.setDocument(this.newDocumentVersion);
            this.newDocumentVersion = null;
        }
    }
    
    /**
     * There is a problem with the way OJB handles 1:1/M relationships and the way the versioning framework triggers new versions.
     * 
     * Basically, A Personnel Attachment has a M:1 with Protocol Person.  When the versioning framework finds the Protocol Person
     * it nulls it primary key to trigger an insert.  At this point Personnel Attachment still has an Id field pointing to the old
     * Protocol Person.  This must be nulled out manually or else the Personnel Attachment will attempt to do an insert referencing
     * the wrong Person.  Since the id field is null OJB will violate a DB constraint attempting to add null for the FK field for
     * Person.
     * 
     * For example:
     * <code>
     * class ProtocolAttachment {
     *   String personId; //null
     *   ProtocolPerson person; valid object with null pk
     * }
     * </code>
     * 
     * So this workaround removes all Personnel Attachments from the Protocol.  Calls save.  Post save all Protocol Persons
     * have non-null pks.  Then the work around loops through all attachments setting the correct fk for the Protocol Persons
     * that have now been saved.
     */
    private void doAttachmentPersonnelSaveHack() {
        
        final List<ProtocolAttachmentPersonnel> attachToSave = this.newDocumentVersion.getProtocol().getAttachmentPersonnels();
        this.newDocumentVersion.getProtocol().setAttachmentPersonnels(new ArrayList<ProtocolAttachmentPersonnel>());          
        this.saveVersionedDocument();
        for (final ProtocolAttachmentPersonnel attach : attachToSave) {
            final String personId = attach.getPerson().getPersonId();
            final Integer rolodexId = attach.getPerson().getRolodexId();
            
            for (final ProtocolPerson person : this.newDocumentVersion.getProtocol().getProtocolPersons()) {
                if (personId != null) {
                    if (personId.equals(person.getPersonId())) {
                        attach.setPersonId(person.getProtocolPersonId());
                    }
                } else if (rolodexId != null) {
                    if (rolodexId.equals(person.getRolodexId())) {
                        attach.setPersonId(person.getProtocolPersonId());
                    }
                }
            }
        }
        
        this.newDocumentVersion.getProtocol().setAttachmentPersonnels(attachToSave);
    }
    
    /**
     * Checks if a version of an attachment should be created.
     * 
     * @param localFile the local attachment file
     * @param persistedFile the persisted attachment file with same id
     * @return true if should be versioned false if not.
     */
    private static boolean hasChanged(final ProtocolAttachmentFile localFile, final ProtocolAttachmentFile persistedFile) {
        
        if (persistedFile == null || localFile == null) {
            return false;
        }
                
        return !(persistedFile.equals(localFile));
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
                for (ProtocolAttachmentPersonnel attachment : protocolVersion.getAttachmentPersonnels()) {
                    System.err.println(attachment + " person: " + attachment.getPerson());
                }
                
                
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
     * Creates a version of an attachment's file. The act of versioning associates the passed in attachment with the new file.
     * 
     * @param <T> the attachment type
     * @param attachment the attachment
     * @return the attachment with the new file version
     * @throws VersionCreationExeption if unable to create a version
     */
    private <T extends ProtocolAttachmentBase> T createFileVersionOnAttachment(final T attachment) {
        assert attachment != null : "the attachment was null";
        
        final ProtocolAttachmentFile newVersion;
        
        try {
            newVersion = this.versionService.versionAssociate(attachment.getFile());
        } catch (final VersionException e) {
            throw new VersionCreationExeption(e);
        }
        
        attachment.setFile(newVersion);
        attachment.setFileId(null);
        
        return attachment;
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
     * Checks if a new Protocol Document version has been created.
     * 
     * @return true if a new version exists.  false if not.
     */
    private boolean doesNewDocumentVersionExist() {
        return this.newDocumentVersion != null;
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
