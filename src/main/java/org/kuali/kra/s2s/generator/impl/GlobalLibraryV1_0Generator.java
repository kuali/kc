/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.system.globalLibraryV10.AddressDataType;
import gov.grants.apply.system.globalLibraryV10.AddressRequireCountryDataType;
import gov.grants.apply.system.globalLibraryV10.ContactPersonDataType;
import gov.grants.apply.system.globalLibraryV10.HumanNameDataType;
import gov.grants.apply.system.universalCodesV10.CountryCodeType;

import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;

public class GlobalLibraryV1_0Generator {

    /**
     * Create AddressDataType from ProposalPerson
     * 
     * @param person ProposalPerson
     * @return AddressDataType corresponding to the ProposalPerson object.
     */
    public AddressDataType getAddressDataType(ProposalPerson person) {

        AddressDataType addressType = AddressDataType.Factory.newInstance();
        if (person != null) {

            String street1 = person.getAddressLine1();
            addressType.setStreet1(street1);
            String street2 = person.getAddressLine2();
            if (street2 != null && !street2.equals("")) {                
                addressType.setStreet2(street2);
            }
            String city = person.getCity();
            addressType.setCity(city);

            String state = person.getState();
            if (state != null && !state.equals("")) {
                addressType.setState(state);
            }
            String zipcode = person.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                addressType.setZipCode(zipcode);
            }
            String county = person.getCounty();
            if (county != null && !county.equals("")) {
                addressType.setCounty(county);
            }
            String country = person.getCountryCode();
            CountryCodeType.Enum countryCode = null;
            if (country != null && !country.equals("")) {
                countryCode = CountryCodeType.Enum.forString(country);
            }
            addressType.setCountry(countryCode);
        }
        return addressType;
    }

    /**
     * Create AddressDataType from rolodex entry
     * 
     * @param rolodex Rolodex entry
     * @return The AddressDataType corresponding to the rolodex entry.
     */

    public AddressDataType getAddressDataType(Rolodex rolodex) {
        AddressDataType addressType = AddressDataType.Factory.newInstance();
        if (rolodex != null) {

            String street1 = rolodex.getAddressLine1();
            addressType.setStreet1(street1);
            String street2 = rolodex.getAddressLine2();
            if (street2 != null && !street2.equals("")) {
                addressType.setStreet2(street2);
            }
            addressType.setCity(rolodex.getCity());
            String state = rolodex.getState();
            if (state != null && !state.equals("")) {
                addressType.setState(state);
            }
            String zipcode = rolodex.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                addressType.setZipCode(zipcode);
            }
            String county = rolodex.getCounty();
            if (county != null && !county.equals("")) {
                addressType.setCounty(county);
            }
            String country = rolodex.getCountryCode();
            if (country != null && !country.equals("")) {
                CountryCodeType.Enum countryCode = CountryCodeType.Enum.forString(country);
                addressType.setCountry(countryCode);
            }
        }
        return addressType;
    }

    /**
     * Create AddressRequireCountryDataType from rolodex object
     * 
     * @param rolodex Rolodex object
     * @return AddressRequireCountryDataType corresponding to the rolodex object.
     */
    public AddressRequireCountryDataType getAddressRequireCountryDataType(Rolodex rolodex) {

        AddressRequireCountryDataType address = AddressRequireCountryDataType.Factory.newInstance();
        if (rolodex != null) {

            String street1 = rolodex.getAddressLine1();
            address.setStreet1(street1);
            String street2 = rolodex.getAddressLine2();
            if (street2 != null && !street2.equals("")) {
                address.setStreet2(street2);
            }
            String city = rolodex.getCity();
            address.setCity(city);
            String county = rolodex.getCounty();
            if (county != null && !county.equals("")) {
                address.setCounty(county);
            }
            String state = rolodex.getState();
            if (state != null && !state.equals("")) {
                address.setState(state);
            }
            String zipcode = rolodex.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                address.setZipCode(zipcode);
            }
            String country = rolodex.getCountryCode();
            address.setCountry(CountryCodeType.Enum.forString(country));

        }
        return address;
    }

    /**
     * Create AddressRequireCountryDataType from ProposalPerson object
     * 
     * @param person ProposalPerson Object
     * @return AddressRequireCountryDataType corresponding to the ProposalPerson object
     */
    public AddressRequireCountryDataType getAddressRequireCountryDataType(ProposalPerson person) {

        AddressRequireCountryDataType address = AddressRequireCountryDataType.Factory.newInstance();
        if (person != null) {

            String street1 = person.getAddressLine1();
            address.setStreet1(street1);
            String street2 = person.getAddressLine2();
            if (street2 != null && !street2.equals("")) {
                address.setStreet2(street2);
            }
            String city = person.getCity();
            address.setCity(city);
            String county = person.getCounty();
            if (county != null && !county.equals("")) {
                address.setCounty(county);
            }
            String state = person.getState();
            if (state != null && !state.equals("")) {
                address.setState(state);
            }
            String zipcode = person.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                address.setZipCode(zipcode);
            }
            String country = person.getCountryCode();
            address.setCountry(CountryCodeType.Enum.forString(country));

        }
        return address;
    }

    /**
     * Create AddressRequireCountryDataType from DepartmentalPerson object
     * 
     * @param person DepartmentalPerson
     * @return AddressRequireCountryDataType corresponding to the DepartmentalPerson object.
     */
    public AddressRequireCountryDataType getAddressRequireCountryDataType(DepartmentalPerson person) {

        AddressRequireCountryDataType address = AddressRequireCountryDataType.Factory.newInstance();
        if (person != null) {

            String street1 = person.getAddress1();
            address.setStreet1(street1);
            String street2 = person.getAddress2();
            if (street2 != null && !street2.equals("")) {
                address.setStreet2(street2);
            }
            String city = person.getCity();
            address.setCity(city);
            String county = person.getCounty();
            if (county != null && !county.equals("")) {
                address.setCounty(county);
            }
            String state = person.getState();
            if (state != null && !state.equals("")) {
                address.setState(state);
            }
            String zipcode = person.getPostalCode();
            if (zipcode != null && !zipcode.equals("")) {
                address.setZipCode(zipcode);
            }
            String country = person.getCountryCode();
            address.setCountry(CountryCodeType.Enum.forString(country));

        }
        return address;
    }

    /**
     * Create HumanNameDataType from ProposalPerson object
     * 
     * @param person ProposalPerson
     * @return HumanNameDataType corresponding to the ProposalPerson object.
     */
    public HumanNameDataType getHumanNameDataType(ProposalPerson person) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        if (person != null) {
            humanName.setFirstName(person.getFirstName());
            humanName.setLastName(person.getLastName());
            String middleName = person.getMiddleName();
            if (middleName != null && !middleName.equals("")) {
                humanName.setMiddleName(middleName);
            }

        }
        return humanName;
    }

    /**
     * Create HumanNameDataType from DepartmentalPerson object
     * 
     * @param person DepartmentalPerson
     * @return HumanNameDataType corresponding to the DepartmentalPerson object
     */

    public HumanNameDataType getHumanNameDataType(DepartmentalPerson person) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        if (person != null) {
            humanName.setFirstName(person.getFirstName());
            humanName.setLastName(person.getLastName());
            String middleName = person.getMiddleName();
            if (middleName != null && !middleName.equals("")) {
                humanName.setMiddleName(middleName);
            }

        }
        return humanName;
    }

    /**
     * Create HumanNameDataType from explanation string. The string is expected to be comma separated values of firstname, lastname,
     * in order.
     * 
     * @param explanation Comma separated string of first name and last name
     * @return HumanNameDataType created from the string explanation
     */
    public HumanNameDataType getHumanNameDataType(String explanation) {

        HumanNameDataType humanNameDataType = HumanNameDataType.Factory.newInstance();
        String firstName = null;
        String lastName = null;
        String formerName = explanation;
        int commaPos = 0;
        if (formerName != null) {
            commaPos = formerName.indexOf(",");

            if (commaPos > 0) {
                lastName = formerName.substring(0, commaPos);
                firstName = formerName.substring(commaPos + 1);
            }
            else {
                lastName = formerName;
            }
        }
        humanNameDataType.setLastName(lastName);
        humanNameDataType.setFirstName(firstName);
        return humanNameDataType;
    }

    /**
     * Create a HumanNameDataType from Rolodex object
     * 
     * @param rolodex Rolodex object
     * @return HumanNameDataType corresponding to the rolodex object.
     */
    public HumanNameDataType getHumanNameDataType(Rolodex rolodex) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        if (rolodex != null) {
            humanName.setFirstName(rolodex.getFirstName());
            humanName.setLastName(rolodex.getLastName());
            String middleName = rolodex.getMiddleName();
            if (middleName != null && !middleName.equals("")) {
                humanName.setMiddleName(middleName);
            }

        }
        return humanName;
    }

    /**
     * Create HumanNameDataType from KeyPersonInfo object
     * 
     * @param keyPerson KeyPersonInfo
     * @return HumanNameDataType corresponding to the KeyPersonInfo object
     */
    public HumanNameDataType getHumanNameDataType(KeyPersonInfo keyPerson) {

        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        humanName.setFirstName(keyPerson.getFirstName());
        humanName.setLastName(keyPerson.getLastName());
        String middleName = keyPerson.getMiddleName();
        if (middleName != null && !middleName.equals("")) {
            humanName.setMiddleName(middleName);
        }
        return humanName;
    }

    /**
     * Create ContactPersonDataType from ProposalPerson object
     * 
     * @param person Proposalperson
     * @return ContactPersonDataType created from ProposalPerson object
     */
    public ContactPersonDataType getContactPersonDataType(ProposalPerson person) {
        ContactPersonDataType contactPerson = ContactPersonDataType.Factory.newInstance();
        if (person != null) {
            contactPerson.setName(getHumanNameDataType(person));
            contactPerson.setPhone(person.getOfficePhone());
            String email = person.getEmailAddress();
            if (email != null && !email.equals("")) {
                contactPerson.setEmail(email);
            }
            String title = person.getDirectoryTitle();
            if (title != null && !title.equals("")) {
                contactPerson.setTitle(title);
            }
            String fax = person.getFaxNumber();
            if (fax != null && !fax.equals("")) {
                contactPerson.setFax(fax);
            }
            contactPerson.setAddress(getAddressDataType(person));
        }
        return contactPerson;
    }

}
