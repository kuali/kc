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
package org.kuali.kra.protocol.actions.correspondence;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.watermark.Watermarkable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.print.CorrespondenceXmlStreamBase;
import org.kuali.kra.protocol.actions.print.ProtocolPrintWatermarkBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateBase;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ProtocolActionsCorrespondenceBase extends AbstractPrint {
    
    private ProtocolBase protocol;
    
    public void setProtocol(ProtocolBase protcol) {
        this.protocol = protcol;
    }
    
    public ProtocolBase getProtocol() {
        return this.protocol;
    }
    @Override
    public KcPersistableBusinessObjectBase getPrintableBusinessObject() {
        return getProtocol();
    }
    protected abstract ProtocolActionTypeToCorrespondenceTemplateService getProtocolActionTypeToCorrespondenceTemplateService();
    
    /**
     * 
     * This method returns the appropriate protocol action type, such as ProtocolActionType.ASSIGN_TO_AGENDA.
     * @return a string that is a ProtocolActionType
     */
    public abstract String getProtocolActionType();

    
    private List<ProtocolCorrespondenceTemplateBase> getCorrespondenceTemplates() {
        String committeeId = getProtocol().getProtocolSubmission().getCommitteeId();
        List<ProtocolCorrespondenceTemplateBase> templates = 
            getProtocolActionTypeToCorrespondenceTemplateService().getTemplatesByProtocolAction(getProtocolActionType(), committeeId);
        return templates;
    }
    
    @Override
    public List<Source> getXSLTemplates() {
        List<Source> sourceList = new ArrayList<Source>();
        List<ProtocolCorrespondenceTemplateBase> templates = getCorrespondenceTemplates();
        
        for (ProtocolCorrespondenceTemplateBase template : templates) {
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
        List<ProtocolCorrespondenceTemplateBase> templates = getCorrespondenceTemplates();
        for (ProtocolCorrespondenceTemplateBase template : templates) {
            return template.getProtoCorrespTypeCode();
        }
        return "";
    }
    
    @Override
    public Map<String, byte[]> renderXML() throws PrintingException {
        setXmlStream(getCorrespondenceXmlStream());
        return super.renderXML();
    }

    public abstract CorrespondenceXmlStreamBase getCorrespondenceXmlStream();

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
        ProtocolPrintWatermarkBase prtocolPrintWatermark = getNewProtocolPrintWatermarkInstanceHook(); //new ProtocolPrintWatermarkBase();
        prtocolPrintWatermark.setPersistableBusinessObject(getPrintableBusinessObject());
        return prtocolPrintWatermark;
    }

    protected abstract ProtocolPrintWatermarkBase getNewProtocolPrintWatermarkInstanceHook();

    public String getLeadUnitNumber() {
        return getProtocol().getLeadUnitNumber();
    }

    protected abstract String getAdministratorType();

    protected abstract String getModuleNameSpace();
    
}
