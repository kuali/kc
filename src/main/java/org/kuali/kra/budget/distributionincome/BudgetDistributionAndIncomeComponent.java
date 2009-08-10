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
package org.kuali.kra.budget.distributionincome;

import java.util.LinkedHashMap;

import org.kuali.kra.budget.core.BudgetAssociate;

public abstract class BudgetDistributionAndIncomeComponent extends BudgetAssociate {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5475883963362089242L;
    private Integer documentComponentId;

    public abstract String getDocumentComponentIdKey();
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("documentComponentId", getDocumentComponentId());
        return hashMap;
    }

    public Integer getDocumentComponentId() {
        return documentComponentId;
    }

    public void setDocumentComponentId(Integer costShareId) {
        this.documentComponentId = costShareId;
    }
    
}
