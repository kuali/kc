/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
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

package org.kuali.coeus.irb.api.dto;

import com.codiform.moo.annotation.Property;

import java.sql.Date;

public class IrbProtocolPersonDto {

    private String protocolPersonRoleId;

    private String personId;

    private Integer rolodexId;

    private Integer affiliationTypeCode;

    private String comments;

    private boolean trained;

    @Property(translate = false)
    private String lastName;
    @Property(translate = false)
    private String firstName;
    @Property(translate = false)
    private String middleName;
    @Property(translate = false)
    private String fullName;
    @Property(translate = false)
    private String priorName;
    @Property(translate = false)
    private String userName;
    @Property(translate = false)
    private String emailAddress;
    @Property(translate = false)
    private Date dateOfBirth;
    @Property(translate = false)
    private Integer age;
    @Property(translate = false)
    private Integer ageByFiscalYear;
    @Property(translate = false)
    private String gender;
    @Property(translate = false)
    private String race;
    @Property(translate = false)
    private String educationLevel;
    @Property(translate = false)
    private String degree;
    @Property(translate = false)
    private String major;
    @Property(translate = false)
    private Boolean handicappedFlag;
    @Property(translate = false)
    private String handicapType;
    @Property(translate = false)
    private Boolean veteranFlag;
    @Property(translate = false)
    private String veteranType;
    @Property(translate = false)
    private String visaCode;
    @Property(translate = false)
    private String visaType;
    @Property(translate = false)
    private Date visaRenewalDate;
    @Property(translate = false)
    private Boolean hasVisa;
    @Property(translate = false)
    private String officeLocation;
    @Property(translate = false)
    private String officePhone;
    @Property(translate = false)
    private String secondaryOfficeLocation;
    @Property(translate = false)
    private String secondaryOfficePhone;
    @Property(translate = false)
    private String school;
    @Property(translate = false)
    private String yearGraduated;
    @Property(translate = false)
    private String directoryDepartment;
    @Property(translate = false)
    private String saluation;
    @Property(translate = false)
    private String countryOfCitizenship;
    @Property(translate = false)
    private String primaryTitle;
    @Property(translate = false)
    private String directoryTitle;
    @Property(translate = false)
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
    @Property(translate = false)
    private String idProvided;
    @Property(translate = false)
    private String idVerified;
    @Property(translate = false)
    private String addressLine1;
    @Property(translate = false)
    private String addressLine2;
    @Property(translate = false)
    private String addressLine3;
    @Property(translate = false)
    private String city;
    @Property(translate = false)
    private String county;
    @Property(translate = false)
    private String state;
    @Property(translate = false)
    private String postalCode;
    @Property(translate = false)
    private String countryCode;
    @Property(translate = false)
    private String faxNumber;
    @Property(translate = false)
    private String pagerNumber;
    @Property(translate = false)
    private String mobilePhoneNumber;
    @Property(translate = false)
    private String eraCommonsUserName;

    public String getProtocolPersonRoleId() {
        return protocolPersonRoleId;
    }

    public void setProtocolPersonRoleId(String protocolPersonRoleId) {
        this.protocolPersonRoleId = protocolPersonRoleId;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public Integer getAffiliationTypeCode() {
        return affiliationTypeCode;
    }

    public void setAffiliationTypeCode(Integer affiliationTypeCode) {
        this.affiliationTypeCode = affiliationTypeCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isTrained() {
        return trained;
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
