package org.kuali.kra.award.printing.xmlstream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.AmountInfoType;
import noNamespace.AwardHeaderType;
import noNamespace.AwardNoticeDocument;
import noNamespace.AwardTransactionType;
import noNamespace.MoneyHistoryReportType;
import noNamespace.AwardNoticeDocument.AwardNotice;
import noNamespace.AwardNoticeDocument.AwardNotice.PrintRequirement;
import noNamespace.AwardType.AwardDetails;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;

public class MoneyAndEndDatesHistoryXmlStream extends AwardBaseStream {

	public Map<String, XmlObject> generateXmlStream(
			ResearchDocumentBase document, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		AwardNoticeDocument awardNoticeDocument = AwardNoticeDocument.Factory
				.newInstance();
		initialize((AwardDocument) document);
		if (award != null) {
			awardNoticeDocument
					.setAwardNotice(getAwardNotice(reportParameters));
		}
		xmlObjectList.put(AwardPrintType.MONEY_AND_END_DATES_HISTORY
				.getAwardPrintType(), awardNoticeDocument);
		return xmlObjectList;
	}

	/*
	 * This method initializes the awardDocument ,award and awardAamountInfo
	 * reference variables
	 */
	private void initialize(AwardDocument awardDocument) {
		this.awardDocument = awardDocument;
		award = awardDocument.getAward();
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
		for (AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()) {
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
		AwardTransactionType awardTransactionType = AwardTransactionType.Factory
				.newInstance();
		awardTransactionType.setAwardNumber(award.getAwardNumber());
		awardTransactionType.setTransactionTypeCode(awardAmountTransaction
				.getTransactionTypeCode());
		// TODO: need to set TransactionTypeDesc
		// awardTransactionType.setTransactionTypeDesc(arg0);
		awardTransactionType.setComments(awardAmountTransaction.getComments());
		awardTransactionType.setNoticeDate(dateTimeService
				.getCalendar(awardAmountTransaction.getNoticeDate()));
		awardTransactionTypes.add(awardTransactionType);
		awardTransactionType.setTreeLevel(rowNo);

	}

	private void setMoneyHistoryInfos(List<AmountInfoType> amountInfoTypes,
			AwardAmountInfo awardAmountInfo, int rowNo) {
		AmountInfoType amountInfoType = AmountInfoType.Factory.newInstance();
		amountInfoType.setObligatedChange(awardAmountInfo.getObligatedChange()
				.bigDecimalValue());
		amountInfoType.setAnticipatedChange(awardAmountInfo
				.getAnticipatedChange().bigDecimalValue());
		amountInfoType.setAmtObligatedToDate(awardAmountInfo
				.getAmountObligatedToDate().bigDecimalValue());
		amountInfoType.setObligatedDistributableAmt(awardAmountInfo
				.getObliDistributableAmount().bigDecimalValue());
		amountInfoType.setAnticipatedTotalAmt(awardAmountInfo
				.getAnticipatedTotalAmount().bigDecimalValue());
		amountInfoType.setAnticipatedDistributableAmt(awardAmountInfo
				.getAntDistributableAmount().bigDecimalValue());
		amountInfoType.setObligationExpirationDate(dateTimeService
				.getCalendar(awardAmountInfo.getObligationExpirationDate()));
		amountInfoType.setCurrentFundEffectiveDate(dateTimeService
				.getCalendar(awardAmountInfo.getCurrentFundEffectiveDate()));
		amountInfoType.setFinalExpirationDate(dateTimeService
				.getCalendar(awardAmountInfo.getFinalExpirationDate()));
		amountInfoType.setObligatedChangeDirect(new BigDecimal(awardAmountInfo
				.getObligatedChangeDirect()));
		amountInfoType.setObligatedChangeIndirect(new BigDecimal(
				awardAmountInfo.getObligatedChangeIndirect()));
		amountInfoType.setAnticipatedChangeDirect(new BigDecimal(
				awardAmountInfo.getAnticipatedChangeDirect()));
		amountInfoType.setAnticipatedChangeIndirect(new BigDecimal(
				awardAmountInfo.getAnticipatedChangeIndirect()));
		String enableAwdAntOblDirectIndirectCost = PrintingUtils
				.getParameterValue(AwardDocument.class, OBLIGATED_DIRECT_INDIRECT_COST_CONSTANT);
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
		// Time Money Doc number - to be fixed
		timeAndMoneyMap.put("", timeAndMoneyDocNumber);
		List<TimeAndMoneyDocument> timeAndMoneyDocs = (List<TimeAndMoneyDocument>) businessObjectService
				.findMatching(TimeAndMoneyDocument.class, timeAndMoneyMap);
		if (timeAndMoneyDocs != null && !timeAndMoneyDocs.isEmpty()) {
			TimeAndMoneyDocument andMoneyDocument = timeAndMoneyDocs.get(0);
			awardAmountTransaction = andMoneyDocument
					.getNewAwardAmountTransaction();
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

	protected noNamespace.AwardType getAward() {
		noNamespace.AwardType awardType = noNamespace.AwardType.Factory
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
		if (award.getAwardNumber() != null) {
			awardHeaderType.setAwardNumber(award.getAwardNumber());
		}
		return awardHeaderType;
	}

}
