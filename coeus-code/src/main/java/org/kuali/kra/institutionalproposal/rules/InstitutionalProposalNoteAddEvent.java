/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is for the event to add Institutional Proposal note
 */
public class InstitutionalProposalNoteAddEvent  extends InstitutionalProposalNoteEventBase<InstitutionalProposalNoteAddRule> {
    
    private static final String MSG = "Add Institutional Proposal note  ";
    
    public InstitutionalProposalNoteAddEvent(String errorPathPrefix, InstitutionalProposalDocument document, InstitutionalProposalNotepad institutionalProposalNotepad, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, institutionalProposalNotepad, type);
    }
    
    public InstitutionalProposalNoteAddEvent(String errorPathPrefix, Document document, InstitutionalProposalNotepad institutionalProposalNotepad, ErrorType type) {
        this(errorPathPrefix, (InstitutionalProposalDocument)document, institutionalProposalNotepad, type);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new InstitutionalProposalNoteAddRule();
    }

}
