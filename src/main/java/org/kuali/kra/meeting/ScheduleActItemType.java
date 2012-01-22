/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.meeting;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is for schedule other action type code.
 */
public class ScheduleActItemType extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2501084035126192701L;

    private String scheduleActItemTypeCode;

    private String description;

    private CommScheduleActItem commScheduleActItems;

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

    public CommScheduleActItem getCommScheduleActItems() {
        return commScheduleActItems;
    }

    public void setCommScheduleActItems(CommScheduleActItem commScheduleActItems) {
        this.commScheduleActItems = commScheduleActItems;
    }
}
