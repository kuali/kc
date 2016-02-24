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

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * The Unit Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("unitService")
public class UnitServiceImpl implements UnitService {

    private static final Logger LOGGER = Logger.getLogger(UnitServiceImpl.class);
    private static final String COLUMN = ":";
    private static final String SEPARATOR = ";1;";
    private static final String DASH = "-";
    private static final String UNIT_NUMBER = "unitNumber";
    private static final String PARENT_UNIT_NUMBER = "parentUnitNumber";
    public static final String ACTIVE = "active";
    public static final String ACTIVE_YES = "Y";

    @Autowired
    @Qualifier("unitLookupDao")
    private UnitLookupDao unitLookupDao;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public Unit getUnitCaseInsensitive(String unitNumber) {
        Unit unit = null;
        if (StringUtils.isNotEmpty(unitNumber)) {
            unit = getUnitLookupDao().findUnitbyNumberCaseInsensitive(unitNumber);
        }
        return unit;
    }

    @Override
    public String getUnitName(String unitNumber) {
        final Unit unit = getUnit(unitNumber);
        return unit != null ? unit.getUnitName() : null;
    }

    @Override
    public List<Unit> getUnits() {
        return allUnitsCache.get();
    }

    private final Supplier<List<Unit>> allUnitsCache = Suppliers.memoizeWithExpiration(
            () -> new ArrayList<>(getBusinessObjectService().findAll(Unit.class)), 1, TimeUnit.MINUTES);

    @Override
    public Unit getUnit(String unitNumber) {

        if (StringUtils.isNotEmpty(unitNumber)) {
            return getBusinessObjectService().findBySinglePrimaryKey(Unit.class, unitNumber);
        }

        return null;
    }

    @Override
    public List<Unit> getSubUnits(String unitNumber) {
        List<Unit> units = new ArrayList<>();
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(PARENT_UNIT_NUMBER, unitNumber);
        units.addAll(getBusinessObjectService().findMatching(Unit.class, fieldValues));
        return units;
    }

    @Override
    public List<Unit> getActiveSubUnits(String unitNumber) {
        List<Unit> units = new ArrayList<>();
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(ACTIVE, ACTIVE_YES);
        fieldValues.put(PARENT_UNIT_NUMBER, unitNumber);
        units.addAll(getBusinessObjectService().findMatching(Unit.class, fieldValues));
        return units;
    }

    @Override
    public List<Unit> getAllSubUnits(final String unitNumber) {
        return findUnitsWithDirectParent(getUnits(), unitNumber);
    }

    protected List<Unit> findUnitsWithDirectParent(List<Unit> units, final String directParent) {
        if (CollectionUtils.isNotEmpty(units)) {
            final List<Unit> matched = ListUtils.select(units, new Predicate<Unit>() {
                @Override
                public boolean evaluate(Unit input) {
                    return input.getParentUnitNumber() != null && input.getParentUnitNumber().equals(directParent);
                }
            });

            final List<Unit> totalMatched = new ArrayList<>(matched);
            for (Unit child : matched) {
                totalMatched.addAll(findUnitsWithDirectParent(units, child.getUnitNumber()));
            }
            return totalMatched;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Unit> getUnitHierarchyForUnit(String unitNumber) {
        return getParentUnitsInclusive(getUnits(),unitNumber);
    }

    protected List<Unit> getParentUnitsInclusive(List<Unit> units, final String unit) {
        if (CollectionUtils.isNotEmpty(units)) {
            final Unit matched = CollectionUtils.find(units, new Predicate<Unit>() {
                @Override
                public boolean evaluate(Unit input) {
                    return input.getUnitNumber() != null && input.getUnitNumber().equals(unit);
                }
            });

            if (matched != null) {
                final List<Unit> totalMatched = new ArrayList<>();
                totalMatched.add(matched);
                totalMatched.addAll(getParentUnitsInclusive(units, matched.getParentUnitNumber()));
                return totalMatched;
            } else if (unit != null) {
                LOGGER.error("Invalid parent found " + unit);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public String getSubUnitsForTreeView(String unitNumber) {
        // unitNumber will be like "<table width="600"><tr><td width="70%">BL-BL : BLOOMINGTON CAMPUS"
        String subUnits = null;
        int startIdx = unitNumber.indexOf("px\">", unitNumber.indexOf("<tr>"));
        for (Unit unit : getSubUnits(unitNumber.substring(startIdx + 4, unitNumber.indexOf(COLUMN, startIdx) - 1))) {
            if (StringUtils.isNotBlank(subUnits)) {
                subUnits = subUnits + "#SEPARATOR#" + unit.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + unit.getUnitName();
            } else {
                subUnits = unit.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + unit.getUnitName();
            }
        }
        return subUnits;

    }

    @Override
    public Unit getTopUnit() {
    	return getUnitLookupDao().getTopUnit();
    }

    /**
     * Basic data structure : Get the Top node to display.
     * The node data is like following : 'parentidx-unitNumber : unitName' and separated by ';1;'
     */
    public String getInitialUnitsForUnitHierarchy() {
        Unit instituteUnit = getTopUnit();
        int parentIdx = 0;
        String subUnits = instituteUnit.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + instituteUnit.getUnitName() + SEPARATOR;
        for (Unit unit : getSubUnits(instituteUnit.getUnitNumber())) {
            String subUnit = parentIdx + DASH + unit.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + unit.getUnitName();
            subUnits = subUnits + subUnit + SEPARATOR;
            for (Unit unit1 : getSubUnits(unit.getUnitNumber())) {
                subUnits = subUnits + getParentIndex(subUnits, subUnit) + DASH + unit1.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + unit1.getUnitName() + SEPARATOR;
            }
        }
        subUnits = subUnits.substring(0, subUnits.length() - 3);

        return subUnits;

    }

    public String getInitialUnitsForUnitHierarchy(int depth) {
        Unit instituteUnit = getTopUnit();
        int parentIdx = 0;
        String subUnits = instituteUnit.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + instituteUnit.getUnitName() + SEPARATOR;
        for (Unit unit : getSubUnits(instituteUnit.getUnitNumber())) {
            String subUnit = parentIdx + DASH + unit.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + unit.getUnitName();
            subUnits = subUnits + subUnit + SEPARATOR;
            if (depth - 2 > 0) {
                subUnits = subUnits + getSubUnits(getParentIndex(subUnits, subUnit), unit, depth - 2);
            }
        }
        subUnits = subUnits.substring(0, subUnits.length() - 3);

        return subUnits;

    }

    protected String getSubUnits(int parentIdx, Unit unit, int level) {
        String subUnits = "";
        level--;
        for (Unit unit1 : getSubUnits(unit.getUnitNumber())) {
            String subUnit = parentIdx + DASH + unit1.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + unit1.getUnitName();
            subUnits = subUnits + subUnit + SEPARATOR;
            ;
            if (level > 0) {
                subUnits = subUnits + getSubUnits(getParentIndex(subUnits, subUnit), unit1, level);
            }
        }
        return subUnits;
    }

    protected int getParentIndex(String subUnits, String subUnit) {
        String[] units = subUnits.split(SEPARATOR);
        int i = 0;
        for (String unit : units) {
            if (StringUtils.equals(unit, subUnit)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    public List<UnitAdministrator> retrieveUnitAdministratorsByUnitNumber(String unitNumber) {
        Map<String, String> queryMap = new HashMap<>();
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
         */
        //FIXME fix crap
        return getBusinessObjectService().findAll(Unit.class).size();
    }

    @Override
    public List<UnitCorrespondent> retrieveUnitCorrespondentsByUnitNumber(String unitNumber) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(UNIT_NUMBER, unitNumber);
        List<UnitCorrespondent> unitCorrespondents =
                (List<UnitCorrespondent>) getBusinessObjectService().findMatching(UnitCorrespondent.class, queryMap);
        return unitCorrespondents;
    }

    @Override
    public List<IacucUnitCorrespondent> retrieveIacucUnitCorrespondentsByUnitNumber(String unitNumber) {
        Map<String, String> queryMap = new HashMap<>();
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

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return this.businessObjectService;
    }

}
