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
