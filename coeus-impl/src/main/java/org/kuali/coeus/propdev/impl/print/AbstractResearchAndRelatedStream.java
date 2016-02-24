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
package org.kuali.coeus.propdev.impl.print;

import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.ContactInfoType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.PostalAddressType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.AnimalSubjectDocument.AnimalSubject;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ApplicantOrganizationType.OrganizationContactPerson;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.*;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.EquipmentCostsDocument.EquipmentCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectCostsDocument.OtherDirectCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantPatientCostsDocument.ParticipantPatientCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSurveyDocument.ProjectSurvey;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelCostsDocument.TravelCosts;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationYnq;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryMap;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryMapping;
import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public abstract class AbstractResearchAndRelatedStream extends ProposalBaseStream {
    private static final Log LOG = LogFactory.getLog(AbstractResearchAndRelatedStream.class);

    protected static final String EMPTY_STRING = " ";
    protected static final String REPORT_NAME = "Research and Related";
    protected static final String SPECIAL_REVIEW_CODE_2 = "2";
    protected static final String IACU_APPROVAL_PENDING_VALUE = "Pending";
    protected static final String BUDGET_JUSTIFICATION_IDENTIFIER = "budgetJust";
    private static final String CATEGORY_CODE_EQUIPMENT_RENTAL = "13";
    private static final String CATEGORY_CODE_EQUIPMENT = "20";
    private static final String CATEGORY_CODE_TRAVEL_FOREIGN = "23";
    private static final String CATEGORY_CODE_TRAVEL_DOMESTIC = "7";
    private static final String CATEGORY_CODE_PARTICIPANT_TRAVEL = "31";
    private static final String CATEGORY_CODE_PARTICIPANT_SUBSISTANCE = "36";
    private static final String CATEGORY_CODE_PARTICIPANT_STIPENDS = "32";
    private static final String CATEGORY_CODE_PARTICIPANT_OTHER = "2";
    private static final String CATEGORY_CODE_PARTICIPANT_TUITION = "35"; 
    private static final String CATEGORY_CODE_OUTPATIENT = "33";
    private static final String CATEGORY_CODE_INPATIENT = "9";
    protected static final String DEFAULT_VALUE_UNKNOWN = "Unknown";
    private static final String PROPOSALQUESTION_ID15 = "15";
    private static final String ANSWER_INDICATOR_VALUE = "Y";
    private static final String DEFAULT_ANSWER = "This question has not been answered";
    private static final String STEMCELL = "18";
    private static final String INTERNATIONAL_ACTIVITIES = "H1";
    private static final String PROPRIETARY_INFO = "G8";
    private static final String HISTORICAL_SITES = "G6";
    private static final String GENETICALLY_ENGINEERED = "G4";
    private static final String HAZARDOUS_MATERIALS = "G3";
    private static final String NSFSMALL_GRANT = "14";
    private static final String NSF_BEGINNING_INV = "12";
    private static final String LOBBYING_ACTIVITIES = "H4";
    private static final String ANSWER_PARAMETER = "answer";
    private static final String QUESTION_ID_PARAMETER = "questionId";
    private static final String ORGANIZATION_ID_PARAMETER = "organizationId";

    public static final String KEY_MAPPING_NAME = "mappingName";
    public static final String KEY_TARGET_CATEGORY_CODE = "targetCategoryCode";
    private static final String RATE_CLASS_TYPE_EMPLOYEE_BENEFITS = "E";
    private static final String RATE_CLASS_TYPE_VACATION = "V";
    private static final String RATE_TYPE_ADMINISTRATIVE_SALARIES = "2";
    private static final String RATE_TYPE_SUPPORT_STAFF_SALARIES = "3";
    private static final String PERIOD_TYPE_ACADEMIC_MONTHS = "2";
    private static final String PERIOD_TYPE_SUMMER_MONTHS = "4";
    private static final String SENIOR_PERSONNEL_CATEGORY_CODE = "1";    
    private static final String KEYPERSON_OTHER = "Other (Specify)";
    private static final String APPOINTMENT_TYPE_SUM_EMPLOYEE = "SUM EMPLOYEE";
    private static final String APPOINTMENT_TYPE_TMP_EMPLOYEE = "TMP EMPLOYEE";
    public static final String VALUE_UNKNOWN = "Unknown";
    public static final String BUDGET_PERSON = "budgetPerson";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("budgetPersonService")
    private BudgetPersonService budgetPersonService;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    /*
     * This method will set the values to animal subject attributes like
     * AssuranceNumber,VertebrateAnimalsUsedQuestion,IACUCApprovalDate and IACUCApprovalPending.
     */
    protected AnimalSubject getAnimalSubject(DevelopmentProposal developmentProposal) {
        AnimalSubject animalSubject = AnimalSubject.Factory.newInstance();
        List<ProposalSpecialReview> specialReviewList = developmentProposal.getPropSpecialReviews();
        if (specialReviewList != null) {
            for (ProposalSpecialReview proposalSpecialReview : specialReviewList) {
                if (proposalSpecialReview.getSpecialReviewTypeCode() != null
                        && proposalSpecialReview.getSpecialReviewTypeCode().equals(SPECIAL_REVIEW_CODE_2)) {
                    animalSubject.setVertebrateAnimalsUsedQuestion(true);
                    String animalWelfareAssurance = getAnimalWelfareAssuranceNumber(developmentProposal);
                    if (animalWelfareAssurance != null) {
                        animalSubject.setAssuranceNumber(animalWelfareAssurance);
                    }
                    if (proposalSpecialReview.getApplicationDate() != null) {
                        animalSubject.setIACUCApprovalDate(getDateTimeService().getCalendar(proposalSpecialReview.getApplicationDate()));
                    }
                    else {
                        animalSubject.setIACUCApprovalPending(IACU_APPROVAL_PENDING_VALUE);
                    }
                    break;
                }
            }
        }
        return animalSubject;
    }

    /*
     * This method will get the animal welfare assurance number from the organization
     */
    protected String getAnimalWelfareAssuranceNumber(DevelopmentProposal developmentProposal) {
        String animalWelfareAssurance = null;
        Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
        if (organization != null && organization.getAnimalWelfareAssurance() != null) {
            animalWelfareAssurance = organization.getAnimalWelfareAssurance();
        }
        return animalWelfareAssurance;
    }

    /*
     * This method will get the Organization from the Development proposal.
     */
    protected Organization getOrganizationFromDevelopmentProposal(DevelopmentProposal developmentProposal) {
        Organization organization = null;
        ProposalSite proposalSite = developmentProposal.getApplicantOrganization();
        if (proposalSite != null) {
            organization = proposalSite.getOrganization();
        }
        return organization;
    }

    /*
     * This method gets CoreSubmissionCategoryType XMLObject and setting data to CoreSubmissionCategoryType object from
     * developmentProposal activityDescription and creationStatusCode data
     */
    protected CoreSubmissionCategoryType getSubmissionCategoryForResearchCoverPage(String activityDescription,
            String creationStatusCode) {
        CoreSubmissionCategoryType coreSubmissionCategoryType = CoreSubmissionCategoryType.Factory.newInstance();
        coreSubmissionCategoryType.setProjectCategory(activityDescription == null ? EMPTY_STRING : activityDescription);

        coreSubmissionCategoryType.setSubmissionStatus(creationStatusCode == null ? EMPTY_STRING : creationStatusCode);
        return coreSubmissionCategoryType;
    }

    /*
     * This method gets BudgetTotalType XMLObject by setting data from totalCost and costSharingAmount to BudgetTotalType for
     * budgetPeriod and budget
     */
    protected BudgetTotalsType getBudgetTotals(ScaleTwoDecimal totalCost, ScaleTwoDecimal costSharingAmount) {
        BudgetTotalsType budgetTotalType = BudgetTotalsType.Factory.newInstance();
        budgetTotalType.setFederalCost(totalCost.bigDecimalValue());
        budgetTotalType.setApplicantCost(costSharingAmount.bigDecimalValue());
        budgetTotalType.setStateCost(new BigDecimal(0));
        budgetTotalType.setLocalCost(new BigDecimal(0));
        budgetTotalType.setOtherCost(new BigDecimal(0));
        budgetTotalType.setProgramIncome(new BigDecimal(0));
        return budgetTotalType;
    }

    /*
     * This method gets DescriptionBlockType XMLObject. In the DescriptionBlockType object only FileIdentifier fields sets by
     * concatenating proposal number and budget Just
     */
    protected DescriptionBlockType getBudgetJustification(String proposalNumber) {
        DescriptionBlockType descBlockType = DescriptionBlockType.Factory.newInstance();
        descBlockType.setFileIdentifier(new StringBuilder(proposalNumber).append(BUDGET_JUSTIFICATION_IDENTIFIER).toString());
        return descBlockType;
    }

    /*
     * This method gets arrays of otherDirectCost XMLObject from List of BudgetLineItems by checking the budgetCategoryCode as Other
     */
    protected OtherDirectCosts[] getOtherDirectCosts(DevelopmentProposal developmentProposal,List<BudgetLineItem> budgetLineItems) {
        List<OtherDirectCosts> otherDirectCostList = new ArrayList<OtherDirectCosts>();
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryOther(budgetLineItem)) {
                OtherDirectCosts otherDirectCost = OtherDirectCosts.Factory.newInstance();
                otherDirectCost.setCost(budgetLineItem.getLineItemCost().bigDecimalValue());
                otherDirectCost.setDescription(budgetLineItem.getLineItemDescription());
                otherDirectCost.setType(getOtherCategoryMapTypeDescription(developmentProposal,budgetLineItem));// budgetLineItem.getBudgetCategory().getDescription()));
                otherDirectCostList.add(otherDirectCost);
            }
        }
        return otherDirectCostList.toArray(new OtherDirectCosts[0]);
    }

    private String getOtherCategoryMapTypeDescription(DevelopmentProposal developmentProposal, BudgetLineItem budgetLineItem) {
        BudgetCategoryMap budgetCategoryMap = getBudgetCategoryMap(developmentProposal, budgetLineItem);
        if(budgetCategoryMap!=null){
            return budgetCategoryMap.getDescription();
        }else{
            return "Other Direct Costs"; 
        }
    }


    private BudgetCategoryMap getBudgetCategoryMap(DevelopmentProposal developmentProposal, BudgetLineItem budgetLineItem) {
        boolean isNih = getSponsorHierarchyService().isSponsorNihOsc(developmentProposal.getSponsorCode())
                                || getSponsorHierarchyService().isSponsorNihMultiplePi(developmentProposal.getSponsorCode());
        String mappingName = isNih?"NIH_PRINTING":"NSF_PRINTING";
        BudgetCategoryMap budgetCategoryMap = null;
        Map<String, String> categoryMap = new HashMap<String, String>();
        categoryMap.put("budgetCategoryCode", budgetLineItem.getBudgetCategoryCode());
        categoryMap.put(KEY_MAPPING_NAME, mappingName);
        List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);
        if(!budgetCategoryList.isEmpty()){
            BudgetCategoryMapping budgetCategoryMapping = budgetCategoryList.get(0);
            categoryMap = new HashMap<String, String>();
            categoryMap.put(KEY_MAPPING_NAME, mappingName);
            categoryMap.put("targetCategoryCode", budgetCategoryMapping.getTargetCategoryCode());
            List<BudgetCategoryMap> budgetCategoryMaps = (List)getBusinessObjectService().findMatching(BudgetCategoryMap.class, categoryMap);
            if(!budgetCategoryMaps.isEmpty()){
                budgetCategoryMap =  budgetCategoryMaps.get(0);
            }
        }
        return budgetCategoryMap;
    }

    /*
     * This method check budgetCagegoryCode for other in BudgetLineItem by checking budgetCategoryCode travel, equipment, patient
     * and participant
     */
    protected boolean isBudgetCategoryOther(BudgetLineItem budgetLineItem) {
        boolean isOther = true;
        if (isBudgetCategoryEquipment(budgetLineItem) || isBudgetCategoryTravel(budgetLineItem)
                || isBudgetCategoryParticipantPatient(budgetLineItem) || 
                budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equals("P") || budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_PARTICIPANT_TUITION) ) {
            isOther = false;
        }
        return isOther;
    }

    /*
     * This method check budgetCagegoryCode for equipment in BudgetLineItem
     */
    protected boolean isBudgetCategoryEquipment(BudgetLineItem budgetLineItem) {
        return budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_EQUIPMENT_RENTAL)
                || budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_EQUIPMENT);
    }

    /*
     * This method gets sum of lineItemCost as travel total cost from List of BudgetLineItem, if budgetCategoryCode is travel for
     * budgetLineItem
     */
    protected BigDecimal getTravelTotal(List<BudgetLineItem> budgetLineItems) {
        ScaleTwoDecimal cost = ScaleTwoDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryTravel(budgetLineItem)) {
                cost = cost.add(budgetLineItem.getLineItemCost());
            }
        }
        return cost.bigDecimalValue();
    }

    /*
     * This method check budgetCagegoryCode for travel in BudgetLineItem
     */
    protected boolean isBudgetCategoryTravel(BudgetLineItem budgetLineItem) {
        return budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_TRAVEL_DOMESTIC)
                || budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_TRAVEL_FOREIGN);
    }

    /*
     * This method check budgetCagegoryCode for Participant and Patient in BudgetLineItem
     */
    protected boolean isBudgetCategoryParticipantPatient(BudgetLineItem budgetLineItem) {
        return 
       budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_PARTICIPANT_OTHER)||
               budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_PARTICIPANT_STIPENDS)  ||
                budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_PARTICIPANT_SUBSISTANCE) ||
               budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_PARTICIPANT_TRAVEL) ||
                budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_INPATIENT)
                || budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_OUTPATIENT)||
                budgetLineItem.getBudgetCategoryCode().equals(CATEGORY_CODE_PARTICIPANT_TUITION);
    }

    /*
     * This method gets arrays of EquipmentCost XMLObject from list of budgetLineItems by checking the budgetCategory as equipment
     */
    protected EquipmentCosts[] getEquipmentCosts(List<BudgetLineItem> budgetLineItems) {
        List<EquipmentCosts> equipmentCostList = new ArrayList<EquipmentCosts>();
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryEquipment(budgetLineItem)) {
                EquipmentCosts equipmentCost = EquipmentCosts.Factory.newInstance();
                equipmentCost.setCost(budgetLineItem.getLineItemCost().bigDecimalValue());
                equipmentCost.setDescription(budgetLineItem.getLineItemDescription());
                equipmentCost.setEquipmentDescription(budgetLineItem.getBudgetCategory().getDescription());
                equipmentCostList.add(equipmentCost);
            }
        }
        return equipmentCostList.toArray(new EquipmentCosts[0]);
    }


    /*
     * This method gets personFullNameType XMLObject and setting lastName, firstName, middleName data to it
     */
    protected gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.PersonFullNameType getContactPersonFullName(
            String lastName, String firstName, String middleName) {
        PersonFullNameType personFullNameType = PersonFullNameType.Factory.newInstance();
        personFullNameType.setLastName(lastName);
        personFullNameType.setFirstName(firstName);
        personFullNameType.setMiddleName(middleName);
        return personFullNameType;
    }

    /*
     * This method gets OrganizationContactPerson XMLObject and set data from Rolodex if Rolodex is there else it set default values
     * to it
     */
    protected OrganizationContactPerson getOrganizationContactPerson(Rolodex rolodex) {
        OrganizationContactPerson orgContactPerson = OrganizationContactPerson.Factory.newInstance();
        if (rolodex != null) {
            orgContactPerson.setName(getContactPersonFullName(rolodex.getLastName(), rolodex.getFirstName(), rolodex
                    .getMiddleName()));
            orgContactPerson.setPositionTitle(rolodex.getTitle());
            orgContactPerson.setContactInformation(getPersonContactInformation(rolodex));
        }
        else {
            orgContactPerson.setName(getContactPersonFullName(DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN));
            orgContactPerson.setPositionTitle(DEFAULT_VALUE_UNKNOWN);
            orgContactPerson.setContactInformation(getOrganizationPersonContactInformationWithDefaultValues());
        }
        return orgContactPerson;
    }

    /*
     * This method gets ContactInfoType XMLObject and sets data to it from Rolodex
     */
    protected ContactInfoType getPersonContactInformation(Rolodex rolodex) {
        ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
        String emailAddress = rolodex.getEmailAddress();
        if (emailAddress != null) {
            contactInfoType.setEmail(emailAddress);
        }
        String faxNumber = rolodex.getFaxNumber();
        if (faxNumber != null) {
            contactInfoType.setFaxNumber(faxNumber);
        }
        String officePhone = rolodex.getPhoneNumber();
        if (officePhone != null) {
            contactInfoType.setPhoneNumber(officePhone);
        }
        contactInfoType.setPostalAddress(getOrganizationAddress(rolodex));
        return contactInfoType;
    }

    /*
     * This method gets contactInfoType XMLObject and set email, faxNumber, phoneNumber, postalAddress default values to it
     */
    protected ContactInfoType getOrganizationPersonContactInformationWithDefaultValues() {
        ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
        contactInfoType.setEmail(DEFAULT_VALUE_UNKNOWN);
        contactInfoType.setFaxNumber(DEFAULT_VALUE_UNKNOWN);
        contactInfoType.setPhoneNumber(DEFAULT_VALUE_UNKNOWN);
        contactInfoType.setPostalAddress(getPostalAddressWithDefaultValues());
        return contactInfoType;
    }

    /*
     * This method gets PostalAddressType XMLObject and setting rolodex details to it
     */
    protected PostalAddressType getOrganizationAddress(Rolodex rolodex) {
        PostalAddressType postalAddressType = PostalAddressType.Factory.newInstance();
        postalAddressType.setStreetArray(getStreetAddress(rolodex.getAddressLine1(), rolodex.getAddressLine2(), rolodex
                .getAddressLine3()));
        String city = rolodex.getCity();
        postalAddressType.setCity((city == null || city.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN : city);
        postalAddressType.setState(rolodex.getState());
        String postalCode = rolodex.getPostalCode();
        postalAddressType
                .setPostalCode((postalCode == null || postalCode.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN
                        : postalCode);
        String county = rolodex.getCounty();
        postalAddressType.setCountry((county == null || county.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN
                : county);
        return postalAddressType;
    }

    /*
     * This method gets PostalAddressType XMLObject and setting default details to it
     */
    private PostalAddressType getPostalAddressWithDefaultValues() {
        PostalAddressType postalAddressType = PostalAddressType.Factory.newInstance();
        postalAddressType.setStreetArray(getStreetAddress(DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN));
        postalAddressType.setCity(DEFAULT_VALUE_UNKNOWN);
        postalAddressType.setState(DEFAULT_VALUE_UNKNOWN);
        postalAddressType.setPostalCode(DEFAULT_VALUE_UNKNOWN);
        postalAddressType.setCountry(DEFAULT_VALUE_UNKNOWN);
        return postalAddressType;
    }

    /*
     * This method gets arrays of Address from address1, address2, address3
     */
    private String[] getStreetAddress(String address1, String address2, String address3) {
        List<String> streetAddress = new ArrayList<String>();
        if (address1 != null) {
            streetAddress.add(address1);
        }
        if (address2 != null) {
            streetAddress.add(address2);
        }
        if (address3 != null) {
            streetAddress.add(address3);
        }
        return streetAddress.toArray(new String[0]);
    }

    /*
     * This method gets OtherAgencyQuestionsType XMLObject by setting AgencyIndicator and OtherAgencyName name to it if QuestionId
     * value is 15 else it set Only AgencyIndicator
     */
    protected OtherAgencyQuestionsType getOtherAgencyQuestionsForResearchCoverPage(DevelopmentProposal developmentProposal) {
        OtherAgencyQuestionsType otherAgencyQuestionsType = OtherAgencyQuestionsType.Factory.newInstance();
        otherAgencyQuestionsType.setOtherAgencyIndicator(false);
        for (ProposalYnq proposalYnq : developmentProposal.getProposalYnqs()) {
            if (proposalYnq.getQuestionId().equals(PROPOSALQUESTION_ID15) && proposalYnq.getAnswer() != null) {
                otherAgencyQuestionsType.setOtherAgencyIndicator(proposalYnq.getAnswer().equals(ANSWER_INDICATOR_VALUE) ? true
                        : false);
                otherAgencyQuestionsType.setOtherAgencyNames(proposalYnq.getExplanation() == null ? EMPTY_STRING : proposalYnq
                        .getExplanation());
            }
        }
        return otherAgencyQuestionsType;
    }

    /*
     * This method return true if question is answered otherwise false .
     */
    protected boolean getAnswerFromOrganizationYnq(OrganizationYnq organizationYnq) {
        return organizationYnq.getAnswer().equals(ANSWER_INDICATOR_VALUE) ? true : false;
    }

    /*
     * This method will set the values to project survey attributes based on whether question answered or not status. If no proposal
     * YNQ 's then set to default values.
     */
    protected ProjectSurvey getProjectSurvey(DevelopmentProposal developmentProposal) {
        ProjectSurvey projectSurvey = ProjectSurvey.Factory.newInstance();
        List<ProposalYnq> proposalYnqs = developmentProposal.getProposalYnqs();
        if (!proposalYnqs.isEmpty()) {
            for (ProposalYnq proposalYnq : proposalYnqs) {
                boolean questionAnswered = false;
                String answer = proposalYnq.getAnswer();
                String questionId = proposalYnq.getQuestionId();
                String explanation = proposalYnq.getExplanation() == null ? EMPTY_STRING : proposalYnq.getExplanation();
                if (ANSWER_INDICATOR_VALUE.equals(answer)) {
                    questionAnswered = true;
                }
                setProjectSurvey(projectSurvey, questionAnswered, questionId, explanation);
            }
        }
        else {
            setDefaultValuesToProjectSurvey(projectSurvey);
        }
        if (getProposalYNQ(LOBBYING_ACTIVITIES) != null) {
            projectSurvey.setH4Question(true);
        }
        projectSurvey.setCBQuestion(false);
        projectSurvey.setCBText(DEFAULT_ANSWER);
        projectSurvey.setEnvExemptionQuestion(false);
        projectSurvey.setEnvExemptionCBText(DEFAULT_ANSWER);
        projectSurvey.setEnvImpactQuestion(false);
        projectSurvey.setEnvImpactText(DEFAULT_ANSWER);
        return projectSurvey;
    }

    /*
     * This method set the default values to project survey attributes in case of proposal ynq's are not found.
     */
    private void setDefaultValuesToProjectSurvey(ProjectSurvey projectSurvey) {
        projectSurvey.setG3Question(false);
        projectSurvey.setG3Text(DEFAULT_ANSWER);
        projectSurvey.setG4Question(false);
        projectSurvey.setG4Text(DEFAULT_ANSWER);
        projectSurvey.setG6Question(false);
        projectSurvey.setG6Text(DEFAULT_ANSWER);
        projectSurvey.setG8Question(false);
        projectSurvey.setG8Text(DEFAULT_ANSWER);
        projectSurvey.setH1Question(false);
        projectSurvey.setH1Text(DEFAULT_ANSWER);
    }

    /*
     * This method set the values to project survey attributes in case of proposal ynq's are found.
     */
    private void setProjectSurvey(ProjectSurvey projectSurvey, boolean questionAnswered, String questionId, String explanation) {
        if (questionId.equals(HAZARDOUS_MATERIALS)) {
            projectSurvey.setG3Question(questionAnswered);
            projectSurvey.setG3Text(explanation);
        }
        else if (questionId.equals(GENETICALLY_ENGINEERED)) {
            projectSurvey.setG4Question(questionAnswered);
            projectSurvey.setG4Text(explanation);
        }
        else if (questionId.equals(HISTORICAL_SITES)) {
            projectSurvey.setG6Question(questionAnswered);
            projectSurvey.setG6Text(explanation);
        }
        else if (questionId.equals(PROPRIETARY_INFO)) {
            projectSurvey.setG8Question(questionAnswered);
            projectSurvey.setG8Text(explanation);
        }
        else if (questionId.equals(INTERNATIONAL_ACTIVITIES)) {
            projectSurvey.setH1Question(questionAnswered);
            projectSurvey.setH1Text(explanation);
        }
        else if (questionId.equals(LOBBYING_ACTIVITIES)) {
            projectSurvey.setH4Question(questionAnswered);
        }
        else if (questionId.equals(NSFSMALL_GRANT)) {
            projectSurvey.setSmallGrantQuestion(questionAnswered);
        }
        else if (questionId.equals(NSF_BEGINNING_INV)) {
            projectSurvey.setNSFbeginningInvestQuestion(questionAnswered);
        }
        else if (questionId.equals(STEMCELL)) {
            projectSurvey.setStemCellQuestion(questionAnswered);
            projectSurvey.setStemCellText(explanation);
        }
    }

    /*
     * This method will get the Proposal YNQ for given question id and question must be answered.
     */
    private ProposalYnq getProposalYNQ(String questionId) {
        ProposalYnq proposalYnq = null;
        Map<String, String> proposalYnqMap = new HashMap<String, String>();
        proposalYnqMap.put(QUESTION_ID_PARAMETER, questionId);
        proposalYnqMap.put(ANSWER_PARAMETER, ANSWER_INDICATOR_VALUE);
        List<ProposalYnq> proposalYnqs = (List<ProposalYnq>) businessObjectService.findMatching(ProposalYnq.class, proposalYnqMap);
        if (proposalYnqs != null && !proposalYnqs.isEmpty()) {
            proposalYnq = proposalYnqs.get(0);
        }
        return proposalYnq;
    }

    /*
     * This method gets SalariesAndWages from List of BudgetPersonnelRateAndBase List for a BudgetLineItem by checking the
     * rateClassType as Vacation and Employee Benefit
     */
    protected BigDecimal getSalaryWagesTotal(List<BudgetLineItem> budgetLineItems) {
        ScaleTwoDecimal salaryAndWagesTotal = ScaleTwoDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                salaryAndWagesTotal = salaryAndWagesTotal.add(getSalaryWagesTotalForLineItem(budgetPersDetails));
            }
        }
        return salaryAndWagesTotal.bigDecimalValue();
    }

    /*
     * This method gets SalaryAndWages amount from List of BudgetPersonnelRateAndBase as sum of salaryRequested and CalculatedCost
     * by checking the rateClassType for vacation and employee benefit
     */
    private ScaleTwoDecimal getSalaryWagesTotalForLineItem(BudgetPersonnelDetails budgetPersDetails) {
        ScaleTwoDecimal salaryAndWages = ScaleTwoDecimal.ZERO;
        salaryAndWages = salaryAndWages.add(budgetPersDetails.getSalaryRequested());
        salaryAndWages = salaryAndWages.add(getFringeCost(budgetPersDetails));
        return salaryAndWages;
    }

    protected ScaleTwoDecimal getFringeCost(BudgetPersonnelDetails budgetPersDetails) {
        ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
        for (BudgetPersonnelRateAndBase budgetPersRateBase : budgetPersDetails.getBudgetPersonnelRateAndBaseList()) {
            if (isRateAndBaseOfRateClassTypeEB(budgetPersRateBase) || isRateAndBaseOfRateClassTypeVacation(budgetPersRateBase)) {
                fringe = fringe.add(budgetPersRateBase.getCalculatedCost());
            }
        }
        return fringe;
    }

    protected ScaleTwoDecimal getTotalSalaryRequested(BudgetPeriod budgetPeriod) {
        ScaleTwoDecimal salary = ScaleTwoDecimal.ZERO;
        List<BudgetLineItem> lineItems = budgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : lineItems) {
            List<BudgetPersonnelDetails> persDetailsList = budgetLineItem.getBudgetPersonnelDetailsList();
            for (BudgetPersonnelDetails budgetPersonnelDetails : persDetailsList) {
                salary = salary.add(budgetPersonnelDetails.getSalaryRequested());
            }
        }
        return salary;
    }

    protected ScaleTwoDecimal getTotalFringe(BudgetPeriod budgetPeriod) {
        ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
        List<BudgetLineItem> lineItems = budgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : lineItems) {
            List<BudgetPersonnelDetails> persDetailsList = budgetLineItem.getBudgetPersonnelDetailsList();
            for (BudgetPersonnelDetails budgetPersonnelDetails : persDetailsList) {
                for (BudgetPersonnelRateAndBase budgetPersRateBase : budgetPersonnelDetails.getBudgetPersonnelRateAndBaseList()) {
                    if (isRateAndBaseOfRateClassTypeEBVacationOnLA(budgetPersRateBase)) {
                        fringe = fringe.add(budgetPersRateBase.getCalculatedCost());
                    }
                }
            }
        }
        return fringe;
    }

    private boolean isRateAndBaseOfRateClassTypeEBVacationOnLA(BudgetPersonnelRateAndBase rateAndBase) {
        if (rateAndBase == null) {
            LOG.debug("isRateAndBaseOfRateClassTypeEB : Rate and Base is null");
            return false;
        }
        rateAndBase.refreshNonUpdateableReferences();
        if (rateAndBase.getRateClass() != null
                && (RateClassType.EMPLOYEE_BENEFITS.getRateClassType().equals(rateAndBase.getRateClass().getRateClassTypeCode()) && !rateAndBase
                        .getRateTypeCode().equals("3"))
                || (RateClassType.VACATION.getRateClassType().equals(rateAndBase.getRateClass().getRateClassTypeCode()) && !rateAndBase
                        .getRateTypeCode().equals("2"))) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * This method gets true if rateClassType is E else false from RateAndBase
     */
    private boolean isRateAndBaseOfRateClassTypeEB(AbstractBudgetRateAndBase rateAndBase) {
        if (rateAndBase == null) {
            LOG.debug("isRateAndBaseOfRateClassTypeEB : Rate and Base is null");
            return false;
        }
        rateAndBase.refreshNonUpdateableReferences();
        if (rateAndBase.getRateClass() != null
                && RateClassType.EMPLOYEE_BENEFITS.getRateClassType().equals(rateAndBase.getRateClass().getRateClassTypeCode())
                && !rateAndBase.getRateTypeCode().equals("3")) {
            return true;
        }else {
            return false;
        }
    }

    /*
     * This method gets true if rateClassType is V else false from RateAndBase
     */
    private boolean isRateAndBaseOfRateClassTypeVacation(AbstractBudgetRateAndBase rateAndBase) {
        if (rateAndBase == null) {
            LOG.debug("isRateAndBaseOfRateClassTypeVacation : Rate and Base is null");
            return false;
        }
        rateAndBase.refreshNonUpdateableReferences();
        if (rateAndBase.getRateClass() != null
                && RateClassType.VACATION.getRateClassType().equals(rateAndBase.getRateClass().getRateClassTypeCode())
                && !rateAndBase.getRateTypeCode().equals("2")) {
            return true;
        }
        else {
            return false;
        }
    }


    /*
     * This method gets sum of lineItemCost as otherDirect total cost from List of BudgetLineItem, if budgetCategoryCode is
     * paticipantPatient for budgetLineItem
     */
    protected BigDecimal getParticipantPatientTotal(List<BudgetLineItem> budgetLineItems) {
        ScaleTwoDecimal cost = ScaleTwoDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryParticipantPatient(budgetLineItem)) {
                cost = cost.add(budgetLineItem.getLineItemCost());
            }
        }
        return cost.bigDecimalValue();
    }

    /*
     * This method gets sum of lineItemCost as otherDirect total cost from List of BudgetLineItem, if budgetCategoryCode is other
     * for budgetLineItem
     */
    protected BigDecimal getOtherDirectTotal(List<BudgetLineItem> budgetLineItems) {
        ScaleTwoDecimal cost = ScaleTwoDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryOther(budgetLineItem)) {
                cost = cost.add(budgetLineItem.getLineItemCost());
            }
        }
        return cost.bigDecimalValue();
    }

    /*
     * This method gets Arrays of TravelCost XMLObject from list of BudgetLineItems by checking the BudgetCategoryCode as Travel
     */
    protected TravelCosts[] getTravelCosts(List<BudgetLineItem> budgetLineItems) {
        List<TravelCosts> travelCostList = new ArrayList<TravelCosts>();
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryTravel(budgetLineItem)) {
                TravelCosts travelCost = TravelCosts.Factory.newInstance();
                if (CATEGORY_CODE_TRAVEL_FOREIGN.equals(budgetLineItem.getBudgetCategoryCode())) {
                    travelCost.setType(TravelType.FOREIGN);
                }
                else {
                    travelCost.setType(TravelType.DOMESTIC);
                }

                travelCost.setCost(budgetLineItem.getLineItemCost().bigDecimalValue());
                travelCost.setDescription(budgetLineItem.getLineItemDescription());
                travelCostList.add(travelCost);
            }
        }
        return travelCostList.toArray(new TravelCosts[0]);
    }

    /*
     * This method gets Arrays of ParticipantPatientCost XMLObject from list of BudgetLineItems by checking the BudgetCategoryCode
     * as paricipantPatient
     */
    protected ParticipantPatientCosts[] getParticipantPatientCost(DevelopmentProposal developmentProposal,
                                                                                List<BudgetLineItem> budgetLineItems) {
        List<ParticipantPatientCosts> participantPatientCostList = new ArrayList<ParticipantPatientCosts>();
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryParticipantPatient(budgetLineItem)) {
                ParticipantPatientCosts participantPatientCost = ParticipantPatientCosts.Factory.newInstance();
                participantPatientCost.setCost(budgetLineItem.getLineItemCost().bigDecimalValue());
                participantPatientCost.setDescription(budgetLineItem.getLineItemDescription());
                participantPatientCost.setType(getParticipantPatientType(developmentProposal, budgetLineItem));
                participantPatientCostList.add(participantPatientCost);
            }
        }
        return participantPatientCostList.toArray(new ParticipantPatientCosts[0]);
    }

    /*
     * This method gets ParticipantPatientType Enum value based on budgetCategory Description if there is no enum for budgetCategory
     * Description take as Other enum value
     */
    private ParticipantType.Enum getParticipantPatientType(DevelopmentProposal developmentProposal, BudgetLineItem budgetLineItem) {
        BudgetCategoryMap budgetCategoryMap = getBudgetCategoryMap(developmentProposal, budgetLineItem);
        ParticipantType.Enum participantType = null;
        if(budgetCategoryMap!=null){
            if(budgetCategoryMap.getTargetCategoryCode().equals("41")){
                participantType = ParticipantType.INPATIENT;
            }else if(budgetCategoryMap.getTargetCategoryCode().equals("90")){
                participantType = ParticipantType.OUTPATIENT;
            }else if(budgetCategoryMap.getTargetCategoryCode().equals("79")){//SUBSISTANCE
                participantType = ParticipantType.SUBSISTENCE ;
            }else if(budgetCategoryMap.getTargetCategoryCode().equals("77")){//TRAVEL
                participantType = ParticipantType.TRAVEL ;
            }else if(budgetCategoryMap.getTargetCategoryCode().equals("75")){//STIPENDS
                participantType = ParticipantType.STIPENDS;
            }
            else{
                participantType = ParticipantType.OTHER;
            }
        }else{
            participantType = ParticipantType.OTHER;
        }
        return participantType;
    }

    /*
     * This method gets sum of lineItemCost as equipment total cost from List of BudgetLineItem, if budgetCategoryCode is equipment
     * for budgetLineItem
     */
    protected BigDecimal getEquipmentTotal(List<BudgetLineItem> budgetLineItems) {
        ScaleTwoDecimal cost = ScaleTwoDecimal.ZERO;
        for (BudgetLineItem budgetLineItem : budgetLineItems) {
            if (isBudgetCategoryEquipment(budgetLineItem)) {
                cost = cost.add(budgetLineItem.getLineItemCost());
            }
        }
        return cost.bigDecimalValue();
    }

    /*
     * This method will get the list of Organization YNQ for given question id.
     */
    protected List<OrganizationYnq> getOrganizationYNQ(String questionId) {
        OrganizationYnq organizationYnq = null;
        Map<String, String> organizationYnqMap = new HashMap<String, String>();
        organizationYnqMap.put(ORGANIZATION_ID_PARAMETER, questionId);
        List<OrganizationYnq> organizationYnqs = (List<OrganizationYnq>) businessObjectService.findMatching(OrganizationYnq.class,
                organizationYnqMap);
        return organizationYnqs;
    }

    /*
     * This method gets ProjectRoleType as Enum value if the budgetPerson role not match with the enum type the roleType set to
     * Other
     */
    protected gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType.Enum getProjectRoleType(
            DevelopmentProposal developmentProposal, BudgetPerson budgetPerson) {
        gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType.Enum roleType = ProjectRoleType.Enum
        .forString(budgetPerson.getRole());
        if(isPI(developmentProposal,budgetPerson)){
            roleType = ProjectRoleType.PI_PD;
        }else if(isCoPI(developmentProposal,budgetPerson)){
            roleType = ProjectRoleType.CO_PI_PD;
        }else if(isKeyPerson(developmentProposal,budgetPerson)){
            roleType = ProjectRoleType.KEY_PERSON;
        }else if (roleType == null) {
            roleType = ProjectRoleType.OTHER;
        }
        return roleType;
    }

    private boolean isKeyPerson(DevelopmentProposal developmentProposal, BudgetPerson budgetPerson) {
        List<ProposalPerson> proposalPersons = developmentProposal.getProposalPersons();
        for (ProposalPerson proposalPerson : proposalPersons) {
            if(isSamePerson(budgetPerson, proposalPerson)){
                return true;
            }
        }
        return false;
    }

    private boolean isCoPI(DevelopmentProposal developmentProposal, BudgetPerson budgetPerson) {
        List<ProposalPerson> proposalPersons = developmentProposal.getInvestigators();
        for (ProposalPerson proposalPerson : proposalPersons) {
            if(isSamePerson(budgetPerson, proposalPerson)){
                return true;
            }
        }
        return false;
    }

    private boolean isPI(DevelopmentProposal developmentProposal, BudgetPerson budgetPerson) {
        ProposalPerson proposalPerson = developmentProposal.getPrincipalInvestigator();
        return isSamePerson(budgetPerson, proposalPerson);
    }


    private boolean isSamePerson(BudgetPerson budgetPerson, ProposalPerson proposalPerson) {
        if(proposalPerson.getPersonId()!=null && proposalPerson.getPersonId().equals(budgetPerson.getPersonId())){
            return true;
        }
        if(proposalPerson.getRolodexId()!=null && proposalPerson.getRolodexId().equals(budgetPerson.getRolodexId())){
            return true;
        }
        return false;
    }

    /*
     * This method gets CoreApplicationCategoryType XMLObject and setting description to it from ProposalType which comes from
     * DevelopmentProposal
     */
    protected CoreApplicationCategoryType getApplicationCategoryForResearchCoverPage(String propTypeDesc) {
        CoreApplicationCategoryType coreApplicationCategoryType = CoreApplicationCategoryType.Factory.newInstance();
        coreApplicationCategoryType.setCategoryIdentifier(propTypeDesc == null ? EMPTY_STRING : propTypeDesc);
        return coreApplicationCategoryType;
    }

    /*
     * This method gets CoreApplicantSubmissionQualifiersType XMLObject and setting Application date to it
     */
    protected void setApplicantSubmissionQualifiersForResearchCoverPage(DevelopmentProposal developmentProposal,
            CoreApplicantSubmissionQualifiersType coreApplicantSubmissionQualifiersType) {
        coreApplicantSubmissionQualifiersType.setApplicationDate(getDateTimeService().getCalendar(developmentProposal
                .getRequestedEndDateInitial()));
    }

    /*
     * This method gets CoreFederalAgencyReceiptQualifiersType XMLObject and setting AgencyName and AgencyReceiptDate to it
     */
    protected void setFederalAgencyReceiptQualifiersForResearchCoverPage(DevelopmentProposal developmentProposal,
            CoreFederalAgencyReceiptQualifiersType coreFederalAgencyReceiptQualifiersType) {
        coreFederalAgencyReceiptQualifiersType.setAgencyName(developmentProposal.getSponsor().getAcronym());
        if (developmentProposal.getS2sOpportunity() != null && developmentProposal.getS2sOpportunity().getOpeningDate() != null) {
            coreFederalAgencyReceiptQualifiersType.setAgencyReceiptDate(developmentProposal
                    .getS2sOpportunity().getOpeningDate());
        }
    }

    /*
     * This method gets CoreStateReceiptQualifiersType XMLObject and setting stateReceiptDate to it
     */
    protected CoreStateReceiptQualifiersType setStateReceiptQualifiersForResearchCoverPage(DevelopmentProposal developmentProposal,
            CoreStateReceiptQualifiersType coreStateReceiptQualifiersType) {
        if (developmentProposal.getDeadlineDate() != null) {
            coreStateReceiptQualifiersType.setStateReceiptDate(getDateTimeService().getCalendar(developmentProposal.getDeadlineDate()));
        }
        return coreStateReceiptQualifiersType;
    }

    /*
     * This method gets CoreStateIntergovernmentalReviewType XMLObject and setting ReviewAvailityDate and SubjectToreviewQuestion
     * data to it
     */
    protected void setStateIntergovernmentalReviewForResearchCoverPage(DevelopmentProposal developmentProposal,
            CoreStateIntergovernmentalReviewType coreStateIntergovernmentalReviewType) {
        if (developmentProposal.getDeadlineDate() != null) {
            coreStateIntergovernmentalReviewType.setReviewAvailabilityDate(getDateTimeService().getCalendar(developmentProposal
                    .getDeadlineDate()));
        }
        coreStateIntergovernmentalReviewType.setSubjectToReviewQuestion(true);
    }

    /*
     * This method gets CoreFederalDebtDelinquencyQuestionsType XMLObject and setting pplicantDelinquentIndicator data to it
     */
    protected CoreFederalDebtDelinquencyQuestionsType setFederalDebtDelinquencyQuestionForResearchCoverPage(
            DevelopmentProposal developmentProposal, CoreFederalDebtDelinquencyQuestionsType coreFederalDebtDelinquencyQuestionsType) {
        CoreFederalDebtDelinquencyQuestionsType ccoreFedDebtQuestionsType = CoreFederalDebtDelinquencyQuestionsType.Factory
                .newInstance();
        ccoreFedDebtQuestionsType.setApplicantDelinquentIndicator(false);
        return ccoreFedDebtQuestionsType;
    }

    /*
     * This method gets CoreProjectDatesType XMLObject and setting projectStartDate and projectEndDate data to it from
     * developmentProposal requestedStartDateInitial and requestedEndDateInitial
     */
    protected CoreProjectDatesType getProjectDatesForResearchCoverPage(Date startDate, Date endDate) {
        CoreProjectDatesType coreProjectDatesType = CoreProjectDatesType.Factory.newInstance();
        coreProjectDatesType.setProjectStartDate(getDateTimeService().getCalendar(startDate));
        coreProjectDatesType.setProjectEndDate(getDateTimeService().getCalendar(endDate));
        return coreProjectDatesType;
    }

    /*
     * This method gets CoreBudgetTotalsType XMLObject and setting budget data to it if budget is available and budgetModular flag
     * is set else setting to default value as 0
     */
    protected CoreBudgetTotalsType getBudgetTotalsForResearchCoverPage(Budget budget) {
        CoreBudgetTotalsType coreBudgetTotalsType = CoreBudgetTotalsType.Factory.newInstance();
        if (budget != null && budget.getVersionNumber() > 0) {
            if (!budget.getModularBudgetFlag()) {
                coreBudgetTotalsType.setApplicantCost(budget.getCostSharingAmount().bigDecimalValue());
                coreBudgetTotalsType.setFederalCost(budget.getTotalCost().bigDecimalValue());
                coreBudgetTotalsType.setOtherCost(budget.getTotalIndirectCost().bigDecimalValue());
            }
        }
        else {
            coreBudgetTotalsType.setApplicantCost(BigDecimal.ZERO);
            coreBudgetTotalsType.setFederalCost(BigDecimal.ZERO);
            coreBudgetTotalsType.setOtherCost(BigDecimal.ZERO);
        }
        coreBudgetTotalsType.setLocalCost(BigDecimal.ZERO);
        coreBudgetTotalsType.setProgramIncome(BigDecimal.ZERO);
        coreBudgetTotalsType.setStateCost(BigDecimal.ZERO);
        return coreBudgetTotalsType;
    }

    /*
     * This method gets ProjectSiteType XMLObject and set data from performing organization to it
     */
    protected ProjectSiteType getProjectSiteForResearchCoverPage(DevelopmentProposal developmentProposal) {
        ProposalSite performingOrg = developmentProposal.getPerformingOrganization();
        ProjectSiteType projectSiteType = ProjectSiteType.Factory.newInstance();
        Organization organization = performingOrg.getOrganization();
        projectSiteType.setOrganizationName(organization.getOrganizationName());
        projectSiteType.setCongressionalDistrict(organization.getCongressionalDistrict());
        projectSiteType.setPostalAddress(getOrganizationAddress(organization.getRolodex()));
        return projectSiteType;
    }

    /*
     * This method gets PostalAddressType XMLObject and setting proposalPerson details to it
     */
    protected PostalAddressType getPostalAddress(ProposalPerson proposalPerson) {
        PostalAddressType postalAddressType = PostalAddressType.Factory.newInstance();
        postalAddressType.setStreetArray(getStreetAddress(proposalPerson.getAddressLine1(), proposalPerson.getAddressLine2(),
                proposalPerson.getAddressLine3()));
        String city = proposalPerson.getCity();
        postalAddressType.setCity((city == null || city.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN : city);
        postalAddressType.setState(proposalPerson.getState());
        String postalCode = proposalPerson.getPostalCode();
        postalAddressType
                .setPostalCode((postalCode == null || postalCode.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN
                        : postalCode);
        String country = proposalPerson.getCountryCode();
        postalAddressType.setCountry((country == null || country.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN
                : country);
        return postalAddressType;
    }

    /*
     * This method gets contactInfoType XMLObject and set email, faxNumber, phoneNumber, postalAddress to it from proposalPerson
     */
    protected ContactInfoType getPersonContactInformation(ProposalPerson proposalPerson) {
        if (proposalPerson == null)
            return null;
        ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
        String emailAddress = proposalPerson.getEmailAddress();
        if (emailAddress != null) {
            contactInfoType.setEmail(emailAddress);
        }
        String faxNumber = proposalPerson.getFaxNumber();
        if (faxNumber != null) {
            contactInfoType.setFaxNumber(faxNumber);
        }
        String officePhone = proposalPerson.getOfficePhone();
        if (officePhone != null) {
            contactInfoType.setPhoneNumber(officePhone);
        }
        contactInfoType.setPostalAddress(getPostalAddress(proposalPerson));
        return contactInfoType;
    }

    protected void sortKeyPersonWithName(List<ProposalPerson> proposalPersonList) {
        Collections.sort(proposalPersonList, new Comparator<ProposalPerson>() {
            public int compare(ProposalPerson pp1, ProposalPerson pp2) {
                return pp1.getFullName().compareTo(pp2.getFullName());
            }
        });
    }


    protected List<KeyPersonInfo> getBudgetPersonsForCategoryMap(DevelopmentProposal developmentProposal,
            BudgetPeriod budgetPeriod, String categoryCode, String categoryMappingName) {

        List<KeyPersonInfo> keyPersons = new ArrayList<KeyPersonInfo>();

        Map<String, String> categoryMap = new HashMap<String, String>();
        categoryMap.put(KEY_TARGET_CATEGORY_CODE, categoryCode);
        categoryMap.put(KEY_MAPPING_NAME, categoryMappingName);
        List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);

        for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
                if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategoryCode())) {
                    addSeniorPerson(keyPersons, lineItem);
                    break;
                }
            }
        }

        for (KeyPersonInfo keyPersonInfo : keyPersons) {
            setKeyPersonCompensationForPeriod(budgetPeriod, keyPersonInfo);
        }
        return keyPersons;
    }

    private void setKeyPersonCompensationForPeriod(BudgetPeriod budgetPeriod, KeyPersonInfo keyPersonInfo) {
        CompensationInfo compensationInfo = new CompensationInfo();
        setCompensationForPeriod(keyPersonInfo, budgetPeriod, compensationInfo);
        setKeyPersonComp(keyPersonInfo, compensationInfo);
    }

    private void setKeyPersonCompensation(Budget budget, KeyPersonInfo keyPersonComp) {
        CompensationInfo compensationInfo = new CompensationInfo();
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            setCompensationForPeriod(keyPersonComp, budgetPeriod, compensationInfo);
        }
        setKeyPersonComp(keyPersonComp, compensationInfo);
    }

    private void setKeyPersonComp(KeyPersonInfo keyPersonComp, CompensationInfo compensationInfo) {
        keyPersonComp.setAcademicMonths(compensationInfo.getAcademicMonths());
        keyPersonComp.setCalendarMonths(compensationInfo.getCalendarMonths());
        keyPersonComp.setSummerMonths(compensationInfo.getSummerMonths());
        keyPersonComp.setBaseSalary(compensationInfo.getBaseSalary());
        keyPersonComp.setRequestedSalary(compensationInfo.getRequestedSalary());
        keyPersonComp.setFundsRequested(compensationInfo.getFundsRequested());
        keyPersonComp.setFringe(compensationInfo.getFringe());
        keyPersonComp.setCostSharingAmount(compensationInfo.getCostSharingAmount());
        keyPersonComp.setNonFundsRequested(compensationInfo.getNonFundsRequested());
        keyPersonComp.setFringeCostSharing(compensationInfo.getFringeCostSharing());
    }


    private void addSeniorPerson(List<KeyPersonInfo> keyPersons, BudgetLineItem lineItem) {
        for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
            KeyPersonInfo keyPerson;
            budgetPersonnelDetails.refreshReferenceObject(BUDGET_PERSON);
            if (budgetPersonnelDetails.getBudgetPerson().getRolodexId() != null) {
                keyPerson = getKeyPersonFromRolodex(budgetPersonnelDetails);
                addToKeyPersonList(keyPerson, keyPersons);
            }
            else if (StringUtils.isNotBlank(budgetPersonnelDetails.getBudgetPerson().getTbnId())) {
                keyPerson = getKeyPersonFromTbnPerson(budgetPersonnelDetails);
                addToKeyPersonList(keyPerson, keyPersons);
            }
            else if (StringUtils.isNotBlank(budgetPersonnelDetails.getBudgetPerson().getPersonId())) {
                keyPerson = getKeyPersonFromKcPerson(budgetPersonnelDetails.getPersonId());
                addToKeyPersonList(keyPerson, keyPersons);
            }
        }
    }


    protected List<KeyPersonInfo> getBudgetPersonsForCategoryMap(DevelopmentProposal developmentProposal,
            Budget budget, String categoryCode, String categoryMappingName) {

        KeyPersonInfo keyPerson = null;
        List<KeyPersonInfo> keyPersons = new ArrayList<KeyPersonInfo>();
        List<ProposalPerson> proposalPersons = developmentProposal.getProposalPersons();
        for (ProposalPerson proposalPerson : proposalPersons) {
            keyPerson = getKeyPersonFromProposalPerson(proposalPerson);
            keyPersons.add(keyPerson);
        }
        Map<String, String> categoryMap = new HashMap<String, String>();
        categoryMap.put(KEY_TARGET_CATEGORY_CODE, categoryCode);
        categoryMap.put(KEY_MAPPING_NAME, categoryMappingName);
        List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);
        
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
            for (BudgetPeriod budgetPeriod : budgetPeriods) {
                for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                    if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategoryCode())) {
                        addSeniorPerson(keyPersons, lineItem);
                        break;
                    }
                }
            }
        }
        for (KeyPersonInfo keyPersonComp : keyPersons) {
            setKeyPersonCompensation(budget, keyPersonComp);
        }


        return keyPersons;
    }

    private void addToKeyPersonList(KeyPersonInfo keyPerson, List<KeyPersonInfo> keyPersons) {
        if(!keyPersons.contains(keyPerson)){
            keyPersons.add(keyPerson);
        }
    }


    private KeyPersonInfo getKeyPersonFromProposalPerson(ProposalPerson proposalPerson) {
        KeyPersonInfo keyPerson = null;
        if (proposalPerson.getRolodexId() != null) {
            Rolodex rolodexPerson = getBusinessObjectService().findBySinglePrimaryKey(Rolodex.class, proposalPerson.getRolodexId());
            keyPerson = getKeyPeronInfo(rolodexPerson);
        }
        else if (StringUtils.isNotBlank(proposalPerson.getPersonId())) {
            keyPerson = getKeyPersonFromKcPerson(proposalPerson.getPersonId());
        }
        return keyPerson;
    }

    private KeyPersonInfo getKeyPersonFromKcPerson(String personId) {
        KeyPersonInfo keyPerson = null;
        KcPerson kcPerson = null;
        try {
            kcPerson = kcPersonService.getKcPersonByPersonId(personId);
        }
        catch (Exception e) {
            LOG.error("Person not found " + e);
        }
        if (kcPerson != null) {
            keyPerson = new KeyPersonInfo();
            keyPerson.setPersonId(kcPerson.getPersonId());
            keyPerson.setFirstName(kcPerson.getFirstName() == null ? VALUE_UNKNOWN : kcPerson.getFirstName());
            keyPerson.setLastName(kcPerson.getLastName() == null ? VALUE_UNKNOWN : kcPerson.getLastName());
            keyPerson.setMiddleName(kcPerson.getMiddleName());
            keyPerson.setNonMITPersonFlag(false);
            keyPerson.setRole(KEYPERSON_OTHER);
        }
        return keyPerson;
    }

    private KeyPersonInfo getKeyPersonFromTbnPerson(BudgetPersonnelDetails budgetPersonnelDetails) {
        KeyPersonInfo keyPerson = null;
        TbnPerson tbnPerson = budgetPersonnelDetails.getBudgetPerson().getTbnPerson();
        if (tbnPerson != null) {
            keyPerson = new KeyPersonInfo();
            String[] tbnNames = tbnPerson.getPersonName().split(" ");
            int nameIndex = 0;
            keyPerson.setPersonId(tbnPerson.getTbnId());
            keyPerson.setFirstName(tbnNames.length >= 1 ? tbnNames[nameIndex++] : VALUE_UNKNOWN);
            keyPerson.setMiddleName(tbnNames.length >= 3 ? tbnNames[nameIndex++] : " ");
            keyPerson.setLastName(tbnNames.length >= 2 ? tbnNames[nameIndex++] : VALUE_UNKNOWN);
            keyPerson.setRole(tbnPerson.getPersonName());
            keyPerson.setNonMITPersonFlag(false);
        }
        return keyPerson;
    }


    private KeyPersonInfo getKeyPersonFromRolodex(BudgetPersonnelDetails budgetPersonnelDetails) {

        budgetPersonnelDetails.getBudgetPerson().refreshReferenceObject("rolodex");
        Rolodex rolodexPerson = budgetPersonnelDetails.getBudgetPerson().getRolodex();
        KeyPersonInfo keyPerson = getKeyPeronInfo(rolodexPerson);
        return keyPerson;
    }


    private KeyPersonInfo getKeyPeronInfo(Rolodex rolodexPerson) {
        KeyPersonInfo keyPerson = new KeyPersonInfo();
        keyPerson.setRolodexId(rolodexPerson.getRolodexId());
        keyPerson.setFirstName(rolodexPerson.getFirstName() == null ? VALUE_UNKNOWN : rolodexPerson.getFirstName());
        keyPerson.setLastName(rolodexPerson.getLastName() == null ? VALUE_UNKNOWN : rolodexPerson.getLastName());
        keyPerson.setMiddleName(rolodexPerson.getMiddleName());
        keyPerson.setRole(StringUtils.isNotBlank(rolodexPerson.getTitle()) ? rolodexPerson.getTitle() : KEYPERSON_OTHER);
        keyPerson.setNonMITPersonFlag(true);
        return keyPerson;
    }

    @SuppressWarnings("unchecked")
    protected List<BudgetCategoryMapping> getBudgetCategoryMappings(Map<String, String> conditionMap) {
        Collection<BudgetCategoryMapping> budgetCategoryCollection = businessObjectService.findMatching(
                BudgetCategoryMapping.class, conditionMap);
        List<BudgetCategoryMapping> budgetCategoryMappings = new ArrayList<BudgetCategoryMapping>();
        if (budgetCategoryCollection != null) {
            budgetCategoryMappings.addAll(budgetCategoryCollection);
        }
        return budgetCategoryMappings;
    }

    /**
     * This method is to get the compensation for period
     * @param keyPerson
     * @param budgetPeriod
     * @param compensationInfo
     * @return
     */
    private void setCompensationForPeriod(KeyPersonInfo keyPerson, BudgetPeriod budgetPeriod,
            CompensationInfo compensationInfo) {
        for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
            for (BudgetPersonnelDetails personDetails : lineItem.getBudgetPersonnelDetailsList()) {
                if (keyPersonEqualsBudgetPerson(keyPerson, personDetails)) {
                    BigDecimal numberOfMonths = getNumberOfMonths(personDetails.getStartDate(), personDetails.getEndDate()).bigDecimalValue();
                    if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_ACADEMIC_MONTHS)) {
                        BigDecimal academicMonths = personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths).multiply(new ScaleTwoDecimal(0.01).bigDecimalValue());
                        if (lineItem.getBudgetCategoryCode().equals(SENIOR_PERSONNEL_CATEGORY_CODE)) {
                            compensationInfo.setAcademicMonths(compensationInfo.getAcademicMonths().add(new ScaleTwoDecimal(academicMonths)));
                        }
                    }
                    else if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_SUMMER_MONTHS)) {
                        BigDecimal summerMonths = personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths).multiply(new ScaleTwoDecimal(0.01).bigDecimalValue());
                        if (lineItem.getBudgetCategoryCode().equals(SENIOR_PERSONNEL_CATEGORY_CODE)) {
                            compensationInfo.setSummerMonths(compensationInfo.getSummerMonths().add(new ScaleTwoDecimal(summerMonths)));
                        }
                    }
                    else {
                        BigDecimal calendarMonths = personDetails.getPercentEffort().bigDecimalValue().multiply(numberOfMonths).multiply(new ScaleTwoDecimal(0.01).bigDecimalValue());
                        if (lineItem.getBudgetCategoryCode().equals(SENIOR_PERSONNEL_CATEGORY_CODE)) {
                            compensationInfo.setCalendarMonths(compensationInfo.getCalendarMonths().add(new ScaleTwoDecimal(calendarMonths)));
                        }
                    }
                    ScaleTwoDecimal totalSal = personDetails.getSalaryRequested();
                    compensationInfo.setRequestedSalary(compensationInfo.getRequestedSalary().add(totalSal));
                    ScaleTwoDecimal totalSalCostSharing = personDetails.getCostSharingAmount();
                    compensationInfo.setCostSharingAmount(compensationInfo.getCostSharingAmount().add(totalSalCostSharing));

                    for (BudgetPersonnelCalculatedAmount personCalculatedAmt : personDetails.getBudgetPersonnelCalculatedAmounts()) {
                        personCalculatedAmt.refreshReferenceObject("rateClass");
                        if ((personCalculatedAmt.getRateClass().getRateClassTypeCode().equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS) && !personCalculatedAmt
                                .getRateTypeCode().equals(RATE_TYPE_SUPPORT_STAFF_SALARIES))
                                || (personCalculatedAmt.getRateClass().getRateClassTypeCode().equals(RATE_CLASS_TYPE_VACATION) && !personCalculatedAmt
                                        .getRateTypeCode().equals(RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
                            ScaleTwoDecimal fringe = personCalculatedAmt.getCalculatedCost();
                            compensationInfo.setFringe(compensationInfo.getFringe().add(fringe));
                            ScaleTwoDecimal fringeCostSharing = personCalculatedAmt.getCalculatedCostSharing();
                            compensationInfo.setFringeCostSharing(compensationInfo.getFringeCostSharing().add(fringeCostSharing));
                        }
                    }
                    BudgetPerson budgetPerson = personDetails.getBudgetPerson();
                    if (budgetPerson != null) {
                        compensationInfo.setBaseSalary(budgetPerson.getCalculationBase());
                        
                        String apptTypeCode = budgetPerson.getAppointmentType().getAppointmentTypeCode();
                        if (!apptTypeCode.equals(APPOINTMENT_TYPE_SUM_EMPLOYEE)
                                && !apptTypeCode.equals(APPOINTMENT_TYPE_TMP_EMPLOYEE)) {
                            compensationInfo.setBaseSalary(budgetPerson.getCalculationBase());
                        }
                    }
                }
            }
        }
        compensationInfo.setFundsRequested(compensationInfo.getRequestedSalary().add(compensationInfo.getFringe()));
        compensationInfo.setNonFundsRequested(compensationInfo.getCostSharingAmount().add(compensationInfo.getFringeCostSharing()));
    }

    /**
     * This method compares a key person with budget person. It checks whether the key person is from PERSON or ROLODEX and matches
     * the respective person ID with the person in {@link BudgetPersonnelDetails}
     *
     * @param keyPersonInfo - key person to compare
     * @param budgetPersonnelDetails person from BudgetPersonnelDetails
     * @return true if persons match, false otherwise
     */
    public boolean keyPersonEqualsBudgetPerson(KeyPersonInfo keyPersonInfo, BudgetPersonnelDetails budgetPersonnelDetails) {
        boolean equal = false;
        if (keyPersonInfo != null && budgetPersonnelDetails != null) {
            String budgetPersonId = budgetPersonnelDetails.getPersonId();
            if ((keyPersonInfo.getPersonId() != null && keyPersonInfo.getPersonId().equals(budgetPersonId))
                    || (keyPersonInfo.getRolodexId() != null && keyPersonInfo.getRolodexId().toString().equals(budgetPersonId))) {
                equal = true;
            }
        }
        return equal;
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
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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

    public BudgetPersonService getBudgetPersonService() {
        return budgetPersonService;
    }

    public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
        this.budgetPersonService = budgetPersonService;
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }
}
