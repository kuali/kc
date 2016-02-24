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

import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.printing.schema.AwardTransactionType;
import org.kuali.kra.printing.schema.AwardType.AwardTransactionInfo;
import org.kuali.kra.printing.schema.SchoolInfoType2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
