/*
 * Copyright 2006-2008 The Kuali Foundation
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

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
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
@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalPersonId.class)
@Entity
@Table(name="EPS_PROP_PERSON")
public class ProposalPerson extends KraPersistableBusinessObjectBase implements CreditSplitable {
//public class ProposalPerson extends Person implements CreditSplitable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4110005875629288373L;

    @Column(name="CONFLICT_OF_INTEREST_FLAG")
	private boolean conflictOfInterestFlag;
    
    @Column(name="IS_OSC")
	private boolean otherSignificantContributorFlag;
    
    @Column(name="PERCENTAGE_EFFORT")
	private KualiDecimal percentageEffort;
    
    @Column(name="FEDR_DEBR_FLAG")
	private Boolean fedrDebrFlag;
    
    @Column(name="FEDR_DELQ_FLAG")
	private Boolean fedrDelqFlag;
    
    @Column(name="ROLODEX_ID")
	private Integer rolodexId;
    
    @Column(name="PERSON_ID")
	private String  personId;
    
    @Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
    @Id
	@Column(name="PROP_PERSON_NUMBER")
	private Integer proposalPersonNumber;
    
    @Column(name="PROP_PERSON_ROLE_ID")
	private String  proposalPersonRoleId;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable=false, updatable=false), @JoinColumn(name="PROP_PERSON_NUMBER", insertable=false, updatable=false)})
	private ProposalInvestigatorCertification certification;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="PROP_PERSON_ROLE_ID", insertable=false, updatable=false)
	private ProposalPersonRole role;
    
    @Transient
    private boolean delete;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="PERSON_ID", insertable=false, updatable=false)
	private Person person;
    
    @Transient
    private boolean isInvestigator;
    
    @Transient
    private boolean roleChanged;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq.class, mappedBy="proposalPerson")
	private List<ProposalPersonYnq> proposalPersonYnqs;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit.class, mappedBy="proposalPerson")
	private List<ProposalPersonUnit> units;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree.class, mappedBy="proposalPerson")
	private List<ProposalPersonDegree> proposalPersonDegrees;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit.class, mappedBy="proposalPerson")
	private List<ProposalPersonCreditSplit> creditSplits;
    
    @Transient
    private String simpleName;
    
    @Column(name="OPT_IN_UNIT_STATUS")
	private String optInUnitStatus;
    
    @Column(name="OPT_IN_CERTIFICATION_STATUS")
	private String optInCertificationStatus;
    
    @Transient
    private boolean unitdelete;
    
    @Column(name="PROJECT_ROLE")
	private String projectRole;
    
    @Column(name="ORDINAL_POSITION")
	private Integer ordinalPosition;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private ProposalDevelopmentDocument proposalDevelopmentDocument;
    
    
    
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="MIDDLE_NAME")
    private String middleName;
    @Column(name="FULL_NAME")
    private String fullName;
    @Column(name="PRIOR_NAME")
    private String priorName;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name="DATE_OF_BIRTH")
    private String dateOfBirth;
    @Column(name="AGE")
    private Integer age;
    @Column(name="AGE_BY_FISCAL_YEAR")
    private Integer ageByFiscalYear;
    @Column(name="GENDER")
    private String gender;
    @Column(name="RACE")
    private String race;
    @Column(name="EDUCATION_LEVEL")
    private String educationLevel;
    @Column(name="DEGREE")
    private String degree;
    @Column(name="MAJOR")
    private String major;
    @Column(name="IS_HANDICAPPED")
    private Boolean handicappedFlag;
    @Column(name="HANDICAP_TYPE")
    private String handicapType;
    @Column(name="IS_VETERAN")
    private Boolean veteranFlag;
    @Column(name="VETERAN_TYPE")
    private String veteranType;
    @Column(name="VISA_TYPE")
    private String visaType;
    @Column(name="VISA_RENEWAL_DATE")
    private String visaRenewalDate;
    @Column(name="HAS_VISA")
    private Boolean hasVisa;
    @Column(name="OFFICE_LOCATION")
    private String officeLocation;
    @Column(name="OFFICE_PHONE")
    private String officePhone;
    @Column(name="SECONDRY_OFFICE_LOCATION")
    private String secondaryOfficeLocation;
    @Column(name="SECONDRY_OFFICE_PHONE")
    private String secondaryOfficePhone;
    @Column(name="SCHOOL")
    private String school;
    @Column(name="YEAR_GRADUATED")
    private String yearGraduated;
    @Column(name="DIRECTORY_DEPARTMENT")
    private String directoryDepartment;
    @Column(name="SALUTATION")
    private String saluation;
    @Column(name="COUNTRY_OF_CITIZENSHIP")
    private String countryOfCitizenship;
    @Column(name="PRIMARY_TITLE")
    private String primaryTitle;
    @Column(name="DIRECTORY_TITLE")
    private String directoryTitle;
    @Column(name="HOME_UNIT")
    private String homeUnit;
    @Column(name="IS_FACULTY")
    private Boolean facultyFlag;
    @Column(name="IS_GRADUATE_STUDENT_STAFF")
    private Boolean graduateStudentStaffFlag;
    @Column(name="IS_RESEARCH_STAFF")
    private Boolean researchStaffFlag;
    @Column(name="IS_SERVICE_STAFF")
    private Boolean serviceStaffFlag;
    @Column(name="IS_SUPPORT_STAFF")
    private Boolean supportStaffFlag;
    @Column(name="IS_OTHER_ACCADEMIC_GROUP")
    private Boolean otherAcademicGroupFlag;
    @Column(name="IS_MEDICAL_STAFF")
    private Boolean medicalStaffFlag;
    @Column(name="VACATION_ACCURAL")
    private Boolean vacationAccrualFlag;
    @Column(name="IS_ON_SABBATICAL")
    private Boolean onSabbaticalFlag;
    @Column(name="ID_PROVIDED")
    private String idProvided;
    @Column(name="ID_VERIFIED")
    private String idVerified;
    @Column(name="ADDRESS_LINE_1")
    private String addressLine1;
    @Column(name="ADDRESS_LINE_2")
    private String addressLine2;
    @Column(name="ADDRESS_LINE_3")
    private String addressLine3;
    @Column(name="CITY")
    private String city;
    @Column(name="COUNTY")
    private String county;
    @Column(name="STATE")
    private String state;
    @Column(name="POSTAL_CODE")
    private String postalCode;
    @Column(name="COUNTRY_CODE")
    private String countryCode;
    @Column(name="FAX_NUMBER")
    private String faxNumber;
    @Column(name="PAGER_NUMBER")
    private String pagerNumber;
    @Column(name="MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;
    @Column(name="ERA_COMMONS_USER_NAME")
    private String eraCommonsUserName;
    @Column(name="VISA_CODE")
    private String visaCode;
    @Column(name="SSN")
    private String socialSecurityNumber;

    
    
    
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
        this.fullName = fullName;
        
        setSimpleName(new String(getFullName()));
        
        setSimpleName(getSimpleName().toLowerCase());
        setSimpleName(StringUtils.deleteWhitespace(getSimpleName()));
        setSimpleName(StringUtils.remove(getSimpleName(), '.'));
    }
   
    /**
     * @see org.kuali.kra.bo.Person#getFullName()
     */
    //@Override
    @CreditSplitNameInfo
    public String getFullName() {
        return fullName;
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
   	    LinkedHashMap hashmap = new LinkedHashMap();

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
    
    public void setUnitDelete(boolean delete) {
        this.unitdelete=delete;
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

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }

    public boolean isUnitdelete() {
        return unitdelete;
    }

    public void setUnitdelete(boolean unitdelete) {
        this.unitdelete = unitdelete;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPriorName() {
        return priorName;
    }

    public void setPriorName(String priorName) {
        this.priorName = priorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeByFiscalYear() {
        return ageByFiscalYear;
    }

    public void setAgeByFiscalYear(Integer ageByFiscalYear) {
        this.ageByFiscalYear = ageByFiscalYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Boolean getHandicappedFlag() {
        return handicappedFlag;
    }

    public void setHandicappedFlag(Boolean handicappedFlag) {
        this.handicappedFlag = handicappedFlag;
    }

    public String getHandicapType() {
        return handicapType;
    }

    public void setHandicapType(String handicapType) {
        this.handicapType = handicapType;
    }

    public Boolean getVeteranFlag() {
        return veteranFlag;
    }

    public void setVeteranFlag(Boolean veteranFlag) {
        this.veteranFlag = veteranFlag;
    }

    public String getVeteranType() {
        return veteranType;
    }

    public void setVeteranType(String veteranType) {
        this.veteranType = veteranType;
    }

    public void setInvestigator(boolean isInvestigator) {
        this.isInvestigator = isInvestigator;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getVisaRenewalDate() {
        return visaRenewalDate;
    }

    public void setVisaRenewalDate(String visaRenewalDate) {
        this.visaRenewalDate = visaRenewalDate;
    }

    public Boolean getHasVisa() {
        return hasVisa;
    }

    public void setHasVisa(Boolean hasVisa) {
        this.hasVisa = hasVisa;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getSecondaryOfficeLocation() {
        return secondaryOfficeLocation;
    }

    public void setSecondaryOfficeLocation(String secondaryOfficeLocation) {
        this.secondaryOfficeLocation = secondaryOfficeLocation;
    }

    public String getSecondaryOfficePhone() {
        return secondaryOfficePhone;
    }

    public void setSecondaryOfficePhone(String secondaryOfficePhone) {
        this.secondaryOfficePhone = secondaryOfficePhone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getYearGraduated() {
        return yearGraduated;
    }

    public void setYearGraduated(String yearGraduated) {
        this.yearGraduated = yearGraduated;
    }

    public String getDirectoryDepartment() {
        return directoryDepartment;
    }

    public void setDirectoryDepartment(String directoryDepartment) {
        this.directoryDepartment = directoryDepartment;
    }

    public String getSaluation() {
        return saluation;
    }

    public void setSaluation(String saluation) {
        this.saluation = saluation;
    }

    public String getCountryOfCitizenship() {
        return countryOfCitizenship;
    }

    public void setCountryOfCitizenship(String countryOfCitizenship) {
        this.countryOfCitizenship = countryOfCitizenship;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getDirectoryTitle() {
        return directoryTitle;
    }

    public void setDirectoryTitle(String directoryTitle) {
        this.directoryTitle = directoryTitle;
    }

    public String getHomeUnit() {
        return homeUnit;
    }

    public void setHomeUnit(String homeUnit) {
        this.homeUnit = homeUnit;
    }

    public Boolean getFacultyFlag() {
        return facultyFlag;
    }

    public void setFacultyFlag(Boolean facultyFlag) {
        this.facultyFlag = facultyFlag;
    }

    public Boolean getGraduateStudentStaffFlag() {
        return graduateStudentStaffFlag;
    }

    public void setGraduateStudentStaffFlag(Boolean graduateStudentStaffFlag) {
        this.graduateStudentStaffFlag = graduateStudentStaffFlag;
    }

    public Boolean getResearchStaffFlag() {
        return researchStaffFlag;
    }

    public void setResearchStaffFlag(Boolean researchStaffFlag) {
        this.researchStaffFlag = researchStaffFlag;
    }

    public Boolean getServiceStaffFlag() {
        return serviceStaffFlag;
    }

    public void setServiceStaffFlag(Boolean serviceStaffFlag) {
        this.serviceStaffFlag = serviceStaffFlag;
    }

    public Boolean getSupportStaffFlag() {
        return supportStaffFlag;
    }

    public void setSupportStaffFlag(Boolean supportStaffFlag) {
        this.supportStaffFlag = supportStaffFlag;
    }

    public Boolean getOtherAcademicGroupFlag() {
        return otherAcademicGroupFlag;
    }

    public void setOtherAcademicGroupFlag(Boolean otherAcademicGroupFlag) {
        this.otherAcademicGroupFlag = otherAcademicGroupFlag;
    }

    public Boolean getMedicalStaffFlag() {
        return medicalStaffFlag;
    }

    public void setMedicalStaffFlag(Boolean medicalStaffFlag) {
        this.medicalStaffFlag = medicalStaffFlag;
    }

    public Boolean getVacationAccrualFlag() {
        return vacationAccrualFlag;
    }

    public void setVacationAccrualFlag(Boolean vacationAccrualFlag) {
        this.vacationAccrualFlag = vacationAccrualFlag;
    }

    public Boolean getOnSabbaticalFlag() {
        return onSabbaticalFlag;
    }

    public void setOnSabbaticalFlag(Boolean onSabbaticalFlag) {
        this.onSabbaticalFlag = onSabbaticalFlag;
    }

    public String getIdProvided() {
        return idProvided;
    }

    public void setIdProvided(String idProvided) {
        this.idProvided = idProvided;
    }

    public String getIdVerified() {
        return idVerified;
    }

    public void setIdVerified(String idVerified) {
        this.idVerified = idVerified;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(String pagerNumber) {
        this.pagerNumber = pagerNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEraCommonsUserName() {
        return eraCommonsUserName;
    }

    public void setEraCommonsUserName(String eraCommonsUserName) {
        this.eraCommonsUserName = eraCommonsUserName;
    }

    public String getVisaCode() {
        return visaCode;
    }

    public void setVisaCode(String visaCode) {
        this.visaCode = visaCode;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}

