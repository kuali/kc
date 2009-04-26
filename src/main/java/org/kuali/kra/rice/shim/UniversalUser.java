/*
 * Copyright 2005-2007 The Kuali Foundation.
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

package org.kuali.kra.rice.shim;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.bo.impl.PersonImpl;
import org.kuali.rice.kns.bo.Campus;
import org.kuali.rice.kns.service.KualiModuleService;
import org.kuali.rice.kns.util.KualiDecimal;


/**
 * 
 */
@Entity
@Table(name="FS_UNIVERSAL_USR_T")
public class UniversalUser extends PersonImpl implements Person {
    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UniversalUser.class);

    private transient static UniversalUserService universalUserService;
    private transient static KualiModuleService kualiModuleService;
    
    @Id
	@Column(name="PERSON_UNVL_ID")
	private String personUniversalIdentifier;
    @Column(name="PERSON_USER_ID")
	private String personUserIdentifier;
    @Column(name="PRSN_PYRL_ID")
	private String personPayrollIdentifier;
    @Column(name="PRSN_TAX_ID")
	private String personTaxIdentifier;
    @Column(name="PRSN_TAX_ID_TYP_CD")
	private String personTaxIdentifierTypeCode;
    @Column(name="PRSN_1ST_NM")
	private String personFirstName;
    @Column(name="PRSN_LST_NM")
	private String personLastName;
    @Column(name="PRSN_MID_NM")
	private String personMiddleName;
    @Column(name="PERSON_NM")
	private String personName;
    @Column(name="PRSN_EMAIL_ADDR")
	private String personEmailAddress;
    @Column(name="CAMPUS_CD")
	private String campusCode;
    @Column(name="EMP_PRM_DEPT_CD")
	private String primaryDepartmentCode;
    @Column(name="PRSN_CMP_ADDR")
	private String personCampusAddress;
    @Column(name="PRSN_LOC_PHN_NBR")
	private String personLocalPhoneNumber;
    @Column(name="EMP_STAT_CD")
	private String employeeStatusCode;
    @Column(name="EMP_TYPE_CD")
	private String employeeTypeCode;
    @Column(name="PRSN_BASE_SLRY_AMT")
	private KualiDecimal personBaseSalaryAmount;
    @Column(name="FS_ENCRPTD_PWD_TXT")
	private String financialSystemsEncryptedPasswordText;
    
    @Column(name="PRSN_STU_IND")
	private boolean student;
    @Column(name="PRSN_STAFF_IND")
	private boolean staff;
    @Column(name="PRSN_FAC_IND")
	private boolean faculty;
    @Column(name="PRSN_AFLT_IND")
	private boolean affiliate;
    
    @OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinColumn(name="CAMPUS_CD", insertable=false, updatable=false)
	private Campus campus;
    @OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinColumn(name="EMP_STAT_CD", insertable=false, updatable=false)
    
    // TODO this shouldn't really be here
    // need to push a list of changed module codes into workflow - doing this in the UniversalUserPreRules
    // - ideally this would go on the doc, since it relates to the old and new maintainable, but no option for extending document implementation in the maint framework
    // - so, we could put it on the maintainable, but properties of the maintainable don't get put in the main doc xml in populateXmDocumentContentsFromMaintainables
    // - so, we could derive and set after populateMaintainablesFromXmlDocumentContents, but i don't see a hook - have processAfterRetrieve called then on the new maintainable, but we need the od maintainable to derive
    // - so, i could modify the framework - e.g. implement handleRouteLevelChange on maintenance document and call a method on the maintainable, where i pass the prior row - seems like a big change for this need
    @Transient
    private Set<String> changedModuleCodes;
    
    /**
     * Default no-arg constructor.
     */
    public UniversalUser() {
    }
    
    public UniversalUser(Person person) {
        this.setPersonUniversalIdentifier(person.getEntityId());
        this.setPersonUserIdentifier(person.getPrincipalName());
        this.setPersonPayrollIdentifier(person.getEmployeeId());
        this.setPersonTaxIdentifier(person.getEmployeeId());
        //private String personTaxIdentifierTypeCode;
        
        this.setPersonFirstName(person.getFirstName());
        this.setPersonLastName(person.getLastName());
        this.setPersonMiddleName(person.getMiddleName());
        this.setPersonName(person.getName());
        this.setPersonEmailAddress(person.getEmailAddress());
        this.setCampusCode(person.getCampusCode());
        this.setPrimaryDepartmentCode(person.getPrimaryDepartmentCode());
        // private String personCampusAddress;
        this.setPersonLocalPhoneNumber(person.getPhoneNumber());
        this.setEmployeeStatusCode(person.getEmployeeStatusCode());
        this.setEmployeeTypeCode(person.getEmployeeTypeCode());
        this.setPersonBaseSalaryAmount(person.getBaseSalaryAmount());
        principalId = person.getPrincipalId();
        principalName = person.getPrincipalName();
        // private String financialSystemsEncryptedPasswordText;
        
//        @Column(name="PRSN_STU_IND")
//        private boolean student;
//        @Column(name="PRSN_STAFF_IND")
//        private boolean staff;
//        @Column(name="PRSN_FAC_IND")
//        private boolean faculty;
//        @Column(name="PRSN_AFLT_IND")
//        private boolean affiliate;
    }


    /**
     * Gets the personUniversalIdentifier attribute.
     * 
     * @return - Returns the personUniversalIdentifier
     * 
     */
    public String getPersonUniversalIdentifier() {
        return personUniversalIdentifier;
    }


    /**
     * Sets the personUniversalIdentifier attribute.
     * 
     * @param personUniversalIdentifier The personUniversalIdentifier to set.
     * 
     */
    public void setPersonUniversalIdentifier(String personUniversalIdentifier) {
        this.personUniversalIdentifier = personUniversalIdentifier;
    }

    /**
     * Gets the personUserIdentifier attribute.
     * 
     * @return - Returns the personUserIdentifier
     * 
     */
    public String getPersonUserIdentifier() {
        return personUserIdentifier;
    }


    /**
     * Sets the personUserIdentifier attribute.
     * 
     * @param personUserIdentifier The personUserIdentifier to set.
     * 
     */
    public void setPersonUserIdentifier(String personUserIdentifier) {
        this.personUserIdentifier = personUserIdentifier;
    }

    /**
     * Gets the employeeStatusCode attribute.
     * 
     * @return - Returns the employeeStatusCode
     * 
     */
    public String getEmployeeStatusCode() {
        return employeeStatusCode;
    }


    /**
     * Sets the employeeStatusCode attribute.
     * 
     * @param employeeStatusCode The employeeStatusCode to set.
     * 
     */
    public void setEmployeeStatusCode(String employeeStatusCode) {
        this.employeeStatusCode = employeeStatusCode;
    }

    /**
     * Gets the personPayrollIdentifier attribute.
     * 
     * @return - Returns the personPayrollIdentifier
     * 
     */
    public String getPersonPayrollIdentifier() {
        return personPayrollIdentifier;
    }


    /**
     * Sets the personPayrollIdentifier attribute.
     * 
     * @param personPayrollIdentifier The personPayrollIdentifier to set.
     * 
     */
    public void setPersonPayrollIdentifier(String emplid) {
        this.personPayrollIdentifier = emplid;
    }

    /**
     * Gets the primaryDepartmentCode attribute.
     * 
     * @return - Returns the primaryDepartmentCode
     * 
     */
    public String getPrimaryDepartmentCode() {
        return primaryDepartmentCode;
    }


    /**
     * Sets the primaryDepartmentCode attribute.
     * 
     * @param primaryDepartmentCode The primaryDepartmentCode to set.
     * 
     */
    public void setPrimaryDepartmentCode(String deptid) {
        this.primaryDepartmentCode = deptid;
    }

    /**
     * Gets the personEmailAddress attribute.
     * 
     * @return - Returns the personEmailAddress
     * 
     */
    public String getPersonEmailAddress() {
        return personEmailAddress;
    }


    /**
     * Sets the personEmailAddress attribute.
     * 
     * @param personEmailAddress The personEmailAddress to set.
     * 
     */
    public void setPersonEmailAddress(String personEmailAddress) {
        this.personEmailAddress = personEmailAddress;
    }

    /**
     * Gets the personFirstName attribute.
     * 
     * @return - Returns the personFirstName
     * 
     */
    public String getPersonFirstName() {
        return personFirstName;
    }


    /**
     * Sets the personFirstName attribute.
     * 
     * @param personFirstName The personFirstName to set.
     * 
     */
    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    /**
     * Gets the personLastName attribute.
     * 
     * @return - Returns the personLastName
     * 
     */
    public String getPersonLastName() {
        return personLastName;
    }


    /**
     * Sets the personLastName attribute.
     * 
     * @param personLastName The personLastName to set.
     * 
     */
    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    /**
     * Gets the personCampusAddress attribute.
     * 
     * @return - Returns the personCampusAddress
     * 
     */
    public String getPersonCampusAddress() {
        return personCampusAddress;
    }


    /**
     * Sets the personCampusAddress attribute.
     * 
     * @param personCampusAddress The personCampusAddress to set.
     * 
     */
    public void setPersonCampusAddress(String personCampusAddress) {
        this.personCampusAddress = personCampusAddress;
    }

    /**
     * Gets the personLocalPhoneNumber attribute.
     * 
     * @return - Returns the personLocalPhoneNumber
     * 
     */
    public String getPersonLocalPhoneNumber() {
        return personLocalPhoneNumber;
    }


    /**
     * Sets the personLocalPhoneNumber attribute.
     * 
     * @param personLocalPhoneNumber The personLocalPhoneNumber to set.
     * 
     */
    public void setPersonLocalPhoneNumber(String personLocalPhoneNumber) {
        this.personLocalPhoneNumber = personLocalPhoneNumber;
    }

    /**
     * Gets the personBaseSalaryAmount attribute.
     * 
     * @return - Returns the personBaseSalaryAmount
     * 
     */
    public KualiDecimal getPersonBaseSalaryAmount() {
        return personBaseSalaryAmount;
    }


    /**
     * Sets the personBaseSalaryAmount attribute.
     * 
     * @param personBaseSalaryAmount The personBaseSalaryAmount to set.
     * 
     */
    public void setPersonBaseSalaryAmount(KualiDecimal personBaseSalaryAmount) {
        this.personBaseSalaryAmount = personBaseSalaryAmount;
    }

    /**
     * @return Returns the personTaxIdentifier.
     */
    public String getPersonTaxIdentifier() {
        return personTaxIdentifier;
    }

    /**
     * @param personTaxIdentifier The personTaxIdentifier to set.
     */
    public void setPersonTaxIdentifier(String personSocialSecurityNbrId) {
        this.personTaxIdentifier = personSocialSecurityNbrId;
    }

    /**
     * @return Returns the campusCode.
     */
    public String getCampusCode() {
        return campusCode;
    }

    /**
     * @param campusCode The campusCode to set.
     */
    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    /**
     * @return Returns the campus.
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * @param campus The campus to set.
     * @deprecated
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    
    

    /**
     * Gets the personName attribute. 
     * @return Returns the personName.
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Sets the personName attribute value.
     * @param personName The personName to set.
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * Gets the financialSystemsEncryptedPasswordText attribute. 
     * @return Returns the financialSystemsEncryptedPasswordText.
     */
    public String getFinancialSystemsEncryptedPasswordText() {
        return financialSystemsEncryptedPasswordText;
    }

    /**
     * Sets the financialSystemsEncryptedPasswordText attribute value.
     * @param financialSystemsEncryptedPasswordText The financialSystemsEncryptedPasswordText to set.
     */
    public void setFinancialSystemsEncryptedPasswordText(String financialSystemsEncryptedPasswordText) {
        this.financialSystemsEncryptedPasswordText = financialSystemsEncryptedPasswordText;
    }    

    @Transient
    private Map<String,Map<String,String>> moduleProperties;

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("personUniversalIdentifier", this.personUniversalIdentifier);
        return m;
    }

    public String getPersonMiddleName() {
        return personMiddleName;
    }


    public void setPersonMiddleName(String personMiddleName) {
        this.personMiddleName = personMiddleName;
    }


    public String getEmployeeTypeCode() {
        return employeeTypeCode;
    }


    public void setEmployeeTypeCode(String personTypeCode) {
        this.employeeTypeCode = personTypeCode;
    }


    public String getPersonTaxIdentifierTypeCode() {
        return personTaxIdentifierTypeCode;
    }


    public void setPersonTaxIdentifierTypeCode(String personTaxIdentifierTypeCode) {
        this.personTaxIdentifierTypeCode = personTaxIdentifierTypeCode;
    }


    public boolean isAffiliate() {
        return affiliate;
    }


    public void setAffiliate(boolean affiliate) {
        this.affiliate = affiliate;
    }


    public boolean isFaculty() {
        return faculty;
    }


    public void setFaculty(boolean faculty) {
        this.faculty = faculty;
    }


    public boolean isStaff() {
        return staff;
    }


    public void setStaff(boolean staff) {
        this.staff = staff;
    }


    public boolean isStudent() {
        return student;
    }


    public void setStudent(boolean student) {
        this.student = student;
    }

    /* added for XStream de-serialization purposes */ 
    @Deprecated
    @Transient
    private transient String emplid;    
    @Deprecated
    @Transient
    private transient String deptid;

    /**
     * Gets the changedModuleCodes attribute. 
     * @return Returns the changedModuleCodes.
     */
    public Set<String> getChangedModuleCodes() {
        return changedModuleCodes;
    }

    /**
     * Sets the changedModuleCodes attribute value.
     * @param changedModuleCodes The changedModuleCodes to set.
     */
    public void setChangedModuleCodes(Set<String> changedModuleCodes) {
        this.changedModuleCodes = changedModuleCodes;
    }
    
    // KIM Person interface methods
//    public String getPrincipalId() { return null; }
//    public String getPrincipalName() { return null; }
//    public String getEntityId() { return null; }
//    public String getEntityTypeCode() { return null; }
//    
//    /**
//     * The first name from the default name record for the entity.
//     */
//    public String getFirstName() { return null; }
//    public String getMiddleName() { return null; }
//    public String getLastName() { return null; }
//    
//    /*
//     * Method which composites the first, middle and last names.
//     */
//    public String getName() { return null; }
//
//    public String getEmailAddress() { return null; }
//    
//    /**
//     * Returns line1 of the default address for the Person.  Will lazy-load the information from the
//     * IdentityManagementService if requested. 
//     */
//    public String getAddressLine1() { return null; }
//    /**
//     * Returns line2 of the default address for the Person.  Will lazy-load the information from the
//     * IdentityManagementService if requested. 
//     */
//    public String getAddressLine2() { return null; }
//    /**
//     * Returns line3 of the default address for the Person.  Will lazy-load the information from the
//     * IdentityManagementService if requested. 
//     */
//    public String getAddressLine3() { return null; }
//    /**
//     * Returns the city name from the default address for the Person.  Will lazy-load the information from the
//     * IdentityManagementService if requested. 
//     */
//    public String getAddressCityName() { return null; }
//    /**
//     * Returns the state code from the default address for the Person.  Will lazy-load the information from the
//     * IdentityManagementService if requested. 
//     */
//    public String getAddressStateCode() { return null; }
//    /**
//     * Returns the postal code from the default address for the Person.  Will lazy-load the information from the
//     * IdentityManagementService if requested. 
//     */
//    public String getAddressPostalCode() { return null; }
//    /**
//     * Returns the country code from the default address for the Person.  Will lazy-load the information from the
//     * IdentityManagementService if requested. 
//     */
//    public String getAddressCountryCode() { return null; }
//    
//    /** Returns the default phone number for the entity.
//     */
//    public String getPhoneNumber() { return null; }
//
//    public String getCampusCode() { return null; }
//        
//    public Map<String,String> getExternalIdentifiers() { return null; }
//    
//    /** Checks whether the person has an affiliation of a particular type: staff/faculty/student/etc... */
//    public boolean hasAffiliationOfType( String affiliationTypeCode ) { return true; }
//    
//    public List<String> getCampusCodesForAffiliationOfType(String affiliationTypeCode) { return null; }
//    
//    public String getEmployeeStatusCode() { return null; }
//    public String getEmployeeTypeCode() { return null; }
//    public KualiDecimal getBaseSalaryAmount() { return null; }
//    
//    public String getExternalId( String externalIdentifierTypeCode ) { return null; }
//    
//    public String getPrimaryDepartmentCode() { return null; }
//    
//    public String getEmployeeId() { return null; }
//    public boolean isActive() { return true; }
}
