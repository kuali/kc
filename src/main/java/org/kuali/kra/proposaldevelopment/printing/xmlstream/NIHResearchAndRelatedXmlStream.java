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
package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.AssuranceType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.ContactInfoType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.PostalAddressType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.AbstractType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ApplicantOrganizationType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.AuthorizedOrganizationalRepresentativeType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetPeriodType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.FundingOpportunityDetailsType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.HumanSubjectsType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.KeyPersonBiosketchType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.KeyPersonType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.NSFOtherPersonnelType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.NSFSeniorPersonnelType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.OrgAssurancesType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ResearchAndRelatedProjectDocument;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.SalariesAndWagesType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.SignatureType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ApplicantOrganizationType.OrganizationClassification;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetPeriodType.ProgramIncomeDetails;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.IndirectCostRateDetails;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod.ConsortiumCosts;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod.IndirectCostDetails;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod.SalarySubtotals;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.IndirectCostRateDetails.NoDHHSAgreement;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ProgramDirectorPrincipalInvestigatorDocument.ProgramDirectorPrincipalInvestigator;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ProjectDescriptionDocument.ProjectDescription;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ResearchAndRelatedProjectDocument.ResearchAndRelatedProject;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ResearchCoverPageDocument.ResearchCoverPage;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.DescriptionBlockType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.PersonFullNameType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSiteType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProposalPersonType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.KeyPersonType.KeyPersonFlag;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectCostsDocument.OtherDirectCosts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryMapping;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetRateAndBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.budget.bo.ProposalDevelopmentBudgetExt;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularIdc;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * This class generates XML that confirms with the RaR XSD related to Proposal
 * Submission Report or Sponsor Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 * 
 */
public class NIHResearchAndRelatedXmlStream extends
AbstractResearchAndRelatedStream {

    private static final Log LOG = LogFactory
    .getLog(NIHResearchAndRelatedXmlStream.class);

    private static final String ORGANIZATION_QUESTION_ID_H5 = "H5";
    private static final String ORGANIZATION_QUESTION_ID_I8 = "I8";
    private static final String DEFAULT_VALUE_KEY_PERSON_FLAG_CODE = "true";
    private static final String KEY_PERSON_FLAG_DESCRIPTION_VALUE_COLLABORATOR = "Collaborator/Other";
    private static final String KEY_PERSON_FLAG_DESCRIPTION_VALUE_KEY_PERSON = "Key Person";
    private static final String KEY_PERSON_FLAG_CODE_VALUE_FALSE = "false";
    private static final String GENERAL_CERTIFICATION_QUESTION_ID = "H6";
    private static final String LOBBYING_QUESTION_ID = "H0";
    private static final String REFERENCES_BLOCK_TYPE = "references";
    private static final String EQUIPMENT_BLOCK_TYPE = "equipment";
    private static final String FACILITIES_BLOCK_TYPE = "facilities";
    private static final String PROJECT_SUMMARY_BLOCK_TYPE = "summary";
    private static final String SPECIAL_REVIEW_CODE_1 = "1";

    private static final String APPROVAL_TYPE_EXEMPT = "4";
    protected static final String PROPOSAL_YNQ_QUESTION_16 = "16";

    private static final String ACADEMIC_PERIOD = "2";
    private static final String CALENDAR_PERIOD = "3";

    private static final String PROJECT_ROLE_PI = "PI";
    private static final String PROJECT_ROLE_COI = "COI";
    private static final String PROJECT_ROLE_KP = "KP";

    private static final Object PROPOSAL_YNQ_QUESTION_17 = "17";

    private static final int BUDGET_PERIOD_5 = 5;

    private static final String BUDGET_PERIOD_TYPE_4 = "4";

    private static final String BUDGET_PERIOD_TYPE_2 = "2";

    private static final String BUDGET_PERIOD_TYPE_3 = "3";

    protected ParameterService parameterService;
    private SponsorService sponsorService;
    private BudgetDecimal cumulativeCalendarMonthsFunded = BudgetDecimal.ZERO;

    /**
     * This method generates XML for Proposal Submission Report or Sponsor
     * Report. It uses data passed in {@link ResearchDocumentBase} for
     * populating the XML nodes. The XMl once generated is returned as
     * {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(
            KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        DevelopmentProposal developmentProposal = (DevelopmentProposal) printableBusinessObject;
        Budget budget = getBudget(developmentProposal.getProposalDocument());
        ResearchAndRelatedProjectDocument researchAndRelatedProjectDocument = ResearchAndRelatedProjectDocument.Factory
        .newInstance();
        researchAndRelatedProjectDocument
        .setResearchAndRelatedProject(getResearchAndRelatedProject(
                developmentProposal, budget));
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        xmlObjectList.put(REPORT_NAME, researchAndRelatedProjectDocument);
        return xmlObjectList;
    }

    /*
     * This method will set the values to root element of xml object
     * ResearchAndRelatedProject attributes like project description ,
     * Organization assurances , budget summary, Research Cover page, and
     * keyPerson.
     */
    private ResearchAndRelatedProject getResearchAndRelatedProject(
            DevelopmentProposal developmentProposal,Budget budget) {
        ResearchAndRelatedProject researchAndRelatedProject = ResearchAndRelatedProject.Factory.newInstance();
        researchAndRelatedProject.setProjectDescription(getProjectDescription(developmentProposal));
        researchAndRelatedProject.setOrgAssurances(getOrgAssurances(developmentProposal));
        researchAndRelatedProject.setAbstractArray(getAbstractArray(developmentProposal));
        researchAndRelatedProject.setProposalPersonArray(getProposalPersonArray(developmentProposal, budget));
        researchAndRelatedProject.setKeyPersonArray(getKeyPersonArray(developmentProposal));
        researchAndRelatedProject.setNSFPreviousAwardNumber(getNSFPreviousAwardNumber(developmentProposal));
        researchAndRelatedProject.setNSFProjectDuration(getNSFProjectDuration(developmentProposal));
        researchAndRelatedProject.setNihInventions(getNihInventions(developmentProposal));
        Award award = null;
        if(developmentProposal.getCurrentAwardNumber()!=null){
            award = getAward(developmentProposal.getCurrentAwardNumber());
        }
        if (award != null) {
            AwardAmountInfo amountInfo = getMaxAwardAmountInfo(award);
            if(amountInfo!=null){
                if(amountInfo.getCurrentFundEffectiveDate()!=null){
                    Calendar totalProjectStartDate = Calendar.getInstance();
                    totalProjectStartDate.setTime(amountInfo.getCurrentFundEffectiveDate());
                    researchAndRelatedProject.setTotalProjectStartDt(totalProjectStartDate);
                }
                if(amountInfo.getFinalExpirationDate()!=null){
                    Calendar totalProjectEndDate = Calendar.getInstance();
                    totalProjectEndDate.setTime(amountInfo.getFinalExpirationDate());
                    researchAndRelatedProject.setTotalProjectEndDt(totalProjectEndDate);
                }
            }
        }
        setNIHDeatils(researchAndRelatedProject, developmentProposal);
        try {
            researchAndRelatedProject.setResearchCoverPage(getResearchCoverPage(developmentProposal, budget));
        } catch (ParseException e) {
            LOG.error("Unable to parse String date", e);
        }
        researchAndRelatedProject.setBudgetSummary(getBudgetSummary(budget,developmentProposal));
        return researchAndRelatedProject;
    }
    protected boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
        String proposalTypeCodeRenewal = 
            parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL);
        String proposalTypeCodeRevision = 
            parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION);
        String proposalTypeCodeContinuation = 
            parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION);

        return !StringUtils.isEmpty(proposalTypeCode) &&
        (proposalTypeCode.equals(proposalTypeCodeRenewal) ||
                proposalTypeCode.equals(proposalTypeCodeRevision) ||
                proposalTypeCode.equals(proposalTypeCodeContinuation));
    }  

    private void setNIHDeatils(
            ResearchAndRelatedProject researchAndRelatedProject,
            DevelopmentProposal developmentProposal) {
        String proposalTypeCode = developmentProposal.getProposalTypeCode();
        String federalId  = getS2SUtilService().getFederalId(developmentProposal.getProposalDocument());
        researchAndRelatedProject.setNihPriorGrantNumber(federalId);
        //		if (ProposalType.RESUBMISSION_TYPE_CODE.equals(proposalTypeCode)) {
        //			InstitutionalProposal institutionalProposal = getMaxInstitutionalProposal(developmentProposal);
        //			if(institutionalProposal!=null){
        //			    researchAndRelatedProject
        //					.setNihPriorGrantNumber(institutionalProposal.getSponsorProposalNumber()); // setNihPriorGrantNumber
        //			}
        //		} else 

        if (isProposalTypeRenewalRevisionContinuation(proposalTypeCode)) {
            String federalIdComesFromAwardStr = parameterService
            .getParameterValueAsString(ProposalDevelopmentDocument.class,"FEDERAL_ID_COMES_FROM_CURRENT_AWARD");
            if ("Y".equalsIgnoreCase(federalIdComesFromAwardStr)) {
                String currentAwardNumber = developmentProposal.getCurrentAwardNumber();
                Award award = getAward(currentAwardNumber);
                if(award!=null){
                    String sponsorAwardNumber = award.getSponsorAwardNumber(); // setNihPriorGrantNumber
                    if(sponsorAwardNumber!=null && sponsorAwardNumber.length()>5){
                        String activityCode = sponsorAwardNumber.substring(2, 5); // setNihActivityCode
                        String awardType = sponsorAwardNumber.substring(0, 1); // setNihApplicationTypeCode
                        researchAndRelatedProject.setNihActivityCode(activityCode);
                        researchAndRelatedProject.setNihApplicationTypeCode(awardType);
                    }
                }
                //				researchAndRelatedProject
                //						.setNihPriorGrantNumber(sponsorAwardNumber);
            }
        }
    }

    /**
     * @param developmentProposal
     * @return
     */
    private InstitutionalProposal getMaxInstitutionalProposal(
            DevelopmentProposal developmentProposal) {
        String continuedFrom = developmentProposal.getContinuedFrom();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("proposalId", continuedFrom);
        List<InstitutionalProposal> institutionalProposals = (List<InstitutionalProposal>) getBusinessObjectService().findMatching(InstitutionalProposal.class, fieldValues);
        InstitutionalProposal institutionalProposal = null;
        Integer maxSequenceNumber = 0;
        for (InstitutionalProposal proposal : institutionalProposals) {
            if (maxSequenceNumber < proposal.getSequenceNumber()) {
                maxSequenceNumber = proposal.getSequenceNumber();
                institutionalProposal = proposal;
            }
        }
        return institutionalProposal;
    }

    private String getNihInventions(DevelopmentProposal developmentProposal) {
        String nihInventions = null;
        for (ProposalYnq proposalYnq : developmentProposal.getProposalYnqs()) {
            if (proposalYnq.getQuestionId() != null
                    && proposalYnq.getQuestionId().equals(
                            PROPOSAL_YNQ_QUESTION_16)) {
                nihInventions = proposalYnq.getAnswer();
            }
        }
        return nihInventions;
    }

    private BigInteger getNSFProjectDuration(
            DevelopmentProposal developmentProposal) {

        BigInteger projectDuration = null;
        if (developmentProposal.getRequestedEndDateInitial() != null
                && developmentProposal.getRequestedStartDateInitial() != null) {
            BigDecimal months = getMonthsBetweenDates(developmentProposal
                    .getRequestedStartDateInitial(), developmentProposal
                    .getRequestedEndDateInitial());
            projectDuration = months.toBigInteger();
        }
        return projectDuration;
    }

    /**
     * @param Start
     *            Date
     * @param End
     *            Date
     * @return Difference of the Months of the Dates.
     */
    private BigDecimal getMonthsBetweenDates(Date pFrom, Date pTo) {
        BudgetDecimal projectDuration = null;
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTimeInMillis(pFrom.getTime());
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTimeInMillis(pTo.getTime());
        int yd = 12*( calendarEnd.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR));
        int md = (calendarEnd.get( Calendar.MONTH ) - calendarStart.get( Calendar.MONTH ));
        int dd = (calendarEnd.get( Calendar.DAY_OF_MONTH ) - calendarStart.get( Calendar.DAY_OF_MONTH ));
        if(dd>=15) md++;
        int result = yd + md;
        projectDuration = new BudgetDecimal(result);
        return projectDuration.setScale(0).bigDecimalValue();
    }

    private String getNSFPreviousAwardNumber(DevelopmentProposal developmentProposal) {
        String currentAwardNumber = developmentProposal.getCurrentAwardNumber();
        Award award = getAward(currentAwardNumber);
        if (award != null) {
            return award.getSponsorAwardNumber();
        } else {
            return null;
        }
    }

    private AbstractType[] getAbstractArray(
            DevelopmentProposal developmentProposal) {
        List<AbstractType> abstractTypeList = new ArrayList<AbstractType>();
        AbstractType abstractType = null;
        BigInteger abstractTypeCode = null;
        for (ProposalAbstract proposalAbstract : developmentProposal
                .getProposalAbstracts()) {
            if (proposalAbstract.getAbstractType() != null) {
                try {
                    abstractType = AbstractType.Factory.newInstance();
                    abstractType.setAbstractText(proposalAbstract
                            .getAbstractDetails());
                    abstractTypeCode = new BigInteger(proposalAbstract
                            .getAbstractType().getAbstractTypeCode());
                    abstractType.setAbstractTypeCode(abstractTypeCode);
                    abstractType.setAbstractTypeDesc(proposalAbstract
                            .getAbstractType().getDescription());
                    abstractTypeList.add(abstractType);
                } catch (NumberFormatException e) {
                    LOG.info("abstractTypeCode is Not a Number for proposal :"
                            + developmentProposal.getProposalNumber());
                }
            }
        }
        return abstractTypeList.toArray(new AbstractType[0]);
    }

    /*
     * This method will set the values to Proposal person type attributes and
     * finally return ProposalPersonType xml object.
     */
    private ProposalPersonType[] getProposalPersonArray(
            DevelopmentProposal developmentProposal, Budget budget) {
        List<ProposalPersonType> proposalPersonTypeList = new ArrayList<ProposalPersonType>();
        List<ProposalPerson> proposalPersons = developmentProposal
        .getProposalPersons();
        for (ProposalPerson proposalPerson : proposalPersons) {
            proposalPersonTypeList.add(getProposalPersonTypeWithValues(
                    developmentProposal, proposalPerson, budget));
        }
        return proposalPersonTypeList.toArray(new ProposalPersonType[0]);
    }

    private ProposalPersonType getProposalPersonTypeWithValues(
            DevelopmentProposal developmentProposal,
            ProposalPerson proposalPerson, Budget budget) {
        ProposalPersonType proposalPersonType = ProposalPersonType.Factory
        .newInstance();
        String degree = getDegree(proposalPerson);
        proposalPersonType.setDegree(degree);
        proposalPersonType.setEmail(proposalPerson.getEmailAddress());
        proposalPersonType
        .setName(getProposalPersonFullNameType(proposalPerson));
        proposalPersonType.setPhone(proposalPerson.getPhoneNumber());
        if(PROJECT_ROLE_PI.equals(proposalPerson.getProposalPersonRoleId())){
            proposalPersonType.setProjectRole(ProjectRoleType.PI_PD);
        }else if(PROJECT_ROLE_COI.equals(proposalPerson.getProposalPersonRoleId())){
            proposalPersonType.setProjectRole(ProjectRoleType.CO_PI_PD);
        }else{
            proposalPersonType.setProjectRole(ProjectRoleType.KEY_PERSON);
        }

        proposalPersonType.setSSN(proposalPerson.getSocialSecurityNumber());
        if (proposalPerson.getDateOfBirth() != null) {
            proposalPersonType.setDOB(getDateTimeService().toDateString(proposalPerson.getDateOfBirth()));
        }

        BudgetDecimal calendarMonths = BudgetDecimal.ZERO;
        BudgetDecimal academicMonths = BudgetDecimal.ZERO;
        BudgetDecimal summerMonths = BudgetDecimal.ZERO;
        if(budget!=null)
            for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                if (budgetPeriod.getBudgetPeriod().intValue() == 1) {
                    for (BudgetLineItem lineItem : budgetPeriod
                            .getBudgetLineItems()) {
                        for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                            if ((proposalPerson.getPersonId() != null && proposalPerson.getPersonId().equals(budgetPersonnelDetails.getPersonId()))
                                    || (proposalPerson.getRolodexId() != null && proposalPerson.getRolodexId().toString().equals(budgetPersonnelDetails.getPersonId()))) {
                                proposalPersonType.setPercentEffort(budgetPersonnelDetails.getPercentEffort().bigDecimalValue());
                                proposalPersonType.setRequestedCost(budgetPersonnelDetails.getSalaryRequested().bigDecimalValue());
                                if (ACADEMIC_PERIOD.equals(budgetPersonnelDetails.getPeriodTypeCode())) {
                                    academicMonths = academicMonths.add(budgetPersonnelDetails.getPercentEffort().multiply(new BudgetDecimal("0.01"))
                                            .multiply(getNumberOfMonths(budgetPersonnelDetails.getStartDate(),budgetPersonnelDetails.getEndDate())));
                                } else if (CALENDAR_PERIOD.equals(budgetPersonnelDetails.getPeriodTypeCode())) {
                                    calendarMonths = calendarMonths.add(budgetPersonnelDetails.getPercentEffort().multiply(new BudgetDecimal("0.01"))
                                            .multiply(getNumberOfMonths(budgetPersonnelDetails.getStartDate(),budgetPersonnelDetails.getEndDate())));
                                } else
                                    summerMonths = summerMonths.add(budgetPersonnelDetails.getPercentEffort().multiply(new BudgetDecimal("0.01"))
                                            .multiply(getNumberOfMonths(budgetPersonnelDetails.getStartDate(),budgetPersonnelDetails.getEndDate())));
                            }
                        }
                    }
                }
            }
        proposalPersonType.setAcademicFundingMonths(academicMonths.bigDecimalValue().setScale(2));
        proposalPersonType.setSummerFundingMonths(summerMonths.bigDecimalValue().setScale(2));		
        proposalPersonType.setFundingMonths(calendarMonths.bigDecimalValue().setScale(2, RoundingMode.HALF_UP));
        return proposalPersonType;
    }

    /**
     * This method...
     * @param proposalPerson
     * @return
     */
    private String getDegree(ProposalPerson proposalPerson) {
        List<ProposalPersonDegree> proposalPersonDegress = proposalPerson.getProposalPersonDegrees();
        String degree = null;
        for (ProposalPersonDegree proposalPersonDegree : proposalPersonDegress) {
            degree = degree==null?proposalPersonDegree.getDegree():degree+","+proposalPersonDegree.getDegree(); 
        }
        return degree;
    }

    /**
     * 
     * This method computes the number of months between any 2 given
     * {@link Date} objects
     * 
     * @param dateStart
     *            starting date.
     * @param dateEnd
     *            end date.
     * 
     * @return number of months between the start date and end date.
     */
    private BudgetDecimal getNumberOfMonths(Date dateStart, Date dateEnd) {
        BudgetDecimal monthCount = BudgetDecimal.ZERO;
        int fullMonthCount = 0;

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(dateStart);
        endDate.setTime(dateEnd);

        startDate.clear(Calendar.HOUR);
        startDate.clear(Calendar.MINUTE);
        startDate.clear(Calendar.SECOND);
        startDate.clear(Calendar.MILLISECOND);

        endDate.clear(Calendar.HOUR);
        endDate.clear(Calendar.MINUTE);
        endDate.clear(Calendar.SECOND);
        endDate.clear(Calendar.MILLISECOND);

        if (startDate.after(endDate)) {
            return BudgetDecimal.ZERO;
        }
        int startMonthDays = startDate.getActualMaximum(Calendar.DATE)
        - startDate.get(Calendar.DATE);
        startMonthDays++;
        int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
        BudgetDecimal startMonthFraction = new BudgetDecimal(startMonthDays)
        .divide(new BudgetDecimal(startMonthMaxDays));

        int endMonthDays = endDate.get(Calendar.DATE);
        int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

        BudgetDecimal endMonthFraction = new BudgetDecimal(endMonthDays)
        .divide(new BudgetDecimal(endMonthMaxDays));

        startDate.set(Calendar.DATE, 1);
        endDate.set(Calendar.DATE, 1);

        while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
            fullMonthCount++;
        }
        fullMonthCount = fullMonthCount - 1;
        monthCount = monthCount.add(new BudgetDecimal(fullMonthCount)).add(
                startMonthFraction).add(endMonthFraction);
        return monthCount;
    }

    private PersonFullNameType getProposalPersonFullNameType(
            ProposalPerson proposalPerson) {
        PersonFullNameType personFullNameType = PersonFullNameType.Factory
        .newInstance();
        personFullNameType.setFirstName(proposalPerson.getFirstName());
        personFullNameType.setLastName(proposalPerson.getLastName());
        personFullNameType.setMiddleName(proposalPerson.getMiddleName());
        return personFullNameType;
    }

    /*
     * This method will set the values to key person type attributes and finally
     * return KeyPersonType xml object.
     */
    private KeyPersonType[] getKeyPersonArray(DevelopmentProposal developmentProposal) {
        List<ProposalPerson> propKeyPersons = new ArrayList<ProposalPerson>();
        List<ProposalPerson> propInvestigators = new ArrayList<ProposalPerson>();
        List<ProposalPerson> propPIs = new ArrayList<ProposalPerson>();
        for (ProposalPerson proposalPerson : developmentProposal
                .getProposalPersons()) {
            if (ContactRole.PI_CODE.equals(proposalPerson.getProposalPersonRoleId())) {
                propPIs.add(proposalPerson);
            } else if (ContactRole.KEY_PERSON_CODE.equals(proposalPerson.getProposalPersonRoleId())) {
                propKeyPersons.add(proposalPerson);
            } else {
                propInvestigators.add(proposalPerson);
            }
        }
        sortKeyPersonWithName(propPIs);
        sortKeyPersonWithName(propKeyPersons);
        sortKeyPersonWithName(propInvestigators);
        List<KeyPersonType> keyPersonPIList = getKeyPersonForPropInvestigator(
                developmentProposal, propPIs,true);
        List<KeyPersonType> keyPersonInvestigatorList = getKeyPersonForPropInvestigator(
                developmentProposal, propInvestigators,false);
        List<KeyPersonType> keyPersonKeyPersonList = getKeyPersonForPropKeyPerson(
                developmentProposal, propKeyPersons);
        List<KeyPersonType> allKeyPersonList = new ArrayList<KeyPersonType>();
        allKeyPersonList.addAll(keyPersonPIList);
        allKeyPersonList.addAll(keyPersonInvestigatorList);
        allKeyPersonList.addAll(keyPersonKeyPersonList);
        return allKeyPersonList.toArray(new KeyPersonType[0]);
    }

    private List<KeyPersonType> getKeyPersonForPropKeyPerson(DevelopmentProposal developmentProposal,List<ProposalPerson> proposalKeyPersons) {
        List<KeyPersonType> keyPersonlist = new ArrayList<KeyPersonType>();
        for (ProposalPerson proposalPerson : proposalKeyPersons) {
            KeyPersonType keyPersonType = KeyPersonType.Factory.newInstance();
            PersonFullNameType personFullNameType = getPersonFullName(proposalPerson);
            keyPersonType.setName(personFullNameType);
            ContactInfoType contactInfoType = getContactInfoType(proposalPerson);
            keyPersonType.setContactInformation(contactInfoType);
            KeyPersonFlag keyPersonFlag = getKeyPersonFlag(proposalPerson);
            keyPersonType.setKeyPersonFlag(keyPersonFlag);
            keyPersonType.setSocialSecurityNumber(proposalPerson.getSocialSecurityNumber());
            String unitName = getUnitName(proposalPerson);
            if (unitName != null) {
                keyPersonType.setOrganizationDepartment(unitName);
            }
            Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
            keyPersonType.setOrganizationName(organization.getOrganizationName());
            if (proposalPerson.getPrimaryTitle() != null) {
                keyPersonType
                .setPositionTitle(proposalPerson.getPrimaryTitle());
            }
            setBiographicalSketch(proposalPerson,keyPersonType);
            keyPersonType.setOrganizationDivision(getMajorSubDivision(getLeadUnit(developmentProposal)));
            setDegree(proposalPerson, keyPersonType);
            if (proposalPerson.getEraCommonsUserName() == null) {
                keyPersonType.setAccountIdentifier("Unknown");
            }
            else {
                keyPersonType.setAccountIdentifier(proposalPerson.getEraCommonsUserName());
            }

            keyPersonlist.add(keyPersonType);
        }
        return keyPersonlist;
    }

    private void setBiographicalSketch(ProposalPerson proposalPerson, KeyPersonType keyPersonType) {
        KeyPersonBiosketchType keyPersonBioSketch = keyPersonType.addNewNIHBiographicalSketch();
        keyPersonBioSketch.setResearchSupportFileIdentifier("researchfilename");
        keyPersonBioSketch.setPositionsHonorsCitationsFileIdentifier("honorsfilename");

    }

    private void setDegree(ProposalPerson proposalPerson, KeyPersonType keyPersonType) {
        List<ProposalPersonDegree> proposalPersonDegrees = proposalPerson.getProposalPersonDegrees();
        for (ProposalPersonDegree proposalPersonDegree : proposalPersonDegrees) {
            keyPersonType.addDegree(proposalPersonDegree.getDegree());
        }
    }

    private String getMajorSubDivision(String leadUnit) {
        UnitService unitService = KraServiceLocator.getService(UnitService.class);
        List<Unit> units = unitService.getAllSubUnits("000001");
        for (Unit unit : units) {
            if(unit.getUnitNumber().equals(leadUnit)){
                return unit.getParentUnitNumber();
            }
        }
        return leadUnit;
    }

    private String getLeadUnit(DevelopmentProposal developmentProposal) {
        return developmentProposal.getOwnedByUnit().getUnitNumber();
    }

    /*
     * This method will set the values to key person type attributes like
     * organization department, organization name , position title etc ... if
     * key person found.
     */
    private List<KeyPersonType> getKeyPersonForPropInvestigator(
            DevelopmentProposal developmentProposal,
            List<ProposalPerson> proposalPersonList,boolean piFlag) {
        List<KeyPersonType> keyPersonTypeList = new ArrayList<KeyPersonType>();
        for (ProposalPerson proposalPerson : proposalPersonList) {
            KeyPersonType keyPersonType = KeyPersonType.Factory.newInstance();
            PersonFullNameType personFullNameType = getPersonFullName(proposalPerson);
            keyPersonType.setName(personFullNameType);
            ContactInfoType contactInfoType = getContactInfoType(proposalPerson);
            keyPersonType.setContactInformation(contactInfoType);
            setBiographicalSketch(proposalPerson, keyPersonType);
            KeyPersonFlag keyPersonFlag = getKeyPersonFlag(proposalPerson);
            if(piFlag){
                keyPersonFlag.setKeyPersonFlagDesc("PI");
            }
            keyPersonType.setKeyPersonFlag(keyPersonFlag);
            keyPersonType.setSocialSecurityNumber(proposalPerson
                    .getSocialSecurityNumber());
            String unitName = getUnitName(proposalPerson);
            if (unitName != null) {
                keyPersonType.setOrganizationDepartment(unitName);
            }
            Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
            keyPersonType.setOrganizationName(organization.getOrganizationName());
            keyPersonType.setOrganizationDivision(getMajorSubDivision(getLeadUnit(developmentProposal)));
            if (proposalPerson.getPrimaryTitle() != null) {
                keyPersonType
                .setPositionTitle(proposalPerson.getPrimaryTitle());
            }
            setBiographicalSketch(proposalPerson,keyPersonType);
            keyPersonType.setOrganizationDivision(getMajorSubDivision(getLeadUnit(developmentProposal)));
            setDegree(proposalPerson, keyPersonType);
            if (proposalPerson.getEraCommonsUserName() == null) {
                keyPersonType.setAccountIdentifier("Unknown");
            }
            else {
                keyPersonType.setAccountIdentifier(proposalPerson.getEraCommonsUserName());
            }

            keyPersonTypeList.add(keyPersonType);
        }
        return keyPersonTypeList;
    }

    /*
     * This method gets BudgetSummaryType XMLObject and setting data to
     * BudgetSummaryType from budget and budgetPeriod which having final
     * versionFlag enable.
     */
    private BudgetSummaryType getBudgetSummary(Budget budget,DevelopmentProposal developmentProposal) {
        BudgetSummaryType budgetSummaryType = BudgetSummaryType.Factory.newInstance();
        if (budget != null) {
            BudgetPeriod budgetPeriod = budget.getBudgetPeriod(0);
            budgetSummaryType.setInitialBudgetTotals(getBudgetTotals(budgetPeriod.getTotalCost(), budgetPeriod.getCostSharingAmount()));
            budgetSummaryType.setAllBudgetTotals(getBudgetTotals(budget.getTotalCost(), budget.getCostSharingAmount()));
            budgetSummaryType.setBudgetIndirectCostsTotal(budget.getTotalIndirectCost().bigDecimalValue());
            budgetSummaryType.setBudgetPeriodArray(getBudgetPeriodArray(developmentProposal,budget.getBudgetPeriods()));
            budgetSummaryType.setBudgetJustification(getBudgetJustification(developmentProposal.getProposalNumber()));
            setAllNSFSeniorPersonnels(developmentProposal,budget,budgetSummaryType);
            budgetSummaryType.setModularBudgetQuestion(budget.getModularBudgetFlag());
            budgetSummaryType.setBudgetCostsTotal(budget.getTotalCost().bigDecimalValue());
            budgetSummaryType.setBudgetDirectCostsTotal(budget.getTotalDirectCost().bigDecimalValue());

        }
        return budgetSummaryType;
    }

    private void setAllNSFSeniorPersonnels(DevelopmentProposal developmentProposal, Budget budget, BudgetSummaryType budgetSummaryType) {
        List<KeyPersonInfo> nsfSeniorPersons = getBudgetPersonsForCategoryMap(developmentProposal, budget, "01", "NSF_PRINTING");
        int rowNumber = 0;
        BudgetDecimal totalFringe = BudgetDecimal.ZERO;
        BudgetDecimal totalSalary = BudgetDecimal.ZERO;

        for (KeyPersonInfo keyPersonInfo : nsfSeniorPersons) {
            NSFSeniorPersonnelType nsfSeniorPersonnel = budgetSummaryType.addNewNSFSeniorPersonnel();
            setNSFSeniorPersonnel(keyPersonInfo, nsfSeniorPersonnel,rowNumber++);
            totalFringe = totalFringe.add(keyPersonInfo.getFringe());
            totalSalary = totalSalary.add(keyPersonInfo.getRequestedSalary());
        }
        budgetSummaryType.setTotalFringe(totalFringe.bigDecimalValue());
        budgetSummaryType.setTotalSalariesAndWages(totalSalary.bigDecimalValue());
        budgetSummaryType.setTotalSalariesWagesAndFringe(totalSalary.add(totalFringe).bigDecimalValue());
        budgetSummaryType.setIndirectCostRateDetails(getIndirectCostDetails(developmentProposal));
    }

    private IndirectCostRateDetails getIndirectCostDetails(DevelopmentProposal developmentProposal) {
        IndirectCostRateDetails indirectCost = IndirectCostRateDetails.Factory.newInstance();
        String dhhsAgreementFlag = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, "DHHS_AGREEMENT");
        Organization orgBean = developmentProposal.getApplicantOrganization().getOrganization();
        try {
            if (dhhsAgreementFlag.equals("0")) {
                // agreement is not with DHHS
                NoDHHSAgreement noAgreement = NoDHHSAgreement.Factory.newInstance();
                noAgreement.setAgencyName(getS2SUtilService().getCognizantFedAgency(developmentProposal));
                if (orgBean.getIndirectCostRateAgreement() == null) {
                    noAgreement
                    .setAgreementDate(getDateTimeService().getCalendar(getDateTimeService().convertToDate("1900-01-01")));
                }
                else
                    noAgreement.setAgreementDate(getDateTimeService().getCalendar(
                            getDateTimeService().convertToDate(orgBean.getIndirectCostRateAgreement())));
                indirectCost.setNoDHHSAgreement(noAgreement);
            }else {
                // agreement is with DHHS
                // check agreement date . If there is no date, assume that negotiations are in process,
                // and take the agency with whom negotiations are being conducted from the rolodex entry of the
                // cognizant auditor
                if (orgBean.getIndirectCostRateAgreement() != null) {
                    indirectCost.setDHHSAgreementDate(getDateTimeService().getCalendar(
                            getDateTimeService().convertToDate(orgBean.getIndirectCostRateAgreement())));
                }else {
                    indirectCost.setDHHSAgreementNegotiationOffice(getS2SUtilService().getCognizantFedAgency(developmentProposal));
                }
            }
        }catch (ParseException e) {
            LOG.error(e);
        }
        return indirectCost;
    }

    private int setNSFSeniorPersonnels(DevelopmentProposal developmentProposal, BudgetPeriod budgetPeriod,
            gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod  BudgetPeriodType) {
        List<KeyPersonInfo> nsfSeniorPersons = getBudgetPersonsForCategoryMap(developmentProposal, budgetPeriod, "01", "NSF_PRINTING");
        int rowNumber = 0;
        int period = budgetPeriod.getBudgetPeriod();
        for (KeyPersonInfo keyPersonInfo : nsfSeniorPersons) {
             setNSFSeniorPersonnel(keyPersonInfo, BudgetPeriodType.addNewNSFSeniorPersonnel(),rowNumber++,period);
        }
        return rowNumber;
    }
    public void setNSFSeniorPersonnel(KeyPersonInfo seniorPersonnelBean,NSFSeniorPersonnelType nsfSeniorPersonnelType, int rowNumber,int period){
        if(period <= BUDGET_PERIOD_5){
        cumulativeCalendarMonthsFunded = cumulativeCalendarMonthsFunded.add(seniorPersonnelBean.getCalendarMonths());
        }
        nsfSeniorPersonnelType.setFullName(getFullName(seniorPersonnelBean));
        nsfSeniorPersonnelType.setTitle(seniorPersonnelBean.getRole());      
        nsfSeniorPersonnelType.setAcademicMonthsFunded( seniorPersonnelBean.getAcademicMonths().bigDecimalValue());     
        nsfSeniorPersonnelType.setCalendarMonthsFunded( seniorPersonnelBean.getCalendarMonths().bigDecimalValue());         
        nsfSeniorPersonnelType.setSummerMonthsFunded(seniorPersonnelBean.getSummerMonths().bigDecimalValue()); 
        nsfSeniorPersonnelType.setFundsRequested(seniorPersonnelBean.getRequestedSalary().bigDecimalValue());
        nsfSeniorPersonnelType.setPersonID(seniorPersonnelBean.getPersonId());
        nsfSeniorPersonnelType.setRownumber(BigInteger.valueOf(rowNumber));
    }
    
    public void setNSFSeniorPersonnel(KeyPersonInfo seniorPersonnelBean,NSFSeniorPersonnelType nsfSeniorPersonnelType, int rowNumber){    
        nsfSeniorPersonnelType.setFullName(getFullName(seniorPersonnelBean));
        nsfSeniorPersonnelType.setTitle(seniorPersonnelBean.getRole());      
        nsfSeniorPersonnelType.setAcademicMonthsFunded( seniorPersonnelBean.getAcademicMonths().bigDecimalValue());     
        nsfSeniorPersonnelType.setCalendarMonthsFunded(cumulativeCalendarMonthsFunded.bigDecimalValue());         
        nsfSeniorPersonnelType.setSummerMonthsFunded(seniorPersonnelBean.getSummerMonths().bigDecimalValue()); 
        nsfSeniorPersonnelType.setFundsRequested(seniorPersonnelBean.getRequestedSalary().bigDecimalValue());
        nsfSeniorPersonnelType.setPersonID(seniorPersonnelBean.getPersonId());
        nsfSeniorPersonnelType.setRownumber(BigInteger.valueOf(rowNumber));
        cumulativeCalendarMonthsFunded = BudgetDecimal.ZERO;
    }
    private String getFullName(KeyPersonInfo seniorPersonnelBean) {
        return seniorPersonnelBean.getLastName() + ", "+seniorPersonnelBean.getFirstName();
    }

    /*
     * This method gets arrays of BudgetPeriodType XMLObjects by setting each
     * BudgetPeriodType data from budgetPeriod data
     */
    private gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod[] getBudgetPeriodArray(
            DevelopmentProposal developmentProposal,List<BudgetPeriod> budgetPeriodList) {
        List<gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod> budgetPeriods = 
            new ArrayList<gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod>();
        for (BudgetPeriod budgetPeriod : budgetPeriodList) {
            Budget budget = budgetPeriod.getBudget();
            if (budgetPeriod.getBudgetPeriod() != null) {
                List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
                gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod budgetPeriodType = 
                    gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod.Factory.newInstance();
                budgetPeriodType.setBudgetPeriodID(BigInteger.valueOf(budgetPeriod.getBudgetPeriod()));
                budgetPeriodType.setStartDate(getDateTimeService().getCalendar(budgetPeriod.getStartDate()));
                budgetPeriodType.setEndDate(getDateTimeService().getCalendar(budgetPeriod.getEndDate()));
                budgetPeriodType.setFee(new BigDecimal(0));
                setSalaryAndWages(developmentProposal,budget,budgetPeriod,budgetPeriodType);
                //				budgetPeriodType.setSalariesWagesTotal(getSalaryWagesTotal(budgetLineItems));
                //				budgetPeriodType.setSalariesAndWagesArray(getSalaryAndWages(developmentProposal,budget,budgetPeriod));
                budgetPeriodType.setEquipmentTotal(getEquipmentTotal(budgetLineItems));
                budgetPeriodType.setEquipmentCostsArray(getEquipmentCosts(budgetLineItems));
                budgetPeriodType.setOtherDirectCostsArray(getOtherDirectCosts(developmentProposal,budgetLineItems));
                setNonPersonnelLACost(budgetLineItems,budgetPeriodType);
                budgetPeriodType.setOtherDirectTotal(getOtherDirectTotal(budgetLineItems));
                budgetPeriodType.setTravelCostsArray(getTravelCosts(budgetLineItems));
                budgetPeriodType.setTravelTotal(getTravelTotal(budgetLineItems));
                budgetPeriodType.setParticipantPatientCostsArray(getParticipantPatientCost(developmentProposal,budgetLineItems));
                budgetPeriodType.setParticipantPatientTotal(getParticipantPatientTotal(budgetLineItems));
                budgetPeriodType.setPeriodDirectCostsTotal(budgetPeriod.getTotalDirectCost().bigDecimalValue());
                budgetPeriodType.setIndirectCostsTotal(budgetPeriod.getTotalIndirectCost().bigDecimalValue());
                setIndirectCostDetails(budgetPeriod,budgetPeriodType);
                budgetPeriodType.setPeriodCostsTotal(budgetPeriod.getTotalCost().bigDecimalValue());
                budgetPeriodType.setProgramIncome(new BigDecimal(0));
                budgetPeriodType.setConsortiumCosts(getConsortiumCosts(developmentProposal,budgetPeriod));
                int count = setNSFSeniorPersonnels(developmentProposal, budgetPeriod, budgetPeriodType);
                budgetPeriodType.setNSFTotalSeniorPersonnel(BigInteger.valueOf(count));
                setNSFOtherPersonnels(developmentProposal,budgetPeriod,budgetPeriodType);
                setSalarySubTotals(budgetPeriod,budgetPeriodType);
                setProgramIncome(budgetPeriod,budgetPeriodType);

                budgetPeriods.add(budgetPeriodType);
            }
        }
        return budgetPeriods.toArray(new gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod[0]);
    }
    class IndirectCostDetailsBean{
        private BudgetDecimal baseAmount = BudgetDecimal.ZERO;
        private BudgetDecimal rate = BudgetDecimal.ZERO;
        private BudgetDecimal fund = BudgetDecimal.ZERO;
        private String rateTypeDescription;
    }
    private void setIndirectCostDetails(BudgetPeriod budgetPeriod,
            gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod budgetPeriodType) {
        Budget budget = budgetPeriod.getBudget();
        Map<String,IndirectCostDetailsBean> ohAmountsMap = new HashMap<String,IndirectCostDetailsBean>();
        if(budget.getModularBudgetFlag()){
            BudgetModular budgetModular = budgetPeriod.getBudgetModular();
            List<BudgetModularIdc> budgetModularIdcs = budgetModular.getBudgetModularIdcs();
            for (BudgetModularIdc budgetModularIdc : budgetModularIdcs) {
                String key = budgetModularIdc.getDescription()+budgetModularIdc.getIdcRate();
                if(ohAmountsMap.get(key)==null){
                    IndirectCostDetailsBean indcost = new IndirectCostDetailsBean();
                    indcost.rate = budgetModularIdc.getIdcRate();
                    indcost.baseAmount = budgetModularIdc.getIdcBase();
                    indcost.fund = budgetModularIdc.getFundsRequested();
                    indcost.rateTypeDescription = budgetModularIdc.getDescription();
                    ohAmountsMap.put(key, indcost);
                }else{
                    IndirectCostDetailsBean indcost = ohAmountsMap.get(key);
                    indcost.rate = indcost.rate.add(budgetModularIdc.getIdcRate());
                    indcost.baseAmount = indcost.baseAmount.add(budgetModularIdc.getIdcBase());
                    indcost.fund = indcost.fund.add(budgetModularIdc.getFundsRequested());
                    indcost.rateTypeDescription = budgetModularIdc.getDescription();
                }

            }

        }else{
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            for (BudgetLineItem budgetLineItem : budgetLineItems) {
                //                List<BudgetPersonnelDetails> budgetPersonnelLineItems = budgetLineItem.getBudgetPersonnelDetailsList();
                //                if(budgetPersonnelLineItems.isEmpty()){
                List<BudgetRateAndBase> budgetRateAndBases = budgetLineItem.getBudgetRateAndBaseList();
                for (BudgetRateAndBase budgetRateAndBase : budgetRateAndBases) {
                    String key = budgetRateAndBase.getRateClassCode()+budgetRateAndBase.getRateTypeCode();
                    if(budgetRateAndBase.getRateClass().getRateClassType().equals(RateClassType.OVERHEAD.getRateClassType())){
                        if(ohAmountsMap.get(key)==null){
                            IndirectCostDetailsBean indcost = new IndirectCostDetailsBean();
                            indcost.rate = budgetRateAndBase.getAppliedRate();
                            indcost.baseAmount = budgetRateAndBase.getBaseCost().add(budgetRateAndBase.getBaseCostSharing());
                            indcost.fund = budgetRateAndBase.getCalculatedCost().add(budgetRateAndBase.getCalculatedCostSharing());
                            indcost.rateTypeDescription = budgetRateAndBase.getRateClass().getRateClassTypeDescription();
                            ohAmountsMap.put(key, indcost);
                        }else{
                            IndirectCostDetailsBean indcost = ohAmountsMap.get(key);
                            indcost.baseAmount = indcost.baseAmount.add(budgetRateAndBase.getBaseCost()).add(budgetRateAndBase.getBaseCostSharing());
                            indcost.fund = indcost.fund.add(budgetRateAndBase.getCalculatedCost()).add(budgetRateAndBase.getCalculatedCostSharing());
                        }
                    }
                }
                //                }else{
                    //                    for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelLineItems) {
                //                        List<BudgetPersonnelRateAndBase> budgetRateAndBases = budgetPersonnelDetails.getBudgetPersonnelRateAndBaseList();
                //                        for (BudgetPersonnelRateAndBase budgetRateAndBase : budgetRateAndBases) {
                //                            String key = budgetRateAndBase.getRateClassCode()+budgetRateAndBase.getRateTypeCode();
                //                            if(budgetRateAndBase.getRateClass().getRateClassType().equals(RateClassType.OVERHEAD.getRateClassType())){
                //                                if(key==null){
                //                                    IndirectCostDetailsBean indcost = new IndirectCostDetailsBean();
                //                                    indcost.rate = budgetRateAndBase.getAppliedRate();
                //                                    indcost.baseAmount = budgetRateAndBase.getSalaryRequested().add(budgetRateAndBase.getBaseCostSharing());
                //                                    indcost.fund = budgetRateAndBase.getCalculatedCost().add(budgetRateAndBase.getCalculatedCostSharing());
                //                                    indcost.rateTypeDescription = budgetRateAndBase.getRateClass().getRateClassTypeDescription();
                //                                    ohAmountsMap.put(key, indcost);
                //                                }else{
                //                                    IndirectCostDetailsBean indcost = ohAmountsMap.get(key);
                //                                    indcost.baseAmount = indcost.baseAmount.add(budgetRateAndBase.getSalaryRequested()).add(budgetRateAndBase.getBaseCostSharing());
                //                                    indcost.fund = indcost.fund.add(budgetRateAndBase.getCalculatedCost()).add(budgetRateAndBase.getCalculatedCostSharing());
                //                                }
                //                            }
                //                        }
                //                        
                //                    }
                //                }
            }
        }
        for (Iterator<String> iterator = ohAmountsMap.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            IndirectCostDetailsBean indirectCostDetailBean = ohAmountsMap.get(key);
            IndirectCostDetails indDetailsType = budgetPeriodType.addNewIndirectCostDetails();
            indDetailsType.setCostType( indirectCostDetailBean.rateTypeDescription == null ? "UNKNOWN" :
                indirectCostDetailBean.rateTypeDescription);
            indDetailsType.setBaseAmount(indirectCostDetailBean.baseAmount.bigDecimalValue());
            indDetailsType.setRate(indirectCostDetailBean.rate.bigDecimalValue());
            indDetailsType.setFundsRequested(indirectCostDetailBean.fund.bigDecimalValue());
        }

    }

    private void setProgramIncome(BudgetPeriod budgetPeriod, BudgetPeriodType budgetPeriodType) {
        Budget budget = budgetPeriod.getBudget();
        List<BudgetProjectIncome> programIncomes = budget.getBudgetProjectIncomes();
        KualiDecimal totalProgIncome = KualiDecimal.ZERO;
        for (BudgetProjectIncome budgetProjectIncome : programIncomes) {
            if(budgetProjectIncome.getBudgetPeriodNumber().equals(budgetPeriod.getBudgetPeriod())){
                ProgramIncomeDetails progIncDetailsType = budgetPeriodType.addNewProgramIncomeDetails();
                progIncDetailsType.setAnticipatedAmount(budgetProjectIncome.getProjectIncome().bigDecimalValue());
                totalProgIncome = totalProgIncome.add(budgetProjectIncome.getProjectIncome());
                progIncDetailsType.setSources(budgetProjectIncome.getDescription());
            }
        }
        budgetPeriodType.setProgramIncome(totalProgIncome.bigDecimalValue());
    }
    private ConsortiumCosts getConsortiumCosts(DevelopmentProposal developmentProposal,BudgetPeriod budgetPeriod) {
        ProposalDevelopmentBudgetExt budget = (ProposalDevelopmentBudgetExt)budgetPeriod.getBudget();
        BudgetDecimal consortiumDirectCost = BudgetDecimal.ZERO;
        BudgetDecimal consortiumIndirectCosts = BudgetDecimal.ZERO;
        ConsortiumCosts consortiumCost = ConsortiumCosts.Factory.newInstance();
        if(budget.getModularBudgetFlag()){
            BudgetModular budgetModular = budgetPeriod.getBudgetModular();
            consortiumDirectCost = budgetModular.getConsortiumFna();
        }else{
            boolean isNih = sponsorService.isSponsorNihOsc(developmentProposal) || sponsorService.isSponsorNihMultiplePi(developmentProposal);
            String mappingName = isNih?"NIH_PRINTING":"NSF_PRINTING";

            String fnaGt25KParamValue = getParameterService().getParameterValueAsString(BudgetDocument.class, "SUBCONTRACTOR_F_AND_A_GT_25K");
            String fnaLt25KParamValue = getParameterService().getParameterValueAsString(BudgetDocument.class, "SUBCONTRACTOR_F_AND_A_LT_25K");
            String fnaBroadParamValue = getParameterService().getParameterValueAsString(BudgetDocument.class, "BROAD_F_AND_A");
            Map<String, String> categoryMap = new HashMap<String, String>();
            categoryMap.put(KEY_TARGET_CATEGORY_CODE, "04");
            categoryMap.put(KEY_MAPPING_NAME, mappingName);
            List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);
            for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
                    if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategoryCode()))
                        consortiumDirectCost = consortiumDirectCost.add(lineItem.getLineItemCost());
                    if((lineItem.getCostElement().equals(fnaGt25KParamValue) ||
                            lineItem.getCostElement().equals(fnaLt25KParamValue) ||
                            lineItem.getCostElement().equals(fnaBroadParamValue))) {
                        consortiumIndirectCosts = consortiumIndirectCosts.add(lineItem.getLineItemCost());
                    }
                }
            }	        

        }
        consortiumCost.setDirectCosts(consortiumDirectCost.subtract(consortiumIndirectCosts).bigDecimalValue());
        consortiumCost.setIndirectCosts(consortiumIndirectCosts.bigDecimalValue()); 
        return consortiumCost;
    }

    private void setNonPersonnelLACost(List<BudgetLineItem> budgetLineItems,
            gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod budgetPeriodType) {
        BudgetDecimal amount = BudgetDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            List<BudgetLineItemCalculatedAmount> calcAmounts = budgetLineItem.getBudgetCalculatedAmounts();
            for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : calcAmounts) {
                budgetLineItemCalculatedAmount.refreshNonUpdateableReferences();
                if(budgetLineItemCalculatedAmount.getRateClass().getRateClassType().equals(RateClassType.LAB_ALLOCATION.getRateClassType())){
                    amount = amount.add(budgetLineItemCalculatedAmount.getCalculatedCost());
                }
            }
        } 
        if(amount.isGreaterThan(BudgetDecimal.ZERO)){
            OtherDirectCosts otherDirectCost = budgetPeriodType.addNewOtherDirectCosts();
            otherDirectCost.setCost(amount.bigDecimalValue());
            otherDirectCost.setDescription("LA M&S and Utilities");
            otherDirectCost.setType("Other Direct Costs");
        }
    }

    private void setSalarySubTotals(BudgetPeriod budgetPeriod,
            gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod budgetPeriodType) {
        SalarySubtotals salSubTotals = budgetPeriodType.addNewSalarySubtotals();
        salSubTotals.setFringeBenefits(getTotalFringe(budgetPeriod).bigDecimalValue());
        salSubTotals.setSalaryRequested(getTotalSalaryRequested(budgetPeriod).bigDecimalValue());
        budgetPeriodType.setSalarySubtotals(salSubTotals);

    }
    class OtherPersonInfo{
        BudgetDecimal fund = BudgetDecimal.ZERO;
        int count = 0;
        /**
         * Gets the salary attribute. 
         * @return Returns the salary.
         */
        public BudgetDecimal getFund() {
            return fund;
        }
        /**
         * Sets the salary attribute value.
         * @param salary The salary to set.
         */
        public void setFund(BudgetDecimal fund) {
            this.fund = fund;
        }
        /**
         * Gets the count attribute. 
         * @return Returns the count.
         */
        public int getCount() {
            return count;
        }
        /**
         * Sets the count attribute value.
         * @param count The count to set.
         */
        public void setCount(int count) {
            this.count = count;
        }
    }
    private void setNSFOtherPersonnels(DevelopmentProposal developmentProposal, BudgetPeriod budgetPeriod,
            gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod budgetPeriodType) {
        NSFOtherPersonnelType otherPersonnelType  = budgetPeriodType.addNewNSFOtherPersonnel();
        boolean isNih = sponsorService.isSponsorNihOsc(developmentProposal) || sponsorService.isSponsorNihMultiplePi(developmentProposal);
        String mappingName = isNih?"NIH_PRINTING":"NSF_PRINTING";

        OtherPersonInfo otherPersonInfo = getOtherPersonInfo(developmentProposal,budgetPeriod,mappingName,"01-Secretarial");
        otherPersonnelType.setClericalCount(BigInteger.valueOf(otherPersonInfo.getCount()));
        otherPersonnelType.setClericalFunds(otherPersonInfo.getFund().bigDecimalValue());

        otherPersonInfo = getOtherPersonInfo(developmentProposal,budgetPeriod,mappingName,"01-Graduates");
        otherPersonnelType.setGradCount(BigInteger.valueOf(otherPersonInfo.getCount()));
        otherPersonnelType.setGradFunds(otherPersonInfo.getFund().bigDecimalValue());

        otherPersonInfo = getOtherPersonInfo(developmentProposal,budgetPeriod,mappingName,"01-Other Profs");
        otherPersonnelType.setOtherProfCount(BigInteger.valueOf(otherPersonInfo.getCount()));
        otherPersonnelType.setOtherProfFunds(otherPersonInfo.getFund().bigDecimalValue());

        otherPersonInfo = getOtherPersonInfo(developmentProposal,budgetPeriod,mappingName,"01-Undergrads");
        otherPersonnelType.setUnderGradCount(BigInteger.valueOf(otherPersonInfo.getCount()));
        otherPersonnelType.setUnderGradFunds(otherPersonInfo.getFund().bigDecimalValue());

        otherPersonInfo = getOtherPersonInfo(developmentProposal,budgetPeriod,mappingName,"01-Other");
        otherPersonnelType.setOtherCount(BigInteger.valueOf(otherPersonInfo.getCount()));
        otherPersonnelType.setOtherFunds(otherPersonInfo.getFund().bigDecimalValue());

        otherPersonInfo = getOtherPersonInfo(developmentProposal,budgetPeriod,mappingName,"01-PostDocs");
        otherPersonnelType.setPostDocCount(BigInteger.valueOf(otherPersonInfo.getCount()));
        otherPersonnelType.setPostDocFunds(otherPersonInfo.getFund().bigDecimalValue());

        otherPersonnelType.setOtherLAFunds(getOtherLAFunds(budgetPeriod));

    }

    private BigDecimal getOtherLAFunds(BudgetPeriod budgetPeriod) {
        BudgetDecimal laAmount = BudgetDecimal.ZERO;
        List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            List<BudgetLineItemCalculatedAmount> calcAmounts = budgetLineItem.getBudgetLineItemCalculatedAmounts();
            for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : calcAmounts) {
                budgetLineItemCalculatedAmount.refreshNonUpdateableReferences();
                if(RateClassType.LA_SALARIES.getRateClassType().equals(budgetLineItemCalculatedAmount.getRateClass().getRateClassType())){
                    laAmount = laAmount.add(budgetLineItemCalculatedAmount.getCalculatedCost());
                }
            }
        }
        return laAmount.bigDecimalValue();
    }

    private OtherPersonInfo getOtherPersonInfo(DevelopmentProposal developmentProposal,BudgetPeriod budgetPeriod, String categoryMappingName, String categoryCode) {
        Map<String, String> categoryMap = new HashMap<String, String>();
        categoryMap.put(KEY_TARGET_CATEGORY_CODE, categoryCode);
        categoryMap.put(KEY_MAPPING_NAME, categoryMappingName);
        List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);
        OtherPersonInfo otherPersonInfo = new OtherPersonInfo();
        for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
            boolean lineItemMatchesCategory = false;
            for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
                if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategoryCode())) {
                    lineItemMatchesCategory = true;
                    break;
                }
            }
            if (lineItemMatchesCategory) {
                List<BudgetPersonnelDetails> budgetPersonnelDetailsList = lineItem.getBudgetPersonnelDetailsList();
                if(budgetPersonnelDetailsList.size()>0){
                    for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelDetailsList) {
                        if(!isPersonExistsInProposal(developmentProposal,budgetPersonnelDetails)){
                            otherPersonInfo.setFund(otherPersonInfo.getFund().add(budgetPersonnelDetails.getSalaryRequested()));
                            otherPersonInfo.setCount(otherPersonInfo.getCount()+budgetPersonnelDetails.getQuantity().intValue());
                        }
                    }
                }else{
                    otherPersonInfo.setFund(otherPersonInfo.getFund().add(lineItem.getLineItemCost()));
                    otherPersonInfo.setCount(otherPersonInfo.getCount()+lineItem.getQuantity().intValue());
                }
            }
        }
        return otherPersonInfo;
    }

    private boolean isPersonExistsInProposal(DevelopmentProposal developmentProposal, 
            BudgetPersonnelDetails budgetPersonnelDetails) {
        List<ProposalPerson> proposalPersons = developmentProposal.getProposalPersons();
        for (ProposalPerson proposalPerson : proposalPersons) {
            if(getS2SUtilService().proposalPersonEqualsBudgetPerson(proposalPerson, budgetPersonnelDetails))
                return true;
        }
        return false;
    }

    /*
     * This method gets arrays of SalaryAndWagesType XMLObject
     */
    private void setSalaryAndWages(DevelopmentProposal developmentProposal,Budget budget,
            BudgetPeriod budgetPeriod,
            gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod budgetPeriodType ) {
        List<SalariesAndWagesType> salariesAndWagesTypeList = new ArrayList<SalariesAndWagesType>();
        List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
        BudgetDecimal totalSalary = BudgetDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
                    .getBudgetPersonnelDetailsList()) {
                budgetPersDetails.refreshReferenceObject("budgetPerson");
                BudgetPerson budgetPerson = budgetPersDetails.getBudgetPerson();
                if (budgetPerson != null) {
                    SalariesAndWagesType salariesAndWagesType = getSalariesAndWagesType(
                            developmentProposal,budget,budgetPersDetails, budgetPerson);
                    totalSalary = totalSalary.add(new BudgetDecimal(salariesAndWagesType.getSalaryAndFringeTotal()));
                    salariesAndWagesTypeList.add(salariesAndWagesType);
                }
            }
        }
        SalariesAndWagesType laSalariesAndWagesType = getLAAmmountsAsSalaryRecord(developmentProposal,budget,budgetPeriod);
        if(laSalariesAndWagesType!=null){
            totalSalary = totalSalary.add(new BudgetDecimal(laSalariesAndWagesType.getSalaryAndFringeTotal()));
            salariesAndWagesTypeList.add(laSalariesAndWagesType);
        }
        budgetPeriodType.setSalariesWagesTotal(totalSalary.bigDecimalValue());
        budgetPeriodType.setSalariesAndWagesArray(salariesAndWagesTypeList.toArray(new SalariesAndWagesType[0]));
    }

    private SalariesAndWagesType getLAAmmountsAsSalaryRecord(DevelopmentProposal developmentProposal, Budget budget,
            BudgetPeriod budgetPeriod) {
        List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
        BudgetDecimal salaryRequested = BudgetDecimal.ZERO;
        BudgetDecimal fringe = BudgetDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            List<BudgetLineItemCalculatedAmount> budgetLineItemCalcAmounts = budgetLineItem.getBudgetCalculatedAmounts();
            for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : budgetLineItemCalcAmounts) {
                if(budgetLineItemCalculatedAmount.getRateClass().getRateClassType().equals(RateClassType.LA_SALARIES.getRateClassType())){
                    salaryRequested = salaryRequested.add(budgetLineItemCalculatedAmount.getCalculatedCost());
                }
                if((budgetLineItemCalculatedAmount.getRateClass().getRateClassType().equals(RateClassType.EMPLOYEE_BENEFITS.getRateClassType()) && 
                        budgetLineItemCalculatedAmount.getRateTypeCode().equals("3")) || 
                        (budgetLineItemCalculatedAmount.getRateClass().getRateClassType().equals(RateClassType.VACATION.getRateClassType())) && 
                        budgetLineItemCalculatedAmount.getRateTypeCode().equals("2")){
                    fringe = fringe.add(budgetLineItemCalculatedAmount.getCalculatedCost());
                }
            }
        }
        if(salaryRequested.isZero()){
            return null;
        }
        SalariesAndWagesType salariesAndWagesType = SalariesAndWagesType.Factory.newInstance();
        salariesAndWagesType.setName(getContactPersonFullName("Lab Allocation","",""));
        salariesAndWagesType.setRequestedCost(salaryRequested.bigDecimalValue());
        salariesAndWagesType.setFringeCost(fringe.bigDecimalValue());
        salariesAndWagesType.setSalaryAndFringeTotal(salaryRequested.add(fringe).bigDecimalValue());
        return salariesAndWagesType;
    }

    /*
     * This method computes the salaries and wages details of a BudgetPerson and
     * populates SalariesAndWagesType
     */
    private SalariesAndWagesType getSalariesAndWagesType(DevelopmentProposal developmentProposal,Budget budget,
            BudgetPersonnelDetails budgetPersDetails, BudgetPerson budgetPerson) {
        SalariesAndWagesType salariesAndWagesType = SalariesAndWagesType.Factory
        .newInstance();
        salariesAndWagesType.setAppointmentType(budgetPerson
                .getAppointmentTypeCode() == null ? Constants.EMPTY_STRING
                        : budgetPerson.getAppointmentTypeCode());
        salariesAndWagesType.setAppointmentMonths(new BigDecimal(budgetPerson
                .getAppointmentTypeCode() == null ? Constants.EMPTY_STRING
                        : budgetPerson.getAppointmentTypeCode()));
        salariesAndWagesType.setSummerFundingMonths(calculateFundingMonths(developmentProposal,budgetPersDetails, 
                BUDGET_PERIOD_TYPE_4).bigDecimalValue());
        salariesAndWagesType.setAcademicFundingMonths(calculateFundingMonths(developmentProposal,budgetPersDetails, 
                BUDGET_PERIOD_TYPE_2).bigDecimalValue());
        salariesAndWagesType.setFundingMonths(calculateFundingMonths(developmentProposal,budgetPersDetails, 
                BUDGET_PERIOD_TYPE_3).bigDecimalValue());
        try{
            KcPerson person = budgetPerson.getPerson();
            salariesAndWagesType.setName(getContactPersonFullName(person
                    .getLastName(), person.getFirstName(), person.getMiddleName()));
        }
        catch(IllegalArgumentException ie){
            Rolodex rolodex = budgetPerson.getRolodex();
            if(rolodex != null){
                salariesAndWagesType.setName(getContactPersonFullName(rolodex.getLastName(), 
                        rolodex.getFirstName(), rolodex.getMiddleName()));}
        }
        salariesAndWagesType.setProjectRole(getProjectRoleType(developmentProposal,budgetPerson));
        salariesAndWagesType.setProjectRoleDescription(budgetPerson.getRole());
        salariesAndWagesType.setSalariesTotal(budgetPersDetails
                .getSalaryRequested().bigDecimalValue());
        BigDecimal fringe = getFringeCost(budgetPersDetails).bigDecimalValue();
        salariesAndWagesType.setFringeCost(fringe);
        salariesAndWagesType.setRequestedCost(budgetPersDetails
                .getSalaryRequested().bigDecimalValue());
        salariesAndWagesType.setBaseSalary(budgetPerson.getCalculationBase()
                .bigDecimalValue());
        salariesAndWagesType.setSalaryAndFringeTotal(budgetPersDetails
                .getSalaryRequested().bigDecimalValue().add(fringe));
        return salariesAndWagesType;
    }

    /*
     * This method gets ResearchCoverPage XMLObject and set values to it from
     * developmentProposal
     */
    private ResearchCoverPage getResearchCoverPage(
            DevelopmentProposal developmentProposal, Budget budget)
    throws ParseException {
        ResearchCoverPage researchCoverPage = ResearchCoverPage.Factory
        .newInstance();
        developmentProposal.refreshNonUpdateableReferences();
        researchCoverPage
        .setSubmissionCategory(getSubmissionCategoryForResearchCoverPage(
                developmentProposal.getActivityType().getDescription(),
                developmentProposal.getCreationStatusCode()));
        researchCoverPage
        .setApplicationCategory(getApplicationCategoryForResearchCoverPage(developmentProposal
                .getProposalType().getDescription()));
        setApplicantSubmissionQualifiersForResearchCoverPage(developmentProposal,researchCoverPage.addNewApplicantSubmissionQualifiers());
        setFederalAgencyReceiptQualifiersForResearchCoverPage(developmentProposal,researchCoverPage.addNewFederalAgencyReceiptQualifiers());
        setStateReceiptQualifiersForResearchCoverPage(developmentProposal,researchCoverPage.addNewStateReceiptQualifiers());
        setStateIntergovernmentalReviewForResearchCoverPage(developmentProposal,researchCoverPage.addNewStateIntergovernmentalReview());
        setFederalDebtDelinquencyQuestionForResearchCoverPage(developmentProposal,researchCoverPage.addNewFederalDebtDelinquencyQuestions());
        researchCoverPage.setProjectDates(getProjectDatesForResearchCoverPage(
                developmentProposal.getRequestedStartDateInitial(),
                developmentProposal.getRequestedEndDateInitial()));
        researchCoverPage
        .setBudgetTotals(getBudgetTotalsForResearchCoverPage(budget));
        researchCoverPage
        .setProjectTitle(developmentProposal.getTitle() == null ? DEFAULT_VALUE_UNKNOWN
                : developmentProposal.getTitle());
        researchCoverPage
        .setOtherAgencyQuestions(getOtherAgencyQuestionsForResearchCoverPage(developmentProposal));
        researchCoverPage
        .setApplicantOrganization(getApplicantOrganizationForResearchCoverPage(developmentProposal));
        researchCoverPage
        .setPrimaryProjectSite(getProjectSiteForResearchCoverPage(developmentProposal));
        researchCoverPage
        .setProgramDirectorPrincipalInvestigator(getProgramDirectorPrincipalInvestigatorForResearchCoverPage(developmentProposal));
        researchCoverPage
        .setFundingOpportunityDetails(getFundingOpportunityDetailsForResearchCoverPage(developmentProposal));
        researchCoverPage
        .setAuthorizedOrganizationalRepresentative(getAuthorizedOrganizationalRepresentative(developmentProposal));
        setAlternateProjectSites(developmentProposal,researchCoverPage);

        return researchCoverPage;
    }

    private void setAlternateProjectSites(DevelopmentProposal developmentProposal,ResearchCoverPage researchCoverPage) {
        List<ProposalSite> otherOrganizations = developmentProposal.getOtherOrganizations();
        setPerformanceSites(researchCoverPage, otherOrganizations);
        List<ProposalSite> proposalSites = developmentProposal.getPerformanceSites();
        setPerformanceSites(researchCoverPage, proposalSites);
    }

    /**
     * This method...
     * @param researchCoverPage
     * @param proposalSites
     */
    private void setPerformanceSites(ResearchCoverPage researchCoverPage, List<ProposalSite> proposalSites) {
        for (ProposalSite proposalSite : proposalSites) {
            ProjectSiteType projectSiteType= researchCoverPage.addNewAlternateProjectSites();
            Rolodex rolodexBean = proposalSite.getRolodex();
            if(rolodexBean==null){
                rolodexBean = proposalSite.getOrganization().getRolodex();
            }
            projectSiteType.setOrganizationName(rolodexBean.getOrganization());
            setPostalAddressInfo(rolodexBean,projectSiteType);
            List<CongressionalDistrict> congressionalDistricts = proposalSite.getCongressionalDistricts();
            String congrDistName = null;
            for (CongressionalDistrict congressionalDistrict : congressionalDistricts) {
                congrDistName= congrDistName==null?congressionalDistrict.getCongressionalDistrict():
                    congrDistName+","+congressionalDistrict.getCongressionalDistrict();
            }
            projectSiteType.setCongressionalDistrict(congrDistName);  
        }
    }

    private void setPostalAddressInfo(Rolodex rolodexBean,ProjectSiteType projectSiteType) {
        PostalAddressType postalAddressType = projectSiteType.addNewPostalAddress();
        postalAddressType
        .setCity((rolodexBean.getCity() == null || rolodexBean.getCity().trim().equals("")) ? "Unknown"
                : rolodexBean.getCity());
        postalAddressType.setPostalCode((rolodexBean.getPostalCode() == null || rolodexBean.getPostalCode()
                .trim().equals("")) ? "Unknown" : rolodexBean.getPostalCode());
        postalAddressType.setCountry((rolodexBean.getCountryCode() == null || rolodexBean.getCountryCode()
                .trim().equals("")) ? "Unknown" : rolodexBean.getCountryCode());

        if (rolodexBean.getState() != null)
            postalAddressType.setState(rolodexBean.getState());

        if (rolodexBean.getAddressLine1() != null) {
            XmlToken street = postalAddressType.addNewStreet();
            street.setStringValue(rolodexBean.getAddressLine1());
        }
        if (rolodexBean.getAddressLine2() != null) {
            XmlToken street2 = postalAddressType.addNewStreet();
            street2.setStringValue(rolodexBean.getAddressLine2());
        }
        if (rolodexBean.getAddressLine3() != null) {
            XmlToken street3 = postalAddressType.addNewStreet();
            street3.setStringValue(rolodexBean.getAddressLine3());
        }
    }

    /*
     * This method gets AuthorizedOrganizationalRepresentativeType XMLObject and
     * set data to it from authorisedOraganization
     */
    private AuthorizedOrganizationalRepresentativeType getAuthorizedOrganizationalRepresentative(
            DevelopmentProposal developmentProposal) {
        Organization authorisedOrg = getAuthorisedOrganization(developmentProposal);
        Rolodex rolodex = authorisedOrg.getRolodex();
        AuthorizedOrganizationalRepresentativeType authOrgRepType = AuthorizedOrganizationalRepresentativeType.Factory
        .newInstance();
        if (authorisedOrg != null) {
            authOrgRepType.setPositionTitle(rolodex.getTitle());
            authOrgRepType
            .setContactInformation(getPersonContactInformation(rolodex));
            authOrgRepType.setName(getContactPersonFullName(rolodex
                    .getLastName(), rolodex.getFirstName(), rolodex
                    .getMiddleName()));
        } else {
            authOrgRepType.setName(getContactPersonFullName(
                    DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN,
                    DEFAULT_VALUE_UNKNOWN));
            authOrgRepType.setPositionTitle(DEFAULT_VALUE_UNKNOWN);
            authOrgRepType
            .setContactInformation(getOrganizationPersonContactInformationWithDefaultValues());
        }
        return authOrgRepType;
    }

    /*
     * This method gets Authorized Organization from proposalSites by checking
     * locationTypeCode value 1 from list of proposalSites
     */
    private Organization getAuthorisedOrganization(
            DevelopmentProposal developmentProposal) {
        Organization authorisedOrg = null;
        for (ProposalSite proposalSite : developmentProposal.getProposalSites()) {
            if (proposalSite.getLocationTypeCode() == 1) {
                authorisedOrg = proposalSite.getOrganization();
            }
        }
        return authorisedOrg;
    }

    /*
     * This method gets FundingOpportunityDetailsType XMLObject and set
     * programAnnouncementTitle and Number to it
     */
    private FundingOpportunityDetailsType getFundingOpportunityDetailsForResearchCoverPage(
            DevelopmentProposal developmentProposal) {
        FundingOpportunityDetailsType fundingOpportunityType = FundingOpportunityDetailsType.Factory.newInstance();
        String programAnnouncementNumber = developmentProposal.getProgramAnnouncementNumber();
        String programAnnouncementTitle = developmentProposal.getProgramAnnouncementTitle();
        fundingOpportunityType.setFundingOpportunityNumber(programAnnouncementNumber == null ? EMPTY_STRING
                : programAnnouncementNumber);
        fundingOpportunityType.setFundingOpportunityTitle(programAnnouncementTitle == null ? EMPTY_STRING
                : programAnnouncementTitle);
        fundingOpportunityType.setFundingOpportunityResponseCode(developmentProposal.getS2sOpportunity()!=null);
        return fundingOpportunityType;
    }

    /*
     * This method gets ProgramDirectorPrincipalInvestigator XMLObject and set
     * Principal Investigator data to it
     */
    private ProgramDirectorPrincipalInvestigator getProgramDirectorPrincipalInvestigatorForResearchCoverPage(
            DevelopmentProposal developmentProposal) {
        ProgramDirectorPrincipalInvestigator principalInvestigatorType = ProgramDirectorPrincipalInvestigator.Factory.newInstance();
        ProposalPerson principalInvestigator = PrintingUtils.getPrincipalInvestigator(developmentProposal.getProposalPersons());
        if (principalInvestigator != null) {
            principalInvestigatorType.setContactInformation(getPersonContactInformation(principalInvestigator));
            principalInvestigatorType.setName(getContactPersonFullName(principalInvestigator.getLastName(), principalInvestigator
                    .getFirstName(), principalInvestigator.getMiddleName()));


            if (principalInvestigator.getEraCommonsUserName() == null) {
                principalInvestigatorType.setAccountIdentifier("Unknown");
            }
            else {
                principalInvestigatorType.setAccountIdentifier(principalInvestigator.getEraCommonsUserName());
            }
            principalInvestigatorType.setNewInvestigatorQuestion(getNewInvestQuestion(developmentProposal));
            setDegree(principalInvestigator,principalInvestigatorType);
            SignatureType signatureType = principalInvestigatorType.addNewDirectorInvestigatorSignature();
            signatureType.setSignatureAuthentication("unknown");
            signatureType.setSignatureDate(getDateTimeService().getCurrentCalendar());
        }
        return principalInvestigatorType;
    }

    private void setDegree(ProposalPerson principalInvestigator, ProgramDirectorPrincipalInvestigator principalInvestigatorType) {
        List<ProposalPersonDegree> proposalPersonDegrees = principalInvestigator.getProposalPersonDegrees();
        for (ProposalPersonDegree proposalPersonDegree : proposalPersonDegrees) {
            principalInvestigatorType.addDegree(proposalPersonDegree.getDegree());
        }
    }

    private boolean getNewInvestQuestion(DevelopmentProposal developmentProposal) {
        List<ProposalYnq> vecYNQQuestions = developmentProposal.getProposalYnqs();
        for (ProposalYnq proposalYnq : vecYNQQuestions) {
            if (proposalYnq.getQuestionId().equals("13") &&  (proposalYnq.getAnswer()!=null && 
                    proposalYnq.getAnswer().equals("Y"))) {
                return true;
            }   

        }
        return false;
    }

    /*
     * This method gets ApplicantOrganizationType XMLObject for
     * ResearchCoverPage and set the data from organization to it if data is
     * there else put default data
     */
    private ApplicantOrganizationType getApplicantOrganizationForResearchCoverPage(
            DevelopmentProposal developmentProposal) {
        Organization organization = developmentProposal
        .getApplicantOrganization().getOrganization();
        OrganizationType organizationType = organization.getOrganizationType(0);
        ApplicantOrganizationType applicantOrganizationType = ApplicantOrganizationType.Factory
        .newInstance();
        applicantOrganizationType.setOrganizationName(organization
                .getOrganizationName() == null ? DEFAULT_VALUE_UNKNOWN
                        : organization.getOrganizationName());
        applicantOrganizationType.setOrganizationDUNS(organization
                .getDunsNumber() == null ? DEFAULT_VALUE_UNKNOWN : organization
                        .getDunsNumber());
        applicantOrganizationType.setOrganizationEIN(organization
                .getFedralEmployerId() == null ? DEFAULT_VALUE_UNKNOWN
                        : ("1"+organization.getFedralEmployerId())+"A1");
        if (organization.getPhsAccount() != null) {
            applicantOrganizationType.setPHSAccountID(organization
                    .getPhsAccount());
        }
        applicantOrganizationType
        .setOrganizationCategoryCode(organizationType == null ? DEFAULT_VALUE_UNKNOWN
                : organizationType.getOrganizationTypeCode().toString());
        applicantOrganizationType
        .setOrganizationCategoryDescription(organizationType == null ? DEFAULT_VALUE_UNKNOWN
                : organizationType.getOrganizationTypeList()
                .getDescription());
        applicantOrganizationType
        .setOrganizationCongressionalDistrict(organization
                .getCongressionalDistrict() == null ? DEFAULT_VALUE_UNKNOWN
                        : organization.getCongressionalDistrict());
        applicantOrganizationType
        .setOrganizationAddress(getOrganizationAddress(organization
                .getRolodex()));
        applicantOrganizationType
        .setOrganizationContactPerson(getOrganizationContactPerson(developmentProposal
                .getApplicantOrganization().getRolodex()));
        String cageNumber = organization.getCageNumber();
        if (cageNumber != null) {
            applicantOrganizationType.setCageNumber(cageNumber);
        }
        OrganizationClassification orgClassification = applicantOrganizationType.addNewOrganizationClassification();
        List<OrganizationType> organizationTypes = organization.getOrganizationTypes();
        if(!organizationTypes.isEmpty()){
            orgClassification.setCategoryCode(organizationTypes.get(0).getOrganizationTypeCode().toString());
            orgClassification.setSubCategoryCode(organizationTypes.get(0).getOrganizationTypeList().getDescription());
        }
        return applicantOrganizationType;
    }

    /*
     * This method will return the unit name from the unit which is lead unit.
     */
    private String getUnitName(ProposalPerson proposalPerson) {
        String unitName = null;
        for (ProposalPersonUnit proposalPersonUnit : proposalPerson.getUnits()) {
            if (proposalPersonUnit.isLeadUnit()) {
                Unit unit = proposalPersonUnit.getUnit();
                if (unit != null && unit.getUnitName() != null) {
                    unitName = unit.getUnitName();
                }
                break;
            }
        }
        return unitName;
    }

    /*
     * This method will set values to the values to key person flag attributes
     * KeyPersonFlagCode,KeyPersonFlagDesc.
     */
    private KeyPersonFlag getKeyPersonFlag(ProposalPerson proposalPerson) {
        KeyPersonFlag keyPersonFlag = KeyPersonFlag.Factory.newInstance();
        //		if (proposalPerson.getPercentageEffort() != null
        //				&& proposalPerson.getPercentageEffort().intValue() != 999) {
        keyPersonFlag
        .setKeyPersonFlagCode(DEFAULT_VALUE_KEY_PERSON_FLAG_CODE);
        keyPersonFlag
        .setKeyPersonFlagDesc(KEY_PERSON_FLAG_DESCRIPTION_VALUE_KEY_PERSON);
        //		} else {
        //			keyPersonFlag
        //					.setKeyPersonFlagCode(KEY_PERSON_FLAG_CODE_VALUE_FALSE);
        //			keyPersonFlag
        //					.setKeyPersonFlagDesc(KEY_PERSON_FLAG_DESCRIPTION_VALUE_COLLABORATOR);
        //		}
        return keyPersonFlag;
    }

    /*
     * This method will set the values to contact info type attributes like
     * email , fax number,phone number , postal address.
     */
    private ContactInfoType getContactInfoType(ProposalPerson proposalPerson) {
        ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
        contactInfoType.setEmail(proposalPerson.getEmailAddress());
        contactInfoType.setFaxNumber(proposalPerson.getFaxNumber());
        contactInfoType.setPhoneNumber(proposalPerson.getPhoneNumber());
        PostalAddressType postalAddressType = getPostalAddressType(proposalPerson);
        contactInfoType.setPostalAddress(postalAddressType);
        return contactInfoType;
    }

    /*
     * This method will set the values to PostalAddressType attributes like
     * city,country, postal code , and state.
     */
    private PostalAddressType getPostalAddressType(ProposalPerson proposalPerson) {
        PostalAddressType postalAddressType = PostalAddressType.Factory
        .newInstance();
        postalAddressType.setCity(proposalPerson.getCity());
        postalAddressType.setCountry(proposalPerson.getCounty());
        postalAddressType.setPostalCode(proposalPerson.getPostalCode());
        postalAddressType.setState(proposalPerson.getState());
        return postalAddressType;
    }

    /*
     * This method will set the values to person full name type attributes like
     * first name,last name, and middle name.
     */
    private PersonFullNameType getPersonFullName(ProposalPerson proposalPerson) {
        PersonFullNameType personFullNameType = PersonFullNameType.Factory
        .newInstance();
        personFullNameType.setFirstName(proposalPerson.getFirstName());
        personFullNameType.setLastName(proposalPerson.getLastName());
        personFullNameType.setMiddleName(proposalPerson.getMiddleName());
        return personFullNameType;
    }

    /*
     * This method will set the values to OrgAssurancesType xml object
     * attributes like
     * LobbyingQuestion,GeneralCertificationQuestion,DebarmentAndSuspension and
     * DrugFreeWorkplace.
     */
    private OrgAssurancesType getOrgAssurances(
            DevelopmentProposal developmentProposal) {
        OrgAssurancesType orgAssurancesType = OrgAssurancesType.Factory
        .newInstance();
        List<OrganizationYnq> organizationYnqs = null;
        Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
        if (organization != null && organization.getOrganizationId() != null) {
            organizationYnqs = getOrganizationYNQ(organization
                    .getOrganizationId());
        }
        if (organizationYnqs != null) {
            for (OrganizationYnq organizationYnq : organizationYnqs) {
                if (organizationYnq.getQuestionId()
                        .equals(LOBBYING_QUESTION_ID)) {
                    orgAssurancesType
                    .setLobbyingQuestion(getAnswerFromOrganizationYnq(organizationYnq));
                }
                if (organizationYnq.getQuestionId().equals(
                        GENERAL_CERTIFICATION_QUESTION_ID)) {
                    orgAssurancesType
                    .setGeneralCertificationQuestion(getAnswerFromOrganizationYnq(organizationYnq));
                }
            }
            orgAssurancesType.setDebarmentAndSuspension(getAssuranceType(
                    organizationYnqs, ORGANIZATION_QUESTION_ID_I8));
            orgAssurancesType.setDrugFreeWorkplace(getAssuranceType(
                    organizationYnqs, ORGANIZATION_QUESTION_ID_H5));
        }
        return orgAssurancesType;
    }

    /*
     * This method will set the values to the Assurance type attributes if
     * question id I8 found in organization YNQs.
     */
    private AssuranceType getAssuranceType(
            List<OrganizationYnq> organizationYnqs, String questionId) {
        AssuranceType assuranceType = AssuranceType.Factory.newInstance();
        for (OrganizationYnq organizationYnq : organizationYnqs) {
            if (organizationYnq.getQuestionId().equals(questionId)) {
                assuranceType
                .setYesNoAnswer(getAnswerFromOrganizationYnq(organizationYnq));
                assuranceType
                .setExplanation(getExplanationFromOrganizationYnq(organizationYnq));
            }
        }
        return assuranceType;
    }

    /*
     * This method return explanation if explanation found for this question
     * otherwise empty string
     */
    private String getExplanationFromOrganizationYnq(
            OrganizationYnq organizationYnq) {
        return organizationYnq.getExplanation() == null ? EMPTY_STRING
                : organizationYnq.getExplanation();
    }

    /*
     * This method will set the values to project description attributes like
     * human subject type,animal subject , and project survey.
     * 
     */
    private ProjectDescription getProjectDescription(
            DevelopmentProposal developmentProposal) {
        ProjectDescription projectDescription = ProjectDescription.Factory
        .newInstance();
        projectDescription
        .setHumanSubject(getHumanSubjectsType(developmentProposal));
        projectDescription
        .setAnimalSubject(getAnimalSubject(developmentProposal));
        projectDescription
        .setProjectSurvey(getProjectSurvey(developmentProposal));
        projectDescription.setProjectSummary(getDescriptionBlockType(
                developmentProposal, PROJECT_SUMMARY_BLOCK_TYPE, true));
        projectDescription.setFacilitiesDescription(getDescriptionBlockType(
                developmentProposal, FACILITIES_BLOCK_TYPE, false));
        projectDescription.setEquipmentDescription(getDescriptionBlockType(
                developmentProposal, EQUIPMENT_BLOCK_TYPE, false));
        projectDescription.setReferences(getDescriptionBlockType(
                developmentProposal, REFERENCES_BLOCK_TYPE, true));
        return projectDescription;
    }

    /*
     * This method will get the DescriptionBlockType based on the block type
     * (summary,facilities,equipment, and references).
     */
    private DescriptionBlockType getDescriptionBlockType(
            DevelopmentProposal developmentProposal, String blockType,
            boolean isFile) {
        DescriptionBlockType descriptionBlockType = DescriptionBlockType.Factory
        .newInstance();
        if (isFile) {
            descriptionBlockType.setFileIdentifier(new StringBuilder(
                    developmentProposal.getProposalNumber()).append(blockType)
                    .toString());
        } else {
            descriptionBlockType.setText(blockType);
        }
        return descriptionBlockType;
    }

    /*
     * This method will set the the values to human subject like IRB approval
     * date ,exemption number,human assurance number.
     */
    private HumanSubjectsType getHumanSubjectsType(
            DevelopmentProposal developmentProposal) {
        HumanSubjectsType humanSubjectsType = HumanSubjectsType.Factory
        .newInstance();
        // exemptionNumber maximum it occurs 6 times no string array size
        // declared as 6
        String exemptionNumber[] = new String[6];
        List<ProposalSpecialReview> specialReviewList = developmentProposal
        .getPropSpecialReviews();
        int arrayIndex = 0;
        if (specialReviewList != null) {
            for (ProposalSpecialReview proposalSpecialReview : specialReviewList) {
                boolean humanSubjectsUsedQuestion = getHumanSubjectsUsedQuestion(proposalSpecialReview);
                humanSubjectsType
                .setHumanSubjectsUsedQuestion(humanSubjectsUsedQuestion);
                if (!proposalSpecialReview.getApprovalTypeCode().equals(
                        APPROVAL_TYPE_EXEMPT)
                        && proposalSpecialReview.getApprovalDate() != null) {
                    humanSubjectsType.setIRBApprovalDate(getDateTimeService()
                            .getCalendar(proposalSpecialReview
                                    .getApprovalDate()));
                    break;
                } else {
                    String comments = getSpecialReviewComments(proposalSpecialReview);
                    exemptionNumber[arrayIndex] = comments;
                    arrayIndex++;
                }
            }
        }
        setEXemptionNumber(developmentProposal, humanSubjectsType,
                exemptionNumber);
        setPhase3ClinicalTrialQuestion(developmentProposal,humanSubjectsType);
        return humanSubjectsType;
    }

    private void setPhase3ClinicalTrialQuestion(DevelopmentProposal developmentProposal, HumanSubjectsType humanSubjectsType) {
        humanSubjectsType.setPhase3ClinicalTrialQuestion(false);
        String strAnswer = getClinicalTrialQuestion(developmentProposal);
        if(strAnswer!=null){
            humanSubjectsType.setPhase3ClinicalTrialQuestion(strAnswer.equals("Y"));
        }
    }
    private String getClinicalTrialQuestion(DevelopmentProposal developmentProposal) {
        String clinicalAnswer = "N";
        for (ProposalYnq proposalYnq : developmentProposal.getProposalYnqs()) {
            if (proposalYnq.getQuestionId() != null
                    && proposalYnq.getQuestionId().equals(
                            PROPOSAL_YNQ_QUESTION_17)) {
                clinicalAnswer = proposalYnq.getAnswer();
            }
        }
        return clinicalAnswer==null?"N":clinicalAnswer;
    }

    /*
     * This method will return true if special review code 1 found in proposal
     * special review otherwise false.
     */
    private boolean getHumanSubjectsUsedQuestion(
            ProposalSpecialReview proposalSpecialReview) {
        boolean humanSubjectsUsedQuestion = false;
        if (proposalSpecialReview.getSpecialReviewTypeCode() != null
                && proposalSpecialReview.getSpecialReviewTypeCode().equals(
                        SPECIAL_REVIEW_CODE_1)) {
            humanSubjectsUsedQuestion = true;
        }
        return humanSubjectsUsedQuestion;
    }

    /*
     * This method will set the value to ExemptionNumberArray element in
     * HumanSubjectsType type if HumanSubjectsUsedQuestion is not true ,if it is
     * true then set value to AssuranceNumber.
     */
    private void setEXemptionNumber(DevelopmentProposal developmentProposal,
            HumanSubjectsType humanSubjectsType, String[] exemptionNumber) {
        if (humanSubjectsType.getHumanSubjectsUsedQuestion() == true) {
            String humanSubAssurance = getHumanAssuranceNumber(developmentProposal);
            if (humanSubAssurance != null) {
                humanSubjectsType.setAssuranceNumber(humanSubAssurance);
            }
        } else {
            humanSubjectsType.setExemptionNumberArray(exemptionNumber);
        }
    }

    /*
     * This method will get the special review comments if comments found else
     * default values 'Unknown' will be returned.
     */
    private String getSpecialReviewComments(
            ProposalSpecialReview proposalSpecialReview) {
        String comments = null;
        if (proposalSpecialReview.getComments() == null) {
            comments = DEFAULT_VALUE_UNKNOWN;
        } else {
            comments = proposalSpecialReview.getComments();
        }
        return comments;
    }

    /*
     * This method will get the human assurance number from the organization
     */
    private String getHumanAssuranceNumber(
            DevelopmentProposal developmentProposal) {
        String humanSubAssurance = null;
        Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
        if (organization != null && organization.getHumanSubAssurance() != null) {
            humanSubAssurance = organization.getHumanSubAssurance();
        }
        return humanSubAssurance;
    }

    private BudgetDecimal calculateFundingMonths(DevelopmentProposal developmentProposal,
            BudgetPersonnelDetails budgetPersonnelDetails, String budgetPeriodType) {
        BudgetDecimal fundingMonths = BudgetDecimal.ZERO;
        if (isPersonExistsInProposal(developmentProposal,budgetPersonnelDetails)
                && budgetPeriodType.equals(budgetPersonnelDetails.getPeriodTypeCode())) {
            if (budgetPersonnelDetails != null) {
                BigDecimal totalMonths = getMonthsBetweenDates(
                        budgetPersonnelDetails.getStartDate(),
                        budgetPersonnelDetails.getEndDate());
                fundingMonths = budgetPersonnelDetails.getPercentEffort().multiply(
                        new BudgetDecimal(totalMonths));
                fundingMonths = fundingMonths.divide(new BudgetDecimal(100));
            }
        }
        return fundingMonths.setScale(0);
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * Gets the sponsorService attribute. 
     * @return Returns the sponsorService.
     */
    public SponsorService getSponsorService() {
        return sponsorService;
    }

    /**
     * Sets the sponsorService attribute value.
     * @param sponsorService The sponsorService to set.
     */
    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }
}