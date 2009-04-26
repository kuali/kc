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
package org.kuali.kra.budget.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.TbnPerson;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rules.BudgetPersonnelRule;
import org.kuali.kra.budget.service.BudgetPersonService;
import org.kuali.kra.budget.service.BudgetPersonnelBudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Action class for Budget Personnel page
 */
public class BudgetPersonnelAction extends BudgetAction {
    
    private static final Log LOG = LogFactory.getLog(BudgetPersonnelAction.class);
    
    private static final String CONFIRM_DELETE_BUDGET_PERSON = "confirmDeleteBudgetPerson";
    
    /**
     * Overridden to populate BudgetPerson list with persons returned from lookups
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        
        // Process return from person/rolodex multi-value lookup
        if (budgetForm.getLookupResultsBOClassName() != null && budgetForm.getLookupResultsSequenceNumber() != null) {
            String lookupResultsSequenceNumber = budgetForm.getLookupResultsSequenceNumber();
            Class<?> lookupResultsBOClass = Class.forName(budgetForm.getLookupResultsBOClassName());
            
            Collection<PersistableBusinessObject> rawValues = KraServiceLocator.getService(LookupResultsService.class)
                .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                        GlobalVariables.getUserSession().getPerson().getPrincipalName());
            
            BudgetPersonService budgetPersonService = KraServiceLocator.getService(BudgetPersonService.class);
            if (lookupResultsBOClass.isAssignableFrom(Person.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    Person person = (Person) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(person);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getBudgetDocument(), budgetPersonService);
                }
            } else if (lookupResultsBOClass.isAssignableFrom(NonOrganizationalRolodex.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    Rolodex rolodex = (Rolodex) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(rolodex);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getBudgetDocument(), budgetPersonService);
                }
            } else if (lookupResultsBOClass.isAssignableFrom(TbnPerson.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    TbnPerson tbn = (TbnPerson) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(tbn);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getBudgetDocument(), budgetPersonService);
                }
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetDocument budgetDocument = ((BudgetForm) form).getBudgetDocument();
        if (new BudgetPersonnelRule().processCheckBaseSalaryFormat(budgetDocument)) {
            BudgetPersonService budgetPersonService = KraServiceLocator.getService(BudgetPersonService.class);
            budgetPersonService.populateBudgetPersonDefaultDataIfEmpty(budgetDocument);
            super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is used to delete the proposal attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteBudgetPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetDocument budgetDocument = ((BudgetForm) form).getBudgetDocument();
        if (!new BudgetPersonnelRule().processCheckExistBudgetPersonnelDetailsBusinessRules(budgetDocument, budgetDocument.getBudgetPerson(getLineToDelete(request)))) {
            return mapping.findForward(MAPPING_BASIC);
        } else {
            return confirm(buildDeleteBudgetPersonConfirmationQuestion(mapping, form, request, response), CONFIRM_DELETE_BUDGET_PERSON, "");
        }
    }

    /**
     * 
     * This method is used to delete the proposal attachment
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward confirmDeleteBudgetPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_BUDGET_PERSON.equals(question)) {
            BudgetDocument budgetDocument = ((BudgetForm) form).getBudgetDocument();
            getBudgetPersonnelBudgetService()
                .deleteBudgetPersonnelDetailsForPerson(budgetDocument,             
                                                       budgetDocument.getBudgetPerson(getLineToDelete(request)));            

            budgetDocument.getBudgetPersons().remove(getLineToDelete(request));            
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        reconcilePersonnelRoles(budgetForm.getBudgetDocument());
        return forward;
    }
    
    /**
     * This method synchs budget personnel with proposal personnel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward synchToProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        KraServiceLocator.getService(BudgetPersonService.class).synchBudgetPersonsToProposal(budgetForm.getBudgetDocument());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Convenience method for adding populating a new budget person and adding to budget document
     * 
     * @param budgetPerson
     * @param budgetDocument
     * @param budgetPersonService
     */
    private void populateAndAddBudgetPerson(BudgetPerson budgetPerson, BudgetDocument budgetDocument, BudgetPersonService budgetPersonService) {
        budgetPersonService.populateBudgetPersonData(budgetDocument, budgetPerson);
        budgetDocument.addBudgetPerson(budgetPerson);
    }
    
    /**
     * Builds the Delete Abstract Confirmation Question as a <code>{@link StrutsConfirmation}</code> instance.<br/>  
     * <br/>
     * The confirmation question is extracted from the resource bundle
     * and the parameter {0} is replaced with the name of the abstract type
     * that will be deleted.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @param questionId String questionId. This needs to be unique for each type of attachment because there are different attachments to delete.
     * @return the confirmation question
     * @throws Exception
     * @see buildParameterizedConfirmationQuestion
     */
    private StrutsConfirmation buildDeleteBudgetPersonConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetDocument budgetDocument = ((BudgetForm) form).getBudgetDocument();
        String personName = budgetDocument.getBudgetPerson(getLineToDelete(request)).getPersonName();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_BUDGET_PERSON, KeyConstants.QUESTION_DELETE_PERSON, personName);
    }

    private BudgetPersonnelBudgetService getBudgetPersonnelBudgetService() {
        return KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
    }
}
