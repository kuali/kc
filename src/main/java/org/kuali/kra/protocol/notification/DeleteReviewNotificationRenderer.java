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

import java.util.Map;

import org.kuali.kra.protocol.Protocol;

/**
 * Renders additional fields for the Delete Review notification.
 */
public class DeleteReviewNotificationRenderer extends ProtocolNotificationRenderer {

    private static final long serialVersionUID = 9136740657323854396L;

    private String reason;

    /**
     * Constructs a Delete Review notification renderer.
     * 
     * @param protocol
     * @param reason
     */
    public DeleteReviewNotificationRenderer(Protocol protocol, String reason) {
        super(protocol);
        
        this.reason = reason;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{REASON}", reason);
        return params;
    }
}