/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.PersonEditableInterface;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.Sponsorable;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.rice.krad.bo.PersistableBusinessObject
 * @author $Author: gmcgrego $
 * @version $Revision: 1.42 $
 */
public class ProposalPerson extends KraPersistableBusinessObjectBase implements CreditSplitable, PersonRolodex, PersonEditableInterface, AbstractProjectPerson {

    private static final long serialVersionUID = -4110005875629288373L;

    private DevelopmentProposal developmentProposal;

    private boolean conflictOfInterestFlag;

    private boolean otherSignificantContributorFlag;

    private KualiDecimal percentageEffort;

    private Boolean fedrDebrFlag;

    private Boolean fedrDelqFlag;

    private Integer rolodexId;

    private String proposalNumber;

    private Integer proposalPersonNumber;

    private String proposalPersonRoleId;

    private ProposalInvestigatorCertification certification;

    private ProposalPersonRole role;

    private boolean delete;

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

    private boolean multiplePi;

    private String hierarchyProposalNumber;

    private boolean hiddenInHierarchy;

    private String personId;

    private String socialSecurityNumber;

    private String lastName;

    private String firstName;

    private String middleName;

    private String fullName;

    private String priorName;

    private String userName;

    private String emailAddress;

    private Date dateOfBirth;

    private Integer age;

    private Integer ageByFiscalYear;

    private String gender;

    private String race;

    private String educationLevel;

    private String degree;

    private String major;

    private Boolean handicappedFlag;

    private String handicapType;

    private Boolean veteranFlag;

    private String veteranType;

    private String visaCode;

    private String visaType;

    private Date visaRenewalDate;

    private Boolean hasVisa;

    private String officeLocation;

    private String officePhone;

    private String secondaryOfficeLocation;

    private String secondaryOfficePhone;

    private String school;

    private String yearGraduated;

    private String directoryDepartment;

    private String saluation;

    private String countryOfCitizenship;

    private String primaryTitle;

    private String directoryTitle;

    private String homeUnit;

    private Boolean facultyFlag;

    private Boolean graduateStudentStaffFlag;

    private Boolean researchStaffFlag;

    private Boolean serviceStaffFlag;

    private Boolean supportStaffFlag;

    private Boolean otherAcademicGroupFlag;

    private Boolean medicalStaffFlag;

    private Boolean vacationAccrualFlag;

    private Boolean onSabbaticalFlag;

    private String idProvided;

    private String idVerified;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String city;

    private String county;

    private String state;

    private String postalCode;

    private String countryCode;

    private String faxNumber;

    private String pagerNumber;

    private String mobilePhoneNumber;

    private String eraCommonsUserName;

    private Boolean active = true;

    private Unit homeUnitRef;

    private ProposalPersonExtendedAttributes proposalPersonExtendedAttributes;

    private transient boolean moveDownAllowed;

    private transient boolean moveUpAllowed;

    private transient KcPersonService kcPersonService;

    /**
     * This list is not automatically populated by the ORM by design.
     * Call ProposalDevelopmentPersonQuestionnaireService.setAnswerHeaders() to set this list.
     */
    //private List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>(); 
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
     * set the <code>simpleName</code> & the full name.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
        setSimpleName(getFullName());
        setSimpleName(getSimpleName().toLowerCase());
        setSimpleName(StringUtils.deleteWhitespace(getSimpleName()));
        setSimpleName(StringUtils.remove(getSimpleName(), '.'));
    }

    /**
     * gets the full name.
     */
    @CreditSplitNameInfo
    public String getFullName() {
        return this.fullName;
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
     * 
     * This method returns the concatation of proposalNumber + "|" + proposalPersonNumber.
     * Those two fields are the combined primary key on the table.
     * @return
     */
    public String getUniqueId() {
        return this.getProposalNumber() + "|" + this.getProposalPersonNumber();
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
        if (StringUtils.isNotBlank(argPropPersonRoleId)) {
            this.proposalPersonRoleId = argPropPersonRoleId;
            refreshReferenceObject("role");
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
     * Gets the value of person, or null if no personId is defined (this is the case for non-employees).
     *
     * @return the value of person
     */
    public KcPerson getPerson() {
        if (this.personId == null) {
            return null;
        } else {
            return getKcPersonService().getKcPersonByPersonId(this.personId);
        }
    }

    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
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

    public void setUnitDelete(boolean delete) {
        this.unitdelete = delete;
    }

    public boolean isUnitDelete() {
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
        return getCreditSplits().get(index);
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
        return getProposalPersonYnqs().get(index);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        result = prime * result + ((rolodexId == null) ? 0 : rolodexId.hashCode());
        return result;
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
        // Assume if obj is a String, then it must represent the PERSON_ID or ROLODEX_ID 
        if (obj instanceof String) {
            return (obj.equals(getPersonId()) || obj.equals(getRolodexId()));
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProposalPerson other = (ProposalPerson) obj;
        if (personId == null) {
            if (other.personId != null) {
                return false;
            }
        } else if (!personId.equals(other.personId)) {
            return false;
        }
        if (rolodexId == null) {
            if (other.rolodexId != null) {
                return false;
            }
        } else if (!rolodexId.equals(other.rolodexId)) {
            return false;
        }
        return true;
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

    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
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
     * Sets the value of socialSecurityNumber
     *
     * @param argSocialSecurityNumber Value to assign to this.socialSecurityNumber
     */
    public void setSocialSecurityNumber(String argSocialSecurityNumber) {
        this.socialSecurityNumber = argSocialSecurityNumber;
    }

    /**
     * Gets the value of socialSecurityNumber
     *
     * @return the value of socialSecurityNumber
     */
    public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    /**
     * Gets the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the value of lastName
     *
     * @param argLastName Value to assign to this.lastName
     */
    public void setLastName(String argLastName) {
        this.lastName = argLastName;
    }

    /**
     * Gets the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the value of firstName
     *
     * @param argFirstName Value to assign to this.firstName
     */
    public void setFirstName(String argFirstName) {
        this.firstName = argFirstName;
    }

    /**
     * Gets the value of middleName
     *
     * @return the value of middleName
     */
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * Sets the value of middleName
     *
     * @param argMiddleName Value to assign to this.middleName
     */
    public void setMiddleName(String argMiddleName) {
        this.middleName = argMiddleName;
    }

    /**
     * Gets the value of priorName
     *
     * @return the value of priorName
     */
    public String getPriorName() {
        return this.priorName;
    }

    /**
     * Sets the value of priorName
     *
     * @param argPriorName Value to assign to this.priorName
     */
    public void setPriorName(String argPriorName) {
        this.priorName = argPriorName;
    }

    /**
     * Gets the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the value of userName
     *
     * @param argUserName Value to assign to this.userName
     */
    public void setUserName(String argUserName) {
        this.userName = argUserName;
    }

    /**
     * Gets the value of emailAddress
     *
     * @return the value of emailAddress
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Sets the value of emailAddress
     *
     * @param argEmailAddress Value to assign to this.emailAddress
     */
    public void setEmailAddress(String argEmailAddress) {
        this.emailAddress = argEmailAddress;
    }

    /**
     * Gets the value of dateOfBirth
     *
     * @return the value of dateOfBirth
     */
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Sets the value of dateOfBirth
     *
     * @param argDateOfBirth Value to assign to this.dateOfBirth
     */
    public void setDateOfBirth(Date argDateOfBirth) {
        this.dateOfBirth = argDateOfBirth;
    }

    /**
     * Gets the value of age
     *
     * @return the value of age
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * Sets the value of age
     *
     * @param argAge Value to assign to this.age
     */
    public void setAge(Integer argAge) {
        this.age = argAge;
    }

    /**
     * Gets the value of ageByFiscalYear
     *
     * @return the value of ageByFiscalYear
     */
    public Integer getAgeByFiscalYear() {
        return this.ageByFiscalYear;
    }

    /**
     * Sets the value of ageByFiscalYear
     *
     * @param argAgeByFiscalYear Value to assign to this.ageByFiscalYear
     */
    public void setAgeByFiscalYear(Integer argAgeByFiscalYear) {
        this.ageByFiscalYear = argAgeByFiscalYear;
    }

    /**
     * Gets the value of gender
     *
     * @return the value of gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Sets the value of gender
     *
     * @param argGender Value to assign to this.gender
     */
    public void setGender(String argGender) {
        this.gender = argGender;
    }

    /**
     * Gets the value of race
     *
     * @return the value of race
     */
    public String getRace() {
        return this.race;
    }

    /**
     * Sets the value of race
     *
     * @param argRace Value to assign to this.race
     */
    public void setRace(String argRace) {
        this.race = argRace;
    }

    /**
     * Gets the value of educationLevel
     *
     * @return the value of educationLevel
     */
    public String getEducationLevel() {
        return this.educationLevel;
    }

    /**
     * Sets the value of educationLevel
     *
     * @param argEducationLevel Value to assign to this.educationLevel
     */
    public void setEducationLevel(String argEducationLevel) {
        this.educationLevel = argEducationLevel;
    }

    /**
     * Gets the value of degree
     *
     * @return the value of degree
     */
    public String getDegree() {
        return this.degree;
    }

    /**
     * Sets the value of degree
     *
     * @param argDegree Value to assign to this.degree
     */
    public void setDegree(String argDegree) {
        this.degree = argDegree;
    }

    /**
     * Gets the value of major
     *
     * @return the value of major
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * Sets the value of major
     *
     * @param argMajor Value to assign to this.major
     */
    public void setMajor(String argMajor) {
        this.major = argMajor;
    }

    /**
     * Gets the value of handicapped
     *
     * @return the value of handicapped
     */
    public Boolean getHandicappedFlag() {
        return this.handicappedFlag;
    }

    /**
     * Sets the value of handicapped
     *
     * @param argHandicapped Value to assign to this.handicapped
     */
    public void setHandicappedFlag(Boolean argHandicapped) {
        this.handicappedFlag = argHandicapped;
    }

    /**
     * Gets the value of handicapType
     *
     * @return the value of handicapType
     */
    public String getHandicapType() {
        return this.handicapType;
    }

    /**
     * Sets the value of handicapType
     *
     * @param argHandicapType Value to assign to this.handicapType
     */
    public void setHandicapType(String argHandicapType) {
        this.handicapType = argHandicapType;
    }

    /**
     * Gets the value of veteran
     *
     * @return the value of veteran
     */
    public Boolean getVeteranFlag() {
        return this.veteranFlag;
    }

    /**
     * Sets the value of veteran
     *
     * @param argVeteran Value to assign to this.veteran
     */
    public void setVeteranFlag(Boolean argVeteran) {
        this.veteranFlag = argVeteran;
    }

    /**
     * Gets the value of veteranType
     *
     * @return the value of veteranType
     */
    public String getVeteranType() {
        return this.veteranType;
    }

    /**
     * Sets the value of veteranType
     *
     * @param argVeteranType Value to assign to this.veteranType
     */
    public void setVeteranType(String argVeteranType) {
        this.veteranType = argVeteranType;
    }

    /**
     * Gets the value of visaCode
     *
     * @return the value of visaCode
     */
    public String getVisaCode() {
        return this.visaCode;
    }

    /**
     * Sets the value of visaCode
     *
     * @param argVisaCode Value to assign to this.visaCode
     */
    public void setVisaCode(String argVisaCode) {
        this.visaCode = argVisaCode;
    }

    /**
     * Gets the value of visaType
     *
     * @return the value of visaType
     */
    public String getVisaType() {
        return this.visaType;
    }

    /**
     * Sets the value of visaType
     *
     * @param argVisaType Value to assign to this.visaType
     */
    public void setVisaType(String argVisaType) {
        this.visaType = argVisaType;
    }

    /**
     * Gets the value of visaRenewalDate
     *
     * @return the value of visaRenewalDate
     */
    public Date getVisaRenewalDate() {
        return this.visaRenewalDate;
    }

    /**
     * Sets the value of visaRenewalDate
     *
     * @param argVisaRenewalDate Value to assign to this.visaRenewalDate
     */
    public void setVisaRenewalDate(Date argVisaRenewalDate) {
        this.visaRenewalDate = argVisaRenewalDate;
    }

    /**
     * Gets the value of hasVisa
     *
     * @return the value of hasVisa
     */
    public Boolean getHasVisa() {
        return this.hasVisa;
    }

    /**
     * Sets the value of hasVisa
     *
     * @param argHasVisa Value to assign to this.hasVisa
     */
    public void setHasVisa(Boolean argHasVisa) {
        this.hasVisa = argHasVisa;
    }

    /**
     * Gets the value of officeLocation
     *
     * @return the value of officeLocation
     */
    public String getOfficeLocation() {
        return this.officeLocation;
    }

    /**
     * Sets the value of officeLocation
     *
     * @param argOfficeLocation Value to assign to this.officeLocation
     */
    public void setOfficeLocation(String argOfficeLocation) {
        this.officeLocation = argOfficeLocation;
    }

    /**
     * Gets the value of officePhone
     *
     * @return the value of officePhone
     */
    public String getOfficePhone() {
        return this.officePhone;
    }

    /**
     * Sets the value of officePhone
     *
     * @param argOfficePhone Value to assign to this.officePhone
     */
    public void setOfficePhone(String argOfficePhone) {
        this.officePhone = argOfficePhone;
    }

    /**
     * Gets the value of secondaryOfficeLocation
     *
     * @return the value of secondaryOfficeLocation
     */
    public String getSecondaryOfficeLocation() {
        return this.secondaryOfficeLocation;
    }

    /**
     * Sets the value of secondaryOfficeLocation
     *
     * @param argSecondaryOfficeLocation Value to assign to this.secondaryOfficeLocation
     */
    public void setSecondaryOfficeLocation(String argSecondaryOfficeLocation) {
        this.secondaryOfficeLocation = argSecondaryOfficeLocation;
    }

    /**
     * Gets the value of secondaryOfficePhone
     *
     * @return the value of secondaryOfficePhone
     */
    public String getSecondaryOfficePhone() {
        return this.secondaryOfficePhone;
    }

    /**
     * Sets the value of secondaryOfficePhone
     *
     * @param argSecondaryOfficePhone Value to assign to this.secondaryOfficePhone
     */
    public void setSecondaryOfficePhone(String argSecondaryOfficePhone) {
        this.secondaryOfficePhone = argSecondaryOfficePhone;
    }

    /**
     * Gets the value of school
     *
     * @return the value of school
     */
    public String getSchool() {
        return this.school;
    }

    /**
     * Sets the value of school
     *
     * @param argSchool Value to assign to this.school
     */
    public void setSchool(String argSchool) {
        this.school = argSchool;
    }

    /**
     * Gets the value of yearGraduated
     * 
     * @return the value of yearGraduated
     */
    public String getYearGraduated() {
        return this.yearGraduated;
    }

    /**
     * Sets the value of yearGraduated
     *
     * @param argYearGraduated Value to assign to this.yearGraduated
     */
    public void setYearGraduated(String argYearGraduated) {
        this.yearGraduated = argYearGraduated;
    }

    /**
     * Gets the value of directoryDepartment
     *
     * @return the value of directoryDepartment
     */
    public String getDirectoryDepartment() {
        return this.directoryDepartment;
    }

    /**
     * Sets the value of directoryDepartment
     *
     * @param argDirectoryDepartment Value to assign to this.directoryDepartment
     */
    public void setDirectoryDepartment(String argDirectoryDepartment) {
        this.directoryDepartment = argDirectoryDepartment;
    }

    /**
     * Gets the value of saluation
     *
     * @return the value of saluation
     */
    public String getSaluation() {
        return this.saluation;
    }

    /**
     * Sets the value of saluation
     *
     * @param argSaluation Value to assign to this.saluation
     */
    public void setSaluation(String argSaluation) {
        this.saluation = argSaluation;
    }

    /**
     * Gets the value of countryOfCitizenship
     *
     * @return the value of countryOfCitizenship
     */
    public String getCountryOfCitizenship() {
        return this.countryOfCitizenship;
    }

    /**
     * Sets the value of countryOfCitizenship
     *
     * @param argCountryOfCitizenship Value to assign to this.countryOfCitizenship
     */
    public void setCountryOfCitizenship(String argCountryOfCitizenship) {
        this.countryOfCitizenship = argCountryOfCitizenship;
    }

    /**
     * Gets the value of primaryTitle
     *
     * @return the value of primaryTitle
     */
    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    /**
     * Sets the value of primaryTitle
     *
     * @param argPrimaryTitle Value to assign to this.primaryTitle
     */
    public void setPrimaryTitle(String argPrimaryTitle) {
        this.primaryTitle = argPrimaryTitle;
    }

    /**
     * Gets the value of directoryTitle
     *
     * @return the value of directoryTitle
     */
    public String getDirectoryTitle() {
        return this.directoryTitle;
    }

    /**
     * Sets the value of directoryTitle
     *
     * @param argDirectoryTitle Value to assign to this.directoryTitle
     */
    public void setDirectoryTitle(String argDirectoryTitle) {
        this.directoryTitle = argDirectoryTitle;
    }

    /**
     * Gets the value of homeUnit
     *
     * @return the value of homeUnit
     */
    public String getHomeUnit() {
        return this.homeUnit;
    }

    /**
     * Sets the value of homeUnit
     *
     * @param argHomeUnit Value to assign to this.homeUnit
     */
    public void setHomeUnit(String argHomeUnit) {
        this.homeUnit = argHomeUnit;
    }

    /**
     * Gets the value of faculty
     *
     * @return the value of faculty
     */
    public Boolean getFacultyFlag() {
        return this.facultyFlag;
    }

    /**
     * Sets the value of faculty
     *
     * @param argFaculty Value to assign to this.faculty
     */
    public void setFacultyFlag(Boolean argFaculty) {
        this.facultyFlag = argFaculty;
    }

    /**
     * Gets the value of graduateStudentStaff
     *
     * @return the value of graduateStudentStaff
     */
    public Boolean getGraduateStudentStaffFlag() {
        return this.graduateStudentStaffFlag;
    }

    /**
     * Sets the value of graduateStudentStaff
     *
     * @param argGraduateStudentStaff Value to assign to this.graduateStudentStaff
     */
    public void setGraduateStudentStaffFlag(Boolean argGraduateStudentStaff) {
        this.graduateStudentStaffFlag = argGraduateStudentStaff;
    }

    /**
     * Gets the value of researchStaff
     *
     * @return the value of researchStaff
     */
    public Boolean getResearchStaffFlag() {
        return this.researchStaffFlag;
    }

    /**
     * Sets the value of researchStaff
     *
     * @param argResearchStaff Value to assign to this.researchStaff
     */
    public void setResearchStaffFlag(Boolean argResearchStaff) {
        this.researchStaffFlag = argResearchStaff;
    }

    /**
     * Gets the value of serviceStaff
     *
     * @return the value of serviceStaff
     */
    public Boolean getServiceStaffFlag() {
        return this.serviceStaffFlag;
    }

    /**
     * Sets the value of serviceStaff
     *
     * @param argServiceStaff Value to assign to this.serviceStaff
     */
    public void setServiceStaffFlag(Boolean argServiceStaff) {
        this.serviceStaffFlag = argServiceStaff;
    }

    /**
     * Gets the value of supportStaff
     *
     * @return the value of supportStaff
     */
    public Boolean getSupportStaffFlag() {
        return this.supportStaffFlag;
    }

    /**
     * Sets the value of supportStaff
     *
     * @param argSupportStaff Value to assign to this.supportStaff
     */
    public void setSupportStaffFlag(Boolean argSupportStaff) {
        this.supportStaffFlag = argSupportStaff;
    }

    /**
     * Gets the value of otherAcademicGroup
     *
     * @return the value of otherAcademicGroup
     */
    public Boolean getOtherAcademicGroupFlag() {
        return this.otherAcademicGroupFlag;
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getOrganization()
     */
    public String getOrganization() {
        return getUnit().getUnitName();
    }

    /**
     * Sets the value of otherAcademicGroup
     *
     * @param argOtherAcademicGroup Value to assign to this.otherAcademicGroup
     */
    public void setOtherAcademicGroupFlag(Boolean argOtherAcademicGroup) {
        this.otherAcademicGroupFlag = argOtherAcademicGroup;
    }

    /**
     * Gets the value of medicalStaff
     *
     * @return the value of medicalStaff
     */
    public Boolean getMedicalStaffFlag() {
        return this.medicalStaffFlag;
    }

    /**
     * Sets the value of medicalStaff
     *
     * @param argMedicalStaff Value to assign to this.medicalStaff
     */
    public void setMedicalStaffFlag(Boolean argMedicalStaff) {
        this.medicalStaffFlag = argMedicalStaff;
    }

    /**
     * Gets the value of vacationAccrual
     *
     * @return the value of vacationAccrual
     */
    public Boolean getVacationAccrualFlag() {
        return this.vacationAccrualFlag;
    }

    /**
     * Sets the value of vacationAccrual
     *
     * @param argVacationAccrual Value to assign to this.vacationAccrual
     */
    public void setVacationAccrualFlag(Boolean argVacationAccrual) {
        this.vacationAccrualFlag = argVacationAccrual;
    }

    /**
     * Gets the value of onSabbatical
     *
     * @return the value of onSabbatical
     */
    public Boolean getOnSabbaticalFlag() {
        return this.onSabbaticalFlag;
    }

    /**
     * Sets the value of onSabbatical
     *
     * @param argOnSabbatical Value to assign to this.onSabbatical
     */
    public void setOnSabbaticalFlag(Boolean argOnSabbatical) {
        this.onSabbaticalFlag = argOnSabbatical;
    }

    /**
     * Gets the value of idProvided
     *
     * @return the value of idProvided
     */
    public String getIdProvided() {
        return this.idProvided;
    }

    /**
     * Sets the value of idProvided
     *
     * @param argIdProvided Value to assign to this.idProvided
     */
    public void setIdProvided(String argIdProvided) {
        this.idProvided = argIdProvided;
    }

    /**
     * Gets the value of idVerified
     *
     * @return the value of idVerified
     */
    public String getIdVerified() {
        return this.idVerified;
    }

    /**
     * Sets the value of idVerified
     *
     * @param argIdVerified Value to assign to this.idVerified
     */
    public void setIdVerified(String argIdVerified) {
        this.idVerified = argIdVerified;
    }

    /**
     * Gets the value of addressLine1
     *
     * @return the value of addressLine1
     */
    public String getAddressLine1() {
        return this.addressLine1;
    }

    /**
     * Sets the value of addressLine1
     *
     * @param argAddressLine1 Value to assign to this.addressLine1
     */
    public void setAddressLine1(String argAddressLine1) {
        this.addressLine1 = argAddressLine1;
    }

    /**
     * Gets the value of addressLine2
     *
     * @return the value of addressLine2
     */
    public String getAddressLine2() {
        return this.addressLine2;
    }

    /**
     * Sets the value of addressLine2
     *
     * @param argAddressLine2 Value to assign to this.addressLine2
     */
    public void setAddressLine2(String argAddressLine2) {
        this.addressLine2 = argAddressLine2;
    }

    /**
     * Gets the value of addressLine3
     *
     * @return the value of addressLine3
     */
    public String getAddressLine3() {
        return this.addressLine3;
    }

    /**
     * Sets the value of addressLine3
     *
     * @param argAddressLine3 Value to assign to this.addressLine3
     */
    public void setAddressLine3(String argAddressLine3) {
        this.addressLine3 = argAddressLine3;
    }

    /**
     * Gets the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the value of city
     *
     * @param argCity Value to assign to this.city
     */
    public void setCity(String argCity) {
        this.city = argCity;
    }

    /**
     * Gets the value of county
     *
     * @return the value of county
     */
    public String getCounty() {
        return this.county;
    }

    /**
     * Sets the value of county
     *
     * @param argCounty Value to assign to this.county
     */
    public void setCounty(String argCounty) {
        this.county = argCounty;
    }

    /**
     * Gets the value of state
     *
     * @return the value of state
     */
    public String getState() {
        return this.state;
    }

    /**
     * Sets the value of state
     *
     * @param argState Value to assign to this.state
     */
    public void setState(String argState) {
        this.state = argState;
    }

    /**
     * Gets the value of postalCode
     *
     * @return the value of postalCode
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * Sets the value of postalCode
     *
     * @param argPostalCode Value to assign to this.postalCode
     */
    public void setPostalCode(String argPostalCode) {
        this.postalCode = argPostalCode;
    }

    /**
     * Gets the value of countryCode
     *
     * @return the value of countryCode
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Sets the value of countryCode
     *
     * @param argCountryCode Value to assign to this.countryCode
     */
    public void setCountryCode(String argCountryCode) {
        this.countryCode = argCountryCode;
    }

    /**
     * Gets the value of faxNumber
     *
     * @return the value of faxNumber
     */
    public String getFaxNumber() {
        return this.faxNumber;
    }

    /**
     * Sets the value of faxNumber
     *
     * @param argFaxNumber Value to assign to this.faxNumber
     */
    public void setFaxNumber(String argFaxNumber) {
        this.faxNumber = argFaxNumber;
    }

    /**
     * Gets the value of pagerNumber
     *
     * @return the value of pagerNumber
     */
    public String getPagerNumber() {
        return this.pagerNumber;
    }

    /**
     * Sets the value of pagerNumber
     *
     * @param argPagerNumber Value to assign to this.pagerNumber
     */
    public void setPagerNumber(String argPagerNumber) {
        this.pagerNumber = argPagerNumber;
    }

    /**
     * Gets the value of mobilePhoneNumber
     *
     * @return the value of mobilePhoneNumber
     */
    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    /**
     * Sets the value of mobilePhoneNumber
     *
     * @param argMobilePhoneNumber Value to assign to this.mobilePhoneNumber
     */
    public void setMobilePhoneNumber(String argMobilePhoneNumber) {
        this.mobilePhoneNumber = argMobilePhoneNumber;
    }

    /**
     * Gets the value of eraCommonsUserName
     *
     * @return the value of eraCommonsUserName
     */
    public String getEraCommonsUserName() {
        return this.eraCommonsUserName;
    }

    /**
     * Sets the value of eraCommonsUserName
     *
     * @param argEraCommonsUserName Value to assign to this.eraCommonsUserName
     */
    public void setEraCommonsUserName(String argEraCommonsUserName) {
        this.eraCommonsUserName = argEraCommonsUserName;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    /**
     * Unit reference referred by {@link #getUnit()}
     *
     * @param unit 
     */
    public void setHomeUnitRef(Unit unit) {
        this.homeUnitRef = unit;
    }

    /**
     * Unit reference referred by {@link #getUnit()}
     *
     * @return unit 
     */
    public Unit getHomeUnitRef() {
        return homeUnitRef;
    }

    public String getPassword() {
        final KcPerson person = this.getPerson();
        return person != null ? person.getPassword() : null;
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getIdentifier()
     */
    public Serializable getIdentifier() {
        return personId;
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getUnit()
     */
    public Unit getUnit() {
        return homeUnitRef;
    }

    /**
     * @see org.kuali.kra.bo.Contactable#setIdentifier(java.io.Serializable)
     */
    public void setIdentifier(Serializable identifier) {
        setPersonId((String) identifier);
    }

    /**
     * @see org.kuali.kra.bo.Contactable#setUnit(org.kuali.kra.bo.Unit)
     */
    public void setUnit(Unit unit) {
        setHomeUnitRef(unit);
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getPhoneNumber()
     */
    public String getPhoneNumber() {
        return officePhone;
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getContactOrganizationName()
     */
    public String getContactOrganizationName() {
        return getUnit().getUnitName();
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getOrganizationIdentifier()
     */
    public String getOrganizationIdentifier() {
        return homeUnit;
    }

    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    /**
     * Gets the hiddenInHierarchy attribute. 
     * @return Returns the hiddenInHierarchy.
     */
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    /**
     * Sets the hiddenInHierarchy attribute value.
     * @param hiddenInHierarchy The hiddenInHierarchy to set.
     */
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    public ContactRole getContactRole() {
        return getRole();
    }

    public boolean isMultiplePi() {
        return multiplePi;
    }

    public void setMultiplePi(boolean multiplePi) {
        this.multiplePi = multiplePi;
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }

    public Sponsorable getParent() {
        return getDevelopmentProposal();
    }

    public String getInvestigatorRoleDescription() {
        return KraServiceLocator.getService(KeyPersonnelService.class).getPersonnelRoleDesc(this);
    }

    public ProposalPersonExtendedAttributes getProposalPersonExtendedAttributes() {
        return this.proposalPersonExtendedAttributes;
    }

    public void setProposalPersonExtendedAttributes(ProposalPersonExtendedAttributes proposalPersonExtendedAttributes) {
        this.proposalPersonExtendedAttributes = proposalPersonExtendedAttributes;
    }

    /**
     * 
     * This method determines if any of this person's YNQs have been answered.  If so, return yes.
     * Otherwise return no.
     * @return
     */
    public boolean getAnyYNQsAnswered() {
        for (ProposalPersonYnq ynq : this.getProposalPersonYnqs()) {
            if (!StringUtils.isEmpty(ynq.getAnswer())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRoleCode() {
        return this.getRole().getRoleCode();
    }
}
