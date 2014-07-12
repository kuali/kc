package org.kuali.coeus.propdev.impl.budget.core;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetCommonController extends ProposalBudgetControllerBase {
	
	@Autowired
	@Qualifier("uifExportControllerService")
	private UifExportControllerService uifExportControllerService;	

	@MethodAccessible
	@RequestMapping(params="methodToCall=defaultMapping")
	public ModelAndView defaultMapping(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getUifControllerService().defaultMapping(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=start")
	public ModelAndView start(@RequestParam("budgetId") String budgetId, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		form.setBudget(getDataObjectService().findUnique(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("budgetId", Long.valueOf(budgetId))).build()));
		return getUifControllerService().getUIFModelAndViewWithInit(form, "PropBudget-DefaultView");
	}
	
	@MethodAccessible
	@RequestMapping(params = "methodToCall=navigate")
	public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		return super.navigate(form);
	}

	public void checkViewAuthorization(@ModelAttribute("KualiForm") ProposalBudgetForm form, String methodToCall) throws AuthorizationException {
		getUifControllerService().checkViewAuthorization(form, methodToCall);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=sessionTimeout")
	public ModelAndView sessionTimeout(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getUifControllerService().sessionTimeout(form, result, request, response);
	}

	@RequestMapping(params="methodToCall=addLine")
	public ModelAndView addLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifControllerService().addLine(form, result, request, response);
	}

	@RequestMapping(params = "methodToCall=addBlankLine")
	public ModelAndView addBlankLine(@ModelAttribute("KualiForm") ProposalBudgetForm uifForm, HttpServletRequest request,
			HttpServletResponse response) {
		return getUifControllerService().addBlankLine(uifForm, request, response);
	}

	@RequestMapping(params="methodToCall=saveLine")
	public ModelAndView saveLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifControllerService().saveLine(form, result, request, response);
	}

	@RequestMapping(params="methodToCall=deleteLine")
	public ModelAndView deleteLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getUifControllerService().deleteLine(form, result, request, response);
	}

	@RequestMapping(params="methodToCall=back")
	public ModelAndView back(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifControllerService().back(form, result, request, response);
	}

	@RequestMapping(params="methodToCall=returnToPrevious")
	public ModelAndView returnToPrevious(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return getUifControllerService().returnToPrevious(form);
	}

	@RequestMapping(params="methodToCall=returnToHub")
	public ModelAndView returnToHub(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return getUifControllerService().returnToHub(form);
	}

	@RequestMapping(params="methodToCall=returnToHistory")
	public ModelAndView returnToHistory(@ModelAttribute("KualiForm") ProposalBudgetForm form, boolean homeFlag) {
		return getUifControllerService().returnToHistory(form, homeFlag);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=refresh")
	public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getUifControllerService().refresh(form, result, request, response);
	}

	@RequestMapping(params="methodToCall=performLookup")
	public ModelAndView performLookup(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getUifControllerService().performLookup(form, result, request, response);
	}

	@RequestMapping(params="methodToCall=checkForm")
	public ModelAndView checkForm(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifControllerService().checkForm(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=performFieldSuggest")
	public @ResponseBody AttributeQueryResult performFieldSuggest(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getUifControllerService().performFieldSuggest(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=performFieldQuery")
	public @ResponseBody AttributeQueryResult performFieldQuery(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getUifControllerService().performFieldQuery(form, result, request, response);
	}

	@RequestMapping(params="methodToCall=tableCsvRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableCsvRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return uifExportControllerService.tableCsvRetrieval(form, request, response);
	}

	@RequestMapping(params="methodToCall=tableXlsRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXlsRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return uifExportControllerService.tableXlsRetrieval(form, request, response);
	}

	@RequestMapping(params="methodToCall=tableXmlRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXmlRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return uifExportControllerService.tableXmlRetrieval(form, request, response);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=tableJsonRetrieval")
	public ModelAndView tableJsonRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return uifExportControllerService.tableJsonRetrieval(form, result, request, response);
	}
	
    @MethodAccessible
    @RequestMapping(params = "methodToCall=retrieveCollectionPage")
	public ModelAndView retrieveCollectionPage(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return uifExportControllerService.retrieveCollectionPage(form, result, request, response);
	}

	public UifExportControllerService getUifExportControllerService() {
		return uifExportControllerService;
	}

	public void setUifExportControllerService(
			UifExportControllerService uifExportControllerService) {
		this.uifExportControllerService = uifExportControllerService;
	}	
}
