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
package org.kuali.kra.proposaldevelopment.web.krad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.lookup.Lookupable;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.service.impl.LookupCriteriaGenerator;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentPersonnelController extends ProposalDevelopmentControllerBase {
   
    private LookupableHelperService kcPersonLookupableHelperService;
    private LookupCriteriaGenerator lookupCriteriaGenerator;
    private KeyPersonnelService keyPersonnelService;
    
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-PersonnelPage"})
    public ModelAndView navigateToPersonnel(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
        for (ProposalPerson person : propDevForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
            //workaround for the document associated with the OJB retrived dev prop not having a workflow doc.
            person.setDevelopmentProposal(propDevForm.getProposalDevelopmentDocument().getDevelopmentProposal());
            person.getQuestionnaireHelper().populateAnswers();
        }
        return getTransactionalDocumentControllerService().navigate(form, result, request, response);
    }    

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveProposalPersonnel")
   public ModelAndView savePersonnel(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
       for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
           for (AnswerHeader answerHeader : person.getQuestionnaireHelper().getAnswerHeaders()) {
               getLegacyDataAdapter().save(answerHeader);
           }
       }
       return super.save(pdForm, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=performPersonnelSearch")
   public ModelAndView performPersonnelSearch(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       form.getAddKeyPersonHelper().getResults().clear();
       if (StringUtils.equals(form.getAddKeyPersonHelper().getPersonType(), "E")) {
          List<KcPerson> results = (List<KcPerson>) getKcPersonLookupableHelperService().getSearchResults(form.getAddKeyPersonHelper().getLookupFieldValues());
          for (KcPerson person: results) {
              ProposalPerson newPerson = new ProposalPerson();
              newPerson.setPersonId(person.getPersonId());
              newPerson.setFullName(person.getFullName());
              newPerson.setUserName(person.getUserName());
              form.getAddKeyPersonHelper().getResults().add(newPerson);
          }
       } else {
           Collection<Rolodex> results = (Collection<Rolodex>) this.getLegacyDataAdapter().findMatching(Rolodex.class, form.getAddKeyPersonHelper().getLookupFieldValues());
           for (Rolodex rolodex : results) {
               ProposalPerson newPerson = new ProposalPerson();
               newPerson.setRolodexId(rolodex.getRolodexId());
               newPerson.setFullName(rolodex.getFullName());
               form.getAddKeyPersonHelper().getResults().add(newPerson);
           }
       }
       return getTransactionalDocumentControllerService().refresh(form, result, request, response);
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
       getKeyPersonnelService().addProposalPerson(newProposalPerson, form.getProposalDevelopmentDocument());
       form.getAddKeyPersonHelper().reset();
       return getTransactionalDocumentControllerService().refresh(form, result, request, response);
   }
   

    protected LookupableHelperService getKcPersonLookupableHelperService() {
        if (kcPersonLookupableHelperService == null) {
            kcPersonLookupableHelperService = KraServiceLocator.getService("kcPersonLookupableHelperService");
        }
        return kcPersonLookupableHelperService;
    }
    
    @Autowired
    @Qualifier("kcPersonLookupableHelperService")
    public void setKcPersonLookupableHelperService(LookupableHelperService kcPersonLookupableHelperService) {
        this.kcPersonLookupableHelperService = kcPersonLookupableHelperService;
    }

    protected KeyPersonnelService getKeyPersonnelService() {
        return keyPersonnelService;
    }

    @Autowired
    @Qualifier("keyPersonnelService")
    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }

    protected LookupCriteriaGenerator getLookupCriteriaGenerator() {
        return lookupCriteriaGenerator;
    }

    @Autowired
    @Qualifier("lookupCriteriaGenerator")
    public void setLookupCriteriaGenerator(LookupCriteriaGenerator lookupCriteriaGenerator) {
        this.lookupCriteriaGenerator = lookupCriteriaGenerator;
    }
   
}
