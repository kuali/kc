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
