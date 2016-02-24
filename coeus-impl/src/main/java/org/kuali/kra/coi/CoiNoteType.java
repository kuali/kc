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
