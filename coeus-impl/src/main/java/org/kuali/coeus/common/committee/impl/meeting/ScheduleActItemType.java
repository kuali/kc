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
 * 
 * This class is for schedule other action type code.
 */
public class ScheduleActItemType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2501084035126192701L;

    private String scheduleActItemTypeCode;

    private String description;

    private CommScheduleActItemBase commScheduleActItems;

    public ScheduleActItemType() {
    }

    public String getScheduleActItemTypeCode() {
        return scheduleActItemTypeCode;
    }

    public void setScheduleActItemTypeCode(String scheduleActItemTypeCode) {
        this.scheduleActItemTypeCode = scheduleActItemTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommScheduleActItemBase getCommScheduleActItems() {
        return commScheduleActItems;
    }

    public void setCommScheduleActItems(CommScheduleActItemBase commScheduleActItems) {
        this.commScheduleActItems = commScheduleActItems;
    }
}
