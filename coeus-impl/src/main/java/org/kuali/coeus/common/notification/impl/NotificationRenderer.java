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
