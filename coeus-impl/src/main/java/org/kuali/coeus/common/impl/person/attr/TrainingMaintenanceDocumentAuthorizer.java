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
package org.kuali.coeus.common.impl.person.attr;

import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashMap;
import java.util.Map;

public class TrainingMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {
    public static final String PERMISSION_MAINTAIN_TRAINING = "Maintain Training";
    public static final String KC_SYS = "KC-SYS";
    
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        Map<String, String> permissionDetails = new HashMap<String, String>();
        permissionDetails.put(KimConstants.AttributeConstants.DOCUMENT_TYPE_NAME, documentTypeName);
        
        boolean retVal =  getPermissionService().isAuthorized(user.getPrincipalId(), KC_SYS, PERMISSION_MAINTAIN_TRAINING, permissionDetails);
        return retVal;
    }
    
    @Override
    public boolean canMaintain(Object dataObject, Person user) {    
        Map<String, String> permissionDetails = new HashMap<String, String>(2);
        permissionDetails.put(KimConstants.AttributeConstants.DOCUMENT_TYPE_NAME,
                getDocumentDictionaryService().getMaintenanceDocumentTypeName(
                        dataObject.getClass()));
        permissionDetails.put(KRADConstants.MAINTENANCE_ACTN, KC_SYS);
        return !permissionExistsByTemplate(KC_SYS,
                KimConstants.PermissionTemplateNames.INITIATE_DOCUMENT,
                permissionDetails)
                || isAuthorizedByTemplate(
                        dataObject,
                        KC_SYS,
                        KimConstants.PermissionTemplateNames.INITIATE_DOCUMENT,
                        user.getPrincipalId(), permissionDetails, null);
    }
}
