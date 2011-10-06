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
package org.kuali.kra.negotiations.auth;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.negotiations.document.NegotiationDocument;

/**
 * 
 * This class manages the attributes needed for a negotiation task.
 */
public class NegotiationTask extends Task {
    
    private NegotiationDocument negotiationDocument;

    /**
     * 
     * Constructs a NegotiationTask.java.
     * @param taskName
     * @param negotiation
     */
    public NegotiationTask(String taskName, NegotiationDocument negotiationDocument) {
        super(TaskGroupName.NEGOTIATION, taskName);
        this.negotiationDocument = negotiationDocument;
    }
    
    public NegotiationDocument getNegotiationDocument() {
        return negotiationDocument;
    }

    public void setNegotiationDocument(NegotiationDocument negotiationDocument) {
        this.negotiationDocument = negotiationDocument;
    }    
}