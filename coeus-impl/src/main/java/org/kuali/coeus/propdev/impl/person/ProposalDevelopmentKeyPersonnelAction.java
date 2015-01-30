/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.person;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.person.PersonRolodexComparator;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.propdev.impl.print.ProposalDevelopmentPrintingService;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelperComparator;
import org.kuali.coeus.propdev.impl.person.creditsplit.CalculateCreditSplitEvent;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static java.util.Collections.sort;
import static org.apache.commons.lang3.StringUtils.*;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.*;
import static org.kuali.rice.krad.util.KRADConstants.METHOD_TO_CALL_ATTRIBUTE;

/**
 * Handles actions from the Key Persons page of the 
 * <code>{@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.63 $
 */
public class ProposalDevelopmentKeyPersonnelAction extends ProposalDevelopmentAction {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentKeyPersonnelAction.class);

    private static final String EMPTY_STRING = "";
    
    private static final String ERROR_REMOVE_HIERARCHY_PI = "error.hierarchy.personnel.removePrincipleInvestigator";
    private static final String ERROR_FIELD_REMOVE_HIERARCHY_PI ="document.developmentProposalList[0].proposalPersons[%s].delete";

    private QuestionnaireAnswerService questionnaireAnswerService;
    private QuestionnairePrintingService questionnairePrintingService;
    private ProposalHierarchyService proposalHierarchyService;

    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        if (questionnaireAnswerService == null)
            questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
        return questionnaireAnswerService;
    }

    protected QuestionnairePrintingService getQuestionnairePrintingService() {
        if (questionnairePrintingService == null)
            questionnairePrintingService = KcServiceLocator.getService(QuestionnairePrintingService.class);
        return questionnairePrintingService;
    }

    protected  ProposalHierarchyService getProposalHierarchyService(){
        if (proposalHierarchyService == null)
            proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        return proposalHierarchyService;

    }

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
    
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        boolean rulePassed = getKualiRuleService().applyRules(new SaveKeyPersonEvent(EMPTY_STRING, pdform.getProposalDevelopmentDocument()));
        if (rulePassed) {
            super.preSave(mapping, form, request, response);
        }
    }
    
    public ActionForward moveDown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        List<ProposalPerson> keyPersonnel = ((ProposalDevelopmentForm) form).getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons();
        swapAdjacentPersonnel(keyPersonnel, getLineToDelete(request), MoveOperationEnum.MOVING_PERSON_DOWN);
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward moveUp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        List<ProposalPerson> keyPersonnel = ((ProposalDevelopmentForm) form).getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons();
        swapAdjacentPersonnel(keyPersonnel, getLineToDelete(request), MoveOperationEnum.MOVING_PERSON_UP);
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * 
     * This method will save answers to a questionnaire after the proposal has been routed for approval.
     * It should not be available after the proposal has been approved.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward completeQuestionnaireAfterRouting(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        boolean rulePassed = true;
        rulePassed &= getKualiRuleService().applyRules(new SaveKeyPersonEvent(EMPTY_STRING, pdform.getProposalDevelopmentDocument()));
        if(rulePassed) {
            List<AnswerHeader> answerHeadersToSave = new ArrayList<AnswerHeader>();
                //doing this check to make sure the person wasn't automatically deleted after adding.
                ProposalPersonQuestionnaireHelper helper2 = pdform.getProposalPersonQuestionnaireHelpers().get(this.getSelectedLine(request));
                if(helper2 != null) {
                    if (pdform.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons().contains(helper2.getProposalPerson())) {
                        helper2.preSave();
                        answerHeadersToSave.addAll(helper2.getAnswerHeaders());
                    }
                }
            if (!answerHeadersToSave.isEmpty()) {
                this.getBusinessObjectService().save(answerHeadersToSave);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Common helper method for preparing to <code>{@link #execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)}</code>
     * @param form ActionForm
     * @param request HttpServletRequest
     */
    public void prepare(ActionForm form, HttpServletRequest request) {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document=pdform.getProposalDevelopmentDocument();
        for (ProposalPersonQuestionnaireHelper helper : pdform.getProposalPersonQuestionnaireHelpers()) {
            for (int i = 0; i < helper.getAnswerHeaders().size(); i++) {
                helper.updateChildIndicator(i);
            }
        }
        //need to set this based on route status, permissions...
        pdform.populatePersonEditableFields();
        handleRoleChangeEvents(pdform.getProposalDevelopmentDocument());

        LOG.debug("Number of investigators are " + pdform.getProposalDevelopmentDocument().getDevelopmentProposal().getInvestigators().size());
    
        try {
            boolean creditSplitEnabled = this.getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, CREDIT_SPLIT_ENABLED_RULE_NAME)
                && pdform.getProposalDevelopmentDocument().getDevelopmentProposal().getInvestigators().size() > 0;
            request.setAttribute(CREDIT_SPLIT_ENABLED_FLAG, new Boolean(creditSplitEnabled));
            pdform.setCreditSplitEnabled(creditSplitEnabled);
        }
        catch (Exception e) {
            LOG.warn("Couldn't find parameter " + CREDIT_SPLIT_ENABLED_RULE_NAME);
            LOG.warn(e.getMessage());
        }
    }

    
    /**
     * Called to handle situations when the <code>{@link org.kuali.coeus.common.framework.person.PropAwardPersonRole}</code> is changed on a <code>{@link ProposalPerson}</code>. It
     * does this by looping through a <code>{@link List}</code> of <code>{@link org.kuali.coeus.propdev.impl.person.ProposalPerson}</code> instances in a
     * <code>{@link ProposalDevelopmentDocument}</code>
     *  
     * @param document <code>{@link ProposalDevelopmentDocument}</code> instance with <code>{@link List}</code> of 
     * <code>{@link ProposalPerson}</code> instances. 
     */
    private void handleRoleChangeEvents(ProposalDevelopmentDocument document) {
        int index = 0;
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            LOG.debug("roleChanged for person " + person.getFullName() + " = " + person.isRoleChanged());
            
            if (person.isRoleChanged()) {
                if (document.getDevelopmentProposal().isParent()) {
                    GlobalVariables.getMessageMap().putError(String.format(ERROR_FIELD_REMOVE_HIERARCHY_PI, index), "error.hierarchy.unexpected", "Personnel Roles cannot be changed in a Hierarchy Parent Proposal");
                }
                else {
                    changeRole(person, document);
                }
            }
            index++;
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
     * This method is typically called from <code>{@link #handleRoleChangeEvents(ProposalDevelopmentDocument)}</code>
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
            person = new ProposalPerson();
            person.setRolodexId(Integer.parseInt(pdform.getNewRolodexId()));
            getPersonEditableService().populateContactFieldsFromRolodexId(person);
        }
        else if (!isBlank(pdform.getNewPersonId())) {
            person = new ProposalPerson();
            person.setPersonId(pdform.getNewPersonId());
            getPersonEditableService().populateContactFieldsFromPersonId(person);
        }
        
        if (person != null) {
            person.setDevelopmentProposal(pdform.getProposalDevelopmentDocument().getDevelopmentProposal());
            person.setProposalPersonRoleId(pdform.getNewProposalPerson().getProposalPersonRoleId());
            pdform.setNewProposalPerson(person);
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
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        GlobalVariables.getMessageMap().removeFromErrorPath("document.proposalPersons");
        
        if (isNotBlank(pdform.getNewProposalPerson().getProposalPersonRoleId())) {
            if (pdform.getNewProposalPerson().getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE) || pdform.getNewProposalPerson().equals(CO_INVESTIGATOR_ROLE)) {
                pdform.getNewProposalPerson().setOptInUnitStatus(true);
                pdform.getNewProposalPerson().setOptInCertificationStatus(true);
            } else {
                pdform.getNewProposalPerson().setOptInUnitStatus(false);
                pdform.getNewProposalPerson().setOptInCertificationStatus(false);
            }
        }
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new AddKeyPersonEvent(pdform.getProposalDevelopmentDocument(), pdform.getNewProposalPerson()));

        // if the rule evaluation passed, let's add it
        if (rulePassed) {
            
            ProposalPerson proposalPerson = pdform.getNewProposalPerson();
            
            getKeyPersonnelService().addProposalPerson(proposalPerson, document);
            sort(document.getDevelopmentProposal().getProposalPersons(), new PersonRolodexComparator());
            sort(document.getDevelopmentProposal().getInvestigators(), new PersonRolodexComparator());
            
            ProposalPersonQuestionnaireHelper helper = new ProposalPersonQuestionnaireHelper(proposalPerson);
            pdform.getProposalPersonQuestionnaireHelpers().add(helper);
            sort(pdform.getProposalPersonQuestionnaireHelpers(), new ProposalPersonQuestionnaireHelperComparator());
            
            pdform.setNewProposalPerson(new ProposalPerson());
            pdform.setNewRolodexId("");
            pdform.setNewPersonId("");
            
            helper.populateAnswers();
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
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();

        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(selectedPersonIndex);
        ProposalPersonDegree degree = pdform.getNewProposalPersonDegree().get(selectedPersonIndex);
        degree.setDegreeSequenceNumber(pdform.getProposalDevelopmentDocument().getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
         
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
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();

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
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        if (proposal.isParent()) {
            GlobalVariables.getMessageMap().putError("newProposalPerson", "error.hierarchy.unexpected", "Cannot remove Personnel from the Parent of a Hierarchy");
        }
        else {
            ProposalPerson parentPi = null;
            if (proposal.isChild()) {
                parentPi = getProposalHierarchyService().getParentDocument(document).getDevelopmentProposal().getPrincipalInvestigator();
            }
            int index = 0;
            for (Iterator<ProposalPerson> person_it = proposal.getProposalPersons().iterator(); person_it.hasNext(); index++) {
                ProposalPerson person = person_it.next();
                if (person.isDelete()) {
                    pdform.getProposalPersonsToDelete().add(person);
                    if (parentPi != null && parentPi.equals(person)) {
                        GlobalVariables.getMessageMap().putError(String.format(ERROR_FIELD_REMOVE_HIERARCHY_PI, index), ERROR_REMOVE_HIERARCHY_PI, person.getFullName());                  
                    }
                    else {
                        person_it.remove();
                        proposal.getInvestigators().remove(person);
                        proposal.removePersonnelAttachmentForDeletedPerson(person);
                        ProposalPersonQuestionnaireHelper helper = new ProposalPersonQuestionnaireHelper(person);
                        pdform.getAnswerHeadersToDelete().addAll(helper.getAnswerHeaders());
                    }
                }
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
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
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

        updateCurrentOrdinalPositions(((ProposalDevelopmentForm) form).getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons());
        
        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new SaveKeyPersonEvent(EMPTY_STRING, pdform.getProposalDevelopmentDocument()));

        // if the rule evaluation passed, then save. It is possible that invoking save without checking rules first will
        // let the document save anyhow, so let's check first.
        if (rulePassed) {
            //save the answer headers
            List<AnswerHeader> answerHeadersToSave = new ArrayList<AnswerHeader>();
            for (ProposalPersonQuestionnaireHelper helper : pdform.getProposalPersonQuestionnaireHelpers()) {
                //doing this check to make sure the person wasn't automatically deleted after adding.
                if (pdform.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons().contains(helper.getProposalPerson())) {
                    helper.preSave();
                    answerHeadersToSave.addAll(helper.getAnswerHeaders());
                }
            }
            if (!answerHeadersToSave.isEmpty()) {
                this.getBusinessObjectService().save(answerHeadersToSave);
            }
            
            /**
             * Simply deleting pdform.getAnswerHeadersToDelete() causes an OLE, with the error saying that OJB thinks
             * the object has already been deleted or modified by another user.  This avoids that situation.
             */
            if (!pdform.getAnswerHeadersToDelete().isEmpty()) {
                List<AnswerHeader> freshHeaders = new ArrayList<AnswerHeader>();
                for (AnswerHeader header : pdform.getAnswerHeadersToDelete()) {
                    Map primaryKeys = new HashMap();
                    primaryKeys.put("QUESTIONNAIRE_ANSWER_HEADER_ID", header.getId());
                    AnswerHeader ah = (AnswerHeader) this.getBusinessObjectService().findByPrimaryKey(AnswerHeader.class, primaryKeys);
                    if (ah != null) {
                        freshHeaders.add(ah);
                    }
                }
                if (!freshHeaders.isEmpty()) {
                    this.getBusinessObjectService().delete(freshHeaders);
                }
                pdform.getAnswerHeadersToDelete().clear();
            }
            pdform.setPropsoalPersonsToDelete(new ArrayList<ProposalPerson>());
            
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
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, document);
        ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(selectedPersonIndex);
        if (isNotBlank(person.getHomeUnit()) && getKeyPersonnelService().isValidHomeUnit(person,person.getHomeUnit())){
            getKeyPersonnelService().addUnitToPerson(person,getKeyPersonnelService().createProposalPersonUnit(person.getHomeUnit(), person));
        }
        person.setOptInUnitStatus(true);
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
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        selectedPerson.setOptInUnitStatus(false);
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
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        selectedPerson.setOptInCertificationStatus(true);
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
    public ActionForward printCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        ProposalDevelopmentPrintingService printService = getProposalDevelopmentPrintingService();
        Map<String,Object> reportParameters = new HashMap<String,Object>();
        reportParameters.put(ProposalDevelopmentPrintingService.PRINT_CERTIFICATION_PERSON, selectedPerson);
        AttachmentDataSource dataStream = printService.printProposalDevelopmentReport(document.getDevelopmentProposal(),
                ProposalDevelopmentPrintingService.PRINT_CERTIFICATION_REPORT, reportParameters);
        streamToResponse(dataStream, response);
        return null;
    }

    /**
     * Prints the Certification Questions for the keyperson 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward removeCertificationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        ProposalPerson selectedPerson = getSelectedPerson(request, document);
        selectedPerson.setOptInCertificationStatus(false);
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

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward returnValue = super.reload(mapping, form, request, response);
        clearProposalPerson(mapping, form, request, response);
        return returnValue;
    }
    
    public ActionForward printQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        
        final int personIndex = this.getSelectedLine(request);
        
        ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(personIndex);
        ProposalPersonQuestionnaireHelper helper = new ProposalPersonQuestionnaireHelper(person);
        AnswerHeader header = helper.getAnswerHeaders().get(0);
        
        reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, header.getQuestionnaire().getQuestionnaireSeqIdAsInteger());
        reportParameters.put("template", header.getQuestionnaire().getTemplate());

        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(person, reportParameters);
        if (dataStream.getData() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        
        return forward;
    }   

    public ActionForward updateAnswerToNewVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        
        final String formProperty = getFormProperty(request,"updateAnswerToNewVersion");
        
        if (StringUtils.contains(formProperty, ".proposalPersonQuestionnaireHelpers[")) {
            int selectedPersonIndex = Integer.parseInt(formProperty.substring(36, formProperty.length()-1));
            
            ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(selectedPersonIndex);
            ProposalPersonQuestionnaireHelper helper = pdform.getProposalPersonQuestionnaireHelpers().get(selectedPersonIndex);
            
            helper.updateQuestionnaireAnswer(getLineToDelete(request));
            getBusinessObjectService().save(helper.getAnswerHeaders().get(getLineToDelete(request)));
            
            return mapping.findForward(MAPPING_BASIC);
            
        } else {
            throw new RuntimeException(String.format("Do not know how to process updateAnswerToNewVersion for formProperty %s",formProperty));
        }
    }
}
