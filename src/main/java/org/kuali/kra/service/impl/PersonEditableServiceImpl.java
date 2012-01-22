/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.PersonEditableInterface;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.PersonEditableService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class PersonEditableServiceImpl implements PersonEditableService {
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;

    public void populateContactFieldsFromPersonId(PersonEditableInterface protocolPerson) {
        
        DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);

        KcPerson person = this.kcPersonService.getKcPersonByPersonId(protocolPerson.getPersonId());
        protocolPerson.setSocialSecurityNumber(person.getSocialSecurityNumber());
        protocolPerson.setLastName(person.getLastName());
        protocolPerson.setFirstName(person.getFirstName());
        protocolPerson.setMiddleName(person.getMiddleName());
        protocolPerson.setFullName(person.getFullName());
        protocolPerson.setPriorName(person.getPriorName());
        protocolPerson.setUserName(person.getUserName());
        protocolPerson.setEmailAddress(person.getEmailAddress());
        //prop_person.setDateOfBirth(person.getDateOfBirth());
        try{
            java.util.Date dobUtil = dateFormat.parse(person.getDateOfBirth());
            protocolPerson.setDateOfBirth(new java.sql.Date(dobUtil.getYear(), dobUtil.getMonth(), dobUtil.getDate()));
        }catch(Exception e){
            //invalid date
            protocolPerson.setDateOfBirth(null);
        }
        protocolPerson.setAge(person.getAge());
        protocolPerson.setAgeByFiscalYear(person.getAgeByFiscalYear());
        protocolPerson.setGender(person.getGender());
        protocolPerson.setRace(person.getRace());
        protocolPerson.setEducationLevel(person.getEducationLevel());
        protocolPerson.setDegree(person.getDegree());
        protocolPerson.setMajor(person.getMajor());
        protocolPerson.setHandicappedFlag(person.getHandicappedFlag());
        protocolPerson.setHandicapType(person.getHandicapType());
        protocolPerson.setVeteranFlag(person.getVeteranFlag());
        protocolPerson.setVeteranType(person.getVeteranType());
        protocolPerson.setVisaCode(person.getVisaCode());
        protocolPerson.setVisaType(person.getVisaType());
        //prop_person.setVisaRenewalDate(person.getVisaRenewalDate());
        try{
            java.util.Date visaUtil = dateFormat.parse(person.getVisaRenewalDate());
            protocolPerson.setVisaRenewalDate(new java.sql.Date(visaUtil.getYear(), visaUtil.getMonth(), visaUtil.getDate()));
        }catch(Exception e){
            //invalid date
            protocolPerson.setVisaRenewalDate(null);
        }
        protocolPerson.setHasVisa(person.getHasVisa());
        protocolPerson.setOfficeLocation(person.getOfficeLocation());
        protocolPerson.setOfficePhone(person.getOfficePhone());
        protocolPerson.setSecondaryOfficeLocation(person.getSecondaryOfficeLocation());
        protocolPerson.setSecondaryOfficePhone(person.getSecondaryOfficePhone());
        protocolPerson.setSchool(person.getSchool());
        protocolPerson.setYearGraduated(person.getYearGraduated());
        protocolPerson.setDirectoryDepartment(person.getDirectoryDepartment());
        protocolPerson.setSaluation(person.getSaluation());
        protocolPerson.setCountryOfCitizenship(person.getCountryOfCitizenship());
        protocolPerson.setPrimaryTitle(person.getPrimaryTitle());
        protocolPerson.setDirectoryTitle(person.getDirectoryTitle());
        protocolPerson.setHomeUnit(person.getOrganizationIdentifier());
        protocolPerson.setFacultyFlag(person.getFacultyFlag());
        protocolPerson.setGraduateStudentStaffFlag(person.getGraduateStudentStaffFlag());
        protocolPerson.setResearchStaffFlag(person.getResearchStaffFlag());
        protocolPerson.setServiceStaffFlag(person.getServiceStaffFlag());
        protocolPerson.setSupportStaffFlag(person.getSupportStaffFlag());
        protocolPerson.setOtherAcademicGroupFlag(person.getOtherAcademicGroupFlag());
        protocolPerson.setMedicalStaffFlag(person.getMedicalStaffFlag());
        protocolPerson.setVacationAccrualFlag(person.getVacationAccrualFlag());
        protocolPerson.setOnSabbaticalFlag(person.getOnSabbaticalFlag());
        protocolPerson.setIdProvided(person.getIdProvided());
        protocolPerson.setIdVerified(person.getIdVerified());
        protocolPerson.setAddressLine1(person.getAddressLine1());
        protocolPerson.setAddressLine2(person.getAddressLine2());
        protocolPerson.setAddressLine3(person.getAddressLine3());
        protocolPerson.setCity(person.getCity());
        protocolPerson.setCounty(person.getCounty());
        protocolPerson.setState(person.getState());
        protocolPerson.setPostalCode(person.getPostalCode());
        protocolPerson.setCountryCode(person.getCountryCode());
        protocolPerson.setFaxNumber(person.getFaxNumber());
        protocolPerson.setPagerNumber(person.getPagerNumber());
        protocolPerson.setMobilePhoneNumber(person.getMobilePhoneNumber());
        protocolPerson.setEraCommonsUserName(person.getEraCommonsUserName());

    }

    public void populateContactFieldsFromRolodexId(PersonEditableInterface protocolPerson) {
        Map valueMap = new HashMap();
        valueMap.put("rolodexId", protocolPerson.getRolodexId());
        Collection<Rolodex> rolodexes = businessObjectService.findMatching(Rolodex.class, valueMap);

        if (CollectionUtils.isNotEmpty(rolodexes)) {
            for (Rolodex rolodex : rolodexes) {
                protocolPerson.setRolodexId(rolodex.getRolodexId());
                protocolPerson.setAddressLine1(rolodex.getAddressLine1());
                protocolPerson.setAddressLine2(rolodex.getAddressLine2());
                protocolPerson.setAddressLine3(rolodex.getAddressLine3());
                protocolPerson.setCity(rolodex.getCity());
                protocolPerson.setCountryCode(rolodex.getCountryCode());
                protocolPerson.setCounty(rolodex.getCounty());
                protocolPerson.setEmailAddress(rolodex.getEmailAddress());
                protocolPerson.setFaxNumber(rolodex.getFaxNumber());
                protocolPerson.setFirstName(rolodex.getFirstName());
                protocolPerson.setLastName(rolodex.getLastName());
                protocolPerson.setMiddleName(rolodex.getMiddleName());
                protocolPerson.setOfficePhone(rolodex.getPhoneNumber());
                protocolPerson.setPostalCode(rolodex.getPostalCode());
                protocolPerson.setState(rolodex.getState());
                protocolPerson.setPrimaryTitle(rolodex.getTitle());
                protocolPerson.setFullName("");
                if (isNotBlank(rolodex.getFirstName())) {
                    protocolPerson.setFullName(rolodex.getFirstName());
                }
                if (isNotBlank(rolodex.getMiddleName())) {
                    protocolPerson.setFullName(protocolPerson.getFullName() + " " + rolodex.getMiddleName());
                }
                if (isNotBlank(rolodex.getLastName())) {
                    protocolPerson.setFullName(protocolPerson.getFullName() + " " + rolodex.getLastName());
                }
            }
        }
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
