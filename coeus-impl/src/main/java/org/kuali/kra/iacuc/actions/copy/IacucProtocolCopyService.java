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
package org.kuali.kra.iacuc.actions.copy;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyService;

public interface IacucProtocolCopyService extends ProtocolCopyService<IacucProtocolDocument>{

    /**
     * This method is to copy source threers data to destination protocol
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolThreers(IacucProtocol srcProtocol, IacucProtocol destProtocol);

    /**
     * This method is to copy source exceptions to destination protocol
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolExceptions(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    
}
