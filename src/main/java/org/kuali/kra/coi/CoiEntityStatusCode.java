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
package org.kuali.kra.coi;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CoiEntityStatusCode extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private String entityStatusCode;

    private String description;

    public CoiEntityStatusCode() {
    }

    public String getEntityStatusCode() {
        return entityStatusCode;
    }

    public void setEntityStatusCode(String entityStatusCode) {
        this.entityStatusCode = entityStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
