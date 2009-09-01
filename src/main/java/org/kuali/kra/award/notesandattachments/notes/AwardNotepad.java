/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;

import org.kuali.kra.award.AwardAssociate;

/**
 * This class...
 */
public class AwardNotepad extends AwardAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Long awardNotepadId; 
    private String awardNumber; 
    private Integer entryNumber; 
    private String comments; 
    private String noteTopic;
    private boolean restrictedView; 
    private Date createTimestamp;

    public AwardNotepad() { 
        Calendar cl = Calendar.getInstance();
        setCreateTimestamp(new Date(cl.getTime().getTime()));
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
    public Date getCreateTimestamp() {
        return createTimestamp;
    }


    /**
     * Sets the createTimeStamp attribute value.
     * @param createTimeStamp The createTimeStamp to set.
     */
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
    

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardNotepadId = null;
    }


    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("awardNotepadId", this.getAwardNotepadId());
        hashMap.put("awardNumber", this.getAwardNumber());
        hashMap.put("entryNumber", this.getEntryNumber());
        hashMap.put("comments", this.getComments());
        hashMap.put("restrictedView", this.getRestrictedView());
        hashMap.put("noteTopic", this.getNoteTopic());
        return hashMap;
    }
}



    

