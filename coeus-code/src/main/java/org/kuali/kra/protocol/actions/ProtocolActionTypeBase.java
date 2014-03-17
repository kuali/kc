/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.actions;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * A ProtocolBase Action Type refers to the type of actions that an
 * that can be performed against a ProtocolBase document.
 */
@SuppressWarnings("serial")
public abstract class ProtocolActionTypeBase extends KcPersistableBusinessObjectBase {

    private String protocolActionTypeCode;

    private String description;

    private boolean triggerSubmission;

    private boolean triggerCorrespondence;

    private boolean finalActionForBatchCorrespondence;


    public ProtocolActionTypeBase() {
    }

    public void setProtocolActionTypeCode(String protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public String getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTriggerSubmission(boolean triggerSubmission) {
        this.triggerSubmission = triggerSubmission;
    }

    public boolean getTriggerSubmission() {
        return triggerSubmission;
    }

    public void setTriggerCorrespondence(boolean triggerCorrespondence) {
        this.triggerCorrespondence = triggerCorrespondence;
    }

    public boolean getTriggerCorrespondence() {
        return triggerCorrespondence;
    }

    public boolean isFinalActionForBatchCorrespondence() {
        return finalActionForBatchCorrespondence;
    }

    public void setFinalActionForBatchCorrespondence(boolean finalActionForBatchCorrespondence) {
        this.finalActionForBatchCorrespondence = finalActionForBatchCorrespondence;
    }
}
