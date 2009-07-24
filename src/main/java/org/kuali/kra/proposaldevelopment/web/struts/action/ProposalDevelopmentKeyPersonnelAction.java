/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web.struts.action;


import static java.util.Collections.sort;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_FLAG;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.Constants.NEW_PERSON_LOOKUP_FLAG;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.FormattedLogger.*;
import static org.kuali.kra.logging.BufferedLogger.info;
import static org.kuali.rice.kns.util.KNSConstants.METHOD_TO_CALL_ATTRIBUTE;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.rule.event.CalculateCreditSplitEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ChangeKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Handles actions from the Key Persons page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.63 $
 */
public class ProposalDevelopmentKeyPersonnelAction extends ProposalDevelopmentAction {
    private static final String MISSING_PARAM_MSG = "Couldn't find parameter '%s'";
    private static final String ROLE_CHANGED_MSG  = "roleChanged for person %s = %s";
    private static final String ADDED_PERSON_MSG  = "Added Proposal Person with proposalNumber = %s and proposalPersonNumber = %s";
    private static final String INV_SIZE_MSG      = "Number of investigators are ";
    private static final String EMPTY_STRING = "";
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#execute(ActionMapping, ActionForm, HttpServletRequest,
     *      HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        ActionForward retval = super.execute(mapping, form, request, response);
        prepare(form, request);
        return retval;
    }
    
    public ActionForward moveDown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        List<ProposalPerson> keyPersonnel = ((ProposalDevelopmentForm) form).getDocument().getDevelopmentProposal().getProposalPersons();
        swapAdjacentPersonnel(keyPersonnel, getLineToDelete(request), MoveOperationEnum.MOVING_PERSON_DOWN);
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward moveUp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        List<ProposalPerson> keyPersonnel = ((ProposalDevelopmentForm) form).getDocument().getDevelopmentProposal().getProposalPersons();
        swapAdjacentPersonnel(keyPersonnel, getLineToDelete(request), MoveOperationEnum.MOVING_PERSON_UP);
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    
    /**
     * Common helper method for preparing to <code>{@link #execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)}</code>
     * @param form ActionForm
     * @param request HttpServletRequest
     */
    void prepare(ActionForm form, HttpServletRequest request) {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        request.setAttribute(NEW_PERSON_LOOKUP_FLAG, EMPTY_STRING);
        ProposalDevelopmentDocument document=pdform.getDocument();
        List<ProposalPerson> proposalpersons=document.getDevelopmentProposal().getProposalPersons();
        for (Iterator<ProposalPerson> iter = proposalpersons.iterator(); iter.hasNext();) {
            ProposalPerson person=(ProposalPerson) iter.next();
            if (person.getRole() != null) {
                person.getRole().setReadOnly(getKeyPersonnelService().isRoleReadOnly(person.getRole()));
            }
        }
        
        pdform.populatePersonEditableFields();
        handleRoleChangeEvents(pdform.getDocument());
        
        debug(INV_SIZE_MSG, pdform.getDocument().getDevelopmentProposal().getInvestigators().size());
    
        try {
            boolean creditSplitEnabled = getConfigurationService().getIndicatorParameter(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, CREDIT_SPLIT_ENABLED_RULE_NAME)
                && pdform.getDocument().getDevelopmentProposal().getInvestigators().size() > 0;
            request.setAttribute(CREDIT_SPLIT_ENABLED_FLAG, new Boolean(creditSplitEnabled));
            pdform.setCreditSplitEnabled(creditSplitEnabled);
        }
        catch (Exception e) {
            warn(MISSING_PARAM_MSG, CREDIT_SPLIT_ENABLED_RULE_NAME);
            warn(e.getMessage());
        }        
    }

    
    /**
     * Called to handle situations when the <code>{@link ProposalPersonRole}</code> is changed on a <code>{@link ProposalPerson}</code>. It
     * does this by looping through a <code>{@link List}</code> of <code>{@link ProposalPerson}</code> instances in a 
     * <code>{@link ProposalDevelopmentDocument}</code>
     *  
     * @param document <code>{@link ProposalDevelopmentDocument}</code> instance with <code>{@link List}</code> of 
     * <code>{@link ProposalPerson}</code> instances. 
     */
    private void handleRoleChangeEvents(ProposalDevelopmentDocument document) {
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            debug(ROLE_CHANGED_MSG, person.getFullName(), person.isRoleChanged());
            
            if (person.isRoleChanged()) {
                changeRole(person, document);
            }
        }
    }

    /**
     * Takes the necessary steps to change a role of a <code>{@link ProposalPerson}</code> instance.<br/>
     * <br/>
     * This includes:
     * <ul>
     *   <li>Updating investigator flag on <code>{@link ProposalPerson}</code></li>
     *   <li>Removing or adding to the investigators <code>{@link List}</code> in the
     *   <code>{@link ProposalDevelopmentDocument}</code> </li>
     *   <li>Adding credit split defaults for investigators</li>
     *   <li>Deleting units and credit splits from key persons</li>
     * </ul>
     * <br/> 
     * This method is typically called from <code>{@link #handleRoleChangeEvents(ProposalDevelopmentDocument)</code>
     * 
     * @param person
     * @param document
     * @see {@link #handleRoleChangeEvents(ProposalDevelopmentDocument)}
     */
    private void changeRole(ProposalPerson person, ProposalDevelopmentDocument document) {
        getKeyPersonnelService().populateProposalPerson(person, document);
    }

    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#refresh(ActionMapping, ActionForm, HttpServletRequest,
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
            person.setProposalNumber(pdform.getDocument().getDevelopmentProposal().getProposalNumber());
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
    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
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
        ProposalDevelopmentDocument document = pdform.getDocument();
        GlobalVariables.getErrorMap().removeFromErrorPath("document.proposalPersons");
        
        if ( isNotBlank(pdform.getNewProposalPerson().getProposalPersonRoleId())){
            if(pdform.getNewProposalPerson().getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE) || pdform.getNewProposalPerson().equals(CO_INVESTIGATOR_ROLE))
            {
                pdform.getNewProposalPerson().setOptInUnitStatus("Y");
                pdform.getNewProposalPerson().setOptInCertificationStatus("Y");
                pdform.setOptInCertificationStatus("Y");
            }else
            {
                pdform.getNewProposalPerson().setOptInUnitStatus("N");
                pdform.getNewProposalPerson().setOptInCertificationStatus("N");
                pdform.setOptInCertificationStatus("N");
            }
        }
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new AddKeyPersonEvent(pdform.getDocument(), pdform.getNewProposalPerson()));

        // if the rule evaluation passed, let's add it
        if (rulePassed) {
            document.getDevelopmentProposal().addProposalPerson(pdform.getNewProposalPerson());
            info(ADDED_PERSON_MSG, pdform.getNewProposalPerson().getProposalNumber(), pdform.getNewProposalPerson().getProposalPersonNumber());
            // handle lead unit for investigators respective to coi or pi
            if (getKeyPersonnelService().isPrincipalInvestigator(pdform.getNewProposalPerson())) {
                getKeyPersonnelService().assignLeadUnit(pdform.getNewProposalPerson(), document.getDevelopmentProposal().getOwnedByUnitNumber());
            }
            else {
                // Lead Unit information needs to be removed in case the person used to be a PI
                ProposalPersonUnit unit =pdform.getNewProposalPerson().getUnit(document.getDevelopmentProposal().getOwnedByUnitNumber());
                if (unit != null) {
                    unit.setLeadUnit(false);
                }                
            }
            if(pdform.getNewProposalPerson().getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE) || pdform.getNewProposalPerson().getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE)){
                if (isNotBlank(pdform.getNewProposalPerson().getHomeUnit()) && isValidHomeUnit(pdform.getNewProposalPerson(),pdform.getNewProposalPerson().getHomeUnit())){
                    getKeyPersonnelService().addUnitToPerson(pdform.getNewProposalPerson(),getKeyPersonnelService().createProposalPersonUnit(pdform.getNewProposalPerson().getHomeUnit(), pdform.getNewProposalPerson()));
                }
            }
            getKeyPersonnelService().populateProposalPerson(pdform.getNewProposalPerson(), document);
            sort(document.getDevelopmentProposal().getProposalPersons(), new ProposalPersonComparator());
            sort(document.getDevelopmentProposal().getInvestigators(), new ProposalPersonComparator());
            pdform.setNewProposalPerson(new ProposalPerson());
            pdform.setNewRolodexId("");
            pdform.setNewPersonId("");

        }  

        return mapping.findForward(MAPPING_BASIC);
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
     * Add a degree to a <code>{@link ProposalPerson}</code>
     *
     * @return ActionForward
     */
    public ActionForward insertDegree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getDocument();

        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(selectedPersonIndex);
        ProposalPersonDegree degree = pdform.getNewProposalPersonDegree().get(selectedPersonIndex);
        degree.setDegreeSequenceNumber(pdform.getDocument().getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
         
        // check any business rules
        
        boolean rulePassed = getKualiRuleService().applyRules(new ChangeKeyPersonEvent(document, person, degree,selectedPersonIndex));

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
        ProposalDevelopmentDocument document = pdform.getDocument();

        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(selectedPersonIndex);
        ProposalPersonUnit unit = getKeyPersonnelService().createProposalPersonUnit(pdform.getNewProposalPersonUnit().get(selectedPersonIndex).getUnitNumber(), person);
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new ChangeKeyPersonEvent(document, person, unit,selectedPersonIndex));

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
        ProposalDevelopmentDocument document = pdform.getDocument();
        
        for (Iterator<ProposalPerson> person_it = document.getDevelopmentProposal().getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if (person.isDelete()) {
                person_it.remove();
                document.getDevelopmentProposal().getInvestigators().remove(person);
                document.getDevelopmentProposal().removePersonnelAttachmentForDeletedPerson(person);
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
        ProposalDevelopmentDocument document = pdform.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson selectedPerson =  document.getDevelopmentProposal().getProposalPerson(selectedPersonIndex);
        ProposalPersonUnit unit = selectedPerson.getUnit(getSelectedLine(request));
        selectedPerson.getUnits().remove(unit);

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
        ProposalDevelopmentDocument document = pdform.getDocument();
        
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
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getDocument();
        boolean rulePassed = getKualiRuleService().applyRules(new CalculateCreditSplitEvent(EMPTY_STRING, document));
        if(rulePassed){
            prepare(form, request);
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        boolean rulePassed = true;

        updateCurrentOrdinalPositions(((ProposalDevelopmentForm) form).getDocument().getDevelopmentProposal().getProposalPersons());
        
        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new SaveKeyPersonEvent(EMPTY_STRING, pdform.getDocument()));

        // if the rule evaluation passed, then save. It is possible that invoking save without checking rules first will
        // let the document save anyhow, so let's check first.
        if (rulePassed) {
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(MAPPING_BASIC);
    }
    

    /**
     *Adds the unit Details for the keyperson 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addUnitDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(selectedPersonIndex);
        if (isNotBlank(person.getHomeUnit()) && isValidHomeUnit(person,person.getHomeUnit())){
            getKeyPersonnelService().addUnitToPerson(person,getKeyPersonnelService().createProposalPersonUnit(person.getHomeUnit(), person));
        }
        person.setOptInUnitStatus("Y");
        pdform.setOptInUnitDetails("Y");  
        getKeyPersonnelService().populateProposalPerson(person, document);
        return mapping.findForward(MAPPING_BASIC);
    }
    
    
    /**
     * Removes the unit Details for  keyperson 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward removeUnitDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getDocument();
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        pdform.setOptInUnitDetails("N");
        selectedPerson.setOptInUnitStatus("N");
        selectedPerson.getUnits().clear();
        document.getDevelopmentProposal().getInvestigators().remove(selectedPerson);
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     *Adds the Certification Question for the keyperson 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addCertificationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getDocument();
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        pdform.setOptInCertificationStatus("Y");
        selectedPerson.setOptInCertificationStatus("Y");
        getKeyPersonnelService().populateProposalPerson(selectedPerson, document);
        return mapping.findForward(MAPPING_BASIC);
    }
    /**
     * Removes the Certification Question for the keyperson 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward removeCertificationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getDocument();
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        pdform.setOptInCertificationStatus("N");
        selectedPerson.setOptInCertificationStatus("N");
        selectedPerson.getProposalPersonYnqs().clear();
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
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            int lineNumber = Integer.parseInt(substringBetween(parameterName, "proposalPersons[", "]."));
            retval = document.getDevelopmentProposal().getProposalPerson(lineNumber);
        }

        return retval;
    }

    protected int getSelectedPersonIndex(HttpServletRequest request, ProposalDevelopmentDocument document) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(METHOD_TO_CALL_ATTRIBUTE);
        if (isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(substringBetween(parameterName, "proposalPersons[", "]."));
        }

        return selectedPersonIndex;
    }

    /**
     * Determines whether the person has valid unit
     * 
     * @param proposalperson
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    protected boolean isValidHomeUnit(ProposalPerson person, String unitId){
        Map valueMap = new HashMap();
        valueMap.put("unitNumber", unitId);
        Collection<Unit> units = getBusinessObjectService().findMatching(Unit.class, valueMap);
        
        return CollectionUtils.isNotEmpty(units);
    }
       
    @Override
    protected BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }
    
    private void swapAdjacentPersonnel(List<ProposalPerson> keyPersonnel, int index1, MoveOperationEnum op) {
        ProposalPerson movingPerson = keyPersonnel.get(index1);
        
        if ((op == MoveOperationEnum.MOVING_PERSON_DOWN && movingPerson.isMoveDownAllowed()) || (op == MoveOperationEnum.MOVING_PERSON_UP && movingPerson.isMoveUpAllowed())) {            
            int index2 = index1 + op.getOffset();
            keyPersonnel.set(index1, keyPersonnel.get(index2));
            keyPersonnel.set(index2, movingPerson);
        }
    }
    
    private enum MoveOperationEnum {
        MOVING_PERSON_DOWN (1),
        MOVING_PERSON_UP(-1);
        
        private int offset;
        
        private MoveOperationEnum(int offset) {
            this.offset = offset;
        }
        
        public int getOffset() { return offset; }
    };
    
    private void updateCurrentOrdinalPositions(List<ProposalPerson> keyPersonnel) {
        Integer index = 0;
        for(ProposalPerson person: keyPersonnel) {
            person.setOrdinalPosition(index++);
        }
    }

    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward returnValue = super.reload(mapping, form, request, response);
        clearProposalPerson(mapping, form, request, response);
        return returnValue;
    }
    
    
}


