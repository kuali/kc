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
     * @throws NullPointerException if the code is null
     */
    ProtocolAttachmentType getTypeFromCode(String code);
    
    /**
     * Gets a {@link ProtocolAttachmentStatus ProtocolAttachmentStatus} from a code.
     * 
     * @param code the code.
     * @return the {@link ProtocolAttachmentStatus ProtocolAttachmentStatus}.    If the code is not
     * found then null is returned.
     * @throws NullPointerException if the code is null
     */
    ProtocolAttachmentStatus getStatusFromCode(String code);
    
    /**
     * Saves (persists) an {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
     * This method will modify the passed in attachment setting any missing properties. It
     * also saves all BOs that the attachment contains (ex. the File).
     * 
     * @param attachment the attachment.
     * @throws NullPointerException if the attachment or attachment's new file is null
     */
    void saveAttatchment(ProtocolAttachmentProtocol attachment);
    
    /**
     * Saves (persists) an {@link ProtocolAttachmentPersonnel ProtocolAttachmentPersonnel}.
     * This method will modify the passed in attachment setting any missing properties. It
     * also saves all BOs that the attachment contains (ex. the File).
     * 
     * @param attachment the attachment.
     * @throws NullPointerException if the attachment or attachment's new file is null
     */
    void saveAttatchment(ProtocolAttachmentPersonnel attachment);
}
