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