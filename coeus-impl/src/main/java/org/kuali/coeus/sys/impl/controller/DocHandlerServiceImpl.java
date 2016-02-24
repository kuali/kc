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
package org.kuali.coeus.sys.impl.controller;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.controller.SysFrameworkControllerConstants;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.config.ConfigurationException;
import org.kuali.rice.core.api.config.module.RunMode;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.api.parameter.EvaluationOperator;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.api.parameter.ParameterType;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("docHandlerService")
public class DocHandlerServiceImpl implements DocHandlerService, InitializingBean {

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Override
    public String getDocHandlerUrl(String routeHeaderId) {
        DocumentRouteHeaderValue routeHeader = routeHeaderService.getRouteHeader(routeHeaderId);
        return routeHeader.getDocumentType().getResolvedDocumentHandlerUrl();
    }

    @Override
    public void publishDocHandlerUrlPrefix() {
        if (RunMode.EMBEDDED == getKewRunMode()) {
            ParameterType.Builder parameterType = ParameterType.Builder.create("CONFG");
            Parameter.Builder parameter = Parameter.Builder.create(getApplicationId(), KewApiConstants.KEW_NAMESPACE, ParameterConstants.ALL_COMPONENT, SysFrameworkControllerConstants.CONFIG_KUALI_DOCHANDLER_URL_PREFIX, parameterType);
            parameter.setDescription("KC application docHandler prefix");
            parameter.setValue(getApplicationUrl());
            parameter.setEvaluationOperator(EvaluationOperator.ALLOW);
            Parameter existingParameter = parameterService.getParameter(KewApiConstants.KEW_NAMESPACE, ParameterConstants.ALL_COMPONENT, SysFrameworkControllerConstants.CONFIG_KUALI_DOCHANDLER_URL_PREFIX);
            if (existingParameter == null) {
                parameterService.createParameter(parameter.build());
            } else if (!StringUtils.equals(existingParameter.getValue(), getApplicationUrl())) {
                parameter.setObjectId(existingParameter.getObjectId());
                parameter.setVersionNumber(existingParameter.getVersionNumber());
                parameterService.updateParameter(parameter.build());
            }
        }
    }

    protected RunMode getKewRunMode() {
        String runMode = configurationService.getPropertyValueAsString(KewApiServiceLocator.KEW_RUN_MODE_PROPERTY);
        if (StringUtils.isBlank(runMode)) {
            throw new ConfigurationException("Failed to determine kew run mode.  Please be sure to set configuration parameter " + KewApiServiceLocator.KEW_RUN_MODE_PROPERTY);
        }
        return RunMode.valueOf(runMode.toUpperCase());
    }

    protected String getApplicationId() {
        String applicationId = configurationService.getPropertyValueAsString(CoreConstants.Config.APPLICATION_ID);
        if (StringUtils.isBlank(applicationId)) {
            throw new ConfigurationException("Failed to determine the application id.  Please be sure to set configuration parameter " + CoreConstants.Config.APPLICATION_ID);
        }
        return applicationId;
    }

    protected String getApplicationUrl() {
        String applicationUrl = configurationService.getPropertyValueAsString(KRADConstants.ConfigParameters.APPLICATION_URL);
        if (StringUtils.isBlank(applicationUrl)) {
            throw new ConfigurationException("Failed to determine the application url.  Please be sure to set configuration parameter " + KRADConstants.ConfigParameters.APPLICATION_URL);
        }
        return applicationUrl;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        publishDocHandlerUrlPrefix();
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
