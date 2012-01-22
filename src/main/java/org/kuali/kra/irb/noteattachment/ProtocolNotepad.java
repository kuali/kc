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
package org.kuali.kra.irb.noteattachment;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;

import org.kuali.kra.SkipVersioning;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;

/**
 * The Protocol Notepad class.
 */
public class ProtocolNotepad extends ProtocolAssociate implements Comparable<ProtocolNotepad> {

    private static final long serialVersionUID = -294125058992878907L;

    private Long id;

    private Integer entryNumber = Integer.valueOf(0);

    private String comments;

    private boolean restrictedView;

    private String noteTopic;

    private boolean editable;

    @SkipVersioning
    private transient String updateUserFullName;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolNotepad() {
        super();
        this.editable = false;
    }

    /**
     * Convenience ctor to add the protocol as an owner.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param protocol the protocol.
     */
    public ProtocolNotepad(final Protocol protocol) {
        super(protocol);
        this.editable = false;
    }

    /**
     * Gets the id attribute. 
     * @return Returns the id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id attribute value.
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the entryNumber attribute. 
     * @return Returns the entryNumber.
     */
    public Integer getEntryNumber() {
        return this.entryNumber;
    }

    /**
     * Sets the entryNumber attribute value.
     * @param entryNumber The entryNumber to set.
     */
    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    /**
     * Gets the comments attribute. 
     * @return Returns the comments.
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * Sets the comments attribute value.
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the restrictedView attribute. 
     * @return Returns the restrictedView.
     */
    public boolean getRestrictedView() {
        return this.restrictedView;
    }

    /**
     * Sets the restrictedView attribute value.
     * @param restrictedView The restrictedView to set.
     */
    public void setRestrictedView(boolean restrictedView) {
        this.restrictedView = restrictedView;
    }

    /**
     * Gets the noteTopic attribute. 
     * @return Returns the noteTopic.
     */
    public String getNoteTopic() {
        return this.noteTopic;
    }

    /**
     * Sets the noteTopic attribute value.
     * @param noteTopic The noteTopic to set.
     */
    public void setNoteTopic(String noteTopic) {
        this.noteTopic = noteTopic;
    }

    /**
     * Gets the updateUserFullName attribute. 
     * @return Returns the updateUserFullName.
     */
    public String getUpdateUserFullName() {
        return updateUserFullName;
    }

    /**
     * Sets the updateUserFullName attribute value.
     * @param updateUserFullName The updateUserFullName to set.
     */
    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
    }

    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.id = null;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.comments == null) ? 0 : this.comments.hashCode());
        result = prime * result + ((this.entryNumber == null) ? 0 : this.entryNumber.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.noteTopic == null) ? 0 : this.noteTopic.hashCode());
        result = prime * result + (this.restrictedView ? 1231 : 1237);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ProtocolNotepad other = (ProtocolNotepad) obj;
        if (this.comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!this.comments.equals(other.comments)) {
            return false;
        }
        if (this.entryNumber == null) {
            if (other.entryNumber != null) {
                return false;
            }
        } else if (!this.entryNumber.equals(other.entryNumber)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.noteTopic == null) {
            if (other.noteTopic != null) {
                return false;
            }
        } else if (!this.noteTopic.equals(other.noteTopic)) {
            return false;
        }
        if (this.restrictedView != other.restrictedView) {
            return false;
        }
        return true;
    }

    /**
     * compares Notepads by entry number.
     */
    public static final class NotepadByEntryNumber implements Comparator<ProtocolNotepad>, Serializable {

        public static final NotepadByEntryNumber INSTANCE = new NotepadByEntryNumber();

        private static final long serialVersionUID = -2271453419166988229L;

        private NotepadByEntryNumber() {
        }

        /** {@inheritDoc} */
        public int compare(ProtocolNotepad o1, ProtocolNotepad o2) {
            return o1.getEntryNumber().compareTo(o2.getEntryNumber());
        }
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (updateTimestamp == null || getUpdateTimestamp() == null || isEditable()) {
            super.setUpdateTimestamp(updateTimestamp);
        }
    }

    @Override
    public void setUpdateUser(String updateUser) {
        if (updateUser == null || getUpdateUser() == null || isEditable()) {
            super.setUpdateUser(updateUser);
        }
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * Sort by updateTimestamp
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(ProtocolNotepad protocolNotepad) {
        return this.getUpdateTimestamp().compareTo(protocolNotepad.getUpdateTimestamp());
    }
}
