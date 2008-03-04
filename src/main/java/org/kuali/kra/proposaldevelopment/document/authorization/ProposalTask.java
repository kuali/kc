/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.document.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * A Proposal Task is a task that performs an action against a
 * Proposal Development Document.  To assist authorization, the
 * Proposal Development Document is available.
 */
public class ProposalTask extends Task {

    private ProposalDevelopmentDocument document;
    
    /**
     * Constructs a ProposalTask.
     * @param actionName the name of the action
     * @param taskName the name of the task
     * @param doc the Proposal Development Document
     */
    public ProposalTask(String actionName, String taskName, ProposalDevelopmentDocument document) {
        super(actionName, taskName);
        this.document = document;
    }

    /**
     * Get the Proposal Development Document.
     * @return the Proposal Development Document
     */
    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return document;
    }
}
