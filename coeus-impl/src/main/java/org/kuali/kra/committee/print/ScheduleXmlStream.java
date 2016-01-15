/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.committee.print;

import edu.mit.irb.irbnamespace.InvestigatorDocument.Investigator;
import edu.mit.irb.irbnamespace.PersonDocument.Person;
import edu.mit.irb.irbnamespace.ProtocolMasterDataDocument.ProtocolMasterData;
import edu.mit.irb.irbnamespace.ProtocolSubmissionDocument.ProtocolSubmission;
import edu.mit.irb.irbnamespace.ProtocolSummaryDocument.ProtocolSummary;
import edu.mit.irb.irbnamespace.ScheduleDocument;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.meeting.CommScheduleActItemBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttendanceBase;
import org.kuali.coeus.common.framework.print.stream.xml.PrintBaseXmlStream;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRole;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

public class ScheduleXmlStream extends PrintBaseXmlStream {

    private static final Log LOG = LogFactory.getLog(ScheduleXmlStream.class);
    private static final String SCHEDULE = "Schedule";
    private static final String PROTOCOL_SUBMISSIONS = "protocolSubmissions";
    private static final String SUBMISSION_ID_FK = "submissionIdFk";
    private static final String COMMITTEE_ID_FK = "committeeIdFk";
    private static final String SCHEDULED_DATE = "scheduledDate";
    private static final String PROTOCOL_ID = "protocolId";

    private CommitteeMembershipService committeeMembershipService;
    private IrbPrintXmlUtilService irbPrintXmlUtilService;

    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        CommitteeSchedule committeeSchedule = (CommitteeSchedule)printableBusinessObject;

        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<>();
        ScheduleDocument scheduleDocument =
		ScheduleDocument.Factory.newInstance();
        scheduleDocument.setSchedule(getSchedule(committeeSchedule));
        xmlObjectList.put(SCHEDULE, scheduleDocument);
        return xmlObjectList;
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
        committeeSchedule.refreshReferenceObject(PROTOCOL_SUBMISSIONS);

        committeeSchedule.getLatestProtocolSubmissions().stream()
        .sorted(Comparator.comparing(ProtocolSubmissionLite::getProtocolReviewTypeCode).thenComparing(ProtocolSubmissionLite::getSubmissionTypeCode)
                .thenComparing(ProtocolSubmissionLite::getProtocolNumber))
                .forEach(protocolSubmission -> {

                    ProtocolSubmission protocolSubmissionType = schedule.addNewProtocolSubmission();
                    SubmissionDetails protocolSubmissionDetail = protocolSubmissionType.addNewSubmissionDetails();
                    ProtocolSummary protocolSummary = protocolSubmissionType.addNewProtocolSummary();
                    ProtocolMasterData protocolMaster = protocolSummary.addNewProtocolMasterData();

                    Protocol protocol = getBusinessObjectService().findByPrimaryKey(Protocol.class, Collections.singletonMap(PROTOCOL_ID, protocolSubmission.getProtocolId()));

                    protocolMaster.setProtocolNumber(protocol.getProtocolNumber());
                    protocolMaster.setSequenceNumber(new BigInteger(String.valueOf(protocol.getSequenceNumber())));
                    protocolMaster.setProtocolTitle(protocol.getTitle());
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
                        protocolSubmissionDetail.setSubmissionTypeCode(new BigInteger(String.valueOf(protocolSubmission.getSubmissionTypeCode())));
                    }
                    if (protocolSubmission.getProtocolSubmissionType() != null) {
                        protocolSubmissionDetail.setSubmissionTypeDesc(protocolSubmission.getProtocolSubmissionType().getDescription());
                    }
                    if (protocolSubmission.getSubmissionNumber() != null) {
                        protocolSubmissionDetail.setSubmissionNumber(new BigInteger(String.valueOf(protocolSubmission.getSubmissionNumber())));
                    }
                    if (protocolSubmission.getSubmissionStatusCode() != null) {
                        protocolSubmissionDetail.setSubmissionStatusCode(new BigInteger(String.valueOf(protocolSubmission.getSubmissionStatusCode())));
                    }
                    if (protocolSubmission.getSubmissionStatus() != null) {
                        protocolSubmissionDetail.setSubmissionStatusDesc(protocolSubmission.getSubmissionStatus().getDescription());
                    }
                    if (protocolSubmission.getSubmissionTypeQualifierCode() != null) {
                        protocolSubmissionDetail.setSubmissionTypeQualifierCode(new BigInteger(protocolSubmission.getSubmissionTypeQualifierCode()));
                    }
                    if (protocolSubmission.getProtocolSubmissionQualifierType() != null) {
                        protocolSubmissionDetail.setSubmissionTypeQualifierDesc(protocolSubmission.getProtocolSubmissionQualifierType().getDescription());
                    }
                    if (protocolSubmission.getYesVoteCount() != null) {
                        protocolSubmissionDetail.setYesVote(BigInteger.valueOf(protocolSubmission.getYesVoteCount()));
                    } else {
                        protocolSubmissionDetail.setYesVote(BigInteger.ZERO);
                    }
                    if (protocolSubmission.getNoVoteCount() != null) {
                        protocolSubmissionDetail.setNoVote(BigInteger.valueOf(protocolSubmission.getNoVoteCount()));
                    } else {
                        protocolSubmissionDetail.setNoVote(BigInteger.ZERO);
                    }
                    if (protocolSubmission.getAbstainerCount() != null) {
                        protocolSubmissionDetail.setAbstainerCount(BigInteger.valueOf(protocolSubmission.getAbstainerCount()));
                    } else {
                        protocolSubmissionDetail.setAbstainerCount(BigInteger.ZERO);
                    }
                    protocolSubmissionDetail.setVotingComments(protocolSubmission.getVotingComments());

                    setProtocolSubmissionAction(protocolSubmission, protocol, protocolSubmissionDetail);
                    if (protocolSubmission.getSubmissionDate() != null) {
                        protocolSubmissionDetail.setSubmissionDate(getDateTimeService().getCalendar(protocolSubmission.getSubmissionDate()));
                    }
                    setSubmissionCheckListinfo(protocolSubmission, protocolSubmissionDetail);
                    setProtocolSubmissionReviewers(protocolSubmission, protocolSubmissionDetail);
                    List<ProtocolPersonBase> protocolPersons = protocol.getProtocolPersons();
                    for (ProtocolPersonBase protocolPerson : protocolPersons) {
                        if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)
                                || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CO_INVESTIGATOR)) {
                            Investigator investigator = protocolSummary.addNewInvestigator();
                            getIrbPrintXmlUtilService().setPersonRolodexType((ProtocolPerson) protocolPerson, investigator.addNewPerson());
                            if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)) {
                                investigator.setPIFlag(true);
                            }
                        }
                    }
                    List<ProtocolRiskLevel> cvRiskLevels = protocol.getProtocolRiskLevels();
                    for (ProtocolRiskLevel protocolRiskLevelBean : cvRiskLevels) {
                        edu.mit.irb.irbnamespace.ProtocolSummaryDocument.ProtocolSummary.RiskLevels riskLevelType = protocolSummary.addNewRiskLevels();
                        riskLevelType.setRiskLevelDescription(protocolRiskLevelBean.getRiskLevel().getDescription());
                        riskLevelType.setComments(protocolRiskLevelBean.getComments());
                    }

                    List<ProtocolFundingSourceBase> vecFundingSource = protocol.getProtocolFundingSources();
                    int fundingSourceTypeCode;
                    String fundingSourceName, fundingSourceCode;
                    for (ProtocolFundingSourceBase protocolFundingSourceBean : vecFundingSource) {
                        protocolFundingSourceBean.refreshNonUpdateableReferences();
                        edu.mit.irb.irbnamespace.ProtocolSummaryDocument.ProtocolSummary.FundingSource fundingSource = protocolSummary.addNewFundingSource();
                        fundingSourceCode = protocolFundingSourceBean.getFundingSourceNumber();
                        fundingSourceTypeCode = Integer.valueOf(protocolFundingSourceBean.getFundingSourceTypeCode());
                        fundingSourceName = getFundingSourceNameForType(fundingSourceTypeCode, fundingSourceCode);

                        fundingSource.setFundingSourceName(fundingSourceName);
                        if (protocolFundingSourceBean.getFundingSourceType() != null) {
                            fundingSource.setTypeOfFundingSource(protocolFundingSourceBean.getFundingSourceType().getDescription());
                        }
                    }

                    getIrbPrintXmlUtilService().setProcotolMinutes(committeeSchedule, protocolSubmission, protocolSubmissionType);
                });
        setOtherActionItems(committeeSchedule, schedule);
        return schedule;

    }

 
    /**
     * This method is to set ProtocolActionType
     */
    private void setProtocolSubmissionAction(org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite protocolSubmission, Protocol protocol,
            SubmissionDetails protocolSubmissionDetail) {
        ProtocolAction protcolAction = findProtocolActionForSubmission(protocolSubmission, protocol);
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
        List<CommScheduleActItemBase> otherActions = committeeSchedule.getCommScheduleActItems();
        for (CommScheduleActItemBase otherActionInfoBean : otherActions) {
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


    private void setProtocolSubmissionReviewers(org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite protocolSubmission,
            SubmissionDetails protocolSubmissionDetail) {

        Collection<ProtocolReviewer> vecReviewers = getBusinessObjectService().findMatching(ProtocolReviewer.class, Collections.singletonMap(SUBMISSION_ID_FK, protocolSubmission.getSubmissionId()));
        for (org.kuali.kra.protocol.actions.submit.ProtocolReviewer protocolReviewer : vecReviewers) {
            protocolReviewer.refreshNonUpdateableReferences();
            edu.mit.irb.irbnamespace.ProtocolReviewerDocument.ProtocolReviewer protocolReviewerType = protocolSubmissionDetail
                    .addNewProtocolReviewer();
            setPerson((ProtocolReviewer) protocolReviewer, protocolReviewerType);
            protocolReviewerType.setReviewerTypeDesc(protocolReviewer.getProtocolReviewerType().getDescription());
            protocolReviewerType.setReviewerTypeCode(new BigInteger(String.valueOf(protocolReviewer.getReviewerTypeCode())));
        }
    }

    private void setPerson(ProtocolReviewer protocolReviewer,
            edu.mit.irb.irbnamespace.ProtocolReviewerDocument.ProtocolReviewer protocolReviewerType) {
        Person personType = protocolReviewerType.addNewPerson();
        boolean nonEmployeeFlag = protocolReviewer.getNonEmployeeFlag();
        if (!nonEmployeeFlag) {
            getIrbPrintXmlUtilService().setPersonXml(protocolReviewer.getPerson(), personType);

        }else {
            Rolodex rolodex = protocolReviewer.getRolodex();
            ProtocolPersonRolodex protocolRolodex = getBusinessObjectService()
                    .findBySinglePrimaryKey(ProtocolPersonRolodex.class, rolodex.getRolodexId());
            getIrbPrintXmlUtilService().setPersonXml(protocolRolodex, personType);
        }
    }


    private void setSubmissionCheckListinfo(org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite protocolSubmission,
            SubmissionDetails protocolSubmissionDetail) {
        SubmissionChecklistInfo submissionChecklistInfo = protocolSubmissionDetail.addNewSubmissionChecklistInfo();
        String formattedCode = "";
        Collection<ProtocolExemptStudiesCheckListItem> protocolExemptCheckList = getBusinessObjectService().findMatching(ProtocolExemptStudiesCheckListItem.class, Collections.singletonMap(SUBMISSION_ID_FK, protocolSubmission.getSubmissionId()));
        for (ProtocolExemptStudiesCheckListItem protocolExemptStudiesCheckListBean : protocolExemptCheckList) {
            Checklists checkListItem = submissionChecklistInfo.addNewChecklists();
            checkListItem.setChecklistCode(protocolExemptStudiesCheckListBean.getExemptStudiesCheckListCode());
            checkListItem.setChecklistDescription(protocolExemptStudiesCheckListBean.getExemptStudiesCheckListItem().getDescription());
            formattedCode = formattedCode + "(" + protocolExemptStudiesCheckListBean.getExemptStudiesCheckListCode() + ") ";
        }
        if (formattedCode.length() > 0) {
            submissionChecklistInfo.setChecklistCodesFormatted(formattedCode); // this will have format (3) (7) like that...
        }
        Collection<ProtocolExpeditedReviewCheckListItem> vecExpeditedCheckList = getBusinessObjectService().findMatching(ProtocolExpeditedReviewCheckListItem.class, Collections.singletonMap(SUBMISSION_ID_FK, protocolSubmission.getSubmissionId()));
        for (ProtocolExpeditedReviewCheckListItem protocolReviewTypeCheckListBean : vecExpeditedCheckList) {
            Checklists checkListItem = submissionChecklistInfo.addNewChecklists();
            checkListItem.setChecklistCode(protocolReviewTypeCheckListBean.getExpeditedReviewCheckListCode());
            checkListItem.setChecklistDescription(protocolReviewTypeCheckListBean.getExpeditedReviewCheckListItem().getDescription());
            formattedCode = formattedCode + "(" + protocolReviewTypeCheckListBean.getExpeditedReviewCheckListCode() + ") ";
        }

        if (formattedCode.length() > 0) {
            submissionChecklistInfo.setChecklistCodesFormatted(formattedCode); // this will have format (3) (7) like that...
        }
    }


    private ProtocolAction findProtocolActionForSubmission(org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite protocolSubmission, Protocol protocol) {
        List<ProtocolActionBase> protocolActions = protocol.getProtocolActions();
        for (ProtocolActionBase protocolAction : protocolActions) {
            if(protocolAction.getSubmissionNumber()!=null && protocolAction.getSubmissionNumber().equals(protocolSubmission.getSubmissionNumber())){
                return (ProtocolAction) protocolAction;
            }
        }
        return null;
    }

    private void setAttendance(CommitteeSchedule committeeSchedule, Schedule schedule) {
        List<CommitteeScheduleAttendanceBase> attendenceList = committeeSchedule.getCommitteeScheduleAttendances();
        for (CommitteeScheduleAttendanceBase attendanceInfoBean : attendenceList) {
            Attendents attendents = schedule.addNewAttendents();
            attendents.setAttendentName(attendanceInfoBean.getPersonName());
            attendents.setAlternateFlag(attendanceInfoBean.getAlternateFlag());
            attendents.setGuestFlag(attendanceInfoBean.getGuestFlag());
            attendents.setAlternateFor(attendanceInfoBean.getAlternateFor());
            attendents.setPresentFlag(true);
        }

        List<CommitteeMembershipBase> committeeMemberships = committeeSchedule.getParentCommittee().getCommitteeMemberships();
        committeeMemberships.stream().filter(committeeMembership -> isAbsent(attendenceList, committeeMembership, committeeSchedule.getScheduledDate())).forEach(committeeMembership -> {
            Attendents attendents = schedule.addNewAttendents();
            attendents.setAttendentName(committeeMembership.getPersonName());
            attendents.setAlternateFlag(false);
            attendents.setGuestFlag(false);
            attendents.setPresentFlag(false);
        });
    }

    private boolean isAbsent(List<CommitteeScheduleAttendanceBase> attendenceList, CommitteeMembershipBase committeeMembership, java.sql.Date scheduledDate) {
    	return !attendenceList.stream()
                .anyMatch(committeeScheduleAttendanceBase -> committeeScheduleAttendanceBase.isCommitteeMember(committeeMembership)) &&
                committeeMembership.isActive(scheduledDate);
    }

    public ScheduleMasterData setScheduleMasterData(CommitteeSchedule scheduleDetailsBean, ScheduleMasterData currentSchedule) {
        scheduleDetailsBean.refreshNonUpdateableReferences();
        String committeeId = scheduleDetailsBean.getParentCommittee().getCommitteeId();
        currentSchedule.setScheduleId(scheduleDetailsBean.getScheduleId());
        currentSchedule.setCommitteeId(committeeId);
        currentSchedule.setCommitteeName(scheduleDetailsBean.getParentCommittee().getCommitteeName());
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
                currentSchedule.setProtocolSubDeadline(getDateTimeService().getCalendar(scheduleDetailsBean.getProtocolSubDeadline()));
            }
            if (scheduleDetailsBean.getMeetingDate() != null) {
                currentSchedule.setMeetingDate(getDateTimeService().getCalendar(scheduleDetailsBean.getMeetingDate()));
            }

            if (scheduleDetailsBean.getStartTime() != null) {
                currentSchedule.setStartTime(getDateTimeService().getCalendar(getDateTimeService().convertToSqlDate(scheduleDetailsBean.getStartTime())));
            }

            if (scheduleDetailsBean.getEndTime() != null) {
                currentSchedule.setEndTime(getDateTimeService().getCalendar(getDateTimeService().convertToSqlDate(scheduleDetailsBean.getEndTime())));
            }
            if (scheduleDetailsBean.getAgendaProdRevDate() != null) {
                currentSchedule.setAgendaProdRevDate(getDateTimeService().getCalendar(scheduleDetailsBean.getAgendaProdRevDate()));
            }
        }
        catch (ParseException e) {
            LOG.error(e.getMessage(), e);
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
        CommitteeSchedule prevSchedule = getNextOrPreviousSchedule(scheduleDetailsBean, false);
        if (prevSchedule != null){
            setScheduleMasterData(prevSchedule, scheduleMasterDataType);
        }
    }

    /**
     * 
     * This method returns next or previous schedule depending on the nextFlag.
     */
    private CommitteeSchedule getNextOrPreviousSchedule(CommitteeSchedule scheduleDetailsBean, boolean nextFlag) {
        Map<String, String> scheduleParam = new HashMap<>();
        scheduleParam.put(COMMITTEE_ID_FK, scheduleDetailsBean.getParentCommittee().getId().toString());
        List<CommitteeSchedule> schedules = (List<CommitteeSchedule>) getBusinessObjectService().findMatchingOrderBy(CommitteeSchedule.class,
                scheduleParam, SCHEDULED_DATE, true);
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


    public void setCommitteeMembershipService(CommitteeMembershipService committeeMembershipService) {
        this.committeeMembershipService = committeeMembershipService;
    }

    public CommitteeMembershipService getCommitteeMembershipService() {
        return committeeMembershipService;
    }

    public IrbPrintXmlUtilService getIrbPrintXmlUtilService() {
        return irbPrintXmlUtilService;
    }

    public void setIrbPrintXmlUtilService(IrbPrintXmlUtilService irbPrintXmlUtilService) {
        this.irbPrintXmlUtilService = irbPrintXmlUtilService;
    }

}
