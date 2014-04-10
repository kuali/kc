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
package org.kuali.coeus.propdev.impl.person;

import java.io.Serializable;


public class CoPiInfoDO implements Serializable  {

    private String coPiName;
    private String coPiUnit;
    
    public String getCoPiName() {
        return coPiName;
    }
    public void setCoPiName(String coPiName) {
        this.coPiName = coPiName;
    }
    public String getCoPiUnit() {
        return coPiUnit;
    }
    public void setCoPiUnit(String coPiUnit) {
        this.coPiUnit = coPiUnit;
    }
}
