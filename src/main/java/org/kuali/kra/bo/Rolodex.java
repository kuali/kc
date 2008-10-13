package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.ForceDiscriminator;
import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="ROLODEX")
@DiscriminatorValue("OrganizationalRolodex")
@DiscriminatorFormula("case when (FIRST_NAME is null and LAST_NAME is null) then 'OrganizationalRolodex' else 'NonOrganizationalRolodex' end")
public class Rolodex extends KraPersistableBusinessObjectBase {
    @Id
    @Column(name="ROLODEX_ID")
    private Integer rolodexId;
    @Column(name="ADDRESS_LINE_1")
    private String addressLine1;
    @Column(name="ADDRESS_LINE_2")
    private String addressLine2;
    @Column(name="ADDRESS_LINE_3")
    private String addressLine3;
    @Column(name="CITY")
    private String city;
    @Column(name="COMMENTS")
    private String comments;
    @Column(name="COUNTRY_CODE", updatable=false, insertable=false )
    private String countryCode;
    @Column(name="COUNTY")
    private String county;
    @Column(name="DELETE_FLAG")
    private Boolean deleteFlag;
    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name="FAX_NUMBER")
    private String faxNumber;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="MIDDLE_NAME")
    private String middleName;
    @Column(name="ORGANIZATION")
    private String organization;
    @Column(name="OWNED_BY_UNIT")
    private String ownedByUnit;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;
    @Column(name="POSTAL_CODE")
    private String postalCode;
    @Column(name="PREFIX")
    private String prefix;
    @Type(type="yes_no")
    @Column(name="SPONSOR_ADDRESS_FLAG")
    private boolean sponsorAddressFlag;
    @Column(name="SPONSOR_CODE")
    private String sponsorCode;
    @Column(name="STATE")
    private String state;
    @Column(name="SUFFIX")
    private String suffix;
    @Column(name="TITLE")
    private String title;
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name="OWNED_BY_UNIT", insertable=false, updatable=false)
    private Unit unit;
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name="SPONSOR_CODE", insertable=false, updatable=false)
    private Sponsor sponsor;
    @Column(name="CREATE_USER")
    private String createUser;
    
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override 
    public void setUpdateUser(String updateUser) {
        super.setUpdateUser(updateUser);
        setCreateUser(updateUser);
    }
    
    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOwnedByUnit() {
        return ownedByUnit;
    }

    public void setOwnedByUnit(String ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean getSponsorAddressFlag() {
        return sponsorAddressFlag;
    }

    public void setSponsorAddressFlag(boolean sponsorAddressFlag) {
        this.sponsorAddressFlag = sponsorAddressFlag;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Unit reference referred by {@link #getOwnedByUnit()}
     *
     * @param unit 
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Unit reference referred by {@link #getOwnedByUnit()}
     *
     * @return unit 
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sponsor reference referred by {@link #getSponsorCode()}
     *
     * @param sponsor 
     */
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * Sponsor reference referred by {@link #getSponsorCode()}
     *
     * @return Sponsor 
     */
    public Sponsor getSponsor() {
        return sponsor;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("rolodexId", getRolodexId());
        hashMap.put("addressLine1", getAddressLine1());
        hashMap.put("addressLine2", getAddressLine2());
        hashMap.put("addressLine3", getAddressLine3());
        hashMap.put("city", getCity());
        hashMap.put("comments", getComments());
        hashMap.put("countryCode", getCountryCode());
        hashMap.put("county", getCounty());
        hashMap.put("deleteFlag", getDeleteFlag());
        hashMap.put("emailAddress", getEmailAddress());
        hashMap.put("faxNumber", getFaxNumber());
        hashMap.put("firstName", getFirstName());
        hashMap.put("lastName", getLastName());
        hashMap.put("middleName", getMiddleName());
        hashMap.put("organization", getOrganization());
        hashMap.put("ownedByUnit", getOwnedByUnit());
        hashMap.put("phoneNumber", getPhoneNumber());
        hashMap.put("postalCode", getPostalCode());
        hashMap.put("prefix", getPrefix());
        hashMap.put("sponsorAddressFlag", getSponsorAddressFlag());
        hashMap.put("sponsorCode", getSponsorCode());
        hashMap.put("state", getState());
        hashMap.put("suffix", getSuffix());
        hashMap.put("title", getTitle());
        return hashMap;
    }
}

