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
package org.kuali.kra.protocol.actions.correspondence;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.ProtocolBase;

/**
 * 
 * This interface defines the functions needed generate a correspondence and attach it to a protocol.
 */
public interface ProtocolActionCorrespondenceGenerationService {
    
    /**
     * 
     * This method attaches an appropriate template based PDF document to the protocol and saves it.
     * @param printableCorrespondence an implementation of AbstractProtocolActionsCorrespondence.
     * @throws PrintingException
     */
    void generateCorrespondenceDocumentAndAttach(ProtocolActionsCorrespondenceBase printableCorrespondence) throws PrintingException;
    
    AttachmentDataSource reGenerateCorrespondenceDocument(ProtocolActionsCorrespondenceBase printableCorrespondence) throws PrintingException ; 

    public void attachProtocolCorrespondence(ProtocolBase protocol, byte[] data, String correspTypeCode);
    
}
