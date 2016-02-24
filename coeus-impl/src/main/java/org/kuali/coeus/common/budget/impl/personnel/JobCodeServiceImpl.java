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
package org.kuali.coeus.common.budget.impl.personnel;

import org.kuali.coeus.common.budget.framework.personnel.JobCode;
import org.kuali.coeus.common.budget.framework.personnel.JobCodeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("jobCodeService")
public class JobCodeServiceImpl implements JobCodeService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;    

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    /**
     * 
     * This method provides the appropriate JobCode object
     * for a particular jobCode string.
     * 
     */
    public JobCode findJobCodeRef(String jobCode) {
        return getDataObjectService().find(JobCode.class, jobCode);
    }

    public String findJobCodeTitle(String jobCode) {
        String jobTitle = null;
        JobCode jcRef= findJobCodeRef(jobCode);
        if (jcRef!= null) {
            jobTitle = jcRef.getJobTitle();
        }
        return jobTitle;
    }
    

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

}
