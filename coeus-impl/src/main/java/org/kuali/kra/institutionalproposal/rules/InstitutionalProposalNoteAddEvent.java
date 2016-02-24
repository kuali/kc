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
