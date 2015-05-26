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
package org.kuali.kra.award.web.struts.action;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.validation.AuditHelper.ValidationState;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.budget.framework.copy.CopyPeriodsQuestion;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Struts Action class for the Propsoal Development Budget Versions page
 */
public class AwardBudgetsAction extends AwardAction implements AuditModeAction {

    private static final Log LOG = LogFactory.getLog(AwardBudgetsAction.class);

    private static final String CONFIRM_SYNCH_BUDGET_RATE = "confirmSynchBudgetRate";
    private static final String NO_SYNCH_BUDGET_RATE = "noSynchBudgetRate";
    public static final String DEFAULT_BUDGET_ACTIVITY_TYPE_CODE = "x";

    /**
     * Main execute method that is run. Populates A map of rate types in the {@link HttpServletRequest} instance to be used
     * in the JSP. The map is called <code>rateClassMap</code> this is set everytime execute is called in this class. This should only
     * happen for the BudgetVersions tab. This ensures that even if {@link RateClass} persisted data may change, it will update the map
     * correctly.
     * 
     * @param mapping {@link ActionMapping}
     * @param form {@link ActionForm} instance
     * @param request {@link HttpServletRequest} instance
     * @param response {@link HttpServletResponse} instance 
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        //since the award budget versions panel is usually(always??) only usable when the award is
        //read only we need to make sure to perform post budget copy stuff here
        if (!StringUtils.equals(awardForm.getMethodToCall(), "save") && awardForm.isSaveAfterCopy()) {
            final List<AwardBudgetExt> overviews = awardForm.getAwardDocument().getBudgetDocumentVersions();
            final AwardBudgetExt copiedBudget = overviews.get(overviews.size() - 1);
            final String copiedName = copiedBudget.getName();
            copiedBudget.setName("copied placeholder");
            LOG.debug("validating " + copiedName);
            boolean valid = getBudgetService().isBudgetVersionNameValid(awardForm.getAwardDocument().getAward(), copiedName);
            copiedBudget.setName(copiedName);
            awardForm.setSaveAfterCopy(!valid);
            if (!valid) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            } else {
                awardForm.getAwardDocument().updateBudgetDescriptions(awardForm.getAwardDocument().getAward().getBudgets());
            }
        }
        
        request.setAttribute("rateClassMap", getBudgetRatesService().getBudgetRateClassMap("O"));
        ActionForward ac = super.execute(mapping, form, request, response);
        getAwardBudgetService().populateBudgetLimitSummary(awardForm.getBudgetLimitSummary(), awardForm.getAwardDocument().getAward());
        return ac;
    }

    /**
     * Action called to create a new budget version.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward addBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();
        ActionForward actionForward = activate(mapping, form, request, response);
            if(actionForward == mapping.findForward(Constants.MAPPING_BASIC)) { 
                Budget newBudget = getBudgetService().addBudgetVersion(awardDoc, awardForm.getNewBudgetVersionName(), Collections.EMPTY_MAP);
                if(newBudget!=null){
                    awardForm.setNewBudgetVersionName("");
                }
            }
        return actionForward;
    }
    
    
    
    
    /**
     * Action called to create a new budget version.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward rebudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();

        AwardBudgetDocument newBudgetDoc = getAwardBudgetService().rebudget(awardDoc, awardForm.getNewBudgetVersionName());
        if(newBudgetDoc!=null){
            awardForm.setNewBudgetVersionName("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC); 
    }

    /**
     * This method opens a particular budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @SuppressWarnings(value={"unchecked","rawtypes"})
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        BudgetService budgetService = KcServiceLocator.getService(BudgetService.class);
        BudgetRatesService budgetRatesService = KcServiceLocator.getService(BudgetRatesService.class);
        AwardBudgetService awardBudgetService = KcServiceLocator.getService(AwardBudgetService.class);
        if ("TRUE".equals(awardForm.getEditingMode().get("modifyAwardBudget"))) {
            save(mapping, form, request, response);
        }
        
        AwardDocument awardDocument = awardForm.getAwardDocument();
        awardDocument.refreshBudgetDocumentVersions();
        Budget budgetToOpen = awardDocument.getBudgetDocumentVersion(getSelectedLine(request));
        Collection<BudgetRate> allBudgetRates = budgetService.getSavedProposalRates(budgetToOpen);
        Award newestAward = getAwardBudgetService().getActiveOrNewestAward(awardDocument.getAward().getAwardNumber());
        newestAward.refreshReferenceObject("awardFandaRate");
        List<AwardFandaRate> fandaRates = newestAward.getAwardFandaRate();
        List ebRates =new ArrayList();
        if(newestAward.getSpecialEbRateOffCampus()!=null)
        	ebRates.add(newestAward.getSpecialEbRateOffCampus());
        if(newestAward.getSpecialEbRateOnCampus()!=null)
        	ebRates.add(newestAward.getSpecialEbRateOnCampus());
        if(newestAward.getRequestedStartDateInitial()==null || newestAward.getRequestedEndDateInitial()==null){
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        if(awardBudgetService.checkRateChange(allBudgetRates, newestAward)){
        	return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_AWARD_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        	 }
        if (budgetRatesService.checkActivityTypeChange(allBudgetRates, newestAward.getActivityTypeCode())) {
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } else if(CollectionUtils.isEmpty(allBudgetRates)) {
            //Throw Empty Rates message
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_NO_RATES_ATTEMPT_SYNCH), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } else {
            DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
            AwardBudgetDocument budgetDocument = (AwardBudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
            String routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getDocumentId();
            Budget budget = budgetDocument.getBudget();
            if (budget.getActivityTypeCode().equals(DEFAULT_BUDGET_ACTIVITY_TYPE_CODE)) {
                budget.setActivityTypeCode(getBudgetService().getActivityTypeForBudget(budget));
            }
            String backUrl = URLEncoder.encode(buildActionUrl(awardDocument.getDocumentNumber(), Constants.MAPPING_AWARD_BUDGET_VERSIONS_PAGE, "AwardDocument"), StandardCharsets.UTF_8.name());
            String forward = buildForwardUrl(routeHeaderId) + "&backLocation=" + backUrl;
            if (!budget.getActivityTypeCode().equals(newestAward.getActivityTypeCode()) || budget.isRateClassTypesReloaded()) {
                budget.setActivityTypeCode(newestAward.getActivityTypeCode());
                forward = forward.replace("awardBudgetParameters.do?", "awardBudgetParameters.do?syncBudgetRate=Y&");
            }
            if (awardForm.isAuditActivated()) {
                forward = StringUtils.replace(forward, "awardBudgetParameters.do?", "awardBudgetParameters.do?auditActivated=true&");
            } else {
            	forward = StringUtils.replace(forward, "awardBudgetParameters.do?", "awardBudgetParameters.do?auditActivated=false&");
            }
            //add in the showAllBudgetVersions flag so it will be persisted until they leave the documents.
            forward = StringUtils.replace(forward, "Parameters.do?", "Parameters.do?showAllBudgetVersions=" + awardForm.isShowAllBudgetVersions() + "&");
            return new ActionForward(forward, true);
        }
    }

    public ActionForward confirmSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, true);
    }

    public ActionForward noSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, false);
    }

    private ActionForward synchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, boolean confirm) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();
        Budget budgetToOpen = awardDoc.getBudgetDocumentVersion(getSelectedLine(request));
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
        String routeHeaderId = awardBudgetDocument.getDocumentHeader().getWorkflowDocument().getDocumentId();
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            awardBudgetDocument.getBudget().setActivityTypeCode(awardDoc.getBudgetParent().getActivityTypeCode());
            Budget budget = awardBudgetDocument.getBudget();
          
          budget.setRateClassTypesReloaded(false);
          forward = forward.replace("awardBudgetParameters.do?", "awardBudgetParameters.do?syncBudgetRate=Y&");
        }
        if (awardForm.isAuditActivated()) {
            forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
        }
        return new ActionForward(forward, true);
    }

    /**
     * This method copies a budget version's data to a new budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward copyBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm pdForm = (AwardForm) form;
        Budget versionToCopy = getSelectedVersion(pdForm, request);
        if (!getAwardBudgetService().validateAddingNewBudget(pdForm.getAwardDocument())) {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        if (StringUtils.isNotBlank(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME))) {
            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            if (CopyPeriodsQuestion.ONE.equals(buttonClicked)) {
                pdForm.setSaveAfterCopy(true);
                return copyBudgetPeriodOne(mapping, form, request, response);
            }
            else if (CopyPeriodsQuestion.ALL.equals(buttonClicked)) {
                pdForm.setSaveAfterCopy(true);
                return copyBudgetAllPeriods(mapping, form, request, response);
            } else {
                // URL hack, just return
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
        
        return performQuestionWithoutInput(mapping, form, request, response, COPY_BUDGET_PERIOD_QUESTION, QUESTION_TEXT + versionToCopy.getBudgetVersionNumber() + ".", QUESTION_TYPE, pdForm.getMethodToCall(), "");
    }
    
    public ActionForward copyBudgetPeriodOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, true);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward copyBudgetAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, false);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private Budget getSelectedVersion(AwardForm proposalDevelopmentForm, HttpServletRequest request) {
        return proposalDevelopmentForm.getAwardDocument().getBudgetDocumentVersion(getSelectedLine(request));
    }
    
    private void copyBudget(ActionForm form, HttpServletRequest request, boolean copyPeriodOneOnly) throws WorkflowException {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();
        Budget budgetToCopy = getSelectedVersion(awardForm, request);
        copyBudget(awardDoc.getBudgetParent(), budgetToCopy, copyPeriodOneOnly);
    }
    
    private StrutsConfirmation syncBudgetRateConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_BUDGET_RATE,
                message, "");
    }

    /**
     * Gets the budgetService attribute. 
     * @return Returns the budgetService.
     */
    public BudgetService getBudgetService() {
        return KcServiceLocator.getService(BudgetService.class);
    }

    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (AwardForm) form, true);
        ValidationState state = KcServiceLocator.getService(AuditHelper.class).isValidSubmission((AwardForm) form, false);
        if (state == ValidationState.ERROR) {
            actionForward = mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
        }
        return actionForward;
    }

    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return null;
    }

}
