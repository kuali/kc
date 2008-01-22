/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.pojo;

import java.util.LinkedHashMap;

/**
 * An Attribute is a name/value pair.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class Attribute extends Pojo {
    
    private static final long serialVersionUID = 3687404087107496071L;
    
    private String name;
    private String value;
    
    /**
     * Constructs a Attribute.
     * @param name the name of the attribute
     * @param value the value for the attribute
     */
    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Get the attribute's name.
     * @return the attribute's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the attribute's value.
     * @return the attribute's value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * @see org.kuali.kra.kim.pojo.Pojo#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", getName());
        map.put("value", getValue());
        return map;
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!obj.getClass().equals(this.getClass()))
            return false;
        Attribute attr = (Attribute) obj;
        return name.equals(attr.name) && value.equals(attr.value);
    }
}
