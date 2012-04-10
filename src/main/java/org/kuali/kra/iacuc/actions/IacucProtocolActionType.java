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
package org.kuali.kra.iacuc.actions;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class IacucProtocolActionType extends KraPersistableBusinessObjectBase { 


    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7507534134436245199L;
    private Integer protocolActionTypeCode; 
    private String description; 
    private boolean triggerSubmission; 
    private boolean triggerCorrespondence; 
    
    
    public IacucProtocolActionType() { 

    } 
    
    public Integer getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public void setProtocolActionTypeCode(Integer protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getTriggerSubmission() {
        return triggerSubmission;
    }

    public void setTriggerSubmission(boolean triggerSubmission) {
        this.triggerSubmission = triggerSubmission;
    }

    public boolean getTriggerCorrespondence() {
        return triggerCorrespondence;
    }

    public void setTriggerCorrespondence(boolean triggerCorrespondence) {
        this.triggerCorrespondence = triggerCorrespondence;
    }

    
}