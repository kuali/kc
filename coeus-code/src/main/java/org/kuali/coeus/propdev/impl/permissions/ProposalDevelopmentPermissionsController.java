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
package org.kuali.coeus.propdev.impl.permissions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentPermissionsController extends ProposalDevelopmentControllerBase {

   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addPermissionsUser")
   public ModelAndView addPermissionsUser(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ModelAndView modelAndView = getTransactionalDocumentControllerService().docHandler(form, result, request, response);
       ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
System.err.println("\npopupAccessC called...");
	   return modelAndView;
   }
   
}
