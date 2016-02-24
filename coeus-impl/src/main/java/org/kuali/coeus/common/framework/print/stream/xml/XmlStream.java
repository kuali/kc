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
package org.kuali.coeus.common.framework.print.stream.xml;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Map;

/**
 * This class is used for generating XML stream for report generation of any
 * document.
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
	Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters);
}
