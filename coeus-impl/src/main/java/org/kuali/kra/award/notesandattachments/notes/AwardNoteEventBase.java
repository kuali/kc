/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is the event base class for all award note event.
 */
@SuppressWarnings("unchecked")
public abstract class AwardNoteEventBase<Z extends KcBusinessRule> extends KcDocumentEventBaseExtension {
    
    /**
     * Enum helps identify type of error to respond.
     */
    public enum ErrorType {HARDERROR, SOFTERROR};
    
    
    private AwardNotepad awardNotepad;
    
    private ErrorType type;
    
    public AwardNoteEventBase(String description, String errorPathPrefix, Document document,  AwardNotepad awardNotepad, ErrorType type) {        
        super(description, errorPathPrefix, document);
        this.awardNotepad = awardNotepad;
        this.type = type;
    }
    
    
    /**
     * This method should return instance of awardnotepad.
     * @return
     */
    public AwardNotepad getAwardNotepad(){
        return this.awardNotepad;
    }
    
    /**
     * This method should return Error type.
     * @return
     */
    public ErrorType getType() {
        return this.type;
    }


}
