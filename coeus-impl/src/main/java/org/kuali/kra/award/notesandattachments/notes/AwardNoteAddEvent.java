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
package org.kuali.kra.award.notesandattachments.notes;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is for the event to add award note
 */
public class AwardNoteAddEvent  extends AwardNoteEventBase<AwardNoteAddRule> {
    
    private static final String MSG = "Add award note  ";
    
    public AwardNoteAddEvent(String errorPathPrefix, AwardDocument document, AwardNotepad awardNotepad, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, awardNotepad, type);
    }
    
    public AwardNoteAddEvent(String errorPathPrefix, Document document, AwardNotepad awardNotepad, ErrorType type) {
        this(errorPathPrefix, (AwardDocument)document, awardNotepad, type);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new AwardNoteAddRule();
    }

}
