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
package org.kuali.kra.institutionalproposal.printing.xmlstream;

import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class will contain all common methods that can be used across
 * Institution Proposal XML generator streams . All those report XML stream
 * implementations need to extend and use the functions defined in this class.
 * 
 */
public abstract class InstitutionalProposalBaseStream implements XmlStream {
	protected DateTimeService dateTimeService;
	protected BusinessObjectService businessObjectService = null;
	protected String personName;
	public static final String PERSON_ID = "personId";
	
	protected static final int PROPOSAL_TYPE_NEW = 1;
	protected static final int PROPOSAL_TYPE_CONTINUATION = 2;
	protected static final int PROPOSAL_TYPE_RESUBMISSION = 4;
	protected static final int PROPOSAL_TYPE_REVISION = 5;
	protected static final int PROPOSAL_TYPE_TASK_ORDER = 6;
	protected static final int PROPOSAL_TYPE_CODE_8 = 8;
	protected static final int PROPOSAL_STATUS_PENDING = 1;
	protected static final int PROPOSAL_STATUS_REVISION_REQUESTED = 6;
	protected static final int AWARD_STATUS_ACTIVE = 1;
	protected static final int AWARD_STATUS_PENDING = 3;

	

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
