package org.kuali.coeus.common.api.person;

import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.sys.api.model.Inactivatable;

public interface KcPersonContract extends Inactivatable {

    String getPersonId(); 

    String getSocialSecurityNumber();

    String getLastName();

    String getFirstName(); 

    String getMiddleName(); 

    String getFullName(); 

    String getPriorName();

    String getUserName(); 

    String getEmailAddress();

    String getDateOfBirth(); 

    Integer getAge();

    Integer getAgeByFiscalYear(); 

    String getGender(); 

    String getRace(); 

    String getEducationLevel(); 

    String getDegree(); 

    String getMajor(); 

    Boolean getHandicappedFlag(); 

    String getHandicapType(); 

    Boolean getVeteranFlag();

    String getVeteranType(); 

    String getVisaCode();

    String getVisaType(); 

    String getVisaRenewalDate();

    Boolean getHasVisa(); 

    String getOfficeLocation(); 

    String getOfficePhone();

    String getSecondaryOfficeLocation(); 

    String getSecondaryOfficePhone();

    String getSchool(); 

    String getYearGraduated(); 

    String getDirectoryDepartment(); 

    String getSaluation(); 

    String getCountryOfCitizenship(); 

    String getPrimaryTitle(); 

    String getDirectoryTitle(); 

    Boolean getFacultyFlag(); 

    Boolean getGraduateStudentStaffFlag(); 

    Boolean getResearchStaffFlag(); 

    Boolean getServiceStaffFlag(); 

    Boolean getSupportStaffFlag(); 

    Boolean getOtherAcademicGroupFlag(); 

    Boolean getMedicalStaffFlag(); 

    Boolean getVacationAccrualFlag(); 

    Boolean getOnSabbaticalFlag(); 

    String getIdProvided(); 

    String getIdVerified(); 

    String getAddressLine1(); 

    String getAddressLine2(); 

    String getAddressLine3(); 

    String getCity(); 

    String getCounty(); 

    Integer getCitizenshipTypeCode(); 

    String getState(); 

    String getPostalCode(); 

    String getCountryCode(); 

    String getFaxNumber(); 

    String getPagerNumber(); 

    String getMobilePhoneNumber(); 

    String getEraCommonsUserName();

    String getIdentifier(); 

    UnitContract getUnit();

    String getPhoneNumber(); 

    String getContactOrganizationName(); 

    String getOrganizationIdentifier(); 

    String getCampusCode();
}
