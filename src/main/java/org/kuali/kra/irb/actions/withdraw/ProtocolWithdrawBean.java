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
package org.kuali.kra.irb.actions.withdraw;

import java.io.Serializable;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;

/**
 * This class is really just a "form" containing the reason
 * for withdrawing a protocol.
 */
public class ProtocolWithdrawBean extends ProtocolActionBean implements Serializable {
    
    private static final long serialVersionUID = -3244694733749584969L;
    
    private String reason = "";
    
    /**
     * Constructs a ProtocolWithdrawBean.java.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolWithdrawBean(ActionHelper actionHelper) {
        super(actionHelper);
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
    
    /**
     * 
     * This method returns the correct correspondence for this object
     * @return a WithdrawCorrespondence object
     */
    public WithdrawCorrespondence getCorrespondence() {
        WithdrawCorrespondence correspondence = new WithdrawCorrespondence();
        return correspondence;
    }
}
