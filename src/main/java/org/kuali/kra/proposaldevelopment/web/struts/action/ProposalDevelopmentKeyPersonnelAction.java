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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.KualiDecimal;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

import static org.apache.commons.beanutils.PropertyUtils.getProperty;
import static org.apache.commons.beanutils.PropertyUtils.INDEXED_DELIM;
import static org.apache.commons.beanutils.PropertyUtils.INDEXED_DELIM2;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_FLAG;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.Constants.NEW_PERSON_LOOKUP_FLAG;
import static org.kuali.kra.infrastructure.Constants.NEW_PROPOSAL_PERSON_PROPERTY_NAME;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;


/**
 * Handles actions from the Key Persons page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.13 $
 */
public class ProposalDevelopmentKeyPersonnelAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentKeyPersonnelAction.class);

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#execute(ActionMapping, ActionForm, HttpServletRequest,
     *      HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        request.setAttribute(NEW_PERSON_LOOKUP_FLAG, "");

        try {
            boolean creditSplitEnabled = getConfigurationService().getApplicationParameterIndicator("SYSTEM", CREDIT_SPLIT_ENABLED_RULE_NAME);

            if (creditSplitEnabled) {
                request.setAttribute(CREDIT_SPLIT_ENABLED_FLAG, new Boolean(creditSplitEnabled));
            }
        }
        catch (Exception e) {
            LOG.warn("Couldn't find parameter '" + CREDIT_SPLIT_ENABLED_RULE_NAME + "'");
            LOG.warn(e.getMessage());
        }
        
        pdform.populatePersonEditableFields();
        return super.execute(mapping, form, request, response);
    }

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(ActionMapping, ActionForm, HttpServletRequest,
     *      HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalPerson person = null;
        
        if (!isBlank(pdform.getNewRolodexId())) {
            person = createProposalPersonFromRolodexId(pdform.getNewRolodexId());
        }
        else if (!isBlank(pdform.getNewPersonId())) {
            person = createProposalPersonFromPersonId(pdform.getNewPersonId());
        }
        
        if (person != null) {
            person.setProposalNumber(pdform.getProposalDevelopmentDocument().getProposalNumber());
            pdform.setNewProposalPerson(person);
            request.setAttribute(NEW_PERSON_LOOKUP_FLAG, new Boolean(true));
        }

        // repopulate form investigators
        pdform.populateInvestigators();
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }
 
    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
   
    /**
     * Clears the <code>{@link ProposalPerson}</code> buffer
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward clearProposalPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        pdform.setNewProposalPerson(new ProposalPerson());
        pdform.setNewRolodexId("");
        pdform.setNewPersonId("");        
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Action for inserting a <code>{@link ProposalPerson}</code> into a <code>{@link ProposalDevelopmentDocument}</code>
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward insertProposalPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        boolean rulePassed = true;

        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new AddKeyPersonEvent(NEW_PROPOSAL_PERSON_PROPERTY_NAME, pdform.getDocument(), pdform.getNewProposalPerson()));

        // if the rule evaluation passed, let's add it
        if (rulePassed) {
            document.addProposalPerson(pdform.getNewProposalPerson());

            for (InvestigatorCreditType creditType : (List<InvestigatorCreditType>) pdform.getInvestigatorCreditTypes()) {
                ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
                creditSplit.setProposalNumber(document.getProposalNumber());
                creditSplit.setProposalPersonNumber(pdform.getNewProposalPerson().getProposalPersonNumber());
                creditSplit.setInvCreditTypeCode(creditType.getInvCreditTypeCode());
                creditSplit.setCredit(new KualiDecimal(0));
                pdform.getNewProposalPerson().getCreditSplits().add(creditSplit);
            }

            pdform.getNewProposalPerson().refreshReferenceObject("role");
            pdform.setNewProposalPerson(new ProposalPerson());
            pdform.setNewRolodexId("");
            pdform.setNewPersonId("");

            // repopulate form investigators
            pdform.populateInvestigators();
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Add a degree to a <code>{@link ProposalPerson}</code>
     *
     * @return ActionForward
     */
    public ActionForward insertDegree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();

        ProposalPerson person = (ProposalPerson) getProperty(pdform, pdform.getAddToPerson());
        ProposalPersonDegree degree = pdform.getNewProposalPersonDegree();
        person.addDegree(degree);
        degree.refreshReferenceObject("degreeType");
        pdform.setNewProposalPersonDegree(new ProposalPersonDegree());

        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Add a unit to a <code>{@link ProposalPerson}</code>
     *
     * @return ActionForward
     */
    public ActionForward insertUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();

        ProposalPerson person = (ProposalPerson) getProperty(pdform, pdform.getAddToPerson());
        ProposalPersonUnit unit = createProposalPersonUnit(pdform.getNewProposalPersonUnit());
        unit.setProposalNumber(person.getProposalNumber());
        unit.setProposalPersonNumber(person.getProposalPersonNumber());

        for (InvestigatorCreditType creditType : (List<InvestigatorCreditType>) pdform.getInvestigatorCreditTypes()) {
            ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
            creditSplit.setProposalNumber(document.getProposalNumber());
            creditSplit.setProposalPersonNumber(person.getProposalPersonNumber());
            creditSplit.setUnitNumber(unit.getUnitNumber());
            creditSplit.setInvCreditTypeCode(creditType.getInvCreditTypeCode());
            creditSplit.setCredit(new KualiDecimal(0));
            unit.getCreditSplits().add(creditSplit);
        }

        person.addUnit(unit);
        unit.refreshReferenceObject("unit");

        pdform.setNewProposalPersonUnit(new Unit());

        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Uses a <code>{@link Unit}</code> obtained from the <code>{@link Unit}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPersonUnit}</code> instance.
     *
     * @param unit
     * @return ProposalPersonUnit
     */
    private ProposalPersonUnit createProposalPersonUnit(Unit unit) {
        ProposalPersonUnit retval = new ProposalPersonUnit();
        Map valueMap = new HashMap();
        valueMap.put("unitNumber", unit.getUnitNumber());
        Collection<Unit> units = getBusinessObjectService().findMatching(Unit.class, valueMap);
        
        for (Unit found : units) {
            retval.setUnitNumber(found.getUnitNumber());
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
    private ProposalPerson createProposalPersonFromRolodexId(String rolodexId) {
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
    private ProposalPerson createProposalPersonFromPersonId(String personId) {
        Map valueMap = new HashMap();
        valueMap.put("personId", personId);

        Collection<Person> persons= getBusinessObjectService().findMatching(Person.class, valueMap);
        if (persons == null || persons.size() < 1) {
            return null;
        }
        
        ProposalPerson prop_person = new ProposalPerson();

        for (Person person : persons) {
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

    /**
     * Remove a <code>{@link ProposalPerson}</code> from the <code>{@link ProposalDevelopmentDocument}</code>
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deletePerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        
        for (Iterator<ProposalPerson> person_it = document.getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if (person.isDelete()) {
                person_it.remove();
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    private KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
    }
}
