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
package org.kuali.kra.committee.print;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRole;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.meeting.CommScheduleActItem;
import org.kuali.kra.meeting.CommitteeScheduleAttendance;
import org.kuali.kra.printing.xmlstream.PrintBaseXmlStream;
import org.kuali.kra.service.KcPersonService;

import edu.mit.irb.irbnamespace.ScheduleDocument;
import edu.mit.irb.irbnamespace.InvestigatorDocument.Investigator;
import edu.mit.irb.irbnamespace.PersonDocument.Person;
import edu.mit.irb.irbnamespace.ProtocolMasterDataDocument.ProtocolMasterData;
import edu.mit.irb.irbnamespace.ProtocolSubmissionDocument.ProtocolSubmission;
import edu.mit.irb.irbnamespace.ProtocolSummaryDocument.ProtocolSummary;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule.Attendents;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule.NextSchedule;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule.OtherBusiness;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule.PreviousSchedule;
import edu.mit.irb.irbnamespace.ScheduleMasterDataDocument.ScheduleMasterData;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails.ActionType;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails.SubmissionChecklistInfo;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails.SubmissionChecklistInfo.Checklists;

public class ScheduleXmlStream extends PrintBaseXmlStream {
    private CommitteeMembershipService committeeMembershipService;
    private KcPersonService kcPersonService;
    private IrbPrintXmlUtilService irbPrintXmlUtilService;

    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        Committee committee = (Committee)printableBusinessObject;
        String scheduleId = (String)reportParameters.get("scheduleId");
        CommitteeSchedule committeeSchedule = findCommitteeSchedule(committee,scheduleId);
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        ScheduleDocument scheduleDocument = ScheduleDocument.Factory.newInstance();
        scheduleDocument.setSchedule(getSchedule(committeeSchedule));
        xmlObjectList.put("Schedule", scheduleDocument);
        return xmlObjectList;
    }


    private CommitteeSchedule findCommitteeSchedule(Committee committee, String scheduleId) {
        List<CommitteeSchedule> committeeSchedules = committee.getCommitteeSchedules();
        for (CommitteeSchedule committeeSchedule : committeeSchedules) {
            if(committeeSchedule.getScheduleId().equals(scheduleId)){
                return committeeSchedule;
            }
        }
        return null;
    }


    public Schedule getSchedule(CommitteeSchedule committeeSchedule) {
        Schedule schedule = Schedule.Factory.newInstance();
        setScheduleMasterData(committeeSchedule, schedule.addNewScheduleMasterData());
        PreviousSchedule prevSchedule = schedule.addNewPreviousSchedule();
        setPreviousSchedule(committeeSchedule,prevSchedule.addNewScheduleMasterData());
        NextSchedule nextScheduleType = schedule.addNewNextSchedule();
        setNextSchedule(committeeSchedule,nextScheduleType.addNewScheduleMasterData());

        getIrbPrintXmlUtilService().setMinutes(committeeSchedule, schedule);
        setAttendance(committeeSchedule, schedule);
        committeeSchedule.refreshReferenceObject("protocolSubmissions");
        List<org.kuali.kra.irb.actions.submit.ProtocolSubmission> submissions = committeeSchedule.getProtocolSubmissions();
        for (org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission : submissions) {
//            protocolSubmission.refreshNonUpdateableReferences();
            ProtocolSubmission protocolSubmissionType = schedule.addNewProtocolSubmission();
            SubmissionDetails protocolSubmissionDetail = protocolSubmissionType.addNewSubmissionDetails();
            ProtocolSummary protocolSummary = protocolSubmissionType.addNewProtocolSummary();
            ProtocolMasterData protocolMaster = protocolSummary.addNewProtocolMasterData();

            Protocol protocol = protocolSubmission.getProtocol();
//            protocol.refreshNonUpdateableReferences();
            protocolMaster.setProtocolNumber(protocol.getProtocolNumber());
            protocolMaster.setSequenceNumber(new BigInteger(String.valueOf(protocol.getSequenceNumber())));
            protocolMaster.setProtocolTitle(protocol.getTitle());
            // where is applicationDate?
            // if (protocol.getApplicationDate() != null)
            // {
            // protocolMaster.setApplicationDate() ;
            // }
            protocolMaster.setProtocolStatusCode(new BigInteger(String.valueOf(protocol.getProtocolStatusCode())));
            protocolMaster.setProtocolStatusDesc(protocol.getProtocolStatus().getDescription());
            protocolMaster.setProtocolTypeCode(new BigInteger(String.valueOf(protocol.getProtocolTypeCode())));
            protocolMaster.setProtocolTypeDesc(protocol.getProtocolType().getDescription());

            if (protocol.getDescription() != null) {
                protocolMaster.setProtocolDescription(protocol.getDescription());
            }

            if (protocol.getApprovalDate() != null) {
                protocolMaster.setApprovalDate(getDateTimeService().getCalendar(protocol.getApprovalDate()));
            }

            if (protocol.getExpirationDate() != null) {
                protocolMaster.setExpirationDate(getDateTimeService().getCalendar(protocol.getExpirationDate()));
            }
            // where is
            // protocolMaster.setBillableFlag(protocol.isBillableFlag()) ;

            if (protocol.getFdaApplicationNumber() != null) {
                protocolMaster.setFdaApplicationNumber(protocol.getFdaApplicationNumber());
            }

            if (protocol.getReferenceNumber1() != null) {
                protocolMaster.setRefNumber1(protocol.getReferenceNumber1());
            }

            if (protocol.getReferenceNumber2() != null) {
                protocolMaster.setRefNumber2(protocol.getReferenceNumber2());
            }

            protocolSubmissionDetail.setProtocolNumber(protocolSubmission.getProtocolNumber());
            if (protocolSubmission.getProtocolSubmissionType() != null) {
                protocolSubmissionDetail.setSubmissionTypeDesc(protocolSubmission.getProtocolSubmissionType().getDescription());
            }
            if (protocolSubmission.getProtocolReviewTypeCode() != null) {
                protocolSubmissionDetail.setProtocolReviewTypeCode(new BigInteger(protocolSubmission.getProtocolReviewTypeCode()));
            }
            if (protocolSubmission.getProtocolReviewType() != null) {
                protocolSubmissionDetail.setProtocolReviewTypeDesc(protocolSubmission.getProtocolReviewType().getDescription());
            }
            if (protocolSubmission.getSubmissionTypeCode() != null) {
                protocolSubmissionDetail.setSubmissionTypeCode(new BigInteger(String.valueOf(protocolSubmission
                        .getSubmissionTypeCode())));
            }
            if (protocolSubmission.getProtocolSubmissionType() != null) {
                protocolSubmissionDetail.setSubmissionTypeDesc(protocolSubmission.getProtocolSubmissionType().getDescription());
            }
            if (protocolSubmission.getSubmissionNumber() != null) {
                protocolSubmissionDetail.setSubmissionNumber(new BigInteger(String
                        .valueOf(protocolSubmission.getSubmissionNumber())));
            }
            if (protocolSubmission.getSubmissionStatusCode() != null) {
                protocolSubmissionDetail.setSubmissionStatusCode(new BigInteger(String.valueOf(protocolSubmission
                        .getSubmissionStatusCode())));
            }
            if (protocolSubmission.getSubmissionStatus() != null) {
                protocolSubmissionDetail.setSubmissionStatusDesc(protocolSubmission.getSubmissionStatus().getDescription());
            }
            if (protocolSubmission.getSubmissionTypeQualifierCode() != null) {
                protocolSubmissionDetail.setSubmissionTypeQualifierCode(new BigInteger(protocolSubmission
                        .getSubmissionTypeQualifierCode()));
            }
            if (protocolSubmission.getProtocolSubmissionQualifierType() != null) {
                protocolSubmissionDetail.setSubmissionTypeQualifierDesc(protocolSubmission.getProtocolSubmissionQualifierType()
                        .getDescription());
            }
            if (protocolSubmission.getYesVoteCount() != null) {
                protocolSubmissionDetail.setYesVote(BigInteger.valueOf(protocolSubmission.getYesVoteCount()));
            }
            else {
                protocolSubmissionDetail.setYesVote(BigInteger.ZERO);
            }
            if (protocolSubmission.getNoVoteCount() != null) {
                protocolSubmissionDetail.setNoVote(BigInteger.valueOf(protocolSubmission.getNoVoteCount()));
            }
            else {
                protocolSubmissionDetail.setNoVote(BigInteger.ZERO);
            }
            if (protocolSubmission.getAbstainerCount() != null) {
                protocolSubmissionDetail.setAbstainerCount(BigInteger.valueOf(protocolSubmission.getAbstainerCount()));
            }
            else {
                protocolSubmissionDetail.setAbstainerCount(BigInteger.ZERO);
            }
            protocolSubmissionDetail.setVotingComments(protocolSubmission.getVotingComments());

            setProtocolSubmissionAction(protocolSubmission, protocolSubmissionDetail);
            if (protocolSubmission.getSubmissionDate() != null) {
                protocolSubmissionDetail
                        .setSubmissionDate(getDateTimeService().getCalendar(protocolSubmission.getSubmissionDate()));
            }
            setSubmissionCheckListinfo(protocolSubmission, protocolSubmissionDetail);
            setProtocolSubmissionReviewers(protocolSubmission, protocolSubmissionDetail);
            List<ProtocolPerson> protocolPersons = protocolSubmission.getProtocol().getProtocolPersons();
            for (ProtocolPerson protocolPerson : protocolPersons) {
                if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)
                        || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CO_INVESTIGATOR)) {
                    Investigator investigator = protocolSummary.addNewInvestigator();
                    getIrbPrintXmlUtilService().setPersonRolodexType(protocolPerson, investigator.addNewPerson());
                    if(protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)){
                        investigator.setPIFlag(true);
                    }
                }
            }

            List<ProtocolFundingSource> vecFundingSource = protocol.getProtocolFundingSources();
            int fundingSourceTypeCode;
            String fundingSourceName, fundingSourceCode;
            for (ProtocolFundingSource protocolFundingSourceBean : vecFundingSource) {
                protocolFundingSourceBean.refreshNonUpdateableReferences();
                edu.mit.irb.irbnamespace.ProtocolSummaryDocument.ProtocolSummary.FundingSource fundingSource = protocolSummary
                        .addNewFundingSource();
                fundingSourceCode = protocolFundingSourceBean.getFundingSourceNumber();
                fundingSourceTypeCode = Integer.valueOf(protocolFundingSourceBean.getFundingSourceTypeCode());
                fundingSourceName = getFundingSourceNameForType(fundingSourceTypeCode, fundingSourceCode);

                fundingSource.setFundingSourceName(fundingSourceName);
                if (protocolFundingSourceBean.getFundingSourceType() != null) {
                    fundingSource.setTypeOfFundingSource(protocolFundingSourceBean.getFundingSourceType().getDescription());
                }
            }

            getIrbPrintXmlUtilService().setProcotolMinutes(committeeSchedule,protocolSubmission,protocolSubmissionType);
        }
        setOtherActionItems(committeeSchedule,schedule);
        return schedule;

    }


    /**
     * This method is to set ProtocolActionType
     * @param protocolSubmission
     * @param protocolSubmissionDetail
     */
    private void setProtocolSubmissionAction(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission,
            SubmissionDetails protocolSubmissionDetail) {
        ProtocolAction protcolAction = findProtocolActionForSubmission(protocolSubmission);
        if(protcolAction!=null){
            protcolAction.refreshNonUpdateableReferences();
            ActionType actionTypeInfo = protocolSubmissionDetail.addNewActionType();
            actionTypeInfo.setActionId(BigInteger.valueOf(protcolAction.getActionId()));
            if (protcolAction.getProtocolActionTypeCode() != null) {
                actionTypeInfo.setActionTypeCode(new BigInteger(protcolAction.getProtocolActionTypeCode()));
                actionTypeInfo.setActionTypeDescription(protcolAction.getProtocolActionType().getDescription());
            }
            if (protcolAction.getActionDate() != null) {
                actionTypeInfo.setActionDate(getDateTimeService().getCalendar(protcolAction.getActionDate()));
            }
            actionTypeInfo.setActionComments(protcolAction.getComments());
        }
    }

    private void setOtherActionItems(CommitteeSchedule committeeSchedule, Schedule schedule) {
        List<CommScheduleActItem> otherActions = committeeSchedule.getCommScheduleActItems();
        for (CommScheduleActItem otherActionInfoBean : otherActions) {
            otherActionInfoBean.refreshNonUpdateableReferences();
            OtherBusiness otherBusinessType = schedule.addNewOtherBusiness();
            otherBusinessType.setActionItemNumber(BigInteger.valueOf(otherActionInfoBean.getActionItemNumber()));
            otherBusinessType.setActionItemDesc(otherActionInfoBean.getItemDescription());
            if(otherActionInfoBean.getScheduleActItemType()!=null){
                otherBusinessType.setActionItemCode(new BigInteger(otherActionInfoBean.getScheduleActItemTypeCode()));
                otherBusinessType.setActionItemCodeDesc(otherActionInfoBean.getScheduleActItemType().getDescription());
            }
        }
    }




    private String getFundingSourceNameForType(int sourceType, String sourceCode) {
        String name = null;
        if (sourceType == 1) {
            Sponsor sponsor = getBusinessObjectService().findBySinglePrimaryKey(Sponsor.class, sourceCode);
            if (sponsor != null) {
                name = sponsor.getSponsorName();
            }
        }else if (sourceType == 2) {
            Unit unit  = getBusinessObjectService().findBySinglePrimaryKey(Unit.class, sourceCode);
            if (unit != null) {
                name = unit.getUnitName();
            }
        }else {
            name = sourceCode;
        }
        return name;
    }


    private void setProtocolSubmissionReviewers(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission,
            SubmissionDetails protocolSubmissionDetail) {
        List<ProtocolReviewer> vecReviewers = protocolSubmission.getProtocolReviewers();
        for (ProtocolReviewer protocolReviewer : vecReviewers) {
            protocolReviewer.refreshNonUpdateableReferences();
            edu.mit.irb.irbnamespace.ProtocolReviewerDocument.ProtocolReviewer protocolReviewerType = protocolSubmissionDetail
                    .addNewProtocolReviewer();
            setPerson(protocolReviewer, protocolReviewerType);
            protocolReviewerType.setReviewerTypeDesc(protocolReviewer.getProtocolReviewerType().getDescription());
            protocolReviewerType.setReviewerTypeCode(new BigInteger(String.valueOf(protocolReviewer.getReviewerTypeCode())));
        }
    }

    private void setPerson(ProtocolReviewer protocolReviewer,
            edu.mit.irb.irbnamespace.ProtocolReviewerDocument.ProtocolReviewer protocolReviewerType) {
        Person personType = protocolReviewerType.addNewPerson();
        boolean nonEmployeeFlag = protocolReviewer.getNonEmployeeFlag();
        if (!nonEmployeeFlag) {
            String personId = protocolReviewer.getPersonId();
            KcPerson person = getKcPersonService().getKcPersonByPersonId(personId);
            getIrbPrintXmlUtilService().setPersonXml(person, personType);

        }else {
            Rolodex rolodex = protocolReviewer.getRolodex();
            ProtocolPersonRolodex protocolRolodex = getBusinessObjectService()
                    .findBySinglePrimaryKey(ProtocolPersonRolodex.class, rolodex.getRolodexId());
            getIrbPrintXmlUtilService().setPersonXml(protocolRolodex, personType);
        }
    }


    private void setSubmissionCheckListinfo(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission,
            SubmissionDetails protocolSubmissionDetail) {
        SubmissionChecklistInfo submissionChecklistInfo = protocolSubmissionDetail.addNewSubmissionChecklistInfo();
        String formattedCode = new String();

        List<ProtocolExemptStudiesCheckListItem> protocolExemptCheckList = protocolSubmission.getExemptStudiesCheckList();
        for (ProtocolExemptStudiesCheckListItem protocolExemptStudiesCheckListBean : protocolExemptCheckList) {
            Checklists checkListItem = submissionChecklistInfo.addNewChecklists();
            checkListItem.setChecklistCode(protocolExemptStudiesCheckListBean.getExemptStudiesCheckListCode());
            checkListItem.setChecklistDescription(protocolExemptStudiesCheckListBean.getExemptStudiesCheckListItem()
                    .getDescription());
            formattedCode = formattedCode + "(" + protocolExemptStudiesCheckListBean.getExemptStudiesCheckListCode() + ") ";
        }
        if (formattedCode.length() > 0) {
            submissionChecklistInfo.setChecklistCodesFormatted(formattedCode); // this will have format (3) (7) like that...
        }

        List<ProtocolExpeditedReviewCheckListItem> vecExpeditedCheckList = protocolSubmission.getExpeditedReviewCheckList();
        for (ProtocolExpeditedReviewCheckListItem protocolReviewTypeCheckListBean : vecExpeditedCheckList) {
            Checklists checkListItem = submissionChecklistInfo.addNewChecklists();
            checkListItem.setChecklistCode(protocolReviewTypeCheckListBean.getExpeditedReviewCheckListCode());
            checkListItem.setChecklistDescription(protocolReviewTypeCheckListBean.getExpeditedReviewCheckListItem()
                    .getDescription());
            formattedCode = formattedCode + "(" + protocolReviewTypeCheckListBean.getExpeditedReviewCheckListCode() + ") ";
        }

        if (formattedCode.length() > 0) {
            submissionChecklistInfo.setChecklistCodesFormatted(formattedCode); // this will have format (3) (7) like that...
        }
    }


    private ProtocolAction findProtocolActionForSubmission(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission) {
        List<ProtocolAction> protocolActions = protocolSubmission.getProtocol().getProtocolActions();
        for (ProtocolAction protocolAction : protocolActions) {
            if(protocolAction.getSubmissionNumber()!=null && protocolAction.getSubmissionNumber().equals(protocolSubmission.getSubmissionNumber())){
                return protocolAction;
            }
        }
        return null;
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("scheduleIdFk", protocolSubmission.getScheduleIdFk());
//        List<ProtocolAction> actions = (List) getBusinessObjectService().findMatchingOrderBy(ProtocolAction.class, param,
//                "actionId", true);
//        return actions.isEmpty() ? null : actions.get(0);
    }

    private void setAttendance(CommitteeSchedule committeeSchedule, Schedule schedule) {
        List<CommitteeScheduleAttendance> attendenceList = committeeSchedule.getCommitteeScheduleAttendances();
        for (CommitteeScheduleAttendance attendanceInfoBean : attendenceList) {
            Attendents attendents = schedule.addNewAttendents();
            attendents.setAttendentName(attendanceInfoBean.getPersonName());
            attendents.setAlternateFlag(attendanceInfoBean.getAlternateFlag());
            attendents.setGuestFlag(attendanceInfoBean.getGuestFlag());
            attendents.setAlternateFor(attendanceInfoBean.getAlternateFor());
            attendents.setPresentFlag(true);
        }

        List<CommitteeMembership> committeeMemberships = committeeSchedule.getCommittee().getCommitteeMemberships();
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (!getCommitteeMembershipService().isMemberAttendedMeeting(committeeMembership,
                    committeeSchedule.getCommittee().getCommitteeId())) {
                Attendents attendents = schedule.addNewAttendents();
                attendents.setAttendentName(committeeMembership.getPersonName());
                attendents.setAlternateFlag(false);
                attendents.setGuestFlag(false);
                attendents.setPresentFlag(false);
            }
        }
    }



    public ScheduleMasterData setScheduleMasterData(CommitteeSchedule scheduleDetailsBean, ScheduleMasterData currentSchedule) {
        scheduleDetailsBean.refreshNonUpdateableReferences();
        String committeeId = scheduleDetailsBean.getCommittee().getCommitteeId();
        currentSchedule.setScheduleId(scheduleDetailsBean.getScheduleId());
        currentSchedule.setCommitteeId(committeeId);
        currentSchedule.setCommitteeName(scheduleDetailsBean.getCommittee().getCommitteeName());
        currentSchedule.setScheduleStatusCode(BigInteger.valueOf(scheduleDetailsBean.getScheduleStatusCode()));
        currentSchedule.setScheduleStatusDesc(scheduleDetailsBean.getScheduleStatus().getDescription());
        if (scheduleDetailsBean.getScheduledDate() != null) {
            currentSchedule.setScheduledDate(getDateTimeService().getCalendar(scheduleDetailsBean.getScheduledDate()));
        }
        else {
            currentSchedule.setScheduledDate(getDateTimeService().getCurrentCalendar());
        }
        try {
            if (scheduleDetailsBean.getTime() != null) {
                Date date;
                date = getDateTimeService().convertToSqlDate(scheduleDetailsBean.getTime());
                currentSchedule.setScheduledTime(getDateTimeService().getCalendar(date));
            }

            currentSchedule.setPlace(scheduleDetailsBean.getPlace());

            if (scheduleDetailsBean.getProtocolSubDeadline() != null) {
                currentSchedule.setProtocolSubDeadline(getDateTimeService().getCalendar(
                        scheduleDetailsBean.getProtocolSubDeadline()));
            }
            if (scheduleDetailsBean.getMeetingDate() != null) {
                currentSchedule.setMeetingDate(getDateTimeService().getCalendar(scheduleDetailsBean.getMeetingDate()));
            }

            if (scheduleDetailsBean.getStartTime() != null) {
                currentSchedule.setStartTime(getDateTimeService().getCalendar(
                        getDateTimeService().convertToSqlDate(scheduleDetailsBean.getStartTime())));
            }

            if (scheduleDetailsBean.getEndTime() != null) {
                currentSchedule.setEndTime(getDateTimeService().getCalendar(
                        getDateTimeService().convertToSqlDate(scheduleDetailsBean.getEndTime())));
            }
            if (scheduleDetailsBean.getAgendaProdRevDate() != null) {
                currentSchedule.setAgendaProdRevDate(getDateTimeService().getCalendar(scheduleDetailsBean.getAgendaProdRevDate()));
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        currentSchedule.setMaxProtocols(new BigInteger(String.valueOf(scheduleDetailsBean.getMaxProtocols())));
        if (scheduleDetailsBean.getComments() != null) {
            currentSchedule.setComments(scheduleDetailsBean.getComments());
        }

        return currentSchedule;
    }

    public void setNextSchedule(CommitteeSchedule scheduleDetailsBean,ScheduleMasterData scheduleMasterData) {
        CommitteeSchedule nextSchedule = getNextOrPreviousSchedule(scheduleDetailsBean, true);
        if (nextSchedule != null){
            setScheduleMasterData(nextSchedule, scheduleMasterData);
        }
    }

    public void setPreviousSchedule(CommitteeSchedule scheduleDetailsBean,ScheduleMasterData scheduleMasterDataType) {
        CommitteeSchedule prevSchedule = getNextOrPreviousSchedule(scheduleDetailsBean, true);
        if (prevSchedule != null){
            setScheduleMasterData(prevSchedule, scheduleMasterDataType);
        }
    }

    /**
     * 
     * This method returns next or previous schedule depending on the nextFlag
     * 
     * @param scheduleDetailsBean
     * @param nextFlag
     * @return
     */
    private CommitteeSchedule getNextOrPreviousSchedule(CommitteeSchedule scheduleDetailsBean, boolean nextFlag) {
        Map<String, String> scheduleParam = new HashMap<String, String>();
        scheduleParam.put("committeeIdFk", scheduleDetailsBean.getCommittee().getId().toString());
        List<CommitteeSchedule> schedules = (List) getBusinessObjectService().findMatchingOrderBy(CommitteeSchedule.class,
                scheduleParam, "scheduledDate", false);
        if (!schedules.isEmpty()) {
            int size = schedules.size();
            for (int i = 0; i < size; i++) {
                CommitteeSchedule schedule = schedules.get(i);
                if (schedule.getScheduleId().equals(scheduleDetailsBean.getScheduleId())) {
                    if (nextFlag && i < (size -1)) {
                        return schedules.get(i + 1);
                    }
                    else if (!nextFlag && i > 0) {
                        return schedules.get(i - 1);
                    }
                    else {
                        return null;
                    }
                }
            }
        }
        return null;
    }


    /**
     * Sets the committeeMembershipService attribute value.
     * 
     * @param committeeMembershipService The committeeMembershipService to set.
     */
    public void setCommitteeMembershipService(CommitteeMembershipService committeeMembershipService) {
        this.committeeMembershipService = committeeMembershipService;
    }


    /**
     * Gets the committeeMembershipService attribute.
     * 
     * @return Returns the committeeMembershipService.
     */
    public CommitteeMembershipService getCommitteeMembershipService() {
        return committeeMembershipService;
    }


    /**
     * Gets the kcPersonService attribute.
     * 
     * @return Returns the kcPersonService.
     */
    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }


    /**
     * Sets the kcPersonService attribute value.
     * 
     * @param kcPersonService The kcPersonService to set.
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }


    /**
     * Gets the irbPrintXmlUtilService attribute.
     * 
     * @return Returns the irbPrintXmlUtilService.
     */
    public IrbPrintXmlUtilService getIrbPrintXmlUtilService() {
        return irbPrintXmlUtilService;
    }


    /**
     * Sets the irbPrintXmlUtilService attribute value.
     * 
     * @param irbPrintXmlUtilService The irbPrintXmlUtilService to set.
     */
    public void setIrbPrintXmlUtilService(IrbPrintXmlUtilService irbPrintXmlUtilService) {
        this.irbPrintXmlUtilService = irbPrintXmlUtilService;
    }

}
