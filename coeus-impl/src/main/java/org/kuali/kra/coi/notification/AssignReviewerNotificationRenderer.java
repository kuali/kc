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
package org.kuali.kra.coi.notification;

import org.kuali.kra.coi.CoiDisclosure;

import java.util.Map;

/**
 * Renders additional fields for the Disclosure Certified notification.
 */
public class AssignReviewerNotificationRenderer extends CoiNotificationRenderer {


    private static final long serialVersionUID = 8779177260177014284L;
    private String actionTaken;
    
    /**
     * Constructs an Agenda Created notification renderer.
     * 
     * @param protocol
     * @param actionTaken
     */
    public AssignReviewerNotificationRenderer(CoiDisclosure coiDisclosure, String actionTaken) {
        super(coiDisclosure);
        this.actionTaken = actionTaken;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{ACTION_TAKEN}", actionTaken);
        return params;
    }    

}
