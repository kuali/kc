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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
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
