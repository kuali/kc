/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.krms;

import org.kuali.rice.krad.bo.BusinessObject;

public class KcKrmsRule implements  BusinessObject {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6371729243943568479L;
    private String id;
    private String namespace;
    private String description;
    private String name;
    private String typeId;
    private String propId;
    private boolean active = true;

    
    public KcKrmsRule() {
        
    }
    
    public KcKrmsRule(String id, String namespace, String description, String name, String typeId, String propId, boolean active) {
        this.id = id;
        this.namespace = namespace;
        this.description = description;
        this.name = name;
        this.typeId = typeId;
        this.propId = propId;
        this.active = active;
    }
    
    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getNamespace() {
        return namespace;
    }



    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getTypeId() {
        return typeId;
    }



    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }



    public String getPropId() {
        return propId;
    }



    public void setPropId(String propId) {
        this.propId = propId;
    }



    public boolean isActive() {
        return active;
    }



    public void setActive(boolean active) {
        this.active = active;
    }

}
