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
package org.kuali.kra.coi.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * 
 * This class is to check if user has permission to modify coi disclosure
 */
public class ModifyCoiDisclosureAuthorizer extends CoiDisclosureAuthorizer {

    @Override
    public boolean isAuthorized(String userId, CoiDisclosureTask task) {
        // TODO : need to verify whether after 'certify' , is it still 'saved' status or not ?
        boolean hasPermission = true;
        CoiDisclosure coiDisclosure = task.getCoiDisclosure();
        if (coiDisclosure == null || coiDisclosure.getCoiDisclosureId() == null) {
            
            // We have to consider the case when we are saving the coi disclosure for the first time.
            
           // hasPermission = hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.MAINTAIN_COI_DISCLOSURE);
            hasPermission = getCoiDisclosureService().isReporter() || hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.REPORT_COI_DISCLOSURE)
                    || hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.MAINTAIN_COI_DISCLOSURE);
        } 
        else {
            /*
             * After the initial save, the disclosure can only be modified if it is editable 
             * and if the logged-in user either has the required permission or is the original reporter.
             */
            hasPermission = isDisclosureEditable(coiDisclosure) &&
                                ( (hasPermission(userId, coiDisclosure, PermissionConstants.MAINTAIN_COI_DISCLOSURE)) 
                                        || (StringUtils.equals(userId, coiDisclosure.getPersonId()) 
                                                && StringUtils.equals(coiDisclosure.getCoiDisclosureStatus().getCoiDisclosureStatusCode(), CoiDisclosureStatus.IN_PROGRESS)) );

        }
        return hasPermission;
    }
    

}
