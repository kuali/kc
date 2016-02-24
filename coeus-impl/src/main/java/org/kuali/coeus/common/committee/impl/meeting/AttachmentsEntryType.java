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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Class representation of the Attachments Entry Type Type Business Object
 *
 */
public class AttachmentsEntryType extends KcPersistableBusinessObjectBase implements Comparable< AttachmentsEntryType>{

    private String attachmentsTypeCode;
    private Integer sortId;
    private String description;
    
    @Override
    public int compareTo(AttachmentsEntryType arg) {
        return this.getSortId().compareTo(arg.getSortId());
    }
    
    /**
     * Gets the attachmentsTypeCode attribute. 
     * @return Returns the attachmentsTypeCode.
     */
    public String getAttachmentsTypeCode() {
        return attachmentsTypeCode;
    }
    
    /**
     * Sets the deadlineTypeCode attribute value.
     * @param deadlineTypeCode
     */
    public void setAttachmentsTypeCode(String attachmentsTypeCode) {
        this.attachmentsTypeCode = attachmentsTypeCode;
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
     * @param sortId
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
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
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
