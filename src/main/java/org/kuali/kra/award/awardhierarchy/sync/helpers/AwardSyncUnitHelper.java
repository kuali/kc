/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Award Hierarchy Sync Helper for Award Person Units.
 */
public class AwardSyncUnitHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, 
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
