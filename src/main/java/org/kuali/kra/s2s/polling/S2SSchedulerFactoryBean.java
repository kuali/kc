/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 
 * This class is required in order to stop scheduling of tasks whose cron triggers are either invalid or are those that will not
 * fire ever again.
 */
public class S2SSchedulerFactoryBean extends SchedulerFactoryBean {
    private static final Logger LOG = Logger.getLogger(S2SSchedulerFactoryBean.class);
    private List<S2SCronTrigger> triggers;

    /**
     * Gets the triggers attribute.
     * 
     * @return Returns the triggers.
     */
    public List<S2SCronTrigger> getTriggers() {
        return triggers;
    }

    /**
     * This method checks if cron expressions are valid and will run. Only then, they are passed on to super class for scheduling
     * 
     * @param triggers The triggers to set.
     */
    public void setTriggers(List<S2SCronTrigger> triggers) {
        this.triggers = triggers;

        CronExpression cronExpression;
        List<S2SCronTrigger> schedulableTriggers = new ArrayList<S2SCronTrigger>();

        for (S2SCronTrigger s2SCronTrigger : triggers) {
            try {
                cronExpression = new CronExpression(s2SCronTrigger.getCronExpression());
                if (cronExpression.getNextValidTimeAfter(new Date()) == null) {
                    // The cron expression is valid, but will never trigger.
                    LOG.info(s2SCronTrigger.getCronExpression() + " not valid cronexpression. The job will not be scheduled.");
                    continue;
                }
            }
            catch (ParseException e) {
                LOG.info(s2SCronTrigger.getCronExpression() + " not valid cronexpression. The job will not be scheduled.");
                continue;
            }
            schedulableTriggers.add(s2SCronTrigger);
        }
        super.setTriggers(schedulableTriggers.toArray(new S2SCronTrigger[0]));
    }
}
