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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProposalLogMergeAction extends KualiAction {
    
    public ActionForward pageEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalLogMergeForm proposalLogMergeForm = (ProposalLogMergeForm) form;
        String applicationUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.APPLICATION_URL_KEY);
        request.getSession().setAttribute("proposalLogNumber", proposalLogMergeForm.getProposalLogNumber());
        response.sendRedirect("kr/lookup.do?methodToCall=start&businessObjectClassName=" + InstitutionalProposal.class + "&docFormKey=88888888&includeCustomActionUrls=true&returnLocation="
                + applicationUrl + "/mergeProposalLog.do&hideReturnLink=true");
        return null;
    }
    
    public ActionForward mergeToInstitutionalProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String proposalLogNumber = (String) request.getSession().getAttribute("proposalLogNumber");
        request.getSession().removeAttribute("proposalLogNumber");
        getProposalLogService().mergeProposalLog(proposalLogNumber);
        return mapping.findForward("portal");
    }
    
    //http://127.0.0.1:8080/kc-dev/mergeProposalLog.do?methodToCall=getMatchingTemporaryProposals&proposalLogTypeCode=1&piId=10000000002
    public ActionForward getMatchingTemporaryProposals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalLogMergeForm proposalLogMergeForm = (ProposalLogMergeForm) form;
        proposalLogMergeForm.setMatchedProposalLogs(getProposalLogService().getMatchingTemporaryProposalLogs(proposalLogMergeForm.getProposalLogTypeCode(), proposalLogMergeForm.getPiId(), proposalLogMergeForm.getRolodexId()));
        return mapping.findForward("temporaryList");
    }
    
    protected ProposalLogService getProposalLogService() {
        return KcServiceLocator.getService(ProposalLogService.class);
    }
    
    protected ConfigurationService getKualiConfigurationService() {
        return CoreApiServiceLocator.getKualiConfigurationService();
    }

}
