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
package org.kuali.kra.protocol.auth;

import org.kuali.coeus.common.framework.auth.perm.Permissionable;

/**
 * This interface defines a document class to be load able with a unit access control list.
 */
public interface UnitAclLoadable extends Permissionable {
    /**
     * Returns the unit number of the unit to which the document belongs to.
     * 
     * @return unitNumber of document
     */
    String getDocumentUnitNumber();
    
    /**
     * Returns the type code of the document (i.e. Proposal, Protocol, etc).
     * 
     * @return typeCode of document
     */
    String getDocumentRoleTypeCode();
}
