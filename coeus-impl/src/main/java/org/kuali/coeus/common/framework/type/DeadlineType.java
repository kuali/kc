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
package org.kuali.coeus.common.framework.type;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class representation of the Deadline Type Business Object
 *
 */
@Entity
@Table(name = "DEADLINE_TYPE")
public class DeadlineType extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "DEADLINE_TYPE_CODE")
    private String deadlineTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Gets the deadlineTypeCode attribute. 
     * @return Returns the deadlineTypeCode.
     */
    public String getDeadlineTypeCode() {
        return deadlineTypeCode;
    }

    /**
     * Sets the deadlineTypeCode attribute value.
     * @param deadlineTypeCode The deadlineTypeCode to set.
     */
    public void setDeadlineTypeCode(String deadlineTypeCode) {
        this.deadlineTypeCode = deadlineTypeCode;
    }

    /**
     * Retrieves the description attribute
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigns the description attribute
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
