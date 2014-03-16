/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.meeting;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("deprecation")
@Transactional
public abstract class MeetingServiceImplBase<CS extends CommitteeScheduleBase<CS, CMT, ?, CSM>,
                                         CSM extends CommitteeScheduleMinuteBase<CSM,CS>, 
                                         CMT extends CommitteeBase<CMT, ?, CS>>

                                         implements CommonMeetingService<CS, CSM> {

    BusinessObjectService businessObjectService;
    
    SequenceAccessorService sequenceAccessorService;
    
    DateTimeService dateTimeService;
    
    private static final String PROTOCOL_SUBMISSIONS_REF_ID = "protocolSubmissions";
    private static final String COMMITTEE_SCHEDULE_MINUTES_REF_ID = "committeeScheduleMinutes";

    /*
     * 
     * This method is to get the last agenda generation date.
     * 
     * @param scheduleId
     * 
     * @return
     */
    protected Date getAgendaGenerationDate(Long scheduleId) {
        List<ScheduleAgendaBase> scheduleAgendas = getAgenda(scheduleId);
        if (scheduleAgendas.isEmpty()) {
            return null;
        }
        else {
            return new Date(scheduleAgendas.get(scheduleAgendas.size() - 1).getCreateTimestamp().getTime());
        }
    }

    
    
    /*
     * This method is to get the generated agenda for this committee schedule.
     */
    protected List<ScheduleAgendaBase> getAgenda(Long scheduleId) {
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("scheduleIdFk", scheduleId);

        return (List<ScheduleAgendaBase>) businessObjectService.findMatchingOrderBy(getScheduleAgendaBOClassHook(), fieldValues, "createTimestamp", true);
    }

    protected abstract Class<? extends ScheduleAgendaBase> getScheduleAgendaBOClassHook();
    
    
    

    /*
     * This method is get the meeting minute documents of the selected committee schedule
     */
    protected List<CommScheduleMinuteDocBase> getMinuteDoc(Long scheduleId) {
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("scheduleIdFk", scheduleId);

        return (List<CommScheduleMinuteDocBase>) businessObjectService.findMatchingOrderBy(getCommScheduleMinuteDocBOClassHook(), fieldValues, "createTimestamp", true);
    }
    
    protected abstract Class<? extends CommScheduleMinuteDocBase> getCommScheduleMinuteDocBOClassHook();

    
    
    /*
     * This method is to get all protocol correspondences of the protocols that are related
     * to this committee schedule. ie, protocols that have been submitted to this committee schedule.
     */
    protected List<ProtocolCorrespondence> getCorrespondences(CS committeeSchedule) {
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        List<Long> protocolIds = new ArrayList<Long>();
        List<ProtocolCorrespondence> correspondences = new ArrayList<ProtocolCorrespondence>();
        // TODO : check if want to use criteria/dao to get the list or use this loop
        for (ProtocolSubmissionBase submission : committeeSchedule.getLatestProtocolSubmissions()) {
            if (!protocolIds.contains(submission.getProtocolId())) {
                protocolIds.add(submission.getProtocolId());
                fieldValues.put("protocolId", submission.getProtocolId());

                correspondences.addAll((List<? extends ProtocolCorrespondence>) businessObjectService.findMatching(getProtocolCorrespondenceBOClassHook(), fieldValues));
            }
        }
        return correspondences;
    }

    protected abstract Class<? extends ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook();

    
    
    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#SaveMeetingDetails(org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase, java.util.List)
     */
    public void saveMeetingDetails(CS committeeSchedule, List<? extends PersistableBusinessObject> deletedBos) {
        committeeSchedule.setStartTime(addHrMinToDate(committeeSchedule.getStartTime(), committeeSchedule.getViewStartTime()));
        committeeSchedule.setEndTime(addHrMinToDate(committeeSchedule.getEndTime(), committeeSchedule.getViewEndTime()));
        committeeSchedule.setTime(addHrMinToDate(committeeSchedule.getTime(), committeeSchedule.getViewTime()));

        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }
        
        refreshAndSaveSchedule(committeeSchedule);
        
        // display "successfully saved" message
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_SAVED);

    }

    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#getStandardReviewComment(java.lang.String)
     */
    public String getStandardReviewComment(String protocolContingencyCode) {
        String description = null;
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("protocolContingencyCode", protocolContingencyCode);
        ProtocolContingencyBase protocolContingency = businessObjectService.findByPrimaryKey(getProtocolContingencyBOClassHook(), queryMap);
        if (protocolContingency != null) {
            description = protocolContingency.getDescription();
        }
        return description;
    }
    
    protected abstract Class<? extends ProtocolContingencyBase> getProtocolContingencyBOClassHook();

    /*
     * utility methods by adding minutes to date
     */
    protected Timestamp addHrMinToDate(Timestamp time, Time12HrFmt viewTime) {
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
    
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#addOtherAction(org.kuali.coeus.common.committee.impl.meeting.CommScheduleActItemBase,
     *      org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase)
     */
    public void addOtherAction(CommScheduleActItemBase newOtherAction, CS committeeSchedule) {
        newOtherAction.refreshReferenceObject("scheduleActItemType");
        newOtherAction.setCommScheduleActItemsId(getNextCommScheduleActItemId(newOtherAction.getClass()));
        newOtherAction.setScheduleIdFk(committeeSchedule.getId());
        newOtherAction.setActionItemNumber(getNextActionItemNumber(committeeSchedule));
        committeeSchedule.getCommScheduleActItems().add(newOtherAction);
    }
    
    protected Long getNextCommScheduleActItemId(Class boClass) {
        Long nextCommScheduleActItemId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_MEETING_ID", boClass);
        return nextCommScheduleActItemId;
    }

    /*
     * find the max action number and increase by one.
     */
    protected Integer getNextActionItemNumber(CS committeeSchedule) {
        Integer nextActionItemNumber = committeeSchedule.getCommScheduleActItems().size();
        for (CommScheduleActItemBase commScheduleActItem : committeeSchedule.getCommScheduleActItems()) {
            if (commScheduleActItem.getActionItemNumber() > nextActionItemNumber) {
                nextActionItemNumber = commScheduleActItem.getActionItemNumber();
            }
        }
        return nextActionItemNumber + 1;

    }

    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#deleteOtherAction(org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase, int,
     *      java.util.List)
     */
    public void deleteOtherAction(CS committeeSchedule, int itemNumber, List<CommScheduleActItemBase> deletedOtherActions) {
        CommScheduleActItemBase commScheduleActItem = committeeSchedule.getCommScheduleActItems().get(itemNumber);
        if (commScheduleActItem.getCommScheduleActItemsId() != null) {
            deletedOtherActions.add(commScheduleActItem);
        }
        committeeSchedule.getCommScheduleActItems().remove(itemNumber);

    }


    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#markAbsent(java.util.List, java.util.List, int)
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
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#presentVoting(org.kuali.coeus.common.committee.impl.meeting.MeetingHelperBase, int)
     */
    public void presentVoting(MeetingHelperBase meetingHelper, int itemNumber) {
        MemberAbsentBean memberAbsentBean = meetingHelper.getMemberAbsentBeans().get(itemNumber);
        MemberPresentBean memberPresentBean = new MemberPresentBean();
        memberPresentBean.setAttendance(memberAbsentBean.getAttendance());
        memberPresentBean.getAttendance().setAlternateFlag(
                isAlternateForMember((CS) meetingHelper.getCommitteeSchedule(), memberPresentBean.getAttendance(), meetingHelper.getCommitteeSchedule().getScheduledDate()));
        meetingHelper.getMemberPresentBeans().add(memberPresentBean);
        meetingHelper.getMemberAbsentBeans().remove(itemNumber);
    }

    /*
     * This is a utility method to reset alternate flag before 'present voting'
     */
    protected boolean isAlternateForMember(CS commonCommitteeSchedule, CommitteeScheduleAttendanceBase committeeScheduleAttendance, Date scheduledDate) {
        boolean isAlternate = false;
        for (CommitteeMembershipBase committeeMembership : commonCommitteeSchedule.getParentCommittee().getCommitteeMemberships()) {
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
    protected boolean isActiveMembership(CommitteeMembershipBase committeeMembership, Date scheduledDate) {
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
    protected boolean isAlternate(CommitteeMembershipBase committeeMembership, Date scheduledDate) {
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
    protected void getRoleName(CommitteeScheduleAttendanceBase committeeScheduleAttendance,
            List<CommitteeMembershipBase> committeeMemberships, Date scheduleDate) {
        String roleName = "";
        for (CommitteeMembershipBase committeeMembership : committeeMemberships) {
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
    protected String getRoleNameForMembership(CommitteeMembershipBase committeeMembership, Date scheduledDate) {
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
    protected boolean isActiveMember(CommitteeScheduleAttendanceBase committeeScheduleAttendance,
            List<CommitteeMembershipBase> committeeMemberships, Date scheduleDate) {
        boolean isActiveMember = false;
        for (CommitteeMembershipBase committeeMembership : committeeMemberships) {
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
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#presentOther(org.kuali.coeus.common.committee.impl.meeting.MeetingHelperBase, int)
     */
    public void presentOther(MeetingHelperBase meetingHelper, int itemNumber) {
        MemberAbsentBean memberAbsentBean = meetingHelper.getMemberAbsentBeans().get(itemNumber);
        OtherPresentBeanBase otherPresentBean = getNewOtherPresentBeanInstanceHook();
        otherPresentBean.setAttendance(memberAbsentBean.getAttendance());
        otherPresentBean.setMember(true);
        meetingHelper.getOtherPresentBeans().add(otherPresentBean);
        meetingHelper.getMemberAbsentBeans().remove(itemNumber);
    }
    
    protected abstract OtherPresentBeanBase getNewOtherPresentBeanInstanceHook();

    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#addOtherPresent(org.kuali.coeus.common.committee.impl.meeting.MeetingHelperBase)
     */
    public void addOtherPresent(MeetingHelperBase meetingHelper) {
        OtherPresentBeanBase otherPresentBean = getNewOtherPresentBeanInstanceHook();
        otherPresentBean.setMember(false);
        meetingHelper.getNewOtherPresentBean().getAttendance().setRoleName("Guest");
        otherPresentBean.setAttendance(meetingHelper.getNewOtherPresentBean().getAttendance());
        memberHandling(meetingHelper, otherPresentBean);
        meetingHelper.getOtherPresentBeans().add(otherPresentBean);
        meetingHelper.setNewOtherPresentBean(getNewOtherPresentBeanInstanceHook());
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
    protected void memberHandling(MeetingHelperBase meetingHelper, OtherPresentBeanBase otherPresentBean) {
        MemberAbsentBean matchedMemberAbsentBean = null;
        for (MemberAbsentBean memberAbsentBean : meetingHelper.getMemberAbsentBeans()) {
            if (isAbsentMember(memberAbsentBean, otherPresentBean)) {
                otherPresentBean.setMember(true);
                getRoleName(otherPresentBean.getAttendance(), meetingHelper.getCommitteeSchedule().getParentCommittee()
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
    protected boolean isAbsentMember(MemberAbsentBean memberAbsentBean, OtherPresentBeanBase otherPresentBean) {
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
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#deleteOtherPresent(org.kuali.coeus.common.committee.impl.meeting.MeetingHelperBase, int)
     */
    public void deleteOtherPresent(MeetingHelperBase meetingHelper, int itemNumber) {
        OtherPresentBeanBase otherPresentBean = meetingHelper.getOtherPresentBeans().get(itemNumber);
        if (otherPresentBean.isMember()) {
            MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
            memberAbsentBean.setAttendance(otherPresentBean.getAttendance());
            meetingHelper.getMemberAbsentBeans().add(memberAbsentBean);
        }
        meetingHelper.getOtherPresentBeans().remove(itemNumber);
    }


    /**
     * {@inheritDoc}
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#addCommitteeScheduleMinute(org.kuali.coeus.common.committee.impl.meeting.MeetingHelperBase)
     */
    public void addCommitteeScheduleMinute(MeetingHelperBase meetingHelper) {
        meetingHelper.getNewCommitteeScheduleMinute().refreshReferenceObject("minuteEntryType");
        meetingHelper.getNewCommitteeScheduleMinute().refreshReferenceObject("protocol");
        meetingHelper.getNewCommitteeScheduleMinute().refreshReferenceObject("commScheduleActItem");
        
        Long submissionId = null;
        if (meetingHelper.getNewCommitteeScheduleMinute().getProtocol() != null) {
            submissionId = meetingHelper.getNewCommitteeScheduleMinute().getProtocol().getProtocolSubmission().getSubmissionId();
        }
        Long scheduleId = meetingHelper.getCommitteeSchedule().getId();
        Integer entryNumber = getNextMinuteEntryNumber((CS) meetingHelper.getCommitteeSchedule());
        String principalName = GlobalVariables.getUserSession().getPrincipalName();
        String minuteEntryTypeCode = meetingHelper.getNewCommitteeScheduleMinute().getMinuteEntryTypeCode();
        Timestamp createTimestamp = dateTimeService.getCurrentTimestamp();
        
        meetingHelper.getNewCommitteeScheduleMinute().setSubmissionIdFk(submissionId);
        meetingHelper.getNewCommitteeScheduleMinute().setScheduleIdFk(scheduleId);
        meetingHelper.getNewCommitteeScheduleMinute().setEntryNumber(entryNumber);
        meetingHelper.getNewCommitteeScheduleMinute().setCreateUser(principalName);
        meetingHelper.getNewCommitteeScheduleMinute().setUpdateUser(principalName);
        meetingHelper.getNewCommitteeScheduleMinute().setCreateTimestamp(createTimestamp);
        // set this up for display when 'add'
        meetingHelper.getNewCommitteeScheduleMinute().setUpdateTimestamp(createTimestamp);
        
        if (MinuteEntryType.ATTENDANCE.equals(minuteEntryTypeCode)) {
            addAttendanceMinuteEntry(meetingHelper);
        } else if (MinuteEntryType.ACTION_ITEM.equals(minuteEntryTypeCode)) {
            addActionItem(meetingHelper);
        } else if (MinuteEntryType.PROTOCOL.equals(minuteEntryTypeCode)) {
            resetActionItemFields(meetingHelper);
        } else {
            resetProtocolFields(meetingHelper);
            resetActionItemFields(meetingHelper);
        }
        
        ((List<CSM>)(meetingHelper.getCommitteeSchedule().getCommitteeScheduleMinutes())).add((CSM) meetingHelper.getNewCommitteeScheduleMinute());
        meetingHelper.setNewCommitteeScheduleMinute(getNewCommitteeScheduleMinuteInstanceHook());
    }
    
    protected abstract CSM getNewCommitteeScheduleMinuteInstanceHook();
    
    /*
     * Utility method to figure out next entry number for this schedule.
     */
    protected Integer getNextMinuteEntryNumber(CS commonCommitteeSchedule) {
        Integer nextMinuteEntryNumber = commonCommitteeSchedule.getCommitteeScheduleMinutes().size();
        for (CSM committeeScheduleMinute : commonCommitteeSchedule.getCommitteeScheduleMinutes()) {
            if (committeeScheduleMinute.getEntryNumber() > nextMinuteEntryNumber) {
                nextMinuteEntryNumber = committeeScheduleMinute.getEntryNumber();
            }
        }
        return nextMinuteEntryNumber + 1;
    }
    
    /*
     * Adds a minute entry to an attendance minute type
     */
    protected void addAttendanceMinuteEntry(MeetingHelperBase meetingHelper) {
        if (meetingHelper.getNewCommitteeScheduleMinute().isGenerateAttendance() && meetingHelper.isJsDisabled()) {
            // in case JS is disabled
            meetingHelper.getNewCommitteeScheduleMinute().setMinuteEntry(
                    generateAttendanceComment(meetingHelper.getMemberPresentBeans(), meetingHelper.getOtherPresentBeans(),
                            (CS) meetingHelper.getCommitteeSchedule()));
        }
        resetProtocolFields(meetingHelper);
        resetActionItemFields(meetingHelper);
    }

    /*
     * This is to generate comment for minute entry Type of 'Attendance' and 'generate attendance is checked
     */
    protected String generateAttendanceComment(List<MemberPresentBean> memberPresentBeans, List<OtherPresentBeanBase> otherPresentBeans, CS commonCommitteeSchedule) {
        String comment = "";
        String eol = System.getProperty("line.separator");
        for (MemberPresentBean memberPresentBean : memberPresentBeans) {
            if (StringUtils.isNotBlank(comment)) {
                comment = comment + eol;
            }
            comment = comment + memberPresentBean.getAttendance().getPersonName();
            if (StringUtils.isNotBlank(memberPresentBean.getAttendance().getAlternateFor())) {
                comment = comment + " Alternate For: "
                        + getAlternateForName(commonCommitteeSchedule, memberPresentBean.getAttendance().getAlternateFor());
            }
        }
        for (OtherPresentBeanBase otherPresentBean : otherPresentBeans) {
            if (StringUtils.isNotBlank(comment)) {
                comment = comment + eol;
            }
            comment = comment + otherPresentBean.getAttendance().getPersonName() + " Guest ";
        }
        return comment;
    }

    /*
     * Utility to get person name for 'alternate for'. This name is used when 'generate attendance' is checked.
     */
    protected String getAlternateForName(CS commonCommitteeSchedule, String alternateFor) {

        String personName = "";
        for (CommitteeMembershipBase committeeMembership : commonCommitteeSchedule.getParentCommittee().getCommitteeMemberships()) {
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
    
    /*
     * Adds an action item to an action item minute type
     */
    protected void addActionItem(MeetingHelperBase meetingHelper) {
        if (meetingHelper.getNewCommitteeScheduleMinute().getCommScheduleActItemsIdFk() != null) {
            // in case adding non-persisted action item
            meetingHelper.getNewCommitteeScheduleMinute().setCommScheduleActItem(
                    getActionItem(meetingHelper.getNewCommitteeScheduleMinute().getCommScheduleActItemsIdFk(),
                            meetingHelper.getCommitteeSchedule().getCommScheduleActItems()));
        }
        resetProtocolFields(meetingHelper);
    }
    
    /*
     * Empties protocol fields if the minute type is not PROTOCOL
     */
    protected void resetProtocolFields(MeetingHelperBase meetingHelper) {
        meetingHelper.getNewCommitteeScheduleMinute().setProtocolIdFk(null);
        meetingHelper.getNewCommitteeScheduleMinute().setProtocol(null);
    }
    
    /*
     * Empties action item fields if the minute type is not ACTION_ITEM
     */
    protected void resetActionItemFields(MeetingHelperBase meetingHelper) {
        meetingHelper.getNewCommitteeScheduleMinute().setCommScheduleActItemsIdFk(null);
        meetingHelper.getNewCommitteeScheduleMinute().setCommScheduleActItem(null);
    }
    
    /*
     * Utility to find a CommScheduleActItemBase with ID commScheduleActItemsIdFk
     */
    protected CommScheduleActItemBase getActionItem(Long commScheduleActItemsIdFk, List<CommScheduleActItemBase> commScheduleActItems) {
        CommScheduleActItemBase actionItem = null;
        
        for (CommScheduleActItemBase commScheduleActItem : commScheduleActItems) {
            if (commScheduleActItem.getCommScheduleActItemsId().equals(commScheduleActItemsIdFk)) {
                actionItem = commScheduleActItem;
                break;
            }
        }
        
        return actionItem;
    }


    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#deleteCommitteeScheduleMinute(org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase,
     *      java.util.List, int)
     */
    public void deleteCommitteeScheduleMinute(CS committeeSchedule, List<CSM> deletedCommitteeScheduleMinutes, int itemNumber) {
        CSM committeeScheduleMinute = committeeSchedule.getCommitteeScheduleMinutes().get(itemNumber);
        if (committeeScheduleMinute.getCommScheduleMinutesId() != null) {
            deletedCommitteeScheduleMinutes.add(committeeScheduleMinute);
        }
        committeeSchedule.getCommitteeScheduleMinutes().remove(itemNumber);
    }


    /**
     * 
     * @see org.kuali.coeus.common.committee.impl.meeting.CommonMeetingService#populateFormHelper(org.kuali.coeus.common.committee.impl.meeting.MeetingHelperBase,
     *      org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase, int)
     */
    public void populateFormHelper(MeetingHelperBase meetingHelper, CS commSchedule, int lineNumber) {
        for (ProtocolSubmissionBase protocolSubmission : commSchedule.getLatestProtocolSubmissions()) {
            ProtocolSubmittedBean protocolSubmittedBean = new ProtocolSubmittedBean();
            ProtocolPersonBase pi = protocolSubmission.getProtocol().getPrincipalInvestigator();
            protocolSubmittedBean.setPersonId(pi.getPersonId());
            protocolSubmittedBean.setPersonName(pi.getPersonName());
            protocolSubmittedBean.setRolodexId(pi.getRolodexId());
            meetingHelper.getProtocolSubmittedBeans().add(protocolSubmittedBean);
        }
        if (commSchedule.getCommitteeScheduleAttendances().isEmpty()
                && !commSchedule.getParentCommittee().getCommitteeMemberships().isEmpty()) {
            initAttendance(meetingHelper.getMemberAbsentBeans(), commSchedule);
        }
        else {
            populateAttendanceToForm(meetingHelper, commSchedule.getParentCommittee().getCommitteeMemberships(), commSchedule);
        }

        meetingHelper.setAgendaGenerationDate(getAgendaGenerationDate(commSchedule.getId()));
        meetingHelper.setCommitteeSchedule(commSchedule);
        meetingHelper.setTabLabel(getMeetingTabTitle((CS) meetingHelper.getCommitteeSchedule(), lineNumber));
        meetingHelper.setScheduleAgendas(getAgenda(commSchedule.getId()));
        meetingHelper.setMinuteDocs(getMinuteDoc(commSchedule.getId()));     
        meetingHelper.setCorrespondences(getCorrespondences(commSchedule));
    }
    
    

    /*
     * set up title of the first header tab in meeting page. lineNumber is this selected schedule's item number in committee
     * schedule list
     */
    protected String getMeetingTabTitle(CS commonCommitteeSchedule, int lineNumber) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        return commonCommitteeSchedule.getParentCommittee().getCommitteeName() + " #" + lineNumber + " Meeting "
                + dateFormat.format(commonCommitteeSchedule.getScheduledDate());

    }

    /*
     * populate 3 attendance form beans
     */
    protected void populateAttendanceToForm(MeetingHelperBase meetingHelper, List<CommitteeMembershipBase> committeeMemberships, CS commSchedule) {
        populatePresentBean(meetingHelper, committeeMemberships, commSchedule);
        populateMemberAbsentBean(meetingHelper, committeeMemberships, commSchedule);

    }

    /*
     * populate memberpresentbean & otherpresentbean
     */
    protected void populatePresentBean(MeetingHelperBase meetingHelper, List<CommitteeMembershipBase> committeeMemberships,
            CS commSchedule) {
        meetingHelper.setOtherPresentBeans(new ArrayList<OtherPresentBeanBase>());
        meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
        for (CommitteeScheduleAttendanceBase committeeScheduleAttendance : commSchedule.getCommitteeScheduleAttendances()) {
            getRoleName(committeeScheduleAttendance, committeeMemberships, commSchedule.getScheduledDate());
            if (committeeScheduleAttendance.getGuestFlag()) {
                OtherPresentBeanBase otherPresentBean = getNewOtherPresentBeanInstanceHook();
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
    protected void populateMemberAbsentBean(MeetingHelperBase meetingHelper, List<CommitteeMembershipBase> committeeMemberships,
            CS commSchedule) {
        meetingHelper.setMemberAbsentBeans(new ArrayList<MemberAbsentBean>());
        for (CommitteeMembershipBase committeeMembership : committeeMemberships) {
            if (!isInMemberPresent(meetingHelper.getMemberPresentBeans(), committeeMembership)
                    && !isInOtherPresent(meetingHelper.getOtherPresentBeans(), committeeMembership)) {
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();

                CommitteeScheduleAttendanceBase attendance = getNewCommitteeScheduleAttendanceInstanceHook();
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

    protected abstract CommitteeScheduleAttendanceBase getNewCommitteeScheduleAttendanceInstanceHook();
    
    

    /*
     * Init attendance if this meeting schedule is maintained for the first time.
     */
    protected void initAttendance(List<MemberAbsentBean> memberAbsentBeans, CS commSchedule) {
        List<CommitteeMembershipBase> committeeMemberships = commSchedule.getParentCommittee().getCommitteeMemberships();
        for (CommitteeMembershipBase committeeMembership : committeeMemberships) {
            if (isActiveMembership(committeeMembership, commSchedule.getScheduledDate())) {

                CommitteeScheduleAttendanceBase committeeScheduleAttendance = getNewCommitteeScheduleAttendanceInstanceHook();
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
    protected boolean isInMemberPresent(List<MemberPresentBean> memberPresentBeans, CommitteeMembershipBase committeeMembership) {
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
    protected boolean isInOtherPresent(List<OtherPresentBeanBase> otherPresentBeans, CommitteeMembershipBase committeeMembership) {
        boolean isPresent = false;
        for (OtherPresentBeanBase otherPresentBean : otherPresentBeans) {
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

    // this method will refresh the set of protocol submissions and review comments before saving because 
    // they could have been changed asynchronously in a different concurrent user session.
    private void refreshAndSaveSchedule(CS committeeSchedule) {
        // Since a refresh will wipe out all the newly added (unsaved) minutes from the schedule, we will
        // collect all newly added minutes in a separate collection and add them back after the refresh        
        List<CSM> preRefreshMinutes = committeeSchedule.getCommitteeScheduleMinutes();
        List<CSM> newlyAddedMinutes = new ArrayList<CSM>();  
        for(CSM minute:preRefreshMinutes) {
            if(null == minute.getCommScheduleMinutesId()) {
                newlyAddedMinutes.add(minute);
            }
        }
        committeeSchedule.refreshReferenceObject(COMMITTEE_SCHEDULE_MINUTES_REF_ID);
        List<CSM> postRefreshMinutes = committeeSchedule.getCommitteeScheduleMinutes();
        postRefreshMinutes.addAll(newlyAddedMinutes);
        committeeSchedule.refreshReferenceObject(PROTOCOL_SUBMISSIONS_REF_ID);
        
        businessObjectService.save(committeeSchedule);
    }


}
