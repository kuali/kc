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

import org.drools.core.util.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.protocol.notification.ProtocolReplacementParameters;
import org.kuali.rice.core.api.CoreApiServiceLocator;

import java.util.Map;

/**
 * Renders fields for the IRB and IACUC notifications.
 */
public class IacucProtocolRequestActionNotificationRenderer extends IacucProtocolNotificationRenderer {

    private static final long serialVersionUID = 1058103556564286014L;

    private String reason;

    private static final String NO_REASON_GIVEN="message.none.given";
    
    public IacucProtocolRequestActionNotificationRenderer(IacucProtocol protocol, String reason) {
        super(protocol);
        this.reason = reason;
    }

    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put(ProtocolReplacementParameters.REASON, !StringUtils.isEmpty(reason) ? reason : CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(NO_REASON_GIVEN));
        return params;
    }
}
