/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.committee.impl.web.struts.form;

import org.apache.commons.lang3.time.DateUtils;
import org.kuali.coeus.common.committee.impl.bo.*;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * The CommitteeHelperBase corresponds to the CommitteeBase tab web page.
 */
public abstract class CommitteeHelperBase implements Serializable {
    
    private static final long serialVersionUID = 1744329032797755384L;

    private static final String BATCH_CORRESPONDENCE_PANEL_TITLE = "Batch Correspondence";
    
    private CommitteeFormBase committeeForm;
    private boolean modifyCommittee = false;
    private CommitteeMembershipBase newCommitteeMembership;
    private List<CommitteeMembershipRole> newCommitteeMembershipRoles;
    private List<CommitteeMembershipExpertiseBase> newCommitteeMembershipExpertise;
    private ScheduleData scheduleData;
    private String generateBatchCorrespondenceTypeCode;
    private java.sql.Date generateStartDate;
    private java.sql.Date generateEndDate;
    private List<CommitteeBatchCorrespondenceBase> generateBatchCorrespondence;
    private String historyBatchCorrespondenceTypeCode;
    private java.sql.Date historyStartDate;
    private java.sql.Date historyEndDate;
    private List<CommitteeBatchCorrespondenceBase> batchCorrespondenceHistory;
    private Boolean printRooster;
    private Boolean printFutureScheduledMeeting;
    private boolean modifySchedule = false;
    private boolean viewSchedule = false;
    private List<Boolean> viewSpecificSchedule;
    private boolean performAction = false;
    private boolean showActiveMembersOnly = true;
    private List<CorrespondencePrintOption> correspondencesToPrint;

    // Needed when multipleValuesLookup populates a CommitteeMembershipBase with the CommitteeMembershipExpertise,
    // so it know which CommitteeMembershipBase should get them.
    private int memberIndex;

    public CommitteeHelperBase(CommitteeFormBase committeeForm) {
        this.committeeForm = committeeForm;

        this.newCommitteeMembership = getNewCommitteeMembershipInstanceHook();
        this.newCommitteeMembershipRoles = new ArrayList<CommitteeMembershipRole>();
        this.setScheduleData(new ScheduleData());
        this.setGenerateBatchCorrespondence(new ArrayList<CommitteeBatchCorrespondenceBase>());
        this.setBatchCorrespondenceHistory(new ArrayList<CommitteeBatchCorrespondenceBase>());
        this.memberIndex = -1;
    }
    
    protected abstract CommitteeMembershipBase getNewCommitteeMembershipInstanceHook();
    

    public CommitteeBase<?, ?, ?> getCommittee() {
        return committeeForm.getCommitteeDocument().getCommittee();
    }
    
    /**
     * This method is UI view hook.
     */
    public void prepareView() {
        if (committeeForm.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().isFinal() || committeeForm.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().isEnroute() || committeeForm.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().isCanceled()) {
            modifyCommittee = false;
            CommitteeBase activeCommittee = getCommitteeService().getCommitteeById(
                    getCommittee().getCommitteeId());
            if (activeCommittee != null && (activeCommittee.getId().equals(getCommittee().getId())
                    || KewApiConstants.ROUTE_HEADER_CANCEL_CD.equals(activeCommittee.getCommitteeDocument().getDocStatusCode()))) {
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
        initPrintCorrespondences();
    }
    
    /**
     * Helper method prepares a view for deleteable CommitteeSchedules, sorted by the scheduled date.
     */
    private void prepareCommitteeScheduleDeleteView() {
        boolean flag = false;
        CommitteeScheduleServiceBase service = getCommitteeScheduleService();
        for (CommitteeScheduleBase committeeSchedule : getSortedCommitteeScheduleList()) {            
            flag = service.isCommitteeScheduleDeletable(committeeSchedule);
            committeeSchedule.setDelete(flag);
        }    
    }
    
    /**
     * This method returns CommitteeScheduleService.
     * @return
     */
    private CommitteeScheduleServiceBase getCommitteeScheduleService() {
        return KcServiceLocator.getService(getCommitteeScheduleServiceClassHook());
    }

    protected abstract Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook();
    
    

    public boolean canModifyCommittee() {
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.MODIFY_COMMITTEE, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private CommitteeServiceBase getCommitteeService() {
        return KcServiceLocator.getService(getCommitteeServiceClassHook());
    }

    protected abstract Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook();
    

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
    
    public CommitteeMembershipBase getNewCommitteeMembership() {
        return newCommitteeMembership;
    }

    public void setNewCommitteeMembership(CommitteeMembershipBase newCommitteeMembership) {
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
    
    public List<CommitteeMembershipExpertiseBase> getNewCommitteeMembershipExpertise() {
        while (this.committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships().size() > this.newCommitteeMembershipExpertise.size()) {
            this.newCommitteeMembershipExpertise.add(this.newCommitteeMembershipExpertise.size(), getNewCommitteeMembershipExpertiseInstanceHook());
        }
        return newCommitteeMembershipExpertise;
    }

    protected abstract CommitteeMembershipExpertiseBase getNewCommitteeMembershipExpertiseInstanceHook();
    
    public void setNewCommitteeMembershipExpertise(List <CommitteeMembershipExpertiseBase> newCommitteeMembershipExpertise) {
        this.newCommitteeMembershipExpertise = newCommitteeMembershipExpertise;
    }

    public ScheduleData getScheduleData() {
        return scheduleData;
    }

    public void setScheduleData(ScheduleData scheduleData) {
        this.scheduleData = scheduleData;
    }    
    
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

    public List<CommitteeBatchCorrespondenceBase> getGenerateBatchCorrespondence() {
        return generateBatchCorrespondence;
    }

    public void setGenerateBatchCorrespondence(List<CommitteeBatchCorrespondenceBase> generateBatchCorrespondence) {
        this.generateBatchCorrespondence = generateBatchCorrespondence;
    }

    public List<CommitteeBatchCorrespondenceBase> getBatchCorrespondenceHistory() {
        Collections.sort(batchCorrespondenceHistory);
        return batchCorrespondenceHistory;
    }

    public void setBatchCorrespondenceHistory(List<CommitteeBatchCorrespondenceBase> batchCorrespondenceHistory) {
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
     * @param committeeForm the CommitteeFormBase
     */
    public void resetBatchCorrespondenceHistory(CommitteeFormBase committeeForm) {
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
        for (CommitteeScheduleBase committeeSchedule : getSortedCommitteeScheduleList()) {            
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
        for (CommitteeScheduleBase committeeSchedule : getSortedCommitteeScheduleList()) {
            committeeSchedule.setFilter(true);            
        }
        getScheduleData().setFilterStartDate(null);
        getScheduleData().setFilerEndDate(null);
    }
    
    public boolean canModifySchedule() {
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.MODIFY_SCHEDULE, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    public boolean canViewSchedule() {
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.VIEW_SCHEDULE, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    public List<Boolean> canViewSpecificSchedule() {
        List<Boolean> canViewSchedule = new ArrayList<Boolean>();
        for (CommitteeScheduleBase committeeSchedule : getCommittee().getCommitteeSchedules()) {
            CommitteeTaskBase task = getNewCommitteeScheduleTaskInstanceHook(TaskName.VIEW_SCHEDULE, committeeSchedule.getParentCommittee(), committeeSchedule);
            canViewSchedule.add(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
        }
        return canViewSchedule; 
    }
    
    protected abstract CommitteeScheduleTaskBase getNewCommitteeScheduleTaskInstanceHook(String taskName, CommitteeBase committee, CommitteeScheduleBase committeeSchedule);

    @SuppressWarnings("unused") 
    public List<Boolean> canNotViewSpecificSchedule() {
        List<Boolean> canNotViewSchedule = new ArrayList<Boolean>();
        for (CommitteeScheduleBase committeeSchedule : getCommittee().getCommitteeSchedules()) {
            canNotViewSchedule.add(false);
        }
        return canNotViewSchedule; 
    }
    
    public boolean canPerformAction() {
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.PERFORM_COMMITTEE_ACTIONS, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee);

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
        for (CommitteeMembershipBase committeeMembership : ((CommitteeBase<?, ?, ?>)committeeForm.getCommitteeDocument().getCommittee()).getCommitteeMemberships()) {
            if (!committeeMembership.isActive()) {
                committeeMembership.setWasInactiveAtLastSave(true);
            } else {
                committeeMembership.setWasInactiveAtLastSave(false);
            }
        }
    }

    private List<CommitteeScheduleBase> getSortedCommitteeScheduleList() {
        List<CommitteeScheduleBase> committeeSchedules = committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        Collections.sort(committeeSchedules);
        return committeeSchedules;
    }

    protected abstract void initPrintCorrespondences();
    
    public List<CorrespondencePrintOption> getCorrespondencesToPrint() {
        return correspondencesToPrint;
    }

    public void setCorrespondencesToPrint(List<CorrespondencePrintOption> correspondencesToPrint) {
        this.correspondencesToPrint = correspondencesToPrint;
    }
}
