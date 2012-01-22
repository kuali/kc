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
package org.kuali.kra.coi.service;

import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.print.CoiReportType;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

//TODO: Needs work.  Lots of work.

/**
 * This class provides the interface for printing reports related to COI. It has the
 * capability to provide a PDF document of various reports related to COI like 
 * certifications.
 */
public interface CoiPrintingService {
    
    public static String PRINT_CERTIFICATION_PERSON = "person";
    public static String PRINT_CERTIFICATION_STATEMENT = "statement";
    public static String PRINT_CERTIFICATION_TIMESTAMP = "timestamp";
    public static String PRINT_CERTIFICATION = "print";
    public static String PRINT_REPORT_TYPE = "coiReportTypeCode";
    
    /**
     * This method generates the required report and returns the PDF stream as
     * {@link AttachmentDataSource}
     * 
     * @param disclosureDocument
     *            disclosure data using which report is generated
     * @param reportName
     *            report to be generated
     * @param reportParameters
     *            {@link Map} of parameters required for report generation
     * @return {@link AttachmentDataSource} which contains the byte array of the
     *         generated PDF
     * @throws PrintingException
     *             if any errors occur during report generation
     */
    public AttachmentDataSource printDisclosureCertification( KraPersistableBusinessObjectBase institutionalProposal, 
                                                              String reportName,
            Map<String, Object> reportParameters) throws PrintingException;

    public AbstractPrint getCoiPrintable(CoiReportType reportType);
    
    public Printable getCoiPrintArtifacts(CoiDisclosure coiDisclosure);
    
}
