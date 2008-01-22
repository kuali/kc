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
 * A Person Attribute is a name/value pair that is within a namespace.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PersonAttribute extends Attribute {

    private static final long serialVersionUID = -7249779907855883478L;
    
    private String namespaceName = null;
    
    /**
     * Constructs a PersonAttribute.
     * 
     * @param namespaceName the name of the Namespace
     * @param name the attribute's name
     * @param value the attribute's value
     */
    public PersonAttribute(String namespaceName, String name, String value) {
        super(name, value);
        this.namespaceName = namespaceName;
    }

    /**
     * Get the name of the Namespace.
     * @return the name of the Namespace
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * @see org.kuali.kra.kim.pojo.Pojo#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = super.toStringMapper();
        map.put("namespaceName", getNamespaceName());
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
        PersonAttribute attr = (PersonAttribute) obj;
        return namespaceName.equals(attr.namespaceName) && super.equals(obj);
    }
}
