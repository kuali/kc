/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
     */
    List<GenericProtocol> getProtocols(Map<String,String> fieldValues);
    
    /**
     * 
     * This method Check if there are any pending amendmends/renewals for this protocols.
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
     * @param endDate of the date range for the 
     * @return List of the requested Protocols
     */
    List<ProtocolBase> getNotifiedProtocols(String committeeId, Date startDate, Date endDate);

}
