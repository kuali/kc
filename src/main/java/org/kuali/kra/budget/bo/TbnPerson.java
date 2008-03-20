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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class TbnPerson extends KraPersistableBusinessObjectBase {
    
    private String tbnId;
    private String personName;
    private String jobCode;
    
    private JobCode jobCodeReference;
    
    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTbnId() {
        return tbnId;
    }

    public void setTbnId(String tbnId) {
        this.tbnId = tbnId;
    }
    
    public JobCode getJobCodeReference() {
        return jobCodeReference;
    }

    public void setJobCodeReference(JobCode jobCodeReference) {
        this.jobCodeReference = jobCodeReference;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("tbnId", getTbnId());
        hashMap.put("personName", getPersonName());
        hashMap.put("jobCode", getJobCode());
        return hashMap;
    }

}
