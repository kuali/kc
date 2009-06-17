/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class ExemptionType   extends KraPersistableBusinessObjectBase implements Comparable<ExemptionType> {

    private String exemptionTypeCode;
    private String description;
    private String detailedDescription;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExemptionTypeCode() {
        return exemptionTypeCode;
    }

    public void setExemptionTypeCode(String exemptionTypeCode) {
        this.exemptionTypeCode = exemptionTypeCode;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("exemptionTypeCode", this.getExemptionTypeCode());
        propMap.put("description", this.getDescription());
        propMap.put("detailed description", this.getDetailedDescription());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }

    public int compareTo(ExemptionType o) {
        return this.description.compareTo(o.description);
    }

}
