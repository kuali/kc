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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noNamespace.AmountInfoType;
import noNamespace.AwardType;
import noNamespace.AwardNoticeDocument.AwardNotice;
import noNamespace.AwardType.AwardAmountInfo;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;

/**
 * This class generates XML that conforms with the XSD related to Award Budget
 * History Transaction Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class AwardBudgetHistoryTransaction extends AwardBudgetBaseStream {

	private static final String AWARD_AMOUNT_INFO_MODIFIED_VALUE = "1";
	private static final String AWARD_NUMBER = "awardNumber";
	private static final String TRANSACTION_ID = "transactionId";
	private VersionHistoryService versionHistoryService;

	/**
	 * This method generates XML for Award Budget History Transaction Report. It
	 * uses data passed in {@link ResearchDocumentBase} for populating the XML
	 * nodes. The XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> budgetHierarchyMap = new HashMap<String, XmlObject>();
		Award award = (Award) printableBusinessObject;
		AwardNotice awardNotice = AwardNotice.Factory.newInstance();
		String awardNumber = getAwardNumber(reportParameters);
		Long transactionId = getTransactionId(reportParameters);
		if (award != null) {
			awardNotice
					.setAward(getAwardType(award, transactionId, awardNumber));
			awardNotice.setSchoolInfo(getSchoolInfoType());
		}
		budgetHierarchyMap.put(AwardPrintType.AWARD_BUDGET_HISTORY_TRANSACTION
				.getAwardPrintType(), awardNotice);
		return budgetHierarchyMap;
	}

	/*
	 * This method will get the award number from input report parameters
	 */
	private String getAwardNumber(Map<String, Object> reportParameters) {
		String awardNumber = null;
		if (reportParameters.get(AWARD_NUMBER) != null) {
			awardNumber = String.valueOf(reportParameters.get(AWARD_NUMBER));
		}
		return awardNumber;
	}

	/*
	 * This method will get the transaction id from input report parameters
	 */
	private Long getTransactionId(Map<String, Object> reportParameters) {
		Long transactionId = null;
		if (reportParameters.get(TRANSACTION_ID) != null) {
			transactionId = Long.valueOf(String.valueOf(reportParameters
					.get(TRANSACTION_ID)));
		}

		return transactionId;
	}

	/*
	 * This method will set the values to Award type xml object attributes. It
	 * will set the following values like award amount info , Transaction info .
	 * 
	 */
	private AwardType getAwardType(Award award, Long transactionId,
			String awardNumber) {
		AwardType awardType = AwardType.Factory.newInstance();
		awardType.setAwardAmountInfo(getAwardAmountInfo(award, transactionId,
				awardNumber));
		awardType.setAwardTransactionInfo(getAwardTransactiontInfo(award));
		return awardType;
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes.
	 */
	private AwardAmountInfo getAwardAmountInfo(Award award, Long transactionId,
			String awardNumber) {
		AmountInfoType amountInfoType = null;
		AwardAmountInfo awardAmountInfo = AwardAmountInfo.Factory.newInstance();
		List<AmountInfoType> amountInfoTypes = new ArrayList<AmountInfoType>();
		for (org.kuali.kra.award.home.AwardAmountInfo awardAmount : award
				.getAwardAmountInfos()) {
			Long transacationIdValue = awardAmount.getTransactionId();
			String awardNumberValue = awardAmount.getAwardNumber();
			if (transactionId == transacationIdValue
					&& awardNumber.equals(awardNumberValue)) {
				amountInfoType = setAwardAmountInfo(award, awardAmount,
						transacationIdValue);
				org.kuali.kra.award.home.AwardAmountInfo prevAwardAmount = getPrevAwardAmountInfo(
						award, transactionId, awardNumber);
				setAwardAmountInfoModifiedValues(amountInfoType, awardAmount,
						prevAwardAmount);
				amountInfoTypes.add(amountInfoType);
			}
		}
		awardAmountInfo.setAmountInfoArray(amountInfoTypes
				.toArray(new AmountInfoType[0]));
		return awardAmountInfo;
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes which are modified by comapring previous award amount info
	 */
	private void setAwardAmountInfoModifiedValues(
			AmountInfoType amountInfoType,
			org.kuali.kra.award.home.AwardAmountInfo awrdAmountInfo,
			org.kuali.kra.award.home.AwardAmountInfo prevAwardAmount) {
		if (prevAwardAmount != null) {
			if (prevAwardAmount.getAmountObligatedToDate() != awrdAmountInfo
					.getAmountObligatedToDate()) {
				amountInfoType
						.setAmtObligatedToDateModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (prevAwardAmount.getObliDistributableAmount() != awrdAmountInfo
					.getObliDistributableAmount()) {
				amountInfoType
						.setObligatedDistributableAmtModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (prevAwardAmount.getAnticipatedTotalAmount() != awrdAmountInfo
					.getAnticipatedTotalAmount()) {
				amountInfoType
						.setAnticipatedTotalAmtModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (prevAwardAmount.getAntDistributableAmount() != awrdAmountInfo
					.getAntDistributableAmount()) {
				amountInfoType
						.setAnticipatedDistributableAmtModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (!prevAwardAmount.getObligationExpirationDate().equals(
					awrdAmountInfo.getObligationExpirationDate())) {
				amountInfoType
						.setObligationExpDateModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (!prevAwardAmount.getCurrentFundEffectiveDate().equals(
					awrdAmountInfo.getCurrentFundEffectiveDate())) {
				amountInfoType
						.setCurrentFundEffectiveDateModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (!prevAwardAmount.getFinalExpirationDate().equals(
					awrdAmountInfo.getFinalExpirationDate())) {
				amountInfoType
						.setFinalExpDateModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
		}
	}

	/*
	 * This method will get the previous award amount info for given transaction
	 * id
	 */
	private org.kuali.kra.award.home.AwardAmountInfo getPrevAwardAmountInfo(
			Award award, Long transactionId, String awardNumber) {
		int prevTransactionId = 0;
		List<AwardAmountTransaction> awardAmountTransactions = getAwardAmountTransactions(awardNumber);
		boolean transactionIdFound = false;
		for (AwardAmountTransaction timeAndMoneyActionSummary : awardAmountTransactions) {
			if (transactionId == timeAndMoneyActionSummary
					.getAwardAmountTransactionId().intValue()) {
				transactionIdFound = true;
			}
			if (transactionIdFound) {
				prevTransactionId = timeAndMoneyActionSummary
						.getAwardAmountTransactionId().intValue();
				break;
			}
		}
		org.kuali.kra.award.home.AwardAmountInfo prevAwardAmount = getPrevAwardAmountInfo(
				award, prevTransactionId);
		return prevAwardAmount;
	}

	/*
	 * This method will get the previous awardAmountInfo for transaction id
	 */
	private org.kuali.kra.award.home.AwardAmountInfo getPrevAwardAmountInfo(
			Award award, int transactionId) {
		org.kuali.kra.award.home.AwardAmountInfo awardAmountInfo = null;
		for (org.kuali.kra.award.home.AwardAmountInfo awardAmount : award
				.getAwardAmountInfos()) {
			if (awardAmount.getTransactionId() == transactionId) {
				awardAmountInfo = awardAmount;
				break;
			}
		}
		return awardAmountInfo;
	}

	/*
	 * This method will return the award amount transaction list from
	 * timeAndMoney document,which matches award number given.
	 */
	private List<AwardAmountTransaction> getAwardAmountTransactions(
			String awardNumber) {
		List<AwardAmountTransaction> awardAmountTransactions = null;
		Map<String, String> timeAndMoneyMap = new HashMap<String, String>();
		timeAndMoneyMap.put(AWARD_NUMBER_PARAMETER, awardNumber);
		List<TimeAndMoneyDocument> timeAndMoneyList = (List<TimeAndMoneyDocument>) businessObjectService
				.findMatching(TimeAndMoneyDocument.class, timeAndMoneyMap);
		if (timeAndMoneyList != null && !timeAndMoneyList.isEmpty()) {
			TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyList.get(0);
			awardAmountTransactions = timeAndMoneyDocument
					.getAwardAmountTransactions();
		}
		return awardAmountTransactions;
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes based on selected award number and transaction id.
	 */
	private AmountInfoType setAwardAmountInfo(Award award,
			org.kuali.kra.award.home.AwardAmountInfo awardAmount,
			Long transacationIdValue) {
		AmountInfoType amountInfoType;
		amountInfoType = AmountInfoType.Factory.newInstance();
		if (award.getAccountNumber() != null) {
			amountInfoType.setAccountNumber(award.getAccountNumber());
		}
		if (transacationIdValue != null) {
			amountInfoType.setAmountSequenceNumber(transacationIdValue
					.intValue());
		}
		if (awardAmount.getAmountObligatedToDate() != null) {
			amountInfoType.setAmtObligatedToDate(awardAmount
					.getAmountObligatedToDate().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedChange() != null) {
			amountInfoType.setAnticipatedChange(awardAmount
					.getAnticipatedChange().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedChangeDirect() != null) {
			amountInfoType.setAnticipatedChangeDirect(awardAmount.getAnticipatedChangeDirect().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedChangeIndirect() != null) {
			amountInfoType.setAnticipatedChangeIndirect(awardAmount.getAnticipatedChangeIndirect().bigDecimalValue());
		}
		if (awardAmount.getAntDistributableAmount() != null) {
			amountInfoType.setAnticipatedDistributableAmt(awardAmount
					.getAntDistributableAmount().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalAmount() != null) {
			amountInfoType.setAnticipatedTotalAmt(awardAmount
					.getAnticipatedTotalAmount().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalDirect() != null) {
			amountInfoType.setAnticipatedTotalDirect(awardAmount
					.getAnticipatedTotalDirect().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalIndirect() != null) {
			amountInfoType.setAnticipatedTotalIndirect(awardAmount
					.getAnticipatedTotalIndirect().bigDecimalValue());
		}
		if (award.getAwardNumber() != null) {
			amountInfoType.setAwardNumber(award.getAwardNumber());
		}
		if (awardAmount.getObligationExpirationDate() != null) {
			amountInfoType.setObligationExpirationDate(dateTimeService
					.getCalendar(awardAmount.getObligationExpirationDate()));
		}
		if (awardAmount.getObligatedTotalIndirect() != null) {
			amountInfoType.setObligatedTotalIndirect(awardAmount
					.getObligatedTotalIndirect().bigDecimalValue());
		}
		if (awardAmount.getObligatedTotalDirect() != null) {
			amountInfoType.setObligatedTotalDirect(awardAmount
					.getObligatedTotalDirect().bigDecimalValue());
		}
		if (awardAmount.getCurrentFundEffectiveDate() != null) {
			amountInfoType.setCurrentFundEffectiveDate(dateTimeService
					.getCalendar(awardAmount.getCurrentFundEffectiveDate()));
		}
		return amountInfoType;
	}

	public VersionHistoryService getVersionHistoryService() {
		return versionHistoryService;
	}

	public void setVersionHistoryService(
			VersionHistoryService versionHistoryService) {
		this.versionHistoryService = versionHistoryService;
	}
}
