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
package org.kuali.kra.award.printing.xmlstream;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.printing.schema.*;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice.PrintRequirement;
import org.kuali.kra.printing.schema.AwardType.AwardDetails;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;

import java.util.*;

public class MoneyAndEndDatesHistoryXmlStream extends AwardBaseStream {

	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		AwardNoticeDocument awardNoticeDocument = AwardNoticeDocument.Factory
				.newInstance();
		initialize((Award) printableBusinessObject);
		awardNoticeDocument.setAwardNotice(getAwardNotice(reportParameters));
		xmlObjectList.put(AwardPrintType.MONEY_AND_END_DATES_HISTORY
				.getAwardPrintType(), awardNoticeDocument);
		return xmlObjectList;
	}

	/*
	 * This method initializes the awardDocument ,award and awardAamountInfo
	 * reference variables
	 */
	private void initialize(Award award) {
		this.awardDocument = award.getAwardDocument();
		List<AwardAmountInfo> awardAmountInfos = award.getAwardAmountInfos();
		if (awardAmountInfos != null && !awardAmountInfos.isEmpty()) {
			awardAmountInfo = awardAmountInfos.get(0);
		}
	}

	protected AwardNotice getAwardNotice(Map<String, Object> reportParameters) {
		AwardNotice awardNotice = AwardNotice.Factory.newInstance();
		awardNotice.setSchoolInfo(getSchoolInfoType());
		awardNotice.setAward(getAward());
		awardNotice.setMoneyHistoryReportArray(getMoneyHistoryReports());
		awardNotice.setPrintRequirement(getPrintRequirement(reportParameters));
		return awardNotice;
	}

	private MoneyHistoryReportType[] getMoneyHistoryReports() {
		List<MoneyHistoryReportType> moneyHistoryReportTypes = new ArrayList<MoneyHistoryReportType>();
		List<AwardTransactionType> awardTransactionTypes = new ArrayList<AwardTransactionType>();
		List<AmountInfoType> amountInfoTypes = new ArrayList<AmountInfoType>();
		MoneyHistoryReportType moneyHistoryReportType = MoneyHistoryReportType.Factory
				.newInstance();
		int rowNo = 1;
		int seqNo = 0;
		for (AwardAmountInfo awardAmountInfo : awardDocument.getAward().getAwardAmountInfos()) {
			if (seqNo != awardAmountInfo.getSequenceNumber()) {
				setMoneyHistoryInfos(amountInfoTypes, awardAmountInfo, rowNo);
				setMoneyHistoryTransactionInfos(awardTransactionTypes,
						awardAmountInfo, rowNo);
				moneyHistoryReportType.setMoneyHistorySeq(awardAmountInfo
						.getSequenceNumber());
				seqNo = awardAmountInfo.getSequenceNumber();
			} else {
				setMoneyHistoryInfos(amountInfoTypes, awardAmountInfo, rowNo++);
				setMoneyHistoryTransactionInfos(awardTransactionTypes,
						awardAmountInfo, rowNo++);
			}
		}
		moneyHistoryReportType.setMoneyHistoryInfoArray(amountInfoTypes
				.toArray(new AmountInfoType[0]));
		moneyHistoryReportType
				.setMoneyHistoryTransactionInfoArray(awardTransactionTypes
						.toArray(new AwardTransactionType[0]));
		moneyHistoryReportTypes.add(moneyHistoryReportType);
		return moneyHistoryReportTypes.toArray(new MoneyHistoryReportType[0]);
	}

	private void setMoneyHistoryTransactionInfos(
			List<AwardTransactionType> awardTransactionTypes,
			AwardAmountInfo awardAmountInfo, int rowNo) {
		AwardAmountTransaction awardAmountTransaction = getAwardAmountTransaction(awardAmountInfo
				.getTimeAndMoneyDocumentNumber());
		
		if (awardAmountTransaction != null) {
			AwardTransactionType awardTransactionType = AwardTransactionType.Factory
					.newInstance();

			awardTransactionType.setAwardNumber(awardDocument.getAward().getAwardNumber());
			awardTransactionType.setTransactionTypeCode(awardAmountTransaction
					.getTransactionTypeCode());
			// TODO: need to set TransactionTypeDesc
			// awardTransactionType.setTransactionTypeDesc(arg0);
			awardTransactionType.setComments(awardAmountTransaction
					.getComments());
			if (awardAmountTransaction.getNoticeDate() != null) {
    			awardTransactionType.setNoticeDate(dateTimeService
    					.getCalendar(awardAmountTransaction.getNoticeDate()));
			}
			awardTransactionType.setTreeLevel(rowNo);
			
			awardTransactionTypes.add(awardTransactionType);
		}

	}

	private void setMoneyHistoryInfos(List<AmountInfoType> amountInfoTypes,
			AwardAmountInfo awardAmountInfo, int rowNo) {
		AmountInfoType amountInfoType = AmountInfoType.Factory.newInstance();
		if (awardAmountInfo.getObligatedChange() != null) {
			amountInfoType.setObligatedChange(awardAmountInfo
					.getObligatedChange().bigDecimalValue());
		}
		if (awardAmountInfo.getAnticipatedChange() != null) {
			amountInfoType.setAnticipatedChange(awardAmountInfo
					.getAnticipatedChange().bigDecimalValue());
		}
		if (awardAmountInfo.getAmountObligatedToDate() != null) {
			amountInfoType.setAmtObligatedToDate(awardAmountInfo
					.getAmountObligatedToDate().bigDecimalValue());
		}
		if (awardAmountInfo.getObliDistributableAmount() != null) {
			amountInfoType.setObligatedDistributableAmt(awardAmountInfo
					.getObliDistributableAmount().bigDecimalValue());
		}
		if (awardAmountInfo.getAnticipatedTotalAmount() != null) {
			amountInfoType.setAnticipatedTotalAmt(awardAmountInfo
					.getAnticipatedTotalAmount().bigDecimalValue());
		}
		if (awardAmountInfo.getAntDistributableAmount() != null) {
			amountInfoType.setAnticipatedDistributableAmt(awardAmountInfo
					.getAntDistributableAmount().bigDecimalValue());
		}
		if (awardAmountInfo.getObligationExpirationDate() != null) {
			amountInfoType
					.setObligationExpirationDate(dateTimeService
							.getCalendar(awardAmountInfo
									.getObligationExpirationDate()));
		}
		if (awardAmountInfo.getCurrentFundEffectiveDate() != null) {
			amountInfoType
					.setCurrentFundEffectiveDate(dateTimeService
							.getCalendar(awardAmountInfo
									.getCurrentFundEffectiveDate()));
		}
		if (awardAmountInfo.getFinalExpirationDate() != null) {
			amountInfoType.setFinalExpirationDate(dateTimeService
					.getCalendar(awardAmountInfo.getFinalExpirationDate()));
		}
		if (awardAmountInfo.getObligatedChangeDirect() != null) {
			amountInfoType.setObligatedChangeDirect(awardAmountInfo.getObligatedChangeDirect().bigDecimalValue());
		}
		if (awardAmountInfo.getObligatedChangeIndirect() != null) {
			amountInfoType.setObligatedChangeIndirect(awardAmountInfo.getObligatedChangeIndirect().bigDecimalValue());
		}
		if (awardAmountInfo.getAnticipatedChangeDirect() != null) {
			amountInfoType.setAnticipatedChangeDirect(awardAmountInfo.getAnticipatedChangeDirect().bigDecimalValue());
		}
		if (awardAmountInfo.getAnticipatedChangeIndirect() != null) {
			amountInfoType.setAnticipatedChangeIndirect(awardAmountInfo.getAnticipatedChangeIndirect().bigDecimalValue());
		}
		String enableAwdAntOblDirectIndirectCost = getAwardParameterValue(OBLIGATED_DIRECT_INDIRECT_COST_CONSTANT);
		amountInfoType
				.setEnableAwdAntOblDirectIndirectCost(enableAwdAntOblDirectIndirectCost);
		amountInfoType.setTreeLevel(rowNo);
		amountInfoTypes.add(amountInfoType);
	}

	/*
	 * This method will get the AwardAmountTransaction for given
	 * timeAndMoneyDocument Number
	 */
	private AwardAmountTransaction getAwardAmountTransaction(
			String timeAndMoneyDocNumber) {
		AwardAmountTransaction awardAmountTransaction = null;
		Map<String, String> timeAndMoneyMap = new HashMap<String, String>();
		timeAndMoneyMap.put(DOCUMENT_NUMBER, timeAndMoneyDocNumber);
		List<TimeAndMoneyDocument> timeAndMoneyDocs = (List<TimeAndMoneyDocument>) businessObjectService
				.findMatching(TimeAndMoneyDocument.class, timeAndMoneyMap);
		if (timeAndMoneyDocs != null && !timeAndMoneyDocs.isEmpty()) {
			TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyDocs.get(0);
			List<AwardAmountTransaction> awardAmountTransactionList = timeAndMoneyDocument
					.getAwardAmountTransactions();
			if (awardAmountTransactionList != null
					&& !awardAmountTransactionList.isEmpty()) {
				awardAmountTransaction = awardAmountTransactionList.get(0);
			}
		}
		return awardAmountTransaction;
	}

	@Override
	protected PrintRequirement getPrintRequirement(
			Map<String, Object> reportParameters) {
		PrintRequirement printRequirement = PrintRequirement.Factory
				.newInstance();
		if (reportParameters != null) {
			printRequirement.setCurrentDate(dateTimeService
					.getCurrentCalendar());
		}
		return printRequirement;
	}

	protected org.kuali.kra.printing.schema.AwardType getAward() {
		org.kuali.kra.printing.schema.AwardType awardType = org.kuali.kra.printing.schema.AwardType.Factory
				.newInstance();
		awardType.setAwardDetails(getAwardDetails());
		return awardType;
	}

	protected AwardDetails getAwardDetails() {
		AwardDetails awardDetailsType = AwardDetails.Factory.newInstance();
		AwardHeaderType awardHeaderType = getAwardHeaderType();
		awardDetailsType.setAwardHeader(awardHeaderType);
		return awardDetailsType;
	}

	protected AwardHeaderType getAwardHeaderType() {
		AwardHeaderType awardHeaderType = AwardHeaderType.Factory.newInstance();
		if (awardDocument.getAward().getAwardNumber() != null) {
			awardHeaderType.setAwardNumber(awardDocument.getAward().getAwardNumber());
		}
		return awardHeaderType;
	}
}
