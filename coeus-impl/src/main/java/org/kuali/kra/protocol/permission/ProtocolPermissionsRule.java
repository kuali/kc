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
package org.kuali.kra.protocol.permission;

import org.kuali.coeus.common.permissions.impl.rules.PermissionsRuleBase;
import org.kuali.kra.infrastructure.RoleConstants;

/**
 * Business Rule to determine the legality of modifying the access
 * to a ProtocolBase Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolPermissionsRule extends PermissionsRuleBase {
    
    @Override
    protected String getAdministratorRoleName() {
        return RoleConstants.PROTOCOL_AGGREGATOR;
    }   
}
