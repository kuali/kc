/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol;

import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.TypedAttachment;

import java.sql.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * This class is used to run ojb query directly.
 */
public interface ProtocolDao<GenericProtocol extends ProtocolBase> {
    
    /**
     * 
     * This method get protocols lookup search results.
     * @param fieldValues
     * @return
     */
    List<GenericProtocol> getProtocols(Map<String,String> fieldValues);
    
    /**
     * 
     * This method Check if there are any pending amendmends/renewals for this protocols.
     * @param protocolNumber
     * @return
     */
    boolean getProtocolSubmissionCountFromProtocol(String protocolNumber);
    
    /**
     * Gets all the attachment versions based on the passed in attachment.  This will include the pass-in attachment. 
     * @param attachment the attachment to base the query off of
     * @param type a class token used for retrieving the attachments
     * @return the list of attachments, empty list if none to return or the attachment is invalid for a lookup
     */
    <T extends ProtocolAttachmentBase & TypedAttachment> List<T> retrieveAttachmentVersions(T attachment, Class<T> type);
    
    /**
     * This method returns all Protocols that were approved by the specified committee and
     * expires in the specified date range.
     * 
     * @param committeeId of the most recent protocol submission that approved the protocol. 
     * @param startDate of the date range for the expiration date of the protocol.  The start date is 
     *        optional. If no start date is specified, the beginning of time will be used. 
     * @param endDate of the date range for the expiration date of the protocol.  The end date is 
     *        optional. If no end date is specified, the end of time will be used.
     * @return List of the requested Protocols
     */
    List<GenericProtocol> getExpiringProtocols(String committeeId, Date startDate, Date endDate);
    
    /**
     * This method returns all Protocols that were given a decision status of "Specify Minor Revision" (SMR)
     * or "Substantive Revision Required" (SRR) by the specified committee in the specified date range.
     * 
     * @param committeeId of the 
     * @param startDate
     * @param endDate of the date range for the 
     * @return List of the requested Protocols
     */
    List<ProtocolBase> getNotifiedProtocols(String committeeId, Date startDate, Date endDate);

}
