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
package org.kuali.kra.subaward.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class SubawardTemplateType extends KcPersistableBusinessObjectBase{
    
    private Integer templateTypeCode;

    private String description;

    /**.
    * This is the Getter Method for templateTypeCode
    * @return Returns the templateTypeCode.
    */
    public Integer getTemplateTypeCode() {
        return templateTypeCode;
    }

    /**.
     * This is the Setter Method for templateTypeCode
     * @param templateTypeCode The templateTypeCode to set.
     */
    public void setTemplateTypeCode(Integer templateTypeCode) {
        this.templateTypeCode = templateTypeCode;
    }

    /**.
    * This is the Getter Method for description
    * @return Returns the description.
    */
    public String getDescription() {
        return description;
    }

    /**.
     * This is the Getter Method for description
     * @return Returns the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
