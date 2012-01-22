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
package org.kuali.kra.irb.actions.followup;

import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ValidProtocolActionAction;

public interface FollowupActionService {
    
    
    /**
     * Determines if the action is a follow-up action for the current state of the
     * protocol.  This is a replacement method for the one found in the ProtocolActionService
     * that relies on drools rules instead of the ValidProtocolActionAction maintenance artifact.
     * 
     * @param protocolActionTypeCode  The type code we are checking is a follow up action
     * @param protocol The protocol you are interested in.
     * 
     * @return 
     */
    boolean isActionOpenForFollowup(String protocolActionTypeCode, Protocol protocol);
    
    List<ValidProtocolActionAction> getFollowupsForActionTypeAndMotionType(String protocolActionTypeCode, String committeeMotionTypeCode);
    
    List<ValidProtocolActionAction> getFollowupsForProtocol(Protocol protocol);

}
