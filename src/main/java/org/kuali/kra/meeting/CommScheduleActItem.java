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
import org.kuali.kra.committee.bo.CommitteeSchedule;

/**
 * 
 * This class is for schedule meeting other action.
 */
public class CommScheduleActItem extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 5688256868516863628L;

    private Long commScheduleActItemsId;

    private Long scheduleIdFk;

    private Integer actionItemNumber;

    private String scheduleActItemTypeCode;

    private String itemDescription;

    private CommitteeSchedule committeeSchedule;

    private ScheduleActItemType scheduleActItemType;

    public CommScheduleActItem() {
    }

    public Long getCommScheduleActItemsId() {
        return commScheduleActItemsId;
    }

    public void setCommScheduleActItemsId(Long commScheduleActItemsId) {
        this.commScheduleActItemsId = commScheduleActItemsId;
    }

    public Integer getActionItemNumber() {
        return actionItemNumber;
    }

    public void setActionItemNumber(Integer actionItemNumber) {
        this.actionItemNumber = actionItemNumber;
    }

    public String getScheduleActItemTypeCode() {
        return scheduleActItemTypeCode;
    }

    public void setScheduleActItemTypeCode(String scheduleActItemTypeCode) {
        this.scheduleActItemTypeCode = scheduleActItemTypeCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public ScheduleActItemType getScheduleActItemType() {
        return scheduleActItemType;
    }

    public void setScheduleActItemType(ScheduleActItemType scheduleActItemType) {
        this.scheduleActItemType = scheduleActItemType;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CommScheduleActItem other = (CommScheduleActItem) obj;
        if (commScheduleActItemsId == null) {
            if (other.commScheduleActItemsId != null) {
                return false;
            }
        } else if (!commScheduleActItemsId.equals(other.commScheduleActItemsId)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commScheduleActItemsId == null) ? 0 : commScheduleActItemsId.hashCode());
        return result;
    }
}
