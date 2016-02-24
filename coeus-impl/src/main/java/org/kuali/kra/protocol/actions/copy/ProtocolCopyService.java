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
package org.kuali.kra.protocol.actions.copy;

import org.kuali.kra.protocol.ProtocolDocumentBase;


/**
 * The ProtocolBase Copy Service is responsible for copying protocols.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface ProtocolCopyService<GenericProtocolDocument extends ProtocolDocumentBase> {
    
    /**
     * Copy a protocol document.
     * 
     * @param doc the protocol document to copy.
     * @return the new document that is saved in the database; 
     *         otherwise null if an error occurred, e.g. the user didn't have permission to copy the document
     * @throws Exception if anything really bad happens
     */
    public GenericProtocolDocument copyProtocol(GenericProtocolDocument doc) throws Exception;
   
    /**
     * Copy a protocol document with a given protocol number.  This is
     * used when protocols are copied for amendments and renewals.  Instead
     * of creating a new protocol number, the original one is appended to.
     * 
     * @param doc the protocol document to copy.
     * @param protocolNumber the protocol number to assign to the protocol
     * @return the new document that is saved in the database; 
     *         otherwise null if an error occurred, e.g. the user didn't have permission to copy the document
     * @throws Exception if anything really bad happens
     */
    public ProtocolDocumentBase copyProtocol(GenericProtocolDocument doc, String protocolNumber, boolean isAmendmentRenewal) throws Exception;
}
