/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

    /**
     * Gets the createTimeStamp attribute. 
     * @return Returns the createTimeStamp.
     */
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * Sets the createTimeStamp attribute value.
     * @param createTimeStamp The createTimeStamp to set.
     */
    public void setCreateTimestamp(Timestamp pCreateTimestamp) {
        this.createTimestamp = pCreateTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**    public void setCreateUserFullNamtee(String createUserFullName) {
        this.createUserFullName = createUserFullName;
    }


     * @see org.kuali.coeus.common.framework.version.sequence.Sequenceable#resetPersistenceState()
     */
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
}
