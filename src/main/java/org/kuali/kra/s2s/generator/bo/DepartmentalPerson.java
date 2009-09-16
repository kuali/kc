/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.bo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.sql.Timestamp;

public class DepartmentalPerson implements java.io.Serializable {

    private static final String PERSON_ID = "personId";
    private static final String AW_PERSON_ID = "AW_PERSON_ID";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String USER_NAME = "userName";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String UPDATE_USER = "updateUser";
    private static final String UPDATE_TIME_STAMP = "updateTimestamp";
    private static final String AC_TYPE = "acType";
    private static final String SSN = "ssn";
    private static final String FULL_NAME = "fullName";
    private static final String PRIOR_NAME = "priorName";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String AGE = "age";
    private static final String AGE_BY_FISCAL_YEAR = "ageByFiscalYear";
    private static final String GENDER = "gender";
    private static final String DEGREE = "degree";
    private static final String RACE = "race";
    private static final String EDU_LEVEL = "eduLevel";
    private static final String MAJOR = "major";
    private static final String IS_HANDICAP = "isHandicap";
    private static final String HANDICAP_TYPE = "handiCapType";
    private static final String IS_VETERAN = "isVeteran";
    private static final String VETERAN_TYPE = "veteranType";
    private static final String VISA_CODE = "visaCode";
    private static final String VISA_TYPE = "visaType";
    private static final String VISA_REN_DATE = "visaRenDate";
    private static final String HAS_VISA = "hasVisa";
    private static final String OFFICE_LOCATION = "officeLocation";
    private static final String OFFICE_PHONE = "officePhone";
    private static final String SEC_OFFICE_LOCATION = "secOfficeLocation";
    private static final String SEC_OFFICE_PHONE = "secOfficePhone";
    private static final String SCHOOL = "school";
    private static final String YEAR_GRADUATED = "yearGraduated";
    private static final String DIR_DEPT = "dirDept";
    private static final String SALUTUATION = "saltuation";
    private static final String COUNTRY_CITIZENSHIP = "countryCitizenship";
    private static final String PRIMARY_TITLE = "primaryTitle";
    private static final String DIR_TITLE = "dirTitle";
    private static final String HOME_UNIT = "homeUnit";
    private static final String UNIT_NAME = "unitName";
    private static final String IS_FACULTY = "isFaculty";
    private static final String IS_GRADUATE_STUDENT_STAFF = "isGraduateStudentStaff";
    private static final String IS_RESEARCH_STAFF = "isResearchStaff";
    private static final String IS_SERVICE_STAFF = "isServiceStaff";
    private static final String IS_SUPPORT_STAFF = "isSupportStaff";
    private static final String IS_OTHER_ACADEMIC_GROUP = "isOtherAcademicGroup";
    private static final String IS_MEDICAL_STAFF = "isMedicalStaff";
    private static final String VACATION_ACCURAL = "vacationAccural";
    private static final String IS_ON_SABBATICAL = "isOnSabbatical";
    private static final String IS_PROVIDED = "isProvided";
    private static final String IS_VERIFIED = "isVerified";
    private static final String STATUS = "status";

    // Case #1602 Added for Person Enhancement - Contact Info Start 1
    private static final String ADDRESS1 = "address1";
    private static final String ADDRESS2 = "address2";
    private static final String ADDRESS3 = "address3";
    private static final String CITY = "city";
    private static final String COUNTY = "county";
    private static final String STATE = "state";
    private static final String POSTAL_CODE = "postalCode";
    private static final String COUNTRY_CODE = "countryCode";
    private static final String FAX_NO = "faxNumber";
    private static final String PAGER_NO = "pagerNumber";
    private static final String MOBILE_PH_NO = "mobilePhNumber";
    private static final String ERA_USER_NAME = "eraCommonsUsrName";
    // Case #1602 Added for Person Enhancement - Contact Info End 1

    // holds the person id
    private String personId;

    // holds awperson id used to check while updating
    private String awPersonId;
    // holds the person last name
    private String lastName;

    // holds the person first name.
    private String firstName;

    // holds the middle Name
    private String middleName;

    // holds the user name
    private String userName;

    // holds the date of birth
    private Date dateOfBirth;

    // holds update user id
    private String updateUser;

    // holds update timestamp
    private Timestamp updateTimestamp;

    // holds account type
    private String acType;

    // holds the ssn
    private String ssn;

    // holds the full name of the person
    private String fullName;

    // holds the prior name
    private String priorName;

    // holds the email address
    private String emailAddress;

    // holds the person age
    private int age;

    // holds the age by fiscal year
    private int ageByFiscalYear;

    // holds the person gender
    private String gender;

    // holds the person degree
    private String degree;

    // holds the person race
    private String race;

    // holds the person education level
    private String eduLevel;

    // holds the major subject in the degree
    private String major;

    // hold the person handicaped.
    private boolean isHandicap;

    // holds the person handicap type.
    private String handiCapType;

    // hold the person handicaped.
    private boolean isVeteran;

    // holds the person verteran type.
    private String veteranType;

    // holds the person visa code.
    private String visaCode;

    // holds the person visa type.
    private String visaType;

    // holds the visa renewal date
    private Date visaRenDate;

    // hold the person has visa.
    private boolean hasVisa;

    // holds the person office location
    private String officeLocation;

    // holds the person office phone
    private String officePhone;

    // holds the person secondary office location
    private String secOfficeLocation;

    // holds the person secondary office phone
    private String secOfficePhone;

    // holds the person school
    private String school;

    // holds the person graduation year
    private String yearGraduated;

    // holds the person directory department
    private String dirDept;

    // holds the person saltuation
    private String saltuation;

    // holds the person country of citizen ship
    private String countryCitizenship;

    // holds the person primary title
    private String primaryTitle;

    // holds the person directory title
    private String dirTitle;

    // holds the person home unit
    private String homeUnit;

    // holds the person home unit name
    private String unitName;

    // hold the person faculty.
    private boolean isFaculty;

    // hold the person is graduate student staff.
    private boolean isGraduateStudentStaff;

    // hold the person is research staff.
    private boolean isResearchStaff;

    // hold the person is service staff.
    private boolean isServiceStaff;

    // hold the person is support staff.
    private boolean isSupportStaff;

    // hold the person is other academic group.
    private boolean isOtherAcademicGroup;

    // hold the person is medical staff.
    private boolean isMedicalStaff;

    // hold the person is vacation accural.
    private boolean vacationAccural;

    // hold the person is on sabbatical.
    private boolean isOnSabbatical;

    // holds the person is provided
    private String isProvided;

    // holds the person is verified
    private String isVerified;

    // holds the person status
    private String status;

    private PropertyChangeSupport propertySupport;

    // Case #1602 Added for Person Enhancement - Contact Info Start 2
    // holds the person address1
    private String address1;

    // holds the person address2
    private String address2;

    // holds the person address3
    private String address3;

    // holds the person city
    private String city;

    // holds the person county
    private String county;

    // holds the person State
    private String state;

    // holds the person postalCode
    private String postalCode;

    // holds the person CountryCode
    private String countryCode;

    // holds the person faxNumber
    private String faxNumber;

    // holds the person pagerNumber
    private String pagerNumber;

    // holds the person mobilePhNumber
    private String mobilePhNumber;

    // holds the person eraCommonsUsrName
    private String eraCommonsUsrName;


    public DepartmentalPerson() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public DepartmentalPerson(DepartmentalPerson copyBean) {

        propertySupport = new PropertyChangeSupport(this);

        setAcType(copyBean.getAcType());
        setAge(copyBean.getAge());
        setAgeByFiscalYear(copyBean.getAgeByFiscalYear());
        setAWPersonId(copyBean.getAWPersonId());
        setCountryCitizenship(copyBean.getCountryCitizenship());
        setDateOfBirth(copyBean.getDateOfBirth());
        setDegree(copyBean.getDegree());
        setDirDept(copyBean.getDirDept());
        setDirTitle(copyBean.getDirTitle());
        setEduLevel(copyBean.getEduLevel());
        setEmailAddress(copyBean.getEmailAddress());
        setFaculty(copyBean.isFaculty());
        setFirstName(copyBean.getFirstName());
        setFullName(copyBean.getFullName());
        setGender(copyBean.getGender());
        setGraduateStudentStaff(copyBean.isGraduateStudentStaff());
        setHandicap(copyBean.isHandicap());
        setHandiCapType(copyBean.getHandiCapType());
        setHasVisa(copyBean.isHasVisa());
        setHomeUnit(copyBean.getHomeUnit());
        setLastName(copyBean.getLastName());
        setMajor(copyBean.getMajor());
        setMedicalStaff(copyBean.getMedicalStaff());
        setMiddleName(copyBean.getMiddleName());
        setOfficeLocation(copyBean.getOfficeLocation());
        setOfficePhone(copyBean.getOfficePhone());
        setOnSabbatical(copyBean.getOnSabbatical());
        setOtherAcademicGroup(copyBean.getOtherAcademicGroup());
        setPersonId(copyBean.getPersonId());
        setPrimaryTitle(copyBean.getPrimaryTitle());
        setPriorName(copyBean.getPriorName());
        setProvided(copyBean.getProvided());
        setRace(copyBean.getRace());
        setResearchStaff(copyBean.getResearchStaff());
        setSaltuation(copyBean.getSaltuation());
        setSchool(copyBean.getSchool());
        setSecOfficeLocation(copyBean.getSecOfficeLocation());
        setSecOfficePhone(copyBean.getSecOfficePhone());
        setServiceStaff(copyBean.getServiceStaff());
        setSsn(copyBean.getSsn());
        setSupportStaff(copyBean.getSupportStaff());
        setUnitName(copyBean.getUnitName());
        setUpdateTimestamp(copyBean.getUpdateTimestamp());
        setUpdateUser(copyBean.getUpdateUser());
        setUserName(copyBean.getUserName());
        setVacationAccural(copyBean.getVacationAccural());
        setVerified(copyBean.getVerified());
        setVeteran(copyBean.isVeteran());
        setVeteranType(copyBean.getVeteranType());
        setVisaCode(copyBean.getVisaCode());
        setVisaRenDate(copyBean.getVisaRenDate());
        setVisaType(copyBean.getVisaType());
        setYearGraduated(copyBean.getYearGraduated());

        setAddress1(copyBean.getAddress1());
        setAddress2(copyBean.getAddress2());
        setAddress3(copyBean.getAddress3());
        setCity(copyBean.getCity());
        setCounty(copyBean.getCounty());
        setState(copyBean.getState());
        setPostalCode(copyBean.getPostalCode());
        setCountryCode(copyBean.getCountryCode());
        setFaxNumber(copyBean.getFaxNumber());
        setPagerNumber(copyBean.getPagerNumber());
        setMobilePhNumber(copyBean.getMobilePhNumber());
        setEraCommonsUsrName(copyBean.getEraCommonsUsrName());

    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String newPersonId) {
        String oldPersonId = personId;
        this.personId = newPersonId;
        propertySupport.firePropertyChange(PERSON_ID, oldPersonId, personId);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        String oldLastName = lastName;
        this.lastName = newLastName;
        propertySupport.firePropertyChange(LAST_NAME, oldLastName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        String oldFirstName = firstName;
        this.firstName = newFirstName;
        propertySupport.firePropertyChange(FIRST_NAME, oldFirstName, firstName);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String newMiddleName) {
        String oldMiddleName = middleName;
        this.middleName = newMiddleName;
        propertySupport.firePropertyChange(MIDDLE_NAME, oldMiddleName, middleName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String newUserName) {
        String oldUserName = userName;
        this.userName = newUserName;
        propertySupport.firePropertyChange(USER_NAME, oldUserName, userName);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date newDateOfBirth) {
        Date oldDateOfBirth = dateOfBirth;
        this.dateOfBirth = newDateOfBirth;
        propertySupport.firePropertyChange(DATE_OF_BIRTH, oldDateOfBirth, dateOfBirth);
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String newUpdateUser) {
        String oldUpdateUser = updateUser;
        this.updateUser = newUpdateUser;
        propertySupport.firePropertyChange(UPDATE_USER, oldUpdateUser, updateUser);
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp newUpdateTimestamp) {
        Timestamp oldUpdateTimeStamp = updateTimestamp;
        this.updateTimestamp = newUpdateTimestamp;
        propertySupport.firePropertyChange(UPDATE_TIME_STAMP, oldUpdateTimeStamp, updateTimestamp);
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String newAcType) {
        String oldAcType = acType;
        this.acType = newAcType;
        propertySupport.firePropertyChange(AC_TYPE, oldAcType, acType);
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String newSsn) {
        String oldSsn = ssn;
        this.ssn = newSsn;
        propertySupport.firePropertyChange(SSN, oldSsn, ssn);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String newFullName) {
        String oldFulName = fullName;
        this.fullName = newFullName;
        propertySupport.firePropertyChange(FULL_NAME, oldFulName, fullName);
    }

    public String getPriorName() {
        return priorName;
    }

    public void setPriorName(String newPriorName) {
        String oldPriorName = priorName;
        this.priorName = newPriorName;
        propertySupport.firePropertyChange(PRIOR_NAME, oldPriorName, priorName);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String newEmailAddress) {
        String oldEmailAddress = emailAddress;
        this.emailAddress = newEmailAddress;
        propertySupport.firePropertyChange(EMAIL_ADDRESS, oldEmailAddress, emailAddress);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        int oldAge = age;
        this.age = newAge;
        propertySupport.firePropertyChange(AGE, oldAge, age);
    }

    public int getAgeByFiscalYear() {
        return ageByFiscalYear;
    }

    public void setAgeByFiscalYear(int newAgeByFiscalYear) {
        int oldAgeByFiscalYear = ageByFiscalYear;
        this.ageByFiscalYear = newAgeByFiscalYear;
        propertySupport.firePropertyChange(AGE_BY_FISCAL_YEAR, oldAgeByFiscalYear, ageByFiscalYear);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String newGender) {
        String oldGender = gender;
        this.gender = newGender;
        propertySupport.firePropertyChange(GENDER, oldGender, gender);
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String newDegree) {
        String oldDegree = degree;
        this.degree = newDegree;
        propertySupport.firePropertyChange(DEGREE, oldDegree, degree);
    }

    public String getRace() {
        return race;
    }

    public void setRace(String newRace) {
        String oldRace = race;
        this.race = newRace;
        propertySupport.firePropertyChange(RACE, oldRace, race);
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String newEduLevel) {
        String oldEduLevel = eduLevel;
        this.eduLevel = newEduLevel;
        propertySupport.firePropertyChange(EDU_LEVEL, oldEduLevel, eduLevel);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String newMajor) {
        String oldMajor = major;
        this.major = newMajor;
        propertySupport.firePropertyChange(MAJOR, oldMajor, major);
    }

    public boolean isHandicap() {
        return isHandicap;
    }

    public void setHandicap(boolean newIsHandicap) {
        boolean oldIsHandicap = isHandicap;
        this.isHandicap = newIsHandicap;
        propertySupport.firePropertyChange(IS_HANDICAP, oldIsHandicap, isHandicap);
    }

    public String getHandiCapType() {
        return handiCapType;
    }

    public void setHandiCapType(String newHandiCapType) {
        String oldHandicapType = handiCapType;
        this.handiCapType = newHandiCapType;
        propertySupport.firePropertyChange(HANDICAP_TYPE, oldHandicapType, handiCapType);
    }

    public boolean isVeteran() {
        return isVeteran;
    }

    public void setVeteran(boolean newIsVeteran) {
        boolean oldIsVeteran = isVeteran;
        this.isVeteran = newIsVeteran;
        propertySupport.firePropertyChange(IS_VETERAN, oldIsVeteran, isVeteran);
    }

    public String getVeteranType() {
        return veteranType;
    }

    public void setVeteranType(String newVeteranType) {
        String oldVeteranType = veteranType;
        this.veteranType = newVeteranType;
        propertySupport.firePropertyChange(VETERAN_TYPE, oldVeteranType, veteranType);
    }

    public String getVisaCode() {
        return visaCode;
    }

    public void setVisaCode(String newVisaCode) {
        String oldVisaCode = visaCode;
        this.visaCode = newVisaCode;
        propertySupport.firePropertyChange(VISA_CODE, oldVisaCode, visaCode);
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String newVisaType) {
        String oldVisaType = visaType;
        this.visaType = newVisaType;
        propertySupport.firePropertyChange(VISA_TYPE, oldVisaType, visaType);
    }

    public Date getVisaRenDate() {
        return visaRenDate;
    }

    public void setVisaRenDate(Date newVisaRenDate) {
        Date oldVisaRenDate = visaRenDate;
        this.visaRenDate = newVisaRenDate;
        propertySupport.firePropertyChange(VISA_REN_DATE, oldVisaRenDate, visaRenDate);
    }

    public boolean isHasVisa() {
        return hasVisa;
    }

    public void setHasVisa(boolean newHasVisa) {
        boolean oldHasVisa = hasVisa;
        this.hasVisa = newHasVisa;
        propertySupport.firePropertyChange(HAS_VISA, oldHasVisa, hasVisa);
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String newOfficeLocation) {
        String oldOfficeLocation = officeLocation;
        this.officeLocation = newOfficeLocation;
        propertySupport.firePropertyChange(OFFICE_LOCATION, oldOfficeLocation, officeLocation);
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String newOfficePhone) {
        String oldOfficePhone = officePhone;
        this.officePhone = newOfficePhone;
        propertySupport.firePropertyChange(OFFICE_PHONE, oldOfficePhone, officePhone);
    }

    public String getSecOfficeLocation() {
        return secOfficeLocation;
    }

    public void setSecOfficeLocation(String newSecOfficeLocation) {
        String oldSecOfficeLocation = secOfficeLocation;
        this.secOfficeLocation = newSecOfficeLocation;
        propertySupport.firePropertyChange(SEC_OFFICE_LOCATION, oldSecOfficeLocation, secOfficeLocation);
    }

    public String getSecOfficePhone() {
        return secOfficePhone;
    }

    public void setSecOfficePhone(String newSecOfficePhone) {
        String oldSecOfficePhone = secOfficePhone;
        this.secOfficePhone = newSecOfficePhone;
        propertySupport.firePropertyChange(SEC_OFFICE_PHONE, oldSecOfficePhone, secOfficePhone);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String newSchool) {
        String oldSchool = school;
        this.school = newSchool;
        propertySupport.firePropertyChange(SCHOOL, oldSchool, school);
    }

    public String getYearGraduated() {
        return yearGraduated;
    }

    public void setYearGraduated(String newYearGraduated) {
        String oldYearGraduated = yearGraduated;
        this.yearGraduated = newYearGraduated;
        propertySupport.firePropertyChange(YEAR_GRADUATED, oldYearGraduated, yearGraduated);
    }

    public String getDirDept() {
        return dirDept;
    }

    public void setDirDept(String newDirDept) {
        String oldDirDept = dirDept;
        this.dirDept = newDirDept;
        propertySupport.firePropertyChange(DIR_DEPT, oldDirDept, dirDept);
    }

    public String getSaltuation() {
        return saltuation;
    }

    public void setSaltuation(String newSaltuation) {
        String oldSaltuation = saltuation;
        this.saltuation = newSaltuation;
        propertySupport.firePropertyChange(SALUTUATION, oldSaltuation, saltuation);
    }

    public String getCountryCitizenship() {
        return countryCitizenship;
    }

    public void setCountryCitizenship(String newCountryCitizenship) {
        String oldCountryCitizenship = countryCitizenship;
        this.countryCitizenship = newCountryCitizenship;
        propertySupport.firePropertyChange(COUNTRY_CITIZENSHIP, oldCountryCitizenship, countryCitizenship);
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String newPrimaryTitle) {
        String oldPrimaryTitle = primaryTitle;
        this.primaryTitle = newPrimaryTitle;
        propertySupport.firePropertyChange(PRIMARY_TITLE, oldPrimaryTitle, primaryTitle);
    }

    public String getDirTitle() {
        return dirTitle;
    }

    public void setDirTitle(String newDirTitle) {
        String oldDirTitle = dirTitle;
        this.dirTitle = newDirTitle;
        propertySupport.firePropertyChange(DIR_TITLE, oldDirTitle, dirTitle);
    }

    public String getHomeUnit() {
        return homeUnit;
    }

    public void setHomeUnit(String newHomeUnit) {
        String oldHomeUnit = homeUnit;
        this.homeUnit = newHomeUnit;
        propertySupport.firePropertyChange(HOME_UNIT, oldHomeUnit, homeUnit);
    }

    public boolean isFaculty() {
        return isFaculty;
    }

    public void setFaculty(boolean newIsFaculty) {
        boolean oldIsFaculty = isFaculty;
        this.isFaculty = newIsFaculty;
        propertySupport.firePropertyChange(IS_FACULTY, oldIsFaculty, isFaculty);
    }

    public boolean isGraduateStudentStaff() {
        return isGraduateStudentStaff;
    }

    public void setGraduateStudentStaff(boolean newIsGraduateStudentStaff) {
        boolean oldIsGraduateStudentStaff = isGraduateStudentStaff;
        this.isGraduateStudentStaff = newIsGraduateStudentStaff;
        propertySupport.firePropertyChange(IS_GRADUATE_STUDENT_STAFF, oldIsGraduateStudentStaff, isGraduateStudentStaff);
    }

    public boolean getResearchStaff() {
        return isResearchStaff;
    }

    public void setResearchStaff(boolean newIsResearchStaff) {
        boolean oldIsResearchStaff = isResearchStaff;
        this.isResearchStaff = newIsResearchStaff;
        propertySupport.firePropertyChange(IS_RESEARCH_STAFF, oldIsResearchStaff, isResearchStaff);
    }

    public boolean getServiceStaff() {
        return isServiceStaff;
    }

    public void setServiceStaff(boolean newIsServiceStaff) {
        boolean oldIsServiceStaff = isServiceStaff;
        this.isServiceStaff = newIsServiceStaff;
        propertySupport.firePropertyChange(IS_SERVICE_STAFF, oldIsServiceStaff, isServiceStaff);
    }

    public boolean getSupportStaff() {
        return isSupportStaff;
    }

    public void setSupportStaff(boolean newIsSupportStaff) {
        boolean oldIsSupportStaff = isSupportStaff;
        this.isSupportStaff = newIsSupportStaff;
        propertySupport.firePropertyChange(IS_SUPPORT_STAFF, oldIsSupportStaff, isSupportStaff);
    }

    public boolean getOtherAcademicGroup() {
        return isOtherAcademicGroup;
    }

    public void setOtherAcademicGroup(boolean newIsOtherAcademicGroup) {
        boolean oldIsOtherAcademicGroup = isOtherAcademicGroup;
        this.isOtherAcademicGroup = newIsOtherAcademicGroup;
        propertySupport.firePropertyChange(IS_OTHER_ACADEMIC_GROUP, oldIsOtherAcademicGroup, isOtherAcademicGroup);
    }

    public boolean getMedicalStaff() {
        return isMedicalStaff;
    }

    public void setMedicalStaff(boolean newIsMedicalStaff) {
        boolean oldIsMedicalStaff = isMedicalStaff;
        this.isMedicalStaff = newIsMedicalStaff;
        propertySupport.firePropertyChange(IS_MEDICAL_STAFF, oldIsMedicalStaff, isMedicalStaff);
    }

    public boolean getVacationAccural() {
        return vacationAccural;
    }

    public void setVacationAccural(boolean newVacationAccural) {
        boolean oldVacationAccural = vacationAccural;
        this.vacationAccural = newVacationAccural;
        propertySupport.firePropertyChange(VACATION_ACCURAL, oldVacationAccural, vacationAccural);
    }

    public boolean getOnSabbatical() {
        return isOnSabbatical;
    }

    public void setOnSabbatical(boolean newIsOnSabbatical) {
        boolean oldIsOnSabbatical = isOnSabbatical;
        this.isOnSabbatical = newIsOnSabbatical;
        propertySupport.firePropertyChange(IS_ON_SABBATICAL, oldIsOnSabbatical, isOnSabbatical);
    }

    public String getProvided() {
        return isProvided;
    }

    public void setProvided(String newIsProvided) {
        String oldIsProvided = isProvided;
        this.isProvided = newIsProvided;
        propertySupport.firePropertyChange(IS_PROVIDED, oldIsProvided, isProvided);
    }

    public String getVerified() {
        return isVerified;
    }

    public void setVerified(String newIsVerified) {
        String oldIsVerified = isVerified;
        this.isVerified = newIsVerified;
        propertySupport.firePropertyChange(IS_VERIFIED, oldIsVerified, isVerified);
    }

    /**
     * Getter for property unitName.
     * 
     * @return Value of property unitName.
     */
    public java.lang.String getUnitName() {
        return unitName;
    }

    /**
     * Setter for property unitName.
     * 
     * @param unitName New value of property unitName.
     */
    public void setUnitName(java.lang.String newUnitName) {
        String oldUnitName = unitName;
        this.unitName = newUnitName;
        propertySupport.firePropertyChange(UNIT_NAME, oldUnitName, unitName);
    }

    /**
     * Getter for property awPersonId.
     * 
     * @return Value of property awPersonId.
     */
    public String getAWPersonId() {
        return awPersonId;
    }

    /**
     * Setter for property awPersonId.
     * 
     * @param awPersonId New value of property awPersonId.
     */
    public void setAWPersonId(String newAwPersonId) {
        String oldAwPersonId = awPersonId;
        this.awPersonId = newAwPersonId;
        propertySupport.firePropertyChange(AW_PERSON_ID, oldAwPersonId, awPersonId);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    /**
     * Getter for property address1.
     * 
     * @return Value of property address1.
     */
    public java.lang.String getAddress1() {
        return address1;
    }

    /**
     * Setter for property address1.
     * 
     * @param address1 New value of property address1.
     */
    public void setAddress1(java.lang.String newaddress1) {
        String oldAddress1 = address1;
        this.address1 = newaddress1;
        propertySupport.firePropertyChange(ADDRESS1, oldAddress1, address1);
    }

    /**
     * Getter for property address2.
     * 
     * @return Value of property address2.
     */
    public java.lang.String getAddress2() {
        return address2;
    }

    /**
     * Setter for property address2.
     * 
     * @param address2 New value of property address2.
     */
    public void setAddress2(java.lang.String newAddress2) {
        String oldAddress2 = address2;
        this.address2 = newAddress2;
        propertySupport.firePropertyChange(ADDRESS2, oldAddress2, address2);
    }

    /**
     * Getter for property address3.
     * 
     * @return Value of property address3.
     */
    public java.lang.String getAddress3() {
        return address3;
    }

    /**
     * Setter for property address3.
     * 
     * @param address3 New value of property address3.
     */
    public void setAddress3(java.lang.String newAddress3) {
        String oldAddress3 = address3;
        this.address3 = newAddress3;
        propertySupport.firePropertyChange(ADDRESS3, oldAddress3, address3);
    }

    /**
     * Getter for property city.
     * 
     * @return Value of property city.
     */
    public java.lang.String getCity() {
        return city;
    }

    /**
     * Setter for property city.
     * 
     * @param city New value of property city.
     */
    public void setCity(java.lang.String newCity) {
        String oldCity = city;
        this.city = newCity;
        propertySupport.firePropertyChange(CITY, oldCity, city);
    }

    /**
     * Getter for property county.
     * 
     * @return Value of property county.
     */
    public java.lang.String getCounty() {
        return county;
    }

    /**
     * Setter for property county.
     * 
     * @param county New value of property county.
     */
    public void setCounty(java.lang.String newCounty) {
        String oldCouonty = county;
        this.county = newCounty;
        propertySupport.firePropertyChange(COUNTY, oldCouonty, county);
    }

    /**
     * Getter for property state.
     * 
     * @return Value of property state.
     */
    public java.lang.String getState() {
        return state;
    }

    /**
     * Setter for property state.
     * 
     * @param state New value of property state.
     */
    public void setState(java.lang.String newState) {
        String oldState = state;
        this.state = newState;
        propertySupport.firePropertyChange(STATE, oldState, state);
    }

    /**
     * Getter for property postalCode.
     * 
     * @return Value of property postalCode.
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for property postalCode.
     * 
     * @param postalCode New value of property postalCode.
     */
    public void setPostalCode(java.lang.String newPostalCode) {
        String oldPostalCode = postalCode;
        this.postalCode = newPostalCode;
        propertySupport.firePropertyChange(POSTAL_CODE, oldPostalCode, postalCode);
    }

    /**
     * Getter for property CountryCode.
     * 
     * @return Value of property CountryCode.
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }

    /**
     * Setter for property CountryCode.
     * 
     * @param CountryCode New value of property CountryCode.
     */
    public void setCountryCode(java.lang.String newCountryCode) {
        String oldCountryCode = countryCode;
        this.countryCode = newCountryCode;
        propertySupport.firePropertyChange(COUNTRY_CODE, oldCountryCode, countryCode);
    }

    /**
     * Getter for property faxNumber.
     * 
     * @return Value of property faxNumber.
     */
    public java.lang.String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Setter for property faxNumber.
     * 
     * @param faxNumber New value of property faxNumber.
     */
    public void setFaxNumber(java.lang.String newFaxNumber) {
        String oldFaxNumber = faxNumber;
        this.faxNumber = newFaxNumber;
        propertySupport.firePropertyChange(FAX_NO, oldFaxNumber, faxNumber);
    }

    /**
     * Getter for property pagerNumber.
     * 
     * @return Value of property pagerNumber.
     */
    public java.lang.String getPagerNumber() {
        return pagerNumber;
    }

    /**
     * Setter for property pagerNumber.
     * 
     * @param pagerNumber New value of property pagerNumber.
     */
    public void setPagerNumber(java.lang.String newPagerNumber) {
        String oldPagerNumber = pagerNumber;
        this.pagerNumber = newPagerNumber;
        propertySupport.firePropertyChange(PAGER_NO, oldPagerNumber, pagerNumber);
    }

    /**
     * Getter for property mobilePhNumber.
     * 
     * @return Value of property mobilePhNumber.
     */
    public java.lang.String getMobilePhNumber() {
        return mobilePhNumber;
    }

    /**
     * Setter for property mobilePhNumber.
     * 
     * @param mobilePhNumber New value of property mobilePhNumber.
     */
    public void setMobilePhNumber(java.lang.String newMobilePhNumber) {
        String oldMobilePhNumber = mobilePhNumber;
        this.mobilePhNumber = newMobilePhNumber;
        propertySupport.firePropertyChange(MOBILE_PH_NO, oldMobilePhNumber, mobilePhNumber);
    }

    /**
     * Getter for property eraCommonsUsrName.
     * 
     * @return Value of property eraCommonsUsrName.
     */
    public java.lang.String getEraCommonsUsrName() {
        return eraCommonsUsrName;
    }

    /**
     * Setter for property eraCommonsUsrName.
     * 
     * @param eraCommonsUsrName New value of property eraCommonsUsrName.
     */
    public void setEraCommonsUsrName(java.lang.String newEraCommonsUsrName) {
        String oldEraCommonsUsrName = eraCommonsUsrName;
        this.eraCommonsUsrName = newEraCommonsUsrName;
        propertySupport.firePropertyChange(ERA_USER_NAME, oldEraCommonsUsrName, eraCommonsUsrName);
    }

    /**
     * Getter for property status.
     * 
     * @return Value of property active.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for property status.
     * 
     * @param newStatus New value of property status.
     */
    public void setStatus(String newStatus) {
        String oldStatus = status;
        this.status = newStatus;
        propertySupport.firePropertyChange(STATUS, oldStatus, newStatus);
    }
}
