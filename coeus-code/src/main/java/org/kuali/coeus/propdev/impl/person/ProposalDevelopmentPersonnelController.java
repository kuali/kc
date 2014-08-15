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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.kra.infrastructure.PersonTypeConstants;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
public class ProposalDevelopmentPersonnelController extends ProposalDevelopmentControllerBase {
   
	@Autowired
	@Qualifier("kcPersonLookupableHelperService")
    private LookupableHelperService kcPersonLookupableHelperService;
	
    @Autowired
    @Qualifier("keyPersonnelService")
	private KeyPersonnelService keyPersonnelService;

    @Autowired
    @Qualifier("lookupService")
    private LookupService lookupService;

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-PersonnelPage"})
    public ModelAndView navigateToPersonnel(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
        propDevForm.getAddKeyPersonHelper().setPersonType(PersonTypeConstants.EMPLOYEE.getCode());
        for (ProposalPerson person : propDevForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
            //workaround for the document associated with the OJB retrived dev prop not having a workflow doc.
            person.setDevelopmentProposal(propDevForm.getProposalDevelopmentDocument().getDevelopmentProposal());
            person.getQuestionnaireHelper().populateAnswers();
        }
        sortPersonnelCollection(propDevForm.getDevelopmentProposal().getProposalPersons());
        return getNavigationControllerService().navigate(form);
    } 
    
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigateToPersonError"})
    public ModelAndView navigateToPersonError(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        form.setAjaxReturnType("update-page");
    	return navigateToPersonnel(form, result, request, response);
    } 
    
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=save", "pageId=PropDev-PersonnelPage"})
    public ModelAndView save(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
    	ModelAndView mv = super.save(form, result, request, response);
    	refreshPersonCertificaitonAnswerHeaders(pdForm);
    	return mv;
    }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=performPersonnelSearch")
   public ModelAndView performPersonnelSearch(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       form.getAddKeyPersonHelper().getResults().clear();
       for (Map.Entry<String, String> entry : form.getAddKeyPersonHelper().getLookupFieldValues().entrySet()) {
			if (entry.getValue() == null) {
				entry.setValue("");
			}
       }
       if (StringUtils.equals(form.getAddKeyPersonHelper().getPersonType(), PersonTypeConstants.EMPLOYEE.getCode())) {
          List<KcPerson> results = (List<KcPerson>) getKcPersonLookupableHelperService().getSearchResults(form.getAddKeyPersonHelper().getLookupFieldValues());
          for (KcPerson person: results) {
              if (!personAlreadyExists(person.getPersonId(),form.getDevelopmentProposal().getProposalPersons())){
                  ProposalPerson newPerson = new ProposalPerson();
                  newPerson.setPersonId(person.getPersonId());
                  newPerson.setFullName(person.getFullName());
                  newPerson.setUserName(person.getUserName());
                  form.getAddKeyPersonHelper().getResults().add(newPerson);
              }
          }
       } else {
           Collection<Rolodex> results = getLookupService().findCollectionBySearchHelper(Rolodex.class, form.getAddKeyPersonHelper().getLookupFieldValues(), Collections.EMPTY_LIST, false, 100);
           for (Rolodex rolodex : results) {
               if (!personAlreadyExists(rolodex.getRolodexId().toString(),form.getDevelopmentProposal().getProposalPersons())){
               ProposalPerson newPerson = new ProposalPerson();
               newPerson.setRolodexId(rolodex.getRolodexId());
               newPerson.setFullName(rolodex.getFullName());
               form.getAddKeyPersonHelper().getResults().add(newPerson);
               }
           }
       }
       return getRefreshControllerService().refresh(form);
   }

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addPerson")
   public ModelAndView addPerson(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalPerson newProposalPerson = null;
       for (ProposalPerson person : form.getAddKeyPersonHelper().getResults()) {
           if (person.isSelected()) {
               newProposalPerson = person;
               break;
           }
       }
       newProposalPerson.setProposalPersonRoleId(form.getAddKeyPersonHelper().getPersonRole());
       newProposalPerson.setProjectRole(form.getAddKeyPersonHelper().getKeyPersonProjectRole());
       getKeyPersonnelService().addProposalPerson(newProposalPerson, form.getProposalDevelopmentDocument());
       sortPersonnelCollection(form.getDevelopmentProposal().getProposalPersons());
       form.getAddKeyPersonHelper().reset();
       refreshPersonCertificaitonAnswerHeaders(form);
       return getRefreshControllerService().refresh(form);
   }

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-CreditAllocationPage"})
    public ModelAndView navigateToCreditAllocation(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = super.navigate(form,result,request,response);
        ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).populateCreditSplits(form);
        return mv;
    }

    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=save", "pageId=PropDev-CreditAllocationPage"})
    public ModelAndView creditAllocationSave(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView retVal = super.save(form, result, request, response);
        getKeyPersonnelService().populateDocument(form.getProposalDevelopmentDocument());
        return retVal;
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
	   saveAnswerHeaders(pdForm,request.getParameter(UifParameters.PAGE_ID));
	   ModelAndView mv = this.save(pdForm, result, request, response);
	   return mv;
   }

    protected boolean personAlreadyExists(String personId, List<ProposalPerson> existingPersons) {
        for (ProposalPerson existingPerson: existingPersons ) {
            if (personId.equals(existingPerson.getPersonId())) {
                return true;
            }
        }
        return false;
    }
    private void sortPersonnelCollection(List<ProposalPerson> proposalPersons ) {
        Collections.sort(proposalPersons, new Comparator<ProposalPerson>(){
            public int compare(ProposalPerson p1, ProposalPerson p2) {
                int c = convertRoleForSort(p1.getRole()).compareTo(convertRoleForSort(p2.getRole()));
                if (c == 0) {
                    c = p1.getLastName().compareTo(p2.getLastName());
                }
                if (c == 0) {
                    c = p1.getFirstName().compareTo(p2.getFirstName());
                }
                return c;
            }
        });
    }

    private String convertRoleForSort(PropAwardPersonRole role) {
        if (role.getCode().equals(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR)) {
            return "A";
        } else if (role.getCode().equals(PropAwardPersonRole.MULTI_PI)) {
            return "B";
        } else if (role.getCode().equals(PropAwardPersonRole.CO_INVESTIGATOR)) {
            return "C";
        } else if (role.getCode().equals(PropAwardPersonRole.KEY_PERSON)) {
            return "D";
        }
        return "Z";
    }

    protected LookupableHelperService getKcPersonLookupableHelperService() {
        return kcPersonLookupableHelperService;
    }
    
    public void setKcPersonLookupableHelperService(LookupableHelperService kcPersonLookupableHelperService) {
        this.kcPersonLookupableHelperService = kcPersonLookupableHelperService;
    }

    protected KeyPersonnelService getKeyPersonnelService() {
        return keyPersonnelService;
    }

    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }
   
    public void refreshPersonCertificaitonAnswerHeaders(ProposalDevelopmentDocumentForm pdForm) {
		for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
			ProposalPersonQuestionnaireHelper qh = new ProposalPersonQuestionnaireHelper(person);
			qh.populateAnswers();
			person.setQuestionnaireHelper(qh);
	    }
	}

    public LookupService getLookupService() {
        return lookupService;
    }

    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }
}