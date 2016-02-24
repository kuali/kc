/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
