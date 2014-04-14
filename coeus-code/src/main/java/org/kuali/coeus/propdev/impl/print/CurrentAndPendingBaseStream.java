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
package org.kuali.coeus.propdev.impl.print;

import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This class will contain all common methods that can be used across
 * Institution Proposal XML generator streams . All those report XML stream
 * implementations need to extend and use the functions defined in this class.
 * 
 */
public abstract class CurrentAndPendingBaseStream implements XmlStream {

    @Autowired
    @Qualifier("dateTimeService")
    protected DateTimeService dateTimeService;

    @Autowired
    @Qualifier("businessObjectService")
	protected BusinessObjectService businessObjectService = null;

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
