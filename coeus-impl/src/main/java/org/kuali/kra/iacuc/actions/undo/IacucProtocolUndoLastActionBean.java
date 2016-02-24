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
package org.kuali.kra.iacuc.actions.undo;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBean;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBeanHelperBase;

public class IacucProtocolUndoLastActionBean extends IacucProtocolGenericActionBean implements UndoLastActionBean {

    private UndoLastActionBeanHelperBase helper;
    
    public IacucProtocolUndoLastActionBean(IacucActionHelper actionHelper, String errorPropertyKey) {
        super(actionHelper, errorPropertyKey);
        this.helper = new IacucProtocolUndoLastActionBeanHelper();
    }

    @Override
    public boolean canUndoLastAction() {
        return helper.canUndoLastAction(getProtocol());
    }

    @Override
    public ProtocolActionBase getLastAction() {
        return helper.getLastPerformedAction(getProtocol().getProtocolActions());
    }

}
