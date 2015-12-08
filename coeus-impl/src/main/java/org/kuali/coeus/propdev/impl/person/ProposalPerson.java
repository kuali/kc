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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.person.attr.PersonTraining;
import org.kuali.coeus.common.framework.person.editable.PersonEditable;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleService;
import org.kuali.coeus.common.framework.person.attr.CitizenshipType;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.person.creditsplit.NamedCreditSplitable;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
public class ProposalPerson extends KcPersistableBusinessObjectBase implements NamedCreditSplitable, PersonRolodex, PersonEditable, AbstractProjectPerson, ProposalPersonContract, HierarchyMaintainable, MutableInactivatable {

    private static final long serialVersionUID = -4110005875629288373L;

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER")
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
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Column(name = "PROP_PERSON_ROLE_ID")
    private String proposalPersonRoleId;
	
	
	@Column(name = "CERTIFIED_BY")
	private String certifiedBy;


	@Column(name = "LAST_NOTIFICATION")
	private Timestamp lastNotification;
    

	@Column(name = "CERTIFIED_TIME")
	private Timestamp certifiedTime;

    @OneToOne(cascade = { CascadeType.REFRESH })
    @PrimaryKeyJoinColumns({ @PrimaryKeyJoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @PrimaryKeyJoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER") })
    private ProposalInvestigatorCertification certification;

    @Transient
    private boolean delete;

    @Transient
    private boolean roleChanged;

    @OneToMany(mappedBy="proposalPerson", orphanRemoval = true, cascade = { CascadeType.ALL })
    @OrderBy("questionId")
    private List<ProposalPersonYnq> proposalPersonYnqs;

    @OneToMany(mappedBy="proposalPerson", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalPersonUnit> units;

    @OneToMany(mappedBy="proposalPerson", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalPersonDegree> proposalPersonDegrees;

    @OneToMany(mappedBy="proposalPerson", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalPersonCreditSplit> creditSplits;

    @Transient
    private List<PersonTraining> personTrainings;

    @Transient
    private String simpleName;

    @Column(name = "OPT_IN_UNIT_STATUS")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean optInUnitStatus = Boolean.FALSE;

    @Column(name = "OPT_IN_CERTIFICATION_STATUS")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean optInCertificationStatus = Boolean.FALSE;

    @Transient
    private boolean unitdelete;

    @Column(name = "PROJECT_ROLE")
    private String projectRole;

    @Column(name = "ORDINAL_POSITION")
    private Integer ordinalPosition;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

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

    @Column(name="ACADEMIC_YEAR_EFFORT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal academicYearEffort;

    @Column(name="CALENDAR_YEAR_EFFORT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal calendarYearEffort;

    @Column(name="SUMMER_EFFORT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal summerEffort;
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CITIZENSHIP_TYPE_CODE", insertable = false, updatable = false)
    private CitizenshipType citizenshipType;

    @Transient
    private boolean selectedPerson;

    @Transient
    private Boolean active = true;

    @ManyToOne(targetEntity = Unit.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "HOME_UNIT", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
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
    
    @Transient
    private transient PropAwardPersonRoleService propAwardPersonRoleService;
    
    @Transient
    private transient String  certifiedPersonName;
    
    @Transient
    private transient String  certifiedTimeStamp;

    @Transient
    private Timestamp createTimestamp;
    

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
        delete = false;
        setFullName(new String());
        questionnaireHelper = new ProposalPersonQuestionnaireHelper(this);
    }

    /**
     * set the <code>simpleName</code> &amp; the full name.
     */
    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
        setSimpleName(getFullName());
        setSimpleName(StringUtils.lowerCase(getSimpleName()));
        setSimpleName(StringUtils.deleteWhitespace(getSimpleName()));
        setSimpleName(StringUtils.remove(getSimpleName(), '.'));
    }

    public String getFullName() {
        return this.fullName;
    }

    @Override
    public String getCreditSplitName() {
        return getFullName();
    }

    public Date getSalaryAnniversaryDate() {
        if (getPerson() != null){
         return getPerson().getExtendedAttributes().getSalaryAnniversaryDate();
        }
       return null;
    }


    public boolean getInvestigatorFlag() {
        return isPrincipalInvestigator() || isMultiplePi() || isCoInvestigator()
                || (isKeyPerson() && getOptInUnitStatus());
    }

    @Override
    public boolean isInvestigator() {
        return getInvestigatorFlag();
    }

    @Override
    public boolean isPrincipalInvestigator() {
    	return StringUtils.equals(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR, getProposalPersonRoleId());
    }

    @Override
    public boolean isCoInvestigator() {
    	return StringUtils.equals(PropAwardPersonRole.CO_INVESTIGATOR, getProposalPersonRoleId());
    }

    @Override
    public boolean isKeyPerson() {
    	return StringUtils.equals(PropAwardPersonRole.KEY_PERSON, getProposalPersonRoleId());
    }

    @Override
    public boolean isMultiplePi() {
    	return StringUtils.equals(PropAwardPersonRole.MULTI_PI, getProposalPersonRoleId());
    }

    public void setCreditSplits(List<ProposalPersonCreditSplit> creditSplit) {
        this.creditSplits = creditSplit;
    }

    @Override
    public List<ProposalPersonCreditSplit> getCreditSplits() {
        return this.creditSplits;
    }

    @Override
    public ProposalInvestigatorCertification getCertification() {
        return this.certification;
    }


    public void setCertification(ProposalInvestigatorCertification argCertification) {
        this.certification = argCertification;
    }

    @Override
    public List<ProposalPersonUnit> getUnits() {
        return this.units;
    }


    public void setUnits(List<ProposalPersonUnit> argUnits) {
        this.units = argUnits;
    }

    @Override
    public List<ProposalPersonDegree> getProposalPersonDegrees() {
        return this.proposalPersonDegrees;
    }

    public void setProposalPersonDegrees(List<ProposalPersonDegree> argDegrees) {
        this.proposalPersonDegrees = argDegrees;
    }

    @Override
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
        return this.getDevelopmentProposal().getProposalNumber() + "|" + this.getProposalPersonNumber();
    }

    public void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
    }

    @Override
    public boolean getConflictOfInterestFlag() {
        return this.conflictOfInterestFlag;
    }

    @Override
    public ScaleTwoDecimal getPercentageEffort() {
        return this.percentageEffort;
    }

    public void setPercentageEffort(ScaleTwoDecimal argPercentageEffort) {
        this.percentageEffort = argPercentageEffort;
    }

    @Override
    public Boolean getFedrDebrFlag() {
        return this.fedrDebrFlag;
    }

    public void setFedrDebrFlag(Boolean argFedrDebr) {
        this.fedrDebrFlag = argFedrDebr;
    }

    @Override
    public Boolean getFedrDelqFlag() {
        return this.fedrDelqFlag;
    }

    public void setFedrDelqFlag(Boolean argFedrDelq) {
        this.fedrDelqFlag = argFedrDelq;
    }

    @Override
    public Integer getRolodexId() {
        return this.rolodexId;
    }

    @Override
    public void setRolodexId(Integer argRolodexId) {
        this.rolodexId = argRolodexId;
    }

    @Override
    public String getProposalPersonRoleId() {
        return this.proposalPersonRoleId;
    }

    public void setProposalPersonRoleId(String argPropPersonRoleId) {
    	this.proposalPersonRoleId = argPropPersonRoleId;
    }

    public PropAwardPersonRole getRole() {
    	if (StringUtils.isNotBlank(getProposalPersonRoleId()) && getDevelopmentProposal() != null &&
    			StringUtils.isNotBlank(getDevelopmentProposal().getSponsorCode())) {
    		return getPropAwardPersonRoleService().getRole(getProposalPersonRoleId(), getDevelopmentProposal().getSponsorCode());
    	} else {
    		return null;
    	}
    }

    public void setConflictOfInterestFlag(boolean argConflictOfInterest) {
        this.conflictOfInterestFlag = argConflictOfInterest;
    }

    /**
     * Gets the value of person, or null if no personId is defined (this is the case for non-employees).
     *
     * @return the value of person
     */
    @Override
    public KcPerson getPerson() {
        if (this.personId == null) {
            return null;
        } else {
            return getKcPersonService().getKcPersonByPersonId(this.personId);
        }
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
		this.kcPersonService = kcPersonService;
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

    @Override
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

    @Override
    public boolean isOtherSignificantContributorFlag() {
        return otherSignificantContributorFlag;
    }

    public void setOtherSignificantContributorFlag(boolean otherSignificantContributorFlag) {
        this.otherSignificantContributorFlag = otherSignificantContributorFlag;
    }

    @Override
    public Boolean getOptInUnitStatus() {
        return optInUnitStatus;
    }

    public void setOptInUnitStatus(Boolean optInUnitStatus) {
        this.optInUnitStatus = optInUnitStatus;
    }

    @Override
    public Boolean getOptInCertificationStatus() {
        return optInCertificationStatus;
    }

    public void setOptInCertificationStatus(Boolean optInCertificationStatus) {
        this.optInCertificationStatus = optInCertificationStatus;
    }

    @Override
    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    @Override
    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    @Override
    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String argPersonId) {
        this.personId = argPersonId;
    }

    @Override
    public void setSocialSecurityNumber(String argSocialSecurityNumber) {
        this.socialSecurityNumber = argSocialSecurityNumber;
    }

    @Override
    public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String argLastName) {
        this.lastName = argLastName;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String argFirstName) {
        this.firstName = argFirstName;
    }

    @Override
    public String getMiddleName() {
        return this.middleName;
    }

    @Override
    public void setMiddleName(String argMiddleName) {
        this.middleName = argMiddleName;
    }

    @Override
    public String getPriorName() {
        return this.priorName;
    }

    @Override
    public void setPriorName(String argPriorName) {
        this.priorName = argPriorName;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public void setUserName(String argUserName) {
        this.userName = argUserName;
    }

    @Override
    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public void setEmailAddress(String argEmailAddress) {
        this.emailAddress = argEmailAddress;
    }

    @Override
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date argDateOfBirth) {
        this.dateOfBirth = argDateOfBirth;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    @Override
    public void setAge(Integer argAge) {
        this.age = argAge;
    }

    @Override
    public Integer getAgeByFiscalYear() {
        return this.ageByFiscalYear;
    }

    @Override
    public void setAgeByFiscalYear(Integer argAgeByFiscalYear) {
        this.ageByFiscalYear = argAgeByFiscalYear;
    }

    @Override
    public String getGender() {
        return this.gender;
    }

    @Override
    public void setGender(String argGender) {
        this.gender = argGender;
    }

    @Override
    public String getRace() {
        return this.race;
    }

    @Override
    public void setRace(String argRace) {
        this.race = argRace;
    }

    @Override
    public String getEducationLevel() {
        return this.educationLevel;
    }

    @Override
    public void setEducationLevel(String argEducationLevel) {
        this.educationLevel = argEducationLevel;
    }

    @Override
    public String getDegree() {
        return this.degree;
    }

    @Override
    public void setDegree(String argDegree) {
        this.degree = argDegree;
    }

    @Override
    public String getMajor() {
        return this.major;
    }

    @Override
    public void setMajor(String argMajor) {
        this.major = argMajor;
    }

    @Override
    public Boolean getHandicappedFlag() {
        return this.handicappedFlag;
    }

    @Override
    public void setHandicappedFlag(Boolean argHandicapped) {
        this.handicappedFlag = argHandicapped;
    }

    @Override
    public String getHandicapType() {
        return this.handicapType;
    }

    @Override
    public void setHandicapType(String argHandicapType) {
        this.handicapType = argHandicapType;
    }

    @Override
    public Boolean getVeteranFlag() {
        return this.veteranFlag;
    }

    @Override
    public void setVeteranFlag(Boolean argVeteran) {
        this.veteranFlag = argVeteran;
    }

    @Override
    public String getVeteranType() {
        return this.veteranType;
    }

    @Override
    public void setVeteranType(String argVeteranType) {
        this.veteranType = argVeteranType;
    }

    @Override
    public String getVisaCode() {
        return this.visaCode;
    }

    @Override
    public void setVisaCode(String argVisaCode) {
        this.visaCode = argVisaCode;
    }

    @Override
    public String getVisaType() {
        return this.visaType;
    }

    @Override
    public void setVisaType(String argVisaType) {
        this.visaType = argVisaType;
    }

    @Override
    public Date getVisaRenewalDate() {
        return this.visaRenewalDate;
    }

    @Override
    public void setVisaRenewalDate(Date argVisaRenewalDate) {
        this.visaRenewalDate = argVisaRenewalDate;
    }

    @Override
    public Boolean getHasVisa() {
        return this.hasVisa;
    }

    @Override
    public void setHasVisa(Boolean argHasVisa) {
        this.hasVisa = argHasVisa;
    }

    @Override
    public String getOfficeLocation() {
        return this.officeLocation;
    }

    @Override
    public void setOfficeLocation(String argOfficeLocation) {
        this.officeLocation = argOfficeLocation;
    }

    @Override
    public String getOfficePhone() {
        return this.officePhone;
    }

    @Override
    public void setOfficePhone(String argOfficePhone) {
        this.officePhone = argOfficePhone;
    }

    @Override
    public String getSecondaryOfficeLocation() {
        return this.secondaryOfficeLocation;
    }

    @Override
    public void setSecondaryOfficeLocation(String argSecondaryOfficeLocation) {
        this.secondaryOfficeLocation = argSecondaryOfficeLocation;
    }

    @Override
    public String getSecondaryOfficePhone() {
        return this.secondaryOfficePhone;
    }

    @Override
    public void setSecondaryOfficePhone(String argSecondaryOfficePhone) {
        this.secondaryOfficePhone = argSecondaryOfficePhone;
    }

    @Override
    public String getSchool() {
        return this.school;
    }

    @Override
    public void setSchool(String argSchool) {
        this.school = argSchool;
    }

    @Override
    public String getYearGraduated() {
        return this.yearGraduated;
    }

    @Override
    public void setYearGraduated(String argYearGraduated) {
        this.yearGraduated = argYearGraduated;
    }

    @Override
    public String getDirectoryDepartment() {
        return this.directoryDepartment;
    }

    @Override
    public void setDirectoryDepartment(String argDirectoryDepartment) {
        this.directoryDepartment = argDirectoryDepartment;
    }

    @Override
    public String getSaluation() {
        return this.saluation;
    }

    @Override
    public void setSaluation(String argSaluation) {
        this.saluation = argSaluation;
    }

    @Override
    public String getCountryOfCitizenship() {
        return this.countryOfCitizenship;
    }

    @Override
    public void setCountryOfCitizenship(String argCountryOfCitizenship) {
        this.countryOfCitizenship = argCountryOfCitizenship;
    }

    @Override
    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    @Override
    public void setPrimaryTitle(String argPrimaryTitle) {
        this.primaryTitle = argPrimaryTitle;
    }

    @Override
    public String getDirectoryTitle() {
        return this.directoryTitle;
    }

    @Override
    public void setDirectoryTitle(String argDirectoryTitle) {
        this.directoryTitle = argDirectoryTitle;
    }

    @Override
    public String getHomeUnit() {
        return this.homeUnit;
    }

    @Override
    public void setHomeUnit(String argHomeUnit) {
        this.homeUnit = argHomeUnit;
    }

    @Override
    public Boolean getFacultyFlag() {
        return this.facultyFlag;
    }

    @Override
    public void setFacultyFlag(Boolean argFaculty) {
        this.facultyFlag = argFaculty;
    }

    @Override
    public Boolean getGraduateStudentStaffFlag() {
        return this.graduateStudentStaffFlag;
    }

    @Override
    public void setGraduateStudentStaffFlag(Boolean argGraduateStudentStaff) {
        this.graduateStudentStaffFlag = argGraduateStudentStaff;
    }

    @Override
    public Boolean getResearchStaffFlag() {
        return this.researchStaffFlag;
    }

    @Override
    public void setResearchStaffFlag(Boolean argResearchStaff) {
        this.researchStaffFlag = argResearchStaff;
    }

    @Override
    public Boolean getServiceStaffFlag() {
        return this.serviceStaffFlag;
    }

    @Override
    public void setServiceStaffFlag(Boolean argServiceStaff) {
        this.serviceStaffFlag = argServiceStaff;
    }

    @Override
    public Boolean getSupportStaffFlag() {
        return this.supportStaffFlag;
    }

    @Override
    public void setSupportStaffFlag(Boolean argSupportStaff) {
        this.supportStaffFlag = argSupportStaff;
    }

    @Override
    public Boolean getOtherAcademicGroupFlag() {
        return this.otherAcademicGroupFlag;
    }

    public String getOrganization() {
        return getUnit().getUnitName();
    }

    @Override
    public void setOtherAcademicGroupFlag(Boolean argOtherAcademicGroup) {
        this.otherAcademicGroupFlag = argOtherAcademicGroup;
    }

    @Override
    public Boolean getMedicalStaffFlag() {
        return this.medicalStaffFlag;
    }

    @Override
    public void setMedicalStaffFlag(Boolean argMedicalStaff) {
        this.medicalStaffFlag = argMedicalStaff;
    }

    @Override
    public Boolean getVacationAccrualFlag() {
        return this.vacationAccrualFlag;
    }

    @Override
    public void setVacationAccrualFlag(Boolean argVacationAccrual) {
        this.vacationAccrualFlag = argVacationAccrual;
    }

    @Override
    public Boolean getOnSabbaticalFlag() {
        return this.onSabbaticalFlag;
    }

    @Override
    public void setOnSabbaticalFlag(Boolean argOnSabbatical) {
        this.onSabbaticalFlag = argOnSabbatical;
    }

    @Override
    public String getIdProvided() {
        return this.idProvided;
    }

    @Override
    public void setIdProvided(String argIdProvided) {
        this.idProvided = argIdProvided;
    }

    @Override
    public String getIdVerified() {
        return this.idVerified;
    }

    @Override
    public void setIdVerified(String argIdVerified) {
        this.idVerified = argIdVerified;
    }

    @Override
    public String getAddressLine1() {
        return this.addressLine1;
    }

    @Override
    public void setAddressLine1(String argAddressLine1) {
        this.addressLine1 = argAddressLine1;
    }

    @Override
    public String getAddressLine2() {
        return this.addressLine2;
    }

    @Override
    public void setAddressLine2(String argAddressLine2) {
        this.addressLine2 = argAddressLine2;
    }

    @Override
    public String getAddressLine3() {
        return this.addressLine3;
    }

    @Override
    public void setAddressLine3(String argAddressLine3) {
        this.addressLine3 = argAddressLine3;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public void setCity(String argCity) {
        this.city = argCity;
    }

    @Override
    public String getCounty() {
        return this.county;
    }

    @Override
    public void setCounty(String argCounty) {
        this.county = argCounty;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public void setState(String argState) {
        this.state = argState;
    }

    @Override
    public String getPostalCode() {
        return this.postalCode;
    }

    @Override
    public void setPostalCode(String argPostalCode) {
        this.postalCode = argPostalCode;
    }

    @Override
    public String getCountryCode() {
        return this.countryCode != null ? this.countryCode.trim() : null;
    }

    @Override
    public void setCountryCode(String argCountryCode) {
        this.countryCode = argCountryCode;
    }

    @Override
    public String getFaxNumber() {
        return this.faxNumber;
    }

    @Override
    public void setFaxNumber(String argFaxNumber) {
        this.faxNumber = argFaxNumber;
    }

    @Override
    public String getPagerNumber() {
        return this.pagerNumber;
    }

    @Override
    public void setPagerNumber(String argPagerNumber) {
        this.pagerNumber = argPagerNumber;
    }

    @Override
    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    @Override
    public void setMobilePhoneNumber(String argMobilePhoneNumber) {
        this.mobilePhoneNumber = argMobilePhoneNumber;
    }

    @Override
    public String getEraCommonsUserName() {
        if (StringUtils.isBlank(eraCommonsUserName) && personId != null) {
            this.eraCommonsUserName = getKcPersonService().getKcPersonByPersonId(personId).getExtendedAttributes().getEraCommonUserName();
        }
        return this.eraCommonsUserName;
    }

    @Override
    public void setEraCommonsUserName(String argEraCommonsUserName) {
        this.eraCommonsUserName = argEraCommonsUserName;
    }

    @Override
    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setHomeUnitRef(Unit unit) {
        this.homeUnitRef = unit;
    }

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

    @Override
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    @Override
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    @Override
    public ContactRole getContactRole() {
        return getRole();
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }

    @Override
    public Sponsorable getParent() {
        return getDevelopmentProposal();
    }

    @Override
    public String getInvestigatorRoleDescription() {
        return getRole().getDescription();
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

        private String developmentProposal;

        private Integer proposalPersonNumber;

		public String getDevelopmentProposal() {
			return developmentProposal;
		}

		public void setDevelopmentProposal(String developmentProposal) {
			this.developmentProposal = developmentProposal;
		}

        public Integer getProposalPersonNumber() {
            return this.proposalPersonNumber;
        }

        public void setProposalPersonNumber(Integer proposalPersonNumber) {
            this.proposalPersonNumber = proposalPersonNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("developmentProposal", this.developmentProposal).append("proposalPersonNumber", this.proposalPersonNumber).toString();
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
            return new EqualsBuilder().append(this.developmentProposal, rhs.developmentProposal).append(this.proposalPersonNumber, rhs.proposalPersonNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.developmentProposal).append(this.proposalPersonNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonId other) {
            return new CompareToBuilder().append(this.developmentProposal, other.developmentProposal).append(this.proposalPersonNumber, other.proposalPersonNumber).toComparison();
        }
    }

	public ProposalPersonQuestionnaireHelper getQuestionnaireHelper() {
        if (questionnaireHelper != null && questionnaireHelper.getAnswerHeaders() == null){
            questionnaireHelper.populateAnswers();
        }
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

    @Override
	public void setCitizenshipTypeCode(Integer citizenshipTypeCode) {
		this.citizenshipTypeCode = citizenshipTypeCode;
	}

    @Override
	public CitizenshipType getCitizenshipType() {
		return citizenshipType;
	}

	public void setCitizenshipType(CitizenshipType citizenshipType) {
		this.citizenshipType = citizenshipType;
	}

	protected PropAwardPersonRoleService getPropAwardPersonRoleService() {
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
    public String getProposalNumber() {
        return getDevelopmentProposal().getProposalNumber();
    }
    
    public boolean isQuestionnairesCompleted() {
    	 boolean retVal = true;
    	 if (this.getQuestionnaireHelper() != null && this.getQuestionnaireHelper().getAnswerHeaders() != null) {
    		 for (AnswerHeader ah : getQuestionnaireHelper().getAnswerHeaders()) {
    			 retVal &= ah.isCompleted();
    		 }
    	 }
    	 return retVal;
    }

    public List<PersonTraining> getPersonTrainings() {
        if (CollectionUtils.isEmpty(personTrainings)) {
            personTrainings = (List<PersonTraining>) KcServiceLocator.getService(BusinessObjectService.class).findMatching(PersonTraining.class, Collections.singletonMap("personId",this.getPersonId()));
        }

        return personTrainings;
    }

    public void setPersonTrainings(List<PersonTraining> personTrainings) {
        this.personTrainings = personTrainings;
    }

    public String getPersonName(){
        return getFullName();
    }

    public ScaleTwoDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    public void setAcademicYearEffort(ScaleTwoDecimal academicYearEffort) {
        this.academicYearEffort = academicYearEffort;
    }

    public ScaleTwoDecimal getCalendarYearEffort() {
        return calendarYearEffort;
    }

    public void setCalendarYearEffort(ScaleTwoDecimal calendarYearEffort) {
        this.calendarYearEffort = calendarYearEffort;
    }

    public ScaleTwoDecimal getSummerEffort() {
        return summerEffort;
    }

    public void setSummerEffort(ScaleTwoDecimal summerEffort) {
        this.summerEffort = summerEffort;
    }

	public boolean isSelectedPerson() {
		return selectedPerson;
	}


	public void setSelectedPerson(boolean selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	
	public String getCertifiedBy() {
		
		return certifiedBy;
	}

	public void setCertifiedBy(String certifiedBy) {
		this.certifiedBy = certifiedBy;
	}
	
	public Timestamp getLastNotification() {
		return lastNotification;
	}

	public void setLastNotification(Timestamp lastNotification) {
		this.lastNotification = lastNotification;
	}

	public Timestamp getCertifiedTime() {
		
		return certifiedTime;
	}

	public void setCertifiedTime(Timestamp certifiedTime) {
		this.certifiedTime = certifiedTime;
	}
	
	public String getCertifiedPersonName() {
		if(this.certifiedBy!=null){
			 this.certifiedPersonName = getKcPersonService().getKcPersonByPersonId(certifiedBy).getUserName();
			}
		return certifiedPersonName;
	}

	public void setCertifiedPersonName(String certifiedPersonName) {
		this.certifiedPersonName = certifiedPersonName;
	}
	
	public String getCertifiedTimeStamp() {
		if(this.certifiedTime != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.MM_DD_YYYY_HH_MM_A_DATE_FORMAT);
            certifiedTimeStamp = simpleDateFormat.format(certifiedTime);
		}
		return certifiedTimeStamp;
	}

	public void setCertifiedTimeStamp(String certifiedTimeStamp) {
		this.certifiedTimeStamp = certifiedTimeStamp;
	}

}
