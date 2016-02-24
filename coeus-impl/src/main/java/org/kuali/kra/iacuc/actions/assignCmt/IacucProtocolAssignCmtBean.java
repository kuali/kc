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
