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
package org.kuali.coeus.common.framework.print;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.print.watermark.Watermarkable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.xml.transform.Source;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides all the objects required for printing reports. It
 * provides methods for fetching XML generator {@link XmlStream},{@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase},
 * {@link Map} of parameters required for printing.
 * 
 * @author
 * 
 */
public abstract class AbstractPrint implements Printable {

	private XmlStream xmlStream;
	private KcPersistableBusinessObjectBase printableBusinessObject;
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
     * Fetches the {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase}
     * 
     * @return {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} document
     */
    public KcPersistableBusinessObjectBase getPrintableBusinessObject() {
        return printableBusinessObject;
    }

	/**
	 * @param printableBusinessObject
	 *            the document to set
	 */
	public void setPrintableBusinessObject(KcPersistableBusinessObjectBase printableBusinessObject) {
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
	 * @see org.kuali.coeus.common.framework.print.Printable#isWatermarkEnabled()
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
