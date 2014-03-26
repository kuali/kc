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
package org.kuali.kra.protocol.actions.print;

import org.drools.core.util.StringUtils;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.print.watermark.Watermarkable;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is the abstract class for ProtocolBase report printables
 */
public abstract class ProtocolReportPrintBase extends AbstractPrint {

    private static final long serialVersionUID = 2778568731674597840L;
    private ProtocolPrintHelper printHelper;
    private static final String ERROR_MESSAGE = "Unknown report template.";

    /**
     * 
     * This method is get the print type of the printable.
     * 
     * @return
     */
    public abstract String getProtocolPrintType();

    /**
     * This method fetches the XSL style-sheets required for transforming the generated XML into PDF.
     * 
     * @return {@link ArrayList} of {@link Source} XSLs
     */
    public List<Source> getXSLTemplates() {
        if(StringUtils.isEmpty(getPrintHelper().getTemplate())) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        ArrayList<Source> sourceList = PrintingUtils.getXSLTforReportTemplate(getPrintHelper().getTemplate());
        return sourceList;
    }

    /**
     * This method is to enable watermark. Overriding AbstractPrint method isWatermarkEnabled()
     * 
     * @return boolean
     */
    @Override
    public boolean isWatermarkEnabled() {
        return true;
    }

    /**
     * This method for getting watermark for protocol report printing. Overriding AbstractPrint method getWatermarkable
     * 
     * @return prtocolPrintWatermark
     */
    @Override
    public Watermarkable getWatermarkable() {
        ProtocolPrintWatermarkBase prtocolPrintWatermark = getNewProtocolPrintWatermarkInstanceHook(); //new ProtocolPrintWatermarkBase();
        prtocolPrintWatermark.setPersistableBusinessObject(getPrintableBusinessObject());
        return prtocolPrintWatermark;
    }

    public ProtocolPrintHelper getPrintHelper() {
        return printHelper;
    }

    public void setPrintHelper(ProtocolPrintHelper printHelper) {
        this.printHelper = printHelper;
    }

    protected abstract ProtocolPrintWatermarkBase getNewProtocolPrintWatermarkInstanceHook();

}
