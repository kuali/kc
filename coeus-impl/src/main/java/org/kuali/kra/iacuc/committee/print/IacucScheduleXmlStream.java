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
package org.kuali.kra.iacuc.committee.print;

import edu.mit.coeus.xml.iacuc.*;
import edu.mit.coeus.xml.iacuc.ScheduleType.Attendents;
import edu.mit.coeus.xml.iacuc.ScheduleType.OtherBusiness;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommScheduleActItemBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttendanceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeMembershipServiceBase;
import org.kuali.coeus.common.framework.print.stream.xml.PrintBaseXmlStream;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewer;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionLite;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.print.service.IacucPrintXmlUtilService;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRolodex;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRoleBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

public class IacucScheduleXmlStream extends PrintBaseXmlStream {

    private static final String EXPEDIT_ACTION_TYPE_CODE = "205";
    private static final String EXEMPT_ACTION_TYPE_CODE = "206";
    private static final String FOLLOW_UP_ACTION_CODE = "109";

    private static final Log LOG = LogFactory.getLog(IacucScheduleXmlStream.class);
    private static final String SCHEDULE = "Schedule";
    private static final String PROTOCOL_SUBMISSIONS = "protocolSubmissions";
    private static final String COMMITTEE_ID_FK = "committeeIdFk";
    private static final String SCHEDULED_DATE = "scheduledDate";
    private static final String PROTOCOL_ID = "protocolId";
    private static final String SUBMISSION_ID_FK = "submissionIdFk";

    private CommitteeMembershipServiceBase committeeMembershipService;
    private IacucPrintXmlUtilService printXmlUtilService;

    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        IacucCommitteeSchedule committeeSchedule = (IacucCommitteeSchedule)printableBusinessObject;
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<>();
        ScheduleDocument scheduleDocument =
		ScheduleDocument.Factory.newInstance();
        scheduleDocument.setSchedule(getSchedule(committeeSchedule));
        xmlObjectList.put(SCHEDULE, scheduleDocument);
        return xmlObjectList;
    }

    public ScheduleType getSchedule(IacucCommitteeSchedule committeeSchedule) {
        ScheduleType schedule = ScheduleType.Factory.newInstance();
        setScheduleMasterData(committeeSchedule, schedule.addNewScheduleMasterData());
        ScheduleSummaryType prevSchedule = schedule.addNewPreviousSchedule();
        setPreviousSchedule(committeeSchedule,prevSchedule.addNewScheduleMasterData());
        ScheduleSummaryType nextScheduleType = schedule.addNewNextSchedule();
        setNextSchedule(committeeSchedule,nextScheduleType.addNewScheduleMasterData());
        
        getPrintXmlUtilService().setMinutes(committeeSchedule, schedule);
        setAttendance(committeeSchedule, schedule);
        committeeSchedule.refreshReferenceObject(PROTOCOL_SUBMISSIONS);
        committeeSchedule.getLatestProtocolSubmissions().stream()
                .sorted(Comparator.comparing(IacucProtocolSubmissionLite::getProtocolReviewTypeCode).thenComparing(IacucProtocolSubmissionLite::getSubmissionTypeCode)
                        .thenComparing(IacucProtocolSubmissionLite::getProtocolNumber))
                .forEach(protocolSubmission -> {
                    ProtocolSubmissionType protocolSubmissionType =
                            schedule.addNewProtocolSubmission();

                    SubmissionDetailsType protocolSubmissionDetail = protocolSubmissionType.addNewSubmissionDetails();
                    ProtocolSummaryType protocolSummary =
                            protocolSubmissionType.addNewProtocolSummary();
                    ProtocolMasterDataType protocolMaster = protocolSummary.addNewProtocolMasterData();
                    String followUpAction = null;
                    String actionTypeCode = null;
                    IacucProtocol protocol = getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, Collections.singletonMap(PROTOCOL_ID, protocolSubmission.getProtocolId()));
                    List<ProtocolActionBase> protocolActions = protocol.getProtocolActions();

                    for (ProtocolActionBase protocolAction : protocolActions) {
                        actionTypeCode = protocolAction.getProtocolActionTypeCode();
                        if (actionTypeCode.equals(EXPEDIT_ACTION_TYPE_CODE) || actionTypeCode.equals(EXEMPT_ACTION_TYPE_CODE)) {
                            if (protocolAction.getFollowupActionCode() != null
                                    && protocolAction.getFollowupActionCode().equals(FOLLOW_UP_ACTION_CODE)) {
                                followUpAction = protocolAction.getFollowupActionCode();
                            }
                            break;
                        }
                    }
                    if (!((EXPEDIT_ACTION_TYPE_CODE.equals(actionTypeCode) || EXEMPT_ACTION_TYPE_CODE.equals(actionTypeCode)))
                            && followUpAction == null) {

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
                            protocolSubmissionDetail
                                    .setSubmissionDate(getDateTimeService().getCalendar(protocolSubmission.getSubmissionDate()));
                        }
                        setSubmissionCheckListinfo(protocolSubmission, protocolSubmissionDetail);
                        setProtocolSubmissionReviewers(protocolSubmission, protocolSubmissionDetail);
                        List<ProtocolPersonBase> protocolPersons = protocol.getProtocolPersons();
                        protocolPersons.stream()
                                .filter(protocolPerson -> protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)
                                        || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_CO_INVESTIGATOR))
                                .forEach(protocolPerson -> {
                                    InvestigatorType investigator = protocolSummary.addNewInvestigator();
                                    getPrintXmlUtilService().setPersonRolodexType(protocolPerson, investigator.addNewPerson());
                                    if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)) {
                                        investigator.setPIFlag(true);
                                    }
                                });


                        List<ProtocolFundingSourceBase> vecFundingSource = protocol.getProtocolFundingSources();
                        int fundingSourceTypeCode;
                        String fundingSourceName, fundingSourceCode;
                        for (ProtocolFundingSourceBase protocolFundingSourceBean : vecFundingSource) {
                            protocolFundingSourceBean.refreshNonUpdateableReferences();
                            FundingSourceType fundingSource = protocolSummary
                                    .addNewFundingSource();
                            fundingSourceCode = protocolFundingSourceBean.getFundingSourceNumber();
                            fundingSourceTypeCode = Integer.valueOf(protocolFundingSourceBean.getFundingSourceTypeCode());
                            fundingSourceName = getFundingSourceNameForType(fundingSourceTypeCode, fundingSourceCode);

                            fundingSource.setFundingSourceName(fundingSourceName);
                            if (protocolFundingSourceBean.getFundingSourceType() != null) {
                                fundingSource.setTypeOfFundingSource(protocolFundingSourceBean.getFundingSourceType().getDescription());
                            }
                        }

                        getPrintXmlUtilService().setProcotolMinutes(committeeSchedule, protocolSubmission, protocolSubmissionType);

                        setOtherActionItems(committeeSchedule, schedule);
                    }
                });
        return schedule;

    }

    private void setProtocolSubmissionAction(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase protocolSubmission, IacucProtocol protocol,
            SubmissionDetailsType protocolSubmissionDetail) {
        ProtocolActionBase protcolAction = findProtocolActionForSubmission(protocolSubmission, protocol);
        if(protcolAction!=null){
            protcolAction.refreshNonUpdateableReferences();
           edu.mit.coeus.xml.iacuc.SubmissionDetailsType.ActionType actionTypeInfo = protocolSubmissionDetail.addNewActionType();
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

    private void setOtherActionItems(IacucCommitteeSchedule committeeSchedule, ScheduleType schedule) {
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


    private void setProtocolSubmissionReviewers(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail) {


        Collection<IacucProtocolReviewer> vecReviewers = getBusinessObjectService().findMatching(IacucProtocolReviewer.class, Collections.singletonMap(SUBMISSION_ID_FK, protocolSubmission.getSubmissionId()));
        List<ProtocolReviewerType> protocolReviewerTypeList = new ArrayList<>();
        for (ProtocolReviewer protocolReviewer : vecReviewers) {
            protocolReviewer.refreshNonUpdateableReferences();
            ProtocolReviewerType protocolReviewerType = ProtocolReviewerType.Factory.newInstance();
            setPerson(protocolReviewer, protocolReviewerType);
            protocolReviewerType.setReviewerTypeDesc(protocolReviewer.getProtocolReviewerType().getDescription());
            protocolReviewerType.setReviewerTypeCode(new BigInteger(String.valueOf(protocolReviewer.getReviewerTypeCode())));
            protocolReviewerTypeList.add(protocolReviewerType);
        }
        protocolSubmissionDetail.setProtocolReviewerArray(protocolReviewerTypeList.toArray(new ProtocolReviewerType[0]));
    }

    private void setPerson(ProtocolReviewer protocolReviewer,
           ProtocolReviewerType protocolReviewerType) {
        PersonType personType = protocolReviewerType.addNewPerson();
        boolean nonEmployeeFlag = protocolReviewer.getNonEmployeeFlag();
        if (!nonEmployeeFlag) {
            getPrintXmlUtilService().setPersonXml(protocolReviewer.getPerson(), personType);

        }else {
            Rolodex rolodex = protocolReviewer.getRolodex();
            ProtocolPersonRolodexBase protocolRolodex = getBusinessObjectService()
                    .findBySinglePrimaryKey(IacucProtocolPersonRolodex.class, rolodex.getRolodexId());
            getPrintXmlUtilService().setPersonXml(protocolRolodex, personType);
        }
    }


    private void setSubmissionCheckListinfo(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail) {
        protocolSubmissionDetail.addNewSubmissionChecklistInfo();
    }


    private ProtocolActionBase findProtocolActionForSubmission(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase protocolSubmission, IacucProtocol protocol) {
        List<ProtocolActionBase> protocolActions = protocol.getProtocolActions();
        for (ProtocolActionBase protocolAction : protocolActions) {
            if(protocolAction.getSubmissionNumber()!=null && protocolAction.getSubmissionNumber().equals(protocolSubmission.getSubmissionNumber())){
                return protocolAction;
            }
        }
        return null;
    }

    private void setAttendance(IacucCommitteeSchedule committeeSchedule, ScheduleType schedule) {
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
        committeeMemberships.stream()
                .filter(committeeMembership -> !getCommitteeMembershipService().isMemberAttendedMeeting(committeeMembership, committeeSchedule.getParentCommittee().getCommitteeId()))
                .forEach(committeeMembership -> {
                    Attendents attendents = schedule.addNewAttendents();
                    attendents.setAttendentName(committeeMembership.getPersonName());
                    attendents.setAlternateFlag(false);
                    attendents.setGuestFlag(false);
                    attendents.setPresentFlag(false);
                });
    }



    public ScheduleMasterDataType setScheduleMasterData(CommitteeScheduleBase scheduleDetailsBean, ScheduleMasterDataType scheduleMasterDataType) {
        scheduleDetailsBean.refreshNonUpdateableReferences();
        String committeeId = scheduleDetailsBean.getParentCommittee().getCommitteeId();
        scheduleMasterDataType.setScheduleId(scheduleDetailsBean.getScheduleId());
        scheduleMasterDataType.setCommitteeId(committeeId);
        scheduleMasterDataType.setCommitteeName(scheduleDetailsBean.getParentCommittee().getCommitteeName());
        scheduleMasterDataType.setScheduleStatusCode(BigInteger.valueOf(scheduleDetailsBean.getScheduleStatusCode()));
        scheduleMasterDataType.setScheduleStatusDesc(scheduleDetailsBean.getScheduleStatus().getDescription());
        if (scheduleDetailsBean.getScheduledDate() != null) {
            scheduleMasterDataType.setScheduledDate(getDateTimeService().getCalendar(scheduleDetailsBean.getScheduledDate()));
        }
        else {
            scheduleMasterDataType.setScheduledDate(getDateTimeService().getCurrentCalendar());
        }
        try {
            if (scheduleDetailsBean.getTime() != null) {
                Date date;
                date = getDateTimeService().convertToSqlDate(scheduleDetailsBean.getTime());
                scheduleMasterDataType.setScheduledTime(getDateTimeService().getCalendar(date));
            }

            scheduleMasterDataType.setPlace(scheduleDetailsBean.getPlace());

            if (scheduleDetailsBean.getProtocolSubDeadline() != null) {
                scheduleMasterDataType.setProtocolSubDeadline(getDateTimeService().getCalendar(
                        scheduleDetailsBean.getProtocolSubDeadline()));
            }
            if (scheduleDetailsBean.getMeetingDate() != null) {
                scheduleMasterDataType.setMeetingDate(getDateTimeService().getCalendar(scheduleDetailsBean.getMeetingDate()));
            }

            if (scheduleDetailsBean.getStartTime() != null) {
                scheduleMasterDataType.setStartTime(getDateTimeService().getCalendar(
                        getDateTimeService().convertToSqlDate(scheduleDetailsBean.getStartTime())));
            }

            if (scheduleDetailsBean.getEndTime() != null) {
                scheduleMasterDataType.setEndTime(getDateTimeService().getCalendar(
                        getDateTimeService().convertToSqlDate(scheduleDetailsBean.getEndTime())));
            }
            if (scheduleDetailsBean.getAgendaProdRevDate() != null) {
                scheduleMasterDataType.setAgendaProdRevDate(getDateTimeService().getCalendar(scheduleDetailsBean.getAgendaProdRevDate()));
            }
        }
        catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }

        scheduleMasterDataType.setMaxProtocols(new BigInteger(String.valueOf(scheduleDetailsBean.getMaxProtocols())));
        if (scheduleDetailsBean.getComments() != null) {
            scheduleMasterDataType.setComments(scheduleDetailsBean.getComments());
        }

        return scheduleMasterDataType;
    }

    public void setNextSchedule(CommitteeScheduleBase scheduleDetailsBean,ScheduleMasterDataType scheduleMasterData) {
        CommitteeScheduleBase nextSchedule = getNextOrPreviousSchedule(scheduleDetailsBean, true);
        if (nextSchedule != null){
            setScheduleMasterData(nextSchedule, scheduleMasterData);
        }
    }

    public void setPreviousSchedule(CommitteeScheduleBase scheduleDetailsBean,ScheduleMasterDataType scheduleMasterDataType) {
        CommitteeScheduleBase prevSchedule = getNextOrPreviousSchedule(scheduleDetailsBean, false);
        if (prevSchedule != null){
            setScheduleMasterData(prevSchedule, scheduleMasterDataType);
        }
    }

    /**
     * 
     * This method returns next or previous schedule depending on the nextFlag
     *
     */
    private CommitteeScheduleBase getNextOrPreviousSchedule(CommitteeScheduleBase scheduleDetailsBean, boolean nextFlag) {
        Map<String, String> scheduleParam = new HashMap<>();
        scheduleParam.put(COMMITTEE_ID_FK, scheduleDetailsBean.getParentCommittee().getId().toString());
        List<IacucCommitteeSchedule> schedules = (List<IacucCommitteeSchedule>) getBusinessObjectService().findMatchingOrderBy(IacucCommitteeSchedule.class,
                scheduleParam, SCHEDULED_DATE, true);
        if (!schedules.isEmpty()) {
            int size = schedules.size();
            for (int i = 0; i < size; i++) {
                CommitteeScheduleBase schedule = schedules.get(i);
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


    public void setCommitteeMembershipService(CommitteeMembershipServiceBase committeeMembershipService) {
        this.committeeMembershipService = committeeMembershipService;
    }

    public CommitteeMembershipServiceBase getCommitteeMembershipService() {
        return committeeMembershipService;
    }

    public IacucPrintXmlUtilService getPrintXmlUtilService() {
        return this.printXmlUtilService;
    }

    public void setPrintXmlUtilService(IacucPrintXmlUtilService printXmlUtilService) {
        this.printXmlUtilService = printXmlUtilService;
    }

}
