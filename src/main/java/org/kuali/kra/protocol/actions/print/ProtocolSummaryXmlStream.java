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
package org.kuali.kra.protocol.actions.print;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.common.committee.print.CommitteeXmlStream;
import org.kuali.kra.common.committee.print.PrintXmlUtilService;
import org.kuali.kra.common.committee.print.ScheduleXmlStream;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomData;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRole;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.threers.IacucPrinciples;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.xmlstream.PrintBaseXmlStream;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.noteattachment.ProtocolNotepad;
import org.kuali.kra.protocol.personnel.ProtocolPerson;
import org.kuali.kra.protocol.personnel.ProtocolPersonRole;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodex;
import org.kuali.kra.protocol.personnel.ProtocolUnit;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.protocol.protocol.location.ProtocolLocation;
import org.kuali.kra.protocol.protocol.reference.ProtocolReference;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchArea;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReview;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import edu.mit.coeus.xml.iacuc.AmendRenewalType;
import edu.mit.coeus.xml.iacuc.CorrespondentType;
import edu.mit.coeus.xml.iacuc.ExceptionType;
import edu.mit.coeus.xml.iacuc.FundingSourceType;
import edu.mit.coeus.xml.iacuc.InvestigatorType;
import edu.mit.coeus.xml.iacuc.KeyStudyPersonType;
import edu.mit.coeus.xml.iacuc.LocationType;
import edu.mit.coeus.xml.iacuc.NotesType;
import edu.mit.coeus.xml.iacuc.OtherDataType;
import edu.mit.coeus.xml.iacuc.PersonType;
import edu.mit.coeus.xml.iacuc.PrinciplesType;
import edu.mit.coeus.xml.iacuc.PrintRequirementType;
import edu.mit.coeus.xml.iacuc.ProtocolActionsType;
import edu.mit.coeus.xml.iacuc.ProtocolMasterDataType;
import edu.mit.coeus.xml.iacuc.ProtocolReviewerType;
import edu.mit.coeus.xml.iacuc.ProtocolType;
import edu.mit.coeus.xml.iacuc.ReferencesType;
import edu.mit.coeus.xml.iacuc.ResearchAreaType;
import edu.mit.coeus.xml.iacuc.RolesType;
import edu.mit.coeus.xml.iacuc.ScheduleSummaryType;
import edu.mit.coeus.xml.iacuc.SpecialReviewType;
import edu.mit.coeus.xml.iacuc.SpeciesType;
import edu.mit.coeus.xml.iacuc.SubmissionDetailsType;
import edu.mit.coeus.xml.iacuc.UserRolesType;
import edu.mit.coeus.xml.iacuc.ProtocolType.Submissions;

/**
 * This class is to generate Protocol Summary Xml file
 */
public abstract class ProtocolSummaryXmlStream extends PrintBaseXmlStream {
    private BusinessObjectService businessObjectService;
    private PrintXmlUtilService printXmlUtilService;
    private ScheduleXmlStream scheduleXmlStream;
    private CommitteeXmlStream committeeXmlStream;
    
    
    protected static final String FLAG_YES = "Yes";
    protected static final String FLAG_NO = "No";


    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#generateXmlStream(org.kuali.kra.bo.KraPersistableBusinessObjectBase, java.util.Map)
     */
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        IacucProtocol protocol =  (IacucProtocol) printableBusinessObject;
        edu.mit.coeus.xml.iacuc.ProtocolDocument protocolDocument = edu.mit.coeus.xml.iacuc.ProtocolDocument.Factory.newInstance();
        protocolDocument.setProtocol(getProtocolSummary(protocol, reportParameters));
        Map<String, XmlObject> map = new HashMap<String,XmlObject>();
        map.put("PrtocolSummary", protocolDocument);
        
        return map; 
    }
    
    private String getOptionString(boolean printOption) {
        return printOption ? "1" : "0";
    }
    
    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#generateXmlStream(org.kuali.kra.bo.KraPersistableBusinessObjectBase, java.util.Map)
     */
    public ProtocolType getProtocolSummary(KraPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> htData) {
        IacucProtocol protocol = (IacucProtocol) printableBusinessObject;
        protocol.refreshNonUpdateableReferences();
        ProtocolType protocolType = ProtocolType.Factory.newInstance();
        
        List<PrintRequirementType> printRequirementTypeList = new ArrayList<PrintRequirementType>();
        PrintRequirementType printRequirementType = PrintRequirementType.Factory.newInstance();
        if (htData != null) {
            ProtocolSummaryPrintOptions summaryOptions = (ProtocolSummaryPrintOptions) htData.get(ProtocolSummaryPrintOptions.class);
            printRequirementType.setOrganizationRequired(getOptionString(summaryOptions.isOrganizaition()));
            printRequirementType.setSpeciesGroupRequired(getOptionString(summaryOptions.isSpeciesAndGroups()));
            printRequirementType.setInvestigatorsRequired(getOptionString(summaryOptions.isInvestigator()));
            printRequirementType.setKeyPersonsRequired(getOptionString(summaryOptions.isStudyPersonnels()));
            printRequirementType.setCorrespondentsRequired(getOptionString(summaryOptions.isCorrespondents()));
            printRequirementType.setResearchAreasRequired(getOptionString(summaryOptions.isAreaOfResearch()));
            printRequirementType.setFundingSourcesRequired(getOptionString(summaryOptions.isFundingSource()));
            printRequirementType.setActionsRequired(getOptionString(summaryOptions.isActions()));
            printRequirementType.setProceduresRequired(getOptionString(summaryOptions.isProcedure()));
            printRequirementType.setSpecialReviewRequired(getOptionString(summaryOptions.isSpecialReview()));
            printRequirementType.setRiskLevelsRequired(getOptionString(summaryOptions.isRiskLevel()));
            printRequirementType.setNotesRequired(getOptionString(summaryOptions.isNotes()));
            printRequirementType.setAmendRenewSRequired(getOptionString(summaryOptions.isAmmendmentRenewalSummary()));
            printRequirementType.setOtherDataRequired(getOptionString(summaryOptions.isOtherData()));
            printRequirementType.setUserRolesRequired(getOptionString(summaryOptions.isRoles()));
            printRequirementType.setReferencesRequired(getOptionString(summaryOptions.isReferences()));
            printRequirementType.setPrinciplesRequired(getOptionString(summaryOptions.isPrinciples()));
        }
        printRequirementTypeList.add(printRequirementType);
        protocolType.setPrintRequirementArray(printRequirementTypeList.toArray(new PrintRequirementType[0]));        
        printRequirementType.setCurrentDate(getDateTimeService().getCurrentCalendar());
        
        setProtocolPersons(protocol,protocolType);
        setProtocolLocations(protocol,protocolType);
        setProtocolResearchAreas(protocol,protocolType);
        setProtocolFundingResources(protocol,protocolType);
        setProtocolActions(protocol,protocolType);
        setProtocolSpecialReviewes(protocol,protocolType);
        setProtocolRiskLevels(protocol,protocolType);
        setProtocolNotes(protocol,protocolType);
        setProtocolAmendmentRenewals(protocol,protocolType);
        setProtocolOtherData(protocol,protocolType);
        setProtocolReferences(protocol,protocolType);
        setProtocolUserRoles(protocol,protocolType);
        setProtocolMasterData(protocol, protocolType);
        setSpeciesGoups(protocol, protocolType);
        setProcedures(protocol, protocolType);
        setPrinciples(protocol, protocolType);
        setSubmissionDetails(protocol, protocolType);
        
        return protocolType;
    }
   
    private String getProposalParameterValue(String param) {
        ParameterService parameterService = KraServiceLocator.getService(ParameterService.class);
        return parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, param);
    }

    /**
     * Sets the protocolRoles.
     * 
     * @param protocol
     * @param protocolType
     * @return     
     */
    private void setProtocolUserRoles(Protocol protocol,ProtocolType protocolType) {
        UserRolesType userRolesType = UserRolesType.Factory.newInstance();
        List<RolesType> rolesTypeList = new ArrayList<RolesType>();
        List<UserRolesType> userRolesTypeList = new ArrayList<UserRolesType>();
        for (ProtocolPerson protocolPerson : protocol.getProtocolPersons()) {
            userRolesType.setRoleDesc(protocolPerson.getProtocolPersonRole().getDescription());            
            userRolesType.setUnitName(protocolPerson.getPerson().getUnit().getUnitName());
            userRolesType.setUnitNumber(protocolPerson.getPerson().getUnit().getUnitNumber());
            userRolesType.setUserName(protocolPerson.getUserName());
            userRolesType.setUserId(protocolPerson.getPersonId());
            userRolesTypeList.add(userRolesType);     
        }
        RolesType rolesType = RolesType.Factory.newInstance();
        rolesType.setUserRolesArray(userRolesTypeList.toArray(new UserRolesType[0]));
        rolesTypeList.add(rolesType);
            
        protocolType.setUserRolesArray(rolesTypeList.toArray(new RolesType[0]));
    }
    
    private void setSubmissionDetails(IacucProtocol protocol, ProtocolType protocolType) {
        addSubmissionDetails(protocol, protocolType, null,FLAG_NO);        
    }
    
    private void addSubmissionDetails(IacucProtocol protocol, ProtocolType protocolType, Integer submissionNumber, String currentFlag) {
        IacucProtocolSubmission submissionInfoBean = null;
        submissionInfoBean = (IacucProtocolSubmission) (submissionNumber == null ? protocol.getProtocolSubmission()
                                  : findProtocolSubmission(protocol,submissionNumber));
        if (submissionInfoBean == null || submissionInfoBean.getSubmissionNumber() == null) {
            return;
        }
        submissionInfoBean.refreshNonUpdateableReferences();
        edu.mit.coeus.xml.iacuc.ProtocolType.Submissions submission = protocolType.addNewSubmissions();
        SubmissionDetailsType submissionDetail = submission.addNewSubmissionDetails();
        submissionDetail.setAbstainerCount(BigInteger.valueOf(submissionInfoBean.getAbstainerCount()));
        if (submissionInfoBean.getNoVoteCount() != null) {
            submissionDetail.setNoVote(BigInteger.valueOf(submissionInfoBean.getNoVoteCount()));
        }
        submissionDetail.setProtocolNumber(submissionInfoBean.getProtocolNumber());
        if (submissionInfoBean.getProtocolReviewType() != null) {
            submissionDetail.setProtocolReviewTypeCode(new BigInteger(submissionInfoBean.getProtocolReviewTypeCode()));
            submissionDetail.setProtocolReviewTypeDesc(submissionInfoBean.getProtocolReviewType().getDescription());
        }
        List<ProtocolReviewer> vecReviewers = submissionInfoBean.getProtocolReviewers();
        for (ProtocolReviewer protocolReviewer : vecReviewers) {
            protocolReviewer.refreshNonUpdateableReferences();
            ProtocolReviewerType protocolReviewerType = submissionDetail
                    .addNewProtocolReviewer();
            if (protocolReviewer.getProtocolReviewerType() != null) {
                protocolReviewerType.setReviewerTypeDesc(protocolReviewer.getProtocolReviewerType().getDescription());
                protocolReviewerType.setReviewerTypeCode(new BigInteger(String.valueOf(protocolReviewer.getReviewerTypeCode())));
            }
            PersonType personType = protocolReviewerType.addNewPerson();
            boolean isNonEmployee = protocolReviewer.getNonEmployeeFlag();
            if (isNonEmployee) {
                ProtocolPersonRolodex rolodex = getBusinessObjectService().findBySinglePrimaryKey(ProtocolPersonRolodex.class,
                        protocolReviewer.getRolodexId());
                getPrintXmlUtilService().setPersonXml(rolodex, personType);

            } else {
                KcPerson kcPerson = KraServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(protocolReviewer.getPersonId()); 
                getPrintXmlUtilService().setPersonXml(kcPerson, personType);
            }
        }
        submissionDetail.setSubmissionComments(submissionInfoBean.getComments());
        if (submissionInfoBean.getSubmissionDate() != null) {
            submissionDetail.setSubmissionDate(getDateTimeService().getCalendar(submissionInfoBean.getSubmissionDate()));
        } else {
            submissionDetail.setSubmissionDate(getDateTimeService().getCurrentCalendar());
        }
        submissionDetail.setSubmissionNumber(BigInteger.valueOf(submissionInfoBean.getSubmissionNumber()));
        if (submissionInfoBean.getSubmissionStatus() != null) {
            submissionDetail.setSubmissionStatusCode(new BigInteger(submissionInfoBean.getSubmissionStatusCode()));
            submissionDetail.setSubmissionStatusDesc(submissionInfoBean.getSubmissionStatus().getDescription());
        }
        if (submissionInfoBean.getProtocolSubmissionType() != null) {
            submissionDetail.setSubmissionTypeCode(new BigInteger(submissionInfoBean.getSubmissionTypeCode()));
            submissionDetail.setSubmissionTypeDesc(submissionInfoBean.getProtocolSubmissionType().getDescription());
        }
        if (submissionInfoBean.getProtocolSubmissionQualifierType() != null) {
            submissionDetail.setSubmissionTypeQualifierCode(new BigInteger(String.valueOf(submissionInfoBean
                    .getSubmissionTypeQualifierCode())));
            submissionDetail.setSubmissionTypeQualifierDesc(submissionInfoBean.getProtocolSubmissionQualifierType()
                    .getDescription());
        }
        submissionDetail.setVotingComments(submissionInfoBean.getVotingComments());
        if (submissionInfoBean.getYesVoteCount() != null) {
            submissionDetail.setYesVote(BigInteger.valueOf(submissionInfoBean.getYesVoteCount()));
        }
        getPrintXmlUtilService().setProtocolSubmissionAction(submissionInfoBean, submissionDetail);
        getPrintXmlUtilService().setSubmissionCheckListinfo(submissionInfoBean, submissionDetail);
        submission.setCurrentSubmissionFlag(currentFlag);
        setMinutes(submissionInfoBean, submission);
        if (submissionInfoBean.getCommitteeId() != null) {
            CommonCommittee committee = submissionInfoBean.getCommittee();            
            getCommitteeXmlStream().setCommitteeMasterData(committee, submission.addNewCommitteeMasterData());
            getCommitteeXmlStream().setCommitteeMembers(committee, submission);
        }

        if (submissionInfoBean.getScheduleId() != null) {
            CommonCommitteeSchedule committeeSchedule = submissionInfoBean.getCommitteeSchedule();
            getScheduleXmlStream().setScheduleMasterData(committeeSchedule, submission.addNewScheduleMasterData());
            ScheduleSummaryType nextSchedule = submission.addNewNextSchedule();
            getScheduleXmlStream().setNextSchedule(committeeSchedule, nextSchedule.addNewScheduleMasterData());
        }
    }
    
    private ProtocolSubmission findProtocolSubmission(org.kuali.kra.protocol.Protocol protocol, Integer submissionNumber) {
        List<ProtocolSubmission> protocolSubmissions = protocol.getProtocolSubmissions();
        for (ProtocolSubmission protocolSubmission : protocolSubmissions) {
            if (protocolSubmission.getSubmissionNumber().equals(submissionNumber)) {
                return protocolSubmission;
            }
        }
        return null;
    }
    
    /**
     * This method...
     * @param submissionInfoBean
     * @param submission
     * @return
     */
    protected void setMinutes(org.kuali.kra.protocol.actions.submit.ProtocolSubmission submissionInfoBean,
            Submissions submission) {
        CommonCommitteeSchedule committeeSchedule = submissionInfoBean.getCommitteeSchedule();
        if (committeeSchedule != null) {
            getPrintXmlUtilService().setProtocolReviewMinutes(committeeSchedule, submissionInfoBean, submission);
        }
    }
    
    /**
     * Sets the references.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolReferences(Protocol protocol,ProtocolType protocolType) {
        ReferencesType referencesType = ReferencesType.Factory.newInstance();
        List<ReferencesType> referncestypeList = new ArrayList<ReferencesType>();        
        for (ProtocolReference protocolReference : protocol.getProtocolReferences()) {
            referencesType.setReferenceNumber(protocolReference.getProtocolReferenceNumber());
            if (protocolReference.getProtocolReferenceType() != null) {
                referencesType.setReferenceTypeCode(protocolReference.getProtocolReferenceType().getProtocolReferenceTypeCode());
                referencesType.setReferenceTypeDesc(protocolReference.getProtocolReferenceType().getDescription());
            }
            referencesType.setReferenceKey(protocolReference.getReferenceKey());
            referencesType.setApprovalDate(getDateTimeService().getCalendar(protocolReference.getApprovalDate()));
            referencesType.setApplicationDate(getDateTimeService().getCalendar(protocolReference.getApplicationDate()));
            referencesType.setComments(protocolReference.getComments());
            referncestypeList.add(referencesType);
        }

        protocolType.setReferencesArray(referncestypeList.toArray(new ReferencesType[0]));
    }
    
    /**
     * Sets the otherData.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolOtherData(IacucProtocol protocol,ProtocolType protocolType) {
        List<OtherDataType> otherDataTypeList = new ArrayList<OtherDataType>();
        for (IacucProtocolCustomData iacucProtocolCustomData : protocol.getIacucProtocolCustomDataList()) {
            OtherDataType otherDataType = OtherDataType.Factory.newInstance();
            otherDataType.setColumnName(iacucProtocolCustomData.getCustomAttribute().getName());
            otherDataType.setColumnValue(iacucProtocolCustomData.getCustomAttribute().getValue());
            otherDataTypeList.add(otherDataType);            
        }        
        protocolType.setOthersDataArray(otherDataTypeList.toArray(new OtherDataType[0]));
    }
    
    /**
     * Sets the amendRenewal.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolAmendmentRenewals(Protocol protocol,ProtocolType protocolType) {
        List<AmendRenewalType> amendRenewalTypeList = new ArrayList<AmendRenewalType>();
        for (ProtocolAmendRenewal protocolAmendRenewal : protocol.getProtocolAmendRenewals()) {
            AmendRenewalType amendRenewalType = AmendRenewalType.Factory.newInstance();            
            amendRenewalType.setVersion(protocolAmendRenewal.getVersionNumber().toString());
            if (protocolAmendRenewal.getProtocol() != null 
                    && protocolAmendRenewal.getProtocol().getProtocolStatus() != null) {
                amendRenewalType.setProtocolStatusDesc(protocolAmendRenewal.getProtocol().getProtocolStatus().getDescription());
            }            
            amendRenewalType.setDateCreated(getDateTimeService().getCalendar(protocolAmendRenewal.getDateCreated()));
            amendRenewalType.setSummary(protocolAmendRenewal.getSummary());
            amendRenewalTypeList.add(amendRenewalType);
        }
        protocolType.setAmenRenewalArray(amendRenewalTypeList.toArray(new AmendRenewalType[0]));
    }

    /**
     * Sets the notes.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolNotes(Protocol protocol,ProtocolType protocolType) {
        List<NotesType> notesTypelist = new ArrayList<NotesType>();
        for (ProtocolNotepad protocolNotepad:protocol.getNotepads()) {
            NotesType notesType = NotesType.Factory.newInstance();
            notesType.setComments(protocolNotepad.getComments());
            notesType.setEntryNumber(protocolNotepad.getEntryNumber());
            if (protocolNotepad.getRestrictedView()) {
                notesType.setRestrictedView(FLAG_YES);
            } else {
                notesType.setRestrictedView(FLAG_NO);
            }            
            notesType.setUpdateUser(protocolNotepad.getUpdateUser());
            notesType.setUpdateTimestamp(getDateTimeService().getCalendar(protocolNotepad.getUpdateTimestamp()));
            notesTypelist.add(notesType);
        }        
        protocolType.setNotesArray(notesTypelist.toArray(new NotesType[0]));
    }
    
    private Calendar convertDateToCalendar(Date date) {
        return date == null ? null : getDateTimeService().getCalendar(date);
    }

    /**
     * Sets the riskLevels.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolRiskLevels(Protocol protocol,ProtocolType protocolType) {
             // TODO - IRB specific code to be removed        
        //        List<ProtocolRiskLevel> protocolRiskLevels = protocol.getProtocolRiskLevels();
        //        for (ProtocolRiskLevel protocolRiskLevelBean : protocolRiskLevels) {
        //            protocolRiskLevelBean.refreshNonUpdateableReferences();
        //            ProtocolRiskLevelsType protocolRiskLevelsType = protocolSummary.addNewProtocolRiskLevels();
        //            protocolRiskLevelsType.setComments(protocolRiskLevelBean.getComments());
        //            protocolRiskLevelsType.setDateAssigned(convertDateToCalendar(protocolRiskLevelBean.getDateAssigned()));
        //            protocolRiskLevelsType.setDateUpdated(convertDateToCalendar(protocolRiskLevelBean.getDateInactivated()));
        //            protocolRiskLevelsType.setProtocolNumber(protocolRiskLevelBean.getProtocolNumber());
        //            if(protocolRiskLevelBean.getRiskLevelCode()!=null){
        //                protocolRiskLevelsType.setRiskLevelCode(Integer.parseInt(protocolRiskLevelBean.getRiskLevelCode()));
        //                protocolRiskLevelsType.setRiskLevelDesc(protocolRiskLevelBean.getRiskLevel().getDescription());
        //            }
        //            protocolRiskLevelsType.setSequenceNumber(protocolRiskLevelBean.getSequenceNumber());
        //            protocolRiskLevelsType.setStatus(protocolRiskLevelBean.getStatus());
        //            protocolRiskLevelsType.setUpdateUser(protocolRiskLevelBean.getUpdateUser());
        //        }
    }

    /**
     * Sets the specialReview.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolSpecialReviewes(Protocol protocol,ProtocolType protocolType) {
        List<ProtocolSpecialReview> vecSpecialReview = protocol.getSpecialReviews();
        for (ProtocolSpecialReview specialReviewBean : vecSpecialReview) {
            specialReviewBean.refreshNonUpdateableReferences();
            SpecialReviewType specialReview = protocolType.addNewSpecialReview();
            if (specialReviewBean.getApplicationDate() != null) {
                specialReview.setSpecialReviewApplicationDate(getDateTimeService().getCalendar(
                        specialReviewBean.getApplicationDate()));
            } else {
                specialReview.setSpecialReviewApplicationDate(getDateTimeService().getCurrentCalendar());
            }
            if (specialReviewBean.getApprovalDate() != null) {
                specialReview.setSpecialReviewApprovalDate(getDateTimeService().getCalendar(specialReviewBean.getApprovalDate()));
            } else {
                specialReview.setSpecialReviewApprovalDate(getDateTimeService().getCurrentCalendar());
            }
            if (specialReviewBean.getApprovalType() != null) {
                specialReview.setSpecialReviewApprovalTypeCode(new BigInteger(specialReviewBean.getApprovalTypeCode()));
                specialReview.setSpecialReviewApprovalTypeDesc(specialReviewBean.getApprovalType().getDescription());
            }
            specialReview.setSpecialReviewComments(specialReviewBean.getComments());
            if (specialReviewBean.getSpecialReviewNumber() != null) {
                specialReview.setSpecialReviewNumber(BigInteger.valueOf(specialReviewBean.getSpecialReviewNumber()));
            }
            specialReview.setSpecialReviewProtocolNumber(specialReviewBean.getProtocolNumber());
            if (specialReviewBean.getSpecialReviewType() != null) {
                specialReview.setSpecialReviewTypeCode(new BigInteger(specialReviewBean.getSpecialReviewTypeCode()));
                specialReview.setSpecialReviewTypeDesc(specialReviewBean.getSpecialReviewType().getDescription());
            }
        }
    }
    
    /**
     * Sets the actions.
     * 
     * @param protocol
     * @param protocolType
     * @return     
     */
    private void setProtocolActions(Protocol protocol,ProtocolType protocolType) {
        List<ProtocolActionsType> protocolActionsTypeList = new ArrayList<ProtocolActionsType>();
        for (ProtocolAction protocolAction : protocol.getProtocolActions()) {
            ProtocolActionsType protocolActionsType = ProtocolActionsType.Factory.newInstance();
            protocolActionsType.setActionId(protocolAction.getActionId());
            if (protocolAction.getProtocolActionTypeCode() != null) { 
                protocolActionsType.setActionTypeCode(Integer.parseInt(protocolAction.getProtocolActionTypeCode()));
                protocolActionsType.setActionTypeDesc(protocolAction.getProtocolActionType().getDescription());
            }
            protocolActionsType.setComments(protocolAction.getComments());
            if (protocolAction.getActionDate() != null) {
                protocolActionsType.setActionDate(KraServiceLocator.getService(DateTimeService.class).getCalendar(protocolAction.getActionDate()));
            }
            protocolActionsTypeList.add(protocolActionsType);
        }
        protocolType.setActionsArray(protocolActionsTypeList.toArray(new ProtocolActionsType[0]));
    }
    
    /**
     * Sets the fundingSource.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolFundingResources(Protocol protocol,ProtocolType protocolType) {
        int fundingSourceTypeCode;
        String fundingSourceName; 
        String fundingSourceCode;
        List<ProtocolFundingSource> vecFundingSource = protocol.getProtocolFundingSources();       
        for (ProtocolFundingSource protocolFundingSourceBean : vecFundingSource) {
            FundingSourceType fundingSource = protocolType.addNewFundingSource();
            fundingSourceCode = protocolFundingSourceBean.getFundingSourceNumber();
            fundingSourceTypeCode = Integer.valueOf(protocolFundingSourceBean.getFundingSourceTypeCode());
            fundingSourceName = getFundingSourceNameForType(fundingSourceTypeCode, fundingSourceCode);
            if (fundingSourceName != null) {
                fundingSource.setFundingSourceName(fundingSourceName);
            }            
            if (protocolFundingSourceBean.getFundingSourceType() != null) {
                fundingSource.setTypeOfFundingSource(protocolFundingSourceBean.getFundingSourceType().getDescription());
            }
        }
    }
    
    private String getFundingSourceNameForType(int sourceType, String sourceCode) {
        String name = null;
        if (sourceType == 1) {
            Sponsor sponsorBean = getBusinessObjectService().findBySinglePrimaryKey(Sponsor.class, sourceCode);
            if (sponsorBean != null) {
                name = sponsorBean.getSponsorName();
            }
        } else if (sourceType == 2) {
            Unit unitBean = getBusinessObjectService().findBySinglePrimaryKey(Unit.class, sourceCode);
            if (unitBean != null) {
                name = unitBean.getUnitName();
            }
        } else {
            name = sourceCode;
        }
        return name;
    }

    /**
     * Sets the researchArea.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolResearchAreas(Protocol protocol,ProtocolType protocolType) {
        List<ProtocolResearchArea> researchAreas = protocol.getProtocolResearchAreas();
        for (ProtocolResearchArea protocolReasearchAreasBean : researchAreas) {
            protocolReasearchAreasBean.refreshNonUpdateableReferences();
            ResearchAreaType researchArea = protocolType.addNewResearchArea();
            researchArea.setResearchAreaCode(protocolReasearchAreasBean.getResearchAreaCode());
            if (protocolReasearchAreasBean.getResearchAreas() != null) {
                researchArea.setResearchAreaDescription(protocolReasearchAreasBean.getResearchAreas().getDescription()); 
            }            
        }
    }
    
    /**
     * Sets the protocolLocations.
     * 
     * @param protocol
     * @param protocolType
     * @return     
     */
    private void setProtocolLocations(Protocol protocol,ProtocolType protocolType) {
        List<LocationType> locationTypeList = new ArrayList<LocationType>();
        for (ProtocolLocation protocolLocation  : protocol.getProtocolLocations()) {
            LocationType locationType = LocationType.Factory.newInstance();
            if (protocolLocation.getProtocolOrganizationType() != null) {
                locationType.setOrgTypeDesc(protocolLocation.getProtocolOrganizationType().getDescription());
            }
            if (protocolLocation.getOrganization() != null) {
                locationType.setOrgName(protocolLocation.getOrganization().getOrganizationName());
                locationType.setAddress(protocolLocation.getOrganization().getAddress());
                locationType.setAnimalWelfareAssurance(protocolLocation.getOrganization().getAnimalWelfareAssurance());
            }            
            locationTypeList.add(locationType);
        }  
        protocolType.setOrganizationArray(locationTypeList.toArray(new LocationType[0]));
    }

    /**
     * Sets the protocolPersons.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolPersons(IacucProtocol protocol, ProtocolType protocolType) {
        List<ProtocolPerson> vecInvestigator = protocol.getProtocolPersons();
        for (ProtocolPerson protocolPerson : vecInvestigator) {
            protocolPerson.refreshNonUpdateableReferences();
            if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)
                    || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CO_INVESTIGATOR)) {
                InvestigatorType investigator = protocolType.addNewInvestigator();
                if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)) {
                    investigator.setPIFlag(true);
                    if (protocolPerson.isTrained()) {
                        investigator.setTrainingFlag(FLAG_YES);
                    } else {
                        investigator.setTrainingFlag(FLAG_NO); 
                    } 
                    if (protocolPerson.getAffiliationType() != null) {
                        investigator.setAffiliationDesc(protocolPerson.getAffiliationType().getDescription());
                    } 
                    List<edu.mit.coeus.xml.iacuc.InvestigatorType.Unit> unitList = new ArrayList<InvestigatorType.Unit>();
                    for (ProtocolUnit protocolUnit :protocolPerson.getProtocolUnits()) {
                        edu.mit.coeus.xml.iacuc.InvestigatorType.Unit unit = edu.mit.coeus.xml.iacuc.InvestigatorType.Unit.Factory.newInstance();
                        unit.setUnitName(protocolUnit.getUnitName());
                        unit.setUnitNumber(protocolUnit.getUnitNumber());
                        unitList.add(unit);                        
                    } 
                    investigator.setUnitArray((edu.mit.coeus.xml.iacuc.InvestigatorType.Unit[]) unitList.
                            toArray(new edu.mit.coeus.xml.iacuc.InvestigatorType.Unit[0]));
                     
                }
                getPrintXmlUtilService().setPersonRolodexType(protocolPerson, investigator.addNewPerson());
            } else if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_STUDY_PERSONNEL)) {
                KeyStudyPersonType keyStudyPerson = protocolType.addNewKeyStudyPerson();
                if (protocolPerson.getAffiliationType() != null) {
                    keyStudyPerson.setAffiliation(protocolPerson.getAffiliationType().getDescription());
                }
                if (protocolPerson.getRolodex() != null) {
                    //TODO - verify as part of refactor PrimaryTitle is changed to Title
                    keyStudyPerson.setRole(protocolPerson.getRolodex().getTitle());
                } else if (protocolPerson.getPerson() != null) {
                    keyStudyPerson.setRole(protocolPerson.getPerson().getDirectoryTitle());
                }
                getPrintXmlUtilService().setPersonRolodexType(protocolPerson, keyStudyPerson.addNewPerson());
            } else if (protocolPerson.getProtocolPersonRoleId().equals(IacucProtocolPersonRole.ROLE_CORRESPONDENTS)
                  || (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR))) {
                CorrespondentType correspondent = protocolType.addNewCorrespondent();
                correspondent.setTypeOfCorrespondent(protocolPerson.getProtocolPersonRole().getDescription());
                correspondent.setCorrespondentTypeDesc(protocolPerson.getProtocolPersonRole().getDescription());
                getPrintXmlUtilService().setPersonRolodexType(protocolPerson, correspondent.addNewPerson());
            }

            // TODO : verify code - Code refactor
            //            
            //            else if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CORRESPONDENT_CRC)
            //                    || (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR))) {
            //                Correspondent correspondent = protocolType.addNewCorrespondent();
            //                // not sure where the comments should come from
            //                // correspondent.setCorrespondentComments(protocolPerson.getComments()) ;
            //                correspondent.setTypeOfCorrespondent(protocolPerson.getProtocolPersonRole().getDescription());
            //                getPrintXmlUtilService().setPersonRolodexType(protocolPerson, correspondent.addNewPerson());
            //            }
        }
    }

    /**
     * Sets the protocolMasterData.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolMasterData(IacucProtocol protocol, ProtocolType protocolType) {
        ProtocolMasterDataType protocolMaster = protocolType.addNewProtocolMasterData();
        if (protocol == null) {
            return;
        }            
        protocol.refreshNonUpdateableReferences();
        protocolMaster.setProtocolNumber(protocol.getProtocolNumber());
        protocolMaster.setSequenceNumber(BigInteger.valueOf(protocol.getSequenceNumber()));
        protocolMaster.setProtocolTitle(protocol.getTitle());

        if (protocol.getSubmissionDate() != null) {
            protocolMaster.setApplicationDate(getDateTimeService().getCalendar(protocol.getSubmissionDate()));
        }
        if (protocol.getProtocolStatus() != null) {
            protocolMaster.setProtocolStatusCode(new BigInteger(protocol.getProtocolStatusCode()));
            protocolMaster.setProtocolStatusDesc(protocol.getProtocolStatus().getDescription());
        }
        if (protocol.getProtocolType() != null) {
            protocolMaster.setProtocolTypeCode(new BigInteger(protocol.getProtocolTypeCode()));
            protocolMaster.setProtocolTypeDesc(protocol.getProtocolType().getDescription());
        }
        if (protocol.getDescription() != null) {
            protocolMaster.setProtocolDescription(protocol.getDescription());
        }
        if (protocol.getApprovalDate() != null) {
            protocolMaster.setApprovalDate(getDateTimeService().getCalendar(protocol.getApprovalDate()));
        }
        if (protocol.getLastApprovalDate() != null) {
            protocolMaster.setLastApprovalDate(getDateTimeService().getCalendar(protocol.getLastApprovalDate()));        
        } 
        if (protocol.getExpirationDate() != null) {
            protocolMaster.setExpirationDate(getDateTimeService().getCalendar(protocol.getExpirationDate()));
        }
        if (protocol.getProtocolSubmission() != null) {
            protocolMaster.setBillableFlag(protocol.getProtocolSubmission().isBillable());
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
        if (protocol.getInvestigator() != null) {
            protocolMaster.setPrincipleInvestigatorName(protocol.getInvestigator());
        }
        
        if (protocol.getOverviewTimeline() != null) {
            protocolMaster.setOverviewTimeline(protocol.getOverviewTimeline());
        }
    }
    
    /**
     * Sets the speciesGoups.
     * 
     * @param protocol
     * @param protocolType
     * @return     
     */
    private void setSpeciesGoups(IacucProtocol protocol, ProtocolType protocolType) {       
        List<SpeciesType> speciesTypeList = new ArrayList<SpeciesType>();
        for (IacucProtocolSpecies iacucProtocolSpecies : protocol.getIacucProtocolSpeciesList()) {
            SpeciesType speciesType = SpeciesType.Factory.newInstance();
            speciesType.setSpeciesGroup(iacucProtocolSpecies.getSpeciesGroup());
            speciesType.setSpeciesDesc(iacucProtocolSpecies.getSpeciesName());
            speciesType.setStrain(iacucProtocolSpecies.getStrain());
            speciesType.setPainCategoryDesc(iacucProtocolSpecies.getPainCategoryName());
            speciesType.setPainCategoryCode(iacucProtocolSpecies.getPainCategoryCode());
            if (iacucProtocolSpecies.getUsdaCovered()) {
                speciesType.setIsUsdaCovered(FLAG_YES);
            } else {
                speciesType.setIsUsdaCovered(FLAG_NO);
            }
            if (iacucProtocolSpecies.getIacucSpeciesCountType() != null) {
                speciesType.setCountTypeCode(iacucProtocolSpecies.getIacucSpeciesCountType().getSpeciesCountCode());
                speciesType.setCountTypeDesc(iacucProtocolSpecies.getIacucSpeciesCountType().getDescription());
            }            
            speciesType.setSpeciesCount(iacucProtocolSpecies.getSpeciesCount());
            setExceptions(iacucProtocolSpecies.getIacucProtocolExceptions(),speciesType);
            speciesTypeList.add(speciesType);
        }
        protocolType.setSpeciesArray(speciesTypeList.toArray(new SpeciesType[0]));
    }

    /**
     * Sets the exceptions.
     * 
     * @param iacucProtocolExceptionList
     * @param speciesType
     * @return     
     */
    private void setExceptions(List<IacucProtocolException> iacucProtocolExceptionList, SpeciesType speciesType) {       
        List<ExceptionType> exceptionTypeList = new ArrayList<ExceptionType>();
        for (IacucProtocolException iacucProtocolException : iacucProtocolExceptionList) {
            ExceptionType exceptionType = ExceptionType.Factory.newInstance();
            if (iacucProtocolException.getIacucExceptionCategory() != null) {
                exceptionType.setExceptionCategoryDesc(iacucProtocolException.getIacucExceptionCategory().getExceptionCategoryDesc());
            }           
            exceptionType.setDescription(iacucProtocolException.getExceptionDescription());
            exceptionTypeList.add(exceptionType); 
        }
        speciesType.setExceptionArray(exceptionTypeList.toArray(new ExceptionType[0]));  
    }
    
    /**
     * Sets the principles.
     * 
     * @param protocol
     * @param protocolType
     * @return     
     */
    private void setPrinciples(IacucProtocol protocol, ProtocolType protocolType) {        
        List<PrinciplesType> principleTypesList = new ArrayList<PrinciplesType>();
        for (IacucPrinciples iacucPrinciples : protocol.getIacucPrinciples()) {
            PrinciplesType principleTypes = PrinciplesType.Factory.newInstance();
            principleTypes.setReductionPrinciple(iacucPrinciples.getReduction());
            principleTypes.setRefinementPrinciple(iacucPrinciples.getRefinement());
            principleTypes.setReplacementPrinciple(iacucPrinciples.getReplacement());
            principleTypesList.add(principleTypes);
        }
        protocolType.setPrinciplesArray(principleTypesList.toArray(new PrinciplesType[0]));
    }
    
    /**
     * Sets the procedures.
     * 
     * @param protocol
     * @param protocolType
     * @return     
     */
    private void setProcedures(IacucProtocol protocol, ProtocolType protocolType) {  

        
    }    

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected final boolean hasPermission(String userId, Protocol protocol, String permissionName) {
        
        return KraServiceLocator.getService(KraAuthorizationService.class).hasPermission(userId, protocol, permissionName);
    }   
    
    /**
     * Sets the scheduleXmlStream attribute value.
     * @param scheduleXmlStream The scheduleXmlStream to set.
     */
    public void setScheduleXmlStream(ScheduleXmlStream scheduleXmlStream) {
        this.scheduleXmlStream = scheduleXmlStream;
    }

    /**
     * Gets the scheduleXmlStream attribute. 
     * @return Returns the scheduleXmlStream.
     */
    public ScheduleXmlStream getScheduleXmlStream() {
        return scheduleXmlStream;
    }

    /**
     * Sets the committeeXmlStream attribute value.
     * @param committeeXmlStream The committeeXmlStream to set.
     */
    public void setCommitteeXmlStream(CommitteeXmlStream comitteeXmlStream) {
        this.committeeXmlStream = comitteeXmlStream;
    }

    /**
     * Gets the committeeXmlStream attribute. 
     * @return Returns the committeeXmlStream.
     */
    public CommitteeXmlStream getCommitteeXmlStream() {
        return committeeXmlStream;
    }    
       
    /**
     * Sets the printXmlUtilService attribute value.
     * 
     * @param printXmlUtilService The printXmlUtilService to set.
     */
    public void setPrintXmlUtilService(PrintXmlUtilService printXmlUtilService) {
        this.printXmlUtilService = printXmlUtilService;
    }

    /**
     * Gets the printXmlUtilService attribute.
     * 
     * @return Returns the printXmlUtilService.
     */
    public PrintXmlUtilService getPrintXmlUtilService() {
        return printXmlUtilService;
    }

    public abstract ProtocolActionService getProtocolActionServiceHook();
    
}
