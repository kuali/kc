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

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * This job is triggered by the quartz scheduler to kick off the CFDA table update.
 * Extends QuartzJobBean for Persistence.
 */
public class CfdaBatchJob extends QuartzJobBean {
    
    private static final String BREAK = "<BR>";
    private static final String CONTEXT_NAME = "CFDA Batch Job";
    private static final String SUBJECT = "CFDA batch job result";
    
    private static final Log LOG = LogFactory.getLog(CfdaBatchJob.class);
    
    private String user;

    private CfdaService cfdaService;
    private ParameterService parameterService;
    private KcNotificationService kcNotificationService;

    /*
     * This is the method that is called by the Quartz job scheduler.
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        StringBuilder builder = new StringBuilder();
        // The cron job runs as this user
        UserSession userSession = new UserSession(user);
        GlobalVariables.setUserSession(userSession);        
        CfdaUpdateResults updateResults = new CfdaUpdateResults();
        try {
            updateResults = cfdaService.updateCfda();
        } catch (IOException e) {
            updateResults.setMessage("Error occured while updating " + e.getMessage());
        }
        builder.append("Message: " + updateResults.getMessage() + BREAK);
        builder.append("NumberOfRecordsDeactivatedBecauseNoLongerOnWebSite: " + updateResults.getNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite() + BREAK);
        builder.append("NumberOfRecordsInKcDatabase: " + updateResults.getNumberOfRecordsInKcDatabase() + BREAK);
        builder.append("NumberOfRecordsNewlyAddedFromWebSite: " + updateResults.getNumberOfRecordsNewlyAddedFromWebSite() + BREAK);
        builder.append("NumberOfRecordsNotUpdatedBecauseManual: " + updateResults.getNumberOfRecordsNotUpdatedBecauseManual() + BREAK);
        builder.append("NumberOfRecordsNotUpdatedForHistoricalPurposes: " + updateResults.getNumberOfRecordsNotUpdatedForHistoricalPurposes() + BREAK);
        builder.append("NumberOfRecordsReActivated: " + updateResults.getNumberOfRecordsReActivated() + BREAK);
        builder.append("NumberOfRecordsRetrievedFromWebSite: " + updateResults.getNumberOfRecordsRetrievedFromWebSite() + BREAK);
        builder.append("NumberOfRecordsUpdatedBecauseAutomatic: " + updateResults.getNumberOfRecordsUpdatedBecauseAutomatic() + BREAK);
        
        String message = builder.toString();
        
        LOG.info(message);
        
        String recipient = getRecipient();
        // Send notification only if recipient has been set in the param
        if (ObjectUtils.isNotNull(recipient) && !StringUtils.isEmpty(recipient)) {
            kcNotificationService.sendNotification(CONTEXT_NAME, SUBJECT, message, Collections.singletonList(recipient));
        }
    }
    
    /**
     * This method gets the recipient specified in the parameter.
     * 
     * @return
     */
    protected String getRecipient() {
        String recipient = parameterService.getParameterValue(Constants.PARAMETER_MODULE_AWARD, 
                Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.CFDA_BATCH_NOTIFICATION_RECIPIENT_PARAMETER);  
        return recipient;
    }
    
    /**
     * Injected by spring.
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }
    
    /**
     * This is injected into the scheduler context by spring.
     * @param cfdaService
     */
    public void setCfdaService(CfdaService cfdaService) {
        this.cfdaService = cfdaService;
    }

    /**
     * This is injected into the scheduler context by spring.
     * @param kcNotificationService
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * This is injected into the scheduler context by spring.
     * @param kcNotificationService
     */
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

}