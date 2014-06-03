package org.kuali.coeus.propdev.impl.location;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings({ "unchecked", "deprecation" })
@Controller
public class ProposalDevelopmentOrganizationLocationController extends ProposalDevelopmentControllerBase{
	
	
	public LookupableHelperService rolodexLookupableHelperService;		
	private BusinessObjectService businessObjectService;
	
	
	 @SuppressWarnings({ })
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performOrganizationalSearch")
	   public ModelAndView performOrganizationalSearch(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
		pdForm.getAddOrganizationHelper().getResults().clear();
		getRolodexLookupableHelperService().setBusinessObjectClass(Rolodex.class);
		Map<String, String> fieldValuesMap = null;
		List<Rolodex> results = null;
		businessObjectService = (BusinessObjectService) KcServiceLocator.getService("businessObjectService");
		fieldValuesMap = pdForm.getAddOrganizationHelper().getLookupFieldValues();
		Collection<String> values = null;
		values = fieldValuesMap.values();
		boolean res = false;
		if(values != null && values.size() > 0){
			for(String val : values){
				if(val != null){
					res = true;
				}
			}
		}
		if (res) {
			results = (List<Rolodex>) getRolodexLookupableHelperService().getSearchResults(fieldValuesMap);
		} else {
			results = (List<Rolodex>) businessObjectService.findAll(Rolodex.class);
		}
		pdForm.getAddOrganizationHelper().setResults(results);
		return getTransactionalDocumentControllerService().refresh(form, result, request, response);

	}

	public LookupableHelperService getRolodexLookupableHelperService() {		
		if (rolodexLookupableHelperService == null) {
			rolodexLookupableHelperService = KcServiceLocator.getService("rolodexLookupableHelperService");
        }       
		return rolodexLookupableHelperService;
	}

	public void setRolodexLookupableHelperService(
			LookupableHelperService rolodexLookupableHelperService) {
		rolodexLookupableHelperService = rolodexLookupableHelperService;
	}
	
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=addNewOrganization")
	   public ModelAndView addNewOrganization(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
	           HttpServletRequest request, HttpServletResponse response) throws Exception {	
			ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
			pdForm.getAddOrganizationHelper().getNewOrganizationFieldValues();
		 	
		 	ProposalSite performanceSite = new ProposalSite();		 	
		 	Rolodex rolodex= new Rolodex();		 	
		 	rolodex.setOrganization(pdForm.getAddOrganizationHelper().getNewOrganizationFieldValues().get("organization"));
		 	rolodex.setAddressLine1(pdForm.getAddOrganizationHelper().getNewOrganizationFieldValues().get("addressLine1"));
		 	rolodex.setAddressLine2(pdForm.getAddOrganizationHelper().getNewOrganizationFieldValues().get("addressLine2"));
		 	rolodex.setCity(pdForm.getAddOrganizationHelper().getNewOrganizationFieldValues().get("city"));
		 	rolodex.setState(pdForm.getAddOrganizationHelper().getNewOrganizationFieldValues().get("state"));
		 	rolodex.setPostalCode(pdForm.getAddOrganizationHelper().getNewOrganizationFieldValues().get("postalCode")); 	
		 	
		 	performanceSite.setRolodex(rolodex);
		 	performanceSite.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);
		 	pdForm.getDevelopmentProposal().addPerformanceSite(performanceSite);
		 	return getTransactionalDocumentControllerService().refresh(form, result, request, response);
	}	
	
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=addDistrict")
	   public ModelAndView addDistrict(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
	           HttpServletRequest request, HttpServletResponse response) throws Exception {	
			ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;			
			return null;
	}
	
	
	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=selectLine")
	   public ModelAndView selectLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
	           HttpServletRequest request, HttpServletResponse response) throws Exception {	
		 final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
	        final String selectedCollectionId = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_ID);

	        if (StringUtils.isBlank(selectedCollectionPath)) {
	            throw new RuntimeException("Selected collection was not set for delete line action, cannot delete line");
	        }

	        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);	        
	        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;	 
	        DevelopmentProposal developmentProposal = pdForm.getDevelopmentProposal();
	        ProposalSite performanceSite = new ProposalSite();
	        performanceSite.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);
	        Rolodex rolodex = null;
	        rolodex = pdForm.getAddOrganizationHelper().getResults().get(Integer.parseInt(selectedLine));
	        performanceSite.setRolodex(rolodex);
	        performanceSite.setLocationName(rolodex.getOrganization());
	        performanceSite.setRolodexId(rolodex.getRolodexId());
	        performanceSite.setOrganizationId(rolodex.getOwnedByUnit());
	        performanceSite.setDevelopmentProposal(developmentProposal);
	        pdForm.getDevelopmentProposal().addPerformanceSite(performanceSite);
	     
	        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}
}

