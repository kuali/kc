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

/**
 * Enum with the types of logs that award sync can generate.
 */
public enum AwardSyncLogType {

    CHANGE_LOG("CL", "Change"), VALIDATION_MESSAGE("VM", "Validation Message");
    private String code;
    private String desc;
    private AwardSyncLogType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    public static AwardSyncLogType getLogTypeFromCode(String code) {
        for (AwardSyncLogType type : AwardSyncLogType.values()) {
            if (StringUtils.equals(code, type.getCode())) {
                return type;
            }
        }
        return null;
    }
    
}
