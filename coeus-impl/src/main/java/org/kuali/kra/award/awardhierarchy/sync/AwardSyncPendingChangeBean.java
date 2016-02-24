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
package org.kuali.kra.award.awardhierarchy.sync;

import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.io.Serializable;

/**
 * DTO bean for saving pending sync changes to build and persist after
 * a document save.
 */
public class AwardSyncPendingChangeBean implements Serializable {

    private static final long serialVersionUID = -7950844997053979351L;
    private AwardSyncType syncType;
    private PersistableBusinessObject object;
    private String attrName;
    private String awardAttr;
    
    public AwardSyncPendingChangeBean() {
        
    }
    
    public AwardSyncPendingChangeBean(AwardSyncType syncType, PersistableBusinessObject object, String awardAttr, String attrName) {
        this.syncType = syncType;
        this.object = object;
        this.attrName = attrName;
        this.awardAttr = awardAttr;
    }
    
    public AwardSyncPendingChangeBean(AwardSyncType syncType, PersistableBusinessObject object, String awardAttr) {
        this.syncType = syncType;
        this.object = object;
        this.attrName = null;
        this.awardAttr = awardAttr;
    }

    public AwardSyncType getSyncType() {
        return syncType;
    }
    public void setSyncType(AwardSyncType syncType) {
        this.syncType = syncType;
    }
    public PersistableBusinessObject getObject() {
        return object;
    }
    public void setObject(PersistableBusinessObject object) {
        this.object = object;
    }
    public String getAttrName() {
        return attrName;
    }
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
    public String getAwardAttr() {
        return awardAttr;
    }
    public void setAwardAttr(String awardAttr) {
        this.awardAttr = awardAttr;
    }      
}
