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
package org.kuali.kra.protocol.auth;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.protocol.ProtocolBase;

public abstract class ProtocolTaskBase extends Task {
    
    private ProtocolBase protocol;
    
    
    /**
     * Constructs a ProtocolTaskBase.
     * @param taskName the name of the task
     * @param protocol the ProtocolBase
     */
    public ProtocolTaskBase(String taskGroupName, String taskName, ProtocolBase protocol) {
        super(taskGroupName, taskName);
        this.protocol = protocol;
    }
    
    public ProtocolTaskBase(String taskGroupName, String taskName, ProtocolBase protocol, String genericTaskName) {
        super(taskGroupName, taskName, genericTaskName);
        this.protocol = protocol;
    }

    
    /**
     * Get the ProtocolBase.
     * @return the ProtocolBase
     */
    public ProtocolBase getProtocol() {
        return protocol;
    }

}
