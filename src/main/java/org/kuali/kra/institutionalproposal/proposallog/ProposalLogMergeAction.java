/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.proposallog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.KRADConstants;

public class ProposalLogMergeAction extends KualiAction {
    
    public ActionForward pageEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalLogMergeForm proposalLogMergeForm = (ProposalLogMergeForm) form;
        String applicationUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.APPLICATION_URL_KEY);
        request.getSession().setAttribute("proposalLogNumber", proposalLogMergeForm.getProposalLogNumber());
        response.sendRedirect("kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.home.InstitutionalProposal&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=" 
                + applicationUrl + "/mergeProposalLog.do&hideReturnLink=true");
        return null;
    }
    
    public ActionForward mergeToInstitutionalProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String proposalLogNumber = (String) request.getSession().getAttribute("proposalLogNumber");
        request.getSession().removeAttribute("proposalLogNumber");
        getProposalLogService().mergeProposalLog(proposalLogNumber);
        return mapping.findForward("portal");
    }
    
    protected ProposalLogService getProposalLogService() {
        return KraServiceLocator.getService(ProposalLogService.class);
    }
    
    protected ConfigurationService getKualiConfigurationService() {
        return KRADServiceLocator.getKualiConfigurationService();
    }

}
