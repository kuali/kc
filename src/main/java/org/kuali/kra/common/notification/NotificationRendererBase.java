/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.common.notification;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines a base class for rendering Notification messages.
 */
public abstract class NotificationRendererBase implements NotificationRenderer, Serializable {

    private static final long serialVersionUID = 7355369114077509177L;

    public static final String USER_FULLNAME = "{USER_FULLNAME}";
    public static final String DOCHANDLER_PREFIX = "{DOCUMENT_PREFIX}";
    public static final String RICE_SERVER_URL = "rice.server.url";
    public static final String APP_LINK_PREFIX = "{APP_LINK_PREFIX}";
    public static final String APPLICATION_URL_PARM = "application.url";

    private transient KcPersonService kcPersonService;
    private ConfigurationService kualiConfigurationService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#render(java.lang.String)
     */
    @Override
    public String render(String text) {
        return render(text, getDefaultReplacementParameters());
    }
    
    /**
     * Renders the {@code text} based on the given default parameters.
     * 
     * @param text the message to be rendered
     * @param replacementParameters the parameters to replace in the message
     * @return the message with all the possible search and replace 
     */
    protected String render(String text, Map<String,String> replacementParameters) { 
        for (String key : replacementParameters.keySet()) {
            text = StringUtils.replace(text, key, replacementParameters.get(key));
        }
        
        return text;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#getReplacementParameters()
     */
    public Map<String, String> getDefaultReplacementParameters() {

        Map<String, String> params = new HashMap<String, String>();
        if (GlobalVariables.getUserSession() != null) {
            params.put(USER_FULLNAME, getKcPersonService().getKcPersonByPersonId(GlobalVariables.getUserSession().getPrincipalId()).getFullName());
        }
        params.put(DOCHANDLER_PREFIX, getDocumentLocation());
        params.put(APP_LINK_PREFIX, getApplicationLinkPrefix());
        return params;
    }


    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    protected String getSafeMessage(String key, String message) {
        
        if (StringUtils.isBlank(message)) {
            message = "(No value set for " + key + " in this action)";
        }
        
        return message;
    }

    private String getDocumentLocation() {
        return getKualiConfigurationService().getPropertyValueAsString(RICE_SERVER_URL);
    }
    
    
    private String getApplicationLinkPrefix() {
        String retVal = null;
        retVal = getKualiConfigurationService().getPropertyValueAsString(APPLICATION_URL_PARM);
        if (retVal == null) {
            retVal = "..";
        }
        return retVal;
    }

    private ConfigurationService getKualiConfigurationService() {
        if (kualiConfigurationService == null) {
            kualiConfigurationService = CoreApiServiceLocator.getKualiConfigurationService();
        }
        return kualiConfigurationService;
    }

}
