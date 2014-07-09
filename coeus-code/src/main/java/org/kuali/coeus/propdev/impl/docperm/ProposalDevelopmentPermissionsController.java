/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

/**
 * The ProposalDevelopmentPermissionsAction responds to user events from the
 * Permissions web page.  
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Controller
public class ProposalDevelopmentPermissionsController extends ProposalDevelopmentControllerBase {
    
    private ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService;
    private PersonService personService;

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=savePermissions")
    public ModelAndView savePermissions(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = getTransactionalDocumentControllerService().docHandler(form, result, request, response);
        ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
        ProposalDevelopmentPermissionsHelper helper = propDevForm.getPermissionsHelper();

        ProposalDevelopmentDocument doc = propDevForm.getProposalDevelopmentDocument();
        getProposalDevelopmentPermissionsService().savePermissions(doc, helper.getOldUserRoles(),
                helper.getWorkingUserRoles());
        helper.setOldUserRoles(helper.getWorkingUserRoles());  // reset for next time

        return modelAndView;

    }
    
    
    protected ProposalDevelopmentPermissionsService getProposalDevelopmentPermissionsService() {
        if (proposalDevelopmentPermissionsService == null) {
            proposalDevelopmentPermissionsService = KcServiceLocator.getService(ProposalDevelopmentPermissionsService.class);
        }
        return proposalDevelopmentPermissionsService;
    }



    public void setProposalDevelopmentPermissionsService(ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService) {
        this.proposalDevelopmentPermissionsService = proposalDevelopmentPermissionsService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {

        this.personService = personService;
    }
}
