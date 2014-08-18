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
package org.kuali.kra.protocol.actions.approve;

import org.kuali.kra.protocol.ProtocolBase;

/**
 * 
 * This class handles the persistence of an approval action to a protocol.
 */
public interface ProtocolApproveService {
    
    /**
     * Approves a full submission to a ProtocolBase.
     * @param protocol the current ProtocolBase
     * @param actionBean the bean that contains the comments and dates
     * @throws Exception
     */
    void grantFullApproval(ProtocolBase protocol, ProtocolApproveBean actionBean) throws Exception;

    /**
     * Approves a response submission to a ProtocolBase.
     * @param protocol the current ProtocolBase
     * @param actionBean the bean that contains the comments and dates
     * @throws Exception
     */
    void grantResponseApproval(ProtocolBase protocol, ProtocolApproveBean actionBean) throws Exception;
    
    
    /**
     * Administratively approves a ProtocolBase.
     * @param protocol the current ProtocolBase
     * @param actionBean the bean that contains the comments and dates
     * @throws Exception
     */
    public void grantAdminApproval(ProtocolBase protocol, ProtocolApproveBean actionBean) throws Exception;

}
