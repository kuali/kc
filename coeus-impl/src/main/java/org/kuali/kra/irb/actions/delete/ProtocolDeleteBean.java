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
package org.kuali.kra.irb.actions.delete;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;

/**
 * This class is really just a "form" containing the reason
 * for deleting a protocol/amendment/renewal.
 */
public class ProtocolDeleteBean extends ProtocolActionBean implements org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean {

    private static final long serialVersionUID = 7654109710201779704L;
    
    private String reason = "";
    
    /**
     * Constructs a ProtocolDeleteBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolDeleteBean(ActionHelper actionHelper) {
        super(actionHelper);
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
