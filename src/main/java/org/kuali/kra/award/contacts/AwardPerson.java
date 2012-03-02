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
import java.util.List;

import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.service.Sponsorable;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * This class implements an Award Person 
 */
public class AwardPerson extends AwardContact implements PersonRolodex, Comparable<AwardPerson> {

    private static final long serialVersionUID = 7980027108784055721L;

    @AwardSyncableProperty
    private KualiDecimal academicYearEffort;

    @AwardSyncableProperty
    private KualiDecimal calendarYearEffort;

    @AwardSyncableProperty
    private boolean faculty;

    @AwardSyncableProperty
    private KualiDecimal summerEffort;

    @AwardSyncableProperty
    private KualiDecimal totalEffort;

    @AwardSyncableProperty
    private String keyPersonRole;
    
    @AwardSyncableProperty
    private boolean optInUnitStatus;

    @AwardSyncableProperty
    private List<AwardPersonUnit> units;

    private List<AwardPersonCreditSplit> creditSplits;

    @AwardSyncableProperty
    private boolean multiplePi;

    public AwardPerson() {
        super();
        init();
    }

    public AwardPerson(NonOrganizationalRolodex rolodex, ContactRole contactRole) {
        super(rolodex, contactRole);
        init();
    }

    public AwardPerson(KcPerson person, ContactRole role) {
        super(person, role);
        init();
    }

    /**
     * @param creditSplit
     */
    public void add(AwardPersonCreditSplit creditSplit) {
        creditSplits.add(creditSplit);
        creditSplit.setAwardPerson(this);
    }

    /**
     * 
     * This method associates a unit to the 
     * @param unit
     * @param isLeadUnit
     */
    public void add(AwardPersonUnit awardPersonUnit) {
        units.add(awardPersonUnit);
        awardPersonUnit.setAwardPerson(this);
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getUnits());
        return managedLists;
    }

    /**
     * This method finds the lead unit for an AwardPerson
     * @return
     */
    public Unit findLeadUnit() {
        return AwardPersonUnit.findLeadUnit(units);
    }

    /**
     * Gets the academicYearEffort attribute. 
     * @return Returns the academicYearEffort.
     */
    public KualiDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    /**
     * Gets the calendarYearEffort attribute. 
     * @return Returns the calendarYearEffort.
     */
    public KualiDecimal getCalendarYearEffort() {
        return calendarYearEffort;
    }

    /**
     * @param index
     * @return
     */
    public AwardPersonCreditSplit getCreditSplit(int index) {
        return creditSplits.get(index);
    }

    /**
     * @return
     */
    public List<AwardPersonCreditSplit> getCreditSplits() {
        return creditSplits;
    }

    /**
     * Gets the summerEffort attribute. 
     * @return Returns the summerEffort.
     */
    public KualiDecimal getSummerEffort() {
        return summerEffort;
    }

    /**
     * Gets the totalEffort attribute. 
     * @return Returns the totalEffort.
     */
    public KualiDecimal getTotalEffort() {
        return totalEffort;
    }

    /**
     * @param index
     * @return
     */
    public AwardPersonUnit getUnit(int index) {
        return units.get(index);
    }

    /**
     * Get the award unit if it exists from this person
     * 
     * @param unitNumber String
     * @return AwardPersonUnit 
     */
    public AwardPersonUnit getUnit(String unitNumber) {
        for (AwardPersonUnit awardPersonUnit : this.getUnits()) {
            if (awardPersonUnit.getUnitNumber().equals(unitNumber)) {
                return awardPersonUnit;
            }
        }
        return null;
    }

    /**
     * Gets the units attribute. 
     * @return Returns the units.
     */
    public List<AwardPersonUnit> getUnits() {
        return units;
    }

    /**
     * This method determines if person is CO-I
     * @return
     */
    public boolean isCoInvestigator() {
        return getContactRole() != null && getContactRole().getRoleCode().equals(ContactRole.COI_CODE);
    }

    /**
     * Gets the faculty attribute. 
     * @return Returns the faculty.
     */
    public boolean isFaculty() {
        return faculty;
    }

    /**
     * This method determines if person is KeyPerson
     * @return
     */
    public boolean isKeyPerson() {
        return getContactRole() != null && getContactRole().getRoleCode().equals(ContactRole.KEY_PERSON_CODE);
    }

    /**
     * @return
     */
    public boolean isPrincipalInvestigator() {
        return getContactRole() != null && getContactRole().getRoleCode().equals(ContactRole.PI_CODE);
    }

    /**
     * Sets the academicYearEffort attribute value.
     * @param academicYearEffort The academicYearEffort to set.
     */
    public void setAcademicYearEffort(KualiDecimal academicYearEffort) {
        this.academicYearEffort = academicYearEffort;
    }

    /**
     * Sets the calendarYearEffort attribute value.
     * @param calendarYearEffort The calendarYearEffort to set.
     */
    public void setCalendarYearEffort(KualiDecimal calendarYearEffort) {
        this.calendarYearEffort = calendarYearEffort;
    }

    /**
     * Sets the creditSplits attribute value.
     * @param creditSplits The creditSplits to set.
     */
    public void setCreditSplits(List<AwardPersonCreditSplit> creditSplits) {
        this.creditSplits = creditSplits;
    }

    /**
     * Sets the faculty attribute value.
     * @param faculty The faculty to set.
     */
    public void setFaculty(boolean faculty) {
        this.faculty = faculty;
    }

    /**
     * Sets the summerEffort attribute value.
     * @param summerEffort The summerEffort to set.
     */
    public void setSummerEffort(KualiDecimal summerEffort) {
        this.summerEffort = summerEffort;
    }

    /**
     * Sets the totalEffort attribute value.
     * @param totalEffort The totalEffort to set.
     */
    public void setTotalEffort(KualiDecimal totalEffort) {
        this.totalEffort = totalEffort;
    }

    /**
     * Sets the units attribute value.
     * @param units The units to set.
     */
    public void setUnits(List<AwardPersonUnit> units) {
        this.units = units;
    }

    public String toString() {
        return String.format("%s:%s", getContact().getIdentifier().toString(), getContact().getFullName());
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleType()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class getContactRoleType() {
        return ProposalPersonRole.class;
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleTypeIdentifier()
     */
    @Override
    protected String getContactRoleTypeIdentifier() {
        return "proposalPersonRoleId";
    }

    protected void init() {
        units = new ArrayList<AwardPersonUnit>();
        creditSplits = new ArrayList<AwardPersonCreditSplit>();
        optInUnitStatus = false;
    }

    public String getProjectRole() {
        return getContactRole().getRoleDescription();
    }

    public boolean isOtherSignificantContributorFlag() {
        return false;
    }

    public String getKeyPersonRole() {
        return keyPersonRole;
    }

    public void setKeyPersonRole(String keyPersonRole) {
        this.keyPersonRole = keyPersonRole;
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(AwardPerson o) {
        int lastNameComp = this.getPerson().getLastName().compareTo(o.getPerson().getLastName());
        if (lastNameComp != 0) {
            return lastNameComp;
        } else {
            return this.getPerson().getFirstName().compareTo(o.getPerson().getFirstName());
        }
    }

    public boolean isMultiplePi() {
        return multiplePi;
    }

    public void setMultiplePi(boolean multiplePi) {
        this.multiplePi = multiplePi;
    }

    public Sponsorable getParent() {
        return getAward();
    }

    public String getInvestigatorRoleDescription() {
        return KraServiceLocator.getService(KeyPersonnelService.class).getPersonnelRoleDesc(this);
    }

    public boolean isOptInUnitStatus() {
        return optInUnitStatus;
    }

    public void setOptInUnitStatus(boolean optInUnitStatus) {
        this.optInUnitStatus = optInUnitStatus;
    }
}
