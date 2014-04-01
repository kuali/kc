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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentQuestionnaireController extends ProposalDevelopmentControllerBase {

        @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-QuestionnairePage"})
        public ModelAndView navigateToQuestionnaire(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
            ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
            propDevForm.getQuestionnaireHelper().prepareView();
            propDevForm.getQuestionnaireHelper().populateAnswers();
            return getTransactionalDocumentControllerService().navigate(form, result, request, response);
        }    
        
       @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveQuestionnaire")
       public ModelAndView saveQuestionnaire(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
               HttpServletRequest request, HttpServletResponse response) throws Exception {
           ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
           pdForm.getQuestionnaireHelper().preSave();
           getLegacyDataAdapter().save(pdForm.getQuestionnaireHelper().getAnswerHeaders());
           pdForm.getQuestionnaireHelper().populateAnswers();
           return super.save(pdForm, result, request, response);
       }


}
