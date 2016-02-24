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
package org.kuali.kra.protocol.noteattachment;

/**
 * Contains all the property prefixes used in this package.
 */
public enum NoteAndAttachmentPrefix {
    NEW_ATTACHMENT_PROTOCOL("notesAttachmentsHelper.newAttachmentProtocol", false),
    NEW_ATTACHMENT_PERSONNEL("notesAttachmentsHelper.newAttachmentPersonnel", false),
    ATTACHMENT_PROTOCOL("document.protocolList[0].attachmentProtocols", true),
    ATTACHMENT_PERSONNEL("document.protocolList[0].attachmentPersonnels", true),
    NEW_NOTEPAD("notesAttachmentsHelper.newProtocolNotepad", false);
    
    private final String name;
    private final boolean indexed;
    
    /**
     * Sets the enum properties.
     * @param name the name.
     * @param indexed whether it is an index type.
     */
    NoteAndAttachmentPrefix(final String name, boolean indexed) {
        this.name = name;
        this.indexed = indexed;
    }
    
    /**
     * Gets the prefix name.
     * @return the prefix name.
     */
    public String getPrefixName() {
        return this.name;
    }
    
    /**
     * Gets the prefix name with an index value.
     * @param index the index
     * @return the prefix name with an index value.
     * @throws UnsupportedOperationException if not an indexed type
     */
    public String getIndexedPrefix(int index) {
        if (!this.indexed) {
            throw new UnsupportedOperationException(this.name() + " is not an indexed type.");
        }
        
        return this.name + "[" + index + "]";
    }
    
    @Override
    public String toString() {
        return "name: " + this.name + " indexed: " + this.indexed;
    }
}
