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
package org.kuali.coeus.propdev.impl.budget.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintType;
import org.kuali.coeus.common.budget.impl.print.BudgetPrintForm;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.controller.ControllerFileUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/proposalDevelopment")
public class ProposalBudgetController extends ProposalDevelopmentControllerBase {

    private static final String BUDGET_SUMMARY_DIALOG_ID = "PropBudget-SummaryPage-Dialog";
    private static final String BUDGET_PRINT_FORMS_DIALOG_ID = "PropBudget-PrintForms-Dialog";
    private static final String ENABLE_SUMMARY_PRINT_PANEL = "enableSummaryPrintPanel";

	@Autowired
	@Qualifier("proposalBudgetSharedControllerService")
	private ProposalBudgetSharedControllerService proposalBudgetSharedController;

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
	@Qualifier("budgetPrintService")
	private BudgetPrintService budgetPrintService;

    @RequestMapping(params="methodToCall=addBudget")
    public ModelAndView addBudget(@RequestParam("addBudgetDto.budgetName") String budgetName, 
    		@RequestParam("addBudgetDto.summaryBudget") Boolean summaryBudget,
            @RequestParam(value="addBudgetDto.modularBudget",defaultValue="false") Boolean modularBudget,
    		@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		super.save(form);
		return getProposalBudgetSharedController().addBudget(budgetName, summaryBudget, modularBudget, form.getDevelopmentProposal(), form);
    }

	@MethodAccessible
    @Transactional @RequestMapping(params="methodToCall=copyBudget")
	public ModelAndView copyBudget(@RequestParam("copyBudgetDto.budgetName") String budgetName, 
			@RequestParam("copyBudgetDto.originalBudgetId") Long originalBudgetId, 
			@RequestParam("copyBudgetDto.allPeriods") Boolean allPeriods,
			@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		super.save(form);
		return getProposalBudgetSharedController().copyBudget(budgetName, originalBudgetId, allPeriods, form.getDevelopmentProposal(), form);
	}

	@Transactional @RequestMapping(params="methodToCall=populateBudgetSummary")
    public ModelAndView populateBudgetSummary(@RequestParam("budgetId") Long budgetId,
                                              @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        super.save(form);
        ProposalDevelopmentBudgetExt selectedBudget = null;
        if (form.getProposalDevelopmentDocument().getDevelopmentProposal().getBudgets() != null) {
            for (ProposalDevelopmentBudgetExt curBudget : form.getProposalDevelopmentDocument().getDevelopmentProposal().getBudgets()) {
                if (ObjectUtils.equals(budgetId, curBudget.getBudgetId())) {
                    selectedBudget = curBudget;
                }
            }
        }
        form.setSelectedBudget(selectedBudget);
        if(form.getSelectedBudget().getBudgetSummaryDetails().isEmpty()) {
            getBudgetCalculationService().populateBudgetSummaryTotals(form.getSelectedBudget());
        }
        return getModelAndViewService().showDialog(BUDGET_SUMMARY_DIALOG_ID, true, form);
    }

    @Transactional @RequestMapping(params="methodToCall=markForSubmission")
	public ModelAndView markForSubmission(@RequestParam("budgetId") Long budgetId, @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		ProposalDevelopmentBudgetExt finalBudget = null;
		if (form.getProposalDevelopmentDocument().getDevelopmentProposal().getBudgets() != null) {
			for (ProposalDevelopmentBudgetExt curBudget : form.getProposalDevelopmentDocument().getDevelopmentProposal().getBudgets()) {
				if (ObjectUtils.equals(budgetId, curBudget.getBudgetId())) {
					finalBudget = curBudget;
				}
			}
		}
		form.getProposalDevelopmentDocument().getDevelopmentProposal().setFinalBudget(finalBudget);
		return super.save(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=openBudget")
	public ModelAndView openBudget(@RequestParam("budgetId") String budgetId, @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		super.save(form);
		return getProposalBudgetSharedController().openBudget(budgetId, form);
	}		

	@Transactional @RequestMapping(params="methodToCall=populatePrintForms")
    public ModelAndView populatePrintForms(@RequestParam("budgetId") Long budgetId,
			@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)
			throws Exception {
		String enableSummaryPrintPanel = getParameterService()
				.getParameterValueAsString(
						Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
						Constants.PARAMETER_COMPONENT_DOCUMENT,
						ENABLE_SUMMARY_PRINT_PANEL);
		if (!ObjectUtils.equals(enableSummaryPrintPanel, "Y")) {
			return getModelAndViewService().getModelAndView(form);
		} else {
			super.save(form);
			ProposalDevelopmentBudgetExt selectedBudget = null;
			if (form.getProposalDevelopmentDocument().getDevelopmentProposal().getBudgets() != null) {
				for (ProposalDevelopmentBudgetExt curBudget : form.getProposalDevelopmentDocument()
						.getDevelopmentProposal().getBudgets()) {
					if (ObjectUtils.equals(budgetId, curBudget.getBudgetId())) {
						selectedBudget = curBudget;
					}
				}
			}
			form.setSelectedBudget(selectedBudget);
			if (form.getSelectedBudget().getBudgetPrintForms().isEmpty()) {
				getBudgetPrintService().populateBudgetPrintForms(form.getSelectedBudget());
			}
			return getModelAndViewService().showDialog(BUDGET_PRINT_FORMS_DIALOG_ID, true, form);
		}
	}
	
	@Transactional @RequestMapping(params="methodToCall=printBudgetForms")
	public ModelAndView printBudgetForms(
			@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
			HttpServletResponse response) throws Exception {
		Budget selectedBudget = form.getSelectedBudget();
		List<BudgetPrintForm> selectedBudgetPrintForms = new ArrayList<BudgetPrintForm>();
		for (BudgetPrintForm selectedForm : selectedBudget.getBudgetPrintForms()) {
			if (Boolean.TRUE.equals(selectedForm.getSelectToPrint())) {
				selectedBudgetPrintForms.add(selectedForm);
			}
		}
		if (selectedBudgetPrintForms != null
				&& selectedBudgetPrintForms.size() > 0) {
			String reportName = BudgetPrintType.BUDGET_SUMMARY_REPORT
					.getBudgetPrintType();
			AttachmentDataSource dataStream = getBudgetPrintService()
					.readBudgetSelectedPrintStreams(selectedBudget,
							selectedBudgetPrintForms, reportName);
			if (dataStream.getData() != null) {
				ControllerFileUtils.streamToResponse(dataStream, response);
			}
		}
		return getModelAndViewService().getModelAndView(form);
	}

	public ProposalBudgetSharedControllerService getProposalBudgetSharedController() {
		return proposalBudgetSharedController;
	}

	public void setProposalBudgetSharedController(
			ProposalBudgetSharedControllerService proposalBudgetSharedController) {
		this.proposalBudgetSharedController = proposalBudgetSharedController;
	}

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

	public BudgetPrintService getBudgetPrintService() {
		return budgetPrintService;
	}

	public void setBudgetPrintService(BudgetPrintService budgetPrintService) {
		this.budgetPrintService = budgetPrintService;
	}
}
