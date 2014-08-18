/*
 * Copyright 2005-2014 The Kuali Foundation
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
