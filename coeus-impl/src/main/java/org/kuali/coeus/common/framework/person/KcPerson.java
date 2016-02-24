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
package org.kuali.coeus.common.framework.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.address.EntityAddress;
import org.kuali.rice.kim.api.identity.address.EntityAddressContract;
import org.kuali.rice.kim.api.identity.affiliation.EntityAffiliationContract;
import org.kuali.rice.kim.api.identity.citizenship.EntityCitizenshipContract;
import org.kuali.rice.kim.api.identity.email.EntityEmailContract;
import org.kuali.rice.kim.api.identity.employment.EntityEmploymentContract;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.kuali.rice.kim.api.identity.entity.EntityContract;
import org.kuali.rice.kim.api.identity.external.EntityExternalIdentifierContract;
import org.kuali.rice.kim.api.identity.name.EntityNameContract;
import org.kuali.rice.kim.api.identity.personal.EntityBioDemographicsContract;
import org.kuali.rice.kim.api.identity.phone.EntityPhoneContract;
import org.kuali.rice.kim.api.identity.principal.PrincipalContract;
import org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo;
import org.kuali.rice.kim.api.identity.type.EntityTypeContactInfoContract;
import org.kuali.rice.krad.bo.TransientBusinessObjectBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Represents a person in KC.
 */
public class KcPerson extends TransientBusinessObjectBase implements Contactable, KcPersonContract {

    private static final long serialVersionUID = 1L;

    private String personId;
    
    private transient IdentityService identityService;
    private EntityContract entity;
    
    private transient BusinessObjectService boService;
    private KcPersonExtendedAttributes extendedAttributes;
    
    private transient CountryService countryService;
    
    private static final Log LOG = LogFactory.getLog(KcPerson.class);

    /**
     * When using this ctor {@link #setPersonId(String)} must be call else this call is in an invalid state.
     */
    public KcPerson() {
        //no-arg ctor so that this BO can be used in rice...
        //initializing to empty objects to help with unit testing
        this.entity = Entity.Builder.create();
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
     * Creates a KcPerson from an EntityContract &amp; principalId.
     * 
     * @param entity the KIM entity.
     * @param personId the principal id in KIM.  This is needed b/c a KIM entity can have multiple principal.  This determines
     * which principal this wrapper will refer to.
     * @return a KcPerson instance
     * @throws IllegalArgumentException if the entity is null, principalId is empty, or if the entity does not contain a principal
     * with the passed in principal id
     */
    public static KcPerson fromEntityAndPersonId(final EntityContract entity, final String personId) {
        if (entity == null) {
          // throw new IllegalArgumentException("the entity is null");
          final IllegalArgumentException e = new IllegalArgumentException(
              "the entity is null; behavior change: returning empty KcPerson reference instead of throwing an IllegalArgumentException: personId="
                  + personId);
          LOG.info(e.getMessage(), e);
          return fromPersonId(personId);
        }
        
        if (StringUtils.isEmpty(personId)) {
            throw new IllegalArgumentException("the personId is empty");
        }
        KcPerson person = new KcPerson();
        
        boolean contains = false;
        for (PrincipalContract principal : entity.getPrincipals()) {
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
     * Creates a KcPerson from an EntityContract &amp; principalId.
     * 
     * @param entity the KIM entity.
     * @param userName the principal's user name in KIM.  This is needed b/c a KIM entity can have multiple principal.  This determines
     * which principal this wrapper will refer to.
     * @return a KcPerson instance
     * @throws IllegalArgumentException if the entity is null, userName is empty, or if the entity does not contain a principal
     * with the passed in user name
     */
    public static KcPerson fromEntityAndUserName(final EntityContract entity, final String userName) {
        if (entity == null) {
            throw new IllegalArgumentException("the entity is null");
        }
        
        if (StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("the userName is empty");
        }
        KcPerson person = new KcPerson();
        
        boolean contains = false;
        for (PrincipalContract principal : entity.getPrincipals()) {
            if (userName.equalsIgnoreCase(principal.getPrincipalName())) {
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
    
    @Override
    public void refresh() {
        this.refreshEntity();
        this.refreshExtendedAttributes();
    }
    
    /** refreshes just the entity object. */
    private void refreshEntity() {
        this.entity = this.getIdentityService().getEntityByPrincipalId(this.personId);
        
        if (this.entity == null) {
            this.entity = Entity.Builder.create();
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
    

    public void prepareForWorkflow() {
        //do nothing
    }

    /**
     * Gets the person id which is the KIM principal id.
     * @return the person id
     */
    @Override
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
    @Override
    public String getSocialSecurityNumber() {
        return this.getExternalId("SSN");
    }
    
    
    /**
     * Gets the value of lastName.
     * @return the value of lastName
     */
    @Override
    public String getLastName() {
        final EntityNameContract name = this.entity.getDefaultName();
        if (name == null) {
            return "";
        }
        
        return name.getLastName() != null ? name.getLastName() : "";
    }

    /**
     * Gets the value of firstName.
     * @return the value of firstName
     */
    @Override
    public String getFirstName() {
        final EntityNameContract name = this.entity.getDefaultName();
        if (name == null) {
            return "";
        }
        
        return name.getFirstName() != null ? name.getFirstName() : "";
    }

    /**
     * Gets the value of middleName.
     *
     * @return the value of middleName
     */
    @Override
    public String getMiddleName() {
        final EntityNameContract name = this.entity.getDefaultName();
        if (name == null) {
            return "";
        }
        
        return name.getMiddleName() != null ? name.getMiddleName() : "";
    }

    /**
     * Gets the value of fullName.
     *
     * @return the value of fullName
     */
    @Override
    public String getFullName() {
        final String middleName = StringUtils.isNotEmpty(this.getMiddleName()) ? this.getMiddleName() + " " : "";
        
        return (this.getFirstName() + " " + middleName + this.getLastName()).trim();
    }

    /**
     * Gets the value of priorName.
     *
     * @return the value of priorName
     */
    @Override
    public String getPriorName() {
        return selectSingleValue(this.entity.getNames(), new Selector<EntityNameContract, String>() {
            public String notFoundValue() { return ""; }
            public String select(EntityNameContract a) { return a.getLastName(); }
            public boolean shouldSelect(EntityNameContract a) { return "PRIOR".equals(a.getNameType().getName()) && a.getLastName() != null; }
        });
    }

    /**
     * Gets the value of userName.
     *
     * @return the value of userName
     */
    @Override
    public String getUserName() {
        final String userName = this.getPrincipal() != null? this.getPrincipal().getPrincipalName() : null;
        
        return userName != null ? userName : "";
    }

    /**
     * Gets the value of emailAddress.
     *
     * @return the value of emailAddress
     */
    @Override
    public String getEmailAddress() {
        return selectSingleValue(this.getEntityType().getEmailAddresses(), new Selector<EntityEmailContract, String>() {
            public String notFoundValue() { return ""; }
            public String select(EntityEmailContract a) { return a.getEmailAddress(); }
            public boolean shouldSelect(EntityEmailContract a) { return a.isActive() && a.isDefaultValue() && a.getEmailAddress() != null; }
        });
    }

    /**
     * Gets the value of dateOfBirth.
     *
     * @return the value of dateOfBirth
     */
    @Override
    public String getDateOfBirth() {
        final EntityBioDemographicsContract bio = this.entity.getBioDemographics();
        if (bio == null) {
            return "";
        }
        
        return bio.getBirthDate();
    }

    /**
     * Gets the value of age. Will return null if not set.
     *
     * @return the value of age
     */
    @Override
    public Integer getAge() {
        final EntityBioDemographicsContract bio = this.entity.getBioDemographics();
        if (bio == null) {
            return null;
        }
        Date birthDate = null;
        
        try {
            birthDate = (bio.getBirthDate() != null) ? DateUtils.parseDate(bio.getBirthDate(), new String[] {"mm/dd/yyyy"}) : null;
        }
        catch (ParseException e) {
        	LOG.warn(e.getMessage(),e);
        }
        
        return birthDate != null ?  this.calcAge(birthDate): null;
        
    }

    /**
     * Gets the value of ageByFiscalYear. Will return null if not set.
     *
     * @return the value of ageByFiscalYear
     */
    @Override
    public Integer getAgeByFiscalYear() {
        return this.extendedAttributes.getAgeByFiscalYear();
    }

    /**
     * Gets the value of gender.
     *
     * @return the value of gender
     */
    @Override
    public String getGender() {
        final EntityBioDemographicsContract bio = this.entity.getBioDemographics();
        if (bio == null) {
            return "";
        }
        
        return bio.getGenderCode() != null ? bio.getGenderCode() : "";
    }

    /**
     * Gets the value of race.
     *
     * @return the value of race
     */
    @Override
    public String getRace() {
        return this.extendedAttributes.getRace();
    }

    /**
     * Gets the value of educationLevel.
     *
     * @return the value of educationLevel
     */
    @Override
    public String getEducationLevel() {
        return this.extendedAttributes.getEducationLevel();
    }

    /**
     * Gets the value of degree.
     *
     * @return the value of degree
     */
    @Override
    public String getDegree() {
        return this.extendedAttributes.getDegree();
    }

    /**
     * Gets the value of major.
     *
     * @return the value of major
     */
    @Override
    public String getMajor() {
        return this.extendedAttributes.getMajor();
    }

    /**
     * Gets the value of handicapped.
     *
     * @return the value of handicapped
     */
    @Override
    public Boolean getHandicappedFlag() {
        return this.extendedAttributes.getHandicappedFlag();
    }

    /**
     * Gets the value of handicapType.
     *
     * @return the value of handicapType
     */
    @Override
    public String getHandicapType() {
        return this.extendedAttributes.getHandicapType();
    }

    /**
     * Gets the value of veteran.
     *
     * @return the value of veteran
     */
    @Override
    public Boolean getVeteranFlag() {
        return this.extendedAttributes.getVeteranFlag();
    }

    /**
     * Gets the value of veteranType.
     *
     * @return the value of veteranType
     */
    @Override
    public String getVeteranType() {
        return this.extendedAttributes.getVeteranType();
    }

    /**
     * Gets the value of visaCode.
     *
     * @return the value of visaCode
     */
    @Override
    public String getVisaCode() {
        return this.extendedAttributes.getVisaCode();
    }

    /**
     * Gets the value of visaType.
     *
     * @return the value of visaType
     */
    @Override
    public String getVisaType() {
        return this.extendedAttributes.getVisaType();
    }

    /**
     * Gets the value of visaRenewalDate.
     *
     * @return the value of visaRenewalDate
     */
    @Override
    public String getVisaRenewalDate() {
        return this.extendedAttributes.getVisaRenewalDate() != null ? this.formatDate(this.extendedAttributes.getVisaRenewalDate()) : null;
    }

    /**
     * Gets the value of hasVisa.
     *
     * @return the value of hasVisa
     */
    @Override
    public Boolean getHasVisa() {
        return this.extendedAttributes.getHasVisa();
    }

    /**
     * Gets the value of officeLocation.
     *
     * @return the value of officeLocation
     */
    @Override
    public String getOfficeLocation() {
        return this.extendedAttributes.getOfficeLocation();
    }

    /**
     * Gets the value of officePhone.
     *
     * @return the value of officePhone
     */
    @Override
    public String getOfficePhone() {
        //office phone is migrated as WRK type default
        return this.getPhoneNumber("WRK", true);
    }

    /**
     * Gets the value of secondaryOfficeLocation.
     *
     * @return the value of secondaryOfficeLocation
     */
    @Override
    public String getSecondaryOfficeLocation() {
        return this.extendedAttributes.getSecondaryOfficeLocation();
    }

    /**
     * Gets the value of secondaryOfficePhone.
     *
     * @return the value of secondaryOfficePhone
     */
    @Override
    public String getSecondaryOfficePhone() {
        //2nd office phone is migrated as WRK type non-default
        return this.getPhoneNumber("WRK", false);
    }

    /**
     * Gets the value of school.
     *
     * @return the value of school
     */
    @Override
    public String getSchool() {
        return this.extendedAttributes.getSchool();
    }

    /**
     * Gets the value of yearGraduated.
     * 
     * @return the value of yearGraduated
     */
    @Override
    public String getYearGraduated() {
        return this.extendedAttributes.getYearGraduated();
    }

    /**
     * Gets the value of directoryDepartment.
     *
     * @return the value of directoryDepartment
     */
    @Override
    public String getDirectoryDepartment() {
        return this.extendedAttributes.getDirectoryDepartment();
    }

    /**
     * Gets the value of saluation.
     *
     * @return the value of saluation
     */
    @Override
    public String getSaluation() {
        final EntityNameContract name = this.entity.getDefaultName();
        if (name == null) {
            return "";
        }
        
        return name.getNameTitle() != null ? name.getNameTitle() : "";
    }

    /**
     * Gets the value of countryOfCitizenship.
     *
     * @return the value of countryOfCitizenship
     */
    @Override
    public String getCountryOfCitizenship() {
        return selectSingleValue(this.entity.getCitizenships(), new Selector<EntityCitizenshipContract, String>() {
            public String notFoundValue() { return ""; }
            public String select(EntityCitizenshipContract a) { return convert2DigitCountryCodeTo3Digit(a.getCountryCode()); }
            public boolean shouldSelect(EntityCitizenshipContract a) { return a.getCountryCode() != null; }
        });
    }

    /**
     * Gets the value of primaryTitle.
     *
     * @return the value of primaryTitle
     */
    @Override
    public String getPrimaryTitle() {
        return this.extendedAttributes.getPrimaryTitle();
    }

    /**
     * Gets the value of directoryTitle.
     *
     * @return the value of directoryTitle
     */
    @Override
    public String getDirectoryTitle() {
        return this.extendedAttributes.getDirectoryTitle();
    }

    /**
     * Gets the value of faculty.
     *
     * @return the value of faculty
     */
    @Override
    public Boolean getFacultyFlag() {      
        return Boolean.valueOf(this.hasAffiliation("FCLTY"));
    }
     
    /**
     * Gets the value of graduateStudentStaff.
     *
     * @return the value of graduateStudentStaff
     */
    @Override
    public Boolean getGraduateStudentStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("GRD_STDNT_STAFF"));
    }

    /**
     * Gets the value of researchStaff.
     *
     * @return the value of researchStaff
     */
    @Override
    public Boolean getResearchStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("RSRCH_STAFF"));
    }

    /**
     * Gets the value of serviceStaff.
     *
     * @return the value of serviceStaff
     */
    @Override
    public Boolean getServiceStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("SRVC_STAFF"));
    }

    /**
     * Gets the value of supportStaff.
     *
     * @return the value of supportStaff
     */
    @Override
    public Boolean getSupportStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("SUPPRT_STAFF"));
    }

    /**
     * Gets the value of otherAcademicGroup.
     *
     * @return the value of otherAcademicGroup
     */
    @Override
    public Boolean getOtherAcademicGroupFlag() {
        return Boolean.valueOf(this.hasAffiliation("OTH_ACADMC_GRP"));
    }

    /**
     * Gets the value of medicalStaff.
     *
     * @return the value of medicalStaff
     */
    @Override
    public Boolean getMedicalStaffFlag() {
        return Boolean.valueOf(this.hasAffiliation("MED_STAFF"));
    }

    /**
     * Gets the value of vacationAccrual.
     *
     * @return the value of vacationAccrual
     */
    @Override
    public Boolean getVacationAccrualFlag() {
        return this.extendedAttributes.getVacationAccrualFlag();
    }

    /**
     * Gets the value of onSabbatical.
     *
     * @return the value of onSabbatical
     */
    @Override
    public Boolean getOnSabbaticalFlag() {
        return this.extendedAttributes.getOnSabbaticalFlag();
    }

    /**
     * Gets the value of idProvided.
     *
     * @return the value of idProvided
     */
    @Override
    public String getIdProvided() {
        return this.extendedAttributes.getIdProvided();
    }

    /**
     * Gets the value of idVerified.
     *
     * @return the value of idVerified
     */
    @Override
    public String getIdVerified() {
        return this.extendedAttributes.getIdVerified();
    }

    /**
     * Gets the value of addressLine1.
     *
     * @return the value of addressLine1
     */
    @Override
    public String getAddressLine1() {
        final EntityAddressContract address = this.getDefaultActiveAddress();
        return address.getLine1() != null ? address.getLine1() : "";
    }

    /**
     * Gets the value of addressLine2.
     *
     * @return the value of addressLine2
     */
    @Override
    public String getAddressLine2() {
        final EntityAddressContract address = this.getDefaultActiveAddress();
        return address.getLine2() != null ? address.getLine2() : "";
    }

    /**
     * Gets the value of addressLine3.
     *
     * @return the value of addressLine3
     */
    @Override
    public String getAddressLine3() {
        final EntityAddressContract address = this.getDefaultActiveAddress();
        return address.getLine3() != null ? address.getLine3() : "";
    }

    /**
     * Gets the value of city.
     *
     * @return the value of city
     */
    @Override
    public String getCity() {
        final EntityAddressContract address = this.getDefaultActiveAddress();
        return address.getCity() != null ? address.getCity() : "";
    }

    /**
     * Gets the value of county.
     *
     * @return the value of county
     */
    @Override
    public String getCounty() {
        return this.extendedAttributes.getCounty();
    }

    @Override
    public Integer getCitizenshipTypeCode() {
    	return this.extendedAttributes.getCitizenshipTypeCode();
    }

    /**
     * Gets the value of state.
     *
     * @return the value of state
     */
    @Override
    public String getState() {
        final EntityAddressContract address = this.getDefaultActiveAddress();
        return address.getStateProvinceCode() != null ? address.getStateProvinceCode() : "";
    }

    /**
     * Gets the value of postalCode.
     *
     * @return the value of postalCode
     */
    @Override
    public String getPostalCode() {
        final EntityAddressContract address = this.getDefaultActiveAddress();
        return address.getPostalCode() != null ? address.getPostalCode() : "";
    }

    /**
     * Gets the value of countryCode.
     *
     * @return the value of countryCode
     */
    @Override
    public String getCountryCode() {
        final EntityAddressContract address = this.getDefaultActiveAddress();
        return address.getCountryCode() != null ? this.convert2DigitCountryCodeTo3Digit(address.getCountryCode()) : "";
    }
    
    /**
     * Gets the value of faxNumber.
     *
     * @return the value of faxNumber
     */
    @Override
    public String getFaxNumber() {
        return this.getPhoneNumber("FAX");
    }
    
    /**
     * Gets the value of pagerNumber.
     *
     * @return the value of pagerNumber
     */
    @Override
    public String getPagerNumber() {      
        return this.getPhoneNumber("PGR");
    }

    /**
     * Gets the value of mobilePhoneNumber.
     *
     * @return the value of mobilePhoneNumber
     */
    @Override
    public String getMobilePhoneNumber() {
        return this.getPhoneNumber("MBL");
    }

    /**
     * Gets the value of eraCommonsUserName.
     *
     * @return the value of eraCommonsUserName
     */
    @Override
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

    @Override
    public boolean isActive() {
        return getActive();
    }

    @Override
    public String getIdentifier() {
        return this.getPersonId();
    }
    
    @Override
    public Unit getUnit() {
        final String org = this.getOrganizationIdentifier();
        
        return org != null ? (Unit) this.getBusinessObjectService().findByPrimaryKey(Unit.class, Collections.singletonMap("unitNumber", org)) : null; 
    }

    @Override
    public String getPhoneNumber() {
        return this.getOfficePhone();
    }

    @Override
    public String getContactOrganizationName() {
        return this.getUnit() != null ? this.getUnit().getUnitName() : null;
    }

    @Override
    public String getOrganizationIdentifier() {
        final EntityEmploymentContract emp = this.entity.getPrimaryEmployment();
        if (emp == null) {
            return "";
        }
        
        return emp.getPrimaryDepartmentCode() != null ? emp.getPrimaryDepartmentCode() : "";
    }
    
    /**
     * Gets the campus code.
     * @return the campus code
     */
    @Override
    public String getCampusCode() {
        return this.getCampusCode(true);
    }
    
    /**
     * Gets the {@link BusinessObjectService BusinessObjectService}.
     * @return Gets the BusinessObjectService
     */
    BusinessObjectService getBusinessObjectService() {
        if (this.boService == null) {
            this.boService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return this.boService;
    }
    
    /**
     * Gets the {@link IdentityService IdentityService}.
     * @return Gets the IdentityService
     */
    IdentityService getIdentityService() {
        if (this.identityService == null) {
            this.identityService = KcServiceLocator.getService(IdentityService.class);
        }
        return this.identityService;
    }
    
    /**
     * Gets the {@link CountryService CountryService}.
     * @return Gets the CountryService
     */
    CountryService getCountryService() {
        if (this.countryService == null) {
            this.countryService = KcServiceLocator.getService(CountryService.class);
        }
        return this.countryService;
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
    private String getPhoneNumber(final String type) {
        return selectSingleValue(this.getEntityType().getPhoneNumbers(), new Selector<EntityPhoneContract, String>() {
            public String notFoundValue() { return ""; }
            public String select(EntityPhoneContract a) { return a.getPhoneNumber(); }
            public boolean shouldSelect(EntityPhoneContract a) { return type.equals(a.getPhoneType().getCode()) && a.getPhoneNumber() != null; }
        });
    }
    
    /**
     * Gets the phone number for a specific type and matching the default indicator
     * out of the KIM object.  This phone number will not contain country code
     * or extension b/c phone numbers in KC do not have them.  Will return an empty string
     * if unable to find a number for a type/default ind.
     * @param type the type
     * @param isDefault the number must be default
     * @return the phone number (ex: 123-4567)
     */
    private String getPhoneNumber(final String type, final boolean isDefault) {
        return selectSingleValue(this.getEntityType().getPhoneNumbers(), new Selector<EntityPhoneContract, String>() {
            public String notFoundValue() { return ""; }
            public String select(EntityPhoneContract a) { return a.getPhoneNumber(); }
            public boolean shouldSelect(EntityPhoneContract a) { return type.equals(a.getPhoneType().getCode()) && isDefault == a.isDefaultValue() && a.getPhoneNumber() != null; }
        });
    }
    
    /**
     * Checks if the KIM entity has an affiliation based on a type code.
     * @param affilTypeCode the affliation type code
     * @return true if the entity has an affiliation
     */
    private boolean hasAffiliation(final String affilTypeCode) {
        return selectSingleValue(this.entity.getAffiliations(), new Selector<EntityAffiliationContract, Boolean>() {
            public Boolean notFoundValue() { return Boolean.FALSE; }
            public Boolean select(EntityAffiliationContract a) { return Boolean.TRUE; }
            public boolean shouldSelect(EntityAffiliationContract a) { return affilTypeCode.equals(a.getAffiliationType().getCode()); }
        });
    }
    
    /**
     * Gets the campus code matching the default indicator out of the KIM object.
     * @param isDefault whether the campus code must be default
     * @return the campus code
     */
    private String getCampusCode(final boolean isDefault) {
        return selectSingleValue(this.entity.getAffiliations(), new Selector<EntityAffiliationContract, String>() {
            public String notFoundValue() { return ""; }
            public String select(EntityAffiliationContract a) { return a.getCampusCode(); }
            public boolean shouldSelect(EntityAffiliationContract a) { return isDefault == a.isDefaultValue(); }
        });
    }
    
    /**
     * Formats a date in the format KC expects.
     * @param dte the date to format
     * @return the formatted date
     * @see #getKCDateFormat() for the format
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
        final EntityExternalIdentifierContract extId = this.entity.getEntityExternalIdentifier(type);
        return extId != null ? extId.getExternalId() : ""; 
    }
    
    /** 
     * Gets the default &amp; active address.  will never return null
     * @return address
     */
    private EntityAddressContract getDefaultActiveAddress() {
        return selectSingleValue(this.getEntityType().getAddresses(), new Selector<EntityAddressContract, EntityAddressContract>() {
            public EntityAddressContract notFoundValue() { return EntityAddress.Builder.create(); }
            public EntityAddressContract select(EntityAddressContract a) { return a; }
            public boolean shouldSelect(EntityAddressContract a) { return a.isActive() && a.isDefaultValue(); }
        });
    }
    
    /**
     * Gets the entity type that represents a person in KC.  KC only supports a single entity type. will not return null.
     * @return the entity type object
     */
    private EntityTypeContactInfoContract getEntityType() {     
        final EntityTypeContactInfoContract ent = this.entity.getEntityTypeContactInfoByTypeCode("PERSON");
        
        return ent != null ? ent : EntityTypeContactInfo.Builder.create("","");
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
        final Country country = this.getCountryService().getCountry(digit2);
        return country != null && country.getAlternateCode() != null ? country.getAlternateCode() : "";
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
    private PrincipalContract getPrincipal() {
        return selectSingleValue(this.entity.getPrincipals(), new Selector<PrincipalContract, PrincipalContract>() {
            public PrincipalContract notFoundValue() { return null; } 
            public PrincipalContract select(PrincipalContract a) { return a; }
            public boolean shouldSelect(PrincipalContract a) { return personId.equals(a.getPrincipalId()); }
        });
    }
    
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
    
    /**
     * This method exists to cut down on all the branching, looping, etc. that has to be done for kim
     * in order to handle nulls and other conditions. Essentially all the logic in this method would have
     * had to be duplicated in every getter accessing a collection from kim.
     * 
     * @param <A> the input type
     * @param <B> the output type
     * @param values the collection to select from
     * @param selector the selector to use to determine the selected value
     * @return the selected value
     */
    private static <A, B> B selectSingleValue(Collection<? extends A> values, Selector<A, ? extends B> selector) {
        assert selector != null : "selector is null";
        
        if (values == null) {
            return selector.notFoundValue();
        }
        
        for (A a : values) {
            if (a == null) {
                continue;
            }
            
            if (selector.shouldSelect(a)) {
                return selector.select(a);
            }
        }
        
        return selector.notFoundValue();
    }
    
    /**
     * Used when selecting a value.
     */
    private static interface Selector<A, B> {
        /** should the passed in value be selected from. */
        boolean shouldSelect(A a);
        /** select from A deriving B. */ 
        B select(A a);
        /** value to use when no values are selected. */
        B notFoundValue();
    }
}
