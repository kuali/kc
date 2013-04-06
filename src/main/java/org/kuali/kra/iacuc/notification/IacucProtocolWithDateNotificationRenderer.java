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
package org.kuali.kra.iacuc.notification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.drools.core.util.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.protocol.notification.ProtocolReplacementParameters;
import org.kuali.rice.krad.service.KRADServiceLocator;

/**
 * Renders additional fields for the Protocol Expired notification.
 */
public class IacucProtocolWithDateNotificationRenderer extends IacucProtocolNotificationRenderer {

    private static final long serialVersionUID = 4359032348861900659L;
    
    private Date genericDate;
    private static final String NO_DATE_GIVEN="message.none.given";
    
    /**
     * Constructs a Protocol Notification renderer which specifies a date.
     * @param protocol
     * @param expirationDate
     */
    public IacucProtocolWithDateNotificationRenderer(IacucProtocol protocol, Date genericDate) {
        super(protocol);
        this.genericDate = genericDate;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put(ProtocolReplacementParameters.DATE, genericDate != null ? new SimpleDateFormat("d'-'MMM'-'yyyy").format(genericDate)
                                                                           : KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(NO_DATE_GIVEN));
        return params;
    }
}