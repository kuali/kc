/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.auth;

import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;

/**
 * A Protocol Task is a task that performs an action against a
 * Protocol.  To assist authorization, the Protocol is available.
 */
public final class ProtocolTask extends ProtocolTaskBase {
  public static final String CREATE_PROPOSAL_FOR_IRB_PROTOCOL = "createProposalForIrbProtocol";

    /**
     * Constructs a ProtocolTaskBase.
     * @param taskName the name of the task
     * @param protocol the Protocol
     */
    public ProtocolTask(String taskName, Protocol protocol) {
        super(TaskGroupName.PROTOCOL, taskName, protocol);
    }
    
    public ProtocolTask(String taskName, Protocol protocol, String genericTaskName) {
        super(TaskGroupName.PROTOCOL, taskName, protocol, genericTaskName);
    }

    public Protocol getProtocol() {
        return (Protocol)super.getProtocol();
    }
}
