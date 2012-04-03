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
package org.kuali.kra.iacuc.auth;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.TaskGroupName;

public final class IacucProtocolTask  extends Task {
    
    private IacucProtocol protocol;
    
    /**
     * Constructs a ProtocolTask.
     * @param taskName the name of the task
     * @param protocol the IacucProtocol
     */
    public IacucProtocolTask(String taskName, IacucProtocol protocol) {
        super(TaskGroupName.IACUC_PROTOCOL, taskName);
        this.protocol = protocol;
    }
    
    public IacucProtocolTask(String taskName, IacucProtocol protocol, String genericTaskName) {
        super(TaskGroupName.IACUC_PROTOCOL, taskName, genericTaskName);
        this.protocol = protocol;
    }

    /**
     * Get the Protocol.
     * @return the Protocol
     */
    public IacucProtocol getProtocol() {
        return protocol;
    }

}
