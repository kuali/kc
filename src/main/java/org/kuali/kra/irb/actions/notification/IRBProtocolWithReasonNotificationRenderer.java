/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.irb.actions.notification;

import java.util.Map;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean;

/**
 * Renders additional fields for the Protocol Disapproved notification.
 */
public class IRBProtocolWithReasonNotificationRenderer extends IRBNotificationRenderer {

    private static final long serialVersionUID = -2712991120331640207L;

    private String reason;
    
    /**
     * Constructs a notification renderer that includes a reason.
     * @param protocol
     * @param reason
     */
    public IRBProtocolWithReasonNotificationRenderer(Protocol protocol, ProtocolDeleteBean protocolDeleteBean) {
        super(protocol);
        reason = protocolDeleteBean.getReason();
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{REASON}", reason == null ? "N/A" : reason);
        return params;
    }

}