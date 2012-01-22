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
package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

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
			bdDoc = KraServiceLocator.getService(
					S2SBudgetCalculatorService.class).getFinalBudgetVersion(
					proposalDevelopmentDocument);
		} catch (S2SException e) {
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
