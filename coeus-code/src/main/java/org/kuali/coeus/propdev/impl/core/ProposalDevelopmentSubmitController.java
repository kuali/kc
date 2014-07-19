package org.kuali.coeus.propdev.impl.core;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.controller.AuditActionHelper;
import org.kuali.coeus.sys.framework.controller.AuditActionHelper.ValidationState;
import org.kuali.coeus.sys.framework.controller.UifControllerService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.rules.rule.event.RouteDocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("deprecation")
@Controller
public class ProposalDevelopmentSubmitController extends
		ProposalDevelopmentControllerBase {
	
	@Autowired
	@Qualifier("uifControllerService")
	private UifControllerService uifControllerService;
	
	public UifControllerService getUifControllerService() {
		return uifControllerService;
	}
	public void setUifControllerService(UifControllerService uifControllerService) {
		this.uifControllerService = uifControllerService;
	}
	@Autowired
    @Qualifier("krmsRulesExecutionService")
    private KrmsRulesExecutionService krmsRulesExecutionService;
	
    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=deleteProposal")
    public ModelAndView deleteProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	getProposalDevelopmentService().deleteProposal(form.getProposalDevelopmentDocument());
        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAndExit")
    public  ModelAndView saveAndExit(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception{	
   		save(form,result,request,response);
   		return getTransactionalDocumentControllerService().returnToHub(form); 
	}
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=submitForReview")
    public  ModelAndView submitForReview(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception {	
    	form.setAuditActivated(true);
        ValidationState state = new AuditActionHelper().isValidSubmission(form, true);
        if (state != ValidationState.ERROR){
        
    	//if(!isValidateErrorData(form.getProposalDevelopmentDocument())){
    		 getUifControllerService().showDialog("PropDev-DataValidationSection", false, form);
        	form.getDevelopmentProposal().setSubmitFlag(true);
    		return getTransactionalDocumentControllerService().route(form, result, request, response);
    	}else{
    		GlobalVariables.getMessageMap().clearErrorMessages(); 
    		return getUifControllerService().showDialog("PropDev-DataValidationSection", true, form);
    	}
   } 
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=cancelProposal")
    public ModelAndView cancelProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
	   form.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.CANCELED);
	   return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }
   
  
   public KrmsRulesExecutionService getKrmsRulesExecutionService() {
       return krmsRulesExecutionService;
   }

   public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
       this.krmsRulesExecutionService = krmsRulesExecutionService;
   }
   /*public boolean isValidateErrorData(ProposalDevelopmentDocument document ){
       int count=0;
       KNSGlobalVariables.getAuditErrorMap().clear();
       KRADServiceLocatorWeb.getKualiRuleService().applyRules(new RouteDocumentEvent(document));
       for (Entry<String,AuditCluster> entry : KNSGlobalVariables.getAuditErrorMap().entrySet()) {
    	   AuditCluster auditCluster = (AuditCluster) entry.getValue();
           List<AuditError> auditErrors = auditCluster.getAuditErrorList();
           count+=auditErrors.size();
       }
       List<Map<String,String>> krmsErrors = getKrmsRulesExecutionService().processUnitKcValidations(document.getLeadUnitNumber(),document);
       for (Map<String,String> error: krmsErrors) {        
       	if (error.get(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE).equals("E")) 
       		count++;
       }
		return count >0;
}   */
   
}