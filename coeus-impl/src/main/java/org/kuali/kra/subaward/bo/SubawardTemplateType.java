/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
