/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.auth.UnitAuthorizationService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.lookup.KualiLookupableImpl;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("unitLookupable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UnitLookupableImpl extends KualiLookupableImpl {

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("unitAuthorizationService")
    private UnitAuthorizationService unitAuthorizationService;

    @Autowired
    @Qualifier("unitLookupableHelperService")
    @Override
    public void setLookupableHelperService(LookupableHelperService lookupableHelperService) {
        super.setLookupableHelperService(lookupableHelperService);
    }

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