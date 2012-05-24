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
package org.kuali.kra.protocol.auth;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.protocol.Protocol;

public abstract class ProtocolTask extends Task {
    
    private Protocol protocol;
    
    
    /**
     * Constructs a ProtocolTask.
     * @param taskName the name of the task
     * @param protocol the Protocol
     */
    public ProtocolTask(String taskGroupName, String taskName, Protocol protocol) {
        super(taskGroupName, taskName);
        this.protocol = protocol;
    }
    
    public ProtocolTask(String taskGroupName, String taskName, Protocol protocol, String genericTaskName) {
        super(taskGroupName, taskName, genericTaskName);
        this.protocol = protocol;
    }

    
    /**
     * Get the Protocol.
     * @return the Protocol
     */
    public Protocol getProtocol() {
        return protocol;
    }

}
