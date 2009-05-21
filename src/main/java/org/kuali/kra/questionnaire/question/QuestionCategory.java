/*
 * Copyright 2006-2008 The Kuali Foundation
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


import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;

public class QuestionCategory extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer categoryTypeCode;
    private String categoryName;

    public QuestionCategory() {

    } 
    
    public Integer getCategoryTypeCode() {
        return categoryTypeCode;
    }

    public void setCategoryTypeCode(Integer categoryTypeCode) {
        this.categoryTypeCode = categoryTypeCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("categoryTypeCode", this.getCategoryTypeCode());
        hashMap.put("categoryName", this.getCategoryName());
        return hashMap;
    }
    
}