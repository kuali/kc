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
package org.kuali.kra.irb.actions.notification;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;

import java.util.Map;

/**
 * Renders additional fields for the Return Review notification.
 */
public class RejectReviewNotificationRenderer extends IRBNotificationRenderer {

    private static final long serialVersionUID = 7476787428506238817L;
    
    private String reason;

    /**
     * Constructs a Return Review notification renderer.
     * 
     * @param protocol
     * @param reason
     */
    public RejectReviewNotificationRenderer(Protocol protocol, String reason) {
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
