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
package org.kuali.kra.award.awardhierarchy.sync;

import java.util.Map;

/**
 * Class used to serialize changes to xml for persistence.
 */
public class AwardSyncXmlExport {
    private String className;
    private boolean addIfNotFound = true;
    private boolean partOfObjectKey;
    private Map<String, Object> keys;
    private Map<String, Object> values;
    
    public String toString() {
        return "(className, addIfNotFound, keys, values)=(" + className + "," + addIfNotFound + "," + keys + "," + values + ")";
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public boolean isAddIfNotFound() {
        return addIfNotFound;
    }
    public void setAddIfNotFound(boolean addIfNotFound) {
        this.addIfNotFound = addIfNotFound;
    }
    public Map<String, Object> getKeys() {
        return keys;
    }
    public void setKeys(Map<String, Object> keys) {
        this.keys = keys;
    }
    public Map<String, Object> getValues() {
        return values;
    }
    public void setValues(Map<String, Object> values) {
        this.values = values;
    }
    public boolean isPartOfObjectKey() {
        return partOfObjectKey;
    }
    public void setPartOfObjectKey(boolean partOfObjectKey) {
        this.partOfObjectKey = partOfObjectKey;
    }
}
