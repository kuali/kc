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
package org.kuali.kra.protocol;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class CriteriaFieldHelper {

    private String critFieldName;
    private String searchKey;
    private String fieldValue;
    private Class<? extends KcPersistableBusinessObjectBase> clazz;

    public CriteriaFieldHelper() {
        
    }
    
    public CriteriaFieldHelper(String searchKey, String critFieldName, Class<? extends KcPersistableBusinessObjectBase> clazz) {
        this.searchKey = searchKey;
        this.critFieldName = critFieldName;
        this.clazz = clazz;
    }

    public String getCritFieldName() {
        return critFieldName;
    }

    public void setCritFieldName(String critFieldName) {
        this.critFieldName = critFieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Class<? extends KcPersistableBusinessObjectBase> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends KcPersistableBusinessObjectBase> clazz) {
        this.clazz = clazz;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
