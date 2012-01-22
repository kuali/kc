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

import noNamespace.AwardTransactionType;
import noNamespace.SchoolInfoType2;
import noNamespace.AwardType.AwardTransactionInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * <p>
 * This class will contain all common methods that can be used across all XML
 * generator streams related to Award budget hierarchy and Award budget history .
 * All award report XML stream implementations need to extend and use the
 * functions defined in this class.
 * </p>
 * 
 * @author
 * 
 */
public abstract class AwardBudgetBaseStream implements XmlStream {

	private static final Log LOG = LogFactory.getLog(AwardBudgetBaseStream.class);

	protected BusinessObjectService businessObjectService = null;
	protected DateTimeService dateTimeService = null;
	private static final String SCHOOL_NAME = "SCHOOL_NAME";
	private static final String SCHOOL_ACRONYM = "SCHOOL_ACRONYM";
	protected static final String AWARD_NUMBER_PARAMETER = "awardNumber";
	protected static final String DOCUMENT_NUMBER = "documentNumber";
	private ParameterService parameterService;
	/**
	 * <p>
	 * This method will set the values to award transaction info xml object
	 * attributes
	 * </p>.
	 * 
	 * @return AwardTransactionInfo xml object
	 */
	protected AwardTransactionInfo getAwardTransactiontInfo(Award award) {
		List<AwardTransactionType> awardTransactionTypeList = new ArrayList<AwardTransactionType>();
		AwardTransactionInfo awardTransactionInfo = AwardTransactionInfo.Factory
				.newInstance();
		AwardTransactionType awardTransactionType = null;
		for (org.kuali.kra.award.home.AwardAmountInfo awardAmount : award
				.getAwardAmountInfos()) {
			AwardAmountTransaction awardAmountTransaction = getAwardAmountTransaction(awardAmount
					.getTimeAndMoneyDocumentNumber());
			if (awardAmountTransaction != null) {
				awardTransactionType = AwardTransactionType.Factory
						.newInstance();
				setAwardAmountTransaction(awardTransactionType,
						awardAmountTransaction);
				awardTransactionTypeList.add(awardTransactionType);
				break;
			}
		}
		awardTransactionInfo.setTransactionInfoArray(awardTransactionTypeList
				.toArray(new AwardTransactionType[0]));
		return awardTransactionInfo;
	}
    private String getProposalParameterValue(String param) {
        return parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, param);
    }
	/*
	 * This method will set the values to award amount transaction xml object
	 * attributes
	 */
	private void setAwardAmountTransaction(
			AwardTransactionType awardTransactionType,
			AwardAmountTransaction awardAmountTransaction) {
		if (awardAmountTransaction.getAwardNumber() != null) {
			awardTransactionType.setAwardNumber(awardAmountTransaction
					.getAwardNumber());
		}
		if (awardAmountTransaction.getTransactionTypeCode() != null) {
			awardTransactionType.setTransactionTypeCode(awardAmountTransaction
					.getTransactionTypeCode());
		}
		if (awardAmountTransaction.getAuthorPersonName() != null) {
			awardTransactionType.setTransactionTypeDesc(awardAmountTransaction
					.getAwardTransactionType().getDescription());
		}
		if (awardAmountTransaction.getComments() != null) {
			awardTransactionType.setComments(awardAmountTransaction
					.getComments());
		}
		if (awardAmountTransaction.getNoticeDate() != null) {
			awardTransactionType.setNoticeDate(dateTimeService
					.getCalendar(awardAmountTransaction.getNoticeDate()));
		}
	}

	/*
	 * This method will get the AwardAmountTransaction for given
	 * timeAndMoneyDocument Number
	 */
	private AwardAmountTransaction getAwardAmountTransaction(
			String timeAndMoneyDocNumber) {
		AwardAmountTransaction awardAmountTransaction = null;
		Map<String, String> timeAndMoneyMap = new HashMap<String, String>();
		// TODO Time Money Doc number - to be fixed
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

	/**
	 * <p>
	 * This method will set the values to school info attributes and finally
	 * returns SchoolInfoType XmlObject.
	 * </p>
	 * 
	 * @return returns SchoolInfoType XmlObject
	 */
	protected SchoolInfoType2 getSchoolInfoType() {
		SchoolInfoType2 schoolInfoType = SchoolInfoType2.Factory.newInstance();
		String schoolName = getAwardParameterValue(SCHOOL_NAME);
		String schoolAcronym = getProposalParameterValue(SCHOOL_ACRONYM);
		if (schoolName != null) {
			schoolInfoType.setSchoolName(schoolName);
		}
		if (schoolAcronym != null) {
			schoolInfoType.setAcronym(schoolAcronym);
		}
		return schoolInfoType;
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	private String getAwardParameterValue(String param) {
		String value = null;
		try {
			value = PrintingUtils.getParameterValue(param);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return value;
	}
    /**
     * Gets the parameterService attribute. 
     * @return Returns the parameterService.
     */
    public ParameterService getParameterService() {
        return parameterService;
    }
    /**
     * Sets the parameterService attribute value.
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
