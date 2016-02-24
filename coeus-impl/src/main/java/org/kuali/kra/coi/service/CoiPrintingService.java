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
package org.kuali.kra.coi.service;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.print.CoiReportType;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.util.List;

/**
 * This class provides the interface for printing reports related to COI. It has the
 * capability to provide a PDF document of various reports related to COI like 
 * certifications.
 */
public interface CoiPrintingService {

    public AbstractPrint getCoiPrintable(CoiReportType reportType);
    
    public Printable getCoiPrintArtifacts(CoiDisclosure coiDisclosure);
    
    public AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException;
    
}
