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
package org.kuali.kra.irb.actions.print;

import edu.mit.irb.irbnamespace.CorrespondentDocument.Correspondent;
import edu.mit.irb.irbnamespace.InvestigatorDocument.Investigator;
import edu.mit.irb.irbnamespace.KeyStudyPersonDocument.KeyStudyPerson;
import edu.mit.irb.irbnamespace.PersonDocument.Person;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.FundingSource;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.RiskLevels;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.Submissions;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.Submissions.NextSchedule;
import edu.mit.irb.irbnamespace.ProtocolMasterDataDocument.ProtocolMasterData;
import edu.mit.irb.irbnamespace.SpecialReviewDocument.SpecialReview;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails;
import edu.mit.irb.irbnamespace.VulnerableSubjectDocument.VulnerableSubject;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.print.CommitteeXmlStream;
import org.kuali.kra.committee.print.IrbPrintXmlUtilService;
import org.kuali.kra.committee.print.ScheduleXmlStream;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRole;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.print.ProtocolXmlStreamBase;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to populate xmlbeans object for Protocol schema elements
 */
public class ProtocolXmlStream extends ProtocolXmlStreamBase {

    private IrbPrintXmlUtilService irbPrintXmlUtilService;
    private KcPersonService kcPersonService;
    private ScheduleXmlStream scheduleXmlStream;
    private CommitteeXmlStream committeeXmlStream;
    protected static final String AMEND_COMMENT = "Amendment-";
    protected static final String RENEW_COMMENT = "Renewal-";

	@Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        org.kuali.kra.irb.Protocol protocol = (org.kuali.kra.irb.Protocol)printableBusinessObject;
        edu.mit.irb.irbnamespace.ProtocolDocument protocolDocumentType = edu.mit.irb.irbnamespace.ProtocolDocument.Factory.newInstance();
        protocolDocumentType.setProtocol(getProtocol(protocol));
        Map<String,XmlObject> xmlObjectMap = new HashMap<String, XmlObject>();
        xmlObjectMap.put("Protocol", protocolDocumentType);
        return xmlObjectMap;
    }

    public Protocol getProtocol(org.kuali.kra.irb.Protocol protocolInfoBean, Integer submissionNumber) {
        Protocol protocolType = Protocol.Factory.newInstance();
        setProtocolMasterData(protocolInfoBean,protocolType);
        addProtocolPersons(protocolInfoBean, protocolType) ;
        addResearchArea(protocolInfoBean, protocolType) ;
        addFundingSource(protocolInfoBean, protocolType) ;
        addVulnerableSubject(protocolInfoBean, protocolType) ;
        addSpecialReview(protocolInfoBean, protocolType) ;
        addSubmissionDetails(protocolInfoBean,protocolType,submissionNumber, "Yes") ;
        Integer parentSubmissionNumber = getParentSubmissionNumber(protocolInfoBean, submissionNumber ) ;
        addSubmissionDetails(protocolInfoBean, protocolType, parentSubmissionNumber, "No") ;
        addRiskLevels(protocolInfoBean, protocolType);
        return protocolType ;
    }
    private Integer getParentSubmissionNumber(org.kuali.kra.irb.Protocol protocolInfoBean, Integer submissionNumber) {
        return 0;
    }

    public Protocol getProtocol(org.kuali.kra.irb.Protocol protocol) {
        Protocol protocolType = Protocol.Factory.newInstance();

        setProtocolMasterData(protocol, protocolType);

        addProtocolPersons(protocol, protocolType);
        addResearchArea(protocol, protocolType);
        addFundingSource(protocol, protocolType);
        addVulnerableSubject(protocol, protocolType);
        addSpecialReview(protocol, protocolType);
        addSubmissionDetails(protocol, protocolType);
        addRiskLevels(protocol, protocolType);
        return protocolType;
    }

    private void addRiskLevels(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        List<ProtocolRiskLevel> cvRiskLevels = protocol.getProtocolRiskLevels();
        for (ProtocolRiskLevel protocolRiskLevelBean : cvRiskLevels) {
            protocolRiskLevelBean.refreshNonUpdateableReferences();
            RiskLevels riskLevelsType = protocolType.addNewRiskLevels();
            if (protocolRiskLevelBean.getRiskLevelCode() != null) {
                riskLevelsType.setRiskLevelCode(new BigInteger(protocolRiskLevelBean.getRiskLevelCode()));
                riskLevelsType.setRiskLevelDescription(protocolRiskLevelBean.getRiskLevel().getDescription());
            }
            if (protocolRiskLevelBean.getComments() != null) {
                riskLevelsType.setComments(protocolRiskLevelBean.getComments());
            }
            if (protocolRiskLevelBean.getDateAssigned() != null) {
                riskLevelsType.setDateAssigned(getDateTimeService().getCalendar(protocolRiskLevelBean.getDateAssigned()));
            }
            if (protocolRiskLevelBean.getUpdateTimestamp() != null) {
                riskLevelsType.setDateUpdated(getDateTimeService().getCalendar(protocolRiskLevelBean.getUpdateTimestamp()));
            }
            if (protocolRiskLevelBean.getStatus() != null) {
                if (protocolRiskLevelBean.getStatus().equalsIgnoreCase("A")) {
                    riskLevelsType.setStatus("Active");
                }
                else if (protocolRiskLevelBean.getStatus().equalsIgnoreCase("I")) {
                    riskLevelsType.setStatus("Inactive");
                }
            }
            if (protocolRiskLevelBean.getUpdateUser() != null) {
                riskLevelsType.setUpdateUser(protocolRiskLevelBean.getUpdateUser());
            }
            if (protocolRiskLevelBean.getUpdateTimestamp() != null) {
                riskLevelsType.setUpdateTimestamp(getDateTimeService().getCalendar(protocolRiskLevelBean.getUpdateTimestamp()));
            }
        }
    }

    private void addSubmissionDetails(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        addSubmissionDetails(protocol, protocolType, null,"No");
        
    }
    private void addSubmissionDetails(org.kuali.kra.irb.Protocol protocol, Protocol protocolType, Integer submissionNumber, String currentFlag) {
        org.kuali.kra.irb.actions.submit.ProtocolSubmission submissionInfoBean = null;
        submissionInfoBean = submissionNumber==null?protocol.getProtocolSubmission():findProtocolSubmission(protocol,submissionNumber);
        if(submissionInfoBean==null || submissionInfoBean.getSubmissionNumber()==null) return;
        submissionInfoBean.refreshNonUpdateableReferences();
        Submissions submission = protocolType.addNewSubmissions();
        SubmissionDetails submissionDetail = submission.addNewSubmissionDetails();
        submissionDetail.setAbstainerCount(BigInteger.valueOf(submissionInfoBean.getAbstainerCount()));
        if(submissionInfoBean.getNoVoteCount()!=null){
            submissionDetail.setNoVote(BigInteger.valueOf(submissionInfoBean.getNoVoteCount()));
        }
        submissionDetail.setProtocolNumber(submissionInfoBean.getProtocolNumber());
        if(submissionInfoBean.getProtocolReviewType()!=null){
            submissionDetail.setProtocolReviewTypeCode(new BigInteger(submissionInfoBean.getProtocolReviewTypeCode()));
            submissionDetail.setProtocolReviewTypeDesc(submissionInfoBean.getProtocolReviewType().getDescription());
        }
        List<ProtocolReviewer> vecReviewers = (List) submissionInfoBean.getProtocolReviewers();
        for (ProtocolReviewer protocolReviewer : vecReviewers) {
            protocolReviewer.refreshNonUpdateableReferences();
            edu.mit.irb.irbnamespace.ProtocolReviewerDocument.ProtocolReviewer protocolReviewerType = submissionDetail
                    .addNewProtocolReviewer();
            if (protocolReviewer.getProtocolReviewerType() != null) {
                protocolReviewerType.setReviewerTypeDesc(protocolReviewer.getProtocolReviewerType().getDescription());
                protocolReviewerType.setReviewerTypeCode(new BigInteger(String.valueOf(protocolReviewer.getReviewerTypeCode())));
            }
            Person personType = protocolReviewerType.addNewPerson();
            boolean isNonEmployee = protocolReviewer.getNonEmployeeFlag();
            if (isNonEmployee) {
                ProtocolPersonRolodex rolodex = getBusinessObjectService().findBySinglePrimaryKey(ProtocolPersonRolodex.class,
                        protocolReviewer.getRolodexId());
                getIrbPrintXmlUtilService().setPersonXml(rolodex, personType);

            }
            else {
                KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(protocolReviewer.getPersonId());
                getIrbPrintXmlUtilService().setPersonXml(kcPerson, personType);
            }
        }
        submissionDetail.setSubmissionComments(submissionInfoBean.getComments());
        if (submissionInfoBean.getSubmissionDate() != null) {
            submissionDetail.setSubmissionDate(getDateTimeService().getCalendar(submissionInfoBean.getSubmissionDate()));
        }
        else {
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
            String code = submissionInfoBean.getSubmissionTypeQualifierCode();
            submissionDetail.setSubmissionTypeQualifierCode(code == null ? new BigInteger("0") : new BigInteger(code));
            submissionDetail.setSubmissionTypeQualifierDesc(submissionInfoBean.getProtocolSubmissionQualifierType()
                    .getDescription());
        }
        submissionDetail.setVotingComments(submissionInfoBean.getVotingComments());
        if(submissionInfoBean.getYesVoteCount()!=null){
            submissionDetail.setYesVote(BigInteger.valueOf(submissionInfoBean.getYesVoteCount()));
        }
        getIrbPrintXmlUtilService().setProtocolSubmissionAction(submissionInfoBean, submissionDetail);
        getIrbPrintXmlUtilService().setSubmissionCheckListinfo(submissionInfoBean, submissionDetail);
        submission.setCurrentSubmissionFlag(currentFlag);
        setMinutes(submissionInfoBean, submission);
        if (submissionInfoBean.getCommitteeId() != null) {
            Committee committee = (Committee) submissionInfoBean.getCommittee();
            getCommitteeXmlStream().setCommitteeMasterData(committee, submission.addNewCommitteeMasterData());
            getCommitteeXmlStream().setCommitteeMembers(committee, submission);
        }

        if (submissionInfoBean.getScheduleId() != null) {
            CommitteeSchedule committeeSchedule = (CommitteeSchedule) submissionInfoBean.getCommitteeSchedule();
            getScheduleXmlStream().setScheduleMasterData(committeeSchedule, submission.addNewScheduleMasterData());
            NextSchedule nextSchedule = submission.addNewNextSchedule();
            getScheduleXmlStream().setNextSchedule(committeeSchedule, nextSchedule.addNewScheduleMasterData());
        }
        
        setAmendmentOrRenewal(protocol,submissionDetail,submissionNumber);
        
    }

    protected void setAmendmentOrRenewal(org.kuali.kra.irb.Protocol protocol,SubmissionDetails submissionDetail,Integer submissionNumber) {
    	for (ProtocolActionBase protocolAction : protocol.getProtocolActions()) {
    		if (protocolAction.getSubmissionNumber()!=null && protocolAction.getSubmissionNumber().equals(submissionNumber)) {
    			if (protocolAction.getComments()!=null && protocolAction.getComments().startsWith(AMEND_COMMENT)) {
    				submissionDetail.setIsAmendment(true);
    			} else if(protocolAction.getComments()!=null && protocolAction.getComments().startsWith(RENEW_COMMENT)) {
    				submissionDetail.setIsRenewal(true);
    			}
    		}
    	}
    }
    
    protected void setMinutes(org.kuali.kra.irb.actions.submit.ProtocolSubmission submissionInfoBean,
            Submissions submission) {
        CommitteeSchedule committeeSchedule = (CommitteeSchedule) submissionInfoBean.getCommitteeSchedule();
        if (committeeSchedule != null) {
            getIrbPrintXmlUtilService().setProtocolReviewMinutes(committeeSchedule, submissionInfoBean, submission);
        }
    }

    private ProtocolSubmission findProtocolSubmission(org.kuali.kra.irb.Protocol protocol, Integer submissionNumber) {
        List<ProtocolSubmission> protocolSubmissions = (List) protocol.getProtocolSubmissions();
        for (ProtocolSubmission protocolSubmission : protocolSubmissions) {
            if(protocolSubmission.getSubmissionNumber().equals(submissionNumber)){
                return protocolSubmission;
            }
        }
        return null;
    }

    private void addSpecialReview(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        List<ProtocolSpecialReview> vecSpecialReview = (List) protocol.getSpecialReviews();
        for (ProtocolSpecialReview specialReviewBean : vecSpecialReview) {
            specialReviewBean.refreshNonUpdateableReferences();
            SpecialReview specialReview = protocolType.addNewSpecialReview();
            if (specialReviewBean.getApplicationDate() != null) {
                specialReview.setSpecialReviewApplicationDate(getDateTimeService().getCalendar(
                        specialReviewBean.getApplicationDate()));
            }
            else {
                specialReview.setSpecialReviewApplicationDate(getDateTimeService().getCurrentCalendar());
            }
            if (specialReviewBean.getApprovalDate() != null) {
                specialReview.setSpecialReviewApprovalDate(getDateTimeService().getCalendar(specialReviewBean.getApprovalDate()));
            }
            else {
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

    private void addVulnerableSubject(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        List<ProtocolParticipant> protocolParticipants = protocol.getProtocolParticipants();
        for (ProtocolParticipant protocolParticipant : protocolParticipants) {
            protocolParticipant.refreshNonUpdateableReferences();
            VulnerableSubject vulnerableSubject = protocolType.addNewVulnerableSubject();
            if (protocolParticipant.getParticipantType() != null) {
                vulnerableSubject.setVulnerableSubjectTypeCode(new BigInteger(protocolParticipant.getParticipantTypeCode()));
                vulnerableSubject.setVulnerableSubjectTypeDesc(protocolParticipant.getParticipantType().getDescription());
            }
        }

    }

    private void addFundingSource(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        int fundingSourceTypeCode;
        String fundingSourceName, fundingSourceCode;
        List<ProtocolFundingSource> vecFundingSource = (List) protocol.getProtocolFundingSources();
        for (ProtocolFundingSource protocolFundingSourceBean : vecFundingSource) {
            FundingSource fundingSource = protocolType.addNewFundingSource();
            fundingSourceCode = protocolFundingSourceBean.getFundingSourceNumber();
            fundingSourceTypeCode = Integer.valueOf(protocolFundingSourceBean.getFundingSourceTypeCode());
            fundingSourceName = getFundingSourceNameForType(fundingSourceTypeCode, fundingSourceCode);
            fundingSource.setFundingSourceName(fundingSourceName);
            if (protocolFundingSourceBean.getFundingSourceType() == null){
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
        }else if (sourceType == 2) {
            Unit unitBean = getBusinessObjectService().findBySinglePrimaryKey(Unit.class, sourceCode);
            if (unitBean != null) {
                name = unitBean.getUnitName();
            }
        }else {
            name = sourceCode;
        }
        return name;
    }

    private void addResearchArea(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        List<ProtocolResearchArea> researchAreas = (List) protocol.getProtocolResearchAreas();
        for (ProtocolResearchArea protocolReasearchAreasBean : researchAreas) {
            protocolReasearchAreasBean.refreshNonUpdateableReferences();
            edu.mit.irb.irbnamespace.ResearchAreaDocument.ResearchArea researchArea = protocolType.addNewResearchArea();
            researchArea.setResearchAreaCode(protocolReasearchAreasBean.getResearchAreaCode()) ;
            researchArea.setResearchAreaDescription(protocolReasearchAreasBean.getResearchAreas().getDescription());
        }    
    }

    private void addProtocolPersons(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        List<ProtocolPerson> vecInvestigator = (List) protocol.getProtocolPersons();
        for (ProtocolPerson protocolPerson : vecInvestigator) {
            protocolPerson.refreshNonUpdateableReferences();
            if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)
                    || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CO_INVESTIGATOR)) {
                Investigator investigator = protocolType.addNewInvestigator();
                if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)) {
                    investigator.setPIFlag(true);
                }
                getIrbPrintXmlUtilService().setPersonRolodexType(protocolPerson, investigator.addNewPerson());
            }
            else if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_STUDY_PERSONNEL)) {
                KeyStudyPerson keyStudyPerson = protocolType.addNewKeyStudyPerson();
                if (protocolPerson.getAffiliationType() != null) {
                    keyStudyPerson.setAffiliation(protocolPerson.getAffiliationType().getDescription());
                }
                if(protocolPerson.getRolodex()!=null){
                    keyStudyPerson.setRole(((ProtocolPersonRolodex) protocolPerson.getRolodex()).getPrimaryTitle());
                }else if(protocolPerson.getPerson()!=null){
                    keyStudyPerson.setRole(protocolPerson.getPerson().getDirectoryTitle());
                }
                getIrbPrintXmlUtilService().setPersonRolodexType(protocolPerson, keyStudyPerson.addNewPerson());
            }
            else if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CORRESPONDENT_CRC)
                    || (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR))) {
                Correspondent correspondent = protocolType.addNewCorrespondent();
                correspondent.setTypeOfCorrespondent(protocolPerson.getProtocolPersonRole().getDescription());
                getIrbPrintXmlUtilService().setPersonRolodexType(protocolPerson, correspondent.addNewPerson());
            }
        }

    }

    private void setProtocolMasterData(org.kuali.kra.irb.Protocol protocol, Protocol protocolType) {
        ProtocolMasterData protocolMaster = protocolType.addNewProtocolMasterData();
        if (protocol == null)
            return;
        protocolMaster.setProtocolNumber(protocol.getProtocolNumber());
        protocolMaster.setSequenceNumber(BigInteger.valueOf(protocol.getSequenceNumber()));
        protocolMaster.setProtocolTitle(protocol.getTitle());
        protocolMaster.setDocumentNumber(protocol.getProtocolDocument().getDocumentNumber());

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
        if(protocol.getLastApprovalDate() != null){
        	protocolMaster.setLastApprovalDate(getDateTimeService().getCalendar(protocol.getLastApprovalDate()));
        
        }if (protocol.getExpirationDate() != null) {
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

    }

    /**
     * Sets the irbPrintXmlUtilService attribute value.
     * 
     * @param irbPrintXmlUtilService The irbPrintXmlUtilService to set.
     */
    public void setIrbPrintXmlUtilService(IrbPrintXmlUtilService irbPrintXmlUtilService) {
        this.irbPrintXmlUtilService = irbPrintXmlUtilService;
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

}
