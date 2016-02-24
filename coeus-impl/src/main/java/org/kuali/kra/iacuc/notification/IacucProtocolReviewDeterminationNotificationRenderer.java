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

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Renders additional fields for the Protocol Disapproved notification.
 */
public class IacucProtocolReviewDeterminationNotificationRenderer extends IacucProtocolNotificationRenderer {

    private Date dueDate;
    
    /**
     * Constructs a Protocol Review Type Determination notification renderer.
     * @param protocol
     * @param dueDate
     */
    public IacucProtocolReviewDeterminationNotificationRenderer(IacucProtocol protocol, Date dueDate) {
        super(protocol);
        this.dueDate = dueDate;
    }
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{DUE_DATE}", (dueDate == null ? "N/A" : new SimpleDateFormat("d'-'MMM'-'yyyy").format(dueDate)));
        return params;
    }

}
