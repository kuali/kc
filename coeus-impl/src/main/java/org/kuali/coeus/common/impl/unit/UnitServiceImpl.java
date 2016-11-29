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

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Component("unitService")
public class UnitServiceImpl implements UnitService {

    private static final Logger LOGGER = Logger.getLogger(UnitServiceImpl.class);
    private static final String COLUMN = ":";
    private static final String SEPARATOR = ";1;";
    private static final String DASH = "$-$";
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
            () -> new ArrayList<>(getBusinessObjectService().findAllOrderBy(Unit.class, "unitNumber", true)), 1, TimeUnit.MINUTES);

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
        units.addAll(getBusinessObjectService().findMatchingOrderBy(Unit.class, fieldValues, "unitNumber", true));
        return units;
    }

    @Override
    public List<Unit> getActiveSubUnits(String unitNumber) {
        List<Unit> units = new ArrayList<>();
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(ACTIVE, ACTIVE_YES);
        fieldValues.put(PARENT_UNIT_NUMBER, unitNumber);
        units.addAll(getBusinessObjectService().findMatchingOrderBy(Unit.class, fieldValues, "unitNumber", true));
        return units;
    }

    @Override
    public List<Unit> getAllSubUnits(final String unitNumber) {
        return findUnitsWithDirectParent(getUnits(), unitNumber);
    }

    protected List<Unit> findUnitsWithDirectParent(List<Unit> units, final String directParent) {
        if (CollectionUtils.isNotEmpty(units)) {
            final List<Unit> matched = ListUtils.select(units, input -> input.getParentUnitNumber() != null && input.getParentUnitNumber().equals(directParent));

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
            final Unit matched = CollectionUtils.find(units, input -> input.getUnitNumber() != null && input.getUnitNumber().equals(unit));

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
     * The node data is like following : 'parentidx$-$unitNumber : unitName' and separated by ';1;'
     */
    @Override
    public String getInitialUnitsForUnitHierarchy() {
        return getInitialUnitsForUnitHierarchy(3);
    }

    @Override
    public String getInitialUnitsForUnitHierarchy(int depth) {
        final List<Unit> sortedTruncatedUnits = sortUnits(truncate(getUnits(), depth));

        return sortedTruncatedUnits.stream()
                .map(unit -> (StringUtils.isNotBlank(unit.getParentUnitNumber()) ? unit.getParentUnitNumber() + DASH : "") + unit.getUnitNumber() + KRADConstants.BLANK_SPACE + COLUMN + KRADConstants.BLANK_SPACE + unit.getUnitName())
                .collect(Collectors.joining(SEPARATOR));
    }

    protected List<Unit> sortUnits(List<Unit> units) {
        final List<Unit> unsortedUnits = new ArrayList<>(units);
        final List<Unit> sortedUnits = new ArrayList<>();
        final Queue<Unit> processQueue = new LinkedList<>();
        processQueue.addAll(unsortedUnits.stream()
                .filter(unit -> StringUtils.isBlank(unit.getParentUnitNumber()))
                .sorted(Comparator.comparing(Unit::getUnitNumber))
                .collect(Collectors.toList()));

        while(!processQueue.isEmpty()) {
            final Unit toProcess = processQueue.remove();
            unsortedUnits.remove(toProcess);
            sortedUnits.add(toProcess);
            processQueue.addAll(unsortedUnits.stream()
                    .filter(unit -> unit.getParentUnitNumber().equals(toProcess.getUnitNumber()))
                    .sorted(Comparator.comparing(Unit::getUnitNumber))
                    .collect(Collectors.toList()));
        }
        return sortedUnits;
    }

    protected List<Unit> truncate(List<Unit> units, int depth) {
        final List<Unit> unprocessed = new ArrayList<>(units);
        final List<Unit> processed = new ArrayList<>();

        List<Unit> parents = unprocessed.stream()
                .filter(unit -> StringUtils.isBlank(unit.getParentUnitNumber()))
                .collect(Collectors.toList());
        processTruncate(unprocessed, processed, parents);

        int currentDepth = 1;
        while (currentDepth < depth && !unprocessed.isEmpty()) {
            final List<Unit> currentParents = parents;
            parents = unprocessed.stream()
                    .filter(unit -> currentParents.stream()
                            .map(Unit::getUnitNumber)
                            .collect(Collectors.toList()).contains(unit.getParentUnitNumber()))
                    .collect(Collectors.toList());

            processTruncate(unprocessed, processed, parents);
            currentDepth++;
        }

        return processed;
    }

    /**
     * This method keeps track of units that have been processed and the units yet to be processed by
     * adding to the processed list and removing from the unprocessed list.
     */
    protected void processTruncate(List<Unit> unprocessedList, List<Unit> processedList, List<Unit> currentlyProcessing) {
        processedList.addAll(currentlyProcessing);
        unprocessedList.removeAll(currentlyProcessing);
    }

    @Override
    public List<UnitAdministrator> retrieveUnitAdministratorsByUnitNumber(String unitNumber) {
        return (List<UnitAdministrator>) getBusinessObjectService()
                .findMatching(UnitAdministrator.class, Collections.singletonMap(UNIT_NUMBER, unitNumber));
    }

    @Override
    public int getMaxUnitTreeDepth() {
        return getBusinessObjectService().findAll(Unit.class).size();
    }

    @Override
    public List<UnitCorrespondent> retrieveUnitCorrespondentsByUnitNumber(String unitNumber) {
        return (List<UnitCorrespondent>) getBusinessObjectService()
                .findMatching(UnitCorrespondent.class, Collections.singletonMap(UNIT_NUMBER, unitNumber));
    }

    @Override
    public List<IacucUnitCorrespondent> retrieveIacucUnitCorrespondentsByUnitNumber(String unitNumber) {
        return (List<IacucUnitCorrespondent>) getBusinessObjectService()
                .findMatching(IacucUnitCorrespondent.class, Collections.singletonMap(UNIT_NUMBER, unitNumber));
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
