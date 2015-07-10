/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.notification;

import org.apache.commons.lang3.StringUtils;
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
        params.put(ProtocolReplacementParameters.REASON, StringUtils.isNotBlank(reason) ? reason : CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(NO_REASON_GIVEN));
        return params;
    }
}
