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
package org.kuali.kra.coi.actions;

import java.io.Serializable;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.CoiReviewer;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;

public class DisclosureActionHelper implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4602681870006425531L;
    
    private CoiUserRole newCoiUserRole;
    private CoiDisclosureForm coiDisclosureForm;
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private TaskAuthorizationService taskAuthorizationService;
    private boolean disapproveDisclosure;
    private boolean approveDisclosure;

    public DisclosureActionHelper(CoiDisclosureForm coiDisclosureForm) {
        this.coiDisclosureForm = coiDisclosureForm;
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        parameterService = KraServiceLocator.getService(ParameterService.class);
        taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    public void prepareView() {
        this.initializePermissions();      
    }
    
    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    private void initializePermissions() {
        approveDisclosure = canApproveCoiDisclosure();
        disapproveDisclosure = canDisapproveCoiDisclosure();
    }
    

    private boolean canDisapproveCoiDisclosure() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.DISAPPROVE_COI_DISCLOSURE, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    private boolean canApproveCoiDisclosure() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.APPROVE_COI_DISCLOSURE, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    public CoiDisclosure getCoiDisclosure() {

        if (this.coiDisclosureForm.getDocument() == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (this.coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure() == null) {
            throw new IllegalArgumentException("the coiDisclosure is null");
        }
        return this.coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return taskAuthorizationService;
    }
    
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    public boolean getDisapproveDisclosure() {
        return disapproveDisclosure;
    }

    public void setDisapproveDisclosure(boolean disapproveDisclosure) {
        this.disapproveDisclosure = disapproveDisclosure;
    }

    public boolean getApproveDisclosure() {
        return approveDisclosure;
    }

    public void setApproveDisclosure(boolean approveDisclosure) {
        this.approveDisclosure = approveDisclosure;
    }

    public CoiUserRole getNewCoiUserRole() {
        return newCoiUserRole;
    }

    public void setNewCoiUserRole(CoiUserRole newCoiUserRole) {
        this.newCoiUserRole = newCoiUserRole;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public CoiDisclosureForm getCoiDisclosureForm() {
        return coiDisclosureForm;
    }
    
    public void setCoiDisclosureForm(CoiDisclosureForm coiDisclosureForm) {
        this.coiDisclosureForm = coiDisclosureForm;
    }
    

}
