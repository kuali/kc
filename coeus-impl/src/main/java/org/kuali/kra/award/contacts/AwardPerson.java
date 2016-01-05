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
package org.kuali.kra.award.contacts;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements an Award Person 
 */
public class AwardPerson extends AwardContact implements PersonRolodex, Comparable<AwardPerson>, AbstractProjectPerson {

    private static final long serialVersionUID = 7980027108784055721L;

    @AwardSyncableProperty
    private ScaleTwoDecimal academicYearEffort;

    @AwardSyncableProperty
    private ScaleTwoDecimal calendarYearEffort;

    @AwardSyncableProperty
    private boolean faculty;

    @AwardSyncableProperty
    private ScaleTwoDecimal summerEffort;

    @AwardSyncableProperty
    private ScaleTwoDecimal totalEffort;

    @AwardSyncableProperty
    private String keyPersonRole;
    
    @AwardSyncableProperty
    private boolean optInUnitStatus;

    @AwardSyncableProperty
    private List<AwardPersonUnit> units;

    private List<AwardPersonCreditSplit> creditSplits;
    
    private transient boolean roleChanged;
    
    private transient PropAwardPersonRoleService propAwardPersonRoleService;

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
     * Gets the academicYearEffort attribute. 
     * @return Returns the academicYearEffort.
     */
    public ScaleTwoDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    /**
     * Gets the calendarYearEffort attribute. 
     * @return Returns the calendarYearEffort.
     */
    public ScaleTwoDecimal getCalendarYearEffort() {
        return calendarYearEffort;
    }

    /**
     * @param index
     * @return
     */
    public AwardPersonCreditSplit getCreditSplit(int index) {
        return creditSplits.get(index);
    }


    public List<AwardPersonCreditSplit> getCreditSplits() {
        return creditSplits;
    }

    /**
     * Gets the summerEffort attribute. 
     * @return Returns the summerEffort.
     */
    public ScaleTwoDecimal getSummerEffort() {
        return summerEffort;
    }

    /**
     * Gets the totalEffort attribute. 
     * @return Returns the totalEffort.
     */
    public ScaleTwoDecimal getTotalEffort() {
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
        return StringUtils.equals(getContactRoleCode(), ContactRole.COI_CODE);
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
        return StringUtils.equals(getContactRoleCode(), ContactRole.KEY_PERSON_CODE);
    }


    public boolean isPrincipalInvestigator() {
        return StringUtils.equals(getContactRoleCode(), ContactRole.PI_CODE);
    }
    
    public boolean isMultiplePi() {
    	return StringUtils.equals(getContactRoleCode(), PropAwardPersonRole.MULTI_PI);
    }

    /**
     * Sets the academicYearEffort attribute value.
     * @param academicYearEffort The academicYearEffort to set.
     */
    public void setAcademicYearEffort(ScaleTwoDecimal academicYearEffort) {
        this.academicYearEffort = academicYearEffort;
    }

    /**
     * Sets the calendarYearEffort attribute value.
     * @param calendarYearEffort The calendarYearEffort to set.
     */
    public void setCalendarYearEffort(ScaleTwoDecimal calendarYearEffort) {
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
    public void setSummerEffort(ScaleTwoDecimal summerEffort) {
        this.summerEffort = summerEffort;
    }

    /**
     * Sets the totalEffort attribute value.
     * @param totalEffort The totalEffort to set.
     */
    public void setTotalEffort(ScaleTwoDecimal totalEffort) {
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

    @SuppressWarnings("unchecked")
    @Override
    protected Class getContactRoleType() {
        return PropAwardPersonRole.class;
    }

    @Override
    protected Map<String, Object> getContactRoleIdentifierMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", getRoleCode());
        map.put("sponsorHierarchyName", getPropAwardPersonRoleService().getSponsorHierarchy(getAward().getSponsorCode()));
        return map;
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

    @Override
    public int compareTo(AwardPerson o) {
        int lastNameComp = ObjectUtils.compare(this.getLastName(), o.getLastName());
        if (lastNameComp != 0) {
            return lastNameComp;
        } else {
            return ObjectUtils.compare(this.getFirstName(), o.getFirstName());
        }
    }

    public Award getParent() {
        return getAward();
    }

    public String getInvestigatorRoleDescription() {
    	return getContactRole().getRoleDescription();
    }

    public boolean isOptInUnitStatus() {
        return optInUnitStatus;
    }

    public void setOptInUnitStatus(boolean optInUnitStatus) {
        this.optInUnitStatus = optInUnitStatus;
    }
    
    public String getLastName() {
        String lastName = null;
        if (getPerson() != null) {
            lastName = getPerson().getLastName();
        } else if (getRolodex() != null) {
            lastName = getRolodex().getLastName();
        }
        return lastName;
    }
    
    public String getFirstName() {
        String firstName = null;
        if (getPerson() != null) {
            firstName = getPerson().getFirstName();
        } else if (getRolodex() != null) {
            firstName = getRolodex().getFirstName();
        }
        return firstName;
    }
    
    public boolean getIsRolodexPerson() {
        return this.getRolodex() != null;
    }
    
    @Override
    public void setContactRoleCode(String roleCode) {
        if (!StringUtils.equals(roleCode, this.roleCode)) {
            updateBasedOnRoleChange();
            //used by AwardContactsAction to work around repopulation of units due to credit split being on page and posted with
            //role change.
            roleChanged = true;
        }
        super.setContactRoleCode(roleCode);
    }
    
    public void updateBasedOnRoleChange() {
        if (PropAwardPersonRole.KEY_PERSON.equals(roleCode)) {
            this.setOptInUnitStatus(true);
        } else {
            if (this.getPerson() != null && this.getPerson().getUnit() != null && this.getUnits().isEmpty()) {
                this.add(new AwardPersonUnit(this, this.getPerson().getUnit(), true));
            }                
        }        
    }

    public boolean isRoleChanged() {
        return roleChanged;
    }

    public void setRoleChanged(boolean roleChanged) {
        this.roleChanged = roleChanged;
    }

    protected ContactRole refreshContactRole() {
    	if (StringUtils.isNotBlank(getRoleCode()) && getParent() != null && StringUtils.isNotBlank(getParent().getSponsorCode())) {
    		contactRole = getPropAwardPersonRoleService().getRole(getRoleCode(), getParent().getSponsorCode());
    	} else {
    		contactRole = null;
    	}
    	return contactRole;
    }

	public PropAwardPersonRoleService getPropAwardPersonRoleService() {
		if (propAwardPersonRoleService == null) {
			propAwardPersonRoleService = KcServiceLocator.getService(PropAwardPersonRoleService.class);
		}
		return propAwardPersonRoleService;
	}

	public void setPropAwardPersonRoleService(
			PropAwardPersonRoleService propAwardPersonRoleService) {
		this.propAwardPersonRoleService = propAwardPersonRoleService;
	}
	
	@Override
	public Integer getOrdinalPosition() {
		return 0;
	}
	
	@Override
	public boolean isInvestigator() {
		return isPrincipalInvestigator() || isMultiplePi() || isCoInvestigator() || (isKeyPerson() && isOptInUnitStatus());
	}

}
