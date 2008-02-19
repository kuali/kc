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
package org.kuali.kra.proposaldevelopment.rule;

import java.util.List;

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * Defines the Business Rule for processing modifications to the narrative user rights.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface NewNarrativeUserRightsRule extends BusinessRule {
    
    /**
     * Determines the legality of editing a Narrative's user rights.
     * 
     * @param document the proposal development document.
     * @param newNarrativeUserRights the new narrative user rights
     * @param narrativeIndex the index of the narrative in the document
     * @return true if the modification is valid; otherwise false.
     */
    public boolean processNewNarrativeUserRightsBusinessRules(ProposalDevelopmentDocument document, 
                                                              List<NarrativeUserRights> newNarrativeUserRights,
                                                              int narrativeIndex);
}
