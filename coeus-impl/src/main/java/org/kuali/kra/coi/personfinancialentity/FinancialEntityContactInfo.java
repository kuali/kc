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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class is to contain entity's contact info, mainly addresses.  This is kc only.
 */
public class FinancialEntityContactInfo extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -279139759458525391L;

    private Long financialEntityContactInfoId;

    private Long personFinIntDisclosureId;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String city;

    private String countryCode;

    private String webAddress1;

    private String webAddress2;

    private String postalCode;

    private String state;

    private Organization organization;

    private PersonFinIntDisclosure personFinIntDisclosure;

    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }

    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getWebAddress1() {
        return webAddress1;
    }

    public void setWebAddress1(String webAddress1) {
        this.webAddress1 = webAddress1;
    }

    public String getWebAddress2() {
        return webAddress2;
    }

    public void setWebAddress2(String webAddress2) {
        this.webAddress2 = webAddress2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long getFinancialEntityContactInfoId() {
        return financialEntityContactInfoId;
    }

    public void setFinancialEntityContactInfoId(Long financialEntityContactInfoId) {
        this.financialEntityContactInfoId = financialEntityContactInfoId;
    }

    public PersonFinIntDisclosure getPersonFinIntDisclosure() {
        return personFinIntDisclosure;
    }

    public void setPersonFinIntDisclosure(PersonFinIntDisclosure personFinIntDisclosure) {
        this.personFinIntDisclosure = personFinIntDisclosure;
    }
    
    public boolean infoMatches(FinancialEntityContactInfo comparator) {
        return (StringUtils.equalsIgnoreCase(toString(), comparator.toString()));
    }
    
    @Override
    public String toString() {
        return "FE Contact info, a1 = " + getAddressLine1() +
        ", a2 = " + getAddressLine2() +
        ", a3 = " + getAddressLine3() +
        ", ci = " + getCity() +
        ", st = " + getState() +
        ", pc = " + getPostalCode() +
        ", cc = " + getCountryCode();
    }
}
