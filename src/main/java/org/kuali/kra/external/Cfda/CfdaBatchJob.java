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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.kuali.kra.external.Notifications.service.CfdaNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.ken.exception.InvalidXMLException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.xml.sax.SAXException;

/**
 * This job is triggered by the quartz scheduler to kick off the CFDA table update.
 * Extends QuartzJobBean for Persistence.
 */
public class CfdaBatchJob extends QuartzJobBean {

    private CfdaService cfdaService;
    private String user;
    private CfdaNotificationService cfdaNotificationService;
    private ParameterService parameterService;
    private static final String htmlNewLine = "<BR>";
    private static final Log LOG = LogFactory.getLog(CfdaBatchJob.class);
    private static final String title = "CFDA batch job result";

    /*
     * This is the method that is called by the Quartz job scheduler.
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        StringBuilder message = new StringBuilder();
        // The cron job runs as this user
        UserSession userSession = new UserSession(user);
        GlobalVariables.setUserSession(userSession);        
        CfdaUpdateResults updateResults = new CfdaUpdateResults();
        try {
            updateResults = cfdaService.updateCfda();
        } catch (IOException e) {
            updateResults.setMessage("Error occured while updating " + e.getMessage());
        }
        message.append("Message: " + updateResults.getMessage() + htmlNewLine);
        message.append("NumberOfRecordsDeactivatedBecauseNoLongerOnWebSite: " 
                        + updateResults.getNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite() + htmlNewLine);
        message.append("NumberOfRecordsInKcDatabase: " + updateResults.getNumberOfRecordsInKcDatabase() + htmlNewLine);
        message.append("NumberOfRecordsNewlyAddedFromWebSite: " + updateResults.getNumberOfRecordsNewlyAddedFromWebSite() + htmlNewLine);
        message.append("NumberOfRecordsNotUpdatedBecauseManual: " + updateResults.getNumberOfRecordsNotUpdatedBecauseManual() 
                        + htmlNewLine);
        message.append("NumberOfRecordsNotUpdatedForHistoricalPurposes: " 
                        + updateResults.getNumberOfRecordsNotUpdatedForHistoricalPurposes() + htmlNewLine);
        message.append("NumberOfRecordsReActivated: " + updateResults.getNumberOfRecordsReActivated() + htmlNewLine);
        message.append("NumberOfRecordsRetrievedFromWebSite: " + updateResults.getNumberOfRecordsRetrievedFromWebSite() + htmlNewLine);
        message.append("NumberOfRecordsUpdatedBecauseAutomatic: " + updateResults.getNumberOfRecordsUpdatedBecauseAutomatic() + htmlNewLine);
        LOG.info(message.toString());
        
        String recipient = getRecipient();
        // Send notification only if recipient has been set in the param
        if (ObjectUtils.isNotNull(recipient) && !StringUtils.isEmpty(recipient)) {
            try {
                cfdaNotificationService.setTitle(title);
                cfdaNotificationService.setSender(user);
                cfdaNotificationService.setMessage(message.toString());
                cfdaNotificationService.setRecipient(recipient);
                cfdaNotificationService.sendNotification();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (InvalidXMLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * This method gets the recipient specified in the parameter
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
     * This is injected into the scheduler context. 
     * @param parameterService
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    /**
     * This is injected into the scheduler context by spring.
     * @param kcNotificationService
     */
    public void setCfdaNotificationService(CfdaNotificationService kcNotificationService) {
        this.cfdaNotificationService = kcNotificationService;
    }
    
    /**
     * This is injected into the scheduler context by spring.
     * @param cfdaService
     */
    public void setCfdaService(CfdaService cfdaService) {
        this.cfdaService = cfdaService;
    }

}
