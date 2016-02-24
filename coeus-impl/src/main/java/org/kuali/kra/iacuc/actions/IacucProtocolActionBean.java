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
package org.kuali.kra.iacuc.actions;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.ProtocolActionBean;

/**
 * Defines the superclass of all ProtocolBase action beans.
 */
public abstract class IacucProtocolActionBean implements ProtocolActionBean {
    
    private ActionHelperBase actionHelper;
    
    /**
     * Constructs a ProtocolActionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolActionBean(ActionHelperBase actionHelper) {
        this.setActionHelper(actionHelper);
    }
    
    public IacucProtocolActionBean() {
    }

    public void setActionHelper(ActionHelperBase actionHelper) {
        this.actionHelper = actionHelper;
    }

    public ActionHelperBase getActionHelper() {
        return actionHelper;
    }
    
    public ProtocolBase getProtocol() {
        return actionHelper.getProtocol();
    }

}
