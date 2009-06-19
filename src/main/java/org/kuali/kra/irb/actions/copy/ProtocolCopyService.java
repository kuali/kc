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
package org.kuali.kra.irb.actions.copy;

import org.kuali.kra.irb.ProtocolDocument;

/**
 * The Protocol Copy Service is responsible for copying protocols.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface ProtocolCopyService {
    
    /**
     * Copy a protocol document.
     * 
     * @param doc the protocol document to copy.
     * @return the new document that is saved in the database; 
     *         otherwise null if an error occurred, e.g. the user didn't have permission to copy the document
     * @throws Exception if anything really bad happens
     */
    public ProtocolDocument copyProtocol(ProtocolDocument doc) throws Exception;
   
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
    public ProtocolDocument copyProtocol(ProtocolDocument doc, String protocolNumber) throws Exception;
}
