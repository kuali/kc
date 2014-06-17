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

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.specialreview.impl.rule.event.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReviewExemption;
import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.contrib.krad.web.bind.UifCalendarEditor;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentHomeController extends ProposalDevelopmentControllerBase {

   @MethodAccessible
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createProposal")
   public ModelAndView createProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {

       ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
       initialSave(proposalDevelopmentDocument);
       save(form, result, request, response);
       initializeProposalUsers(proposalDevelopmentDocument);
       //setting to null so the previous page id(PropDev-InitiatePage) doesn't override the default 
       form.setPageId(null);
       return getTransactionalDocumentControllerService().getUIFModelAndViewWithInit(form, PROPDEV_DEFAULT_VIEW_ID);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=clearAnswers")
   public ModelAndView clearAnswers(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
	   ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
	   String personNumber = pdForm.getActionParamaterValue("personNumber");
	   for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
		   if (StringUtils.equals(personNumber, person.getProposalPersonNumber().toString())) {
			   //get the certification questions
			   AnswerHeader ah = person.getQuestionnaireHelper().getAnswerHeaders().get(0);
			   for (Answer answer : ah.getAnswers()) {
				   answer.setAnswer(null);
			   }
		   }
	   }
	   saveAnswerHeaders(pdForm);
	   ModelAndView mv = this.save(pdForm, result, request, response);
	   return mv;
   }
   
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=save")
   public ModelAndView save(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
	   ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
	   saveAnswerHeaders(pdForm);
	   ModelAndView mv = super.save(form, result, request, response);
	   //rebuild the questionnaire, and other non-JPAed docs so it displays correctly
	   refreshAnswerHeaders(pdForm);
	   return mv;
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
   
   @MethodAccessible
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

   @MethodAccessible
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=docHandler")
   public ModelAndView docHandler(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
           HttpServletResponse response) throws Exception {
       ModelAndView modelAndView = getTransactionalDocumentControllerService().docHandler(form, result, request, response);
       ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
       propDevForm.initialize();
       propDevForm.getCustomDataHelper().prepareCustomData();
       return modelAndView;
   }
   
   @InitBinder
   protected void initBinder(WebDataBinder binder) throws Exception {
	   binder.registerCustomEditor(List.class, "document.developmentProposal.propScienceKeywords", new PropScienceKeywordEditor());
	   binder.registerCustomEditor(List.class, "document.developmentProposal.propSpecialReviews.specialReviewExemptions", new PropSpecialReviewExemptionTypeEditor());
	   binder.registerCustomEditor(Calendar.class, new UifCalendarEditor());
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

   protected class PropSpecialReviewExemptionTypeEditor extends CustomCollectionEditor {
		public PropSpecialReviewExemptionTypeEditor() {
			super(List.class);
		}

		protected Object convertElement(Object element) {
			if (element instanceof String) {
				return new ProposalSpecialReviewExemption(null, getExemptionType(element));
			}
			return null;
		}
	}
   
   protected ExemptionType getExemptionType(Object element) {
	   return (ExemptionType) getDataObjectService().findUnique(ExemptionType.class, QueryByCriteria.Builder.forAttribute("exemptionTypeCode", element).build());
   }
   
}
