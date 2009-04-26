/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_INVESTIGATOR;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_ROLE_PARAMETER_PREFIX;
import static org.kuali.kra.infrastructure.Constants.SPONSOR_HIERARCHY_NAME;
import static org.kuali.kra.infrastructure.Constants.SPONSOR_LEVEL_HIERARCHY;
import static org.kuali.kra.logging.FormattedLogger.debug;
import static org.kuali.kra.logging.FormattedLogger.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.YnqService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * A Service implementation for persisted modifications of Key Personnel related business objects
 *
 * @see org.kuali.kra.proposaldevelopment.bo.ProposalPerson
 * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentKeyPersonnelAction
 * @see org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm
 * @author $Author: gmcgrego $
 * @version $Revision: 1.34 $
 */
public class KeyPersonnelServiceImpl implements KeyPersonnelService {
    private static final String READ_ONLY_ROLES_PARAM_NAME = "proposaldevelopment.personrole.readonly.roles";
    private BusinessObjectService businessObjectService;
    private NarrativeService narrativeService;
    private YnqService ynqService;
    private KualiConfigurationService configurationService;    

    /**
     * Part of a complete breakfast, it has everything you need to populate Key Personnel into a <code>{@link ProposalDevelopmentDocument}</code>
     * 
     * @param document
     */
    public void populateDocument(ProposalDevelopmentDocument document) {
        if(document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("R")){
            Collection<InvestigatorCreditType> invcrdttype=getAllInvestigatorCreditTypes();
            List<InvestigatorCreditType> inv=new ArrayList<InvestigatorCreditType>();
            for (ProposalPerson person : document.getInvestigators()) {
                for(ProposalPersonCreditSplit proposalpersoncrdt:person.getCreditSplits()){
                    for(InvestigatorCreditType invcredtype:invcrdttype){
                        if(invcredtype.getInvCreditTypeCode().equals(proposalpersoncrdt.getInvCreditTypeCode())){
                            inv.add(invcredtype);
                        }
                    }
                }
            }
            document.setInvestigatorCreditTypes(inv);          
        }else
        {
            document.setInvestigatorCreditTypes(getInvestigatorCreditTypes());
        }
        if (document.getInvestigators().isEmpty() && !document.getProposalPersons().isEmpty()) {
            info("Need to repopulate investigator list");
            populateInvestigators(document);
            if(!(document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("R"))){
                populateActiveCredittypesPerson(document);
            }
        }
        /* check for new certification questions */
        for (ProposalPerson person : document.getProposalPersons()) {
            getYnqService().getPersonYNQ(person, document);
        }
    }
    /**
     * It populates the Active credit type in the proposalpersoncreditsplit and unitcreditsplit
     *
     * @param document
     * @return true or false
     */
    public void populateActiveCredittypesPerson(ProposalDevelopmentDocument document){
        Collection<InvestigatorCreditType> invcrdttype=getInvestigatorCreditTypes();
        for (ProposalPerson person : document.getInvestigators()) {
            for(InvestigatorCreditType invcredtype:invcrdttype){
                boolean creditTypeFound = false;
                for(ProposalPersonCreditSplit proposalpersoncrdt:person.getCreditSplits()){
                    if((invcredtype.getInvCreditTypeCode().equals(proposalpersoncrdt.getInvCreditTypeCode()))){
                        creditTypeFound = true;
                        break;
                    }
                }
                if (!creditTypeFound ) {
                    ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
                    creditSplit.setProposalNumber(person.getProposalNumber());
                    creditSplit.setProposalPersonNumber(person.getProposalPersonNumber());
                    creditSplit.setInvCreditTypeCode(invcredtype.getInvCreditTypeCode());
                    creditSplit.setCredit(new KualiDecimal(0));
                    person.getCreditSplits().add(creditSplit);
                }
            }
            for(ProposalPersonUnit unitsplit:person.getUnits()){
                for(InvestigatorCreditType invcrdtype:invcrdttype){
                    boolean creditTypeFound = false;
                    for(ProposalUnitCreditSplit unitcreditsplit:unitsplit.getCreditSplits()){
                        if((invcrdtype.getInvCreditTypeCode().equals(unitcreditsplit.getInvCreditTypeCode()))){
                            creditTypeFound = true;
                            break;
                        }
                    }
                    if (!creditTypeFound ) {
                        ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
                        creditSplit.setProposalNumber(person.getProposalNumber());
                        creditSplit.setProposalPersonNumber(person.getProposalPersonNumber());
                        creditSplit.setInvCreditTypeCode(invcrdtype.getInvCreditTypeCode());
                        creditSplit.setCredit(new KualiDecimal(0));
                        unitsplit.getCreditSplits().add(creditSplit);
                    }
                }
            }

        }
    }

    /**
     * Populate investigators
     * 
     * @param document The <code>{@link ProposalDevelopmentDocument}</code> to populate
     * investigators on
     */
    public void populateInvestigators(ProposalDevelopmentDocument document) {
        // Populate Investigators from a proposal document's persons
        debug("Populating Investigators");
        debug("Clearing investigator list");
        document.getInvestigators().clear();

        for (ProposalPerson person : document.getProposalPersons()) {
            debug(person.getFullName() + " is " + isInvestigator(person));
            person.setInvestigatorFlag(isInvestigator(person));

            if (person.isInvestigator()) {
                info("Adding investigator " + person.getFullName());
                document.getInvestigators().add(person);
            }
        }
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#populateProposalPerson(ProposalPerson, ProposalDevelopmentDocument)
     */
    public void populateProposalPerson(ProposalPerson person, ProposalDevelopmentDocument document) {
        /* populate certification questions for new person */
        person = getYnqService().getPersonYNQ(person, document);

        person.setInvestigatorFlag(isInvestigator(person));

        if (person.isInvestigator()) {
            if (!document.getInvestigators().contains(person)) {
                document.getInvestigators().add(person);
            }
            populateCreditTypes(person);

        }

        person.refreshReferenceObject("role");

        if (person.getRole() != null) {
            person.getRole().setReadOnly(isRoleReadOnly(person.getRole()));
        }

        person.setRoleChanged(false);

    }

    /**
     * Initializes credit splits for new investigators
     * 
     * @param person
     */
    private void populateCreditTypes(ProposalPerson person) {
        if (!person.getCreditSplits().isEmpty()) {
            return;
        }

        for (InvestigatorCreditType creditType : (Collection<InvestigatorCreditType>) getInvestigatorCreditTypes()) {
            ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
            creditSplit.setProposalNumber(person.getProposalNumber());
            creditSplit.setProposalPersonNumber(person.getProposalPersonNumber());
            creditSplit.setInvCreditTypeCode(creditType.getInvCreditTypeCode());
            creditSplit.setCredit(new KualiDecimal(0));
            person.getCreditSplits().add(creditSplit);
        }

    }

    /**
     * Queries persistent storage for a <code>{@link Collection}</code> of <code>{@link InvestigatorCreditType}</code>
     * instances.
     * 
     * @return Collection<InvestigatorCreditType> of active credit types
     */
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        Map<String,String> valueMap = new HashMap<String, String>();
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        valueMap.put("active", "true");
        return bos.findMatching(InvestigatorCreditType.class, valueMap);
    }

    /**
     * Queries persistent storage for a <code>{@link Collection}</code> of <code>{@link InvestigatorCreditType}</code>
     * instances.
     * 
     * @return Collection<InvestigatorCreditType> of all credit types
     */
    public Collection<InvestigatorCreditType> getAllInvestigatorCreditTypes() {
        return getBusinessObjectService().findAll(InvestigatorCreditType.class);
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
            Map<String,KualiDecimal> creditTypeTotals = retval.get(investigator.getUserName());
            Map<String,KualiDecimal> investigatorCreditTypeTotals = retval.get(PROPOSAL_PERSON_INVESTIGATOR);

            if (creditTypeTotals == null) {
                creditTypeTotals = new HashMap<String,KualiDecimal>();
                retval.put(investigator.getUserName(), creditTypeTotals);
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
     * 
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * assign the <code>{@link BusinessObjectService}</code> to use.
     * 
     * @param boservice <code>{@link BusinessObjectService}</code> instance to assign
     */
    public void setBusinessObjectService(BusinessObjectService boservice) {
        businessObjectService = boservice;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#isPrincipalInvestigator(org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public boolean isPrincipalInvestigator(ProposalPerson person) {
        return PRINCIPAL_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#isCoInvestigator(org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public boolean isCoInvestigator(ProposalPerson person) {
        return CO_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }


    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#isCoInvestigator(org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public boolean isKeyPerson(ProposalPerson person) {
        return KEY_PERSON_ROLE.equals(person.getProposalPersonRoleId());
    }
    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#isInvestigator(org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public boolean isInvestigator(ProposalPerson person) {
        if(isNotBlank(person.getOptInUnitStatus()) && (person.getOptInUnitStatus().equals("Y")))
        {
            return isPrincipalInvestigator(person) || isCoInvestigator(person) || isKeyPerson(person);
        }else
        {
            return isPrincipalInvestigator(person) || isCoInvestigator(person);
        }
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#hasPrincipalInvestigator(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        boolean retval = false;

        for (Iterator<ProposalPerson> person_it = document.getProposalPersons().iterator();
        person_it.hasNext() && !retval;) {
            ProposalPerson person = person_it.next();
            retval |= isPrincipalInvestigator(person);
        }

        return retval;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#addUnitToPerson(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit)
     */
    public void addUnitToPerson(ProposalPerson person, ProposalPersonUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Cannot add null units to a ProposalPerson instance");
        }

        if (!person.containsUnit(unit.getUnitNumber())) {
            unit.setProposalNumber(person.getProposalNumber());
            unit.setProposalPersonNumber(person.getProposalPersonNumber());

            person.addUnit(unit);
            unit.refreshReferenceObject("unit");
        }
    }

    /**
     * Assigns the lead unit of the proposal to the given principal investigator
     *
     * @param document
     * @param person Principal 
     */
    public void assignLeadUnit(ProposalPerson person, String unitNumber) {
        if (person.containsUnit(unitNumber)) {
            person.getUnit(unitNumber).setLeadUnit(true);
            return;
        }

        ProposalPersonUnit unit = createProposalPersonUnit(unitNumber, person);
        unit.setLeadUnit(true);
        addUnitToPerson(person, unit);
    }

    /**
     * Uses a <code>{@link Unit}</code> obtained from the <code>{@link Unit}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPersonUnit}</code> instance.
     *
     * @param unitId
     * @return ProposalPersonUnit
     */
    public ProposalPersonUnit createProposalPersonUnit(String unitId, ProposalPerson person) {
        ProposalPersonUnit retval = new ProposalPersonUnit();
        Map valueMap = new HashMap();
        valueMap.put("unitNumber", unitId);
        Collection<Unit> units = getBusinessObjectService().findMatching(Unit.class, valueMap);

        for (Unit found : units) {
            retval.setUnitNumber(found.getUnitNumber());
            retval.setUnit(found);
        }

        for (InvestigatorCreditType creditType : getInvestigatorCreditTypes()) {
            ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
            creditSplit.setProposalNumber(person.getProposalNumber());
            creditSplit.setProposalPersonNumber(person.getProposalPersonNumber());
            creditSplit.setUnitNumber(unitId);
            creditSplit.setInvCreditTypeCode(creditType.getInvCreditTypeCode());
            creditSplit.setCredit(new KualiDecimal(0));
            retval.getCreditSplits().add(creditSplit);
        }

        return retval;        
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
            prop_person.setFullName("");
            if (isNotBlank(rolodex.getFirstName())) {
                prop_person.setFullName(rolodex.getFirstName());
            }
            if (isNotBlank(rolodex.getMiddleName())) {
                prop_person.setFullName(prop_person.getFullName() + " " + rolodex.getMiddleName());                     
            }
            if (isNotBlank(rolodex.getLastName())) {
                prop_person.setFullName(prop_person.getFullName() + " " + rolodex.getLastName());                     
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
            prop_person.setHandicappedFlag(person.getHandicappedFlag());
            prop_person.setHandicapType(person.getHandicapType());
            prop_person.setVeteranFlag(person.getVeteranFlag());
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
            prop_person.setFacultyFlag(person.getFacultyFlag());
            prop_person.setGraduateStudentStaffFlag(person.getGraduateStudentStaffFlag());
            prop_person.setResearchStaffFlag(person.getResearchStaffFlag());
            prop_person.setServiceStaffFlag(person.getServiceStaffFlag());
            prop_person.setSupportStaffFlag(person.getSupportStaffFlag());
            prop_person.setOtherAcademicGroupFlag(person.getOtherAcademicGroupFlag());
            prop_person.setMedicalStaffFlag(person.getMedicalStaffFlag());
            prop_person.setVacationAccrualFlag(person.getVacationAccrualFlag());
            prop_person.setOnSabbaticalFlag(person.getOnSabbaticalFlag());
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

    /**
     * Accessor method for dependency injection
     * 
     * @param ynqService
     */
    public void setYnqService(YnqService ynqService) {
        this.ynqService = ynqService;
    }

    /**
     * Accessor method for dependency injection
     * 
     * @return NarrativeService
     */
    public NarrativeService getNarrativeService() {
        return narrativeService;
    }

    /**
     * Accessor method for dependency injection
     * 
     * @param narrativeService
     */
    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    /**
     * Accessor method for dependency injection
     * 
     * @return YnqService
     */
    public YnqService getYnqService() {
        return ynqService;
    }

    /**
     * Uses the {@link KualiConfigurationService} to determine if the application-level configuration parameter is enabled
     * 
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#isCreditSplitEnabled()
     */
    public boolean isCreditSplitEnabled() {
        return getConfigurationService().getIndicatorParameter(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, CREDIT_SPLIT_ENABLED_RULE_NAME);
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#getConfigurationService()
     */
    public KualiConfigurationService getConfigurationService() {
        return this.configurationService;
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#setConfigurationService(org.kuali.rice.kns.service.KualiConfigurationService)
     */
    public void setConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.configurationService = kualiConfigurationService;        
    }    

    /**
     * Compares the given <code>roleId</code> against the <code>proposaldevelopment.personrole.readonly.roles</code> to see if it is 
     * read only or not.
     * 
     * @param roleId to check
     * @return true if the <code>roleId</code> is a value in the <code>proposaldevelopment.personrole.readonly.roles</code> system parameter, and false
     *         if the <coderoleId</code> is null
     */
    public boolean isRoleReadOnly(String roleId) {
        if (roleId == null) {
            return false;
        }
        // return true;
        return getConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT,READ_ONLY_ROLES_PARAM_NAME).getParameterValue().toLowerCase().contains(roleId.toLowerCase());
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#isRoleReadOnly(org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole)
     */
    public boolean isRoleReadOnly(ProposalPersonRole role) {
        if (role == null) {
            return false;
        }
        return isRoleReadOnly(role.getProposalPersonRoleId());
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.KeyPersonnelService#getPrincipalInvestigatorRoleDescription(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public String getPrincipalInvestigatorRoleDescription(ProposalDevelopmentDocument document) {
        String parameterName = "proposaldevelopment.personrole.pi";
        if (document.getSponsor() != null && document.getSponsor().getAcronym() != null && document.getSponsor().getAcronym().toLowerCase().equals("nih")) {
            parameterName = "proposaldevelopment.personrole.nonnih.pi";
        }
        return getConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT,parameterName).getParameterValue();
    }

    public boolean isSponsorNIH(ProposalDevelopmentDocument document) {

        boolean nih=false;
        Map valueMap = new HashMap();
        HashMap nihDescription=new HashMap<String, String>();
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        final Collection<ProposalPersonRole> roles = bos.findAll(ProposalPersonRole.class);
        document.setNih(false);
        valueMap.put("sponsorCode", document.getSponsorCode());
        valueMap.put("hierarchyName",getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                PARAMETER_COMPONENT_DOCUMENT, 
                SPONSOR_HIERARCHY_NAME ));
        Collection<SponsorHierarchy> sponsor_hierarchy=  bos.findMatching(SponsorHierarchy.class, valueMap);
        if (CollectionUtils.isNotEmpty(sponsor_hierarchy)) {
            for (Object variable : sponsor_hierarchy) {
                SponsorHierarchy sponhierarchy=(SponsorHierarchy) variable;
                if(StringUtils.isNotEmpty(sponhierarchy.getLevel1()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel2()) && (sponhierarchy.getLevel2().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel3()) && (sponhierarchy.getLevel3().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel4()) && (sponhierarchy.getLevel4().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel5()) && (sponhierarchy.getLevel5().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel6()) && (sponhierarchy.getLevel6().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel7()) && (sponhierarchy.getLevel7().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel8()) && (sponhierarchy.getLevel8().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel9()) && (sponhierarchy.getLevel9().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel9()) && (sponhierarchy.getLevel10().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                    nih=true;
                }
            }
        }
        if(document.isNih()){
            for (ProposalPersonRole role : roles) {
                nihDescription.put(role.getProposalPersonRoleId(), findNIHRoleDescription(role));

            }
            document.setNihDescription(nihDescription);
        }
        return nih;
        // TODO Auto-generated method stub

    }
    protected String findNIHRoleDescription(ProposalPersonRole role) {
        return getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                PARAMETER_COMPONENT_DOCUMENT, 
                PROPOSAL_PERSON_ROLE_PARAMETER_PREFIX 
                + "nonnih."
                + role.getProposalPersonRoleId().toLowerCase());    
    }
}
