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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardSyncChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -1131991638179375877L;

    private Long awardSyncChangeId;

    private Long awardId;

    private Award award;

    private String xml;

    private String className;

    private String attrName;

    private String objectDesc;

    private String dataDesc;

    private String syncType;

    private String syncDescendants;

    private boolean syncFabricated;

    private boolean syncCostSharing;

    private transient boolean delete;

    private transient AwardSyncXmlExport xmlExport;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public Long getAwardSyncChangeId() {
        return awardSyncChangeId;
    }

    public void setAwardSyncChangeId(Long awardSyncChangeId) {
        this.awardSyncChangeId = awardSyncChangeId;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isSyncFabricated() {
        return syncFabricated;
    }

    public void setSyncFabricated(boolean syncFabricated) {
        this.syncFabricated = syncFabricated;
    }

    public boolean isSyncCostSharing() {
        return syncCostSharing;
    }

    public void setSyncCostSharing(boolean syncCostSharing) {
        this.syncCostSharing = syncCostSharing;
    }

    public AwardSyncXmlExport getXmlExport() {
        return xmlExport;
    }

    public void setXmlExport(AwardSyncXmlExport xmlExport) {
        this.xmlExport = xmlExport;
    }

    public String getObjectDesc() {
        return objectDesc;
    }

    public void setObjectDesc(String objectDesc) {
        this.objectDesc = objectDesc;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public String getSyncDescendants() {
        return syncDescendants;
    }

    public void setSyncDescendants(String syncDescendants) {
        this.syncDescendants = syncDescendants;
    }

    public AwardSyncDescendantValues getSyncDescendantsType() {
        for (AwardSyncDescendantValues value : AwardSyncDescendantValues.values()) {
            if (StringUtils.equals(value.getSyncValue(), syncDescendants)) {
                return value;
            }
        }
        return null;
    }

    public void setSyncDescendantsType(AwardSyncDescendantValues value) {
        syncDescendants = value.getSyncValue();
    }

    public AwardSyncType getType() {
        for (AwardSyncType type : AwardSyncType.values()) {
            if (StringUtils.equals(type.getSyncValue(), syncType)) {
                return type;
            }
        }
        return null;
    }

    public void setType(AwardSyncType type) {
        syncType = type.getSyncValue();
    }
}
