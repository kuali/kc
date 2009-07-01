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

import gov.grants.apply.system.globalLibraryV20.AddressDataType;
import gov.grants.apply.system.globalLibraryV20.ContactPersonDataType;
import gov.grants.apply.system.globalLibraryV20.HumanNameDataType;
import gov.grants.apply.system.universalCodesV20.CountryCodeDataType;
import gov.grants.apply.system.universalCodesV20.StateCodeDataType;

import org.kuali.kra.bo.Country;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.State;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.service.S2SUtilService;

public class GlobalLibraryV2_0Generator {

    private S2SUtilService s2sUtilService;

    public GlobalLibraryV2_0Generator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }

    /**
     * Create a CountryCodeDataType.Enum as defined in UniversalCodes 2.0 from the given country code.
     * 
     * @param countryCode The country code
     * @return The CountryCodeDataType type corresponding to the given country code
     */
    public CountryCodeDataType.Enum getCountryCodeDataType(String countryCode) {
        CountryCodeDataType.Enum countryCodeDataType = null;
        Country country = s2sUtilService.getCountryFromCode(countryCode);
        if (country != null) {
            StringBuilder countryDetail = new StringBuilder();
            countryDetail.append(country.getCountryCode());
            countryDetail.append(": ");
            countryDetail.append(country.getCountryName().toUpperCase());
            countryCodeDataType = CountryCodeDataType.Enum.forString(countryDetail.toString());
        }
        return countryCodeDataType;
    }

    /**
     * Create a StateCodeDataType.Enum as defined in UniversalCodes 2.0 from the given name of the state.
     * 
     * @param StateCode The state name
     * @return The StateCodeDataType type corresponding to the given State code.
     */
    public StateCodeDataType.Enum getStateCodeDataType(String stateName) {
        StateCodeDataType.Enum stateCodeDataType = null;
        State state = s2sUtilService.getStateFromName(stateName);
        if (state != null) {
            StringBuilder stateDetail = new StringBuilder();
            stateDetail.append(state.getStateCode());
            stateDetail.append(": ");
            stateDetail.append(state.getDescription());
            stateCodeDataType = StateCodeDataType.Enum.forString(stateDetail.toString());
        }
        return stateCodeDataType;
    }

    /**
     * Create AddressDataType from rolodex entry
     * 
     * @param rolodex Rolodex entry
     * @return The AddressDataType corresponding to the rolodex entry.
     */
    public AddressDataType getAddressDataType(Rolodex rolodex) {

        AddressDataType addressDataType = AddressDataType.Factory.newInstance();
        if (rolodex != null) {

            String street1 = rolodex.getAddressLine1();
            addressDataType.setStreet1(street1);
            String street2 = rolodex.getAddressLine2();
            if (street2 != null && !street2.equals("")) {
                addressDataType.setStreet2(street2);
            }
            String city = rolodex.getCity();
            addressDataType.setCity(city);
            String county = rolodex.getCounty();
            if (county != null && !county.equals("")) {
                addressDataType.setCounty(county);
            }
            String state = rolodex.getState();
            if (state != null && !state.equals("")) {
                addressDataType.setState(getStateCodeDataType(state));
            }
            String postalCode = rolodex.getPostalCode();
            if (postalCode != null && !postalCode.equals("")) {
                addressDataType.setZipPostalCode(postalCode);
            }
            String country = rolodex.getCountryCode();
            addressDataType.setCountry(getCountryCodeDataType(country));
        }
        return addressDataType;
    }

    /**
     * Create AddressDataType from Person
     * 
     * @param person Person
     * @return AddressDataType corresponding to the Person object.
     */
    public AddressDataType getAddressDataType(Person person) {
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
            String county = person.getCounty();
            if (county != null && !county.equals("")) {
                addressType.setCounty(county);
            }
            String state = person.getState();
            if (state != null && !state.equals("")) {
                addressType.setState(getStateCodeDataType(state));
            }
            String postalCode = person.getPostalCode();
            if (postalCode != null && !postalCode.equals("")) {
                addressType.setZipPostalCode(postalCode);
            }
            String country = person.getCountryCode();
            addressType.setCountry(getCountryCodeDataType(country));
        }
        return addressType;
    }

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
                addressType.setState(getStateCodeDataType(state));
            }
            String postalCode = person.getPostalCode();
            if (postalCode != null && !postalCode.equals("")) {
                addressType.setZipPostalCode(postalCode);
            }
            String country = person.getCountryCode();
            addressType.setCountry(getCountryCodeDataType(country));
        }
        return addressType;
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
        if (formerName != null) {
            int commaPos = formerName.indexOf(",");
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
        if (keyPerson != null) {
            humanName.setFirstName(keyPerson.getFirstName());
            humanName.setLastName(keyPerson.getLastName());
            String middleName = keyPerson.getMiddleName();
            if (middleName != null && !middleName.equals("")) {
                humanName.setMiddleName(middleName);
            }
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
            contactPerson.setName((getHumanNameDataType(person)));
            String phone = person.getOfficePhone();
            if (phone != null && !phone.equals("")) {
                contactPerson.setPhone(phone);
            }
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

    /**
     * Create ContactPersonDataType from Rolodex object
     * 
     * @param person Rolodex
     * @return ContactPersonDataType created from Rolodex object
     */
    public ContactPersonDataType getContactPersonDataType(Rolodex rolodex) {

        ContactPersonDataType contactPerson = ContactPersonDataType.Factory.newInstance();
        if (rolodex != null) {

            contactPerson.setName(getHumanNameDataType(rolodex));
            String phone = rolodex.getPhoneNumber();
            if (phone != null && !phone.equals("")) {
                contactPerson.setPhone(phone);
            }
            String fax = rolodex.getFaxNumber();
            if (fax != null && !fax.equals("")) {
                contactPerson.setFax(fax);
            }
            String email = rolodex.getEmailAddress();
            if (email != null && !email.equals("")) {
                contactPerson.setEmail(rolodex.getEmailAddress());
            }
            String title = rolodex.getTitle();
            if (title != null && !title.equals("")) {
                contactPerson.setTitle(title);
            }
            contactPerson.setAddress(getAddressDataType(rolodex));
        }
        return contactPerson;
    }


}
