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
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;

/**
 * This is the "Helper" class for ProtocolNoteAndAttachment.
 */
public class ProtocolAttachmentHelper implements Serializable {
    
    private transient final ProtocolAttachmentService notesService;
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private final ProtocolForm form;
    
    private ProtocolAttachmentProtocol newAttachmentProtocol;
    private ProtocolAttachmentPersonnel newAttachmentPersonnel;
    private boolean modifyProtocol;

    /**
     * Constructs a helper setting the dependencies to default values.
     * @param form the form
     * @throws IllegalArgumentException if the form is null
     */
    public ProtocolAttachmentHelper(final ProtocolForm form) {
        this(form, KraServiceLocator.getService(ProtocolAttachmentService.class));
    }
    
    /**
     * Constructs a helper.
     * @param form the form
     * @param notesService the notesService
     * @throws IllegalArgumentException if the form or notesService is null
     */
    ProtocolAttachmentHelper(final ProtocolForm form, final ProtocolAttachmentService notesService) {
        if (form == null) {
            throw new IllegalArgumentException("the form was null");
        }
        
        if (notesService == null) {
            throw new IllegalArgumentException("the notesService was null");
        }
        
        this.form = form;
        this.notesService = notesService;
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
     * returns whether a protocol can be modified.
     * @return true if modification is allowed false if not.
     */
    public boolean isModifyProtocol() {
        return true;
        //return modifyProtocol;
    }

    /**
     * sets whether a protocol can be modified.
     * @param modifyProtocol true if modification is allowed false if not.
     */
    public void setModifyProtocol(boolean modifyProtocol) {
        this.modifyProtocol = modifyProtocol;
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
     * Deletes the "existing" ProtocolAttachmentProtocol from the Protocol Document.
     * If attachmentNumber is not valid then this method does returns false.  This is because
     * the item to delete comes from the client and may not be a valid item.
     * 
     * @param attachmentNumber the item to delete.
     * @return whether a delete successfully executed.
     */
    boolean deleteExistingAttachmentProtocol(final int attachmentNumber) {

        if (!this.validIndexForList(attachmentNumber, this.getProtocol().getAttachmentProtocols())) {
            return false;
        }
        
        this.getProtocol().getAttachmentProtocols().remove(attachmentNumber);
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
        
        if (!this.validIndexForList(attachmentNumber, this.getProtocol().getAttachmentProtocols())) {
            return null;
        }
        
        return this.getProtocol().getAttachmentProtocols().get(attachmentNumber);
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

        if (!this.validIndexForList(attachmentNumber, this.getProtocol().getAttachmentPersonnels())) {
            return false;
        }
        
        this.getProtocol().getAttachmentPersonnels().remove(attachmentNumber);
        return true;
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
        
        if (!this.validIndexForList(attachmentNumber, this.getProtocol().getAttachmentPersonnels())) {
            return null;
        }
        
        return this.getProtocol().getAttachmentPersonnels().get(attachmentNumber);
    }
    
    /**
     * Checks if a given index is valid for a given list. This method returns null if the list is null.
     * 
     * @param index the index
     * @param forList the list
     * @return true if a valid index
     */
    private boolean validIndexForList(final int index, final List<?> forList) {      
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
    
    /** refreshes all attachment's references that can change on the protocol. */
    void refreshAttachmentReferences() {
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentPersonnels());
        this.refreshAttachmentReferences(this.getProtocol().getAttachmentProtocols());
        this.refreshAttachmentReferences(Collections.singletonList(this.newAttachmentPersonnel));
        this.refreshAttachmentReferences(Collections.singletonList(this.newAttachmentProtocol));
    }
    
    /** 
     * refreshes a given Collection of attachment's references that can change.
     * @param attachments the attachments.
     */
    private void refreshAttachmentReferences(final Collection<? extends ProtocolAttachmentBase> attachments) {
        assert attachments != null : "the attachments was null";
        
        for (ProtocolAttachmentBase attachment : attachments) {   
            if (attachment instanceof ProtocolAttachmentProtocol) {
                final ProtocolAttachmentProtocol protocolAttachment  = (ProtocolAttachmentProtocol) attachment;
                if (protocolAttachment.getStatus() != null && protocolAttachment.getStatus().getCode() != null) {
                    protocolAttachment.setStatus(this.notesService.getStatusFromCode(protocolAttachment.getStatus().getCode()));    
                }
            }
            
            if (attachment instanceof ProtocolAttachmentPersonnel) {
                final ProtocolAttachmentPersonnel personnelAttachment  = (ProtocolAttachmentPersonnel) attachment;
                if (personnelAttachment.getPerson() != null && personnelAttachment.getPerson().getProtocolPersonId() != null) {
                    personnelAttachment.setPerson(this.notesService.getPerson(personnelAttachment.getPerson().getProtocolPersonId()));
                }
            }
            
            if (attachment.getType() != null && attachment.getType().getCode() != null) {
                attachment.setType(this.notesService.getTypeFromCode(attachment.getType().getCode()));    
            }
        }
    }
    
    
    /** Syncs all new files for attachments on the protocol. */
    void syncNewFiles() {
        this.syncNewFiles(this.getProtocol().getAttachmentPersonnels());
        this.syncNewFiles(this.getProtocol().getAttachmentProtocols());
        this.syncNewFiles(Collections.singletonList(this.newAttachmentPersonnel));
        this.syncNewFiles(Collections.singletonList(this.newAttachmentProtocol));
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
