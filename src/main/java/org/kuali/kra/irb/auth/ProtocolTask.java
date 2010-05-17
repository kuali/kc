/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.irb.Protocol;

/**
 * A Protocol Task is a task that performs an action against a
 * Protocol.  To assist authorization, the Protocol is available.
 */
public final class ProtocolTask extends Task {
    
    private Protocol protocol;
    
    /**
     * Constructs a ProtocolTask.
     * @param taskName the name of the task
     * @param protocol the Protocol
     */
    public ProtocolTask(String taskName, Protocol protocol) {
        super(TaskGroupName.PROTOCOL, taskName);
        this.protocol = protocol;
    }
    
    public ProtocolTask(String taskName, Protocol protocol, String genericTaskName) {
        super(TaskGroupName.PROTOCOL, taskName, genericTaskName);
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
