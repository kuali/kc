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
package org.kuali.kra.external.Cfda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * This class is the cron trigger class for the CFDA batch job
 */
public class CfdaCronTrigger extends CronTriggerBean implements ApplicationListener {
   /**
    * 
    *      1.  Seconds
           2. Minutes
           3. Hours
           4. Day-of-Month
           5. Month
           6. Day-of-Week
           7. Year (optional field)
    */
    private ParameterService parameterService;
    private boolean refreshed = false;
    
    private static final Log LOG = LogFactory.getLog(CfdaCronTrigger.class);

   
    /**
     * Cron Expression is to be set based upon the value in the system parameters.
     * 
     * @see org.springframework.scheduling.quartz.CronTriggerBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        setCronExpression(Constants.DEFAULT_CRON_EXPRESSION);
        setStartTime(getDefaultCronStartTime());
        super.afterPropertiesSet();
    }
    
    private Date getDefaultCronStartTime() {
        return new Date(System.currentTimeMillis() + (365*24*60*60*1000L)); 
    }
    
    private Date getCronStartTime() {
        String integrationParameter = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, 
                                                                            Constants.PARAMETER_COMPONENT_DOCUMENT,
                                                                            Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
        Date defaultDate; 
        // if the integration parameter is off, this trigger will never fire
        if (!StringUtils.equalsIgnoreCase(integrationParameter, Constants.FIN_SYSTEM_INTEGRATION_ON)) {
            defaultDate = new Date(System.currentTimeMillis() + (365*24*60*60*1000L));
        } else {
            defaultDate = new Date(System.currentTimeMillis());
        }
        
        String DATE_FORMAT = "dd-MMM-yyyy hh:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String defaultDateStr = dateFormat.format(defaultDate);
        try {
            String dateString = getParameterValue(Constants.CFDA_CRON_START_TIME_PARAMETER);
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            LOG.warn("Not able to get the starttime for CFDA from system param table. Set it to " + defaultDateStr);
        }
        return defaultDate;
    }

    /**
     * Get the Cron Expression from the system parameters.
     * 
     * @return the Cron Expression
     */
    private String getSystemCronExpression() {
        String systemCronExpression = getParameterValue(Constants.CFDA_BATCH_JOB_CRON_EXPRESSION_PARAMETER);
        return (StringUtils.isEmpty(systemCronExpression)) ? Constants.DEFAULT_CRON_EXPRESSION : systemCronExpression;
    }

    /**
     * Get system parameter value.
     * 
     * @param key the key (name) of the parameter
     * @return the parameter's value
     */
    private String getParameterValue(String key) {
        return parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_AWARD,Constants.PARAMETER_COMPONENT_DOCUMENT, key);
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }
    
    /**
     * FIXME
     * This is a hack as a result of a rice upgrade to reset the cron expression after it is initialized.
     * This is because the ParamterService not being available when this Bean is being created by Spring
     * and lazy-init or depends-on will not work in this case.
     * {@inheritDoc}
     */
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextStartedEvent && !this.refreshed) {
            try {
                final String newExpr = this.getSystemCronExpression();
                this.refreshed = true;
                this.setCronExpression(newExpr);
                this.setStartTime(this.getCronStartTime());
                LOG.info("refreshing cron expression to [" + newExpr + "].");
            } catch (ParseException e) {
                LOG.warn("unable refresh cron expression");
            }
        }
        
    }


}


