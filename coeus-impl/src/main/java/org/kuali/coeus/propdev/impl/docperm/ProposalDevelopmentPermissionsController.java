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

import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.uif.UifParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The ProposalDevelopmentPermissionsController responds to user events from the
 * Permissions web page.  
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Controller
public class ProposalDevelopmentPermissionsController extends ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("proposalDevelopmentPermissionsService")
    private ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("proposalRoleService")
    private ProposalRoleService proposalRoleService;

    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=savePermission")
    public ModelAndView savePermission(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {

        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        boolean success = getProposalDevelopmentPermissionsService().validateUpdatePermissions(form.getProposalDevelopmentDocument(), form.getWorkingUserRoles(),
                form.getWorkingUserRoles().get(Integer.parseInt(selectedLine)));

        if (success){
            getProposalDevelopmentPermissionsService().processUpdatePermission(form.getProposalDevelopmentDocument(),form.getWorkingUserRoles().get(Integer.parseInt(selectedLine)));

            if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)){
                form.getEditableCollectionLines().get(selectedCollectionPath).remove(selectedLine);
            }
            form.setEvaluateFlagsAndModes(true);
        }

        return getRefreshControllerService().refresh(form);
    }


    protected ProposalDevelopmentPermissionsService getProposalDevelopmentPermissionsService() {
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

    public ProposalRoleService getProposalRoleService() {
        return proposalRoleService;
    }

    public void setProposalRoleService(ProposalRoleService proposalRoleService) {
        this.proposalRoleService = proposalRoleService;
    }
}
