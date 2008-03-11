/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.NarrativeTask;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.service.PersonService;

/**
 * The Narrative Read Authorizer checks to see if the user has 
 * the necessary permission to read/view a specific narrative.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NarrativeReadAuthorizer extends ProposalAuthorizer {

    private static final String ACTION_NAME = "AbstractsAttachments";
    private String taskName;
    
    /**
     * Set the name of the narrative task.  Injected by the Spring Framework.
     * @param taskName the name of the task
     */
    public void setTask(String taskName) {
        this.taskName = taskName;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer#isResponsible(org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask)
     */
    public boolean isResponsible(ProposalTask task) {
        return StringUtils.equals(ACTION_NAME, task.getActionName()) &&
               StringUtils.equals(taskName, task.getTaskName());
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer#isAuthorized(org.kuali.core.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        
        NarrativeTask narrativeTask = (NarrativeTask) task;
        
        PersonService personService = KraServiceLocator.getService(PersonService.class);
        ProposalAuthorizationService auth = KraServiceLocator.getService(ProposalAuthorizationService.class);
        
        ProposalDevelopmentDocument doc = task.getProposalDevelopmentDocument();
       
        // First, the user must have the VIEW_NARRATIVE permission.  This is really
        // a sanity check.  If they have the VIEW or MODIFY_NARRATIVE_RIGHT, then they are
        // required to have the VIEW_NARRATIVE permission.
        
        boolean hasPermission = false;
        if (auth.hasPermission(username, doc, PermissionConstants.VIEW_NARRATIVE)) {
            Narrative narrative = narrativeTask.getNarrative();
            List<NarrativeUserRights> userRightsList = narrative.getNarrativeUserRights();
            for (NarrativeUserRights userRights : userRightsList) {
                Person person = personService.getPerson(userRights.getUserId());
                if (StringUtils.equals(username, person.getUserName())) {
                    if (StringUtils.equals(userRights.getAccessType(), NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType()) ||
                        StringUtils.equals(userRights.getAccessType(), NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType())) {
                       
                        hasPermission = true;
                    }
                    break;
                }
            }
        }
        return hasPermission;
    }
}
