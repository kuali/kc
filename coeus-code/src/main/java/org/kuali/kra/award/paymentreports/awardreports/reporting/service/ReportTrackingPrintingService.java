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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.util.List;

/**
 * This class provides the means for printing reports related to Award. It has
 * the capability to provide a PDF document of various reports related to Award
 * like Delta Report, Award Notice etc.
 * 
 * @author
 * 
 */
public interface ReportTrackingPrintingService {

    /**
     * This method will return the printable object
     * @param reportTrackingType
     *           report tracking type
     * @param detailResult
     *            report to be generated
     * @param printable
     */
    public AwardReportTracking getReportPrintable(ReportTrackingType reportTrackingType,ReportTracking detailResult,AwardReportTracking printable);

    /**
     * This method is for prints all Award Reports
     * @param printableArtifactList list of Award reports
     */
    public AttachmentDataSource printAwardReportTracking(List<Printable> printableArtifactList) throws PrintingException;
    
    
}
