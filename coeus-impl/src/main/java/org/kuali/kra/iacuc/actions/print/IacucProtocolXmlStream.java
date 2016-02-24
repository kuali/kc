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
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.committee.print.IacucCommitteeXmlStream;
import org.kuali.kra.iacuc.committee.print.IacucScheduleXmlStream;
import org.kuali.kra.iacuc.committee.print.service.IacucPrintXmlUtilService;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRole;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRolodex;
import org.kuali.kra.protocol.actions.print.ProtocolXmlStreamBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRoleBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewBase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolXmlStream extends ProtocolXmlStreamBase {
    
    private IacucPrintXmlUtilService printXmlUtilService;
    private KcPersonService kcPersonService;
    private IacucScheduleXmlStream scheduleXmlStream;
    private IacucCommitteeXmlStream committeeXmlStream;
    
    protected static final String FLAG_YES = "Yes";
    protected static final String FLAG_NO = "No";

    @Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        IacucProtocol protocol = (IacucProtocol) printableBusinessObject;        
        ProtocolDocument protocolDocumentType = ProtocolDocument.Factory.newInstance();   
        protocolDocumentType.setProtocol(getProtocol(protocol));
        Map<String,XmlObject> xmlObjectMap = new HashMap<String, XmlObject>();
        xmlObjectMap.put("Protocol", protocolDocumentType);
        return xmlObjectMap;
    }

    public ProtocolType getProtocol(IacucProtocol protocolInfoBean, Integer submissionNumber) {
        ProtocolType protocolType = ProtocolType.Factory.newInstance();
        setProtocolMasterData(protocolInfoBean, protocolType);
        addProtocolPersons(protocolInfoBean, protocolType);
        addResearchArea(protocolInfoBean, protocolType);
        addFundingSource(protocolInfoBean, protocolType);
        addSpecialReview(protocolInfoBean, protocolType);
        addSubmissionDetails(protocolInfoBean,protocolType,submissionNumber, "Yes");
        Integer parentSubmissionNumber = getParentSubmissionNumber(protocolInfoBean, submissionNumber);
        addSubmissionDetails(protocolInfoBean, protocolType, parentSubmissionNumber, "No");
        addRiskLevels(protocolInfoBean, protocolType);
        return protocolType;
    }
    private Integer getParentSubmissionNumber(org.kuali.kra.protocol.ProtocolBase protocolInfoBean, Integer submissionNumber) {
        return 0;
    }

    public ProtocolType getProtocol(IacucProtocol protocol) {
        ProtocolType protocolType = ProtocolType.Factory.newInstance();

        setProtocolMasterData(protocol, protocolType);

        addProtocolPersons(protocol, protocolType);
        addResearchArea(protocol, protocolType);
        addFundingSource(protocol, protocolType);
        addSpecialReview(protocol, protocolType);
        addSubmissionDetails(protocol, protocolType);
        addRiskLevels(protocol, protocolType);
        return protocolType;
    }

    private void addRiskLevels(IacucProtocol protocol, ProtocolType protocolType) {

    }

    private void addSubmissionDetails(IacucProtocol protocol, ProtocolType protocolType) {
        addSubmissionDetails(protocol, protocolType, null,"No");
        
    }
    private void addSubmissionDetails(IacucProtocol protocol, ProtocolType protocolType, Integer submissionNumber, String currentFlag) {
        IacucProtocolSubmission submissionInfoBean = null;
        submissionInfoBean = (IacucProtocolSubmission) (submissionNumber == null ? protocol.getProtocolSubmission() : findProtocolSubmission(protocol,submissionNumber));
        if (submissionInfoBean == null || submissionInfoBean.getSubmissionNumber() == null) return;
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
                KcServiceLocator.getService(IacucPrintXmlUtilService.class).setPersonXml(rolodex, personType);

            } else {
                KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(protocolReviewer.getPersonId()); 
                KcServiceLocator.getService(IacucPrintXmlUtilService.class).setPersonXml(kcPerson, personType);
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
            // hack, this shouldn't be necessary, but BigIntegers can't be constructed from nulls.
            BigInteger typeQual = submissionInfoBean.getSubmissionTypeQualifierCode() == null ? new BigInteger("0") :
                    new BigInteger(String.valueOf(submissionInfoBean.getSubmissionTypeQualifierCode()));
            submissionDetail.setSubmissionTypeQualifierCode(typeQual);
            submissionDetail.setSubmissionTypeQualifierDesc(submissionInfoBean.getProtocolSubmissionQualifierType()
                    .getDescription());
        }
        submissionDetail.setVotingComments(submissionInfoBean.getVotingComments());
        if (submissionInfoBean.getYesVoteCount() != null) {
            submissionDetail.setYesVote(BigInteger.valueOf(submissionInfoBean.getYesVoteCount()));
        }
        KcServiceLocator.getService(IacucPrintXmlUtilService.class).setProtocolSubmissionAction(submissionInfoBean, submissionDetail);
        KcServiceLocator.getService(IacucPrintXmlUtilService.class).setSubmissionCheckListinfo(submissionInfoBean, submissionDetail);
        submission.setCurrentSubmissionFlag(currentFlag);
        setMinutes(submissionInfoBean, submission);
    }


    protected void setMinutes(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase submissionInfoBean,
            Submissions submission) {
        CommitteeScheduleBase committeeSchedule = submissionInfoBean.getCommitteeSchedule();
        if (committeeSchedule != null) {
            getPrintXmlUtilService().setProtocolReviewMinutes(committeeSchedule, submissionInfoBean, submission);
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

    private void addSpecialReview(IacucProtocol protocol, ProtocolType protocolType) {
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

    private void addFundingSource(IacucProtocol protocol, ProtocolType protocolType) {
        int fundingSourceTypeCode;
        String fundingSourceName, fundingSourceCode;
        List<ProtocolFundingSourceBase> vecFundingSource = protocol.getProtocolFundingSources();       
        for (ProtocolFundingSourceBase protocolFundingSourceBean : vecFundingSource) {
            FundingSourceType fundingSource = protocolType.addNewFundingSource();
            fundingSourceCode = protocolFundingSourceBean.getFundingSourceNumber();
            fundingSourceTypeCode = Integer.valueOf(protocolFundingSourceBean.getFundingSourceTypeCode());
            fundingSourceName = getFundingSourceNameForType(fundingSourceTypeCode, fundingSourceCode);
            fundingSource.setFundingSourceName(fundingSourceName);
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

    private void addResearchArea(IacucProtocol protocol, ProtocolType protocolType) {
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

    private void addProtocolPersons(IacucProtocol protocol, ProtocolType protocolType) {
        List<ProtocolPersonBase> vecInvestigator = protocol.getProtocolPersons();
        for (ProtocolPersonBase protocolPerson : vecInvestigator) {
            protocolPerson.refreshNonUpdateableReferences();
            if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)
                    || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_CO_INVESTIGATOR)) {
                InvestigatorType investigator = protocolType.addNewInvestigator();
                if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)) {
                    investigator.setPIFlag(true);
                    if (protocolPerson.isTrained()) {
                        investigator.setTrainingFlag(FLAG_YES);
                    } else {
                        investigator.setTrainingFlag(FLAG_NO); 
                    } 
                    if (protocolPerson.getAffiliationType() != null) {
                        investigator.setAffiliationDesc(protocolPerson.getAffiliationType().getDescription());
                    } 
                    List<edu.mit.coeus.xml.iacuc.InvestigatorType.Unit> unitList = new ArrayList <InvestigatorType.Unit>();
                    for (ProtocolUnitBase protocolUnit :protocolPerson.getProtocolUnits()) {
                        edu.mit.coeus.xml.iacuc.InvestigatorType.Unit unit = edu.mit.coeus.xml.iacuc.InvestigatorType.Unit.Factory.newInstance();
                        unit.setUnitName(protocolUnit.getUnitName());
                        unit.setUnitNumber(protocolUnit.getUnitNumber());
                        unitList.add(unit);                        
                    } 
                    investigator.setUnitArray((edu.mit.coeus.xml.iacuc.InvestigatorType.Unit[]) unitList.
                            toArray(new edu.mit.coeus.xml.iacuc.InvestigatorType.Unit[0]));
                     
                }
                KcServiceLocator.getService(IacucPrintXmlUtilService.class).setPersonRolodexType(protocolPerson, investigator.addNewPerson());
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
                KcServiceLocator.getService(IacucPrintXmlUtilService.class).setPersonRolodexType(protocolPerson, keyStudyPerson.addNewPerson());
            } else if (protocolPerson.getProtocolPersonRoleId().equals(IacucProtocolPersonRole.ROLE_CORRESPONDENTS)
                  || (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRoleBase.ROLE_CORRESPONDENT_ADMINISTRATOR))) {
                CorrespondentType correspondent = protocolType.addNewCorrespondent();
                correspondent.setTypeOfCorrespondent(protocolPerson.getProtocolPersonRole().getDescription());
                correspondent.setCorrespondentTypeDesc(protocolPerson.getProtocolPersonRole().getDescription());
                KcServiceLocator.getService(IacucPrintXmlUtilService.class).setPersonRolodexType(protocolPerson, correspondent.addNewPerson());
            }
        }

    }

    private void setProtocolMasterData(IacucProtocol protocol, ProtocolType protocolType) {
        ProtocolMasterDataType protocolMaster = protocolType.addNewProtocolMasterData();
        if (protocol == null)
            return;
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

    }

    /**
     * Sets the irbPrintXmlUtilService attribute value.
     * 
     * @param irbPrintXmlUtilService The irbPrintXmlUtilService to set.
     */
    public void setPrintXmlUtilService(IacucPrintXmlUtilService printXmlUtilService) {
        this.printXmlUtilService = printXmlUtilService;
    }

    /**
     * Gets the irbPrintXmlUtilService attribute.
     * 
     * @return Returns the irbPrintXmlUtilService.
     */
    public IacucPrintXmlUtilService getPrintXmlUtilService() {
        return printXmlUtilService;
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
     * @param committeeXmlStream The committeeXmlStream to set.
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


}
