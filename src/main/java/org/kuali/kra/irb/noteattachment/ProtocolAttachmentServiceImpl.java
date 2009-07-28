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
import java.util.Collections;

import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;


/** Implementation of {@link ProtocolAttachmentService ProtocolNoteAndAttachmentService}. */
class ProtocolAttachmentServiceImpl implements ProtocolAttachmentService {

    private final BusinessObjectService boService;
    private final ProtocolDao protocolDao;
    
    /**
     * Constructor than sets the dependencies.
     * 
     * @param boService the {@link BusinessObjectService BusinessObjectService}
     * @throws IllegalArgumentException if the boService or protocolDao is null
     */
    public ProtocolAttachmentServiceImpl(final BusinessObjectService boService, final ProtocolDao protocolDao) {
        if (boService == null) {
            throw new IllegalArgumentException("the boService was null");
        }
        
        if (protocolDao == null) {
            throw new IllegalArgumentException("the protocolDao was null");
        }
        
        this.boService = boService;
        this.protocolDao = protocolDao;
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
    public Collection<ProtocolAttachmentType> getTypesForGroup(String code) {
        if (code == null) {
            throw new IllegalArgumentException("the code is null");
        }
        
        @SuppressWarnings("unchecked")
        final Collection<ProtocolAttachmentTypeGroup> typeGroups
            = this.boService.findMatching(ProtocolAttachmentTypeGroup.class, Collections.singletonMap("groupCode", code));
        if (typeGroups == null) {
            return new ArrayList<ProtocolAttachmentType>();
        }
        
        final Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
        for (final ProtocolAttachmentTypeGroup typeGroup : typeGroups) {
            types.add(typeGroup.getType());
        }
        
        return types;
    }
    
    /** {@inheritDoc} */
    public void saveAttatchment(ProtocolAttachmentBase attachment) {
        
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        
        this.boService.save(attachment);
    }
    
    /** {@inheritDoc} */
    public void deleteAttatchment(ProtocolAttachmentBase attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        
        this.boService.delete(attachment);
    }
    
    /** {@inheritDoc} */
    public ProtocolPerson getPerson(Integer personId) {
        if (personId == null) {
            throw new IllegalArgumentException("the personId is null");
        }
        
        return (ProtocolPerson) this.boService.findByPrimaryKey(ProtocolPerson.class, Collections.singletonMap("protocolPersonId", personId));
    }
    
    /** {@inheritDoc} */
    public <T extends ProtocolAttachmentBase> T getAttachment(Class<T> type, Long id) {
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        @SuppressWarnings("unchecked")
        final T attachment = (T) this.boService.findByPrimaryKey(type, Collections.singletonMap("id", id));
        return attachment;
    }
    
    /** {@inheritDoc} */
    public <T extends ProtocolAttachmentBase & TypedAttachment> Collection<T> getAttachmentsWithOlderFileVersions(final T attachment, final Class<T> type) {
        
        final Collection<T> olderAttachments = new ArrayList<T>();
        for (final T version : this.protocolDao.retrieveAttachmentVersions(attachment, type)) {
            if (version.getFile().getSequenceNumber().intValue() < attachment.getFile().getSequenceNumber().intValue()) {
                olderAttachments.add(version);
            }
        }
        return olderAttachments;
    }
    
    /**
     * Gets a "code" BO from a code.  This method will only work for a BO that has a property of "code" that is the
     * primary key.
     *   
     * @param <T> the BO type
     * @param type the type
     * @param code the code value
     * @return the BO
     * @throws IllegalArgumentException if the code or type is null.
     */
    private <T extends PersistableBusinessObject> T getCodeType(final Class<T> type, final String code) {
        if (type == null) {
            throw new IllegalArgumentException("the type is null");
        }
        
        if (code == null) {
            throw new IllegalArgumentException("the code is null");
        }
        
        @SuppressWarnings("unchecked")
        final T bo = (T) this.boService.findByPrimaryKey(type, Collections.singletonMap("code", code));
        
        return bo;
    }
}
