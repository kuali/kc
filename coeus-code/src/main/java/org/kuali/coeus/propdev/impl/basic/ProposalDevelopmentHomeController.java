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
package org.kuali.coeus.propdev.impl.basic;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.specialreview.impl.rule.event.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.web.krad.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
public class ProposalDevelopmentHomeController extends ProposalDevelopmentControllerBase {

   @Autowired
   @Qualifier("uifExportControllerService")
   private UifExportControllerService uifExportControllerService;

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createProposal")
   public ModelAndView createProposal(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {

       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
       initialSave(proposalDevelopmentDocument);
       save(form, result, request, response);
       initializeProposalUsers(proposalDevelopmentDocument);

       //hack for rice upgrade m5
       form.setPageId("PropDev-DetailsPage");

       return getTransactionalDocumentControllerService().getUIFModelAndViewWithInit(form, PROPDEV_DEFAULT_VIEW_ID);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=save")
   public ModelAndView save(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       return super.save(form, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=saveAndContinue")
   public ModelAndView saveAndContinue(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       List<Action> actions = form.getOrderedNavigationActions();
       int indexOfCurrentAction = form.findIndexOfPageId(actions);
       if (indexOfCurrentAction == -1) {
           indexOfCurrentAction = 0;
       }
       if (indexOfCurrentAction < actions.size()-1) {
           form.getActionParameters().put(UifParameters.NAVIGATE_TO_PAGE_ID, actions.get(indexOfCurrentAction+1).getNavigateToPageId());
       }
       return save(form, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=previousPage")
   public ModelAndView previousPage(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       List<Action> actions = form.getOrderedNavigationActions();
       int indexOfCurrentAction = form.findIndexOfPageId(actions);
       if (indexOfCurrentAction != -1 && indexOfCurrentAction > 0) {
           form.getActionParameters().put(UifParameters.NAVIGATE_TO_PAGE_ID, actions.get(indexOfCurrentAction-1).getNavigateToPageId());
       }
       return navigate(form, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=getSponsor")
   public @ResponseBody Sponsor sponsorByCode(@RequestParam("sponsorCode") String sponsorCode) {
       Sponsor sponsor = getLegacyDataAdapter().findBySinglePrimaryKey(Sponsor.class, sponsorCode);
       //clear references that are likely circular
       if (sponsor != null) {
           sponsor.setUnit(null);
           sponsor.setRolodex(null);
       }
       return sponsor;
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=linkProtocol")
   public ModelAndView linkProtocol(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
       for (ProposalSpecialReview specialReview : proposalDevelopmentDocument.getDevelopmentProposal().getPropSpecialReviews()) {
           if (!specialReview.isLinkedToProtocol()) {
               pdForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
           }
       }
       return getTransactionalDocumentControllerService().getUIFModelAndView(form);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveDetails")
   public ModelAndView saveDetails(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       return super.save(form, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveOpportunity")
   public ModelAndView saveOpportunity(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       return super.save(form, result, request, response);
   }      
   
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveCompliance")
   public ModelAndView saveComplaince(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        return super.save(pdForm, result, request, response, SaveDocumentSpecialReviewEvent.class);
   }   
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveProposalAttachments")
   public ModelAndView saveProposalAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveInternalAttachments")
   public ModelAndView saveInternalAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }   
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=savePersonnelAttachments")
   public ModelAndView savePersonnelAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAbstracts")
   public ModelAndView saveAbstracts(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveNotes")
   public ModelAndView saveNotes(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=docHandler")
   public ModelAndView docHandler(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
           HttpServletResponse response) throws Exception {
       ModelAndView modelAndView = getTransactionalDocumentControllerService().docHandler(form, result, request, response);
       ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
       propDevForm.initialize();
       return modelAndView;
   }
   
   @InitBinder
   protected void initBinder(WebDataBinder binder) throws Exception {
	   binder.registerCustomEditor(List.class, "document.developmentProposal.propScienceKeywords", new PropScienceKeywordEditor());
   }
   	  
   protected class PropScienceKeywordEditor extends CustomCollectionEditor {
		public PropScienceKeywordEditor() {
			super(List.class);
		}

		protected Object convertElement(Object element) {
			if (element instanceof String) {
				return new PropScienceKeyword(null, getScienceKeyword(element));
			}
			return null;
		}
	}
   
   protected ScienceKeyword getScienceKeyword(Object element) {
	   return (ScienceKeyword) getDataObjectService().findUnique(ScienceKeyword.class, QueryByCriteria.Builder.forAttribute("scienceKeywordCode", element).build());
   }

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=defaultMapping")
   public ModelAndView defaultMapping(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
           HttpServletResponse response) {
       return getTransactionalDocumentControllerService().defaultMapping(form, result, request, response);
   }

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
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=navigate")
   public ModelAndView navigate(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
       return getTransactionalDocumentControllerService().navigate(form, result, request, response);
   }

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

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=performFieldQuery")
   public @ResponseBody AttributeQueryResult performFieldQuery(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
           HttpServletResponse response) {
       return getTransactionalDocumentControllerService().performFieldQuery(form, result, request, response);
   }

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnFromLightbox")
   public ModelAndView returnFromLightbox(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
           HttpServletResponse response) {
       return getTransactionalDocumentControllerService().returnFromLightbox(form, result, request, response);
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

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableJsonRetrieval")
   public ModelAndView tableJsonRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
           HttpServletResponse response) {
       return uifExportControllerService.tableJsonRetrieval(form, result, request, response);
   }

    public void setUifExportControllerService(UifExportControllerService uifExportControllerService) {
        this.uifExportControllerService = uifExportControllerService;
    }
}
