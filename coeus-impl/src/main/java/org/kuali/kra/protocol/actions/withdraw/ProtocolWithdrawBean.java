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
package org.kuali.kra.protocol.actions.withdraw;

import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class is really just a "form" containing the reason
 * for withdrawing a protocol.
 */
public interface ProtocolWithdrawBean extends ProtocolActionBean, Serializable {

    public void setReason(String reason);

    public String getReason();

    public Date getActionDate();
    
    public void setActionDate(Date actionDate);
    
    /**
     * 
     * This method returns the correct correspondence for this object
     * @return a WithdrawCorrespondence object
     */
    public ProtocolActionsCorrespondenceBase getCorrespondence();
}
