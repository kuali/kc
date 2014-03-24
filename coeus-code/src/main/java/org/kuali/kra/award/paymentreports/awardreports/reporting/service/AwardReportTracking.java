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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.util.watermark.Watermarkable;

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
public class AwardReportTracking implements Printable,Cloneable {

    private XmlStream xmlStream;
    private KcPersistableBusinessObjectBase printableBusinessObject;
    private Map<String, Object> reportParameters;
    private Map<String, byte[]> attachments;
    private static final Log LOG = LogFactory.getLog(AwardReportTracking.class);
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
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

}
