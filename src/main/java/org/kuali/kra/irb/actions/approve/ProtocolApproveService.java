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
package org.kuali.kra.irb.actions.approve;

import org.kuali.kra.irb.Protocol;

/**
 * 
 * This class handles the persistence of an approval action to a protocol.
 */
public interface ProtocolApproveService {
    
    /**
     *     
     * This method adds a new protocol action to the protocol which is an approval.
     * @param protocol The protocol to be acted upon
     * @param actionBean the bean that contains UI information
     * @throws Exception if a problem occurs while persisting the data
     */
    void approve(Protocol protocol, ProtocolApproveBean actionBean) throws Exception;

}