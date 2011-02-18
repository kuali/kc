/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.costshare;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.service.ParameterService;

/**
 * This class...
 */
public class CostShareServiceImpl implements CostShareService {
    
    private static final String PARAM_LABEL_NAME = "CostShareProjectPeriodNameLabel";
    
    private static final String NAMESPACE = "KC-GEN";
    private static final String DETAIL_TYPE_CODE = "All"; 
    
    private ParameterService parameterService;
    
    private String label = null;
    private Boolean validateAsFiscalYear = null;

    /**
     * @see org.kuali.kra.costshare.CostShareService#getCostShareLabel()
     */
    public String getCostShareLabel() {
        if (label == null) {
            label = this.getParameterService().getParameterValue(NAMESPACE, DETAIL_TYPE_CODE, PARAM_LABEL_NAME);
        }
        return label;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
