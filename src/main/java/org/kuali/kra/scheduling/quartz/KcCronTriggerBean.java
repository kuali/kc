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
package org.kuali.kra.scheduling.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * The KC Cron Trigger Bean is needed because we can't inject
 * the Cron Expression from the SpringBeans.xml file.  Rather,
 * we have to retrieve the Cron Expression from the System Parameters.
 */
public class KcCronTriggerBean extends CronTriggerBean {

    private static final Log LOG = LogFactory.getLog(KcCronTriggerBean.class);

    /**
     * Default Cron expression which is 1 AM every day.
     */
    private static final String DEFAULT_CRON_EXPRESSION = "0 0 1 * * ?";
    
    private String defaultCronExpression = DEFAULT_CRON_EXPRESSION;
    private String parameterNamespace;
    private String parameterComponent;
    private String cronExpressionParameterName = KeyConstants.PESSIMISTIC_LOCKING_CRON_EXPRESSION;
    private String triggerEnabledParameterName;
    private String startTimeParameterName;
    private ParameterService parameterService;
    private DateTimeService dateTimeService;
    
    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * We need to set the Cron Expression based upon the value in the system parameters.
     * 
     * @see org.springframework.scheduling.quartz.CronTriggerBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        setCronExpression(getSystemCronExpression());
        setStartTime(getCronStartTime());
        super.afterPropertiesSet();
    }
    
    /**
     * Get the Cron Expression from the system parameters.
     * @return the Cron Expression
     */
    protected String getSystemCronExpression() {
        if (StringUtils.isNotBlank(cronExpressionParameterName) 
                && getParameterService().parameterExists(parameterNamespace, parameterComponent, cronExpressionParameterName)) {
            final String param = getParameterService().getParameterValueAsString(parameterNamespace, parameterComponent, cronExpressionParameterName);
            if (param != null) {
                return param;
            } 
            LOG.warn("parameter [" + cronExpressionParameterName + "] not found using default value of [" + DEFAULT_CRON_EXPRESSION + "].");
    
        }
        return DEFAULT_CRON_EXPRESSION;
    }
    
    protected Date getCronStartTime() {
        Calendar today = dateTimeService.getCurrentCalendar();
        today.add(Calendar.YEAR, 2);
        Date cronStartTime = today.getTime();
        if (!isTriggerEnabled()) {
            return cronStartTime;
        } else if (StringUtils.isBlank(startTimeParameterName)) {
            cronStartTime = dateTimeService.getCurrentDate();
        } else {
            String CUSTOM_DATE_FORMAT = "dd-MMM-yyyy hh:mm a";
            SimpleDateFormat dateFormat = new SimpleDateFormat(CUSTOM_DATE_FORMAT);
            try {
                String parmStartTime = getParameterService().getParameterValueAsString(parameterNamespace, parameterComponent, startTimeParameterName);
                try {
                    cronStartTime = dateTimeService.convertToDate(parmStartTime);
                } catch (ParseException e) {
                    cronStartTime = dateFormat.parse(parmStartTime);
                }
            } catch (Exception e) {
                String defaultDateStr = dateFormat.format(cronStartTime);
                LOG.warn("Not able to get the starttime for " + this.getJobName() + " scheduler from system param table. Set it to " + defaultDateStr);
            }
        }
        return cronStartTime;
    }
    
    protected boolean isTriggerEnabled() {
        if (StringUtils.isNotBlank(triggerEnabledParameterName)) {
            if (getParameterService().parameterExists(parameterNamespace, parameterComponent, triggerEnabledParameterName)) {
                return getParameterService().getParameterValueAsBoolean(parameterNamespace, parameterComponent, triggerEnabledParameterName);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public String getDefaultCronExpression() {
        return defaultCronExpression;
    }

    public void setDefaultCronExpression(String defaultCronExpression) {
        this.defaultCronExpression = defaultCronExpression;
    }

    public String getCronExpressionParameterName() {
        return cronExpressionParameterName;
    }

    public void setCronExpressionParameterName(String cronExpressionParameterName) {
        this.cronExpressionParameterName = cronExpressionParameterName;
    }

    public String getTriggerEnabledParameterName() {
        return triggerEnabledParameterName;
    }

    public void setTriggerEnabledParameterName(String triggerEnabledParameterName) {
        this.triggerEnabledParameterName = triggerEnabledParameterName;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public String getStartTimeParameterName() {
        return startTimeParameterName;
    }

    public void setStartTimeParameterName(String startTimeParameterName) {
        this.startTimeParameterName = startTimeParameterName;
    }

    protected DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public String getParameterNamespace() {
        return parameterNamespace;
    }

    public void setParameterNamespace(String parameterNamespace) {
        this.parameterNamespace = parameterNamespace;
    }

    public String getParameterComponent() {
        return parameterComponent;
    }

    public void setParameterComponent(String parameterComponent) {
        this.parameterComponent = parameterComponent;
    }
}
