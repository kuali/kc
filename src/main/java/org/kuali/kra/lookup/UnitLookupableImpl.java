/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.lookup;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kns.lookup.KualiLookupableImpl;
import org.kuali.rice.krad.util.GlobalVariables;

public class UnitLookupableImpl extends KualiLookupableImpl {

    private KcPersonService kcPersonService;
    private UnitAuthorizationService unitAuthorizationService;
    
    @Override
    public String getCreateNewUrl() {
        String url = "";
        String personId = getKcPersonService().getKcPersonByPersonId(GlobalVariables.getUserSession().getPerson().getPrincipalId()).getPersonId();
        if (getUnitAuthorizationService().hasPermission(personId, "KC-UNT", PermissionConstants.ADD_UNIT)) {
            url =  super.getCreateNewUrl();
        }
        return url;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        return unitAuthorizationService;
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

}