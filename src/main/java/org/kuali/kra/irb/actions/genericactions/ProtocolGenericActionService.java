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
package org.kuali.kra.irb.actions.genericactions;

import org.kuali.kra.irb.Protocol;

/**
 * 
 * This interface defines the functions needed for the generic action service implementation.
 */
public interface ProtocolGenericActionService {
    
    /**
     * Reopen a protocol for enrollment.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void reopen(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;
    
    /**
     * Close enrollment for a protocol.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void closeEnrollment(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;
    
    /**
     * Suspend the protocol by the PI or by the IRB Administrator.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void suspend(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;
    
    /**
     * Suspend the protocol by DSMB.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void suspendByDsmb(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;
    
    /**
     * Close a protocol.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void close(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;
    
    /**
     * Expire a protocol.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void expire(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;
    
    /**
     * Terminate a protocol.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void terminate(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;
    
    /**
     * Permit Data Analysis.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void permitDataAnalysis(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;

    /**
     * Defer a protocol.
     * @param protocol Protocol object
     * @param actionBean ProtocolGenericActionBean object
     * @throws Exception if there was a general problem performing the action
     */
    void defer(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception;

}
