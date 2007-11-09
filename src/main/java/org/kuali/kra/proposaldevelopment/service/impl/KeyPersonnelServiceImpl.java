/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.service.impl;

import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_INVESTIGATOR;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYesNoQuestion;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentKeyPersonsRule;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;

/**
 * A Service implementation for persisted modifications of Key Personnel related business objects
 *
 * @see org.kuali.kra.proposaldevelopment.bo.ProposalPerson
 * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentKeyPersonnelAction
 * @see org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm
 * @author $Author: lprzybyl $
 * @version $Revision: 1.2 $
 */
public class KeyPersonnelServiceImpl implements KeyPersonnelService {
    private BusinessObjectService businessObjectService;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelServiceImpl.class);
    
    /**
     * Part of a complete breakfast, it has everything you need to populate Key Personnel into a <code>{@link ProposalDevelopmentDocument}</code>
     * 
     * @param document
     */
    public void populateDocument(ProposalDevelopmentDocument document) {
        document.setInvestigatorCreditTypes(getInvestigatorCreditTypes());
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#populateProposalPerson(ProposalPerson, ProposalDevelopmentDocument)
     */
    public void populateProposalPerson(ProposalPerson person, ProposalDevelopmentDocument document) {
        for (InvestigatorCreditType creditType : (Collection<InvestigatorCreditType>) getInvestigatorCreditTypes()) {
            ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
            creditSplit.setProposalNumber(document.getProposalNumber());
            creditSplit.setProposalPersonNumber(person.getProposalPersonNumber());
            creditSplit.setInvCreditTypeCode(creditType.getInvCreditTypeCode());
            creditSplit.setCredit(new KualiDecimal(0));
            person.getCreditSplits().add(creditSplit);
        }

        for (Ynq question : getYesNoQuestions()) {
            ProposalPersonYesNoQuestion personQuestion = new ProposalPersonYesNoQuestion();
            personQuestion.setProposalNumber(document.getProposalNumber());
            personQuestion.setProposalPersonNumber(person.getProposalPersonNumber());
            personQuestion.setQuestionId(question.getQuestionId());
            personQuestion.refreshReferenceObject("question");
            person.getQuestions().add(personQuestion);
        }
        
        if (isPrincipalInvestigator(person)) {
            assignLeadUnit(person, document.getOwnedByUnitNumber());
        }
        
        person.setIsInvestigator(isInvestigator(person));
        
        person.refreshReferenceObject("role");
        
        document.addProposalPerson(person);
    }

    /**
     * Queries persistent storage for a <code>{@link Collection}</code> of <code>{@link InvestigatorCreditType}</code>
     * instances.
     * 
     * @return Collection<InvestigatorCreditType>
     */
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        Collection<InvestigatorCreditType> types = getBusinessObjectService().findAll(InvestigatorCreditType.class);
        InvestigatorCreditType[] creditTypeArray=new InvestigatorCreditType[4];
        for (InvestigatorCreditType type : types) {
            creditTypeArray[Integer.parseInt(type.getInvCreditTypeCode())]=type;
        }
        return asList(creditTypeArray);
    }

    /**
     * Queries persistent storage for a <code>{@link Collection}</code> of <code>{@link Ynq}</code>
     * instances.
     * 
     * @return Collection<Ynq>
     */
    public Collection<Ynq> getYesNoQuestions() {
        return getBusinessObjectService().findAll(Ynq.class);
    }

    /**
     * Everytime something changes that will effect credit split values, this gets called to generate a graph of the
     * new data.
     *
     * @param document
     * @return Map
     */
    public Map calculateCreditSplitTotals(ProposalDevelopmentDocument document) {
        Map<String, Map<String,KualiDecimal>> retval = new HashMap<String,Map<String,KualiDecimal>>();
        
        // Initialize investigator credit types if there aren't any
        if (document.getInvestigatorCreditTypes() == null || document.getInvestigatorCreditTypes().size() == 0) {
            document.setInvestigatorCreditTypes(getInvestigatorCreditTypes());
        }
        Collection<InvestigatorCreditType> creditTypes = document.getInvestigatorCreditTypes();
        
        for (ProposalPerson investigator : document.getInvestigators()) {
            Map<String,KualiDecimal> creditTypeTotals = retval.get(investigator.getFullName());
            Map<String,KualiDecimal> investigatorCreditTypeTotals = retval.get(PROPOSAL_PERSON_INVESTIGATOR);

            if (creditTypeTotals == null) {
                creditTypeTotals = new HashMap<String,KualiDecimal>();
                retval.put(investigator.getFullName(), creditTypeTotals);
            }
            if (investigatorCreditTypeTotals == null) {
                investigatorCreditTypeTotals = new HashMap<String,KualiDecimal>();
                retval.put(PROPOSAL_PERSON_INVESTIGATOR, investigatorCreditTypeTotals);
            }
            
            // Initialize everything to zero
            for (InvestigatorCreditType creditType : creditTypes) {                
                    KualiDecimal totalCredit = creditTypeTotals.get(creditType.getInvCreditTypeCode());
                    
                    if (totalCredit == null) {
                        totalCredit = new KualiDecimal(0);
                        creditTypeTotals.put(creditType.getInvCreditTypeCode(), totalCredit);
                    }
                    KualiDecimal investigatorTotalCredit = investigatorCreditTypeTotals.get(creditType.getInvCreditTypeCode());
                    
                    if (investigatorTotalCredit == null) {
                        investigatorTotalCredit = new KualiDecimal(0);
                        investigatorCreditTypeTotals.put(creditType.getInvCreditTypeCode(), investigatorTotalCredit);
                    }
                    // set investigator credit total 
                    for (CreditSplit creditSplit : investigator.getCreditSplits()) {
                        if (creditSplit.getInvCreditTypeCode().equals(creditType.getInvCreditTypeCode())) {
                            investigatorCreditTypeTotals.put(creditType.getInvCreditTypeCode(), investigatorTotalCredit.add(creditSplit.getCredit()));
                        }
                    }
            }

            for (ProposalPersonUnit unit : investigator.getUnits()) {
                for (CreditSplit creditSplit : unit.getCreditSplits()) {
                    KualiDecimal totalCredit = creditTypeTotals.get(creditSplit.getInvCreditTypeCode());
                    
                    if (totalCredit == null) {
                        totalCredit = new KualiDecimal(0);
                        creditTypeTotals.put(creditSplit.getInvCreditTypeCode(), totalCredit);
                    }
                    creditTypeTotals.put(creditSplit.getInvCreditTypeCode(),totalCredit.add(creditSplit.getCredit()));
                }
            }
        }
        
        return retval;
    }

    /**
     * Retrieve the injected <code>{@link BusinessObjectService}</code>
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * assign the <code>{@link BusinessObjectService}</code> to use.
     */
    public void setBusinessObjectService(BusinessObjectService boservice) {
        businessObjectService = boservice;
    }

    public boolean isPrincipalInvestigator(ProposalPerson person) {
        return PRINCIPAL_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }

    public boolean isCoInvestigator(ProposalPerson person) {
        return CO_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }
    
    public boolean isInvestigator(ProposalPerson person) {
        return isPrincipalInvestigator(person) || isCoInvestigator(person);
    }
        
    public boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        boolean retval = false;

        for (Iterator<ProposalPerson> person_it = document.getProposalPersons().iterator();
             person_it.hasNext() && !retval;) {
            ProposalPerson person = person_it.next();
            retval |= isPrincipalInvestigator(person);
        }
        
        return retval;
    }

    public void addUnitToPerson(ProposalPerson person, ProposalPersonUnit unit) {
        unit.setProposalNumber(person.getProposalNumber());
        unit.setProposalPersonNumber(person.getProposalPersonNumber());

        person.addUnit(unit);
        unit.refreshReferenceObject("unit");
    }

    /**
     * Assigns the lead unit of the proposal to the given principal investigator
     *
     * @param document
     * @param person Principal 
     */
    private void assignLeadUnit(ProposalPerson person, String unitNumber) {
        ProposalPersonUnit unit = createProposalPersonUnitFromId(unitNumber, person);
        person.setHomeUnit(unitNumber);
        addUnitToPerson(person, unit);
    }
    
    /**
     * Uses a <code>{@link Unit}</code> obtained from the <code>{@link Unit}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPersonUnit}</code> instance.
     *
     * @param unit
     * @return ProposalPersonUnit
     */
    public ProposalPersonUnit createProposalPersonUnit(Unit unit, ProposalPerson person) {
        ProposalPersonUnit retval = new ProposalPersonUnit();
        Map valueMap = new HashMap();
        valueMap.put("unitNumber", unit.getUnitNumber());
        Collection<Unit> units = getBusinessObjectService().findMatching(Unit.class, valueMap);
        
        for (Unit found : units) {
            retval.setUnitNumber(found.getUnitNumber());
        }

        for (InvestigatorCreditType creditType : getInvestigatorCreditTypes()) {
            ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
            creditSplit.setProposalNumber(person.getProposalNumber());
            creditSplit.setProposalPersonNumber(person.getProposalPersonNumber());
            creditSplit.setUnitNumber(unit.getUnitNumber());
            creditSplit.setInvCreditTypeCode(creditType.getInvCreditTypeCode());
            creditSplit.setCredit(new KualiDecimal(0));
            retval.getCreditSplits().add(creditSplit);
        }

        return retval;
    }
    
    /**
     * Uses a <code>{@link Unit}</code> obtained from the <code>{@link Unit}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPersonUnit}</code> instance.
     *
     * @param unitId
     * @return ProposalPersonUnit
     */
    public ProposalPersonUnit createProposalPersonUnitFromId(String unitId, ProposalPerson person) {
        Unit unit = new Unit();
        unit.setUnitNumber(unitId);

        return createProposalPersonUnit(unit, person);
    }
    
    /**
     * Uses a <code>rolodexId</code> obtained from the <code>{@link Person}</code> or <code>{@link Rolodex}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPerson}</code> instance.
     *
     * @param rolodexId
     * @return ProposalPerson
     */
    public ProposalPerson createProposalPersonFromRolodexId(String rolodexId) {
        Map valueMap = new HashMap();
        valueMap.put("rolodexId", rolodexId);
        Collection<Rolodex> rolodexes = getBusinessObjectService().findMatching(Rolodex.class, valueMap);

        if (rolodexes == null || rolodexes.size() < 1) {
            return null;
        }
        
        ProposalPerson prop_person = new ProposalPerson();

        for (Rolodex rolodex : rolodexes) {
            prop_person.setRolodexId(rolodex.getRolodexId());
            prop_person.setAddressLine1(rolodex.getAddressLine1());
            prop_person.setAddressLine2(rolodex.getAddressLine2());
            prop_person.setAddressLine3(rolodex.getAddressLine3());
            prop_person.setCity(rolodex.getCity());
            prop_person.setCountryCode(rolodex.getCountryCode());
            prop_person.setCounty(rolodex.getCounty());
            prop_person.setEmailAddress(rolodex.getEmailAddress());
            prop_person.setFaxNumber(rolodex.getFaxNumber());
            prop_person.setFirstName(rolodex.getFirstName());
            prop_person.setLastName(rolodex.getLastName());
            prop_person.setMiddleName(rolodex.getMiddleName());
            prop_person.setOfficePhone(rolodex.getPhoneNumber());
            prop_person.setPostalCode(rolodex.getPostalCode());
            prop_person.setState(rolodex.getState());
            prop_person.setPrimaryTitle(rolodex.getTitle());
            if (isNotBlank(rolodex.getFirstName()+" "+rolodex.getLastName())) {
                prop_person.setFullName(rolodex.getFirstName()+" "+rolodex.getLastName());
            }
        }
        
        return prop_person;
    }

    /**
     * Uses a <code>personId</code> obtained from the <code>{@link Person}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPerson}</code> instance.
     *
     * @param personId
     * @return ProposalPerson
     */
    public ProposalPerson createProposalPersonFromPersonId(String personId) {
        Map valueMap = new HashMap();
        valueMap.put("personId", personId);

        Collection<Person> persons= getBusinessObjectService().findMatching(Person.class, valueMap);
        if (persons == null || persons.size() < 1) {
            return null;
        }
        
        ProposalPerson prop_person = new ProposalPerson();

        for (Person person : persons) {
            prop_person.setPersonId(personId);
            prop_person.setSocialSecurityNumber(person.getSocialSecurityNumber());
            prop_person.setLastName(person.getLastName());
            prop_person.setFirstName(person.getFirstName());
            prop_person.setMiddleName(person.getMiddleName());
            prop_person.setFullName(person.getFullName());
            prop_person.setPriorName(person.getPriorName());
            prop_person.setUserName(person.getUserName());
            prop_person.setEmailAddress(person.getEmailAddress());
            prop_person.setDateOfBirth(person.getDateOfBirth());
            prop_person.setAge(person.getAge());
            prop_person.setAgeByFiscalYear(person.getAgeByFiscalYear());
            prop_person.setGender(person.getGender());
            prop_person.setRace(person.getRace());
            prop_person.setEducationLevel(person.getEducationLevel());
            prop_person.setDegree(person.getDegree());
            prop_person.setMajor(person.getMajor());
            prop_person.setIsHandicapped(person.isHandicapped());
            prop_person.setHandicapType(person.getHandicapType());
            prop_person.setIsVeteran(person.isVeteran());
            prop_person.setVeteranType(person.getVeteranType());
            prop_person.setVisaCode(person.getVisaCode());
            prop_person.setVisaType(person.getVisaType());
            prop_person.setVisaRenewalDate(person.getVisaRenewalDate());
            prop_person.setHasVisa(person.getHasVisa());
            prop_person.setOfficeLocation(person.getOfficeLocation());
            prop_person.setOfficePhone(person.getOfficePhone());
            prop_person.setSecondaryOfficeLocation(person.getSecondaryOfficeLocation());
            prop_person.setSecondaryOfficePhone(person.getSecondaryOfficePhone());
            prop_person.setSchool(person.getSchool());
            prop_person.setYearGraduated(person.getYearGraduated());
            prop_person.setDirectoryDepartment(person.getDirectoryDepartment());
            prop_person.setSaluation(person.getSaluation());
            prop_person.setCountryOfCitizenship(person.getCountryOfCitizenship());
            prop_person.setPrimaryTitle(person.getPrimaryTitle());
            prop_person.setDirectoryTitle(person.getDirectoryTitle());
            prop_person.setHomeUnit(person.getHomeUnit());
            prop_person.setIsFaculty(person.isFaculty());
            prop_person.setIsGraduateStudentStaff(person.isGraduateStudentStaff());
            prop_person.setIsResearchStaff(person.isResearchStaff());
            prop_person.setIsServiceStaff(person.isServiceStaff());
            prop_person.setIsSupportStaff(person.isSupportStaff());
            prop_person.setIsOtherAcademicGroup(person.isOtherAcademicGroup());
            prop_person.setIsMedicalStaff(person.isMedicalStaff());
            prop_person.setIsVacationAccrual(person.isVacationAccrual());
            prop_person.setIsOnSabbatical(person.isOnSabbatical());
            prop_person.setIdProvided(person.getIdProvided());
            prop_person.setIdVerified(person.getIdVerified());
            prop_person.setAddressLine1(person.getAddressLine1());
            prop_person.setAddressLine2(person.getAddressLine2());
            prop_person.setAddressLine3(person.getAddressLine3());
            prop_person.setCity(person.getCity());
            prop_person.setCounty(person.getCounty());
            prop_person.setState(person.getState());
            prop_person.setPostalCode(person.getPostalCode());
            prop_person.setCountryCode(person.getCountryCode());
            prop_person.setFaxNumber(person.getFaxNumber());
            prop_person.setPagerNumber(person.getPagerNumber());
            prop_person.setMobilePhoneNumber(person.getMobilePhoneNumber());
            prop_person.setEraCommonsUserName(person.getEraCommonsUserName());
        }
        
        return prop_person;
    }
}
