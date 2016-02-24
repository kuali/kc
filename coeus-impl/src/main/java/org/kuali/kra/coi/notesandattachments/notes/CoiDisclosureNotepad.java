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
package org.kuali.kra.coi.notesandattachments.notes;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureAssociate;
import org.kuali.kra.coi.CoiNoteType;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Calendar;

public class CoiDisclosureNotepad extends CoiDisclosureAssociate implements Comparable<CoiDisclosureNotepad> {


    private static final long serialVersionUID = -3452466623013297158L;
    private String comments;
    private Integer entryNumber;
    private Long id;
    private String noteTopic;
    private boolean restrictedView;
    private boolean editable;
    private transient String updateUserFullName;
    private String projectId;
    private Long originalCoiDisclosureId; 
    private String noteTypeCode;
    @SkipVersioning
    private CoiDisclosure originalCoiDisclosure; 
    private Long financialEntityId;
    private String createUser;
    private Timestamp createTimestamp;
    private transient String createUserFullName;
    private CoiNoteType noteType;

    private String usageSectionId;
    
    
    @SkipVersioning
    private PersonFinIntDisclosure financialEntity;
    private String eventTypeCode;

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
    
    public Long getFinancialEntityId() {
        return financialEntityId;
    }

    public void setFinancialEntityId(Long financialEntityId) {
        this.financialEntityId = financialEntityId;
    }

    public String getEventTypeCode() {
        return eventTypeCode;
    }

    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public PersonFinIntDisclosure getFinancialEntity() {
        return financialEntity;
    }

    public void setFinancialEntity(PersonFinIntDisclosure financialEntity) {
        this.financialEntity = financialEntity;
    }
    
    public String getFinancialEntityName() {
        refreshReferenceObject("financialEntity");
        return ObjectUtils.isNotNull(getFinancialEntity()) ? getFinancialEntity().getEntityName() : "";
    }
    
    public String getProjectName() {
        refreshReferenceObject("coiDisclProjects");
        for (CoiDisclProject project : getCoiDisclosure().getCoiDisclProjects()) {
            if (StringUtils.equalsIgnoreCase(project.getProjectId(), getProjectId())) {
                return project.getCoiProjectTitle();
            }
        }
        return "";
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

    public void resetUpdateTimestamp() {
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        setUpdateTimestamp(timestamp);
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
        if (this.getCreateTimestamp() == null || coiDisclosureNotepad.getCreateTimestamp() == null) {
            return this.getUpdateTimestamp().compareTo(coiDisclosureNotepad.getUpdateTimestamp());
        } else {
            return this.getCreateTimestamp().compareTo(coiDisclosureNotepad.getCreateTimestamp());
        }

    }

    @Override
    public void preUpdate() {
        super.preUpdate();
        setUpdateTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    }
    
    @Override
    public void postUpdate() {
        super.postUpdate();
        setEditable(false);
    }
    
    public Long getOriginalCoiDisclosureId() {
        return originalCoiDisclosureId;
    }

    public void setOriginalCoiDisclosureId(Long originalCoiDisclosureId) {
        this.originalCoiDisclosureId = originalCoiDisclosureId;
    }

    public CoiDisclosure getOriginalCoiDisclosure() {
        return originalCoiDisclosure;
    }

    public void setOriginalCoiDisclosure(CoiDisclosure originalCoiDisclosure) {
        this.originalCoiDisclosure = originalCoiDisclosure;
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
    
    public String getCreateUserFullName() {
        return createUserFullName;
    }

    public void setCreateUserFullName(String createUserFullName) {
        this.createUserFullName = createUserFullName;
    }
    /*
    @Override
    protected void prePersist() {
        super.prePersist();
        this.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        this.setCreateTimestamp(((DateTimeService) KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
    }
    */

    public String getUsageSectionId() {
        return usageSectionId;
    }

    public void setUsageSectionId(String usageSectionId) {
        this.usageSectionId = usageSectionId;
    }
    
    /**
     * Gets the noteType attribute. 
     * @return Returns the noteType.
     */
    public CoiNoteType getNoteType() {
        return noteType;
    }

    /**
     * Sets the noteType attribute value.
     * @param noteType The noteType to set.
     */
    public void setNoteType(CoiNoteType noteType) {
        this.noteType = noteType;
    }
    
    /**
     * Gets the noteTypeCode attribute. 
     * @return Returns the noteTypeCode.
     */
    public String getNoteTypeCode() {
        return noteTypeCode;
    }

    /**
     * Sets the noteTypeCode attribute value.
     * @param noteTypeCode The noteTypeCode to set.
     */
    public void setNoteTypeCode(String noteTypeCode) {
        this.noteTypeCode = noteTypeCode;
    }
}
