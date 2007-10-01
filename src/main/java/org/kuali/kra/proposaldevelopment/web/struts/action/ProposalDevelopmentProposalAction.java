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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.PropLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.SponsorService;

public class ProposalDevelopmentProposalAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument=((ProposalDevelopmentForm)form).getProposalDevelopmentDocument();
        if (proposalDevelopmentDocument.getOrganizationId()!=null && proposalDevelopmentDocument.getPropLocations().size()==0) {
            // populate 1st location.  Not sure yet
            PropLocation propLocation=new PropLocation();
            propLocation.setLocation(proposalDevelopmentDocument.getOrganization().getOrganizationName());
            proposalDevelopmentDocument.getPropLocations().add(propLocation);
        }

        // populate the Prime Sponsor Name if we have the code
        // this is necessary since the name is only on the form not the document
        // and it is only populated by a lookup or through AJAX/DWR
        String primeSponsorCode = proposalDevelopmentDocument.getPrimeSponsorCode();
        if (StringUtils.isNotEmpty(primeSponsorCode)) {
            ((ProposalDevelopmentForm)form).setPrimeSponsorName(KraServiceLocator.getService(SponsorService.class).getSponsorName(primeSponsorCode));
        }

        return super.execute(mapping, form, request, response);
    }

    public ActionForward addLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocations().add(proposalDevelopmentForm.getNewPropLocation());
        proposalDevelopmentForm.setNewPropLocation(new PropLocation());
        return mapping.findForward("basic");
    }
    public ActionForward deleteLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO : do we want to put logic to check whether this is the only one
        // or we'll let the business rule handle 'at least one location' rule?
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocations().remove(getLineToDelete(request));
        return mapping.findForward("basic");
    }
    public ActionForward clearAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        int index= getSelectedLine(request);

        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocations().get(index).setRolodexId(new Integer(0));
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropLocations().get(index).setRolodex(new Rolodex());

        return mapping.findForward("basic");
    }

}
