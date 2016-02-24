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
package org.kuali.kra.subaward.reporting.printing.service;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.bo.SubAwardPrintAgreement;

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
			KcPersistableBusinessObjectBase awardDocument, SubAwardPrintType subAwardPrintType,
			Map<String, Object> reportParameters) throws PrintingException;
	
	public AttachmentDataSource printSubAwardFDPReport(KcPersistableBusinessObjectBase subAwardDoc,SubAwardPrintType subAwardPrintType,
            Map<String, Object> reportParameters) throws PrintingException;
	
	List<SubAwardForms> getSponsorFormTemplates( SubAwardPrintAgreement subAwardPrintAgreement, List<SubAwardForms> subAwardFormList);
	public boolean isPdf(byte[] data);

}
