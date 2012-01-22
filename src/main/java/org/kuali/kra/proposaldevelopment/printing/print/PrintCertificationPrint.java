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
package org.kuali.kra.proposaldevelopment.printing.print;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;

/**
 * This class provides the implementation for printing Print Certification
 * Report. It generates XML that conforms with Certification Report XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 */
public class PrintCertificationPrint extends AbstractPrint {

	/**
	 * This method fetches the XSL style-sheets required for transforming the
	 * generated XML into PDF.
	 * 
	 * @return {@link ArrayList}} of {@link Source} XSLs
	 */
	public List<Source> getXSLTemplates() {
		ArrayList<Source> sourceList = PrintingUtils
				.getXSLTforReport(ProposalDevelopmentPrintingService.PRINT_CERTIFICATION_REPORT);
		return sourceList;
	}

//	/**
//	 * This method generates the XML that conforms to certification Report XSD
//	 * returns it as {@link InputStream}
//	 * 
//	 * @return {@link InputStream} of generated XML
//	 * @throws PrintingException
//	 *             in case of any errors occur during XML generation
//	 */
//	public Map<String, byte[]> renderXML() throws PrintingException {
//		Map<String, InputStream> xmlStreamMap = new LinkedHashMap<String, InputStream>();
//		Map<String, XmlObject> xmlObjectMap = getXmlStream().generateXmlStream(
//				getDocument(), getReportParameters());
//		for (String xmlObjectKey : xmlObjectMap.keySet()) {
//			xmlStreamMap.put(xmlObjectKey, xmlObjectMap.get(xmlObjectKey)
//					.newInputStream());
//		}
//		return xmlStreamMap;
//	}
}
