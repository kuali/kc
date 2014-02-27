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

import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

import java.util.List;


/**
 * The ProtocolFinderDao is used to find protocols.
 */
public interface ProtocolFinderDao {

    /**
     * Find the current protocol given a protocolNumber.  The
     * current protocol is the one with the highest sequence number.
     * @param protocolNumber
     * @return the protocol or null if not found
     */
    ProtocolBase findCurrentProtocolByNumber(String protocolNumber);
    
    /**
     * 
     * This method all protocol submission belong to this protocolNumber.  also include amendment and renewal of this protocol.
     * @param protocolNumber
     * @param submissionNumber
     * @return
     */
    List<ProtocolSubmissionBase> findProtocolSubmissions(String protocolNumber, int submissionNumber);

    /**
     * 
     * This method is to find all the versioned protocols, amendments, renewals with this protocolNumber.
     * @param protocolNumber
     * @return
     */
    List<ProtocolBase> findProtocols(String protocolNumber);
}
