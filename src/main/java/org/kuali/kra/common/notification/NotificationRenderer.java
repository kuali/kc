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

import java.util.Map;

/**
 * Defines the text renderer for Notifications.
 */
public interface NotificationRenderer {

    /**
     * Renders the message in {@code text} using default replacement parameters.
     * 
     * @param text the message to be rendered
     * @return the message with all possible search and replace parameters filled in
     */
    String render(String text);
    
    /**
     * Returns the default replacement parameters for the renderer.
     * 
     * @return the default replacement parameters for the renderer
     */
    Map<String, String> getDefaultReplacementParameters();

}