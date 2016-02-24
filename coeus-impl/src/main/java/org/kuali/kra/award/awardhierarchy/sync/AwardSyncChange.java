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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.Award;

public class AwardSyncChange extends KcPersistableBusinessObjectBase {

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
