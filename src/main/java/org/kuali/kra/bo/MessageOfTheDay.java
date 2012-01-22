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
package org.kuali.kra.bo;


/**
 * This class is Business Object representation of a comment type.
 */
public class MessageOfTheDay extends KraPersistableBusinessObjectBase {

    private Long messageOfTheDayId;

    private String message;

    private boolean active;

    private Long displayOrder;

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5649376154094364142L;

    public Long getMessageOfTheDayId() {
        return messageOfTheDayId;
    }

    public void setMessageOfTheDayId(Long messageOfTheDayId) {
        this.messageOfTheDayId = messageOfTheDayId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }
}
