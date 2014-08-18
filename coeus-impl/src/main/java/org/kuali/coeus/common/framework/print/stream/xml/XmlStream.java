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
package org.kuali.coeus.common.framework.print.stream.xml;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Map;

/**
 * This class is used for generating XML stream for report generation of any
 * document like {@link AwardDocument}, {@link BudgetDocument} etc
 * 
 * @author
 * 
 */
public interface XmlStream {

	/**
	 * This method generates XML for a given {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} based
	 * on the parameters provided in a {@link Map} and returns an
	 * {@link XmlObject} of the generated XML.
	 * 
	 * @param printableBusinessObject
	 *            for which XMl is to be generated
	 * @param reportParameters
	 *            {@link Map} of various parameters required for XML generation
	 * @return Map consisting of XML Objects mapped to bookmarks
	 * @throws PrintingException
	 *             in case of any errors occur during report generation
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters);

	/**
	 * Getter for {@link BusinessObjectService}
	 * 
	 * @return {@link BusinessObjectService}
	 */
	public BusinessObjectService getBusinessObjectService();

	/**
	 * Setter for {@link BusinessObjectService}
	 * 
	 * @param businessObjectService
	 */
	public void setBusinessObjectService(
			BusinessObjectService businessObjectService);

	/**
	 * @return the dateTimeService
	 */
	public DateTimeService getDateTimeService();

	/**
	 * @param dateTimeService
	 *            the dateTimeService to set
	 */
	public void setDateTimeService(DateTimeService dateTimeService);
}
