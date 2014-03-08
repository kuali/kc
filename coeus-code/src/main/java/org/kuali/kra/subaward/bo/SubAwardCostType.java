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

public class SubAwardCostType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;
    private String costTypeCode;
    private String costTypeDescription;
    public String getCostTypeCode() {
        return costTypeCode;
    }
    public void setCostTypeCode(String costTypeCode) {
        this.costTypeCode = costTypeCode;
    }
    public String getCostTypeDescription() {
        return costTypeDescription;
    }
    public void setCostTypeDescription(String costTypeDescription) {
        this.costTypeDescription = costTypeDescription;
    }
    
}
