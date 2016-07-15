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
package org.kuali.kra.protocol.actions.approve;

import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * 
 * This class handles the persistence of an approval action to a protocol.
 */
public interface ProtocolApproveService {
    
    /**
     * Approves a full submission to a ProtocolBase.
     * @param protocolDocument the current ProtocolBase
     * @param actionBean the bean that contains the comments and dates
     * @throws Exception
     */
    void grantFullApproval(ProtocolDocumentBase protocolDocument, ProtocolApproveBean actionBean) throws Exception;

    /**
     * Approves a response submission to a ProtocolBase.
     * @param protocolDocument the current ProtocolBase
     * @param actionBean the bean that contains the comments and dates
     * @throws Exception
     */
    void grantResponseApproval(ProtocolDocumentBase protocolDocument, ProtocolApproveBean actionBean) throws Exception;
    
    
    /**
     * Administratively approves a ProtocolBase.
     * @param protocolDocument the current ProtocolBase
     * @param actionBean the bean that contains the comments and dates
     * @throws Exception
     */
    public void grantAdminApproval(ProtocolDocumentBase protocolDocument, ProtocolApproveBean actionBean) throws Exception;

}
