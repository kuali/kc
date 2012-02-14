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
package org.kuali.kra.common.notification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Defines a base class for rendering Notification messages.
 */
public abstract class NotificationRendererBase implements NotificationRenderer, Serializable {

    private static final long serialVersionUID = 7355369114077509177L;

    public static final String USER_FULLNAME = "{USER_FULLNAME}";
    public static final String DOCHANDLER_PREFIX = "{DOCUMENT_PREFIX}";
    
    public static final String DOCHANDLER_SCHM_PROP = "application.http.scheme";
    public static final String DOCHANDLER_HOST_PROP = "application.host";
    public static final String DOCHANDLER_PORT_PROP = "http.port";
    public static final String DOCHANDLER_CODE_PROP = "app.code";
    public static final String DOCHANDLER_ENVR_PROP = "environment";

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
    private String render(String text, Map<String,String> replacementParameters) { 
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
        String result = null;
        String schm = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_SCHM_PROP);
        String host = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_HOST_PROP);
        String port = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_PORT_PROP);
        String code = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_CODE_PROP);
        String envr = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_ENVR_PROP);
        if (schm == null || host == null || code == null || envr == null) {
            result = "..";   // default is to back up URL before KEN (relative to base at this server)
        } else {
            result = schm + "://" + host + (!StringUtils.isEmpty(port) ? ":" + port : "") + "/" + code + "-" + envr;
        }
        return result;
    }

    private ConfigurationService getKualiConfigurationService() {
        if (kualiConfigurationService == null) {
            kualiConfigurationService = KRADServiceLocator.getKualiConfigurationService();
        }
        return kualiConfigurationService;
    }

}
