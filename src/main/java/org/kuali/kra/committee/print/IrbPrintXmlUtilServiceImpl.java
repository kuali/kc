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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;
import org.kuali.kra.meeting.CommScheduleActItem;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

import edu.mit.irb.irbnamespace.MinutesDocument.Minutes;
import edu.mit.irb.irbnamespace.PersonDocument.Person;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.Submissions;
import edu.mit.irb.irbnamespace.ProtocolSubmissionDocument.ProtocolSubmission;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails.ActionType;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails.SubmissionChecklistInfo;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails.SubmissionChecklistInfo.Checklists;

/**
 * This class...
 */
public class IrbPrintXmlUtilServiceImpl implements IrbPrintXmlUtilService {

    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private ReviewCommentsService reviewCommentsService;
    
    public void setPersonXml(KcPerson person, Person personType) {
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

    public void setPersonRolodexType(ProtocolPerson protocolPerson, Person personType) {
        if (protocolPerson.getPerson() == null) {
            ProtocolPersonRolodex rolodex = getBusinessObjectService().findBySinglePrimaryKey(ProtocolPersonRolodex.class,
                    protocolPerson.getRolodexId());
            setPersonXml(rolodex, personType);
        }
        else {
            KcPerson kcPerson = protocolPerson.getPerson();
            setPersonXml(kcPerson, personType);
        }
    }


    public void setPersonXml(ProtocolPersonRolodex rolodex, Person personType) {
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
        // person.setCountry(rolodexBean.getCountryName()!= null? rolodexBean.getCountryName() : null);
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
    public void setProtocolSubmissionAction(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission,
            SubmissionDetails protocolSubmissionDetail) {
        ProtocolAction protcolAction = findProtocolActionForSubmission(protocolSubmission);
        if (protcolAction != null) {
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

    protected ProtocolAction findProtocolActionForSubmission(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("submissionIdFk", protocolSubmission.getSubmissionId());
        List<ProtocolAction> actions = (List) getBusinessObjectService().findMatchingOrderBy(ProtocolAction.class, param,
                "actionId", true);
        return actions.isEmpty() ? null : actions.get(0);
    }

    public void setSubmissionCheckListinfo(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission,
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

    public void setMinutes(CommitteeSchedule scheduleDetailsBean, Schedule schedule) {
        List<CommitteeScheduleMinute> vecMinutes = scheduleDetailsBean.getCommitteeScheduleMinutes();
        if (!vecMinutes.isEmpty()) {
            for (CommitteeScheduleMinute minuteEntryInfoBean : vecMinutes) {
                if (!minuteEntryInfoBean.getMinuteEntryTypeCode().equals("3") && 
                        !minuteEntryInfoBean.getPrivateCommentFlag()) {
                    if (reviewCommentsService.getReviewerCommentsView(minuteEntryInfoBean)){
                        if(!minuteEntryInfoBean.getPrivateCommentFlag()&& minuteEntryInfoBean.isFinalFlag()){ 
                            addMinute(scheduleDetailsBean, minuteEntryInfoBean, schedule.addNewMinutes());
                        }
                    }
                   
                }
            }
        }
    }

    protected void addMinute(CommitteeSchedule committeeSchedule, CommitteeScheduleMinute committeeScheduleMinute, Minutes minutesType) {
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

    }

    protected String getOtherItemDescription(CommitteeSchedule committeeSchedule, CommitteeScheduleMinute committeeScheduleMinute) {
        List<CommScheduleActItem> actionItems = committeeSchedule.getCommScheduleActItems();
        for (CommScheduleActItem commScheduleActItem : actionItems) {
            if (committeeScheduleMinute.getMinuteEntryTypeCode().equals("4")
                    && committeeScheduleMinute.getProtocol().getProtocolNumber().equals(commScheduleActItem.getActionItemNumber())) {
                return commScheduleActItem.getItemDescription();
            }
        }
        return committeeScheduleMinute.getProtocol().getProtocolNumber();
    }

    public void setProcotolMinutes(CommitteeSchedule committeeSchedule,
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, ProtocolSubmission protocolSubmissionType) {
        List<CommitteeScheduleMinute> minutes = committeeSchedule.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinute minuteEntryInfoBean : minutes) {
            Protocol protocol = minuteEntryInfoBean.getProtocol();
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

    public void setProcotolSubmissionMinutes(CommitteeSchedule committeeSchedule,
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, Submissions submissionsType) {
        List<CommitteeScheduleMinute> minutes = committeeSchedule.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinute minuteEntryInfoBean : minutes) {
            Protocol protocol = minuteEntryInfoBean.getProtocol();
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
    public void setProtocolReviewMinutes(CommitteeSchedule committeeSchedule,
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, Submissions submissionsType) {
        List<CommitteeScheduleMinute> minutes = committeeSchedule.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinute minuteEntryInfoBean : minutes) {
            Protocol protocol = minuteEntryInfoBean.getProtocol();
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
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
        return businessObjectService;
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
