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
package org.kuali.coeus.common.framework.motd;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class is Business Object representation of a comment type.
 */
public class MessageOfTheDay extends KcPersistableBusinessObjectBase {

    private Long messageOfTheDayId;

    private String message;

    private boolean active;

    private Long displayOrder;


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
