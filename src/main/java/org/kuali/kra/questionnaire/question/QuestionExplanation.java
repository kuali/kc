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
package org.kuali.kra.questionnaire.question;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class QuestionExplanation extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer questionExplanationId; 
    private Integer questionId; 
    private String explanationType; 
    private String explanation; 
    

    public QuestionExplanation() { 

    } 
    
    public Integer getQuestionExplanationId() {
        return questionExplanationId;
    }

    public void setQuestionExplanationId(Integer questionExplanationId) {
        this.questionExplanationId = questionExplanationId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getExplanationType() {
        return explanationType;
    }

    public void setExplanationType(String explanationType) {
        this.explanationType = explanationType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("questionExplanationId", this.getQuestionExplanationId());
        hashMap.put("questionId", this.getQuestionId());
        hashMap.put("explanationType", this.getExplanationType());
        hashMap.put("explanation", this.getExplanation());
        return hashMap;
    }
    
}