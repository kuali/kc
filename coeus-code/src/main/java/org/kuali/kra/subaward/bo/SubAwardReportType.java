/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.subaward.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;


public class SubAwardReportType extends KcPersistableBusinessObjectBase {

    private Integer subAwardReportTypeCode;
    
    private String description;

    private Integer sortId;

    public Integer getSubAwardReportTypeCode() {
        return subAwardReportTypeCode;
    }

    public void setSubAwardReportTypeCode(Integer subAwardReportTypeCode) {
        this.subAwardReportTypeCode = subAwardReportTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the sortId attribute. 
     * @return Returns the sortId.
     */
    public Integer getSortId() {
        return sortId;
    }

    /**
     * Sets the sortId attribute value.
     * @param sortId The sortId to set.
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }
    

}
