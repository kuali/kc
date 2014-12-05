package org.kuali.coeus.propdev.impl.budget.datavalidation;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetViewHelperServiceImpl;
import org.kuali.rice.krad.uif.UifParameters;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("proposalBudgetDataValidationController")
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetDataValidationController extends ProposalBudgetControllerBase {

    @Transactional @RequestMapping(params="methodToCall=validateData")
    public ModelAndView validateData(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        setDataValidation(form);
        return getModelAndViewService().showDialog("DataValidationSection", true, form);
    }

    @Transactional @RequestMapping(params="methodToCall=toggleValidation")
    public ModelAndView toggleValidation(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        form.setAuditActivated(!form.isAuditActivated());
        setDataValidation(form);
        return getRefreshControllerService().refresh(form);
    }

    protected void setDataValidation(ProposalBudgetForm form) {
        if(form.isAuditActivated()) {
            form.setDataValidationItems(((ProposalBudgetViewHelperServiceImpl)form.getViewHelperService()).populateDataValidation(form));
        }
    }

    @Transactional @RequestMapping(params="methodToCall=navigateToError")
    public ModelAndView navigateToError(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
    	((ProposalBudgetViewHelperServiceImpl)form.getViewHelperService()).applyBudgetAuditRules(form);
        form.setAjaxReturnType("update-page");
        String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
        if (StringUtils.equals(pageId,BudgetConstants.BudgetAuditRules.BUDGET_SETTINGS.getPageId())) {
        	return getModelAndViewService().showDialog(BudgetConstants.BudgetAuditRules.BUDGET_SETTINGS.getPageId(), true, form);
        }else {
            return getNavigationControllerService().navigate(form);
        }
    }
}
