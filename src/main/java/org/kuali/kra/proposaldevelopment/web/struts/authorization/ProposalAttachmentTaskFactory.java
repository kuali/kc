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
package org.kuali.kra.proposaldevelopment.web.struts.authorization;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

/**
 * The Proposal Attachment Task Factory will create a Narrative Task using
 * an proposal attachment narrative.
 */
public class ProposalAttachmentTaskFactory extends NarrativeTaskFactory {
    
    /**
     * @see org.kuali.kra.proposaldevelopment.web.struts.authorization.NarrativeTaskFactory#getNarrative(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
     */
    protected Narrative getNarrative(ActionForm form, HttpServletRequest request) {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        int index = getLineNumber(request);
        return proposalDevelopmentForm.getProposalDevelopmentDocument().getNarrative(index);
    }
}
