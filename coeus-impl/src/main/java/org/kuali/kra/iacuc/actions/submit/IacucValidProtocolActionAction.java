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
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.protocol.actions.submit.ValidProtocolActionActionBase;

/**
 * This class represents the action follow up mapping as it exists in coeus.
 * A follow up action is mapped to an action via this bo.  When the user takes the action
 * protocolActionTypeCode they will be prompted to complete each of the actions associated with it 
 * via this object.  The properties protocolActionTypeCode, actionNumber, and followUpActionCode form
 * a unique key on the underlying table.
 * 
 */
public class IacucValidProtocolActionAction extends ValidProtocolActionActionBase {


    private static final long serialVersionUID = 920783769885103992L;

}
