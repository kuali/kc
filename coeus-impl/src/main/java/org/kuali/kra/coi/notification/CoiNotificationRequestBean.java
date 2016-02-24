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

import java.io.Serializable;

public class CoiNotificationRequestBean implements Serializable {

    private CoiDisclosure coiDisclosure;
    private String actionType;
    private String description;
    private String docNumber;

    public CoiNotificationRequestBean(CoiDisclosure coiDisclosure, String actionType, String description) {
        this.coiDisclosure = coiDisclosure;
        this.actionType = actionType;
        this.description = description;
    }
    
    public CoiNotificationRequestBean(CoiDisclosure coiDisclosure, String actionType, String description, String docNumber) {
        this(coiDisclosure, actionType, description);
        this.docNumber = docNumber;
    }
    
    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

}
