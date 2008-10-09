/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the YnqExplanation BO.
 */
@SuppressWarnings("serial")
public class YnqExplanationId implements Serializable {
    
    @Column(name="EXPLANATION_TYPE")
    private String explanationType; 

    @Column(name="QUESTION_ID")
    private String questionId; 
    
    public String getExplanationType() {
        return this.explanationType;
    }
    
    public String getQuestionId() {
        return this.questionId;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof YnqExplanationId)) return false;
        if (obj == null) return false;
        YnqExplanationId other = (YnqExplanationId) obj;
        return StringUtils.equals(explanationType, other.explanationType) &&
               StringUtils.equals(questionId, other.questionId);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(explanationType).append(questionId).toHashCode();
    }
}
