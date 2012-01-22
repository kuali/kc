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
import org.kuali.rice.core.api.CoreApiServiceLocator;
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
    public static final String DOCHANDLER_PREFIX_PROPERTY = "appserver.url";
    private transient KcPersonService kcPersonService;
    
    public static final String[] REPLACEMENT_PARAMETERS = new String[] { USER_FULLNAME,
                                                                         DOCHANDLER_PREFIX,
                                                                       };
    
    
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

        String[] replacementParameters = REPLACEMENT_PARAMETERS;
        Map<String, String> params = new HashMap<String, String>();
        
        String key = null;
        for (int i = 0; i < replacementParameters.length; i++) {
            key = replacementParameters[i];
            if (StringUtils.equals(key, USER_FULLNAME)) {
                if (GlobalVariables.getUserSession() != null) {
                    params.put(key, getKcPersonService().getKcPersonByPersonId(GlobalVariables.getUserSession().getPrincipalId()).getFullName());
                }
            } else if (StringUtils.equals(key, DOCHANDLER_PREFIX)) {
                params.put(key, getDocumentLocation());
            }
        }
        
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
        String result = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_PREFIX_PROPERTY);
        if (result == null) {
            result = "..";   // default is current relative location (relative to base at this server)
        }
        return result;
    }

    private ConfigurationService getKualiConfigurationService() {
        return KRADServiceLocator.getKualiConfigurationService();
    }

}