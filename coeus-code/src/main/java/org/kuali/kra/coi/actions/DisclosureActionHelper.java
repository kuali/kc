/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.coi.certification.SubmitDisclosureAction;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisclosureActionHelper implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4602681870006425531L;
    
    private CoiUserRole newCoiUserRole;
    private CoiDisclosureForm coiDisclosureForm;
    private CoiDisclosureActionService coiDisclosureActionService;
    private CoiDisclosureService coiDisclosureService;

    private SubmitDisclosureAction submitDisclosureAction;
    private transient BusinessObjectService businessObjectService;
    private transient ParameterService parameterService;
    private transient TaskAuthorizationService taskAuthorizationService;
    private transient KcPersonService kcPersonService;
    private boolean approveDisclosure;
    private boolean maintainReviewers;

    public DisclosureActionHelper(CoiDisclosureForm coiDisclosureForm) {
        this.coiDisclosureForm = coiDisclosureForm;
        newCoiUserRole = new CoiUserRole();
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        parameterService = KcServiceLocator.getService(ParameterService.class);
        taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        coiDisclosureActionService = KcServiceLocator.getService(CoiDisclosureActionService.class);
        coiDisclosureService = KcServiceLocator.getService(CoiDisclosureService.class);
        submitDisclosureAction = new SubmitDisclosureAction(this);
        kcPersonService = KcServiceLocator.getService(KcPersonService.class);
    }

    public void prepareView() {
        this.initializePermissions(); 
        this.populateCoiUserRoleData();
    }
    
    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    private void initializePermissions() {
        approveDisclosure = canApproveCoiDisclosure();
        maintainReviewers = canMaintainReviewers();
    }

    private boolean canApproveCoiDisclosure() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.APPROVE_COI_DISCLOSURE, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean canMaintainReviewers() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.MAINTAIN_COI_REVIEWERS, getCoiDisclosure());
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

    public boolean getApproveDisclosure() {
        return approveDisclosure;
    }

    public void setApproveDisclosure(boolean approveDisclosure) {
        this.approveDisclosure = approveDisclosure;
    }

    public boolean isMaintainReviewers() {
        return maintainReviewers;
    }

    public void setMaintainReviewers(boolean maintainReviewers) {
        this.maintainReviewers = maintainReviewers;
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

    public CoiDisclosureActionService getCoiDisclosureActionService() {
        return coiDisclosureActionService;
    }
    
    public CoiDisclosureService getCoiDisclosureService() {
        return coiDisclosureService;
    }
    
    public CoiDisclosureForm getCoiDisclosureForm() {
        return coiDisclosureForm;
    }
    
    public void setCoiDisclosureForm(CoiDisclosureForm coiDisclosureForm) {
        this.coiDisclosureForm = coiDisclosureForm;
    }

    public void approveDisclosure(String coiDispositionCode) throws WorkflowException {
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
                 
        getCoiDisclosureActionService().approveDisclosure(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure(), coiDispositionCode);
        coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(
                  getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerHeaders(coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders());
        coiDisclosureForm.getDisclosureQuestionnaireHelper().resetHeaderLabels();
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerQuestionnaire(false);
    }

    
    /**
     * This method is used to disapprove a disclosure. Everything here is the same as an approval except the disclosure does not become 
     * the master.
     * @throws Exception
     */
    public void disapproveDisclosure(String coiDispositionCode) throws Exception {
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
            getCoiDisclosureActionService().disapproveDisclosure(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure(), coiDispositionCode);
            coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(
                    getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
    }

    public SubmitDisclosureAction getSubmitDisclosureAction() {
        return submitDisclosureAction;
    }

    private void populateCoiUserRoleData() {
        List<CoiUserRole> userRoles = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiUserRoles();
        syncUserRolesData(userRoles);
    }
    
    private void syncUserRolesData(List<CoiUserRole> userRoles) {
        if (CollectionUtils.isNotEmpty(userRoles)) {
            for (CoiUserRole userRole : userRoles) {
                userRole.setPerson(getKcPerson(userRole.getUserId()));
                userRole.setCoiReviewer(getCoiReviewer(userRole.getReviewerCode()));
            }
        }
    }
    
    public void setSubmitDisclosureAction(SubmitDisclosureAction submitDisclosureAction) {
        this.submitDisclosureAction = submitDisclosureAction;
    }

    public List<CoiUserRole> getCoiUserRoles() {
        this.populateCoiUserRoleData();
        if(!canMaintainReviewers()) {
            getCoiDisclosureActionService().tagUserRolesToCompleteReview(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiUserRoles());
        }
        return coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiUserRoles();
    }

    public KcPerson getKcPerson(String userName) {
        return getKcPersonService().getKcPersonByUserName(userName);
    }
    
    public CoiReviewer getCoiReviewer(String reviewerCode) {
        CoiReviewer coiReviewer = null;
        Map<String, String>fieldValues = new HashMap<String, String>();
        fieldValues.put("reviewerCode", reviewerCode);
        List<CoiReviewer> reviewers = (List<CoiReviewer>) getBusinessObjectService().findMatching(CoiReviewer.class, fieldValues);
        if (CollectionUtils.isNotEmpty(reviewers)) {
            coiReviewer = reviewers.get(0);
        }
        
        return coiReviewer;
    }
   

    public KcPersonService getKcPersonService() {        
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public boolean isDisclosureReviewComplete() {
        return getCoiDisclosureActionService().isDisclosureReviewComplete(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiUserRoles());
    }

    public boolean isDisclosureAssignedToReviewer() {
        return coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiUserRoles().size() > 0;
    }

    /**
     * This method returns true when the disclosure reporter and the current user are the same; otherwise false is returned.
     */
    public boolean isUserDisclosureReporter() {
        String currentUser = this.getUserIdentifier();
        String disclosureReporter = this.getCoiDisclosure().getDisclosureReporter().getReporter().getPersonId();
        if (StringUtils.isNotBlank(currentUser) && StringUtils.isNotBlank(disclosureReporter)){
            return currentUser.equalsIgnoreCase(disclosureReporter);
        }
        return false;
    }
    
    public CoiDispositionStatus getMaximumDispositionStatus() {
        return getCoiDisclosureService().calculateMaximumDispositionStatus(getCoiDisclosure());
    }
}
