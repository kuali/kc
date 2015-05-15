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
package org.kuali.coeus.common.framework.person.attr;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;


/**
 * Class representation of the Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * @author $Author: gmcgrego $
 * @version $Revision: 1.3 $
 */
public class PersonEditableField extends KcPersistableBusinessObjectBase implements MutableInactivatable {

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
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }
}
