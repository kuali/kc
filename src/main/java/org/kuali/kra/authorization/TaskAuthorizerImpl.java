/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.authorization;

import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * Base implementation for all Task Authorizers.  The Unit and Proposal Authorization Services
 * and KRA Workflow Services are defined here because many derived classes make use of them.
 */
public abstract class TaskAuthorizerImpl implements TaskAuthorizer {

    private String taskName;
    private ProposalAuthorizationService proposalAuthorizationService;
    private UnitAuthorizationService unitAuthorizationService;
    protected KraWorkflowService kraWorkflowService;
    protected PersonService personService;
    
    /**
     * Set the name of the task.  Injected by the Spring Framework.
     * @param taskName the task's name
     */
    public final void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    /**
     * Set the Unit Authorization Service.  Injected by the Spring Framework.
     * @param unitAuthorizationService the Unit Authorization Service
     */
    public final void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }
    
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param proposalAuthorizationService the Proposal Authorization Service
     */
    public final void setProposalAuthorizationService(ProposalAuthorizationService proposalAuthorizationService) {
        this.proposalAuthorizationService = proposalAuthorizationService;
    }
    
    /**
     * Set the KRA Workflow Service.  Injected by the Spring Framework.
     * @param kraWorkflowService the KRA Workflow Service
     */
    public final void setKraWorkflowService(KraWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
    
    /**
     * Set the Person Service.  Injected by the Spring Framework.
     * @param personService the Person Service
     */
    public final void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#getTaskName()
     */
    public final String getTaskName() {
        return taskName;
    }
    
    /**
     * Does the given user has the permission for this proposal development document?
     * @param username the unique username of the user
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasUnitPermission(String username, String permissionName) {
        return unitAuthorizationService.hasPermission(username, permissionName);
    }
    
    protected final boolean hasUnitPermission(String username, String unitNumber, String permissionName) {
        return unitAuthorizationService.hasPermission(username, unitNumber, permissionName);
    }
    
    /**
     * Does the given user has the permission for this proposal development document?
     * @param username the unique username of the user
     * @param doc the proposal development document
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasProposalPermission(String username, ProposalDevelopmentDocument doc, String permissionName) {
        return proposalAuthorizationService.hasPermission(username, doc, permissionName);
    }
}
