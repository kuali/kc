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
package org.kuali.kra.iacuc.actions.assignagenda;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaService;


/**
 * Handles the processing of assigning a protocol to an agenda.
 */
public interface IacucProtocolAssignToAgendaService extends ProtocolAssignToAgendaService {
    
    public void removeFromAgenda(IacucProtocol protocol, IacucProtocolGenericActionBean actionBean) throws Exception;
    
}
