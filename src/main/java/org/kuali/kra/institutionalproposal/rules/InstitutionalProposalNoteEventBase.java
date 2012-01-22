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
package org.kuali.kra.institutionalproposal.rules;

import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is the event base class for all INSP note event.
 */
@SuppressWarnings("unchecked")
public abstract class InstitutionalProposalNoteEventBase<Z extends BusinessRuleInterface> extends KraDocumentEventBaseExtension {
    
    /**
     * Enum helps identify type of error to respond.
     */
    public enum ErrorType {HARDERROR, SOFTERROR};
    
    
    private InstitutionalProposalNotepad institutionalProposalNotepad;
    
    private ErrorType type;
    
    public InstitutionalProposalNoteEventBase(String description, String errorPathPrefix, Document document,  InstitutionalProposalNotepad institutionalProposalNotepad, ErrorType type) {        
        super(description, errorPathPrefix, document);
        this.institutionalProposalNotepad = institutionalProposalNotepad;
        this.type = type;
    }
    
    
    /**
     * This method should return instance of institutionalProposalNotepad.
     * @return
     */
    public InstitutionalProposalNotepad getInstitutionalProposalNotepad(){
        return this.institutionalProposalNotepad;
    }
    
    /**
     * This method should return Error type.
     * @return
     */
    public ErrorType getType() {
        return this.type;
    }


}
