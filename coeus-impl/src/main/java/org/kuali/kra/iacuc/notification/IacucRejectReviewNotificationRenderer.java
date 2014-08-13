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

import java.util.Map;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;


/**
 * Renders additional fields for the IACUC Reject Review notification.
 */
public class IacucRejectReviewNotificationRenderer extends IacucProtocolNotificationRenderer {

    

    private static final long serialVersionUID = 8996340436089157540L;
    
    private String reason;

    /**
     * Constructs an IACUC Reject Review notification renderer.
     * 
     * @param protocol
     * @param reason
     */
    public IacucRejectReviewNotificationRenderer(IacucProtocol protocol, String reason) {
        super(protocol);
        
        this.reason = reason;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{REASON}", reason);
        return params;
    }
}