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
package org.kuali.kra.award.awardhierarchy.sync;

import java.io.Serializable;

import org.kuali.rice.krad.bo.PersistableBusinessObject;

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
