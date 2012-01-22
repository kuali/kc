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
 * This class is minute entry type code.
 */
public class MinuteEntryType extends KraPersistableBusinessObjectBase implements Comparable<MinuteEntryType> {

    public static final String ATTENDANCE = "2";

    public static final String PROTOCOL = "3";

    public static final String ACTION_ITEM = "4";

    public static final String PROTOCOL_REVIEWER_COMMENT = "6";

    private static final long serialVersionUID = 3106451618464691958L;

    private String minuteEntryTypeCode;

    private Integer sortId;

    private String description;

    public MinuteEntryType() {
    }

    public String getMinuteEntryTypeCode() {
        return minuteEntryTypeCode;
    }

    public void setMinuteEntryTypeCode(String minuteEntryTypeCode) {
        this.minuteEntryTypeCode = minuteEntryTypeCode;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int compareTo(MinuteEntryType arg) {
        return this.getSortId().compareTo(arg.getSortId());
    }
}
