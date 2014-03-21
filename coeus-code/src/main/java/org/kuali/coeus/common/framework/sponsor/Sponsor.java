/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.sponsor;

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

/**
 * Class representing a Sponsor Business Object
 */
@Entity
@Table(name = "SPONSOR")
public class Sponsor extends KcPersistableBusinessObjectBase {

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

    @ManyToOne(targetEntity = SponsorType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SPONSOR_TYPE_CODE", referencedColumnName = "SPONSOR_TYPE_CODE", insertable = false, updatable = false)
    private SponsorType sponsorType;

    @ManyToOne(targetEntity = Unit.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "OWNED_BY_UNIT", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @ManyToOne(targetEntity = Rolodex.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ROLODEX_ID", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex rolodex;

    @Column(name = "ACTV_IND")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    public Sponsor() {
        super();
        setCreateUser(getUpdateUser());
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAuditReportSentForFy() {
        return auditReportSentForFy;
    }

    public void setAuditReportSentForFy(String auditReportSentForFy) {
        this.auditReportSentForFy = auditReportSentForFy;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDodacNumber() {
        return dodacNumber;
    }

    public void setDodacNumber(String dodacNumber) {
        this.dodacNumber = dodacNumber;
    }

    public String getDunAndBradstreetNumber() {
        return dunAndBradstreetNumber;
    }

    public void setDunAndBradstreetNumber(String dunAndBradstreetNumber) {
        this.dunAndBradstreetNumber = dunAndBradstreetNumber;
    }

    public String getDunsPlusFourNumber() {
        return dunsPlusFourNumber;
    }

    public void setDunsPlusFourNumber(String dunsPlusFourNumber) {
        this.dunsPlusFourNumber = dunsPlusFourNumber;
    }

    public String getOwnedByUnit() {
        return ownedByUnit;
    }

    public void setOwnedByUnit(String ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

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

    public SponsorType getSponsorType() {
        return sponsorType;
    }

    public void setSponsorType(SponsorType sponsorType) {
        this.sponsorType = sponsorType;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
