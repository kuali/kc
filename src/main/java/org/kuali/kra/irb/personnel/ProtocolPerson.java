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
package org.kuali.kra.irb.personnel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AffiliationType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.PersonEditableInterface;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.service.KcPersonService;

public class ProtocolPerson extends ProtocolAssociate implements PersonEditableInterface {

    private static final long serialVersionUID = 3226064839786525909L;

    private Integer protocolPersonId;

    private String personId;

    private String personName;

    private String protocolPersonRoleId;

    private Integer rolodexId;

    private Integer affiliationTypeCode;

    private String comments;

    private AffiliationType affiliationType;

    private ProtocolPersonRole protocolPersonRole;

    private ProtocolPersonRolodex rolodex;

    private boolean delete;

    private boolean trained;

    private List<ProtocolUnit> protocolUnits;

    private List<ProtocolAttachmentPersonnel> attachmentPersonnels;

    private int selectedUnit;

    private String previousPersonRoleId;

    private transient KcPersonService kcPersonService;

    private transient KcPerson kcPerson;

    // editable fields  
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

    public ProtocolPerson() {
        this.protocolUnits = new ArrayList<ProtocolUnit>();
        this.attachmentPersonnels = new ArrayList<ProtocolAttachmentPersonnel>();
    }

    public Integer getProtocolPersonId() {
        return this.protocolPersonId;
    }

    public void setProtocolPersonId(Integer protocolPersonId) {
        this.protocolPersonId = protocolPersonId;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getProtocolPersonRoleId() {
        return this.protocolPersonRoleId;
    }

    public void setProtocolPersonRoleId(String protocolPersonRoleId) {
        this.setPreviousPersonRoleId(this.protocolPersonRoleId);
        this.protocolPersonRoleId = protocolPersonRoleId;
    }

    public Integer getRolodexId() {
        return this.rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public Integer getAffiliationTypeCode() {
        return this.affiliationTypeCode;
    }

    public void setAffiliationTypeCode(Integer affiliationTypeCode) {
        this.affiliationTypeCode = affiliationTypeCode;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public AffiliationType getAffiliationType() {
        return this.affiliationType;
    }

    public void setAffiliationType(AffiliationType affiliationType) {
        this.affiliationType = affiliationType;
    }

    public ProtocolPersonRole getProtocolPersonRole() {
        return this.protocolPersonRole;
    }

    public void setProtocolPersonRoles(ProtocolPersonRole protocolPersonRole) {
        this.protocolPersonRole = protocolPersonRole;
    }

    public KcPerson getPerson() {
        if (kcPerson == null && this.personId != null) {
            kcPerson = getKcPersonService().getKcPersonByPersonId(this.personId);
        }
        return kcPerson;
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

    public ProtocolPersonRolodex getRolodex() {
        return this.rolodex;
    }

    public void setRolodex(ProtocolPersonRolodex rolodex) {
        this.rolodex = rolodex;
    }

    public boolean isDelete() {
        return this.delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /**
     * This method is linked to personnel service to check whether person has attended any training session.
     * 
     * @return boolean
     */
    public boolean isTrained() {
        return this.trained;
    }

    /**
     * This method is to check whether protocol units is required.
     * 
     * @return boolean
     */
    public boolean isUnitRequired() {
        return this.getProtocolPersonRole().isUnitDetailsRequired();
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    public List<ProtocolUnit> getProtocolUnits() {
        return this.protocolUnits;
    }

    public void setProtocolUnits(List<ProtocolUnit> protocolUnits) {
        this.protocolUnits = protocolUnits;
    }

    /**
     * This method adds a new unit to the collection of person units.
     * 
     * @param protocolUnit
     */
    public void addProtocolUnit(ProtocolUnit protocolUnit) {
        this.getProtocolUnits().add(protocolUnit);
    }

    /**
     * Gets index i from the protocol units list.
     * 
     * @param index
     * @return protocol unit at index i
     */
    public ProtocolUnit getProtocolUnit(int index) {
        return this.getProtocolUnits().get(index);
    }

    public int getSelectedUnit() {
        return this.selectedUnit;
    }

    public void setSelectedUnit(int selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    /**
     * This method is to reset all lead unit flag in protocol unit.
     */
    public void resetAllProtocolLeadUnits() {
        for (ProtocolUnit protocolUnit : this.getProtocolUnits()) {
            protocolUnit.setLeadUnitFlag(false);
        }
    }

    public String getPersonKey() {
        return this.getPersonId() == null ? this.getRolodexId().toString() : this.getPersonId();
    }

    /**
     * This method is to build and return a unique key for protocol person.
     * 
     * @return String
     */
    public String getPersonUniqueKey() {
        return new StringBuilder(this.getPersonId() == null ? this.getRolodexId().toString() : this.getPersonId()).append(this.getProtocolPersonRoleId()).toString();
    }

    public String getPreviousPersonRoleId() {
        return this.previousPersonRoleId;
    }

    public void setPreviousPersonRoleId(String previousPersonRoleId) {
        this.previousPersonRoleId = previousPersonRoleId;
    }

    /**
     * This method checks whether person is an employee or not non employee details are updated in rolodex a value in rolodex.
     * indicates that the person is non employee
     * 
     * @return true / false
     */
    public boolean isNonEmployee() {
        return this.rolodex != null || (this.rolodexId != null && StringUtils.isNotBlank(this.rolodexId.toString()));
    }

    /**
     * This method is to find lead unit from unit list.
     * 
     * @return ProtocolUnit (lead unit)
     */
    public ProtocolUnit getLeadUnit() {
        ProtocolUnit leadUnit = null;
        for (ProtocolUnit unit : this.getProtocolUnits()) {
            if (unit.getLeadUnitFlag()) {
                leadUnit = unit;
                break;
            }
        }
        return leadUnit;
    }

    /**
     * Gets the attachment personnels. Cannot return {@code null}.
     * @return the attachment personnels
     */
    public List<ProtocolAttachmentPersonnel> getAttachmentPersonnels() {
        if (this.attachmentPersonnels == null) {
            this.attachmentPersonnels = new ArrayList<ProtocolAttachmentPersonnel>();
        }
        return this.attachmentPersonnels;
    }

    public void setAttachmentPersonnels(List<ProtocolAttachmentPersonnel> attachmentPersonnels) {
        this.attachmentPersonnels = attachmentPersonnels;
    }

    /** 
     * {@inheritDoc} 
     * inits Protocol Units. 
     */
    @Override
    public void postInitHook(Protocol protocol) {
        for (ProtocolAttachmentPersonnel attachment : this.attachmentPersonnels) {
            attachment.init(this);
        }
        for (ProtocolUnit unit : this.protocolUnits) {
            unit.init(this);
        }
    }

    /** {@inheritDoc}  */
    public void resetPersistenceState() {
        this.setProtocolPersonId(null);
    }

    /**  {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.affiliationTypeCode == null) ? 0 : this.affiliationTypeCode.hashCode());
        result = prime * result + (this.delete ? 1231 : 1237);
        result = prime * result + ((this.personId == null) ? 0 : this.personId.hashCode());
        result = prime * result + ((this.personName == null) ? 0 : this.personName.hashCode());
        result = prime * result + ((this.previousPersonRoleId == null) ? 0 : this.previousPersonRoleId.hashCode());
        result = prime * result + ((this.protocolPersonId == null) ? 0 : this.protocolPersonId.hashCode());
        result = prime * result + ((this.protocolPersonRoleId == null) ? 0 : this.protocolPersonRoleId.hashCode());
        result = prime * result + ((this.protocolUnits == null) ? 0 : this.protocolUnits.hashCode());
        result = prime * result + ((this.rolodexId == null) ? 0 : this.rolodexId.hashCode());
        result = prime * result + this.selectedUnit;
        result = prime * result + (this.trained ? 1231 : 1237);
        return result;
    }

    /**  {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ProtocolPerson other = (ProtocolPerson) obj;
        if (this.affiliationTypeCode == null) {
            if (other.affiliationTypeCode != null) {
                return false;
            }
        } else if (!this.affiliationTypeCode.equals(other.affiliationTypeCode)) {
            return false;
        }
        if (this.delete != other.delete) {
            return false;
        }
        if (this.personId == null) {
            if (other.personId != null) {
                return false;
            }
        } else if (!this.personId.equals(other.personId)) {
            return false;
        }
        if (this.personName == null) {
            if (other.personName != null) {
                return false;
            }
        } else if (!this.personName.equals(other.personName)) {
            return false;
        }
        if (this.previousPersonRoleId == null) {
            if (other.previousPersonRoleId != null) {
                return false;
            }
        } else if (!this.previousPersonRoleId.equals(other.previousPersonRoleId)) {
            return false;
        }
        if (this.protocolPersonId == null) {
            if (other.protocolPersonId != null) {
                return false;
            }
        } else if (!this.protocolPersonId.equals(other.protocolPersonId)) {
            return false;
        }
        if (this.protocolPersonRoleId == null) {
            if (other.protocolPersonRoleId != null) {
                return false;
            }
        } else if (!this.protocolPersonRoleId.equals(other.protocolPersonRoleId)) {
            return false;
        }
        if (this.protocolUnits == null) {
            if (other.protocolUnits != null) {
                return false;
            }
        } else if (!this.protocolUnits.equals(other.protocolUnits)) {
            return false;
        }
        if (this.rolodexId == null) {
            if (other.rolodexId != null) {
                return false;
            }
        } else if (!this.rolodexId.equals(other.rolodexId)) {
            return false;
        }
        if (this.selectedUnit != other.selectedUnit) {
            return false;
        }
        if (this.trained != other.trained) {
            return false;
        }
        return true;
    }

    public boolean isPrincipalInvestigator() {
        if (StringUtils.equals(protocolPersonRoleId, "PI")) {
            return true;
        } else {
            return false;
        }
    }

    public String getLastName() {
        //        if (this.personId!=null) {  
        //            return getPerson().geeptLastName();  
        //        } else if (getRolodex()!=null) {  
        //            return getRolodex().getLastName();  
        //        } else {  
        //            return null;  
        //        }  
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public String getVisaCode() {
        return visaCode;
    }

    public void setVisaCode(String visaCode) {
        this.visaCode = visaCode;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public Date getVisaRenewalDate() {
        return visaRenewalDate;
    }

    public void setVisaRenewalDate(Date visaRenewalDate) {
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
