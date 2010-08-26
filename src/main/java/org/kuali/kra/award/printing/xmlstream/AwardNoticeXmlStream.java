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
package org.kuali.kra.award.printing.xmlstream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import noNamespace.AwardHierarchyType;
import noNamespace.AwardNoticeDocument;
import noNamespace.AwardType;
import noNamespace.ChildAwardType;
import noNamespace.OtherGroupDetailsType;
import noNamespace.OtherGroupType;
import noNamespace.SpecialReviewType;
import noNamespace.AwardNoticeDocument.AwardNotice.PrintRequirement;
import noNamespace.AwardType.AwardBudgetDetails;
import noNamespace.AwardType.AwardFundingSummary;
import noNamespace.AwardType.AwardOtherDatas;
import noNamespace.AwardType.AwardPaymentSchedules;
import noNamespace.AwardType.AwardSpecialReviews;
import noNamespace.AwardType.AwardTransferringSponsors;
import noNamespace.AwardType.ChildAwardDetails;
import noNamespace.AwardType.AwardBudgetDetails.BudgetDetails;
import noNamespace.AwardType.AwardOtherDatas.OtherData;
import noNamespace.AwardType.AwardPaymentSchedules.PaymentSchedule;
import noNamespace.AwardType.AwardTransferringSponsors.TransferringSponsor;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardBasisOfPayment;
import org.kuali.kra.award.home.AwardMethodOfPayment;
import org.kuali.kra.award.home.AwardStatus;
import org.kuali.kra.award.home.AwardTransferringSponsor;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.award.home.Distribution;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.closeout.CloseoutReportType;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.bo.AccountType;
import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.bo.Country;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.OrganizationTypeList;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SponsorType;
import org.kuali.kra.bo.Training;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.core.BudgetCategory;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.proposaldevelopment.bo.AbstractType;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kns.bo.State;
import org.kuali.rice.kns.service.StateService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class generates XML that conforms with the XSD related to Award Notice
 * Report. The data for XML is derived from {@link ResearchDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * 
 */
public class AwardNoticeXmlStream extends AwardBaseStream {

	private static final String PERSON_ID_PARAMETER = "personId";
	private static final String UNIT_NUMBER_PARAMETER = "unitNumber";
	private static final String ROLODEX_ID_PARAMETER = "rolodexId";
	private static final String COST_ELEMENT_PARAMETER = "costElement";
	private static final String LOOKUP_RETURN_PARAMETER = "value";
	private static final String ARGUMENT_NAME_PARAMETER = "argumentName";
	private static final String PROPOSAL_STATUS_CODE_PARAMETER = "proposalStatusCode";
	private static final String PROPOSAL_TYPE_CODE_PARAMETER = "proposalTypeCode";
	private static final String REPORT_CODE_PARAMETER = "reportCode";
	private static final String REPORT_CLASS_CODE_PARAMETER = "reportClassCode";
	private static final String APPROVAL_TYPE_CODE_PARAMETER = "approvalTypeCode";
	private static final String SPECIAL_REVIEW_CODE_PARAMETER = "specialReviewCode";
	private static final String SPONSOR_TYPE_CODE_PARAMETER = "sponsorTypeCode";
	private static final String STATE_CODE_PARAMETER = "stateCode";
	private static final String W_ARG_CODE_TBL = "W_ARG_CODE_TBL";
	private static final String W_ARG_VALUE_LIST = "W_ARG_VALUE_LIST";
	private static final String W_SELECT_COST_ELEMENT = "W_SELECT_COST_ELEMENT";
	private static final String W_ROLODEX_SELECT = "W_ROLODEX_SELECT";
	private static final String W_UNIT_SELECT = "W_UNIT_SELECT";
	private static final String W_PERSON_SELECT = "W_PERSON_SELECT";
	private static final String FIN_ENTITY_RELATIONSHIP_TYPE = "FIN ENTITY RELATIONSHIP TYPE";
	private static final String EQUIPMENT_APPROVAL = "EQUIPMENT APPROVAL";
	private static final String ENTITY_STATUS = "ENTITY STATUS";
	private static final String INVENTION = "INVENTION";
	private static final String IP_REVIEW_ACTIVITY_TYPE = "IP REVIEW ACTIVITY TYPE";
	private static final String IP_REVIEW_REQ_TYPE = "IP REVIEW REQ TYPE";
	private static final String IP_REVIEW_RESULT_TYPE = "IP REVIEW RESULT TYPE";
	private static final String NEGOTIATION_ACTIVITY_TYPE = "NEGOTIATION ACTIVITY TYPE";
	private static final String NEGOTIATION_STATUS = "NEGOTIATION STATUS";
	private static final String PRIOR_APPROVAL = "PRIOR APPROVAL";
	private static final String PROPERTY = "PROPERTY";
	private static final String PROPOSAL_STATUS = "PROPOSAL STATUS";
	private static final String PROPOSAL_TYPE = "PROPOSAL TYPE";
	private static final String PUBLICATION = "PUBLICATION";
	private static final String REFERENCED_DOCUMENT = "REFERENCED DOCUMENT";
	private static final String RIGHTS_IN_DATA = "RIGHTS IN DATA";
	private static final String SUBCONTRACT_STATUS = "SUBCONTRACT STATUS";
	private static final String SUB_CONTRACT_APPROVAL = "SUB CONTRACT APPROVAL";
	private static final String TRAVEL_RESTRICTION = "TRAVEL RESTRICTION";
	private static final String REPORTING = "reporting";
	private static final String TECHNICAL_REPORTING = "technicalReporting";
	private static final String TERMS = "terms";
	private static final String SPECIAL_REVIEW = "specialReview";
	private static final String SUBCONTRACT = "subcontract";
	private static final String SCIENCE_CODE = "scienceCode";
	private static final String PROPOSAL_DUE = "proposalDue";
	private static final String PAYMENT = "payment";
	private static final String INDIRECT_COST = "indirectCost";
	private static final String HIERARCHY_INFO = "hierarchyInfo";
	private static final String FOREIGN_TRAVEL = "foreignTravel";
	private static final String FLOW_THRU = "flowThru";
	private static final String EQUIPMENT = "equipment";
	private static final String COST_SHARING = "costSharing";
	private static final String COMMENTS = "comments";
	private static final String CLOSEOUT = "closeout";
	private static final String ADDRESS_LIST = "addressList";
	private static final String TRAINING = "TRAINING";
	private static final String STATE = "STATE";
	private static final String SPONSOR_TYPE = "SPONSOR TYPE";
	private static final String SPECIAL_REVIEW_TYPE = "SPECIAL REVIEW TYPE";
	private static final String PUBLICATION_CODE = "publicationCode";
	private static final String APPROVAL_TYPE = "APPROVAL TYPE";
	private static final String REPORT_CLASS = "REPORT CLASS";
	private static final String REPORT_TYPE = "REPORT TYPE";
	private VersionHistoryService versionHistoryService;

	/**
	 * This method generates XML for Award Notice Report. It uses data passed in
	 * {@link ResearchDocumentBase} for populating the XML nodes. The XML once
	 * generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		AwardNoticeDocument awardNoticeDocument = AwardNoticeDocument.Factory
				.newInstance();
		initialize((Award) printableBusinessObject);
		if (award != null) {
			awardNoticeDocument
					.setAwardNotice(getAwardNotice(reportParameters));
		}
		xmlObjectList.put(AwardPrintType.AWARD_NOTICE_REPORT
				.getAwardPrintType(), awardNoticeDocument);
		return xmlObjectList;
	}

	/*
	 * This method initializes the awardDocument ,award and awardAamountInfo
	 * reference variables
	 */
	private void initialize(Award award) {
		this.awardDocument = award.getAwardDocument();
		award.refreshNonUpdateableReferences();
		List<AwardAmountInfo> awardAmountInfos = award.getAwardAmountInfos();
		if (awardAmountInfos != null && !awardAmountInfos.isEmpty()) {
			awardAmountInfo = awardAmountInfos.get(0);
		}
	}

	/*
	 * This method will set the values to award attributes and finally returns
	 * award Xml object
	 */
	protected AwardType getAward() {
		AwardType awardType = super.getAward();
		awardType.setAwardTransferringSponsors(getAwardTransferringSponsors());
		awardType.setAwardPaymentSchedules(getAwardPaymentSchedules());
		awardType.setAwardSpecialReviews(getAwardSpecialReviews());
		awardType.setAwardOtherDatas(getAwardOtherDatas());
		awardType.setAwardBudgetDetails(getAwardBudgetDetails());
		awardType.setAwardFundingSummary(getAwardFundingSummary());
		awardType.setChildAwardDetails(getChildAwardDetails());
		return awardType;
	}

	/*
	 * This method will set the values to AwardSpecialReviews attributes and
	 * finally returns AwardSpecialReviews Xml object
	 */
	private AwardSpecialReviews getAwardSpecialReviews() {
		AwardSpecialReviews awardSpecialReviews = AwardSpecialReviews.Factory
				.newInstance();
		List<SpecialReviewType> specialReviewTypesList = new LinkedList<SpecialReviewType>();
		List<AwardSpecialReview> specialReviewList = award.getSpecialReviews();
		SpecialReviewType specialReviewType = null;
		for (AwardSpecialReview awardSpecialReview : specialReviewList) {
			specialReviewType = getAwardSpecialReview(awardSpecialReview);
			specialReviewTypesList.add(specialReviewType);
		}
		awardSpecialReviews.setSpecialReviewArray(specialReviewTypesList
				.toArray(new SpecialReviewType[0]));
		return awardSpecialReviews;
	}

	/*
	 * This method will set the values to AwardTransferringSponsors attributes
	 * and finally returns AwardTransferringSponsors Xml object
	 */
	private AwardTransferringSponsors getAwardTransferringSponsors() {
		AwardTransferringSponsors transferringSponsors = AwardTransferringSponsors.Factory
				.newInstance();
		List<TransferringSponsor> transferringSponsorList = new LinkedList<TransferringSponsor>();
		List<AwardTransferringSponsor> awardTransferringSponsorList = award
				.getAwardTransferringSponsors();
		TransferringSponsor transferringSponsor = null;
		for (AwardTransferringSponsor awardTransferringSponsor : awardTransferringSponsorList) {
			transferringSponsor = getAwardTransferringSponsor(awardTransferringSponsor);
			transferringSponsorList.add(transferringSponsor);
		}
		transferringSponsors
				.setTransferringSponsorArray(transferringSponsorList
						.toArray(new TransferringSponsor[0]));
		return transferringSponsors;
	}

	/*
	 * This method will set the values to AwardPaymentSchedules attributes and
	 * finally returns AwardPaymentSchedules Xml object
	 */
	private AwardPaymentSchedules getAwardPaymentSchedules() {
		AwardPaymentSchedules awardPaymentSchedules = AwardPaymentSchedules.Factory
				.newInstance();
		PaymentSchedule paymentSchedule = null;
		List<PaymentSchedule> paymentSchedulesList = new LinkedList<PaymentSchedule>();
		for (AwardPaymentSchedule awardPaymentSchedule : award
				.getPaymentScheduleItems()) {
			paymentSchedule = getAwardPaymentSchedule(awardPaymentSchedule);
			paymentSchedulesList.add(paymentSchedule);
		}
		awardPaymentSchedules.setPaymentScheduleArray(paymentSchedulesList
				.toArray(new PaymentSchedule[0]));
		return awardPaymentSchedules;
	}

	/*
	 * This method will set the values to AwardOtherDatas attributes and finally
	 * returns AwardOtherDatas Xml object
	 */
	private AwardOtherDatas getAwardOtherDatas() {
		AwardOtherDatas awardOtherDatas = AwardOtherDatas.Factory.newInstance();
		List<AwardCustomData> awardCustomDataList = award
				.getAwardCustomDataList();
		OtherData otherData = null;
		List<OtherData> otherDatas = new ArrayList<OtherData>();
		for (AwardCustomData awardCustomData : awardCustomDataList) {
			otherData = getOtherData(awardCustomData);
			otherDatas.add(otherData);
		}
		awardOtherDatas.setOtherDataArray(otherDatas.toArray(new OtherData[0]));
		return awardOtherDatas;
	}

	/*
	 * This method will set the values to other data bean and finally returns
	 * OtherData Xml object
	 */
	private OtherData getOtherData(AwardCustomData awardCustomData) {
		OtherData otherData = OtherData.Factory.newInstance();
		List<OtherGroupType> otherGroupTypes = new ArrayList<OtherGroupType>();
		String value = null;
		if (awardCustomData.getValue() != null) {
			value = awardCustomData.getValue().toUpperCase();
		}
		String lookupClass = null;
		String lookupReturn = null;
		if (awardCustomData.getCustomAttribute() != null) {
			if (awardCustomData.getCustomAttribute().getLookupClass() != null) {
				lookupClass = awardCustomData.getCustomAttribute()
						.getLookupClass().toUpperCase();
			}
			if (awardCustomData.getCustomAttribute().getLookupReturn() != null) {
				lookupReturn = awardCustomData.getCustomAttribute()
						.getLookupReturn();
			}
		}
		String description = null;
		if (lookupClass != null && value != null) {
			otherData.setColumnValue(value);
			description = getDescriptionForLookupCode(value, lookupClass,
					lookupReturn);
			if (description.length() == 0) {
				description = null;
			}
		}
		if (awardCustomData.getCustomAttribute() != null
				&& awardCustomData.getCustomAttribute().getName() != null) {
			otherData.setColumnName(awardCustomData.getCustomAttribute()
					.getName());
		}
		OtherGroupType otherGroupType = OtherGroupType.Factory.newInstance();
		if (description != null) {
			otherGroupType.setDescription(description);
		}
		List<OtherGroupDetailsType> otherGroupDetailsTypes = new ArrayList<OtherGroupDetailsType>();
		OtherGroupDetailsType otherGroupDetailsType = OtherGroupDetailsType.Factory
				.newInstance();
		if (awardCustomData.getCustomAttribute() != null
				&& awardCustomData.getCustomAttribute().getName() != null) {
			otherGroupDetailsType.setColumnName(awardCustomData
					.getCustomAttribute().getName());
		}
		if (awardCustomData.getValue() != null) {
			otherGroupDetailsType.setColumnValue(awardCustomData.getValue()
					.toUpperCase());
		}
		otherGroupDetailsTypes.add(otherGroupDetailsType);
		otherGroupType.setOtherGroupDetailsArray(otherGroupDetailsTypes
				.toArray(new OtherGroupDetailsType[0]));
		otherGroupTypes.add(otherGroupType);
		otherData.setOtherDetailsArray(otherGroupTypes
				.toArray(new OtherGroupType[0]));
		return otherData;
	}

	/*
	 * This method will set the values to AwardFundingSummary attributes and
	 * finally returns AwardFundingSummary Xml object
	 */
	private AwardFundingSummary getAwardFundingSummary() {
		AwardFundingSummary awardFundingSummary = AwardFundingSummary.Factory
				.newInstance();
		awardFundingSummary.setFundingSummaryArray(getAwardAmountInfo()
				.getAmountInfoArray());
		return awardFundingSummary;
	}

	/*
	 * This method will set the values to AwardBudgetDetails attributes and
	 * finally returns AwardBudgetDetails Xml object
	 */
	private AwardBudgetDetails getAwardBudgetDetails() {
		AwardBudgetDetails awardBudgetDetails = AwardBudgetDetails.Factory
				.newInstance();
		List<BudgetDetails> budgetDetailsList = new ArrayList<BudgetDetails>();
		BudgetDocument budgetDocument = getBudgetDocument();

		if (budgetDocument != null) {
			for (BudgetLineItem budgetLineItem : budgetDocument.getBudget()
					.getBudgetPeriod(0).getBudgetLineItems()) {
				BudgetDetails budgetDetails = BudgetDetails.Factory
						.newInstance();
				budgetDetails.setAwardNumber(award.getAwardNumber());
				budgetDetails.setSequenceNumber(award.getSequenceNumber());
				budgetDetails.setLineItemNumber(budgetLineItem
						.getLineItemNumber());
				budgetDetails.setCostElementCode(budgetLineItem
						.getCostElement());
				budgetDetails.setCostElementDescription(budgetLineItem
						.getCostElementBO().getDescription());
				budgetDetails.setLineItemDescription(budgetLineItem
						.getLineItemDescription());
				budgetDetailsList.add(budgetDetails);
			}
		}
		awardBudgetDetails.setBudgetDetailsArray(budgetDetailsList
				.toArray(new BudgetDetails[0]));
		return awardBudgetDetails;
	}

	/*
	 * This method will set the values to ChildAwardDetails attributes and
	 * finally returns ChildAwardDetails Xml object
	 */
	private ChildAwardDetails getChildAwardDetails() {
		ChildAwardDetails childAwardDetails = ChildAwardDetails.Factory
				.newInstance();
		ChildAwardType childAwardType = null;
		List<ChildAwardType> childAwardDetailsList = new ArrayList<ChildAwardType>();
		for (AwardAmountInfo amountInfo : award.getAwardAmountInfos()) {
			childAwardType = getChildAwardType(amountInfo);
			childAwardDetailsList.add(childAwardType);
		}
		childAwardDetails.setChildAwardArray(childAwardDetailsList
				.toArray(new ChildAwardType[0]));
		return childAwardDetails;
	}

	/*
	 * This method will set the values to ChildAwardType attributes and finally
	 * returns ChildAwardType Xml object
	 */
	private ChildAwardType getChildAwardType(AwardAmountInfo amountInfo) {
		AwardHierarchyType awardHierarchyType;
		ChildAwardType childAwardType = ChildAwardType.Factory.newInstance();
		if (amountInfo.getAwardAmountInfoId() != null) {
			childAwardType.setAccountNumber(String.valueOf(amountInfo
					.getAwardAmountInfoId()));
		}
		if (amountInfo.getAnticipatedTotalAmount() != null) {
			childAwardType.setAnticipatedTotalAmt(amountInfo
					.getAnticipatedTotalAmount().bigDecimalValue());
		}
		if (amountInfo.getFinalExpirationDate() != null) {
			Calendar finalExpDate = dateTimeService.getCalendar(amountInfo
					.getFinalExpirationDate());
			childAwardType.setFinalExpirationDate(finalExpDate);
		}
		if (amountInfo.getCurrentFundEffectiveDate() != null) {
			Calendar currentFundEffectiveDate = dateTimeService
					.getCalendar(amountInfo.getCurrentFundEffectiveDate());
			childAwardType
					.setCurrentFundEffectiveDate(currentFundEffectiveDate);
		}
		if (amountInfo.getAmountObligatedToDate() != null) {
			childAwardType.setAmtObligatedToDate(amountInfo
					.getAmountObligatedToDate().bigDecimalValue());
		}
		if (amountInfo.getObligationExpirationDate() != null) {
			Calendar obligationExpirationDate = dateTimeService
					.getCalendar(amountInfo.getObligationExpirationDate());
			childAwardType
					.setObligationExpirationDate(obligationExpirationDate);
		}
		KualiDecimal totalObligatedAmount = amountInfo
				.getObligatedTotalDirect().add(
						amountInfo.getObligatedTotalIndirect());
		childAwardType.setTotalObligatedAmount(totalObligatedAmount
				.bigDecimalValue());
		if (award.getPrincipalInvestigator() != null
				&& award.getPrincipalInvestigator().getFullName() != null) {
			childAwardType.setPIName(award.getPrincipalInvestigator()
					.getFullName());
		}
		awardHierarchyType = AwardHierarchyType.Factory.newInstance();
		awardHierarchyType.setAwardNumber(award.getAwardNumber());
		// TODO : RootAwardNumber, ParentAwardNumber Not Found
		childAwardType.setAwardHierarchy(awardHierarchyType);
		return childAwardType;
	}

	/*
	 * This method sets the values to print requirement attributes and finally
	 * returns the print requirement xml object
	 */
	protected PrintRequirement getPrintRequirement(
			Map<String, Object> reportParameters) {
		PrintRequirement printRequirement = PrintRequirement.Factory
				.newInstance();
		if (reportParameters != null) {
			printRequirement
					.setAddressListRequired(getPrintRequirementTypeRequired(
							reportParameters, ADDRESS_LIST));
			printRequirement
					.setCloseoutRequired(getPrintRequirementTypeRequired(
							reportParameters, CLOSEOUT));
			printRequirement
					.setCommentsRequired(getPrintRequirementTypeRequired(
							reportParameters, COMMENTS));
			printRequirement
					.setCostSharingRequired(getPrintRequirementTypeRequired(
							reportParameters, COST_SHARING));
			printRequirement
					.setEquipmentRequired(getPrintRequirementTypeRequired(
							reportParameters, EQUIPMENT));
			printRequirement
					.setFlowThruRequired(getPrintRequirementTypeRequired(
							reportParameters, FLOW_THRU));
			printRequirement
					.setForeignTravelRequired(getPrintRequirementTypeRequired(
							reportParameters, FOREIGN_TRAVEL));
			printRequirement
					.setHierarchyInfoRequired(getPrintRequirementTypeRequired(
							reportParameters, HIERARCHY_INFO));
			printRequirement
					.setIndirectCostRequired(getPrintRequirementTypeRequired(
							reportParameters, INDIRECT_COST));
			printRequirement
					.setPaymentRequired(getPrintRequirementTypeRequired(
							reportParameters, PAYMENT));
			printRequirement
					.setProposalDueRequired(getPrintRequirementTypeRequired(
							reportParameters, PROPOSAL_DUE));
			printRequirement
					.setSubcontractRequired(getPrintRequirementTypeRequired(
							reportParameters, SUBCONTRACT));
			printRequirement
					.setScienceCodeRequired(getPrintRequirementTypeRequired(
							reportParameters, SCIENCE_CODE));
			printRequirement
					.setSpecialReviewRequired(getPrintRequirementTypeRequired(
							reportParameters, SPECIAL_REVIEW));
			printRequirement.setTermsRequired(getPrintRequirementTypeRequired(
					reportParameters, TERMS));
			printRequirement
					.setTechnicalReportingRequired(getPrintRequirementTypeRequired(
							reportParameters, TECHNICAL_REPORTING));
			printRequirement
					.setReportingRequired(getPrintRequirementTypeRequired(
							reportParameters, REPORTING));
			printRequirement.setCurrentDate(dateTimeService
					.getCurrentCalendar());
			printRequirement
					.setSignatureRequired(getPrintRequirementTypeRequired(
							reportParameters, SIGNATURE_REQUIRED));
		}
		return printRequirement;
	}

	/*
	 * This method will get the type required if input param checked then it is
	 * true.If true means value 1 otherwise 0.
	 */
	private String getPrintRequirementTypeRequired(
			Map<String, Object> reportParameters, String printRequirementType) {
		String required = null;
		if (reportParameters.get(printRequirementType) != null
				&& ((Boolean) reportParameters.get(printRequirementType))
						.booleanValue()) {
			required = REQUIRED;
		} else {
			required = NOT_REQUIRED;
		}
		return required;
	}

	/*
	 * This method will get the description based on (look up return and value)
	 * or (look up return , value and look up argument)
	 */
	private String getDescriptionForLookupCode(String value,
			String lookupClass, String lookupReturn) {
		StringBuilder description = new StringBuilder();
		description.append(getPersonDescription(lookupClass, value));
		description.append(getUnitDescription(lookupClass, value));
		description.append(getRolodexDescription(lookupClass, value));
		description.append(getCostElementDescription(lookupClass, value));
		description.append(getArgValueLookUp(lookupClass, value, lookupReturn));
		if (lookupClass.equals(W_ARG_CODE_TBL)) {
			description.append(getAbstractTypeDescription(value, lookupReturn));
			description.append(getAccountTypeDescription(value, lookupReturn));
			description.append(getActivityTypeDescription(value, lookupReturn));
			description.append(getAwardStatusDescription(value, lookupReturn));
			description.append(getAwardTypeDescription(value, lookupReturn));
			description
					.append(getBasisOfPaymentDescription(value, lookupReturn));
			description.append(getBudgetCategory(value, lookupReturn));
			description.append(getCloseoutTypeDescription(value, lookupReturn));
			description.append(getCommentTypeDescription(value, lookupReturn));
			description.append(getContactTypeDescription(value, lookupReturn));
			description.append(getCostSharingTypeDesc(value, lookupReturn));
			description.append(getCountryDescription(value, lookupReturn));
			description.append(getDistibutionDescription(value, lookupReturn));
			description.append(getFinEntityRelationshipTypeDesc(value,
					lookupReturn));
			description.append(getEquipmentApprovalDesc(value, lookupReturn));
			description.append(getEntityStatusDescription(value, lookupReturn));
			description.append(getFrequencyDescription(value, lookupReturn));
			description
					.append(getFrequencyBaseDescription(value, lookupReturn));
			description.append(getRateTypeDescription(value, lookupReturn));
			description.append(getInventionDescription(value, lookupReturn));
			description.append(getReviewActivityTypeDesc(value, lookupReturn));
			description.append(getReviewRequestTypeDesc(value, lookupReturn));
			description.append(geIpReviewResultTypeDesc(value, lookupReturn));
			description.append(getMethodOfPaymentDesc(value, lookupReturn));
			description.append(getNegotationActivityTypeDesc(value,
					lookupReturn));
			description.append(getNegotationStatusDesc(value, lookupReturn));
			description.append(getNoticeOfOpportunityDesc(value, lookupReturn));
			description.append(getnsfCodeDescription(value, lookupReturn));
			description.append(getOranizationTypeDescription(value,
					lookupReturn));
			description
					.append(getPriorApprovalDescription(value, lookupReturn));
			description.append(getPropertyDesc(value, lookupReturn));
			description
					.append(getProposalStatusDescription(value, lookupReturn));
			description.append(getProposalTypeDescription(value, lookupReturn));
			description.append(getPublicationDescription(value, lookupReturn));
			description.append(getReferencedDocumentDesc(value, lookupReturn));
			description.append(getReportTypeDescription(value, lookupReturn));
			description.append(getReportClassDescription(value, lookupReturn));
			description.append(getRightsInDataDescription(value, lookupReturn));
			description.append(getCodeDescription(value, lookupReturn));
			description.append(getSpecialApprovalTypeDesc(value, lookupReturn));
			description.append(getSpecialReviewTypeDesc(value, lookupReturn));
			description.append(getSponsorTypeDescription(value, lookupReturn));
			description.append(getStateDescription(value, lookupReturn));
			description.append(getSubcontractStatusdesc(value, lookupReturn));
			description.append(getSubContractApprovalDesc(value, lookupReturn));
			description.append(getTrainingDescription(value, lookupReturn));
			description.append(getTravelRestrictionDesc(value, lookupReturn));
		}
		return description.toString();
	}

	private String getTravelRestrictionDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(TRAVEL_RESTRICTION)) {
		}
		return description;
	}

	/*
	 * This method will get the description for given trainingCode
	 */
	private String getTrainingDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(TRAINING)) {
			Map<String, String> TrainingMap = new HashMap<String, String>();
			TrainingMap.put("trainingCode", value);
			List<Training> trainingList = (List<Training>) businessObjectService
					.findMatching(Training.class, TrainingMap);
			if (trainingList != null && !trainingList.isEmpty()) {
				description = trainingList.get(0).getDescription();
			}
		}
		return description;
	}

	private String getSubContractApprovalDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(SUB_CONTRACT_APPROVAL)) {
		}
		return description;
	}

	private String getSubcontractStatusdesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(SUBCONTRACT_STATUS)) {
		}
		return description;
	}

	/*
	 * This method will get the description for given stateCode
	 */
	private String getStateDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(STATE)) {
			State state = getStateService().getByPrimaryId(value);
			if (state != null) {
				description = state.getPostalStateName();
			}
		}
		return description;
	}
	
	private StateService getStateService() {
	    return KraServiceLocator.getService(StateService.class);
	}

	/*
	 * This method will get the description for given sponsorTypeCode
	 */
	private String getSponsorTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(SPONSOR_TYPE)) {
			Map<String, String> sponsorTypeMap = new HashMap<String, String>();
			sponsorTypeMap.put(SPONSOR_TYPE_CODE_PARAMETER, value);
			List<SponsorType> sponsorTypes = (List<SponsorType>) businessObjectService
					.findMatching(SponsorType.class, sponsorTypeMap);
			if (sponsorTypes != null && !sponsorTypes.isEmpty()) {
				description = sponsorTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given specialReviewCode
	 */
	private String getSpecialReviewTypeDesc(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(SPECIAL_REVIEW_TYPE)) {
			Map<String, String> specialReviewMap = new HashMap<String, String>();
			specialReviewMap.put(SPECIAL_REVIEW_CODE_PARAMETER, value);
			List<SpecialReview> specialReviewType = (List<SpecialReview>) businessObjectService
					.findMatching(SpecialReview.class, specialReviewMap);
			if (specialReviewType != null && !specialReviewType.isEmpty()) {
				description = specialReviewType.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given approvalTypeCode
	 */
	private String getSpecialApprovalTypeDesc(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(APPROVAL_TYPE)) {
			Map<String, String> approvalTypeMap = new HashMap<String, String>();
			approvalTypeMap.put(APPROVAL_TYPE_CODE_PARAMETER, value);
			List<SpecialReviewApprovalType> approvalTypes = (List<SpecialReviewApprovalType>) businessObjectService
					.findMatching(SpecialReviewApprovalType.class,
							approvalTypeMap);
			if (approvalTypes != null && !approvalTypes.isEmpty()) {
				description = approvalTypes.get(0).getDescription();
			}
		}
		return description;
	}

	private String getCodeDescription(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(PUBLICATION_CODE)) {
		}
		return description;
	}

	private String getRightsInDataDescription(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(RIGHTS_IN_DATA)) {
		}
		return description;
	}

	/*
	 * This method will get the description for given reportClassCode
	 */
	private String getReportClassDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(REPORT_CLASS)) {
			Map<String, String> proposalTypeMap = new HashMap<String, String>();
			proposalTypeMap.put(REPORT_CLASS_CODE_PARAMETER, value);
			List<ReportClass> proposalType = (List<ReportClass>) businessObjectService
					.findMatching(ReportClass.class, proposalTypeMap);
			if (proposalType != null && !proposalType.isEmpty()) {
				description = proposalType.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given reportCode
	 */
	private String getReportTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(REPORT_TYPE)) {
			Map<String, String> reportMap = new HashMap<String, String>();
			reportMap.put(REPORT_CODE_PARAMETER, value);
			List<Report> reportTypeList = (List<Report>) businessObjectService
					.findMatching(Report.class, reportMap);
			if (reportTypeList != null && !reportTypeList.isEmpty()) {
				description = reportTypeList.get(0).getDescription();
			}
		}
		return description;
	}

	private String getReferencedDocumentDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(REFERENCED_DOCUMENT)) {
		}
		return description;
	}

	private String getPublicationDescription(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(PUBLICATION)) {
		}
		return description;
	}

	/*
	 * This method will get the description for given proposalTypeCode
	 */
	private String getProposalTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(PROPOSAL_TYPE)) {
			Map<String, String> proposalTypeMap = new HashMap<String, String>();
			proposalTypeMap.put(PROPOSAL_TYPE_CODE_PARAMETER, value);
			List<ProposalType> proposalTypeList = (List<ProposalType>) businessObjectService
					.findMatching(ProposalType.class, proposalTypeMap);
			if (proposalTypeList != null && !proposalTypeList.isEmpty()) {
				description = proposalTypeList.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given proposalStatusCode
	 */
	private String getProposalStatusDescription(String value,
			String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(PROPOSAL_STATUS)) {
			Map<String, String> proposalStatusMap = new HashMap<String, String>();
			proposalStatusMap.put(PROPOSAL_STATUS_CODE_PARAMETER, value);
			List<ProposalStatus> proposalStatusList = (List<ProposalStatus>) businessObjectService
					.findMatching(ProposalStatus.class, proposalStatusMap);
			if (proposalStatusList != null && !proposalStatusList.isEmpty()) {
				description = proposalStatusList.get(0).getDescription();
			}
		}
		return description;
	}

	private String getPropertyDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(PROPERTY)) {
		}
		return description;
	}

	private String getPriorApprovalDescription(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(PRIOR_APPROVAL)) {
		}
		return description;
	}

	/*
	 * This method will get the description for given organizationTypeCode
	 */
	private String getOranizationTypeDescription(String value,
			String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(ORGANIZATION_TYPE)) {
			Map<String, String> organizationTypeListMap = new HashMap<String, String>();
			organizationTypeListMap
					.put(ORGANIZATION_TYPE_CODE_PARAMETER, value);
			List<OrganizationTypeList> organizationTypes = (List<OrganizationTypeList>) businessObjectService
					.findMatching(OrganizationTypeList.class,
							organizationTypeListMap);
			if (organizationTypes != null && !organizationTypes.isEmpty()) {
				description = organizationTypes.get(0).getDescription();
			}
		}
		return description;
	}

	private String getnsfCodeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(NSF_CODE)) {
			description = getNSFDescription(value);
		}
		return description;
	}

	/*
	 * This method will get the description for given noticeOfOpportunityCode
	 */
	private String getNoticeOfOpportunityDesc(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(NOTICE_OF_OPPORTUNITY)) {
			Map<String, String> noticeOfOpportunityMap = new HashMap<String, String>();
			noticeOfOpportunityMap.put(NOTICE_OF_OPPORTUNITY_CODE_PARAMETER,
					value);
			List<NoticeOfOpportunity> noticeOfOpportunityList = (List<NoticeOfOpportunity>) businessObjectService
					.findMatching(NoticeOfOpportunity.class,
							noticeOfOpportunityMap);
			if (noticeOfOpportunityList != null
					&& !noticeOfOpportunityList.isEmpty()) {
				description = noticeOfOpportunityList.get(0).getDescription();
			}
		}
		return description;
	}

	private String getNegotationStatusDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(NEGOTIATION_STATUS)) {
		}
		return description;
	}

	private String getNegotationActivityTypeDesc(String value,
			String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(NEGOTIATION_ACTIVITY_TYPE)) {
		}
		return description;
	}

	/*
	 * This method will get the description for given methodOfPaymentCode
	 */
	private String getMethodOfPaymentDesc(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(METHOD_OF_PAYMENT)) {
			Map<String, String> awardMethodOfPaymentMap = new HashMap<String, String>();
			awardMethodOfPaymentMap
					.put(METHOD_OF_PAYMENT_CODE_PARAMETER, value);
			List<AwardMethodOfPayment> awardMethodOfPaymentList = (List<AwardMethodOfPayment>) businessObjectService
					.findMatching(AwardMethodOfPayment.class,
							awardMethodOfPaymentMap);
			if (awardMethodOfPaymentList != null
					&& !awardMethodOfPaymentList.isEmpty()) {
				description = awardMethodOfPaymentList.get(0).getDescription();
			}
		}
		return description;
	}

	private String geIpReviewResultTypeDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(IP_REVIEW_RESULT_TYPE)) {

		}
		return description;
	}

	private String getReviewRequestTypeDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(IP_REVIEW_REQ_TYPE)) {

		}
		return description;
	}

	private String getReviewActivityTypeDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(IP_REVIEW_ACTIVITY_TYPE)) {

		}
		return description;
	}

	private String getInventionDescription(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(INVENTION)) {

		}
		return description;
	}

	/*
	 * This method will get the description for given rateTypeCode
	 */
	private String getRateTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(IDC_RATE_TYPE)) {
			Map<String, String> rateTypeMap = new HashMap<String, String>();
			rateTypeMap.put(RATE_TYPE_CODE_PARAMETER, value);
			List<RateType> rateTypes = (List<org.kuali.kra.budget.rates.RateType>) businessObjectService
					.findMatching(org.kuali.kra.budget.rates.RateType.class,
							rateTypeMap);
			if (rateTypes != null && !rateTypes.isEmpty()) {
				description = rateTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given frequencyBaseCode
	 */
	private String getFrequencyBaseDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(FREQUENCY_BASE)) {
			Map<String, String> frequencyBaseMap = new HashMap<String, String>();
			frequencyBaseMap.put(FREQUENCY_BASE_CODE_PARAMETER, value);
			List<FrequencyBase> frequencyBaseList = (List<FrequencyBase>) businessObjectService
					.findMatching(FrequencyBase.class, frequencyBaseMap);
			if (frequencyBaseList != null && !frequencyBaseList.isEmpty()) {
				description = frequencyBaseList.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given frequencyCode
	 */
	private String getFrequencyDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(FREQUENCY)) {
			Map<String, String> frequencyMap = new HashMap<String, String>();
			frequencyMap.put(FREQUENCY_CODE_PARAMETER, value);
			List<Frequency> frequencyList = (List<Frequency>) businessObjectService
					.findMatching(Frequency.class, frequencyMap);
			if (frequencyList != null && !frequencyList.isEmpty()) {
				description = frequencyList.get(0).getDescription();
			}
		}
		return description;
	}

	private String getEntityStatusDescription(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(ENTITY_STATUS)) {

		}
		return description;
	}

	private String getEquipmentApprovalDesc(String value, String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(EQUIPMENT_APPROVAL)) {

		}
		return description;
	}

	private String getFinEntityRelationshipTypeDesc(String value,
			String lookupReturn) {
		String description = null;
		// TODO : Need to be done
		if (lookupReturn.equals(FIN_ENTITY_RELATIONSHIP_TYPE)) {

		}
		return description;
	}

	/*
	 * This method will get the description for given ospDistributionCode
	 */
	private String getDistibutionDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(DISTRIBUTION)) {
			Map<String, String> distributionMap = new HashMap<String, String>();
			distributionMap.put(OSP_DISTRIBUTION_CODE_PARAMETER, value);
			List<Distribution> distribution = (List<Distribution>) businessObjectService
					.findMatching(Distribution.class, distributionMap);
			if (distribution != null && !distribution.isEmpty()) {
				description = distribution.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given countryCode
	 */
	private String getCountryDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(COUNTRY)) {
			Map<String, String> countryMap = new HashMap<String, String>();
			countryMap.put(COUNTRY_CODE_PARAMETER, value);
			List<Country> countries = (List<Country>) businessObjectService
					.findMatching(Country.class, countryMap);
			if (countries != null && !countries.isEmpty()) {
				description = countries.get(0).getCountryName();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given costShareTypeCode
	 */
	private String getCostSharingTypeDesc(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(COST_SHARING_TYPE)) {
			Map<String, String> costSharingTypeMap = new HashMap<String, String>();
			costSharingTypeMap.put(COST_SHARE_TYPE_CODE_PARAMETER, value);
			List<CostShareType> costSharingTypes = (List<CostShareType>) businessObjectService
					.findMatching(CostShareType.class, costSharingTypeMap);
			if (costSharingTypes != null && !costSharingTypes.isEmpty()) {
				description = costSharingTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given contactTypeCode
	 */
	private String getContactTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(CONTACT_TYPE)) {
			Map<String, String> contactTypeMap = new HashMap<String, String>();
			contactTypeMap.put(CONTACT_TYPE_CODE_PARAMETER, value);
			List<ContactType> contactTypes = (List<ContactType>) businessObjectService
					.findMatching(ContactType.class, contactTypeMap);
			if (contactTypes != null && !contactTypes.isEmpty()) {
				description = contactTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given commentTypeCode
	 */
	private String getCommentTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(COMMENT_TYPE)) {
			Map<String, String> commentTypeMap = new HashMap<String, String>();
			commentTypeMap.put(COMMENT_TYPE_CODE_PARAMETER, value);
			List<org.kuali.kra.bo.CommentType> commentTypes = (List<org.kuali.kra.bo.CommentType>) businessObjectService
					.findMatching(org.kuali.kra.bo.CommentType.class,
							commentTypeMap);
			if (commentTypes != null && !commentTypes.isEmpty()) {
				description = commentTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given closeoutReportCode
	 */
	private String getCloseoutTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(CLOSEOUT_TYPE)) {
			// TODO :Need to confirm
			Map<String, String> closeoutReportTypeMap = new HashMap<String, String>();
			closeoutReportTypeMap.put(CLOSEOUT_REPORT_CODE_PARAMETER, value);
			List<CloseoutReportType> closeoutReportTypes = (List<CloseoutReportType>) businessObjectService
					.findMatching(CloseoutReportType.class,
							closeoutReportTypeMap);
			if (closeoutReportTypes != null && !closeoutReportTypes.isEmpty()) {
				description = closeoutReportTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given budgetCategoryCode
	 */
	private String getBudgetCategory(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(BUDGET_CATEGORY)) {
			Map<String, String> budgetCategoryMap = new HashMap<String, String>();
			budgetCategoryMap.put(BUDGET_CATEGORY_CODE_PARAMETER, value);
			List<BudgetCategory> budgetCategories = (List<BudgetCategory>) businessObjectService
					.findMatching(BudgetCategory.class, budgetCategoryMap);
			if (budgetCategories != null && !budgetCategories.isEmpty()) {
				description = budgetCategories.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given basisOfPaymentCode
	 */
	private String getBasisOfPaymentDescription(String value,
			String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(BASIS_OF_PAYMENT)) {
			Map<String, String> basisOfPaymentMap = new HashMap<String, String>();
			basisOfPaymentMap.put(BASIS_OF_PAYMENT_CODE_PARAMETER, value);
			List<AwardBasisOfPayment> basisOfPayments = (List<AwardBasisOfPayment>) businessObjectService
					.findMatching(AwardBasisOfPayment.class, basisOfPaymentMap);
			if (basisOfPayments != null && !basisOfPayments.isEmpty()) {
				description = basisOfPayments.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given awardTypeCode
	 */
	private String getAwardTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(AWARD_TYPE)) {
			Map<String, String> awardStatusMap = new HashMap<String, String>();
			awardStatusMap.put(AWARD_TYPE_CODE_PARAMETER, value);
			List<org.kuali.kra.award.home.AwardType> awardType = (List<org.kuali.kra.award.home.AwardType>) businessObjectService
					.findMatching(org.kuali.kra.award.home.AwardType.class,
							awardStatusMap);
			if (awardType != null && !awardType.isEmpty()) {
				description = awardType.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given statusCode
	 */
	private String getAwardStatusDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(AWARD_STATUS)) {
			Map<String, String> awardStatusMap = new HashMap<String, String>();
			awardStatusMap.put(STATUS_CODE_PARAMETER, value);
			List<AwardStatus> awardStatus = (List<AwardStatus>) businessObjectService
					.findMatching(AwardStatus.class, awardStatusMap);
			if (awardStatus != null && !awardStatus.isEmpty()) {
				description = awardStatus.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given activityTypeCode
	 */
	private String getActivityTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(ACTIVITY_TYPE)) {
			Map<String, String> activityTypeMap = new HashMap<String, String>();
			activityTypeMap.put(ACTIVITY_TYPE_CODE_PARAMETER, value);
			List<ActivityType> activityTypes = (List<ActivityType>) businessObjectService
					.findMatching(ActivityType.class, activityTypeMap);
			if (activityTypes != null && !activityTypes.isEmpty()) {
				description = activityTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given accountTypeCode
	 */
	private String getAccountTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(ACCOUNT_TYPE)) {
			Map<String, String> accountTypeMap = new HashMap<String, String>();
			accountTypeMap.put(ACCOUNT_TYPE_CODE_PARAMETER, value);
			List<AccountType> accountTypes = (List<AccountType>) businessObjectService
					.findMatching(AccountType.class, accountTypeMap);
			if (accountTypes != null && !accountTypes.isEmpty()) {
				description = accountTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given abstractTypeCode
	 */
	private String getAbstractTypeDescription(String value, String lookupReturn) {
		String description = null;
		if (lookupReturn.equals(ABSTRACT_TYPE)) {
			Map<String, String> abstractTypeMap = new HashMap<String, String>();
			abstractTypeMap.put(ABSTRACT_TYPE_CODE_PARAMETER, value);
			List<AbstractType> abstractTypes = (List<AbstractType>) businessObjectService
					.findMatching(AbstractType.class, abstractTypeMap);
			if (abstractTypes != null && !abstractTypes.isEmpty()) {
				description = abstractTypes.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given argumentName and value
	 */
	private String getArgValueLookUp(String lookupClass, String value,
			String lookupReturn) {
		String description = null;
		if (lookupClass.equals(W_ARG_VALUE_LIST)) {
			Map<String, String> argValueLookupMap = new HashMap<String, String>();
			argValueLookupMap.put(ARGUMENT_NAME_PARAMETER, value);
			argValueLookupMap.put(LOOKUP_RETURN_PARAMETER, lookupReturn);
			List<ArgValueLookup> argValueLookupList = (List<ArgValueLookup>) businessObjectService
					.findMatching(ArgValueLookup.class, argValueLookupMap);
			ArgValueLookup argValueLookup = null;
			if (argValueLookupList != null && !argValueLookupList.isEmpty()) {
				description = argValueLookupList.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given costElement
	 */
	private String getCostElementDescription(String lookupClass, String value) {
		String description = null;
		if (lookupClass.equals(W_SELECT_COST_ELEMENT)) {
			Map<String, String> costElementMap = new HashMap<String, String>();
			costElementMap.put(COST_ELEMENT_PARAMETER, value);
			List<CostElement> costElements = (List<CostElement>) businessObjectService
					.findMatching(CostElement.class, costElementMap);
			if (costElements != null && !costElements.isEmpty()) {
				description = costElements.get(0).getDescription();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given rolodexId
	 */
	private String getRolodexDescription(String lookupClass, String value) {
		String description = null;
		if (lookupClass.equals(W_ROLODEX_SELECT)) {
			Map<String, String> rolodexMap = new HashMap<String, String>();
			rolodexMap.put(ROLODEX_ID_PARAMETER, value);
			List<Rolodex> rolodexes = (List<Rolodex>) businessObjectService
					.findMatching(Rolodex.class, rolodexMap);
			if (rolodexes != null && !rolodexes.isEmpty()) {
				Rolodex rolodex = rolodexes.get(0);
				description = rolodex.getLastName() == null ? EMPTY_SPACE
						: rolodex.getLastName();
				description = rolodex.getFirstName() == null ? EMPTY_SPACE
						: rolodex.getFirstName();
				description = rolodex.getMiddleName() == null ? EMPTY_SPACE
						: rolodex.getMiddleName();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given unitNumber
	 */
	private String getUnitDescription(String lookupClass, String value) {
		String description = null;
		if (lookupClass.equals(W_UNIT_SELECT)) {
			Map<String, String> unitMap = new HashMap<String, String>();
			unitMap.put(UNIT_NUMBER_PARAMETER, value);
			List<Unit> units = (List<Unit>) businessObjectService.findMatching(
					Unit.class, unitMap);
			if (units != null && !units.isEmpty()) {
				description = units.get(0).getUnitName();
			}
		}
		return description;
	}

	/*
	 * This method will get the description for given personId
	 */
	private String getPersonDescription(String lookupClass, String value) {
		String description = null;
		if (lookupClass.equals(W_PERSON_SELECT)) {
			KcPerson person = getKcPersonService().getKcPersonByPersonId(value);
			description = person.getFirstName();
//			Map<String, String> personMap = new HashMap<String, String>();
//			personMap.put(PERSON_ID_PARAMETER, value);
//			List<Person> persons = (List<Person>) businessObjectService
//					.findMatching(Person.class, personMap);
//			if (persons != null && !persons.isEmpty()) {
//				description = persons.get(0).getFirstName();
//			}
		}
		return description;
	}

	private KcPersonService getKcPersonService() {
		return KraServiceLocator.getService(KcPersonService.class);
	}

	public VersionHistoryService getVersionHistoryService() {
		return versionHistoryService;
	}

	public void setVersionHistoryService(
			VersionHistoryService versionHistoryService) {
		this.versionHistoryService = versionHistoryService;
	}
}
