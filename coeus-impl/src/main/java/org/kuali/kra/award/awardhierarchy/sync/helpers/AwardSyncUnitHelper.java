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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Award Hierarchy Sync Helper for Award Person Units.
 */
public class AwardSyncUnitHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException, AwardSyncException {
        Collection awardPersons = award.getProjectPersons();
        AwardSyncXmlExport unitExport = (AwardSyncXmlExport) change.getXmlExport().getValues().get("units");
        AwardPerson person = (AwardPerson) getAwardSyncUtilityService().findMatchingBo(awardPersons, change.getXmlExport().getKeys());
        if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
            if (person != null) {
                checkAndFixLeadUnit(person, unitExport);
                setValuesOnSyncable(person, change.getXmlExport().getValues(), change);
                fixLeadUnit(award, person);
            } else {
                throw new AwardSyncException(Constants.AWARD_SYNC_NOT_APPLICABLE, true);
            }
        } else {
            if (person != null) {
                AwardPersonUnit unit = 
                    (AwardPersonUnit) getAwardSyncUtilityService().findMatchingBo((Collection) person.getUnits(), unitExport.getKeys());
                if (unit != null) {
                    person.getUnits().remove(unit);
                }
            } else {
                throw new AwardSyncException(Constants.AWARD_SYNC_NOT_APPLICABLE, true);
            }
        }
    }
    
    /**
     * If the export is for a lead unit, then clear other lead unit identifiers.
     * @param person
     * @param export
     */
    protected void checkAndFixLeadUnit(AwardPerson person, AwardSyncXmlExport export) {
        if ((Boolean) export.getValues().get("leadUnit")) {
            //if we are syncing a lead unit, remove any other lead units
            for (AwardPersonUnit unit : person.getUnits()) {
                unit.setLeadUnit(false);
            }
        }
    }
    
    /**
     * Make sure the lead unit of the award still matches the lead unit of the person, if
     * the person is a PI.
     * @param award
     * @param person
     */
    protected void fixLeadUnit(Award award, AwardPerson person) {
        if (person.isPrincipalInvestigator()) {
            for (AwardPersonUnit unit : person.getUnits()) {
                if (unit.isLeadUnit()) {
                    if (!StringUtils.equals(award.getLeadUnit().getUnitNumber(), unit.getUnitNumber())) {
                        award.setLeadUnit(unit.getUnit());
                    }
                }
            }
        }
    }
    
    @Override
    protected String getObjectDesc(PersistableBusinessObject syncableObject, String attrName) {
        AwardPersonUnit unit = (AwardPersonUnit) syncableObject;
        if (unit.isLeadUnit()) {
            return "Lead Unit";
        } else {
            return "Unit";
        }
    }
    
    @Override
    protected String getDataDesc(PersistableBusinessObject syncableObject, String attrName) {
        AwardPersonUnit unit = (AwardPersonUnit) syncableObject;
        return unit.getAwardPerson().getFullName() + " - " + unit.getUnitNumber() + " : " + unit.getUnitName();
    } 
}
