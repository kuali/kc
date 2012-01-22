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
package org.kuali.kra.printing.print;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.util.watermark.Watermarkable;

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
	private KraPersistableBusinessObjectBase printableBusinessObject;
	 // reportParameters used to pass parameters to CommitteeTemplatePrint.java
	private Map<String, Object> reportParameters;
	private Map<String, byte[]> attachments;

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
     * Fetches the {@link ResearchDocumentBase}
     * 
     * @return {@link ResearchDocumentBase} document
     */
    public KraPersistableBusinessObjectBase getPrintableBusinessObject() {
        return printableBusinessObject;
    }

	/**
	 * @param printableBusinessObject
	 *            the document to set
	 */
	public void setPrintableBusinessObject(KraPersistableBusinessObjectBase printableBusinessObject) {
		this.printableBusinessObject = printableBusinessObject;
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

	public Map<String, byte[]> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, byte[]> attachments) {
		this.attachments = attachments;
	}

	protected byte[] getBytes(XmlObject xmlObject) {
		byte[] xmlBytes = null;
		String xmlString = xmlObject.xmlText();
		xmlBytes = xmlString.getBytes();
		return xmlBytes;
	}

	/**
	 * This method generates the XML that conforms to Delta Report XSD returns
	 * it as {@link InputStream}
	 * 
	 * @return {@link InputStream} of generated XML
	 * @throws PrintingException
	 *             in case of any errors occur during XML generation
	 */
	public Map<String, byte[]> renderXML() throws PrintingException {
		Map<String, byte[]> xmlStreamMap = new LinkedHashMap<String, byte[]>();
		Map<String, XmlObject> xmlObjectMap = getXmlStream().generateXmlStream(
				getPrintableBusinessObject(), getReportParameters());
		for (String xmlObjectKey : xmlObjectMap.keySet()) {
			xmlStreamMap.put(xmlObjectKey, getBytes(xmlObjectMap
					.get(xmlObjectKey)));
		}
		return xmlStreamMap;
	}
	
	/**
	 * This method should be overridden if any printable artifacts wants to send Templates with separate bookmarks.
	 */
    public Map<String,Source> getXSLTemplateWithBookmarks(){
        return null;
    }
    public List<Source> getXSLTemplates(){
        return null;
    }
	/**
	 * This method for checking watermark is enable or disable
	 * @see org.kuali.kra.printing.Printable#isWatermarkEnabled()
	 */
    public boolean isWatermarkEnabled(){
        return false;
    }
    /**
     * 
     *This method for getting the watermark object 
     *with respect to the appropriate document.
     */
    public Watermarkable getWatermarkable(){
        if(isWatermarkEnabled()){
            throw new RuntimeException("Watermarkable not implemented");
        }else{
            return null;
        }
    }

}
