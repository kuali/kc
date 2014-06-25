package org.kuali.coeus.common.api.person.attr;

import java.util.Date;

public interface KcPersonExtendedAttributesContract {
    
    String getPersonId();
    
    Integer getAgeByFiscalYear();
    
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
    
    Date getVisaRenewalDate();
    
    Boolean getHasVisa();
    
    String getOfficeLocation();
    
    String getSecondaryOfficeLocation();
    
    String getSchool();
    
    String getYearGraduated();
    
    String getDirectoryDepartment();
    
    String getPrimaryTitle();
    
    String getDirectoryTitle();
    
    Boolean getVacationAccrualFlag();
    
    Boolean getOnSabbaticalFlag();
    
    String getIdProvided();
    
    String getIdVerified();
    
    String getCounty();
    
    CitizenshipTypeContract getCitizenshipType();

}
