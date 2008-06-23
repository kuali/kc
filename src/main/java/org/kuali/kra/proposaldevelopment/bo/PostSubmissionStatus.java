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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class PostSubmissionStatus extends KraPersistableBusinessObjectBase {
    
    private Integer postSubmissionStatusCode;
    private String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPostSubmissionStatusCode() {
        return postSubmissionStatusCode;
    }

    public void setPostSubmissionStatusCode(Integer postSubmissionStatusCode) {
        this.postSubmissionStatusCode = postSubmissionStatusCode;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("postSubmissionStatusCode", getPostSubmissionStatusCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }

}
