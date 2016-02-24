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
import org.kuali.kra.protocol.notification.ProtocolReplacementParameters;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Map;

/**
 * Renders additional fields for the Notify IRB notification.
 */
public class ProtocolWithdrawnNotificationRenderer extends IRBNotificationRenderer {

    private static final long serialVersionUID = -6321174692715820352L;

    protected String reason;
    protected Date date;

    /**
     * Constructs a Notify IRB notification renderer.
     * @param protocol
     * @param reason filled in by user for the withdrawal
     * @param date is the date the action happened
     */
    public ProtocolWithdrawnNotificationRenderer(final Protocol protocol, final String reason, final Date date) {
        super(protocol);
        
        this.date = date;
        this.reason = reason;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put(ProtocolReplacementParameters.DATE, (new SimpleDateFormat("d'-'MMM'-'yyyy")).format(getDate()));
        params.put(ProtocolReplacementParameters.REASON, getReason());
        return params;
    }

}
