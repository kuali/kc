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
package org.kuali.kra.iacuc.notification;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean;

import java.util.Map;

/**
 * Renders additional fields for the Protocol Disapproved notification.
 */
public class IacucProtocolWithReasonNotificationRenderer extends IacucProtocolNotificationRenderer {

    private static final long serialVersionUID = 1859318194507340344L;
    private String reason;
    
    /**
     * Constructs a Protocol Disapproved notification renderer.
     * @param protocol
     * @param actionComments
     */
    public IacucProtocolWithReasonNotificationRenderer(IacucProtocol protocol, ProtocolDeleteBean protocolDeleteBean) {
        super(protocol);
        reason = protocolDeleteBean.getReason();
    }
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{REASON}", reason == null ? "N/A" : reason);
        return params;
    }

}