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
package org.kuali.coeus.common.framework.print.watermark;



/**
 * 
 * This method invokes the KC watermarkBean for reports, docs. In this method the input pdfBytes are merged with watermark and
 * returns the resultant pdf.
 * 
 */

public interface WatermarkService {
    /**
     * 
     * This method for applying watermark to the pdf.In this method the input pdfBytes are merged with watermark content and returns
     * the resultant pdf.
     * @param pdfBytes
     * @param watermarkBean
     * @return pdfFileData
     * @throws Exception
     */
    byte[] applyWatermark(byte[] pdfBytes, WatermarkBean watermarkBean) throws Exception;

}
