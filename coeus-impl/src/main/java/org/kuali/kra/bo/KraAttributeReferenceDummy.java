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
package org.kuali.kra.bo;


import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.RecordedUpdate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.api.model.Sortable;
import org.kuali.rice.krad.datadictionary.AttributeReference;

import java.sql.Timestamp;

/**
 * Attribute Reference Dummy Business Object.
 */
public class KraAttributeReferenceDummy extends AttributeReference implements Sortable, Describable, RecordedUpdate {

    private String description;
    private String bigDescription;
    private String city;
    private String county;
    private String state;
    private String countryCode;
    private String updateUser;
    private Timestamp updateTimestamp;
    private String principalInvestigator;
    private ScaleTwoDecimal genericRate;
    private String budgetName;
    private Integer sequenceNumber;
    private String sourceAccount;
    private Boolean checkBox;
    private Integer sortId;
    private String reporter;
    private Timestamp createTimestamp;
    private String committeeId;
    private String committeeName;
    private String sponsorS2S;

    /**
     * Gets the value of city
     *
     * @return the value of city
     */
    public final String getCity() {
        return this.city;
    }

    /**
     * Sets the value of city
     *
     * @param argCity Value to assign to this.city
     */
    public final void setCity(final String argCity) {
        this.city = argCity;
    }

    /**
     * Gets the value of county
     *
     * @return the value of county
     */
    public final String getCounty() {
        return this.county;
    }

    /**
     * Sets the value of county
     *
     * @param argCounty Value to assign to this.county
     */
    public final void setCounty(final String argCounty) {
        this.county = argCounty;
    }

    /**
     * Gets the value of state
     *
     * @return the value of state
     */
    public final String getState() {
        return this.state;
    }

    /**
     * Sets the value of state
     *
     * @param argState Value to assign to this.state
     */
    public final void setState(final String argState) {
        this.state = argState;
    }

    /**
     * Gets the value of countryCode
     *
     * @return the value of countryCode
     */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Sets the value of countryCode
     *
     * @param argCountryCode Value to assign to this.countryCode
     */
    public final void setCountryCode(final String argCountryCode) {
        this.countryCode = argCountryCode;
    }


    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    @Override
    public final String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public final void setDescription(final String argDescription) {
        this.description = argDescription;
    }

    public String getBigDescription() {
        return bigDescription;
    }

    public void setBigDescription(String bigDescription) {
        this.bigDescription = bigDescription;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(String principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public final ScaleTwoDecimal getGenericRate() {
        return genericRate;
    }

    public final void setGenericRate(ScaleTwoDecimal genericRate) {
        this.genericRate = genericRate;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(Boolean checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getSponsorS2S() {
        return sponsorS2S;
    }

    public void setSponsorS2S(String sponsorS2S) {
        this.sponsorS2S = sponsorS2S;
    }

}
