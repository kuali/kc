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
