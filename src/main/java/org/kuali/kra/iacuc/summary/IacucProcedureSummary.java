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
package org.kuali.kra.iacuc.summary;

import java.io.Serializable;

import org.kuali.kra.iacuc.procedures.IacucProcedure;

public class IacucProcedureSummary implements Serializable { 
    
    private static final long serialVersionUID = 5109886893332110100L;

    private Integer procedureCode; 
    private Integer procedureCategoryCode; 
    private String procedureDescription; 
    
    public IacucProcedureSummary(IacucProcedure procedure) { 
        procedureCode = procedure.getProcedureCode();
        procedureCategoryCode = procedure.getProcedureCategoryCode();
        procedureDescription = procedure.getProcedureDescription();
    } 
    
    public Integer getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(Integer procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Integer getProcedureCategoryCode() {
        return procedureCategoryCode;
    }

    public void setProcedureCategoryCode(Integer procedureCategoryCode) {
        this.procedureCategoryCode = procedureCategoryCode;
    }

    public String getProcedureDescription() {
        return procedureDescription;
    }

    public void setProcedureDescription(String procedureDescription) {
        this.procedureDescription = procedureDescription;
    }
  
}