/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * A Narrative Task is a task that corresponds to
 * task on a particular Narrative.
 */
public class NarrativeTask extends Task {
   
    private ProposalDevelopmentDocument document;
    private Narrative narrative;
    
    /**
     * Constructs a NarrativeTask.
     * @param taskName the name of the task
     * @param document the Proposal Development Document
     * @param narrative the narrative
     */
    public NarrativeTask(String taskName, ProposalDevelopmentDocument document, Narrative narrative) {
        super(TaskGroupName.NARRATIVE, taskName);
        this.document = document;
        this.narrative = narrative;
    }

    /**
     * Get the Proposal Development Document.
     * @return the Proposal Development Document
     */
    public ProposalDevelopmentDocument getDocument() {
        return document;
    }
    
    /**
     * Get the Narrative.
     * @return the Narrative
     */
    public Narrative getNarrative() {
        return narrative;
    }
}
