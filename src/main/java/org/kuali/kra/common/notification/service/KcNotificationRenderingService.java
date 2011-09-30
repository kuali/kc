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
package org.kuali.kra.common.notification.service;

import java.util.Map;

public interface KcNotificationRenderingService {

    /**
     * 
     * This method renders the text message using the default replacement parameters
     * @param text the message to be rendered
     * @return the message with all possible search and replace parameters filled in.
     */
    public String render(String text);
    
    /**
     * 
     * This method...
     * @param text the message to be rendered
     * @param replacementParameters the replacement parameters you want to use against the message
     * @return the message with all possible search and replace parameters filled in.
     */
    public String render(String text, Map<String,String> replacementParameters);
    
    /**
     * 
     * This method returns the default replacement parameters
     * @return the map of replacement parameters and their values
     */
    public Map<String, String> getReplacementParameters();
}
