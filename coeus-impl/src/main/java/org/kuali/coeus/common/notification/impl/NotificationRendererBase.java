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
package org.kuali.coeus.common.notification.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
    private transient ConfigurationService kualiConfigurationService;
    
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

    @Override
    public Map<String, String> getDefaultReplacementParameters() {

        Map<String, String> params = new HashMap<>();
        if (GlobalVariables.getUserSession() != null) {
            params.put(USER_FULLNAME, getKcPersonService().getKcPersonByPersonId(GlobalVariables.getUserSession().getPrincipalId()).getFullName());
        } else {
            params.put(USER_FULLNAME, StringUtils.EMPTY);
        }
        params.put(DOCHANDLER_PREFIX, getDocumentLocation());
        params.put(APP_LINK_PREFIX, getApplicationLinkPrefix());
        return params;
    }


    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
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
        String retVal = getKualiConfigurationService().getPropertyValueAsString(APPLICATION_URL_PARM);
        if (retVal == null) {
            retVal = "..";
        }
        return retVal;
    }

    protected ConfigurationService getKualiConfigurationService() {
        if (kualiConfigurationService == null) {
            kualiConfigurationService = CoreApiServiceLocator.getKualiConfigurationService();
        }
        return kualiConfigurationService;
    }

}
