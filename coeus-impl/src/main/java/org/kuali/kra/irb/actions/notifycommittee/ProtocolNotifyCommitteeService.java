/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.actions.notifycommittee;

import org.kuali.kra.irb.Protocol;
import org.kuali.rice.kew.api.exception.WorkflowException;

/**
 * Protocol Notify Committee Service.
 */
public interface ProtocolNotifyCommitteeService {

    /**
     * Submit a notification to the IRB committee
     * The corresponding submission and protocol action
     * entries will be added to the database.
     * @param protocol the protocol
     * @param notifyCommitteeBean the notification data
     */
    public void submitCommitteeNotification(Protocol protocol, ProtocolNotifyCommitteeBean notifyCommitteeBean) throws WorkflowException ;
}
