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
package org.kuali.kra.bo;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;


/**
 * Class representation of the Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * @author $Author: gmcgrego $
 * @version $Revision: 1.3 $
 */
public class PersonEditableField extends KraPersistableBusinessObjectBase implements MutableInactivatable {

    private String fieldName;

    private boolean active;

    private String moduleCode;

    private Long personEditableFieldId;

    private CoeusModule coeusModule;

    /**
     * Gets the value of fieldName
     *
     * @return the value of fieldName
     */
    public String getFieldName() {
        return this.fieldName;
    }

    /**
     * Sets the value of fieldName
     *
     * @param argFieldName Value to assign to this.fieldName
     */
    public void setFieldName(String argFieldName) {
        this.fieldName = argFieldName;
    }

    /**
     * Read access to active flag
     * @return boolean active or not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Write access to active flag
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public Long getPersonEditableFieldId() {
        return personEditableFieldId;
    }

    public void setPersonEditableFieldId(Long personEditableFieldId) {
        this.personEditableFieldId = personEditableFieldId;
    }

    public CoeusModule getCoeusModule() {
        // if "All" is implemented 
        //        if (StringUtils.equals("0", moduleCode) && coeusModule == null) { 
        //            coeusModule = new CoeusModule(); 
        //            coeusModule.setModuleCode("0"); 
        //            coeusModule.setDescription("All"); 
        //        } 
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }
}
