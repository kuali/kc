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
