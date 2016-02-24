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
package org.kuali.coeus.common.impl.unit.admin;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.AbstractUnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.AbstractUnitAdministratorDerivedRoleTypeService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.framework.role.RoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Checks whether the principal is a Unit Administrator for the given unit.
 */
@Component("unitAdministratorDerivedRoleTypeService")
public class UnitAdministratorDerivedRoleTypeServiceImpl extends AbstractUnitAdministratorDerivedRoleTypeService implements RoleTypeService {

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Override
    public List<? extends AbstractUnitAdministrator> getUnitAdministrators(Map<String, String> qualification) {
        String unitNumber = qualification.get(KcKimAttributes.UNIT_NUMBER);
        
        if (StringUtils.isNotBlank(unitNumber)) {
            return unitService.retrieveUnitAdministratorsByUnitNumber(unitNumber);
        } else {
            return new ArrayList<UnitAdministrator>();
        }
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
}
