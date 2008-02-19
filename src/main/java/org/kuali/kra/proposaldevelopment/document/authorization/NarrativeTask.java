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

import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * A Narrative Task is a task that corresponds to
 * task on a particular Narrative.
 */
public class NarrativeTask extends ProposalTask {

    private int narrativeIndex;
    
    /**
     * Constructs a NarrativeTask.
     * @param taskName the name of the task
     * @param document the Proposal Development Document
     * @param narrativeIndex the index of the narrative in the proposal
     */
    public NarrativeTask(String taskName, ProposalDevelopmentDocument document, int narrativeIndex) {
        super(taskName, document);
        this.narrativeIndex = narrativeIndex;
    }

    /**
     * Get the Narrative Index.
     * @return the Narrative Index
     */
    public int getNarrativeIndex() {
        return narrativeIndex;
    }
    
    /**
     * Get the Narrative.
     * @return the Narrative
     */
    public Narrative getNarrative() {
        return getProposalDevelopmentDocument().getNarrative(narrativeIndex);
    }
}
