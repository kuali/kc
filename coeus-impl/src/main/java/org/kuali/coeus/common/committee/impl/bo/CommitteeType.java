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
package org.kuali.coeus.common.committee.impl.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * A committee type defines what the committee is responsible for.
 * In the initial release of KC, only one type of committee is 
 * supported: IRB.
 */
@SuppressWarnings("serial")
public class CommitteeType extends KcPersistableBusinessObjectBase {

    public static final String IRB_TYPE_CODE = "1";
    public static final String COI_TYPE_CODE = "2";
    public static final String IACUC_TYPE_CODE = "3";
    
    private String committeeTypeCode;

    private String description;


    public CommitteeType() {
    }

    public String getCommitteeTypeCode() {
        return committeeTypeCode;
    }

    public void setCommitteeTypeCode(String committeeTypeCode) {
        this.committeeTypeCode = committeeTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
