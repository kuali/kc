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

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.framework.engine.AgendaTree;
import org.kuali.rice.krms.framework.engine.BasicAgenda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class UnitAgenda extends BasicAgenda {

    public static final String TYPE_ID = "typeId";
    public static final String DELIMITER = ",";
    private UnitService unitService;
    private Map<String, String> qualifiers;
    private boolean isActive;

    public UnitAgenda(Map<String, String> qualifiers, AgendaTree agendaTree, String agendaTypeId, boolean isActive) {
        super(qualifiers, agendaTree);
        this.isActive = isActive;
        this.qualifiers = new HashMap<>(qualifiers);
        this.qualifiers.put(TYPE_ID, agendaTypeId);
    }


    @Override
    public boolean appliesTo(ExecutionEnvironment environment) {
        if (!isActive) {
            return false;
        }
        Set<Map.Entry<String, String>> agendaQualifiers = environment.getSelectionCriteria().getAgendaQualifiers().entrySet();
        for (Map.Entry<String, String> agendaQualifier : agendaQualifiers) {
            String agendaQualifierValue = qualifiers.get(agendaQualifier.getKey());
            String environmentQualifierValue = agendaQualifier.getValue();
            if (KcKrmsConstants.UNIT_NUMBER.equals(agendaQualifier.getKey())) {
                return appliesToUnit(agendaQualifierValue,environmentQualifierValue);
            }
        }
        return false;
    }

    protected boolean appliesToUnit(String agendaQualifierValue, String environmentQualifierValue) {
        if(environmentQualifierValue == null) {
            return Boolean.FALSE;
        }
        for (String environmentUnitNumber : environmentQualifierValue.split(DELIMITER)) {
            List<Unit> unitHierarchyForUnit = getUnitService().getUnitHierarchyForUnit(environmentUnitNumber);
            if (appliesToAnyUnitInHierarchy(agendaQualifierValue, unitHierarchyForUnit)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    protected boolean appliesToAnyUnitInHierarchy(String agendaQualifierValue, List<Unit> unitHierarchyForUnit) {
        for (Unit unit : unitHierarchyForUnit) {
            String unitNumber = unit.getUnitNumber();
            if (unitNumber.equals(agendaQualifierValue)) {
                return true;
            }
        }
        return false;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
}
