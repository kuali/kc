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
package org.kuali.kra.irb.actions.print;

import java.util.List;

import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * 
 * This class is for Protocol print in protocol actions page
 */
public interface ProtocolPrintingService {
   
    /**
     * 
     * This method is to get the printable for the selected report.
     * @param reportType
     * @return
     */
    Printable getProtocolPrintable(ProtocolPrintType reportType);
    
    /**
     * 
     * This method to print the report.  Actually this service is just use PrintingService's print
     * But need to be defined here.
     * @param printableArtifactList
     * @return
     * @throws PrintingException
     */
    AttachmentDataSource print(String reportName, List<Printable> printableArtifactList) throws PrintingException;
}
