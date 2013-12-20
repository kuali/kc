/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.auth;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.auth.ProtocolAuthorizerBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;

/**
 * A Protocol Authorizer determines if a user can perform
 * a given task on a protocol.  
 */
public abstract class ProtocolAuthorizer extends ProtocolAuthorizerBase {
  
    public final boolean isAuthorized(String userId, ProtocolTaskBase task) {
        return isAuthorized(userId, (ProtocolTask) task);
    }
    
    
    /**
     * Is the user authorized to execute the given protocol task?
     * @param username the user's unique username
     * @param task the protocol task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, ProtocolTask task);
        
    protected final boolean hasCommitteeId(Protocol protocol) {
        return protocol.getProtocolSubmission().getCommitteeId() != null;
    }
}
