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
package org.kuali.kra.coi.notesandattachments.notes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureAssociate;

public class CoiDisclosureNotepad extends CoiDisclosureAssociate implements Comparable<CoiDisclosureNotepad> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3452466623013297158L;
    private String comments;
    private Integer entryNumber;
    private Long id;
    private String noteTopic;
    private boolean restrictedView;
    private boolean editable;
    private transient String updateUserFullName;
    private String projectId;
    private String entityNumber;
    private Long entitySequenceNumber;
  
    public CoiDisclosureNotepad() {
        super();
        editable = false;
    }
    
    public CoiDisclosureNotepad(final CoiDisclosure coiDisclosure) {
        super(coiDisclosure);
        editable = false;
    }
    
    
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    public Long getEntitySequenceNumber() {
        return entitySequenceNumber;
    }

    public void setEntitySequenceNumber(Long entitySequenceNumber) {
        this.entitySequenceNumber = entitySequenceNumber;
    }

    public String getUpdateUserFullName() {
        return updateUserFullName;
    }

    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteTopic() {
        return noteTopic;
    }

    public void setNoteTopic(String noteTopic) {
        this.noteTopic = noteTopic;
    }

    public boolean getRestrictedView() {
        return restrictedView;
    }

    public void setRestrictedView(boolean restrictedView) {
        this.restrictedView = restrictedView;
    }
    
    public final static class NotepadByEntryNumber implements Comparator<CoiDisclosureNotepad>, Serializable {

        public static final NotepadByEntryNumber INSTANCE = new NotepadByEntryNumber();
        private static final long serialVersionUID = -2271453419166988229L;
        
        private NotepadByEntryNumber() {
            //internal
        }
        
        /** {@inheritDoc} */
        public int compare(CoiDisclosureNotepad o1, CoiDisclosureNotepad o2) {
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
        if (updateUser == null || getUpdateUser() == null || isEditable() ) {
            super.setUpdateUser(updateUser);
        }
    }
    
    public boolean isEditable() {
        return editable;
    }


    public void setEditable(boolean editable) {
        this.editable = editable;
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
        
        final CoiDisclosureNotepad other = (CoiDisclosureNotepad) obj;
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
    
    @Override
    public int compareTo(CoiDisclosureNotepad coiDisclosureNotepad) {
        return this.getUpdateTimestamp().compareTo(coiDisclosureNotepad.getUpdateTimestamp());

    }

}
