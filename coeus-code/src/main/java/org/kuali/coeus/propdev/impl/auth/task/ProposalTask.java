/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.auth.task;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.infrastructure.TaskGroupName;

/**
 * A Proposal Task is a task that performs an action against a
 * Proposal Development Document.  To assist authorization, the
 * Proposal Development Document is available.
 */
public final class ProposalTask extends Task {
    
    private ProposalDevelopmentDocument document;
    public static final String CREATE_IRB_PROTOCOL_FROM_PROPOSAL = "createIrbProtocolFromProposal";
    public static final String CREATE_IACUC_PROTOCOL_FROM_PROPOSAL = "createIacucProtocolFromProposal";
    
    /**
     * Constructs a ProposalTask.
     * @param taskName the name of the task
     * @param document the Proposal Development Document
     */
    public ProposalTask(String taskName, ProposalDevelopmentDocument document) {
        super(TaskGroupName.PROPOSAL, taskName);
        this.document = document;
    }

    /**
     * Get the Proposal Development Document.
     * @return the Proposal Development Document
     */
    public ProposalDevelopmentDocument getDocument() {
        return document;
    }
}
