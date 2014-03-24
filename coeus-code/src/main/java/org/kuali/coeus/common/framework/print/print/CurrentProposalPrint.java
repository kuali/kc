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
package org.kuali.coeus.common.framework.print.print;

import org.kuali.coeus.common.framework.print.service.CurrentAndPendingReportService;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.kra.util.watermark.Watermarkable;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the implementation for printing Current proposal Report.
 * It generates XML that conforms with current and pending support XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 */
public class CurrentProposalPrint extends AbstractPrint {


	/**
	 * This method fetches the XSL style-sheets required for transforming the
	 * generated XML into PDF.
	 * 
	 * @return {@link ArrayList}} of {@link Source} XSLs
	 */
	public List<Source> getXSLTemplates() {
		ArrayList<Source> sourceList = PrintingUtils
				.getXSLTforReport(CurrentAndPendingReportService.CURRENT_REPORT_TYPE);
		return sourceList;
	}

	/**
     * 
     * This method for checking watermark is enable or disable
     * for this document.
     */
     public boolean isWatermarkEnabled(){
         return false;
     }
     /**
      * This method for getting the watermark.
      * @see org.kuali.coeus.common.framework.print.Printable#getWatermarkable()
      * return watermarkable
      */
     public Watermarkable getWatermarkable(){
         if(isWatermarkEnabled()){
             throw new RuntimeException("Watermarkable not implemented");
         }else{
             return null;
         }
     }

   

}
