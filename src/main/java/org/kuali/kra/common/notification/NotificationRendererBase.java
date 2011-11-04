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
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Defines a base class for rendering Notification messages.
 */
public abstract class NotificationRendererBase implements NotificationRenderer, Serializable {

    private static final long serialVersionUID = 7355369114077509177L;

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

}