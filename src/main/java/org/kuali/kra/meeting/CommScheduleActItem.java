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

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;

/**
 * 
 * This class is for schedule meeting other action.
 */
@Entity 
@Table(name="COMM_SCHEDULE_ACT_ITEMS")
public class CommScheduleActItem extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 2406440045117841427L;
    @Id 
    @Column(name="COMM_SCHEDULE_ACT_ITEMS_ID")
    private Long commScheduleActItemsId; 
    @Column(name="SCHEDULE_ID_FK")
    private Long scheduleIdFk; 
    @Column(name="ACTION_ITEM_NUMBER")
    private Integer actionItemNumber; 
    @Column(name="SCHEDULE_ACT_ITEM_TYPE_CODE")
    private String scheduleActItemTypeCode; 
    @Column(name="ITEM_DESCTIPTION")
    private String itemDesctiption; 
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ID_FK", insertable=false, updatable=false)
    private CommitteeSchedule committeeSchedule;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ACT_ITEM_TYPE_CODE", insertable=false, updatable=false)
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

    public String getItemDesctiption() {
        return itemDesctiption;
    }

    public void setItemDesctiption(String itemDesctiption) {
        this.itemDesctiption = itemDesctiption;
    }


    public ScheduleActItemType getScheduleActItemType() {
        return scheduleActItemType;
    }

    public void setScheduleActItemType(ScheduleActItemType scheduleActItemType) {
        this.scheduleActItemType = scheduleActItemType;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("commScheduleActItemsId", this.getCommScheduleActItemsId());
        hashMap.put("scheduleIdFk", this.getScheduleIdFk());
        hashMap.put("actionItemNumber", this.getActionItemNumber());
        hashMap.put("scheduleActItemTypeCode", this.getScheduleActItemTypeCode());
        hashMap.put("itemDesctiption", this.getItemDesctiption());
        return hashMap;
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


}