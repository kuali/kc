/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.internal.weaving.RelationshipInfo;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleService;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements an Award Person 
 */
public class AwardPerson extends AwardContact implements PersonRolodex, Comparable<AwardPerson>, AbstractProjectPerson {

    private static final long serialVersionUID = 7980027108784055721L;

    @AwardSyncableProperty(key = true)
    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    protected Long roleCode;
    
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

    public AwardPerson(NonOrganizationalRolodex rolodex, PropAwardPersonRole contactRole) {
        super(rolodex, contactRole);
        init();
    }

    public AwardPerson(KcPerson person, PropAwardPersonRole role) {
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
        return StringUtils.equals(getContactRole().getCode(), PropAwardPersonRole.CO_INVESTIGATOR);
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
        return StringUtils.equals(getContactRole().getCode(), PropAwardPersonRole.KEY_PERSON);
    }


    public boolean isPrincipalInvestigator() {
        return StringUtils.equals(getContactRole().getCode(), PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
    }
    
    public boolean isMultiplePi() {
    	return StringUtils.equals(getContactRole().getCode(), PropAwardPersonRole.MULTI_PI);
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
    protected String getContactRoleTypeIdentifier() {
        return "id";
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

    public Sponsorable getParent() {
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
    
    public void setContactRoleCode(Long roleCode) {
        if (!roleCode.equals(this.roleCode)) {
            updateBasedOnRoleChange();
            //used by AwardContactsAction to work around repopulation of units due to credit split being on page and posted with
            //role change.
            roleChanged = true;
        }
        this.roleCode = roleCode;
        refreshContactRole();
    }
    
    public void updateBasedOnRoleChange() {
    	PropAwardPersonRole propAwardPersonRole = getRole(PropAwardPersonRole.KEY_PERSON);
        if (propAwardPersonRole.getId().equals(roleCode)) {
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

    public PropAwardPersonRole getRole(String roleCode) {
    	if (StringUtils.isNotBlank(roleCode) && getParent() != null &&
    			StringUtils.isNotBlank(getParent().getSponsorCode())) {
    		return getPropAwardPersonRoleService().getRole(roleCode, getParent().getSponsorCode());
    	} else {
    		return null;
    	}
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
    public PropAwardPersonRole getContactRole() {
        return (PropAwardPersonRole)contactRole;
    }

    @Override
    public void setContactRole(ContactRole contactRole) {
        super.setContactRole(contactRole);
        this.roleCode = contactRole != null ? ((PropAwardPersonRole)contactRole).getId() : null;
    }
    
	public String getRoleCode() {
		return getContactRole().getCode();
	}

	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
        refreshContactRole();
	}

    public Long getContactRoleCode() {
        return roleCode;
    }

	@Override
	protected String getRoleKey() {
		return roleCode.toString();
	}

}
