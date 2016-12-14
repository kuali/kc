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
package org.kuali.kra.iacuc.actions.print;

import edu.mit.coeus.xml.iacuc.*;
import edu.mit.coeus.xml.iacuc.ProtocolType.Submissions;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucExceptionCategory;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucSpeciesCountType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolActionService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.committee.print.IacucCommitteeXmlStream;
import org.kuali.kra.iacuc.committee.print.IacucScheduleXmlStream;
import org.kuali.kra.iacuc.committee.print.service.IacucPrintXmlUtilService;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomData;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRole;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRolodex;
import org.kuali.kra.iacuc.procedures.IacucProcedure;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.threers.IacucAlternateSearch;
import org.kuali.kra.iacuc.threers.IacucPrinciples;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewalBase;
import org.kuali.kra.protocol.actions.print.ProtocolSummaryPrintOptions;
import org.kuali.kra.protocol.actions.print.ProtocolSummaryXmlStreamBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolNotepadBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRoleBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolSummaryXmlStream extends ProtocolSummaryXmlStreamBase {

    
    private IacucProtocolActionService iacucProtocolActionService;
    
    private BusinessObjectService businessObjectService;
    private IacucPrintXmlUtilService printXmlUtilService;
    private IacucScheduleXmlStream scheduleXmlStream;
    private IacucCommitteeXmlStream committeeXmlStream;
    
    
    protected static final String FLAG_YES = "Yes";
    protected static final String FLAG_NO = "No";


    @Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject,
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

    public ProtocolType getProtocolSummary(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<?, Object> htData) {
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
            printRequirementType.setExceptionsRequired(getOptionString(summaryOptions.isException()));
            printRequirementType.setNotesRequired(getOptionString(summaryOptions.isNotes()));
            printRequirementType.setAmendRenewSRequired(getOptionString(summaryOptions.isAmmendmentRenewalSummary()));
            printRequirementType.setOtherDataRequired(getOptionString(summaryOptions.isOtherData()));
            printRequirementType.setUserRolesRequired(getOptionString(summaryOptions.isRoles()));
            printRequirementType.setReferencesRequired(getOptionString(summaryOptions.isReferences()));
            printRequirementType.setPrinciplesRequired(getOptionString(summaryOptions.isPrinciples()));
            printRequirementType.setProtocolDetailsRequired(getOptionString(summaryOptions.isProtocolDetails())); 
            printRequirementType.setAlternativeSearchesRequired(getOptionString(summaryOptions.isPrinciples()));
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

    /**
     * Sets the protocolRoles.
     * 
     * @param protocol
     * @param protocolType
     * @return     
     */
    private void setProtocolUserRoles(ProtocolBase protocol,ProtocolType protocolType) {
        List<RolesType> rolesTypeList = new ArrayList<RolesType>();
        List<UserRolesType> userRolesTypeList = new ArrayList<UserRolesType>();
        for (ProtocolPersonBase protocolPerson : protocol.getProtocolPersons()) {
            UserRolesType userRolesType = UserRolesType.Factory.newInstance();
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
                ProtocolPersonRolodexBase rolodex = getBusinessObjectService().findBySinglePrimaryKey(IacucProtocolPersonRolodex.class, protocolReviewer.getRolodexId());
                getPrintXmlUtilService().setPersonXml(rolodex, personType);

            } else {
                KcPerson kcPerson = KcServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(protocolReviewer.getPersonId());
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
            submissionDetail.setSubmissionTypeQualifierCode(submissionInfoBean.getSubmissionTypeQualifierCode() == null ?
                    new BigInteger("0") : new BigInteger(String.valueOf(submissionInfoBean.getSubmissionTypeQualifierCode())));
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
            CommitteeBase committee = submissionInfoBean.getCommittee();            
            getCommitteeXmlStream().setCommitteeMasterData(committee, submission.addNewCommitteeMasterData());
            getCommitteeXmlStream().setCommitteeMembers(committee, submission);
        }

        if (submissionInfoBean.getScheduleId() != null) {
            CommitteeScheduleBase committeeSchedule = submissionInfoBean.getCommitteeSchedule();
            getScheduleXmlStream().setScheduleMasterData(committeeSchedule, submission.addNewScheduleMasterData());
            ScheduleSummaryType nextSchedule = submission.addNewNextSchedule();
            getScheduleXmlStream().setNextSchedule(committeeSchedule, nextSchedule.addNewScheduleMasterData());
        }
    }
    
    private ProtocolSubmissionBase findProtocolSubmission(org.kuali.kra.protocol.ProtocolBase protocol, Integer submissionNumber) {
        List<ProtocolSubmissionBase> protocolSubmissions = protocol.getProtocolSubmissions();
        for (ProtocolSubmissionBase protocolSubmission : protocolSubmissions) {
            if (protocolSubmission.getSubmissionNumber().equals(submissionNumber)) {
                return protocolSubmission;
            }
        }
        return null;
    }
    

    protected void setMinutes(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase submissionInfoBean,
            Submissions submission) {
        CommitteeScheduleBase committeeSchedule = submissionInfoBean.getCommitteeSchedule();
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
    private void setProtocolReferences(ProtocolBase protocol,ProtocolType protocolType) {
        ReferencesType referencesType = ReferencesType.Factory.newInstance();
        List<ReferencesType> referncestypeList = new ArrayList<ReferencesType>();        
        for (ProtocolReferenceBase protocolReference : protocol.getProtocolReferences()) {
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
            if (iacucProtocolCustomData.getCustomAttribute() != null) {
                otherDataType.setColumnName(iacucProtocolCustomData.getCustomAttribute().getName());
                otherDataType.setColumnValue(iacucProtocolCustomData.getCustomAttribute().getValue());
            }
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
    private void setProtocolAmendmentRenewals(ProtocolBase protocol,ProtocolType protocolType) {
        List<AmendRenewalType> amendRenewalTypeList = new ArrayList<AmendRenewalType>();
        for (ProtocolAmendRenewalBase protocolAmendRenewal : protocol.getProtocolAmendRenewals()) {
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
    private void setProtocolNotes(ProtocolBase protocol,ProtocolType protocolType) {
        List<NotesType> notesTypelist = new ArrayList<NotesType>();
        for (ProtocolNotepadBase protocolNotepad:protocol.getNotepads()) {
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

    /**
     * Sets the specialReview.
     * 
     * @param protocol
     * @param protocolType
     * @return
     */
    private void setProtocolSpecialReviewes(ProtocolBase protocol,ProtocolType protocolType) {
        List<ProtocolSpecialReviewBase> vecSpecialReview = protocol.getSpecialReviews();
        for (ProtocolSpecialReviewBase specialReviewBean : vecSpecialReview) {
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
    private void setProtocolActions(ProtocolBase protocol,ProtocolType protocolType) {
        List<ProtocolActionsType> protocolActionsTypeList = new ArrayList<ProtocolActionsType>();
        for (ProtocolActionBase protocolAction : protocol.getProtocolActions()) {
            ProtocolActionsType protocolActionsType = ProtocolActionsType.Factory.newInstance();
            protocolActionsType.setActionId(protocolAction.getActionId());
            if (protocolAction.getProtocolActionTypeCode() != null) { 
                protocolActionsType.setActionTypeCode(Integer.parseInt(protocolAction.getProtocolActionTypeCode()));
                protocolActionsType.setActionTypeDesc(protocolAction.getProtocolActionType().getDescription());
            }
            protocolActionsType.setComments(protocolAction.getComments());
            if (protocolAction.getActionDate() != null) {
                protocolActionsType.setActionDate(KcServiceLocator.getService(DateTimeService.class).getCalendar(protocolAction.getActionDate()));
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
    private void setProtocolFundingResources(ProtocolBase protocol,ProtocolType protocolType) {
        int fundingSourceTypeCode;
        String fundingSourceName; 
        String fundingSourceCode;
        List<ProtocolFundingSourceBase> vecFundingSource = protocol.getProtocolFundingSources();       
        for (ProtocolFundingSourceBase protocolFundingSourceBean : vecFundingSource) {
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
            fundingSource.setFundingSource(protocolFundingSourceBean.getFundingSourceNumber());
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
    private void setProtocolResearchAreas(ProtocolBase protocol,ProtocolType protocolType) {
        List<ProtocolResearchAreaBase> researchAreas = protocol.getProtocolResearchAreas();
        for (ProtocolResearchAreaBase protocolReasearchAreasBean : researchAreas) {
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
    private void setProtocolLocations(ProtocolBase protocol,ProtocolType protocolType) {
        List<LocationType> locationTypeList = new ArrayList<LocationType>();
        for (ProtocolLocationBase protocolLocation  : protocol.getProtocolLocations()) {
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
        List<ProtocolPersonBase> vecInvestigator = protocol.getProtocolPersons();
        for (ProtocolPersonBase protocolPerson : vecInvestigator) {
            protocolPerson.refreshNonUpdateableReferences();
            if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)
                    || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_CO_INVESTIGATOR)) {
                InvestigatorType investigator = protocolType.addNewInvestigator();
                if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)) {
                    investigator.setPIFlag(true);
                }
                if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)||
                        protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_CO_INVESTIGATOR) ) {                    
                    if (protocolPerson.isTrained()) {
                        investigator.setTrainingFlag(FLAG_YES);
                    } else {
                        investigator.setTrainingFlag(FLAG_NO); 
                    } 
                    if (protocolPerson.getAffiliationType() != null) {
                        investigator.setAffiliationDesc(protocolPerson.getAffiliationType().getDescription());
                    } 
                    List<edu.mit.coeus.xml.iacuc.InvestigatorType.Unit> unitList = new ArrayList<InvestigatorType.Unit>();
                    for (ProtocolUnitBase protocolUnit :protocolPerson.getProtocolUnits()) {
                        edu.mit.coeus.xml.iacuc.InvestigatorType.Unit unit = edu.mit.coeus.xml.iacuc.InvestigatorType.Unit.Factory.newInstance();
                        unit.setUnitName(protocolUnit.getUnitName());
                        unit.setUnitNumber(protocolUnit.getUnitNumber());
                        unitList.add(unit);                        
                    } 
                    investigator.setUnitArray((edu.mit.coeus.xml.iacuc.InvestigatorType.Unit[]) unitList.
                            toArray(new edu.mit.coeus.xml.iacuc.InvestigatorType.Unit[0]));
                     
                }
                getPrintXmlUtilService().setPersonRolodexType(protocolPerson, investigator.addNewPerson());
            } else if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_STUDY_PERSONNEL)) {
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
                if (protocolPerson.isTrained()) {
                    keyStudyPerson.setTrainingFlag(FLAG_YES);
                } else {
                    keyStudyPerson.setTrainingFlag(FLAG_NO); 
                } 
                getPrintXmlUtilService().setPersonRolodexType(protocolPerson, keyStudyPerson.addNewPerson());
            } else if (protocolPerson.getProtocolPersonRoleId().equals(IacucProtocolPersonRole.ROLE_CORRESPONDENTS)
                  || (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_CORRESPONDENT_ADMINISTRATOR))) {
                CorrespondentType correspondent = protocolType.addNewCorrespondent();
                correspondent.setTypeOfCorrespondent(protocolPerson.getProtocolPersonRole().getDescription());
                correspondent.setCorrespondentTypeDesc(protocolPerson.getProtocolPersonRole().getDescription());
                correspondent.setComments(protocolPerson.getComments());
                getPrintXmlUtilService().setPersonRolodexType(protocolPerson, correspondent.addNewPerson());
            }
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
        protocolMaster.setLayStatement1(protocol.getLayStatement1());
        protocolMaster.setLayStatement2(protocol.getLayStatement2());

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
       
        if (protocol.getProtocolProjectType() != null) {
            protocolMaster.setProjectTypeCode(BigInteger.valueOf(Integer.parseInt(protocol.getProtocolProjectType().getProjectTypeCode())));
            protocolMaster.setProjectTypeDesc(protocol.getProtocolProjectType().getDescription());
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
            speciesType.setCountTypeCode(iacucProtocolSpecies.getSpeciesCountCode());
            
            if(iacucProtocolSpecies.getSpeciesCountCode() != null) {
                Map params = new HashMap();
                params.put("speciesCountCode", iacucProtocolSpecies.getSpeciesCountCode());
                IacucSpeciesCountType iacucSpeciesCountType = this.getBusinessObjectService().findByPrimaryKey(IacucSpeciesCountType.class, params);
                speciesType.setCountTypeDesc(iacucSpeciesCountType.getDescription());
            }            
            speciesType.setSpeciesCount(iacucProtocolSpecies.getSpeciesCount());
            setExceptions(protocol,speciesType);
            speciesTypeList.add(speciesType);
        }
        protocolType.setSpeciesArray(speciesTypeList.toArray(new SpeciesType[0]));
    }

    private void setExceptions(IacucProtocol protocol, SpeciesType speciesType) {       
        List<ExceptionType> exceptionTypeList = new ArrayList<ExceptionType>();
        for (IacucProtocolException iacucProtocolException : protocol.getIacucProtocolExceptions()) {
            ExceptionType exceptionType = ExceptionType.Factory.newInstance();
            String exceptionCategoryDescription = null;
            
            if (iacucProtocolException.getExceptionCategoryCode() != null) {
                Map params = new HashMap();
                params.put("exceptionCategoryCode",iacucProtocolException.getExceptionCategoryCode());
                exceptionCategoryDescription =getBusinessObjectService().findByPrimaryKey(IacucExceptionCategory.class, params).getExceptionCategoryDesc();
            }
            exceptionType.setExceptionCategoryDesc(exceptionCategoryDescription);
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
        
        if (protocol.getIacucPrinciples() != null && protocol.getIacucPrinciples().size() >0 && 
                protocol.getIacucPrinciples().get(0).getSearchRequired()!= null && protocol.getIacucPrinciples().get(0).getSearchRequired().
                equalsIgnoreCase(Constants.TRUE_FLAG)) { 
            List<AlternateDbSearchType> alternateDbSearchTypeList = new ArrayList<AlternateDbSearchType>();
            for (IacucAlternateSearch iacucAlternateSearch :protocol.getIacucAlternateSearches()) {
                AlternateDbSearchType alternateDbSearchType = AlternateDbSearchType.Factory.newInstance();
                alternateDbSearchType.setSearchDate(DateUtils.toCalendar(iacucAlternateSearch.getSearchDate()));
                alternateDbSearchType.setYearsSearched(iacucAlternateSearch.getYearsSearched());
                if (iacucAlternateSearch.getDatabases()!= null && iacucAlternateSearch.getDatabases().size() >0) {
                    alternateDbSearchType.setDatabasDesc(iacucAlternateSearch.getDatabases().get(0).getAlternateSearchDatabaseName());
                }                
                alternateDbSearchType.setKeywordsSearched(iacucAlternateSearch.getKeywords());
                alternateDbSearchType.setComments(iacucAlternateSearch.getComments());
                
                alternateDbSearchTypeList.add(alternateDbSearchType);
            }
            protocolType.setAlternateDbSearchArray(alternateDbSearchTypeList.toArray(new AlternateDbSearchType[0]));
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

        List<StudyGroupType> studyGroupTypeList = new ArrayList<StudyGroupType>();
        IacucProtocolStudyGroup iacucProtocolStudyGroups = null;
        
        for (IacucProtocolStudyGroupBean iacucProtocolStudyGroup : protocol.getIacucProtocolStudyGroups()) {
            StudyGroupType studyGroupType = StudyGroupType.Factory.newInstance();
            studyGroupType.setProcedureCategoryCode(iacucProtocolStudyGroup.getProcedureCategoryCode());            
            
            Map params = new HashMap();
            params.put("procedureCode", iacucProtocolStudyGroup.getProcedureCode());            
            IacucProcedure iacucProcedure = this.getBusinessObjectService().findByPrimaryKey(IacucProcedure.class, params);
            studyGroupType.setProcedureCode(iacucProtocolStudyGroup.getProcedureCode());
            if (iacucProcedure != null) {
                studyGroupType.setProcedureDesc(iacucProcedure.getProcedureDescription());
            }            
            
            if (iacucProtocolStudyGroup.getIacucProtocolStudyGroups().size() >0){
                iacucProtocolStudyGroups =iacucProtocolStudyGroup.getIacucProtocolStudyGroups().get(0); 
            }
            if (iacucProtocolStudyGroup.getIacucProcedureCategory() != null) {
                studyGroupType.setProcedureCategoryCode(iacucProtocolStudyGroup.getIacucProcedureCategory().getProcedureCategoryCode());
                studyGroupType.setProcedureCategoryDesc(iacucProtocolStudyGroup.getIacucProcedureCategory().getProcedureCategory());
            }
            if (iacucProtocolStudyGroups.getIacucProtocolSpecies() != null) {
                studyGroupType.setSpeciesGroup(iacucProtocolStudyGroups.getIacucProtocolSpecies().getSpeciesGroup());
            }
            
            if (iacucProtocolStudyGroups.getIacucPainCategory() != null) {
                studyGroupType.setPainCategoryDesc(iacucProtocolStudyGroups.getIacucPainCategory().getPainCategory());
            }             

            studyGroupTypeList.add(studyGroupType);
        }
        protocolType.setStudyGroupArray(studyGroupTypeList.toArray(new StudyGroupType[0]));
    }    

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Sets the scheduleXmlStream attribute value.
     * @param scheduleXmlStream The scheduleXmlStream to set.
     */
    public void setScheduleXmlStream(IacucScheduleXmlStream scheduleXmlStream) {
        this.scheduleXmlStream = scheduleXmlStream;
    }

    /**
     * Gets the scheduleXmlStream attribute. 
     * @return Returns the scheduleXmlStream.
     */
    public IacucScheduleXmlStream getScheduleXmlStream() {
        return scheduleXmlStream;
    }

    /**
     * Sets the committeeXmlStream attribute value.
     * @param comitteeXmlStream The committeeXmlStream to set.
     */
    public void setCommitteeXmlStream(IacucCommitteeXmlStream comitteeXmlStream) {
        this.committeeXmlStream = comitteeXmlStream;
    }

    /**
     * Gets the committeeXmlStream attribute. 
     * @return Returns the committeeXmlStream.
     */
    public IacucCommitteeXmlStream getCommitteeXmlStream() {
        return committeeXmlStream;
    }    
       
    /**
     * Sets the printXmlUtilService attribute value.
     * 
     * @param printXmlUtilService The printXmlUtilService to set.
     */
    public void setPrintXmlUtilService(IacucPrintXmlUtilService printXmlUtilService) {
        this.printXmlUtilService = printXmlUtilService;
    }

    /**
     * Gets the printXmlUtilService attribute.
     * 
     * @return Returns the printXmlUtilService.
     */
    public IacucPrintXmlUtilService getPrintXmlUtilService() {
        return printXmlUtilService;
    }
    
    
    
    public IacucProtocolActionService getIacucProtocolActionService() {
        return iacucProtocolActionService;
    }

    public void setIacucProtocolActionService(IacucProtocolActionService iacucProtocolActionService) {
        this.iacucProtocolActionService = iacucProtocolActionService;
    }

}
