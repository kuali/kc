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
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.service.ParameterService;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * The S2SCronTrigger is needed because we can't inject the Cron Expression from the SpringBeans.xml file. 
 * Rather, we have to retrieve the Cron Expression from the System Parameters.
 */
public class S2SCronTrigger extends CronTriggerBean {

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
    private static final String S2S_CRON_STARTTIME="s2sschedulercronExpressionstarttime";

    private ParameterService parameterService;
    private String cronExpressionParameterName;

    private static final Log LOG = LogFactory.getLog(S2SCronTrigger.class);

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
    public void afterPropertiesSet() throws ParseException {
        setCronExpression(getSystemCronExpression());
        setStartTime(getS2sCronStartTime());
        super.afterPropertiesSet();
    }

    private Date getS2sCronStartTime() {
        Date defaultDate = new Date(System.currentTimeMillis()+(365*24*60*60*1000L));
        String DATE_FORMAT = "dd-MMM-yyyy hh:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String defaultDateStr = dateFormat.format(defaultDate);
        try {
            String dateString = getParameterValue(S2S_CRON_STARTTIME);
            return dateFormat.parse(dateString);
        }catch (Exception e) {
            LOG.warn("Not able to get the starttime for S2S scheduler from system param table. Set it to "+defaultDateStr);
            return defaultDate;
        }
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
        return this.parameterService.getParameterValue(ProposalDevelopmentDocument.class, key);
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
