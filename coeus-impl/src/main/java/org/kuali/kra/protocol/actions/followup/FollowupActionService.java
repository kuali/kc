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
package org.kuali.kra.protocol.actions.followup;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ValidProtocolActionActionBase;

import java.util.List;

public interface FollowupActionService<T extends ValidProtocolActionActionBase> {
    
    
    /**
     * Determines if the action is a follow-up action for the current state of the
     * protocol.  This is a replacement method for the one found in the ProtocolActionService
     * that relies on drools rules instead of the ValidProtocolActionActionBase maintenance artifact.
     * 
     * @param protocolActionTypeCode  The type code we are checking is a follow up action
     * @param protocol The protocol you are interested in.
     * 
     * @return 
     */
    boolean isActionOpenForFollowup(String protocolActionTypeCode, ProtocolBase protocol);
    
    List<T> getFollowupsForActionTypeAndMotionType(String protocolActionTypeCode, String committeeMotionTypeCode);
    
    List<T> getFollowupsForProtocol(ProtocolBase protocol);

}
