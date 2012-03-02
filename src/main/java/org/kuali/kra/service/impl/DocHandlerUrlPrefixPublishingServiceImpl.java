/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.service.DocHandlerUrlPrefixPublishingService;
import org.kuali.rice.coreservice.api.parameter.EvaluationOperator;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.api.parameter.ParameterType;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.InitializingBean;

/**
 * The DocHandlerUrlPrefixPublishingService Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class DocHandlerUrlPrefixPublishingServiceImpl implements DocHandlerUrlPrefixPublishingService, InitializingBean {
    private String parameterName;
    private String parameterApplicationId;
    private String parameterNamespaceCode;
    private String parameterComponentCode;
    private String parameterTypeCode;
    private String parameterValue;

    private ParameterService parameterService;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterApplicationId() {
        return parameterApplicationId;
    }

    public void setParameterApplicationId(String parameterApplicationId) {
        this.parameterApplicationId = parameterApplicationId;
    }

    public String getParameterNamespaceCode() {
        return parameterNamespaceCode;
    }

    public void setParameterNamespaceCode(String parameterNamespaceCode) {
        this.parameterNamespaceCode = parameterNamespaceCode;
    }

    public String getParameterComponentCode() {
        return parameterComponentCode;
    }

    public void setParameterComponentCode(String parameterComponentCode) {
        this.parameterComponentCode = parameterComponentCode;
    }

    public String getParameterTypeCode() {
        return parameterTypeCode;
    }

    public void setParameterTypeCode(String parameterTypeCode) {
        this.parameterTypeCode = parameterTypeCode;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    @Override
    public void publishDocHandlerUrlPrefix() {
        ParameterType.Builder parameterType = ParameterType.Builder.create(parameterTypeCode);
        Parameter.Builder parameter = Parameter.Builder.create(parameterApplicationId, parameterNamespaceCode, parameterComponentCode, parameterName, parameterType);
        parameter.setDescription("KC application docHandler prefix");
        parameter.setValue(parameterValue);
        parameter.setEvaluationOperator(EvaluationOperator.ALLOW);
        Parameter existingParameter = parameterService.getParameter(parameterNamespaceCode, parameterComponentCode, parameterName);
        if(existingParameter == null) {
            parameterService.createParameter(parameter.build());
        } else if(!StringUtils.equals(existingParameter.getValue(), parameterValue)) {
            parameter.setObjectId(existingParameter.getObjectId());
            parameter.setVersionNumber(existingParameter.getVersionNumber());
            parameterService.updateParameter(parameter.build());
        } 
    }  

    @Override
    public void afterPropertiesSet() throws Exception {
        publishDocHandlerUrlPrefix();
    }
}