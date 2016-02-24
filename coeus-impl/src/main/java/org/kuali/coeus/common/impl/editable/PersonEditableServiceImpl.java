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
package org.kuali.coeus.common.impl.editable;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.editable.PersonEditable;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component("personEditableService")
public class PersonEditableServiceImpl implements PersonEditableService {
    
	@Autowired
	@Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
	
	@Autowired
	@Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

	public void populateContactFields(PersonEditable person) {
		if (StringUtils.isNotBlank(person.getPersonId())) {
			populateContactFieldsFromPersonId(person);
		} else if (person.getRolodexId() != null) {
			populateContactFieldsFromRolodexId(person);
		}
	}
	
    public void populateContactFieldsFromPersonId(PersonEditable protocolPerson) {
        
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
        protocolPerson.setCitizenshipTypeCode(person.getCitizenshipTypeCode());
    }

    public void populateContactFieldsFromRolodexId(PersonEditable protocolPerson) {
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
