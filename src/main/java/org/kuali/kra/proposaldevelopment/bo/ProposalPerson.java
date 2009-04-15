/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.Person;
import org.kuali.core.util.KualiDecimal;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * @author $Author: gmcgrego $
 * @version $Revision: 1.42 $
 */
public class ProposalPerson extends Person implements CreditSplitable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4110005875629288373L;

    private boolean conflictOfInterestFlag;
    private boolean otherSignificantContributorFlag;
    private KualiDecimal percentageEffort;
    private Boolean fedrDebrFlag;
    private Boolean fedrDelqFlag;
    private Integer rolodexId;
    private String  personId;
    private String proposalNumber;
    private Integer proposalPersonNumber;
    private String  proposalPersonRoleId;
    private ProposalInvestigatorCertification certification;
    private ProposalPersonRole role;
    private boolean delete;
    private Person person;
    private boolean isInvestigator;
    private boolean roleChanged;
    private List<ProposalPersonYnq> proposalPersonYnqs;
    private List<ProposalPersonUnit> units;
    private List<ProposalPersonDegree> proposalPersonDegrees;
    private List<ProposalPersonCreditSplit> creditSplits;
    private String simpleName;
    private String optInUnitStatus;
    private String optInCertificationStatus;
    private boolean unitdelete;
    private String projectRole;
    private Integer ordinalPosition;
    
    private transient boolean moveDownAllowed;
    private transient boolean moveUpAllowed;    
    
    public boolean isMoveDownAllowed() {
        return moveDownAllowed;
    }

    public boolean isMoveUpAllowed() {
        return moveUpAllowed;
    }

    public void setMoveDownAllowed(boolean moveDownAllowed) {
        this.moveDownAllowed = moveDownAllowed;
    }

    public void setMoveUpAllowed(boolean moveUpAllowed) {
        this.moveUpAllowed = moveUpAllowed;
    }

    /**
     *
     * new ProposalPerson
     */
    public ProposalPerson() {
        proposalPersonDegrees = new ArrayList<ProposalPersonDegree>();
        setUnits(new ArrayList<ProposalPersonUnit>());
        setCreditSplits(new ArrayList<ProposalPersonCreditSplit>());
        setProposalPersonYnqs(new ArrayList<ProposalPersonYnq>());
        roleChanged = false;
        isInvestigator = false;
        delete = false;
        setFullName(new String());
    }
    
    /**
     * Overridden from {@link Person} to set the <code>simpleName</code> 
     * 
     * @see org.kuali.kra.bo.Person#setFullName(java.lang.String)
     */
    public void setFullName(String fullName) {
        super.setFullName(fullName);
        
        setSimpleName(new String(getFullName()));
        
        setSimpleName(getSimpleName().toLowerCase());
        setSimpleName(StringUtils.deleteWhitespace(getSimpleName()));
        setSimpleName(StringUtils.remove(getSimpleName(), '.'));
    }
   
    /**
     * @see org.kuali.kra.bo.Person#getFullName()
     */
    @Override
    @CreditSplitNameInfo
    public String getFullName() {
        return super.getFullName();
    }
    
    /**
     * Stateful variable set by the Action to determine whether this <code>{@link ProposalPerson}</code> 
     * is an investigator or not.
     *
     * @return boolean;
     */
    public boolean isInvestigator() {
        return getInvestigatorFlag();
    }
    
    
    /**
     * Stateful variable set by the Action to determine whether this <code>{@link ProposalPerson}</code> 
     * is an investigator or not.
     *
     * @return boolean;
     */
    public boolean getInvestigatorFlag() {
        return isInvestigator;
    }
    
    /**
     * Stateful variable set by the Action to determine whether this <code>{@link ProposalPerson}</code> 
     * is an investigator or not.
     *
     * @param b;
     */
    public void setInvestigatorFlag(boolean b) {
        isInvestigator = b;
    }


    /**
     * Set a <code>{@link List}</code> of credit splits
     *
     * @param creditSplits
     */
    public void setCreditSplits(List<ProposalPersonCreditSplit> creditSplit) {
        this.creditSplits = creditSplit;
    }
    
    /**
     * Get a <code>{@link List}</code> of credit splits
     *
     * @return List<ProposalPersonCreditSplit>
     */ 
    public List<ProposalPersonCreditSplit> getCreditSplits() {
        return this.creditSplits;
    }

    /**
     * Gets the value of certification
     *
     * @return the value of certification
     */
    public ProposalInvestigatorCertification getCertification() {
        return this.certification;
    }

    /**
     * Sets the value of certification
     *
     * @param argCertification Value to assign to this.certification
     */
    public void setCertification(ProposalInvestigatorCertification argCertification) {
        this.certification = argCertification;
    }

    /**
     * Gets the value of units
     *
     * @return the value of units
     */
    public List<ProposalPersonUnit> getUnits() {
        return this.units;
    }

    /**
     * Sets the value of units
     *
     * @param argUnits Value to assign to this.units
     */
    public void setUnits(List<ProposalPersonUnit> argUnits) {
        this.units = argUnits;
    }

    /**
     * Gets the value of degrees
     *
     * @return the value of degrees
     */
    public List<ProposalPersonDegree> getProposalPersonDegrees() {
        return this.proposalPersonDegrees;
    }

    /**
     * Sets the value of degrees
     *
     * @param argDegrees Value to assign to this.degrees
     */
    public void setProposalPersonDegrees(List<ProposalPersonDegree> argDegrees) {
        this.proposalPersonDegrees = argDegrees;
    }

    /**
     * Gets the value of proposalPersonNumber
     *
     * @return the value of proposalPersonNumber
     */
    public Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of proposalPersonNumber
     *
     * @param argProposalPersonNumber Value to assign to this.proposalPersonNumber
     */
    public void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
    }

    /**
     * Gets the value of conflictOfInterest
     *
     * @return the value of conflictOfInterest
     */
    public boolean getConflictOfInterestFlag() {
        return this.conflictOfInterestFlag;
    }


    /**
     * Gets the value of percentageEffort
     *
     * @return the value of percentageEffort
     */
    public KualiDecimal getPercentageEffort() {
          return this.percentageEffort;
    }

    /**
     * Sets the value of percentageEffort
     *
     * @param argPercentageEffort Value to assign to this.percentageEffort
     */
    public void setPercentageEffort(KualiDecimal argPercentageEffort) {
        this.percentageEffort = argPercentageEffort;
    }

    /**
     * Gets the value of fedrDebr
     *
     * @return the value of fedrDebr
     */
    public Boolean getFedrDebrFlag() {
        return this.fedrDebrFlag;
    }

    /**
     * Sets the value of fedrDebr
     *
     * @param argFedrDebr Value to assign to this.fedrDebr
     */
    public void setFedrDebrFlag(Boolean argFedrDebr) {
        this.fedrDebrFlag = argFedrDebr;
    }

    /**
     * Gets the value of fedrDelq
     *
     * @return the value of fedrDelq
     */
    public Boolean getFedrDelqFlag() {
        return this.fedrDelqFlag;
    }

    /**
     * Sets the value of fedrDelq
     *
     * @param argFedrDelq Value to assign to this.fedrDelq
     */
    public void setFedrDelqFlag(Boolean argFedrDelq) {
        this.fedrDelqFlag = argFedrDelq;
    }

    /**
     * Gets the value of rolodexId
     *
     * @return the value of rolodexId
     */
    public Integer getRolodexId() {
        return this.rolodexId;
    }

    /**
     * Sets the value of rolodexId
     *
     * @param argRolodexId Value to assign to this.rolodexId
     */
    public void setRolodexId(Integer argRolodexId) {
        this.rolodexId = argRolodexId;
    }

    /**
     * Gets the value of proposalNumber
     *
     * @return the value of proposalNumber
     */
    public String getProposalNumber() {
        return this.proposalNumber;
    }

    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public void setProposalNumber(String argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }

    /**
     * Gets the value of propPersonRoleId
     *
     * @return the value of propPersonRoleId
     */
    public String getProposalPersonRoleId() {
        return this.proposalPersonRoleId;
    }

    /** 
     * Sets the value of propPersonRoleId
     *
     * @param argPropPersonRoleId Value to assign to this.propPersonRoleId
     */
    public void setProposalPersonRoleId(String argPropPersonRoleId) {
                
        if (isNotBlank(argPropPersonRoleId)) {
            this.proposalPersonRoleId = argPropPersonRoleId;
            refreshReferenceObject("role");
            //setRoleChanged(true);
        }
    }

    /**
     * Gets the value of propPersonRoleId
     *
     * @return the value of propPersonRoleId
     */
    public String getNonNihProposalPersonRoleId() {
        return this.proposalPersonRoleId;
    }

    /** 
     * Sets the value of propPersonRoleId
     *
     * @param argPropPersonRoleId Value to assign to this.propPersonRoleId
     */
    public void setNonNihProposalPersonRoleId(String argPropPersonRoleId) {
        this.proposalPersonRoleId = argPropPersonRoleId;
    }

    /**
     * Gets the value of propPersonRole
     *
     * @return the value of propPersonRole
     */
    public ProposalPersonRole getRole() {
        return role;
    }

    /** 
     * Sets the value of propPersonRole
     *
     * @param argPropPersonRole Value to assign to this.propPersonRole
     */
    public void setRole(ProposalPersonRole argPropPersonRole) {
        this.role = argPropPersonRole;
    }
   
    /**
     * Sets the value of conflictOfInterest
     *
     * @param argConflictOfInterest Value to assign to this.conflictOfInterest
     */
    public void setConflictOfInterestFlag(boolean argConflictOfInterest) {
        this.conflictOfInterestFlag = argConflictOfInterest;
    }

    /**
     * Gets the value of personId
     *
     * @return the value of personId
     */
    public String getPersonId() {
        return this.personId;
    }

    /**
     * Sets the value of personId
     *
     * @param argPersonId Value to assign to this.personId
     */
    public void setPersonId(String argPersonId) {
        this.personId = argPersonId;
    }

    /**
     * Gets the value of person
     *
     * @return the value of person
     */
    public Person getPerson() {
        return this.person;
    }

    /**
     * Sets the value of person
     *
     * @param argPerson Value to assign to this.person
     */
    public void setPerson(Person argPerson) {
        this.person = argPerson;
    }

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
   	    LinkedHashMap hashmap = super.toStringMapper();

        hashmap.put("conflictOfInterest", getConflictOfInterestFlag());
        hashmap.put("percentageEffort", getPercentageEffort());
        hashmap.put("fedrDebr", getFedrDebrFlag());
        hashmap.put("fedrDelq", getFedrDelqFlag());
        hashmap.put("personId", getPersonId());
        hashmap.put("rolodexId", getRolodexId());
        hashmap.put("proposalNumber", getProposalNumber());
        hashmap.put("proposalPersonNumber", getProposalPersonNumber());
        hashmap.put("proposalPersonRoleId", getProposalPersonRoleId());
        
		return hashmap;
	}

    /**
     * Adds a new degree to the collection in the person
     *
     * @param d degree to add
     */
    public void addDegree(ProposalPersonDegree d) {
        getProposalPersonDegrees().add(d);
    }

    /**
     * Gets index i from the degrees list.
     * 
     * @param index
     * @return <code>{@link ProposalPersonDegree}</code> instance at index i
     */
    public ProposalPersonDegree getProposalPersonDegree(int index) {
        while (getProposalPersonDegrees().size() <= index) {
            getProposalPersonDegrees().add(new ProposalPersonDegree());
        }
        
        return getProposalPersonDegrees().get(index);
    }

    /**
     * Adds a new unit to the collection in the person
     *
     * @param unit to add
     */
    public void addUnit(ProposalPersonUnit unit) {
        getUnits().add(unit);
    }

    /**
     * Gets index i from the units list.
     * 
     * @param index
     * @return <code>{@link ProposalPersonUnit}</code> instance at index i
     */
    public ProposalPersonUnit getUnit(int index) {
        while (getUnits().size() <= index) {
            getUnits().add(new ProposalPersonUnit());
        }
        
        return getUnits().get(index);
    }

    /**
     * Gets unit with unitNumber from the units list.
     * 
     * @param unitNumber
     * @return <code>{@link ProposalPersonUnit}</code> instance at index i
     */
    public ProposalPersonUnit getUnit(String unitNumber) {
        if (unitNumber == null) {
            return null;
        }
        
        for (ProposalPersonUnit unit : getUnits()) {
            if (unit != null && unitNumber.equals(unit.getUnitNumber())) {
                return unit;
            }
        }
        
        return null;
    }
    
    /**
     * Read access to a flag that determines if this instance should be deleted from a list of other instances.
     * 
     * @return boolean
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * Write access to a flag that determines if this instance should be deleted from a list of other instances.
     * 
     * @param delete 
     */    
    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    
    public void setUnitDelete(boolean delete){
        this.unitdelete=delete;
    }
    
    public boolean isUnitDelete()
    {
        return unitdelete;
    }
    /**
     * Gets index i from the creditSplits list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalPersonCreditSplit getCreditSplit(int index) {
        while (getCreditSplits().size() <= index) {
            getCreditSplits().add(new ProposalPersonCreditSplit());
        }
        return (ProposalPersonCreditSplit) getCreditSplits().get(index);
    }


    public List<ProposalPersonYnq> getProposalPersonYnqs() {
        return proposalPersonYnqs;
    }

    public void setProposalPersonYnqs(List<ProposalPersonYnq> proposalPersonYnqs) {
        this.proposalPersonYnqs = proposalPersonYnqs;
    }

    
    /**
     * Gets index i from the proposalPersonYnqs list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalPersonYnq getProposalPersonYnq(int index) {
        while (getProposalPersonYnqs().size() <= index) {
            getProposalPersonYnqs().add(new ProposalPersonYnq());
        }
        return (ProposalPersonYnq) getProposalPersonYnqs().get(index);
    }

    /**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        // Assume if obj is a String, then it must represent the PERSON_ID or ROLODEX_ID
        if (obj instanceof String) {
            return (obj.equals(getPersonId()) || obj.equals(getRolodexId()));
        }
       
        if (obj instanceof ProposalPerson) {
            ProposalPerson p = (ProposalPerson) obj;
            return ((getPersonId() != null && getPersonId().equals(p.getPersonId())) 
                    || (getRolodexId() != null && getRolodexId().equals(p.getRolodexId())));
        }
        return false;
    }

    /**
     * Determine if the <code>{@link ProposalPerson}</code> instance role has changed
     * 
     * @return boolean
     */
    public boolean isRoleChanged() {
        return roleChanged;
    }

    /**
     * Trigger a role change
     * 
     * @param roleChanged
     */
    public void setRoleChanged(boolean roleChanged) {
        this.roleChanged = roleChanged;
    }
    
    /**
     * Loops through units to determine if the person has a <code>{@link ProposalPersonUnit}</code> with the given number.
     * 
     * @param unitNumber
     * @return if the unit exists
     */
    public boolean containsUnit(String unitNumber) {
        if (unitNumber == null) {
            return false;
        }
        
        for (ProposalPersonUnit unit : getUnits()) {
            if (unit != null && unitNumber.equals(unit.getUnitNumber())) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Gets the simpleName attribute. <code>simpleName</code> is used for mapping credit split totals by person. They are mapped
     * to the simpleName instead of a fullName because simpleName doesn't have any funny characters. 
     * 
     * @return Returns the simpleName.
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * Sets the simpleName attribute value. <code>simpleName</code> is used for mapping credit split totals by person. They are mapped
     * to the simpleName instead of a fullName because simpleName doesn't have any funny characters.
     * 
     * @param simpleName The simpleName to set.
     */
    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    /**
     * Gets the otherSignificantContributorFlag attribute. 
     * @return Returns the otherSignificantContributorFlag.
     */
    public boolean isOtherSignificantContributorFlag() {
        return otherSignificantContributorFlag;
    }

    /**
     * Sets the otherSignificantContributorFlag attribute value.
     * @param otherSignificantContributorFlag The otherSignificantContributorFlag to set.
     */
    public void setOtherSignificantContributorFlag(boolean otherSignificantContributorFlag) {
        this.otherSignificantContributorFlag = otherSignificantContributorFlag;
    }

    public String getOptInUnitStatus() {
        return optInUnitStatus;
    }

    public void setOptInUnitStatus(String optInUnitStatus) {
        this.optInUnitStatus = optInUnitStatus;
    }

    public String getOptInCertificationStatus() {
        return optInCertificationStatus;
    }

    public void setOptInCertificationStatus(String optInCertificationStatus) {
        this.optInCertificationStatus = optInCertificationStatus;
    }
    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }


}
