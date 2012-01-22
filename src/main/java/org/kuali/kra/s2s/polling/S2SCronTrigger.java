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
package org.kuali.kra.s2s.polling;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * The S2SCronTrigger is needed because we can't inject the Cron Expression from the SpringBeans.xml file. 
 * Rather, we have to retrieve the Cron Expression from the System Parameters.
 */
public class S2SCronTrigger extends CronTriggerBean implements ApplicationListener {

    /**
     * Default Cron expression which is every 20 minutes.
     * 
     *      1.  Seconds
            2. Minutes
            3. Hours
            4. Day-of-Month
            5. Month
            6. Day-of-Week
            7. Year (optional field)

     */
    private static final String DEFAULT_CRON_EXPRESSION = "0 0/20 * * * ?";
    private static final String S2S_POLLING_SCHEDULER_ENABLED="s2s.polling.scheduler.enabled";
    

    private ParameterService parameterService;
    private String cronExpressionParameterName;
    private DateTimeService dateTimeService;

    private static final Log LOG = LogFactory.getLog(S2SCronTrigger.class);

    private boolean refreshed = false;
    
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * Cron Expression is to be set based upon the value in the system parameters.
     * 
     * @see org.springframework.scheduling.quartz.CronTriggerBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        setCronExpression(DEFAULT_CRON_EXPRESSION);
        setStartTime(getDefaultS2sCronStartTime());
        super.afterPropertiesSet();
    }

    private Date getDefaultS2sCronStartTime() {
        Calendar today = dateTimeService.getCurrentCalendar();
        today.add(Calendar.YEAR, 2);
        return today.getTime();
    }
    
    private Date getS2sCronStartTime() {
        String s2sSchedulerEnabled="false";
        Calendar today = dateTimeService.getCurrentCalendar();
        today.add(Calendar.YEAR, 2);
        Date dateAfterOneYear = today.getTime();
        String DATE_FORMAT = "dd-MMM-yyyy hh:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            s2sSchedulerEnabled = getParameterValue(S2S_POLLING_SCHEDULER_ENABLED);
            if(Boolean.parseBoolean(s2sSchedulerEnabled)){
                return dateTimeService.getCurrentDate();
            }
        }catch (Exception e) {
            String defaultDateStr = dateFormat.format(dateAfterOneYear);
            LOG.warn("Not able to get the starttime for S2S scheduler from system param table. Set it to "+defaultDateStr);
        }
        return dateAfterOneYear;
    }

    /**
     * Get the Cron Expression from the system parameters.
     * 
     * @return the Cron Expression
     */
    private String getSystemCronExpression() {
        String systemCronExpression = getParameterValue(this.cronExpressionParameterName);
        return (StringUtils.isEmpty(systemCronExpression)) ? DEFAULT_CRON_EXPRESSION : systemCronExpression;
    }

    /**
     * Get a proposal development system parameter value.
     * 
     * @param key the key (name) of the parameter
     * @return the parameter's value
     */
    private String getParameterValue(String key) {
        return this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, key);
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
                this.setStartTime(this.getS2sCronStartTime());
                LOG.info("refreshing cron expression to [" + newExpr + "].");
            } catch (ParseException e) {
                LOG.warn("unable refresh cron expression");
            }
        }
        
    }


}
