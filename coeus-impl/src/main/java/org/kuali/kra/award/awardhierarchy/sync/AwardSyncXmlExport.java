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
