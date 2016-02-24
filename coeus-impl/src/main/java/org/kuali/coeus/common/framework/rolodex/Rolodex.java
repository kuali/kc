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
package org.kuali.coeus.common.framework.rolodex;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLODEX")
public class Rolodex extends KcPersistableBusinessObjectBase implements Contactable, MutableInactivatable, RolodexContract {


    private static final long serialVersionUID = -278526635683595863L;

    @Id
    @Column(name = "ROLODEX_ID")
    private Integer rolodexId;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "DELETE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean deleteFlag;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "FAX_NUMBER")
    private String faxNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "ORGANIZATION")
    private String organization;

    @Column(name = "OWNED_BY_UNIT")
    private String ownedByUnit;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "PREFIX")
    private String prefix;

    @Column(name = "SPONSOR_ADDRESS_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean sponsorAddressFlag;

    @Column(name = "SPONSOR_CODE")
    private String sponsorCode;

    @Column(name = "STATE")
    private String state;

    @Column(name = "SUFFIX")
    private String suffix;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "ACTV_IND")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "OWNED_BY_UNIT", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @OneToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SPONSOR_CODE", referencedColumnName = "SPONSOR_CODE", insertable = false, updatable = false)
    private Sponsor sponsor;

    @Override
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

    @Override
    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Override
    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String getOwnedByUnit() {
        return ownedByUnit;
    }

    public void setOwnedByUnit(String ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean getSponsorAddressFlag() {
        return sponsorAddressFlag;
    }

    public void setSponsorAddressFlag(boolean sponsorAddressFlag) {
        this.sponsorAddressFlag = sponsorAddressFlag;
    }

    @Override
    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
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
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the full name is format (Last, First Middle)
     * 
     * Will also leave out any name that does not exist.
     * For example: (Last, First) will be returned when no middle
     * name exists.
     * 
     * Will return <code>null</code> if no full name exists.
     * @return full name.
     */
    public String getFullName() {
        final StringBuilder name = new StringBuilder();
        if (this.getLastName() != null) {
            name.append(this.getLastName());
            name.append(", ");
        }
        if (this.getPrefix() != null) {
            name.append(this.getPrefix());
            name.append(" ");
        }
        if (this.getFirstName() != null) {
            name.append(this.getFirstName());
            name.append(" ");
        }
        if (this.getMiddleName() != null) {
            name.append(this.getMiddleName());
        }
        return name.length() > 0 ? name.toString() : null;
    }

    @Override
    public String getContactOrganizationName() {
        return this.getUnit().getUnitName();
    }

    @Override
    public Serializable getIdentifier() {
        return this.getRolodexId();
    }

    @Override
    public String getOrganizationIdentifier() {
        return this.getUnit().getOrganizationId();
    }

    //deprecated property for maint docs that might still exist
    @Deprecated
    /**
     * @deprecated
     */
    public String getIsSponsorAddress() {
    	return sponsorAddressFlag ? "Y" : "N";
    }

    @Deprecated
    /**
     * @deprecated
     */
    public void setIsSponsorAddress(String isSponsorAddress) {
    	sponsorAddressFlag = StringUtils.equalsIgnoreCase(isSponsorAddress, "Y");
    }    
}
