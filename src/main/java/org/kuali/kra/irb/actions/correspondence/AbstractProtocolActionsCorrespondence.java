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
package org.kuali.kra.irb.actions.correspondence;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.print.CorrespondenceXmlStream;
import org.kuali.kra.irb.actions.print.ProtocolPrintWatermark;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.util.watermark.Watermarkable;

/**
 * 
 * This class needs to be extended by specific action correspondence objects.
 */
public abstract class AbstractProtocolActionsCorrespondence extends AbstractPrint {
    
    private Protocol protocol;
    
    public void setProtocol(Protocol protcol) {
        this.protocol = protcol;
    }
    
    public Protocol getProtocol() {
        return this.protocol;
    }
    @Override
    public KraPersistableBusinessObjectBase getPrintableBusinessObject() {
        return getProtocol();
    }
    private ProtocolActionTypeToCorrespondenceTemplateService getProtocolActionTypeToCorrespondenceTemplateService() {
        return KraServiceLocator.getService(ProtocolActionTypeToCorrespondenceTemplateService.class);
    }
    
    /**
     * 
     * This method returns the appropriate protocol action type, such as ProtocolActionType.ASSIGN_TO_AGENDA.
     * @return a string that is a ProtocolActionType
     */
    public abstract String getProtocolActionType();

    
    private List<ProtocolCorrespondenceTemplate> getCorrespondenceTemplates() {
        List<ProtocolCorrespondenceTemplate> templates = 
            getProtocolActionTypeToCorrespondenceTemplateService().getTemplatesByProtocolAction(getProtocolActionType());
        return templates;
    }
    
    @Override
    public List<Source> getXSLTemplates() {
        List<Source> sourceList = new ArrayList<Source>();
        List<ProtocolCorrespondenceTemplate> templates = getCorrespondenceTemplates();
        
        for (ProtocolCorrespondenceTemplate template : templates) {
            InputStream iputStream = new ByteArrayInputStream(template.getCorrespondenceTemplate()); 
            StreamSource stream = new StreamSource(iputStream);
            sourceList.add(stream);
        }   
        return sourceList;
    }
    
    /**
     * 
     * This method returns the protocol correspondence type code of the first template associated the action of the sub class.
     * If there are no templates, returns an empty String.
     * @return a String
     */
    public String getProtoCorrespTypeCode() {
        List<ProtocolCorrespondenceTemplate> templates = getCorrespondenceTemplates();
        for (ProtocolCorrespondenceTemplate template : templates) {
            return template.getProtoCorrespTypeCode();
        }
        return "";
    }
    
    @Override
    public Map<String, byte[]> renderXML() throws PrintingException {
        setXmlStream(getCorrespondenceXmlStream());
        return super.renderXML();
    }

    public CorrespondenceXmlStream getCorrespondenceXmlStream() {
         return KraServiceLocator.getService(CorrespondenceXmlStream.class);
    }

    /**
     * This method is to enable watermark in correspondence. Overriding AbstractPrint method isWatermarkEnabled()
     * 
     * @return boolean
     */
    @Override
    public boolean isWatermarkEnabled() {
        return true;
    }

    /**
     * This method for getting watermark for protocol correspondence PDF. Overriding AbstractPrint method getWatermarkable
     * 
     * @return prtocolPrintWatermark
     */
    @Override
    public Watermarkable getWatermarkable() {
        ProtocolPrintWatermark prtocolPrintWatermark = new ProtocolPrintWatermark();
        prtocolPrintWatermark.setPersistableBusinessObject(getPrintableBusinessObject());
        return prtocolPrintWatermark;
    }
}