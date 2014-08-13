/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.personnel;

import org.kuali.coeus.common.budget.framework.personnel.JobCode;
import org.kuali.coeus.common.budget.framework.personnel.JobCodeService;
import org.kuali.kra.infrastructure.Constants;
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

    /**
     * 
     * This method provides the appropriate JobCode object
     * for a particular jobCode string.
     * 
     * @param JobCode
     * @return
     */
    public JobCode findJobCodeRef(String jobCode) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(Constants.JOB_CODE, jobCode);
        JobCode ret = (JobCode)businessObjectService.findByPrimaryKey(JobCode.class, queryMap);
        return ret;
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

}
