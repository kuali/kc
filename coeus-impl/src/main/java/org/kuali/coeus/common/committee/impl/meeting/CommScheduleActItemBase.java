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

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class is for schedule meeting other action.
 */
public abstract class CommScheduleActItemBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 5688256868516863628L;

    private Long commScheduleActItemsId;

    private Long scheduleIdFk;

    private Integer actionItemNumber;

    private String scheduleActItemTypeCode;

    private String itemDescription;

    private CommitteeScheduleBase committeeSchedule;

    private ScheduleActItemType scheduleActItemType;

    public CommScheduleActItemBase() {
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

    public CommitteeScheduleBase getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeScheduleBase committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

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
        CommScheduleActItemBase other = (CommScheduleActItemBase) obj;
        if (commScheduleActItemsId == null) {
            if (other.commScheduleActItemsId != null) {
                return false;
            }
        } else if (!commScheduleActItemsId.equals(other.commScheduleActItemsId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commScheduleActItemsId == null) ? 0 : commScheduleActItemsId.hashCode());
        return result;
    }
}
