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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.api.unit.UnitRepositoryService;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("unitRepositoryService")
public class UnitRepositoryServiceImpl implements UnitRepositoryService {

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Override
    public UnitContract findUnitByUnitNumber(String unitNumber) {
        if (StringUtils.isBlank(unitNumber)) {
            throw new IllegalArgumentException("unitNumber in blank");
        }

        return unitService.getUnit(unitNumber);
    }

    @Override
    public UnitContract findTopUnit() {
        return unitService.getTopUnit();
    }
    
    @Override
	public List<UnitContract> getUnitHierarchyForUnit(String unitNumber) {
    	if (StringUtils.isBlank(unitNumber)) {
            throw new IllegalArgumentException("unitNumber is blank");
        }
    	List<UnitContract> unitContractList = new ArrayList<UnitContract>();
    	unitContractList.addAll(unitService.getUnitHierarchyForUnit(unitNumber));
		return ListUtils.emptyIfNull(unitContractList);
	}

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
}
