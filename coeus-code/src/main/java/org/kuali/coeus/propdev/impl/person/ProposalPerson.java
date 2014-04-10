/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.editable.PersonEditable;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.bo.CitizenshipType;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.proposaldevelopment.bo.CreditSplitNameInfo;
import org.kuali.kra.proposaldevelopment.bo.CreditSplitable;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonQuestionnaireHelper;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.rice.krad.bo.PersistableBusinessObject
 * @author $Author: gmcgrego $
 * @version $Revision: 1.42 $
 */
@Entity
@Table(name = "EPS_PROP_PERSON")
@IdClass(ProposalPerson.ProposalPersonId.class)
public class ProposalPerson extends KcPersistableBusinessObjectBase implements CreditSplitable, PersonRolodex, PersonEditable, AbstractProjectPerson {

    private static final long serialVersionUID = -4110005875629288373L;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private DevelopmentProposal developmentProposal;

    @Column(name = "CONFLICT_OF_INTEREST_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean conflictOfInterestFlag;

    @Column(name = "IS_OSC")
    @Convert(converter = BooleanYNConverter.class)
    private boolean otherSignificantContributorFlag;

    @Column(name = "PERCENTAGE_EFFORT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal percentageEffort;

    @Column(name = "FEDR_DEBR_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean fedrDebrFlag;

    @Column(name = "FEDR_DELQ_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean fedrDelqFlag;

    @Column(name = "ROLODEX_ID")
    private Integer rolodexId;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Column(name = "PROP_PERSON_ROLE_ID")
    private String proposalPersonRoleId;

    @OneToOne(targetEntity = ProposalInvestigatorCertification.class, cascade = { CascadeType.REFRESH })
    @PrimaryKeyJoinColumns({ @PrimaryKeyJoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @PrimaryKeyJoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER") })
    private ProposalInvestigatorCertification certification;

    @ManyToOne(targetEntity = ProposalPersonRole.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROP_PERSON_ROLE_ID", referencedColumnName = "PROP_PERSON_ROLE_ID", insertable = false, updatable = false)
    private ProposalPersonRole role;

    @Transient
    private boolean delete;

    @Transient
    private boolean isInvestigator;

    @Transient
    private boolean roleChanged;

    @OneToMany(mappedBy="proposalPerson", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL })
    @OrderBy("questionId")
    private List<ProposalPersonYnq> proposalPersonYnqs;

    @OneToMany(targetEntity = ProposalPersonUnit.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER", insertable = false, updatable = false) })
    private List<ProposalPersonUnit> units;

    @OneToMany(targetEntity = ProposalPersonDegree.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER", insertable = false, updatable = false) })
    private List<ProposalPersonDegree> proposalPersonDegrees;

    @OneToMany(targetEntity = ProposalPersonCreditSplit.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER", insertable = false, updatable = false) })
    private List<ProposalPersonCreditSplit> creditSplits;

    @Transient
    private String simpleName;

    @Column(name = "OPT_IN_UNIT_STATUS")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean optInUnitStatus = Boolean.TRUE;

    @Column(name = "OPT_IN_CERTIFICATION_STATUS")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean optInCertificationStatus = Boolean.TRUE;

    @Transient
    private boolean unitdelete;

    @Column(name = "PROJECT_ROLE")
    private String projectRole;

    @Column(name = "ORDINAL_POSITION")
    private Integer ordinalPosition;

    @Column(name = "MULTIPLE_PI")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean multiplePi = Boolean.FALSE;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean hiddenInHierarchy;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "SSN")
    private String socialSecurityNumber;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "PRIOR_NAME")
    private String priorName;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "AGE_BY_FISCAL_YEAR")
    private Integer ageByFiscalYear;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "RACE")
    private String race;

    @Column(name = "EDUCATION_LEVEL")
    private String educationLevel;

    @Column(name = "DEGREE")
    private String degree;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "IS_HANDICAPPED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean handicappedFlag;

    @Column(name = "HANDICAP_TYPE")
    private String handicapType;

    @Column(name = "IS_VETERAN")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean veteranFlag;

    @Column(name = "VETERAN_TYPE")
    private String veteranType;

    @Column(name = "VISA_CODE")
    private String visaCode;

    @Column(name = "VISA_TYPE")
    private String visaType;

    @Column(name = "VISA_RENEWAL_DATE")
    private Date visaRenewalDate;

    @Column(name = "HAS_VISA")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean hasVisa;

    @Column(name = "OFFICE_LOCATION")
    private String officeLocation;

    @Column(name = "OFFICE_PHONE")
    private String officePhone;

    @Column(name = "SECONDRY_OFFICE_LOCATION")
    private String secondaryOfficeLocation;

    @Column(name = "SECONDRY_OFFICE_PHONE")
    private String secondaryOfficePhone;

    @Column(name = "SCHOOL")
    private String school;

    @Column(name = "YEAR_GRADUATED")
    private String yearGraduated;

    @Column(name = "DIRECTORY_DEPARTMENT")
    private String directoryDepartment;

    @Column(name = "SALUTATION")
    private String saluation;

    @Column(name = "COUNTRY_OF_CITIZENSHIP")
    private String countryOfCitizenship;

    @Column(name = "PRIMARY_TITLE")
    private String primaryTitle;

    @Column(name = "DIRECTORY_TITLE")
    private String directoryTitle;

    @Column(name = "HOME_UNIT")
    private String homeUnit;

    @Column(name = "IS_FACULTY")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean facultyFlag;

    @Column(name = "IS_GRADUATE_STUDENT_STAFF")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean graduateStudentStaffFlag;

    @Column(name = "IS_RESEARCH_STAFF")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean researchStaffFlag;

    @Column(name = "IS_SERVICE_STAFF")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean serviceStaffFlag;

    @Column(name = "IS_SUPPORT_STAFF")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean supportStaffFlag;

    @Column(name = "IS_OTHER_ACCADEMIC_GROUP")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean otherAcademicGroupFlag;

    @Column(name = "IS_MEDICAL_STAFF")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean medicalStaffFlag;

    @Column(name = "VACATION_ACCURAL")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean vacationAccrualFlag;

    @Column(name = "IS_ON_SABBATICAL")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean onSabbaticalFlag;

    @Column(name = "ID_PROVIDED")
    private String idProvided;

    @Column(name = "ID_VERIFIED")
    private String idVerified;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "STATE")
    private String state;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "FAX_NUMBER")
    private String faxNumber;

    @Column(name = "PAGER_NUMBER")
    private String pagerNumber;

    @Column(name = "MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;

    @Column(name = "ERA_COMMONS_USER_NAME")
    private String eraCommonsUserName;

    @Column(name = "DIVISION")
    private String division;

    @Column(name="CITIZENSHIP_TYPE_CODE")
    private Integer citizenshipTypeCode;
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CITIZENSHIP_TYPE_CODE", insertable = false, updatable = false)
    private CitizenshipType citizenshipType;
    
    
    @Transient
    private Boolean active = true;

    @Transient
    private Unit homeUnitRef;

    @Transient
    private transient boolean moveDownAllowed;

    @Transient
    private transient boolean moveUpAllowed;
    
    @Transient
    private boolean selected;

    @Transient
    private transient KcPersonService kcPersonService;
    
    @Transient
    private ProposalPersonQuestionnaireHelper questionnaireHelper;
 
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

    public ProposalPerson() {
        proposalPersonDegrees = new ArrayList<ProposalPersonDegree>();
        setUnits(new ArrayList<ProposalPersonUnit>());
        setCreditSplits(new ArrayList<ProposalPersonCreditSplit>());
        setProposalPersonYnqs(new ArrayList<ProposalPersonYnq>());
        roleChanged = false;
        isInvestigator = false;
        delete = false;
        setFullName(new String());
        questionnaireHelper = new ProposalPersonQuestionnaireHelper(this);
    }

    /**
     * set the <code>simpleName</code> & the full name.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
        setSimpleName(getFullName());
        setSimpleName(StringUtils.lowerCase(getSimpleName()));
        setSimpleName(StringUtils.deleteWhitespace(getSimpleName()));
        setSimpleName(StringUtils.remove(getSimpleName(), '.'));
    }

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
    public ScaleTwoDecimal getPercentageEffort() {
        return this.percentageEffort;
    }

    /**
     * Sets the value of percentageEffort
     *
     * @param argPercentageEffort Value to assign to this.percentageEffort
     */
    public void setPercentageEffort(ScaleTwoDecimal argPercentageEffort) {
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
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        result = prime * result + ((rolodexId == null) ? 0 : rolodexId.hashCode());
        return result;
    }

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

    public Boolean getOptInUnitStatus() {
        return optInUnitStatus;
    }

    public void setOptInUnitStatus(Boolean optInUnitStatus) {
        this.optInUnitStatus = optInUnitStatus;
    }

    public Boolean getOptInCertificationStatus() {
        return optInCertificationStatus;
    }

    public void setOptInCertificationStatus(Boolean optInCertificationStatus) {
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
        return this.countryCode != null ? this.countryCode.trim() : null;
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
        if (StringUtils.isBlank(eraCommonsUserName) && personId != null) {
            this.eraCommonsUserName = getKcPersonService().getKcPersonByPersonId(personId).getExtendedAttributes().getEraCommonUserName();
        }
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

    /**
     * 
     * Gets the value of division;
     * 
     * @return the value of division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the value of division
     * 
     * @param division Value to assign to this.division
     */
    public void setDivision(String division) {
        this.division = division;
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

    public Serializable getIdentifier() {
        return personId;
    }

    public Unit getUnit() {
        return homeUnitRef;
    }

    public void setIdentifier(Serializable identifier) {
        setPersonId((String) identifier);
    }

    public void setUnit(Unit unit) {
        setHomeUnitRef(unit);
    }

    public String getPhoneNumber() {
        return officePhone;
    }

    public String getContactOrganizationName() {
        return getUnit().getUnitName();
    }

    public String getOrganizationIdentifier() {
        return homeUnit;
    }

    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    public Boolean getHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    public void setHiddenInHierarchy(Boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    public ContactRole getContactRole() {
        return getRole();
    }

    //Return value here is boolean instead of Boolean due to PersonRolodex definition affecting numerous classes
    public boolean isMultiplePi() {
        return multiplePi == null ? false : multiplePi.booleanValue();
    }

    public void setMultiplePi(Boolean multiplePi) {
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
        return KcServiceLocator.getService(KeyPersonnelService.class).getPersonnelRoleDesc(this);
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

    public static final class ProposalPersonId implements Serializable, Comparable<ProposalPersonId> {

        private String proposalNumber;

        private Integer proposalPersonNumber;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public Integer getProposalPersonNumber() {
            return this.proposalPersonNumber;
        }

        public void setProposalPersonNumber(Integer proposalPersonNumber) {
            this.proposalPersonNumber = proposalPersonNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("proposalPersonNumber", this.proposalPersonNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalPersonId rhs = (ProposalPersonId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.proposalPersonNumber, rhs.proposalPersonNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.proposalPersonNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.proposalPersonNumber, other.proposalPersonNumber).toComparison();
        }
    }

	public ProposalPersonQuestionnaireHelper getQuestionnaireHelper() {
		return questionnaireHelper;
	}

	public void setQuestionnaireHelper(
			ProposalPersonQuestionnaireHelper questionnaireHelper) {
		this.questionnaireHelper = questionnaireHelper;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getCitizenshipTypeCode() {
		return citizenshipTypeCode;
	}

	public void setCitizenshipTypeCode(Integer citizenshipTypeCode) {
		this.citizenshipTypeCode = citizenshipTypeCode;
	}

	public CitizenshipType getCitizenshipType() {
		return citizenshipType;
	}

	public void setCitizenshipType(CitizenshipType citizenshipType) {
		this.citizenshipType = citizenshipType;
	}
}
