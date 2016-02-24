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
package org.kuali.coeus.common.framework.sponsor;

import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.external.dunningcampaign.DunningCampaign;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

/**
 * Class representing a Sponsor Business Object
 */
@Entity
@Table(name = "SPONSOR")
public class Sponsor extends KcPersistableBusinessObjectBase implements SponsorContract {

    @PortableSequenceGenerator(name = "SEQ_SPONSOR_CODE")
    @GeneratedValue(generator = "SEQ_SPONSOR_CODE")
    @Id
    @Column(name = "SPONSOR_CODE")
    private String sponsorCode;

    @Column(name = "ACRONYM")
    private String acronym;

    @Column(name = "AUDIT_REPORT_SENT_FOR_FY")
    private String auditReportSentForFy;

    @Column(name = "CAGE_NUMBER")
    private String cageNumber;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "DODAC_NUMBER")
    private String dodacNumber;

    @Column(name = "DUN_AND_BRADSTREET_NUMBER")
    private String dunAndBradstreetNumber;

    @Column(name = "DUNS_PLUS_FOUR_NUMBER")
    private String dunsPlusFourNumber;

    @Column(name = "OWNED_BY_UNIT")
    private String ownedByUnit;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "ROLODEX_ID")
    private Integer rolodexId;

    @Column(name = "SPONSOR_NAME")
    private String sponsorName;

    @Column(name = "SPONSOR_TYPE_CODE")
    private String sponsorTypeCode;

    @Column(name = "STATE")
    private String state;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "DUNNING_CAMPAIGN_ID")
    private String dunningCampaignId;

    @Transient
    private DunningCampaign dunningCampaign;

    @Column(name = "CUSTOMER_NUMBER")
    private String customerNumber;

    @Transient
    private String customerExists;

    @Transient
    private String customerTypeCode;

    @Column(name = "ACTV_IND")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SPONSOR_TYPE_CODE", referencedColumnName = "SPONSOR_TYPE_CODE", insertable = false, updatable = false)
    private SponsorType sponsorType;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "OWNED_BY_UNIT", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @OneToOne(mappedBy = "sponsor", orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "ROLODEX_ID", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex rolodex;

    public Sponsor() {
        super();
        setCreateUser(getUpdateUser());
    }

    @Override
    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    @Override
    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public String getAuditReportSentForFy() {
        return auditReportSentForFy;
    }

    public void setAuditReportSentForFy(String auditReportSentForFy) {
        this.auditReportSentForFy = auditReportSentForFy;
    }

    @Override
    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String getDodacNumber() {
        return dodacNumber;
    }

    public void setDodacNumber(String dodacNumber) {
        this.dodacNumber = dodacNumber;
    }

    @Override
    public String getDunAndBradstreetNumber() {
        return dunAndBradstreetNumber;
    }

    public void setDunAndBradstreetNumber(String dunAndBradstreetNumber) {
        this.dunAndBradstreetNumber = dunAndBradstreetNumber;
    }

    @Override
    public String getDunsPlusFourNumber() {
        return dunsPlusFourNumber;
    }

    public void setDunsPlusFourNumber(String dunsPlusFourNumber) {
        this.dunsPlusFourNumber = dunsPlusFourNumber;
    }

    @Override
    public String getOwnedByUnit() {
        return ownedByUnit;
    }

    public void setOwnedByUnit(String ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    @Override
    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorTypeCode() {
        return sponsorTypeCode;
    }

    public void setSponsorTypeCode(String sponsorTypeCode) {
        this.sponsorTypeCode = sponsorTypeCode;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    @Override
    public SponsorType getSponsorType() {
        return sponsorType;
    }

    public void setSponsorType(SponsorType sponsorType) {
        this.sponsorType = sponsorType;
    }

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

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDunningCampaignId() {
        return dunningCampaignId;
    }

    public void setDunningCampaignId(String dunningCampaignId) {
        this.dunningCampaignId = dunningCampaignId;
    }

    public DunningCampaign getDunningCampaign() {
        return dunningCampaign;
    }

    public void setDunningCampaign(DunningCampaign dunningCampaign) {
        this.dunningCampaign = dunningCampaign;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerExists() {
        return customerExists;
    }

    public void setCustomerExists(String customerExists) {
        this.customerExists = customerExists;
    }

    public String getCustomerTypeCode() {
        return customerTypeCode;
    }

    public void setCustomerTypeCode(String customerTypeCode) {
        this.customerTypeCode = customerTypeCode;
    }

    @Override
    public void refreshNonUpdateableReferences() {

    }
}
