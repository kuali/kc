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
package org.kuali.kra.iacuc.actions.withdraw;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyIncompleteBean;

public class IacucProtocolAdministrativelyIncompleteBean extends IacucProtocolWithdrawBean implements ProtocolAdministrativelyIncompleteBean {


    private static final long serialVersionUID = 8495329655747136375L;

    public IacucProtocolAdministrativelyIncompleteBean(IacucActionHelper actionHelper) {
        super(actionHelper);
    }
    
    /**
     * 
     * This method returns the correct correspondence for this object
     * 
     */
    public IacucProtocolActionsCorrespondence getCorrespondence() {
        IacucProtocolActionsCorrespondence correspondence = new IacucProtocolActionsCorrespondence(IacucProtocolActionType.ADMINISTRATIVELY_INCOMPLETE);
        return correspondence;
    }
    

}
