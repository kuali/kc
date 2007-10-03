/*
 * Copyright 2006-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Class representation of the Person <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * $Id: Person.java,v 1.4 2007-10-03 19:20:20 tdurkin Exp $
 */
public class Person extends KraPersistableBusinessObjectBase {
    private String personId;
    private String socialSecurityNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private String fullName;
    private String priorName;
    private String userName;
    private String emailAddress;
    private String dateOfBirth;
    private Integer age;
    private Integer ageByFiscalYear;
    private String gender;
    private String race;
    private String educationLevel;
    private String degree;
    private String major;
    private Boolean handicapped;
    private String handicapType;
    private Boolean veteran;
    private String veteranType;
    private String visaCode;
    private String visaType;
    private String visaRenewalDate;
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
    private Boolean faculty;
    private Boolean graduateStudentStaff;
    private Boolean researchStaff;
    private Boolean serviceStaff;
    private Boolean supportStaff;
    private Boolean otherAcademicGroup;
    private Boolean medicalStaff;
    private Boolean vacationAccrual;
    private Boolean onSabbatical;
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

    private List<UserRole> userRoles = new ArrayList();
    
    
    public Person() {
        super();
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
     * Gets the value of fullName
     *
     * @return the value of fullName
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Sets the value of fullName
     *
     * @param argFullName Value to assign to this.fullName
     */
    public void setFullName(String argFullName) {
        this.fullName = argFullName;
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
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Sets the value of dateOfBirth
     *
     * @param argDateOfBirth Value to assign to this.dateOfBirth
     */
    public void setDateOfBirth(String argDateOfBirth) {
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
    public Boolean isHandicapped() {
        return getIsHandicapped();
    }

    /**
     * Gets the value of handicapped
     *
     * @return the value of handicapped
     */
    public Boolean getIsHandicapped() {
        return this.handicapped;
    }

    /**
     * Sets the value of handicapped
     *
     * @param argHandicapped Value to assign to this.handicapped
     */
    public void setIsHandicapped(Boolean argHandicapped) {
        this.handicapped = argHandicapped;
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
    public Boolean isVeteran() {
        return getIsVeteran();
    }

    /**
     * Gets the value of veteran
     *
     * @return the value of veteran
     */
    public Boolean getIsVeteran() {
        return this.veteran;
    }

    /**
     * Sets the value of veteran
     *
     * @param argVeteran Value to assign to this.veteran
     */
    public void setIsVeteran(Boolean argVeteran) {
        this.veteran = argVeteran;
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
    public String getVisaRenewalDate() {
        return this.visaRenewalDate;
    }

    /**
     * Sets the value of visaRenewalDate
     *
     * @param argVisaRenewalDate Value to assign to this.visaRenewalDate
     */
    public void setVisaRenewalDate(String argVisaRenewalDate) {
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
    public Boolean isFaculty() {
        return getIsFaculty();
    }

    /**
     * Gets the value of faculty
     *
     * @return the value of faculty
     */
    public Boolean getIsFaculty() {
        return this.faculty;
    }

    /**
     * Sets the value of faculty
     *
     * @param argFaculty Value to assign to this.faculty
     */
    public void setIsFaculty(Boolean argFaculty) {
        this.faculty = argFaculty;
    }
     
    /**
     * Gets the value of graduateStudentStaff
     *
     * @return the value of graduateStudentStaff
     */
    public Boolean isGraduateStudentStaff() {
        return getIsGraduateStudentStaff();
    }

    /**
     * Gets the value of graduateStudentStaff
     *
     * @return the value of graduateStudentStaff
     */
    public Boolean getIsGraduateStudentStaff() {
        return this.graduateStudentStaff;
    }

    /**
     * Sets the value of graduateStudentStaff
     *
     * @param argGraduateStudentStaff Value to assign to this.graduateStudentStaff
     */
    public void setIsGraduateStudentStaff(Boolean argGraduateStudentStaff) {
        this.graduateStudentStaff = argGraduateStudentStaff;
    }

    /**
     * Gets the value of researchStaff
     *
     * @return the value of researchStaff
     */
    public Boolean isResearchStaff() {
        return getIsResearchStaff();
    }

    /**
     * Gets the value of researchStaff
     *
     * @return the value of researchStaff
     */
    public Boolean getIsResearchStaff() {
        return this.researchStaff;
    }

    /**
     * Sets the value of researchStaff
     *
     * @param argResearchStaff Value to assign to this.researchStaff
     */
    public void setIsResearchStaff(Boolean argResearchStaff) {
        this.researchStaff = argResearchStaff;
    }

    /**
     * Gets the value of serviceStaff
     *
     * @return the value of serviceStaff
     */
    public Boolean isServiceStaff() {
        return getIsServiceStaff();
    }

    /**
     * Gets the value of serviceStaff
     *
     * @return the value of serviceStaff
     */
    public Boolean getIsServiceStaff() {
        return this.serviceStaff;
    }

    /**
     * Sets the value of serviceStaff
     *
     * @param argServiceStaff Value to assign to this.serviceStaff
     */
    public void setIsServiceStaff(Boolean argServiceStaff) {
        this.serviceStaff = argServiceStaff;
    }

    /**
     * Gets the value of supportStaff
     *
     * @return the value of supportStaff
     */
    public Boolean isSupportStaff() {
        return getIsSupportStaff();
    }

    /**
     * Gets the value of supportStaff
     *
     * @return the value of supportStaff
     */
    public Boolean getIsSupportStaff() {
        return this.supportStaff;
    }

    /**
     * Sets the value of supportStaff
     *
     * @param argSupportStaff Value to assign to this.supportStaff
     */
    public void setIsSupportStaff(Boolean argSupportStaff) {
        this.supportStaff = argSupportStaff;
    }

    /**
     * Gets the value of otherAcademicGroup
     *
     * @return the value of otherAcademicGroup
     */
    public Boolean isOtherAcademicGroup() {
        return getIsOtherAcademicGroup();
    }

    /**
     * Gets the value of otherAcademicGroup
     *
     * @return the value of otherAcademicGroup
     */
    public Boolean getIsOtherAcademicGroup() {
        return this.otherAcademicGroup;
    }

    /**
     * Sets the value of otherAcademicGroup
     *
     * @param argOtherAcademicGroup Value to assign to this.otherAcademicGroup
     */
    public void setIsOtherAcademicGroup(Boolean argOtherAcademicGroup) {
        this.otherAcademicGroup = argOtherAcademicGroup;
    }

    /**
     * Gets the value of medicalStaff
     *
     * @return the value of medicalStaff
     */
    public Boolean isMedicalStaff() {
        return getIsMedicalStaff();
    }

    /**
     * Gets the value of medicalStaff
     *
     * @return the value of medicalStaff
     */
    public Boolean getIsMedicalStaff() {
        return this.medicalStaff;
    }

    /**
     * Sets the value of medicalStaff
     *
     * @param argMedicalStaff Value to assign to this.medicalStaff
     */
    public void setIsMedicalStaff(Boolean argMedicalStaff) {
        this.medicalStaff = argMedicalStaff;
    }

    /**
     * Gets the value of vacationAccrual
     *
     * @return the value of vacationAccrual
     */
    public Boolean isVacationAccrual() {
        return getIsVacationAccrual();
    }

    /**
     * Gets the value of vacationAccrual
     *
     * @return the value of vacationAccrual
     */
    public Boolean getIsVacationAccrual() {
        return this.vacationAccrual;
    }

    /**
     * Sets the value of vacationAccrual
     *
     * @param argVacationAccrual Value to assign to this.vacationAccrual
     */
    public void setIsVacationAccrual(Boolean argVacationAccrual) {
        this.vacationAccrual = argVacationAccrual;
    }

    /**
     * Gets the value of onSabbatical
     *
     * @return the value of onSabbatical
     */
    public Boolean isOnSabbatical() {
        return getIsOnSabbatical();
    }

    /**
     * Gets the value of onSabbatical
     *
     * @return the value of onSabbatical
     */
    public Boolean getIsOnSabbatical() {
        return this.onSabbatical;
    }

    /**
     * Sets the value of onSabbatical
     *
     * @param argOnSabbatical Value to assign to this.onSabbatical
     */
    public void setIsOnSabbatical(Boolean argOnSabbatical) {
        this.onSabbatical = argOnSabbatical;
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

	@Override 
	protected LinkedHashMap toStringMapper() {
   	    LinkedHashMap hashmap = new LinkedHashMap();
        hashmap.put("personId", getPersonId());
        hashmap.put("socialSecurityNumber", getSocialSecurityNumber());
        hashmap.put("lastName", getLastName());
        hashmap.put("firstName", getFirstName());
        hashmap.put("middleName", getMiddleName());
        hashmap.put("fullName", getFullName());
        hashmap.put("priorName", getPriorName());
        hashmap.put("userName", getUserName());
        hashmap.put("emailAddress", getEmailAddress());
        hashmap.put("dateOfBirth", getDateOfBirth());
        hashmap.put("age", getAge());
        hashmap.put("ageByFiscalYear", getAgeByFiscalYear());
        hashmap.put("gender", getGender());
        hashmap.put("race", getRace());
        hashmap.put("educationLevel", getEducationLevel());
        hashmap.put("degree", getDegree());
        hashmap.put("major", getMajor());
        hashmap.put("handicapped", isHandicapped());
        hashmap.put("handicapType", getHandicapType());
        hashmap.put("veteran", isVeteran());
        hashmap.put("veteranType", getVeteranType());
        hashmap.put("visaCode", getVisaCode());
        hashmap.put("visaType", getVisaType());
        hashmap.put("visaRenewalDate", getVisaRenewalDate());
        hashmap.put("hasVisa", getHasVisa());
        hashmap.put("officeLocation", getOfficeLocation());
        hashmap.put("officePhone", getOfficePhone());
        hashmap.put("secondaryOfficeLocation", getSecondaryOfficeLocation());
        hashmap.put("secondaryOfficePhone", getSecondaryOfficePhone());
        hashmap.put("school", getSchool());
        hashmap.put("yearGraduated", getYearGraduated());
        hashmap.put("directoryDepartment", getDirectoryDepartment());
        hashmap.put("saluation", getSaluation());
        hashmap.put("countryOfCitizenship", getCountryOfCitizenship());
        hashmap.put("primaryTitle", getPrimaryTitle());
        hashmap.put("directoryTitle", getDirectoryTitle());
        hashmap.put("homeUnit", getHomeUnit());
        hashmap.put("faculty", isFaculty());
        hashmap.put("graduateStudentStaff", isGraduateStudentStaff());
        hashmap.put("researchStaff", isResearchStaff());
        hashmap.put("serviceStaff", isServiceStaff());
        hashmap.put("supportStaff", isSupportStaff());
        hashmap.put("otherAcademicGroup", isOtherAcademicGroup());
        hashmap.put("medicalStaff", isMedicalStaff());
        hashmap.put("vacationAccrual", isVacationAccrual());
        hashmap.put("onSabbatical", isOnSabbatical());
        hashmap.put("idProvided", getIdProvided());
        hashmap.put("idVerified", getIdVerified());
        hashmap.put("addressLine1", getAddressLine1());
        hashmap.put("addressLine2", getAddressLine2());
        hashmap.put("addressLine3", getAddressLine3());
        hashmap.put("city", getCity());
        hashmap.put("county", getCounty());
        hashmap.put("state", getState());
        hashmap.put("postalCode", getPostalCode());
        hashmap.put("countryCode", getCountryCode());
        hashmap.put("faxNumber", getFaxNumber());
        hashmap.put("pagerNumber", getPagerNumber());
        hashmap.put("mobilePhoneNumber", getMobilePhoneNumber());
        hashmap.put("eraCommonsUserName", getEraCommonsUserName());
		return hashmap;
	}

    /**
     * Gets the userRoles attribute. 
     * @return Returns the userRoles.
     */
    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * Sets the userRoles attribute value.
     * @param userRoles The userRoles to set.
     */
    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}
