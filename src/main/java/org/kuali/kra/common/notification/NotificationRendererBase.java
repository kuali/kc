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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.notification.IRBReplacementParameters;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Defines a base class for rendering Notification messages.
 */
public abstract class NotificationRendererBase implements NotificationRenderer, Serializable {

    private static final long serialVersionUID = 7355369114077509177L;

    public static final String USER_FULLNAME = "{USER_FULLNAME}";
    public static final String APPLICATION_URL = "{APPLICATION_URL}";
    public static final String APPLICATION_URL_PARM = "application.url";
    private transient KcPersonService kcPersonService;
    
    public static final String[] REPLACEMENT_PARAMETERS = new String[] { USER_FULLNAME,
                                                                         APPLICATION_URL
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
            } else if (StringUtils.equals(key, APPLICATION_URL)) {
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

    private String getDocumentLocation() {
        String result = getKualiConfigurationService().getPropertyString(APPLICATION_URL_PARM);
        if (result == null) {
            result = "..";   // default is current relative location
        }
        return result;
    }

    private KualiConfigurationService getKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }

}