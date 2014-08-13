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
package org.kuali.coeus.common.framework.medusa;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MedusaAjaxAction extends KualiDocumentActionBase {

    private MedusaService medusaService;
    protected  MedusaService getMedusaService (){
        if (medusaService == null)
            medusaService = KcServiceLocator.getService(MedusaService.class);
        return medusaService;
    }
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MedusaForm medusaForm = (MedusaForm)form;
        medusaForm.getMedusaBean().setCurrentNode(getMedusaService().getMedusaNode(medusaForm.getMedusaBean().getModuleName(), medusaForm.getMedusaBean().getModuleIdentifier()));
        
        return super.execute(mapping, medusaForm, request, response);
    }
    
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MedusaForm medusaForm = (MedusaForm)form;
        medusaForm.setCommand("displayDocSearchView");

        MedusaNode node = getMedusaService().getMedusaNode(medusaForm.getMedusaBean().getModuleName(), medusaForm.getMedusaBean().getModuleIdentifier());
        
        if (StringUtils.equals(node.getType(), "IP")) {
            InstitutionalProposal proposal = (InstitutionalProposal)node.getBo();
            medusaForm.setDocId(proposal.getInstitutionalProposalDocument().getDocumentNumber());
        } else if (StringUtils.equals(node.getType(), "award")) {
            Award award = (Award)node.getBo();
            medusaForm.setDocId(award.getAwardDocument().getDocumentNumber());
        } else if (StringUtils.equals(node.getType(), "DP")) {
            DevelopmentProposal devProposal = (DevelopmentProposal)node.getBo();
            medusaForm.setDocId(devProposal.getProposalDocument().getDocumentNumber());
        }else if (StringUtils.equals(node.getType(), "subaward")) {
            SubAward subAward = (SubAward)node.getBo();
            medusaForm.setDocId(subAward.getSubAwardDocument().getDocumentNumber());
        }
            
        return super.docHandler(mapping, form, request, response);
    }
}
