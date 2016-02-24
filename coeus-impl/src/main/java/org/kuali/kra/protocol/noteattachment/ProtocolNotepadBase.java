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

import org.kuali.kra.SkipVersioning;
import org.kuali.kra.infrastructure.KraNotepadInterface;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.kra.protocol.ProtocolBase;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;

/**
 * The ProtocolBase Notepad class.
 */
public abstract class ProtocolNotepadBase extends ProtocolAssociateBase implements Comparable<ProtocolNotepadBase>, KraNotepadInterface {

    private static final long serialVersionUID = -294125058992878907L;

    protected Long id;

    protected Integer entryNumber = Integer.valueOf(0);

    protected String comments;

    protected boolean restrictedView;

    protected String noteTopic;

    protected boolean editable;
    
    private String createUser;
    
    private Timestamp createTimestamp;

    @SkipVersioning
    protected transient String updateUserFullName;
    protected transient String createUserFullName;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    protected ProtocolNotepadBase() {
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
    public ProtocolNotepadBase(final ProtocolBase protocol) {
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

    public String getCreateUserFullName() {
        return createUserFullName;
    }

    public void setCreateUserFullName(String createUserFullName) {
        this.createUserFullName = createUserFullName;
    }

    @Override
    public void resetPersistenceState() {
        this.id = null;
    }

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
        final ProtocolNotepadBase other = (ProtocolNotepadBase) obj;
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
    public static final class NotepadByEntryNumber implements Comparator<ProtocolNotepadBase>, Serializable {

        public static final NotepadByEntryNumber INSTANCE = new NotepadByEntryNumber();

        private static final long serialVersionUID = -2271453419166988229L;

        private NotepadByEntryNumber() {
        }

        @Override
        public int compare(ProtocolNotepadBase o1, ProtocolNotepadBase o2) {
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
    public int compareTo(ProtocolNotepadBase protocolNotepad) {
        if (this.getCreateTimestamp() == null || protocolNotepad.getCreateTimestamp() == null) {
            return this.getUpdateTimestamp().compareTo(protocolNotepad.getUpdateTimestamp());
        } else {
            return this.getCreateTimestamp().compareTo(protocolNotepad.getCreateTimestamp());
        }
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
    
}
