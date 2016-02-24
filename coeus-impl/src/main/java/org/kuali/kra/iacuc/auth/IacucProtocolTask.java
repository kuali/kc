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
package org.kuali.kra.iacuc.auth;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;

public final class IacucProtocolTask  extends ProtocolTaskBase {
    
    public static final String CREATE_PROPOSAL_FOR_IACUC_PROTOCOL = "createProposalForIacucProtocol";
    
    /**
     * Constructs a ProtocolTaskBase.
     * @param taskName the name of the task
     * @param protocol the IacucProtocol
     */
    public IacucProtocolTask(String taskName, IacucProtocol protocol) {
        super(TaskGroupName.IACUC_PROTOCOL, taskName, protocol);
    }
    
    public IacucProtocolTask(String taskName, IacucProtocol protocol, String genericTaskName) {
        super(TaskGroupName.IACUC_PROTOCOL, taskName, protocol, genericTaskName);
    }

    public IacucProtocol getProtocol() {
        return (IacucProtocol)super.getProtocol();
    }

}
