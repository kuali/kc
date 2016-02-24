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
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardTransferringSponsor;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.printing.AwardPrintParameters;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.printing.schema.AwardNoticeDocument;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice.PrintRequirement;
import org.kuali.kra.printing.schema.AwardType;
import org.kuali.kra.printing.schema.AwardType.AwardOtherDatas;
import org.kuali.kra.printing.schema.AwardType.AwardOtherDatas.OtherData;
import org.kuali.kra.printing.schema.AwardType.AwardPaymentSchedules;
import org.kuali.kra.printing.schema.AwardType.AwardPaymentSchedules.PaymentSchedule;
import org.kuali.kra.printing.schema.AwardType.AwardSpecialReviews;
import org.kuali.kra.printing.schema.AwardType.AwardTransferringSponsors;
import org.kuali.kra.printing.schema.AwardType.AwardTransferringSponsors.TransferringSponsor;
import org.kuali.kra.printing.schema.SpecialReviewType;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;

import java.util.*;

/**
 * This class generates XML that conforms with the XSD related to Award Delta
 * Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 */
public class AwardDeltaXmlStream extends AwardBaseStream {

	private static final String SEQUENCE_NUMBER = "sequenceNumber";
	private VersionHistoryService versionHistoryService;

	/**
	 * This method generates XML for Award Delta Report. It uses data passed in
	 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl once
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
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<>();
		AwardNoticeDocument awardNoticeDocument = AwardNoticeDocument.Factory
				.newInstance();
		initialize((Award) printableBusinessObject, reportParameters);
		if (award != null) {
			awardNoticeDocument.setAwardNotice(getAwardNotice(reportParameters));
		}
		xmlObjectList.put(
				AwardPrintType.AWARD_DELTA_REPORT.getAwardPrintType(),awardNoticeDocument);
		return xmlObjectList;
	}

	/*
	 * This method initializes the awardDocument ,award , and ,awardAmountInfo
	 * reference variables.
	 */
	private void initialize(Award award1,
			Map<String, Object> reportParameters) {
		this.awardDocument = award1.getAwardDocument();
		this.award = award1;
		String awardNumber = getAwardNumberFromAward(awardDocument);
		if (awardNumber != null) {
			List<VersionHistory> awardSequenceHistoryList = versionHistoryService.loadVersionHistory(Award.class, awardNumber);
			int sequenceNumber = 0;
			int prevSequenceNumber = 0;
			Long transactionId = 0L;
			long prevTransactionId = 0;
			if (reportParameters.get(SEQUENCE_NUMBER) != null) {
				sequenceNumber = (Integer) reportParameters.get(SEQUENCE_NUMBER);
				award = getAwardForSeqenceNumber(awardNumber, sequenceNumber);
			}
			if (reportParameters.get(AwardPrintParameters.TRANSACTION_ID_INDEX.getAwardPrintParameter()) != null) {
				int transactionIdx = (Integer) reportParameters.get(AwardPrintParameters.TRANSACTION_ID_INDEX.getAwardPrintParameter());
				if (award != null) {
					awardAmountInfo = award.getAwardAmountInfos().get(transactionIdx); 
					transactionId = awardAmountInfo.getTransactionId();
				}
			}
			boolean sequenceNumberFound = false;
			for (VersionHistory versionHistory : awardSequenceHistoryList) {
				if (sequenceNumber == versionHistory.getSequenceOwnerSequenceNumber()) {
					sequenceNumberFound = true;
				}
				if (sequenceNumberFound) {
					prevSequenceNumber = sequenceNumber-1;
					break;
				}
			}
			if (sequenceNumber != 0) {
				award = getAwardForSeqenceNumber(awardNumber, sequenceNumber);
			}
			List<AwardAmountTransaction> awardAmountTransactions = getAwardAmountTransactions(awardNumber);
			boolean transactionIdFound = false;
			for (AwardAmountTransaction timeAndMoneyActionSummary : awardAmountTransactions) {
				if (transactionId != null && transactionId.equals(timeAndMoneyActionSummary.getAwardAmountTransactionId())) {
					transactionIdFound = true;
				}
				if (transactionIdFound) {
					prevTransactionId = transactionId-1;//timeAndMoneyActionSummary.getAwardAmountTransactionId();
					break;
				}
			}
			if(prevSequenceNumber!=0){
			    initializePrevAward(awardNumber, prevSequenceNumber,prevTransactionId);
			}
		}
	}

	/*
	 * This method will return the award amount transaction list from
	 * timeAndMoney document,which matches award number given.
	 */
	private List<AwardAmountTransaction> getAwardAmountTransactions(
			String awardNumber) {
		List<AwardAmountTransaction> awardAmountTransactions = new ArrayList<>();
		Map<String, String> timeAndMoneyMap = new HashMap<>();
		timeAndMoneyMap.put(ROOT_AWARD_NUMBER_PARAMETER, awardNumber);
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
	 * This method will get the Award Number from Award Document
	 */
	private String getAwardNumberFromAward(AwardDocument awardDocument) {
		String awardNumber = null;
		if (awardDocument != null && awardDocument.getAward() != null
				&& awardDocument.getAward().getAwardNumber() != null) {
			awardNumber = awardDocument.getAward().getAwardNumber();
		}
		return awardNumber;
	}

	/*
	 * This method initializes the prevAward and prevAwardAmountInfo reference
	 * variables.
	 */
	private void initializePrevAward(String awardNumber, long sequenceNumber,long transactionId) {
		if (transactionId > 1) {
			prevAward = award;
			if (award != null) {
				prevAwardAmountInfo = getPrevAwardAmountInfo(award,transactionId);
			}
		} else {
			prevAward = getAwardForSeqenceNumber(awardNumber, sequenceNumber);
			if (prevAward != null) {
				List<AwardAmountInfo> awardAmountInfos = prevAward.getAwardAmountInfos();
				if (awardAmountInfos != null && !awardAmountInfos.isEmpty()) {
					prevAwardAmountInfo = awardAmountInfos.get(0);
				}
			}
		}
	}

	/*
	 * This method will get the awardAmountInfo for given transaction id
	 */
	private AwardAmountInfo getPrevAwardAmountInfo(Award award,
			long transactionId) {
		org.kuali.kra.award.home.AwardAmountInfo awardAmountInfo = null;
		for (AwardAmountInfo awardAmount : award.getAwardAmountInfos()) {
			if (awardAmount.getTransactionId()!=null && awardAmount.getTransactionId().equals(transactionId)) {
				awardAmountInfo = awardAmount;
				break;
			}
		}
		return awardAmountInfo;
	}

	/*
	 * This method will get the awardForSeqenceNumber
	 */
	private Award getAwardForSeqenceNumber(String awardNumber,
			long sequenceNumber) {
		Award awardForSeqenceNumber = null;
		Map<String, String> awardMap = new HashMap<>();
		awardMap.put(AWARD_NUMBER_PARAMETER, awardNumber);
		awardMap.put(SEQUENCE_NUMBER_PARAMETER, String.valueOf(sequenceNumber));
		List<Award> awardList = (List<Award>) businessObjectService
				.findMatching(Award.class, awardMap);
		if (awardList != null && !awardList.isEmpty()) {
			awardForSeqenceNumber = awardList.get(0);
		}
		return awardForSeqenceNumber;
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
		return awardType;
	}

	/*
	 * This method will set the values to AwardSpecialReviews attributes and
	 * finally returns AwardSpecialReviews Xml object
	 */
	private AwardSpecialReviews getAwardSpecialReviews() {
		AwardSpecialReviews awardSpecialReviews = AwardSpecialReviews.Factory
				.newInstance();
		String specialReviewIndicator = award.getSpecialReviewIndicator();
		List<SpecialReviewType> specialReviewTypesList = new LinkedList<>();
		if (specialReviewIndicator != null
				&& !specialReviewIndicator.equals(EMPTY_STRING)) {
			specialReviewIndicator = specialReviewIndicator.length() == 1 ? specialReviewIndicator
					: specialReviewIndicator.substring(1, 2);
			if (specialReviewIndicator.equals(INDICATOR_CONSTANT)) {
				List<AwardSpecialReview> specialReviewList = award
						.getSpecialReviews();
				SpecialReviewType specialReviewType;
				for (AwardSpecialReview awardSpecialReview : specialReviewList) {
					specialReviewType = getAwardSpecialReview(awardSpecialReview);
					specialReviewTypesList.add(specialReviewType);
				}
			}
		}
		awardSpecialReviews.setSpecialReviewArray(specialReviewTypesList
				.toArray(new SpecialReviewType[0]));
		return awardSpecialReviews;
	}

	/*
	 * This method will set the values to AwardPaymentSchedules attributes and
	 * finally returns AwardPaymentSchedules Xml object
	 */
	private AwardPaymentSchedules getAwardPaymentSchedules() {
		AwardPaymentSchedules awardPaymentSchedules = AwardPaymentSchedules.Factory
				.newInstance();
		String paymentScheduleIndicator = award.getPaymentScheduleIndicator();
		List<PaymentSchedule> paymentSchedulesList = new LinkedList<>();
		if (paymentScheduleIndicator != null
				&& !paymentScheduleIndicator.equals(EMPTY_STRING)) {
			paymentScheduleIndicator = paymentScheduleIndicator.length() == 1 ? paymentScheduleIndicator
					: paymentScheduleIndicator.substring(1, 2);
			if (paymentScheduleIndicator.equals(INDICATOR_CONSTANT)) {
				List<AwardPaymentSchedule> paymentScheduleItems = award
						.getPaymentScheduleItems();
				PaymentSchedule paymentSchedule;
				for (AwardPaymentSchedule awardPaymentSchedule : paymentScheduleItems) {
					paymentSchedule = getAwardPaymentSchedule(awardPaymentSchedule);
					paymentSchedulesList.add(paymentSchedule);
				}
			}
		}
		awardPaymentSchedules.setPaymentScheduleArray(paymentSchedulesList
				.toArray(new PaymentSchedule[0]));
		return awardPaymentSchedules;
	}

	/*
	 * This method will set the values to AwardTransferringSponsors attributes
	 * and finally returns AwardTransferringSponsors Xml object
	 */
	private AwardTransferringSponsors getAwardTransferringSponsors() {
		AwardTransferringSponsors transferringSponsors = AwardTransferringSponsors.Factory.newInstance();
		List<TransferringSponsor> transferringSponsorList = new LinkedList<>();
		List<AwardTransferringSponsor> awardTransferringSponsorList = award.getAwardTransferringSponsors();
        List<AwardTransferringSponsor> prevTransferringSponsorList = prevAward == null?new ArrayList<>():
                                                                        prevAward.getAwardTransferringSponsors();
		TransferringSponsor transferringSponsor;
		for (AwardTransferringSponsor awardTransferringSponsor : awardTransferringSponsorList) {
		    if(checkSponsorCodeChange(awardTransferringSponsor, prevTransferringSponsorList)){
		        transferringSponsor = getAwardTransferringSponsor(awardTransferringSponsor);
		    }else{
		        transferringSponsor = getAwardTransferringSponsor(awardTransferringSponsor,SPONSOR_CODE_ADDED_INDICATOR);
		    }
            transferringSponsorList.add(transferringSponsor);
		}
		for (AwardTransferringSponsor awardTransferringSponsor : prevTransferringSponsorList) {
            if(!checkSponsorCodeChange(awardTransferringSponsor, awardTransferringSponsorList)){
                transferringSponsor = getAwardTransferringSponsor(awardTransferringSponsor,SPONSOR_CODE_DELETED_INDICATOR);
                transferringSponsorList.add(transferringSponsor);
            }
		}
		transferringSponsors.setTransferringSponsorArray(transferringSponsorList.toArray(new TransferringSponsor[0]));
		return transferringSponsors;
	}

    private boolean checkSponsorCodeChange(AwardTransferringSponsor awardTransferringSponsor,
            List<AwardTransferringSponsor> awardTransferringSponsors) {
        for (AwardTransferringSponsor currentAwardTransferringSponsor : awardTransferringSponsors) {
            if( awardTransferringSponsor.getSponsorCode().equals(currentAwardTransferringSponsor.getSponsorCode())){
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * This method will set the values to transferring sponsor XmlObject and
     * return it
     * </p>
     * 
     * @param awardTransferringSponsor
     *            it contains information about the award transferring sponsor
     *            {@link AwardTransferringSponsor}
     * @return awardTransferringSponsor xmlObject
     */
    protected TransferringSponsor getAwardTransferringSponsor(
            AwardTransferringSponsor awardTransferringSponsor, String prefix) {
        TransferringSponsor transferringSponsor = TransferringSponsor.Factory.newInstance();
        if (awardTransferringSponsor.getAwardNumber() != null) {
            transferringSponsor.setAwardNumber(awardTransferringSponsor
                    .getAwardNumber());
        }
        if (awardTransferringSponsor.getSequenceNumber() != null) {
            transferringSponsor.setSequenceNumber(awardTransferringSponsor
                    .getSequenceNumber());
        }
        transferringSponsor.setSponsorCode(prefix+awardTransferringSponsor.getSponsorCode());
        Sponsor sponsor = awardTransferringSponsor.getSponsor();
        if (sponsor != null && sponsor.getSponsorName() != null) {
            transferringSponsor.setSponsorDescription(sponsor.getSponsorName());
        }
        return transferringSponsor;
    }
    
    /*
	 * This method will set the values to AwardOtherDatas attributes and finally
	 * returns AwardOtherDatas Xml object
	 */
	private AwardOtherDatas getAwardOtherDatas() {
		AwardOtherDatas awardOtherDatas = AwardOtherDatas.Factory.newInstance();
		List<AwardCustomData> awardCustomDataList = award
				.getAwardCustomDataList();
		List<OtherData> otherDatas = new ArrayList<>();
		OtherData otherData = null;
		int count =0;
        for (AwardCustomData awardCustomData : awardCustomDataList) {
			otherData = OtherData.Factory.newInstance();
			String columnValue = awardCustomData.getValue();
			if (awardCustomData.getCustomAttribute() != null
					&& awardCustomData.getCustomAttribute().getName() != null) {
				otherData.setColumnName(awardCustomData.getCustomAttribute()
						.getName());
			}
			if (columnValue != null) {
				if (prevAward != null && !prevAward.getAwardCustomDataList().isEmpty()) {
					String prevColumValue = prevAward.getAwardCustomDataList().get(count).getValue();
					columnValue = getModColumValue(columnValue,
							prevColumValue);
				} 
			otherData.setColumnValue(columnValue);
			}
			otherDatas.add(otherData);
			count++;
		}
		awardOtherDatas.setOtherDataArray(otherDatas.toArray(new OtherData[0]));
		return awardOtherDatas;
	}

	private String getModColumValue(String columnValue,
			String prevColumValue) {
		String columnValueMod = null;
		if (columnValue != null) {
			if (prevColumValue == null
					|| !columnValue.equals(prevColumValue)) {
				columnValueMod = new StringBuilder(START_ASTERISK_SPACE_INDICATOR).append(
						columnValue).toString();

			} else {
				columnValueMod = columnValue;
			}
		}
		return columnValueMod;
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
			printRequirement.setAddressListRequired(REQUIRED);
			printRequirement.setCloseoutRequired(REQUIRED);
			printRequirement.setCommentsRequired(REQUIRED);
			printRequirement.setCostSharingRequired(REQUIRED);
			printRequirement.setEquipmentRequired(REQUIRED);
			printRequirement.setFlowThruRequired(REQUIRED);
			printRequirement.setForeignTravelRequired(REQUIRED);
			printRequirement.setHierarchyInfoRequired(REQUIRED);
			printRequirement.setIndirectCostRequired(REQUIRED);
			printRequirement.setPaymentRequired(REQUIRED);
			printRequirement.setProposalDueRequired(REQUIRED);
			printRequirement.setSubcontractRequired(REQUIRED);
			printRequirement.setScienceCodeRequired(REQUIRED);
			printRequirement.setSpecialReviewRequired(REQUIRED);
			printRequirement.setTermsRequired(REQUIRED);
			printRequirement.setTechnicalReportingRequired(REQUIRED);
			printRequirement.setReportingRequired(REQUIRED);
			printRequirement.setCurrentDate(dateTimeService
					.getCurrentCalendar());
			printRequirement
					.setSignatureRequired(getSignatureRequired(reportParameters));
		}
		return printRequirement;
	}

	/*
	 * This method will get the signature required if input parameter signature
	 * required is true then 1 else 0
	 */
	private String getSignatureRequired(Map<String, Object> reportParameters) {
		String signatureRequired;
		if (reportParameters.get(SIGNATURE_REQUIRED) != null
				&& ((Boolean) reportParameters.get(SIGNATURE_REQUIRED))) {
			signatureRequired = REQUIRED;
		} else {
			signatureRequired = NOT_REQUIRED;
		}
		return signatureRequired;
	}

	public VersionHistoryService getVersionHistoryService() {
		return versionHistoryService;
	}

	public void setVersionHistoryService(
			VersionHistoryService versionHistoryService) {
		this.versionHistoryService = versionHistoryService;
	}
}
