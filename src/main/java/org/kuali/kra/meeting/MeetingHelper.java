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

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

public class MeetingHelper implements Serializable {

    private static final long serialVersionUID = 2363534404324211441L;
    private static final String FIELD_SEPARAATOR = "#f#";
    private static final String NAMESPACE = "KC-UNT";
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
    private boolean modifySchedule = false;
    private boolean viewSchedule = false;
    private List<ScheduleAgenda> scheduleAgendas;
    private List<CommScheduleMinuteDoc> minuteDocs;
    private List<ProtocolCorrespondence> correspondences;
    private String reportType;
    private String viewId;
    private Boolean printRooster;
    private Boolean printFutureScheduledMeeting;
    private boolean hideReviewerName;
    private static String minutesSentMessage;
    private static String agendaSentMessage;
    private ProtocolCorrespondence protocolCorrespondence;
    private List<ProtocolCorrespondence> regeneratedCorrespondences;

    private static final String AGENDA_SENT_MESSAGE = "message.disclosure.submit.thankyou";
    private static final String MINUTES_SENT_MESSAGE = "message.disclosure.submit.thankyou";

    private static final String MESSAGE_COMMITTEESCHEDULE_AGENDASENT = "message.committeeSchedule.agendaSent";
    private static final String MESSAGE_COMMITTEESCHEDULE_MINUTESSENT = "message.committeeSchedule.minutesSent";
    private transient KraAuthorizationService kraAuthorizationService;

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
        scheduleAgendas = new ArrayList<ScheduleAgenda>();
        minuteDocs = new ArrayList<CommScheduleMinuteDoc>();
        correspondences = new ArrayList<ProtocolCorrespondence>() ;
        printRooster = new Boolean(false);
        printFutureScheduledMeeting = new Boolean(false);
 //       hideReviewerName = getReviewerCommentsService().isHideReviewerName();
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
        List<CommitteeScheduleAttendance> attendances = new ArrayList<CommitteeScheduleAttendance>();
        for (MemberPresentBean memberPresentBean : this.getMemberPresentBeans()) {
            memberPresentBean.getAttendance().setGuestFlag(false);
            attendances.add(memberPresentBean.getAttendance());
        }
        for (OtherPresentBean otherPresentBean : this.getOtherPresentBeans()) {
            otherPresentBean.getAttendance().setGuestFlag(true);
            attendances.add(otherPresentBean.getAttendance());
        }
        this.setDeletedAttendances(committeeSchedule.getCommitteeScheduleAttendances());
        committeeSchedule.setCommitteeScheduleAttendances(attendances);
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

    /*
     * Utility method to check whether user has permission to view/modify schedule. This is needed if user enter here thru url not
     * from the schedule 'maintain' button.
     */
    public boolean hasViewModifySchedulePermission() {
        return this.canModifySchedule() || (this.canViewSchedule() && this.form.isReadOnly());
    }
    
    public boolean canModifySchedule() {
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_SCHEDULE, committeeSchedule.getCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    public boolean getCanModifySchedule() {
        return  canModifySchedule();
    }

    public boolean canViewSchedule() {
        CommitteeTask task = new CommitteeScheduleTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getCommittee(), committeeSchedule);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    public boolean getCanViewSchedule() {
        return canViewSchedule();
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

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    /**
     * Get the principalId of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
   }


    public List<ScheduleAgenda> getScheduleAgendas() {
        return scheduleAgendas;
    }


    public void setScheduleAgendas(List<ScheduleAgenda> scheduleAgendas) {
        this.scheduleAgendas = scheduleAgendas;
    }


    public List<CommScheduleMinuteDoc> getMinuteDocs() {
        return minuteDocs;
    }


    public void setMinuteDocs(List<CommScheduleMinuteDoc> minuteDocs) {
        this.minuteDocs = minuteDocs;
    }


    public List<ProtocolCorrespondence> getCorrespondences() {
        return correspondences;
    }


    public void setCorrespondences(List<ProtocolCorrespondence> correspondences) {
        this.correspondences = correspondences;
    }


    public String getReportType() {
        return reportType;
    }


    public void setReportType(String reportType) {
        this.reportType = reportType;
    }


    public String getViewId() {
        return viewId;
    }


    public void setViewId(String viewId) {
        this.viewId = viewId;
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

    private ReviewCommentsService getReviewerCommentsService() {
        return KraServiceLocator.getService(ReviewCommentsService.class);
    }

    public boolean isHideReviewerName() {
        return hideReviewerName;
    }


    public void setHideReviewerName(boolean hideReviewerName) {
        this.hideReviewerName = hideReviewerName;
    }

    public String getMinutesSentMessage() {
        if (minutesSentMessage == null) {
            minutesSentMessage = KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(MESSAGE_COMMITTEESCHEDULE_MINUTESSENT);
        }
        return minutesSentMessage;
    }
    
    public String getAgendaSentMessage() {
        if (agendaSentMessage == null) {
            agendaSentMessage = KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(MESSAGE_COMMITTEESCHEDULE_AGENDASENT);
        }
        return agendaSentMessage;
    }


    public ProtocolCorrespondence getProtocolCorrespondence() {
        return protocolCorrespondence;
    }


    public void setProtocolCorrespondence(ProtocolCorrespondence protocolCorrespondence) {
        this.protocolCorrespondence = protocolCorrespondence;
    }


    public List<ProtocolCorrespondence> getRegeneratedCorrespondences() {
        return regeneratedCorrespondences;
    }


    public void setRegeneratedCorrespondences(List<ProtocolCorrespondence> regeneratedCorrespondences) {
        this.regeneratedCorrespondences = regeneratedCorrespondences;
    }
 
    public boolean isIrbAdmin() {
        return getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IRB_ADMINISTRATOR);
    }
    
    protected KraAuthorizationService getKraAuthorizationService() {
        if (this.kraAuthorizationService == null) {
            this.kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        }
        
        return this.kraAuthorizationService;
    }
    

}
