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

import java.util.Collections;

import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Person;


/** Implementation of {@link ProtocolAttachmentService ProtocolNoteAndAttachmentService}. */
class ProtocolAttachmentServiceImpl implements ProtocolAttachmentService {

    private final BusinessObjectService boService;
    
    /**
     * Constructor than sets the dependencies.
     * 
     * @param boService the {@link BusinessObjectService BusinessObjectService}
     * @throws NullPointerException if the boService is null
     */
    public ProtocolAttachmentServiceImpl(final BusinessObjectService boService) {
        if (boService == null) {
            throw new NullPointerException("the boService was null");
        }
        
        this.boService = boService;
    }
    
    /** {@inheritDoc} */
    public ProtocolAttachmentStatus getStatusFromCode(final String code) {
        return this.getCodeType(ProtocolAttachmentStatus.class, code);
    }

    /** {@inheritDoc} */
    public ProtocolAttachmentType getTypeFromCode(final String code) {
        return this.getCodeType(ProtocolAttachmentType.class, code);
    }
    
    /** {@inheritDoc} */
    public void saveAttatchment(ProtocolAttachmentProtocol attachment) {
        if (attachment == null) {
            throw new NullPointerException("the attachment is null");
        }
        
        if (attachment.getNewFile() == null) {
            throw new NullPointerException("the newFile is null");
        }
        
        attachment.setStatus(this.getStatusFromCode(attachment.getStatusCode()));
        this.setAndsaveAttachmentBase(attachment);
    }
    
    /** {@inheritDoc} */
    public void saveAttatchment(ProtocolAttachmentPersonnel attachment) {
        if (attachment == null) {
            throw new NullPointerException("the attachment is null");
        }
        
        if (attachment.getNewFile() == null) {
            throw new NullPointerException("the newFile is null");
        }
        
        attachment.setPerson(this.getPerson(attachment.getPersonId()));
        this.setAndsaveAttachmentBase(attachment);
    }
    
    /**
     * This method sets all of the common items between attachments and persists those items.
     * Make sure before calling this method that all attachment specific properties are set.
     * <p>
     * This method will modify the passed in attachment.
     * </p>
     * @param attachment the attachment.
     */
    private void setAndsaveAttachmentBase(ProtocolAttachmentBase attachment) {
        assert attachment != null : "the attachment is null";
        assert attachment.getNewFile() != null : "the newFile is null";
        
        attachment.setType(this.getTypeFromCode(attachment.getTypeCode()));
        attachment.setFile(ProtocolAttachmentFile.createFromFormFile(attachment.getNewFile()));
        
        //bogus numbers
        attachment.setAttachmentVersionNumber(Integer.valueOf(1));
        attachment.setDocumentId(Integer.valueOf(1));
        
        this.boService.save(attachment.getFile());
        attachment.setFileId(attachment.getFile().getId());
        this.boService.save(attachment);
    }
    
    /**
     * Gets a "code" BO from a code.  This method will only work for a BO that has a property of "code" that is the
     * primary key.
     *   
     * @param <T> the BO type
     * @param type the type
     * @param code the code value
     * @return the BO
     * @throws NullPointerException if the code or type is null.
     */
    private <T extends PersistableBusinessObject> T getCodeType(final Class<T> type, final String code) {
        if (type == null) {
            throw new NullPointerException("the type is null");
        }
        
        if (code == null) {
            throw new NullPointerException("the code is null");
        }
        
        @SuppressWarnings("unchecked")
        final T bo = (T) this.boService.findByPrimaryKey(type, Collections.singletonMap("code", code));
        
        return bo;
    }
    
    /**
     * Gets a Person BO from personId.
     * 
     * @param personId the person id
     * @return the BO
     * @throws NullPointerException if the code or type is null.
     */
    private Person getPerson(String personId) {
        if (personId == null) {
            throw new NullPointerException("the personId is null");
        }
        
        return (Person) this.boService.findByPrimaryKey(Person.class, Collections.singletonMap("personId", personId));
    }
}
