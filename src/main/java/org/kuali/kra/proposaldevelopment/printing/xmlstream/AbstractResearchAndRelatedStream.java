package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.ContactInfoType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.PostalAddressType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.BudgetTotalsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreApplicantSubmissionQualifiersType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreApplicationCategoryType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreBudgetTotalsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreFederalAgencyReceiptQualifiersType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreFederalDebtDelinquencyQuestionsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreProjectDatesType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreStateIntergovernmentalReviewType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreStateReceiptQualifiersType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreSubmissionCategoryType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.DescriptionBlockType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherAgencyQuestionsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.PersonFullNameType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSiteType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.AnimalSubjectDocument.AnimalSubject;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ApplicantOrganizationType.OrganizationContactPerson;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.EquipmentCostsDocument.EquipmentCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectCostsDocument.OtherDirectCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantPatientCostsDocument.ParticipantPatientCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSurveyDocument.ProjectSurvey;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelCostsDocument.TravelCosts;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.core.BudgetCategoryMapping;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.budget.personnel.TbnPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.s2s.generator.bo.CompensationInfo;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.kns.service.BusinessObjectService;

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
    private static final String PERIOD_TYPE_CALENDAR_MONTHS = "3";
    private static final String PERIOD_TYPE_SUMMER_MONTHS = "4";
    private static final String PERIOD_TYPE_CYCLE_MONTHS = "CY";
    private static final String TARGET_CATEGORY_CODE_EQUIPMENT_COST = "42";
    private static final String TARGET_CATEGORY_CODE_01 = "01";
    private static final String KEYPERSON_OTHER = "Other (Specify)";
    private static final String APPOINTMENT_TYPE_SUM_EMPLOYEE = "SUM EMPLOYEE";
    private static final String APPOINTMENT_TYPE_TMP_EMPLOYEE = "TMP EMPLOYEE";
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private S2SUtilService s2SUtilService;

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
    protected BudgetTotalsType getBudgetTotals(BudgetDecimal totalCost, BudgetDecimal costSharingAmount) {
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

    private String getCategoryMapTypeDescription(DevelopmentProposal developmentProposal, BudgetLineItem budgetLineItem) {
        BudgetCategoryMap budgetCategoryMap = getBudgetCategoryMap(developmentProposal, budgetLineItem);
        if(budgetCategoryMap!=null){
            return budgetCategoryMap.getDescription();
        }else if(budgetLineItem.getLineItemDescription()!=null && !budgetLineItem.getLineItemDescription().equals("")){
           return budgetLineItem.getLineItemDescription();
        }else{
           return budgetLineItem.getBudgetCategory().getDescription(); 
        }
    }

    /**
     * This method...
     * @param developmentProposal
     * @param budgetLineItem
     * @return
     */
    private BudgetCategoryMap getBudgetCategoryMap(DevelopmentProposal developmentProposal, BudgetLineItem budgetLineItem) {
        boolean isNih = getSponsorService().isSponsorNihOsc(developmentProposal) 
                                || getSponsorService().isSponsorNihMultiplePi(developmentProposal);
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

    /**
     * This method...
     * @return
     */
    private SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
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
        BudgetDecimal cost = BudgetDecimal.ZERO;
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
     * This method gets OtherDirectType Enum value based on budgetCategory Description if the budgetCategory description role not
     * match with the enum type the roleType set to Other
     */
    protected gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType.Enum getOtherDirectType(
            String budgetCategoryDesc) {
        gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType.Enum otherDirectType = OtherDirectType.Enum
                .forString(budgetCategoryDesc);
        if (otherDirectType == null) {
            otherDirectType = OtherDirectType.OTHER;
        }
        return otherDirectType;
    }

    /*
     * This method gets TravelType Enum value based on budgetCategory Description
     */
    protected TravelType.Enum getTravelType(String budgetCategoryDesc) {
        TravelType.Enum travelType = TravelType.Enum.forString(budgetCategoryDesc);
        return travelType;
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
        BudgetDecimal salaryAndWagesTotal = BudgetDecimal.ZERO;
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
    private BudgetDecimal getSalaryWagesTotalForLineItem(BudgetPersonnelDetails budgetPersDetails) {
        BudgetDecimal salaryAndWages = BudgetDecimal.ZERO;
        salaryAndWages = salaryAndWages.add(budgetPersDetails.getSalaryRequested());
        salaryAndWages = salaryAndWages.add(getFringeCost(budgetPersDetails));
        return salaryAndWages;
    }

    /*
     * This method gets the fringe amount from List of BudgetPersonnelRateAndBase
     */
    protected BudgetDecimal getFringeCost(BudgetPersonnelDetails budgetPersDetails) {
        BudgetDecimal fringe = BudgetDecimal.ZERO;
        for (BudgetPersonnelRateAndBase budgetPersRateBase : budgetPersDetails.getBudgetPersonnelRateAndBaseList()) {
            if (isRateAndBaseOfRateClassTypeEB(budgetPersRateBase) || isRateAndBaseOfRateClassTypeVacation(budgetPersRateBase)) {
                fringe = fringe.add(budgetPersRateBase.getCalculatedCost());
            }
        }
        return fringe;
    }

    protected BudgetDecimal getTotalSalaryRequested(BudgetPeriod budgetPeriod) {
        BudgetDecimal salary = BudgetDecimal.ZERO;
        List<BudgetLineItem> lineItems = budgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : lineItems) {
            List<BudgetPersonnelDetails> persDetailsList = budgetLineItem.getBudgetPersonnelDetailsList();
            for (BudgetPersonnelDetails budgetPersonnelDetails : persDetailsList) {
                salary = salary.add(budgetPersonnelDetails.getSalaryRequested());
            }
        }
        return salary;
    }

    protected BudgetDecimal getTotalFringe(BudgetPeriod budgetPeriod) {
        BudgetDecimal fringe = BudgetDecimal.ZERO;
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
                && (RateClassType.EMPLOYEE_BENEFITS.getRateClassType().equals(rateAndBase.getRateClass().getRateClassType()) && !rateAndBase
                        .getRateTypeCode().equals("3"))
                || (RateClassType.VACATION.getRateClassType().equals(rateAndBase.getRateClass().getRateClassType()) && !rateAndBase
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
                && RateClassType.EMPLOYEE_BENEFITS.getRateClassType().equals(rateAndBase.getRateClass().getRateClassType())
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
                && RateClassType.VACATION.getRateClassType().equals(rateAndBase.getRateClass().getRateClassType())
                && !rateAndBase.getRateTypeCode().equals("2")) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * This method gets true if rateClassType is O else false from RateAndBase
     */
    protected boolean isRateAndBaseOfRateClassTypeOverhead(AbstractBudgetRateAndBase rateAndBase) {
        if (rateAndBase == null) {
            LOG.debug("isRateAndBaseOfRateClassTypeOverhead : Rate and Base is null");
            return false;
        }
        rateAndBase.refreshNonUpdateableReferences();
        if (rateAndBase.getRateClass() != null
                && RateClassType.OVERHEAD.getRateClassType().equals(rateAndBase.getRateClass().getRateClassType())) {
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
        BudgetDecimal cost = BudgetDecimal.ZERO;
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
        BudgetDecimal cost = BudgetDecimal.ZERO;
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
                else { // if (CATEGORY_CODE_TRAVEL_DOMESTIC.equals(budgetLineItem.getBudgetCategoryCode())) {
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
        BudgetDecimal cost = BudgetDecimal.ZERO;
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

    /**
     * This method...
     * @param budgetPerson
     * @param proposalPerson
     */
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
            coreFederalAgencyReceiptQualifiersType.setAgencyReceiptDate(getDateTimeService().getCalendar(developmentProposal
                    .getS2sOpportunity().getOpeningDate()));
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

        boolean personAlreadyAdded = false;
        KeyPersonInfo keyPerson = null;
        List<KeyPersonInfo> keyPersons = new ArrayList<KeyPersonInfo>();
        List<ProposalPerson> proposalPersons = developmentProposal.getProposalPersons();
        for (ProposalPerson proposalPerson : proposalPersons) {
            // if(proposalPerson.getProposalPersonRoleId().equals(ProposalPersonRole.PI_CODE) ||
            // proposalPerson.getProposalPersonRoleId().equals(ProposalPersonRole.COI_CODE)){
            keyPerson = getKeyPersonFromProposalPerson(proposalPerson);
            keyPersons.add(keyPerson);
            // }

        }
        Map<String, String> categoryMap = new HashMap<String, String>();
        categoryMap.put(KEY_TARGET_CATEGORY_CODE, categoryCode);
        categoryMap.put(KEY_MAPPING_NAME, categoryMappingName);
        CompensationInfo compensationInfo = new CompensationInfo();
        List<BudgetCategoryMapping> budgetCategoryList = getBudgetCategoryMappings(categoryMap);
        for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
            boolean lineItemIsSeniorPersonnel = false;
            for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
                if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategoryCode())) {
                    lineItemIsSeniorPersonnel = true;
                    break;
                }
            }
            if (lineItemIsSeniorPersonnel) {
                for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                    personAlreadyAdded = false;
                    for (ProposalPerson proposalPerson : developmentProposal.getProposalPersons()) {
                        if (s2SUtilService.proposalPersonEqualsBudgetPerson(proposalPerson, budgetPersonnelDetails)) {
                            personAlreadyAdded = true;
                            break;
                        }
                    }
                    budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
                    if (!personAlreadyAdded) {
                        if (budgetPersonnelDetails.getBudgetPerson().getRolodexId() != null) {
                            keyPerson = getKeyPersonFromRolodex(budgetPersonnelDetails);
                            keyPersons.add(keyPerson);
                        }
                        else if (StringUtils.isNotBlank(budgetPersonnelDetails.getBudgetPerson().getTbnId())) {
                            keyPerson = getKeyPersonFromTbnPerson(budgetPersonnelDetails);
                            keyPersons.add(keyPerson);
                        }
                        else if (StringUtils.isNotBlank(budgetPersonnelDetails.getBudgetPerson().getPersonId())) {
                            keyPerson = getKeyPersonFromKcPerson(budgetPersonnelDetails.getPersonId());
                            keyPersons.add(keyPerson);
                        }
                    }
                }
            }
        }

        for (KeyPersonInfo keyPersonInfo : keyPersons) {
            KeyPersonInfo keyPersonComp = keyPersonInfo;
            setCompensation(keyPersonComp, budgetPeriod,compensationInfo);
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
        return keyPersons;
    }

    protected List<KeyPersonInfo> getBudgetPersonsForCategoryMap(DevelopmentProposal developmentProposal,
            Budget budget, String categoryCode, String categoryMappingName) {

        boolean personAlreadyAdded = false;
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
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                boolean lineItemIsSeniorPersonnel = false;
                for (BudgetCategoryMapping categoryMapping : budgetCategoryList) {
                    if (categoryMapping.getBudgetCategoryCode().equals(lineItem.getBudgetCategoryCode())) {
                        lineItemIsSeniorPersonnel = true;
                        break;
                    }
                }
                if (lineItemIsSeniorPersonnel) {
                    for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                        for (ProposalPerson proposalPerson : developmentProposal.getProposalPersons()) {
                            if (s2SUtilService.proposalPersonEqualsBudgetPerson(proposalPerson, budgetPersonnelDetails)) {
                                personAlreadyAdded = true;
//                                break;
                            }
                        }
                        budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
                        if (!personAlreadyAdded) {
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
                }
            }
        }
        for (KeyPersonInfo keyPersonComp : keyPersons) {
            CompensationInfo compensationInfo = getCompensation(keyPersonComp,budget);
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


        return keyPersons;
    }

    /**
     * This method...
     * @param keyPerson
     * @param keyPersons
     */
    private void addToKeyPersonList(KeyPersonInfo keyPerson, List<KeyPersonInfo> keyPersons) {
        if(!keyPersons.contains(keyPerson)){
            keyPersons.add(keyPerson);
        }
    }


    private KeyPersonInfo getKeyPersonFromProposalPerson(ProposalPerson proposalPerson) {
        KeyPersonInfo keyPerson = null;
        if (proposalPerson.getRolodexId() != null) {
            proposalPerson.refreshReferenceObject("rolodex");
            Rolodex rolodexPerson = getBusinessObjectService().findBySinglePrimaryKey(Rolodex.class, proposalPerson.getRolodexId());
            keyPerson = getKeyPeronInfo(rolodexPerson);
        }
        else if (StringUtils.isNotBlank(proposalPerson.getPersonId())) {
            keyPerson = getKeyPersonFromKcPerson(proposalPerson.getPersonId());
        }
        return keyPerson;
    }

    /**
     * This method...
     * 
     * @param keyPersons
     * @param budgetPersonnelDetails
     */
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
            keyPerson.setFirstName(kcPerson.getFirstName() == null ? S2SConstants.VALUE_UNKNOWN : kcPerson.getFirstName());
            keyPerson.setLastName(kcPerson.getLastName() == null ? S2SConstants.VALUE_UNKNOWN : kcPerson.getLastName());
            keyPerson.setMiddleName(kcPerson.getMiddleName());
            keyPerson.setNonMITPersonFlag(false);
            keyPerson.setRole(KEYPERSON_OTHER);
        }
        return keyPerson;
    }

    /**
     * This method...
     * 
     * @param keyPersons
     * @param budgetPersonnelDetails
     */
    private KeyPersonInfo getKeyPersonFromTbnPerson(BudgetPersonnelDetails budgetPersonnelDetails) {
        KeyPersonInfo keyPerson = null;
        Map<String, String> searchMap = new HashMap<String, String>();
        searchMap.put("tbnId", budgetPersonnelDetails.getBudgetPerson().getTbnId());
        TbnPerson tbnPerson = (TbnPerson) businessObjectService.findByPrimaryKey(TbnPerson.class, searchMap);
        if (tbnPerson != null) {
            keyPerson = new KeyPersonInfo();
            keyPerson.setPersonId(tbnPerson.getJobCode());
            String[] tbnNames = tbnPerson.getPersonName().split(" ");
            int nameIndex = 0;
            keyPerson.setPersonId(tbnPerson.getTbnId());
            keyPerson.setFirstName(tbnNames.length >= 1 ? tbnNames[nameIndex++] : S2SConstants.VALUE_UNKNOWN);
            keyPerson.setMiddleName(tbnNames.length >= 3 ? tbnNames[nameIndex++] : " ");
            keyPerson.setLastName(tbnNames.length >= 2 ? tbnNames[nameIndex++] : S2SConstants.VALUE_UNKNOWN);
            keyPerson.setRole(tbnPerson.getPersonName());
            keyPerson.setNonMITPersonFlag(false);
        }
        return keyPerson;
    }

    /**
     * This method...
     * 
     * @param budgetPersonnelDetails
     * @return
     */
    private KeyPersonInfo getKeyPersonFromRolodex(BudgetPersonnelDetails budgetPersonnelDetails) {

        budgetPersonnelDetails.getBudgetPerson().refreshReferenceObject("rolodex");
        Rolodex rolodexPerson = budgetPersonnelDetails.getBudgetPerson().getRolodex();
        KeyPersonInfo keyPerson = getKeyPeronInfo(rolodexPerson);
        return keyPerson;
    }

    /**
     * This method...
     * 
     * @param rolodexPerson
     * @return
     */
    private KeyPersonInfo getKeyPeronInfo(Rolodex rolodexPerson) {
        KeyPersonInfo keyPerson = new KeyPersonInfo();
        keyPerson.setRolodexId(rolodexPerson.getRolodexId());
        keyPerson.setFirstName(rolodexPerson.getFirstName() == null ? S2SConstants.VALUE_UNKNOWN : rolodexPerson.getFirstName());
        keyPerson.setLastName(rolodexPerson.getLastName() == null ? S2SConstants.VALUE_UNKNOWN : rolodexPerson.getLastName());
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

    private void setCompensation(KeyPersonInfo keyPerson, BudgetPeriod budgetPeriod,CompensationInfo compensationInfo) {
        setCompensationForPeriod(keyPerson, budgetPeriod, compensationInfo);
    }
    private CompensationInfo getCompensation(KeyPersonInfo keyPerson, Budget budget) {
        CompensationInfo compensationInfo = new CompensationInfo();
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            setCompensationForPeriod(keyPerson, budgetPeriod, compensationInfo);
        }
        return compensationInfo;
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
                if (s2SUtilService.keyPersonEqualsBudgetPerson(keyPerson, personDetails)) {
                    BudgetDecimal numberOfMonths = s2SUtilService.getNumberOfMonths(personDetails.getStartDate(), personDetails.getEndDate());
                    if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_ACADEMIC_MONTHS)) {
                        BudgetDecimal academicMonths = personDetails.getPercentEffort().multiply(numberOfMonths).multiply(new BudgetDecimal(0.01));
                        compensationInfo.setAcademicMonths(compensationInfo.getAcademicMonths().add(academicMonths));
                    }
                    else if (personDetails.getPeriodTypeCode().equals(PERIOD_TYPE_SUMMER_MONTHS)) {
                        BudgetDecimal summerMonths = personDetails.getPercentEffort().multiply(numberOfMonths).multiply(new BudgetDecimal(0.01));
                        compensationInfo.setSummerMonths(compensationInfo.getCalendarMonths().add(summerMonths));
                    }
                    else {
                        BudgetDecimal calendarMonths = personDetails.getPercentEffort().multiply(numberOfMonths).multiply(new BudgetDecimal(0.01));
                        compensationInfo.setCalendarMonths(compensationInfo.getAcademicMonths().add(calendarMonths));
                    }
                    BudgetDecimal totalSal = personDetails.getSalaryRequested();
                    compensationInfo.setRequestedSalary(compensationInfo.getRequestedSalary().add(totalSal));
                    BudgetDecimal totalSalCostSharing = personDetails.getCostSharingAmount();
                    compensationInfo.setCostSharingAmount(compensationInfo.getCostSharingAmount().add(totalSalCostSharing));
                    for (BudgetPersonnelCalculatedAmount personCalculatedAmt : personDetails.getBudgetPersonnelCalculatedAmounts()) {
                        personCalculatedAmt.refreshReferenceObject("rateClass");
                        if ((personCalculatedAmt.getRateClass().getRateClassType().equals(RATE_CLASS_TYPE_EMPLOYEE_BENEFITS) && !personCalculatedAmt
                                .getRateTypeCode().equals(RATE_TYPE_SUPPORT_STAFF_SALARIES))
                                || (personCalculatedAmt.getRateClass().getRateClassType().equals(RATE_CLASS_TYPE_VACATION) && !personCalculatedAmt
                                        .getRateTypeCode().equals(RATE_TYPE_ADMINISTRATIVE_SALARIES))) {
                            BudgetDecimal fringe = personCalculatedAmt.getCalculatedCost();
                            compensationInfo.setFringe(compensationInfo.getFringe().add(fringe));
                            BudgetDecimal fringeCostSharing = personCalculatedAmt.getCalculatedCostSharing();
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

    /**
     * Gets the s2SUtilService attribute.
     * 
     * @return Returns the s2SUtilService.
     */
    public S2SUtilService getS2SUtilService() {
        return s2SUtilService;
    }

    /**
     * Sets the s2SUtilService attribute value.
     * 
     * @param utilService The s2SUtilService to set.
     */
    public void setS2SUtilService(S2SUtilService utilService) {
        s2SUtilService = utilService;
    }
}
