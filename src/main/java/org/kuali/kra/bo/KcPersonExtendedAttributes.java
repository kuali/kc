/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.rice.krad.bo.PersistableAttachment;
import org.springframework.util.AutoPopulatingList;

/**
 * Class contains attributes related to a KIM entity that do not currently have a home inside of KIM.
 */
public class KcPersonExtendedAttributes extends KraPersistableBusinessObjectBase implements PersistableAttachment {

    private static final long serialVersionUID = -6236580785268966391L;

    private String personId;

    private Integer ageByFiscalYear;

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

    private String secondaryOfficeLocation;

    private String school;

    private String yearGraduated;

    private String directoryDepartment;

    private String primaryTitle;

    private String directoryTitle;

    private Boolean vacationAccrualFlag;

    private Boolean onSabbaticalFlag;

    private String idProvided;

    private String idVerified;

    private String county;

    private Integer citizenshipTypeCode;

    private String multiCampusPrincipalId;

    private String multiCampusPrincipalName;
    private Date salaryAnniversaryDate;

    private String biosketchDescription;

    private String fileName;

    private String contentType;

    private byte[] attachmentContent;

    private transient FormFile templateFile;

    private List<PersonDegree> personDegrees = new AutoPopulatingList<PersonDegree>(PersonDegree.class);

    private List<PersonAppointment> personAppointments = new AutoPopulatingList<PersonAppointment>(PersonAppointment.class);

    private CitizenshipType citizenshipType;

    /**
     * Gets the value of personId which is actually the KIM principal id.
     * @return the value of personId
     */
    public String getPersonId() {
        return this.personId;
    }

    /**
     * Sets the value of personId which is actually the KIM principal id.
     * @param personId Value to assign to this.personId
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     * Gets the value of ageByFiscalYear.
     * @return the value of ageByFiscalYear
     */
    public Integer getAgeByFiscalYear() {
        return this.ageByFiscalYear;
    }

    /**
     * Sets the value of ageByFiscalYear.
     * @param argAgeByFiscalYear Value to assign to this.ageByFiscalYear
     */
    public void setAgeByFiscalYear(Integer argAgeByFiscalYear) {
        this.ageByFiscalYear = argAgeByFiscalYear;
    }

    /**
     * Gets the value of race.
     * @return the value of race
     */
    public String getRace() {
        return this.race;
    }

    /**
     * Sets the value of race.
     * @param argRace Value to assign to this.race
     */
    public void setRace(String argRace) {
        this.race = argRace;
    }

    /**
     * Gets the value of educationLevel.
     * @return the value of educationLevel
     */
    public String getEducationLevel() {
        return this.educationLevel;
    }

    /**
     * Sets the value of educationLevel.
     * @param argEducationLevel Value to assign to this.educationLevel
     */
    public void setEducationLevel(String argEducationLevel) {
        this.educationLevel = argEducationLevel;
    }

    /**
     * Gets the value of degree.
     * @return the value of degree
     */
    public String getDegree() {
        return this.degree;
    }

    /**
     * Sets the value of degree.
     * @param argDegree Value to assign to this.degree
     */
    public void setDegree(String argDegree) {
        this.degree = argDegree;
    }

    /**
     * Gets the value of major.
     * @return the value of major
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * Sets the value of major.
     * @param argMajor Value to assign to this.major
     */
    public void setMajor(String argMajor) {
        this.major = argMajor;
    }

    /**
     * Gets the value of handicapped.
     * @return the value of handicapped
     */
    public Boolean getHandicappedFlag() {
        return this.handicappedFlag;
    }

    /**
     * Sets the value of handicapped.
     * @param argHandicapped Value to assign to this.handicapped
     */
    public void setHandicappedFlag(Boolean argHandicapped) {
        this.handicappedFlag = argHandicapped;
    }

    /**
     * Gets the value of handicapType.
     * @return the value of handicapType
     */
    public String getHandicapType() {
        return this.handicapType;
    }

    /**
     * Sets the value of handicapType.
     * @param argHandicapType Value to assign to this.handicapType
     */
    public void setHandicapType(String argHandicapType) {
        this.handicapType = argHandicapType;
    }

    /**
     * Gets the value of veteran.
     * @return the value of veteran
     */
    public Boolean getVeteranFlag() {
        return this.veteranFlag;
    }

    /**
     * Sets the value of veteran.
     * @param argVeteran Value to assign to this.veteran
     */
    public void setVeteranFlag(Boolean argVeteran) {
        this.veteranFlag = argVeteran;
    }

    /**
     * Gets the value of veteranType.
     * @return the value of veteranType
     */
    public String getVeteranType() {
        return this.veteranType;
    }

    /**
     * Sets the value of veteranType.
     * @param argVeteranType Value to assign to this.veteranType
     */
    public void setVeteranType(String argVeteranType) {
        this.veteranType = argVeteranType;
    }

    /**
     * Gets the value of visaCode.
     * @return the value of visaCode
     */
    public String getVisaCode() {
        return this.visaCode;
    }

    /**
     * Sets the value of visaCode.
     * @param argVisaCode Value to assign to this.visaCode
     */
    public void setVisaCode(String argVisaCode) {
        this.visaCode = argVisaCode;
    }

    /**
     * Gets the value of visaType.
     * @return the value of visaType
     */
    public String getVisaType() {
        return this.visaType;
    }

    /**
     * Sets the value of visaType.
     * @param argVisaType Value to assign to this.visaType
     */
    public void setVisaType(String argVisaType) {
        this.visaType = argVisaType;
    }

    /**
     * Gets the value of visaRenewalDate.
     * @return the value of visaRenewalDate
     */
    public Date getVisaRenewalDate() {
        return this.visaRenewalDate;
    }

    /**
     * Sets the value of visaRenewalDate.
     * @param argVisaRenewalDate Value to assign to this.visaRenewalDate
     */
    public void setVisaRenewalDate(Date argVisaRenewalDate) {
        this.visaRenewalDate = argVisaRenewalDate;
    }

    /**
     * Gets the value of hasVisa.
     * @return the value of hasVisa
     */
    public Boolean getHasVisa() {
        return this.hasVisa;
    }

    /**
     * Sets the value of hasVisa.
     * @param argHasVisa Value to assign to this.hasVisa
     */
    public void setHasVisa(Boolean argHasVisa) {
        this.hasVisa = argHasVisa;
    }

    /**
     * Gets the value of officeLocation.
     * @return the value of officeLocation
     */
    public String getOfficeLocation() {
        return this.officeLocation;
    }

    /**
     * Sets the value of officeLocation.
     * @param argOfficeLocation Value to assign to this.officeLocation
     */
    public void setOfficeLocation(String argOfficeLocation) {
        this.officeLocation = argOfficeLocation;
    }

    /**
     * Gets the value of secondaryOfficeLocation.
     * @return the value of secondaryOfficeLocation
     */
    public String getSecondaryOfficeLocation() {
        return this.secondaryOfficeLocation;
    }

    /**
     * Sets the value of secondaryOfficeLocation.
     * @param argSecondaryOfficeLocation Value to assign to this.secondaryOfficeLocation
     */
    public void setSecondaryOfficeLocation(String argSecondaryOfficeLocation) {
        this.secondaryOfficeLocation = argSecondaryOfficeLocation;
    }

    /**
     * Gets the value of school.
     * @return the value of school
     */
    public String getSchool() {
        return this.school;
    }

    /**
     * Sets the value of school.
     * @param argSchool Value to assign to this.school
     */
    public void setSchool(String argSchool) {
        this.school = argSchool;
    }

    /**
     * Gets the value of yearGraduated.
     * @return the value of yearGraduated
     */
    public String getYearGraduated() {
        return this.yearGraduated;
    }

    /**
     * Sets the value of yearGraduated.
     * @param argYearGraduated Value to assign to this.yearGraduated
     */
    public void setYearGraduated(String argYearGraduated) {
        this.yearGraduated = argYearGraduated;
    }

    /**
     * Gets the value of directoryDepartment.
     * @return the value of directoryDepartment
     */
    public String getDirectoryDepartment() {
        return this.directoryDepartment;
    }

    /**
     * Sets the value of directoryDepartment.
     * @param argDirectoryDepartment Value to assign to this.directoryDepartment
     */
    public void setDirectoryDepartment(String argDirectoryDepartment) {
        this.directoryDepartment = argDirectoryDepartment;
    }

    /**
     * Gets the value of primaryTitle.
     * @return the value of primaryTitle
     */
    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    /**
     * Sets the value of primaryTitle.
     * @param argPrimaryTitle Value to assign to this.primaryTitle
     */
    public void setPrimaryTitle(String argPrimaryTitle) {
        this.primaryTitle = argPrimaryTitle;
    }

    /**
     * Gets the value of directoryTitle.
     * @return the value of directoryTitle
     */
    public String getDirectoryTitle() {
        return this.directoryTitle;
    }

    /**
     * Sets the value of directoryTitle.
     * @param argDirectoryTitle Value to assign to this.directoryTitle
     */
    public void setDirectoryTitle(String argDirectoryTitle) {
        this.directoryTitle = argDirectoryTitle;
    }

    /**
     * Gets the value of vacationAccrual.
     * @return the value of vacationAccrual
     */
    public Boolean getVacationAccrualFlag() {
        return this.vacationAccrualFlag;
    }

    /**
     * Sets the value of vacationAccrual.
     * @param argVacationAccrual Value to assign to this.vacationAccrual
     */
    public void setVacationAccrualFlag(Boolean argVacationAccrual) {
        this.vacationAccrualFlag = argVacationAccrual;
    }

    /**
     * Gets the value of onSabbatical.
     * @return the value of onSabbatical
     */
    public Boolean getOnSabbaticalFlag() {
        return this.onSabbaticalFlag;
    }

    /**
     * Sets the value of onSabbatical.
     * @param argOnSabbatical Value to assign to this.onSabbatical
     */
    public void setOnSabbaticalFlag(Boolean argOnSabbatical) {
        this.onSabbaticalFlag = argOnSabbatical;
    }

    /**
     * Gets the value of idProvided.
     * @return the value of idProvided
     */
    public String getIdProvided() {
        return this.idProvided;
    }

    /**
     * Sets the value of idProvided.
     * @param argIdProvided Value to assign to this.idProvided
     */
    public void setIdProvided(String argIdProvided) {
        this.idProvided = argIdProvided;
    }

    /**
     * Gets the value of idVerified.
     * @return the value of idVerified
     */
    public String getIdVerified() {
        return this.idVerified;
    }

    /**
     * Sets the value of idVerified.
     * @param argIdVerified Value to assign to this.idVerified
     */
    public void setIdVerified(String argIdVerified) {
        this.idVerified = argIdVerified;
    }

    /**
     * Gets the value of county.
     * @return the value of county
     */
    public String getCounty() {
        return this.county;
    }

    /**
     * Sets the value of county.
     * @param argCounty Value to assign to this.county
     */
    public void setCounty(String argCounty) {
        this.county = argCounty;
    }

    public List<PersonDegree> getPersonDegrees() {
        return personDegrees;
    }

    public void setPersonDegrees(List<PersonDegree> personDegrees) {
        this.personDegrees = personDegrees;
    }

    public List<PersonAppointment> getPersonAppointments() {
        return personAppointments;
    }

    public void setPersonAppointments(List<PersonAppointment> personAppointments) {
        this.personAppointments = personAppointments;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public FormFile getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }

    public String getBiosketchDescription() {
        return biosketchDescription;
    }

    public void setBiosketchDescription(String biosketchDescription) {
        this.biosketchDescription = biosketchDescription;
    }

    public Integer getCitizenshipTypeCode() {
        return citizenshipTypeCode;
    }

    public void setCitizenshipTypeCode(Integer citizenshipTypeCode) {
        this.citizenshipTypeCode = citizenshipTypeCode;
    }

    public CitizenshipType getCitizenshipType() {
        return citizenshipType;
    }

    public void setCitizenshipType(CitizenshipType citizenshipType) {
        this.citizenshipType = citizenshipType;
    }

    public String getMultiCampusPrincipalId() {
        return multiCampusPrincipalId;
    }

    public void setMultiCampusPrincipalId(String multiCampusPrincipalId) {
        this.multiCampusPrincipalId = multiCampusPrincipalId;
    }

    public String getMultiCampusPrincipalName() {
        return multiCampusPrincipalName;
    }

    public void setMultiCampusPrincipalName(String multiCampusPrincipalName) {
        this.multiCampusPrincipalName = multiCampusPrincipalName;
    }

    /**
     * Gets the salaryAnniversaryDate attribute. 
     * @return Returns the salaryAnniversaryDate.
     */
    public Date getSalaryAnniversaryDate() {
        return salaryAnniversaryDate;
    }

    /**
     * Sets the salaryAnniversaryDate attribute value.
     * @param salaryAnniversaryDate The salaryAnniversaryDate to set.
     */
    public void setSalaryAnniversaryDate(Date salaryAnniversaryDate) {
        this.salaryAnniversaryDate = salaryAnniversaryDate;
    }
    
}
