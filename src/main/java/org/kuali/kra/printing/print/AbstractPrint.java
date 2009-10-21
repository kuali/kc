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
package org.kuali.kra.printing.print;

import java.util.Map;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.xmlstream.XmlStream;

/**
 * This class provides all the objects required for printing reports. It
 * provides methods for fetching XML generator {@link XmlStream},{@link ResearchDocumentBase},
 * {@link Map} of parameters required for printing.
 * 
 * @author
 * 
 */
public abstract class AbstractPrint implements Printable {

	private XmlStream xmlStream;
	protected ResearchDocumentBase document;
	private Map<String, Object> reportParameters;

	/**
	 * @return the xmlStream
	 */
	public XmlStream getXmlStream() {
		return xmlStream;
	}

	/**
	 * @param xmlStream
	 *            the xmlStream to set
	 */
	public void setXmlStream(XmlStream xmlStream) {
		this.xmlStream = xmlStream;
	}

	/**
	 * @return the document
	 */
	public abstract ResearchDocumentBase getDocument();

	/**
	 * @param document
	 *            the document to set
	 */
	public void setDocument(ResearchDocumentBase document) {
		this.document = document;
	}

	/**
	 * @return the reportParameters
	 */
	public Map<String, Object> getReportParameters() {
		return reportParameters;
	}

	/**
	 * @param reportParameters
	 *            the reportParameters to set
	 */
	public void setReportParameters(Map<String, Object> reportParameters) {
		this.reportParameters = reportParameters;
	}

}
