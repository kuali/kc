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
package org.kuali.kra.award.printing.xmlstream;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardTransferringSponsor;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.kra.printing.schema.*;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice.PrintRequirement;
import org.kuali.kra.printing.schema.AwardType.*;
import org.kuali.kra.printing.schema.AwardType.AwardBudgetDetails.BudgetDetails;
import org.kuali.kra.printing.schema.AwardType.AwardOtherDatas.OtherData;
import org.kuali.kra.printing.schema.AwardType.AwardPaymentSchedules.PaymentSchedule;
import org.kuali.kra.printing.schema.AwardType.AwardTransferringSponsors.TransferringSponsor;

import java.util.*;

/**
 * This class generates XML that conforms with the XSD related to Award Notice
 * Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * 
 */
public class AwardNoticeXmlStream extends AwardBaseStream {

	private static final String REPORTING = "reporting";
	private static final String TECHNICAL_REPORTING = "technicalReporting";
	private static final String TERMS = "terms";
	private static final String SPECIAL_REVIEW = "specialReview";
	private static final String SUBCONTRACT = "subcontract";
	private static final String SCIENCE_CODE = "keywords";
	private static final String PROPOSAL_DUE = "proposalDue";
	private static final String PAYMENT = "payment";
	private static final String INDIRECT_COST = "fACost";
	private static final String FUNDING_SUMMARY="fundingSummary";
	private static final String HIERARCHY_INFO = "hierarchyInfo";
	private static final String FOREIGN_TRAVEL = "foreignTravel";
	private static final String FLOW_THRU = "flowThru";
	private static final String EQUIPMENT = "equipment";
	private static final String COST_SHARING = "costSharing";
	private static final String COMMENTS = "comments";
	private static final String CLOSEOUT = "closeout";
	private static final String ADDRESS_LIST = "addressList";
	private VersionHistoryService versionHistoryService;

	/**
	 * This method generates XML for Award Notice Report. It uses data passed in
	 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XML once
	 * generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		AwardNoticeDocument awardNoticeDocument = AwardNoticeDocument.Factory
				.newInstance();
		initialize((Award) printableBusinessObject);
		if (award != null) {
			awardNoticeDocument.setAwardNotice(getAwardNotice(reportParameters));
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
		this.award = award;
		List<AwardAmountInfo> awardAmountInfos = award.getAwardAmountInfos();
		if (awardAmountInfos != null && !awardAmountInfos.isEmpty()) {
			awardAmountInfo = awardAmountInfos.get(awardAmountInfos.size() - 1);
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
		List<AwardTransferringSponsor> awardTransferringSponsorList = award.getAwardTransferringSponsors();
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
		AwardPaymentSchedules awardPaymentSchedules = AwardPaymentSchedules.Factory.newInstance();
		PaymentSchedule paymentSchedule = null;
		List<PaymentSchedule> paymentSchedulesList = new LinkedList<PaymentSchedule>();
		for (AwardPaymentSchedule awardPaymentSchedule : award.getPaymentScheduleItems()) {
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
		List<AwardCustomData> awardCustomDataList = award.getAwardCustomDataList();
		OtherData otherData = null;
		String prevGroupName = null;
		OtherGroupType otherGroupType = null;
		for (AwardCustomData awardCustomData : awardCustomDataList) {
	        awardCustomData.refreshReferenceObject("customAttribute");
	        CustomAttribute customAttribute = awardCustomData.getCustomAttribute();
	        if (customAttribute != null) {
	            otherData = awardOtherDatas.addNewOtherData();
	            String groupName = customAttribute.getGroupName();
	            String attributeLabel = customAttribute.getLabel();
	            String attributeValue = awardCustomData.getValue();
	            if(attributeValue!=null){
    	            if(groupName!=null && !groupName.equals(prevGroupName)){
    	                otherGroupType = otherData.addNewOtherDetails();
    	                otherGroupType.setDescription(groupName);
    	            }
    	            prevGroupName = groupName;
    	            if(otherGroupType!=null){
    	                OtherGroupDetailsType otherGroupDetailsType = otherGroupType.addNewOtherGroupDetails();
    	                otherGroupDetailsType.setColumnName(attributeLabel);
    	                otherGroupDetailsType.setColumnValue(attributeValue);
    	            }
	            }
	                
	        }
		}
		return awardOtherDatas;
	}

	/*
	 * This method will set the values to AwardFundingSummary attributes and
	 * finally returns AwardFundingSummary Xml object
	 */
	private AwardFundingSummary getAwardFundingSummary() {
		AwardFundingSummary awardFundingSummary = AwardFundingSummary.Factory.newInstance();
		awardFundingSummary.setFundingSummaryArray(getAwardAmountInfo().getAmountInfoArray());
		return awardFundingSummary;
	}

	/*
	 * This method will set the values to AwardBudgetDetails attributes and
	 * finally returns AwardBudgetDetails Xml object
	 */
	private AwardBudgetDetails getAwardBudgetDetails() {
		AwardBudgetDetails awardBudgetDetails = AwardBudgetDetails.Factory.newInstance();
		List<BudgetDetails> budgetDetailsList = new ArrayList<BudgetDetails>();
		AwardBudgetDocument awardBudgetDocument = getBudgetDocument();
		if (awardBudgetDocument != null) {
			for (BudgetLineItem budgetLineItem : awardBudgetDocument.getBudget().getBudgetPeriod(0).getBudgetLineItems()) {
				BudgetDetails budgetDetails = BudgetDetails.Factory.newInstance();
				budgetDetails.setAwardNumber(award.getAwardNumber());
				budgetDetails.setSequenceNumber(award.getSequenceNumber());
				budgetDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
				budgetDetails.setCostElementCode(budgetLineItem.getCostElement());
				budgetDetails.setCostElementDescription(budgetLineItem.getCostElementBO().getDescription());
				budgetDetails.setLineItemDescription(budgetLineItem.getLineItemDescription());
				budgetDetailsList.add(budgetDetails);
			}
		}
		awardBudgetDetails.setBudgetDetailsArray(budgetDetailsList.toArray(new BudgetDetails[0]));
		return awardBudgetDetails;
	}

	/*
	 * This method will set the values to ChildAwardDetails attributes and
	 * finally returns ChildAwardDetails Xml object
	 */
	private ChildAwardDetails getChildAwardDetails() {
		ChildAwardDetails childAwardDetails = ChildAwardDetails.Factory.newInstance();
        
		AwardHierarchy awardHierarchy = getAwardHierarchyService().loadAwardHierarchyBranch(award.getAwardNumber());
		List<AwardHierarchy> children = awardHierarchy.getChildren();
        for (AwardHierarchy awardHierarchy2 : children) {
            setAwardHierarchy(awardHierarchy2, childAwardDetails);
        }
		return childAwardDetails;
	}

	private void setAwardHierarchy(AwardHierarchy awardHierarchy, ChildAwardDetails childAwardDetails) {
        if(awardHierarchy!=null) {
            ChildAwardType childAwardType = childAwardDetails.addNewChildAward();
            AwardHierarchyType hierarchyType = childAwardType.addNewAwardHierarchy();
            hierarchyType.setAwardNumber(awardHierarchy.getAwardNumber());
            hierarchyType.setParentAwardNumber(awardHierarchy.getParentAwardNumber());
            hierarchyType.setRootAwardNumber(awardHierarchy.getRootAwardNumber());
            setAwardAmountInfoDetails(awardHierarchy,childAwardType);
            List<AwardHierarchy> children = awardHierarchy.getChildren();
            for (AwardHierarchy awardHierarchy2 : children) {
                setAwardHierarchy(awardHierarchy2, childAwardDetails);
            }
        }
    }

    private void setAwardAmountInfoDetails(AwardHierarchy awardHierarchy, ChildAwardType childAwardType) {
        awardHierarchy.refreshReferenceObject("award");
        Award childAward = awardHierarchy.getAward();
        AwardAmountInfo awardAmountInfo = childAward.getLastAwardAmountInfo();
        if (awardHierarchy.getAward().getAccountNumber() != null) {
            childAwardType.setAccountNumber(awardHierarchy.getAward().getAccountNumber());
        }
        if (awardAmountInfo.getAnticipatedTotalAmount() != null) {
            childAwardType.setAnticipatedTotalAmt(awardAmountInfo.getAnticipatedTotalAmount().bigDecimalValue());
        }
        if (awardAmountInfo.getFinalExpirationDate() != null) {
            Calendar finalExpDate = dateTimeService.getCalendar(awardAmountInfo.getFinalExpirationDate());
            childAwardType.setFinalExpirationDate(finalExpDate);
        }
        if (awardAmountInfo.getCurrentFundEffectiveDate() != null) {
            Calendar currentFundEffectiveDate = dateTimeService.getCalendar(awardAmountInfo.getCurrentFundEffectiveDate());
            childAwardType.setCurrentFundEffectiveDate(currentFundEffectiveDate);
        }
        if (awardAmountInfo.getAmountObligatedToDate() != null) {
            childAwardType.setAmtObligatedToDate(awardAmountInfo.getAmountObligatedToDate().bigDecimalValue());
        }
        if (awardAmountInfo.getObligationExpirationDate() != null) {
            Calendar obligationExpirationDate = dateTimeService.getCalendar(awardAmountInfo.getObligationExpirationDate());
            childAwardType.setObligationExpirationDate(obligationExpirationDate);
        }
        childAwardType.setPIName(childAward.getPrincipalInvestigator().getFullName());
    }

    private AwardHierarchyService getAwardHierarchyService() {
        return KcServiceLocator.getService(AwardHierarchyService.class);
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
			        .setFundingSummaryRequired(getPrintRequirementTypeRequired(
			                reportParameters, FUNDING_SUMMARY));
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
            printRequirement.setOtherDataRequired(getPrintRequirementTypeRequired(
                    reportParameters, OTHER_DATA));
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

	public VersionHistoryService getVersionHistoryService() {
		return versionHistoryService;
	}

	public void setVersionHistoryService(
			VersionHistoryService versionHistoryService) {
		this.versionHistoryService = versionHistoryService;
	}
}
