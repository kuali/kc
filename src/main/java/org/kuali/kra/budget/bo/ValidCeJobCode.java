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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidCeJobCode extends KraPersistableBusinessObjectBase {
	private String costElement;
	private String jobCode;
	
	private JobCode jobCodeReference;
	private CostElement costElementReference;

	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	/**
     * Gets the jobCodeReference attribute. 
     * @return Returns the jobCodeReference.
     */
    public JobCode getJobCodeReference() {
        return jobCodeReference;
    }

    /**
     * Sets the jobCodeReference attribute value.
     * @param jobCodeReference The jobCodeReference to set.
     */
    public void setJobCodeReference(JobCode jobCodeReference) {
        this.jobCodeReference = jobCodeReference;
    }

    /**
     * Gets the costElementReference attribute. 
     * @return Returns the costElementReference.
     */
    public CostElement getCostElementReference() {
        return costElementReference;
    }

    /**
     * Sets the costElementReference attribute value.
     * @param costElementReference The costElementReference to set.
     */
    public void setCostElementReference(CostElement costElementReference) {
        this.costElementReference = costElementReference;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("costElement", getCostElement());
		hashMap.put("jobCode", getJobCode());
		return hashMap;
	}
}
