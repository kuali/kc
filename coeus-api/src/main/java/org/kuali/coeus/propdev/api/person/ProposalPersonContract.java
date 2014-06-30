package org.kuali.coeus.propdev.api.person;

import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.person.attr.CitizenshipTypeContract;
import org.kuali.coeus.propdev.api.hierarchy.HierarchicalProposal;
import org.kuali.coeus.propdev.api.person.creditsplit.ProposalPersonCreditSplitContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;
import java.util.List;

public interface ProposalPersonContract extends ProposalPersonLink, HierarchicalProposal {
    
    boolean getConflictOfInterestFlag();

    boolean isOtherSignificantContributorFlag();

    ScaleTwoDecimal getPercentageEffort();

    Boolean getFedrDebrFlag();

    Boolean getFedrDelqFlag();

    Integer getRolodexId();

    String getProposalPersonRoleId();
    
    Boolean getOptInUnitStatus();
    
    Boolean getOptInCertificationStatus();
    
    String getProjectRole();
    
    Integer getOrdinalPosition();
    
    String getPersonId();
    
    String getSocialSecurityNumber();
    
    String getLastName();
    
    String getFirstName();
    
    String getMiddleName();
    
    String getFullName();
    
    String getPriorName();
    
    String getUserName();
    
    String getEmailAddress();
    
    Date getDateOfBirth();
    
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
    
    Date getVisaRenewalDate();
    
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
    
    String getHomeUnit();
    
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
    
    String getState();
    
    String getPostalCode();
    
    String getCountryCode();
    
    String getFaxNumber();
    
    String getPagerNumber();
    
    String getMobilePhoneNumber();
    
    String getEraCommonsUserName();
    
    String getDivision();

    CitizenshipTypeContract getCitizenshipType();

    ProposalInvestigatorCertificationContract getCertification();

    List<? extends ProposalPersonYnqContract> getProposalPersonYnqs();

    List<? extends ProposalPersonUnitContract> getUnits();

    List<? extends ProposalPersonDegreeContract> getProposalPersonDegrees();

    List<? extends ProposalPersonCreditSplitContract> getCreditSplits();

    boolean isInvestigator();

    boolean isPrincipalInvestigator();

    boolean isCoInvestigator();

    boolean isKeyPerson();

    boolean isMultiplePi();

    KcPersonContract getPerson();
}
