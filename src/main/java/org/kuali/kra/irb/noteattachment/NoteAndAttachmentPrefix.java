/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.noteattachment;

/**
 * Contains all the property prefixes used in this package.
 */
public enum NoteAndAttachmentPrefix {
    NEW_ATTACHMENT_PROTOCOL("notesAndAttachmentsHelper.newAttachmentProtocol", false),
    NEW_ATTACHMENT_PERSONNEL("notesAndAttachmentsHelper.newAttachmentPersonnel", false),
    ATTACHMENT_PROTOCOL("document.protocolList[0].attachmentProtocol", true),
    ATTACHMENT_PERSONNEL("document.protocolList[0].attachmentPersonnel", true);
    
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "name: " + this.name + " indexed: " + this.indexed;
    }
}
