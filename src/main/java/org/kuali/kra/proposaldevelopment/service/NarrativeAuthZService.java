/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.service;

import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class...
 */
public interface NarrativeAuthZService {
    
    public NarrativeRight authorize(Narrative narrative,String userId);

    /**
     * Gets the default narrative right for a user.  The default narrative
     * right is the highest right that a user can have based upon his/her
     * permissions.
     * 
     * @param username the user's unique username
     * @param doc the Proposal Development Document
     * @return the user's default narrative right
     */
    public NarrativeRight getDefaultNarrativeRight(String username, ProposalDevelopmentDocument doc);
}
