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
package org.kuali.kra.irb;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.irb.noteattachment.ProtocolAttachmentBase;


/**
 * 
 * This class is used to run ojb query directly.
 */
public interface ProtocolDao {
    
    /**
     * 
     * This method get protocols lookup search results.
     * @param fieldValues
     * @return
     */
    List<Protocol> getProtocols(Map<String,String> fieldValues);
    
    Integer getProtocolSubmissionCountFromProtocol(String protocolNumber);
    
    /**
     * Gets all attachments (of a type) for a protocol not matching the passed attachment ids.
     * @param <T> the attachment type
     * @param type the class token
     * @param protocolId the protocol id
     * @param attachmentIds the attachment ids
     * @return the attachments
     * @throws IllegalArgumentException if any arguments are null
     */
    <T extends ProtocolAttachmentBase> Collection<T> getAttachmentsNotMatchingIds(Class<T> type, Long protocolId, Long... attachmentIds);
}
