package org.kuali.coeus.propdev.impl.basic;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.kuali.coeus.propdev.impl.print.CurrentOrPendingOrSelectedReportPrint;
import org.kuali.coeus.propdev.impl.print.ProposalDevelopmentPrintingService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
 
@Controller
public class ProposalDevelopmentCoreController extends ProposalDevelopmentControllerBase {
	
	@Lazy
	@Autowired
	@Qualifier("proposalDevelopmentPrintingService")
	protected ProposalDevelopmentPrintingService proposalDevelopmentPrintingService;

	private CurrentOrPendingOrSelectedReportPrint currentOrPendingOrSelectedReportPrint;

	public CurrentOrPendingOrSelectedReportPrint getCurrentOrPendingOrSelectedReportPrint() {
		if( currentOrPendingOrSelectedReportPrint == null) {
			currentOrPendingOrSelectedReportPrint = new CurrentOrPendingOrSelectedReportPrint();
		}
		return currentOrPendingOrSelectedReportPrint;
	}

	public void setCurrentOrPendingOrSelectedReportPrint(
			CurrentOrPendingOrSelectedReportPrint currentOrPendingOrSelectedReportPrint) {
		this.currentOrPendingOrSelectedReportPrint = currentOrPendingOrSelectedReportPrint;
	}

	public ProposalDevelopmentPrintingService getProposalDevelopmentPrintingService() {
		if (proposalDevelopmentPrintingService == null) {
			proposalDevelopmentPrintingService = KcServiceLocator
					.getService(ProposalDevelopmentPrintingService.class);
		}
		return proposalDevelopmentPrintingService;
	}

	public void setProposalDevelopmentPrintingService(
			ProposalDevelopmentPrintingService proposalDevelopmentPrintingService) {
		this.proposalDevelopmentPrintingService = proposalDevelopmentPrintingService;
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=defaultMapping")
	public ModelAndView defaultMapping(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().start(form);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=start")
	public ModelAndView start(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getTransactionalDocumentControllerService().start(form);
	}

	public void checkViewAuthorization(@ModelAttribute("KualiForm") DocumentFormBase form, String methodToCall) throws AuthorizationException {
		getTransactionalDocumentControllerService().checkViewAuthorization(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=reload")
	public ModelAndView reload(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getTransactionalDocumentControllerService().reload(form);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=sessionTimeout")
	public ModelAndView sessionTimeout(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().sessionTimeout(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=addLine")
	public ModelAndView addLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getCollectionControllerService().addLine(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=complete")
	public ModelAndView complete(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getTransactionalDocumentControllerService().complete(form);
	}

	@RequestMapping(value ="/proposalDevelopment", params = "methodToCall=addBlankLine")
	public ModelAndView addBlankLine(@ModelAttribute("KualiForm") UifFormBase uifForm, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().addBlankLine(uifForm);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveLine")
	public ModelAndView saveLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getCollectionControllerService().saveLine(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteLine")
	public ModelAndView deleteLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().deleteLine(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=back")
	public ModelAndView back(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getNavigationControllerService().back(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToPrevious")
	public ModelAndView returnToPrevious(@ModelAttribute("KualiForm") UifFormBase form) {
		return getNavigationControllerService().returnToPrevious(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToHub")
	public ModelAndView returnToHub(@ModelAttribute("KualiForm") UifFormBase form) {
		return getNavigationControllerService().returnToHub(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToHistory")
	public ModelAndView returnToHistory(@ModelAttribute("KualiForm") UifFormBase form, boolean homeFlag) {
		return getNavigationControllerService().returnToHistory(form, false, homeFlag, false);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=refresh")
	public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getRefreshControllerService().refresh(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performLookup")
	public ModelAndView performLookup(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performLookup(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=checkForm")
	public ModelAndView checkForm(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getModelAndViewService().checkForm(form);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performFieldSuggest")
	public @ResponseBody AttributeQueryResult performFieldSuggest(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldSuggest(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=downloadAttachment")
	public ModelAndView downloadAttachment(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws ServletRequestBindingException, FileNotFoundException, IOException {
		return getTransactionalDocumentControllerService().downloadAttachment(form, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performFieldQuery")
	public @ResponseBody AttributeQueryResult performFieldQuery(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldQuery(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableCsvRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableCsvRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableCsvRetrieval(form, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableXlsRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXlsRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXlsRetrieval(form, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableXmlRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXmlRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXmlRetrieval(form, request, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableJsonRetrieval")
	public ModelAndView tableJsonRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().tableJsonRetrieval(form);
	}
	
    @MethodAccessible
    @RequestMapping(params = "methodToCall=retrieveCollectionPage")
	public ModelAndView retrieveCollectionPage(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getCollectionControllerService().retrieveCollectionPage(form);
	}
    
    @MethodAccessible
    @RequestMapping(params = "methodToCall=supervisorFunctions")
    public ModelAndView supervisorFunctions(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	return getTransactionalDocumentControllerService().supervisorFunctions(form);
    }
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=printCurrentReport"})
    public ModelAndView printCurrentReport(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        getCurrentOrPendingOrSelectedReportPrint().printCurrentReportPdf(form, request, response);
        return getModelAndViewService().getModelAndView(form);

    }
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=printPendingReport"})
    public ModelAndView printPendingReport(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	getCurrentOrPendingOrSelectedReportPrint().printPendingReportPdf(form, request, response);
        return getModelAndViewService().getModelAndView(form);
    }
	@RequestMapping(value = "/proposalDevelopment", params={"methodToCall=printSelected"})
    public ModelAndView printSelected(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		getCurrentOrPendingOrSelectedReportPrint().printSelectedTemplates(form, request, response, getProposalDevelopmentPrintingService());
        return getModelAndViewService().getModelAndView(form);

    }
    
    
}
