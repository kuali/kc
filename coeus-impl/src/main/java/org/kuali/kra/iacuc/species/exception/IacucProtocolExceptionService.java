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
package org.kuali.kra.iacuc.species.exception;

import org.kuali.kra.iacuc.IacucProtocol;

public interface IacucProtocolExceptionService {

    /**
     * This method adds Protocol Exception to the List of Protocol Exception.
     * @param protocol which contains list of ProtocolException.
     * @param ProtocolException object is added to ProtocolException list.
     */
    public abstract void addProtocolException(IacucProtocol protocol, IacucProtocolException protocolException);
    
    /**
     * This method is to get a new formatted protocol exception
     * @param protocol
     * @param protocolException
     * @return
     */
    public IacucProtocolException getNewProtocolException(IacucProtocol protocol, IacucProtocolException protocolException);

}
