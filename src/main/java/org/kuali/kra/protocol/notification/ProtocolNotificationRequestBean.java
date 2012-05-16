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
package org.kuali.kra.protocol.notification;

import java.io.Serializable;

import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;

public interface ProtocolNotificationRequestBean extends Serializable {
    
    public Protocol getProtocol();

    public void setProtocol(Protocol protocol);

    public ProtocolOnlineReview getProtocolOnlineReview();

    public void setProtocolOnlineReview(ProtocolOnlineReview protocolOnlineReview);

    public String getActionType();

    public void setActionType(String actionType);

    public String getDescription();

    public void setDescription(String description);

    public String getDocNumber();

    public void setDocNumber(String docNumber);

    public String getOlrEvent();

    public void setOlrEvent(String olrEvent);

    public String getCommitteeName();

    public void setCommitteeName(String committeeName);
        
}
