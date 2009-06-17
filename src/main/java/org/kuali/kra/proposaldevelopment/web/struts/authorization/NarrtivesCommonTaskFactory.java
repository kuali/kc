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
package org.kuali.kra.proposaldevelopment.web.struts.authorization;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;



/**
 * The Narratives Common Task Factory will create a Proposal Task.
 */
public class NarrtivesCommonTaskFactory extends NarrativeTaskFactory {

    public Task createTask(ActionForm form, HttpServletRequest request) {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        return new ProposalTask(getTaskName(), proposalDevelopmentForm.getDocument());
    }

    protected Narrative getNarrative(ActionForm form, HttpServletRequest request) {
        // TODO Not sure what to do with this.
        return new Narrative();
    }

}
