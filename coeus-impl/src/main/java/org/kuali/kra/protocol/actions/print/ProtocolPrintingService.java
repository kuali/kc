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
package org.kuali.kra.protocol.actions.print;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;

import java.util.List;

/**
 * 
 * This class is for ProtocolBase print in protocol actions page
 */
public interface ProtocolPrintingService {
    
    /**
     * 
     * This method to print the report.  Actually this service is just use PrintingService's print
     * But need to be defined here.
     * @param printableArtifactList
     * @return
     * @throws PrintingException
     */
    AttachmentDataSource print(String reportName, List<Printable> printableArtifactList) throws PrintingException;
    
    /**
     * 
     * This method is to get the printable Artifacts for the selected protocol.
     * @param protocol
     * @return
     */
    Printable getProtocolPrintArtifacts(ProtocolBase protocol) ; 
    
    
    /**
     * This method is to print items in protocol document
     * @param protocolForm
     * @return
     * @throws PrintingException
     */
    AttachmentDataSource printProtocolDocument(ProtocolFormBase protocolForm) throws PrintingException;
   
    /**
     * This method is to print selected protocol items
     * @param protocolForm
     * @return
     * @throws PrintingException
     */
    AttachmentDataSource printProtocolSelectedItems(ProtocolFormBase protocolForm) throws PrintingException;
    
}
