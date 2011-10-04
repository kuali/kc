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
package org.kuali.kra.committee.web.struts.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.WebUtils;

/**
 * The CommitteeHelper corresponds to the Committee tab web page.
 */
public class CommitteeHelper implements Serializable {
    
    private static final long serialVersionUID = 1744329032797755384L;

    private static final String BATCH_CORRESPONDENCE_PANEL_TITLE = "Batch Correspondence";
    
    private CommitteeForm committeeForm;
    private boolean modifyCommittee = false;
    private CommitteeMembership newCommitteeMembership;
    private List<CommitteeMembershipRole> newCommitteeMembershipRoles;
    private List<CommitteeMembershipExpertise> newCommitteeMembershipExpertise;
    private ScheduleData scheduleData;
    private String generateBatchCorrespondenceTypeCode;
    private java.sql.Date generateStartDate;
    private java.sql.Date generateEndDate;
    private List<CommitteeBatchCorrespondence> generateBatchCorrespondence;
    private String historyBatchCorrespondenceTypeCode;
    private java.sql.Date historyStartDate;
    private java.sql.Date historyEndDate;
    private List<CommitteeBatchCorrespondence> batchCorrespondenceHistory;
    private Boolean printRooster;
    private Boolean printFutureScheduledMeeting;
    private boolean modifySchedule = false;
    private boolean viewSchedule = false;
    private List<Boolean> viewSpecificSchedule;
    private boolean performAction = false;
    private boolean showActiveMembersOnly = true;

    // Needed when multipleValuesLookup populates a CommitteeMembership with the CommitteeMembershipExpertise,
    // so it know which CommitteeMembership should get them.
    private int memberIndex;

    public CommitteeHelper(CommitteeForm committeeForm) {
        this.committeeForm = committeeForm;
        this.newCommitteeMembership = new CommitteeMembership();
        this.newCommitteeMembershipRoles = new ArrayList<CommitteeMembershipRole>();
//        this.setScheduleData(new ScheduleData());
        this.setGenerateBatchCorrespondence(new ArrayList<CommitteeBatchCorrespondence>());
        this.setBatchCorrespondenceHistory(new ArrayList<CommitteeBatchCorrespondence>());
        this.memberIndex = -1;
    }
    
    public Committee getCommittee() {
        return committeeForm.getCommitteeDocument().getCommittee();
    }
    
    /**
     * This method is UI view hook.
     */
    public void prepareView() {
        if (committeeForm.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().stateIsFinal() || committeeForm.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().stateIsEnroute() || committeeForm.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().stateIsCanceled()) {
            modifyCommittee = false;
            Committee activeCommittee = getCommitteeService().getCommitteeById(
                    getCommittee().getCommitteeId());
            if (activeCommittee != null && activeCommittee.getId().equals(getCommittee().getId())) {
                modifySchedule = canModifySchedule();
                viewSchedule = canViewSchedule();
                viewSpecificSchedule = canViewSpecificSchedule();
            } else {
                // inactive committee can not access schedule data either
                modifySchedule = false;
                viewSchedule = false;
                viewSpecificSchedule = canNotViewSpecificSchedule();
            }
        } else {
            modifyCommittee = canModifyCommittee();
            modifySchedule = false;
            viewSchedule = false;
            viewSpecificSchedule = canNotViewSpecificSchedule();
        }
        prepareCommitteeScheduleDeleteView();

        performAction = canPerformAction();
    }
    
    /**
     * Helper method prepares a view for deleteable CommitteeSchedules, sorted by the scheduled date.
     */
    private void prepareCommitteeScheduleDeleteView() {
        boolean flag = false;
        CommitteeScheduleService service = getCommitteeScheduleService();
        for (CommitteeSchedule committeeSchedule : getSortedCommitteeScheduleList()) {            
            flag = service.isCommitteeScheduleDeletable(committeeSchedule);
            committeeSchedule.setDelete(flag);
        }    
    }
    
    /**
     * This method returns CommitteeScheduleService.
     * @return
     */
    private CommitteeScheduleService getCommitteeScheduleService() {
        return KraServiceLocator.getService(CommitteeScheduleService.class);
    }

    public boolean canModifyCommittee() {
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
    }

    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    public boolean getModifyCommittee() {
        return modifyCommittee;
    }

    public void setModifyCommittee(boolean modifyCommittee) {
        this.modifyCommittee = modifyCommittee;
    }
    
    public CommitteeMembership getNewCommitteeMembership() {
        return newCommitteeMembership;
    }

    public void setNewCommitteeMembership(CommitteeMembership newCommitteeMembership) {
        this.newCommitteeMembership = newCommitteeMembership;
    }
    
    public List<CommitteeMembershipRole> getNewCommitteeMembershipRoles() {
        while (this.committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships().size() > this.newCommitteeMembershipRoles.size()) {
            this.newCommitteeMembershipRoles.add(this.newCommitteeMembershipRoles.size(), new CommitteeMembershipRole());
        }
        return newCommitteeMembershipRoles;
    }

    public void setNewCommitteeMembershipRoles(List <CommitteeMembershipRole> newCommitteeMembershipRoles) {
        this.newCommitteeMembershipRoles = newCommitteeMembershipRoles;
    }
    
    public List<CommitteeMembershipExpertise> getNewCommitteeMembershipExpertise() {
        while (this.committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships().size() > this.newCommitteeMembershipExpertise.size()) {
            this.newCommitteeMembershipExpertise.add(this.newCommitteeMembershipExpertise.size(), new CommitteeMembershipExpertise());
        }
        return newCommitteeMembershipExpertise;
    }

    public void setNewCommitteeMembershipExpertise(List <CommitteeMembershipExpertise> newCommitteeMembershipExpertise) {
        this.newCommitteeMembershipExpertise = newCommitteeMembershipExpertise;
    }

    public ScheduleData getScheduleData() {
        return scheduleData;
    }

//    public void setScheduleData(ScheduleData scheduleData) {
//        this.scheduleData = scheduleData;
//    }    
    
    public String getGenerateBatchCorrespondenceTypeCode() {
        return generateBatchCorrespondenceTypeCode;
    }

    public void setGenerateBatchCorrespondenceTypeCode(String generateBatchCorrespondenceTypeCode) {
        this.generateBatchCorrespondenceTypeCode = generateBatchCorrespondenceTypeCode;
    }

    public java.sql.Date getGenerateStartDate() {
        return generateStartDate;
    }

    public void setGenerateStartDate(java.sql.Date generateStartDate) {
        this.generateStartDate = generateStartDate;
    }

    public java.sql.Date getGenerateEndDate() {
        return generateEndDate;
    }

    public void setGenerateEndDate(java.sql.Date generateEndDate) {
        this.generateEndDate = generateEndDate;
    }

    public String getHistoryBatchCorrespondenceTypeCode() {
        return historyBatchCorrespondenceTypeCode;
    }

    public void setHistoryBatchCorrespondenceTypeCode(String historyBatchCorrespondenceTypeCode) {
        this.historyBatchCorrespondenceTypeCode = historyBatchCorrespondenceTypeCode;
    }

    public java.sql.Date getHistoryStartDate() {
        return historyStartDate;
    }

    public void setHistoryStartDate(java.sql.Date historyStartDate) {
        this.historyStartDate = historyStartDate;
    }

    public java.sql.Date getHistoryEndDate() {
        return historyEndDate;
    }

    public void setHistoryEndDate(java.sql.Date historyEndDate) {
        this.historyEndDate = historyEndDate;
    }

    public List<CommitteeBatchCorrespondence> getGenerateBatchCorrespondence() {
        return generateBatchCorrespondence;
    }

    public void setGenerateBatchCorrespondence(List<CommitteeBatchCorrespondence> generateBatchCorrespondence) {
        this.generateBatchCorrespondence = generateBatchCorrespondence;
    }

    public List<CommitteeBatchCorrespondence> getBatchCorrespondenceHistory() {
        Collections.sort(batchCorrespondenceHistory);
        return batchCorrespondenceHistory;
    }

    public void setBatchCorrespondenceHistory(List<CommitteeBatchCorrespondence> batchCorrespondenceHistory) {
        this.batchCorrespondenceHistory = batchCorrespondenceHistory;
    }

    public Boolean getPrintRooster() {
        return printRooster;
    }

    public void setPrintRooster(Boolean printRooster) {
        this.printRooster = printRooster;
    }
    
    public Boolean getPrintFutureScheduledMeeting() {
        return printFutureScheduledMeeting;
    }

    public void setPrintFutureScheduledMeeting(Boolean printFutureScheduledMeeting) {
        this.printFutureScheduledMeeting = printFutureScheduledMeeting;
    }
    
    /**
     * 
     * This method resets the Batch Correspondence history. 
     * This involves clearing the previous displayed history information and collapsing all panel content with 
     * the exception of the Batch Correspondence panel.
     * 
     * @param committeeForm the CommitteeForm
     */
    public void resetBatchCorrespondenceHistory(CommitteeForm committeeForm) {
        setBatchCorrespondenceHistory(null);
        committeeForm.setTabStates(new HashMap<String, String>());
        committeeForm.getTabStates().put(WebUtils.generateTabKey(BATCH_CORRESPONDENCE_PANEL_TITLE), "OPEN");
    }

    public void setMemberIndex(int memberIndex) {
        this.memberIndex = memberIndex;
    }

    public int getMemberIndex() {
        return memberIndex;
    }
    
    /**
     * This method prepares a view to filter dates between start and end date, sorted by the scheduled date.
     * @param startDate
     * @param endDate
     */
    public void prepareFilterDatesView(java.util.Date startDate, java.util.Date endDate) {
        startDate = DateUtils.addDays(startDate, -1);
        endDate = DateUtils.addDays(endDate, 1);
        java.util.Date scheduleDate = null;
        for (CommitteeSchedule committeeSchedule : getSortedCommitteeScheduleList()) {            
            scheduleDate = committeeSchedule.getScheduledDate();
            if ((scheduleDate != null) && scheduleDate.after(startDate) && scheduleDate.before(endDate)) {
                committeeSchedule.setFilter(true);            
            } else {
                committeeSchedule.setFilter(false);
            }
        }
    }

    /**
     * This method prepares view to reset filtered dates and sorts them by the scheduled date.
     */
    public void resetFilterDatesView() {
        for (CommitteeSchedule committeeSchedule : getSortedCommitteeScheduleList()) {
            committeeSchedule.setFilter(true);            
        }
        if (getScheduleData() != null) {
            getScheduleData().setFilterStartDate(null);
            getScheduleData().setFilterEndDate(null);
        }
    }
    
    public boolean canModifySchedule() {
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_SCHEDULE, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    public boolean canViewSchedule() {
        CommitteeTask task = new CommitteeTask(TaskName.VIEW_SCHEDULE, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    public List<Boolean> canViewSpecificSchedule() {
        List<Boolean> canViewSchedule = new ArrayList<Boolean>();
        for (CommitteeSchedule committeeSchedule : getCommittee().getCommitteeSchedules()) {
            CommitteeTask task = new CommitteeScheduleTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getCommittee(), committeeSchedule);
            canViewSchedule.add(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
        }
        return canViewSchedule; 
    }
    
    @SuppressWarnings("unused") 
    public List<Boolean> canNotViewSpecificSchedule() {
        List<Boolean> canNotViewSchedule = new ArrayList<Boolean>();
        for (CommitteeSchedule committeeSchedule : getCommittee().getCommitteeSchedules()) {
            canNotViewSchedule.add(false);
        }
        return canNotViewSchedule; 
    }
    
    public boolean canPerformAction() {
        CommitteeTask task = new CommitteeTask(TaskName.PERFORM_COMMITTEE_ACTIONS, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    public boolean isModifySchedule() {
        return modifySchedule;
    }

    public void setModifySchedule(boolean modifySchedule) {
        this.modifySchedule = modifySchedule;
    }

    public boolean isViewSchedule() {
        return viewSchedule;
    }

    public void setViewSchedule(boolean viewSchedule) {
        this.viewSchedule = viewSchedule;
    }
    
    public List<Boolean> getViewSpecificSchedule() {
        return viewSpecificSchedule;
    }

    public boolean isPerformAction() {
        return performAction;
    }
    
    public boolean isShowActiveMembersOnly() {
        return showActiveMembersOnly;
    }
    
    public void setShowActiveMembersOnly(boolean showActiveMembersOnly) {
        this.showActiveMembersOnly = showActiveMembersOnly;
    }
    
    public void flagInactiveMembers() {
        for (CommitteeMembership committeeMembership : committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships()) {
            if (!committeeMembership.isActive()) {
                committeeMembership.setWasInactiveAtLastSave(true);
            } else {
                committeeMembership.setWasInactiveAtLastSave(false);
            }
        }
    }

    private List<CommitteeSchedule> getSortedCommitteeScheduleList() {
        List<CommitteeSchedule> committeeSchedules = committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        Collections.sort(committeeSchedules);
        return committeeSchedules;
    }
}
