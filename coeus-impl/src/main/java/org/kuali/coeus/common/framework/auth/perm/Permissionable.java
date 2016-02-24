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
package org.kuali.coeus.common.framework.auth.perm;

import java.util.List;
import java.util.Map;

public interface Permissionable {
    
    /**
     * 
     * This method returns the appropriate document number for implementing documents
     * For award it would be awardNumber and for PDD it would be proposal Number.
     */
    String getDocumentNumberForPermission();
    
    /**
     * 
     * This method returns unique key for implementing document.
     */
    String getDocumentKey();
    
    /**
     * 
     * This method gets all the role names for particular document.
     */
    List<String> getRoleNames();
    
    String getNamespace();
    
    String getLeadUnitNumber();
    
    String getDocumentRoleTypeCode();
    
    /**
     * Allows a permissionable to set additional qualified role attributes that may be needed by 
     * kim services to resolve the role members.
     */
    void populateAdditionalQualifiedRoleAttributes( Map<String, String> qualifiedRoleAttributes );
    
    
    
}
