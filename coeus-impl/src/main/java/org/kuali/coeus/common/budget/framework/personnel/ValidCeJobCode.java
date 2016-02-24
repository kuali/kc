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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.framework.core.CostElement;

public class ValidCeJobCode extends KcPersistableBusinessObjectBase {

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
}
