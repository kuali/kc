/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.crrspndnt.UnitCorrespondent;
import org.kuali.kra.iacuc.bo.IacucUnitCorrespondent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The Unit Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("unitService")
public class UnitServiceImpl implements UnitService {

    private static final String COLUMN = ":";
    private static final String SEPARATOR = ";1;";
    private static final String DASH = "-";
    private static final String UNIT_NUMBER = "unitNumber";
    
    @Autowired
    @Qualifier("unitLookupDao")
    private UnitLookupDao unitLookupDao;
	
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;   
    
    @Override
    public Unit getUnitCaseInsensitive(String unitNumber){
        Unit unit = null;
        if (StringUtils.isNotEmpty(unitNumber)) {
            unit = getUnitLookupDao().findUnitbyNumberCaseInsensitive(unitNumber);
        }
        return unit;
    }
    
    @Override
    public String getUnitName(String unitNumber) {
        String unitName = null;
        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(unitNumber)) {
            primaryKeys.put("unitNumber", unitNumber);
            Unit unit = (Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, primaryKeys);
            if (unit != null) {
                unitName = unit.getUnitName();
            }
        }

        return unitName;
    }

    @Override
    public Collection<Unit> getUnits() {
        return getBusinessObjectService().findAll(Unit.class);
    }

    @Override
    public Unit getUnit(String unitNumber) {
        Unit unit = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(unitNumber)) {
            primaryKeys.put("unitNumber", unitNumber);
            unit = (Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, primaryKeys);
        }

        return unit;
    }
    
    @Override
    public List<Unit> getSubUnits(String unitNumber) {
        List<Unit> units = new ArrayList<Unit>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentUnitNumber", unitNumber);
        units.addAll(getBusinessObjectService().findMatching(Unit.class, fieldValues));
        return units;
    }
    
    @Override
    public List<Unit> getAllSubUnits(String unitNumber) {
        List<Unit> units = new ArrayList<Unit>();
        List<Unit> subUnits = getSubUnits(unitNumber);
        units.addAll(subUnits);
        for (Unit subUnit : subUnits) {
            units.addAll(getAllSubUnits(subUnit.getUnitNumber()));
        }
        
        return units;
    }
    
    @Override
    public List<Unit> getUnitHierarchyForUnit(String unitNumber) {
        List<Unit> units = new ArrayList<Unit>();
        Unit thisUnit = this.getUnit(unitNumber);
        if (thisUnit != null) {
            units.addAll(getUnitParentsAndSelf(thisUnit));
            //units.addAll(getAllSubUnits(unitNumber));
        }
        return units;
    }
    
    /**
     * 
     * This method returns a List of Units containing all the unit's parents up to the root unit, and includes the unit itself.
     * @param unit
     * @return
     */
    private List<Unit> getUnitParentsAndSelf(Unit unit) {
        List<Unit> units = new ArrayList<Unit>();
        if (!StringUtils.isEmpty(unit.getParentUnitNumber())) {
            units.addAll(getUnitHierarchyForUnit(unit.getParentUnitNumber()));
        }
        units.add(unit);
        return units;
    }

    
    @Override
    public String getSubUnitsForTreeView(String unitNumber) {
        // unitNumber will be like "<table width="600"><tr><td width="70%">BL-BL : BLOOMINGTON CAMPUS"
        String subUnits = null;
        // Following index check maybe changed if refactor jsp page to align buttons.
        int startIdx = unitNumber.indexOf("px\">", unitNumber.indexOf("<tr>"));
        for (Unit unit : getSubUnits(unitNumber.substring(startIdx+4, unitNumber.indexOf(COLUMN, startIdx) - 1))) {
            if (StringUtils.isNotBlank(subUnits)) {
                subUnits = subUnits +"#SEPARATOR#" +unit.getUnitNumber()+KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+unit.getUnitName();
            } else {
                subUnits = unit.getUnitNumber()+KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+unit.getUnitName();                
            }
        }
        return subUnits;
        
    }
    
    @Override
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
     * @see org.kuali.coeus.common.framework.unit.UnitService#getInitialUnitsForUnitHierarchy()
     * Basic data structure : Get the Top node to display.
     * The node data is like following : 'parentidx-unitNumber : unitName' and separated by ';1;'
     */
    public String getInitialUnitsForUnitHierarchy() {
        Unit instituteUnit = getTopUnit();
        int parentIdx = 0;
        String subUnits = instituteUnit.getUnitNumber() +KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+instituteUnit.getUnitName()+SEPARATOR;
        int numberOfUnits = 0;
        for (Unit unit : getSubUnits(instituteUnit.getUnitNumber())) {
            subUnits = subUnits + parentIdx + DASH + unit.getUnitNumber()+KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+unit.getUnitName()+SEPARATOR;
            // we can make it more flexible, to add a while loop and with a 'depth' argument.
            numberOfUnits++;
            for (Unit unit1 : getSubUnits(unit.getUnitNumber())) {
                subUnits = subUnits + numberOfUnits + DASH + unit1.getUnitNumber()+KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+unit1.getUnitName()+SEPARATOR;
            }
        }
        subUnits = subUnits.substring(0, subUnits.length() - 3);

        return subUnits;
        
    }
    
    public String getInitialUnitsForUnitHierarchy(int depth) {
        Unit instituteUnit = getTopUnit();
        int parentIdx = 0;
        String subUnits = instituteUnit.getUnitNumber() +KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+instituteUnit.getUnitName()+SEPARATOR;
        int numberOfUnits = 0;
        for (Unit unit : getSubUnits(instituteUnit.getUnitNumber())) {
            subUnits = subUnits + parentIdx + DASH + unit.getUnitNumber()+KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+unit.getUnitName()+SEPARATOR;
            // we can make it more flexible, to add a while loop and with a 'depth' argument.
            numberOfUnits++;
            if (depth - 2 > 0) {
                subUnits = subUnits +  getSubUnits(numberOfUnits, unit, depth - 2);
            }
        }
        subUnits = subUnits.substring(0, subUnits.length() - 3);

        return subUnits;
        
    }

    protected String getSubUnits (int numberOfUnits, Unit unit, int level) {
        String subUnits="";
        int parentNum = numberOfUnits;
        level--;
        for (Unit unit1 : getSubUnits(unit.getUnitNumber())) {
            subUnits = subUnits + parentNum + DASH + unit1.getUnitNumber()+KRADConstants.BLANK_SPACE+COLUMN+KRADConstants.BLANK_SPACE+unit1.getUnitName()+SEPARATOR;
            numberOfUnits++;
            if (level > 0) {
                subUnits = subUnits +  getSubUnits(numberOfUnits, unit1, level);
            }
        }
        return subUnits;        
    }
    
    @SuppressWarnings("unchecked")
    public List<UnitAdministrator> retrieveUnitAdministratorsByUnitNumber(String unitNumber) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(UNIT_NUMBER, unitNumber);
        List<UnitAdministrator> unitAdministrators = 
            (List<UnitAdministrator>) getBusinessObjectService().findMatching(UnitAdministrator.class, queryMap);
        return unitAdministrators;
    }
    
    @Override
    public int getMaxUnitTreeDepth() {
        /**
         * This function returns a higher number than the actual depth of the hirearchy tree.  This does not cause any problem as of yet.
         * A closer to accurate query would be:
         *      select count(distinct parent_unit_number) as counter from unit where PARENT_UNIT_NUMBER is not null
         * although this to will result in a higher number than the true depth.
         * @TODO fix this as time allows. 
         */
        return getBusinessObjectService().findAll(Unit.class).size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UnitCorrespondent> retrieveUnitCorrespondentsByUnitNumber(String unitNumber) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(UNIT_NUMBER, unitNumber);
        List<UnitCorrespondent> unitCorrespondents = 
            (List<UnitCorrespondent>) getBusinessObjectService().findMatching(UnitCorrespondent.class, queryMap);
        return unitCorrespondents;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<IacucUnitCorrespondent> retrieveIacucUnitCorrespondentsByUnitNumber(String unitNumber) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(UNIT_NUMBER, unitNumber);
        List<IacucUnitCorrespondent> unitCorrespondents = 
            (List<IacucUnitCorrespondent>) getBusinessObjectService().findMatching(IacucUnitCorrespondent.class, queryMap);
        return unitCorrespondents;
    }
    
    public UnitLookupDao getUnitLookupDao() {
        return unitLookupDao;
    }

    public void setUnitLookupDao(UnitLookupDao unitLookupDao) {
        this.unitLookupDao = unitLookupDao;
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
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return this.businessObjectService;
    }
    
}
