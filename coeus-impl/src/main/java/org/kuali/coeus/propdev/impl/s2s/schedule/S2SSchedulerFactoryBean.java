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
package org.kuali.coeus.propdev.impl.s2s.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * This class is required in order to stop scheduling of tasks whose cron triggers are either invalid or are those that will not
 * fire ever again.
 */
public class S2SSchedulerFactoryBean extends SchedulerFactoryBean {
    private static final Log LOG = LogFactory.getLog(S2SSchedulerFactoryBean.class);
    private List<CronTrigger> triggers;

    /**
     * Gets the triggers attribute.
     * 
     * @return Returns the triggers.
     */
    public List<CronTrigger> getTriggers() {
        return triggers;
    }

    /**
     * This method checks if cron expressions are valid and will run. Only then, they are passed on to super class for scheduling
     * 
     * @param triggers The triggers to set.
     */
    public void setTriggers(List<CronTrigger> triggers) {
        this.triggers = triggers;

        CronExpression cronExpression;
        List<CronTrigger> schedulableTriggers = new ArrayList<CronTrigger>();

        for (CronTrigger cronTrigger : triggers) {
            try {
                cronExpression = new CronExpression(cronTrigger.getCronExpression());
                if (cronExpression.getNextValidTimeAfter(new Date()) == null) {
                    // The cron expression is valid, but will never trigger.
                    LOG.info(cronTrigger.getCronExpression() + " not valid cronexpression. The job will not be scheduled.");
                    continue;
                }
            }
            catch (ParseException e) {
                LOG.info(cronTrigger.getCronExpression() + " not valid cronexpression. The job will not be scheduled.");
                continue;
            }
            schedulableTriggers.add(cronTrigger);
        }
        super.setTriggers(schedulableTriggers.toArray(new CronTrigger[0]));
    }
}
