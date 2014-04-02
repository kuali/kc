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
package org.kuali.coeus.propdev.impl.auth.perm;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;

/**
 * The Proposal Role Template Service.
 */
public interface ProposalRoleTemplateService {

    /**
     * Adds users to the proposal giving them proposal roles based upon a set
     * of templates.  Administrators can assign users to proposal roles within
     * a specific unit.  When a proposal is created, those users are added to
     * the proposal corresponding to the proposal's lead unit.
     * @param doc
     */
    public void addUsers(ProposalDevelopmentDocument doc);
    
    public void initializeProposalUsers(ProposalDevelopmentDocument doc);
}
