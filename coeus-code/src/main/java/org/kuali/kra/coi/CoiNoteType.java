/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

public class CoiNoteType extends KcPersistableBusinessObjectBase implements MutableInactivatable {


    private static final long serialVersionUID = 1520636068966348199L;
    
    public static final String PI_ENTRY_NOTE_TYPE_CODE = "1";
    public static final String REVIEWER_COMMENT_NOTE_TYPE_CODE = "2";
    public static final String COI_OFFICER_NOTE_TYPE_CODE = "3";

    private String noteTypeCode;
    private String description;
    private Integer sortId;
    private boolean active;



    public CoiNoteType() {
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


    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the sortId attribute. 
     * @return Returns the sortId.
     */
    public Integer getSortId() {
        return sortId;
    }


    /**
     * Sets the sortId attribute value.
     * @param sortId The sortId to set.
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }


    /**
     * Gets the active attribute. 
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }


    /**
     * Sets the active attribute value.
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
