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
package org.kuali.kra.proposaldevelopment.rule;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

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
