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

//import java.sql.Date;  

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.infrastructure.KraNotepadInterface;

import java.sql.Timestamp;


public class AwardNotepad extends AwardAssociate implements KraNotepadInterface {

    private static final long serialVersionUID = 1L;

    private Long awardNotepadId;

    private String awardNumber;

    private Integer entryNumber;

    private String comments;

    private String noteTopic;

    private boolean restrictedView;

    private Timestamp createTimestamp;

    private String createUser;

    @SkipVersioning
    protected transient String updateUserFullName;
    protected transient String createUserFullName;

    private transient KcPersonService kcPersonService;


    public AwardNotepad() {
    }

    public Long getAwardNotepadId() {
        return awardNotepadId;
    }

    public void setAwardNotepadId(Long awardNotepadId) {
        this.awardNotepadId = awardNotepadId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean getRestrictedView() {
        return restrictedView;
    }

    public void setRestrictedView(boolean restrictedView) {
        this.restrictedView = restrictedView;
    }

    /**
     * Gets the noteTopic attribute. 
     * @return Returns the noteTopic.
     */
    public String getNoteTopic() {
        return noteTopic;
    }

    /**
     * Sets the noteTopic attribute value.
     * @param noteTopic The noteTopic to set.
     */
    public void setNoteTopic(String noteTopic) {
        this.noteTopic = noteTopic;
    }


    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void resetPersistenceState() {
        this.awardNotepadId = null;
    }

    @Override
    public String getCreateUserFullName() {
        if (createUserFullName == null && createUser != null) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(createUser);
            if (person != null) {
                createUserFullName = person.getFullName();
            }
        }
        return createUserFullName;
    }

    public void setCreateUserFullName(String createUserFullName) {
        this.createUserFullName = createUserFullName;
    }

    @Override
    public String getUpdateUserFullName() {
        if (updateUserFullName == null && getUpdateUser() != null) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(getUpdateUser());
            if (person != null) {
                updateUserFullName = person.getFullName();
            }
        }
        return this.updateUserFullName;
    }

    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
    }
    
    @Override
    public boolean isEditable() {
        return false;
    }


    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (getUpdateTimestamp() == null) {
            super.setUpdateTimestamp(updateTimestamp);
        }
    }

    @Override
    public void setUpdateUser(String updateUser) {
        if (getUpdateUser() == null) {
            super.setUpdateUser(updateUser);
        }
    }
}
