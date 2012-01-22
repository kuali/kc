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
package org.kuali.kra.award.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class associates an AwardContact and a Unit
 */
public class AwardPersonUnit extends KraPersistableBusinessObjectBase implements Comparable<AwardPersonUnit>, SequenceAssociate<Award> {

    public static final boolean IS_LEAD_UNIT = Boolean.TRUE;

    public static final boolean IS_NOT_LEAD_UNIT = Boolean.FALSE;

    private static final long serialVersionUID = 3550317176047537585L;

    private Long awardPersonUnitId;

    //don't version parent bo as it leads to odd and destructive behavior in some cases  
    @SkipVersioning
    @AwardSyncableProperty(parent = true, parentProperty = "units")
    private AwardPerson awardPerson;

    @AwardSyncableProperty
    private boolean leadUnit;

    private Unit unit;

    private List<AwardPersonUnitCreditSplit> creditSplits;

    // OJB Hack  
    @AwardSyncableProperty(key = true)
    private String unitNumber;

    private Long awardContactId;

    /**
     * Default Constructor
     */
    public AwardPersonUnit() {
        super();
        creditSplits = new ArrayList<AwardPersonUnitCreditSplit>();
    }

    /**
     * Constructs a AwardPersonUnit
     * @param awardPerson
     */
    AwardPersonUnit(AwardPerson awardPerson) {
        this();
        setAwardPerson(awardPerson);
    }

    /**
     * Constructs a AwardPersonUnit
     * @param awardPerson
     * @param unit
     * @param isLeadUnit
     */
    AwardPersonUnit(AwardPerson awardPerson, Unit unit, boolean isLeadUnit) {
        this();
        setAwardPerson(awardPerson);
        setUnit(unit);
        leadUnit = isLeadUnit;
    }

    /**
     * Find the lead unit from among award awardPerson units
     * @param awardPersonUnits
     * @return
     */
    public static Unit findLeadUnit(Collection<AwardPersonUnit> awardPersonUnits) {
        Unit foundLeadUnit = null;
        for (AwardPersonUnit apu : awardPersonUnits) {
            if (apu.isLeadUnit()) {
                foundLeadUnit = apu.getUnit();
            }
        }
        return foundLeadUnit;
    }

    /**
     * @param creditSplit
     */
    public void add(AwardPersonUnitCreditSplit creditSplit) {
        creditSplits.add(creditSplit);
        creditSplit.setAwardPersonUnit(this);
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(AwardPersonUnit other) {
        return this.unit.getUnitName().compareToIgnoreCase(other.getUnit().getUnitName());
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AwardPersonUnit)) {
            return false;
        }
        AwardPersonUnit other = (AwardPersonUnit) obj;
        if (awardPerson == null) {
            if (other.awardPerson != null) {
                return false;
            }
        } else if (!awardPerson.equals(other.awardPerson)) {
            return false;
        }
        if (unit == null) {
            if (other.unit != null) {
                return false;
            }
        } else if (!unit.equals(other.unit)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the awardPersonId attribute. 
     * @return Returns the awardPersonId.
     */
    public Long getAwardContactId() {
        return awardContactId;
    }

    public List<UnitAdministrator> getOspAdministrators() {
        List<UnitAdministrator> ospAdministrators = new ArrayList<UnitAdministrator>();
        if (unit != null) {
            for (UnitAdministrator unitAdministrator : unit.getUnitAdministrators()) {
                if (unitAdministrator.getUnitAdministratorType().getDescription().equals("OSP_ADMINISTRATOR")) {
                    ospAdministrators.add(unitAdministrator);
                }
            }
        }
        return ospAdministrators;
    }

    /**
     * @return
     */
    public AwardContact getAwardPerson() {
        return awardPerson;
    }

    /**
     * Gets the awardPersonUnitId attribute. 
     * @return Returns the awardPersonUnitId.
     */
    public Long getAwardPersonUnitId() {
        return awardPersonUnitId;
    }

    /**
     * @param index
     * @return
     */
    public AwardPersonUnitCreditSplit getCreditSplit(int index) {
        return creditSplits.get(index);
    }

    /**
     * Gets the creditSplits attribute. 
     * @return Returns the creditSplits.
     */
    public List<AwardPersonUnitCreditSplit> getCreditSplits() {
        return creditSplits;
    }

    /**
     * @return
     */
    public String getFullName() {
        return awardPerson != null ? (awardPerson.getContact() != null ? awardPerson.getContact().getFullName() : null) : null;
    }

    /**
     * @return
     */
    public Unit getUnit() {
        lazilyLoadUnit();
        return unit;
    }

    /**
     * @return
     */
    public String getUnitName() {
        return unit != null ? unit.getUnitName() : null;
    }

    /**
     * @return
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((awardPerson == null) ? 0 : awardPerson.hashCode());
        result = PRIME * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    /**
     * @return
     */
    public boolean isLeadUnit() {
        return leadUnit;
    }

    /**
     * Sets the awardPersonId attribute value.
     * @param awardPersonId The awardPersonId to set.
     */
    public void setAwardContactId(Long awardPersonId) {
        this.awardContactId = awardPersonId;
    }

    /**
     * @param awardPerson
     */
    public void setAwardPerson(AwardPerson awardPerson) {
        this.awardPerson = awardPerson;
        this.awardContactId = awardPerson != null ? awardPerson.getAwardContactId() : null;
    }

    /**
     * Sets the awardPersonUnitId attribute value.
     * @param awardPersonUnitId The awardPersonUnitId to set.
     */
    public void setAwardPersonUnitId(Long awardPersonUnitId) {
        this.awardPersonUnitId = awardPersonUnitId;
    }

    /**
     * Sets the creditSplits attribute value.
     * @param creditSplits The creditSplits to set.
     */
    public void setCreditSplits(List<AwardPersonUnitCreditSplit> creditSplits) {
        this.creditSplits = creditSplits;
    }

    /**
     * @param leadUnit
     */
    public void setLeadUnit(boolean leadUnit) {
        this.leadUnit = leadUnit;
    }

    /**
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
        this.unitNumber = unit != null ? unit.getUnitNumber() : null;
    }

    /**
     * Sets the unitNumber attribute value.
     * @param unitNumber The unitNumber to set.
     */
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("unitNumber", unitNumber);
        Unit unit = (Unit) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(Unit.class, pk);
        if (unit != null) {
            this.unit = unit;
        } else {
            this.unit = null;
            this.unitNumber = null;
        }
    }

    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    private void lazilyLoadUnit() {
        if (StringUtils.isNotEmpty(unitNumber) && unit == null) {
            Collection c = getBusinessObjectService().findMatching(Unit.class, ServiceHelper.getInstance().buildCriteriaMap("unitNumber", unitNumber));
            if (c.size() > 0) {
                unit = (Unit) c.iterator().next();
            }
        }
    }

    public Award getSequenceOwner() {
        return awardPerson != null ? awardPerson.getAward() : null;
    }

    public void setSequenceOwner(Award newlyVersionedOwner) {
        if (awardPerson != null) {
            awardPerson.setAward(newlyVersionedOwner);
        }
    }

    public Integer getSequenceNumber() {
        return awardPerson != null ? awardPerson.getSequenceNumber() : 0;
    }

    public void resetPersistenceState() {
        awardPersonUnitId = null;
    }
}
