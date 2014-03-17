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
package org.kuali.kra.budget.rates;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class FormulatedType extends KcPersistableBusinessObjectBase {
    


    private static final long serialVersionUID = 8085064744469084409L;
    private String formulatedTypeCode; 
    private String description; 
    
    public FormulatedType() { 

    } 
    
    public String getFormulatedTypeCode() {
        return formulatedTypeCode;
    }

    public void setFormulatedTypeCode(String formulatedTypeCode) {
        this.formulatedTypeCode = formulatedTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}