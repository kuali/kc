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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.Constants;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.PropLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

import edu.iu.uis.eden.exception.WorkflowException;

public class ProposalDevelopmentProposalAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAction.class);

    public ActionForward addLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocation() == null) {
            proposalDevelopmentForm.getProposalDevelopmentDocument().setPropLocation(new ArrayList());
        }
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocation().add(proposalDevelopmentForm.getProposalDevelopmentDocument().getNewPropLocation());
        proposalDevelopmentForm.getProposalDevelopmentDocument().setNewPropLocation(new PropLocation());
        return mapping.findForward("basic");
    }
    public ActionForward deleteLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocation().remove(getLineToDelete(request));
        return mapping.findForward("basic");
    }
    public ActionForward clearAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        int index= getLineToDelete(request);

        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocation().get(index).setRolodexId(new Integer(0));
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocation().get(index).setRolodex(new Rolodex());
        
        return mapping.findForward("basic");
    }
    
}
