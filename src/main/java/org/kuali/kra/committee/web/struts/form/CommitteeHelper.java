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
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The CommitteeHelper corresponds to the Committee tab web page.
 */
public class CommitteeHelper implements Serializable {
    
    private static final long serialVersionUID = 1744329032797755384L;
    
    private CommitteeForm committeeForm;
    private boolean modifyCommittee = false;
    private CommitteeMembership newCommitteeMembership;
    private List<CommitteeMembershipRole> newCommitteeMembershipRoles;
    private List<CommitteeMembershipExpertise> newCommitteeMembershipExpertise;
    private ScheduleData scheduleData;
    private CommitteeActionsHelper committeeActionsHelper;
    private boolean modifySchedule = false;
    private boolean viewSchedule = false;

    // Needed when multipleValuesLookup populates a CommitteeMembership with the CommitteeMembershipExpertise,
    // so it know which CommitteeMembership should get them.
    private int memberIndex;

    public CommitteeHelper(CommitteeForm committeeForm) {
        this.committeeForm = committeeForm;
        this.newCommitteeMembership = new CommitteeMembership();
        this.newCommitteeMembershipRoles = new ArrayList<CommitteeMembershipRole>();
        this.setScheduleData(new ScheduleData());
        this.setCommitteeActionsHelper(new CommitteeActionsHelper());
    }
    
    public Committee getCommittee() {
        return committeeForm.getCommitteeDocument().getCommittee();
    }
    
    /**
     * This method is UI view hook.
     */
    public void prepareView() {
        if (committeeForm.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_FINAL_CD)) {
            modifyCommittee = false;
            modifySchedule = canModifySchedule();
            viewSchedule = canViewSchedule();
        } else {
            modifyCommittee = canModifyCommittee();
            modifySchedule = false;
            viewSchedule = false;
        }
        prepareCommitteeScheduleDeleteView();

    }
    
    /**
     * Helper method prepares view for deleteable CommitteeSchedules.
     */
    private void prepareCommitteeScheduleDeleteView(){
    
        List<CommitteeSchedule> committeeSchedules = committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        boolean flag = false;
        CommitteeScheduleService service = getCommitteeScheduleService();
        for(CommitteeSchedule committeeSchedule: committeeSchedules) {            
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
        if (this.committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships().size() > this.newCommitteeMembershipRoles.size()) {
            this.newCommitteeMembershipRoles.add(this.newCommitteeMembershipRoles.size(), new CommitteeMembershipRole());
        }
        return newCommitteeMembershipRoles;
    }

    public void setNewCommitteeMembershipRoles(List <CommitteeMembershipRole> newCommitteeMembershipRoles) {
        this.newCommitteeMembershipRoles = newCommitteeMembershipRoles;
    }
    
    public List<CommitteeMembershipExpertise> getNewCommitteeMembershipExpertise() {
        if (this.committeeForm.getCommitteeDocument().getCommittee().getCommitteeMemberships().size() > this.newCommitteeMembershipExpertise.size()) {
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

    public void setScheduleData(ScheduleData scheduleData) {
        this.scheduleData = scheduleData;
    }    
    
    public CommitteeActionsHelper getCommitteeActionsHelper() {
        return committeeActionsHelper;
    }

    public void setCommitteeActionsHelper(CommitteeActionsHelper committeeActionsHelper) {
        this.committeeActionsHelper = committeeActionsHelper;
    }

    public void setMemberIndex(int memberIndex) {
        this.memberIndex = memberIndex;
    }

    public int getMemberIndex() {
        return memberIndex;
    }
    
    /**
     * This method prepares view to filter dates between start and end date.
     * @param startDate
     * @param endDate
     */
    public void prepareFilterDatesView(Date startDate, Date endDate) {
        List<CommitteeSchedule> committeeSchedules = committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        startDate = DateUtils.addDays(startDate, -1);
        endDate = DateUtils.addDays(endDate, 1);
        Date scheduleDate = null;
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {            
            scheduleDate = committeeSchedule.getScheduledDate();
            if(scheduleDate.after(startDate) && scheduleDate.before(endDate)) 
                committeeSchedule.setFilter(true);            
            else
                committeeSchedule.setFilter(false);
        }
    }

    /**
     * This method prepares view to reset filtered dates.
     */
    public void resetFilterDatesView() {
        List<CommitteeSchedule> committeeSchedules = committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {
                committeeSchedule.setFilter(true);            
        }
        getScheduleData().setFilterStartDate(null);
        getScheduleData().setFilerEndDate(null);
    }
    
    public boolean canModifySchedule() {
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_SCHEDULE, getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    public boolean canViewSchedule() {
        CommitteeTask task = new CommitteeTask(TaskName.VIEW_SCHEDULE, getCommittee());
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


}
