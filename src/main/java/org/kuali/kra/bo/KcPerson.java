/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CountryCodeService;
import org.kuali.rice.kim.bo.entity.KimEntity;
import org.kuali.rice.kim.bo.entity.KimEntityAddress;
import org.kuali.rice.kim.bo.entity.KimEntityAffiliation;
import org.kuali.rice.kim.bo.entity.KimEntityCitizenship;
import org.kuali.rice.kim.bo.entity.KimEntityEmail;
import org.kuali.rice.kim.bo.entity.KimEntityEntityType;
import org.kuali.rice.kim.bo.entity.KimEntityExternalIdentifier;
import org.kuali.rice.kim.bo.entity.KimEntityName;
import org.kuali.rice.kim.bo.entity.KimEntityPhone;
import org.kuali.rice.kim.bo.entity.KimPrincipal;
import org.kuali.rice.kim.bo.entity.dto.KimEntityAddressInfo;
import org.kuali.rice.kim.bo.entity.dto.KimEntityInfo;
import org.kuali.rice.kim.bo.entity.dto.KimPrincipalInfo;
import org.kuali.rice.kim.service.IdentityService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Represents a person in KC.
 */
public class KcPerson implements Contactable, BusinessObject {

    private static final long serialVersionUID = 1L;

    private String personId;
    
    private transient IdentityService identityService;
    private KimEntity entity;
    
    private transient BusinessObjectService boService;
    private KcPersonExtendedAttributes extendedAttributes;

    private transient CountryCodeService countryCodeService;
    
    /**
     * When using this ctor {@link #setPersonId(String)} must be call else this call is in an invalid state.
     */
    public KcPerson() {
        //no-arg ctor so that this BO can be used in rice...
        //initializing to empty objects to help with unit testing
        this.entity = new KimEntityInfo();
        this.extendedAttributes = new KcPersonExtendedAttributes();
    }
    
    /**
     * Creates a KcPerson from a principal id.
     * 
     * @param personId the principal id in KIM
     * @return a KcPerson instance
     * @throws IllegalArgumentException if the personId is empty
     */
    public static KcPerson fromPersonId(final String personId) {
        if (StringUtils.isEmpty(personId)) {
            throw new IllegalArgumentException("the principalId is empty");
        }
        
        KcPerson person = new KcPerson();
        person.personId = personId;
        person.refreshEntity();
        person.refreshExtendedAttributes();
        
        return person;
    }
    
    /**
     * Creates a KcPerson from an KcPersonExtendedAttributes BO.
     * 
     * @param extendedAttributes the KC Person Extended Attributes.
     * @return a KcPerson instance
     * @throws IllegalArgumentException if the extendedAttributes is null or the extendedAttributes.personId is empty
     */
    public static KcPerson fromExtendedAttributes(final KcPersonExtendedAttributes extendedAttributes) {
        if (extendedAttributes == null) {
            throw new IllegalArgumentException("the extendedAttributes is null");
        }
        
        if (StringUtils.isEmpty(extendedAttributes.getPersonId())) {
            throw new IllegalArgumentException("the extendedAttributes.personId is empty");
        }
        KcPerson person = new KcPerson();
        person.personId = extendedAttributes.getPersonId();
        person.extendedAttributes = extendedAttributes;
        person.refreshEntity();
        
        return person;
    }
    
    /**
     * Creates a KcPerson from an KimEntity & principalId.
     * 
     * @param entity the KIM entity.
     * @param personId the principal id in KIM.  This is needed b/c a KIM entity can have multiple principal.  This determines
     * which principal this wrapper will refer to.
     * @return a KcPerson instance
     * @throws IllegalArgumentException if the entity is null, principalId is empty, or if the entity does not contain a principal
     * with the passed in principal id
     */
    public static KcPerson fromEntityAndPersonId(final KimEntity entity, final String personId) {
        if (entity == null) {
            throw new IllegalArgumentException("the entity is null");
        }
        
        if (StringUtils.isEmpty(personId)) {
            throw new IllegalArgumentException("the personId is empty");
        }
        KcPerson person = new KcPerson();
        
        boolean contains = false;
        for (KimPrincipal principal : entity.getPrincipals()) {
            if (personId.equals(principal.getPrincipalId())) {
                contains = true;
                break;
            }
        }
        
        if (!contains) {
            throw new IllegalArgumentException("the entity " + entity + " does not have a principal with id " + personId);
        }
        
        person.personId = personId;
        person.entity = entity;
        person.refreshExtendedAttributes();
        
        return person;
    }
    
    /**
     * Creates a KcPerson from an KimEntity & principalId.
     * 
     * @param entity the KIM entity.
     * @param userName the principal's user name in KIM.  This is needed b/c a KIM entity can have multiple principal.  This determines
     * which principal this wrapper will refer to.
     * @return a KcPerson instance
     * @throws IllegalArgumentException if the entity is null, userName is empty, or if the entity does not contain a principal
     * with the passed in user name
     */
    public static KcPerson fromEntityAndUserName(final KimEntity entity, final String userName) {
        if (entity == null) {
            throw new IllegalArgumentException("the entity is null");
        }
        
        if (StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("the userName is empty");
        }
        KcPerson person = new KcPerson();
        
        boolean contains = false;
        for (KimPrincipal principal : entity.getPrincipals()) {
            if (userName.equals(principal.getPrincipalName())) {
                contains = true;
                person.personId = principal.getPrincipalId();
                break;
            }
        }
        
        if (!contains) {
            throw new IllegalArgumentException("the entity " + entity + " does not have a principal name " + userName);
        }
        
        person.entity = entity;
        person.refreshExtendedAttributes();
        
        return person;
    }
    
    /** {@inheritDoc} */
    public void refresh() {
        this.refreshEntity();
        this.refreshExtendedAttributes();
    }
    
    /** refreshes just the entity object. */
    private void refreshEntity() {
        this.entity = this.getIdentityService().getEntityInfoByPrincipalId(this.personId);
        
        if (this.entity == null) {
            this.entity = new KimEntityInfo();
        }
    }
    
    /** refreshes just the extended attributes object. */
    private void refreshExtendedAttributes() {
        this.extendedAttributes = (KcPersonExtendedAttributes)
            this.getBusinessObjectService().findByPrimaryKey(KcPersonExtendedAttributes.class, Collections.singletonMap("personId", this.personId));
    
        if (this.extendedAttributes == null) {
            this.extendedAttributes = new KcPersonExtendedAttributes();
        }
    }
    
    /** {@inheritDoc} */
    public void prepareForWorkflow() {
        //do nothing
    }

    /**
     * Gets the person id which is the KIM principal id.
     * @return the person id
     */
    public String getPersonId() {
        return this.personId;
    }
    
    /**
     * Sets the person id which is the KIM principal id.
     * @param personId the person id
     */
    public void setPersonId(String personId) {
        this.personId = personId;
        refreshEntity();
        refreshExtendedAttributes();
    }
    
    /**
     * Gets the value of socialSecurityNumber.
     * @return the value of socialSecurityNumber
     */
    public String getSocialSecurityNumber() {
        return this.getExternalId("SSN");
    }
    
    
    /**
     * Gets the value of lastName.
     * @return the value of lastName
     */
    public String getLastName() {
        final KimEntityName name = this.entity.getDefaultName();
        return name.getLastName();
    }

    /**
     * Gets the value of firstName.
     * @return the value of firstName
     */
    public String getFirstName() {
        final KimEntityName name = this.entity.getDefaultName();
        return name.getFirstName();
    }

    /**
     * Gets the value of middleName.
     *
     * @return the value of middleName
     */
    public String getMiddleName() {
        final KimEntityName name = this.entity.getDefaultName();
        return (name != null) ? name.getMiddleName() : "";
    }

    /**
     * Gets the value of fullName.
     *
     * @return the value of fullName
     */
    public String getFullName() {
        final String middleName = this.getMiddleName() != null ? this.getMiddleName() + " " : "";
        
        return (this.getFirstName() + " " + middleName + this.getLastName()).trim();
    }

    /**
     * Gets the value of priorName.
     *
     * @return the value of priorName
     */
    public String getPriorName() {
        for (KimEntityName name : this.entity.getNames()) {
            if ("PRIOR".equals(name.getNameTypeCode())) {
                return name.getLastName();
            }
        }

        return "";
    }

    /**
     * Gets the value of userName.
     *
     * @return the value of userName
     */
    public String getUserName() {
        return this.getPrincipal().getPrincipalName();
    }

    /**
     * Gets the value of emailAddress.
     *
     * @return the value of emailAddress
     */
    public String getEmailAddress() {
        for (KimEntityEmail email : this.getEntityType().getEmailAddresses()) {
            if (email.isActive() && email.isDefault()) {
                return email.getEmailAddress();
            }
        }
        return "";
    }

    /**
     * Gets the value of dateOfBirth.
     *
     * @return the value of dateOfBirth
     */
    public String getDateOfBirth() {
        return this.formatDate(this.entity.getBioDemographics().getBirthDate());
    }

    /**
     * Gets the value of age.
     *
     * @return the value of age
     */
    public Integer getAge() {
        final Date birthDate = this.entity.getBioDemographics().getBirthDate();
        return this.calcAge(birthDate);
    }

    /**
     * Gets the value of ageByFiscalYear.
     *
     * @return the value of ageByFiscalYear
     */
    public Integer getAgeByFiscalYear() {
        return this.extendedAttributes.getAgeByFiscalYear();
    }

    /**
     * Gets the value of gender.
     *
     * @return the value of gender
     */
    public String getGender() {
        return this.entity.getBioDemographics().getGenderCode();
    }

    /**
     * Gets the value of race.
     *
     * @return the value of race
     */
    public String getRace() {
        return this.extendedAttributes.getRace();
    }

    /**
     * Gets the value of educationLevel.
     *
     * @return the value of educationLevel
     */
    public String getEducationLevel() {
        return this.extendedAttributes.getEducationLevel();
    }

    /**
     * Gets the value of degree.
     *
     * @return the value of degree
     */
    public String getDegree() {
        return this.extendedAttributes.getDegree();
    }

    /**
     * Gets the value of major.
     *
     * @return the value of major
     */
    public String getMajor() {
        return this.extendedAttributes.getMajor();
    }

    /**
     * Gets the value of handicapped.
     *
     * @return the value of handicapped
     */
    public Boolean getHandicappedFlag() {
        return this.extendedAttributes.getHandicappedFlag();
    }

    /**
     * Gets the value of handicapType.
     *
     * @return the value of handicapType
     */
    public String getHandicapType() {
        return this.extendedAttributes.getHandicapType();
    }

    /**
     * Gets the value of veteran.
     *
     * @return the value of veteran
     */
    public Boolean getVeteranFlag() {
        return this.extendedAttributes.getVeteranFlag();
    }

    /**
     * Gets the value of veteranType.
     *
     * @return the value of veteranType
     */
    public String getVeteranType() {
        return this.extendedAttributes.getVeteranType();
    }

    /**
     * Gets the value of visaCode.
     *
     * @return the value of visaCode
     */
    public String getVisaCode() {
        return this.extendedAttributes.getVisaCode();
    }

    /**
     * Gets the value of visaType.
     *
     * @return the value of visaType
     */
    public String getVisaType() {
        return this.extendedAttributes.getVisaType();
    }

    /**
     * Gets the value of visaRenewalDate.
     *
     * @return the value of visaRenewalDate
     */
    public String getVisaRenewalDate() {
        return this.extendedAttributes.getVisaRenewalDate() != null ? this.formatDate(this.extendedAttributes.getVisaRenewalDate()) : null;
    }

    /**
     * Gets the value of hasVisa.
     *
     * @return the value of hasVisa
     */
    public Boolean getHasVisa() {
        return this.extendedAttributes.getHasVisa();
    }

    /**
     * Gets the value of officeLocation.
     *
     * @return the value of officeLocation
     */
    public String getOfficeLocation() {
        return this.extendedAttributes.getOfficeLocation();
    }

    /**
     * Gets the value of officePhone.
     *
     * @return the value of officePhone
     */
    public String getOfficePhone() {
        //office phone is migrated as WRK type default
        return this.getPhoneNumber("WRK", true);
    }

    /**
     * Gets the value of secondaryOfficeLocation.
     *
     * @return the value of secondaryOfficeLocation
     */
    public String getSecondaryOfficeLocation() {
        return this.extendedAttributes.getSecondaryOfficeLocation();
    }

    /**
     * Gets the value of secondaryOfficePhone.
     *
     * @return the value of secondaryOfficePhone
     */
    public String getSecondaryOfficePhone() {
        //2nd office phone is migrated as WRK type non-default
        return this.getPhoneNumber("WRK", false);
    }

    /**
     * Gets the value of school.
     *
     * @return the value of school
     */
    public String getSchool() {
        return this.extendedAttributes.getSchool();
    }

    /**
     * Gets the value of yearGraduated.
     * 
     * @return the value of yearGraduated
     */
    public String getYearGraduated() {
        return this.extendedAttributes.getYearGraduated();
    }

    /**
     * Gets the value of directoryDepartment.
     *
     * @return the value of directoryDepartment
     */
    public String getDirectoryDepartment() {
        return this.extendedAttributes.getDirectoryDepartment();
    }

    /**
     * Gets the value of saluation.
     *
     * @return the value of saluation
     */
    public String getSaluation() {
        final KimEntityName name = this.entity.getDefaultName();
        return name.getTitle();
    }

    /**
     * Gets the value of countryOfCitizenship.
     *
     * @return the value of countryOfCitizenship
     */
    public String getCountryOfCitizenship() {
        final Iterator<? extends KimEntityCitizenship> i = this.entity.getCitizenships().iterator();
        
        if (i.hasNext()) {
            //KC only support one citizenship
            final KimEntityCitizenship cit = i.next();
                
            return this.convert2DigitCountryCodeTo3Digit(cit.getCountryCode());
            
        }
        return "";
    }

    /**
     * Gets the value of primaryTitle.
     *
     * @return the value of primaryTitle
     */
    public String getPrimaryTitle() {
        return this.extendedAttributes.getPrimaryTitle();
    }

    /**
     * Gets the value of directoryTitle.
     *
     * @return the value of directoryTitle
     */
    public String getDirectoryTitle() {
        return this.extendedAttributes.getDirectoryTitle();
    }

    /**
     * Gets the value of faculty.
     *
     * @return the value of faculty
     */
    public Boolean getFacultyFlag() {      
        return Boolean.valueOf(this.hasAffiliation("FCLTY"));
    }
     
    /**
     * Gets the value of graduateStudentStaff.
     *
     * @return the value of graduateStudentStaff
     */
    public Boolean getGraduateStudentStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("GRD_STDNT_STAFF"));
    }

    /**
     * Gets the value of researchStaff.
     *
     * @return the value of researchStaff
     */
    public Boolean getResearchStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("RSRCH_STAFF"));
    }

    /**
     * Gets the value of serviceStaff.
     *
     * @return the value of serviceStaff
     */
    public Boolean getServiceStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("SRVC_STAFF"));
    }

    /**
     * Gets the value of supportStaff.
     *
     * @return the value of supportStaff
     */
    public Boolean getSupportStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("SUPPRT_STAFF"));
    }

    /**
     * Gets the value of otherAcademicGroup.
     *
     * @return the value of otherAcademicGroup
     */
    public Boolean getOtherAcademicGroupFlag() {
        return Boolean.valueOf(this.hasAffiliation("OTH_ACADMC_GRP"));
    }

    /**
     * Gets the value of medicalStaff.
     *
     * @return the value of medicalStaff
     */
    public Boolean getMedicalStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("MED_STAFF"));
    }

    /**
     * Gets the value of vacationAccrual.
     *
     * @return the value of vacationAccrual
     */
    public Boolean getVacationAccrualFlag() {
        return this.extendedAttributes.getVacationAccrualFlag();
    }

    /**
     * Gets the value of onSabbatical.
     *
     * @return the value of onSabbatical
     */
    public Boolean getOnSabbaticalFlag() {
        return this.extendedAttributes.getOnSabbaticalFlag();
    }

    /**
     * Gets the value of idProvided.
     *
     * @return the value of idProvided
     */
    public String getIdProvided() {
        return this.extendedAttributes.getIdProvided();
    }

    /**
     * Gets the value of idVerified.
     *
     * @return the value of idVerified
     */
    public String getIdVerified() {
        return this.extendedAttributes.getIdVerified();
    }

    /**
     * Gets the value of addressLine1.
     *
     * @return the value of addressLine1
     */
    public String getAddressLine1() {
        return this.getDefaultActiveAddress().getLine1();
    }

    /**
     * Gets the value of addressLine2.
     *
     * @return the value of addressLine2
     */
    public String getAddressLine2() {
        return this.getDefaultActiveAddress().getLine2();
    }

    /**
     * Gets the value of addressLine3.
     *
     * @return the value of addressLine3
     */
    public String getAddressLine3() {
        return this.getDefaultActiveAddress().getLine3();
    }

    /**
     * Gets the value of city.
     *
     * @return the value of city
     */
    public String getCity() {
        return this.getDefaultActiveAddress().getCityName();
    }

    /**
     * Gets the value of county.
     *
     * @return the value of county
     */
    public String getCounty() {
        return this.extendedAttributes.getCounty();
    }

    /**
     * Gets the value of state.
     *
     * @return the value of state
     */
    public String getState() {
        return this.getDefaultActiveAddress().getStateCode();
    }

    /**
     * Gets the value of postalCode.
     *
     * @return the value of postalCode
     */
    public String getPostalCode() {
        return this.getDefaultActiveAddress().getPostalCode();
    }

    /**
     * Gets the value of countryCode.
     *
     * @return the value of countryCode
     */
    public String getCountryCode() {
        return this.convert2DigitCountryCodeTo3Digit(this.getDefaultActiveAddress().getCountryCode());
    }
    
    /**
     * Gets the value of faxNumber.
     *
     * @return the value of faxNumber
     */
    public String getFaxNumber() {
        return this.getPhoneNumber("FAX");
    }
    
    /**
     * Gets the value of pagerNumber.
     *
     * @return the value of pagerNumber
     */
    public String getPagerNumber() {      
        return this.getPhoneNumber("PGR");
    }

    /**
     * Gets the value of mobilePhoneNumber.
     *
     * @return the value of mobilePhoneNumber
     */
    public String getMobilePhoneNumber() {
        return this.getPhoneNumber("MBL");
    }

    /**
     * Gets the value of eraCommonsUserName.
     *
     * @return the value of eraCommonsUserName
     */
    public String getEraCommonsUserName() {
        return this.getExternalId("ERAC");
    }
    
    /**
     * Gets the active flag.
     * @return the active flag
     */
    public Boolean getActive() {
        return Boolean.valueOf(this.entity.isActive());
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword() {
        return this.getPrincipal().getPassword();
    }

    /** {@inheritDoc} */
    public String getIdentifier() {
        return this.getPersonId();
    }
    
    /** {@inheritDoc} */
    public Unit getUnit() {
        final String org = this.getOrganizationIdentifier();
        
        return org != null ? (Unit) this.getBusinessObjectService().findByPrimaryKey(Unit.class, Collections.singletonMap("unitNumber", org)) : null; 
    }

    /** {@inheritDoc} */
    public String getPhoneNumber() {
        return this.getOfficePhone();
    }

    /** {@inheritDoc} */
    public String getContactOrganizationName() {
        return this.getUnit() != null ? this.getUnit().getUnitName() : null;
    }

    /** {@inheritDoc} */
    public String getOrganizationIdentifier() {
        return this.entity.getPrimaryEmployment().getPrimaryDepartmentCode();
    }
    
    /**
     * Gets the {@link BusinessObjectService BusinessObjectService}.
     * @return Gets the BusinessObjectService
     */
    BusinessObjectService getBusinessObjectService() {
        if (this.boService == null) {
            this.boService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return this.boService;
    }
    
    /**
     * Gets the {@link IdentityService IdentityService}.
     * @return Gets the IdentityService
     */
    IdentityService getIdentityService() {
        if (this.identityService == null) {
            this.identityService = KraServiceLocator.getService(IdentityService.class);
        }
        return this.identityService;
    }
    
    /**
     * Gets the {@link CountryCodeService CountryCodeService}.
     * @return Gets the CountryCodeService
     */
    CountryCodeService getCountryCodeService() {
        if (this.countryCodeService == null) {
            this.countryCodeService = KraServiceLocator.getService(CountryCodeService.class);
        }
        return this.countryCodeService;
    }
    
    /**
     * gets the extended attributes BO.  This is only here to make rice DD relationships work.
     * @return the BO
     */
    public KcPersonExtendedAttributes getExtendedAttributes() {
        return this.extendedAttributes;
    }
    
    /**
     * Gets the phone number for a specific type out of the KIM object.  This
     * phone number will not contain country code or extension b/c phone numbers
     * in KC do not have them.  Will return an empty string if unable to find a
     * number for a type.
     * @param type the type
     * @return the phone number (ex: 123-4567)
     */
    private String getPhoneNumber(String type) {
        for (KimEntityPhone phone : this.getEntityType().getPhoneNumbers()) {
            if (type.equals(phone.getPhoneTypeCode())) {
                return phone.getPhoneNumber();
            }
        }
        
        return "";
    }
    
    /**
     * Gets the phone number for a specific type and matching the default indicator
     * out of the KIM object.  This phone number will not contain country code
     * or extension b/c phone numbers in KC do not have them.  Will return an empty string
     * if unable to find a number for a type/default ind.
     * @param type the type
     * @param whether the number must be default
     * @return the phone number (ex: 123-4567)
     */
    private String getPhoneNumber(String type, boolean isDefault) {
        for (KimEntityPhone phone : this.getEntityType().getPhoneNumbers()) {
            if (type.equals(phone.getPhoneTypeCode()) && isDefault == phone.isDefault()) {
                return phone.getPhoneNumber();
            }
        }
        
        return "";
    }
    
    /**
     * Checks if the KIM entity has an affiliation based on a type code.
     * @param affilTypeCode the affliation type code
     * @return true if the entity has an affiliation
     */
    private boolean hasAffiliation(String affilTypeCode) {
        for (KimEntityAffiliation a : this.entity.getAffiliations()) {
            if (a.getAffiliationTypeCode().equals(affilTypeCode)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Formats a date in the format KC expects.
     * @param dte the date to format
     * @return the formatted date
     * @see #standardDateFormat for the format
     */
    private String formatDate(Date dte) {
        assert dte != null : "the date is null";
        return this.getKCDateFormat().format(dte);
    }
    
    /**
     * Gets an external id based on a type.
     * @param type the type
     * @return the id
     */
    private String getExternalId(String type) {
        final KimEntityExternalIdentifier extId = this.entity.getEntityExternalIdentifier(type);
        return extId.getExternalId(); 
    }
    
    /** 
     * Gets the default & active address.
     * @return address
     */
    private KimEntityAddress getDefaultActiveAddress() {
        for (KimEntityAddress address : this.getEntityType().getAddresses()) {
            if (address.isActive() && address.isDefault()) {
                return address;
            }
        }
        
        return new KimEntityAddressInfo();
    }
    
    /**
     * Gets the entity type that represents a person in KC.  KC only supports a single entity type.
     * @return the entity type object
     */
    private KimEntityEntityType getEntityType() {     
        return this.entity.getEntityType("PERSON");
    }
    
    /**
     * Calculates the age based on a date of birth and the current system date.
     * @param dob the date of birth
     * @return the age in days
     */
    private Integer calcAge(Date dob) {
        return Integer.getInteger(DurationFormatUtils.formatPeriod(dob.getTime(), new Date().getTime(), "y"));
    }
    
    /**
     * Converts a 2 digit country code to the three digit code KC uses.
     * @param digit2 the 2 digit code
     * @return the 3 digit code
     */
    private String convert2DigitCountryCodeTo3Digit(String digit2) {
        final CountryCode countryCode = this.getCountryCodeService().getCountryCodeByTwoCharacterCode(digit2);
        return countryCode != null && countryCode.getThreeCharacterCountryCode() != null ? countryCode.getThreeCharacterCountryCode() : "";
    }
    
    /**
     * DateFormats are not thread safe.  Creating a new instance for use each time.
     * @return a date format
     */
    private DateFormat getKCDateFormat() {
        return new SimpleDateFormat("MM/dd/yyyy");
    }
    
    /**
     * Gets the principal which matches the passed in principal id.  This method will never return null.
     * an empty Principal object will be returned in the case where a principal cannot be found.
     * @return the Kim Principal.
     */
    private KimPrincipal getPrincipal() {
        for (KimPrincipal p : this.entity.getPrincipals()) {
            if (p.getPrincipalId().equals(this.personId)) {
                return p;
            }
        }
        
        return new KimPrincipalInfo();
    }
    
    /** {@inheritDoc} */       
    @Override()
    public String toString(){
        StringBuffer retVal = new StringBuffer(50);
        retVal.append("getAddressLine1:'").append(this.getAddressLine1()).append("'\n");
        retVal.append("getAddressLine2:'").append(this.getAddressLine2()).append("'\n");
        retVal.append("getAddressLine3:'").append(this.getAddressLine3()).append("'\n");
        retVal.append("city:'").append(this.getCity()).append("'\n");
        retVal.append("org name:'").append(this.getContactOrganizationName()).append("'\n");
        retVal.append("userName:'").append(this.getUserName()).append("'\n");
        retVal.append("first name:'").append(this.getFirstName()).append("'\n");
        retVal.append("last name:'").append(this.getLastName()).append("'\n");
        retVal.append("full name:'").append(this.getFullName()).append("'\n");
        retVal.append("personID:'").append(this.getPersonId()).append("'\n");
        retVal.append("entity type:'").append(this.getEntityType()).append("'\n");
        return retVal.toString();
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    public void setBusinessObjectService(BusinessObjectService boService) {
        this.boService = boService;
    }
}