/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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

import org.kuali.kra.award.home.ContactRole;

/**
 * This class models the UnitAdministratorType
 */
public class UnitAdministratorType extends KraPersistableBusinessObjectBase implements ContactRole {

    public static final String OSP_ADMINISTRATOR_TYPE_CODE = "2";

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8872381393239718701L;

    private String unitAdministratorTypeCode;

    private String description;

    private Boolean multiplesFlag;

    private String defaultGroupFlag;

    private UnitContactType unitContactType;

    public UnitAdministratorType() {
        super();
    }

    public String getUnitAdministratorTypeCode() {
        return unitAdministratorTypeCode;
    }

    public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
        this.unitAdministratorTypeCode = unitAdministratorTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleCode() {
        return getUnitAdministratorTypeCode();
    }

    public String getRoleDescription() {
        return getDescription();
    }

    /**
     * @return
     */
    public UnitContactType getUnitContactType() {
        return unitContactType;
    }

    /**
     * @param unitContactType
     */
    public void setUnitContactType(UnitContactType unitContactType) {
        this.unitContactType = unitContactType;
    }

    /**
     * Gets the multiplesFlag attribute. 
     * @return Returns the multiplesFlag.
     */
    public Boolean getMultiplesFlag() {
        return multiplesFlag;
    }

    /**
     * Sets the multiplesFlag attribute value.
     * @param multiplesFlag The multiplesFlag to set.
     */
    public void setMultiplesFlag(Boolean multiplesFlag) {
        this.multiplesFlag = multiplesFlag;
    }

    /**
     * Gets the defaultGroupFlag attribute. 
     * @return Returns the defaultGroupFlag.
     */
    public String getDefaultGroupFlag() {
        return defaultGroupFlag;
    }

    /**
     * Sets the defaultGroupFlag attribute value.
     * @param defaultGroupFlag The defaultGroupFlag to set.
     */
    public void setDefaultGroupFlag(String defaultGroupFlag) {
        this.defaultGroupFlag = defaultGroupFlag;
    }
}
