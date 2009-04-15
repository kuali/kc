/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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

/**
 * Business Object for Post Submission Status.
 */
public class PostSubmissionStatus extends KraPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 4195140481939165537L;
    
    private Integer postSubmissionStatusCode;
    private String description;
    private String definition;
    
    /**
     * Gets the short description.
     * @return the short description.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Sets the short description.
     * @param description the short description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the post submission status code.
     * @return the post submission status code.
     */
    public Integer getPostSubmissionStatusCode() {
        return this.postSubmissionStatusCode;
    }

    /**
     * Sets the post submission status code.
     * @param postSubmissionStatusCode the post submission status code.
     */
    public void setPostSubmissionStatusCode(final Integer postSubmissionStatusCode) {
        this.postSubmissionStatusCode = postSubmissionStatusCode;
    }

    /**
     * Gets the long definition.
     * @return the long definition.
     */
    public String getDefinition() {
        return this.definition;
    }

    /**
     * Sets the long definition.
     * @param definition the long definition.
     */
    public void setDefinition(final String definition) {
        this.definition = definition;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("postSubmissionStatusCode", this.getPostSubmissionStatusCode());
        hashMap.put("description", this.getDescription());
        hashMap.put("definition", this.getDefinition());
        return hashMap;
    }
}
