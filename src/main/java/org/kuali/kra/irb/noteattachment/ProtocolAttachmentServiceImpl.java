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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.PersonService;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;


/** Implementation of {@link ProtocolAttachmentService ProtocolNoteAndAttachmentService}. */
class ProtocolAttachmentServiceImpl implements ProtocolAttachmentService {

    private final BusinessObjectService boService;
    private final ProtocolDao protocolDao;
    private PersonService<Person> personService;
    
    protected final Log LOG = LogFactory.getLog(getClass()); 
    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";
    
    
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
//        if (attachment instanceof ProtocolAttachmentPersonnel) {
//            int idx = 0;
//            for (ProtocolAttachmentPersonnel attachmentPersonnel : ((ProtocolAttachmentPersonnel) attachment).getPerson().getAttachmentPersonnels()) {
//                if (attachmentPersonnel.getId().equals(attachment.getId())) {
//                    break;
//                }
//                idx++;
//            }
//            ((ProtocolAttachmentPersonnel) attachment).getPerson().getAttachmentPersonnels().remove(idx);
//        }
        
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
    
    /**
     * 
     * @see org.kuali.kra.irb.noteattachment.ProtocolAttachmentService#isNewAttachmentVersion(org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol)
     */
    @SuppressWarnings("unchecked")
    public boolean isNewAttachmentVersion(ProtocolAttachmentProtocol attachment) {
        Map keyMap = new HashMap();
        // the initial version of amendment & renewal need to do this
        if ((attachment.getProtocol().isAmendment() || attachment.getProtocol().isRenewal()) && attachment.getProtocol().getSequenceNumber() == 0) {
            Protocol protocol = getActiveProtocol(attachment.getProtocol().getProtocolNumber().substring(0, 
                    attachment.getProtocol().getProtocolNumber().indexOf(attachment.getProtocol().isAmendment() ? "A" : "R")));            
            keyMap.put("protocolNumber", protocol.getProtocolNumber());
            keyMap.put("sequenceNumber", protocol.getSequenceNumber());
        } else {
           keyMap.put("protocolNumber", attachment.getProtocolNumber());
           keyMap.put("sequenceNumber", attachment.getSequenceNumber() - 1);
        }
        keyMap.put("attachmentVersion", attachment.getAttachmentVersion());
        keyMap.put("documentId", attachment.getDocumentId());
   
        return this.boService.findMatching(ProtocolAttachmentProtocol.class, keyMap).isEmpty();
    }

    

    @Override
    public boolean isAttachmentActive(ProtocolAttachmentProtocol attachment) {
        boolean retValue;
        // first get the active version of the protocol with the number given in the attachment
        String protocolNumber = attachment.getProtocol().getProtocolNumber();
        if ( (attachment.getProtocol().isAmendment()) || (attachment.getProtocol().isRenewal()) ) {
            protocolNumber = attachment.getProtocol().getAmendedProtocolNumber(); 
        }
        Protocol activeProtocol = getActiveProtocol(protocolNumber);
        // now check if the current attachment is in the list of the active, 'non-deleted' attachments for this protocol
        // note: see equals methods for protocol attachment for the appropriate semantics for "contains" below
        if(activeProtocol.getActiveAttachmentProtocolsNoDelete().contains(attachment)) {
            retValue = true;
        }
        else {
            retValue = false;
        }
        return retValue;
    }
    
    /*
     * This method is to get the current protocol.  The protocol with the highest sequence number.
     */
    @SuppressWarnings("unchecked")
    protected Protocol getActiveProtocol(String protocolNumber) {
        Map keyMap = new HashMap();
        keyMap.put("protocolNumber", protocolNumber);
        List<Protocol> protocols = (List <Protocol>)this.boService.findMatchingOrderBy(Protocol.class, keyMap, "sequenceNumber", false);
        return protocols.get(0);
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
    protected <T extends PersistableBusinessObject> T getCodeType(final Class<T> type, final String code) {
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
    
    /**
     * 
     * @see org.kuali.kra.irb.noteattachment.ProtocolAttachmentService#isSharedFile(org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel)
     */
    @SuppressWarnings("unchecked")
    public boolean isSharedFile(ProtocolAttachmentPersonnel attachment) {
        Map keyMap = new HashMap();
        keyMap.put("fileId", attachment.getFileId());   
        return this.boService.findMatching(ProtocolAttachmentPersonnel.class, keyMap).size() > 1;
    }
    
    /**
     * @see org.kuali.kra.irb.noteattachment.ProtocolAttachmentService#setProtocolAttachmentPersonnelUpdateUsersName(java.util.List)
     */
    public void setProtocolAttachmentUpdateUsersName(List<? extends ProtocolAttachmentBase> protocolAttachmentBases) {
        
        for (ProtocolAttachmentBase pab : protocolAttachmentBases) {
            if (LOG.isDebugEnabled()) { 
                LOG.debug(String.format("Looking up person for update user %s.", pab.getUpdateUser()));
            }
            Person person = personService.getPersonByPrincipalName(pab.getUpdateUser());
            pab.setUpdateUserFullName(person==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, pab.getUpdateUser()):person.getName());
        }
    }
 
    /**
     * Sets the personService attribute value.
     * @param personService The personService to set.
     */
    public void setPersonService(PersonService<Person> personService) {
        this.personService = personService;
    }


}
