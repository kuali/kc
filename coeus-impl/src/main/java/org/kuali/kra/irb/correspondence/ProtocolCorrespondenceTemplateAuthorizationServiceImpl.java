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
package org.kuali.kra.irb.correspondence;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class provides a call to validate whether user has certain permission.
 */
public class ProtocolCorrespondenceTemplateAuthorizationServiceImpl implements ProtocolCorrespondenceTemplateAuthorizationService {
    
    private UnitAuthorizationService unitAuthorizationService;
    private KcPersonService kcPersonService;
    
    @Override
    public boolean hasPermission(String permissionName){
        KcPerson person = kcPersonService.getKcPersonByUserName(getUserName());       
        return unitAuthorizationService.hasPermission(person.getPersonId(), "KC-PROTOCOL", permissionName);

    }
    protected String getUserName() {
        return GlobalVariables.getUserSession().getPerson().getPrincipalName();
    }
    
    /**
     * 
     * This method inject UnitAuthorizationService
     * @param unitAuthorizationService
     */
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    /**
     * 
     * This method inject KcPersonService
     * @param kcPersonService
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
