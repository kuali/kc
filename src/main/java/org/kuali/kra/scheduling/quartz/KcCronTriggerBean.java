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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
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
public class KcCronTriggerBean extends CronTriggerBean implements ApplicationListener {

    private static final Log LOG = LogFactory.getLog(KcCronTriggerBean.class);

    /**
     * Default Cron expression which is 1 AM every day.
     */
    private static final String DEFAULT_CRON_EXPRESSION = "0 0 1 * * ?";
    
    private ParameterService parameterService;
    private boolean refreshed = false;
    
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
        //setCronExpression(DEFAULT_CRON_EXPRESSION); 
        setCronExpression(getSystemCronExpression());
        super.afterPropertiesSet();
    }
    
    /**
     * Get the Cron Expression from the system parameters.
     * @return the Cron Expression
     */
    private String getSystemCronExpression() {
        final String param = this.getParameterValue(KeyConstants.PESSIMISTIC_LOCKING_CRON_EXPRESSION);
        if (param != null) {
            return param;
        } 
        LOG.warn("parameter [" + KeyConstants.PESSIMISTIC_LOCKING_CRON_EXPRESSION + "] not found using default value of [" + DEFAULT_CRON_EXPRESSION + "].");

        return DEFAULT_CRON_EXPRESSION;
    }
    
    /**
     * Get a proposal development system parameter value.
     * @param key the key (name) of the parameter
     * @return the parameter's value or null if the parameter does not exist.
     */
    private String getParameterValue(String key) {
        if (this.parameterService.parameterExists(ProposalDevelopmentDocument.class, key)) {
            return this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, key);
        }
        
        return null;
    }

    /**
     * FIXME
     * This is a hack as a result of a rice upgrade to reset the cron expression after it is initialized.
     * This is because the ParamterService not being available when this Bean is being created by Spring
     * and lazy-init or depends-on will not work in this case.
     * {@inheritDoc}
     */
    public void onApplicationEvent(ApplicationEvent event) {
        /*
        if (event instanceof ContextStartedEvent && !this.refreshed) {
            try {
                final String newExpr = this.getSystemCronExpression();
                this.refreshed = true;
                this.setCronExpression(newExpr);
                LOG.info("refreshing cron expression to [" + newExpr + "].");
            } catch (ParseException e) {
                LOG.warn("unable refresh cron expression");
            }
        }
        */
    }
}
