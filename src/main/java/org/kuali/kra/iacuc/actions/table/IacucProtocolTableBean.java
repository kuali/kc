/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.table;

import java.io.Serializable;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;

/**
 * This class is really just a "form" containing the reason
 * for tabling an IACUC protocol.
 */
public class IacucProtocolTableBean extends IacucProtocolActionBean implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6076002106217543225L;

    /**
     * Constructs a ProtocolWithdrawBean.java.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolTableBean(ActionHelper actionHelper) {
        super((IacucActionHelper) actionHelper);
    }

}
