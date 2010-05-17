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
package org.kuali.kra.meeting;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MeetingServiceImpl implements MeetingService {

    BusinessObjectService businessObjectService;

    /*
     * 
     * This method is to get the last agenda generation date.
     * 
     * @param scheduleId
     * 
     * @return
     */
    private Date getAgendaGenerationDate(Long scheduleId) {
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("scheduleIdFk", scheduleId);
        List<ScheduleAgenda> scheduleAgendas = (List<ScheduleAgenda>) businessObjectService.findMatchingOrderBy(
                ScheduleAgenda.class, fieldValues, "createTimestamp", false);
        if (scheduleAgendas.isEmpty()) {
            return null;
        }
        else {
            return new Date(scheduleAgendas.get(0).getCreateTimestamp().getTime());
        }
    }

    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#SaveMeetingDetails(org.kuali.kra.committee.bo.CommitteeSchedule, java.util.List)
     */
    public void saveMeetingDetails(CommitteeSchedule committeeSchedule, List<? extends PersistableBusinessObject> deletedBos) {
        committeeSchedule.setStartTime(addHrMinToDate(committeeSchedule.getStartTime(), committeeSchedule.getViewStartTime()));
        committeeSchedule.setEndTime(addHrMinToDate(committeeSchedule.getEndTime(), committeeSchedule.getViewEndTime()));
        committeeSchedule.setTime(addHrMinToDate(committeeSchedule.getTime(), committeeSchedule.getViewTime()));

        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }
        businessObjectService.save(committeeSchedule);
        // a hook to display "successfully saved" message
        GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);

    }

    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#getStandardReviewComment(java.lang.String)
     */
    public String getStandardReviewComment(String protocolContingencyCode) {
        String description = null;
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("protocolContingencyCode", protocolContingencyCode);
        ProtocolContingency protocolContingency = (ProtocolContingency) businessObjectService.findByPrimaryKey(
                ProtocolContingency.class, queryMap);
        if (protocolContingency != null) {
            description = protocolContingency.getDescription();
        }
        return description;
    }

    /*
     * utility methods by adding minutes to date
     */
    private Timestamp addHrMinToDate(Timestamp time, Time12HrFmt viewTime) {
        java.util.Date dt = new java.util.Date(0); // this is actually 12-31-1969 19:00.  its GMT time
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        try {
            // support localization. 
            dt = dateFormat.parse("01/01/1970 "+viewTime.getTime()+" "+viewTime.getMeridiem());
            return new Timestamp(dt.getTime());
        } catch (Exception e) {
            // TODO : not sure to throw runtimeexception or not.
            // folowing may convert date to 07-02-1970 iftz is gmt+12 or more
            dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH); 
            return new Timestamp(DateUtils.addMinutes(dt, viewTime.findMinutes()).getTime());
            
        }
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#addOtherAction(org.kuali.kra.meeting.CommScheduleActItem,
     *      org.kuali.kra.committee.bo.CommitteeSchedule)
     */
    public void addOtherAction(CommScheduleActItem newOtherAction, CommitteeSchedule committeeSchedule) {
        newOtherAction.refreshReferenceObject("scheduleActItemType");
        newOtherAction.setScheduleIdFk(committeeSchedule.getId());
        newOtherAction.setActionItemNumber(getNextActionItemNumber(committeeSchedule));
        committeeSchedule.getCommScheduleActItems().add(newOtherAction);
    }

    /*
     * find the max action number and increase by one.
     */
    private Integer getNextActionItemNumber(CommitteeSchedule committeeSchedule) {
        Integer nextActionItemNumber = committeeSchedule.getCommScheduleActItems().size();
        for (CommScheduleActItem commScheduleActItem : committeeSchedule.getCommScheduleActItems()) {
            if (commScheduleActItem.getActionItemNumber() > nextActionItemNumber) {
                nextActionItemNumber = commScheduleActItem.getActionItemNumber();
            }
        }
        return nextActionItemNumber + 1;

    }

    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#deleteOtherAction(org.kuali.kra.committee.bo.CommitteeSchedule, int,
     *      java.util.List)
     */
    public void deleteOtherAction(CommitteeSchedule committeeSchedule, int itemNumber, List<CommScheduleActItem> deletedOtherActions) {
        CommScheduleActItem commScheduleActItem = committeeSchedule.getCommScheduleActItems().get(itemNumber);
        if (commScheduleActItem.getCommScheduleActItemsId() != null) {
            deletedOtherActions.add(commScheduleActItem);
        }
        committeeSchedule.getCommScheduleActItems().remove(itemNumber);

    }


    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#markAbsent(java.util.List, java.util.List, int)
     */
    public void markAbsent(List<MemberPresentBean> memberPresentBeans, List<MemberAbsentBean> memberAbsentBeans, int itemNumber) {
        MemberPresentBean memberPresentBean = memberPresentBeans.get(itemNumber);
        MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
        memberPresentBean.getAttendance().setAlternateFor(null);
        memberAbsentBean.setAttendance(memberPresentBean.getAttendance());
        memberAbsentBeans.add(memberAbsentBean);
        memberPresentBeans.remove(itemNumber);
    }

    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#presentVoting(org.kuali.kra.meeting.MeetingHelper, int)
     */
    public void presentVoting(MeetingHelper meetingHelper, int itemNumber) {
        MemberAbsentBean memberAbsentBean = meetingHelper.getMemberAbsentBeans().get(itemNumber);
        MemberPresentBean memberPresentBean = new MemberPresentBean();
        memberPresentBean.setAttendance(memberAbsentBean.getAttendance());
        memberPresentBean.getAttendance().setAlternateFlag(
                isAlternateForMember(meetingHelper.getCommitteeSchedule(), memberPresentBean.getAttendance(), meetingHelper
                        .getCommitteeSchedule().getScheduledDate()));
        meetingHelper.getMemberPresentBeans().add(memberPresentBean);
        meetingHelper.getMemberAbsentBeans().remove(itemNumber);
    }

    /*
     * This is a utility method to reset alternate flag before 'present voting'
     */
    private boolean isAlternateForMember(CommitteeSchedule committeeSchedule,
            CommitteeScheduleAttendance committeeScheduleAttendance, Date scheduledDate) {
        boolean isAlternate = false;
        for (CommitteeMembership committeeMembership : committeeSchedule.getCommittee().getCommitteeMemberships()) {
            if ((committeeScheduleAttendance.getNonEmployeeFlag() && committeeMembership.getRolodexId() != null && committeeScheduleAttendance
                    .getPersonId().equals(committeeMembership.getRolodexId().toString()))
                    || (!committeeScheduleAttendance.getNonEmployeeFlag() && committeeScheduleAttendance.getPersonId().equals(
                            committeeMembership.getPersonId()))) {
                if (isActiveMembership(committeeMembership, scheduledDate) && isAlternate(committeeMembership, scheduledDate)) {
                    isAlternate = true;
                    break;
                }
            }
        }
        return isAlternate;
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
            if (membershipRole.getMembershipRoleCode().equals(CommitteeMembershipRole.ALTERNATE_ROLE)
                    && !membershipRole.getStartDate().after(scheduledDate) && !membershipRole.getEndDate().before(scheduledDate)) {
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
            List<CommitteeMembership> committeeMemberships, Date scheduleDate) {
        String roleName = "";
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if ((committeeScheduleAttendance.getNonEmployeeFlag() && committeeMembership.getRolodexId() != null && committeeScheduleAttendance
                    .getPersonId().equals(committeeMembership.getRolodexId().toString()))
                    || (!committeeScheduleAttendance.getNonEmployeeFlag() && committeeScheduleAttendance.getPersonId().equals(
                            committeeMembership.getPersonId()))) {
                roleName = getRoleNameForMembership(committeeMembership, scheduleDate);
                break;
            }
        }
        committeeScheduleAttendance.setRoleName(roleName);
    }


    /*
     * get rolename, concatenated with ',' separator if multiple roles exist for this membership
     */
    private String getRoleNameForMembership(CommitteeMembership committeeMembership, Date scheduledDate) {
        String roleName = "";
        for (CommitteeMembershipRole membershipRole : committeeMembership.getMembershipRoles()) {
            if (!membershipRole.getStartDate().after(scheduledDate) && !membershipRole.getEndDate().before(scheduledDate)) {
                roleName = roleName + "," + membershipRole.getMembershipRole().getDescription();
            }
        }
        if (StringUtils.isNotBlank(roleName)) {
            roleName = roleName.substring(1); // remove ","
        }
        return roleName;
    }


    /*
     * Check if this member is active in this committee. Inactive scenario : - not defined in membership. - in membership, but non
     * of the memberships period cover schedule date - an 'Inactive' role period cover schedule date.
     */
    private boolean isActiveMember(CommitteeScheduleAttendance committeeScheduleAttendance,
            List<CommitteeMembership> committeeMemberships, Date scheduleDate) {
        boolean isActiveMember = false;
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if ((committeeScheduleAttendance.getNonEmployeeFlag() && committeeMembership.getRolodexId() != null && committeeScheduleAttendance
                    .getPersonId().equals(committeeMembership.getRolodexId().toString()))
                    || (!committeeScheduleAttendance.getNonEmployeeFlag() && committeeScheduleAttendance.getPersonId().equals(
                            committeeMembership.getPersonId()))) {
                if (!committeeMembership.getTermStartDate().after(scheduleDate)
                        && !committeeMembership.getTermEndDate().before(scheduleDate)) {
                    isActiveMember = isActiveMembership(committeeMembership, scheduleDate);
                }
            }
        }
        return isActiveMember;
    }


    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#presentOther(org.kuali.kra.meeting.MeetingHelper, int)
     */
    public void presentOther(MeetingHelper meetingHelper, int itemNumber) {
        MemberAbsentBean memberAbsentBean = meetingHelper.getMemberAbsentBeans().get(itemNumber);
        OtherPresentBean otherPresentBean = new OtherPresentBean();
        otherPresentBean.setAttendance(memberAbsentBean.getAttendance());
        otherPresentBean.setMember(true);
        meetingHelper.getOtherPresentBeans().add(otherPresentBean);
        meetingHelper.getMemberAbsentBeans().remove(itemNumber);
    }

    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#addOtherPresent(org.kuali.kra.meeting.MeetingHelper)
     */
    public void addOtherPresent(MeetingHelper meetingHelper) {
        OtherPresentBean otherPresentBean = new OtherPresentBean();
        otherPresentBean.setMember(false);
        meetingHelper.getNewOtherPresentBean().getAttendance().setRoleName("Guest");
        otherPresentBean.setAttendance(meetingHelper.getNewOtherPresentBean().getAttendance());
        memberHandling(meetingHelper, otherPresentBean);
        meetingHelper.getOtherPresentBeans().add(otherPresentBean);
        meetingHelper.setNewOtherPresentBean(new OtherPresentBean());
    }

    /**
     * 
     * This method is called when 'addOtherPresent'. It is to check if the selected person/rolodex is in member absent. if the
     * selected person/rolodex is in memberabsentbean list, then removed it from the membersentbean list; so this person will not be
     * displayed in member absent panel.
     * 
     * @param memberAbsentBeans
     * @param otherPresentBean
     */
    private void memberHandling(MeetingHelper meetingHelper, OtherPresentBean otherPresentBean) {
        MemberAbsentBean matchedMemberAbsentBean = null;
        for (MemberAbsentBean memberAbsentBean : meetingHelper.getMemberAbsentBeans()) {
            if (isAbsentMember(memberAbsentBean, otherPresentBean)) {
                otherPresentBean.setMember(true);
                getRoleName(otherPresentBean.getAttendance(), meetingHelper.getCommitteeSchedule().getCommittee()
                        .getCommitteeMemberships(), meetingHelper.getCommitteeSchedule().getScheduledDate());
                matchedMemberAbsentBean = memberAbsentBean;
            }
        }
        if (matchedMemberAbsentBean != null) {
            meetingHelper.getMemberAbsentBeans().remove(matchedMemberAbsentBean);
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
     * @see org.kuali.kra.meeting.MeetingService#deleteOtherPresent(org.kuali.kra.meeting.MeetingHelper, int)
     */
    public void deleteOtherPresent(MeetingHelper meetingHelper, int itemNumber) {
        OtherPresentBean otherPresentBean = meetingHelper.getOtherPresentBeans().get(itemNumber);
        if (otherPresentBean.isMember()) {
            MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
            memberAbsentBean.setAttendance(otherPresentBean.getAttendance());
            meetingHelper.getMemberAbsentBeans().add(memberAbsentBean);
        }
        meetingHelper.getOtherPresentBeans().remove(itemNumber);
    }


    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#addCommitteeScheduleMinute(org.kuali.kra.meeting.MeetingHelper)
     */
    public void addCommitteeScheduleMinute(MeetingHelper meetingHelper) {
        meetingHelper.getNewCommitteeScheduleMinute().refreshReferenceObject("minuteEntryType");
        meetingHelper.getNewCommitteeScheduleMinute().refreshReferenceObject("protocol");
        meetingHelper.getNewCommitteeScheduleMinute().setScheduleIdFk(meetingHelper.getCommitteeSchedule().getId());
        meetingHelper.getNewCommitteeScheduleMinute()
                .setEntryNumber(getNextMinuteEntryNumber(meetingHelper.getCommitteeSchedule()));
        if (MinuteEntryType.ATTENDANCE.equals(meetingHelper.getNewCommitteeScheduleMinute().getMinuteEntryTypeCode())
                && meetingHelper.getNewCommitteeScheduleMinute().isGenerateAttendance() && meetingHelper.isJsDisabled()) {
            // in case JS is disabled
            meetingHelper.getNewCommitteeScheduleMinute().setMinuteEntry(
                    generateAttendanceComment(meetingHelper.getMemberPresentBeans(), meetingHelper.getOtherPresentBeans(),
                            meetingHelper.getCommitteeSchedule()));
        }
        meetingHelper.getCommitteeSchedule().getCommitteeScheduleMinutes().add(meetingHelper.getNewCommitteeScheduleMinute());
        meetingHelper.setNewCommitteeScheduleMinute(new CommitteeScheduleMinute());

    }

    /*
     * This is to generate comment for minute entry Type of 'Attendance' and 'generate attendance is checked
     */
    private String generateAttendanceComment(List<MemberPresentBean> memberPresentBeans, List<OtherPresentBean> otherPresentBeans,
            CommitteeSchedule committeeSchedule) {
        String comment = "";
        String eol = System.getProperty("line.separator");
        for (MemberPresentBean memberPresentBean : memberPresentBeans) {
            if (StringUtils.isNotBlank(comment)) {
                comment = comment + eol;
            }
            comment = comment + memberPresentBean.getAttendance().getPersonName();
            if (StringUtils.isNotBlank(memberPresentBean.getAttendance().getAlternateFor())) {
                comment = comment + " Alternate For: "
                        + getAlternateForName(committeeSchedule, memberPresentBean.getAttendance().getAlternateFor());
            }
        }
        for (OtherPresentBean otherPresentBean : otherPresentBeans) {
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
    private Integer getNextMinuteEntryNumber(CommitteeSchedule committeeSchedule) {
        Integer nextMinuteEntryNumber = committeeSchedule.getCommitteeScheduleMinutes().size();
        for (CommitteeScheduleMinute committeeScheduleMinute : committeeSchedule.getCommitteeScheduleMinutes()) {
            if (committeeScheduleMinute.getEntryNumber() > nextMinuteEntryNumber) {
                nextMinuteEntryNumber = committeeScheduleMinute.getEntryNumber();
            }
        }
        return nextMinuteEntryNumber + 1;
    }

    /*
     * Utility to get person name for 'alternate for'. This name is used when 'generate attendance' is checked.
     */
    private String getAlternateForName(CommitteeSchedule committeeSchedule, String alternateFor) {

        String personName = "";
        for (CommitteeMembership committeeMembership : committeeSchedule.getCommittee().getCommitteeMemberships()) {
            if ((StringUtils.isNotBlank(committeeMembership.getPersonId()) && committeeMembership.getPersonId()
                    .equals(alternateFor))
                    || (StringUtils.isBlank(committeeMembership.getPersonId()) && committeeMembership.getRolodexId().equals(
                            alternateFor))) {
                personName = committeeMembership.getPersonName();
                break;
            }
        }
        return personName;
    }


    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#deleteCommitteeScheduleMinute(org.kuali.kra.committee.bo.CommitteeSchedule,
     *      java.util.List, int)
     */
    public void deleteCommitteeScheduleMinute(CommitteeSchedule committeeSchedule,
            List<CommitteeScheduleMinute> deletedCommitteeScheduleMinutes, int itemNumber) {
        CommitteeScheduleMinute committeeScheduleMinute = committeeSchedule.getCommitteeScheduleMinutes().get(itemNumber);
        if (committeeScheduleMinute.getCommScheduleMinutesId() != null) {
            deletedCommitteeScheduleMinutes.add(committeeScheduleMinute);
        }
        committeeSchedule.getCommitteeScheduleMinutes().remove(itemNumber);
    }


    /**
     * 
     * @see org.kuali.kra.meeting.MeetingService#populateFormHelper(org.kuali.kra.meeting.MeetingHelper,
     *      org.kuali.kra.committee.bo.CommitteeSchedule, int)
     */
    public void populateFormHelper(MeetingHelper meetingHelper, CommitteeSchedule commSchedule, int lineNumber) {
        for (ProtocolSubmission protocolSubmission : commSchedule.getProtocolSubmissions()) {
            ProtocolSubmittedBean protocolSubmittedBean = new ProtocolSubmittedBean();
            ProtocolPerson pi = protocolSubmission.getProtocol().getPrincipalInvestigator();
            protocolSubmittedBean.setPersonId(pi.getPersonId());
            protocolSubmittedBean.setPersonName(pi.getPersonName());
            protocolSubmittedBean.setRolodexId(pi.getRolodexId());
            meetingHelper.getProtocolSubmittedBeans().add(protocolSubmittedBean);
        }
        if (commSchedule.getCommitteeScheduleAttendances().isEmpty()
                && !commSchedule.getCommittee().getCommitteeMemberships().isEmpty()) {
            initAttendance(meetingHelper.getMemberAbsentBeans(), commSchedule);
        }
        else {
            populateAttendanceToForm(meetingHelper, commSchedule.getCommittee().getCommitteeMemberships(), commSchedule);
        }

        meetingHelper.setAgendaGenerationDate(getAgendaGenerationDate(commSchedule.getId()));
        meetingHelper.setCommitteeSchedule(commSchedule);
        meetingHelper.setTabLabel(getMeetingTabTitle(meetingHelper.getCommitteeSchedule(), lineNumber));

    }

    /*
     * set up title of the first header tab in meeting page. lineNumber is this selected schedule's item number in committee
     * schedule list
     */
    private String getMeetingTabTitle(CommitteeSchedule committeeSchedule, int lineNumber) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        return committeeSchedule.getCommittee().getCommitteeName() + " #" + lineNumber + " Meeting "
                + dateFormat.format(committeeSchedule.getScheduledDate());

    }

    /*
     * populate 3 attendance form beans
     */
    private void populateAttendanceToForm(MeetingHelper meetingHelper, List<CommitteeMembership> committeeMemberships,
            CommitteeSchedule commSchedule) {
        populatePresentBean(meetingHelper, committeeMemberships, commSchedule);
        populateMemberAbsentBean(meetingHelper, committeeMemberships, commSchedule);

    }

    /*
     * populate memberpresentbean & otherpresentbean
     */
    private void populatePresentBean(MeetingHelper meetingHelper, List<CommitteeMembership> committeeMemberships,
            CommitteeSchedule commSchedule) {
        meetingHelper.setOtherPresentBeans(new ArrayList<OtherPresentBean>());
        meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
        for (CommitteeScheduleAttendance committeeScheduleAttendance : commSchedule.getCommitteeScheduleAttendances()) {
            getRoleName(committeeScheduleAttendance, committeeMemberships, commSchedule.getScheduledDate());
            if (committeeScheduleAttendance.getGuestFlag()) {
                OtherPresentBean otherPresentBean = new OtherPresentBean();
                otherPresentBean.setAttendance(committeeScheduleAttendance);
                otherPresentBean.setMember(isActiveMember(committeeScheduleAttendance, committeeMemberships, commSchedule
                        .getScheduledDate()));
                if (StringUtils.isBlank(committeeScheduleAttendance.getRoleName())) {
                    committeeScheduleAttendance.setRoleName("Guest");
                }
                meetingHelper.getOtherPresentBeans().add(otherPresentBean);
                otherPresentBean.setAttendance(committeeScheduleAttendance);
            }
            else {
                MemberPresentBean memberPresentBean = new MemberPresentBean();
                memberPresentBean.setAttendance(committeeScheduleAttendance);
                meetingHelper.getMemberPresentBeans().add(memberPresentBean);
            }
        }
    }

    /*
     * populate memberabsentbean
     */
    private void populateMemberAbsentBean(MeetingHelper meetingHelper, List<CommitteeMembership> committeeMemberships,
            CommitteeSchedule commSchedule) {
        meetingHelper.setMemberAbsentBeans(new ArrayList<MemberAbsentBean>());
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (!isInMemberPresent(meetingHelper.getMemberPresentBeans(), committeeMembership)
                    && !isInOtherPresent(meetingHelper.getOtherPresentBeans(), committeeMembership)) {
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
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
                meetingHelper.getMemberAbsentBeans().add(memberAbsentBean);
            }
        }

    }

    /*
     * Init attendance if this meeting schedule is maintained for the first time.
     */
    private void initAttendance(List<MemberAbsentBean> memberAbsentBeans, CommitteeSchedule commSchedule) {
        List<CommitteeMembership> committeeMemberships = commSchedule.getCommittee().getCommitteeMemberships();
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (isActiveMembership(committeeMembership, commSchedule.getScheduledDate())) {
                CommitteeScheduleAttendance committeeScheduleAttendance = new CommitteeScheduleAttendance();
                if (StringUtils.isBlank(committeeMembership.getPersonId())) {
                    committeeScheduleAttendance.setPersonId(committeeMembership.getRolodexId().toString());
                    committeeScheduleAttendance.setNonEmployeeFlag(true);
                } else {
                    committeeScheduleAttendance.setPersonId(committeeMembership.getPersonId());
                    committeeScheduleAttendance.setNonEmployeeFlag(false);
                }
                committeeScheduleAttendance.setPersonName(committeeMembership.getPersonName());
                if (isAlternate(committeeMembership, commSchedule.getScheduledDate())) {
                    committeeScheduleAttendance.setAlternateFlag(true);
                } else {
                    committeeScheduleAttendance.setAlternateFlag(false);

                }
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
                committeeScheduleAttendance.setRoleName(getRoleNameForMembership(committeeMembership, commSchedule
                        .getScheduledDate()));
                memberAbsentBean.setAttendance(committeeScheduleAttendance);
                memberAbsentBeans.add(memberAbsentBean);
            }
        }
    }

    /*
     * check if person is in member present.
     */
    private boolean isInMemberPresent(List<MemberPresentBean> memberPresentBeans, CommitteeMembership committeeMembership) {
        boolean isPresent = false;
        for (MemberPresentBean memberPresentBean : memberPresentBeans) {
            if (memberPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isBlank(committeeMembership.getPersonId())
                    && memberPresentBean.getAttendance().getPersonId().equals(committeeMembership.getRolodexId().toString())) {
                isPresent = true;
                break;
            } else if (!memberPresentBean.getAttendance().getNonEmployeeFlag()
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
    private boolean isInOtherPresent(List<OtherPresentBean> otherPresentBeans, CommitteeMembership committeeMembership) {
        boolean isPresent = false;
        for (OtherPresentBean otherPresentBean : otherPresentBeans) {
            if (otherPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isBlank(committeeMembership.getPersonId())
                    && otherPresentBean.getAttendance().getPersonId().equals(committeeMembership.getRolodexId().toString())) {
                isPresent = true;
                break;
            } else if (!otherPresentBean.getAttendance().getNonEmployeeFlag()
                    && StringUtils.isNotBlank(committeeMembership.getPersonId())
                    && otherPresentBean.getAttendance().getPersonId().equals(committeeMembership.getPersonId())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }


}
