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
package org.kuali.kra.s2s.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
//import org.kuali.core.mail.MailMessage;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.polling.MailInfo;
import org.kuali.kra.s2s.polling.PollingInfo;
import org.kuali.kra.s2s.polling.SchedulerReader;
import org.kuali.kra.s2s.polling.StatusInfo;
import org.kuali.kra.s2s.polling.TaskInfo;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.service.SchedulerService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kns.mail.MailMessage;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * This class is used to start the S2S Scheduler for polling proposal submission status from Grants.Gov. It schedules all the jobs
 * listed in the configuration file S2SScheduler.xml as per the scheduling interval
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SchedulerServiceImpl implements SchedulerService {
    private boolean started;
    private static final Logger LOG = Logger.getLogger(SchedulerServiceImpl.class);
    // private static SchedulerServiceImpl instance;
    private Scheduler scheduler = null;
    private S2SUtilService s2SUtilService;
    private static final String KEY_JOB_SCHEDULER = "jobscheduler";
    private static final String KEY_ENABLING_KEY = "enablingkey";
    private static final String KEY_ENABLING_VALUE = "1";
    private static final String SCHEDULER_GROUP = "S2SSchedulerGroup";
    private static final String KEY_TASK = "task";
    private static final String KEY_ID = "id";
    private static final String KEY_POLLING_INTERVAL = "pollinginterval";
    private static final String KEY_MAILING_INTERVAL = "mailinterval";
    private static final String KEY_STOP_POLLING_INTERVAL = "stoppollinterval";
    private static final String KEY_MAIL = "mail";
    private static final String KEY_ADDRESS_TO = "to";
    private static final String KEY_ADDRESS_CC = "cc";
    private static final String KEY_ADDRESS_BCC = "bcc";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_FOOTER = "footer";
    private static final String KEY_DUNS_NUMBER = "dunsnumber";
    private static final String KEY_STATUS = "status";
    private static final String KEY_CODE = "code";
    private static final String KEY_VALUE = "value";

    /**
     * 
     * @see org.kuali.kra.s2s.service.SchedulerService#startAllServices()
     */
    //TODO: Declarations should be outside of the loops
    public synchronized void startAllServices() {
        if (started){
            return;
        }
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Hashtable<String, Element> elements;
        try {
            scheduler = schedulerFactory.getScheduler();
            elements = SchedulerReader.getScheduleNodes();
        }
        catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
            return;
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return;
        }
        Enumeration<String> keys = elements.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            Element schNode = (Element) elements.get(key);
            String taskClassName = schNode.getAttribute(KEY_JOB_SCHEDULER);
            String enablingKey = schNode.getAttribute(KEY_ENABLING_KEY);
            String enabledStr = s2SUtilService.getParameterValue(enablingKey);
            if (enabledStr.equals("") || !enabledStr.trim().equals(KEY_ENABLING_VALUE)) {
                continue;
            }
            try {
                TaskInfo[] tasks = read(schNode);
                for (int ti = 0; ti < tasks.length; ti++) {
                    TaskInfo taskInfo = tasks[ti];
                    try {
                        JobDetail jobDetail = new JobDetail(taskInfo.getTaskId(), SCHEDULER_GROUP, Class.forName(taskClassName));
                        jobDetail.getJobDataMap().put(S2SConstants.TASK_INFO, taskInfo);
                        SimpleTrigger simpleTrigger = new SimpleTrigger(taskInfo.getTaskId(), SCHEDULER_GROUP, new Date(), null,
                            SimpleTrigger.REPEAT_INDEFINITELY, 1000 * 60 * taskInfo.getPollingInterval());
                        scheduler.scheduleJob(jobDetail, simpleTrigger);
                    }
                    catch (Exception ex) {
                        LOG.error(ex.getMessage(), ex);
                        LOG.error("Error while creating schedule for task: " + taskInfo.getTaskId());
                    }
                }
                scheduler.start();
            }
            catch (Exception ex) {
                LOG.error("Error in scheduling job for Node " + key, ex);
            }
        }
        started = true;
    }

    /**
     * 
     * @see org.kuali.kra.s2s.service.SchedulerService#stopAllServices()
     */
    public synchronized void stopAllServices() {
        try {
            scheduler.shutdown();
        }
        catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see org.kuali.kra.s2s.service.SchedulerService#restartAllServices()
     */
    public synchronized void restartAllServices() {
        stopAllServices();
        started = false;
        startAllServices();
    }
    
    /**
     * 
     * This method fetches all the property values and loads them into BO
     * 
     * @param element
     * @return {@link TaskInfo} array
     */
    //TODO: Declarations should be outside of the loops
    private TaskInfo[] read(Element element) {
        NodeList taskList = element.getElementsByTagName(KEY_TASK);
        int taskSize = taskList.getLength();
        TaskInfo taskInfo[] = new TaskInfo[taskSize];
        for (int index = 0; index < taskSize; index++) {
            Element taskNode = (Element) taskList.item(index);
            String taskId = taskNode.getAttribute(KEY_ID);
            PollingInfo pollingInfo = new PollingInfo();
            pollingInfo.setTaskId(taskId);
            pollingInfo.setPollingInterval(taskNode.getAttribute(KEY_POLLING_INTERVAL));
            pollingInfo.setMailInterval(taskNode.getAttribute(KEY_MAILING_INTERVAL));
            pollingInfo.setStopPollInterval(taskNode.getAttribute(KEY_STOP_POLLING_INTERVAL));

            NodeList mailList = taskNode.getElementsByTagName(KEY_MAIL);
            int mlLength = mailList.getLength();

            List<MailInfo> mailInfoList = new ArrayList<MailInfo>(mlLength);
            for (int k = 0; k < mlLength; k++) {
                Element mailNode = (Element) mailList.item(k);
                MailInfo mailInfo = new MailInfo();
                MailMessage mailMessage = mailInfo.getMailMessage();
                mailMessage.addToAddress(mailNode.getAttribute(KEY_ADDRESS_TO));
                mailMessage.addCcAddress(mailNode.getAttribute(KEY_ADDRESS_CC));
                mailMessage.addBccAddress(mailNode.getAttribute(KEY_ADDRESS_BCC));
                mailMessage.setMessage(mailNode.getAttribute(KEY_MESSAGE));
                mailMessage.setSubject(mailNode.getAttribute(KEY_SUBJECT));
                mailInfo.setMailMessage(mailMessage);
                mailInfo.setFooter(mailNode.getAttribute(KEY_FOOTER));
                mailInfo.setDunsNumber(mailNode.getAttribute(KEY_DUNS_NUMBER));
                mailInfoList.add(mailInfo);
            }
            pollingInfo.setMailInfoList(mailInfoList);

            NodeList statusList = taskNode.getElementsByTagName(KEY_STATUS);
            int stLength = statusList.getLength();
            StatusInfo statusArray[] = new StatusInfo[stLength];
            for (int j = 0; j < stLength; j++) {
                Element statusNode = (Element) statusList.item(j);
                StatusInfo status = new StatusInfo();
                status.setCode(statusNode.getAttribute(KEY_CODE));
                status.setValue(statusNode.getAttribute(KEY_VALUE));
                statusArray[j] = status;
            }
            pollingInfo.setStatuses(statusArray);
            taskInfo[index] = pollingInfo;
        }
        return taskInfo;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.stopAllServices();
    }

    /**
     * Sets the s2SUtilService attribute value.
     * 
     * @param generatorUtilService The s2SUtilService to set.
     */
    public synchronized void sets2SUtilService(S2SUtilService s2SUtilService) {
        this.s2SUtilService = s2SUtilService;
        // Services are started from here instead of constructor. This is because, the method startAllServices uses service
        // S2SUtilService. If the method is called from constructor, S2SUtilService would not yet be injected into
        // this service and results into NullPointerException
        this.startAllServices();
        LOG.info("Scheduler started");
    }
}
