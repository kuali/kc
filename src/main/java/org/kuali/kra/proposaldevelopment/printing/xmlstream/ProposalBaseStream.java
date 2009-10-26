/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.apache.log4j.Logger;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;

/**
 * This class will contain all common methods that can be used across Proposal
 * Development XML generator streams . All those report XML stream
 * implementations need to extend and use the functions defined in this class.
 * 
 */
public abstract class ProposalBaseStream implements XmlStream {
	
	private final static Logger LOG=Logger.getLogger(ProposalBaseStream.class);
	protected DateTimeService dateTimeService;
	protected BusinessObjectService businessObjectService = null;

	/**
	 * This method fetches the final/latest Budget associated with the given {@link ProposalDevelopmentDocument}
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
}
