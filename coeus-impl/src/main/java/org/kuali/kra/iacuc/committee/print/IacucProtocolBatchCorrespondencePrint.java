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
package org.kuali.kra.iacuc.committee.print;

import org.kuali.coeus.common.committee.impl.print.TemplatePrintBase;
import org.kuali.coeus.common.framework.print.watermark.Watermarkable;
import org.kuali.kra.iacuc.actions.print.IacucProtocolPrintWatermark;
import org.kuali.kra.protocol.actions.print.ProtocolPrintWatermarkBase;

/**
 * 
 * This class identifies the template print functionality for committee reports.
 */
public class IacucProtocolBatchCorrespondencePrint extends TemplatePrintBase {

    private static final long serialVersionUID = 8304381236192765809L;

    @Override
    public String getProtoCorrespTypeCode() {
        return  (String) getReportParameters().get("protoCorrespTypeCode");
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
     * This method for getting watermark for protocol correspondence printing. Overriding AbstractPrint method getWatermarkable
     * 
     * @return prtocolPrintWatermark
     */
    @Override
    public Watermarkable getWatermarkable() {
        ProtocolPrintWatermarkBase prtocolPrintWatermark = new IacucProtocolPrintWatermark();
        prtocolPrintWatermark.setPersistableBusinessObject(getPrintableBusinessObject());
        return prtocolPrintWatermark;
    }

}
