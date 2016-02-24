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

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * ProtocolBase Withdraw Service.
 */
public interface ProtocolWithdrawService {

    /**
     * Perform the task of withdrawing a protocol. A new protocol document will be created
     * so that it can be re-submitted into workflow at a later time.
     * @param protocol the protocol
     * @param withdrawBean the required data for performing a withdrawal
     * @return new protocol document 
     * @throws Exception 
     */
    public ProtocolDocumentBase withdraw(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws Exception;

    
    /**
     * Perform the task of administratively withdrawing a protocol. A new protocol document will be created
     * so that it can be re-submitted into workflow at a later time.
     * @param protocol the protocol
     * @param withdrawBean the required data for performing a withdrawal
     * @return new protocol document 
     * @throws Exception 
     */
    public ProtocolDocumentBase administrativelyWithdraw(ProtocolBase protocol, ProtocolAdministrativelyWithdrawBean administrativelyWithdrawBean) throws Exception;
    
    /**
     * Perform the task of administratively marking a protocol as 'incomplete'. A new protocol document will be created
     * so that it can be re-submitted into workflow at a later time.
     * @param protocol the protocol
     * @param withdrawBean the required data for performing a withdrawal
     * @return new protocol document 
     * @throws Exception 
     */
    public ProtocolDocumentBase administrativelyMarkIncomplete(ProtocolBase protocol, ProtocolAdministrativelyIncompleteBean markIncompleteBean) throws Exception;
}
