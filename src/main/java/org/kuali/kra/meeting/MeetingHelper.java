/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.meeting;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.MembershipRole;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.bo.PersistableBusinessObject;

import edu.emory.mathcs.backport.java.util.Collections;

public class MeetingHelper implements Serializable {

    private static final long serialVersionUID = 2363534404324211441L;
    private MeetingForm form;
    private Date agendaGenerationDate;
    private CommitteeSchedule committeeSchedule;
    private List<ProtocolSubmittedBean> protocolSubmittedBeans;
    private CommScheduleActItem newOtherAction;
    private List<CommScheduleActItem> deletedOtherActions;
    private List<CommitteeScheduleAttendance> deletedAttendances;
    // the label used for the first page tab.
    private String tabLabel;
    private List<MemberPresentBean> memberPresentBeans;
    private List<MemberAbsentBean> memberAbsentBeans;
    private List<OtherPresentBean> otherPresentBeans;
    private OtherPresentBean newOtherPresentBean;
    private String absenteeList;
    private CommitteeScheduleMinute newCommitteeScheduleMinute;
    private List<CommitteeScheduleMinute> deletedCommitteeScheduleMinutes;
    // It is for minute entry/attendance, and generate attendance comment by server if js is disabled.
    private boolean jsDisabled = false;
    
    private static final String FIELD_SEPARAATOR = "#f#";
    // TODO : still not sure whetehr to use scheduledate/meetingdate as a base to decide such as
    // member is active or not
    public MeetingHelper(MeetingForm form) {
        this.form = form;
        committeeSchedule = new CommitteeSchedule();
        protocolSubmittedBeans = new ArrayList<ProtocolSubmittedBean>();
        memberPresentBeans = new ArrayList<MemberPresentBean>();
        memberAbsentBeans = new ArrayList<MemberAbsentBean>();
        otherPresentBeans = new ArrayList<OtherPresentBean>();
        newOtherAction = new CommScheduleActItem();
        newCommitteeScheduleMinute = new CommitteeScheduleMinute();
        newOtherPresentBean = new OtherPresentBean();
        initDeletedList();
    }

    
    public MeetingForm getForm() {
        return form;
    }

    public void setForm(MeetingForm form) {
        this.form = form;
    }


    public CommScheduleActItem getNewOtherAction() {
        return newOtherAction;
    }

    public void setNewOtherAction(CommScheduleActItem newOtherAction) {
        this.newOtherAction = newOtherAction;
    }

    public String getTabLabel() {
        return tabLabel;
    }

    public void setTabLabel(String tabLabel) {
        this.tabLabel = tabLabel;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Date getAgendaGenerationDate() {
        return agendaGenerationDate;
    }

    public void setAgendaGenerationDate(Date agendaGenerationDate) {
        this.agendaGenerationDate = agendaGenerationDate;
    }

    public List<CommScheduleActItem> getDeletedOtherActions() {
        return deletedOtherActions;
    }

    public void setDeletedOtherActions(List<CommScheduleActItem> deletedOtherActions) {
        this.deletedOtherActions = deletedOtherActions;
    }

    public List<ProtocolSubmittedBean> getProtocolSubmittedBeans() {
        return protocolSubmittedBeans;
    }

    public void setProtocolSubmittedBeans(List<ProtocolSubmittedBean> protocolSubmittedBeans) {
        this.protocolSubmittedBeans = protocolSubmittedBeans;
    }

    public List<MemberPresentBean> getMemberPresentBeans() {
        return memberPresentBeans;
    }

    public void setMemberPresentBeans(List<MemberPresentBean> memberPresentBeans) {
        this.memberPresentBeans = memberPresentBeans;
    }

    public List<MemberAbsentBean> getMemberAbsentBeans() {
        return memberAbsentBeans;
    }

    public void setMemberAbsentBeans(List<MemberAbsentBean> memberAbsentBeans) {
        this.memberAbsentBeans = memberAbsentBeans;
    }

    public List<OtherPresentBean> getOtherPresentBeans() {
        return otherPresentBeans;
    }

    public void setOtherPresentBeans(List<OtherPresentBean> otherPresentBeans) {
        this.otherPresentBeans = otherPresentBeans;
    }

    public OtherPresentBean getNewOtherPresentBean() {
        return newOtherPresentBean;
    }

    public void setNewOtherPresentBean(OtherPresentBean newOtherPresentBean) {
        this.newOtherPresentBean = newOtherPresentBean;
    }

    /**
     * 
     * This method is to get absent list which will be used to create 'alternate for' drop down list. Can only pass a string to
     * valuesfinder as property, so has to concatenate the needed data into a string.
     * 
     * @return
     */
    public String getAbsenteeList() {
        if (StringUtils.isBlank(absenteeList) && !memberAbsentBeans.isEmpty()) {
            absenteeList = initAbsenteeList();
        }
        return absenteeList;
    }

    public void setAbsenteeList(String absenteeList) {
        this.absenteeList = absenteeList;
    }

    private String initAbsenteeList() {

        String result = "";
        for (MemberAbsentBean memberAbsentBean : memberAbsentBeans) {
            if (StringUtils.isBlank(result)) {
                result = memberAbsentBean.getAttendance().getPersonId() + FIELD_SEPARAATOR
                        + memberAbsentBean.getAttendance().getPersonName();
            }
            else {
                result = result + "#m#" + memberAbsentBean.getAttendance().getPersonId() + FIELD_SEPARAATOR
                        + memberAbsentBean.getAttendance().getPersonName();
            }
        }
        return result;
    }

    public List<CommitteeScheduleAttendance> getDeletedAttendances() {
        return deletedAttendances;
    }

    public void setDeletedAttendances(List<CommitteeScheduleAttendance> deletedAttendances) {
        this.deletedAttendances = deletedAttendances;
    }

    public CommitteeScheduleMinute getNewCommitteeScheduleMinute() {
        return newCommitteeScheduleMinute;
    }

    public void setNewCommitteeScheduleMinute(CommitteeScheduleMinute newCommitteeScheduleMinute) {
        this.newCommitteeScheduleMinute = newCommitteeScheduleMinute;
    }

    public List<CommitteeScheduleMinute> getDeletedCommitteeScheduleMinutes() {
        return deletedCommitteeScheduleMinutes;
    }

    public void setDeletedCommitteeScheduleMinutes(List<CommitteeScheduleMinute> deletedCommitteeScheduleMinutes) {
        this.deletedCommitteeScheduleMinutes = deletedCommitteeScheduleMinutes;
    }

    /**
     * 
     * This method method is used to initialize the deleted bo list.
     */
    protected void initDeletedList() {
        setDeletedOtherActions(new ArrayList<CommScheduleActItem>());
        setDeletedCommitteeScheduleMinutes(new ArrayList<CommitteeScheduleMinute>());
        setDeletedAttendances(new ArrayList<CommitteeScheduleAttendance>());

    }

    /**
     * 
     * This method put all deleted bos to a list, so boservice can delete them.
     * 
     * @return
     */
    protected List<PersistableBusinessObject> getDeletedBos() {
        List<PersistableBusinessObject> deletedBos = new ArrayList<PersistableBusinessObject>();
        deletedBos.addAll(((MeetingForm) form).getMeetingHelper().getDeletedOtherActions());
        deletedBos.addAll(((MeetingForm) form).getMeetingHelper().getDeletedAttendances());
        deletedBos.addAll(((MeetingForm) form).getMeetingHelper().getDeletedCommitteeScheduleMinutes());
        return deletedBos;

    }

    /**
     * 
     * This method populate committeescheduleattendance from 3 beans, memberpresent/otherpresent/memberabsent.
     */
    protected void populateAttendancePreSave() {
        CommitteeSchedule committeeSchedule = this.getCommitteeSchedule();
        List<CommitteeScheduleAttendance> attendances = new ArrayList<CommitteeScheduleAttendance>();
        for (MemberPresentBean memberPresentBean : this.getMemberPresentBeans()) {
            memberPresentBean.getAttendance().setGuestFlag(false);
            attendances.add(memberPresentBean.getAttendance());
        }
        for (OtherPresentBean otherPresentBean : this.getOtherPresentBeans()) {
            otherPresentBean.getAttendance().setGuestFlag(true);
            attendances.add(otherPresentBean.getAttendance());
        }
        // TODO : just delete old one and then insert new ones
        // need to think it again
        this.setDeletedAttendances(committeeSchedule.getCommitteeScheduleAttendances());
        committeeSchedule.setCommitteeScheduleAttendances(attendances);
    }

    /**
     * 
     * This method is to add new other action to other action list.
     */
    protected void addOtherAction() {
        newOtherAction.refreshReferenceObject("scheduleActItemType");
        newOtherAction.setScheduleIdFk(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule().getId());
        newOtherAction.setActionItemNumber(getNextActionItemNumber());
        this.getCommitteeSchedule().getCommScheduleActItems().add(newOtherAction);
        this.setNewOtherAction(new CommScheduleActItem());

    }

    /*
     * find the max action number and increase by one.
     */
    private Integer getNextActionItemNumber() {
        Integer nextActionItemNumber = this.getCommitteeSchedule().getCommScheduleActItems().size();
        for (CommScheduleActItem commScheduleActItem : this.getCommitteeSchedule().getCommScheduleActItems()) {
            if (commScheduleActItem.getActionItemNumber() > nextActionItemNumber) {
                nextActionItemNumber = commScheduleActItem.getActionItemNumber();
            }
        }
        return nextActionItemNumber + 1;

    }

    /**
     * 
     * This method is to delete the selected other action from the list.
     * 
     * @param itemNumber
     */
    protected void deleteOtherAction(int itemNumber) {
        CommScheduleActItem commScheduleActItem = getCommitteeSchedule().getCommScheduleActItems().get(itemNumber);
        if (commScheduleActItem.getCommScheduleActItemsId() != null) {
            getDeletedOtherActions().add(commScheduleActItem);
        }
        getCommitteeSchedule().getCommScheduleActItems().remove(itemNumber);

    }

    /**
     * 
     * This method is to move member from present list to absent list.
     * 
     * @param itemNumber
     */
    protected void markAbsent(int itemNumber) {
        MemberPresentBean memberPresentBean = getMemberPresentBeans().get(itemNumber);
        MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
        memberPresentBean.getAttendance().setAlternateFor(null);
        memberAbsentBean.setAttendance(memberPresentBean.getAttendance());
//        memberAbsentBean.setRole(memberPresentBean.getRole());
        getMemberAbsentBeans().add(memberAbsentBean);
        getMemberPresentBeans().remove(itemNumber);
    }

    /**
     * 
     * This method is to move member absent to member present list.
     * 
     * @param itemNumber
     */
    protected void presentVoting(int itemNumber) {
        MemberAbsentBean memberAbsentBean = getMemberAbsentBeans().get(itemNumber);
        MemberPresentBean memberPresentBean = new MemberPresentBean();
        memberPresentBean.setAttendance(memberAbsentBean.getAttendance());
//        memberPresentBean.setRole(memberAbsentBean.getRole());
        getMemberPresentBeans().add(memberPresentBean);
        getMemberAbsentBeans().remove(itemNumber);
    }


    /**
     * 
     * This method is to move absent member to other present.
     * 
     * @param itemNumber
     */
    protected void presentOther(int itemNumber) {
        MemberAbsentBean memberAbsentBean = getMemberAbsentBeans().get(itemNumber);
        OtherPresentBean otherPresentBean = new OtherPresentBean();
        otherPresentBean.setAttendance(memberAbsentBean.getAttendance());
//        otherPresentBean.setRole(memberAbsentBean.getRole());
        otherPresentBean.setMember(true);
        getOtherPresentBeans().add(otherPresentBean);
        getMemberAbsentBeans().remove(itemNumber);
    }

    /**
     * 
     * This method is to add the selected person or rolodex to other present list.
     */
    protected void addOtherPresent() {
        OtherPresentBean otherPresentBean = new OtherPresentBean();
        otherPresentBean.setMember(false);
        getNewOtherPresentBean().getAttendance().setRoleName("Guest");
        otherPresentBean.setAttendance(getNewOtherPresentBean().getAttendance());
        memberHandling(getMemberAbsentBeans(), otherPresentBean);
//        otherPresentBean.getRole().setDescription("Guest");
        getOtherPresentBeans().add(otherPresentBean);
        setNewOtherPresentBean(new OtherPresentBean());

    }

    /**
     * 
     * This method is to check if the selected person/rolodex is in member absent. if it is them removed it.
     * 
     * @param memberAbsentBeans
     * @param otherPresentBean
     */
    private void memberHandling(List<MemberAbsentBean> memberAbsentBeans, OtherPresentBean otherPresentBean) {
        MemberAbsentBean matchedMemberAbsentBean = null;
        for (MemberAbsentBean memberAbsentBean : memberAbsentBeans) {
            if (isAbsentMember(memberAbsentBean, otherPresentBean)) {
                // TODO : should 'role' be set here too ?
                otherPresentBean.setMember(true);
                //getRole(otherPresentBean.getAttendance(), getCommitteeSchedule().getCommittee().getCommitteeMemberships(), getCommitteeSchedule().getScheduledDate());
                getRoleName(otherPresentBean.getAttendance(), getCommitteeSchedule().getCommittee().getCommitteeMemberships(), getCommitteeSchedule().getScheduledDate());
                matchedMemberAbsentBean = memberAbsentBean;
            }
        }
        if (matchedMemberAbsentBean != null) {
            memberAbsentBeans.remove(matchedMemberAbsentBean);
        }

    }

    /*
     * This method checks if the person/rolodex is an absent member.
     */
    private boolean isAbsentMember(MemberAbsentBean memberAbsentBean, OtherPresentBean otherPresentBean) {
        boolean isPresent = false;
        if (memberAbsentBean.getAttendance().getNonEmployeeFlag() && otherPresentBean.getAttendance().getNonEmployeeFlag()
                && memberAbsentBean.getAttendance().getPersonId().equals(otherPresentBean.getAttendance().getPersonId())) {
            isPresent = true;
        }
        else if (!memberAbsentBean.getAttendance().getNonEmployeeFlag() && !otherPresentBean.getAttendance().getNonEmployeeFlag()
                && memberAbsentBean.getAttendance().getPersonId().equals(otherPresentBean.getAttendance().getPersonId())) {
            isPresent = true;
        }
        return isPresent;
    }

    /**
     * 
     * This method is to delete other present. if the deleted other present is a member, then this person will be added to absent list.
     * 
     * @param itemNumber
     */
    protected void deleteOtherPresent(int itemNumber) {
        OtherPresentBean otherPresentBean = getOtherPresentBeans().get(itemNumber);
        if (otherPresentBean.isMember()) {
            MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
            memberAbsentBean.setAttendance(otherPresentBean.getAttendance());
//            memberAbsentBean.setRole(otherPresentBean.getRole());
            getMemberAbsentBeans().add(memberAbsentBean);
        }
        getOtherPresentBeans().remove(itemNumber);
    }

    /**
     * 
     * This method is to add new committee schedule minute entry to minute entry list.
     */
    protected void addCommitteeScheduleMinute() {
        newCommitteeScheduleMinute.refreshReferenceObject("minuteEntryType");
        newCommitteeScheduleMinute.refreshReferenceObject("protocol");
        newCommitteeScheduleMinute.setScheduleIdFk(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule().getId());
        newCommitteeScheduleMinute.setEntryNumber(getNextMinuteEntryNumber());
        if (MinuteEntryType.ATTENDANCE.equals(newCommitteeScheduleMinute.getMinuteEntryTypeCode()) && newCommitteeScheduleMinute.isGenerateAttendance()
                && isJsDisabled()) {
            // in case JS is disabled
            newCommitteeScheduleMinute.setMinuteEntry(generateAttendanceComment());            
        }
        getCommitteeSchedule().getCommitteeScheduleMinutes().add(newCommitteeScheduleMinute);
        setNewCommitteeScheduleMinute(new CommitteeScheduleMinute());

    }

    /*
     * This is to generate comment for minute entry Type of 'Attendance' and 'generate attendance is checked 
     */
    private String generateAttendanceComment() {
        String comment = "";
        String eol = System.getProperty("line.separator");
        for (MemberPresentBean memberPresentBean : getMemberPresentBeans()) {
            if (StringUtils.isNotBlank(comment)) {
                comment = comment + eol;
            }
            comment = comment + memberPresentBean.getAttendance().getPersonName();
            if (StringUtils.isNotBlank(memberPresentBean.getAttendance().getAlternateFor())) {
                comment = comment + " Alternate For: " + getAlternateForName(memberPresentBean.getAttendance().getAlternateFor());
            }
        }
        for (OtherPresentBean otherPresentBean : getOtherPresentBeans()) {
            if (StringUtils.isNotBlank(comment)) {
                comment = comment + eol;
            }
            comment = comment + otherPresentBean.getAttendance().getPersonName() + " Guest ";
        }
        return comment;
    }
    
    /*
     * Utility method to figure out next entry number for this schedule.
     */
    private Integer getNextMinuteEntryNumber() {
        Integer nextMinuteEntryNumber = getCommitteeSchedule().getCommitteeScheduleMinutes().size();
        for (CommitteeScheduleMinute committeeScheduleMinute : getCommitteeSchedule().getCommitteeScheduleMinutes()) {
            if (committeeScheduleMinute.getEntryNumber() > nextMinuteEntryNumber) {
                nextMinuteEntryNumber = committeeScheduleMinute.getEntryNumber();
            }
        }
        return nextMinuteEntryNumber + 1;
    }

    /*
     * Utility to get person name for 'alternate for'.  This name is used when 'generate attendance' is checked.
     */
    private String getAlternateForName(String alternateFor) {
        
        String personName = "";
        for (CommitteeMembership committeeMembership : getCommitteeSchedule().getCommittee().getCommitteeMemberships()) {
            if ((StringUtils.isNotBlank(committeeMembership.getPersonId()) && committeeMembership.getPersonId().equals(alternateFor)) 
                    || (StringUtils.isBlank(committeeMembership.getPersonId()) && committeeMembership.getRolodexId().equals(alternateFor))){
                personName = committeeMembership.getPersonName();
                break;
            }                 
        }
        return personName;
    }
    /**
     * 
     * This method is to delete committee schedule minute entry from minute entry list.
     * 
     * @param itemNumber
     */
    protected void deleteCommitteeScheduleMinute(int itemNumber) {
        CommitteeScheduleMinute committeeScheduleMinute = getCommitteeSchedule().getCommitteeScheduleMinutes().get(itemNumber);
        if (committeeScheduleMinute.getCommScheduleMinutesId() != null) {
            getDeletedCommitteeScheduleMinutes().add(committeeScheduleMinute);
        }
        getCommitteeSchedule().getCommitteeScheduleMinutes().remove(itemNumber);
    }


    /**
     * 
     * This method is to populate meeting form/helper data when meeting page is loaded.
     * @param commSchedule
     * @param lineNumber
     */
    public void populateFormHelper(CommitteeSchedule commSchedule, int lineNumber) {
        for (ProtocolSubmission protocolSubmission : commSchedule.getProtocolSubmissions()) {
            ProtocolSubmittedBean protocolSubmittedBean = new ProtocolSubmittedBean();
            ProtocolPerson pi = protocolSubmission.getProtocol().getPrincipalInvestigator();
            protocolSubmittedBean.setPersonId(pi.getPersonId());
            protocolSubmittedBean.setPersonName(pi.getPersonName());
            protocolSubmittedBean.setRolodexId(pi.getRolodexId());
            // protocolSubmittedBean.setProtocolPersonId(pi.getProtocolPersonId());
            ((MeetingForm) form).getMeetingHelper().getProtocolSubmittedBeans().add(protocolSubmittedBean);
        }
        boolean isEmptyAttendance = commSchedule.getCommitteeScheduleAttendances().isEmpty();
        if (commSchedule.getCommitteeScheduleAttendances().isEmpty() 
                && !commSchedule.getCommittee().getCommitteeMemberships().isEmpty()) {
          //  KraServiceLocator.getService(MeetingService.class).initAttendance(commSchedule);
            initAttendance(commSchedule);
        } else {
            populateAttendanceToForm(commSchedule.getCommittee().getCommitteeMemberships(), commSchedule);
        }

        ((MeetingForm) form).getMeetingHelper().setAgendaGenerationDate(getAgendaGenerationDate(commSchedule.getId()));
        ((MeetingForm) form).getMeetingHelper().setCommitteeSchedule(commSchedule);
        ((MeetingForm) form).getMeetingHelper().setTabLabel(getMeetingTabTitle(lineNumber));

    }

    /*
     * set up title of the first header tab in meeting page.
     * lineNumber is this selected schedule's item number in committee schedule list
     */
    private String getMeetingTabTitle(int lineNumber) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("MM/dd/yyyy");

        return getCommitteeSchedule().getCommittee().getCommitteeName() + " #" + lineNumber + " Meeting "
                + dateFormat.format(getCommitteeSchedule().getScheduledDate());

    }
    
    /*
     * call meeeting service to get the last agenda generation date.
     */
    private Date getAgendaGenerationDate(Long scheduleId) {
        return KraServiceLocator.getService(MeetingService.class).getAgendaGenerationDate(scheduleId);
    }

    /*
     * populate 3 attendance form beans
     */
    private void populateAttendanceToForm(List<CommitteeMembership> committeeMemberships,
            CommitteeSchedule commSchedule) {
        populatePresentBean(committeeMemberships, commSchedule);
        populateMemberAbsentBean(committeeMemberships, commSchedule);

    }

    /*
     * populate memberpresentbean & otherpresentbean
     */
    private void populatePresentBean(List<CommitteeMembership> committeeMemberships, CommitteeSchedule commSchedule) {
        for (CommitteeScheduleAttendance committeeScheduleAttendance : commSchedule.getCommitteeScheduleAttendances()) {
            Date meetingDate = commSchedule.getMeetingDate();
// TODO : temporary comment out to use schedule date until further decision
//            if (meetingDate == null) {
//                meetingDate = commSchedule.getScheduledDate();
//            }
            //MembershipRole role = getRole(committeeScheduleAttendance, committeeMemberships, meetingDate);
            getRoleName(committeeScheduleAttendance, committeeMemberships, meetingDate);
            if (committeeScheduleAttendance.getGuestFlag()) {
                OtherPresentBean otherPresentBean = new OtherPresentBean();
                otherPresentBean.setAttendance(committeeScheduleAttendance);
                // TODO : is it based on role or membership start/end date & 'inactive' role
                // to decide whetehr he/she is member ?
                otherPresentBean.setMember(isActiveMember(committeeScheduleAttendance, committeeMemberships, meetingDate));
                if (StringUtils.isBlank(committeeScheduleAttendance.getRoleName())) {
                    committeeScheduleAttendance.setRoleName("Guest");
                }
                getOtherPresentBeans().add(otherPresentBean);
                otherPresentBean.setAttendance(committeeScheduleAttendance);
            }
            else {
                MemberPresentBean memberPresentBean = new MemberPresentBean();
//                committeeScheduleAttendance.setRoleName(committeeScheduleAttendance.getRoleName());
                memberPresentBean.setAttendance(committeeScheduleAttendance);
                getMemberPresentBeans().add(memberPresentBean);
            }
        }
    }
    
    /*
     * populate memberabsentbean
     */
    private void populateMemberAbsentBean(List<CommitteeMembership> committeeMemberships, CommitteeSchedule commSchedule) {
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (!isInMemberPresent(committeeMembership)
                    && !isInOtherPresent(committeeMembership)) {
            //if (!isPresent && !isAlternate(committeeMembership, commSchedule.getScheduledDate())) {
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
//                memberAbsentBean.setRole(getRole(committeeMembership, commSchedule.getScheduledDate()));
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
//                attendance.setRoleName(getRoleNameForMembership(committeeMembership, commSchedule.getMeetingDate() != null 
//                        ? commSchedule.getMeetingDate() : commSchedule.getScheduledDate()));
                attendance.setRoleName(getRoleNameForMembership(committeeMembership, commSchedule.getScheduledDate()));
                if (StringUtils.isBlank(committeeMembership.getPersonId())) {
                    attendance.setPersonId(committeeMembership.getRolodexId().toString());
                }
                else {
                    attendance.setPersonId(committeeMembership.getPersonId());
                }
                attendance.setPersonName(committeeMembership.getPersonName());
                attendance.setAlternateFlag(false);
                attendance.setNonEmployeeFlag(StringUtils.isBlank(committeeMembership.getPersonId()));
                memberAbsentBean.setAttendance(attendance);
                getMemberAbsentBeans().add(memberAbsentBean);
            }
        }

    }

    /*
     * check if person is in member present.
     */
    private boolean isInMemberPresent(CommitteeMembership committeeMembership) {
        boolean isPresent = false;
        for (MemberPresentBean memberPresentBean : getMemberPresentBeans()) {
            if (memberPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isBlank(committeeMembership.getPersonId())
                    && memberPresentBean.getAttendance().getPersonId().equals(committeeMembership.getRolodexId().toString())) {
                isPresent = true;
                break;
            }
            else if (!memberPresentBean.getAttendance().getNonEmployeeFlag()
                    && StringUtils.isNotBlank(committeeMembership.getPersonId())
                    && memberPresentBean.getAttendance().getPersonId().equals(committeeMembership.getPersonId())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /*
     * check if person is in other present
     */
    private boolean isInOtherPresent(CommitteeMembership committeeMembership) {
        boolean isPresent = false;
        for (OtherPresentBean otherPresentBean : getOtherPresentBeans()) {
            if (otherPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isBlank(committeeMembership.getPersonId())
                    && otherPresentBean.getAttendance().getPersonId().equals(committeeMembership.getRolodexId().toString())) {
                isPresent = true;
                break;
            }
            else if (!otherPresentBean.getAttendance().getNonEmployeeFlag()
                    && StringUtils.isNotBlank(committeeMembership.getPersonId())
                    && otherPresentBean.getAttendance().getPersonId().equals(committeeMembership.getPersonId())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /*
     * Init attendance if this meeting schedule is maintained for the first time.
     */
    private void initAttendance(CommitteeSchedule commSchedule) {
        List<CommitteeMembership> committeeMemberships = commSchedule.getCommittee().getCommitteeMemberships();
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (isActiveMembership(committeeMembership, commSchedule.getScheduledDate())) {
                CommitteeScheduleAttendance committeeScheduleAttendance = new CommitteeScheduleAttendance();
                if (StringUtils.isBlank(committeeMembership.getPersonId())) {
                    committeeScheduleAttendance.setPersonId(committeeMembership.getRolodexId().toString());
                    committeeScheduleAttendance.setNonEmployeeFlag(true);
                }
                else {
                    committeeScheduleAttendance.setPersonId(committeeMembership.getPersonId());
                    committeeScheduleAttendance.setNonEmployeeFlag(false);
                }
                committeeScheduleAttendance.setPersonName(committeeMembership.getPersonName());
                if (isAlternate(committeeMembership, commSchedule.getScheduledDate())) {
                    committeeScheduleAttendance.setAlternateFlag(true);
                }
                else {
                    committeeScheduleAttendance.setAlternateFlag(false);

                }
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
//                memberAbsentBean.setRole(getRole(committeeMembership, commSchedule.getScheduledDate()));
//                committeeScheduleAttendance.setRoleName(getRoleNameForMembership(committeeMembership, commSchedule.getMeetingDate() != null ? commSchedule.getMeetingDate() : commSchedule.getScheduledDate()));
                committeeScheduleAttendance.setRoleName(getRoleNameForMembership(committeeMembership, commSchedule.getScheduledDate()));
                memberAbsentBean.setAttendance(committeeScheduleAttendance);
                getMemberAbsentBeans().add(memberAbsentBean);
            }
        }
    }

    /* 
     * check if this membership is active based on schedule date
     */
    private boolean isActiveMembership(CommitteeMembership committeeMembership, Date scheduledDate) {
        boolean isActiveMember = !committeeMembership.getTermStartDate().after(scheduledDate)
                && !committeeMembership.getTermEndDate().before(scheduledDate);
        if (isActiveMember) {
            for (CommitteeMembershipRole membershipRole : committeeMembership.getMembershipRoles()) {
                if (!membershipRole.getStartDate().after(scheduledDate) && !membershipRole.getEndDate().before(scheduledDate)) {
                    if (membershipRole.getMembershipRoleCode().equals(CommitteeMembershipRole.INACTIVE_ROLE)) {
                        // Inactive matched, stop here
                        isActiveMember = false;
                        break;
                    }
                }
            }
        }
        return isActiveMember;

    }


    /*
     * check if this membership has alternate role based on schedule date.
     */
    private boolean isAlternate(CommitteeMembership committeeMembership, Date scheduledDate) {
        boolean isAlternate = false;
        for (CommitteeMembershipRole membershipRole : committeeMembership.getMembershipRoles()) {
            if (membershipRole.getMembershipRoleCode().equals(CommitteeMembershipRole.ALTERNATE_ROLE) && !membershipRole.getStartDate().after(scheduledDate)
                    && !membershipRole.getEndDate().before(scheduledDate)) {
                isAlternate = true;
                break;
            }
        }
        return isAlternate;
    }

    /*
     * get a person's role name within this committee memberships based on schedule date.
     */
    private void getRoleName(CommitteeScheduleAttendance committeeScheduleAttendance,
            List<CommitteeMembership> committeeMemberships, Date meetingDate) {
        String roleName = "";
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if ((committeeScheduleAttendance.getNonEmployeeFlag() && committeeMembership.getRolodexId() != null && committeeScheduleAttendance
                    .getPersonId().equals(committeeMembership.getRolodexId().toString()))
                    || (!committeeScheduleAttendance.getNonEmployeeFlag() && committeeScheduleAttendance.getPersonId().equals(
                            committeeMembership.getPersonId()))) {
                roleName = getRoleNameForMembership(committeeMembership, meetingDate);
                break;
            }
        }
        committeeScheduleAttendance.setRoleName(roleName);
        //return roleName;
    }

    /*
     * Check if this member is active in this committee.
     * Inactive scenario :
     *  - not defined in membership.
     *  - in membership, but non of the memberships period cover schedule date
     *  - an 'Inactive' role period cover schedule date.
     */
    private boolean isActiveMember(CommitteeScheduleAttendance committeeScheduleAttendance,
            List<CommitteeMembership> committeeMemberships, Date meetingDate) {
        boolean isActiveMember = false;
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if ((committeeScheduleAttendance.getNonEmployeeFlag() && committeeMembership.getRolodexId() != null && committeeScheduleAttendance
                    .getPersonId().equals(committeeMembership.getRolodexId().toString()))
                    || (!committeeScheduleAttendance.getNonEmployeeFlag() && committeeScheduleAttendance.getPersonId().equals(
                            committeeMembership.getPersonId()))) {
                if (!committeeMembership.getTermStartDate().after(meetingDate)
                     && !committeeMembership.getTermEndDate().before(meetingDate)) {
                    isActiveMember = isActiveMembership(committeeMembership, meetingDate);
                }
            }
        }
        return isActiveMember;
    }
    
    /*
     * get rolename, concatenated with ',' separator if multiple roles exist for this membership
     */
    private String getRoleNameForMembership(CommitteeMembership committeeMembership, Date scheduledDate) {
        String roleName = "";
        for (CommitteeMembershipRole membershipRole : committeeMembership.getMembershipRoles()) {
            if (!membershipRole.getStartDate().after(scheduledDate) && !membershipRole.getEndDate().before(scheduledDate)) {
                roleName = roleName +"," + membershipRole.getMembershipRole().getDescription();
            }
        }
        if (StringUtils.isNotBlank(roleName)) {
            roleName = roleName.substring(1); // remove ","
        }
        return roleName;
    }

    /*
     * Sort attendances by person name.
     */
    protected void sortAttendances() {
        if (!getMemberPresentBeans().isEmpty()) {
            Collections.sort(getMemberPresentBeans());
        }
        if (!getMemberAbsentBeans().isEmpty()) {
            Collections.sort(getMemberAbsentBeans());
        }
        if (!getOtherPresentBeans().isEmpty()) {
            Collections.sort(getOtherPresentBeans());
        }

    }

    public boolean isJsDisabled() {
        return jsDisabled;
    }

    public void setJsDisabled(boolean jsDisabled) {
        this.jsDisabled = jsDisabled;
    }
}
