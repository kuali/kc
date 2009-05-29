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

import org.kuali.kra.irb.personnel.ProtocolPerson;


/**
 * Contains the methods used for Protocol Notes and Attachments.
 */
public interface ProtocolAttachmentService {

    /**
     * Gets a {@link ProtocolAttachmentType ProtocolAttachmentType} from a code.
     * 
     * @param code the code.
     * @return the {@link ProtocolAttachmentType ProtocolAttachmentType}.  If the code is not
     * found then null is returned.
     * @throws IllegalArgumentException if the code is null
     */
    ProtocolAttachmentType getTypeFromCode(String code);
    
    /**
     * Gets a Collection of {@link ProtocolAttachmentType ProtocolAttachmentType} from a group code.
     * 
     * @param code the group code.
     * @return a Collection of {@link ProtocolAttachmentType ProtocolAttachmentType}.  If no codes are
     * found then an empty Collection is returned.
     * @throws IllegalArgumentException if the code is null
     */
    Collection<ProtocolAttachmentType> getTypesForGroup(String code);
    
    /**
     * Gets a {@link ProtocolAttachmentStatus ProtocolAttachmentStatus} from a code.
     * 
     * @param code the code.
     * @return the {@link ProtocolAttachmentStatus ProtocolAttachmentStatus}.    If the code is not
     * found then null is returned.
     * @throws IllegalArgumentException if the code is null
     */
    ProtocolAttachmentStatus getStatusFromCode(String code);
      
    /**
     * Saves (persists) an {@link ProtocolAttachmentBase ProtocolAttachmentBase}.
     * This method will modify the passed in attachment setting any missing properties. It
     * also saves all BOs that the attachment contains (ex. the File).
     * 
     * @param attachment the attachment.
     * @throws IllegalArgumentException if the attachment or attachment's new file is null
     */
    void saveAttatchment(ProtocolAttachmentBase attachment);
    
    /**
     * Deletes an existing {@link ProtocolAttachmentBase ProtocolAttachmentBase}. It will also
     * delete any child BOs that should also be removed (ex. the File).
     * 
     * @param attachment the attachment.
     * @throws IllegalArgumentException if the attachment or attachment's new file is null
     */
    void deleteAttatchment(ProtocolAttachmentBase attachment);
    
    /**
     * Gets a Person BO from personId.
     * 
     * @param personId the person id
     * @return the BO
     * @throws IllegalArgumentException if the code or type is null.
     */
    ProtocolPerson getPerson(Integer personId);
}
