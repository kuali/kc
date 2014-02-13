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
package org.kuali.kra.common.committee.meeting;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.service.KcAuthorizationService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MeetingHelperBase implements Serializable {

    private static final long serialVersionUID = 2363534404324211441L;
    private static final String FIELD_SEPARAATOR = "#f#";
    
    private MeetingFormBase form;
    private Date agendaGenerationDate;
    private CommitteeScheduleBase<?, ?, ?, ?> committeeSchedule;
    private List<ProtocolSubmittedBean> protocolSubmittedBeans;
    private CommScheduleActItemBase newOtherAction;
    private List<CommScheduleActItemBase> deletedOtherActions;
    private List<CommitteeScheduleAttendanceBase> deletedAttendances;
    // the label used for the first page tab.
    private String tabLabel;
    private List<MemberPresentBean> memberPresentBeans;
    private List<MemberAbsentBean> memberAbsentBeans;
    private List<OtherPresentBeanBase> otherPresentBeans;
    private OtherPresentBeanBase newOtherPresentBean;
    private String absenteeList;
    private CommitteeScheduleMinuteBase<?, ?> newCommitteeScheduleMinute;
    private List<CommitteeScheduleMinuteBase<?, ?>> deletedCommitteeScheduleMinutes;
    // It is for minute entry/attendance, and generate attendance comment by server if js is disabled.
    private boolean jsDisabled = false;
    private boolean modifySchedule = false;
    private boolean viewSchedule = false;
    private List<ScheduleAgendaBase> scheduleAgendas;
    private List<CommScheduleMinuteDocBase> minuteDocs;
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
    private List<CorrespondencePrintOption> correspondencesToPrint;

    private CommitteeScheduleAttachmentsBase newCommitteeScheduleAttachments;

    private static final String MESSAGE_COMMITTEESCHEDULE_AGENDASENT = "message.committeeSchedule.agendaSent";
    private static final String MESSAGE_COMMITTEESCHEDULE_MINUTESSENT = "message.committeeSchedule.minutesSent";
    private transient KcAuthorizationService kraAuthorizationService;

    public MeetingHelperBase(MeetingFormBase form) {
        this.form = form;
        committeeSchedule = getNewCommitteeScheduleInstanceHook();
        protocolSubmittedBeans = new ArrayList<ProtocolSubmittedBean>();
        memberPresentBeans = new ArrayList<MemberPresentBean>();
        memberAbsentBeans = new ArrayList<MemberAbsentBean>();
        otherPresentBeans = new ArrayList<OtherPresentBeanBase>();

        newOtherAction = getNewCommScheduleActItemInstanceHook();
        newCommitteeScheduleMinute = getNewCommitteeScheduleMinuteInstanceHook();

        newCommitteeScheduleAttachments= getNewCommitteeScheduleAttachmentsInstanceHook();

        newOtherPresentBean = getNewOtherPresentBeanInstanceHook();
        scheduleAgendas = new ArrayList<ScheduleAgendaBase>();
        minuteDocs = new ArrayList<CommScheduleMinuteDocBase>();
        correspondences = new ArrayList<ProtocolCorrespondence>() ;
        printRooster = new Boolean(false);
        printFutureScheduledMeeting = new Boolean(false);
 //       hideReviewerName = getReviewerCommentsService().isHideReviewerName();
        initDeletedList();
        initPrintCorrespondence();
    }

    protected abstract CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook();
    
    protected abstract OtherPresentBeanBase getNewOtherPresentBeanInstanceHook();
    
    protected abstract CommScheduleActItemBase getNewCommScheduleActItemInstanceHook();

    protected abstract CommitteeScheduleMinuteBase<?, ?> getNewCommitteeScheduleMinuteInstanceHook();


    protected abstract CommitteeScheduleBase<?, ?, ?, ?> getNewCommitteeScheduleInstanceHook();

    public MeetingFormBase getForm() {
        return form;
    }

    public void setForm(MeetingFormBase form) {
        this.form = form;
    }


    public CommScheduleActItemBase getNewOtherAction() {
        return newOtherAction;
    }

    public void setNewOtherAction(CommScheduleActItemBase newOtherAction) {
        this.newOtherAction = newOtherAction;
    }

    public String getTabLabel() {
        return tabLabel;
    }

    public void setTabLabel(String tabLabel) {
        this.tabLabel = tabLabel;
    }

    public CommitteeScheduleBase<?, ?, ?, ?> getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeScheduleBase<?, ?, ?, ?> committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Date getAgendaGenerationDate() {
        return agendaGenerationDate;
    }

    public void setAgendaGenerationDate(Date agendaGenerationDate) {
        this.agendaGenerationDate = agendaGenerationDate;
    }

    public List<CommScheduleActItemBase> getDeletedOtherActions() {
        return deletedOtherActions;
    }

    public void setDeletedOtherActions(List<CommScheduleActItemBase> deletedOtherActions) {
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

    public List<OtherPresentBeanBase> getOtherPresentBeans() {
        return otherPresentBeans;
    }

    public void setOtherPresentBeans(List<OtherPresentBeanBase> otherPresentBeans) {
        this.otherPresentBeans = otherPresentBeans;
    }

    public OtherPresentBeanBase getNewOtherPresentBean() {
        return newOtherPresentBean;
    }

    public void setNewOtherPresentBean(OtherPresentBeanBase newOtherPresentBean) {
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

    public List<CommitteeScheduleAttendanceBase> getDeletedAttendances() {
        return deletedAttendances;
    }

    public void setDeletedAttendances(List<CommitteeScheduleAttendanceBase> deletedAttendances) {
        this.deletedAttendances = deletedAttendances;
    }

    public CommitteeScheduleMinuteBase getNewCommitteeScheduleMinute() {
        return newCommitteeScheduleMinute;
    }

    public void setNewCommitteeScheduleMinute(CommitteeScheduleMinuteBase<?, ?> newCommitteeScheduleMinute) {
        this.newCommitteeScheduleMinute = newCommitteeScheduleMinute;
    }

    public List<CommitteeScheduleMinuteBase<?, ?>> getDeletedCommitteeScheduleMinutes() {
        return deletedCommitteeScheduleMinutes;
    }
    
    public CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachments() {
        return newCommitteeScheduleAttachments;
    }


    public void setNewCommitteeScheduleAttachments(CommitteeScheduleAttachmentsBase newCommitteeScheduleAttachments) {
        this.newCommitteeScheduleAttachments = newCommitteeScheduleAttachments;
    }

    public void setDeletedCommitteeScheduleMinutes(List<CommitteeScheduleMinuteBase<?, ?>> deletedCommitteeScheduleMinutes) {
        this.deletedCommitteeScheduleMinutes = deletedCommitteeScheduleMinutes;
    }
    
    /**
     * 
     * This method method is used to initialize the deleted bo list.
     */
    protected void initDeletedList() {
        setDeletedOtherActions(new ArrayList<CommScheduleActItemBase>());
        setDeletedCommitteeScheduleMinutes(new ArrayList<CommitteeScheduleMinuteBase<?, ?>>());
        setDeletedAttendances(new ArrayList<CommitteeScheduleAttendanceBase>());

    }

    /**
     * 
     * This method put all deleted bos to a list, so boservice can delete them.
     * 
     * @return
     */
    protected List<PersistableBusinessObject> getDeletedBos() {
        List<PersistableBusinessObject> deletedBos = new ArrayList<PersistableBusinessObject>();
        deletedBos.addAll(((MeetingFormBase) form).getMeetingHelper().getDeletedOtherActions());
        deletedBos.addAll(((MeetingFormBase) form).getMeetingHelper().getDeletedAttendances());
        deletedBos.addAll(((MeetingFormBase) form).getMeetingHelper().getDeletedCommitteeScheduleMinutes());
        return deletedBos;

    }

    /**
     * 
     * This method populate committeescheduleattendance from 3 beans, memberpresent/otherpresent/memberabsent.
     */
    protected void populateAttendancePreSave() {
        List<CommitteeScheduleAttendanceBase> attendances = new ArrayList<CommitteeScheduleAttendanceBase>();
        for (MemberPresentBean memberPresentBean : this.getMemberPresentBeans()) {
            memberPresentBean.getAttendance().setGuestFlag(false);
            attendances.add(memberPresentBean.getAttendance());
        }
        for (OtherPresentBeanBase otherPresentBean : this.getOtherPresentBeans()) {
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
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.MODIFY_SCHEDULE, committeeSchedule.getParentCommittee());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee);
    

    public boolean getCanModifySchedule() {
        return  canModifySchedule();
    }

    public boolean canViewSchedule() {
        CommitteeTaskBase task = getNewCommitteeScheduleTaskInstanceHook(TaskName.VIEW_SCHEDULE, committeeSchedule.getParentCommittee(), committeeSchedule);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract CommitteeScheduleTaskBase getNewCommitteeScheduleTaskInstanceHook(String taskName, CommitteeBase committee, CommitteeScheduleBase committeeSchedule);


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
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }

    /**
     * Get the principalId of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
   }


    public List<ScheduleAgendaBase> getScheduleAgendas() {
        return scheduleAgendas;
    }


    public void setScheduleAgendas(List<ScheduleAgendaBase> scheduleAgendas) {
        this.scheduleAgendas = scheduleAgendas;
    }


    public List<CommScheduleMinuteDocBase> getMinuteDocs() {
        return minuteDocs;
    }


    public void setMinuteDocs(List<CommScheduleMinuteDocBase> minuteDocs) {
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

    public boolean isHideReviewerName() {
        return hideReviewerName;
    }


    public void setHideReviewerName(boolean hideReviewerName) {
        this.hideReviewerName = hideReviewerName;
    }

    public String getMinutesSentMessage() {
        if (minutesSentMessage == null) {
            minutesSentMessage = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(MESSAGE_COMMITTEESCHEDULE_MINUTESSENT);
        }
        return minutesSentMessage;
    }
    
    public String getAgendaSentMessage() {
        if (agendaSentMessage == null) {
            agendaSentMessage = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(MESSAGE_COMMITTEESCHEDULE_AGENDASENT);
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
    
    
    protected KcAuthorizationService getKraAuthorizationService() {
        if (this.kraAuthorizationService == null) {
            this.kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        }
        
        return this.kraAuthorizationService;
    }
    
    public abstract boolean isAdmin();

    public List<CorrespondencePrintOption> getCorrespondencesToPrint() {
        return correspondencesToPrint;
    }

    public void setCorrespondencesToPrint(List<CorrespondencePrintOption> correspondencesToPrint) {
        this.correspondencesToPrint = correspondencesToPrint;
    }
    
    protected abstract void initPrintCorrespondence();

}
