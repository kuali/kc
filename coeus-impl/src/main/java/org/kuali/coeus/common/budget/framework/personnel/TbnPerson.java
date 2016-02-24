/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
