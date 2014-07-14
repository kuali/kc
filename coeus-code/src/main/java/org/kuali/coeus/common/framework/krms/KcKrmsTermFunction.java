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
package org.kuali.coeus.common.framework.krms;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.util.List;


public class KcKrmsTermFunction extends KcPersistableBusinessObjectBase {
    

    private static final long serialVersionUID = 229744717657419313L;
    private Long kcKrmsTermFunctionId;
    private String krmsTermName;
    private String description;
    private String returnType;
    private String functionType;
    private List<KcKrmsTermFunctionParam> termFunctionParams;
    public Long getKcKrmsTermFunctionId() {
        return kcKrmsTermFunctionId;
    }
    public void setKcKrmsTermFunctionId(Long kcKrmsTermFunctionId) {
        this.kcKrmsTermFunctionId = kcKrmsTermFunctionId;
    }
    public String getKrmsTermName() {
        return krmsTermName;
    }
    public void setKrmsTermSpecId(String krmsTermName) {
        this.krmsTermName = krmsTermName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getReturnType() {
        return returnType;
    }
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    public String getFunctionType() {
        return functionType;
    }
    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }
    public List<KcKrmsTermFunctionParam> getTermFunctionParams() {
        return termFunctionParams;
    }
    public void setTermFunctionParams(List<KcKrmsTermFunctionParam> termFunctionParams) {
        this.termFunctionParams = termFunctionParams;
    }
    
}
