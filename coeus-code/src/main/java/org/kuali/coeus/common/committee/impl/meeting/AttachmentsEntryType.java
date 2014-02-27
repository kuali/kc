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
