/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.personnel;

import javax.persistence.*;

import org.kuali.coeus.common.budget.api.personnel.TbnPersonContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * To Be Named person business object
 */
@Entity
@Table(name = "TBN")
public class TbnPerson extends KcPersistableBusinessObjectBase implements TbnPersonContract {

    @Id
    @Column(name = "TBN_ID")
    private String tbnId;

    @Column(name = "PERSON_NAME")
    private String personName;

    @Column(name = "JOB_CODE")
    private String jobCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE", insertable = false, updatable = false)
    private JobCode jobCodeReference;
    
    @Transient
    private int quantity;

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    @Override
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

    @Override
    public JobCode getJobCodeReference() {
        return jobCodeReference;
    }

    public void setJobCodeReference(JobCode jobCodeReference) {
        this.jobCodeReference = jobCodeReference;
    }

    @Override
    public String getId() {
        return getTbnId();
    }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
