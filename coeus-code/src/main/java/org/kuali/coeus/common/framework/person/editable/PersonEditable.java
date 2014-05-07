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
package org.kuali.coeus.common.framework.person.editable;

import java.sql.Date;

public interface PersonEditable {

    String getPersonId();

    Integer getRolodexId();

    String getFullName();

    void setRolodexId(Integer rolodexId);

    void setSocialSecurityNumber(String socialSecurityNumber);

    void setFirstName(String firstName);

    void setMiddleName(String middleName);

    void setFullName(String fullName);

    void setPriorName(String priorName);

    void setUserName(String userName);

    void setEmailAddress(String emailAddress);

    void setDateOfBirth(Date dateOfBirth);

    void setAge(Integer age);

    void setAgeByFiscalYear(Integer ageByFiscalYear);

    void setGender(String gender);

    void setRace(String race);

    void setEducationLevel(String educationLevel);

    void setDegree(String degree);

    void setMajor(String major);

    void setHandicappedFlag(Boolean handicappedFlag);

    void setHandicapType(String handicapType);

    void setVeteranFlag(Boolean veteranFlag);

    void setVeteranType(String veteranType);

    void setVisaCode(String visaCode);

    void setVisaType(String visaType);

    void setVisaRenewalDate(Date visaRenewalDate);

    void setHasVisa(Boolean hasVisa);

    void setOfficeLocation(String officeLocation);

    void setOfficePhone(String officePhone);

    void setSecondaryOfficeLocation(String secondaryOfficeLocation);

    void setSecondaryOfficePhone(String secondaryOfficePhone);

    void setSchool(String school);

    void setYearGraduated(String yearGraduated);

    void setDirectoryDepartment(String directoryDepartment);

    void setSaluation(String saluation);

    void setCountryOfCitizenship(String countryOfCitizenship);

    void setPrimaryTitle(String primaryTitle);

    void setDirectoryTitle(String directoryTitle);

    void setHomeUnit(String homeUnit);

    void setFacultyFlag(Boolean facultyFlag);

    void setGraduateStudentStaffFlag(Boolean graduateStudentStaffFlag);

    void setResearchStaffFlag(Boolean researchStaffFlag);

    void setServiceStaffFlag(Boolean serviceStaffFlag);

    void setSupportStaffFlag(Boolean supportStaffFlag);

    void setOtherAcademicGroupFlag(Boolean otherAcademicGroupFlag);

    void setMedicalStaffFlag(Boolean medicalStaffFlag);

    void setVacationAccrualFlag(Boolean vacationAccrualFlag);

    void setOnSabbaticalFlag(Boolean onSabbaticalFlag);

    void setIdProvided(String idProvided);

    void setIdVerified(String idVerified);

    void setAddressLine1(String addressLine1);

    void setAddressLine2(String addressLine2);

    void setAddressLine3(String addressLine3);

    void setCity(String city);

    void setCounty(String county);

    void setState(String state);

    void setPostalCode(String postalCode);

    void setCountryCode(String countryCode);

    void setFaxNumber(String faxNumber);

    void setPagerNumber(String pagerNumber);

    void setMobilePhoneNumber(String mobilePhoneNumber);

    void setEraCommonsUserName(String eraCommonsUserName);

    void setLastName(String lastName);
    
    void setCitizenshipTypeCode(Integer citizenshipTypeCode);

}
