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
package org.kuali.kra.coi.notification;

import java.io.Serializable;

import org.kuali.kra.coi.CoiDisclosure;

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
