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
package org.kuali.kra.committee.print;

import org.kuali.coeus.common.committee.impl.print.TemplatePrintBase;
import org.kuali.coeus.common.framework.print.watermark.Watermarkable;
import org.kuali.kra.irb.actions.print.ProtocolPrintWatermark;

/**
 * 
 * This class identifies the template print functionality for committee reports.
 */
public class ProtocolCorrespondenceTemplatePrint extends TemplatePrintBase {

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
        ProtocolPrintWatermark prtocolPrintWatermark = new ProtocolPrintWatermark();
        prtocolPrintWatermark.setPersistableBusinessObject(getPrintableBusinessObject());
        return prtocolPrintWatermark;
    }

}
