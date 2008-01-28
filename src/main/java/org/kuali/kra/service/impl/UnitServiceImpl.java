/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.service.UnitService;

/**
 * The Unit Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnitServiceImpl implements UnitService {
    
    private BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.kra.service.UnitService#getUnitName(java.lang.String)
     */
    public String getUnitName(String unitNumber) {
        String unitName = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(unitNumber)) {
            primaryKeys.put("unitNumber", unitNumber);
            Unit unit = (Unit)businessObjectService.findByPrimaryKey(Unit.class, primaryKeys);
            if (unit != null) {
                unitName = unit.getUnitName();
            }
        }

        return unitName;
    }

    /**
     * @see org.kuali.kra.service.UnitService#getUnits()
     */
    public Collection<Unit> getUnits() {
        return businessObjectService.findAll(Unit.class);
    }

    /**
     * @see org.kuali.kra.service.UnitService#getUnit(java.lang.String)
     */
    public Unit getUnit(String unitNumber) {
        Unit unit = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(unitNumber)) {
            primaryKeys.put("unitNumber", unitNumber);
            unit = (Unit)businessObjectService.findByPrimaryKey(Unit.class, primaryKeys);
        }

        return unit;
    }
    
    /**
     * @see org.kuali.kra.service.UnitService#getSubUnits(java.lang.String)
     */
    public List<Unit> getSubUnits(String unitNumber) {
        List<Unit> units = new ArrayList<Unit>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentUnitNumber", unitNumber);
        units.addAll(businessObjectService.findMatching(Unit.class, fieldValues));
        return units;
    }
    
    /**
     * @see org.kuali.kra.service.UnitService#getAllSubUnits(java.lang.String)
     */
    public List<Unit> getAllSubUnits(String unitNumber) {
        List<Unit> units = new ArrayList<Unit>();
        List<Unit> subUnits = getSubUnits(unitNumber);
        units.addAll(subUnits);
        for (Unit subUnit : subUnits) {
            units.addAll(getAllSubUnits(subUnit.getUnitNumber()));
        }
        
        return units;
    }

    /**
     * Sets the businessObjectService attribute value. Injected by Spring.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
