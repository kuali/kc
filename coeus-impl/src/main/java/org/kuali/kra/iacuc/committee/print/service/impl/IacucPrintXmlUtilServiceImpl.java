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
package org.kuali.kra.iacuc.committee.print.service.impl;

import edu.mit.coeus.xml.iacuc.*;
import edu.mit.coeus.xml.iacuc.ProtocolType.Submissions;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommScheduleActItemBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.committee.print.service.IacucPrintXmlUtilService;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRolodex;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucPrintXmlUtilServiceImpl implements IacucPrintXmlUtilService {

    private DateTimeService dateTimeService;
    private ReviewCommentsService reviewCommentsService;
    
    public void setPersonXml(KcPerson person, PersonType personType) {
        personType.setPersonID(person.getPersonId());
        personType.setFullname(person.getFullName());
        personType.setLastName(person.getLastName());
        personType.setFirstname(person.getFirstName());
        // case 1646 start
        personType.setDegree(person.getDegree() != null ? person.getDegree() : null);
        personType.setSalutation(person.getSaluation() != null ? person.getSaluation() : null);
        // case 1646 end
        personType.setEmail(person.getEmailAddress() != null ? person.getEmailAddress() : null);
        personType.setOfficeLocation(person.getOfficeLocation() != null ? person.getOfficeLocation() : null);
        personType.setOfficePhone(person.getOfficePhone() != null ? person.getOfficePhone() : null);
        personType.setDirectoryTitle(person.getDirectoryTitle() != null ? person.getDirectoryTitle() : null);
        // Added for Case 2081 - Investigators Address in templates : Bring forward the address of an individual in correspondences
        // - Start
        personType.setAddressLine1(person.getAddressLine1() != null ? person.getAddressLine1() : null);
        personType.setAddressLine2(person.getAddressLine2() != null ? person.getAddressLine2() : null);
        personType.setAddressLine3(person.getAddressLine3() != null ? person.getAddressLine3() : null);
        personType.setCity(person.getCity() != null ? person.getCity() : null);
        personType.setState(person.getState() != null ? person.getState() : null);
        personType.setCountry(person.getCountryCode() != null ? person.getCountryCode() : null);
        personType.setCountryCode(person.getCountryCode() != null ? person.getCountryCode() : null);
        personType.setPostalCode(person.getPostalCode() != null ? person.getPostalCode() : null);
        personType.setFaxNumber(person.getFaxNumber() != null ? person.getFaxNumber() : null);
        personType.setPagerNumber(person.getPagerNumber() != null ? person.getPagerNumber() : null);
        personType.setMobilePhoneNumber(person.getMobilePhoneNumber() != null ? person.getMobilePhoneNumber() : null);
        personType.setDepartmentOrganization(person.getDirectoryDepartment() != null ? person.getDirectoryDepartment() : null);

    }

    public void setPersonRolodexType(ProtocolPersonBase protocolPerson, PersonType personType) {
        if (protocolPerson.getPerson() == null) {
            IacucProtocolPersonRolodex rolodex = getBusinessObjectService().findBySinglePrimaryKey(IacucProtocolPersonRolodex.class,
                    protocolPerson.getRolodexId());
            setPersonXml(rolodex, personType);
        }
        else {
            KcPerson kcPerson = protocolPerson.getPerson();
            setPersonXml(kcPerson, personType);
        }
    }


    public void setPersonXml(ProtocolPersonRolodexBase rolodex, PersonType personType) {
        personType.setPersonID(rolodex.getRolodexId().toString());
        String fullName = rolodex.getMiddleName() != null ? rolodex.getLastName() + "," + rolodex.getFirstName()
                + rolodex.getMiddleName() : rolodex.getLastName() + "," + rolodex.getFirstName();
        personType.setFullname(fullName);
        personType.setLastName(rolodex.getLastName());
        personType.setFirstname(rolodex.getFirstName());
        personType.setEmail(rolodex.getEmailAddress() != null ? rolodex.getEmailAddress() : null);
        personType.setAddressLine1(rolodex.getAddressLine1() != null ? rolodex.getAddressLine1() : null);
        personType.setAddressLine2(rolodex.getAddressLine2() != null ? rolodex.getAddressLine2() : null);
        personType.setAddressLine3(rolodex.getAddressLine3() != null ? rolodex.getAddressLine3() : null);
        personType.setCity(rolodex.getCity() != null ? rolodex.getCity() : null);
        personType.setState(rolodex.getState() != null ? rolodex.getState() : null);
        personType.setCountryCode(rolodex.getCountryCode() != null ? rolodex.getCountryCode() : null);
        personType.setPostalCode(rolodex.getPostalCode() != null ? rolodex.getPostalCode() : null);
        personType.setFaxNumber(rolodex.getFaxNumber() != null ? rolodex.getFaxNumber() : null);
        personType.setMobilePhoneNumber(rolodex.getPhoneNumber() != null ? rolodex.getPhoneNumber() : null);
        personType.setDepartmentOrganization(rolodex.getOrganization() != null ? rolodex.getOrganization() : null);
    }

    /**
     * This method is to set ProtocolActionType
     * 
     * @param protocolSubmission
     * @param protocolSubmissionDetail
     */
    public void setProtocolSubmissionAction(IacucProtocolSubmission protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail) {
        ProtocolActionBase protcolAction = findProtocolActionForSubmission(protocolSubmission);
        if (protcolAction != null) {
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

    protected ProtocolActionBase findProtocolActionForSubmission(IacucProtocolSubmission protocolSubmission) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("submissionIdFk", protocolSubmission.getSubmissionId());
        List<ProtocolActionBase> actions = (List) getBusinessObjectService().findMatchingOrderBy(IacucProtocolAction.class, param,
                "actionId", true);
        return actions.isEmpty() ? null : actions.get(0);
    }

    public void setSubmissionCheckListinfo(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail) {
        edu.mit.coeus.xml.iacuc.SubmissionDetailsType.SubmissionChecklistInfo submissionChecklistInfo = protocolSubmissionDetail.addNewSubmissionChecklistInfo();
        String formattedCode = new String();
    }

    public void setMinutes(CommitteeScheduleBase scheduleDetailsBean, ScheduleType schedule) {
        List<CommitteeScheduleMinuteBase> vecMinutes = scheduleDetailsBean.getCommitteeScheduleMinutes();
        if (!vecMinutes.isEmpty()) {
            for (CommitteeScheduleMinuteBase minuteEntryInfoBean : vecMinutes) {
                if (!minuteEntryInfoBean.getMinuteEntryTypeCode().equals("3") && 
                        !minuteEntryInfoBean.getPrivateCommentFlag()) {
                    if (reviewCommentsService.getReviewerCommentsView(minuteEntryInfoBean)){
                        if(!minuteEntryInfoBean.getPrivateCommentFlag()&& minuteEntryInfoBean.isFinalFlag()){
                            MinuteType mt = schedule.insertNewMinutes(schedule.getMinutesArray().length);
                            addMinute(scheduleDetailsBean, minuteEntryInfoBean, mt);
                        }
                    }
                }
            }
        }
    }

    protected void addMinute(CommitteeScheduleBase committeeSchedule, CommitteeScheduleMinuteBase committeeScheduleMinute, MinuteType minutesType) {
        committeeScheduleMinute.refreshNonUpdateableReferences();
        minutesType.setScheduleId(committeeScheduleMinute.getScheduleIdFk().toString());
        minutesType.setEntryNumber(new BigInteger(String.valueOf(committeeScheduleMinute.getEntryNumber())));
        minutesType.setEntryTypeCode(new BigInteger(String.valueOf(committeeScheduleMinute.getMinuteEntryTypeCode())));
        minutesType.setEntryTypeDesc(committeeScheduleMinute.getMinuteEntryType().getDescription());
        minutesType
                .setProtocolContingencyCode(committeeScheduleMinute.getProtocolContingencyCode() != null ? committeeScheduleMinute
                        .getProtocolContingencyCode() : null);
        minutesType.setMinuteEntry(committeeScheduleMinute.getMinuteEntry());
        minutesType.setPrivateCommentFlag(committeeScheduleMinute.getPrivateCommentFlag());
        committeeScheduleMinute.refreshReferenceObject("protocol");
        if (committeeScheduleMinute.getProtocol() != null && committeeScheduleMinute.getProtocol().getProtocolNumber() != null) {
            String otherItemDescription = getOtherItemDescription(committeeSchedule, committeeScheduleMinute);
            if (otherItemDescription != null) {
                minutesType.setProtocolNumber(otherItemDescription);
            }
            else {
                minutesType.setProtocolNumber(committeeScheduleMinute.getProtocol().getProtocolNumber());
            }
        }
        minutesType.setEntrySortCode(BigInteger.valueOf(committeeScheduleMinute.getMinuteEntryType().getSortId()));
        Calendar updateTime = Calendar.getInstance();
        updateTime.setTimeInMillis(committeeScheduleMinute.getUpdateTimestamp().getTime());
        minutesType.setUpdateTimestamp(updateTime);
        minutesType.setUpdateUser(committeeScheduleMinute.getUpdateUserFullName());

    }

    protected String getOtherItemDescription(CommitteeScheduleBase committeeSchedule, CommitteeScheduleMinuteBase committeeScheduleMinute) {
        List<CommScheduleActItemBase> actionItems = committeeSchedule.getCommScheduleActItems();
        for (CommScheduleActItemBase commScheduleActItem : actionItems) {
            if (committeeScheduleMinute.getMinuteEntryTypeCode().equals("4")
                    && committeeScheduleMinute.getProtocol().getProtocolNumber().equals(commScheduleActItem.getActionItemNumber())) {
                return commScheduleActItem.getItemDescription();
            }
        }
        return committeeScheduleMinute.getProtocol().getProtocolNumber();
    }

    public void setProcotolMinutes(CommitteeScheduleBase committeeSchedule,
            org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission, ProtocolSubmissionType protocolSubmissionType) {       
        List<CommitteeScheduleMinuteBase> minutes = committeeSchedule.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinuteBase minuteEntryInfoBean : minutes) {
            ProtocolBase protocol = minuteEntryInfoBean.getProtocol();
            if (protocol != null && protocol.getProtocolNumber() != null) {
                if (protocol.getProtocolNumber().equals(protocolSubmission.getProtocolNumber())
                        && protocol.getProtocolSubmission() != null
                        && protocol.getProtocolSubmission().getSubmissionNumber().equals(protocolSubmission.getSubmissionNumber())) {
                    if (reviewCommentsService.getReviewerCommentsView(minuteEntryInfoBean)){
                        if(!minuteEntryInfoBean.getPrivateCommentFlag()&& minuteEntryInfoBean.isFinalFlag()){
                        addMinute(committeeSchedule, minuteEntryInfoBean, protocolSubmissionType.addNewMinutes());
                        }
                    }
                    
                }
            }
        }
    }

    public void setProcotolSubmissionMinutes(CommitteeScheduleBase committeeSchedule,
            ProtocolSubmissionBase protocolSubmission, Submissions submissionsType) {      
        List<CommitteeScheduleMinuteBase> minutes = committeeSchedule.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinuteBase minuteEntryInfoBean : minutes) {
            ProtocolBase protocol = minuteEntryInfoBean.getProtocol();
            if (protocol != null && protocol.getProtocolNumber() != null) {
                if (protocol.getProtocolNumber().equals(protocolSubmission.getProtocolNumber())
                      && protocol.getProtocolSubmission() != null
                        && protocol.getProtocolSubmission().getSubmissionNumber().equals(protocolSubmission.getSubmissionNumber())) {
                    if (reviewCommentsService.getReviewerCommentsView(minuteEntryInfoBean)){
                        addMinute(committeeSchedule, minuteEntryInfoBean, submissionsType.addNewMinutes());
                    }
                    
                }
            }  
        }
    }
    /**
     * 
     * This method for set the review minute in correspondence Letter.
     * @param committeeSchedule
     * @param protocolSubmission
     * @param submissionsType
     */
    public void setProtocolReviewMinutes(CommitteeScheduleBase committeeSchedule,
            org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission, Submissions submissionsType) {      
        List<CommitteeScheduleMinuteBase> minutes = committeeSchedule.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinuteBase minuteEntryInfoBean : minutes) {
            ProtocolBase protocol = minuteEntryInfoBean.getProtocol();
            if (protocol != null && protocol.getProtocolNumber() != null) {
                if (protocol.getProtocolNumber().equals(protocolSubmission.getProtocolNumber())
                      && protocol.getProtocolSubmission() != null
                        && protocol.getProtocolSubmission().getSubmissionNumber().equals(protocolSubmission.getSubmissionNumber())) {
                    if (reviewCommentsService.getReviewerCommentsView(minuteEntryInfoBean)){
                        addMinute(committeeSchedule, minuteEntryInfoBean, submissionsType.addNewMinutes());
                    }
                    
                }
            }  
        }
    }

    /**
     * 
     * Sets the ReviewCommentsService attribute value.
     * 
     * @param reviewCommentsService The ReviewCommentsService to set.
     */
    public void setReviewCommentsService(ReviewCommentsService reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * Sets the dateTimeService attribute value.
     * 
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    /**
     * Gets the dateTimeService attribute.
     * 
     * @return Returns the dateTimeService.
     */
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }    
    
}
