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
package org.kuali.kra.iacuc.actions.assignCmt;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.protocol.actions.ActionHelperBase;

import java.io.Serializable;

public class IacucProtocolAssignCmtBean extends  IacucProtocolActionBean implements Serializable {


    private static final long serialVersionUID = -4408101740397922792L;
    private String committeeId = "";
    private String newCommitteeId = "";

    public void init() {
        String committeeId = getAssignCmtService().getAssignedCommitteeId(getProtocol());
        if (committeeId != null) {
            this.newCommitteeId = committeeId;
            this.committeeId = committeeId;
        }
    }
    
    public IacucProtocolAssignCmtBean(ActionHelperBase actionHelper) {
        super(actionHelper);
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getNewCommitteeId() {
        return newCommitteeId;
    }

    public void setNewCommitteeId(String newCommitteeId) {
        this.newCommitteeId = newCommitteeId;
    }
   
    protected IacucProtocolAssignCmtService getAssignCmtService() {
        return KcServiceLocator.getService(IacucProtocolAssignCmtService.class);
    }
}
