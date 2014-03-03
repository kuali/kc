/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.coeus.common.framework.rolodex;

import org.kuali.coeus.common.framework.contactable.Contactable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLODEX")
public class Rolodex extends KcPersistableBusinessObjectBase implements Contactable, MutableInactivatable {

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
    private Boolean sponsorAddressFlag;

    @Transient
    private String isSponsorAddress = "N";

    @Column(name = "SPONSOR_CODE")
    private String sponsorCode;

    @Column(name = "STATE")
    private String state;

    @Column(name = "SUFFIX")
    private String suffix;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne(targetEntity = Unit.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "OWNED_BY_UNIT", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @ManyToOne(targetEntity = Sponsor.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SPONSOR_CODE", referencedColumnName = "SPONSOR_CODE", insertable = false, updatable = false)
    private Sponsor sponsor;

    @ManyToOne(targetEntity = Organization.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organizations;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "ACTV_IND")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    // = Boolean.TRUE;  
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
        if (this.isSponsorAddress != null) {
            if (this.isSponsorAddress.equalsIgnoreCase("Y")) {
                if (sponsor != null && sponsor.getPostalCode() != null) {
                    return sponsor.getPostalCode();
                }
            }
            if (this.isSponsorAddress.equalsIgnoreCase("N")) {
                if (organizations != null && organizations.getAddress() != null) {
                    return organizations.getAddress();
                }
            }
        }
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        if (this.isSponsorAddress != null) {
            if (this.isSponsorAddress.equalsIgnoreCase("Y")) {
                if (sponsor != null && sponsor.getState() != null) {
                    return sponsor.getState();
                }
            }
            if (this.isSponsorAddress.equalsIgnoreCase("N")) {
                if (organizations != null) {
                    return null;
                }
            }
        }
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        if (this.isSponsorAddress != null) {
            if (this.isSponsorAddress.equalsIgnoreCase("Y")) {
                if (sponsor != null && sponsor.getCountryCode() != null) {
                    return sponsor.getCountryCode();
                }
            }
            if (this.isSponsorAddress.equalsIgnoreCase("N")) {
                if (organizations != null) {
                    return null;
                }
            }
        }
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
        if (this.isSponsorAddress != null) {
            if (this.isSponsorAddress.equalsIgnoreCase("Y")) {
                if (sponsor != null && sponsor.getSponsorName() != null) {
                    return sponsor.getSponsorName();
                }
            }
            if (this.isSponsorAddress.equalsIgnoreCase("N")) {
                if (organizations != null && organizations.getOrganizationName() != null) {
                    return organizations.getOrganizationName();
                }
            }
        }
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

    public Boolean getSponsorAddressFlag() {
        return sponsorAddressFlag;
    }

    public void setSponsorAddressFlag(Boolean sponsorAddressFlag) {
        this.sponsorAddressFlag = sponsorAddressFlag;
    }

    /**
     * Gets the isSponsorAddress attribute. 
     * @return Returns the isSponsorAddress.
     */
    public String getIsSponsorAddress() {
        return isSponsorAddress;
    }

    /**
     * Sets the isSponsorAddress attribute value.
     * @param isSponsorAddress The isSponsorAddress to set.
     */
    public void setIsSponsorAddress(String isSponsorAddress) {
        this.isSponsorAddress = isSponsorAddress;
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

    public void setOrganizations(Organization organizations) {
        this.organizations = organizations;
    }

    public Organization getOrganizations() {
        return organizations;
    }

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

    /** {@inheritDoc} */
    public String getContactOrganizationName() {
        return this.getUnit().getUnitName();
    }

    /** {@inheritDoc} */
    public Serializable getIdentifier() {
        return this.getRolodexId();
    }

    /** {@inheritDoc} */
    public String getOrganizationIdentifier() {
        return this.getUnit().getOrganizationId();
    }
}
