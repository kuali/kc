/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.budget.service.ProposalBudgetService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * This class will contain all common methods that can be used across Proposal
 * Development XML generator streams . All those report XML stream
 * implementations need to extend and use the functions defined in this class.
 * 
 */
public abstract class ProposalBaseStream implements XmlStream {

	private final static Log LOG = LogFactory
			.getLog(ProposalBaseStream.class);
	private DateTimeService dateTimeService;
	private BusinessObjectService businessObjectService;
	private AwardService awardService;


	/**
	 * This method fetches the final/latest Budget associated with the given
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 * @return {@link Budget} final/latest version
	 */
	protected Budget getBudget(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		BudgetDocument bdDoc = null;
		try {
			bdDoc = KcServiceLocator.getService(
                    ProposalBudgetService.class).getFinalBudgetVersion(
					proposalDevelopmentDocument);
		} catch (WorkflowException e) {
			LOG.error("Error while fetching final Budget Version", e);
		}

		Budget budget = null;
		if (bdDoc != null) {
			budget = bdDoc.getBudget();
		}
		return budget;
	}
	
	protected Award getAward(String currentAwardNumber) {
	    List<Award> awards = getAwardService().findAwardsForAwardNumber(currentAwardNumber);
	    return awards.isEmpty()?null:awards.get(awards.size()-1);
	}

	protected AwardAmountInfo getMaxAwardAmountInfo(Award award) {
		Integer highestSequenceNumber = 0;
		Long highestAwardAmountInfoId = 0L;
		AwardAmountInfo higeshSequenceAwarAmountInfo = null;
			for(AwardAmountInfo amountInfo: award.getAwardAmountInfos()){
				if(highestSequenceNumber < amountInfo.getSequenceNumber()){
					higeshSequenceAwarAmountInfo = amountInfo;
					highestSequenceNumber = amountInfo.getSequenceNumber();
					highestAwardAmountInfoId = amountInfo.getAwardAmountInfoId();
				}else if(highestSequenceNumber == amountInfo.getSequenceNumber() 
							&& highestAwardAmountInfoId < amountInfo.getAwardAmountInfoId()){
					higeshSequenceAwarAmountInfo = amountInfo;
					highestSequenceNumber = amountInfo.getSequenceNumber();
					highestAwardAmountInfoId = amountInfo.getAwardAmountInfoId();
				}
			}
		return higeshSequenceAwarAmountInfo;
	}

    /**
     *
     * This method computes the number of months between any 2 given {@link java.sql.Date} objects
     *
     * @param dateStart starting date.
     * @param dateEnd end date.
     *
     * @return number of months between the start date and end date.
     */
    public ScaleTwoDecimal getNumberOfMonths(Date dateStart, Date dateEnd) {
        ScaleTwoDecimal monthCount = ScaleTwoDecimal.ZERO;
        int fullMonthCount = 0;

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(dateStart);
        endDate.setTime(dateEnd);

        startDate.clear(Calendar.HOUR);
        startDate.clear(Calendar.MINUTE);
        startDate.clear(Calendar.SECOND);
        startDate.clear(Calendar.MILLISECOND);

        endDate.clear(Calendar.HOUR);
        endDate.clear(Calendar.MINUTE);
        endDate.clear(Calendar.SECOND);
        endDate.clear(Calendar.MILLISECOND);

        if (startDate.after(endDate)) {
            return ScaleTwoDecimal.ZERO;
        }
        int startMonthDays = startDate.getActualMaximum(Calendar.DATE) - startDate.get(Calendar.DATE);
        startMonthDays++;
        int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
        BigDecimal startMonthFraction = BigDecimal.valueOf(startMonthDays).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(startMonthMaxDays).setScale(2, RoundingMode.HALF_UP), RoundingMode.HALF_UP);

        int endMonthDays = endDate.get(Calendar.DATE);
        int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

        BigDecimal endMonthFraction = BigDecimal.valueOf(endMonthDays).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(endMonthMaxDays).setScale(2, RoundingMode.HALF_UP), RoundingMode.HALF_UP);

        startDate.set(Calendar.DATE, 1);
        endDate.set(Calendar.DATE, 1);

        while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
            fullMonthCount++;
        }
        fullMonthCount = fullMonthCount - 1;
        monthCount = monthCount.add(new ScaleTwoDecimal(fullMonthCount)).add(new ScaleTwoDecimal(startMonthFraction)).add(new ScaleTwoDecimal(endMonthFraction));
        return monthCount;
    }

	/**
	 * @return the dateTimeService
	 */
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	/**
	 * @param dateTimeService
	 *            the dateTimeService to set
	 */
	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	/**
	 * @return the businessObjectService
	 */
	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	/**
	 * @param businessObjectService
	 *            the businessObjectService to set
	 */
	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

    /**
     * Sets the awardService attribute value.
     * @param awardService The awardService to set.
     */
    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    /**
     * Gets the awardService attribute. 
     * @return Returns the awardService.
     */
    public AwardService getAwardService() {
        return awardService;
    }
}
