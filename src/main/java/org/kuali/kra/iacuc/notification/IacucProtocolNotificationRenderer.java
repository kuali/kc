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
package org.kuali.kra.iacuc.notification;

import java.util.Map;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.protocol.notification.ProtocolNotificationRenderer;

/**
 * Renders fields for the IRB and IACUC notifications.
 */
public class IacucProtocolNotificationRenderer extends ProtocolNotificationRenderer {

    private static final long serialVersionUID = 44807703047564273L;

    /**
     * Constructs a Protocol base notification renderer.
     * @param protocol
     */
    public IacucProtocolNotificationRenderer(IacucProtocol protocol) {
        super(protocol);
    }

    //TODO - This is here just in case we need it for parameters found in IACUC but not in IRB.
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#getReplacementParameters()
     */
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        
// example: params.put(ProtocolReplacementParameters.SUBMISSION_STATUS_NAME, protocol.getProtocolSubmissionStatus());
        return params;
    }

}
