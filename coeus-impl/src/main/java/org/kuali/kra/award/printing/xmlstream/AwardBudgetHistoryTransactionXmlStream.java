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

import org.apache.commons.lang3.ObjectUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.AwardPrintParameters;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.printing.schema.AmountInfoType;
import org.kuali.kra.printing.schema.AwardNoticeDocument;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice;
import org.kuali.kra.printing.schema.AwardType;
import org.kuali.kra.printing.schema.AwardType.AwardAmountInfo;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.*;

/**
 * This class generates XML that conforms with the XSD related to Award Budget
 * History Transaction Report. The data for XML is derived from
 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 * 
 * 
 */
public class AwardBudgetHistoryTransactionXmlStream extends AwardBudgetBaseStream {

	private static final String AWARD_AMOUNT_INFO_MODIFIED_VALUE = "1";
	private VersionHistoryService versionHistoryService;

	/**
	 * This method generates XML for Award Budget History Transaction Report. It
	 * uses data passed in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML
	 * nodes. The XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> budgetHierarchyMap = new HashMap<String, XmlObject>();
		Award award = (Award) printableBusinessObject;
		Integer sequenceNumber = (Integer) reportParameters.get(AwardPrintParameters.SEQUENCE_NUMBER.getAwardPrintParameter());
		int transactionId = (Integer) reportParameters
				.get(AwardPrintParameters.TRANSACTION_ID_INDEX
						.getAwardPrintParameter());
		award = getAwardVersion(award.getAwardNumber(), sequenceNumber);
		AwardNoticeDocument awardNoticeDocument = AwardNoticeDocument.Factory
				.newInstance();
		AwardNotice awardNotice = AwardNotice.Factory.newInstance();
		awardNotice.setAward(getAwardType(award, transactionId));
		awardNotice.setSchoolInfo(getSchoolInfoType());
		awardNoticeDocument.setAwardNotice(awardNotice);
		budgetHierarchyMap.put(AwardPrintType.AWARD_BUDGET_HISTORY_TRANSACTION
				.getAwardPrintType(), awardNoticeDocument);
		return budgetHierarchyMap;
	}
	
	/**
	 * 
	 * Load Award BO using awardNumber and sequenceNumber
	 * @param awardNumber
	 * @param sequenceNumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
    private Award getAwardVersion(String awardNumber, Integer sequenceNumber) {
	    Map<String, Object> fieldValues = new HashMap<String, Object>();
	    fieldValues.put("awardNumber", awardNumber);
	    fieldValues.put("sequenceNumber", sequenceNumber);
	    Collection<Award> awards = businessObjectService.findMatching(Award.class, fieldValues);
	    return awards.iterator().next();
	}

	/*
	 * This method will set the values to Award type xml object attributes. It
	 * will set the following values like award amount info , Transaction info .
	 * 
	 */
	private AwardType getAwardType(Award award, int transactionIdx) {
		AwardType awardType = AwardType.Factory.newInstance();
		awardType.setAwardAmountInfo(getAwardAmountInfo(award,
				transactionIdx));
		awardType.setAwardTransactionInfo(getAwardTransactiontInfo(award));
		return awardType;
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes.
	 */
	private AwardAmountInfo getAwardAmountInfo(Award award,
			int transactionIdx) {

		AmountInfoType amountInfoType = null;
		AwardAmountInfo awardAmountInfo = AwardAmountInfo.Factory.newInstance();
		List<AmountInfoType> amountInfoTypes = new ArrayList<AmountInfoType>();
				
		org.kuali.kra.award.home.AwardAmountInfo amountInfo = award.getAwardAmountInfos().get(transactionIdx);
		if (amountInfo != null) {
    		amountInfoType = setAwardAmountInfo(award, amountInfo,
    				amountInfo.getTransactionId());
    		org.kuali.kra.award.home.AwardAmountInfo prevAwardAmount = getPrevAwardAmountInfo(
    				award, amountInfo.getTransactionId(), award.getAwardNumber());
    		setAwardAmountInfoModifiedValues(amountInfoType,
    				amountInfo, prevAwardAmount);
    		amountInfoTypes.add(amountInfoType);
    
    		awardAmountInfo.setAmountInfoArray(amountInfoTypes
    				.toArray(new AmountInfoType[0]));
		}
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
			if (prevAwardAmount.getObligationExpirationDate()!=null && !prevAwardAmount.getObligationExpirationDate().equals(
					awrdAmountInfo.getObligationExpirationDate())) {
				amountInfoType
						.setObligationExpDateModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (prevAwardAmount.getCurrentFundEffectiveDate()!=null && !prevAwardAmount.getCurrentFundEffectiveDate().equals(
					awrdAmountInfo.getCurrentFundEffectiveDate())) {
				amountInfoType
						.setCurrentFundEffectiveDateModified(AWARD_AMOUNT_INFO_MODIFIED_VALUE);
			}
			if (prevAwardAmount.getFinalExpirationDate()!=null && !prevAwardAmount.getFinalExpirationDate().equals(
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
	    //if transactionId is null the current item is the first one and therefore there
	    //is no previous transactions to find
	    if (transactionId != null) {
    	    org.kuali.kra.award.home.AwardAmountInfo prevAwardAmount = award.getAwardAmountInfos().get(0);
    	    for (org.kuali.kra.award.home.AwardAmountInfo curInfo : award.getAwardAmountInfos()) {
    	        if (ObjectUtils.equals(curInfo.getTransactionId(), transactionId)) {
    	            break;
    	        }
    	        if (curInfo.getTransactionId() != null) {
    	            prevAwardAmount = curInfo;
    	        }
    	    }
    		return prevAwardAmount;
	    } else {
	        return null;
	    }
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes based on selected award number and transaction id.
	 */
	private AmountInfoType setAwardAmountInfo(Award award,
			org.kuali.kra.award.home.AwardAmountInfo awardAmount,
			Long transacationIdValue) {
		AmountInfoType amountInfoType = AmountInfoType.Factory.newInstance();
		amountInfoType.setTransactionDate(dateTimeService
				.getCalendar(awardAmount.getUpdateTimestamp()));
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
		}else{
		    amountInfoType.setAnticipatedChange(ScaleTwoDecimal.ZERO.bigDecimalValue());
		}
		if (awardAmount.getAnticipatedChangeDirect() != null) {
			amountInfoType.setAnticipatedChangeDirect(awardAmount.getAnticipatedChangeDirect().bigDecimalValue());
		}else{
	          amountInfoType.setAnticipatedChangeDirect(ScaleTwoDecimal.ZERO.bigDecimalValue());

		}
		if (awardAmount.getAnticipatedChangeIndirect() != null) {
			amountInfoType.setAnticipatedChangeIndirect(awardAmount.getAnticipatedChangeIndirect().bigDecimalValue());
		}else{
	          amountInfoType.setAnticipatedChangeIndirect(ScaleTwoDecimal.ZERO.bigDecimalValue());
		}
		if (awardAmount.getAntDistributableAmount() != null) {
			amountInfoType.setAnticipatedDistributableAmt(awardAmount
					.getAntDistributableAmount().bigDecimalValue());
		}else{
		    amountInfoType.setAnticipatedDistributableAmt(ScaleTwoDecimal.ZERO.bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalAmount() != null) {
			amountInfoType.setAnticipatedTotalAmt(awardAmount
					.getAnticipatedTotalAmount().bigDecimalValue());
		}else{
		    amountInfoType.setAnticipatedTotalAmt(ScaleTwoDecimal.ZERO.bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalDirect() != null) {
			amountInfoType.setAnticipatedTotalDirect(awardAmount
					.getAnticipatedTotalDirect().bigDecimalValue());
		}else{
		    amountInfoType.setAnticipatedTotalDirect(ScaleTwoDecimal.ZERO.bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalIndirect() != null) {
			amountInfoType.setAnticipatedTotalIndirect(awardAmount
					.getAnticipatedTotalIndirect().bigDecimalValue());
		}else{
		    amountInfoType.setAnticipatedTotalIndirect(ScaleTwoDecimal.ZERO.bigDecimalValue());
		}
		amountInfoType.setAwardNumber(award.getAwardNumber());
		if(awardAmount.getObligatedChange()!=null){
		    amountInfoType.setObligatedChange(awardAmount.getObligatedChange().bigDecimalValue());
		}else{
		    amountInfoType.setObligatedChange(ScaleTwoDecimal.ZERO.bigDecimalValue());
		}
		if(awardAmount.getObligatedChangeDirect()!=null){
		    amountInfoType.setObligatedChangeDirect(awardAmount.getObligatedChangeDirect().bigDecimalValue());
        }else{
            amountInfoType.setObligatedChangeDirect(ScaleTwoDecimal.ZERO.bigDecimalValue());
        }
		if(awardAmount.getObligatedChangeIndirect()!=null){
		    amountInfoType.setObligatedChangeIndirect(awardAmount.getObligatedChangeIndirect().bigDecimalValue());
        }else{
            amountInfoType.setObligatedChangeIndirect(ScaleTwoDecimal.ZERO.bigDecimalValue());
        }
		if(awardAmount.getObliDistributableAmount()!=null){
		    amountInfoType.setObligatedDistributableAmt(awardAmount.getObliDistributableAmount().bigDecimalValue());
        }else{
            amountInfoType.setObligatedDistributableAmt(ScaleTwoDecimal.ZERO.bigDecimalValue());
        }
		if (awardAmount.getFinalExpirationDate() != null) {
		    amountInfoType.setFinalExpirationDate(dateTimeService.getCalendar(
		            awardAmount.getFinalExpirationDate()));
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
