package org.kuali.coeus.propdev.impl.basic;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentCoreController extends ProposalDevelopmentControllerBase {

	@Autowired
	@Qualifier("uifExportControllerService")
	private UifExportControllerService uifExportControllerService;

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=defaultMapping")
	public ModelAndView defaultMapping(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().defaultMapping(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=start")
	public ModelAndView start(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getTransactionalDocumentControllerService().start(form, request, response);
	}

	public void checkViewAuthorization(@ModelAttribute("KualiForm") DocumentFormBase form, String methodToCall) throws AuthorizationException {
		getTransactionalDocumentControllerService().checkViewAuthorization(form, methodToCall);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=reload")
	public ModelAndView reload(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getTransactionalDocumentControllerService().reload(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=sessionTimeout")
	public ModelAndView sessionTimeout(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().sessionTimeout(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=addLine")
	public ModelAndView addLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getTransactionalDocumentControllerService().addLine(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=complete")
	public ModelAndView complete(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getTransactionalDocumentControllerService().complete(form, result, request, response);
	}

	@RequestMapping(value ="/proposalDevelopment", params = "methodToCall=addBlankLine")
	public ModelAndView addBlankLine(@ModelAttribute("KualiForm") UifFormBase uifForm, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().addBlankLine(uifForm, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveLine")
	public ModelAndView saveLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getTransactionalDocumentControllerService().saveLine(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteLine")
	public ModelAndView deleteLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().deleteLine(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=back")
	public ModelAndView back(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getTransactionalDocumentControllerService().back(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToPrevious")
	public ModelAndView returnToPrevious(@ModelAttribute("KualiForm") UifFormBase form) {
		return getTransactionalDocumentControllerService().returnToPrevious(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToHub")
	public ModelAndView returnToHub(@ModelAttribute("KualiForm") UifFormBase form) {
		return getTransactionalDocumentControllerService().returnToHub(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToHistory")
	public ModelAndView returnToHistory(@ModelAttribute("KualiForm") UifFormBase form, boolean homeFlag) {
		return getTransactionalDocumentControllerService().returnToHistory(form, homeFlag);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=navigate")
	public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return super.navigate(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=refresh")
	public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getTransactionalDocumentControllerService().refresh(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performLookup")
	public ModelAndView performLookup(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().performLookup(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=checkForm")
	public ModelAndView checkForm(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getTransactionalDocumentControllerService().checkForm(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performFieldSuggest")
	public @ResponseBody AttributeQueryResult performFieldSuggest(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().performFieldSuggest(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=downloadAttachment")
	public ModelAndView downloadAttachment(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws ServletRequestBindingException, FileNotFoundException, IOException {
		return getTransactionalDocumentControllerService().downloadAttachment(form, result, request, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performFieldQuery")
	public @ResponseBody AttributeQueryResult performFieldQuery(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().performFieldQuery(form, result, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableCsvRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableCsvRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return uifExportControllerService.tableCsvRetrieval(form, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableXlsRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXlsRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return uifExportControllerService.tableXlsRetrieval(form, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableXmlRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXmlRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return uifExportControllerService.tableXmlRetrieval(form, request, response);
	}

	@MethodAccessible
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableJsonRetrieval")
	public ModelAndView tableJsonRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return uifExportControllerService.tableJsonRetrieval(form, result, request, response);
	}
	
    @MethodAccessible
    @RequestMapping(params = "methodToCall=retrieveCollectionPage")
	public ModelAndView retrieveCollectionPage(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return uifExportControllerService.retrieveCollectionPage(form, result, request, response);
	}
    
    @MethodAccessible
    @RequestMapping(params = "methodToCall=supervisorFunctions")
    public ModelAndView supervisorFunctions(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	return getTransactionalDocumentControllerService().supervisorFunctions(form, result, request, response);
    }
	
	protected UifExportControllerService getUifExportControllerService() {
		return uifExportControllerService;
	}

	public void setUifExportControllerService(
			UifExportControllerService uifExportControllerService) {
		this.uifExportControllerService = uifExportControllerService;
	}

}
