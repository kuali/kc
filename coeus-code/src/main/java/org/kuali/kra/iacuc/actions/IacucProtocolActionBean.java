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
