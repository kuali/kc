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


import static java.util.Collections.sort;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.RiceConstants.METHOD_TO_CALL_ATTRIBUTE;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_FLAG;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.Constants.NEW_PERSON_LOOKUP_FLAG;
import static org.kuali.kra.infrastructure.Constants.NEW_PROPOSAL_PERSON_PROPERTY_NAME;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ChangeKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * Handles actions from the Key Persons page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.39 $
 */
public class ProposalDevelopmentKeyPersonnelAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentKeyPersonnelAction.class);

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#execute(ActionMapping, ActionForm, HttpServletRequest,
     *      HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward retval = super.execute(mapping, form, request, response);
        prepare(form, request);
        return retval;
    }
    
    /**
     * Common helper method for preparing to <code>{@link #execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)}</code>
     * @param form ActionForm
     * @param request HttpServletRequest
     */
    public void prepare(ActionForm form, HttpServletRequest request) {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        request.setAttribute(NEW_PERSON_LOOKUP_FLAG, "");

        pdform.populatePersonEditableFields();
        populateInvestigators(pdform);    
    
        try {
            boolean creditSplitEnabled = getConfigurationService().getIndicatorParameter(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, CREDIT_SPLIT_ENABLED_RULE_NAME)
                && pdform.getProposalDevelopmentDocument().getInvestigators().size() > 0;
            request.setAttribute(CREDIT_SPLIT_ENABLED_FLAG, new Boolean(creditSplitEnabled));
        }
        catch (Exception e) {
            LOG.warn("Couldn't find parameter '" + CREDIT_SPLIT_ENABLED_RULE_NAME + "'");
            LOG.warn(e.getMessage());
        }        
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
            person = getKeyPersonnelService().createProposalPersonFromRolodexId(pdform.getNewRolodexId());
        }
        else if (!isBlank(pdform.getNewPersonId())) {
            person = getKeyPersonnelService().createProposalPersonFromPersonId(pdform.getNewPersonId());
        }
        
        if (person != null) {
            person.setProposalNumber(pdform.getProposalDevelopmentDocument().getProposalNumber());
            person.setProposalPersonRoleId(pdform.getNewProposalPerson().getProposalPersonRoleId());
            pdform.setNewProposalPerson(person);
            request.setAttribute(NEW_PERSON_LOOKUP_FLAG, new Boolean(true));
        }

        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Locate from Spring the <code>{@link KualiRuleService}</code> singleton
     * 
     * @return KualiRuleService
     */
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

        // Person needs to be setup before validation
        getKeyPersonnelService().populateProposalPerson(pdform.getNewProposalPerson(), document);

        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new AddKeyPersonEvent(NEW_PROPOSAL_PERSON_PROPERTY_NAME, pdform.getDocument(), pdform.getNewProposalPerson()));
                
        // if the rule evaluation passed, let's add it
        if (rulePassed) {
            document.addProposalPerson(pdform.getNewProposalPerson());
            sort(document.getProposalPersons(), new ProposalPersonComparator());
            sort(document.getInvestigators(), new ProposalPersonComparator());
            pdform.setNewProposalPerson(new ProposalPerson());
            pdform.setNewRolodexId("");
            pdform.setNewPersonId("");

            // repopulate form investigators
            populateInvestigators(pdform);
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

        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson person = document.getProposalPerson(selectedPersonIndex);
        ProposalPersonDegree degree = pdform.getNewProposalPersonDegree().get(selectedPersonIndex);
        degree.setDegreeSequenceNumber(pdform.getProposalDevelopmentDocument().getProposalNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new ChangeKeyPersonEvent(NEW_PROPOSAL_PERSON_PROPERTY_NAME, document, person, degree));

        if (rulePassed) {
            person.addDegree(degree);
            degree.refreshReferenceObject("degreeType");
            pdform.getNewProposalPersonDegree().remove(selectedPersonIndex);
            pdform.getNewProposalPersonDegree().add(selectedPersonIndex,new ProposalPersonDegree());
        }

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

        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson person = document.getProposalPerson(selectedPersonIndex);
        ProposalPersonUnit unit = getKeyPersonnelService().createProposalPersonUnit(pdform.getNewProposalPersonUnit().get(selectedPersonIndex).getUnitNumber(), person);
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new ChangeKeyPersonEvent(NEW_PROPOSAL_PERSON_PROPERTY_NAME, document, person, unit));

        if (rulePassed) {
            getKeyPersonnelService().addUnitToPerson(person, unit);

            pdform.getNewProposalPersonUnit().remove(selectedPersonIndex);
            pdform.getNewProposalPersonUnit().add(selectedPersonIndex,new Unit());
        }

        return mapping.findForward(MAPPING_BASIC);
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
                document.getInvestigators().remove(person);
                document.removePersonnelAttachmentForDeletedPerson(person);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Remove a <code>{@link ProposalPersonUnit}</code> from a <code>{@link ProposalPerson}</code>
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        selectedPerson.getUnits().remove(getSelectedLine(request));
        

        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Remove a <code>{@link ProposalPersonDegree}</code> from a <code>{@link ProposalPerson}</code>
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteDegree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        selectedPerson.getProposalPersonDegrees().remove(getSelectedLine(request));

        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * 
     * This method is to recalculate credit split
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward recalculateCreditSplit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        

        return mapping.findForward(MAPPING_BASIC);
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        boolean rulePassed = true;

        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new SaveKeyPersonEvent(NEW_PROPOSAL_PERSON_PROPERTY_NAME, pdform.getDocument()));

        // if the rule evaluation passed, then save. It is possible that invoking save without checking rules first will
        // let the document save anyhow, so let's check first.
        if (rulePassed) {
            
            
            
            /////////ATTN:///////////////
            /*
             * Adding dummy records for narrative rights(modify right) to all prop users
             * This has to be removed after implementing proposal user roles
             * by Geo
             */
            //BEGIN BLOCK to be REMOVED
            ProposalDevelopmentDocument doc = pdform.getProposalDevelopmentDocument();
            doc.populateDummyPropUserRolesForNarrative();
            //END BLOCK to be REMOVED
            
            
            
            
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Parses the method to call attribute to pick off the line number which should have an action performed on it.
     * 
     * @param request
     * @param document the person is selected on
     * @return ProposalPerson
     */
    protected ProposalPerson getSelectedPerson(HttpServletRequest request, ProposalDevelopmentDocument document) {
        ProposalPerson retval = null;
        int selectedLine = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            int lineNumber = Integer.parseInt(substringBetween(parameterName, "proposalPerson[", "]."));
            retval = document.getProposalPerson(lineNumber);
        }

        return retval;
    }

    protected int getSelectedPersonIndex(HttpServletRequest request, ProposalDevelopmentDocument document) {
        ProposalPerson retval = null;
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, "personIndex","."));
        }

        return selectedPersonIndex;
    }

    /**
     * Populate investigators
     * 
     * @param form The <code>{@link ProposalDevelopmentForm}</code> containing the <code>{@link ProposalDevelopmentDocument}</code> to populate
     * investigators on
     */
    public void populateInvestigators(ProposalDevelopmentForm form) {
        // Populate Investigators from a proposal document's persons
        LOG.debug("Populating Investigators");
        LOG.debug("Clearing investigator list");
        form.getProposalDevelopmentDocument().setInvestigators(new ArrayList<ProposalPerson>());

        for (ProposalPerson person : form.getProposalDevelopmentDocument().getProposalPersons()) {
            LOG.debug(person.getFullName() + " is " + getKeyPersonnelService().isInvestigator(person));
            person.setInvestigatorFlag(getKeyPersonnelService().isInvestigator(person));
            if (getKeyPersonnelService().isInvestigator(person) && !form.getProposalDevelopmentDocument().getInvestigators().contains(person)) {
                form.getProposalDevelopmentDocument().getInvestigators().add(person);
            }
        }
    }
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#loadDocument(KualiDocumentFormBase)
     */
    @Override
    protected void loadDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.loadDocument(kualiDocumentFormBase);


    }
}
