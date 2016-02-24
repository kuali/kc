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
