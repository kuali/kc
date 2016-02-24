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
package org.kuali.kra.irb.actions;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.actions.ActionHelperBase;

/**
 * Defines the superclass of all Protocol action beans.
 */
public abstract class ProtocolActionBean implements org.kuali.kra.protocol.actions.ProtocolActionBean {
    
    private ActionHelper actionHelper;
    
    /**
     * Constructs a ProtocolActionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolActionBean(ActionHelper actionHelper) {
        this.setActionHelper(actionHelper);
    }
    
    public ProtocolActionBean() {
    }

    public void setActionHelper(ActionHelperBase actionHelper) {
        this.actionHelper = (ActionHelper) actionHelper;
    }

    public ActionHelper getActionHelper() {
        return actionHelper;
    }
    
    public Protocol getProtocol() {
        return (Protocol) actionHelper.getProtocol();
    }

}
