/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.s2s.polling;

import java.text.ParseException;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * The S2SCronTrigger is needed because we can't inject the Cron Expression from the SpringBeans.xml file. Rather, we have to
 * retrieve the Cron Expression from the System Parameters.
 */
public class S2SCronTrigger extends CronTriggerBean {

    /**
     * Default Cron expression which is every 20 minutes.
     */
    private static final String DEFAULT_CRON_EXPRESSION = "0 0/20 * * * ?";

    private KualiConfigurationService configurationService;
    private String cronExpressionParameterName;

    /**
     * Set the Configuration Service. Injected by Spring.
     * 
     * @param configurationService the configuration service
     */
    public void setConfigurationService(KualiConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Cron Expression is to be set based upon the value in the system parameters.
     * 
     * @see org.springframework.scheduling.quartz.CronTriggerBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws ParseException {
        setCronExpression(getSystemCronExpression());
        super.afterPropertiesSet();
    }

    /**
     * Get the Cron Expression from the system parameters.
     * 
     * @return the Cron Expression
     */
    private String getSystemCronExpression() {
        try {
            return getParameterValue(this.cronExpressionParameterName);
        }
        catch (Exception ex) {
            return DEFAULT_CRON_EXPRESSION;
        }
    }

    /**
     * Get a proposal development system parameter value.
     * 
     * @param key the key (name) of the parameter
     * @return the parameter's value
     */
    private String getParameterValue(String key) {
        return configurationService.getParameterValue(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT,
                Constants.PARAMETER_COMPONENT_DOCUMENT, key);
    }

    /**
     * Gets the cronExpressionParameterName attribute.
     * 
     * @return Returns the cronExpressionParameterName.
     */
    public String getCronExpressionParameterName() {
        return cronExpressionParameterName;
    }

    /**
     * Sets the cronExpressionParameterName attribute value.
     * 
     * @param cronExpressionParameterName The cronExpressionParameterName to set.
     */
    public void setCronExpressionParameterName(String cronExpressionParameterName) {
        this.cronExpressionParameterName = cronExpressionParameterName;
    }
}
