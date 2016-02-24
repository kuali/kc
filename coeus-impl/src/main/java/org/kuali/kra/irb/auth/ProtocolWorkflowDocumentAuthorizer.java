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
package org.kuali.kra.irb.auth;

import org.kuali.coeus.common.framework.auth.KcWorkflowDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.kra.irb.Protocol;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProtocolWorkflowDocumentAuthorizer extends KcWorkflowDocumentAuthorizerBase {

    @Override
    protected Permissionable getPermissionable(String documentId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("documentNumber", documentId);
        Collection<Protocol> protocols = getBusinessObjectService().findMatching(Protocol.class, values);
        if (protocols != null && !protocols.isEmpty()) {
            return protocols.iterator().next();
        } else {
            return null;
        }
    }

}
