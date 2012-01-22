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
package org.kuali.kra.irb.actions.correspondence;

import java.util.List;

import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.printing.PrintingException;

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
    void generateCorrespondenceDocumentAndAttach(AbstractProtocolActionsCorrespondence printableCorrespondence) throws PrintingException;
    
    /**
     * 
     * This method...
     * @param actionType
     * @return
     */
    List<ProtocolCorrespondenceTemplate> getCorrespondenceTemplates(String actionType);
}