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
package org.kuali.kra.subawardReporting.printing.service;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.bo.SubAwardPrintAgreement;
import org.kuali.kra.subaward.bo.SubawardTemplateType;
import org.kuali.kra.subawardReporting.printing.SubAwardPrintType;

import java.util.List;
import java.util.Map;

/**
 * This class provides the means for printing reports related to Negotiation.
 * 
 * @author
 * 
 */
public interface SubAwardPrintingService {
        
	/**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}
	 * 
	 * @param awardDocument
	 *            awardData data using which report is generated
	 * @param reportName
	 *            report to be generated
	 * @param reportParameters
	 *            {@link Map} of parameters required for report generation
	 * @return {@link AttachmentDataSource} which contains the byte array of the
	 *         generated PDF
	 * @throws PrintingException
	 *             if any errors occur during report generation
	 */
    
    public static final String SELECTED_TEMPLATES = "Selected Templates";
    
	public AttachmentDataSource printSubAwardReport(
			KraPersistableBusinessObjectBase awardDocument, SubAwardPrintType subAwardPrintType,
			Map<String, Object> reportParameters) throws PrintingException;
	
	public AttachmentDataSource printSubAwardFDPReport(KraPersistableBusinessObjectBase subAwardDoc,SubAwardPrintType subAwardPrintType,
            Map<String, Object> reportParameters) throws PrintingException;
	
	List<SubAwardForms> getSponsorFormTemplates( SubAwardPrintAgreement subAwardPrintAgreement, List<SubAwardForms> subAwardFormList);
	public boolean isPdf(byte[] data);

}
