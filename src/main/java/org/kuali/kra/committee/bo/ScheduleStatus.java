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
package org.kuali.kra.committee.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ScheduleStatus extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -7318684957814299092L;

    private Integer scheduleStatusCode;

    private String description;

    public ScheduleStatus() {
    }

    public Integer getScheduleStatusCode() {
        return scheduleStatusCode;
    }

    public void setScheduleStatusCode(Integer scheduleStatusCode) {
        this.scheduleStatusCode = scheduleStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
