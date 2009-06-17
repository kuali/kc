/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The Unit Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnitServiceImpl implements UnitService {
    
    private BusinessObjectService businessObjectService;
    private static final String COLUMN = ":";
    private static final String SEPARATOR = ";1;";
    private static final String DASH = "-";
    private int numberOfUnits;

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
    
    /**
     * 
     * @see org.kuali.kra.service.UnitService#getSubUnitsForTreeView(java.lang.String)
     */
    public String getSubUnitsForTreeView(String unitNumber) {
        // unitNumber will be like "<table width="600"><tr><td width="70%">BL-BL : BLOOMINGTON CAMPUS"
        String subUnits = null;
        // Following index check maybe changed if refactor jsp page to align buttons.
        int startIdx = unitNumber.indexOf("px\">", unitNumber.indexOf("<tr>"));
        for (Unit unit : getSubUnits(unitNumber.substring(startIdx+4, unitNumber.indexOf(COLUMN, startIdx) - 1))) {
            if (StringUtils.isNotBlank(subUnits)) {
                subUnits = subUnits +"," +unit.getUnitNumber()+KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+unit.getUnitName();
            } else {
                subUnits = unit.getUnitNumber()+KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+unit.getUnitName();                
            }
        }
        return subUnits;
        
    }
    
    public Unit getTopUnit() {
        Unit topUnit = null;

        List<Unit> allUnits = (List<Unit>) getUnits();
        if (CollectionUtils.isNotEmpty(allUnits)) {
            for(Unit unit: allUnits) {
                if(StringUtils.isEmpty(unit.getParentUnitNumber())) {
                    topUnit = unit;
                    break;
                }
            }
        }

        return topUnit;
    }
    
    /**
     * TODO : still WIP.  cleanup b4 move to prod
     * @see org.kuali.kra.service.UnitService#getInitialUnitsForUnitHierarchy()
     * Basic data structure : Get the Top node to display.
     * The node data is like following : 'parentidx-unitNumber : unitName' and separated by ';1;'
     */
    public String getInitialUnitsForUnitHierarchy() {
        Unit instituteUnit = getTopUnit();
        int parentIdx = 0;
        String subUnits = instituteUnit.getUnitNumber() +KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+instituteUnit.getUnitName()+SEPARATOR;
        numberOfUnits = 0;
        for (Unit unit : getSubUnits(instituteUnit.getUnitNumber())) {
            subUnits = subUnits + parentIdx + DASH + unit.getUnitNumber()+KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+unit.getUnitName()+SEPARATOR;
            // we can make it more flexible, to add a while loop and with a 'depth' argument.
            numberOfUnits++;
            for (Unit unit1 : getSubUnits(unit.getUnitNumber())) {
                subUnits = subUnits + numberOfUnits + DASH + unit1.getUnitNumber()+KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+unit1.getUnitName()+SEPARATOR;
            }
        }
        subUnits = subUnits.substring(0, subUnits.length() - 3);

        return subUnits;
        
    }
    
    public String getInitialUnitsForUnitHierarchy(int depth) {
        Unit instituteUnit = getTopUnit();
        int parentIdx = 0;
        String subUnits = instituteUnit.getUnitNumber() +KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+instituteUnit.getUnitName()+SEPARATOR;
        numberOfUnits = 0;
        for (Unit unit : getSubUnits(instituteUnit.getUnitNumber())) {
            subUnits = subUnits + parentIdx + DASH + unit.getUnitNumber()+KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+unit.getUnitName()+SEPARATOR;
            // we can make it more flexible, to add a while loop and with a 'depth' argument.
            numberOfUnits++;
            if (depth - 2 > 0) {
                subUnits = subUnits +  getSubUnits(unit, depth - 2);
            }
        }
        subUnits = subUnits.substring(0, subUnits.length() - 3);

        return subUnits;
        
    }

    private String getSubUnits (Unit unit, int level) {
        String subUnits="";
        int parentNum = numberOfUnits;
        level--;
        for (Unit unit1 : getSubUnits(unit.getUnitNumber())) {
            subUnits = subUnits + parentNum + DASH + unit1.getUnitNumber()+KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+unit1.getUnitName()+SEPARATOR;
            numberOfUnits++;
            if (level > 0) {
                subUnits = subUnits +  getSubUnits(unit1, level);
            }
        }
        return subUnits;        
    }
    
}
