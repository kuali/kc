/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.institutionalproposal.service;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;


public interface InstitutionalProposalNoteAttachmentService {

    /**
     * This method is used to add a new Note/Attachment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public abstract ActionForward addNote(ActionMapping mapping, InstitutionalProposalForm form) throws Exception;

    /**
     * This method is used to delete an existing note/attachment
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public abstract ActionForward deleteNote(ActionMapping mapping, InstitutionalProposalForm form, int lineToDelete) throws Exception;

}
