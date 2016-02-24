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
