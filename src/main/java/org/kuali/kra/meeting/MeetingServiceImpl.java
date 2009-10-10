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

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.kuali.kra.budget.personnel.JobCode;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.MembershipRole;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MeetingServiceImpl implements MeetingService {

    BusinessObjectService businessObjectService;

    public Date getAgendaGenerationDate(Long scheduleId) {
        Map fieldValues = new HashMap();
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

    public void SaveMeetingDetails(CommitteeSchedule committeeSchedule, List<? extends PersistableBusinessObject> deletedBos) {
        committeeSchedule.setStartTime(addHrMinToDate(committeeSchedule.getStartTime(), committeeSchedule.getViewStartTime()));
        committeeSchedule.setEndTime(addHrMinToDate(committeeSchedule.getEndTime(), committeeSchedule.getViewEndTime()));
        committeeSchedule.setTime(addHrMinToDate(committeeSchedule.getTime(), committeeSchedule.getViewTime()));

        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }
        businessObjectService.save(committeeSchedule);
        // a hook to display "successfully saved" message
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorKey("message.saved");
        GlobalVariables.getMessageList().add(errorMessage);

    }

    public String getStandardReviewComment(String protocolContingencyCode) {
        String description = null;
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("protocolContingencyCode", protocolContingencyCode);
        ProtocolContingency protocolContingency = (ProtocolContingency)businessObjectService.findByPrimaryKey(ProtocolContingency.class, queryMap);
        if (protocolContingency!= null) {
            description = protocolContingency.getDescription();
        }
        return description;
    }

    private Timestamp addHrMinToDate(Timestamp time, Time12HrFmt viewTime) {
        java.util.Date dt = new java.util.Date(time.getTime());
        dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
        return new Timestamp(DateUtils.addMinutes(dt, viewTime.findMinutes()).getTime());
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void populateFormHelper(ActionForm form, HttpServletRequest request) {
        Map fieldValues = new HashMap();
        fieldValues.put("id", request.getParameter("scheduleId"));
        CommitteeSchedule commSchedule = (CommitteeSchedule) businessObjectService.findByPrimaryKey(CommitteeSchedule.class,
                fieldValues);
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
        if (isEmptyAttendance) {
            initAttendance(commSchedule);
        }
        populateAttendanceToForm(((MeetingForm) form).getMeetingHelper(), commSchedule.getCommittee().getCommitteeMemberships(),
                commSchedule);
        if (isEmptyAttendance) {
            // reset to empty after it is initiatiated to populate form
            commSchedule.setCommitteeScheduleAttendances(new ArrayList<CommitteeScheduleAttendance>());
        }
        
       ((MeetingForm) form).getMeetingHelper().setAgendaGenerationDate(getAgendaGenerationDate(commSchedule.getId()));
        ((MeetingForm) form).getMeetingHelper().setCommitteeSchedule(commSchedule);

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("MM/dd/yyyy");

        String label = commSchedule.getCommittee().getCommitteeName() + " #" + request.getParameter("lineNum") + " Meeting "
                + dateFormat.format(commSchedule.getScheduledDate());
        ((MeetingForm) form).getMeetingHelper().setTabLabel(label);

    }

    private void populateAttendanceToForm(MeetingHelper meetingHelper, List<CommitteeMembership> committeeMemberships,
            CommitteeSchedule commSchedule) {
        for (CommitteeScheduleAttendance committeeScheduleAttendance : commSchedule.getCommitteeScheduleAttendances()) {
            MembershipRole role = getRole(committeeScheduleAttendance, committeeMemberships, commSchedule.getScheduledDate());
            if (committeeScheduleAttendance.getGuestFlag()) {
                OtherPresentBean otherPresentBean = new OtherPresentBean();
                otherPresentBean.setAttendance(committeeScheduleAttendance);
                if (role == null) {
                    otherPresentBean.setMember(false);
                    otherPresentBean.getRole().setDescription("Guest");
                }
                else {
                    otherPresentBean.setMember(true);
                    otherPresentBean.setRole(role);
                }
                meetingHelper.getOtherPresentBeans().add(otherPresentBean);
            }
            else {
                MemberPresentBean memberPresentBean = new MemberPresentBean();
                memberPresentBean.setAttendance(committeeScheduleAttendance);
                memberPresentBean.setRole(role);
                meetingHelper.getMemberPresentBeans().add(memberPresentBean);
            }
        }

        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (!isInMemberPresent(meetingHelper.getMemberPresentBeans(), committeeMembership)
                    && !isInOtherPresent(meetingHelper.getOtherPresentBeans(), committeeMembership)) {
            //if (!isPresent && !isAlternate(committeeMembership, commSchedule.getScheduledDate())) {
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
                memberAbsentBean.setRole(getRole(committeeMembership, commSchedule.getScheduledDate()));
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
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

    private boolean isInMemberPresent(List<MemberPresentBean> memberPresentBeans, CommitteeMembership committeeMembership) {
        boolean isPresent = false;
        for (MemberPresentBean memberPresentBean : memberPresentBeans) {
            if (memberPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isBlank(committeeMembership.getPersonId())
                    && memberPresentBean.getAttendance().getPersonId().equals(committeeMembership.getRolodexId().toString())) {
                isPresent = true;
                break;
            }
            else if (!memberPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isNotBlank(committeeMembership.getPersonId())
                    && memberPresentBean.getAttendance().getPersonId().equals(committeeMembership.getPersonId())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;        
    }
    
    private boolean isInOtherPresent(List<OtherPresentBean> otherPresentBeans, CommitteeMembership committeeMembership) {
        boolean isPresent = false;
        for (OtherPresentBean otherPresentBean : otherPresentBeans) {
            if (otherPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isBlank(committeeMembership.getPersonId())
                    && otherPresentBean.getAttendance().getPersonId().equals(committeeMembership.getRolodexId().toString())) {
                isPresent = true;
                break;
            }
            else if (!otherPresentBean.getAttendance().getNonEmployeeFlag() && StringUtils.isNotBlank(committeeMembership.getPersonId())
                    && otherPresentBean.getAttendance().getPersonId().equals(committeeMembership.getPersonId())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;        
    }
    
    public void initAttendance(CommitteeSchedule commSchedule) {
        // TODO : spec requires to sort this list in alphabetically name order
        // should all comm member in the list, then this panel name is fitting well ?
        List<CommitteeMembership> committeeMemberships = commSchedule.getCommittee().getCommitteeMemberships();
        for (CommitteeMembership committeeMembership : committeeMemberships) {
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
            if (committeeMembership.getMembershipTypeCode().equals("1")) {
                // only voting memebers will be added initially
                if (isAlternate(committeeMembership, commSchedule.getScheduledDate())) {
                    committeeScheduleAttendance.setAlternateFlag(true);
                }
                else {
                    committeeScheduleAttendance.setAlternateFlag(false);

                }
                commSchedule.getCommitteeScheduleAttendances().add(committeeScheduleAttendance);
            }
            else {
                if (isAlternate(committeeMembership, commSchedule.getScheduledDate())) {
                    // TODO : need a flag to decide whther it's voting member or not
                    // is it possible alternate is not a voting member. check coeus
                    committeeScheduleAttendance.setAlternateFlag(true);
                    commSchedule.getCommitteeScheduleAttendances().add(committeeScheduleAttendance);
                }

            }
        }

    }

    private boolean isAlternate(CommitteeMembership committeeMembership, Date scheduledDate) {
        boolean isAlternate = false;
        for (CommitteeMembershipRole membershipRole : committeeMembership.getMembershipRoles()) {
            if (membershipRole.getMembershipRoleCode().equals("12") && !membershipRole.getStartDate().after(scheduledDate)
                    && !membershipRole.getEndDate().before(scheduledDate)) {
                isAlternate = true;
                break;
            }
        }
        return isAlternate;
    }

    private MembershipRole getRole(CommitteeScheduleAttendance committeeScheduleAttendance,
            List<CommitteeMembership> committeeMemberships, Date scheduledDate) {
        MembershipRole role = null;
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if ((committeeScheduleAttendance.getNonEmployeeFlag() && committeeMembership.getRolodexId() != null 
                    && committeeScheduleAttendance.getPersonId().equals(committeeMembership.getRolodexId().toString()))
                    || (!committeeScheduleAttendance.getNonEmployeeFlag() && committeeScheduleAttendance.getPersonId().equals(
                            committeeMembership.getPersonId()))) {
                role = getRole(committeeMembership, scheduledDate);
                break;
            }
        }

        return role;
    }

    private MembershipRole getRole(CommitteeMembership committeeMembership, Date scheduledDate) {
        MembershipRole role = null;
        for (CommitteeMembershipRole membershipRole : committeeMembership.getMembershipRoles()) {
            if (!membershipRole.getStartDate().after(scheduledDate) && !membershipRole.getEndDate().before(scheduledDate)) {
                role = membershipRole.getMembershipRole();
                break;
            }
        }
        return role;
    }

}
